package com.spinnakersimple.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.config.DefaultParametersDef;
import com.capitanperegrina.common.seguridad.recaptcha.ReCaptchaSettings;
import com.capitanperegrina.common.utils.StackTraceUtil;
import com.capitanperegrina.common.validators.Validadores;
import com.capitanperegrina.common.web.ContextoAplicacion;
import com.capitanperegrina.common.web.Gravatar;
import com.spinnakersimple.modelo.entidad.UsuarioPOJO;
import com.spinnakersimple.modelo.util.SpinnakerSimpleGlobals;
import com.spinnakersimple.util.tablasayuda.Parametrizacion;
import com.spinnakersimple.web.ui.bean.UsuarioUI;

/**
 * The Class SpinnakerSimpleAutenthicationFilter.
 */
@Component("authenticationFilter")
public class SpinnakerSimpleAutenthicationFilter extends GenericFilterBean {

    /** The log. */
    static Logger log = Logger.getLogger(SpinnakerSimpleAutenthicationFilter.class);

    /** The acciones publicas. */
    List<String> accionesPublicas;

    /** The acciones administracion. */
    List<String> accionesAdministracion;

    /** The re captcha settings. */
    @Autowired
    private ReCaptchaSettings reCaptchaSettings;

    @Autowired
    private Parametrizacion parametrizacion;

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
        if (log.isDebugEnabled()) {
            log.debug(DefaultGlobalNames.PREFIJO_FILTRO_INI + StackTraceUtil.getCallerInfo());
            log.debug(DefaultGlobalNames.PREFIJO_FILTRO_FIN + StackTraceUtil.getCallerInfo());
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     * javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        if (log.isDebugEnabled()) {
            log.debug(DefaultGlobalNames.PREFIJO_FILTRO_INI + StackTraceUtil.getCallerInfo());
        }

        final String version = ContextoAplicacion.obtenVersion();
        request.setAttribute("app.version", version);

        request.setAttribute(DefaultParametersDef.APP_ESTADISTICAS, this.parametrizacion.isEstadisticas() ? "S" : "N");
        request.setAttribute(DefaultParametersDef.APP_PUBLICIDAD, this.parametrizacion.isPublicidad() ? "S" : "N");
        request.setAttribute(DefaultParametersDef.APP_GOOGLE_ADDS, this.parametrizacion.isGoogleAds() ? "S" : "N");

        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (log.isDebugEnabled()) {
            log.debug(httpRequest.getServletPath());
        }
        request.setAttribute(DefaultGlobalNames.LANGSESSION, LocaleContextHolder.getLocale().getLanguage());

        final UsuarioPOJO u = (UsuarioPOJO) httpRequest.getSession().getAttribute(DefaultGlobalNames.USERSESSION);
        final UsuarioUI uUi = (UsuarioUI) httpRequest.getSession().getAttribute(SpinnakerSimpleGlobals.USERUISESSION);
        if (u == null) {
            request.setAttribute("logged", null);
            if (!this.accionesPublicas.contains(httpRequest.getServletPath())) {
                final HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.sendRedirect("loginForm.action");
                return;
            }
        } else {
            request.setAttribute("logged", "S");
            request.setAttribute("user", u);
            request.setAttribute("userUi", uUi);
            request.setAttribute("gravatarMd5", Gravatar.md5Hex(u.getMail()));

            if (this.accionesAdministracion.contains(httpRequest.getServletPath())
                    && !u.getAdmin().equals(SpinnakerSimpleGlobals.USUARIO_ADMIN)) {
                final HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.sendRedirect("inicio.action");
            }
        }
        request.setAttribute("reCaptchaSiteKey", this.reCaptchaSettings.getSite());

        String qs = "";
        if (!Validadores.estaVacia(httpRequest.getQueryString())) {
            qs = "&" + httpRequest.getQueryString();
        }
        request.setAttribute("queryString", qs);
        chain.doFilter(request, response);

        if (log.isDebugEnabled()) {
            log.debug(DefaultGlobalNames.PREFIJO_FILTRO_FIN + StackTraceUtil.getCallerInfo());
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @PostConstruct
    public void init() {
        if (log.isDebugEnabled()) {
            log.debug(DefaultGlobalNames.PREFIJO_FILTRO_INI + StackTraceUtil.getCallerInfo());
        }

        this.accionesPublicas = new ArrayList<>();
        this.accionesAdministracion = new ArrayList<>();

        this.accionesPublicas.add("/loginForm.action");
        this.accionesPublicas.add("/resetPassRequest.action");
        this.accionesPublicas.add("/resetPass.action");
        this.accionesPublicas.add("/signUp.action");
        // Menu
        this.accionesPublicas.add("/inicio.action");
        this.accionesPublicas.add("/empresa.action");
        this.accionesPublicas.add("/comprarVela.action");
        this.accionesPublicas.add("/comprarVelaExec.action");
        this.accionesPublicas.add("/venderVela.action");
        this.accionesPublicas.add("/contacto.action");
        // Otras
        this.accionesPublicas.add("/verVela.action");
        this.accionesPublicas.add("/renuevaAnuncio.action");
        this.accionesPublicas.add("/agradecimientos.action");
        this.accionesPublicas.add("/tiposDeVela.action");
        this.accionesPublicas.add("/buscaConfiguracionTipoVela.action");
        this.accionesPublicas.add("/buscaBotonesActivosPorTiposClase.action");
        this.accionesPublicas.add("/buscaBotonesActivosPorTiposBarco.action");
        this.accionesPublicas.add("/politicaCookies.action");
        this.accionesPublicas.add("/politicaPrivacidad.action");
        this.accionesPublicas.add("/terminosCondiciones.action");
        this.accionesPublicas.add("/comprarVelaBuscar.action");
        this.accionesPublicas.add("/consultaVela.action");
        this.accionesPublicas.add("/picture.action");
        this.accionesPublicas.add("/renuevaAnuncioUrl.action");
        // Ajax
        this.accionesPublicas.add("/tipoVelaInfoAjax.action");
        this.accionesPublicas.add("/comprarVelaBuscarAjax.action");
        this.accionesPublicas.add("/mensaje.action");
        this.accionesPublicas.add("/legalStuffAjax.action");

        this.accionesAdministracion.add("/adminAnunciosPendientes.action");

        // this.reCaptchaSettings =
        // WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext()).getBean(ReCaptchaSettings.class);

        if (log.isDebugEnabled()) {
            log.debug(DefaultGlobalNames.PREFIJO_FILTRO_FIN + StackTraceUtil.getCallerInfo());
        }
    }
}
