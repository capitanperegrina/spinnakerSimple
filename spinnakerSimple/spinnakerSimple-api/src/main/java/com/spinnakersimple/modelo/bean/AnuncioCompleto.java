package com.spinnakersimple.modelo.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.spinnakersimple.modelo.entidad.AnuncioPOJO;
import com.spinnakersimple.modelo.entidad.CompradoresPOJO;
import com.spinnakersimple.modelo.entidad.FotografiaPOJO;
import com.spinnakersimple.modelo.entidad.TipoVelaPOJO;
import com.spinnakersimple.modelo.entidad.UsuarioPOJO;

/**
 * The Class AnuncioCompleto.
 */
public class AnuncioCompleto implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1540737604575737442L;

	/** The anuncio. */
	protected AnuncioPOJO anuncio;

	/** The usuario. */
	protected UsuarioPOJO usuario;

	protected TipoVelaPOJO tipoVela;

	/** The fotos. */
	protected List<FotografiaPOJO> fotos = new ArrayList<>();

	/** The mensajes. */
	protected List<CompradoresPOJO> mensajes = new ArrayList<>();

	/**
	 * Constructor por defecto de la clase anuncio completo.
	 */
	public AnuncioCompleto() {
		super();
		this.anuncio = new AnuncioPOJO();
		this.tipoVela = new TipoVelaPOJO();
		this.usuario = new UsuarioPOJO();
		this.fotos = new ArrayList<FotografiaPOJO>();
		this.mensajes = new ArrayList<CompradoresPOJO>();
	}

	/**
	 * Gets the anuncio.
	 * @return the anuncio
	 */
	public AnuncioPOJO getAnuncio() {
		return this.anuncio;
	}

	public TipoVelaPOJO getTipoVela() {
		return this.tipoVela;
	}

	public void setTipoVela(final TipoVelaPOJO tipoVela) {
		this.tipoVela = tipoVela;
	}

	/**
	 * Gets the fotos.
	 * @return the fotos
	 */
	public List<FotografiaPOJO> getFotos() {
		return this.fotos;
	}

	/**
	 * Gets the mensajes.
	 * @return the mensajes
	 */
	public List<CompradoresPOJO> getMensajes() {
		return this.mensajes;
	}

	/**
	 * Gets the nombre propietario.
	 * @return the nombre propietario
	 */
	public String getNombrePropietario() {
		return this.usuario.getNombre() + " " + this.usuario.getApellidos();
	}

	/**
	 * Gets the usuario.
	 * @return the usuario
	 */
	public UsuarioPOJO getUsuario() {
		return this.usuario;
	}

	/**
	 * Sets the anuncio.
	 * @param anuncio the new anuncio
	 */
	public void setAnuncio(final AnuncioPOJO anuncio) {
		this.anuncio = anuncio;
	}

	/**
	 * Sets the fotos.
	 * @param fotos the new fotos
	 */
	public void setFotos(final List<FotografiaPOJO> fotos) {
		this.fotos = fotos;
	}

	/**
	 * Sets the mensajes.
	 * @param mensajes the new mensajes
	 */
	public void setMensajes(final List<CompradoresPOJO> mensajes) {
		this.mensajes = mensajes;
	}

	/**
	 * Sets the usuario.
	 * @param usuario the new usuario
	 */
	public void setUsuario(final UsuarioPOJO usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("AnuncioCompleto [anuncio=").append(this.anuncio).append(", usuario=").append(this.usuario).append(", tipoVela=").append(this.tipoVela).append(", fotos=").append(this.fotos).append(", mensajes=").append(this.mensajes).append("]");
		return builder.toString();
	}

}
