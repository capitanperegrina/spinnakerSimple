package com.spinnakersimple.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
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
import com.capitanperegrina.common.exceptions.ServicioErrorException;
import com.capitanperegrina.common.i18n.Mensajes;
import com.capitanperegrina.common.log.Log4jManager;
import com.capitanperegrina.common.modelo.entidad.tabla.ParamsPOJO;
import com.capitanperegrina.common.seguridad.Criptografia;
import com.capitanperegrina.common.servicios.ParamsService;
import com.capitanperegrina.common.ui.adapter.ParamsAdapter;
import com.capitanperegrina.common.utils.StackTraceUtil;
import com.capitanperegrina.common.web.FormUtils;
import com.capitanperegrina.i18n.modelo.util.Divisas;
import com.capitanperegrina.i18n.modelo.util.Paises;
import com.spinnakersimple.modelo.entidad.FotografiaPOJO;
import com.spinnakersimple.modelo.entidad.UsuarioPOJO;
import com.spinnakersimple.modelo.util.SpinnakerSimpleGlobals;
import com.spinnakersimple.servicios.AnunciosService;
import com.spinnakersimple.servicios.UsuariosService;
import com.spinnakersimple.util.tablasayuda.Parametrizacion;
import com.spinnakersimple.util.tablasayuda.TiposVela;
import com.spinnakersimple.web.ui.bean.AnuncioUI;
import com.spinnakersimple.web.ui.bean.CompradoresUI;
import com.spinnakersimple.web.ui.bean.UsuarioUI;
import com.spinnakersimple.web.ui.bean.VenderVelaUI;
import com.spinnakersimple.web.ui.util.AdaptFromUI;
import com.spinnakersimple.web.ui.util.AdaptToUI;
import com.spinnakersimple.web.validator.LoginValidator;
import com.spinnakersimple.web.validator.UsuarioValidator;
import com.spinnakersimple.web.validator.VenderVelaValidator;

/**
 * The Class AdministracionController.
 */
@Controller
public class AdministracionController {

	/** The log. */
	private static Logger log = Logger.getLogger(AdministracionController.class);

	/** The message source. */
	@Autowired
	MessageSource messageSource;

	/** The anuncios service. */
	@Autowired
	AnunciosService anunciosService;

	/** The criptografia. */
	@Autowired
	Criptografia criptografia;

	/** The divisas. */
	@Autowired
	Divisas divisas;

	/** The paises. */
	@Autowired
	Paises paises;

	@Autowired
	TiposVela tiposVela;
	@Autowired
	Parametrizacion parametrizacion;

	/** The mis anuncios controller. */
	@Autowired
	MisAnunciosController misAnunciosController;

	@Autowired
	AjaxSpinnakerSimpleController ajaxSpinnakerSimpleController;

	/** The usuarios service. */
	@Autowired
	UsuariosService usuariosService;

	@Autowired
	ParamsService paramsService;

	/** The login form DTO validator. */
	@Autowired
	LoginValidator loginValidator;

	@Autowired
	UsuarioValidator usuarioValidator;

	/** The vender vela form validator. */
	@Autowired
	VenderVelaValidator venderVelaValidator;

	/**
	 * Inits the binder login form.
	 *
	 * @param binder the binder
	 */
	@InitBinder(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_LOGIN)
	protected void initBinderLoginValidator(final WebDataBinder binder) {
		binder.setValidator(this.loginValidator);
	}

	/**
	 * Inits the binder vender vela form.
	 *
	 * @param binder the binder
	 */
	@InitBinder(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_VENDER)
	protected void initBinderVenderVelaValidator(final WebDataBinder binder) {
		binder.setValidator(this.venderVelaValidator);
	}

	/**
	 * Inits the binder usuario validator.
	 *
	 * @param binder the binder
	 */
	@InitBinder(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_REGISTRO)
	protected void initBinderUsuarioValidator(final WebDataBinder binder) {
		binder.setValidator(this.usuarioValidator);
	}

	/**
	 * Admin get.
	 *
	 * @param request the request
	 * @return the string
	 */
	@RequestMapping(value = "/admin.action", method = RequestMethod.GET)
	public String adminGet(final HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}
		try {
			final UsuarioPOJO u = (UsuarioPOJO) request.getSession().getAttribute(DefaultGlobalNames.USERSESSION);
			if (u != null && u.getAdmin().equals(SpinnakerSimpleGlobals.USUARIO_ADMIN)) {
				for (final Entry<String, Integer> dato : this.anunciosService.obtenEstadisticasEscritorio().entrySet()) {
					request.setAttribute(dato.getKey(), dato.getValue());
				}
				return "adminDashboard";
			} else {
				return "redirect:/inicio.action";
			}
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	/**
	 * Admin anuncios todos get.
	 *
	 * @param request the request
	 * @return the string
	 */
	@RequestMapping(value = "/adminAnuncios.action", method = RequestMethod.GET)
	public String adminAnunciosTodosGet(final HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}
		try {
			final AnuncioUI form = new AnuncioUI();
			form.setVisible(SpinnakerSimpleGlobals.ESTADO_PENDIENTE_REVISION);
			if (!StringUtils.isEmpty(request.getParameter("v"))) {
				form.setVisible(request.getParameter("v"));
			}

			final Map<String, String> opcionesVisibilidad = new HashMap<>();
			opcionesVisibilidad.put("-1", this.messageSource.getMessage("global.todos", null, LocaleContextHolder.getLocale()));
			opcionesVisibilidad.put("0", this.messageSource.getMessage("anuncio.estado.0", null, LocaleContextHolder.getLocale()));
			opcionesVisibilidad.put("1", this.messageSource.getMessage("anuncio.estado.1", null, LocaleContextHolder.getLocale()));
			opcionesVisibilidad.put("2", this.messageSource.getMessage("anuncio.estado.2", null, LocaleContextHolder.getLocale()));
			opcionesVisibilidad.put("30", this.messageSource.getMessage("anuncio.estado.999", null, LocaleContextHolder.getLocale()));

			request.setAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_ANUNCIO, form);
			request.setAttribute("opcionesVisibilidad", opcionesVisibilidad);

			return "adminAnuncios";
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	/**
	 * Admin anuncios A caducar get.
	 *
	 * @param request the request
	 * @return the string
	 */
	@RequestMapping(value = "/adminAnunciosAjax.action", method = RequestMethod.GET)
	public String adminAnunciosAjaxGet(final HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}
		try {
			Integer idUsuario = null;
			Integer visible = null;
			Integer caducaEn = null;
			if (!StringUtils.isEmpty(request.getParameter("idu"))) {
				idUsuario = Integer.parseInt(this.criptografia.descodificaParaUrl(request.getParameter("idu")));
			}
			if (!StringUtils.isEmpty(request.getParameter("v")) && !request.getParameter("v").equals("-1")) {
				visible = Integer.parseInt(request.getParameter("v"));
				if (visible == 30) {
					caducaEn = visible;
					visible = null;
				}
			}

			request.setAttribute("anuncios", AdaptToUI.toAnuncioUIList(this.anunciosService.buscaAnuncios(idUsuario, visible, caducaEn)));

			return "adminAnunciosAjax";
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	@RequestMapping(value = "/adminMensajes.action", method = RequestMethod.GET)
	public String adminMensajesGet(final HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}
		try {
			final CompradoresUI form = new CompradoresUI();
			form.setRevisado(SpinnakerSimpleGlobals.ESTADO_PENDIENTE_REVISION);

			final Map<String, String> opcionesVisibilidad = new HashMap<>();
			opcionesVisibilidad.put("", this.messageSource.getMessage("global.todos", null, LocaleContextHolder.getLocale()));
			// opcionesVisibilidad.put("0", this.messageSource.getMessage("mensajeComprador.estado.0", null, LocaleContextHolder.getLocale()));
			// opcionesVisibilidad.put("1", this.messageSource.getMessage("mensajeComprador.estado.1", null, LocaleContextHolder.getLocale()));
			// opcionesVisibilidad.put("2", this.messageSource.getMessage("mensajeComprador.estado.2", null, LocaleContextHolder.getLocale()));

			request.setAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_CONSULTAR_VELA, form);
			request.setAttribute("opcionesVisibilidad", opcionesVisibilidad);

			return "adminMensajes";
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	@RequestMapping(value = "/adminMensajesAjax.action", method = RequestMethod.GET)
	public String adminMensajesAjaxGet(final HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}
		try {
			Integer revisado = null;
			if (!StringUtils.isEmpty(request.getParameter("r"))) {
				revisado = Integer.parseInt(request.getParameter("r"));
			}
			request.setAttribute("mensajes", AdaptToUI.toCompradoresUIList(this.anunciosService.buscaMensajesDeAnunciosNoFinalizados(revisado)));
			return "adminMensajesAjax";
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	@RequestMapping(value = "/adminRenuevaAnuncio.action", method = RequestMethod.GET)
	public void adminRenuevaAnuncioGet(final HttpServletRequest request, final HttpServletResponse response) {
		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}
		try {
			final Integer idAnuncio = Integer.parseInt(this.criptografia.descodificaParaUrl(request.getParameter("s")));

			final CambioEnTabla ct = new CambioEnTabla();
			ct.setUsu(((UsuarioPOJO) request.getSession().getAttribute(DefaultGlobalNames.USERSESSION)).getIdUsuario());
			ct.setLocale(LocaleContextHolder.getLocale());
			ct.setIp(request.getRemoteAddr());
			ct.setFec(Calendar.getInstance());

			final JSONObject respuestaJson = new JSONObject();
			final Calendar nuevaCaducidad = this.anunciosService.renuevaAnuncio(idAnuncio, ct);
			if (nuevaCaducidad != null) {
				respuestaJson.put("resultado", "OK");
				String nc = this.messageSource.getMessage("renuevaAnuncio.nuevaCaducidad", null, LocaleContextHolder.getLocale());
				nc = nc.replaceAll("%FECHA%", FormUtils.toStringForm(nuevaCaducidad));
				respuestaJson.put("nuevaCaducidad", StringEscapeUtils.escapeHtml4(nc));
			} else {
				respuestaJson.put("resultado", Mensajes.getStringSafe("modificaEstadoAnuncio.error.generico"));
			}

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
	 * Admin cambia estado anuncio get.
	 *
	 * @param request  the request
	 * @param response the response
	 */
	@RequestMapping(value = "/adminCambiaEstadoAnuncio.action", method = RequestMethod.GET)
	public void adminCambiaEstadoAnuncioGet(final HttpServletRequest request, final HttpServletResponse response) {
		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}
		try {

			final Integer idAnuncio = Integer.parseInt(this.criptografia.descodificaParaUrl(request.getParameter("s")));
			final Integer nuevoEstado = Integer.parseInt(request.getParameter("w"));

			final CambioEnTabla ct = new CambioEnTabla();
			ct.setUsu(((UsuarioPOJO) request.getSession().getAttribute(DefaultGlobalNames.USERSESSION)).getIdUsuario());
			ct.setLocale(LocaleContextHolder.getLocale());
			ct.setIp(request.getRemoteAddr());
			ct.setFec(Calendar.getInstance());

			final JSONObject respuestaJson = new JSONObject();
			if (this.anunciosService.modificaEstadoAnuncio(idAnuncio, nuevoEstado, ct)) {
				respuestaJson.put("resultado", "OK");
			} else {
				respuestaJson.put("resultado", Mensajes.getStringSafe("modificaEstadoAnuncio.error.generico"));
			}

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
	 * Admin el perfil get.
	 *
	 * @param model   the model
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = "/adminElPerfil.action", method = RequestMethod.GET)
	public ModelAndView adminElPerfilGet(final Model model, final HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}
		try {
			Integer idUsuario;
			if (!StringUtils.isEmpty(request.getParameter("id"))) {
				idUsuario = Integer.parseInt(this.criptografia.descodificaParaUrl(request.getParameter("id")));
			} else {
				if (((UsuarioPOJO) request.getSession().getAttribute(DefaultGlobalNames.USERSESSION)).getIdUsuario() != null) {
					idUsuario = ((UsuarioPOJO) request.getSession().getAttribute(DefaultGlobalNames.USERSESSION)).getIdUsuario();
				} else {
					throw new ServicioErrorException("usuario.error.edicion");
				}
			}

			final UsuarioUI form = AdaptToUI.toUsuarioUI(this.usuariosService.buscaUsuario(idUsuario));
			cargaEnRequestDatosRegistro(form, model, request);
			return new ModelAndView("adminElPerfil");
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	/**
	 * El perfil post.
	 *
	 * @param form               the form
	 * @param result             the result
	 * @param model              the model
	 * @param redirectAttributes the redirect attributes
	 * @param request            the request
	 * @return the model and view
	 */
	@RequestMapping(value = "/adminElPerfil.action", method = RequestMethod.POST)
	public String adminElPerfilPost(
					@ModelAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_REGISTRO) @Validated final UsuarioUI form,
					final BindingResult result, final Model model, final RedirectAttributes redirectAttributes,
					final HttpServletRequest request) {

		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}

		try {
			if (result.hasErrors()) {
				cargaEnRequestDatosRegistro(form, model, request);
				return "redirect:/adminElPerfil.action";
			} else {
				final CambioEnTabla ct = new CambioEnTabla();
				ct.setUsu(((UsuarioPOJO) request.getSession().getAttribute(DefaultGlobalNames.USERSESSION)).getIdUsuario());
				ct.setFec(Calendar.getInstance());
				ct.setIp(request.getRemoteAddr());
				ct.setLocale(LocaleContextHolder.getLocale());

				final UsuarioPOJO usuario = this.usuariosService.actualizaPerfil(AdaptFromUI.toUsuarioPOJO(form), ct);
				if (usuario.getIdUsuario().equals(ct.getUsu())) {
					// Si me actualizo a mi mismo, me actualizo también en
					// sesión.
					request.getSession().setAttribute(DefaultGlobalNames.USERSESSION, usuario);
				}
				request.setAttribute(DefaultGlobalNames.MENSAJE_MSG, this.messageSource.getMessage("elPerfil.cambio.ok", null, LocaleContextHolder.getLocale()));
				return "redirect:/adminElPerfil.action?id=" + this.criptografia.codificaParaUrl(usuario.getIdUsuario().toString());
			}

		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	/**
	 * Muestra el detalle de una vela.
	 *
	 * @param model   the model
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = "/adminVerVela.action", method = RequestMethod.GET)
	public ModelAndView verVelaGet(final Model model, final HttpServletRequest request) {

		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}

		try {
			// Obtenemos el id del anuncio a consultar.
			final Integer idAnuncio = Integer.parseInt(this.criptografia.descodificaParaUrl(request.getParameter("ida")));

			// Obtenemos el símbolo de la divisa.
			Integer idUsuario = null;
			String simboloDivisa = "&#8364;";
			if (request.getSession().getAttribute(DefaultGlobalNames.USERSESSION) != null) {
				final UsuarioPOJO u = (UsuarioPOJO) request.getSession().getAttribute(DefaultGlobalNames.USERSESSION);
				simboloDivisa = this.divisas.getPojo(u.getDivisa()).getSimboloDivisa();
				idUsuario = u.getIdUsuario();
				request.setAttribute("modoPagina", SpinnakerSimpleGlobals.MENU_MIS_ANUNCIOS);
			}

			// Metemos los datos en la request para mostrar en la vista.
			request.setAttribute("anuncio", AdaptToUI.toAnuncioCompletoUI(this.anunciosService.buscarAnuncio(idAnuncio, idUsuario, false)));
			request.setAttribute("simboloDivisa", simboloDivisa);
			request.setAttribute("modoPagina", null);
			request.setAttribute(SpinnakerSimpleGlobals.MENU_ELEMENTO_SELECCIONADO, SpinnakerSimpleGlobals.MENU_COMPRAR);
			return new ModelAndView("adminVerVela");
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	/**
	 * Admin editar vela get.
	 *
	 * @param model   the model
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = "/adminEditarVela.action", method = RequestMethod.GET)
	public ModelAndView adminEditarVelaGet(final Model model, final HttpServletRequest request) {

		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}

		try {
			final Integer idAnuncio = Integer.parseInt(this.criptografia.descodificaParaUrl(request.getParameter("ida")));
			this.misAnunciosController.edicionAnuncio(model, request, idAnuncio);
			return new ModelAndView("adminEditarVela");

		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	@RequestMapping(value = "/adminMiAnuncioModFoto.action", method = RequestMethod.POST)
	public ModelAndView adminMiAnuncioModFotoPost(
					@ModelAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_MIS_ANUNCIOS) @Validated final VenderVelaUI form,
					final BindingResult result, final Model model, final RedirectAttributes redirectAttributes,
					final HttpServletRequest request) {

		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}
		try {
			form.setIdAnuncio(Integer.toString(Integer.parseInt(this.criptografia.descodificaParaUrl(form.getIdAnuncioCodificado()))));

			if (!result.hasErrors()) {

				final CambioEnTabla ct = new CambioEnTabla();
				ct.setUsu(((UsuarioPOJO) request.getSession().getAttribute(DefaultGlobalNames.USERSESSION)).getIdUsuario());
				ct.setLocale(LocaleContextHolder.getLocale());
				ct.setIp(request.getRemoteAddr());
				ct.setFec(Calendar.getInstance());

				final List<FotografiaPOJO> fotos = AdaptFromUI.toFotografiasPOJOList(form);

				this.anunciosService.guardaFotografias(fotos, ct);
			}

			this.misAnunciosController.edicionAnuncio(model, request, Integer.parseInt(form.getIdAnuncio()));
			return new ModelAndView("adminEditarVela");

		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	@RequestMapping(value = "/adminEdicionFotografias.action", method = RequestMethod.GET)
	public ModelAndView adminEdicionFotografiasGet(final Model model, final HttpServletRequest request) {
		this.ajaxSpinnakerSimpleController.edicionFotografiasGet(model, request);
		return new ModelAndView("adminEdicionFotografias");
	}

	@RequestMapping(value = "/adminUsuarios.action", method = RequestMethod.GET)
	public ModelAndView adminUsuariosGet(final Model model, final HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_FILTRO_INI + StackTraceUtil.getCallerInfo());
		}
		try {
			return new ModelAndView("adminUsuarios");
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_FILTRO_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	@RequestMapping(value = "/adminUsuariosAjax.action", method = RequestMethod.GET)
	public ModelAndView adminUsuariosAjaxGet(final Model model, final HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_FILTRO_INI + StackTraceUtil.getCallerInfo());
		}
		try {
			request.setAttribute("usuarios", AdaptToUI.toUsuarioUIListFromUsuarioCompletoView(this.usuariosService.buscaUsuariosCompleto()));
			return new ModelAndView("adminUsuariosAjax");
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_FILTRO_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	@RequestMapping(value = "/adminVerUsuario.action", method = RequestMethod.GET)
	public ModelAndView adminVerUsuarioGet(final Model model, final HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}
		try {
			final Integer idUsuario = Integer.parseInt(this.criptografia.descodificaParaUrl(request.getParameter("idu")));
			request.setAttribute("usuario", AdaptToUI.toUsuarioUI(this.usuariosService.buscaUsuario(idUsuario)));
			request.setAttribute("anuncios", AdaptToUI.toAnuncioUIList(this.anunciosService.buscarAnuncios(idUsuario, null)));
			return new ModelAndView("adminVerUsuario");
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_FILTRO_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	@RequestMapping(value = "/adminParametrizacion.action", method = RequestMethod.GET)
	public ModelAndView adminParametrizacionGet(final Model model, final HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}
		try {
			request.setAttribute("listaParametros", ParamsAdapter.toParamsUIList(this.paramsService.leeTodos()));
			return new ModelAndView("adminParametrizacion");
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_FILTRO_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	@RequestMapping(value = "/adminParametrizacionSave.action", method = RequestMethod.GET)
	public void adminParametrizacionSaveGet(final HttpServletRequest request, final HttpServletResponse response) {
		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}
		try {
			final String key = StringUtils.trimToEmpty(request.getParameter("c").replace('-', '.').replace("parametrizacionForm_", ""));
			final String value = StringUtils.trimToEmpty(request.getParameter("v"));

			final JSONObject respuestaJson = new JSONObject();
			if (this.parametrizacion.getMapaValidadores().get(key).valid(value)) {
				final CambioEnTabla ct = new CambioEnTabla();
				ct.setUsu(((UsuarioPOJO) request.getSession().getAttribute(DefaultGlobalNames.USERSESSION)).getIdUsuario());
				ct.setLocale(LocaleContextHolder.getLocale());
				ct.setIp(request.getRemoteAddr());
				ct.setFec(Calendar.getInstance());

				final ParamsPOJO param = new ParamsPOJO();
				param.getPk().setIdParam(key);
				param.setValor(value);

				if (this.paramsService.actualizaParametro(param, ct)) {
					respuestaJson.put("resultado", "OK");
					this.parametrizacion.reload();
				} else {
					respuestaJson.put("resultado", StringEscapeUtils.escapeHtml4(Mensajes.getStringSafe("param.error.general")));
				}
			} else {
				respuestaJson.put("resultado", StringEscapeUtils.escapeHtml4(Mensajes.getStringSafe("param.key.validation.error." + key)));
			}

			final PrintWriter out = response.getWriter();
			log.debug(respuestaJson.toString());
			out.write(respuestaJson.toString());
		} catch (IOException | JSONException e) {
			log.error("", e);
			throw new ServicioErrorException(e);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_FILTRO_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	@RequestMapping(value = "/adminLogLevel.action", method = RequestMethod.GET)
	public void adminLogLevelGet(final HttpServletRequest request, final HttpServletResponse response) {
		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}
		try {
			final String p = StringUtils.trimToEmpty(request.getParameter("p"));
			final String l = StringUtils.trimToEmpty(request.getParameter("l"));

			final JSONObject respuestaJson = new JSONObject();
			if (Log4jManager.cambiaNivelLogger(p, l)) {
				respuestaJson.put("resultado", "OK");
				respuestaJson.put("mensaje", StringEscapeUtils.escapeHtml4(this.messageSource.getMessage("log.level.ok", new Object[] {}, LocaleContextHolder.getLocale()).replace("%NIVEL%", l).replace("%PAQUETE%", p)));
			} else {
				respuestaJson.put("resultado", "ERR");
				respuestaJson.put("mensaje", StringEscapeUtils.escapeHtml4(this.messageSource.getMessage("log.level.ok", new Object[] {}, LocaleContextHolder.getLocale()).replace("%NIVEL%", l).replace("%PAQUETE%", p)));
			}
			final PrintWriter out = response.getWriter();
			log.debug(respuestaJson.toString());
			out.write(respuestaJson.toString());
		} catch (IOException | JSONException e) {
			log.error("", e);
			throw new ServicioErrorException(e);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_FILTRO_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	/**
	 * Carga en request datos registro.
	 *
	 * @param form    the form
	 * @param model   the model
	 * @param request the request
	 */
	private void cargaEnRequestDatosRegistro(final UsuarioUI form, final Model model,
					final HttpServletRequest request) {
		model.addAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_REGISTRO, form);
		request.setAttribute("paises", this.paises.getLista(LocaleContextHolder.getLocale().getLanguage().toUpperCase()));
		request.setAttribute("divisas", this.divisas.getLista());
	}

	@RequestMapping(value = "/adminEliminaUsuariosCompletamente.action", method = RequestMethod.GET)
	public void adminEliminaUsuariosCompletamenteGet(final HttpServletRequest request, final HttpServletResponse response) {
		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}
		try {
			final JSONObject respuestaJson = new JSONObject();
			try {
				final Integer id = Integer.parseInt( this.criptografia.descodificaParaUrl(StringUtils.trimToEmpty(request.getParameter("w"))));
				this.usuariosService.eliminaCompletamenteUnUsuario(id);
				respuestaJson.put("resultado", "OK");
				respuestaJson.put("mensaje", StringEscapeUtils.escapeHtml4(this.messageSource.getMessage("usuario.del.ok", new Object[] {}, LocaleContextHolder.getLocale())));
			} catch ( final Exception e ) {
				respuestaJson.put("resultado", "ERR");
				respuestaJson.put("mensaje", StringEscapeUtils.escapeHtml4(this.messageSource.getMessage("usuario.del.err", new Object[] {}, LocaleContextHolder.getLocale())));
			}
			final PrintWriter out = response.getWriter();
			log.debug(respuestaJson.toString());
			out.write(respuestaJson.toString());
		} catch (IOException | JSONException e) {
			log.error("", e);
			throw new ServicioErrorException(e);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_FILTRO_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}
}
