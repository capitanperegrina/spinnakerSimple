package com.spinnakersimple.util.tablasayuda;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.config.DefaultParametersDef;
import com.capitanperegrina.common.modelo.validator.ParamValidator;
import com.capitanperegrina.common.modelo.validator.impl.BooleanParamValidator;
import com.capitanperegrina.common.modelo.validator.impl.MailParamValidator;
import com.capitanperegrina.common.modelo.validator.impl.URLParamValidator;
import com.capitanperegrina.common.servicios.ParamsService;

/**
 * Component class that stores all global parameters readed from db.
 */
@Component
public class Parametrizacion {

    /**
     * The params service.
     */
    @Autowired
    private ParamsService paramsService;

    /**
     * The mapa validadores.
     */
    private Map<String, ParamValidator> mapaValidadores = new HashMap<>();

    /**
     * The app en produccion.
     */
    private boolean appEnProduccion;

    /**
     * The app en mantenimiento.
     */
    private boolean appEnMantenimiento;

    /**
     * The publicar en twitter.
     */
    private boolean publicarEnTwitter;

    /**
     * The publicar en instagram.
     */
    private boolean publicarEnInstagram;

    /**
     * The publicar en instagram.
     */
    private boolean publicarEnFacebook;

    /**
     * The mostrar botones sociales.
     */
    private boolean mostrarBotonesSociales;

    private boolean estadisticas;

    private boolean publicidad;

    private boolean googleAds;

    /**
     * The url base.
     */
    private String urlBase;

    /**
     * The url logo.
     */
    private String urlLogo;

    /**
     * The mail admin.
     */
    private String mailAdmin;

    /**
     * Initializa parameter values from db.
     */
    @PostConstruct
    public void init() {
        this.urlBase = this.paramsService.leeParametro(DefaultParametersDef.APP_URL_BASE);
        this.urlLogo = this.paramsService.leeParametro(DefaultParametersDef.APP_URL_LOGO);
        this.mailAdmin = this.paramsService.leeParametro(DefaultParametersDef.APP_MAIL_ADMIN);
        this.appEnProduccion = this.paramsService.leeParametro(DefaultParametersDef.APP_EN_PRODUCCION)
                .equals(DefaultGlobalNames.SI);
        this.appEnMantenimiento = this.paramsService.leeParametro(DefaultParametersDef.APP_EN_MANTENIMIENTO)
                .equals(DefaultGlobalNames.SI);
        this.mostrarBotonesSociales = this.paramsService.leeParametro(DefaultParametersDef.APP_MOSTRAR_BOTONES_SOCIALES)
                .equals(DefaultGlobalNames.SI);

        this.publicarEnTwitter = this.paramsService.leeParametro(DefaultParametersDef.APP_SOCIAL_TWITTER)
                .equals(DefaultGlobalNames.SI);
        this.publicarEnInstagram = this.paramsService.leeParametro(DefaultParametersDef.APP_SOCIAL_INSTAGRAM)
                .equals(DefaultGlobalNames.SI);
        this.publicarEnFacebook = this.paramsService.leeParametro(DefaultParametersDef.APP_SOCIAL_FACEBOOK)
                .equals(DefaultGlobalNames.SI);

        this.estadisticas = this.paramsService.leeParametro(DefaultParametersDef.APP_ESTADISTICAS).equals(DefaultGlobalNames.SI);
        this.publicidad = this.paramsService.leeParametro(DefaultParametersDef.APP_PUBLICIDAD).equals(DefaultGlobalNames.SI);
        this.googleAds = this.paramsService.leeParametro(DefaultParametersDef.APP_GOOGLE_ADDS).equals(DefaultGlobalNames.SI);

        final BooleanParamValidator booleanValidator = new BooleanParamValidator();
        final URLParamValidator urlValidator = new URLParamValidator();
        this.mapaValidadores.put(DefaultParametersDef.APP_URL_BASE, urlValidator);
        this.mapaValidadores.put(DefaultParametersDef.APP_URL_LOGO, urlValidator);
        this.mapaValidadores.put(DefaultParametersDef.APP_MAIL_ADMIN, new MailParamValidator());
        this.mapaValidadores.put(DefaultParametersDef.APP_EN_PRODUCCION, booleanValidator);
        this.mapaValidadores.put(DefaultParametersDef.APP_EN_MANTENIMIENTO, booleanValidator);
        this.mapaValidadores.put(DefaultParametersDef.APP_SOCIAL_TWITTER, booleanValidator);
        this.mapaValidadores.put(DefaultParametersDef.APP_SOCIAL_INSTAGRAM, booleanValidator);
        this.mapaValidadores.put(DefaultParametersDef.APP_SOCIAL_FACEBOOK, booleanValidator);
        this.mapaValidadores.put(DefaultParametersDef.APP_MOSTRAR_BOTONES_SOCIALES, booleanValidator);
        this.mapaValidadores.put(DefaultParametersDef.APP_ESTADISTICAS, booleanValidator);
        this.mapaValidadores.put(DefaultParametersDef.APP_PUBLICIDAD, booleanValidator);
        this.mapaValidadores.put(DefaultParametersDef.APP_GOOGLE_ADDS, booleanValidator);
    }

    /**
     * Reloads parameters from db.
     */
    public void reload() {
        init();
    }

    /**
     * Gets the url base.
     *
     * @return the url base
     */
    public String getUrlBase() {
        return this.urlBase;
    }

    /**
     * Gets the url logo.
     *
     * @return the url logo
     */
    public String getUrlLogo() {
        return this.urlLogo;
    }

    /**
     * Gets the mail admin.
     *
     * @return the mail admin
     */
    public String getMailAdmin() {
        return this.mailAdmin;
    }

    public boolean isEstadisticas() {
        return this.estadisticas;
    }

    public boolean isPublicidad() {
        return this.publicidad;
    }

    /**
     * Checks if is app en produccion.
     *
     * @return true, if is app en produccion
     */
    public boolean isAppEnProduccion() {
        return this.appEnProduccion;
    }

    /**
     * Checks if is app en mantenimiento.
     *
     * @return true, if is app en mantenimiento
     */
    public boolean isAppEnMantenimiento() {
        return this.appEnMantenimiento;
    }

    /**
     * Gets the mapa validadores.
     *
     * @return the mapa validadores
     */
    public Map<String, ParamValidator> getMapaValidadores() {
        return this.mapaValidadores;
    }

    /**
     * Checks if is publicar en twitter.
     *
     * @return true, if is publicar en twitter
     */
    public boolean isPublicarEnTwitter() {
        return this.publicarEnTwitter;
    }

    /**
     * Checks if is publicar en instagram.
     *
     * @return true, if is publicar en instagram
     */
    public boolean isPublicarEnInstagram() {
        return this.publicarEnInstagram;
    }

    /**
     * Checks if is mostrar botones sociales.
     *
     * @return true, if is mostrar botones sociales
     */
    public boolean isMostrarBotonesSociales() {
        return this.mostrarBotonesSociales;
    }

    public boolean isPublicarEnFacebook() {
        return this.publicarEnFacebook;
    }

    public boolean isGoogleAds() {
        return googleAds;
    }

    public void setGoogleAds(boolean googleAds) {
        this.googleAds = googleAds;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Parametrizacion{");
        sb.append("appEnProduccion=").append(appEnProduccion);
        sb.append(", appEnMantenimiento=").append(appEnMantenimiento);
        sb.append(", publicarEnTwitter=").append(publicarEnTwitter);
        sb.append(", publicarEnInstagram=").append(publicarEnInstagram);
        sb.append(", publicarEnFacebook=").append(publicarEnFacebook);
        sb.append(", mostrarBotonesSociales=").append(mostrarBotonesSociales);
        sb.append(", estadisticas=").append(estadisticas);
        sb.append(", publicidad=").append(publicidad);
        sb.append(", googleAds=").append(googleAds);
        sb.append(", urlBase='").append(urlBase).append('\'');
        sb.append(", urlLogo='").append(urlLogo).append('\'');
        sb.append(", mailAdmin='").append(mailAdmin).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
