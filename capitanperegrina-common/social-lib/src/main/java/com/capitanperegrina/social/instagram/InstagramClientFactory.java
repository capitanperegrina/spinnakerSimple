package com.capitanperegrina.social.instagram;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.brunocvcunha.instagram4j.Instagram4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InstagramClientFactory {

	@Autowired
	InstagramClientConfiguration tConfig;

	private static InstagramClientConfiguration config;

	@PostConstruct
	public void init() {
		InstagramClientFactory.config = this.tConfig;
	}

	public static Instagram4j getTwitterInstance() throws IOException {
		return InstagramClientFactory.getInstagramInstance(InstagramClientFactory.config.getUsuario(), InstagramClientFactory.config.getPassword());
	}

	public static Instagram4j
					getInstagramInstance(final String user, final String pass) throws IOException {
		final Instagram4j instagram = Instagram4j.builder().username(user).password(pass).build();
		instagram.setup();
		instagram.login();
		return instagram;

	}
}
