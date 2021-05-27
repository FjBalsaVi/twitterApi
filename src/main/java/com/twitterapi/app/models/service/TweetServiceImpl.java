package com.twitterapi.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twitterapi.app.models.Tweet;
import com.twitterapi.app.models.dao.TweetDao;

@Service
public class TweetServiceImpl implements ITweetService {

	@Autowired
	private TweetDao tweetDao;

	@Override
	@Transactional(readOnly = true)
	public List<Tweet> findAll() {

		return (List<Tweet>)tweetDao.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Tweet findById(Long id) {
		return tweetDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public Tweet save(Tweet tweet) {
		return tweetDao.save(tweet);
	}

	@Override
	public List<Tweet> findValidacionIsTrue() {
		return tweetDao.findByValidacionIsTrue();
	}

}
