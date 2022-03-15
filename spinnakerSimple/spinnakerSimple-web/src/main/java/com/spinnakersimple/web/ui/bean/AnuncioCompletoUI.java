package com.spinnakersimple.web.ui.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.capitanperegrina.common.web.ui.CaptchaUI;
import com.spinnakersimple.modelo.util.SpinnakerSimpleGlobals;

public class AnuncioCompletoUI extends CaptchaUI implements Serializable {

	private static final long serialVersionUID = -7781150391292863793L;


	/** The anuncio. */
	protected AnuncioUI anuncio;

	protected TipoVelaUI tipoVela;

	/** The usuario. */
	protected UsuarioUI usuario;

	/** The fotos. */
	protected List<FotografiaUI> fotos = new ArrayList<>();

	/** The mensajes. */
	protected List<CompradoresUI> mensajes = new ArrayList<>();

	public AnuncioUI getAnuncio() {
		return this.anuncio;
	}

	public void setAnuncio(final AnuncioUI anuncio) {
		this.anuncio = anuncio;
	}

	public UsuarioUI getUsuario() {
		return this.usuario;
	}

	public void setUsuario(final UsuarioUI usuario) {
		this.usuario = usuario;
	}

	public List<FotografiaUI> getFotos() {
		return this.fotos;
	}

	public void setFotos(final List<FotografiaUI> fotos) {
		this.fotos = fotos;
	}

	public List<CompradoresUI> getMensajes() {
		return this.mensajes;
	}

	public void setMensajes(final List<CompradoresUI> mensajes) {
		this.mensajes = mensajes;
	}

	public TipoVelaUI getTipoVela() {
		return this.tipoVela;
	}

	public void setTipoVela(final TipoVelaUI tipoVela) {
		this.tipoVela = tipoVela;
	}

	/**
	 * Gets the foto 1.
	 * @return the foto 1
	 */
	public String getFoto1() {
		return obtenFoto(1);
	}

	/**
	 * Obten foto.
	 * @param indice the indice
	 * @return the string
	 */
	public String obtenFoto(final int indice) {
		if (this.fotos != null && this.fotos.size() >= indice && this.fotos.get(indice - 1).getImagen() != null
				&& this.fotos.get(indice - 1).getImagen().length() != 0) {
			return this.fotos.get(indice - 1).getImagen();
		} else {
			switch (this.anuncio.getTipoVela()) {
			case "1":
				return SpinnakerSimpleGlobals.FOTO_ASIM;
			case "2":
				return SpinnakerSimpleGlobals.FOTO_MAYOR;
			case "3":
				return SpinnakerSimpleGlobals.FOTO_FOQUE;
			case "4":
				return SpinnakerSimpleGlobals.FOTO_SPI;
			case "5":
				return SpinnakerSimpleGlobals.FOTO_CANGREJA;
			case "6":
				return SpinnakerSimpleGlobals.FOTO_COMETA;
			default:
				return "";
			}
		}
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("AnuncioCompletoUI [anuncio=").append(this.anuncio).append(", tipoVela=").append(this.tipoVela).append(", usuario=").append(this.usuario).append(", fotos=").append(this.fotos).append(", mensajes=").append(this.mensajes).append("]");
		return builder.toString();
	}
}
