package com.insider.fetchNews.controller;

import com.insider.fetchNews.Entity.CommentReturnEntity;
import com.insider.fetchNews.Entity.HackerNewsReturnEntity;
import com.insider.fetchNews.Service.HackerNewsService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shubham Saurav
 *
 */
@RestController
public class FetchNewsRestController {

    private static final String INTERNAL_ERROR = "Internal Error";
    private static final String ID_OF_STORY_NEEDS_TO_BE_PROVIDED = "ID of story needs to be provided";
    private static final String STORY_ID_REQUIRED_TO_MAKE_CALLS = "Story Id required to make calls";
    private static final String NO_VALUE_FOUND_FOR_PAST_STORIES = "No value found for past stories";
    private static final boolean FALSE = false;
    private static final boolean TRUE = true;
    private static final String OK = "OK";

    private Logger logger = Logger.getLogger(FetchNewsRestController.class);

    List<HackerNewsReturnEntity> pastStories;

    private HackerNewsService service;

    @Autowired
    public void setHackerNewsService(HackerNewsService repo) {
        this.service = repo;
    }

    public HackerNewsService getHackerNewsService() {
        return service;
    }

    @CacheEvict(value = "topStories", allEntries = true)
    public static void evictAllCacheValues() {}

    /**
     * Returns the top 10 comments for any story.
     * @param storyId
     * @return top 10 comments in JSON format
     * @throws IOException
     */
    @GetMapping("/comments")
    public ResponseEntity<Map<String, Object>> getComments(@RequestParam(value = "storyId") Integer storyId) throws IOException {
        ResponseEntity<Map<String, Object>> response;
        if (storyId == null) {
            logger.log(Level.INFO,STORY_ID_REQUIRED_TO_MAKE_CALLS);
            response = formatResponse(FALSE, STORY_ID_REQUIRED_TO_MAKE_CALLS, null);
            return response;
        } else {
            try {
                List<CommentReturnEntity> returnComments = service.getTopCommentsForStory(storyId);
                if (returnComments == null) {
                    logger.log(Level.INFO,ID_OF_STORY_NEEDS_TO_BE_PROVIDED);
                    response = formatResponse(FALSE, ID_OF_STORY_NEEDS_TO_BE_PROVIDED, returnComments);
                } else {
                    response = formatResponse(TRUE, OK, returnComments);
                }
            } catch (Exception e) {
                logger.log(Level.ERROR,e.getMessage());
                response = formatResponse(FALSE, INTERNAL_ERROR, null);
            }
            return response;
        }
    }

    /**
     * This method maps the request to fetch last 10 stories served.
     * @return old-stories in JSON Format
     */
    @GetMapping("/old-stories")
    public ResponseEntity<Map<String, Object>> oldStories() {
        ResponseEntity<Map<String, Object>> response;
        if (pastStories == null){
            logger.log(Level.INFO,NO_VALUE_FOUND_FOR_PAST_STORIES);
            response = formatResponse(FALSE,NO_VALUE_FOUND_FOR_PAST_STORIES,null);
        } else {
            response = formatResponse(TRUE,OK,pastStories);
        }
        return response;
    }

    /**
     * This method maps the request to fetch top-stories and returns the required data.
     * @return top stories
     * @throws IOException
     */
    @GetMapping("/top-stories")
    public ResponseEntity<Map<String, Object>> topStories() {
        ResponseEntity<Map<String, Object>> response;
        try {
            pastStories = service.getTopStories();
            response = formatResponse(TRUE,OK,pastStories);
        } catch (Exception e) {
            logger.log(Level.INFO,e.getMessage());
            response = formatResponse(FALSE, INTERNAL_ERROR, null);
        }

        return response;
    }

    private ResponseEntity<Map<String, Object>> formatResponse(Boolean success, String message, Object dataObject){
        Map<String, Object> obj = new LinkedHashMap<String, Object>();
        obj.put("success", success);
        obj.put("message", message);
        obj.put("data",dataObject);
        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }

}
