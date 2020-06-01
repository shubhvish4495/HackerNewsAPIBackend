package com.insider.fetchNews.Entity;

import java.math.BigDecimal;

/**
 * Entity to store HackerNews return object
 * @author Shubham Saurav
 *
 */
public class HackerNewsEntity {
	
	/**
	 * Default Constructor
	 */
	public HackerNewsEntity() {}

	private String by;
	
	private int descendants;
	
	private int id;
	
	private int[] kids;
	
	private int score;

	private Long time;
	
	private String title;
	
	private String type;
	
	private String text;
	
	private String url;
	
	private Boolean deleted;
	
	private Boolean dead;
	
	private BigDecimal parent;

	/**
	 * @return the by
	 */
	public String getBy() {
		return by;
	}

	/**
	 * @param by the by to set
	 */
	public void setBy(String by) {
		this.by = by;
	}

	/**
	 * @return the descendants
	 */
	public int getDescendants() {
		return descendants;
	}

	/**
	 * @param descendants the descendants to set
	 */
	public void setDescendants(int descendants) {
		this.descendants = descendants;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the kids
	 */
	public int[] getKids() {
		return kids;
	}

	/**
	 * @param kids the kids to set
	 */
	public void setKids(int[] kids) {
		this.kids = kids;
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
	 * @return the time
	 */
	public Long getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(Long time) {
		this.time = time;
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
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
	 * @return the deleted
	 */
	public Boolean getDeleted() {
		return deleted;
	}

	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * @return the dead
	 */
	public Boolean getDead() {
		return dead;
	}

	/**
	 * @param dead the dead to set
	 */
	public void setDead(Boolean dead) {
		this.dead = dead;
	}

	/**
	 * @return the parent
	 */
	public BigDecimal getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(BigDecimal parent) {
		this.parent = parent;
	}
	
}
