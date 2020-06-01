package com.insider.fetchNews.Entity;

import java.io.Serializable;

/**
 * Comment Entity to return to front-end
 * @author Shubham Saurav
 *
 */
public class CommentReturnEntity implements Serializable {

	private String userName;
	
	private int userAge;
	
	private String comment;
	
	/**
	 * Default constructor
	 */
	public CommentReturnEntity() {}

	/**
	 * parameterized constructor
	 * @param userName
	 * @param userAge
	 * @param comment
	 */
	public CommentReturnEntity(String userName, int userAge, String comment) {
		super();
		this.userName = userName;
		this.userAge = userAge;
		this.comment = comment;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userAge
	 */
	public int getUserAge() {
		return userAge;
	}

	/**
	 * @param userAge the userAge to set
	 */
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
}
