package com.spinnakersimple.web.ui.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.multipart.MultipartFile;

import com.capitanperegrina.common.basebeans.CambioEnTabla;
import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.exceptions.ServicioErrorException;
import com.capitanperegrina.common.seguridad.GeneradorClaves;
import com.capitanperegrina.common.utils.Cadenas;
import com.capitanperegrina.common.utils.Imagenes;
import com.capitanperegrina.common.validators.Validadores;
import com.capitanperegrina.common.web.FormUtils;
import com.spinnakersimple.modelo.bean.AnuncioCompleto;
import com.spinnakersimple.modelo.bean.MisAnunciosBean;
import com.spinnakersimple.modelo.dto.ContactoDTO;
import com.spinnakersimple.modelo.dto.FiltroBusquedaVelaBean;
import com.spinnakersimple.modelo.dto.ImagenDTO;
import com.spinnakersimple.modelo.dto.VenderVelaDTO;
import com.spinnakersimple.modelo.entidad.AnuncioPOJO;
import com.spinnakersimple.modelo.entidad.CompradoresPOJO;
import com.spinnakersimple.modelo.entidad.FotografiaPOJO;
import com.spinnakersimple.modelo.entidad.UsuarioPOJO;
import com.spinnakersimple.modelo.util.OrdenacionAnuncios;
import com.spinnakersimple.modelo.util.SpinnakerSimpleGlobals;
import com.spinnakersimple.modelo.util.TipoOrdenacion;
import com.spinnakersimple.web.ui.bean.AnuncioUI;
import com.spinnakersimple.web.ui.bean.ComprarVelaUI;
import com.spinnakersimple.web.ui.bean.ConsultarVelaUI;
import com.spinnakersimple.web.ui.bean.ContactoUI;
import com.spinnakersimple.web.ui.bean.MisAnunciosUI;
import com.spinnakersimple.web.ui.bean.UsuarioUI;
import com.spinnakersimple.web.ui.bean.VenderVelaUI;

/**
 * The Class AdaptFromUI.
 *
 * @author <a href="mailto:yo@capitanperegrina.com">Capitán Peregrina</a>
 */
public class AdaptFromUI {

	/** The log. */
	private static Logger log = Logger.getLogger(AdaptFromUI.class);

	/**
	 * Instantiates a new adapter U ito DTO.
	 */
	private AdaptFromUI() {
		super();
	}

	/**
	 * To filtro busqueda vela bean.
	 *
	 * @param ui the ui
	 * @return the filtro busqueda vela bean
	 */
	public static FiltroBusquedaVelaBean toFiltroBusquedaVelaBean(final MisAnunciosUI ui) {
		final FiltroBusquedaVelaBean bean = new FiltroBusquedaVelaBean();
		bean.setTipoAnuncio(ui.getTipoAnuncio());
		bean.setTipoVela(FormUtils.parseIntegerFormField(ui.getTipoVela()));
		return bean;
	}

	/**
	 * To mis anuncios bean.
	 *
	 * @param ui the ui
	 * @return the mis anuncios bean
	 */
	public static MisAnunciosBean toMisAnunciosBean(final MisAnunciosUI ui) {
		final MisAnunciosBean bean = new MisAnunciosBean();
		bean.setTipoAnuncio(ui.getTipoAnuncio());
		bean.setTipoVela(FormUtils.parseIntegerFormField(ui.getTipoVela()));
		return bean;
	}

	/**
	 * Ui 2 dto.
	 *
	 * @param ui the ui
	 * @return the contacto DTO
	 */
	public static ContactoDTO ui2dto(final ContactoUI ui) {
		final ContactoDTO dto = new ContactoDTO();
		dto.setConsulta(ui.getConsulta());
		dto.setEmail(ui.getEmail());
		dto.setNombre(ui.getNombre());
		return dto;
	}

	/**
	 * To compradores.
	 *
	 * @param ui the ui
	 * @return the compradores DTO
	 */
	public static CompradoresPOJO toCompradores(final ConsultarVelaUI ui) {
		final CompradoresPOJO obj = new CompradoresPOJO();
		obj.setIdComprador(null);
		obj.setIdAnuncio(FormUtils.parseIntegerFormField(ui.getIda()));
		obj.setNombre(StringUtils.trimToEmpty(ui.getNombre()));
		obj.setMail(StringUtils.trimToEmpty(ui.getMail()).toLowerCase());
		obj.setObservaciones(StringUtils.trimToEmpty(ui.getObservaciones()));
		obj.setFecha(Calendar.getInstance());
		obj.setRevisado("0");
		obj.setTelefono(StringUtils.trimToEmpty(ui.getTelefono()));
		return obj;
	}

	/**
	 * Adapt to usuario POJO.
	 *
	 * @param ui the ui
	 * @return the usuario POJO
	 */
	public static UsuarioPOJO toUsuarioPOJO(final UsuarioUI ui) {
		final UsuarioPOJO dto = new UsuarioPOJO();
		if (StringUtils.isEmpty(ui.getIdUsuario())) {
			dto.setIdUsuario(0);
		} else {
			dto.setIdUsuario(Integer.valueOf(ui.getIdUsuario()));
		}
		dto.setNombre(Cadenas.trimNoNulo(ui.getNombre()));
		dto.setApellidos(Cadenas.trimNoNulo(ui.getApellidos()));
		dto.setMail(Cadenas.trimNoNulo(ui.getMail()).toLowerCase());
		dto.setMovil(Cadenas.trimNoNulo(ui.getMovil()));
		dto.setDireccion1(Cadenas.trimNoNulo(ui.getDireccion1()));
		dto.setDireccion2(Cadenas.trimNoNulo(ui.getDireccion2()));
		dto.setPais(ui.getPais());
		dto.setDivisa(ui.getDivisa());
		dto.setProvincia(Cadenas.trimNoNulo(ui.getProvincia()));
		dto.setCodPostal(Cadenas.trimNoNulo(ui.getCodPostal()));
		if (StringUtils.isEmpty(ui.getAdmin())) {
			dto.setAdmin(SpinnakerSimpleGlobals.USUARIO_NO_ADMIN);
		} else {
			dto.setAdmin(ui.getAdmin());
		}
		dto.setPass(GeneradorClaves.generaPassword(8));
		dto.setLang(LocaleContextHolder.getLocale().getLanguage().toUpperCase());
		return dto;
	}

	public static AnuncioCompleto toAnuncioCompleto(final VenderVelaUI ui, final CambioEnTabla ct,
			final double tipoDeCambio) throws IOException {
		return toAnuncioCompleto(ui, ct, tipoDeCambio, null);
	}

	/**
	 * To anuncio completo.
	 *
	 * @param ui           the ui
	 * @param ct           the ct
	 * @param tipoDeCambio the tipo de cambio
	 * @return the anuncio completo
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static AnuncioCompleto toAnuncioCompleto(final VenderVelaUI ui, final CambioEnTabla ct,
			final double tipoDeCambio, final UsuarioPOJO u) throws IOException {

		final AnuncioCompleto ac = new AnuncioCompleto();
		ac.setAnuncio(new AnuncioPOJO());
		ac.setUsuario(new UsuarioPOJO());
		ac.setFotos(new ArrayList<FotografiaPOJO>());
		ac.setMensajes(new ArrayList<CompradoresPOJO>());

		// Comun
		ac.getAnuncio().setIdAnuncio(FormUtils.parseIntegerFormField(ui.getIdAnuncio()));
		if (ac.getAnuncio().getIdAnuncio() == null) {
			ac.getAnuncio().setIdAnuncio(0);
		}
		ac.getAnuncio().setTituloAnuncio(ui.getTituloAnuncio());
		ac.getAnuncio().setTipoBarco(FormUtils.parseIntegerFormField(ui.getTipoBarco()));
		ac.getAnuncio().setTipoVela(FormUtils.parseIntegerFormField(ui.getTipoVela()));
		ac.getAnuncio()
				.setGramaje(FormUtils.parseBigDecimalFormField(ui.getGramaje(), LocaleContextHolder.getLocale()));
		ac.getAnuncio().setGratil(FormUtils.parseBigDecimalFormField(ui.getGratil(), LocaleContextHolder.getLocale()));
		ac.getAnuncio().setBaluma(FormUtils.parseBigDecimalFormField(ui.getBaluma(), LocaleContextHolder.getLocale()));
		ac.getAnuncio()
				.setPujamen(FormUtils.parseBigDecimalFormField(ui.getPujamen(), LocaleContextHolder.getLocale()));
		ac.getAnuncio()
				.setCaidaProa(FormUtils.parseBigDecimalFormField(ui.getCaidaProa(), LocaleContextHolder.getLocale()));
		ac.getAnuncio()
				.setCaidaPopa(FormUtils.parseBigDecimalFormField(ui.getCaidaPopa(), LocaleContextHolder.getLocale()));
		ac.getAnuncio()
				.setSuperficie(FormUtils.parseBigDecimalFormField(ui.getSuperficie(), LocaleContextHolder.getLocale()));
		if (ui.getTipoCometa() != null) {
			ac.getAnuncio()
					.setTipoCometa(ui.getTipoCometa().equals(DefaultGlobalNames.NADA_SELECCIONADO_EN_LISTA) ? null
							: ui.getTipoCometa());
		} else {
			ac.getAnuncio().setTipoCometa(null);
		}
		ac.getAnuncio().setDescripcion(Cadenas.trimNoNulo(ui.getDescripcion()));
		ac.getAnuncio().setCaduca(FormUtils.parseCalendarFormField(ui.getCaduca(), LocaleContextHolder.getLocale()));
		if (ui.getDivisa().equals(DefaultGlobalNames.COD_DIVISA_EURO)) {
			ac.getAnuncio()
					.setPrecio(FormUtils.parseBigDecimalFormField(ui.getPrecio(), LocaleContextHolder.getLocale()));
		} else {
			ac.getAnuncio().setPrecio(BigDecimal.valueOf(
					FormUtils.parseBigDecimalFormField(ui.getPrecio(), LocaleContextHolder.getLocale()).doubleValue()
							/ tipoDeCambio));
		}
		ac.getAnuncio().setPais(ui.getPaisVela());

		if (SpinnakerSimpleGlobals.VENDER_TIPO_ALTA_ANUNCIO.contains(ui.getTipoAlta())) {
			ac.getAnuncio().setVisible(SpinnakerSimpleGlobals.ANUNCIO_NO_VISIBLE);
			ac.getAnuncio().setListado(0);
			ac.getAnuncio().setVisto(0);
			ac.getAnuncio().setComentado(0);
			ac.getAnuncio().setFecAlta(ct.getFec());
			ac.getAnuncio().setFecModif(ct.getFec());
			ac.getAnuncio().setIdUsuarioAlta(ct.getUsu());
			ac.getAnuncio().setIdUsuarioModif(ct.getUsu());
			final Calendar caduca = Calendar.getInstance();
			caduca.set(Calendar.HOUR, 0);
			caduca.set(Calendar.MINUTE, 0);
			caduca.set(Calendar.SECOND, 0);
			caduca.set(Calendar.MILLISECOND, 0);
			caduca.add(Calendar.DAY_OF_YEAR, SpinnakerSimpleGlobals.DIAS_CADUCIDAD);
			ac.getAnuncio().setCaduca(caduca);

			ac.setFotos(new ArrayList<FotografiaPOJO>());
			for (final MultipartFile mf : ui.getFiles()) {
				if (mf.getSize() > 0) {
					final FotografiaPOJO f = new FotografiaPOJO();
					f.setFecAlta(ct.getFec());
					f.setFecModif(ct.getFec());
					f.setIdAnuncio(ac.getAnuncio().getIdAnuncio());
					f.setIdFotografia(0);
					f.setIdUsuarioAlta(ct.getUsu());
					f.setIdUsuarioModif(ct.getUsu());
					f.setImagen(Imagenes.resizeImage(mf.getBytes(), mf.getContentType(),
							SpinnakerSimpleGlobals.IMG_MAX_WIDTH));
					f.setTipo(mf.getContentType());
					ac.getFotos().add(f);
				}
			}
		}

		if (ui.getTipoAlta().equals(SpinnakerSimpleGlobals.VENDER_TIPO_ALTA)) {

			ac.getUsuario().setIdUsuario(0);
			ac.getUsuario().setNombre(Cadenas.trimNoNulo(ui.getNombre()));
			ac.getUsuario().setApellidos(Cadenas.trimNoNulo(ui.getApellidos()));
			ac.getUsuario().setMail(Cadenas.trimNoNulo(ui.getEmail()).toLowerCase());
			ac.getUsuario().setMovil(Cadenas.trimNoNulo(ui.getMovil()));
			ac.getUsuario().setDireccion1(Cadenas.trimNoNulo(ui.getDireccion1()));
			ac.getUsuario().setDireccion2(Cadenas.trimNoNulo(ui.getDireccion2()));
			ac.getUsuario().setPais(ui.getPais());
			ac.getUsuario().setDivisa(ui.getDivisa());
			ac.getUsuario().setProvincia(Cadenas.trimNoNulo(ui.getProvincia()));
			ac.getUsuario().setCodPostal(Cadenas.trimNoNulo(ui.getCodPostal()));
			ac.getUsuario().setAdmin(SpinnakerSimpleGlobals.USUARIO_NO_ADMIN);
			ac.getUsuario().setPass(GeneradorClaves.generaPassword(8));
			ac.getUsuario().setLang(LocaleContextHolder.getLocale().getLanguage().toUpperCase());
			ac.getUsuario().setFallosLogin(0);

		} else if (ui.getTipoAlta().equals(SpinnakerSimpleGlobals.VENDER_TIPO_LOGIN)) {

			ac.getUsuario().setMail(ui.getEmail());
			ac.getUsuario().setPass(ui.getPassLogin());

		} else if (ui.getTipoAlta().equals(SpinnakerSimpleGlobals.VENDER_TIPO_ALTA_LOGGED)) {

			if (u != null) {
				ac.getUsuario().setMail(u.getMail());
			}

		}
		// else if
		// (ui.getTipoAlta().equals(SpinnakerSimpleGlobals.VENDER_TIPO_REGISTER_USER))
		// {
		// } else if
		// (ui.getTipoAlta().equals(SpinnakerSimpleGlobals.VENDER_TIPO_EDITAR_DATOS))
		// {
		// } else if
		// (ui.getTipoAlta().equals(SpinnakerSimpleGlobals.VENDER_TIPO_EDITAR_FOTOS))
		// {
		// }

		return ac;
	}

	/**
	 * To vender vela DTO.
	 *
	 * @param ui the ui
	 * @return the vender vela DTO
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static VenderVelaDTO toVenderVelaDTO(final VenderVelaUI ui) throws IOException {
		final VenderVelaDTO dto = new VenderVelaDTO();
		dto.setIdAnuncio(ui.getIdAnuncio());
		dto.setTituloAnuncio(ui.getTituloAnuncio());
		dto.setTipoVela(ui.getTipoVela());
		dto.setGramaje(ui.getGramaje());
		dto.setGratil(ui.getGratil());
		dto.setBaluma(ui.getBaluma());
		dto.setPujamen(ui.getPujamen());
		dto.setDescripcion(ui.getDescripcion());
		dto.setPrecio(ui.getPrecio());
		dto.setPaisVela(ui.getPaisVela());
		dto.setNombre(ui.getNombre());
		dto.setApellidos(ui.getApellidos());
		dto.setDireccion1(ui.getDireccion1());
		dto.setDireccion2(ui.getDireccion2());
		dto.setCodPostal(ui.getCodPostal());
		dto.setProvincia(ui.getProvincia());
		dto.setPais(ui.getPais());
		dto.setDivisa(ui.getDivisa());
		dto.setEmail(ui.getEmail());
		dto.setMovil(ui.getMovil());
		if (ui.getFiles() != null) {
			final List<ImagenDTO> lista = new ArrayList<>();
			for (final MultipartFile mf : ui.getFiles()) {
				final ImagenDTO imagen = new ImagenDTO();
				imagen.setBytes(mf.getBytes());
				imagen.setContentType(mf.getContentType());
			}
			dto.setFiles(lista);
		}
		dto.setPass(ui.getPass());
		dto.setLang(ui.getLang());
		dto.setEmailLogin(ui.getEmailLogin());
		dto.setPassLogin(ui.getPassLogin());
		dto.setTipoAlta(ui.getTipoAlta());
		return dto;
	}

	/**
	 * To comprar vela DTO.
	 *
	 * @param ui the ui
	 * @return the comprar vela DTO
	 */
	public static FiltroBusquedaVelaBean toFiltroBusquedaVelaBean(final ComprarVelaUI ui) {
		final FiltroBusquedaVelaBean bean = new FiltroBusquedaVelaBean();

		bean.setTipoBarco(FormUtils.parseIntegerFormField(ui.getTipoBarco()));
		bean.setTipoClase(FormUtils.parseIntegerFormField(ui.getTipoClase()));
		bean.setTipoVela(FormUtils.parseIntegerFormField(
				ui.getTipoVela().contentEquals(DefaultGlobalNames.NADA_SELECCIONADO_EN_LISTA) ? null
						: ui.getTipoVela()));

		bean.setTitulo(StringUtils.trimToEmpty(ui.getTitulo()));

		bean.setGratilmax(FormUtils.parseBigDecimalFormField(ui.getGratilmax(),
				SpinnakerSimpleGlobals.DIMENSION_VELA_MAXIMA, LocaleContextHolder.getLocale()));
		bean.setGratilmin(FormUtils.parseBigDecimalFormField(ui.getGratilmin(),
				SpinnakerSimpleGlobals.DIMENSION_VELA_MINIMA, LocaleContextHolder.getLocale()));

		bean.setBalumamax(FormUtils.parseBigDecimalFormField(ui.getBalumamax(),
				SpinnakerSimpleGlobals.DIMENSION_VELA_MAXIMA, LocaleContextHolder.getLocale()));
		bean.setBalumamin(FormUtils.parseBigDecimalFormField(ui.getBalumamin(),
				SpinnakerSimpleGlobals.DIMENSION_VELA_MINIMA, LocaleContextHolder.getLocale()));

		bean.setPujamenmax(FormUtils.parseBigDecimalFormField(ui.getPujamenmax(),
				SpinnakerSimpleGlobals.DIMENSION_VELA_MAXIMA, LocaleContextHolder.getLocale()));
		bean.setPujamenmin(FormUtils.parseBigDecimalFormField(ui.getPujamenmin(),
				SpinnakerSimpleGlobals.DIMENSION_VELA_MINIMA, LocaleContextHolder.getLocale()));

		bean.setCaidaProamax(FormUtils.parseBigDecimalFormField(ui.getCaidaProaMax(),
				SpinnakerSimpleGlobals.DIMENSION_VELA_MAXIMA, LocaleContextHolder.getLocale()));
		bean.setCaidaProamin(FormUtils.parseBigDecimalFormField(ui.getCaidaProaMin(),
				SpinnakerSimpleGlobals.DIMENSION_VELA_MINIMA, LocaleContextHolder.getLocale()));

		bean.setCaidaPopamax(FormUtils.parseBigDecimalFormField(ui.getCaidaPopaMax(),
				SpinnakerSimpleGlobals.DIMENSION_VELA_MAXIMA, LocaleContextHolder.getLocale()));
		bean.setCaidaPopamin(FormUtils.parseBigDecimalFormField(ui.getCaidaPopaMin(),
				SpinnakerSimpleGlobals.DIMENSION_VELA_MINIMA, LocaleContextHolder.getLocale()));

		bean.setSuperficiemax(FormUtils.parseBigDecimalFormField(ui.getSuperficieMax(),
				SpinnakerSimpleGlobals.DIMENSION_VELA_MAXIMA, LocaleContextHolder.getLocale()));
		bean.setSuperficiemin(FormUtils.parseBigDecimalFormField(ui.getSuperficieMin(),
				SpinnakerSimpleGlobals.DIMENSION_VELA_MINIMA, LocaleContextHolder.getLocale()));

		if (StringUtils.isNotEmpty(ui.getTipoCometa())
				&& !ui.getTipoCometa().equals(DefaultGlobalNames.NADA_SELECCIONADO_EN_LISTA)) {
			bean.setTipoCometa(ui.getTipoCometa());
		}

		bean.setNumRegistros(SpinnakerSimpleGlobals.NUMERO_ANUNCIOS_POR_CARGA);
		bean.setRegInicial(FormUtils.parseIntegerFormField(ui.getRegInicial()));

		bean.setPujamenmin(FormUtils.parseBigDecimalFormField(ui.getPujamenmin(),
				SpinnakerSimpleGlobals.DIMENSION_VELA_MINIMA, LocaleContextHolder.getLocale()));

		if (!Validadores.estaVacia(ui.getOrden())) {
			bean.setOrdenarPor(ui.getOrden());
		} else {
			bean.setOrdenarPor(OrdenacionAnuncios.FECHA_ALTA);
			if (Validadores.estaVacia(ui.getTipoOrden())) {
				bean.setTipoOrdenacion(TipoOrdenacion.DESCENDENTE);
			}
		}
		if (!Validadores.estaVacia(ui.getTipoOrden())) {
			bean.setTipoOrdenacion(ui.getTipoOrden());
		} else {
			if (bean.getTipoOrdenacion() == null) {
				bean.setTipoOrdenacion(TipoOrdenacion.ASCENDENTE);
			}
		}

		bean.setPais(bean.getPais());
		return bean;
	}

	/**
	 * To anuncio POJO.
	 *
	 * @param ui           the ui
	 * @param ct           the ct
	 * @param tipoDeCambio the tipo de cambio
	 * @return the anuncio POJO
	 */
	public static AnuncioPOJO toAnuncioPOJO(final AnuncioUI ui, final CambioEnTabla ct, final double tipoDeCambio) {
		final AnuncioPOJO pojo = new AnuncioPOJO();
		pojo.setIdAnuncio(0);
		pojo.setTituloAnuncio(Cadenas.trimNoNulo(ui.getTituloAnuncio()));
		pojo.setTipoBarco(Integer.parseInt(ui.getTipoBarco()));
		pojo.setTipoVela(Integer.parseInt(ui.getTipoVela()));
		if (!Validadores.estaVacia(ui.getGramaje())) {
			pojo.setGramaje(FormUtils.parseBigDecimalFormField(Cadenas.trimNoNulo(ui.getGramaje()),
					LocaleContextHolder.getLocale()));
		}
		if (!Validadores.estaVacia(ui.getGratil())) {
			pojo.setGratil(FormUtils.parseBigDecimalFormField(Cadenas.trimNoNulo(ui.getGratil()),
					LocaleContextHolder.getLocale()));
		}
		if (!Validadores.estaVacia(ui.getBaluma())) {
			pojo.setBaluma(FormUtils.parseBigDecimalFormField(Cadenas.trimNoNulo(ui.getBaluma()),
					LocaleContextHolder.getLocale()));
		}
		if (!Validadores.estaVacia(ui.getPujamen())) {
			pojo.setPujamen(FormUtils.parseBigDecimalFormField(Cadenas.trimNoNulo(ui.getPujamen()),
					LocaleContextHolder.getLocale()));
		}
		if (!Validadores.estaVacia(ui.getCaidaProa())) {
			pojo.setCaidaProa(FormUtils.parseBigDecimalFormField(Cadenas.trimNoNulo(ui.getCaidaProa()),
					LocaleContextHolder.getLocale()));
		}
		if (!Validadores.estaVacia(ui.getCaidaPopa())) {
			pojo.setCaidaPopa(FormUtils.parseBigDecimalFormField(Cadenas.trimNoNulo(ui.getCaidaPopa()),
					LocaleContextHolder.getLocale()));
		}
		if (!Validadores.estaVacia(ui.getSuperficie())) {
			pojo.setSuperficie(FormUtils.parseBigDecimalFormField(Cadenas.trimNoNulo(ui.getSuperficie()),
					LocaleContextHolder.getLocale()));
		}
		pojo.setTipoCometa(Cadenas.trimNoNulo(ui.getTipoCometa()));
		pojo.setDescripcion(Cadenas.trimNoNulo(ui.getDescripcion()));
		pojo.setPrecio(FormUtils.parseBigDecimalFormField(ui.getPrecio(), LocaleContextHolder.getLocale()));
		if (ui.getDivisa().equals(DefaultGlobalNames.COD_DIVISA_EURO)) {
			pojo.setPrecio(FormUtils.parseBigDecimalFormField(ui.getPrecio(), LocaleContextHolder.getLocale()));
		} else {
			pojo.setPrecio(BigDecimal.valueOf(
					FormUtils.parseBigDecimalFormField(ui.getPrecio(), LocaleContextHolder.getLocale()).doubleValue()
							/ tipoDeCambio));
		}
		pojo.setPais(ui.getPais());
		pojo.setVisible(SpinnakerSimpleGlobals.ANUNCIO_NO_VISIBLE);
		pojo.setFecAlta(ct.getFec());
		pojo.setFecModif(ct.getFec());
		pojo.setIdUsuarioAlta(ct.getUsu());
		pojo.setIdUsuarioModif(ct.getUsu());
		final Calendar caduca = Calendar.getInstance();
		caduca.add(Calendar.DAY_OF_YEAR, 90);
		pojo.setCaduca(caduca);
		return pojo;
	}

	/**
	 * To anuncio POJO.
	 *
	 * @param ui           the ui
	 * @param ct           the ct
	 * @param tipoDeCambio the tipo de cambio
	 * @return the anuncio POJO
	 */
	public static AnuncioPOJO toAnuncioPOJO(final VenderVelaUI ui, final CambioEnTabla ct, final double tipoDeCambio) {
		final AnuncioPOJO pojo = new AnuncioPOJO();

		pojo.setIdAnuncio(FormUtils.parseIntegerFormField(ui.getIdAnuncio(), 0));
		pojo.setTituloAnuncio(Cadenas.trimNoNulo(ui.getTituloAnuncio()));
		pojo.setTipoBarco(Integer.parseInt(ui.getTipoBarco()));
		pojo.setTipoVela(Integer.parseInt(ui.getTipoVela()));
		if (!Validadores.estaVacia(ui.getGramaje())) {
			pojo.setGramaje(FormUtils.parseBigDecimalFormField(Cadenas.trimNoNulo(ui.getGramaje()),
					LocaleContextHolder.getLocale()));
		}
		if (!Validadores.estaVacia(ui.getGratil())) {
			pojo.setGratil(FormUtils.parseBigDecimalFormField(Cadenas.trimNoNulo(ui.getGratil()),
					LocaleContextHolder.getLocale()));
		}
		if (!Validadores.estaVacia(ui.getBaluma())) {
			pojo.setBaluma(FormUtils.parseBigDecimalFormField(Cadenas.trimNoNulo(ui.getBaluma()),
					LocaleContextHolder.getLocale()));
		}
		if (!Validadores.estaVacia(ui.getPujamen())) {
			pojo.setPujamen(FormUtils.parseBigDecimalFormField(Cadenas.trimNoNulo(ui.getPujamen()),
					LocaleContextHolder.getLocale()));
		}
		if (!Validadores.estaVacia(ui.getCaidaProa())) {
			pojo.setCaidaProa(FormUtils.parseBigDecimalFormField(Cadenas.trimNoNulo(ui.getCaidaProa()),
					LocaleContextHolder.getLocale()));
		}
		if (!Validadores.estaVacia(ui.getCaidaPopa())) {
			pojo.setCaidaPopa(FormUtils.parseBigDecimalFormField(Cadenas.trimNoNulo(ui.getCaidaPopa()),
					LocaleContextHolder.getLocale()));
		}
		if (!Validadores.estaVacia(ui.getSuperficie())) {
			pojo.setSuperficie(FormUtils.parseBigDecimalFormField(Cadenas.trimNoNulo(ui.getSuperficie()),
					LocaleContextHolder.getLocale()));
		}
		pojo.setTipoCometa(Cadenas.trimNoNulo(ui.getTipoCometa()));
		pojo.setDescripcion(Cadenas.trimNoNulo(ui.getDescripcion()));
		pojo.setPrecio(FormUtils.parseBigDecimalFormField(ui.getPrecio(), LocaleContextHolder.getLocale()));
		if (ui.getDivisa().equals(DefaultGlobalNames.COD_DIVISA_EURO)) {
			pojo.setPrecio(FormUtils.parseBigDecimalFormField(ui.getPrecio(), LocaleContextHolder.getLocale()));
		} else {
			pojo.setPrecio(BigDecimal.valueOf(
					FormUtils.parseBigDecimalFormField(ui.getPrecio(), LocaleContextHolder.getLocale()).doubleValue()
							/ tipoDeCambio));
		}
		pojo.setPais(ui.getPaisVela());
		pojo.setVisible(SpinnakerSimpleGlobals.ANUNCIO_NO_VISIBLE);
		pojo.setFecAlta(ct.getFec());
		pojo.setFecModif(ct.getFec());
		pojo.setIdUsuarioAlta(ct.getUsu());
		pojo.setIdUsuarioModif(ct.getUsu());
		if (StringUtils.isEmpty(ui.getCaduca())) {
			final Calendar caduca = Calendar.getInstance();
			caduca.add(Calendar.DAY_OF_YEAR, 90);
			pojo.setCaduca(caduca);
		} else {
			pojo.setCaduca(FormUtils.parseCalendarFormField(ui.getCaduca(), LocaleContextHolder.getLocale()));
		}
		return pojo;
	}

	/**
	 * To fotografias POJO list.
	 *
	 * @param ui the ui
	 * @return the list
	 */
	public static List<FotografiaPOJO> toFotografiasPOJOList(final VenderVelaUI ui) {
		try {
			final List<FotografiaPOJO> fotos = new ArrayList<FotografiaPOJO>(ui.getFiles().size());
			for (final MultipartFile file : ui.getFiles()) {
				if (file.getBytes().length > 0) {
					try {
						final FotografiaPOJO f = new FotografiaPOJO();
						f.setIdFotografia(0);
						f.setIdAnuncio(FormUtils.parseIntegerFormField(ui.getIdAnuncio()));
						f.setImagen(Imagenes.resizeImage(file.getBytes(), file.getContentType(),
								SpinnakerSimpleGlobals.IMG_MAX_WIDTH));
						f.setTipo(file.getContentType());
						fotos.add(f);
					} catch (final Exception ex) {
					} // La ignoramos.
				}
			}
			return fotos;
		} catch (final IOException e) {
			log.error(e.getMessage(), e);
			throw new ServicioErrorException(e);
		}
	}
}
