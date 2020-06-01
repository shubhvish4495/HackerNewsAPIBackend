package com.insider.fetchNews.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.insider.fetchNews.Entity.CommentReturnEntity;
import com.insider.fetchNews.Entity.HackerNewsReturnEntity;
import com.insider.fetchNews.Helper.GetDTOs;
import com.insider.fetchNews.Helper.UtilFunctions;
import com.insider.fetchNews.Repository.HackerNewsRepository;
import com.insider.fetchNews.Service.HackerNewsService;

@RunWith(MockitoJUnitRunner.class)
public class FetchNewsRestControllerTest {

    @InjectMocks
    FetchNewsRestController fetchNewsRestController;

    @Mock
    HackerNewsService hackerNewsServiceMock;

    @Mock
    HackerNewsRepository hackerNewsRepoMock;

    @Before
    public void basicConfig() {
        fetchNewsRestController.setHackerNewsService(hackerNewsServiceMock);
    }


    @Test
    public void testGetCommentsNormal() throws IOException, URISyntaxException {

        //setting up the variables.
        int storyID = 1234;
        List<CommentReturnEntity> commentsList= GetDTOs.getComments();

        ResponseEntity<Map<String, Object>> response= UtilFunctions.formatResponse(true, "OK", commentsList);


        //setting up the mocks behaviour.
        when(hackerNewsServiceMock.getTopCommentsForStory(storyID)).thenReturn(commentsList);

        //actual method call.
        ResponseEntity<Map<String, Object>> actualResponse = fetchNewsRestController.getComments(storyID);

        //Assertion.
//		verify(hackerNewsServiceMock, times(1)).getTopCommentsForStory(storyID);
        assertEquals(response, actualResponse);
    }

    @Test
    public void testGetCommentsErrorForNotStory() throws IOException {

        //setting up the variables.
        int storyID = 1234;
        ResponseEntity<Map<String, Object>> responseEntity=
                UtilFunctions.formatResponse(false, "ID of story needs to be provided", null);

        HackerNewsService hackerNewsService = new HackerNewsService();

        HackerNewsRepository hackerNewsRepo = new HackerNewsRepository();

        fetchNewsRestController.setHackerNewsService(hackerNewsService);

        hackerNewsService.setHackerNewsRepo(hackerNewsRepo);

        //actual method call.
        ResponseEntity<Map<String, Object>> actualResponse = fetchNewsRestController.getComments(storyID);

        //Assertion.
//		verify(hackerNewsService, times(1)).getTopCommentsForStory(storyID);
        assertEquals(responseEntity, actualResponse);
    }

    @Test
    public void testGetCommentsNullStoryID() throws IOException, URISyntaxException {

        //setting up the variables.
//		header.setContentType(MediaType.APPLICATION_JSON);
//		Object ob = GetDTOs.getStoryID();

        ResponseEntity<Map<String, Object>> responseEntity=
                UtilFunctions.formatResponse(false, "Story Id required to make calls", null);

        HackerNewsService hackerNewsService = new HackerNewsService();

        HackerNewsRepository hackerNewsRepo = new HackerNewsRepository();

        fetchNewsRestController.setHackerNewsService(hackerNewsService);

        hackerNewsService.setHackerNewsRepo(hackerNewsRepo);

        //actual method call.
        ResponseEntity<Map<String, Object>> actualResponse = fetchNewsRestController.getComments(null);

        //Assertion.
        //verify(hackerNewsService, times(0)).getTopCommentsForStory(null);
        assertEquals(responseEntity, actualResponse);
    }
    //
    @Test
    public void testGetCommentsException() throws IOException {

        //setting up the variables.
        int storyID = 1234;
        ResponseEntity<Map<String, Object>> responseExpected = UtilFunctions.formatResponse(false, "Internal Error", null);

        //setting up the mocks behaviour.
        Mockito.doThrow(IOException.class).when(hackerNewsServiceMock).getTopCommentsForStory(storyID);

        //actual method call.
        ResponseEntity<Map<String, Object>> actualResponse = fetchNewsRestController.getComments(storyID);

        //Assertion
//		verify(hackerNewsServiceMock, times(0)).getTopCommentsForStory(storyID);
        assertEquals(responseExpected, actualResponse);
    }
    //
    @Test
    public void testOldStoriesEmpty() {

        //setting up the variables.
        fetchNewsRestController.pastStories = null;
        ResponseEntity<Map<String, Object>> response= UtilFunctions.formatResponse(false, "No value found for past stories", null);

        HackerNewsService hackerNewsService = new HackerNewsService();

        HackerNewsRepository hackerNewsRepo = new HackerNewsRepository();

        fetchNewsRestController.setHackerNewsService(hackerNewsService);

        hackerNewsService.setHackerNewsRepo(hackerNewsRepo);

        //Actual method call.
        ResponseEntity<Map<String, Object>> actualResponse = fetchNewsRestController.oldStories();

        //Assertion
        assertEquals(response, actualResponse);
    }
    //
    @Test
    public void testOldStoriesNotEmpty() throws URISyntaxException, IOException {

        //setting up the variables.
        List<HackerNewsReturnEntity> oldStories = GetDTOs.getOldStories();
        fetchNewsRestController.pastStories = oldStories;
        ResponseEntity<Map<String, Object>> response= UtilFunctions.formatResponse(true, "OK", fetchNewsRestController.pastStories);

        HackerNewsService hackerNewsService = new HackerNewsService();

        HackerNewsRepository hackerNewsRepo = new HackerNewsRepository();

        fetchNewsRestController.setHackerNewsService(hackerNewsService);

        hackerNewsService.setHackerNewsRepo(hackerNewsRepo);

        //Actual method call.
        ResponseEntity<Map<String, Object>> actualResponse = fetchNewsRestController.oldStories();

        //Assertion
        assertEquals(response, actualResponse);
    }
    //
    @Test
    public void testTopStories() throws IOException, URISyntaxException {

        //Setting up the desired variables.
        List<HackerNewsReturnEntity> topStories = GetDTOs.getOldStories();
//		fetchNewsApplication.pastStories= topStories;
        ResponseEntity<Map<String, Object>> response= UtilFunctions.formatResponse(true, "OK", topStories);


        //Setting up the mocks behaviour.
        when(hackerNewsServiceMock.getTopStories()).thenReturn(topStories);

        //Actual method call.
        ResponseEntity<Map<String, Object>> actualResponse = fetchNewsRestController.topStories();

        //Assertion
//		verify(hackerNewsServiceMock, times(1)).getTopStories();
        assertEquals(response, actualResponse);

    }
    //
    @Test
    public void testTopStoriesException() throws IOException {

        //Setting up the desired variables.
        fetchNewsRestController.pastStories=null;

        ResponseEntity<Map<String, Object>> responseExpected = UtilFunctions.formatResponse(false, "Internal Error", null);

        //Setting up the mocks behaviour.
        when(hackerNewsServiceMock.getTopStories()).thenThrow(IOException.class);

        //Actual method call.
        ResponseEntity<Map<String, Object>> response = fetchNewsRestController.topStories();

        //Assertion
//		verify(hackerNewsServiceMock, times(0)).getTopStories();
        assertEquals(responseExpected, response);

    }

}
