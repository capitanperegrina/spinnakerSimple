package com.capitanperegrina.common.sql;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.capitanperegrina.common.utils.StackTraceUtil;

/**
 * Clase de origen de datos jdbc mínima
 */
public class SimpleDataSource extends ParametrosConexion implements DataSource {

	static Logger log  = Logger.getLogger( SimpleDataSource.class );
	
	private static final long serialVersionUID = -339533523334791592L;
	
	private static final String NO_IMPLEMENTADO = "No implementado!";
	
	/**
	 * Constructor por defecto.
	 */
	public SimpleDataSource() {
		super();
	}

	/**
	 * Constructor a partir de un objeto ParametrosConexion.
	 * @param pc Parámetros de la conexión
	 */
    public SimpleDataSource( ParametrosConexion pc ) {
        try {
            this.driverClassName = pc.getDriverClassName();
            this.url = pc.getUrl();
            this.user = pc.getUser();
            this.password = pc.getPassword();
            Class.forName( this.driverClassName );
        } catch ( Exception e ) {
            log.error( StackTraceUtil.getStackTrace(e) );
        }
    }	

    @Override
	public Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection( this.url, this.user, this.password );            
        } catch ( SQLException e ) {
            log.error( StackTraceUtil.getStackTrace(e) );
            throw e;
        }
    }

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		log.warn( NO_IMPLEMENTADO );
		return null;
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		log.warn( NO_IMPLEMENTADO );
		return 0;
	}

	@Override
	public void setLogWriter(PrintWriter arg0) throws SQLException {
		log.warn( NO_IMPLEMENTADO );
		
	}

	@Override
	public void setLoginTimeout(int arg0) throws SQLException {
		log.warn( NO_IMPLEMENTADO );
		
	}

	@Override
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		log.warn( NO_IMPLEMENTADO );
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> arg0) throws SQLException {
		log.warn( NO_IMPLEMENTADO );
		return null;
	}

	@Override
	public Connection getConnection(String arg0, String arg1) throws SQLException {
		log.warn( NO_IMPLEMENTADO );
		return null;
	}

	public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}
}
