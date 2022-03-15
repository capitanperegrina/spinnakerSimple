package com.spinnakersimple.helpers;

import java.util.Locale;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.exceptions.ServicioException;
import com.capitanperegrina.common.net.mail.Emailer;
import com.capitanperegrina.common.seguridad.Criptografia;
import com.capitanperegrina.common.utils.Fecha;
import com.capitanperegrina.common.utils.StackTraceUtil;
import com.spinnakersimple.modelo.entidad.UsuarioPOJO;
import com.spinnakersimple.modelo.util.SpinnakerSimpleGlobals;
import com.spinnakersimple.util.LegalStuffUtil;
import com.spinnakersimple.util.LegalStuffUtil.FinalidadesLegales;
import com.spinnakersimple.util.tablasayuda.Parametrizacion;

/**
 * The Class UsuarioMailHelper.
 */
@Component("usuarioMailHelper")
public class UsuarioMailHelper {

	/** The log. */
	static Logger log = Logger.getLogger(UsuarioMailHelper.class);

	/** The message source. */
	@Autowired
	MessageSource messageSource;

	/** The emailer. */
	@Autowired
	private Emailer emailer;

	/** The criptografia. */
	@Autowired
	Criptografia criptografia;

	@Autowired
	private Parametrizacion parametrizacion;

	public void enviaCorreoResetPass(final UsuarioPOJO usuario) {
		log.debug(DefaultGlobalNames.PREFIJO_HELPER_INI + StackTraceUtil.getCallerInfo());
		try {
			final Locale localeUsuario = new Locale(usuario.getLang());

			// Usuario
			if (this.parametrizacion.isAppEnProduccion()) {
				this.emailer.sendEmail(this.emailer.getRemitentePorDefecto(), usuario.getMail(), generaAsuntoResetPass(localeUsuario), generaTextoResetPass(localeUsuario, usuario), Boolean.TRUE, null, null);
			} else {
				this.emailer.sendEmail(this.emailer.getRemitentePorDefecto(), this.parametrizacion.getMailAdmin(), generaAsuntoResetPass(localeUsuario), generaTextoResetPass(localeUsuario, usuario), Boolean.TRUE, null, null);
			}


		} catch (final ServicioException e) {
			log.error("", e);
			throw e.toServicioErrorException();
		} finally {
			log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
		}
	}

	/**
	 * Envia correo alta usuario.
	 *
	 * @param usuario the usuario
	 */
	public void enviaCorreosAltaUsuario(final UsuarioPOJO usuario) {
		log.debug(DefaultGlobalNames.PREFIJO_HELPER_INI + StackTraceUtil.getCallerInfo());
		try {
			final Locale localeUsuario = new Locale(usuario.getLang());
			final Locale localeAdmin = new Locale("es");

			// Usuario
			if (this.parametrizacion.isAppEnProduccion()) {
				this.emailer.sendEmail(this.emailer.getRemitentePorDefecto(), usuario.getMail(), generaAsuntoCorreoAltaUsuario(localeUsuario), generaTextoCorreoAltaUsuario(localeUsuario, usuario), Boolean.TRUE, null, null);
			} else {
				this.emailer.sendEmail(this.emailer.getRemitentePorDefecto(), this.parametrizacion.getMailAdmin(), generaAsuntoCorreoAltaUsuario(localeUsuario), generaTextoCorreoAltaUsuario(localeUsuario, usuario), Boolean.TRUE, null, null);
			}

			// Administrador
			this.emailer.sendEmail(this.emailer.getRemitentePorDefecto(), this.parametrizacion.getMailAdmin(), generaAsuntoCorreoAltaUsuarioAdministrador(localeAdmin), generaTextoCorreoAltaUsuarioAdministrador(localeAdmin, usuario), Boolean.TRUE, null, null);

		} catch (final ServicioException e) {
			log.error("", e);
			throw e.toServicioErrorException();
		} finally {
			log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
		}
	}

	/**
	 * Envia correo cambio clave usuario.
	 *
	 * @param usuario the usuario
	 */
	public void enviaCorreosCambioClaveUsuario(final UsuarioPOJO usuario) {
		log.debug(DefaultGlobalNames.PREFIJO_HELPER_INI + StackTraceUtil.getCallerInfo());
		try {
			final Locale localeUsuario = new Locale(usuario.getLang());
			final Locale localeAdmin = new Locale("es");

			// Usuario
			if (this.parametrizacion.isAppEnProduccion()) {
				this.emailer.sendEmail(this.emailer.getRemitentePorDefecto(), usuario.getMail(), generaAsuntoCorreoCambioClaveUsuario(localeUsuario), generaTextoCorreoCambioClaveUsuario(localeUsuario, usuario), Boolean.TRUE, null, null);
			} else {
				this.emailer.sendEmail(this.emailer.getRemitentePorDefecto(), this.parametrizacion.getMailAdmin(), generaAsuntoCorreoCambioClaveUsuario(localeUsuario), generaTextoCorreoCambioClaveUsuario(localeUsuario, usuario), Boolean.TRUE, null, null);
			}

			// Administrador
			this.emailer.sendEmail(this.emailer.getRemitentePorDefecto(), this.parametrizacion.getMailAdmin(), generaAsuntoCorreoCambioClaveUsuarioAdministrador(localeAdmin), generaTextoCorreoCambioClaveUsuarioAdministrador(localeAdmin, usuario), Boolean.TRUE, null, null);

		} catch (final ServicioException e) {
			log.error("", e);
			throw e.toServicioErrorException();
		} finally {
			log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
		}
	}

	// ASUNTOS
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Genera asunto correo alta usuario.
	 *
	 * @param locale the locale
	 * @return the string
	 */
	public String generaAsuntoCorreoAltaUsuario(final Locale locale) {
		log.debug(DefaultGlobalNames.PREFIJO_HELPER_INI + StackTraceUtil.getCallerInfo());
		final StringBuilder sb = new StringBuilder();
		try {
			if (!this.parametrizacion.isAppEnProduccion()) {
				sb.append(SpinnakerSimpleGlobals.PREFIJO_ASUNTO_MAILS_NO_PRODUCCION);
			}
			sb.append(SpinnakerSimpleGlobals.PREFIJO_ASUNTO_MAILS + this.messageSource.getMessage("usuarioMailHelper.generaAsuntoCorreoAltaUsuario", null, locale));
			return sb.toString();
		} finally {
			log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
		}
	}

	/**
	 * Genera asunto correo alta usuario administrador.
	 *
	 * @param locale the locale
	 * @return the string
	 */
	public String generaAsuntoCorreoAltaUsuarioAdministrador(final Locale locale) {
		log.debug(DefaultGlobalNames.PREFIJO_HELPER_INI + StackTraceUtil.getCallerInfo());
		final StringBuilder sb = new StringBuilder();
		try {
			if (!this.parametrizacion.isAppEnProduccion()) {
				sb.append(SpinnakerSimpleGlobals.PREFIJO_ASUNTO_MAILS_NO_PRODUCCION);
			}
			sb.append(SpinnakerSimpleGlobals.PREFIJO_ASUNTO_MAILS + this.messageSource.getMessage("usuarioMailHelper.generaAsuntoCorreoAltaUsuarioAdministrador", null, locale));
			return sb.toString();
		} finally {
			log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
		}
	}

	/**
	 * Genera asunto correo cambio clave usuario.
	 *
	 * @param locale the locale
	 * @return the string
	 */
	public String generaAsuntoCorreoCambioClaveUsuario(final Locale locale) {
		log.debug(DefaultGlobalNames.PREFIJO_HELPER_INI + StackTraceUtil.getCallerInfo());
		final StringBuilder sb = new StringBuilder();
		try {
			sb.append(SpinnakerSimpleGlobals.PREFIJO_ASUNTO_MAILS + this.messageSource.getMessage("usuarioMailHelper.generaAsuntoCorreoCambioClaveUsuario", null, locale));
			return sb.toString();
		} finally {
			log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
		}
	}

	/**
	 * Genera asunto correo cambio clave usuario administrador.
	 *
	 * @param locale the locale
	 * @return the string
	 */
	public String generaAsuntoCorreoCambioClaveUsuarioAdministrador(final Locale locale) {
		log.debug(DefaultGlobalNames.PREFIJO_HELPER_INI + StackTraceUtil.getCallerInfo());
		final StringBuilder sb = new StringBuilder();
		try {
			sb.append(SpinnakerSimpleGlobals.PREFIJO_ASUNTO_MAILS + this.messageSource.getMessage("usuarioMailHelper.generaAsuntoCorreoCambioClaveUsuarioAdministrador", null, locale));
			return sb.toString();
		} finally {
			log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
		}
	}

	public String generaAsuntoResetPass(final Locale locale) {
		log.debug(DefaultGlobalNames.PREFIJO_HELPER_INI + StackTraceUtil.getCallerInfo());
		final StringBuilder sb = new StringBuilder();
		try {
			sb.append(SpinnakerSimpleGlobals.PREFIJO_ASUNTO_MAILS + this.messageSource.getMessage("usuarioMailHelper.generaAsuntoResetPass", null, locale));
			return sb.toString();
		} finally {
			log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
		}
	}

	// TEXTOS
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Genera texto correo alta usuario.
	 *
	 * @param locale  the locale
	 * @param usuario the usuario
	 * @return the string
	 */
	public String generaTextoCorreoAltaUsuario(final Locale locale, final UsuarioPOJO usuario) {
		log.debug(DefaultGlobalNames.PREFIJO_HELPER_INI + StackTraceUtil.getCallerInfo());
		final StringBuilder sb = new StringBuilder();
		try {
			final String url = this.parametrizacion.getUrlBase() + "/resetPass.action?id=" + this.criptografia.codificaParaUrl(usuario.getIdUsuario().toString()) + "&pw=" + this.criptografia.codificaParaUrl(usuario.getPass()) + "&phm=" + this.criptografia.codificaParaUrl(Fecha.calendar2DateHourString(usuario.getFecModif(), '/', ':'));

			sb.append("<a href=\"" + this.parametrizacion.getUrlBase() + "\"><img style=\"width: 400px;\" src=\"" + this.parametrizacion.getUrlLogo() + "\" alt=\"" + this.parametrizacion.getUrlBase() + "\"></a><br/>");
			sb.append("<br/>");
			sb.append("<br/>\n");
			sb.append(StringEscapeUtils.escapeHtml(this.messageSource.getMessage("usuarioMailHelper.generaTextoCorreoAltaUsuario.10", new Object[] {}, locale).replace("%NOMBRE%", usuario.getNombre())) + "<br/>\n");
			sb.append("<br/>\n");
			sb.append(StringEscapeUtils.escapeHtml(this.messageSource.getMessage("usuarioMailHelper.generaTextoCorreoAltaUsuario.20", new Object[] {}, locale).replaceAll("%MAIL%", usuario.getMail())) + " <a href=\"" + url + "\" target=\"_blank\">" + url + "</a><br/>\n");
			sb.append("<br/>\n");
			sb.append(StringEscapeUtils.escapeHtml(this.messageSource.getMessage("usuarioMailHelper.generaTextoCorreoAltaUsuario.30", null, locale)) + "<br/>\n");
			sb.append(DefaultGlobalNames.HTML_BR);
			sb.append(DefaultGlobalNames.HTML_BR);
			sb.append(LegalStuffUtil.generaClausulaPrivacidad(FinalidadesLegales.USUARIOS, locale, this.parametrizacion.getUrlBase()));
			sb.append(DefaultGlobalNames.HTML_BR);
			sb.append("&copy; <a href=\"" + this.parametrizacion.getUrlBase() + "\">http://www.spinnakerSimple.com</a>");

			return sb.toString();
		} finally {
			log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
		}
	}

	/**
	 * Genera texto correo alta usuario administrador.
	 *
	 * @param locale  the locale
	 * @param usuario the usuario
	 * @return the string
	 */
	public String generaTextoCorreoAltaUsuarioAdministrador(final Locale locale, final UsuarioPOJO usuario) {
		log.debug(DefaultGlobalNames.PREFIJO_HELPER_INI + StackTraceUtil.getCallerInfo());
		final StringBuilder sb = new StringBuilder();
		try {
			sb.append(StringEscapeUtils.escapeHtml(this.messageSource.getMessage("usuarioMailHelper.generaTextoCorreoAltaUsuarioAdministrador.10", null, locale)) + " %USUARIO%<br/>");
			sb.append(DefaultGlobalNames.HTML_BR);
			sb.append("&copy; <a href=\"" + this.parametrizacion.getUrlBase() + "\">http://www.spinnakerSimple.com</a>");
			String ret = sb.toString();
			ret = ret.replace("%USUARIO%", StringEscapeUtils.escapeHtml(usuario.getNombre() + " " + usuario.getApellidos() + " (" + usuario.getMail() + ")"));
			return ret;
		} finally {
			log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
		}
	}

	/**
	 * Genera texto correo cambio clave usuario.
	 *
	 * @param locale  the locale
	 * @param usuario the usuario
	 * @return the string
	 */
	public String generaTextoCorreoCambioClaveUsuario(final Locale locale, final UsuarioPOJO usuario) {
		log.debug(DefaultGlobalNames.PREFIJO_HELPER_INI + StackTraceUtil.getCallerInfo());
		final StringBuilder sb = new StringBuilder();
		try {
			sb.append("<a href=\"" + this.parametrizacion.getUrlBase() + "\"><img style=\"width: 400px;\" src=\"" + this.parametrizacion.getUrlLogo() + "\" alt=\"" + this.parametrizacion.getUrlBase() + "\"></a><br/>");
			sb.append("<br/>");
			sb.append(StringEscapeUtils.escapeHtml(this.messageSource.getMessage("usuarioMailHelper.generaTextoCorreoCambioClaveUsuario.10", null, locale)) + " %NOMBRE%<br/>");
			sb.append("<br/>");
			sb.append(StringEscapeUtils.escapeHtml(this.messageSource.getMessage("usuarioMailHelper.generaTextoCorreoCambioClaveUsuario.20", null, locale)) + " %MAIL_ADMINISTRADOR%.<br/>");
			sb.append(DefaultGlobalNames.HTML_BR);
			sb.append(DefaultGlobalNames.HTML_BR);
			sb.append(LegalStuffUtil.generaClausulaPrivacidad(FinalidadesLegales.USUARIOS, locale, this.parametrizacion.getUrlBase()));
			sb.append(DefaultGlobalNames.HTML_BR);
			sb.append("&copy; <a href=\"" + this.parametrizacion.getUrlBase() + "\">http://www.spinnakerSimple.com</a>");

			String ret = sb.toString();
			ret = ret.replace("%NOMBRE%", StringEscapeUtils.escapeHtml(usuario.getNombre()));
			ret = ret.replace("%MAIL_ADMINISTRADOR%", StringEscapeUtils.escapeHtml(this.emailer.getRemitentePorDefecto()));
			return ret;

		} finally {
			log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
		}
	}

	/**
	 * Genera texto correo cambio clave usuario administrador.
	 *
	 * @param locale  the locale
	 * @param usuario the usuario
	 * @return the string
	 */
	public String generaTextoCorreoCambioClaveUsuarioAdministrador(final Locale locale, final UsuarioPOJO usuario) {
		log.debug(DefaultGlobalNames.PREFIJO_HELPER_INI + StackTraceUtil.getCallerInfo());
		final StringBuilder sb = new StringBuilder();
		try {
			sb.append(StringEscapeUtils.escapeHtml(this.messageSource.getMessage("usuarioMailHelper.generaTextoCorreoCambioClaveUsuarioAdministrador.10", null, locale)) + " %USUARIO%<br/>");
			sb.append(DefaultGlobalNames.HTML_BR);
			sb.append(DefaultGlobalNames.HTML_BR);
			sb.append("&copy; <a href=\"" + this.parametrizacion.getUrlBase() + "\">http://www.spinnakerSimple.com</a>");
			String ret = sb.toString();
			ret = ret.replace("%NOMBRE%", StringEscapeUtils.escapeHtml(usuario.getNombre() + " " + usuario.getApellidos() + "(" + usuario.getMail() + ")"));
			return ret;
		} finally {
			log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
		}
	}

	public String generaTextoResetPass(final Locale locale, final UsuarioPOJO usuario) {
		log.debug(DefaultGlobalNames.PREFIJO_HELPER_INI + StackTraceUtil.getCallerInfo());
		final StringBuilder sb = new StringBuilder();
		try {
			final String urlBase = this.parametrizacion.getUrlBase();

			sb.append("<a href=\"" + urlBase + "\"><img style=\"width: 400px;\" src=\"" + urlBase + "/imagenes/logo.png\" alt=\"" + urlBase + "\"></a><br/>");
			sb.append("<br/>");
			sb.append(StringEscapeUtils.escapeHtml(this.messageSource.getMessage("usuarioMailHelper.generaTextoResetPass.10", null, locale)) + "<br/>");
			sb.append("<br/>");
			sb.append(StringEscapeUtils.escapeHtml(this.messageSource.getMessage("usuarioMailHelper.generaTextoResetPass.20", null, locale)) + " <a href=\"%URL%\" target=\"_blank\">%URL%</a>.");
			sb.append("<br/>");
			sb.append(DefaultGlobalNames.HTML_BR);
			sb.append(DefaultGlobalNames.HTML_BR);
			sb.append(LegalStuffUtil.generaClausulaPrivacidad(FinalidadesLegales.USUARIOS, locale, this.parametrizacion.getUrlBase()));
			sb.append(DefaultGlobalNames.HTML_BR);
			sb.append("&copy; <a href=\"" + this.parametrizacion.getUrlBase() + "\">http://www.spinnakerSimple.com</a>");

			String ret = sb.toString();
			ret = ret.replace("%NOMBRE%", StringEscapeUtils.escapeHtml(usuario.getNombre()));
			ret = ret.replace("%URL%", urlBase + "/resetPass.action?lang=" + locale.getLanguage().toLowerCase() + "&id=" + this.criptografia.codificaParaUrl(usuario.getIdUsuario().toString()) + "&pw=" + this.criptografia.codificaParaUrl(usuario.getPass()) + "&phm=" + this.criptografia.codificaParaUrl(Fecha.calendar2DateHourString(usuario.getFecModif(), '/', ':')));
			return ret;
		} finally {
			log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
		}
	}
}
