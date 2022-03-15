package com.spinnakersimple.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.HtmlUtils;

import com.capitanperegrina.common.basebeans.CambioEnTabla;
import com.capitanperegrina.common.bean.Pair;
import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.exceptions.ServicioErrorException;
import com.capitanperegrina.common.i18n.Mensajes;
import com.capitanperegrina.common.seguridad.Criptografia;
import com.capitanperegrina.common.seguridad.recaptcha.ReCaptchaSettings;
import com.capitanperegrina.common.seguridad.recaptcha.VerifyRecaptcha;
import com.capitanperegrina.common.utils.StackTraceUtil;
import com.capitanperegrina.common.web.MensajeWeb;
import com.capitanperegrina.i18n.modelo.util.Divisas;
import com.capitanperegrina.i18n.modelo.util.Paises;
import com.google.gson.Gson;
import com.spinnakersimple.modelo.bean.AnuncioCompleto;
import com.spinnakersimple.modelo.bean.SocialBean;
import com.spinnakersimple.modelo.entidad.CompradoresPOJO;
import com.spinnakersimple.modelo.entidad.TipoVelaPOJO;
import com.spinnakersimple.modelo.entidad.UsuarioPOJO;
import com.spinnakersimple.modelo.excepciones.AltaUsuarioDuplicadaException;
import com.spinnakersimple.modelo.util.OrdenacionAnuncios;
import com.spinnakersimple.modelo.util.SpinnakerSimpleGlobals;
import com.spinnakersimple.modelo.util.TipoOrdenacion;
import com.spinnakersimple.servicios.AnunciosService;
import com.spinnakersimple.servicios.AuxiliarService;
import com.spinnakersimple.servicios.UsuariosService;
import com.spinnakersimple.util.tablasayuda.Parametrizacion;
import com.spinnakersimple.util.tablasayuda.TiposBarco;
import com.spinnakersimple.util.tablasayuda.TiposVela;
import com.spinnakersimple.web.ui.bean.ComprarVelaUI;
import com.spinnakersimple.web.ui.bean.ConsultarVelaUI;
import com.spinnakersimple.web.ui.bean.VenderVelaUI;
import com.spinnakersimple.web.ui.util.AdaptFromUI;
import com.spinnakersimple.web.ui.util.AdaptToUI;
import com.spinnakersimple.web.validator.ComprarVelaValidator;
import com.spinnakersimple.web.validator.ConsultarVelaValidator;
import com.spinnakersimple.web.validator.VenderVelaValidator;

/**
 * The Class CompraVentaController.
 */
@Controller
public class CompraVentaController {

    /** The LOGGER. */
    private static Logger LOGGER = Logger.getLogger(CompraVentaController.class);

    @Autowired
    MessageSource messageSource;

    @Autowired
    Parametrizacion parametrizacion;

    /** The criptografia. */
    @Autowired
    Criptografia criptografia;

    /** The tipos vela. */
    @Autowired
    TiposVela tiposVela;

    @Autowired
    TiposBarco tiposBarco;

    @Autowired
    Divisas divisas;

    /** The paises. */
    @Autowired
    Paises paises;

    @Autowired
    AnunciosService anunciosService;

    /** The usuarios service. */
    @Autowired
    UsuariosService usuariosService;

    @Autowired
    AuxiliarService auxiliarService;

    @Autowired
    ReCaptchaSettings reCaptchaSettings;

    @Autowired
    ConsultarVelaValidator consultarVelaValidator;

    @Autowired
    VenderVelaValidator venderVelaValidator;

    @Autowired
    ComprarVelaValidator comprarVelaValidator;

    @InitBinder(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_COMPRAR)
    protected void initBinderComprarVelaValidator(final WebDataBinder binder) {
	binder.setValidator(this.comprarVelaValidator);
    }

    @InitBinder(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_CONSULTAR_VELA)
    protected void initBinderConsultarVelaValidator(final WebDataBinder binder) {
	binder.setValidator(this.consultarVelaValidator);
    }

    @InitBinder(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_VENDER)
    protected void initBinderVenderVelaValidator(final WebDataBinder binder) {
	binder.setValidator(this.venderVelaValidator);
    }

    /**
     * Genera el formulario de venta de velas.
     *
     * @param model   the model
     * @param request the request
     * @return the model and view
     */
    @RequestMapping(value = "/venderVela.action", method = RequestMethod.GET)
    public ModelAndView venderVelaGet(final Model model, final HttpServletRequest request) {
	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
	}
	try {
	    // Guardamos en la request qu'e men'u est'a activo
	    request.setAttribute(SpinnakerSimpleGlobals.MENU_ELEMENTO_SELECCIONADO, SpinnakerSimpleGlobals.MENU_VENDER);

	    // Se crea el formulario de venta de velas y se asignan los valores
	    // por defecto.
	    final VenderVelaUI form = new VenderVelaUI();
	    form.setTipoVela(TiposVela.GENOVA.toString());
	    form.setPais(DefaultGlobalNames.NADA_SELECCIONADO_EN_LISTA);

	    // En función de si el usuario está logueado se carga su divisa en
	    // el formulario o la divisa por defecto que es el euro.
	    if (request.getSession().getAttribute(DefaultGlobalNames.USERSESSION) == null) {
		form.setTipoAlta(SpinnakerSimpleGlobals.VENDER_TIPO_ALTA);
		form.setDivisa(DefaultGlobalNames.COD_DIVISA_EURO);
	    } else {
		form.setTipoAlta(SpinnakerSimpleGlobals.VENDER_TIPO_ALTA_LOGGED);
		form.setDivisa(
			((UsuarioPOJO) request.getSession().getAttribute(DefaultGlobalNames.USERSESSION)).getDivisa());
		request.setAttribute("literalDivisa", this.divisas.getPojo(
			((UsuarioPOJO) request.getSession().getAttribute(DefaultGlobalNames.USERSESSION)).getDivisa())
			.getDescripcionCompleta());
	    }

	    // Se pasan los datos a la request y al modelo.
	    model.addAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_VENDER, form);
	    request.setAttribute("tiposBarco",
		    this.tiposBarco.getMapaParaFormulario(LocaleContextHolder.getLocale().getLanguage()));
	    request.setAttribute("paises",
		    this.paises.getLista(LocaleContextHolder.getLocale().getLanguage().toUpperCase()));
	    request.setAttribute("divisas", this.divisas.getLista());

	    return new ModelAndView("venderVela");
	} finally {
	    if (LOGGER.isDebugEnabled()) {
		LOGGER.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
	    }
	}
    }

    @RequestMapping(value = "/tiposDeVela.action", method = RequestMethod.GET)
    public void tiposDeVela(final HttpServletRequest request, final HttpServletResponse response) {
	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
	}
	try {
	    final Integer idTipoBarco = Integer.parseInt(request.getParameter("tb"));
	    final List<TipoVelaPOJO> tiposVela = this.anunciosService.buscaTiposDeVelaPorTipoBarco(idTipoBarco);
	    final Map<String, String> mapaTiposVela = new HashMap<>();
	    for (final TipoVelaPOJO tv : tiposVela) {
		mapaTiposVela.put(tv.getIdTipoVela().toString(), HtmlUtils.htmlEscape(Mensajes
			.getStringSafe("tipoVela." + tv.getIdTipoVela().toString(), LocaleContextHolder.getLocale())));
	    }
	    final JSONObject respuestaJson = new JSONObject(mapaTiposVela);
	    final PrintWriter out = response.getWriter();
	    LOGGER.debug(respuestaJson.toString());
	    out.write(respuestaJson.toString());
	} catch (final IOException e) {
	    LOGGER.error("", e);
	    throw new ServicioErrorException(e);
	} finally {
	    if (LOGGER.isDebugEnabled()) {
		LOGGER.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
	    }
	}
    }

    @RequestMapping(value = "/buscaConfiguracionTipoVela.action", method = RequestMethod.GET)
    public void buscaConfiguracionTipoVelaGet(final HttpServletRequest request, final HttpServletResponse response) {
	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
	}
	try {
	    final Integer idTipoVela = Integer.parseInt(request.getParameter("tv"));
	    final TipoVelaPOJO tv = this.tiposVela.getPojo(LocaleContextHolder.getLocale().getLanguage(), idTipoVela);
	    final JSONObject respuestaJson = new JSONObject(tv);
	    final PrintWriter out = response.getWriter();
	    LOGGER.debug(respuestaJson.toString());
	    out.write(respuestaJson.toString());
	} catch (final IOException e) {
	    LOGGER.error("", e);
	    throw new ServicioErrorException(e);
	} finally {
	    if (LOGGER.isDebugEnabled()) {
		LOGGER.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
	    }
	}
    }

    @RequestMapping(value = "/buscaBotonesActivosPorTiposClase.action", method = RequestMethod.GET)
    public void buscaBotonesActivosPorTiposClaseGet(final HttpServletRequest request,
	    final HttpServletResponse response) {
	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
	}
	try {
	    final List<Pair<String, Integer>> botonesActivosPorTipoClase = this.anunciosService
		    .numeroAnunciosPorTipoClase();
	    final Gson gson = new Gson();
	    final String respuestaJson = gson.toJson(botonesActivosPorTipoClase);
	    final PrintWriter out = response.getWriter();
	    LOGGER.debug(respuestaJson);
	    out.write(respuestaJson);
	} catch (final IOException e) {
	    LOGGER.error("", e);
	    throw new ServicioErrorException(e);
	} finally {
	    if (LOGGER.isDebugEnabled()) {
		LOGGER.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
	    }
	}
    }

    @RequestMapping(value = "/buscaBotonesActivosPorTiposBarco.action", method = RequestMethod.GET)
    public void buscaBotonesActivosPorTiposBarco(final HttpServletRequest request, final HttpServletResponse response) {
	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
	}
	try {
	    final List<Pair<String, Integer>> botonesActivosPorTiposBarco = this.anunciosService
		    .numeroAnunciosPorTipoBarco();
	    final Gson gson = new Gson();
	    final String respuestaJson = gson.toJson(botonesActivosPorTiposBarco);
	    final PrintWriter out = response.getWriter();
	    LOGGER.debug(respuestaJson);
	    out.write(respuestaJson);
	} catch (final IOException e) {
	    LOGGER.error("", e);
	    throw new ServicioErrorException(e);
	} finally {
	    if (LOGGER.isDebugEnabled()) {
		LOGGER.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
	    }
	}
    }

    /**
     * Método que da de alta una vela.
     *
     * @param venderVelaUi       the vender vela ui
     * @param result             the result
     * @param model              the model
     * @param redirectAttributes the redirect attributes
     * @param request            the request
     * @return the model and view
     */
    @RequestMapping(value = "/venderVela.action", method = RequestMethod.POST)
    public ModelAndView venderVelaPost(
	    @ModelAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_VENDER) @Validated final VenderVelaUI venderVelaUi,
	    final BindingResult result, final Model model, final RedirectAttributes redirectAttributes,
	    final HttpServletRequest request) {

	boolean usuarioNuevo = false;
	boolean velaNueva = false;

	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
	}

	try {

	    // Comprobación del captcha si el usuario no está logueado.
	    final String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
	    boolean captchaOk = true;
	    if (request.getSession().getAttribute(DefaultGlobalNames.USERSESSION) == null) {
		captchaOk = VerifyRecaptcha.verify(this.reCaptchaSettings.getSecret(), gRecaptchaResponse);
	    }
	    if (!captchaOk) {
		result.addError(new FieldError(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_CONTACTO, "captcha",
			this.messageSource.getMessage("captcha.erroneo", null, LocaleContextHolder.getLocale())));
		venderVelaUi.setPassLogin("");
		venderVelaUi.setPass("");
		cargaEnRequestDatosAltaVela(venderVelaUi, model, request);
		return new ModelAndView("venderVela");
	    } else if (result.hasErrors()) {
		venderVelaUi.setPassLogin("");
		venderVelaUi.setPass("");
		cargaEnRequestDatosAltaVela(venderVelaUi, model, request);
		return new ModelAndView("venderVela");
	    } else {

		AnuncioCompleto ac = null;

		// Creamos objeto de auditoría.
		final CambioEnTabla ct = new CambioEnTabla();
		ct.setFec(Calendar.getInstance());
		ct.setIp(request.getRemoteAddr());
		ct.setLocale(LocaleContextHolder.getLocale());

		// Se introduce el login a la vez que los datos de venta.
		if (venderVelaUi.getTipoAlta().equals(SpinnakerSimpleGlobals.VENDER_TIPO_LOGIN)) {

		    // Hacemos el login.
		    final UsuarioPOJO usuario = this.usuariosService.login(venderVelaUi.getEmailLogin(),
			    venderVelaUi.getPassLogin(), ct);

		    if (usuario == null) {
			// Login erróneo.
			result.addError(new ObjectError("emailLogin", "loginForm.loginIncorrecto"));
			cargaEnRequestDatosAltaVela(venderVelaUi, model, request);
			return new ModelAndView("venderVela");
		    } else {
			if (usuario.getFallosLogin() > 5) {
			    // Fallos acumulados impidel login.
			    result.addError(new ObjectError("emailLogin", "loginForm.loginBloqueado"));
			    cargaEnRequestDatosAltaVela(venderVelaUi, model, request);
			    return new ModelAndView("venderVela");
			} else {
			    // Login correcto.
			    ct.setUsu(usuario.getIdUsuario());

			    // Damos de alta el anuncio.
			    ac = AdaptFromUI.toAnuncioCompleto(venderVelaUi, ct,
				    this.auxiliarService.leeTipoDeCambio(usuario.getDivisa()));
			    ac = this.anunciosService.altaAnuncio(ac, ct);
			    velaNueva = true;
			}
		    }
		} else if (venderVelaUi.getTipoAlta().equals(SpinnakerSimpleGlobals.VENDER_TIPO_ALTA_LOGGED)) {

		    final UsuarioPOJO usuario = (UsuarioPOJO) request.getSession()
			    .getAttribute(DefaultGlobalNames.USERSESSION);
		    ct.setUsu(usuario.getIdUsuario());

		    // Damos de alta el anuncio.
		    ac = AdaptFromUI.toAnuncioCompleto(venderVelaUi, ct,
			    this.auxiliarService.leeTipoDeCambio(usuario.getDivisa()), usuario);
		    ac = this.anunciosService.altaAnuncio(ac, ct);
		    velaNueva = true;

		} else if (venderVelaUi.getTipoAlta().equals(SpinnakerSimpleGlobals.VENDER_TIPO_ALTA)) {

		    // Damos de alta el anuncio.
		    ac = AdaptFromUI.toAnuncioCompleto(venderVelaUi, ct,
			    this.auxiliarService.leeTipoDeCambio(venderVelaUi.getDivisa()));
		    ac = this.anunciosService.altaUsuarioAnuncio(ac, ct);
		    velaNueva = true;
		    usuarioNuevo = true;
		}

		if (ac != null) {
		    if (velaNueva) {
			request.setAttribute(SpinnakerSimpleGlobals.CONVERSION_ALTA_VELA, DefaultGlobalNames.SI);
		    }
		    request.setAttribute(DefaultGlobalNames.MENSAJE_MSG,
			    this.messageSource
				    .getMessage("altaVelaMailHelper.generaTextoAltaVelaAnunciante.20", new Object[] {},
					    LocaleContextHolder.getLocale())
				    .replaceAll("%TITULO%", ac.getAnuncio().getTituloAnuncio()));
		}

		return new ModelAndView("mensaje");
	    }
	} catch (final AltaUsuarioDuplicadaException e) {
	    venderVelaUi.setPassLogin("");
	    venderVelaUi.setPass("");
	    result.addError(new FieldError(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_VENDER, "email",
		    this.messageSource.getMessage(e.getMessage(), null, LocaleContextHolder.getLocale())));
	    cargaEnRequestDatosAltaVela(venderVelaUi, model, request);
	    return new ModelAndView("venderVela");
	} catch (final IOException e) {
	    LOGGER.error("", e);
	    throw new ServicioErrorException(e);
	} finally {
	    if (LOGGER.isDebugEnabled()) {
		LOGGER.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
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
    @RequestMapping(value = "/verVela.action", method = RequestMethod.GET)
    public ModelAndView verVelaGet(final Model model, final HttpServletRequest request) {

	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
	}

	try {
	    // Obtenemos el id del anuncio a consultar.
	    final String ida = request.getParameter("ida");
	    if (StringUtils.isEmpty(ida)) {
		return new ModelAndView("redirect:" + this.parametrizacion.getUrlBase());
	    }
	    final Integer idAnuncio = Integer
		    .parseInt(this.criptografia.descodificaParaUrl(request.getParameter("ida")));

	    // Creamos el formulario de consulta.
	    final ConsultarVelaUI form = new ConsultarVelaUI();
	    form.setIda(idAnuncio.toString());
	    model.addAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_CONSULTAR_VELA, form);

	    // Obtenemos el símbolo de la divisa.
	    Integer idUsuario = null;
	    String simboloDivisa = "&#8364;";
	    if (request.getSession().getAttribute(DefaultGlobalNames.USERSESSION) != null) {
		final UsuarioPOJO u = (UsuarioPOJO) request.getSession().getAttribute(DefaultGlobalNames.USERSESSION);
		simboloDivisa = this.divisas.getPojo(u.getDivisa()).getSimboloDivisa();
		idUsuario = u.getIdUsuario();
		request.setAttribute("modoPagina", SpinnakerSimpleGlobals.MENU_MIS_ANUNCIOS);
	    }

	    final AnuncioCompleto anuncio = this.anunciosService.buscarAnuncio(idAnuncio, idUsuario, true);

	    if (this.parametrizacion.isMostrarBotonesSociales()) {
		final SocialBean socialBean = new SocialBean();
		socialBean.setOgDescription(anuncio.getAnuncio().getDescripcion());
		if (CollectionUtils.isEmpty(anuncio.getFotos())) {
		    socialBean.setOgImage(this.parametrizacion.getUrlLogo());
		} else {
		    socialBean.setOgImage(this.parametrizacion.getUrlBase() + "/picture.action?f=" + this.criptografia
			    .codificaParaUrl(anuncio.getFotos().get(0).getIdFotografia().toString()));
		}
		socialBean.setOgTittle(anuncio.getAnuncio().getTituloAnuncio());
		socialBean.setOgType("website");
		socialBean.setOgUrl(this.parametrizacion.getUrlBase() + "/verVela.action?ida="
			+ this.criptografia.codificaParaUrl(anuncio.getAnuncio().getIdAnuncio().toString()));
		socialBean.setTexto(URLEncoder.encode(anuncio.getAnuncio().getTituloAnuncio(), "UTF-8"));
		socialBean
			.setUrl(URLEncoder.encode(
				this.parametrizacion.getUrlBase() + "/verVela.action?ida="
					+ this.criptografia
						.codificaParaUrl(anuncio.getAnuncio().getIdAnuncio().toString()),
				"UTF-8"));
		socialBean.setVia("spinnakerSimple");

		// Metemos los datos en la request para mostrar en la vista.
		request.setAttribute("socialButtons", DefaultGlobalNames.SI);
		request.setAttribute("socialBean", socialBean);
	    }

	    request.setAttribute("anuncio", AdaptToUI.toAnuncioCompletoUI(anuncio));
	    request.setAttribute("simboloDivisa", simboloDivisa);
	    request.setAttribute("modoPagina", null);
	    request.setAttribute(SpinnakerSimpleGlobals.MENU_ELEMENTO_SELECCIONADO,
		    SpinnakerSimpleGlobals.MENU_COMPRAR);
	    return new ModelAndView("verVela");
	} catch (final UnsupportedEncodingException e) {
	    LOGGER.error(e.getMessage(), e);
	    throw new ServicioErrorException(e);
	} finally {
	    if (LOGGER.isDebugEnabled()) {
		LOGGER.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
	    }
	}
    }

    /**
     * Envia una consulta acerca de una vela.
     *
     * @param consultaVelaUi     the form
     * @param result             the result
     * @param model              the model
     * @param redirectAttributes the redirect attributes
     * @param request            the request
     * @return the model and view
     */
    @RequestMapping(value = "/consultaVela.action", method = RequestMethod.POST)
    public String consultaVelaPost(
	    @ModelAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_CONSULTAR_VELA) @Validated final ConsultarVelaUI consultaVelaUi,
	    final BindingResult result, final Model model, final RedirectAttributes redirectAttributes,
	    final HttpServletRequest request) {

	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
	}

	try {

	    final String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
	    final boolean captchaOk = VerifyRecaptcha.verify(this.reCaptchaSettings.getSecret(), gRecaptchaResponse);
	    if (!captchaOk) {
		result.addError(new FieldError(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_CONTACTO, "captcha",
			this.messageSource.getMessage("captcha.erroneo", null, LocaleContextHolder.getLocale())));
	    } else {
		if (!result.hasErrors()) {
		    final CompradoresPOJO c = AdaptFromUI.toCompradores(consultaVelaUi);
		    this.anunciosService.saveAndMail(c);

		    final MensajeWeb msg = new MensajeWeb();
		    msg.setVar("msg");
		    msg.setKey("zonaPublicaController.consultaVelaGet.saveAndMail.ok");
		    msg.setArgs(new Object[] {});
		    msg.setTilesMapping("mensaje");
		    msg.setUrlVolver(
			    "verVela.action?ida=" + this.criptografia.codificaParaUrl(c.getIdAnuncio().toString()));
		    msg.getParametros().put(SpinnakerSimpleGlobals.CONVERSION_COMENTARIO_VELA, DefaultGlobalNames.SI);
		    request.getSession().setAttribute("mensajeObj", msg);

		    return "redirect:/mensaje.action";
		}
	    }

	    final Integer idAnuncio = Integer.parseInt(consultaVelaUi.getIda());
	    model.addAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_CONSULTAR_VELA, consultaVelaUi);
	    Integer idUsuario = null;
	    if (request.getSession().getAttribute(DefaultGlobalNames.USERSESSION) != null) {
		idUsuario = ((UsuarioPOJO) request.getSession().getAttribute(DefaultGlobalNames.USERSESSION))
			.getIdUsuario();
	    }
	    request.setAttribute("anuncio",
		    AdaptToUI.toAnuncioCompletoUI(this.anunciosService.buscarAnuncio(idAnuncio, idUsuario, false)));
	    request.setAttribute(SpinnakerSimpleGlobals.MENU_ELEMENTO_SELECCIONADO,
		    SpinnakerSimpleGlobals.MENU_COMPRAR);

	    return "verVela";

	} finally {
	    if (LOGGER.isDebugEnabled()) {
		LOGGER.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
	    }
	}
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Comprar vela get.
     *
     * @param model   the model
     * @param request the request
     * @return the model and view
     */
    @RequestMapping(value = "/comprarVela.action", method = RequestMethod.GET)
    public ModelAndView comprarVelaGet(final Model model, final HttpServletRequest request) {
	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
	}
	try {
	    final ComprarVelaUI form = new ComprarVelaUI();
	    form.setTipoOrden(TipoOrdenacion.DESCENDENTE);
	    form.setOrden(OrdenacionAnuncios.FECHA_ALTA);
	    form.setTipoVela(TiposVela.GENOVA.toString());
	    request.setAttribute(SpinnakerSimpleGlobals.MENU_ELEMENTO_SELECCIONADO,
		    SpinnakerSimpleGlobals.MENU_COMPRAR);
	    model.addAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_COMPRAR, form);
	    request.setAttribute("tiposVela",
		    this.tiposVela.getMapaParaFormulario(LocaleContextHolder.getLocale().getLanguage()));
	    request.setAttribute("busquedaRealizada", DefaultGlobalNames.NO);
	    model.addAttribute("tipoBarcoDescripcion",
		    this.messageSource.getMessage("global.todos", null, LocaleContextHolder.getLocale()));
	    return new ModelAndView("comprarVela");
	} finally {
	    if (LOGGER.isDebugEnabled()) {
		LOGGER.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
	    }
	}
    }

    @RequestMapping(value = "/comprarVelaExec.action", method = RequestMethod.GET)
    public ModelAndView comprarVelaBuscarPost(
	    @ModelAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_COMPRAR) @Validated final ComprarVelaUI filtro,
	    final BindingResult result, final Model model, final RedirectAttributes redirectAttributes,
	    final HttpServletRequest request) {
	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
	}
	try {
	    if (!result.hasErrors()) {
		final UsuarioPOJO u = (UsuarioPOJO) request.getSession().getAttribute(DefaultGlobalNames.USERSESSION);
		final Integer idUsuario = u != null ? u.getIdUsuario() : null;
		request.setAttribute("resultadosBusqueda", AdaptToUI.toAnuncioCompletoUIList(
			this.anunciosService.buscarAnuncios(AdaptFromUI.toFiltroBusquedaVelaBean(filtro), idUsuario)));
	    }
	    request.setAttribute(SpinnakerSimpleGlobals.MENU_ELEMENTO_SELECCIONADO,
		    SpinnakerSimpleGlobals.MENU_COMPRAR);
	    model.addAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_COMPRAR, filtro);
	    if (StringUtils.isNotEmpty(filtro.getTipoBarco())) {
		model.addAttribute("tipoBarcoDescripcion", this.tiposBarco
			.getPojo(LocaleContextHolder.getLocale().getLanguage(), Integer.parseInt(filtro.getTipoBarco()))
			.getTipoBarco());
	    } else {
		model.addAttribute("tipoBarcoDescripcion",
			this.messageSource.getMessage("global.todos", null, LocaleContextHolder.getLocale()));
	    }
	    request.setAttribute("tiposVela",
		    this.tiposVela.getMapaParaFormulario(LocaleContextHolder.getLocale().getLanguage()));
	    request.setAttribute("busquedaRealizada", DefaultGlobalNames.SI);
	    return new ModelAndView("comprarVela");
	} finally {
	    if (LOGGER.isDebugEnabled()) {
		LOGGER.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
	    }
	}
    }

    private void cargaEnRequestDatosAltaVela(final VenderVelaUI form, final Model model,
	    final HttpServletRequest request) {
	if (StringUtils.isNotEmpty(form.getTipoBarco())) {
	    request.setAttribute("nombreTipoBarco", this.tiposBarco
		    .getPojo(LocaleContextHolder.getLocale().getLanguage(), Integer.parseInt(form.getTipoBarco()))
		    .getTipoBarco());
	}
	request.setAttribute("tiposVela",
		this.tiposVela.getMapaParaFormulario(LocaleContextHolder.getLocale().getLanguage()));
	request.setAttribute("paises",
		this.paises.getLista(LocaleContextHolder.getLocale().getLanguage().toUpperCase()));
	model.addAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_VENDER, form);
	request.setAttribute(SpinnakerSimpleGlobals.MENU_ELEMENTO_SELECCIONADO, SpinnakerSimpleGlobals.MENU_VENDER);
	request.setAttribute("divisas", this.divisas.getLista());
    }
}
