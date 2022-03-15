package com.spinnakersimple.web.ui.bean;

import java.io.Serializable;

import com.capitanperegrina.common.web.ui.CaptchaUI;

/**
 * La clase Class FotografiaUI.
 * @author <a href="mailto:yo@capitanperegrina.com">Capitán Peregrina</a>
 */
public class FotografiaUI extends CaptchaUI implements Serializable {

	private static final long serialVersionUID = 6423940939147189877L;

	/** El campo id fotografia. */
	protected String idFotografia;

	/** El campo id anuncio. */
	protected String idAnuncio;

	/** El campo imagen. */
	protected String imagen;

	protected String idAnuncioCodificado;

	protected String idFotografiaCodificada;

	/**
	 * Obtiene la propiedad id fotografia.
	 * @return la propiedad id fotografia
	 */
	public String getIdFotografia() {
		return this.idFotografia;
	}

	/**
	 * Establece la propiedad id fotografia.
	 * @param idFotografia el nuevo valor de la propiedad id fotografia
	 */
	public void setIdFotografia(String idFotografia) {
		this.idFotografia = idFotografia;
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
	 * Obtiene la propiedad imagen.
	 * @return la propiedad imagen
	 */
	public String getImagen() {
		return this.imagen;
	}

	/**
	 * Establece la propiedad imagen.
	 * @param imagen el nuevo valor de la propiedad imagen
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getIdAnuncioCodificado() {
		return this.idAnuncioCodificado;
	}

	public void setIdAnuncioCodificado(String idAnuncioCodificado) {
		this.idAnuncioCodificado = idAnuncioCodificado;
	}

	public String getIdFotografiaCodificada() {
		return this.idFotografiaCodificada;
	}

	public void setIdFotografiaCodificada(String idFotografiaCodificada) {
		this.idFotografiaCodificada = idFotografiaCodificada;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("FotografiaUI [\n    idFotografia=").append(this.idFotografia).append(" \nidAnuncio=")
				.append(this.idAnuncio).append(" \nimagen=").append(this.imagen).append(" \nidFotografiaCodificada=")
				.append(this.idFotografiaCodificada).append("]");
		return builder.toString();
	}
}
