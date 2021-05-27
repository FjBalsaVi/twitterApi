package com.twitterapi.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.twitterapi.app.models.Tweet;
import com.twitterapi.app.models.service.ITweetService;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

@Component
public class TwitterApiSearch implements CommandLineRunner{

	@Autowired
	ITweetService tweetService;
		
    private final Logger log = LoggerFactory.getLogger(TwitterApiSearch.class);


	@Override
	public void run(String... args) throws Exception {
		
		Long followersmax = 1500L;
		ArrayList<String> lang = new ArrayList<String>(Arrays.asList("en", "fr", "es", "it"));

		StatusListener listener = new StatusListener() {
			public void onStatus(Status status) {
				if (status.getUser().getFollowersCount() > followersmax && lang.contains(status.getLang())) {
					
					log.info("Tweet mayor de " +followersmax+ " en el idioma: " + lang.stream().map(Object::toString).collect(Collectors.joining(",")).toString() + 
					" encontrado del usuario " +status.getUser().getScreenName() + " " + status.getLang() +" con el texto " + status.getText());
					
					Tweet tweet = new Tweet(status.getUser().getScreenName(),status.getText(),null != status.getPlace() ? status.getPlace().getCountry() : null);
				
					tweetService.save(tweet);
					log.info("Tweet Guardado");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
						
					
				}
			}

			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
			}

			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
			}

			public void onException(Exception ex) {
				ex.printStackTrace();
			}

			@Override
			public void onScrubGeo(long userId, long upToStatusId) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStallWarning(StallWarning warning) {
				// TODO Auto-generated method stub

			}
		};

		TwitterStreamFactory tsf = new TwitterStreamFactory(); 

		TwitterStream twitterStream = tsf.getInstance();
	    twitterStream.addListener(listener);

	    twitterStream.sample();
		
	}

}