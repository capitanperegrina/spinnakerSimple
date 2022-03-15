package com.capitanperegrina.usuarios.web.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.seguridad.recaptcha.ReCaptchaSettings;
import com.capitanperegrina.common.validators.Validadores;

/**
 * Filtro de autentificación
 * @author Carlos Núñez García
 */
public class AuthenticationFilter implements Filter 
{
	static Logger log = Logger.getLogger( AuthenticationFilter.class );

	/** The re captcha settings. */
	@Autowired
	private ReCaptchaSettings reCaptchaSettings;

	List<String> accionesPublicas;
	
	@Override
	public void destroy() 
	{
		log.debug( "SimpleAuthenticationFilter.destroy" );
	}

	@Override
	public void doFilter( final ServletRequest request, final ServletResponse response, final FilterChain chain ) throws IOException, ServletException 
	{
		log.debug( "AuthenticationFilter.doFilter" );
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		log.debug( httpRequest.getServletPath() );
		
		if ( httpRequest.getSession().getAttribute( DefaultGlobalNames.USERSESSION ) == null )
		{
			request.setAttribute( "logged", null );
			if ( ! this.accionesPublicas.contains( httpRequest.getServletPath() ) )
			{
				HttpServletResponse httpResponse = (HttpServletResponse) response;
				httpResponse.sendRedirect("loginForm.action");
				return;				
			}
		}
		else
		{
			request.setAttribute( "logged", "S" );
			request.setAttribute( "user", httpRequest.getSession().getAttribute( DefaultGlobalNames.USERSESSION ) ); 
		}

		String qs = "";
		if ( ! Validadores.estaVacia( httpRequest.getQueryString() ) )
		{
			qs = "&"+ httpRequest.getQueryString();
		}
		request.setAttribute( "queryString", qs );
		request.setAttribute("reCaptchaSiteKey", this.reCaptchaSettings.getSite());
		chain.doFilter(request, response);
	}

	@Override
	public void init(final FilterConfig config) throws ServletException 
	{
		log.debug( "SimpleAuthenticationFilter.init" );

		this.accionesPublicas = new ArrayList<String>();
		this.accionesPublicas.add( "/mensaje.action" );
		this.accionesPublicas.add( "/main.action" );
		this.accionesPublicas.add( "/loginForm.action" );
		this.accionesPublicas.add( "/recoverPassForm.action" );
		this.accionesPublicas.add( "/registerForm.action" );
		this.accionesPublicas.add( "/resetPassForm.action" );
		this.accionesPublicas.add( "/captcha.action" );
		this.accionesPublicas.add( "/certificates.action" );
		this.accionesPublicas.add( "/tablaCertificadosAjax.action" );
		this.accionesPublicas.add( "/generaCertificadoGN.action" );
		
		this.reCaptchaSettings = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext())
				.getBean(ReCaptchaSettings.class);
	}
}
