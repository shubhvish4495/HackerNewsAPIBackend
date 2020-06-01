package com.insider.fetchNews.comparator;

import java.util.Comparator;

import com.insider.fetchNews.Entity.HackerNewsEntity;

/**
 * Comparator to compare scores of 2 HackerNewsEntity
 * 
 * @author Shubham Saurav
 *
 */
public class ScoreComparator implements Comparator<HackerNewsEntity>{
	
	/**
	 *Method to compare 2 scores of HackerNewsEntity
	 */
	@Override
    public int compare(HackerNewsEntity o1, HackerNewsEntity o2) {
        return Integer.compare(o1.getScore(), o2.getScore());
    }
}
