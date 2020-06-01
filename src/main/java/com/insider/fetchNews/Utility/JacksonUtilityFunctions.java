package com.insider.fetchNews.Utility;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insider.fetchNews.Entity.HackerNewsEntity;
import com.insider.fetchNews.Entity.HackerNewsReturnEntity;

/**
 * Utility functions for POJO to JSON conversion
 * 
 * @author Shubham Saurav
 *
 */
@Component
public class JacksonUtilityFunctions {
	
	ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * This method converts JSON to HackerNewsEntity Object
	 * @param json string
	 * @return HackerNewsEntity object for corresponding JSON passed
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public HackerNewsEntity convertJSONToHackerObject(String json) throws JsonMappingException, JsonProcessingException {
		return mapper.readValue(json, HackerNewsEntity.class);
	}
}
