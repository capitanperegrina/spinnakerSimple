package com.capitanperegrina.common.exceptions;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

import com.capitanperegrina.common.i18n.Mensajes;

/**
 * Excepción que provoca Rollback;.
 */
public class ServicioErrorException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1260300667227893872L;

	/** The ajax. */
	protected boolean ajax = false;

	/** The bundle. */
	protected String bundle = null;

	/** The pendiente localizar. */
	protected Boolean pendienteLocalizar;

	/** The args. */
	protected Object[] args = null;

	/**
	 * Instantiates a new servicio error exception.
	 */
	public ServicioErrorException() {
		super();
	}

	/**
	 * Instantiates a new servicio error exception.
	 *
	 * @param mensaje the mensaje
	 */
	public ServicioErrorException(final String mensaje) {
		super(mensaje);
		this.pendienteLocalizar = true;
		this.bundle = null;
	}

	/**
	 * Instantiates a new servicio error exception.
	 *
	 * @param bundle  the bundle
	 * @param mensaje the mensaje
	 */
	public ServicioErrorException(final String bundle, final String mensaje) {
		super(mensaje);
		this.pendienteLocalizar = true;
		this.bundle = bundle;
	}

	/**
	 * Instantiates a new servicio error exception.
	 *
	 * @param bundle  the bundle
	 * @param mensaje the mensaje
	 * @param args    the args
	 */
	public ServicioErrorException(final String bundle, final String mensaje, final Object[] args) {
		super(mensaje);
		this.pendienteLocalizar = true;
		this.bundle = bundle;
		this.args = args;
	}

	/**
	 * Instantiates a new servicio error exception.
	 *
	 * @param message the message
	 * @param cause   the cause
	 */
	public ServicioErrorException(final String message, final Throwable cause) {
		super(message, cause);
		this.pendienteLocalizar = false;
		this.bundle = null;
	}

	/**
	 * Instantiates a new servicio error exception.
	 *
	 * @param message the message
	 * @param args    the args
	 * @param cause   the cause
	 */
	public ServicioErrorException(final String message, final Object[] args, final Throwable cause) {
		super(message, cause);
		this.pendienteLocalizar = false;
		this.bundle = null;
		this.args = args;
	}

	/**
	 * Instantiates a new servicio error exception.
	 *
	 * @param message the message
	 * @param args    the args
	 */
	public ServicioErrorException(final String message, final Object[] args) {
		super(message);
		this.pendienteLocalizar = true;
		this.bundle = null;
		this.args = args;
	}

	/**
	 * Instantiates a new servicio error exception.
	 *
	 * @param bundle  the bundle
	 * @param message the message
	 * @param cause   the cause
	 */
	public ServicioErrorException(final String bundle, final String message, final Throwable cause) {
		super(message, cause);
		this.pendienteLocalizar = true;
		this.bundle = bundle;
	}

	/**
	 * Instantiates a new servicio error exception.
	 *
	 * @param bundle  the bundle
	 * @param message the message
	 * @param args    the args
	 * @param cause   the cause
	 */
	public ServicioErrorException(final String bundle, final String message, final Object[] args,
					final Throwable cause) {
		super(message, cause);
		this.pendienteLocalizar = true;
		this.bundle = bundle;
		this.args = args;
	}

	/**
	 * Instantiates a new servicio error exception.
	 *
	 * @param mensaje            the mensaje
	 * @param pendienteLocalizar the pendiente localizar
	 */
	public ServicioErrorException(final String mensaje, final boolean pendienteLocalizar) {
		super(mensaje);
		this.pendienteLocalizar = pendienteLocalizar;
		this.bundle = null;
	}

	/**
	 * Instantiates a new servicio error exception.
	 *
	 * @param mensaje            the mensaje
	 * @param args               the args
	 * @param pendienteLocalizar the pendiente localizar
	 */
	public ServicioErrorException(final String mensaje, final Object[] args, final boolean pendienteLocalizar) {
		super(mensaje);
		this.pendienteLocalizar = pendienteLocalizar;
		this.bundle = null;
		this.args = args;
	}

	/**
	 * Instantiates a new servicio error exception.
	 *
	 * @param bundle             the bundle
	 * @param mensaje            the mensaje
	 * @param pendienteLocalizar the pendiente localizar
	 */
	public ServicioErrorException(final String bundle, final String mensaje, final boolean pendienteLocalizar) {
		super(mensaje);
		this.pendienteLocalizar = pendienteLocalizar;
		this.bundle = bundle;
	}

	/**
	 * Instantiates a new servicio error exception.
	 *
	 * @param bundle             the bundle
	 * @param mensaje            the mensaje
	 * @param args               the args
	 * @param pendienteLocalizar the pendiente localizar
	 */
	public ServicioErrorException(final String bundle, final String mensaje, final Object[] args,
					final boolean pendienteLocalizar) {
		super(mensaje);
		this.pendienteLocalizar = pendienteLocalizar;
		this.bundle = bundle;
		this.args = args;
	}

	/**
	 * Instantiates a new servicio error exception.
	 *
	 * @param message            the message
	 * @param pendienteLocalizar the pendiente localizar
	 * @param cause              the cause
	 */
	public ServicioErrorException(final String message, final boolean pendienteLocalizar, final Throwable cause) {
		super(message, cause);
		this.pendienteLocalizar = pendienteLocalizar;
		this.bundle = null;
	}

	/**
	 * Instantiates a new servicio error exception.
	 *
	 * @param message            the message
	 * @param args               the args
	 * @param pendienteLocalizar the pendiente localizar
	 * @param cause              the cause
	 */
	public ServicioErrorException(final String message, final Object[] args, final boolean pendienteLocalizar,
					final Throwable cause) {
		super(message, cause);
		this.pendienteLocalizar = pendienteLocalizar;
		this.bundle = null;
		this.args = args;
	}

	/**
	 * Instantiates a new servicio error exception.
	 *
	 * @param bundle             the bundle
	 * @param message            the message
	 * @param pendienteLocalizar the pendiente localizar
	 * @param cause              the cause
	 */
	public ServicioErrorException(final String bundle, final String message, final boolean pendienteLocalizar,
					final Throwable cause) {
		super(message, cause);
		this.pendienteLocalizar = pendienteLocalizar;
		this.bundle = bundle;
	}

	/**
	 * Instantiates a new servicio error exception.
	 *
	 * @param bundle             the bundle
	 * @param message            the message
	 * @param args               the args
	 * @param pendienteLocalizar the pendiente localizar
	 * @param cause              the cause
	 */
	public ServicioErrorException(final String bundle, final String message, final Object[] args,
					final boolean pendienteLocalizar, final Throwable cause) {
		super(message, cause);
		this.pendienteLocalizar = pendienteLocalizar;
		this.bundle = bundle;
		this.args = args;
	}

	/**
	 * Instantiates a new servicio error exception.
	 *
	 * @param cause the cause
	 */
	public ServicioErrorException(final Throwable cause) {
		super(cause);
		this.pendienteLocalizar = null;
		this.bundle = null;
	}

	/**
	 * To servicio exception.
	 *
	 * @return the servicio exception
	 */
	public ServicioException toServicioException() {
		return new ServicioException(this.bundle, this.getMessage(), this.args, this.getCause());
	}

	/**
	 * Gets the final encapsulated exception.
	 *
	 * @return the final encapsulated exception
	 */
	public Throwable getFinalEncapsulatedException() {
		if (this.getCause() instanceof ServicioErrorException) {
			return ((ServicioErrorException) this.getCause()).getFinalEncapsulatedException();
		} else {
			return this.getCause();
		}
	}

	public String getMensajeLocalizadoString() {
		if (this.pendienteLocalizar) {
			return Mensajes.getStringSafe(this.bundle, this.getMessage(), this.args, LocaleContextHolder.getLocale());
		} else {
			return this.getMessage();
		}

	}

	public String getMensajeLocalizadoString(final Locale locale) {
		if (this.pendienteLocalizar) {
			return Mensajes.getStringSafe(this.bundle, this.getMessage(), this.args, locale);
		} else {
			return this.getMessage();
		}
	}

	/**
	 * Gets the bundle.
	 *
	 * @return the bundle
	 */
	public String getBundle() {
		return this.bundle;
	}

	/**
	 * Sets the bundle.
	 *
	 * @param bundle the new bundle
	 */
	public void setBundle(final String bundle) {
		this.bundle = bundle;
	}

	/**
	 * Gets the pendiente localizar.
	 *
	 * @return the pendiente localizar
	 */
	public Boolean getPendienteLocalizar() {
		return this.pendienteLocalizar;
	}

	/**
	 * Sets the pendiente localizar.
	 *
	 * @param pendienteLocalizar the new pendiente localizar
	 */
	public void setPendienteLocalizar(final Boolean pendienteLocalizar) {
		this.pendienteLocalizar = pendienteLocalizar;
	}

	/**
	 * Gets the args.
	 *
	 * @return the args
	 */
	public Object[] getArgs() {
		return this.args;
	}

	/**
	 * Sets the args.
	 *
	 * @param args the new args
	 */
	public void setArgs(final Object[] args) {
		this.args = args;
	}

	/**
	 * Checks if is ajax.
	 *
	 * @return true, if is ajax
	 */
	public boolean isAjax() {
		return this.ajax;
	}

	/**
	 * Sets the ajax.
	 *
	 * @param ajax the new ajax
	 */
	public void setAjax(final boolean ajax) {
		this.ajax = ajax;
	}
}
