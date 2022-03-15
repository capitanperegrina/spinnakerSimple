package com.capitanperegrina.common.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.capitanperegrina.common.config.CheckExecutionParametersManager;
import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.exceptions.ServicioErrorException;
import com.capitanperegrina.common.exceptions.ServicioException;
import com.capitanperegrina.common.utils.StackTraceUtil;

/**
 * The Class GlobalExceptionHandler.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private static final String URL = "url";

	private static final String MSG = "msg";

	private static final String TRAZA = "traza";

	private static final String EXCEPTION = "exception";

	private static final String MODO_DEBUG = "modoDebug";

	/** The log. */
	static Logger log = Logger.getLogger(GlobalExceptionHandler.class);

	/** The message source. */
	@Autowired
	MessageSource messageSource;

	/**
	 * Servicio error exception handler.
	 *
	 * @param req the req
	 * @param e   the e
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@ExceptionHandler(value = ServicioErrorException.class)
	public ModelAndView servicioErrorExceptionHandler(final HttpServletRequest req, final ServicioErrorException e)
					throws Exception {

		if ( e.getMessage().equals("Criptografia.desencriptar.IOException") ) {
			log.error(req.getRequestURL().append('?').append(req.getQueryString()) + " - Params: " + WebUtils.paramsToString(req) + " - Criptografia.desencriptar.IOException");
			return new ModelAndView("redirect:/" );
		}
		log.error(req.getRequestURL().append('?').append(req.getQueryString()) + " - Params: " + WebUtils.paramsToString(req) ,e);

		if (e.isAjax()) {
			return null;
		} else {
			final ModelAndView mav = new ModelAndView("error");
			mav.addObject(MODO_DEBUG, CheckExecutionParametersManager.getParameter(DefaultGlobalNames.PAREMETRO_MODO_DEBUG));
			mav.addObject(EXCEPTION, e);
			mav.addObject(TRAZA, StackTraceUtil.getStackTrace(e));
			if (e.getPendienteLocalizar()) {
				mav.addObject(MSG, this.messageSource.getMessage(e.getMessage(), e.getArgs(), LocaleContextHolder.getLocale()));
			} else {
				mav.addObject(MSG, e.getMessage());
			}
			mav.addObject(URL, req.getRequestURL());

			return mav;
		}

	}

	/**
	 * Servicio exception handler.-
	 *
	 * @param req the req
	 * @param e   the e
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@ExceptionHandler(value = ServicioException.class)
	public ModelAndView servicioExceptionHandler(final HttpServletRequest req, final ServicioException e)
					throws Exception {

		if ( e.getMessage().equals("Criptografia.desencriptar.IOException") ) {
			log.error(req.getRequestURL().append('?').append(req.getQueryString()) + " - Params: " + WebUtils.paramsToString(req) + " - Criptografia.desencriptar.IOException");
			return new ModelAndView("redirect:/" );
		}
		log.error(req.getRequestURL().append('?').append(req.getQueryString()) + " - Params: " + WebUtils.paramsToString(req) ,e);

		if (e.isAjax()) {
			return null;
		} else {
			final ModelAndView mav = new ModelAndView("error");
			mav.addObject(MODO_DEBUG, CheckExecutionParametersManager.getParameter(DefaultGlobalNames.PAREMETRO_MODO_DEBUG));
			mav.addObject(EXCEPTION, e);
			mav.addObject(TRAZA, StackTraceUtil.getStackTrace(e));
			if (e.getPendienteLocalizar()) {
				mav.addObject(MSG, this.messageSource.getMessage(e.getMessage(), new Object[] {}, LocaleContextHolder.getLocale()));
			} else {
				mav.addObject(MSG, e.getMessage());

			}
			mav.addObject(URL, req.getRequestURL());

			return mav;
		}
	}

	/**
	 * Default error handler.
	 *
	 * @param req the req
	 * @param e   the e
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(final HttpServletRequest req, final Exception e) throws Exception {

		if ( e.getMessage().equals("Criptografia.desencriptar.IOException") ) {
			log.error(req.getRequestURL().append('?').append(req.getQueryString()) + " - Params: " + WebUtils.paramsToString(req) + " - Criptografia.desencriptar.IOException");
			return new ModelAndView("redirect:/" );
		}
		log.error(req.getRequestURL().append('?').append(req.getQueryString()) + " - Params: " + WebUtils.paramsToString(req) ,e);

		final ModelAndView mav = new ModelAndView("error");
		mav.addObject(MODO_DEBUG, CheckExecutionParametersManager.getParameter(DefaultGlobalNames.PAREMETRO_MODO_DEBUG));
		mav.addObject(EXCEPTION, e);
		mav.addObject(TRAZA, StackTraceUtil.getStackTrace(e));

		final String msg = this.messageSource.getMessage("error.generico", new Object[] {}, LocaleContextHolder.getLocale()) + "(" + e.getClass().getCanonicalName() + ")";
		mav.addObject(MSG, msg);
		mav.addObject(URL, req.getRequestURL());

		return mav;
	}
}