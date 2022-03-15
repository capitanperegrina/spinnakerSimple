package com.capitanperegrina.usuarios.modelo.dao.impl.tabla;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.capitanperegrina.common.basebeans.CambioEnTabla;
import com.capitanperegrina.common.exceptions.ServicioException;
import com.capitanperegrina.common.utils.Cadenas;
import com.capitanperegrina.usuarios.modelo.dao.rowmapper.tabla.UsuarioRowMapper;
import com.capitanperegrina.usuarios.modelo.entidad.tabla.UsuarioPOJO;

/**
 * Objeto de acceso a datos para la tabla <code>usuario<code>
 */
@Repository("usuarioDao")
public class UsuarioDao
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    static Logger log = Logger.getLogger( UsuarioDao.class );
    public static final String ENTIDAD = UsuarioPOJO.class.getName();
    public static final String TABLA = "usuario";
    public static final String CAMPOS = "id_usuario, mail, nick, pass, ip, fallos_login, id_usuario_alta, fec_alta, id_usuario_modif, fec_modif ";
    public static final String CAMPOSINSERT = " ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ";
    public static final String CAMPOSUPDATE = " mail = ?, nick = ?, pass = ?, ip = ?, fallos_login = ?, id_usuario_alta = ?, fec_alta = ?, id_usuario_modif = ?, fec_modif = ? ";
    public static final String CAMPOSPKUPDATE = " id_usuario = ? ";

    private static Object[] toParamsTodos( UsuarioPOJO obj ) {
        return ArrayUtils.addAll( toParamsClave( obj ) , toParamsResto( obj ) );
    }

    private static Object[] toParamsUpdate( UsuarioPOJO obj ) {
        return ArrayUtils.addAll( toParamsResto( obj ), toParamsClave( obj ) );
    }

    private static Object[] toParamsClave( UsuarioPOJO obj ) {
        return new Object[] {
            obj.getPk().getIdUsuario()
         };
    }

    private static Object[] toParamsResto( UsuarioPOJO obj ) {
        return new Object[] {
            obj.getMail(),
            obj.getNick(),
            obj.getPass(),
            obj.getIp(),
            obj.getFallosLogin(),
            obj.getIdUsuarioAlta(),
            obj.getFecAlta(),
            obj.getIdUsuarioModif(),
            obj.getFecModif()
        };
    }

    /**
     * M�todo que inserta un registro en la tabla <code>usuario</code> de la base de datos.
     * @param obj Objeto POJO con la informaci�n a insertar en la tabla <code>usuario</code> de la base de datos.
     * @throws DuplicateKeyException si ya existe un registro en la tabla <code>usuario</code> de base de datos con igual clave principal.
     */
    public void crea( UsuarioPOJO obj )
    {
        try
        {
        	StringBuilder q = new StringBuilder("");
        	Object[] p = new Object[] {};
        	q.append( "LOCK TABLES " + TABLA + " WRITE;" );
        	log.trace( Cadenas.generaQuery( q, p ) );
        	this.jdbcTemplate.update( q.toString(), p );
        	
        	q = new StringBuilder("");
        	p = new Object[] {};
        	q.append( "SELECT IFNULL( MAX( id_usuario ) + 1, 1 ) FROM " + TABLA + ";" );
        	Integer newId = this.jdbcTemplate.queryForObject( q.toString(), p, Integer.class);

        	q = new StringBuilder("");
        	p = new Object[] {
        			newId,
                    obj.getMail(),
                    obj.getNick(),
                    obj.getPass(),
                    obj.getIp(),
                    obj.getFallosLogin(),
                    newId,
                    obj.getFecAlta(),
                    newId,
                    obj.getFecModif()
            };
        	q.append( "INSERT INTO " + TABLA + " ( " + CAMPOS + " ) \n" );
        	q.append( "VALUES ( " + CAMPOSINSERT + " ) \n" );

        	log.trace( Cadenas.generaQuery( q, p ) );
        	this.jdbcTemplate.update( q.toString(), p );
        }
        catch ( DuplicateKeyException e )
        {
        	log.debug( "Clave duplicada al intentar insertar la información.", e );
        	throw e;
        }
        finally
        {
        	StringBuilder q = new StringBuilder("");
        	Object[] p = new Object[] {};
        	q.append( "UNLOCK TABLES;\n" );

        	log.trace( Cadenas.generaQuery( q, p ) );
        	this.jdbcTemplate.update( q.toString(), p );
        }
    }

    /**
     * M�todo que comprueba si existe un registro en la tabla <code>usuario</code> de la base de datos a partir de su clave principal.
     * @param obj Objeto del que se desea comprobar su existencia en la tabla <code>usuario</code> de la base de datos.
     * @return true si existe un registro en la tabla <code>usuario</code> de la base de datos con la misma clave principal, false si no es as�.
     */
    public boolean existe ( UsuarioPOJO obj )
    {
        StringBuilder q = new StringBuilder("");
        Object[] p = toParamsClave( obj );
        q.append( " SELECT COUNT(*) \n" );
        q.append( "   FROM " + TABLA +" \n");
        q.append( "  WHERE  id_usuario = ?   \n");

        log.trace( Cadenas.generaQuery( q, p ) );
        return ( this.jdbcTemplate.queryForObject( q.toString(), p, Integer.class ) ) > 0;
    }

    /**
     * M�todo que recupera de la base de datos un registro de la tabla <code>usuario</code> y lo devuelve en el objeto UsuarioPOJO.
     * @param obj Objeto UsuarioPOJO con los campos de la clave principal informados.
     * @return El objeto UsuarioPOJO con la informaci�n recuperada de la base de datos.
     * @throws EmptyResultDataAccessException si no se encuentra el resitro en la tabla <code>usuario</code>.
     */
    public UsuarioPOJO busca( UsuarioPOJO obj )
    {
        try
        {
        	StringBuilder q = new StringBuilder("");
        	Object[] p = toParamsClave( obj );
        	q.append( " SELECT " + CAMPOS + " \n");
        	q.append( "   FROM " + TABLA + " \n");
        	q.append( "  WHERE  id_usuario = ?   \n");

        	log.trace( Cadenas.generaQuery( q, p ) );
        	return this.jdbcTemplate.queryForObject( q.toString(), p, new UsuarioRowMapper() );
        }
        catch ( EmptyResultDataAccessException e )
        {
        	log.debug( "No se ha encontrado la informaci�n buscada.", e );
        	throw e;
        }
    }

    /**
     * M�todo que actualiza un registro de la tabla <code>usuario</code> de la base de datos.
     * @param obj Objeto UsuarioPOJO que se quiere actualizar en la base de datos.
     * @throws EmptyResultDataAccessException Si no se encuentra el registro que se desea actualizar en la tabla <code>usuario</code>.
     * @throws DuplicateKeyException si se encuentra m�s de un registro a actualizar en la tabla la tabla <code>usuario</code>.
     */
    public void actualiza( UsuarioPOJO obj )
    {
		try
		{
        	StringBuilder q = new StringBuilder("");
        	Object[] p = toParamsUpdate( obj );
        	q.append( " UPDATE " + TABLA + " \n"  );
        	q.append( "    SET " + CAMPOSUPDATE + " \n" );
        	q.append( "  WHERE " + CAMPOSPKUPDATE + " \n" );

   	    	log.trace( Cadenas.generaQuery( q, p ) );
        	this.jdbcTemplate.update( q.toString(), p );
		}
		catch ( EmptyResultDataAccessException e )
		{
			log.debug( "El registro que se desea actualizar no existe.", e );
			throw e;
		}
		catch ( DuplicateKeyException e )
		{
			log.debug( "Clave duplicada al intentar actualizar la informaci�n.", e );
			throw e;
		}
    }

    /**
     * M�todo que elimina f�sicamente un registro de la tabla <code>usuario</code> de la base de datos a partir de los valores de su clave principal.
     * @param obj Objeto UsuarioPOJO con los campos de la clave principal informados.
     * @throws EmptyResultDataAccessException Si no se encuentra el registro que se desea eliminar en la tabla <code>usuario</code>.
     */
    public void borra( UsuarioPOJO obj )
    {
		try
		{
        	StringBuilder q = new StringBuilder("");
        	Object[] p = toParamsClave( obj );
        	q.append( " DELETE FROM " + TABLA + " \n" );
        	q.append( "  WHERE  id_usuario = ?   \n");

        	log.trace( Cadenas.generaQuery( q, p ) );
        	this.jdbcTemplate.update( q.toString(), p );
		}
		catch ( EmptyResultDataAccessException e )
		{
			log.debug( "El registro que se desea eliminar no existe.", e );
			throw e;
		}
    }

    /**
     * M�todo devuelve una lista con todos los registros de la tabla <code>usuario</code> de la base de datos.
     * @return Una lista con todos los elementos de la tabla <code>usuario</code>.
     */
    public List<UsuarioPOJO> buscaTodos()
    {
        StringBuilder q = new StringBuilder("");
        Object[] p = {};
        q.append( " SELECT " + CAMPOS + " \n" );
        q.append( "   FROM " + TABLA + " \n");

        log.trace( Cadenas.generaQuery( q, p ) );
        return this.jdbcTemplate.query( q.toString(), new UsuarioRowMapper() );
    }

    /**
     * M�todo devuelve una lista con todos los registros de la tabla <code>usuario</code> de la base de datos a partir de los campos de de un POJO.
     * @param obj Objeto UsuarioPOJO con los campos por los que se desea buscar informados.
     * @return Una lista con todos los elementos de la tabla <code>usuario</code> que cumplan los criterios de b�squeda.
     */
    public List<UsuarioPOJO> buscaVarios( UsuarioPOJO obj )
    {
        if ( obj == null )
        {
            return buscaTodos();
        }

        StringBuilder q = new StringBuilder("");
        q.append( " SELECT " + CAMPOS + " \n" );
        q.append( "   FROM " + TABLA + " \n");
        q.append( "  WHERE 1 = 1 \n");

        List<Object> parametros = new ArrayList<Object>();

        if ( obj.getPk().getIdUsuario() != null )
        {
            q.append( "   AND id_usuario = ? \n" );
            parametros.add( obj.getPk().getIdUsuario() );
        }
        if ( obj.getMail() != null )
        {
            q.append( "   AND mail = ? \n" );
            parametros.add( obj.getMail() );
        }
        if ( obj.getNick() != null )
        {
            q.append( "   AND nick = ? \n" );
            parametros.add( obj.getNick() );
        }
        if ( obj.getPass() != null )
        {
            q.append( "   AND pass = ? \n" );
            parametros.add( obj.getPass() );
        }
        if ( obj.getIp() != null )
        {
            q.append( "   AND ip = ? \n" );
            parametros.add( obj.getIp() );
        }
        if ( obj.getFallosLogin() != null )
        {
            q.append( "   AND fallos_login = ? \n" );
            parametros.add( obj.getFallosLogin() );
        }
        if ( obj.getIdUsuarioAlta() != null )
        {
            q.append( "   AND id_usuario_alta = ? \n" );
            parametros.add( obj.getIdUsuarioAlta() );
        }
        if ( obj.getFecAlta() != null )
        {
            q.append( "   AND fec_alta = ? \n" );
            parametros.add( obj.getFecAlta() );
        }
        if ( obj.getIdUsuarioModif() != null )
        {
            q.append( "   AND id_usuario_modif = ? \n" );
            parametros.add( obj.getIdUsuarioModif() );
        }
        if ( obj.getFecModif() != null )
        {
            q.append( "   AND fec_modif = ? \n" );
            parametros.add( obj.getFecModif() );
        }
        Object[] p = parametros.toArray();

        log.trace( Cadenas.generaQuery( q, p ) );
        return this.jdbcTemplate.query( q.toString(), p, new UsuarioRowMapper() );
    }

    /**
     * M�todo hace una copia del objeto en su tabla de hist�rico correspondiente.
     * @param obj Objeto UsuarioPOJO que se desea historificar.
     * @param ct Objeto CambioEnTabla con los datos de qui�n y cuando historifica.
     */
    public void historifica( UsuarioPOJO obj, CambioEnTabla ct )
    {
        StringBuilder q = new StringBuilder();
       	Object[] p = toParamsClave( obj );
        q.append("INSERT INTO " + TABLA + "_his \n"); // CAMBIAR EL ERROR POR EL NOMBRE DE LA TABLA HISTORICA 
        q.append("SELECT NOW(), " + ct.getUsu() + ", " + CAMPOS + " \n" );
        q.append("  FROM " + TABLA + " \n" );
        q.append(" WHERE 1=1 \n" );
        if ( obj.getPk().getIdUsuario() != null )
        {
            q.append( "   AND id_usuario = ? \n" );
        }
        this.jdbcTemplate.update(q.toString(), p);
    }
    
    // PERSONALIZADO
    
    /**
     * Método que recupera de la base de datos un registro de la tabla <code>usuario</code> y lo devuelve en el objeto GnrUsuarioPOJO.
     * @param mail Correo electrónico buscado.
     * @return El objeto GnrUsuarioPOJO con la información recuperada de la base de datos.
     * @throws EmptyResultDataAccessException si no se encuentra el resitro en la tabla <code>usuario</code>.
     */
    public UsuarioPOJO buscaPorMail( String mail )
    {
    	StringBuilder q = new StringBuilder("");
    	Object[] p = { mail };
    	q.append( " SELECT " + CAMPOS + " \n");
    	q.append( "   FROM " + TABLA + " \n");
    	q.append( "  WHERE  mail = ?   \n");

    	log.trace( Cadenas.generaQuery( q, p ) );
    	return this.jdbcTemplate.queryForObject( q.toString(), p, new UsuarioRowMapper() );
    }
    
    public void incrementaFallos( UsuarioPOJO obj )
    {
		try
		{
        	StringBuilder q = new StringBuilder("");
        	Object[] p = { obj.getPk().getIdUsuario() };
        	q.append( " UPDATE usuario \n" ); 
        	q.append( "    SET fallos_login = fallos_login + 1, \n" );
        	q.append( "        id_usuario_modif = 0,  \n" );
        	q.append( "        fec_modif = now()   \n" );
        	q.append( "  WHERE id_usuario = ? ; \n" );
   
        	log.trace( Cadenas.generaQuery( q, p ) );
        	this.jdbcTemplate.update( q.toString(), p );
		}
		catch ( EmptyResultDataAccessException e )
		{
			log.debug( "El registro que se desea actualizar no existe.", e );
			throw e;
		}
    }
    
    public void reseteaFallos( UsuarioPOJO obj )
    {
		try
		{
        	StringBuilder q = new StringBuilder("");
        	Object[] p = { obj.getPk().getIdUsuario(), obj.getPk().getIdUsuario() };
        	q.append( " UPDATE usuario \n" ); 
        	q.append( "    SET fallos_login = 0, \n" );
        	q.append( "        id_usuario_modif = ?,  \n" );
        	q.append( "        fec_modif = now()   \n" );
        	q.append( "  WHERE id_usuario = ? ; \n" );

        	log.trace( Cadenas.generaQuery( q, p ) );
        	this.jdbcTemplate.update( q.toString(), p );
		}
		catch ( EmptyResultDataAccessException e )
		{
			log.debug( "El registro que se desea eliminar no existe.", e );
			throw e;
		}
    }
}