package com.capitanperegrina.common.modelo.entidad.tabla;

import java.io.Serializable;
import java.util.Calendar;

/**
 * POJO identificado con la tabla <code>social</code>
 */
public class SocialPOJO implements Serializable {
	private static final long serialVersionUID = -59736377739L;

	protected Integer idSocial;
	protected Integer entidad;
	protected Integer clave;
	protected Integer facebook;
	protected Integer twitter;
	protected Integer instagram;
	protected Integer idUsuarioAlta;
	protected Calendar fecAlta;
	protected Integer idUsuarioModif;
	protected Calendar fecModif;

	/**
	 * Constructor por defecto.
	 */
	public SocialPOJO() {
		super();
	}

	public Integer getIdSocial() {
		return this.idSocial;
	}

	public void setIdSocial(final Integer idSocial) {
		this.idSocial = idSocial;
	}

	public Integer getEntidad() {
		return this.entidad;
	}

	public void setEntidad(final Integer entidad) {
		this.entidad = entidad;
	}

	public Integer getClave() {
		return this.clave;
	}

	public void setClave(final Integer clave) {
		this.clave = clave;
	}

	public Integer getFacebook() {
		return this.facebook;
	}

	public void setFacebook(final Integer facebook) {
		this.facebook = facebook;
	}

	public Integer getTwitter() {
		return this.twitter;
	}

	public void setTwitter(final Integer twitter) {
		this.twitter = twitter;
	}

	public Integer getInstagram() {
		return this.instagram;
	}

	public void setInstagram(final Integer instagram) {
		this.instagram = instagram;
	}

	public Integer getIdUsuarioAlta() {
		return this.idUsuarioAlta;
	}

	public void setIdUsuarioAlta(final Integer idUsuarioAlta) {
		this.idUsuarioAlta = idUsuarioAlta;
	}

	public Calendar getFecAlta() {
		return this.fecAlta;
	}

	public void setFecAlta(final Calendar fecAlta) {
		this.fecAlta = fecAlta;
	}

	public Integer getIdUsuarioModif() {
		return this.idUsuarioModif;
	}

	public void setIdUsuarioModif(final Integer idUsuarioModif) {
		this.idUsuarioModif = idUsuarioModif;
	}

	public Calendar getFecModif() {
		return this.fecModif;
	}

	public void setFecModif(final Calendar fecModif) {
		this.fecModif = fecModif;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("SocialPOJO [idSocial=").append(this.idSocial).append(", entidad=").append(this.entidad).append(", clave=").append(this.clave).append(", facebook=").append(this.facebook).append(", twitter=").append(this.twitter).append(", instagram=").append(this.instagram).append(", idUsuarioAlta=").append(this.idUsuarioAlta).append(", fecAlta=").append(this.fecAlta).append(", idUsuarioModif=").append(this.idUsuarioModif).append(", fecModif=").append(this.fecModif).append("]");
		return builder.toString();
	}
}