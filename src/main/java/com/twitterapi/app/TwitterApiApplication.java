package com.twitterapi.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.twitterapi.app.models.service.ITweetService;

@SpringBootApplication
public class TwitterApiApplication {

	
	@Autowired
	static ITweetService tweetService;
	
	public static void main(String[] args) {
		SpringApplication.run(TwitterApiApplication.class, args);

	}
}
