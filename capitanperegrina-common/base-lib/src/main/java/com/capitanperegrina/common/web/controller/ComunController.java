package com.capitanperegrina.common.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.seguridad.Criptografia;
import com.capitanperegrina.common.utils.StackTraceUtil;
import com.capitanperegrina.common.validators.Validadores;
import com.capitanperegrina.common.web.MensajeWeb;

/**
 * The Class ComunController.
 */
@Controller
public class ComunController {

    /** The log. */
    static Logger log = Logger.getLogger(ComunController.class);

    /** The criptografia. */
    @Autowired
    Criptografia criptografia;

    /** The message source. */
    @Autowired
    MessageSource messageSource;

    // MENSAJES ---------------------------------------------------------------

    /**
     * Mensaje get.
     *
     * @param request the request
     * @return the string
     */
    @RequestMapping(value = "/mensaje.action", method = RequestMethod.GET)
    public String mensajeGet(final HttpServletRequest request) {
        if (log.isDebugEnabled()) {
            log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
        }
        try {
            final MensajeWeb msgObj = (MensajeWeb) request.getSession().getAttribute("mensajeObj");
            if (Validadores.estaVacia(msgObj.getVar())) {
                msgObj.setVar(DefaultGlobalNames.MENSAJE_MSG);
            }
            final String mensaje = this.messageSource.getMessage(msgObj.getKey(), msgObj.getArgs(),
                    LocaleContextHolder.getLocale());
            request.setAttribute(msgObj.getVar(), mensaje);

            if (!Validadores.estaVacia(msgObj.getCerrarDialogoMensaje())) {
                request.setAttribute("cerrarDialogoMensaje", msgObj.getCerrarDialogoMensaje());
            } else {
                request.setAttribute("pasosAtras", msgObj.getPasosAtras());
                if (!Validadores.estaVacia(msgObj.getUrlVolver())) {
                    request.setAttribute("urlVolver", msgObj.getUrlVolver());
                }
            }
            return msgObj.getTilesMapping();
        } finally {
            if (log.isDebugEnabled()) {
                log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
            }
        }
    }

    /**
     * Mensaje en popup get.
     *
     * @param request the request
     * @return the string
     */
    @RequestMapping(value = "/mensajeEnPopup.action", method = RequestMethod.GET)
    public String mensajeEnPopupGet(final HttpServletRequest request) {
        if (log.isDebugEnabled()) {
            log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_INI + StackTraceUtil.getCallerInfo());
        }
        try {
            return mensajeGet(request);
        } finally {
            if (log.isDebugEnabled()) {
                log.debug(DefaultGlobalNames.PREFIJO_CONTROLADOR_FIN + StackTraceUtil.getCallerInfo());
            }
        }
    }

    public void estableceAccion(final HttpServletRequest req, final String accion) {
        req.setAttribute(DefaultGlobalNames.ACCION, accion);
        req.setAttribute(DefaultGlobalNames.ACCION + "Codificado", this.criptografia.codificaParaUrl(accion));
    }
}
