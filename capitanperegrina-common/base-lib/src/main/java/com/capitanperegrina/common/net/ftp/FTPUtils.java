package com.capitanperegrina.common.net.ftp;

import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

import com.capitanperegrina.common.exceptions.ServicioErrorException;

/**
 * Clase de utilidades FTP
 */
public class FTPUtils {
	
	static Logger log = Logger.getLogger( FTPUtils.class );
	
	private FTPUtils() {
		super();
	}
	
	/**
	 * Método para obtener un cliente FTP conectado
	 * @param host Nombre del host o IP
	 * @param port Puerto al que conectar.
	 * @param login Usuario
	 * @param pass Clave de acceso.
	 * @return Objeto Cliente FTP.
	 * @throws ServicioErrorException Si se produce cualquier error.
	 */
	public static FTPClient openFtp( String host, Integer port, String login, String pass ) {
		try {
			FTPClient ftpClient = new FTPClient();
			ftpClient.connect( host, port );
			log.info( ">>> CONNECTAR: " + ftpClient.getReplyString() );
	        ftpClient.login( login, pass );
	        log.info( ">>> ACCEDER: " + ftpClient.getReplyString() );
	        ftpClient.enterLocalPassiveMode();
	        log.info( ">>> MODO PASIVO: " + ftpClient.getReplyString() );
	        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
	        log.info( ">>> TRANSMISION BINARIA: " + ftpClient.getReplyString() );
	        return ftpClient;			
		} catch ( Exception e ) {
			log.error( "", e );
			throw new ServicioErrorException(e );
		}
	}
	
	public static void closeFtp( FTPClient ftpClient ) {
        try {
        	if ( ftpClient != null && ftpClient.isConnected() ) {
        		ftpClient.logout();
        		ftpClient.disconnect();
        	}
        } catch ( IOException e ) {
			log.error( "", e );
			throw new ServicioErrorException(e );
        }		
	}
}
