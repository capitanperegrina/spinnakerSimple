package com.capitanperegrina.usuarios.web.controller;

import java.util.Calendar;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.capitanperegrina.common.basebeans.CambioEnTabla;
import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.exceptions.ServicioErrorException;
import com.capitanperegrina.common.exceptions.ServicioException;
import com.capitanperegrina.common.seguridad.Criptografia;
import com.capitanperegrina.common.seguridad.recaptcha.ReCaptchaSettings;
import com.capitanperegrina.common.seguridad.recaptcha.VerifyRecaptcha;
import com.capitanperegrina.common.utils.StackTraceUtil;
import com.capitanperegrina.common.validators.Validadores;
import com.capitanperegrina.common.web.MensajeWeb;
import com.capitanperegrina.usuarios.modelo.entidad.tabla.UsuarioPOJO;
import com.capitanperegrina.usuarios.servicios.UsuarioSrv;
import com.capitanperegrina.usuarios.web.forms.UserFormPOJO;
import com.capitanperegrina.usuarios.web.forms.validators.LoginFormValidator;
import com.capitanperegrina.usuarios.web.forms.validators.MyProfileFormValidator;
import com.capitanperegrina.usuarios.web.forms.validators.RecoverPassFormValidator;
import com.capitanperegrina.usuarios.web.forms.validators.RegisterFormValidator;
import com.capitanperegrina.usuarios.web.forms.validators.ResetPassFormValidator;

import net.tanesha.recaptcha.ReCaptchaImpl;

/**
 * Controlador de las acciones relacionadas con los usuarios
 */
@Controller
public class UsuariosController {
	static Logger log = Logger.getLogger(UsuariosController.class);

	@Autowired
	ReCaptchaSettings reCaptchaSettings;

	@Autowired
	ReCaptchaImpl reCaptcha;

	@Autowired
	LoginFormValidator loginFormValidator;

	@Autowired
	RecoverPassFormValidator recoverPassFormValidator;

	@Autowired
	RegisterFormValidator registerFormValidator;

	@Autowired
	ResetPassFormValidator resetPassFormValidator;

	@Autowired
	MyProfileFormValidator myProfileFormValidator;

	@Autowired
	MessageSource messageSource;

	@Autowired
	UsuarioSrv usuarioSrv;

	/** The criptografia. */
	@Autowired
	Criptografia criptografia;

	@InitBinder("loginForm")
	protected void initBinderLoginForm(final WebDataBinder binder) {
		binder.setValidator(this.loginFormValidator);
	}

	@InitBinder("recoverPassForm")
	protected void initBinderRecoverPassForm(final WebDataBinder binder) {
		binder.setValidator(this.recoverPassFormValidator);
	}

	@InitBinder("resetPassForm")
	protected void initBinderResetPassForm(final WebDataBinder binder) {
		binder.setValidator(this.resetPassFormValidator);
	}

	@InitBinder("registerForm")
	protected void initBinderRegisterForm(final WebDataBinder binder) {
		binder.setValidator(this.registerFormValidator);
	}

	@InitBinder("myProfileForm")
	protected void initBinderMyProfileForm(final WebDataBinder binder) {
		binder.setValidator(this.myProfileFormValidator);
	}

	// MAIN -------------------------------------------------------------------

	@RequestMapping(value = "/main.action", method = RequestMethod.GET)
	public String mainGet(final Model model, final HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
        }
        try {
            if (request.getSession().getAttribute(DefaultGlobalNames.USERSESSION) == null) {
			return "main";
            } else {
                return "mainLogged";
            }
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	@RequestMapping(value = "/mainLogged.action", method = RequestMethod.GET)
	public String mainLoggedGet(final Model model, final HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}
		try {
			return "mainLogged";
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	// LOGIN ------------------------------------------------------------------

	@RequestMapping(value = "/loginForm.action", method = RequestMethod.GET)
	public String loginFormGet(final Model model, final HttpServletRequest request) {
		log.debug("UsuariosController.loginFormGet");
		if (request.getSession().getAttribute(DefaultGlobalNames.USERSESSION) != null) {
			return "redirect:/main.action";
		}
		final UserFormPOJO usuario = new UserFormPOJO();
		model.addAttribute("loginForm", usuario);
		return "loginForm";
	}

	@RequestMapping(value = "/loginForm.action", method = RequestMethod.POST)
	public String loginFormPost(@ModelAttribute("loginForm") @Validated final UserFormPOJO usuario, final BindingResult result,
			final Model model, final RedirectAttributes redirectAttributes, final HttpServletRequest request)
			throws ServicioException {

		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}

		try {
			final String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
			final boolean captchaOk = VerifyRecaptcha.verify(this.reCaptchaSettings.getSecret(), gRecaptchaResponse);
			if (!captchaOk || result.hasErrors()) {
				if (!captchaOk) {
					result.addError(new FieldError("recoverPassForm", "captcha",
							this.messageSource.getMessage("captcha.erroneo", null, LocaleContextHolder.getLocale())));
				}
				usuario.setPass("");
				request.setAttribute("loginForm", usuario);
				return "redirect:/loginForm";
			} else {
				final CambioEnTabla ct = new CambioEnTabla();
				ct.setFec(Calendar.getInstance());
				ct.setUsu(0);
				ct.setIp(request.getRemoteAddr());

				final String login = usuario.getMail().toLowerCase();
				final String pass = usuario.getPass();
				final UsuarioPOJO loggedUser = this.usuarioSrv.login(login, pass, ct);
				request.getSession().setAttribute(DefaultGlobalNames.USERSESSION, loggedUser);
				return "redirect:/mainLogged.action";
			}
		} catch (final ServicioException e) {
			usuario.setPass("");
			request.setAttribute("loginForm", usuario);
            request.setAttribute("msg",
                    this.messageSource.getMessage(e.getMessage(), e.getArgs(), LocaleContextHolder.getLocale()));
			return "loginForm";
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	// LOGOUT -----------------------------------------------------------------

	@RequestMapping(value = "/logout.action", method = RequestMethod.GET)
	public String logout(final Model model, final HttpServletRequest request) {
		log.debug("UsuariosController.logout()");
		request.getSession().invalidate();
		return "redirect:/main.action";
	}

	// RECOVER PASS -----------------------------------------------------------

	@RequestMapping(value = "/recoverPassForm.action", method = RequestMethod.GET)
	public String recoverPassFormGet(final Model model, final HttpServletRequest request) {
		log.debug("UsuariosController.recoverPassFormGet()");
		final UserFormPOJO usuario = new UserFormPOJO();
		usuario.setMail("");
		model.addAttribute("recoverPassForm", usuario);
		return "recoverPassForm";
	}

	@RequestMapping(value = "/recoverPassForm.action", method = RequestMethod.POST)
	public String recoverPassFormPost(@ModelAttribute("recoverPassForm") @Validated final UserFormPOJO usuario,
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
					result.addError(new FieldError("recoverPassForm", "captcha",
							this.messageSource.getMessage("captcha.erroneo", null, LocaleContextHolder.getLocale())));
				}
				return "recoverPassForm";
			} else {
				final CambioEnTabla ct = new CambioEnTabla();
				ct.setFec(Calendar.getInstance());
				ct.setUsu(0);
				ct.setIp(request.getRemoteAddr());
				ct.setLocale(LocaleContextHolder.getLocale());

				final UsuarioPOJO u = this.usuarioSrv.recoverPassInit(usuario.getMail(), ct);
				final MensajeWeb msg = new MensajeWeb();
				msg.setVar("msg");
				msg.setKey("recoverPassForm.url.sent");
				msg.setArgs(new Object[] { u.getMail() });
				msg.setTilesMapping("mensaje");
				request.getSession().setAttribute("mensajeObj", msg);

				return "redirect:/mensaje.action";
			}

		} finally {

			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}

		}


	}

	// RESET PASS -------------------------------------------------------------

	@RequestMapping(value = "/resetPassForm.action", method = RequestMethod.GET)
	public String resetPassFormFormGet(final Model model, final HttpServletRequest request) {

		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}

		try {
			final Integer idUsuario = Integer.parseInt(this.criptografia.desencriptar(request.getParameter("u")));
			final String codigoRecuperacion = request.getParameter("c");

			// Si el código de recuperación no existe es porque se ha hecho
			// alguna trapallada o ya se ha usado.
			if (this.usuarioSrv.existePeticionResetPassActiva(idUsuario, codigoRecuperacion)) {
				final UserFormPOJO usuario = new UserFormPOJO();
				usuario.setPass("");
				usuario.setClaveRepetida("");
				usuario.getPk().setIdUsuario(idUsuario);
				usuario.setCodigoRecuperacion(codigoRecuperacion);

				model.addAttribute("resetPassForm", usuario);
				return "resetPassForm";
			} else {
				throw new ServicioErrorException("usuarioParamsDao.notFound.recoverPassCode");
			}
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	@RequestMapping(value = "/resetPassForm.action", method = RequestMethod.POST)
	public String resetPassFormPost(@ModelAttribute("resetPassForm") @Validated final UserFormPOJO usuario,
			final BindingResult result, final ServletRequest servletRequest, final Model model,
			final RedirectAttributes redirectAttributes, final HttpServletRequest request) {

		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}

		try {
			final String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
			final boolean captchaOk = VerifyRecaptcha.verify(this.reCaptchaSettings.getSecret(), gRecaptchaResponse);
			if (!captchaOk || result.hasErrors()) {
				if (!captchaOk) {
					result.addError(new FieldError("resetPassForm", "captcha",
							this.messageSource.getMessage("captcha.erroneo", null, LocaleContextHolder.getLocale())));
				}
				if (result.hasErrors()) {
					model.addAttribute("resetPassForm", usuario);
				}
				return "resetPassForm";
			} else {
				final Integer idUsuario = usuario.getPk().getIdUsuario();
				final String codigoRecuperacion = usuario.getCodigoRecuperacion();
				final String newPass = usuario.getPass();

				final CambioEnTabla ct = new CambioEnTabla();
				ct.setFec(Calendar.getInstance());
				ct.setUsu(0);
				ct.setIp(request.getRemoteAddr());
				ct.setLocale(LocaleContextHolder.getLocale());

				this.usuarioSrv.recoverPassExec(idUsuario, codigoRecuperacion, newPass, ct);
				final MensajeWeb msg = new MensajeWeb();
				msg.setVar("msg");
				msg.setKey("resetPassForm.pass.ok");
				msg.setArgs(new Object[] {});
				msg.setTilesMapping("mensaje");
				request.getSession().setAttribute("mensajeObj", msg);

				return "redirect:/mensaje.action";
			}

//			final String remoteAddress = servletRequest.getRemoteAddr();
//			final ReCaptchaResponse reCaptchaResponse = this.reCaptcha.checkAnswer(remoteAddress, challangeField,
//					responseField);
//			if (!reCaptchaResponse.isValid()) {
//				redirectAttributes.addFlashAttribute("registerForm", usuario);
//				redirectAttributes.addFlashAttribute("msgErr",
//						this.messageSource.getMessage("captcha.erroneo", null, LocaleContextHolder.getLocale()));
//				return "redirect:/registerForm.action";
//			}

		} catch (final ServicioException e) {
			log.warn("", e);
			final MensajeWeb msg = new MensajeWeb();
			msg.setVar("msg");
			msg.setKey("resetPassForm.pass.err");
			msg.setArgs(new Object[] {});
			msg.setTilesMapping("mensaje");
			request.getSession().setAttribute("mensajeObj", msg);

			return "redirect:/mensaje.action";
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	// REGISTER FORM ----------------------------------------------------------

	@RequestMapping(value = "/registerForm.action", method = RequestMethod.GET)
	public String registerFormGet(final Model model, @ModelAttribute("registerForm") final UserFormPOJO usuario,
			@ModelAttribute("msgErr") final String msgErr, final HttpServletRequest request) {
		log.debug("UsuariosController.registerFormGet()");

		if (Validadores.estaVacia(usuario.getMail())) {
			final UserFormPOJO u = new UserFormPOJO();
			u.setMail("");
			u.setNick("");
			u.setPass("");
			u.setClaveRepetida("");

			model.addAttribute("registerForm", u);
		} else {
			model.addAttribute("registerForm", usuario);
		}

		if (!Validadores.estaVacia(msgErr)) {
			request.setAttribute("msgPopUp", msgErr);
		}
		return "registerForm";
	}

	@RequestMapping(value = "/registerForm.action", method = RequestMethod.POST)
	public String registerFormPost(@ModelAttribute("registerForm") @Validated final UserFormPOJO usuario,
			final BindingResult result, final ServletRequest servletRequest, final Model model,
			final RedirectAttributes redirectAttributes, final HttpServletRequest request) {

		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}

		try {
			final String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
			final boolean captchaOk = VerifyRecaptcha.verify(this.reCaptchaSettings.getSecret(), gRecaptchaResponse);
			if (!captchaOk || result.hasErrors()) {
				if (!captchaOk) {
					result.addError(new FieldError("registerForm", "captcha",
							this.messageSource.getMessage("captcha.erroneo", null, LocaleContextHolder.getLocale())));
				}
				if (result.hasErrors()) {
					model.addAttribute("registerForm", usuario);
				}
				return "registerForm";
			} else {
				final Calendar ahora = Calendar.getInstance();
				final UsuarioPOJO u = new UsuarioPOJO();
				u.getPk().setIdUsuario(0);
				u.setMail(usuario.getMail());
				u.setNick(usuario.getNick());
				u.setPass(usuario.getPass());
				u.setIp(request.getRemoteAddr());
				u.setFallosLogin(0);
				u.setIdUsuarioAlta(0);
				u.setFecAlta(ahora);
				u.setIdUsuarioModif(0);
				u.setFecModif(ahora);

				this.usuarioSrv.creaUsuario(u);
				final MensajeWeb msg = new MensajeWeb();
				msg.setVar("msg");
				msg.setKey("registerForm.crearUsuario.ok");
				msg.setArgs(new Object[] { u.getMail() });
				msg.setTilesMapping("mensaje");
				request.getSession().setAttribute("mensajeObj", msg);

				return "redirect:/mensaje.action";
			}
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	// MY PROFILE -------------------------------------------------------------

	@RequestMapping(value = "/myProfileForm.action", method = RequestMethod.GET)
	public String myProfileGet(final Model model, final HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}
		try {
            final UserFormPOJO usuario = new UserFormPOJO(
                    (UsuarioPOJO) request.getSession().getAttribute(DefaultGlobalNames.USERSESSION));

            model.addAttribute("myProfileForm", usuario);
			return "myProfileForm";
		} finally {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
		}
	}

	@RequestMapping(value = "/myProfileForm.action", method = RequestMethod.POST)
    public String myProfileFormPost(@ModelAttribute("myProfileForm") @Validated final UserFormPOJO usuario,
            final BindingResult result, final Model model, final RedirectAttributes redirectAttributes,
            final HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
		}
		try {
            usuario.getPk().setIdUsuario(
                    ((UsuarioPOJO) request.getSession().getAttribute(DefaultGlobalNames.USERSESSION)).getPk().getIdUsuario());

			final CambioEnTabla ct = new CambioEnTabla();
			ct.setFec(Calendar.getInstance());
			ct.setUsu(usuario.getPk().getIdUsuario());
			ct.setIp(request.getRemoteAddr());
			ct.setLocale(LocaleContextHolder.getLocale());

			final UsuarioPOJO u = this.usuarioSrv.actualiza(usuario, ct);

			// Actualizamos ahora la información del usuario en sesión.
			request.getSession().setAttribute(DefaultGlobalNames.USERSESSION, u);

			final MensajeWeb msg = new MensajeWeb();
			msg.setVar("msg");
			msg.setKey("myProfileForm.guardar.ok");
			msg.setArgs(new Object[] {});
			msg.setTilesMapping("mensaje");
			msg.setUrlVolver("main.action");
			request.getSession().setAttribute("mensajeObj", msg);

			return "redirect:/mensaje.action";
		} finally {
			log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
		}
	}

	/*
     * private void populateDefaultModel(Model model) { List<String> frameworksList
     * = new ArrayList<String>(); frameworksList.add("Spring MVC" );
     * frameworksList.add("Struts 2"); frameworksList.add("JSF 2");
	 * frameworksList.add("GWT"); frameworksList.add("Play");
	 * frameworksList.add("Apache Wicket"); model.addAttribute("frameworkList",
	 * frameworksList);
	 *
	 * Map<String, String> skill = new LinkedHashMap<String, String>();
	 * skill.put("Hibernate", "Hibernate"); skill.put("Spring", "Spring");
	 * skill.put("Struts", "Struts"); skill.put("Groovy", "Groovy");
     * skill.put("Grails", "Grails"); model.addAttribute("javaSkillList", skill);
	 *
	 * List<Integer> numbers = new ArrayList<Integer>(); numbers.add(1);
	 * numbers.add(2); numbers.add(3); numbers.add(4); numbers.add(5);
	 * model.addAttribute("numberList", numbers);
	 *
	 * Map<String, String> country = new LinkedHashMap<String, String>();
	 * country.put("US", "United Stated"); country.put("CN", "China");
	 * country.put("SG", "Singapore"); country.put("MY", "Malaysia");
	 * model.addAttribute("countryList", country); }
	 */
}
