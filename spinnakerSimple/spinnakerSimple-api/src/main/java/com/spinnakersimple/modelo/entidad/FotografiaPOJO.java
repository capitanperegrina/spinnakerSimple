package com.spinnakersimple.modelo.entidad;

import java.io.Serializable;
import java.util.Calendar;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * POJO identificado con la tabla <code>fotografia</code>
 */
public class FotografiaPOJO implements Serializable {
	private static final long serialVersionUID = -57273799168L;

	protected Integer idFotografia;
	protected Integer idAnuncio;
	protected byte[] imagen;
	protected String tipo;
	protected Integer idUsuarioAlta;
	protected Calendar fecAlta;
	protected Integer idUsuarioModif;
	protected Calendar fecModif;

	/**
	 * Constructor por defecto.
	 */
	public FotografiaPOJO() {
		super();
	}

	public Integer getIdFotografia() {
		return this.idFotografia;
	}

	public void setIdFotografia(Integer idFotografia) {
		this.idFotografia = idFotografia;
	}

	public Integer getIdAnuncio() {
		return this.idAnuncio;
	}

	public void setIdAnuncio(Integer idAnuncio) {
		this.idAnuncio = idAnuncio;
	}

	public byte[] getImagen() {
		return this.imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getIdUsuarioAlta() {
		return this.idUsuarioAlta;
	}

	public void setIdUsuarioAlta(Integer idUsuarioAlta) {
		this.idUsuarioAlta = idUsuarioAlta;
	}

	public Calendar getFecAlta() {
		return this.fecAlta;
	}

	public void setFecAlta(Calendar fecAlta) {
		this.fecAlta = fecAlta;
	}

	public Integer getIdUsuarioModif() {
		return this.idUsuarioModif;
	}

	public void setIdUsuarioModif(Integer idUsuarioModif) {
		this.idUsuarioModif = idUsuarioModif;
	}

	public Calendar getFecModif() {
		return this.fecModif;
	}

	public void setFecModif(Calendar fecModif) {
		this.fecModif = fecModif;
	}

	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
		return ReflectionToStringBuilder.toString(this);
	}
}