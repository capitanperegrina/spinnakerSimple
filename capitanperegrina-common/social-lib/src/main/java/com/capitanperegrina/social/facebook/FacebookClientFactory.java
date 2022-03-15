package com.capitanperegrina.social.facebook;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capitanperegrina.common.net.json.NetworkJsonReader;
import com.capitanperegrina.social.facebook.restfb.FacebookConnector;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component
public class FacebookClientFactory {

    @Autowired
    FacebookClientConfiguration tFacebookClientConfiguration;

    private static FacebookClientConfiguration facebookClientConfiguration;

    @PostConstruct
    public void init() {
        FacebookClientFactory.facebookClientConfiguration = this.tFacebookClientConfiguration;
    }

    public static FacebookConnector getFacebookConnector() throws FacebookException {
        final String urlAccessToken = "https://graph.facebook.com/v3.2/me/accounts?access_token="
                + FacebookClientFactory.facebookClientConfiguration.getUserAccessToken();
        final String json = NetworkJsonReader.jsonGetRequest(urlAccessToken);
        final JsonElement jelement = new JsonParser().parse(json);
        final JsonObject jobject = jelement.getAsJsonObject();
        final String accessToken = jobject.get("data").getAsJsonArray().get(0).getAsJsonObject().get("access_token").getAsString();
        return getFacebookConnector(FacebookClientFactory.facebookClientConfiguration.getPageID(), accessToken);
    }

    public static FacebookConnector getFacebookConnector(final String pageID, final String accessToken) throws FacebookException {
        return new FacebookConnector(pageID, accessToken);
    }
}