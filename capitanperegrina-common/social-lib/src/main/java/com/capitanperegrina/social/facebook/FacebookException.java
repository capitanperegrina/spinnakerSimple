package com.capitanperegrina.social.facebook;

import com.capitanperegrina.common.exceptions.ServicioException;

public class FacebookException extends ServicioException {

    private static final long serialVersionUID = 453457345735L;

    public FacebookException(final String message, final Throwable cause) {
        super(message, cause);
        this.pendienteLocalizar = false;
        this.bundle = null;
    }
}