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
import com.capitanperegrina.usuarios.modelo.dao.rowmapper.tabla.UsuarioParamsRowMapper;
import com.capitanperegrina.usuarios.modelo.entidad.tabla.UsuarioParamsPOJO;

/**
 * Objeto de acceso a datos para la tabla <code>usuario_params<code>
 */
@Repository
public class UsuarioParamsDao
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    static Logger log = Logger.getLogger( UsuarioParamsDao.class );
    public static final String ENTIDAD = UsuarioParamsPOJO.class.getName();
    public static final String TABLA = "usuario_params";
    public static final String CAMPOS = "id_usuario, id_param, visible, valor, tipo_java, tipo_js, id_usuario_alta, fec_alta, id_usuario_modif, fec_modif ";
    public static final String CAMPOSINSERT = " ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ";
    public static final String CAMPOSUPDATE = " visible = ?, valor = ?, tipo_java = ?, tipo_js = ?, id_usuario_alta = ?, fec_alta = ?, id_usuario_modif = ?, fec_modif = ? ";
    public static final String CAMPOSPKUPDATE = " id_usuario = ? and id_param = ? ";

    private static Object[] toParamsTodos( UsuarioParamsPOJO obj ) {
        return ArrayUtils.addAll( toParamsClave( obj ) , toParamsResto( obj ) );
    }

    private static Object[] toParamsUpdate( UsuarioParamsPOJO obj ) {
        return ArrayUtils.addAll( toParamsResto( obj ), toParamsClave( obj ) );
    }

    private static Object[] toParamsClave( UsuarioParamsPOJO obj ) {
        return new Object[] {
            obj.getPk().getIdUsuario(),
            obj.getPk().getIdParam()
         };
    }

    private static Object[] toParamsResto( UsuarioParamsPOJO obj ) {
        return new Object[] {
            obj.getVisible(),
            obj.getValor(),
            obj.getTipoJava(),
            obj.getTipoJs(),
            obj.getIdUsuarioAlta(),
            obj.getFecAlta(),
            obj.getIdUsuarioModif(),
            obj.getFecModif()
        };
    }

    /**
     * M�todo que inserta un registro en la tabla <code>usuario_params</code> de la base de datos.
     * @param obj Objeto POJO con la informaci�n a insertar en la tabla <code>usuario_params</code> de la base de datos.
     * @throws DuplicateKeyException si ya existe un registro en la tabla <code>usuario_params</code> de base de datos con igual clave principal.
     */
    public void crea( UsuarioParamsPOJO obj )
    {
        try
        {
        	StringBuilder q = new StringBuilder("");
        	Object[] p = toParamsTodos( obj );
        	q.append( "INSERT INTO " + TABLA + " ( " + CAMPOS + " ) \n" );
        	q.append( "VALUES ( " + CAMPOSINSERT + " ) \n" );

        	log.trace( Cadenas.generaQuery( q, p ) );
        	this.jdbcTemplate.update( q.toString(), p );

/* ESTE C�DIGO ES PARA EL CASO DE TABLAS CON CAMPO CLAVE SERIAL
            // * @return El objeto creado con sus campos autogenerados completos.
         	KeyHolder keyHolder = new GeneratedKeyHolder();
        	jdbcTemplate.update(
        	    new PreparedStatementCreator()
        	    {
        	        @Override
        	        public PreparedStatement createPreparedStatement(Connection con) throws SQLException
        	        {
        	            return SQLUtils.cargaVariables(con, q, p, PreparedStatement.RETURN_GENERATED_KEYS );
        	        }
        	    },
        	    keyHolder);
        	obj.getPk().setCLAVE( (Integer) keyHolder.getKey() );
        	return obj;
*/
        }
        catch ( DuplicateKeyException e )
        {
        	log.debug( "Clave duplicada al intentar insertar la informaci�n.", e );
        	throw e;
        }
    }

    /**
     * M�todo que comprueba si existe un registro en la tabla <code>usuario_params</code> de la base de datos a partir de su clave principal.
     * @param obj Objeto del que se desea comprobar su existencia en la tabla <code>usuario_params</code> de la base de datos.
     * @return true si existe un registro en la tabla <code>usuario_params</code> de la base de datos con la misma clave principal, false si no es as�.
     */
    public boolean existe ( UsuarioParamsPOJO obj )
    {
        StringBuilder q = new StringBuilder("");
        Object[] p = toParamsClave( obj );
        q.append( " SELECT COUNT(*) \n" );
        q.append( "   FROM " + TABLA +" \n");
        q.append( "  WHERE  id_usuario = ? \n   AND id_param = ?   \n");

        log.trace( Cadenas.generaQuery( q, p ) );
        return ( this.jdbcTemplate.queryForObject( q.toString(), p, Integer.class ) ) > 0;
    }

    /**
     * M�todo que recupera de la base de datos un registro de la tabla <code>usuario_params</code> y lo devuelve en el objeto UsuarioParamsPOJO.
     * @param obj Objeto UsuarioParamsPOJO con los campos de la clave principal informados.
     * @return El objeto UsuarioParamsPOJO con la informaci�n recuperada de la base de datos.
     * @throws ServicioException si no se encuentra el resitro en la tabla <code>usuario_params</code>.
     */
    public UsuarioParamsPOJO busca( UsuarioParamsPOJO obj ) throws ServicioException
    {
        try
        {
        	StringBuilder q = new StringBuilder("");
        	Object[] p = toParamsClave( obj );
        	q.append( " SELECT " + CAMPOS + " \n");
        	q.append( "   FROM " + TABLA + " \n");
        	q.append( "  WHERE  id_usuario = ? \n   AND id_param = ?   \n");

        	log.trace( Cadenas.generaQuery( q, p ) );
        	return this.jdbcTemplate.queryForObject( q.toString(), p, new UsuarioParamsRowMapper() );
        }
        catch ( EmptyResultDataAccessException e )
        {
        	log.debug( "No se ha encontrado la información buscada.", e );
        	Object[] params = { obj.getPk().getIdParam() + " - " + obj.getPk().getIdUsuario() };
        	throw new ServicioException( "usuarios", "UsuarioParamsDao.buscaPorMail.EmptyResultDataAccessException", params );
        }
    }

    /**
     * M�todo que actualiza un registro de la tabla <code>usuario_params</code> de la base de datos.
     * @param obj Objeto UsuarioParamsPOJO que se quiere actualizar en la base de datos.
     * @throws EmptyResultDataAccessException Si no se encuentra el registro que se desea actualizar en la tabla <code>usuario_params</code>.
     * @throws DuplicateKeyException si se encuentra m�s de un registro a actualizar en la tabla la tabla <code>usuario_params</code>.
     */
    public void actualiza( UsuarioParamsPOJO obj )
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
     * M�todo que elimina f�sicamente un registro de la tabla <code>usuario_params</code> de la base de datos a partir de los valores de su clave principal.
     * @param obj Objeto UsuarioParamsPOJO con los campos de la clave principal informados.
     * @throws EmptyResultDataAccessException Si no se encuentra el registro que se desea eliminar en la tabla <code>usuario_params</code>.
     */
    public void borra( UsuarioParamsPOJO obj )
    {
		try
		{
        	StringBuilder q = new StringBuilder("");
        	Object[] p = toParamsClave( obj );
        	q.append( " DELETE FROM " + TABLA + " \n" );
        	q.append( "  WHERE  id_usuario = ? \n   AND id_param = ?   \n");

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
     * M�todo devuelve una lista con todos los registros de la tabla <code>usuario_params</code> de la base de datos.
     * @return Una lista con todos los elementos de la tabla <code>usuario_params</code>.
     */
    public List<UsuarioParamsPOJO> buscaTodos()
    {
        StringBuilder q = new StringBuilder("");
        Object[] p = {};
        q.append( " SELECT " + CAMPOS + " \n" );
        q.append( "   FROM " + TABLA + " \n");

        log.trace( Cadenas.generaQuery( q, p ) );
        return this.jdbcTemplate.query( q.toString(), new UsuarioParamsRowMapper() );
    }

    /**
     * M�todo devuelve una lista con todos los registros de la tabla <code>usuario_params</code> de la base de datos a partir de los campos de de un POJO.
     * @param obj Objeto UsuarioParamsPOJO con los campos por los que se desea buscar informados.
     * @return Una lista con todos los elementos de la tabla <code>usuario_params</code> que cumplan los criterios de b�squeda.
     */
    public List<UsuarioParamsPOJO> buscaVarios( UsuarioParamsPOJO obj )
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
        if ( obj.getPk().getIdParam() != null )
        {
            q.append( "   AND id_param = ? \n" );
            parametros.add( obj.getPk().getIdParam() );
        }
        if ( obj.getVisible() != null )
        {
            q.append( "   AND visible = ? \n" );
            parametros.add( obj.getVisible() );
        }
        if ( obj.getValor() != null )
        {
            q.append( "   AND valor = ? \n" );
            parametros.add( obj.getValor() );
        }
        if ( obj.getTipoJava() != null )
        {
            q.append( "   AND tipo_java = ? \n" );
            parametros.add( obj.getTipoJava() );
        }
        if ( obj.getTipoJs() != null )
        {
            q.append( "   AND tipo_js = ? \n" );
            parametros.add( obj.getTipoJs() );
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
        return this.jdbcTemplate.query( q.toString(), p, new UsuarioParamsRowMapper() );
    }

    /**
     * M�todo hace una copia del objeto en su tabla de hist�rico correspondiente.
     * @param obj Objeto UsuarioParamsPOJO que se desea historificar.
     * @param ct Objeto CambioEnTabla con los datos de qui�n y cuando historifica.
     */
    public void historifica( UsuarioParamsPOJO obj, CambioEnTabla ct )
    {
        StringBuilder q = new StringBuilder();
       	Object[] p = toParamsClave( obj );
        q.append("INSERT INTO " +  TABLA + "_his \n"); // CAMBIAR EL ERROR POR EL NOMBRE DE LA TABLA HISTORICA 
        q.append("SELECT NOW(), " + ct.getUsu() + ", " + CAMPOS + " \n" );
        q.append("  FROM " + TABLA + " \n" );
        q.append(" WHERE 1=1 \n" );
        if ( obj.getPk().getIdUsuario() != null )
        {
            q.append( "   AND id_usuario = ? \n" );
        }
        if ( obj.getPk().getIdParam() != null )
        {
            q.append( "   AND id_param = ? \n" );
        }
        this.jdbcTemplate.update(q.toString(), p);
    }
}