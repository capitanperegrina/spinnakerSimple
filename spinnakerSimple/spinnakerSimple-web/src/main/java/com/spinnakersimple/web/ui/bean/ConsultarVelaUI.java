package com.spinnakersimple.web.ui.bean;

import java.io.Serializable;

import com.capitanperegrina.common.web.ui.CaptchaUI;

/**
 * The Class ConsultarVelaUI.
 */
public class ConsultarVelaUI extends CaptchaUI implements Serializable {

	private static final long serialVersionUID = -1480636396049931838L;

	/** The ida. */
	protected String ida;

	/** The nombre. */
	protected String nombre;

	/** The mail. */
	protected String mail;

	/** The telefono. */
	protected String telefono;

	/** The observaciones. */
	protected String observaciones;

	protected String privacidad;
	protected String terminos;

	/**
	 * Instantiates a new consultar vela DTO.
	 */
	public ConsultarVelaUI() {
		super();
	}

	/**
	 * Gets the ida.
	 *
	 * @return the ida
	 */
	public String getIda() {
		return this.ida;
	}

	/**
	 * Gets the mail.
	 *
	 * @return the mail
	 */
	public String getMail() {
		return this.mail;
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
	 * Gets the observaciones.
	 *
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return this.observaciones;
	}

	/**
	 * Gets the telefono.
	 *
	 * @return the telefono
	 */
	public String getTelefono() {
		return this.telefono;
	}

	/**
	 * Sets the ida.
	 *
	 * @param ida the new ida
	 */
	public void setIda(final String ida) {
		this.ida = ida;
	}

	/**
	 * Sets the mail.
	 *
	 * @param mail the new mail
	 */
	public void setMail(final String mail) {
		this.mail = mail;
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
	 * Sets the observaciones.
	 *
	 * @param observaciones the new observaciones
	 */
	public void setObservaciones(final String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * Sets the telefono.
	 *
	 * @param telefono the new telefono
	 */
	public void setTelefono(final String telefono) {
		this.telefono = telefono;
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
		return "ConsultarVelaUI [ida=" + this.ida + ", nombre=" + this.nombre + ", mail=" + this.mail + ", telefono=" + this.telefono + ", observaciones=" + this.observaciones + ", privacidad=" + this.privacidad + ", terminos=" + this.terminos + "]";
	}

}
