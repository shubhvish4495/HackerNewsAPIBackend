package com.insider.fetchNews.Entity;

import java.io.Serializable;

/**
 * Hacker News entity that will be returned
 * @author Shubham Saurav
 *
 */
public class HackerNewsReturnEntity implements Serializable {

	private String title;
	
	private String url;
	
	private Long timeOfSubmission;
	
	private int score;
	
	public String user;
	
	/**
	 * Parameterized Constructor
	 * @param title
	 * @param url
	 * @param time
	 * @param score
	 * @param user
	 */
	public HackerNewsReturnEntity(String title, String url, Long time, int score, String user) {
		this.title = title;
		this.url = url;
		this.timeOfSubmission = time;
		this.score = score;
		this.user = user;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the timeOfSubmission
	 */
	public Long getTimeOfSubmission() {
		return timeOfSubmission;
	}

	/**
	 * @param timeOfSubmission the timeOfSubmission to set
	 */
	public void setTimeOfSubmission(Long timeOfSubmission) {
		this.timeOfSubmission = timeOfSubmission;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
	
}
