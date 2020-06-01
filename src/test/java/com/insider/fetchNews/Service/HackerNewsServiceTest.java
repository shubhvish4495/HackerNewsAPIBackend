package com.insider.fetchNews.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.insider.fetchNews.Application;
import com.insider.fetchNews.Entity.CommentReturnEntity;
import com.insider.fetchNews.Entity.HackerNewsEntity;
import com.insider.fetchNews.Entity.HackerNewsReturnEntity;
import com.insider.fetchNews.Helper.GetDTOs;
import com.insider.fetchNews.Repository.HackerNewsRepository;
import com.insider.fetchNews.Utility.CommonUtilityFunctions;

@RunWith(MockitoJUnitRunner.class)
public class HackerNewsServiceTest {
	

	
	@InjectMocks
	HackerNewsService hackerNewsService;

	@Mock
	HackerNewsRepository hackerNewsRepo;
	
	@Mock
	Application newsApp;
	
	@Before
	public void setRepo() {
		hackerNewsService.setHackerNewsRepo(hackerNewsRepo);
	}
	
	@Test
	public void testGetTopStoriesError() throws IOException, URISyntaxException{
		
		int[] ids = GetDTOs.getTopStories();
		
		HackerNewsEntity hackerNewsEntity = GetDTOs.getHackerNewsEntity();
		List<HackerNewsEntity> response =  new ArrayList<HackerNewsEntity>();
		
		for (int i = 0; i< 10; i++) {
			response.add(hackerNewsEntity);
		}
		
		List<HackerNewsReturnEntity> hackerNewsReturnEntity = hackerNewsService.convertToReturnEntity(response);
		
		
		doReturn(ids).when(hackerNewsRepo).getTopStoriesIds();
		
		doReturn(hackerNewsEntity).when(hackerNewsRepo).fetchNews(anyInt());
		
		List<HackerNewsReturnEntity> actualResponse = hackerNewsService.getTopStories();
		
		for (int j=0 ; j<actualResponse.size() ; j++) {
			assertEquals(true, testEqualityHackerNewsReturnEntity(actualResponse.get(j), hackerNewsReturnEntity.get(j)));
		} 
		
	}
	
	private boolean testEqualityHackerNewsReturnEntity(HackerNewsReturnEntity actual, HackerNewsReturnEntity response) {
		return actual.getScore() == response.getScore() && 
				actual.getTimeOfSubmission().equals(response.getTimeOfSubmission()) &&
				actual.getTitle().equals(response.getTitle()) &&
				actual.getUrl().equals(response.getUrl()) &&
				actual.getUser().equals(response.getUser());
	}
	
	private boolean testEqualityForComment(CommentReturnEntity actual, CommentReturnEntity response) {
		
		return actual.getComment().equals(response.getComment()) &&
				actual.getUserAge() == response.getUserAge() &&
				actual.getUserName().equals(response.getUserName());
	}
	
	@Test
	public void testGetTopComments() throws IOException, URISyntaxException {
		
		int storyID = 121003;
		List<CommentReturnEntity> response = GetDTOs.getTopComments();
		
		hackerNewsService.setHackerNewsRepo(new HackerNewsRepository());
		
		hackerNewsService.setCommonUtility(new CommonUtilityFunctions());
		
		List<CommentReturnEntity> actualResponse = hackerNewsService.getTopCommentsForStory(storyID);
		
		for(int i=0; i<actualResponse.size();i++) {
			assertEquals(true, testEqualityForComment(actualResponse.get(i),response.get(i)));
		}
		
			
	}

}
