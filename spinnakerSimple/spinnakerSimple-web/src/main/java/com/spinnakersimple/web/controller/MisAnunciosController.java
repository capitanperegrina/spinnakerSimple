package com.spinnakersimple.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.capitanperegrina.common.basebeans.CambioEnTabla;
import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.exceptions.ServicioErrorException;
import com.capitanperegrina.common.i18n.Mensajes;
import com.capitanperegrina.common.seguridad.Criptografia;
import com.capitanperegrina.common.utils.StackTraceUtil;
import com.capitanperegrina.common.web.FormUtils;
import com.capitanperegrina.i18n.modelo.util.Divisas;
import com.capitanperegrina.i18n.modelo.util.Paises;
import com.spinnakersimple.helpers.adapters.AdaptFromQueryString;
import com.spinnakersimple.modelo.bean.AnuncioCompleto;
import com.spinnakersimple.modelo.dto.FiltroBusquedaVelaBean;
import com.spinnakersimple.modelo.entidad.FotografiaPOJO;
import com.spinnakersimple.modelo.entidad.UsuarioPOJO;
import com.spinnakersimple.modelo.util.SpinnakerSimpleGlobals;
import com.spinnakersimple.servicios.AnunciosService;
import com.spinnakersimple.util.tablasayuda.TiposBarco;
import com.spinnakersimple.util.tablasayuda.TiposVela;
import com.spinnakersimple.web.ui.bean.MisAnunciosUI;
import com.spinnakersimple.web.ui.bean.VenderVelaUI;
import com.spinnakersimple.web.ui.util.AdaptFromUI;
import com.spinnakersimple.web.ui.util.AdaptToUI;
import com.spinnakersimple.web.validator.VenderVelaValidator;

/**
 * La clase Class MisAnunciosController.
 *
 * @author <a href="mailto:yo@capitanperegrina.com">Capitán Peregrina</a>
 */
@Controller
public class MisAnunciosController {

    /** The log. */
    private static Logger log = Logger.getLogger(MisAnunciosController.class);

    /** The message source. */
    @Autowired
    MessageSource messageSource;

    /** The spinnaker simple publico. */
    @Autowired
    AnunciosService anunciosService;

    /** The criptografia. */
    @Autowired
    Criptografia criptografia;

    /** The tipos vela. */
    @Autowired
    TiposVela tiposVela;

    /** The paises. */
    @Autowired
    Divisas divisas;

    /** The paises. */
    @Autowired
    Paises paises;

    @Autowired
    TiposBarco tiposBarco;

    @Autowired
    VenderVelaValidator venderVelaValidator;

    @InitBinder(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_VENDER)
    protected void initBinderContactoValidator(final WebDataBinder binder) {
        binder.setValidator(this.venderVelaValidator);
    }

    /**
     * Mis anuncios get.
     *
     * @param model   the model
     * @param request the request
     * @return the model and view
     */
    @RequestMapping(value = "/misAnuncios.action", method = RequestMethod.GET)
    public ModelAndView misAnunciosGet(final Model model, final HttpServletRequest request) {
        if (log.isDebugEnabled()) {
            log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
        }
        try {
            // Fomulario con los valores por defecto.
            final MisAnunciosUI form = new MisAnunciosUI();
            form.setTipoVela(DefaultGlobalNames.NADA_SELECCIONADO_EN_LISTA);
            form.setTipoAnuncio(new String[] { "0", "1" });
            model.addAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_MIS_ANUNCIOS, form);

            // Filtro por defecto.
            final FiltroBusquedaVelaBean filtro = AdaptFromUI.toFiltroBusquedaVelaBean(form);

            final CambioEnTabla ct = new CambioEnTabla();
            ct.setUsu(((UsuarioPOJO) request.getSession().getAttribute(DefaultGlobalNames.USERSESSION)).getIdUsuario());
            ct.setLocale(LocaleContextHolder.getLocale());
            ct.setIp(request.getRemoteAddr());
            ct.setFec(Calendar.getInstance());

            final UsuarioPOJO u = ((UsuarioPOJO) request.getSession().getAttribute(DefaultGlobalNames.USERSESSION));
            request.setAttribute("usuario", u);
            request.setAttribute("resultadosBusqueda",
                    AdaptToUI.toAnuncioCompletoUIList(this.anunciosService.buscarAnuncios(filtro, ct, false)));

            request.setAttribute(SpinnakerSimpleGlobals.MENU_ELEMENTO_SELECCIONADO, SpinnakerSimpleGlobals.MENU_MIS_ANUNCIOS);
            request.setAttribute("tiposVela", this.tiposVela.getMapaParaFormulario(LocaleContextHolder.getLocale().getLanguage()));

            return new ModelAndView("misAnuncios");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
            }
        }
    }

    /**
     * Mis anuncios post.
     *
     * @param form               the filtro
     * @param result             the result
     * @param model              the model
     * @param redirectAttributes the redirect attributes
     * @param request            the request
     * @return the model and view
     */
    @RequestMapping(value = "/misAnuncios.action", method = RequestMethod.POST)
    public ModelAndView misAnunciosPost(
            @ModelAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_MIS_ANUNCIOS) @Validated final MisAnunciosUI form,
            final BindingResult result, final Model model, final RedirectAttributes redirectAttributes,
            final HttpServletRequest request) {
        if (log.isDebugEnabled()) {
            log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
        }
        try {
            final FiltroBusquedaVelaBean filtro = AdaptFromUI.toFiltroBusquedaVelaBean(form);

            final CambioEnTabla ct = new CambioEnTabla();
            final UsuarioPOJO u = ((UsuarioPOJO) request.getSession().getAttribute(DefaultGlobalNames.USERSESSION));
            ct.setUsu(u.getIdUsuario());
            ct.setLocale(LocaleContextHolder.getLocale());
            ct.setIp(request.getRemoteAddr());
            ct.setFec(Calendar.getInstance());

            request.setAttribute("usuario", u);
            request.setAttribute("resultadosBusqueda",
                    AdaptToUI.toAnuncioCompletoUIList(this.anunciosService.buscarAnuncios(filtro, ct, false)));

            request.setAttribute(SpinnakerSimpleGlobals.MENU_ELEMENTO_SELECCIONADO, SpinnakerSimpleGlobals.MENU_MIS_ANUNCIOS);
            request.setAttribute("tiposVela", this.tiposVela.getMapaParaFormulario(LocaleContextHolder.getLocale().getLanguage()));
            return new ModelAndView("misAnuncios");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
            }
        }
    }

    /**
     * Mi anuncio cambia estado get.
     *
     * @param model   the model
     * @param request the request
     * @return the model and view
     */
    @RequestMapping(value = "/miAnuncioCambiaEstado.action", method = RequestMethod.GET)
    public ModelAndView miAnuncioCambiaEstadoGet(final Model model, final HttpServletRequest request) {
        if (log.isDebugEnabled()) {
            log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
        }
        try {
            final FiltroBusquedaVelaBean form = AdaptFromQueryString.adaptToMisAnunciosFormDTO(request.getParameter("s"));
            final Integer idAnuncio = Integer.parseInt(this.criptografia.descodificaParaUrl(request.getParameter("ida")));
            final Integer nuevoEstado = Integer.parseInt(request.getParameter("ne"));

            final CambioEnTabla ct = new CambioEnTabla();
            ct.setUsu(((UsuarioPOJO) request.getSession().getAttribute(DefaultGlobalNames.USERSESSION)).getIdUsuario());
            ct.setLocale(LocaleContextHolder.getLocale());
            ct.setIp(request.getRemoteAddr());
            ct.setFec(Calendar.getInstance());

            this.anunciosService.modificaEstadoAnuncio(idAnuncio, nuevoEstado, ct);

            request.setAttribute("resultadosBusqueda",
                    AdaptToUI.toAnuncioCompletoUIList(this.anunciosService.buscarAnuncios(form, ct,
                            ((UsuarioPOJO) request.getSession().getAttribute(DefaultGlobalNames.USERSESSION)).getAdmin()
                                    .equals(SpinnakerSimpleGlobals.USUARIO_ADMIN))));
            request.setAttribute(SpinnakerSimpleGlobals.MENU_ELEMENTO_SELECCIONADO, SpinnakerSimpleGlobals.MENU_MIS_ANUNCIOS);
            model.addAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_MIS_ANUNCIOS, form);
            request.setAttribute("tiposVela", this.tiposVela.getMapaParaFormulario(LocaleContextHolder.getLocale().getLanguage()));

            return new ModelAndView("misAnuncios");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
            }
        }
    }

    /**
     * Mi anuncio mod get.
     *
     * @param model   the model
     * @param request the request
     * @return the model and view
     */
    @RequestMapping(value = "/miAnuncioMod.action", method = RequestMethod.GET)
    public ModelAndView miAnuncioModGet(final Model model, final HttpServletRequest request) {
        if (log.isDebugEnabled()) {
            log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
        }
        try {
            final Integer idAnuncio = Integer.parseInt(this.criptografia.descodificaParaUrl(request.getParameter("ida")));
            edicionAnuncio(model, request, idAnuncio);
            return new ModelAndView("miAnuncioMod");

        } finally {
            if (log.isDebugEnabled()) {
                log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
            }
        }
    }

    /**
     * Mis anuncios post.
     *
     * @param form               the form
     * @param result             the result
     * @param model              the model
     * @param redirectAttributes the redirect attributes
     * @param request            the request
     * @return the model and view
     */
    @RequestMapping(value = "/miAnuncioModFoto.action", method = RequestMethod.POST)
    public ModelAndView misAnunciosPost(
            @ModelAttribute(SpinnakerSimpleGlobals.NOMBRE_FORMULARIO_MIS_ANUNCIOS) @Validated final VenderVelaUI form,
            final BindingResult result, final Model model, final RedirectAttributes redirectAttributes,
            final HttpServletRequest request) {

        if (log.isDebugEnabled()) {
            log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
        }
        try {
            form.setIdAnuncio(
                    Integer.toString(Integer.parseInt(this.criptografia.descodificaParaUrl(form.getIdAnuncioCodificado()))));

            if (!result.hasErrors()) {

                final CambioEnTabla ct = new CambioEnTabla();
                ct.setUsu(((UsuarioPOJO) request.getSession().getAttribute(DefaultGlobalNames.USERSESSION)).getIdUsuario());
                ct.setLocale(LocaleContextHolder.getLocale());
                ct.setIp(request.getRemoteAddr());
                ct.setFec(Calendar.getInstance());

                final List<FotografiaPOJO> fotos = AdaptFromUI.toFotografiasPOJOList(form);

                this.anunciosService.guardaFotografias(fotos, ct);
            }

            edicionAnuncio(model, request, Integer.parseInt(form.getIdAnuncio()));
            return new ModelAndView("miAnuncioMod");

        } finally {
            if (log.isDebugEnabled()) {
                log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
            }
        }
    }

    /**
     * Edicion anuncio.
     *
     * @param model     the model
     * @param request   the request
     * @param idAnuncio the id anuncio
     */
    public void edicionAnuncio(final Model model, final HttpServletRequest request, final Integer idAnuncio) {
        final Integer idUsuario = ((UsuarioPOJO) request.getSession().getAttribute(DefaultGlobalNames.USERSESSION)).getIdUsuario();
        final String literalDivisa = this.divisas
                .getPojo(((UsuarioPOJO) request.getSession().getAttribute(DefaultGlobalNames.USERSESSION)).getDivisa())
                .getDescripcionCompleta();
        request.setAttribute("literalDivisa", literalDivisa);

        final AnuncioCompleto ac = this.anunciosService.buscarAnuncio(idAnuncio, idUsuario, false);
        model.addAttribute("anuncio", AdaptToUI.toAnuncioCompletoUI(ac));

        final VenderVelaUI formVela = AdaptToUI.toVenderVelaUI(ac);
        formVela.setTipoAlta(SpinnakerSimpleGlobals.VENDER_TIPO_EDITAR_DATOS);
        request.setAttribute("nombreClaseBarco", this.tiposBarco
                .getPojo(LocaleContextHolder.getLocale().getLanguage(), ac.getAnuncio().getTipoBarco()).getTipoBarco());
        request.setAttribute("tiposVela", this.tiposVela.getMapaParaFormulario(LocaleContextHolder.getLocale().getLanguage()));
        request.setAttribute("paises", this.paises.getLista(LocaleContextHolder.getLocale().getLanguage().toUpperCase()));
        model.addAttribute("editaVelaForm", formVela);

        if (!idUsuario.equals(ac.getUsuario().getIdUsuario())) {
            request.setAttribute("mostrarPropietario", true);
        }

        request.setAttribute(SpinnakerSimpleGlobals.MENU_ELEMENTO_SELECCIONADO, SpinnakerSimpleGlobals.MENU_MIS_ANUNCIOS);
    }

    @RequestMapping(value = "/renuevaAnuncio.action", method = RequestMethod.GET)
    public void renuevaAnuncioGet(final HttpServletRequest request, final HttpServletResponse response) {
        if (log.isDebugEnabled()) {
            log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
        }
        try {
            String r = request.getParameter("r");
            r = this.criptografia.descodificaParaUrl(r);
            r = this.criptografia.desencriptar(r);
            r = r.split("\\|")[1];

            final JSONObject respuestaJson = new JSONObject();
            final Calendar nuevaCaducidad = this.anunciosService.renuevaAnuncioDesdeUrl(Integer.parseInt(r));
            if (nuevaCaducidad != null) {
                respuestaJson.put("resultado", "OK");
                String nc = this.messageSource.getMessage("renuevaAnuncio.nuevaCaducidad", null, LocaleContextHolder.getLocale());
                nc = nc.replaceAll("%FECHA%", FormUtils.toStringForm(nuevaCaducidad));
                respuestaJson.put("nuevaCaducidad", StringEscapeUtils.escapeHtml4(nc));
            } else {
                respuestaJson.put("resultado", Mensajes.getStringSafe("modificaEstadoAnuncio.error.generico"));
            }

            final PrintWriter out = response.getWriter();
            log.debug(respuestaJson.toString());
            out.write(respuestaJson.toString());
        } catch (IOException | JSONException e) {
            log.error("", e);
            throw new ServicioErrorException(e);
        } finally {
            if (log.isDebugEnabled()) {
                log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
            }
        }

        if (log.isDebugEnabled()) {
            log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
        }
    }

    @RequestMapping(value = "/renuevaAnuncioUrl.action", method = RequestMethod.GET)
    public ModelAndView renuevaAnuncioUrlGet(final HttpServletRequest request, final HttpServletResponse response) {
        if (log.isDebugEnabled()) {
            log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
        }
        try {
            String r = request.getParameter("r");
            r = this.criptografia.descodificaParaUrl(r);
            r = this.criptografia.desencriptar(r);
            r = r.split("\\|")[1];

            String msg;
            final Calendar nuevaCaducidad = this.anunciosService.renuevaAnuncioDesdeUrl(Integer.parseInt(r));
            if (nuevaCaducidad != null) {

                msg = this.messageSource.getMessage("renuevaAnuncio.nuevaCaducidad", null, LocaleContextHolder.getLocale());
                msg = msg.replaceAll("%FECHA%", FormUtils.toStringForm(nuevaCaducidad));

            } else {
                msg = Mensajes.getStringSafe("modificaEstadoAnuncio.error.generico");
            }

            request.setAttribute(DefaultGlobalNames.MENSAJE_MSG, msg);
            return new ModelAndView("mensaje");

        } finally {
            if (log.isDebugEnabled()) {
                log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
            }
        }
    }
}
