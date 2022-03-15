package com.spinnakersimple.modelo.excepciones;

import com.capitanperegrina.common.exceptions.ServicioErrorException;

public class AltaUsuarioDuplicadaException extends ServicioErrorException {

	private static final long serialVersionUID = 1497182366379438637L;

	public AltaUsuarioDuplicadaException() {
		super("usuario.alta.duplicada", true);
	}
}
