package com.spinnakersimple.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.capitanperegrina.common.basebeans.CambioEnTabla;
import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.config.DefaultParameters;
import com.capitanperegrina.common.exceptions.ServicioErrorException;
import com.capitanperegrina.common.seguridad.Criptografia;
import com.capitanperegrina.common.utils.StackTraceUtil;
import com.capitanperegrina.common.validators.util.ValidationnAdapters;
import com.spinnakersimple.modelo.entidad.AnuncioPOJO;
import com.spinnakersimple.modelo.entidad.TipoVelaPOJO;
import com.spinnakersimple.modelo.entidad.UsuarioPOJO;
import com.spinnakersimple.modelo.util.SpinnakerSimpleGlobals;
import com.spinnakersimple.servicios.AnunciosService;
import com.spinnakersimple.util.tablasayuda.TiposVela;
import com.spinnakersimple.web.ui.bean.AnuncioCompletoUI;
import com.spinnakersimple.web.ui.bean.ComprarVelaUI;
import com.spinnakersimple.web.ui.bean.VenderVelaUI;
import com.spinnakersimple.web.ui.util.AdaptFromUI;
import com.spinnakersimple.web.ui.util.AdaptToUI;
import com.spinnakersimple.web.validator.ComprarVelaValidator;
import com.spinnakersimple.web.validator.VenderVelaValidator;

/**
 * The Class AjaxSpinnakerSimpleController.
 */
@Controller
public class AjaxSpinnakerSimpleController {

	/** The log. */
	static Logger log = Logger.getLogger(AjaxSpinnakerSimpleController.class);

	/** The message source. */
	@Autowired
	MessageSource messageSource;

	/** The tipos vela. */
	@Autowired
	TiposVela tiposVela;

	/** The criptografia. */
	@Autowired
	Criptografia criptografia;

	/** The params. */
	@Autowired
	DefaultParameters params;

	/** The anuncios service. */
	@Autowired
	AnunciosService anunciosService;

	/** The validationn adapters. */
	@Autowired
	ValidationnAdapters validationnAdapters;

	/** The vender vela form validator. */
	@Autowired
	VenderVelaValidator venderVelaFormValidator;

	@Autowired
	ComprarVelaValidator comprarVelaFormValidator;

	/**
	 * Inits the binder vender vela form.
	 *
	 * @param binder the binder
	 */
	@InitBinder(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_VENDER)
	protected void initBinderVenderVelaForm(final WebDataBinder binder) {
		binder.setValidator(this.venderVelaFormValidator);
	}

	/**
	 * Inits the binder vender vela form.
	 *
	 * @param binder the binder
	 */
	@InitBinder(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_COMPRAR)
	protected void initBinderComprarVelaForm(final WebDataBinder binder) {
		binder.setValidator(this.comprarVelaFormValidator);
	}

	/**
	 * Comprar vela buscar post.
	 *
	 * @param filtro             the filtro
	 * @param result             the result
	 * @param model              the model
	 * @param redirectAttributes the redirect attributes
	 * @param request            the request
	 * @return the model and view
	 */
	@RequestMapping(value = "/comprarVelaBuscarAjax.action", method = RequestMethod.POST)
	public ModelAndView comprarVelaBuscarPost(
			@ModelAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_COMPRAR) @Validated final ComprarVelaUI filtro,
			final BindingResult result, final Model model, final RedirectAttributes redirectAttributes,
			final HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}
		try {
			if (result.hasErrors()) {
				request.removeAttribute("resultadosBusqueda");
			} else {
				final UsuarioPOJO u = (UsuarioPOJO) request.getSession().getAttribute(DefaultGlobalNames.USERSESSION);
				final Integer idUsuario = u != null ? u.getIdUsuario() : null;
				final List<AnuncioCompletoUI> resultadosBusqueda = AdaptToUI.toAnuncioCompletoUIList(
						this.anunciosService.buscarAnuncios(AdaptFromUI.toFiltroBusquedaVelaBean(filtro), idUsuario));
				request.setAttribute("resultadosBusqueda", resultadosBusqueda);
			}
			return new ModelAndView("comprarVelaAjax");
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	/**
	 * Edicion fotografias get.
	 *
	 * @param model   the model
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = "/edicionFotografias.action", method = RequestMethod.GET)
	public ModelAndView edicionFotografiasGet(final Model model, final HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}
		try {
			final Integer idAnuncio = Integer
					.parseInt(this.criptografia.descodificaParaUrl(request.getParameter("ida")));
			final Integer idUsuario = ((UsuarioPOJO) request.getSession().getAttribute(DefaultGlobalNames.USERSESSION))
					.getIdUsuario();
			final AnuncioCompletoUI ac = AdaptToUI
					.toAnuncioCompletoUI(this.anunciosService.buscarAnuncio(idAnuncio, idUsuario, false));
			model.addAttribute("anuncio", ac);

			final VenderVelaUI venderVelaUi = new VenderVelaUI();
			venderVelaUi.setIdAnuncio(idAnuncio.toString());
			venderVelaUi.setIdAnuncioCodificado(this.criptografia.codificaParaUrl(idAnuncio.toString()));
			venderVelaUi.setTipoAlta(SpinnakerSimpleGlobals.VENDER_TIPO_EDITAR_FOTOS);

			model.addAttribute("editaFotosVelaForm", venderVelaUi);

			return new ModelAndView("edicionFotografias");
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	/**
	 * Mi anuncio del foto get.
	 *
	 * @param request  the request
	 * @param response the response
	 * @return the model and view
	 */
	@RequestMapping(value = "/miAnuncioDelFoto.action", method = RequestMethod.GET)
	public void miAnuncioDelFotoGet(final HttpServletRequest request, final HttpServletResponse response) {
		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}
		try {
			final Integer idAnuncio = Integer
					.parseInt(this.criptografia.descodificaParaUrl(request.getParameter("ida")));
			final Integer idFotografia = Integer
					.parseInt(this.criptografia.descodificaParaUrl(request.getParameter("idb")));
			final Integer idUsuario = ((UsuarioPOJO) request.getSession().getAttribute(DefaultGlobalNames.USERSESSION))
					.getIdUsuario();
			final CambioEnTabla ct = new CambioEnTabla();
			ct.setUsu(idUsuario);
			ct.setLocale(LocaleContextHolder.getLocale());
			ct.setIp(request.getRemoteAddr());
			ct.setFec(Calendar.getInstance());
			this.anunciosService.eliminarFotografia(idFotografia, ct);
			final AnuncioCompletoUI ac = AdaptToUI
					.toAnuncioCompletoUI(this.anunciosService.buscarAnuncio(idAnuncio, idUsuario, false));

			final JSONObject respuestaJson = new JSONObject();
			respuestaJson.put("resultado", "OK");
			respuestaJson.put("id", "#foto_" + request.getParameter("idb"));
			respuestaJson.put("foto", ac.getFoto1());

			final PrintWriter out = response.getWriter();
			log.debug(respuestaJson.toString());
			out.write(respuestaJson.toString());
		} catch (IOException | JSONException e) {
			log.error("", e);
			throw new ServicioErrorException(e);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	/**
	 * Ajax del controlador para las modificaciones ajax de los anuncios.
	 *
	 * @param ui                 the ui
	 * @param result             the result
	 * @param model              the model
	 * @param redirectAttributes the redirect attributes
	 * @param request            the request
	 * @param response           the response
	 */
	@RequestMapping(value = "/miAnuncioModAjax.action", method = RequestMethod.POST)
	public void miAnuncioModAjaxPost(
			@ModelAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_VENDER) @Validated final VenderVelaUI ui,
			final BindingResult result, final Model model, final RedirectAttributes redirectAttributes,
			final HttpServletRequest request, final HttpServletResponse response) {
		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}
		try {
			final JSONObject json = new JSONObject();
			if (result.hasErrors()) {
				final JSONArray erroresJson = this.validationnAdapters.adapt2Json(result);
				json.put("errores", erroresJson);
			} else {
				final UsuarioPOJO usuario = (UsuarioPOJO) request.getSession()
						.getAttribute(DefaultGlobalNames.USERSESSION);
				final CambioEnTabla ct = new CambioEnTabla();
				ct.setFec(Calendar.getInstance());
				ct.setIp(request.getRemoteAddr());
				ct.setLocale(LocaleContextHolder.getLocale());
				ct.setUsu(usuario.getIdUsuario());

				double tipoCambio = 1d;
				if (!usuario.getDivisa().equals(SpinnakerSimpleGlobals.DIVISA_EUR)) {
					tipoCambio = this.params.getEcbExchangeRates().get(usuario.getDivisa()).doubleValue();
				}
				final AnuncioPOJO a = AdaptFromUI.toAnuncioPOJO(ui, ct, tipoCambio);
				this.anunciosService.actualizaAnuncio(a, ct);

				log.debug(ui.toString());
				final JSONObject jsonOk = new JSONObject();
				jsonOk.put("mensaje",
						StringEscapeUtils.escapeHtml(
								this.messageSource.getMessage("AjaxSpinnakerSimpleController.miAnuncioModAjaxPost.ok",
										null, LocaleContextHolder.getLocale())));
				json.put("resultado", jsonOk);
			}
			log.debug(json.toString());
			final PrintWriter out = response.getWriter();
			out.write(json.toString());
		} catch (IOException | JSONException e) {
			log.error("", e);
			throw new ServicioErrorException(e);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	/**
	 * Tipo vela info ajax get.
	 *
	 * @param model    the model
	 * @param request  the request
	 * @param response the response
	 */
	@RequestMapping(value = "/tipoVelaInfoAjax.action", method = RequestMethod.GET, produces = "application/json")
	public void tipoVelaInfoAjaxGet(final Model model, final HttpServletRequest request,
			final HttpServletResponse response) {
		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}
		final Integer idVela = Integer.parseInt(request.getParameter("idTipoVela"));

		try {
			final TipoVelaPOJO tv = this.tiposVela.getPojo(LocaleContextHolder.getLocale().getLanguage(), idVela);
			final JSONObject tipoVelaJson = new JSONObject();
			tipoVelaJson.put("id", tv.getIdTipoVela().toString());
			tipoVelaJson.put("nom", StringEscapeUtils.escapeHtml(this.messageSource
					.getMessage("tipoVela." + tv.getIdTipoVela().toString(), null, LocaleContextHolder.getLocale())));
			tipoVelaJson.put("img",
					SpinnakerSimpleGlobals.RAIZ_IMAGENES_TIPO_VELA + "/" + tv.getUrlTipoVela() + ".jpg");

			final PrintWriter out = response.getWriter();
			log.debug(tipoVelaJson.toString());
			out.write(tipoVelaJson.toString());
		} catch (IOException | JSONException e) {
			log.error("", e);
			throw new ServicioErrorException(e);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}
}
