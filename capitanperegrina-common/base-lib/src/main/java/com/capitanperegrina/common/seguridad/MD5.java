package com.capitanperegrina.common.seguridad;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

/**
 * Clase utilidad para cálculos de MD5.
 */
public class MD5
{
	static Logger log  = Logger.getLogger( MD5.class );
    
	private MD5() {}
	
    /**
     * Conviertert a hexadicimal
     *
     * @param datos - byte[]
     * @return String
     */
    private static String convertToHex( byte[] datos )
    {
        StringBuilder buf = new StringBuilder();
        for ( int i = 0; i < datos.length; i++ )
        {
            int halfbyte = ( datos[i] >>> 4 ) & 0x0F;
            int twoHalfs = 0;
            do
            {
                if ( ( 0 <= halfbyte ) && ( halfbyte <= 9 ) )
                    buf.append( (char) ( '0' + halfbyte ) );
                else
                    buf.append( (char) ( 'a' + ( halfbyte - 10 ) ) );
                halfbyte = datos[i] & 0x0F;
            }
            while ( twoHalfs++ < 1 );
        }
        return buf.toString();
    }
    
    public static String getMD5( String texto )
    {
    	return getMD5( texto, "iso-8859-1" );
    }

    /**
     * Retorna el valor MD5 de una cadena
     *
     * @param texto - String cadena de la que obtener el valor MD5
     * @param encoding - String codificación de la cadena
     * @return String valor MD5 calculado
     */
    public static String getMD5( String texto, String encoding )
    {
        try
        {
            MessageDigest md;
            md = MessageDigest.getInstance( "MD5" );
            md.update( texto.getBytes( encoding ), 0, texto.length() );
            return convertToHex( md.digest() );
        }
        catch ( Exception e ) {
        	log.info( "", e );
        }
        return "";
    }
    
	/**
	 * Calcula un array md5 a partir de una cadena
	 * @param contenido cadena de la que calcular el md5
	 * @return array de bytes md5
	 */
	public static final byte[] calculaMD5( String contenido )
	{
		byte[] md5hash = new byte[32];
		try
		{
	        MessageDigest md;
	        md = MessageDigest.getInstance( "MD5" );
	        md.update( contenido.getBytes(), 0, contenido.length() );
	        md5hash = md.digest();
		}
		catch ( Exception e ) 
		{
			log.info( "", e );
		}
		return md5hash;
	}
}