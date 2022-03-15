package com.capitanperegrina.social.twitter;

import java.io.Serializable;

public class TwitterClientConfiguration implements Serializable {

    private static final long serialVersionUID = -5491479201593884254L;

    private String consumerKey;
    private String consumerSecret;
    private String accessToken;
    private String accessTokenSecret;

    public TwitterClientConfiguration() {
        super();
    }

    public String getConsumerKey() {
        return this.consumerKey;
    }

    public void setConsumerKey(final String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public String getConsumerSecret() {
        return this.consumerSecret;
    }

    public void setConsumerSecret(final String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessTokenSecret() {
        return this.accessTokenSecret;
    }

    public void setAccessTokenSecret(final String accessTokenSecret) {
        this.accessTokenSecret = accessTokenSecret;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("TwitterClient [consumerKey=").append(this.consumerKey).append(", consumerSecret=")
                .append(this.consumerSecret).append(", accessToken=").append(this.accessToken).append(", accessTokenSecret=")
                .append(this.accessTokenSecret).append("]");
        return builder.toString();
    }
}
