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
import com.capitanperegrina.common.utils.StackTraceUtil;
import com.spinnakersimple.modelo.entidad.AnuncioPOJO;
import com.spinnakersimple.modelo.entidad.CompradoresPOJO;
import com.spinnakersimple.modelo.entidad.UsuarioPOJO;
import com.spinnakersimple.modelo.util.SpinnakerSimpleGlobals;
import com.spinnakersimple.util.LegalStuffUtil;
import com.spinnakersimple.util.LegalStuffUtil.FinalidadesLegales;
import com.spinnakersimple.util.tablasayuda.Parametrizacion;

/**
 * The Class ConsultaVelaMailHelper.
 */
@Component
public class ConsultaVelaMailHelper {

	/** The log. */
	static Logger log = Logger.getLogger(ConsultaVelaMailHelper.class);

	/** The emailer. */
	@Autowired
	private Emailer emailer;

	/** The criptografia. */
	@Autowired
	Criptografia criptografia;

	@Autowired
	private Parametrizacion parametrizacion;

	/** The message source. */
	@Autowired
	MessageSource messageSource;

	/**
	 * Envia correo consulta vela.
	 *
	 * @param localeVendedor  the locale vendedor
	 * @param localeComprador the locale comprador
	 * @param comprador       the comprador
	 * @param anuncio         the anuncio
	 * @param usuario         the usuario
	 */
	public void enviaCorreoConsultaVela(final Locale localeVendedor, final Locale localeComprador,
					final CompradoresPOJO comprador, final AnuncioPOJO anuncio, final UsuarioPOJO usuario) {
		log.debug(DefaultGlobalNames.PREFIJO_HELPER_INI + StackTraceUtil.getCallerInfo());
		try {
			String mailComprador = comprador.getMail();
			String mailVendedor = usuario.getMail();
			if (!this.parametrizacion.isAppEnProduccion()) {
				mailComprador = this.parametrizacion.getMailAdmin();
				mailVendedor = this.parametrizacion.getMailAdmin();
			}

			// Correo para el comprador
			this.emailer.sendEmail(this.emailer.getRemitentePorDefecto(), mailComprador, generaAsuntoCompradorMail(localeComprador), generaTextoCompradorMail(localeComprador, comprador, anuncio, usuario), Boolean.TRUE, null, null);

			// Correo para el vendedor
			this.emailer.sendEmail(this.emailer.getRemitentePorDefecto(), mailVendedor, generaAsuntoVendedorMail(localeVendedor), generaTextoVendedorMail(localeVendedor, comprador, anuncio, usuario), Boolean.TRUE, null, comprador.getMail());
		} catch (final ServicioException e) {
			log.error("", e);
			throw e.toServicioErrorException();
		} finally {
			log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
		}
	}

	/**
	 * Genera asunto comprador mail.
	 *
	 * @param locale the locale
	 * @return the string
	 */
	private String generaAsuntoCompradorMail(final Locale locale) {
		log.debug(DefaultGlobalNames.PREFIJO_HELPER_INI + StackTraceUtil.getCallerInfo());
		final StringBuilder sb = new StringBuilder();
		if (!this.parametrizacion.isAppEnProduccion()) {
			sb.append(SpinnakerSimpleGlobals.PREFIJO_ASUNTO_MAILS_NO_PRODUCCION);
		}
		try {
			sb.append(SpinnakerSimpleGlobals.PREFIJO_ASUNTO_MAILS + this.messageSource.getMessage("consultaVelaMailHelper.generaAsuntoCompradorMail", null, locale));
			return sb.toString();
		} finally {
			log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
		}
	}

	/**
	 * Genera asunto vendedor mail.
	 *
	 * @param locale the locale
	 * @return the string
	 */
	private String generaAsuntoVendedorMail(final Locale locale) {
		log.debug(DefaultGlobalNames.PREFIJO_HELPER_INI + StackTraceUtil.getCallerInfo());
		final StringBuilder sb = new StringBuilder();
		if (!this.parametrizacion.isAppEnProduccion()) {
			sb.append(SpinnakerSimpleGlobals.PREFIJO_ASUNTO_MAILS_NO_PRODUCCION);
		}
		try {
			sb.append(SpinnakerSimpleGlobals.PREFIJO_ASUNTO_MAILS + this.messageSource.getMessage("consultaVelaMailHelper.generaAsuntoVendedorMail", null, locale));
			return sb.toString();
		} finally {
			log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
		}
	}

	/**
	 * Genera texto comprador mail.
	 *
	 * @param locale    the locale
	 * @param comprador the comprador
	 * @param anuncio   the anuncio
	 * @param usuario   the usuario
	 * @return the string
	 */
	private String generaTextoCompradorMail(final Locale locale, final CompradoresPOJO comprador,
					final AnuncioPOJO anuncio, final UsuarioPOJO usuario) {
		log.debug(DefaultGlobalNames.PREFIJO_HELPER_INI + StackTraceUtil.getCallerInfo());
		final StringBuilder sb = new StringBuilder();
		try {
			sb.append("<a href=\"" + this.parametrizacion.getUrlBase() + "\"><img style=\"width: 400px;\" src=\"" + this.parametrizacion.getUrlLogo() + "\" alt=\"" + this.parametrizacion.getUrlBase() + "\"></a><br/>");
			sb.append("<br/>");
			sb.append(StringEscapeUtils.escapeHtml(this.messageSource.getMessage("consultaVelaMailHelper.comun.hola", null, locale)) + " %COMPRADOR%.<br/>\n");
			sb.append("<br/>\n");
			sb.append(StringEscapeUtils.escapeHtml(this.messageSource.getMessage("consultaVelaMailHelper.generaTextoCompradorMail.textoIntro", null, locale)) + "<br/>\n");
			sb.append("<br/>\n");
			sb.append("<span style=\"font-weight: bold;\">" + StringEscapeUtils.escapeHtml(this.messageSource.getMessage("consultaVelaMailHelper.generaTextoCompradorMail.datosConsultados", null, locale)) + ":</span><br/>\n");
			sb.append("<br/>\n");
			sb.append("<div style=\"margin-left: 2em\">\n");
			sb.append("<span style=\"font-weight: bold;\">" + StringEscapeUtils.escapeHtml(this.messageSource.getMessage("consultaVelaMailHelper.comun.vela", null, locale)) + ":</span> <a href=\"" + this.parametrizacion.getUrlBase() + "/verVela.action?ida=%ID_ANUNCIO_CODIFICADO%\">%TITULO_ANUNCIO%</a><br/>\n");
			sb.append("<br>\n");
			sb.append("<span style=\"font-weight: bold;\">" + StringEscapeUtils.escapeHtml(this.messageSource.getMessage("consultaVelaMailHelper.comun.nombre", null, locale)) + ":</span> %NOMBRE_VENDEDOR%<br/>\n");
			sb.append("<span style=\"font-weight: bold;\">" + StringEscapeUtils.escapeHtml(this.messageSource.getMessage("consultaVelaMailHelper.comun.mail", null, locale)) + ":</span> %MAIL_VENDEDOR%<br/>\n");
			sb.append("<span style=\"font-weight: bold;\">" + StringEscapeUtils.escapeHtml(this.messageSource.getMessage("consultaVelaMailHelper.comun.telf", null, locale)) + ":</span> %TELEFONO_VENDEDOR%<br/>\n");
			sb.append("<span style=\"font-weight: bold;\">" + StringEscapeUtils.escapeHtml(this.messageSource.getMessage("consultaVelaMailHelper.comun.observaciones", null, locale)) + ":</span> %OBSERVACIONES_COMPRADOR%<br/>\n");
			sb.append("</div><br/>\n");
			sb.append(DefaultGlobalNames.HTML_BR);
			sb.append(DefaultGlobalNames.HTML_BR);
			sb.append(LegalStuffUtil.generaClausulaPrivacidad(FinalidadesLegales.COMPRA, locale, this.parametrizacion.getUrlBase()));
			sb.append(DefaultGlobalNames.HTML_BR);
			sb.append("&copy; <a href=\"" + this.parametrizacion.getUrlBase() + "\">http://www.spinnakerSimple.com</a>");

			String ret = sb.toString();
			ret = ret.replace("%COMPRADOR%", StringEscapeUtils.escapeHtml(comprador.getNombre()));
			ret = ret.replace("%ID_ANUNCIO_CODIFICADO%", this.criptografia.codificaParaUrl(anuncio.getIdAnuncio().toString()));
			ret = ret.replace("%TITULO_ANUNCIO%", StringEscapeUtils.escapeHtml(anuncio.getTituloAnuncio()));
			ret = ret.replace("%NOMBRE_VENDEDOR%", StringEscapeUtils.escapeHtml(usuario.getNombre() + " " + usuario.getApellidos()));
			ret = ret.replace("%MAIL_VENDEDOR%", StringEscapeUtils.escapeHtml(usuario.getMail()));
			ret = ret.replace("%TELEFONO_VENDEDOR%", StringEscapeUtils.escapeHtml(usuario.getMovil()));
			ret = ret.replace("%OBSERVACIONES_COMPRADOR%", StringEscapeUtils.escapeHtml(comprador.getObservaciones()));

			return ret;
		} finally {
			log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
		}
	}

	/**
	 * Genera texto vendedor mail.
	 *
	 * @param locale    the locale
	 * @param comprador the comprador
	 * @param anuncio   the anuncio
	 * @param usuario   the usuario
	 * @return the string
	 */
	private String generaTextoVendedorMail(final Locale locale, final CompradoresPOJO comprador,
					final AnuncioPOJO anuncio, final UsuarioPOJO usuario) {
		log.debug(DefaultGlobalNames.PREFIJO_HELPER_INI + StackTraceUtil.getCallerInfo());
		final StringBuilder sb = new StringBuilder();
		try {
			sb.append("<a href=\"" + this.parametrizacion.getUrlBase() + "\"><img style=\"width: 400px;\" src=\"" + this.parametrizacion.getUrlLogo() + "\" alt=\"" + this.parametrizacion.getUrlBase() + "\"></a><br/>");
			sb.append("<br/>");
			sb.append(StringEscapeUtils.escapeHtml(this.messageSource.getMessage("consultaVelaMailHelper.comun.hola", null, locale)) + " %USUARIO%.<br/>\n");
			sb.append("<br/>\n");
			sb.append(StringEscapeUtils.escapeHtml(this.messageSource.getMessage("consultaVelaMailHelper.generaTextoVendedorMail.textoIntro", null, locale)) + "<br/>\n");
			sb.append("<br/>\n");
			sb.append("<span style=\"font-weight: bold;\">" + StringEscapeUtils.escapeHtml(this.messageSource.getMessage("consultaVelaMailHelper.generaTextoVendedorMail.datosConsultados", null, locale)) + ":</span><br/>\n");
			sb.append("<br/>\n");
			sb.append("<div style=\"margin-left: 2em\">\n");
			sb.append("<span style=\"font-weight: bold;\">" + StringEscapeUtils.escapeHtml(this.messageSource.getMessage("consultaVelaMailHelper.comun.vela", null, locale)) + ":</span> <a href=\"" + this.parametrizacion.getUrlBase() + "/verVela.action?ida=%ID_ANUNCIO_CODIFICADO%\">%TITULO_ANUNCIO%</a><br/>\n");
			sb.append("<br/>\n");
			sb.append("<span style=\"font-weight: bold;\">" + StringEscapeUtils.escapeHtml(this.messageSource.getMessage("consultaVelaMailHelper.comun.nombre", null, locale)) + ":</span> %NOMBRE_COMPRADOR%<br/>\n");
			sb.append("<span style=\"font-weight: bold;\">" + StringEscapeUtils.escapeHtml(this.messageSource.getMessage("consultaVelaMailHelper.comun.mail", null, locale)) + ":</span> %MAIL_COMPRADOR%<br/>\n");
			sb.append("<span style=\"font-weight: bold;\">" + StringEscapeUtils.escapeHtml(this.messageSource.getMessage("consultaVelaMailHelper.comun.telf", null, locale)) + ":</span> %TELEFONO_COMPRADOR%<br/>\n");
			sb.append("<span style=\"font-weight: bold;\">" + StringEscapeUtils.escapeHtml(this.messageSource.getMessage("consultaVelaMailHelper.comun.observaciones", null, locale)) + ":</span> %OBSERVACIONES_COMPRADOR%<br/>\n");
			sb.append("</div><br/>\n");
			sb.append(DefaultGlobalNames.HTML_BR);
			sb.append(DefaultGlobalNames.HTML_BR);
			sb.append(LegalStuffUtil.generaClausulaPrivacidad(FinalidadesLegales.COMPRA, locale, this.parametrizacion.getUrlBase()));
			sb.append(DefaultGlobalNames.HTML_BR);
			sb.append("&copy; <a href=\"" + this.parametrizacion.getUrlBase() + "\">http://www.spinnakerSimple.com</a>");

			String ret = sb.toString();
			ret = ret.replace("%USUARIO%", StringEscapeUtils.escapeHtml(usuario.getNombre() + " " + usuario.getApellidos()));
			ret = ret.replace("%ID_ANUNCIO_CODIFICADO%", this.criptografia.codificaParaUrl(anuncio.getIdAnuncio().toString()));
			ret = ret.replace("%TITULO_ANUNCIO%", StringEscapeUtils.escapeHtml(anuncio.getTituloAnuncio()));
			ret = ret.replace("%NOMBRE_COMPRADOR%", StringEscapeUtils.escapeHtml(comprador.getNombre()));
			ret = ret.replace("%MAIL_COMPRADOR%", StringEscapeUtils.escapeHtml(comprador.getMail()));
			ret = ret.replace("%TELEFONO_COMPRADOR%", StringEscapeUtils.escapeHtml(comprador.getTelefono()));
			ret = ret.replace("%OBSERVACIONES_COMPRADOR%", StringEscapeUtils.escapeHtml(comprador.getObservaciones()));

			return ret;
		} finally {
			log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
		}
	}
}
