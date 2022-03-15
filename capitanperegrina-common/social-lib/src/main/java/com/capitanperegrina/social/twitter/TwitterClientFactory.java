package com.capitanperegrina.social.twitter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Component
public class TwitterClientFactory {

	@Autowired
	TwitterClientConfiguration tTwitterClientConfiguration;

	private static TwitterClientConfiguration twitterClientConfiguration;

	@PostConstruct
	public void init() {
		TwitterClientFactory.twitterClientConfiguration = this.tTwitterClientConfiguration;
	}

	public static Twitter getTwitterInstance() {
		return getTwitterInstance(TwitterClientFactory.twitterClientConfiguration.getConsumerKey(), TwitterClientFactory.twitterClientConfiguration.getConsumerSecret(), TwitterClientFactory.twitterClientConfiguration.getAccessToken(), TwitterClientFactory.twitterClientConfiguration.getAccessTokenSecret());
	}

	public static Twitter getTwitterInstance(final String consumerKey,
					final String consumerSecret, final String accessToken,
					final String accessTokenSecret) {
		final ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey).setOAuthConsumerSecret(consumerSecret).setOAuthAccessToken(accessToken).setOAuthAccessTokenSecret(accessTokenSecret);

		final TwitterFactory tf = new TwitterFactory(cb.build());
		return tf.getInstance();
	}
}
