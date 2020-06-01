package com.insider.fetchNews.comparator;

import com.insider.fetchNews.Entity.HackerNewsEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.insider.fetchNews.Helper.GetDTOs.getHackerNewsEntity;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ScoreComparatorTest {

    @InjectMocks
    ScoreComparator scoreComparator;

    @Test
    public void compareEqual() throws IOException, URISyntaxException {

        //Setting up the desired variable.
        HackerNewsEntity hackerNewsEntity1 = getHackerNewsEntity();
        HackerNewsEntity hackerNewsEntity2 = getHackerNewsEntity();

        //Actual method call.
        int result = scoreComparator.compare(hackerNewsEntity1, hackerNewsEntity2);

        //Assertion
        assertEquals(0, result);
    }

    @Test
    public void compareUnEqual() throws IOException, URISyntaxException {

        //Setting up the desired variable.
        HackerNewsEntity hackerNewsEntity1 = getHackerNewsEntity();
        HackerNewsEntity hackerNewsEntity2 = getHackerNewsEntity();
        hackerNewsEntity2.setScore(123876521);

        //Actual method call.
        int result = scoreComparator.compare(hackerNewsEntity1, hackerNewsEntity2);

        //Assertion
        assertEquals(-1, result);
    }
}
