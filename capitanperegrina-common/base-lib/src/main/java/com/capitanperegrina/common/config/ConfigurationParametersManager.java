package com.capitanperegrina.common.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.capitanperegrina.common.exceptions.ServicioErrorException;

/**
 * @author Carlos N&uacute;ñez Garc&iacute;a
 */
public final class ConfigurationParametersManager
{
	private static final Logger log = Logger.getLogger( ConfigurationParametersManager.class );

    private static final String JNDI_PREFIX = "java:comp/env/";

    private static boolean usesJNDI;
    private static Map<String,String> parameters = new HashMap<String,String>();

    static
    {
        try
        {
            ClassLoader classLoader = ConfigurationParametersManager.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(DefaultGlobalNames.CONFIGURATIONPARAMETERS);
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();

            usesJNDI = false;

            for ( final String name: properties.stringPropertyNames() )
            {
                parameters.put(name, properties.getProperty(name));
            }
        }
        catch ( IOException e )
        {
        	log.info( "", e );
            usesJNDI = true;
            parameters = Collections.synchronizedMap( new HashMap<String,String>() );
        }
    }
    
    private ConfigurationParametersManager()
    {
    	super();
    }

    /**
     * Busca parámetro en el contexto de la aplicación
     * @param name Nombre del parámetro a buscar
     * @return El valor del parámetro
     */
    public static String getParameter(String name)
    {
        String value = parameters.get(name);
        if (value == null)
        {
            if ( usesJNDI )
            {
                try
                {
                    InitialContext initialContext = new InitialContext();

                    value = (String) initialContext.lookup(JNDI_PREFIX + name);
                    parameters.put( name, value );
                }
                catch ( NamingException e )
                {
                	log.error( "", e );
                	String[] args = { name };
                	throw new ServicioErrorException( "ConfigurationParametersManager.getParameter.NamingException", args, e );
                }
            }
            else
            {
            	String[] args = { name };
            	throw new ServicioErrorException( "ConfigurationParametersManager.getParameter.notFound", args );
            }
        }
        return value;
    }
}
