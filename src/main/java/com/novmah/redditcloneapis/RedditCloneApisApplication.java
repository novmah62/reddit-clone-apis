package com.novmah.redditcloneapis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RedditCloneApisApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedditCloneApisApplication.class, args);
	}

}
