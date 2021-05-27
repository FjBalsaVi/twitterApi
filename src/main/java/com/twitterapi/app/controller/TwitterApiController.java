package com.twitterapi.app.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.twitterapi.app.models.Tweet;
import com.twitterapi.app.models.service.ITweetService;

import twitter4j.Trend;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@RestController
public class TwitterApiController {

	@Autowired
	private ITweetService tweetService;

	private final Logger log = LoggerFactory.getLogger(TwitterApiController.class);

	@GetMapping("/listar")
	public List<Tweet> listar() {
		return tweetService.findAll();
	}

	@GetMapping("/validar/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Tweet validarTweet(@PathVariable Long id) {

		Tweet tweetDb = tweetService.findById(id);

		if (null != tweetDb) {
			tweetDb.setValidacion(true);
			tweetService.save(tweetDb);
			return tweetDb;
		}

		return null;

	}

	@GetMapping("/validados")
	public List<Tweet> listarValidados() {
		return tweetService.findValidacionIsTrue();
	}

	@GetMapping("/tophashtags")
	public List<Trend> topHashtags() throws TwitterException {
		Twitter twitter = new TwitterFactory().getInstance();
		Trend[] topTrends;

		final int woeid = 768026; // Murcia
		topTrends = twitter.getPlaceTrends(woeid).getTrends(); // Se supone que solo deberia traer 10 hashtags, pero en realidad trae 50

		return Arrays.asList(topTrends).subList(0, 9); // Devolvemos 10 resultados
	}

}
