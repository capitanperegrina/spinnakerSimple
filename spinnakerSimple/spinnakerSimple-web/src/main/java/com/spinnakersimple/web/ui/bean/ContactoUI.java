package com.spinnakersimple.web.ui.bean;

import java.io.Serializable;

import com.capitanperegrina.common.web.ui.CaptchaUI;

/**
 * The Class ContactoUI.
 */
public class ContactoUI extends CaptchaUI implements Serializable {

	private static final long serialVersionUID = 1767529734700216788L;

	/** The nombre. */
	protected String nombre;

	/** The email. */
	protected String email;

	/** The consulta. */
	protected String consulta;

	protected String privacidad;
	protected String terminos;

	/**
	 * Instantiates a new contacto form DTO.
	 */
	public ContactoUI() {
		super();
	}

	/**
	 * Gets the consulta.
	 *
	 * @return the consulta
	 */
	public String getConsulta() {
		return this.consulta;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * Sets the consulta.
	 *
	 * @param consulta the new consulta
	 */
	public void setConsulta(final String consulta) {
		this.consulta = consulta;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Reset.
	 */
	public void reset() {
		this.nombre = "";
		this.email = "";
		this.consulta = "";
	}

	public String getPrivacidad() {
		return this.privacidad;
	}

	public void setPrivacidad(final String privacidad) {
		this.privacidad = privacidad;
	}

	public String getTerminos() {
		return this.terminos;
	}

	public void setTerminos(final String terminos) {
		this.terminos = terminos;
	}

	@Override
	public String toString() {
		return "ContactoUI [nombre=" + this.nombre + ", email=" + this.email + ", consulta=" + this.consulta + ", privacidad=" + this.privacidad + ", terminos=" + this.terminos + "]";
	}
}
