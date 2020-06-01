package com.insider.fetchNews.Helper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.insider.fetchNews.Entity.CommentReturnEntity;
import com.insider.fetchNews.Entity.HackerNewsEntity;
import com.insider.fetchNews.Entity.HackerNewsReturnEntity;
import com.insider.fetchNews.Entity.UserEntity;
import com.insider.fetchNews.Utility.JacksonUtilityFunctions;

public class GetDTOs {

    public static HackerNewsEntity getHackerNewsEntity() throws URISyntaxException, IOException {

        //Load JSON String
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        String json = Files.lines(Paths.get(loader.getResource("hackerNewsEntity.json").toURI()))
                .parallel()
                .collect(Collectors.joining());

        //Creating HackerNewsEntity object.
        JacksonUtilityFunctions jacksonUtilityFunctions = new JacksonUtilityFunctions();
        return jacksonUtilityFunctions.convertJSONToHackerObject(json);
    }
    
    public static List<HackerNewsReturnEntity> getOldStories() throws IOException, URISyntaxException {
    		//Load JSON String
    		 ClassLoader loader = ClassLoader.getSystemClassLoader();
    		String carInfoJson= new String(Files.readAllBytes(Paths.get(loader.getResource("oldStories.json").toURI())));
    		Gson gson = new Gson();  
    		List<HackerNewsReturnEntity> returnArray = gson.fromJson(carInfoJson, new TypeToken<List<HackerNewsReturnEntity>>() {}.getType());
            return returnArray;  
    }
    
    public static List<CommentReturnEntity> getComments() throws IOException, URISyntaxException {
		//Load JSON String
		 ClassLoader loader = ClassLoader.getSystemClassLoader();
		String carInfoJson= new String(Files.readAllBytes(Paths.get(loader.getResource("comments.json").toURI())));
		Gson gson = new Gson();  
		List<CommentReturnEntity> returnArray = gson.fromJson(carInfoJson, new TypeToken<List<CommentReturnEntity>>() {}.getType());
        return returnArray;  
    }
    
    public static UserEntity getUser() throws IOException, URISyntaxException {
		//Load JSON String
		 ClassLoader loader = ClassLoader.getSystemClassLoader();
		String carInfoJson= new String(Files.readAllBytes(Paths.get(loader.getResource("user.json").toURI())));
		Gson gson = new Gson();  
		UserEntity returnUser = gson.fromJson(carInfoJson, new UserEntity().getClass());
        return returnUser;  
    }
    
    public static List<CommentReturnEntity> getTopComments() throws IOException, URISyntaxException {
		//Load JSON String
		 ClassLoader loader = ClassLoader.getSystemClassLoader();
		String carInfoJson= new String(Files.readAllBytes(Paths.get(loader.getResource("topComments.json").toURI())));
		Gson gson = new Gson();  
		List<CommentReturnEntity> returnArray = gson.fromJson(carInfoJson, new TypeToken<List<CommentReturnEntity>>() {}.getType());
        return returnArray;  
    }
    
    public static int[] getTopStories() throws IOException, URISyntaxException {
		//Load JSON String
		 ClassLoader loader = ClassLoader.getSystemClassLoader();
		String carInfoJson= new String(Files.readAllBytes(Paths.get(loader.getResource("topStories.json").toURI())));
		int[] array = new ObjectMapper().readValue(carInfoJson, int[].class);
		return array;
    }
}
