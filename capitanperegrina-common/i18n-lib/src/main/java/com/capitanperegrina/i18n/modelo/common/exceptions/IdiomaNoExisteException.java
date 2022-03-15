package com.capitanperegrina.i18n.modelo.common.exceptions;

import com.capitanperegrina.common.exceptions.ServicioErrorException;

public class IdiomaNoExisteException extends ServicioErrorException {

	private static final long serialVersionUID = -3023340419011322882L;

	public IdiomaNoExisteException() {
		super();
	}

	public IdiomaNoExisteException(String message, boolean pendienteLocalizar, Throwable cause) {
		super(message, pendienteLocalizar, cause);
	}

	public IdiomaNoExisteException(String mensaje, boolean pendienteLocalizar) {
		super(mensaje, pendienteLocalizar);
	}

	public IdiomaNoExisteException(String message, Object[] args, boolean pendienteLocalizar, Throwable cause) {
		super(message, args, pendienteLocalizar, cause);
	}

	public IdiomaNoExisteException(String mensaje, Object[] args, boolean pendienteLocalizar) {
		super(mensaje, args, pendienteLocalizar);
	}

	public IdiomaNoExisteException(String message, Object[] args, Throwable cause) {
		super(message, args, cause);
	}

	public IdiomaNoExisteException(String message, Object[] args) {
		super(message, args);
	}

	public IdiomaNoExisteException(String bundle, String message, boolean pendienteLocalizar, Throwable cause) {
		super(bundle, message, pendienteLocalizar, cause);
	}

	public IdiomaNoExisteException(String bundle, String mensaje, boolean pendienteLocalizar) {
		super(bundle, mensaje, pendienteLocalizar);
	}

	public IdiomaNoExisteException(String bundle, String message, Object[] args, boolean pendienteLocalizar,
			Throwable cause) {
		super(bundle, message, args, pendienteLocalizar, cause);
	}

	public IdiomaNoExisteException(String bundle, String mensaje, Object[] args, boolean pendienteLocalizar) {
		super(bundle, mensaje, args, pendienteLocalizar);
	}

	public IdiomaNoExisteException(String bundle, String message, Object[] args, Throwable cause) {
		super(bundle, message, args, cause);
	}

	public IdiomaNoExisteException(String bundle, String mensaje, Object[] args) {
		super(bundle, mensaje, args);
	}

	public IdiomaNoExisteException(String bundle, String message, Throwable cause) {
		super(bundle, message, cause);
	}

	public IdiomaNoExisteException(String bundle, String mensaje) {
		super(bundle, mensaje);
	}

	public IdiomaNoExisteException(String message, Throwable cause) {
		super(message, cause);
	}

	public IdiomaNoExisteException(String mensaje) {
		super(mensaje);
	}

	public IdiomaNoExisteException(Throwable cause) {
		super(cause);
	}
}
