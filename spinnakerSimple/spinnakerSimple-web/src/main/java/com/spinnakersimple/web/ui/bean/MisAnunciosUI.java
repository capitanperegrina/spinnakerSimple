package com.spinnakersimple.web.ui.bean;

import java.io.Serializable;
import java.util.Arrays;

import com.capitanperegrina.common.web.ui.CaptchaUI;

public class MisAnunciosUI extends CaptchaUI implements Serializable {

	private static final long serialVersionUID = -3210390301898124927L;

	/** The tipo vela. */
	protected String tipoVela;

	/** The pendientes. */
	protected String[] tipoAnuncio;

	/**
	 * Instantiates a new mis anuncios form DTO.
	 */
	public MisAnunciosUI() {
		super();
	}

	/**
	 * Gets the tipo anuncio.
	 * @return the tipo anuncio
	 */
	public String[] getTipoAnuncio() {
		return this.tipoAnuncio;
	}

	/**
	 * Gets the tipo vela.
	 * @return the tipo vela
	 */
	public String getTipoVela() {
		return this.tipoVela;
	}

	/**
	 * Sets the tipo anuncio.
	 * @param tipoAnuncio the new tipo anuncio
	 */
	public void setTipoAnuncio(String[] tipoAnuncio) {
		this.tipoAnuncio = tipoAnuncio;
	}

	/**
	 * Sets the tipo vela.
	 * @param tipoVela the new tipo vela
	 */
	public void setTipoVela(String tipoVela) {
		this.tipoVela = tipoVela;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MisAnunciosBean [tipoVela=" + this.tipoVela + ", tipoAnuncio=" + Arrays.toString(this.tipoAnuncio)
				+ "]";
	}
}
