package com.insider.fetchNews.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.insider.fetchNews.Entity.HackerNewsEntity;
import com.insider.fetchNews.Entity.UserEntity;

/**
 * Repository to make calls to server to fetch data
 * @author Shubham Saurav
 *
 */
@Repository
public class HackerNewsRepository {
		
	/**
	 * This method fetches the news element using ID by making rest call to server
	 * @param id Id of story/comment to be fetched
	 * @return NewsElement 
	 */
	public HackerNewsEntity fetchNews(int id) {
		String uri = "https://hacker-news.firebaseio.com/v0/item/"+ Integer.toString(id) +".json?print=pretty";
	    RestTemplate restTemplate = new RestTemplate();
	    HackerNewsEntity result = restTemplate.getForObject(uri, HackerNewsEntity.class);
	 
	    return result;
	}
	
	/**
	 * This method fetches the id of all the top stories by making rest call to server
	 * @return array of id of top stories
	 */
	public int[] getTopStoriesIds() {
		String topStoriesUrl = "https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty";

	    RestTemplate restTemplate = new RestTemplate();
	    int[] result = restTemplate.getForObject(topStoriesUrl, int[].class);
	 
	    return result;
	}
	
	/**
	 * This method returns the user entity by making rest call to server
	 * @param key the user handle
	 * @return User Entity
	 */
	public UserEntity getUserInfo(String key) {
		String uri = "https://hacker-news.firebaseio.com/v0/user/"+ key +".json?print=pretty";
	    RestTemplate restTemplate = new RestTemplate();
	    UserEntity result = restTemplate.getForObject(uri, UserEntity.class);
	    return result;
		
	}
}

