package com.capitanperegrina.common.net;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

import com.capitanperegrina.common.exceptions.ServicioErrorException;

/**
 * Utilidades pasa simular un navegador
 */
public class BrowserUtils
{
	static Logger log = Logger.getLogger( BrowserUtils.class );
	
	private BrowserUtils()
	{
	}
	
	/**
	 * Obtiene el html publicado en una url
	 * @param url URL que consultar
	 * @return el html publicado en la web
	 */
	public static String getContentResult( URL url )
	{
		try
		{

		    InputStream in = url.openStream();
		    StringBuilder sb = new StringBuilder();

		    byte [] buffer = new byte[256];

		    while ( true ) 
		    {
		        int byteRead = in.read(buffer);
		        if ( byteRead == -1 )
		        {
		        	break;
		        }
		        for ( int i = 0; i < byteRead; i++ )
		        {
		            sb.append( (char) buffer[i] );
		        }
		    }
		    return sb.toString();	
		}
        catch ( IOException e )
        {
        	log.error( "", e );
        	throw new ServicioErrorException( "BrowserUtils.getContentResult.IOException", e );
        }
	}
	
	/**
	 * Obtiene el html generado por una operación post
	 * @param targetURL URL a la que se envía el post
	 * @param urlParameters Parametros enviados como post
	 * @return la respuesta generada por el post en la url.
	 */
	public static String excutePost( String targetURL, String urlParameters )
	{
		URL url;
		HttpURLConnection connection = null;
		try
		{
			// Create connection
			url = new URL( targetURL );
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod( "POST" );
			connection.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded" );

			connection.setRequestProperty( "Content-Length", "" + Integer.toString( urlParameters.getBytes().length ) );
			connection.setRequestProperty( "Content-Language", "es-ES" );

			connection.setUseCaches( false );
			connection.setDoInput( true );
			connection.setDoOutput( true );

			// Send request
			DataOutputStream wr = new DataOutputStream( connection.getOutputStream() );
			wr.writeBytes( urlParameters );
			wr.flush();
			wr.close();

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader( new InputStreamReader( is ) );
			String line;
			StringBuilder response = new StringBuilder();
			while ( ( line = rd.readLine() ) != null )
			{
				response.append( line );
				response.append( '\r' );
			}
			rd.close();
			return response.toString();

		}
        catch ( IOException e )
        {
        	log.error( "", e );
        	throw new ServicioErrorException( "BrowserUtils.excutePost.IOException", e );
        }
		finally
		{

			if ( connection != null )
			{
				connection.disconnect();
			}
		}
	}
}
