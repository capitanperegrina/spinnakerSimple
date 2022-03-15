package com.capitanperegrina.common.spring;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringFactory {

	static Logger log = Logger.getLogger( SpringFactory.class );

	private static ApplicationContext ctx;

	public static <T> T getBean(final String name)
	{
		try
		{
			if ( ctx == null )
			{
				ctx = new ClassPathXmlApplicationContext( "application-context.xml" );
			}
		}
		catch ( final Exception e )
		{
			log.debug( "", e );
		}
		return (T) ctx.getBean( name );
	}
}
