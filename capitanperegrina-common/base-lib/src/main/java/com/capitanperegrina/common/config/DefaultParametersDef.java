package com.capitanperegrina.common.config;

/**
 * Clase de constantes de claves para parámetros almacenados en bbdd
 */
public class DefaultParametersDef {

    public static final String APP_EN_PRODUCCION = "app.enProduccion";
    public static final String APP_EN_MANTENIMIENTO = "app.enMantenimiento";

    public static final String APP_URL_BASE = "url.base";
    public static final String APP_URL_LOGO = "url.logo";
    public static final String APP_MAIL_ADMIN = "mail.admin";

    public static final String APP_MOSTRAR_BOTONES_SOCIALES = "social.mostrarBotones";
    public static final String APP_SOCIAL_TWITTER = "social.twitter";
    public static final String APP_SOCIAL_INSTAGRAM = "social.instagram";
    public static final String APP_SOCIAL_FACEBOOK = "social.facebook";

    public static final String APP_ESTADISTICAS = "estadisticas";
    public static final String APP_PUBLICIDAD = "publicidad";
    public static final String APP_GOOGLE_ADDS = "googleAds";

    public static final String EMAIL_NO_REPLY = "mail.noReply";
    public static final String RECOVER_PASS_CODE = "recoverPassCode";

    public static final String SEMILLA_ENCRIPTACION = "semillaEncriptacion";

    private DefaultParametersDef() {
        super();
    }
}
