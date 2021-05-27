package com.twitterapi.app.models.dao;



import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.twitterapi.app.models.Tweet;


@Repository
public interface TweetDao extends PagingAndSortingRepository<Tweet, Long> {

	public List<Tweet> findByValidacionIsTrue();

}
