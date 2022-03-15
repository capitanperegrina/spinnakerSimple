package com.spinnakersimple.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.joda.time.Days;
import org.joda.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.exceptions.ServicioException;
import com.capitanperegrina.common.net.mail.Emailer;
import com.capitanperegrina.common.net.mail.MailObject;
import com.capitanperegrina.common.seguridad.Criptografia;
import com.capitanperegrina.common.seguridad.GeneradorClaves;
import com.capitanperegrina.common.utils.StackTraceUtil;
import com.capitanperegrina.common.web.FormUtils;
import com.spinnakersimple.modelo.entidad.AnuncioPOJO;
import com.spinnakersimple.modelo.entidad.RenovacionAnuncioPOJO;
import com.spinnakersimple.modelo.entidad.UsuarioPOJO;
import com.spinnakersimple.modelo.util.SpinnakerSimpleGlobals;
import com.spinnakersimple.servicios.AnunciosService;
import com.spinnakersimple.util.LegalStuffUtil;
import com.spinnakersimple.util.LegalStuffUtil.FinalidadesLegales;
import com.spinnakersimple.util.tablasayuda.Parametrizacion;

/**
 * The Class AltaAnuncioMailHelper.
 */
@Component
public class AnuncioMailHelper {

    /** The log. */
    static Logger log = Logger.getLogger(AnuncioMailHelper.class);

    /** The anuncios service. */
    @Autowired
    private AnunciosService anunciosService;

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
     * Envia correos alta vela.
     *
     * @param a the a
     * @param u the u
     */
    public void enviaCorreosAltaVela(final AnuncioPOJO a, final UsuarioPOJO u) {
        log.debug(DefaultGlobalNames.PREFIJO_HELPER_INI + StackTraceUtil.getCallerInfo());
        try {
            final Locale localeUsuario = new Locale(u.getLang());
            final Locale localeAdmin = new Locale("es");

            // Interesado
            if (this.parametrizacion.isAppEnProduccion()) {
                this.emailer.sendEmail(this.emailer.getRemitentePorDefecto(), u.getMail(),
                        generaAsuntoAltaVelaAnunciante(localeUsuario), generaTextoAltaVelaAnunciante(localeUsuario, a, u),
                        Boolean.TRUE, null, null);
            } else {
                this.emailer.sendEmail(this.emailer.getRemitentePorDefecto(), this.parametrizacion.getMailAdmin(),
                        generaAsuntoAltaVelaAnunciante(localeUsuario), generaTextoAltaVelaAnunciante(localeUsuario, a, u),
                        Boolean.TRUE, null, null);
            }

            // Administrador
            this.emailer.sendEmail(this.emailer.getRemitentePorDefecto(), this.parametrizacion.getMailAdmin(),
                    generaAsuntoAltaVelaAdministrador(localeAdmin), generaTextoAltaVelaAdministrador(localeAdmin), Boolean.TRUE,
                    null, null);
        } catch (final ServicioException e) {
            log.error("", e);
            throw e.toServicioErrorException();
        } finally {
            log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
        }
    }

    /**
     * Envia correos confirmacion alta vela.
     *
     * @param a the a
     * @param u the u
     */
    public void enviaCorreosConfirmacionAltaVela(final AnuncioPOJO a, final UsuarioPOJO u) {
        log.debug(DefaultGlobalNames.PREFIJO_HELPER_INI + StackTraceUtil.getCallerInfo());
        try {
            final Locale localeUsuario = new Locale(u.getLang());

            // Interesado
            if (this.parametrizacion.isAppEnProduccion()) {
                this.emailer.sendEmail(this.emailer.getRemitentePorDefecto(), u.getMail(),
                        generaAsuntoAltaVelaConfirmadaAnunciante(localeUsuario),
                        generaTextoAltaVelaConfirmadaAnunciante(localeUsuario, a, u), Boolean.TRUE, null, null);
            } else {
                this.emailer.sendEmail(this.emailer.getRemitentePorDefecto(), this.parametrizacion.getMailAdmin(),
                        generaAsuntoAltaVelaConfirmadaAnunciante(localeUsuario),
                        generaTextoAltaVelaConfirmadaAnunciante(localeUsuario, a, u), Boolean.TRUE, null, null);
            }

        } catch (final ServicioException e) {
            log.error("", e);
            throw e.toServicioErrorException();
        } finally {
            log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
        }
    }

    public void notificaCaducidadAnuncio(final List<AnuncioPOJO> anunciosANotificar, final Map<Integer, UsuarioPOJO> usuarios) {
        if (log.isDebugEnabled()) {
            log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
        }
        try {
            final List<MailObject> mails = new ArrayList<>(anunciosANotificar.size());
            for (final AnuncioPOJO a : anunciosANotificar) {
                final UsuarioPOJO u = usuarios.get(a.getIdUsuarioAlta());
                final Locale localeUsuario = new Locale(u.getLang());
                final MailObject m = new MailObject();
                m.setAsunto(generaAsuntoNotificaCaducidadAnuncio(a, localeUsuario));
                m.setRemite(this.emailer.getRemitentePorDefecto());
                m.setNombreRemite(this.emailer.getNombreRemitentePorDefecto());
                if (this.parametrizacion.isAppEnProduccion()) {
                    m.setDestinatario(u.getMail());
                } else {
                    m.setDestinatario(this.parametrizacion.getMailAdmin());
                }
                m.setHtml(true);
                m.setMensaje(generaTextoNotificaCaducidadAnuncio(localeUsuario, a, u));
                m.setAdjuntos(null);
                m.setReplyTo(this.parametrizacion.getMailAdmin());
                mails.add(m);

                if (mails.size() == 25) {
                    this.emailer.sendEmail(mails);
                    mails.clear();
                    Thread.sleep(10000);
                }
            }
            this.emailer.sendEmail(mails);
        } catch (final InterruptedException e) {
            log.error("Proceso interrumpido.", e);
        } catch (final ServicioException e) {
            log.error("Error realizando el envío del mensaje.", e);
        } finally {
            if (log.isDebugEnabled()) {
                log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
            }
        }
    }

    public void notificaCaducidadAnuncioConEnlaceDirecto(final List<AnuncioPOJO> anunciosANotificar,
            final Map<Integer, UsuarioPOJO> usuarios, final Map<Integer, RenovacionAnuncioPOJO> rMap) {
        if (log.isDebugEnabled()) {
            log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
        }
        try {
            final List<MailObject> mails = new ArrayList<>(anunciosANotificar.size());
            for (final AnuncioPOJO a : anunciosANotificar) {
                final UsuarioPOJO u = usuarios.get(a.getIdUsuarioAlta());
                final Locale localeUsuario = new Locale(u.getLang());
                final MailObject m = new MailObject();
                m.setAsunto(generaAsuntoNotificaCaducidadAnuncio(a, localeUsuario));
                m.setRemite(this.emailer.getRemitentePorDefecto());
                m.setNombreRemite(this.emailer.getNombreRemitentePorDefecto());
                if (this.parametrizacion.isAppEnProduccion()) {
                    m.setDestinatario(u.getMail());
                } else {
                    m.setDestinatario(this.parametrizacion.getMailAdmin());
                }
                m.setHtml(true);
                m.setMensaje(generaTextoNotificaCaducidadAnuncioConEnlaceDirecto(localeUsuario, a, u, rMap));
                m.setAdjuntos(null);
                m.setReplyTo(this.parametrizacion.getMailAdmin());
                mails.add(m);

                if (mails.size() == 25) {
                    this.emailer.sendEmail(mails);
                    mails.clear();
                    Thread.sleep(10000);
                }
            }
            this.emailer.sendEmail(mails);
        } catch (final InterruptedException e) {
            log.error("Proceso interrumpido.", e);
        } catch (final ServicioException e) {
            log.error("Error realizando el envío del mensaje.", e);
        } finally {
            if (log.isDebugEnabled()) {
                log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
            }
        }
    }

    // ASUNTOS
    // //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Genera asunto alta vela administrador.
     *
     * @param locale the locale
     * @return the string
     */
    public String generaAsuntoAltaVelaAdministrador(final Locale locale) {
        log.debug(DefaultGlobalNames.PREFIJO_HELPER_INI + StackTraceUtil.getCallerInfo());
        final StringBuilder sb = new StringBuilder();
        if (!this.parametrizacion.isAppEnProduccion()) {
            sb.append(SpinnakerSimpleGlobals.PREFIJO_ASUNTO_MAILS_NO_PRODUCCION);
        }
        try {
            sb.append(SpinnakerSimpleGlobals.PREFIJO_ASUNTO_MAILS
                    + this.messageSource.getMessage("altaVelaMailHelper.generaAsuntoAltaVelaAdministrador", null, locale));
            return sb.toString();
        } finally {
            log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
        }
    }

    /**
     * Genera asunto alta vela anunciante.
     *
     * @param locale the locale
     * @return the string
     */
    public String generaAsuntoAltaVelaAnunciante(final Locale locale) {
        log.debug(DefaultGlobalNames.PREFIJO_HELPER_INI + StackTraceUtil.getCallerInfo());
        final StringBuilder sb = new StringBuilder();
        if (!this.parametrizacion.isAppEnProduccion()) {
            sb.append(SpinnakerSimpleGlobals.PREFIJO_ASUNTO_MAILS_NO_PRODUCCION);
        }
        try {
            sb.append(SpinnakerSimpleGlobals.PREFIJO_ASUNTO_MAILS
                    + this.messageSource.getMessage("altaVelaMailHelper.generaAsuntoAltaVelaAnunciante", null, locale));
            return sb.toString();
        } finally {
            log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
        }
    }

    /**
     * Genera asunto alta vela confirmada anunciante.
     *
     * @param locale the locale
     * @return the string
     */
    public String generaAsuntoAltaVelaConfirmadaAnunciante(final Locale locale) {
        log.debug(DefaultGlobalNames.PREFIJO_HELPER_INI + StackTraceUtil.getCallerInfo());
        final StringBuilder sb = new StringBuilder();
        if (!this.parametrizacion.isAppEnProduccion()) {
            sb.append(SpinnakerSimpleGlobals.PREFIJO_ASUNTO_MAILS_NO_PRODUCCION);
        }
        try {
            sb.append(SpinnakerSimpleGlobals.PREFIJO_ASUNTO_MAILS
                    + this.messageSource.getMessage("altaVelaMailHelper.generaAsuntoAltaVelaConfirmadaAnunciante", null, locale));
            return sb.toString();
        } finally {
            log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
        }
    }

    private String generaAsuntoNotificaCaducidadAnuncio(final AnuncioPOJO a, final Locale locale) {
        if (log.isDebugEnabled()) {
            log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
        }

        final StringBuilder sb = new StringBuilder();
        if (!this.parametrizacion.isAppEnProduccion()) {
            sb.append(SpinnakerSimpleGlobals.PREFIJO_ASUNTO_MAILS_NO_PRODUCCION);
        }
        try {
            sb.append(SpinnakerSimpleGlobals.PREFIJO_ASUNTO_MAILS
                    + this.messageSource.getMessage("altaVelaMailHelper.generaAsuntoNotificaCaducidadAnuncio", null, locale));
            String ret = sb.toString();
            ret = ret.replace("%TITULO%", a.getTituloAnuncio());
            return ret;
        } finally {
            if (log.isDebugEnabled()) {
                log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
            }
        }
    }

    // TEXTOS
    // //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Genera texto alta vela administrador.
     *
     * @param locale the locale
     * @return the string
     */
    public String generaTextoAltaVelaAdministrador(final Locale locale) {
        log.debug(DefaultGlobalNames.PREFIJO_HELPER_INI + StackTraceUtil.getCallerInfo());
        final StringBuilder sb = new StringBuilder();
        try {
            sb.append(StringEscapeUtils.escapeHtml(
                    this.messageSource.getMessage("altaVelaMailHelper.generaTextoAltaVelaAdministrador.10", null, locale)));
            String ret = sb.toString();
            ret = ret.replace("%NUMERO_ANUNCIOS%", this.anunciosService.buscaNumeroDeAnunciosPendientesDeConfirmar().toString());
            return ret;
        } finally {
            log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
        }
    }

    /**
     * Genera texto alta vela anunciante.
     *
     * @param locale the locale
     * @param a      the a
     * @param u      the u
     * @return the string
     */
    public String generaTextoAltaVelaAnunciante(final Locale locale, final AnuncioPOJO a, final UsuarioPOJO u) {
        log.debug(DefaultGlobalNames.PREFIJO_HELPER_INI + StackTraceUtil.getCallerInfo());
        final StringBuilder sb = new StringBuilder();
        try {
            sb.append("<a href=\"" + this.parametrizacion.getUrlBase() + "\"><img style=\"width: 400px;\" src=\""
                    + this.parametrizacion.getUrlLogo() + "\" alt=\"http://spinnakersimple.com\"></a><br/>");
            sb.append(DefaultGlobalNames.HTML_BR);
            sb.append(StringEscapeUtils.escapeHtml(
                    this.messageSource.getMessage("altaVelaMailHelper.generaTextoAltaVelaAnunciante.10", new Object[] {}, locale)
                            .replace("%NOMBRE%", u.getNombre())));
            sb.append(DefaultGlobalNames.HTML_BR);
            sb.append(DefaultGlobalNames.HTML_BR);
            sb.append(StringEscapeUtils.escapeHtml(
                    this.messageSource.getMessage("altaVelaMailHelper.generaTextoAltaVelaAnunciante.20", new Object[] {}, locale)
                            .replaceAll("%TITULO%", a.getTituloAnuncio())));
            sb.append(DefaultGlobalNames.HTML_BR);
            sb.append(DefaultGlobalNames.HTML_BR);
            sb.append(
                    LegalStuffUtil.generaClausulaPrivacidad(FinalidadesLegales.VENDER, locale, this.parametrizacion.getUrlBase()));
            sb.append(DefaultGlobalNames.HTML_BR);
            sb.append("&copy; <a href=\"" + this.parametrizacion.getUrlBase() + "\">http://www.spinnakerSimple.com</a>");

            return sb.toString();
        } finally {
            log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
        }
    }

    /**
     * Genera texto alta vela confirmada anunciante.
     *
     * @param locale the locale
     * @param a      the a
     * @param u      the u
     * @return the string
     */
    public String generaTextoAltaVelaConfirmadaAnunciante(final Locale locale, final AnuncioPOJO a, final UsuarioPOJO u) {
        log.debug(DefaultGlobalNames.PREFIJO_HELPER_INI + StackTraceUtil.getCallerInfo());
        final StringBuilder sb = new StringBuilder();
        try {
            sb.append("<a href=\"" + this.parametrizacion.getUrlBase() + "\"><img style=\"width: 400px;\" src=\""
                    + this.parametrizacion.getUrlLogo() + "\" alt=\"http://spinnakersimple.com\"></a><br/>");
            sb.append(DefaultGlobalNames.HTML_BR);
            sb.append(StringEscapeUtils.escapeHtml(
                    this.messageSource.getMessage("altaVelaMailHelper.generaTextoAltaVelaConfirmadaAnunciante", null, locale)));
            sb.append(DefaultGlobalNames.HTML_BR);
            sb.append(StringEscapeUtils.escapeHtml(
                    this.messageSource.getMessage("altaVelaMailHelper.generaTextoAltaVelaConfirmadaAnunciante.20", null, locale))
                    + " <a href=\"%URL%\" target=\"_blank\">%URL%</a>.");
            sb.append(DefaultGlobalNames.HTML_BR);
            sb.append(DefaultGlobalNames.HTML_BR);
            sb.append(
                    LegalStuffUtil.generaClausulaPrivacidad(FinalidadesLegales.VENDER, locale, this.parametrizacion.getUrlBase()));
            sb.append(DefaultGlobalNames.HTML_BR);
            sb.append("&copy; <a href=\"" + this.parametrizacion.getUrlBase() + "\">http://www.spinnakerSimple.com</a>");
            String ret = sb.toString();
            ret = ret.replace("%NOMBRE%", StringEscapeUtils.escapeHtml(u.getNombre()));
            ret = ret.replace("%TITULO%", StringEscapeUtils.escapeHtml(a.getTituloAnuncio()));
            ret = ret.replace("%URL%", this.parametrizacion.getUrlBase() + "/verVela.action?ida="
                    + this.criptografia.codificaParaUrl(a.getIdAnuncio().toString()));
            return ret;
        } finally {
            log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
        }
    }

    public String generaTextoNotificaCaducidadAnuncio(final Locale locale, final AnuncioPOJO a, final UsuarioPOJO u) {
        log.debug(DefaultGlobalNames.PREFIJO_HELPER_INI + StackTraceUtil.getCallerInfo());
        final StringBuilder sb = new StringBuilder();
        try {
            sb.append("<a href=\"" + this.parametrizacion.getUrlBase() + "\"><img style=\"width: 400px;\" src=\""
                    + this.parametrizacion.getUrlLogo() + "\" alt=\"http://spinnakersimple.com\"></a><br/>");
            sb.append(DefaultGlobalNames.HTML_BR);
            sb.append(StringEscapeUtils.escapeHtml(
                    this.messageSource.getMessage("anuncioMailHelper.generaTextoNotificaCaducidadAnuncio.10", null, locale)));
            sb.append(DefaultGlobalNames.HTML_BR);
            sb.append(DefaultGlobalNames.HTML_BR);
            sb.append(StringEscapeUtils.escapeHtml(
                    this.messageSource.getMessage("anuncioMailHelper.generaTextoNotificaCaducidadAnuncio.20", null, locale)));
            sb.append(DefaultGlobalNames.HTML_BR);
            sb.append(DefaultGlobalNames.HTML_BR);
            sb.append(StringEscapeUtils.escapeHtml(
                    this.messageSource.getMessage("anuncioMailHelper.generaTextoNotificaCaducidadAnuncio.30", null, locale)));
            sb.append(DefaultGlobalNames.HTML_BR);
            sb.append(DefaultGlobalNames.HTML_BR);
            sb.append(StringEscapeUtils.escapeHtml(
                    this.messageSource.getMessage("anuncioMailHelper.generaTextoNotificaCaducidadAnuncio.40", null, locale)));
            sb.append(DefaultGlobalNames.HTML_BR);
            sb.append(DefaultGlobalNames.HTML_BR);
            sb.append(
                    LegalStuffUtil.generaClausulaPrivacidad(FinalidadesLegales.VENDER, locale, this.parametrizacion.getUrlBase()));
            sb.append(DefaultGlobalNames.HTML_BR);
            sb.append("&copy; <a href=\"" + this.parametrizacion.getUrlBase() + "\">http://www.spinnakerSimple.com</a>");
            String ret = sb.toString();
            ret = ret.replace("%NOMBRE%", StringEscapeUtils.escapeHtml(u.getNombre()));
            ret = ret.replace("%TITULO%", StringEscapeUtils.escapeHtml(a.getTituloAnuncio()));
            final int dias = Days.daysBetween(Instant.now(), Instant.ofEpochMilli(a.getCaduca().getTimeInMillis())).getDays() + 1;
            ret = ret.replace("%FECHA%", StringEscapeUtils.escapeHtml(FormUtils.toStringForm(a.getCaduca(), locale)));
            ret = ret.replace("%DIAS%", Integer.toString(dias));
            ret = ret.replace("%URL%",
                    StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeHtml(this.parametrizacion.getUrlBase())));
            ret = ret.replace("%URL_LOGIN%", StringEscapeUtils.escapeHtml(this.parametrizacion.getUrlBase() + "/loginForm.action"));
            ret = ret.replace("%NOMBRE_MIS_ANUNCIOS%",
                    StringEscapeUtils.escapeHtml(this.messageSource.getMessage("global.menu.misAnuncios", null, locale)));
            return ret;
        } finally {
            log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
        }
    }

    public String generaTextoNotificaCaducidadAnuncioConEnlaceDirecto(final Locale locale, final AnuncioPOJO a, final UsuarioPOJO u,
            final Map<Integer, RenovacionAnuncioPOJO> rMap) {
        log.debug(DefaultGlobalNames.PREFIJO_HELPER_INI + StackTraceUtil.getCallerInfo());
        final StringBuilder sb = new StringBuilder();
        try {
            final String url = "renuevaAnuncioUrl.action?r="
                    + this.criptografia.codificaParaUrl(this.criptografia.encriptar(GeneradorClaves.generaPin(10) + "|"
                            + rMap.get(a.getIdAnuncio()).getIdRenocacionAnuncio() + "|" + GeneradorClaves.generaPin(10)));

            sb.append("<a href=\"" + this.parametrizacion.getUrlBase() + "\"><img style=\"width: 400px;\" src=\""
                    + this.parametrizacion.getUrlLogo() + "\" alt=\"http://spinnakersimple.com\"></a><br/>");
            sb.append(DefaultGlobalNames.HTML_BR);
            sb.append(StringEscapeUtils.escapeHtml(this.messageSource
                    .getMessage("anuncioMailHelper.generaTextoNotificaCaducidadAnuncioConEnlaceDirecto.10", null, locale)));
            sb.append(DefaultGlobalNames.HTML_BR);
            sb.append(DefaultGlobalNames.HTML_BR);
            sb.append(StringEscapeUtils.escapeHtml(this.messageSource
                    .getMessage("anuncioMailHelper.generaTextoNotificaCaducidadAnuncioConEnlaceDirecto.20", null, locale)));
            sb.append(DefaultGlobalNames.HTML_BR);
            sb.append(DefaultGlobalNames.HTML_BR);
            sb.append("%INICIO_ENLACE%");
            sb.append(StringEscapeUtils.escapeHtml(this.messageSource
                    .getMessage("anuncioMailHelper.generaTextoNotificaCaducidadAnuncioConEnlaceDirecto.30", null, locale)));
            sb.append("%FIN_ENLACE% ");
            sb.append(StringEscapeUtils.escapeHtml(this.messageSource
                    .getMessage("anuncioMailHelper.generaTextoNotificaCaducidadAnuncioConEnlaceDirecto.35", null, locale)));
            sb.append(DefaultGlobalNames.HTML_BR);
            sb.append(DefaultGlobalNames.HTML_BR);
            sb.append(StringEscapeUtils.escapeHtml(this.messageSource
                    .getMessage("anuncioMailHelper.generaTextoNotificaCaducidadAnuncioConEnlaceDirecto.40", null, locale)));
            sb.append(DefaultGlobalNames.HTML_BR);
            sb.append(DefaultGlobalNames.HTML_BR);
            sb.append(
                    LegalStuffUtil.generaClausulaPrivacidad(FinalidadesLegales.VENDER, locale, this.parametrizacion.getUrlBase()));
            sb.append(DefaultGlobalNames.HTML_BR);
            sb.append("&copy; <a href=\"" + this.parametrizacion.getUrlBase() + "\">http://www.spinnakerSimple.com</a>");
            String ret = sb.toString();

            ret = ret.replace("%NOMBRE%", StringEscapeUtils.escapeHtml(u.getNombre()));
            ret = ret.replace("%TITULO%", StringEscapeUtils.escapeHtml(a.getTituloAnuncio()));
            final int dias = Days.daysBetween(Instant.now(), Instant.ofEpochMilli(a.getCaduca().getTimeInMillis())).getDays() + 1;
            ret = ret.replace("%FECHA%", StringEscapeUtils.escapeHtml(FormUtils.toStringForm(a.getCaduca(), locale)));
            ret = ret.replace("%DIAS%", Integer.toString(dias));
            ret = ret.replace("%URL%",
                    StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeHtml(this.parametrizacion.getUrlBase())));
            ret = ret.replace("%INICIO_ENLACE%",
                    "<a href=\"" + StringEscapeUtils.escapeHtml(this.parametrizacion.getUrlBase() + "/" + url) + "\">");
            ret = ret.replace("%FIN_ENLACE%", "</a>");
            ret = ret.replace("%URLRENOVAR%", StringEscapeUtils.escapeHtml(this.parametrizacion.getUrlBase() + "/" + url));
            ret = ret.replace("%NOMBRE_MIS_ANUNCIOS%",
                    StringEscapeUtils.escapeHtml(this.messageSource.getMessage("global.menu.misAnuncios", null, locale)));

            return ret;
        } finally {
            log.debug(DefaultGlobalNames.PREFIJO_HELPER_FIN + StackTraceUtil.getCallerInfo());
        }
    }
}