package com.insider.fetchNews;

import com.insider.fetchNews.controller.FetchNewsRestController;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

import static com.insider.fetchNews.controller.FetchNewsRestController.evictAllCacheValues;

/**
 * @author Shubham Saurav
 *
 */
@SpringBootApplication
@EnableCaching
@ComponentScan(basePackages= {"com.insider.fetchNews.*"})
public class Application {

	private Logger logger = Logger.getLogger(Application.class);

	public static void main(String[] args) {

		Logger logger = Logger.getLogger(Application.class);
		SpringApplication.run(Application.class, args);
		evictAllCacheValues();
		logger.info("Cache Values Evicted.");

	}

}
