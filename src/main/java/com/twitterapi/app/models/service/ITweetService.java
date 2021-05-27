package com.twitterapi.app.models.service;


import java.util.List;

import com.twitterapi.app.models.Tweet;


public interface ITweetService {

	public List<Tweet> findAll();
	public Tweet findById(Long id);
	
	public List<Tweet> findValidacionIsTrue();
	public Tweet save(Tweet tweet);
		
}