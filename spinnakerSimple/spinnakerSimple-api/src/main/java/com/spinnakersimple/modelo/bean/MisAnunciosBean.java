package com.spinnakersimple.modelo.bean;

import java.io.Serializable;
import java.util.Arrays;

/**
 * The Class MisAnunciosBean.
 */
public class MisAnunciosBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The tipo vela. */
	protected Integer tipoVela;

	/** The pendientes. */
	protected String[] tipoAnuncio;

	/**
	 * Instantiates a new mis anuncios form DTO.
	 */
	public MisAnunciosBean() {
		super();
	}

	/**
	 * Gets the tipo anuncio.
	 *
	 * @return the tipo anuncio
	 */
	public String[] getTipoAnuncio() {
		return this.tipoAnuncio;
	}

	/**
	 * Gets the tipo vela.
	 *
	 * @return the tipo vela
	 */
	public Integer getTipoVela() {
		return this.tipoVela;
	}

	/**
	 * Sets the tipo anuncio.
	 *
	 * @param tipoAnuncio
	 *            the new tipo anuncio
	 */
	public void setTipoAnuncio(String[] tipoAnuncio) {
		this.tipoAnuncio = tipoAnuncio;
	}

	/**
	 * Sets the tipo vela.
	 *
	 * @param tipoVela
	 *            the new tipo vela
	 */
	public void setTipoVela(Integer tipoVela) {
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
