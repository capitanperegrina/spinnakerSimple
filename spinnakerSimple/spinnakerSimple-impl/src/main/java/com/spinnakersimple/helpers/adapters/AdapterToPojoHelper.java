package com.spinnakersimple.helpers.adapters;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.capitanperegrina.common.basebeans.CambioEnTabla;
import com.capitanperegrina.common.seguridad.GeneradorClaves;
import com.capitanperegrina.common.utils.Cadenas;
import com.capitanperegrina.common.utils.Imagenes;
import com.capitanperegrina.common.validators.Validadores;
import com.capitanperegrina.common.web.FormUtils;
import com.spinnakersimple.modelo.dto.ImagenDTO;
import com.spinnakersimple.modelo.dto.VenderVelaDTO;
import com.spinnakersimple.modelo.entidad.AnuncioPOJO;
import com.spinnakersimple.modelo.entidad.FotografiaPOJO;
import com.spinnakersimple.modelo.entidad.UsuarioPOJO;
import com.spinnakersimple.modelo.util.SpinnakerSimpleGlobals;

/**
 * The Class AdapterToPojoHelper.
 */
@Component
public class AdapterToPojoHelper {

	Logger log = Logger.getLogger(AdapterToPojoHelper.class);

	/**
	 * Instantiates a new adapter to pojo.
	 */
	private AdapterToPojoHelper() {
		super();
	}

	public UsuarioPOJO adaptToUsuarioPOJO(final VenderVelaDTO dto) {
		final UsuarioPOJO u = new UsuarioPOJO();
		u.setIdUsuario(0);
		u.setNombre(Cadenas.trimNoNulo(dto.getNombre()));
		u.setApellidos(Cadenas.trimNoNulo(dto.getApellidos()));
		u.setMail(Cadenas.trimNoNulo(dto.getEmail()).toLowerCase());
		u.setMovil(Cadenas.trimNoNulo(dto.getMovil()));
		u.setDireccion1(Cadenas.trimNoNulo(dto.getDireccion1()));
		u.setDireccion2(Cadenas.trimNoNulo(dto.getDireccion2()));
		u.setPais(dto.getPais());
		u.setDivisa(dto.getDivisa());
		u.setProvincia(Cadenas.trimNoNulo(dto.getProvincia()));
		u.setCodPostal(Cadenas.trimNoNulo(dto.getCodPostal()));
		u.setAdmin(SpinnakerSimpleGlobals.USUARIO_NO_ADMIN);
		u.setPass(GeneradorClaves.generaPassword(8));
		u.setLang(LocaleContextHolder.getLocale().getLanguage().toUpperCase());
		return u;
	}

	/**
	 * To anuncio POJO.
	 * 
	 * @param dto          the dto
	 * @param ct           the ct
	 * @param tipoDeCambio the tipo de cambio
	 * @return the anuncio POJO
	 */
	public AnuncioPOJO toAnuncioPOJO(final VenderVelaDTO dto, final CambioEnTabla ct, final double tipoDeCambio) {
		final AnuncioPOJO a = new AnuncioPOJO();
		if (Validadores.estaVacia(dto.getIdAnuncio())) {
			a.setIdAnuncio(0);
		} else {
			a.setIdAnuncio(Integer.parseInt(dto.getIdAnuncio()));
		}
		a.setTituloAnuncio(Cadenas.trimNoNulo(dto.getTituloAnuncio()));
		a.setTipoVela(Integer.parseInt(dto.getTipoVela()));
		if (!Validadores.estaVacia(dto.getGratil())) {
			a.setGratil(FormUtils.parseBigDecimalFormField(Cadenas.trimNoNulo(dto.getGratil()), LocaleContextHolder.getLocale()));
		}
		if (!Validadores.estaVacia(dto.getBaluma())) {
			a.setBaluma(FormUtils.parseBigDecimalFormField(Cadenas.trimNoNulo(dto.getBaluma()), LocaleContextHolder.getLocale()));
		}
		if (!Validadores.estaVacia(dto.getPujamen())) {
			a.setPujamen(FormUtils.parseBigDecimalFormField(Cadenas.trimNoNulo(dto.getPujamen()), LocaleContextHolder.getLocale()));
		}
		a.setDescripcion(Cadenas.trimNoNulo(dto.getDescripcion()));
		a.setPrecio(BigDecimal.valueOf(FormUtils.parseBigDecimalFormField(dto.getPrecio(), LocaleContextHolder.getLocale()).doubleValue() / tipoDeCambio));
		a.setVisible(SpinnakerSimpleGlobals.ANUNCIO_NO_VISIBLE);
		a.setFecAlta(ct.getFec());
		a.setFecModif(ct.getFec());
		a.setIdUsuarioAlta(ct.getUsu());
		a.setIdUsuarioModif(ct.getUsu());
		final Calendar caduca = Calendar.getInstance();
		caduca.add(Calendar.DAY_OF_YEAR, 90);
		a.setCaduca(caduca);
		return a;
	}

	// public AnuncioPOJO toAnuncioPOJO(final AnuncioDTO dto) {
	// final AnuncioPOJO pojo = new AnuncioPOJO();
	// pojo.setIdAnuncio(dto.getIdAnuncio());
	// pojo.setTituloAnuncio(dto.getTituloAnuncio());
	// pojo.setTipoVela(dto.getTipoVela());
	// pojo.setGratil(dto.getGratil());
	// pojo.setBaluma(dto.getBaluma());
	// pojo.setPujamen(dto.getPujamen());
	// pojo.setDescripcion(dto.getDescripcion());
	// pojo.setPrecio(dto.getPrecio());
	// pojo.setCaduca(dto.getCaduca());
	// pojo.setVisible(dto.getVisible());
	// pojo.setIdUsuarioAlta(dto.getIdUsuarioAlta());
	// pojo.setFecAlta(dto.getFecAlta());
	// pojo.setIdUsuarioModif(dto.getIdUsuarioModif());
	// pojo.setFecModif(dto.getFecModif());
	// return pojo;
	// }

	/**
	 * To fotografias POJO list.
	 * 
	 * @param dto the dto
	 * @param a   the a
	 * @return the list
	 */
	public List<FotografiaPOJO> toFotografiasPOJOList(final VenderVelaDTO dto, final AnuncioPOJO a) {
		final List<FotografiaPOJO> fotos = new ArrayList<>(dto.getFiles().size());
		for (final ImagenDTO file : dto.getFiles()) {
			final FotografiaPOJO f = new FotografiaPOJO();
			f.setIdFotografia(0);
			f.setIdAnuncio(a.getIdAnuncio());
			f.setImagen(Imagenes.resizeImage(file.getBytes(), file.getContentType(), SpinnakerSimpleGlobals.IMG_MAX_WIDTH));
			f.setTipo(file.getContentType());
			fotos.add(f);
		}
		return fotos;
	}

	// /**
	// * To compradores POJO.
	// * @param dto the dto
	// * @return the compradores POJO
	// */
	// public static CompradoresPOJO toCompradoresPOJO(CompradoresDTO dto) {
	// final CompradoresPOJO pojo = new CompradoresPOJO();
	// pojo.setIdComprador(dto.getIdComprador());
	// pojo.setIdAnuncio(dto.getIdAnuncio());
	// pojo.setNombre(dto.getNombre());
	// pojo.setMail(dto.getMail());
	// pojo.setObservaciones(dto.getObservaciones());
	// pojo.setFecha(dto.getFecha());
	// pojo.setRevisado(dto.getRevisado());
	// pojo.setTelefono(dto.getTelefono());
	// return pojo;
	// }
}
