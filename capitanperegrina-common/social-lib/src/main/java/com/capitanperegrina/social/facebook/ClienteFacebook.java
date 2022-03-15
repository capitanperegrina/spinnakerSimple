package com.capitanperegrina.social.facebook;

import org.apache.log4j.Logger;

import com.capitanperegrina.social.facebook.restfb.FacebookConnector;
import com.restfb.json.JsonObject;

public class ClienteFacebook {

    private static Logger log = Logger.getLogger(ClienteFacebook.class);

    public static final JsonObject post(final String mensaje) throws FacebookException {
        try {
            final FacebookConnector fb = FacebookClientFactory.getFacebookConnector();
            final JsonObject response = fb.post(mensaje);
            log.debug("Respuesta de facebook: \n" + response.toString() + "\n");
            return response;
        } catch (final FacebookException e) {
            log.error(e);
            throw e;
        }
    }

    public static final JsonObject post(final String message, final String imageName, final String imageType,
            final byte[] imageAsBytes) throws FacebookException {
        try {
            final FacebookConnector fb = FacebookClientFactory.getFacebookConnector();
            final JsonObject response = fb.post(message, imageName, imageType, imageAsBytes);
            log.debug("Respuesta de facebook: \n" + response.toString() + "\n");
            return response;
        } catch (final FacebookException e) {
            log.error(e);
            throw e;
        }
    }
}
