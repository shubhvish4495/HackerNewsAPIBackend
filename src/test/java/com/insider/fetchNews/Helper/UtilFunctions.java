package com.insider.fetchNews.Helper;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UtilFunctions {
	public static ResponseEntity<Map<String, Object>> formatResponse(Boolean success, String message, Object dataObject){
		Map<String, Object> obj = new LinkedHashMap<String, Object>();
		obj.put("success", success);
		obj.put("message", message);
		obj.put("data",dataObject);
		return ResponseEntity.status(HttpStatus.OK).body(obj);
	}
}
