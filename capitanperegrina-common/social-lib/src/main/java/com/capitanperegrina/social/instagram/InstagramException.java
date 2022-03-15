package com.capitanperegrina.social.instagram;

import com.capitanperegrina.common.exceptions.ServicioException;

public class InstagramException extends ServicioException {

	private static final long serialVersionUID = 453457345735L;

	public InstagramException(final String message, final Throwable cause) {
		super(message, cause);
		this.pendienteLocalizar = false;
		this.bundle = null;
	}
}
