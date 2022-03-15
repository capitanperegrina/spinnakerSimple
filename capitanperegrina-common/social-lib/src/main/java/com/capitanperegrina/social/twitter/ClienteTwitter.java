package com.capitanperegrina.social.twitter;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.log4j.Logger;

import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class ClienteTwitter {

	private static Logger log = Logger.getLogger(ClienteTwitter.class);

	public static final Status twit(final String tweet) throws TwitterException {
		try {
			final Twitter t = TwitterClientFactory.getTwitterInstance();
			final Status status = t.updateStatus(tweet);
			log.debug("Twiteado: \"" + status.getText() + "\"");
			return status;
		} catch (final TwitterException e) {
			log.error(e);
			throw e;
		}
	}

	public static final Status twit(final String tweet, final String imageName, final byte[] imageBytes) throws TwitterException {
		try {
			final Twitter t = TwitterClientFactory.getTwitterInstance();
			final StatusUpdate statusUpdate = new StatusUpdate(tweet);
			final InputStream is = new ByteArrayInputStream(imageBytes);
			statusUpdate.setMedia(imageName, is);
			final Status status = t.updateStatus(statusUpdate);
			log.debug("Twiteado: \"" + status.getText() + "\"");
			return status;
		} catch (final TwitterException e) {
			log.error(e);
			throw e;
		}
	}
}
