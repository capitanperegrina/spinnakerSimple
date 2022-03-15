package com.capitanperegrina.common.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

public class Networking
{
	private static Logger log  = Logger.getLogger( Networking.class );

	public static String getHostnameString()
	{
		String hostname = "<Desconocido>";
		try
		{
			hostname = InetAddress.getLocalHost().getHostName();
		}
		catch ( final UnknownHostException e )
		{
			log.info( e );
		}
		return hostname;
	}
}