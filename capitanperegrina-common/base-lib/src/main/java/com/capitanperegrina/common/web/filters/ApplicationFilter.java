package com.capitanperegrina.common.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.capitanperegrina.common.web.ContextoAplicacion;

/**
 * Filtro de aplicación
 * @author Carlos Núñez García
 */
public class ApplicationFilter implements Filter {

	static Logger log = Logger.getLogger( ApplicationFilter.class );

	@Override
	public void destroy()
	{
		log.debug( "ApplicationFilter.destroy" );
	}

	@Override
	public void doFilter( final ServletRequest request, final ServletResponse response, final FilterChain chain ) throws IOException, ServletException
	{
		log.debug( "ApplicationFilter.doFilter" );

		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		log.debug( httpRequest.getServletPath() );

		request.setAttribute( "_version", ContextoAplicacion.getVersionDelPom() );

		chain.doFilter(request, response);
	}

	@Override
	public void init(final FilterConfig config) throws ServletException
	{
		log.debug( "ApplicationFilter.init" );
	}
}
