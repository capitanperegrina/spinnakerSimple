package com.capitanperegrina.common.spain;

import javax.security.auth.x500.X500Principal;

import org.apache.log4j.Logger;

/**
 * Utilidades de acceso al DNIe
 */
public class DNIeUtils
{
	static Logger log = Logger.getLogger( DNIeUtils.class );
	
	private DNIeUtils()
	{
	}
	
	/**
	 * Obtiene el DNI de los datos del certificado de un DNIe
	 * @param datos datos del certificado
	 * @return Nif 
	 */
	public static String obtenerNifDNIElectronico( X500Principal datos )
	{
		String nif = "";
		String cadena;

		String valores = datos.getName( X500Principal.RFC1779 );

		int indice = valores.indexOf( "OID.2.5.4.5" );

		if ( indice != -1 )
		{
			cadena = valores.substring( indice + 12, valores.length() );
			int indiceFin = cadena.indexOf( ',' );

			nif = cadena.substring( 0, indiceFin );
		}

		return nif;
	}
}
