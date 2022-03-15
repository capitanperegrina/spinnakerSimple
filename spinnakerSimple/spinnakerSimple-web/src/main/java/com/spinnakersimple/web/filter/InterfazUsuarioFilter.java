package com.spinnakersimple.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.servlet.LocaleResolver;

import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.utils.StackTraceUtil;
import com.capitanperegrina.common.validators.Validadores;
import com.capitanperegrina.common.web.ContextoAplicacion;
import com.spinnakersimple.modelo.util.SpinnakerSimpleGlobals;
import com.spinnakersimple.servicios.AuxiliarService;

/**
 * The Class InterfazUsuarioFilter.
 */
@Component("interfazUsuarioFilter")
public class InterfazUsuarioFilter extends GenericFilterBean {

	static Logger log = Logger.getLogger(InterfazUsuarioFilter.class);

	@Autowired
	private AuxiliarService auxiliarService;

	@Autowired
    private LocaleResolver localeResolver;

	@Override
	public void destroy() {
		if (log.isDebugEnabled()) {
			log.debug(StackTraceUtil.getCallerInfo());
		}
	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
					throws IOException, ServletException {
		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_FILTRO_INI + StackTraceUtil.getCallerInfo());
		}

		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		if (log.isDebugEnabled()) {
			log.debug(httpRequest.getServletPath());
		}

		// request.setAttribute(DefaultGlobalNames.LANGSESSION, LocaleContextHolder.getLocale().getLanguage());

		request.setAttribute("appVersion", ContextoAplicacion.obtenVersion());
		request.setAttribute("imagenesCompras", this.auxiliarService.leeImagenCabecera(SpinnakerSimpleGlobals.COMRAS));
		request.setAttribute("imagenesVentas", this.auxiliarService.leeImagenCabecera(SpinnakerSimpleGlobals.VENTAS));

		String qs = "";
		if (!Validadores.estaVacia(httpRequest.getQueryString())) {
			qs = "&" + httpRequest.getQueryString();
		}
		request.setAttribute("queryString", qs);
		chain.doFilter(request, response);
	}

//	@Override
//	public void init(final FilterConfig config) throws ServletException {
//		if (log.isDebugEnabled()) {
//			log.debug(">" + StackTraceUtil.getCallerInfo());
//		}
//		this.auxiliarService = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext()).getBean(AuxiliarServiceImpl.class);
//		if (log.isDebugEnabled()) {
//			log.debug("<" + StackTraceUtil.getCallerInfo());
//		}
//	}
}
