package com.insider.fetchNews.Repository;

import static org.junit.Assert.assertArrayEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.insider.fetchNews.Entity.UserEntity;
import com.insider.fetchNews.Helper.GetDTOs;

@RunWith(MockitoJUnitRunner.class)
public class HackerNewsRepositoryTest {
	
	@InjectMocks
	HackerNewsRepository hackerNewsRepo;
	
	@Test
	public void testGetUserInfo() throws IOException, URISyntaxException {
		
		UserEntity user = GetDTOs.getUser();
		
		UserEntity actualUserResponse = hackerNewsRepo.getUserInfo("ab");
		
		assertArrayEquals(user.getSubmitted(),actualUserResponse.getSubmitted());		
	}
}
