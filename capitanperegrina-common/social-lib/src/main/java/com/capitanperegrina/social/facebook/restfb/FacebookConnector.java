package com.capitanperegrina.social.facebook.restfb;

import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.exception.FacebookException;
import com.restfb.json.JsonObject;
// import com.restfb.json.JsonObject;

public class FacebookConnector {

    private String pageAccessToken;
    private String pageID;
    private FacebookClient fbClient;

    public FacebookConnector(final String pageID, final String pageAccessToken)
            throws com.capitanperegrina.social.facebook.FacebookException {
        try {
            this.pageID = pageID;
            this.pageAccessToken = pageAccessToken;
            this.fbClient = new DefaultFacebookClient(this.pageAccessToken, Version.VERSION_3_2);
        } catch (final FacebookException ex) {
            throw new com.capitanperegrina.social.facebook.FacebookException("No se pudo contruir el cliente faceboook", ex);
        }
    }

    public JsonObject post(final String message) {
        return this.fbClient.publish(this.pageID + "/feed", JsonObject.class, Parameter.with("message", message));
    }

    public JsonObject post(final String message, final String imageName, final String imageType, final byte[] imageAsBytes) {
        return this.fbClient.publish(this.pageID + "/feed", JsonObject.class,
                BinaryAttachment.with(imageName, imageAsBytes, imageType), Parameter.with("message", message));
    }

    public static void main(final String[] args) {
//        try {
//            final FacebookConnector fb = new FacebookConnector("1746652158993713",
//                    "EAADeFJEvKI0BALZCxiCZCs49fizvSZAj3MmtHTvYtcN3AmZBiDjAHmmXDZA8meEpHZB1DtWb3VNi1seLZAfJL4VKvqpVCmVhDeoM6bYaFZAYuDkxZB6i8BMdrY6wJ8BovYipZBgSSFhHBWAB10N56TsLjSnCZCSZA165hfu0Fv1vuCBwnHf6pyqZAzP4kQ353wt0Bp5MZD");
//            System.out.println(fb.post("Hola mundo!"));
//        } catch (final Exception e) {
//            e.printStackTrace();
//        }
//	byte[] imageAsBytes = fetchBytesFromImage();
//	DefaultFacebookClient client =
//		new DefaultFacebookClient(getTestSettings().getPageAccessToken(), Version.LATEST);
//	JsonObject obj = client.publish(getTestSettings().getPageId() + "/feed", JsonObject.class,
//		BinaryAttachment.with("test.png", imageAsBytes, "image/png"), Parameter.with("message", "TestImage"));
    }
}
