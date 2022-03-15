package com.spinnakersimple.web.ui.bean;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.capitanperegrina.common.web.ui.CaptchaUI;

/**
 * La clase Class CompradoresUI.
 * @author <a href="mailto:yo@capitanperegrina.com">Capitán Peregrina</a>
 */
public class CompradoresUI extends CaptchaUI implements Serializable {

	/** La constante serialVersionUID. */
	private static final long serialVersionUID = -6968872911360108837L;

	/** The id comprador. */
	protected String idComprador;

	/** The id anuncio. */
	protected String idAnuncio;

	/** The nombre. */
	protected String nombre;

	/** The mail. */
	protected String mail;

	/** The observaciones. */
	protected String observaciones;

	/** The fecha. */
	protected String fecha;

	/** The revisado. */
	protected String revisado;

	/** The telefono. */
	protected String telefono;

	/** The id comprador codificado. */
	protected String idCompradorCodificado;

	protected String fechaOrdenacion;

	/**
	 * Constructor por defecto.
	 */
	public CompradoresUI() {
		super();
	}

	/**
	 * Obtiene la propiedad id comprador.
	 * @return la propiedad id comprador
	 */
	public String getIdComprador() {
		return this.idComprador;
	}

	/**
	 * Establece la propiedad id comprador.
	 * @param idComprador el nuevo valor de la propiedad id comprador
	 */
	public void setIdComprador(String idComprador) {
		this.idComprador = idComprador;
	}

	/**
	 * Obtiene la propiedad id anuncio.
	 * @return la propiedad id anuncio
	 */
	public String getIdAnuncio() {
		return this.idAnuncio;
	}

	/**
	 * Establece la propiedad id anuncio.
	 * @param idAnuncio el nuevo valor de la propiedad id anuncio
	 */
	public void setIdAnuncio(String idAnuncio) {
		this.idAnuncio = idAnuncio;
	}

	/**
	 * Obtiene la propiedad nombre.
	 * @return la propiedad nombre
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * Establece la propiedad nombre.
	 * @param nombre el nuevo valor de la propiedad nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene la propiedad mail.
	 * @return la propiedad mail
	 */
	public String getMail() {
		return this.mail;
	}

	/**
	 * Establece la propiedad mail.
	 * @param mail el nuevo valor de la propiedad mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Obtiene la propiedad observaciones.
	 * @return la propiedad observaciones
	 */
	public String getObservaciones() {
		return this.observaciones;
	}

	/**
	 * Establece la propiedad observaciones.
	 * @param observaciones el nuevo valor de la propiedad observaciones
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * Obtiene la propiedad fecha.
	 * @return la propiedad fecha
	 */
	public String getFecha() {
		return this.fecha;
	}

	/**
	 * Establece la propiedad fecha.
	 * @param fecha el nuevo valor de la propiedad fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Obtiene la propiedad revisado.
	 * @return la propiedad revisado
	 */
	public String getRevisado() {
		return this.revisado;
	}

	/**
	 * Establece la propiedad revisado.
	 * @param revisado el nuevo valor de la propiedad revisado
	 */
	public void setRevisado(String revisado) {
		this.revisado = revisado;
	}

	/**
	 * Obtiene la propiedad telefono.
	 * @return la propiedad telefono
	 */
	public String getTelefono() {
		return this.telefono;
	}

	/**
	 * Establece la propiedad telefono.
	 * @param telefono el nuevo valor de la propiedad telefono
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Obtiene la propiedad id comprador codificado.
	 * @return la propiedad id comprador codificado
	 */
	public String getIdCompradorCodificado() {
		return this.idCompradorCodificado;
	}

	/**
	 * Establece la propiedad id comprador codificado.
	 * @param idCompradorCodificado el nuevo valor de la propiedad id comprador
	 *            codificado
	 */
	public void setIdCompradorCodificado(String idCompradorCodificado) {
		this.idCompradorCodificado = idCompradorCodificado;
	}

	public String getFechaOrdenacion() {
		return this.fechaOrdenacion;
	}

	public void setFechaOrdenacion(String fechaOrdenacion) {
		this.fechaOrdenacion = fechaOrdenacion;
	}

	public String getExtractoTitulo() {
		if (StringUtils.trimToEmpty(this.observaciones).length() > 100) {
			return this.observaciones.substring(0, 100) + "...";
		} else {
			return this.observaciones;
		}
	}

	public String getBotonera() {
		return getBotonVerMensaje();
	}

	/**
	 * Gets the boton ver anuncio.
	 *
	 * @return the boton ver anuncio
	 */
	public String getBotonVerMensaje() {
		StringBuilder sb = new StringBuilder();

		sb.append("<button id=\"btnVerAnuncio_" + this.idCompradorCodificado + "\" msg=\"" + this.observaciones + "\" cod=\"" + this.idCompradorCodificado
				+ "\" type=\"button\" class=\"btnVerMensaje btn btn-warning btn-circle");
		sb.append("\"><i class=\"fa fa-eye\"></i>");
		sb.append("</button>");
		return sb.toString();
	}

	@Override
	public String toString() {
		return "CompradoresUI [idComprador=" + this.idComprador + ", idAnuncio=" + this.idAnuncio + ", nombre=" + this.nombre
				+ ", mail=" + this.mail + ", observaciones=" + this.observaciones + ", fecha=" + this.fecha + ", revisado=" + this.revisado
				+ ", telefono=" + this.telefono + ", idCompradorCodificado=" + this.idCompradorCodificado + ", fechaOrdenacion="
				+ this.fechaOrdenacion + "]";
	}
}
