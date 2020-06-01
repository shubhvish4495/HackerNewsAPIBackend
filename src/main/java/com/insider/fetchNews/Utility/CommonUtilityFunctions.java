package com.insider.fetchNews.Utility;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * Common Utility functions
 * @author Shubham Saurav
 *
 */
@Component
public class CommonUtilityFunctions {
	
	/**
	 * This method sorts 2 maps by their values
	 * @param unsortMap
	 * @return Sorted map by value
	 */
	public Map<Integer, Integer> sortByValue(Map<Integer, Integer> unsortMap) {

		List<Map.Entry<Integer, Integer>> list =
				new LinkedList<Map.Entry<Integer, Integer>>(unsortMap.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
			public int compare(Map.Entry<Integer, Integer> o1,
					Map.Entry<Integer, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});
		Collections.reverse(list);

		Map<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
		for (Map.Entry<Integer, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		
		return sortedMap;
	}
	
	/**
	 * This method returns the difference in years between the 2 dates
	 * @param Date1
	 * @param Date2
	 * @return diffrence in years between the dates
	 */
	public int getDifferenceInYears(long Date1, long Date2) {
		
		Date purchasedDate = new Date ();
		
		purchasedDate.setTime((long)Date1*1000);

		Date currentDate = new Date ();
		currentDate .setTime((long)Date2*1000);
 
		int diffInYears = (int)( ( (currentDate.getTime() - purchasedDate.getTime()) 
		                 / (1000 * 60 * 60 * 24) ) / 365 );
		
		return diffInYears;
	}

}
