package com.spinnakersimple.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.exceptions.ServicioErrorException;
import com.capitanperegrina.common.seguridad.Criptografia;
import com.capitanperegrina.common.seguridad.recaptcha.ReCaptchaSettings;
import com.capitanperegrina.common.seguridad.recaptcha.VerifyRecaptcha;
import com.capitanperegrina.common.utils.StackTraceUtil;
import com.spinnakersimple.helpers.ContactoMailHelper;
import com.spinnakersimple.modelo.dto.ContactoDTO;
import com.spinnakersimple.modelo.entidad.FotografiaPOJO;
import com.spinnakersimple.modelo.util.OrdenacionAnuncios;
import com.spinnakersimple.modelo.util.SpinnakerSimpleGlobals;
import com.spinnakersimple.modelo.util.TipoOrdenacion;
import com.spinnakersimple.servicios.AnunciosService;
import com.spinnakersimple.util.LegalStuffUtil;
import com.spinnakersimple.util.LegalStuffUtil.FinalidadesLegales;
import com.spinnakersimple.util.tablasayuda.Parametrizacion;
import com.spinnakersimple.util.tablasayuda.TiposVela;
import com.spinnakersimple.web.ui.bean.ComprarVelaUI;
import com.spinnakersimple.web.ui.bean.ContactoUI;
import com.spinnakersimple.web.ui.util.AdaptFromUI;
import com.spinnakersimple.web.ui.util.AdaptToUI;
import com.spinnakersimple.web.validator.ContactoValidator;

/**
 * The Class OtrasPaginasController.
 */
@Controller
public class OtrasPaginasController {

    /** The log. */
    private static Logger log = Logger.getLogger(OtrasPaginasController.class);

    @Autowired
    MessageSource messageSource;

    @Autowired
    Criptografia criptografia;

    @Autowired
    ReCaptchaSettings reCaptchaSettings;

    @Autowired
    ContactoMailHelper contactoMailHelper;

    @Autowired
    ContactoValidator contactoValidator;

    @Autowired
    Parametrizacion parametrizacion;

    @Autowired
    AnunciosService anuncioService;

    /** The tipos vela. */
    @Autowired
    TiposVela tiposVela;

    @InitBinder(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_CONTACTO)
    protected void initBinderContactoValidator(final WebDataBinder binder) {
	binder.setValidator(this.contactoValidator);
    }

    /**
     * Contacto get.
     *
     * @param model   the model
     * @param request the request
     * @return the model and view
     */
    @RequestMapping(value = "/contacto.action", method = RequestMethod.GET)
    public ModelAndView contactoGet(final Model model, final HttpServletRequest request) {
	if (log.isDebugEnabled()) {
	    log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
	}
	try {
	    request.setAttribute(SpinnakerSimpleGlobals.MENU_ELEMENTO_SELECCIONADO,
		    SpinnakerSimpleGlobals.MENU_CONTACTO);
	    model.addAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_CONTACTO, new ContactoUI());
	    return new ModelAndView("contacto");
	} finally {
	    if (log.isDebugEnabled()) {
		log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
	    }
	}
    }

    /**
     * Contacto post.
     *
     * @param contactoUI         the form
     * @param result             the result
     * @param model              the model
     * @param redirectAttributes the redirect attributes
     * @param request            the request
     * @return the model and view
     */
    @RequestMapping(value = "/contacto.action", method = RequestMethod.POST)
    public ModelAndView contactoPost(
	    @ModelAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_CONTACTO) @Validated final ContactoUI contactoUI,
	    final BindingResult result, final Model model, final RedirectAttributes redirectAttributes,
	    final HttpServletRequest request) {
	if (log.isDebugEnabled()) {
	    log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
	}
	try {
	    final String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
	    final boolean captchaOk = VerifyRecaptcha.verify(this.reCaptchaSettings.getSecret(), gRecaptchaResponse);

	    if (!captchaOk) {
		result.addError(new FieldError(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_CONTACTO, "captcha",
			this.messageSource.getMessage("captcha.erroneo", null, LocaleContextHolder.getLocale())));
	    } else {
		if (!result.hasErrors()) {
		    final ContactoDTO contactoDTO = AdaptFromUI.ui2dto(contactoUI);
		    this.contactoMailHelper.enviaCorreoContacto(contactoDTO, LocaleContextHolder.getLocale());
		    request.setAttribute(DefaultGlobalNames.MENSAJE_MSG, this.messageSource.getMessage(
			    "zonaPublicaController.contactoPost.enviado.ok", null, LocaleContextHolder.getLocale()));
		    contactoUI.reset();
		}
	    }
	    model.addAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_CONTACTO, contactoUI);
	    request.setAttribute(SpinnakerSimpleGlobals.MENU_ELEMENTO_SELECCIONADO,
		    SpinnakerSimpleGlobals.MENU_CONTACTO);
	    return new ModelAndView("contacto");
	} finally {
	    if (log.isDebugEnabled()) {
		log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
	    }
	}
    }

    /**
     * Empresa get.
     *
     * @param model   the model
     * @param request the request
     * @return the model and view
     */
    @RequestMapping(value = "/empresa.action", method = RequestMethod.GET)
    public ModelAndView empresaGet(final Model model, final HttpServletRequest request) {
	if (log.isDebugEnabled()) {
	    log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
	}
	try {
	    return new ModelAndView("empresa");
	} finally {
	    if (log.isDebugEnabled()) {
		log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
	    }
	}
    }

    /**
     * Empresa get.
     *
     * @param model   the model
     * @param request the request
     * @return the model and view
     */
    @RequestMapping(value = "/agradecimientos.action", method = RequestMethod.GET)
    public ModelAndView agradecimientosGet(final Model model, final HttpServletRequest request) {
	if (log.isDebugEnabled()) {
	    log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
	}
	try {
	    return new ModelAndView("agradecimientos");
	} finally {
	    if (log.isDebugEnabled()) {
		log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
	    }
	}
    }

    @RequestMapping(value = "/politicaCookies.action", method = RequestMethod.GET)
    public ModelAndView politicaCookiesGet(final Model model, final HttpServletRequest request) {
	if (log.isDebugEnabled()) {
	    log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
	}
	try {
	    return new ModelAndView("politicaCookies");
	} finally {
	    if (log.isDebugEnabled()) {
		log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
	    }
	}
    }

    @RequestMapping(value = "/politicaPrivacidad.action", method = RequestMethod.GET)
    public ModelAndView politicaPrivacidadGet(final Model model, final HttpServletRequest request) {
	if (log.isDebugEnabled()) {
	    log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
	}
	try {
	    return new ModelAndView("politicaPrivacidad");
	} finally {
	    if (log.isDebugEnabled()) {
		log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
	    }
	}
    }

    @RequestMapping(value = "/terminosCondiciones.action", method = RequestMethod.GET)
    public ModelAndView terminosCondicionesGet(final Model model, final HttpServletRequest request) {
	if (log.isDebugEnabled()) {
	    log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
	}
	try {
	    return new ModelAndView("terminosCondiciones");
	} finally {
	    if (log.isDebugEnabled()) {
		log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
	    }
	}
    }

    /**
     * Inicio get.
     *
     * @param request the request
     * @return the model and view
     */
    @RequestMapping(value = "/inicio.action", method = RequestMethod.GET)
    public ModelAndView inicioGet(final HttpServletRequest request) {
	if (log.isDebugEnabled()) {
	    log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
	}
	try {
	    final ComprarVelaUI form = new ComprarVelaUI();
	    form.setTipoOrden(TipoOrdenacion.DESCENDENTE);
	    form.setOrden(OrdenacionAnuncios.FECHA_ALTA);
	    form.setRegInicial("0");
	    form.setTipoVela(DefaultGlobalNames.NADA_SELECCIONADO_EN_LISTA);
	    request.setAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_COMPRAR, form);
	    request.setAttribute(SpinnakerSimpleGlobals.MENU_ELEMENTO_SELECCIONADO, SpinnakerSimpleGlobals.MENU_INICIO);
	    request.setAttribute("tiposVela",
		    this.tiposVela.getMapaParaFormulario(LocaleContextHolder.getLocale().getLanguage()));
	    request.setAttribute("anunciosDestacados",
		    AdaptToUI.toAnuncioCompletoUIList(this.anuncioService.buscaAnunciosDestacados()));
	    return new ModelAndView("principal");
	} finally {
	    if (log.isDebugEnabled()) {
		log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
	    }
	}
    }

    /**
     * Legal get.
     *
     * @param model   the model
     * @param request the request
     * @return the model and view
     */
    @RequestMapping(value = "/legal.action", method = RequestMethod.GET)
    public ModelAndView legalGet(final Model model, final HttpServletRequest request) {
	if (log.isDebugEnabled()) {
	    log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
	}
	try {
	    request.setAttribute(SpinnakerSimpleGlobals.MENU_ELEMENTO_SELECCIONADO, SpinnakerSimpleGlobals.NADA);
	    return new ModelAndView("legal");
	} finally {
	    if (log.isDebugEnabled()) {
		if (log.isDebugEnabled()) {
		    log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
		}
	    }
	}
    }

    @RequestMapping(value = "/legalStuffAjax.action", method = RequestMethod.GET)
    public void legalStuffAjaxGet(final Model model, final HttpServletRequest request,
	    final HttpServletResponse response) {
	if (log.isDebugEnabled()) {
	    log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
	}
	try {
	    final String fin = request.getParameter("f");
	    FinalidadesLegales finalidad = null;
	    switch (fin) {
	    case "U":
		finalidad = FinalidadesLegales.USUARIOS;
		break;
	    case "V":
		finalidad = FinalidadesLegales.VENDER;
		break;
	    case "C":
		finalidad = FinalidadesLegales.COMPRA;
		break;
	    case "M":
		finalidad = FinalidadesLegales.CONTACTO;
		break;
	    default:
		break;
	    }
	    if (finalidad != null) {
		final String respuesta = LegalStuffUtil.generaClausulaPrivacidad(finalidad,
			LocaleContextHolder.getLocale(), this.parametrizacion.getUrlBase());
		response.setContentType("text/html");
		response.getWriter().println(respuesta);
		response.flushBuffer();
	    }
	} catch (final IOException e) {
	    log.error(e.getMessage(), e);
	    throw new ServicioErrorException("error.generico", e);
	} finally {
	    if (log.isDebugEnabled()) {
		log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
	    }
	}
    }

    @RequestMapping(value = "/picture.action", method = RequestMethod.GET)
    public void pictureGet(final Model model, final HttpServletRequest request, final HttpServletResponse response) {
	if (log.isDebugEnabled()) {
	    log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
	}
	try {
	    final FotografiaPOJO f = this.anuncioService
		    .buscaFotografia(Integer.parseInt(this.criptografia.descodificaParaUrl(request.getParameter("f"))));
	    if (f != null) {
		response.setContentType(f.getTipo());
		response.getOutputStream().write(f.getImagen());
		response.flushBuffer();
	    }
	} catch (final IOException e) {
	    log.error(e.getMessage(), e);
	    throw new ServicioErrorException("error.generico", e);
	} finally {
	    if (log.isDebugEnabled()) {
		log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
	    }
	}
    }
}
