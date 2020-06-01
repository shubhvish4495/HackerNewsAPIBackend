package com.insider.fetchNews.Utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insider.fetchNews.Entity.HackerNewsEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static com.insider.fetchNews.Helper.GetDTOs.getHackerNewsEntity;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JacksonUtilityFunctionsTest {

    @InjectMocks
    JacksonUtilityFunctions jacksonUtilityFunctions;

    @Mock
    ObjectMapper mapper;

    @Test
    public void testConvertJSONToHackerObject() throws URISyntaxException, IOException {

        //Setting up the desired variable
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        String json = Files.lines(Paths.get(loader.getResource("hackerNewsEntity.json").toURI()))
                .parallel()
                .collect(Collectors.joining());

        //Setting desired behaviour.
        when(mapper.readValue(json, HackerNewsEntity.class)).thenReturn(getHackerNewsEntity());

        //Actual method call
        HackerNewsEntity hackerNewsEntityActual = jacksonUtilityFunctions.convertJSONToHackerObject(json);

        //Assert
        assertEquals(8863, hackerNewsEntityActual.getId());
    }
}
