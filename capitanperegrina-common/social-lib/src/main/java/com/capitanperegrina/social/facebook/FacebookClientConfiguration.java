package com.capitanperegrina.social.facebook;

import java.io.Serializable;

public class FacebookClientConfiguration implements Serializable {

    private static final long serialVersionUID = -6987430200848664211L;

    private String userAccessToken;
    private String pageID;

    public FacebookClientConfiguration() {
        super();
    }

    public void setUserAccessToken(final String userAccessToken) {
        this.userAccessToken = userAccessToken;
    }

    public String getUserAccessToken() {
        return this.userAccessToken;
    }

    public String getPageID() {
        return this.pageID;
    }

    public void setPageID(final String pageID) {
        this.pageID = pageID;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("FacebookClientConfiguration [userAccessToken=").append(this.userAccessToken).append(", pageID=")
                .append(this.pageID).append("]");
        return builder.toString();
    }
}
