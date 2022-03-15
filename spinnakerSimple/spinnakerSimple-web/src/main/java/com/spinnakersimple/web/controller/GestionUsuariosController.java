package com.spinnakersimple.web.controller;

import java.util.Calendar;
import java.util.Locale;

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
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.capitanperegrina.common.basebeans.CambioEnTabla;
import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.seguridad.Criptografia;
import com.capitanperegrina.common.seguridad.recaptcha.ReCaptchaSettings;
import com.capitanperegrina.common.seguridad.recaptcha.VerifyRecaptcha;
import com.capitanperegrina.common.utils.Fecha;
import com.capitanperegrina.common.utils.StackTraceUtil;
import com.capitanperegrina.i18n.modelo.util.Divisas;
import com.capitanperegrina.i18n.modelo.util.Paises;
import com.spinnakersimple.helpers.adapters.AdapterToDtoHelper;
import com.spinnakersimple.helpers.adapters.AdapterToPojoHelper;
import com.spinnakersimple.modelo.entidad.UsuarioPOJO;
import com.spinnakersimple.modelo.excepciones.AltaUsuarioDuplicadaException;
import com.spinnakersimple.modelo.util.SpinnakerSimpleGlobals;
import com.spinnakersimple.servicios.UsuariosService;
import com.spinnakersimple.util.tablasayuda.Parametrizacion;
import com.spinnakersimple.web.ui.bean.LoginUI;
import com.spinnakersimple.web.ui.bean.UsuarioUI;
import com.spinnakersimple.web.ui.util.AdaptFromUI;
import com.spinnakersimple.web.ui.util.AdaptToUI;
import com.spinnakersimple.web.validator.LoginValidator;
import com.spinnakersimple.web.validator.UsuarioValidator;
import com.spinnakersimple.web.validator.VenderVelaValidator;

/**
 * The Class GestionUsuariosController.
 */
@Controller
public class GestionUsuariosController {

	/** The log. */
	private static Logger log = Logger.getLogger(GestionUsuariosController.class);

	/** The message source. */
	@Autowired
	MessageSource messageSource;

	/** The adapter to dto helper. */
	@Autowired
	AdapterToDtoHelper adapterToDtoHelper;

	/** The adapter to pojo helper. */
	@Autowired
	AdapterToPojoHelper adapterToPojoHelper;

	/** The re captcha settings. */
	@Autowired
	ReCaptchaSettings reCaptchaSettings;

	/** The paises. */
	@Autowired
	Paises paises;

	/** The paises. */
	@Autowired
	Divisas divisas;

	/** The criptografia. */
	@Autowired
	Criptografia criptografia;

	/** The criptografia. */
	@Autowired
	Parametrizacion parametrizacion;

	/** The usuarios service. */
	@Autowired
	UsuariosService usuariosService;

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
	 * Cambio pass get.
	 *
	 * @param model   the model
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = "/cambioPass.action", method = RequestMethod.GET)
	public ModelAndView cambioPassGet(final Model model, final HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}
		try {
			final LoginUI form = new LoginUI();
			form.setTipoUso(SpinnakerSimpleGlobals.LOGIN_TIPO_USO_CAMBIO);
			model.addAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_LOGIN, form);
			return new ModelAndView("cambioPassForm");
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	/**
	 * El perfil get.
	 *
	 * @param model   the model
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = "/elPerfil.action", method = RequestMethod.GET)
	public ModelAndView elPerfilGet(final Model model, final HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}
		try {
			final UsuarioUI form = AdaptToUI.toUsuarioUI((UsuarioPOJO) request.getSession().getAttribute(DefaultGlobalNames.USERSESSION));
			request.setAttribute(SpinnakerSimpleGlobals.MENU_ELEMENTO_SELECCIONADO, SpinnakerSimpleGlobals.MENU_USUARIO_CONECTADO);
			cargaEnRequestDatosRegistro(form, model, request);
			return new ModelAndView("elPerfil");
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
	@RequestMapping(value = "/elPerfil.action", method = RequestMethod.POST)
	public ModelAndView elPerfilPost(
					@ModelAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_REGISTRO) @Validated final UsuarioUI form,
					final BindingResult result, final Model model, final RedirectAttributes redirectAttributes,
					final HttpServletRequest request) {

		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}

		try {
			if (result.hasErrors()) {

				cargaEnRequestDatosRegistro(form, model, request);
				return new ModelAndView("elPerfil");

			} else {
				final CambioEnTabla ct = new CambioEnTabla();
				ct.setUsu(((UsuarioPOJO) request.getSession().getAttribute(DefaultGlobalNames.USERSESSION)).getIdUsuario());
				ct.setFec(Calendar.getInstance());
				ct.setIp(request.getRemoteAddr());
				ct.setLocale(LocaleContextHolder.getLocale());

				final UsuarioPOJO usuario = this.usuariosService.actualizaPerfil(AdaptFromUI.toUsuarioPOJO(form), ct);
				request.getSession().setAttribute(DefaultGlobalNames.USERSESSION, usuario);
				request.setAttribute(DefaultGlobalNames.MENSAJE_MSG, this.messageSource.getMessage("elPerfil.cambio.ok", null, LocaleContextHolder.getLocale()));
				return new ModelAndView("mensaje");
			}

		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	/**
	 * Login form get.
	 *
	 * @param model   the model
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = "/loginForm.action", method = RequestMethod.GET)
	public ModelAndView loginFormGet(final Model model, final HttpServletRequest request) {

		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}

		try {

			final LoginUI form = new LoginUI();
			form.setTipoUso(SpinnakerSimpleGlobals.LOGIN_TIPO_USO_LOGIN);
			model.addAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_LOGIN, form);
			request.setAttribute(SpinnakerSimpleGlobals.MENU_ELEMENTO_SELECCIONADO, SpinnakerSimpleGlobals.MENU_LOGIN);
			return new ModelAndView("loginForm");

		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	/**
	 * Login form post.
	 *
	 * @param form               the form
	 * @param result             the result
	 * @param model              the model
	 * @param redirectAttributes the redirect attributes
	 * @param request            the request
	 * @return the model and view
	 */
	@RequestMapping(value = "/loginForm.action", method = RequestMethod.POST)
	public String loginFormPost(
					@ModelAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_LOGIN) @Validated final LoginUI form,
					final BindingResult result, final Model model, final RedirectAttributes redirectAttributes,
					final HttpServletRequest request, final HttpServletResponse response) {

		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}

		try {

			final String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
			final boolean captchaOk = VerifyRecaptcha.verify(this.reCaptchaSettings.getSecret(), gRecaptchaResponse);
			if (!captchaOk || result.hasErrors()) {
				if (!captchaOk) {
					result.addError(new FieldError(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_CONTACTO, "captcha", this.messageSource.getMessage("captcha.erroneo", null, LocaleContextHolder.getLocale())));
				}
				form.setPassLogin("");
			} else {
				final CambioEnTabla ct = new CambioEnTabla();
				ct.setFec(Calendar.getInstance());
				ct.setIp(request.getRemoteAddr());
				ct.setLocale(LocaleContextHolder.getLocale());

				final UsuarioPOJO usuario = this.usuariosService.login(form.getEmailLogin(), form.getPassLogin(), ct);

				if (usuario == null) {
					request.getSession().setAttribute(DefaultGlobalNames.USERSESSION, null);
					request.setAttribute("msg", this.messageSource.getMessage("loginForm.loginIncorrecto", null, LocaleContextHolder.getLocale()));
				} else {

					if (usuario.getFallosLogin() > 5) {
						request.setAttribute("msg", this.messageSource.getMessage("loginForm.loginBloqueado", null, LocaleContextHolder.getLocale()));
					} else {
						request.getSession().setAttribute(DefaultGlobalNames.USERSESSION, usuario);
						request.getSession().setAttribute(SpinnakerSimpleGlobals.USERUISESSION, AdaptToUI.toUsuarioUI(usuario));
						request.setAttribute(SpinnakerSimpleGlobals.MENU_ELEMENTO_SELECCIONADO, SpinnakerSimpleGlobals.MENU_INICIO);

						final LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
						if (localeResolver != null) {
							localeResolver.setLocale(request, response, new Locale(usuario.getLang().toLowerCase()));
						}
						if (usuario.getAdmin().equals(SpinnakerSimpleGlobals.USUARIO_ADMIN)) {
							return "redirect:/admin.action";
						} else {
							return "redirect:/inicio.action";
						}
					}
				}
			}

			form.setPassLogin("");
			request.setAttribute(SpinnakerSimpleGlobals.MENU_ELEMENTO_SELECCIONADO, SpinnakerSimpleGlobals.MENU_LOGIN);
			return "loginForm";

		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	/**
	 * Logout get.
	 *
	 * @param model   the model
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = "/logout.action", method = RequestMethod.GET)
	public String logoutGet(final Model model, final HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}
		try {
			request.getSession().removeAttribute(DefaultGlobalNames.USERSESSION);
			request.setAttribute(SpinnakerSimpleGlobals.MENU_ELEMENTO_SELECCIONADO, SpinnakerSimpleGlobals.MENU_INICIO);
			return "redirect:/inicio.action";
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	/**
	 * Reset pass get.
	 *
	 * @param model   the model
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = "/resetPass.action", method = RequestMethod.GET)
	public ModelAndView resetPassGet(final Model model, final HttpServletRequest request) {

		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}

		try {
			final CambioEnTabla ct = new CambioEnTabla();
			ct.setFec(Calendar.getInstance());
			ct.setIp(request.getRemoteAddr());
			ct.setLocale(LocaleContextHolder.getLocale());

			final String id = this.criptografia.descodificaParaUrl(request.getParameter("id"));
			final String pw = this.criptografia.descodificaParaUrl(request.getParameter("pw"));
			final String phm = this.criptografia.descodificaParaUrl(request.getParameter("phm"));

			final UsuarioPOJO u = this.usuariosService.resetPassForm(id, pw, phm, ct);

			request.setAttribute("id", this.criptografia.codificaParaUrl(u.getIdUsuario().toString()));
			request.setAttribute("pw", this.criptografia.codificaParaUrl(u.getPass()));
			request.setAttribute("phm", this.criptografia.codificaParaUrl(Fecha.calendar2DateHourString(u.getFecModif(), '/', ':')));

			final LoginUI form = new LoginUI();
			form.setTipoUso(SpinnakerSimpleGlobals.LOGIN_TIPO_USO_RESET);
			model.addAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_LOGIN, form);

			return new ModelAndView("resetPassForm");
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	/**
	 * Se ejecuta con el POST de l restablecimiento de clave de acceso.
	 *
	 * @param form               the form
	 * @param result             the result
	 * @param model              the model
	 * @param redirectAttributes the redirect attributes
	 * @param request            the request
	 * @return the model and view
	 */
	@RequestMapping(value = "/resetPass.action", method = RequestMethod.POST)
	public ModelAndView resetPassPost(
					@ModelAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_LOGIN) @Validated final LoginUI form,
					final BindingResult result, final Model model, final RedirectAttributes redirectAttributes,
					final HttpServletRequest request) {

		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}

		try {
			final String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
			final boolean captchaOk = VerifyRecaptcha.verify(this.reCaptchaSettings.getSecret(), gRecaptchaResponse);
			if (!captchaOk || result.hasErrors()) {
				if (!captchaOk) {
					result.addError(new FieldError(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_CONTACTO, "captcha", this.messageSource.getMessage("captcha.erroneo", null, LocaleContextHolder.getLocale())));
				}
				form.setPassLogin("");
				model.addAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_LOGIN, form);
				request.setAttribute("id", request.getParameter("id"));
				request.setAttribute("pw", request.getParameter("pw"));
				request.setAttribute("phm", request.getParameter("phm"));
				return new ModelAndView("resetPassForm");
			} else {
				final CambioEnTabla ct = new CambioEnTabla();
				ct.setFec(Calendar.getInstance());
				ct.setIp(request.getRemoteAddr());
				ct.setLocale(LocaleContextHolder.getLocale());

				final String id = this.criptografia.descodificaParaUrl(request.getParameter("id"));
				final String pw = this.criptografia.descodificaParaUrl(request.getParameter("pw"));
				final String phm = this.criptografia.descodificaParaUrl(request.getParameter("phm"));

				this.usuariosService.resetPass(id, pw, phm, form.getPassNew(), ct);

				request.setAttribute("msg", this.messageSource.getMessage("resetPass.ok", null, LocaleContextHolder.getLocale()));

				return new ModelAndView("mensaje");
			}
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	/**
	 * Reset pass request get.
	 *
	 * @param model   the model
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = "/resetPassRequest.action", method = RequestMethod.GET)
	public ModelAndView resetPassRequestGet(final Model model, final HttpServletRequest request) {

		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}

		try {

			final LoginUI form = new LoginUI();
			form.setTipoUso(SpinnakerSimpleGlobals.LOGIN_TIPO_USO_RECORDAR);
			model.addAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_LOGIN, form);
			request.setAttribute(SpinnakerSimpleGlobals.MENU_ELEMENTO_SELECCIONADO, SpinnakerSimpleGlobals.MENU_LOGIN);

			return new ModelAndView("resetPassRequest");

		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	/**
	 * Reset pass request post.
	 *
	 * @param form               the form
	 * @param result             the result
	 * @param model              the model
	 * @param redirectAttributes the redirect attributes
	 * @param request            the request
	 * @return the model and view
	 */
	@RequestMapping(value = "/resetPassRequest.action", method = RequestMethod.POST)
	public ModelAndView resetPassRequestPost(
					@ModelAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_LOGIN) @Validated final LoginUI form,
					final BindingResult result, final Model model, final RedirectAttributes redirectAttributes,
					final HttpServletRequest request) {

		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}

		try {
			final String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
			final boolean captchaOk = VerifyRecaptcha.verify(this.reCaptchaSettings.getSecret(), gRecaptchaResponse);
			if (!captchaOk || result.hasErrors()) {
				if (!captchaOk) {
					result.addError(new FieldError(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_CONTACTO, "captcha", this.messageSource.getMessage("captcha.erroneo", null, LocaleContextHolder.getLocale())));
				}
				form.setPassLogin("");
				model.addAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_LOGIN, form);
				return new ModelAndView("resetPassRequest");
			} else {

				final CambioEnTabla ct = new CambioEnTabla();
				ct.setFec(Calendar.getInstance());
				ct.setIp(request.getRemoteAddr());
				ct.setLocale(LocaleContextHolder.getLocale());

				this.usuariosService.resetPassRequest(form.getEmailLogin().toLowerCase(), ct);

				request.setAttribute("msg", this.messageSource.getMessage("resetPassRequestPost.ok", new Object[] {}, LocaleContextHolder.getLocale()).replaceAll("%MAIL%", form.getEmailLogin().toLowerCase()));

				return new ModelAndView("mensaje");
			}
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	/**
	 * Sign up get.
	 *
	 * @param model   the model
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = "/signUp.action", method = RequestMethod.GET)
	public ModelAndView signUpGet(final Model model, final HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}
		try {
			final UsuarioUI form = new UsuarioUI();
			form.setDivisa(DefaultGlobalNames.COD_DIVISA_EURO);
			cargaEnRequestDatosRegistro(form, model, request);
			return new ModelAndView("signUpForm");

		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	/**
	 * Sign up post.
	 *
	 * @param form               the form
	 * @param result             the result
	 * @param model              the model
	 * @param redirectAttributes the redirect attributes
	 * @param request            the request
	 * @return the model and view
	 */
	@RequestMapping(value = "/signUp.action", method = RequestMethod.POST)
	public ModelAndView signUpPost(
					@ModelAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_REGISTRO) @Validated final UsuarioUI form,
					final BindingResult result, final Model model, final RedirectAttributes redirectAttributes,
					final HttpServletRequest request) {

		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}

		try {
			final String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
			final boolean captchaOk = VerifyRecaptcha.verify(this.reCaptchaSettings.getSecret(), gRecaptchaResponse);

			if (!captchaOk || result.hasErrors()) {
				if (!captchaOk) {
					result.addError(new FieldError(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_CONTACTO, "captcha", this.messageSource.getMessage("captcha.erroneo", null, LocaleContextHolder.getLocale())));
				}
				cargaEnRequestDatosRegistro(form, model, request);
				return new ModelAndView("signUpForm");
			} else {

				final CambioEnTabla ct = new CambioEnTabla();
				ct.setFec(Calendar.getInstance());
				ct.setIp(request.getRemoteAddr());
				ct.setLocale(LocaleContextHolder.getLocale());
				final UsuarioPOJO u = this.usuariosService.altaUsuario(AdaptFromUI.toUsuarioPOJO(form), ct);
				final String msg = this.messageSource.getMessage("alta.usuario.mensaje.exito", new Object[] {}, LocaleContextHolder.getLocale()).replace("%MAIL%", u.getMail());
				request.setAttribute(DefaultGlobalNames.MENSAJE_MSG, msg);
				return new ModelAndView("mensaje");
			}
		} catch (final AltaUsuarioDuplicadaException e) {
			form.setPass("");
			result.addError(new FieldError(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_REGISTRO, "mail",
							this.messageSource.getMessage(e.getMessage(), null, LocaleContextHolder.getLocale())));
			cargaEnRequestDatosRegistro(form, model, request);
			return new ModelAndView("signUpForm");
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
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
		request.setAttribute(SpinnakerSimpleGlobals.MENU_ELEMENTO_SELECCIONADO, SpinnakerSimpleGlobals.MENU_REGISTRO);
	}
}
