package com.insider.fetchNews.Utility;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CommonUtilityFunctionsTest {

	@InjectMocks
    CommonUtilityFunctions commonUtilityFunctions;

    @Test
    public void testGetAgeDifferenceInYears()  {

        //Setting up the desired variable
    	long age1 = 1175714200;
    	
    	long age2 = 1314211127;

        //Actual method call
        int hackerNewsEntityActual = commonUtilityFunctions.getDifferenceInYears(age1, age2);

        //Assert
        assertEquals(4, hackerNewsEntityActual);
    }
    
    @Test
    public void testSortByValue() {
    	
    	//Setting up the desired variable
    	Map<Integer, Integer> unsortMap = new HashMap<Integer, Integer>();
    	unsortMap.put(10, 100);
    	unsortMap.put(1,10);
    	
    	//actual Method Call
    	Map<Integer, Integer> sortedMap = commonUtilityFunctions.sortByValue(unsortMap);
    	
    	List<Integer> keys = sortedMap.entrySet().stream()
				  .map(Map.Entry::getKey)
				  .limit(10)
				  .collect(Collectors.toList());
    	
    	assertEquals(new Integer(10),keys.get(0));
    }
}
