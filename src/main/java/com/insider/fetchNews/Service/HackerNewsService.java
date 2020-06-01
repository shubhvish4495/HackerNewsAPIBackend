package com.insider.fetchNews.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.insider.fetchNews.comparator.ScoreComparator;
import com.insider.fetchNews.Entity.CommentReturnEntity;
import com.insider.fetchNews.Entity.HackerNewsEntity;
import com.insider.fetchNews.Entity.HackerNewsReturnEntity;
import com.insider.fetchNews.Entity.UserEntity;
import com.insider.fetchNews.Repository.HackerNewsRepository;
import com.insider.fetchNews.Utility.CommonUtilityFunctions;
import com.insider.fetchNews.Utility.JacksonUtilityFunctions;

/**
 * This class acts a service layer for hacker news, as it fetches 
 * data from hacker new API and converts the data to desired format
 * and then returns it back to the com.insider.fetchNews.com.insider.fetchNews.controller method.
 * 
 * @author Shubham Saurav
 */
@Service
public class HackerNewsService {
	
	private HackerNewsRepository hackerRepo;

	@Autowired
	public void setHackerNewsRepo(HackerNewsRepository repo) {
		this.hackerRepo = repo;	
	}
	
	public HackerNewsRepository getHackerNewsRepo() {
		return hackerRepo;
	}

	private JacksonUtilityFunctions jacksonUtility;
	
	public JacksonUtilityFunctions getJacksonUtility() {
		return jacksonUtility;
	}
	
	@Autowired
	public void setJacksonUtility(JacksonUtilityFunctions jacksonUtility) {
		this.jacksonUtility = jacksonUtility;
	}
	
	private CommonUtilityFunctions commonUtility;

	public CommonUtilityFunctions getCommonUtility() {
		return commonUtility;
	}

	@Autowired
	public void setCommonUtility(CommonUtilityFunctions commonUtility) {
		this.commonUtility = commonUtility;
	}
	

	/**
	 * This method fetches top stories from hacker news API.
	 * @return top stories in JSON format
	 * @throws IOException
	 */
	@Cacheable(value = "topStories")
	public List<HackerNewsReturnEntity> getTopStories() throws IOException {
		HackerNewsEntity response;
		ArrayList<HackerNewsEntity> topStoriesDataArray = new ArrayList<HackerNewsEntity>();

		int[] topStoriesIdArray = hackerRepo.getTopStoriesIds();
		for (int topStoryId : topStoriesIdArray) {
			response = hackerRepo.fetchNews(topStoryId);
			topStoriesDataArray.add(response);
		}
		topStoriesDataArray.sort(new ScoreComparator());
		List<HackerNewsEntity> topTenStories = topStoriesDataArray.subList(topStoriesDataArray.size()-10,topStoriesDataArray.size());
		Collections.reverse(topTenStories);
		return convertToReturnEntity(topTenStories);
	}

	/**
	 * This method converts HackerNewsEntity to HackerNewsReturnEntity
	 * @param topTenStories List of HackerNewsEntity with all the fields
	 * @return List of HackerNewsReturnEntity format to required fields for output object
	 */
	public List<HackerNewsReturnEntity> convertToReturnEntity(List<HackerNewsEntity> topTenStories) {
		List<HackerNewsReturnEntity> listToReturn = new ArrayList<HackerNewsReturnEntity>();
		for(HackerNewsEntity entity: topTenStories) {
			HackerNewsReturnEntity returnEntity =  new HackerNewsReturnEntity(entity.getTitle(), entity.getUrl(), entity.getTime(), entity.getScore(), entity.getBy());
			listToReturn.add(returnEntity);
		}
		return listToReturn;
	}
	
	/**
	 * This method fetches top 10 comments for given story ID.
	 * @param storyId Id of the story to fetch top comments
	 * @return Top Comments for a story in string format
	 * @throws IOException
	 */
	@Cacheable(value = "topComments",key="#storyId")
	public List<CommentReturnEntity> getTopCommentsForStory(Integer storyId) throws IOException {
		HackerNewsEntity newsParent = hackerRepo.fetchNews(storyId);
		if (!newsParent.getType().equals("story")) {
			return null;
		}
		Map<Integer, Integer> commentIdCountMap = new HashMap<Integer, Integer>();
		for(int parentCommentID: newsParent.getKids()) {
			int commentCount = countComments(parentCommentID);
			commentIdCountMap.put(parentCommentID, commentCount);
		}

		Map<Integer, Integer> sortedCommentIDCountMap = commonUtility.sortByValue(commentIdCountMap);
		List<Integer> keys = sortedCommentIDCountMap.entrySet().stream()
				  .map(Map.Entry::getKey)
				  .limit(10)
				  .collect(Collectors.toList());
		return commentReturnEntityString(keys);
	}

	/**
	 * This method counts the number of child comments for the given parent comment.
	 * @param parentCommentID ID of the parent comment
	 * @return count of child comments in a parent comment
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public int countComments(int parentCommentID) throws JsonMappingException, JsonProcessingException {
		HackerNewsEntity commentParent = hackerRepo.fetchNews(parentCommentID);
		if (commentParent.getKids() == null) {
			return 1;
		}else {
			int count = 0;
			for(int childCommentId: commentParent.getKids()) {
				count += countComments(childCommentId);
			}
			return count+1;
		}
	}
	
	/**
	 * This method returns the expected comment object
	 * @param keys List of id's of top comments on a story
	 * @return JSON formatted List of CommentReturnEntity for top comments
	 * @throws IOException
	 */
	public List<CommentReturnEntity> commentReturnEntityString(List<Integer> keys) throws IOException {
		List<CommentReturnEntity> commentReturnObjectArray = new ArrayList<CommentReturnEntity>();
		for(Integer keyElement: keys) {
			HackerNewsEntity commentObject = hackerRepo.fetchNews(keyElement);
			UserEntity user = hackerRepo.getUserInfo(commentObject.getBy());
			int age = getAgeOfUserProfile(user.getCreated());
			CommentReturnEntity commentSingleEntity = new CommentReturnEntity(user.getId(), age, commentObject.getText());
			commentReturnObjectArray.add(commentSingleEntity);
		}
		return commentReturnObjectArray;
	}
	
	/**
	 * This method returns the age of user's profile.
	 * @param unixUserTime creation time of user hacker news profile
	 * @return age in years of the profile from current date
	 */
	private int getAgeOfUserProfile(long unixUserTime) {
		long unixTime = Instant.now().getEpochSecond();
		
		return commonUtility.getDifferenceInYears(unixUserTime, unixTime);
	}
}
