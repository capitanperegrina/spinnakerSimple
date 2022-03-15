package com.spinnakersimple.modelo.dto;

import java.io.Serializable;

/**
 * The Class ContactoDTO.
 */
public class ContactoDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3462346234634L;

	/** The nombre. */
	protected String nombre;

	/** The email. */
	protected String email;

	/** The consulta. */
	protected String consulta;

	/**
	 * Instantiates a new contacto form DTO.
	 */
	public ContactoDTO() {
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
	 * @param consulta
	 *            the new consulta
	 */
	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre
	 *            the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ContactoFormDTO [nombre=" + this.nombre + ", email=" + this.email + ", consulta=" + this.consulta + "]";
	}
}
