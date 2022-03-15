package com.spinnakersimple.util;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class LegalStuffUtil {

	/** The message source. */
	@Autowired
	MessageSource tMessageSource;

	private static MessageSource messageSource;

	public enum FinalidadesLegales {
		USUARIOS, VENDER, CONTACTO, COMPRA
	}

	/**
	 *
	 */
	@PostConstruct
	public void init() {
		LegalStuffUtil.messageSource = this.tMessageSource;
	}

	public static String generaClausulaPrivacidad(final FinalidadesLegales finalidad, final Locale locale,
					final String urlBase) {

		final String urlPoliticaPrivacidad = urlBase + "/politicaPrivacidad.action";

		final StringBuilder sb = new StringBuilder();
		sb.append("<table style=\"width: 80%; margin-left: auto; margin-right: auto;\";>\n");
		sb.append("    <tr style=\"background-color: #337ab7; color:white;\">\n");
		sb.append("        <td colspan=\"2\" style=\"vertical-align: top; text-align: center; padding: .2em; padding-bottom: .5em;\">\n");
		sb.append("            <p>\n");
		sb.append("                <span style=\"font-size: 1.1em; font-weight: bold;\">" + StringEscapeUtils.escapeHtml4(StringEscapeUtils.unescapeHtml4(LegalStuffUtil.messageSource.getMessage("textolegal.mail.10", null, locale))) + "</span><br/>\n");
		sb.append("                <span style=\"font-size: .8em\">" + StringEscapeUtils.escapeHtml4(StringEscapeUtils.unescapeHtml4(LegalStuffUtil.messageSource.getMessage("textolegal.mail.20", null, locale))) + "</span>\n");
		sb.append("            </p>\n");
		sb.append("        </td>\n");
		sb.append("    </tr>\n");
		sb.append("    <tr>\n");
		sb.append("        <td style=\"vertical-align: top; padding-bottom: .5em;\">\n");
		sb.append("            <p style=\"font-weight: bold; color: #337ab7;\">" + StringEscapeUtils.escapeHtml4(StringEscapeUtils.unescapeHtml4(LegalStuffUtil.messageSource.getMessage("textolegal.mail.30", null, locale))) + "</p>\n");
		sb.append("        </td>\n");
		sb.append("        <td style=\"vertical-align: top; padding-bottom: .5em;\">\n");
		sb.append("            <p>\n");
		// sb.append("                Spinnaker Simple<br/>\n");
		sb.append("                " + StringEscapeUtils.escapeHtml4(StringEscapeUtils.unescapeHtml4(LegalStuffUtil.messageSource.getMessage("textolegal.mail.40", null, locale))) + "<br/>\n");
		sb.append("                " + StringEscapeUtils.escapeHtml4(StringEscapeUtils.unescapeHtml4(LegalStuffUtil.messageSource.getMessage("textolegal.mail.50", null, locale))) + "<br/>\n");
		sb.append("                " + StringEscapeUtils.escapeHtml4(StringEscapeUtils.unescapeHtml4(LegalStuffUtil.messageSource.getMessage("textolegal.mail.60", null, locale))) + "<br/>\n");
		sb.append("                " + StringEscapeUtils.escapeHtml4(StringEscapeUtils.unescapeHtml4(LegalStuffUtil.messageSource.getMessage("textolegal.mail.70", null, locale))));
		sb.append("            </p>\n");
		sb.append("        </td>\n");
		sb.append("    </tr>\n");

		switch (finalidad) {
		case USUARIOS:
			sb.append("    <tr>\n");
			sb.append("        <td style=\"vertical-align: top; padding-bottom: .5em;\">\n");
			sb.append("            <p style=\"font-weight: bold; color: #337ab7;\">" + StringEscapeUtils.escapeHtml4(StringEscapeUtils.unescapeHtml4(LegalStuffUtil.messageSource.getMessage("textolegal.mail.finalidad", null, locale))) + "</p>\n");
			sb.append("        </td>\n");
			sb.append("        <td style=\"vertical-align: top; padding-bottom: .5em;\">\n");
			sb.append("            <p>" + StringEscapeUtils.escapeHtml4(StringEscapeUtils.unescapeHtml4(LegalStuffUtil.messageSource.getMessage("textolegal.mail.usuarios.10", null, locale))) + "</p>\n");
			sb.append("        </td>\n");
			sb.append("    </tr>\n");
			sb.append("    <tr>\n");
			sb.append("        <td style=\"vertical-align: top; padding-bottom: .5em;\">\n");
			sb.append("            <p style=\"font-weight: bold; color: #337ab7;\">" + StringEscapeUtils.escapeHtml4(StringEscapeUtils.unescapeHtml4(LegalStuffUtil.messageSource.getMessage("textolegal.mail.legitimacion", null, locale))) + "</p>\n");
			sb.append("        </td>\n");
			sb.append("        <td style=\"vertical-align: top; padding-bottom: .5em;\">\n");
			sb.append("            <p>" + StringEscapeUtils.escapeHtml4(StringEscapeUtils.unescapeHtml4(LegalStuffUtil.messageSource.getMessage("textolegal.mail.usuarios.20", null, locale))) + "</p>\n");
			sb.append("            <p>" + StringEscapeUtils.escapeHtml4(StringEscapeUtils.unescapeHtml4(LegalStuffUtil.messageSource.getMessage("textolegal.mail.usuarios.30", null, locale))) + "</p>\n");
			sb.append("        </td>\n");
			sb.append("    </tr>\n");
			break;
		case VENDER:
			sb.append("    <tr>\n");
			sb.append("        <td style=\"vertical-align: top; padding-bottom: .5em;\">\n");
			sb.append("            <p style=\"font-weight: bold; color: #337ab7;\">" + StringEscapeUtils.escapeHtml4(StringEscapeUtils.unescapeHtml4(LegalStuffUtil.messageSource.getMessage("textolegal.mail.finalidad", null, locale))) + "</p>\n");
			sb.append("        </td>\n");
			sb.append("        <td style=\"vertical-align: top; padding-bottom: .5em;\">\n");
			sb.append("            <p>" + StringEscapeUtils.escapeHtml4(StringEscapeUtils.unescapeHtml4(LegalStuffUtil.messageSource.getMessage("textolegal.mail.vender.10", null, locale))) + "</p>\n");
			sb.append("        </td>\n");
			sb.append("    </tr>\n");
			sb.append("    <tr>\n");
			sb.append("        <td style=\"vertical-align: top; padding-bottom: .5em;\">\n");
			sb.append("            <p style=\"font-weight: bold; color: #337ab7;\">" + StringEscapeUtils.escapeHtml4(StringEscapeUtils.unescapeHtml4(LegalStuffUtil.messageSource.getMessage("textolegal.mail.legitimacion", null, locale))) + "</p>\n");
			sb.append("        </td>\n");
			sb.append("        <td style=\"vertical-align: top; padding-bottom: .5em;\">\n");
			sb.append("            <p>" + StringEscapeUtils.escapeHtml4(StringEscapeUtils.unescapeHtml4(LegalStuffUtil.messageSource.getMessage("textolegal.mail.vender.20", null, locale))) + "</p>\n");
			sb.append("            <p>" + StringEscapeUtils.escapeHtml4(StringEscapeUtils.unescapeHtml4(LegalStuffUtil.messageSource.getMessage("textolegal.mail.vender.30", null, locale))) + "</p>\n");
			sb.append("        </td>\n");
			sb.append("    </tr>\n");
			break;
		case CONTACTO:
			sb.append("    <tr>\n");
			sb.append("        <td style=\"vertical-align: top; padding-bottom: .5em;\">\n");
			sb.append("            <p style=\"font-weight: bold; color: #337ab7;\">" + StringEscapeUtils.escapeHtml4(StringEscapeUtils.unescapeHtml4(LegalStuffUtil.messageSource.getMessage("textolegal.mail.finalidad", null, locale))) + "</p>\n");
			sb.append("        </td>\n");
			sb.append("        <td style=\"vertical-align: top; padding-bottom: .5em;\">\n");
			sb.append("            <p>" + StringEscapeUtils.escapeHtml4(StringEscapeUtils.unescapeHtml4(LegalStuffUtil.messageSource.getMessage("textolegal.mail.90", null, locale))) + "</p>\n");
			sb.append("        </td>\n");
			sb.append("    </tr>\n");
			sb.append("    <tr>\n");
			sb.append("        <td style=\"vertical-align: top; padding-bottom: .5em;\">\n");
			sb.append("            <p style=\"font-weight: bold; color: #337ab7;\">" + StringEscapeUtils.escapeHtml4(StringEscapeUtils.unescapeHtml4(LegalStuffUtil.messageSource.getMessage("textolegal.mail.legitimacion", null, locale))) + "</p>\n");
			sb.append("        </td>\n");
			sb.append("        <td style=\"vertical-align: top; padding-bottom: .5em;\">\n");
			sb.append("            <p>" + StringEscapeUtils.escapeHtml4(StringEscapeUtils.unescapeHtml4(LegalStuffUtil.messageSource.getMessage("textolegal.mail.contacto.10", null, locale))) + "</p>\n");
			sb.append("            <p>" + StringEscapeUtils.escapeHtml4(StringEscapeUtils.unescapeHtml4(LegalStuffUtil.messageSource.getMessage("textolegal.mail.contacto.20", null, locale))) + "</p>\n");
			sb.append("        </td>\n");
			sb.append("    </tr>\n");
			break;
		case COMPRA:
			sb.append("    <tr>\n");
			sb.append("        <td style=\"vertical-align: top; padding-bottom: .5em;\">\n");
			sb.append("            <p style=\"font-weight: bold; color: #337ab7;\">" + StringEscapeUtils.escapeHtml4(StringEscapeUtils.unescapeHtml4(LegalStuffUtil.messageSource.getMessage("textolegal.mail.80", null, locale))) + "</p>\n");
			sb.append("        </td>\n");
			sb.append("        <td style=\"vertical-align: top; padding-bottom: .5em;\">\n");
			sb.append("            <p>" + StringEscapeUtils.escapeHtml4(StringEscapeUtils.unescapeHtml4(LegalStuffUtil.messageSource.getMessage("textolegal.mail.90", null, locale))) + "</p>\n");
			sb.append("        </td>\n");
			sb.append("    </tr>\n");
			break;
		default:
			break;
		}
		sb.append("    <tr>\n");
		sb.append("        <td style=\"vertical-align: top; padding-bottom: .5em;\">\n");
		sb.append("            <p style=\"font-weight: bold; color: #337ab7;\">" + StringEscapeUtils.escapeHtml4(StringEscapeUtils.unescapeHtml4(LegalStuffUtil.messageSource.getMessage("textolegal.mail.100", null, locale))) + "</p>\n");
		sb.append("        </td>\n");
		sb.append("        <td style=\"vertical-align: top; padding-bottom: .5em;\">\n");
		sb.append("            <p>" + StringEscapeUtils.escapeHtml4(StringEscapeUtils.unescapeHtml4(LegalStuffUtil.messageSource.getMessage("textolegal.mail.110", null, locale))) + "</p>\n");
		sb.append("            <p>" + StringEscapeUtils.escapeHtml4(StringEscapeUtils.unescapeHtml4(LegalStuffUtil.messageSource.getMessage("textolegal.mail.120", null, locale))) + "</p>\n");
		sb.append("        </td>\n");
		sb.append("    </tr>\n");
		sb.append("    <tr>\n");
		sb.append("        <td style=\"vertical-align: top; padding-bottom: .5em;\">\n");
		sb.append("            <p style=\"font-weight: bold; color: #337ab7;\">" + StringEscapeUtils.escapeHtml4(StringEscapeUtils.unescapeHtml4(LegalStuffUtil.messageSource.getMessage("textolegal.mail.130", null, locale))) + "</p>\n");
		sb.append("        </td>\n");
		sb.append("        <td style=\"vertical-align: top; padding-bottom: .5em;\">\n");
		sb.append("            <p>" + StringEscapeUtils.escapeHtml4(StringEscapeUtils.unescapeHtml4(LegalStuffUtil.messageSource.getMessage("textolegal.mail.150", null, locale))) + "</p>\n");
		sb.append("        </td>\n");
		sb.append("    </tr>\n");
		sb.append("    <tr>\n");
		sb.append("        <td style=\"vertical-align: top; padding-bottom: .5em;\">\n");
		sb.append("            <p style=\"font-weight: bold; color: #337ab7;\">" + StringEscapeUtils.escapeHtml4(StringEscapeUtils.unescapeHtml4(LegalStuffUtil.messageSource.getMessage("textolegal.mail.150", null, locale))) + "</p>\n");
		sb.append("        </td>\n");
		sb.append("        <td style=\"vertical-align: top; padding-bottom: .5em;\">\n");
		sb.append("            <p><a style=\"color: #337ab7; text-decoration: none;\" href=\"" + urlPoliticaPrivacidad + "\">" + StringEscapeUtils.escapeHtml4(StringEscapeUtils.unescapeHtml4(LegalStuffUtil.messageSource.getMessage("textolegal.mail.160", null, locale))) + "</a></p>\n");
		sb.append("        </td>\n");
		sb.append("    </tr>\n");
		sb.append("</table>\n");

		return sb.toString();
	}
}
