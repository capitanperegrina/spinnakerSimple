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
import com.capitanperegrina.common.utils.StackTraceUtil;
import com.spinnakersimple.modelo.dto.ContactoDTO;
import com.spinnakersimple.modelo.util.SpinnakerSimpleGlobals;
import com.spinnakersimple.util.LegalStuffUtil;
import com.spinnakersimple.util.LegalStuffUtil.FinalidadesLegales;
import com.spinnakersimple.util.tablasayuda.Parametrizacion;

/**
 * The Class ContactoMailHelper.
 */
@Component("contactoMailHelper")
public class ContactoMailHelper {

	/** The log. */
	static Logger log = Logger.getLogger(ConsultaVelaMailHelper.class);

	/** The emailer. */
	@Autowired
	private Emailer emailer;

	@Autowired
	private Parametrizacion parametrizacion;

	/** The message source. */
	@Autowired
	MessageSource messageSource;

	/**
	 * Envia correo contacto.
	 *
	 * @param c      the c
	 * @param locale the locale
	 */
	public void enviaCorreoContacto(final ContactoDTO c, final Locale locale) {
		log.debug(DefaultGlobalNames.PREFIJO_HELPER_INI + StackTraceUtil.getCallerInfo());
		try {
			final Locale localeEs = new Locale("es");

			// Correo para la administración
			this.emailer.sendEmail(this.emailer.getRemitentePorDefecto(), this.parametrizacion.getMailAdmin(), generaAsuntoAdministracionMail(localeEs, c), generaTextoAdministracionMail(localeEs, c), Boolean.TRUE, null, c.getEmail());

			// Correo para el interesado
			if (this.parametrizacion.isAppEnProduccion()) {
				this.emailer.sendEmail(this.emailer.getRemitentePorDefecto(), c.getEmail(), generaAsuntoContactoMail(locale), generaTextoContactoMail(locale, c), Boolean.TRUE, null, null);
			} else {
				this.emailer.sendEmail(this.emailer.getRemitentePorDefecto(), this.parametrizacion.getMailAdmin(), generaAsuntoContactoMail(locale), generaTextoContactoMail(locale, c), Boolean.TRUE, null, null);
			}

		} catch (final ServicioException e) {
			log.error("", e);
			throw e.toServicioErrorException();
		} finally {
			log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
		}
	}

	/**
	 * Genera asunto administracion mail.
	 *
	 * @param locale the locale
	 * @return the string
	 */
	private String generaAsuntoAdministracionMail(final Locale locale, final ContactoDTO c) {
		final StringBuilder sb = new StringBuilder();
		if (!this.parametrizacion.isAppEnProduccion()) {
			sb.append(SpinnakerSimpleGlobals.PREFIJO_ASUNTO_MAILS_NO_PRODUCCION);
		}
		sb.append(SpinnakerSimpleGlobals.PREFIJO_ASUNTO_MAILS + this.messageSource.getMessage("contactoMailHelper.generaAsuntoAdministracionMail", null, locale) + " " + c.getNombre());
		return sb.toString();
	}

	/**
	 * Genera asunto contacto mail.
	 *
	 * @param locale the locale
	 * @return the string
	 */
	private String generaAsuntoContactoMail(final Locale locale) {
		final StringBuilder sb = new StringBuilder();
		if (!this.parametrizacion.isAppEnProduccion()) {
			sb.append(SpinnakerSimpleGlobals.PREFIJO_ASUNTO_MAILS_NO_PRODUCCION);
		}
		sb.append(SpinnakerSimpleGlobals.PREFIJO_ASUNTO_MAILS + this.messageSource.getMessage("contactoMailHelper.generaAsuntoContactoMail", null, locale));
		return sb.toString();
	}

	/**
	 * Genera texto administracion mail.
	 *
	 * @param locale the locale
	 * @param c      the c
	 * @return the string
	 */
	private String generaTextoAdministracionMail(final Locale locale, final ContactoDTO c) {
		final StringBuilder sb = new StringBuilder();
		sb.append(StringEscapeUtils.escapeHtml(this.messageSource.getMessage("contactoMailHelper.generaTextoAdministracionMail.intro", null, locale)) + ":<br/>");
		sb.append("<br/>");
		sb.append("<div style=\"margin-left: 2em\">");
		sb.append("<span style=\"font-weight: bold;\">" + StringEscapeUtils.escapeHtml(this.messageSource.getMessage("contactoMailHelper.generaTextoAdministracionMail.remitente", null, locale)) + ":</span> %NOMBRE_REMITENTE%<br/>");
		sb.append("<span style=\"font-weight: bold;\">" + StringEscapeUtils.escapeHtml(this.messageSource.getMessage("contactoMailHelper.generaTextoAdministracionMail.email", null, locale)) + ":</span> %MAIL_REMITENTE%<br/>");
		sb.append("<span style=\"font-weight: bold;\">" + StringEscapeUtils.escapeHtml(this.messageSource.getMessage("contactoMailHelper.generaTextoAdministracionMail.consulta", null, locale)) + ":</span> %CONSULTA%<br/>");
		sb.append("</div>");

		String ret = sb.toString();
		ret = ret.replace("%NOMBRE_REMITENTE%", StringEscapeUtils.escapeHtml(c.getNombre()));
		ret = ret.replace("%MAIL_REMITENTE%", StringEscapeUtils.escapeHtml(c.getEmail()));
		ret = ret.replace("%CONSULTA%", StringEscapeUtils.escapeHtml(c.getConsulta()));

		return ret;
	}

	/**
	 * Genera texto contacto mail.
	 *
	 * @param locale the locale
	 * @param c      the c
	 * @return the string
	 */
	private String generaTextoContactoMail(final Locale locale, final ContactoDTO c) {
		final StringBuilder sb = new StringBuilder();

		sb.append("<a href=\"" + this.parametrizacion.getUrlBase() + "\"><img style=\"width: 400px;\" src=\"" + this.parametrizacion.getUrlLogo() + "\" alt=\"http://spinnakersimple.com\"></a><br/>");
		sb.append("<br/>");
		sb.append("" + StringEscapeUtils.escapeHtml(this.messageSource.getMessage("contactoMailHelper.generaTextoContactoMail.hola", null, locale)) + " %NOMBRE%<br/>");
		sb.append("<br/>");
		sb.append(StringEscapeUtils.escapeHtml(this.messageSource.getMessage("contactoMailHelper.generaTextoContactoMail.into", null, locale)) + ":<br/>");
		sb.append("<br/>");
		sb.append("" + StringEscapeUtils.escapeHtml(this.messageSource.getMessage("contactoMailHelper.generaTextoContactoMail.consulta", null, locale)) + ": %CONSULTA%<br/>");
		sb.append(DefaultGlobalNames.HTML_BR);
		sb.append(DefaultGlobalNames.HTML_BR);
		sb.append(LegalStuffUtil.generaClausulaPrivacidad(FinalidadesLegales.CONTACTO, locale, this.parametrizacion.getUrlBase()));
		sb.append(DefaultGlobalNames.HTML_BR);
		sb.append("&copy; <a href=\"" + this.parametrizacion.getUrlBase() + "\">http://www.spinnakerSimple.com</a>");

		String ret = sb.toString();
		ret = ret.replace("%NOMBRE%", StringEscapeUtils.escapeHtml(c.getNombre()));
		ret = ret.replace("%CONSULTA%", StringEscapeUtils.escapeHtml(c.getConsulta()));

		return ret;
	}

}
