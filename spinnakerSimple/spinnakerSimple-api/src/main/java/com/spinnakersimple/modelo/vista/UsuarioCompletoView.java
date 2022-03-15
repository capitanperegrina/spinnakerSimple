package com.spinnakersimple.modelo.vista;

import com.spinnakersimple.modelo.entidad.UsuarioPOJO;

/**
 * The Class UsuarioAnunciosView.
 */
public class UsuarioCompletoView extends UsuarioPOJO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7176728710620054611L;

	/** The num anuncios. */
	private Integer numAnuncios;
	
	/**
	 * Instantiates a new usuario anuncios view.
	 */
	public UsuarioCompletoView() {
		super();
	}

	/**
	 * Gets the num anuncios.
	 *
	 * @return the num anuncios
	 */
	public Integer getNumAnuncios() {
		return numAnuncios;
	}

	/**
	 * Sets the num anuncios.
	 *
	 * @param numAnuncios the new num anuncios
	 */
	public void setNumAnuncios(Integer numAnuncios) {
		this.numAnuncios = numAnuncios;
	}

	/* (non-Javadoc)
	 * @see com.spinnakersimple.modelo.entidad.UsuarioPOJO#toString()
	 */
	@Override
	public String toString() {
		return "UsuarioAnunciosView [numAnuncios=" + numAnuncios + "]";
	}
}
