package com.capitanperegrina.common.modelo.dao.impl;

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
import com.capitanperegrina.common.modelo.dao.ParamsDao;
import com.capitanperegrina.common.modelo.dao.rowmapper.ParamsRowMapper;
import com.capitanperegrina.common.modelo.entidad.tabla.ParamsPOJO;
import com.capitanperegrina.common.utils.Cadenas;

/**
 * Objeto de acceso a datos para la tabla <code>params<code>
 */
@Repository
public class ParamsDaoImpl implements ParamsDao
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    static Logger log = Logger.getLogger( ParamsDaoImpl.class );
    public static final String ENTIDAD = ParamsPOJO.class.getName();
    public static final String TABLA = "params";
    public static final String CAMPOS = "id_param, visible, valor, tipo_java, tipo_js, id_usuario_alta, fec_alta, id_usuario_modif, fec_modif ";
    public static final String CAMPOSINSERT = " ?, ?, ?, ?, ?, ?, ?, ?, ? ";
    public static final String CAMPOSUPDATE = " visible = ?, valor = ?, tipo_java = ?, tipo_js = ?, id_usuario_alta = ?, fec_alta = ?, id_usuario_modif = ?, fec_modif = ? ";
    public static final String CAMPOSPKUPDATE = " id_param = ? ";

    private static Object[] toParamsTodos( ParamsPOJO obj ) {
        return ArrayUtils.addAll( toParamsClave( obj ) , toParamsResto( obj ) );
    }

    private static Object[] toParamsUpdate( ParamsPOJO obj ) {
        return ArrayUtils.addAll( toParamsResto( obj ), toParamsClave( obj ) );
    }

    private static Object[] toParamsClave( ParamsPOJO obj ) {
        return new Object[] {
            obj.getPk().getIdParam()
         };
    }

    private static Object[] toParamsResto( ParamsPOJO obj ) {
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

    /* (non-Javadoc)
	 * @see com.capitanperegrina.common.modelo.dao.impl.ParamsDao#crea(com.capitanperegrina.common.modelo.entidad.tabla.ParamsPOJO)
	 */
    @Override
	public void crea( ParamsPOJO obj )
    {
        try
        {
        	StringBuilder q = new StringBuilder("");
        	Object[] p = toParamsTodos( obj );
        	q.append( "INSERT INTO " + TABLA + " ( " + CAMPOS + " ) " );
        	q.append( "VALUES ( " + CAMPOSINSERT + " ) " );

        	log.trace( Cadenas.generaQuery( q, p ) );
        	this.jdbcTemplate.update( q.toString(), p );
        }
        catch ( DuplicateKeyException e )
        {
        	log.debug( "Clave duplicada al intentar insertar la informaci�n.", e );
        	throw e;
        }
    }

    /* (non-Javadoc)
	 * @see com.capitanperegrina.common.modelo.dao.impl.ParamsDao#existe(com.capitanperegrina.common.modelo.entidad.tabla.ParamsPOJO)
	 */
    @Override
	public boolean existe ( ParamsPOJO obj )
    {
        StringBuilder q = new StringBuilder("");
        Object[] p = toParamsClave( obj );
        q.append( "SELECT COUNT(*) " );
        q.append( "FROM " + TABLA +" ");
        q.append( "WHERE  id_param = ?   ");

        log.trace( Cadenas.generaQuery( q, p ) );
        return ( this.jdbcTemplate.queryForObject( q.toString(), p, Integer.class ) ) > 0;
    }

    /* (non-Javadoc)
	 * @see com.capitanperegrina.common.modelo.dao.impl.ParamsDao#busca(com.capitanperegrina.common.modelo.entidad.tabla.ParamsPOJO)
	 */
    @Override
	public ParamsPOJO busca( ParamsPOJO obj ) throws ServicioException
    {
        try
        {
        	StringBuilder q = new StringBuilder("");
        	Object[] p = toParamsClave( obj );
        	q.append( "SELECT " + CAMPOS + " ");
        	q.append( "FROM " + TABLA + " ");
        	q.append( "WHERE  id_param = ?   ");

        	log.trace( Cadenas.generaQuery( q, p ) );
        	return this.jdbcTemplate.queryForObject( q.toString(), p, new ParamsRowMapper() );
        }
        catch ( EmptyResultDataAccessException e )
        {
        	log.debug( "No se ha encontrado la informaci�n buscada.", e );
        	throw new ServicioException(e);
        }
    }

    /* (non-Javadoc)
	 * @see com.capitanperegrina.common.modelo.dao.impl.ParamsDao#actualiza(com.capitanperegrina.common.modelo.entidad.tabla.ParamsPOJO)
	 */
    @Override
	public void actualiza( ParamsPOJO obj )
    {
		try
		{
        	StringBuilder q = new StringBuilder("");
        	Object[] p = toParamsUpdate( obj );
        	q.append( "UPDATE " + TABLA + " "  );
        	q.append( "SET " + CAMPOSUPDATE + " " );
        	q.append( "WHERE " + CAMPOSPKUPDATE + " " );

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

    /* (non-Javadoc)
	 * @see com.capitanperegrina.common.modelo.dao.impl.ParamsDao#borra(com.capitanperegrina.common.modelo.entidad.tabla.ParamsPOJO)
	 */
    @Override
	public void borra( ParamsPOJO obj )
    {
		try
		{
        	StringBuilder q = new StringBuilder("");
        	Object[] p = toParamsClave( obj );
        	q.append( "DELETE FROM " + TABLA + " " );
        	q.append( "WHERE  id_param = ?   ");

        	log.trace( Cadenas.generaQuery( q, p ) );
        	this.jdbcTemplate.update( q.toString(), p );
		}
		catch ( EmptyResultDataAccessException e )
		{
			log.debug( "El registro que se desea eliminar no existe.", e );
			throw e;
		}
    }

    /* (non-Javadoc)
	 * @see com.capitanperegrina.common.modelo.dao.impl.ParamsDao#buscaTodos()
	 */
    @Override
	public List<ParamsPOJO> buscaTodos()
    {
        StringBuilder q = new StringBuilder("");
        Object[] p = {};
        q.append( "SELECT " + CAMPOS + " " );
        q.append( "FROM " + TABLA + " ");

        log.trace( Cadenas.generaQuery( q, p ) );
        return this.jdbcTemplate.query( q.toString(), new ParamsRowMapper() );
    }

    /* (non-Javadoc)
	 * @see com.capitanperegrina.common.modelo.dao.impl.ParamsDao#buscaVarios(com.capitanperegrina.common.modelo.entidad.tabla.ParamsPOJO)
	 */
    @Override
	public List<ParamsPOJO> buscaVarios( ParamsPOJO obj )
    {
        if ( obj == null )
        {
            return buscaTodos();
        }

        StringBuilder q = new StringBuilder("");
        q.append( "SELECT " + CAMPOS + " " );
        q.append( "FROM " + TABLA + " ");
        q.append( "WHERE 1 = 1 ");

        List<Object> parametros = new ArrayList<Object>();

        if ( obj.getPk().getIdParam() != null )
        {
            q.append( "AND id_param = ? " );
            parametros.add( obj.getPk().getIdParam() );
        }
        if ( obj.getVisible() != null )
        {
            q.append( "AND visible = ? " );
            parametros.add( obj.getVisible() );
        }
        if ( obj.getValor() != null )
        {
            q.append( "AND valor = ? " );
            parametros.add( obj.getValor() );
        }
        if ( obj.getTipoJava() != null )
        {
            q.append( "AND tipo_java = ? " );
            parametros.add( obj.getTipoJava() );
        }
        if ( obj.getTipoJs() != null )
        {
            q.append( "AND tipo_js = ? " );
            parametros.add( obj.getTipoJs() );
        }
        if ( obj.getIdUsuarioAlta() != null )
        {
            q.append( "AND id_usuario_alta = ? " );
            parametros.add( obj.getIdUsuarioAlta() );
        }
        if ( obj.getFecAlta() != null )
        {
            q.append( "AND fec_alta = ? " );
            parametros.add( obj.getFecAlta() );
        }
        if ( obj.getIdUsuarioModif() != null )
        {
            q.append( "AND id_usuario_modif = ? " );
            parametros.add( obj.getIdUsuarioModif() );
        }
        if ( obj.getFecModif() != null )
        {
            q.append( "AND fec_modif = ? " );
            parametros.add( obj.getFecModif() );
        }
        Object[] p = parametros.toArray();

        log.trace( Cadenas.generaQuery( q, p ) );
        return this.jdbcTemplate.query( q.toString(), p, new ParamsRowMapper() );
    }

    /* (non-Javadoc)
	 * @see com.capitanperegrina.common.modelo.dao.impl.ParamsDao#historifica(com.capitanperegrina.common.modelo.entidad.tabla.ParamsPOJO, com.capitanperegrina.common.basebeans.CambioEnTabla)
	 */
    @Override
	public void historifica( ParamsPOJO obj, CambioEnTabla ct )
    {
        StringBuilder q = new StringBuilder();
       	Object[] p = toParamsClave( obj );
        q.append("INSERT INTO " + TABLA + "_his "); // CAMBIAR EL ERROR POR EL NOMBRE DE LA TABLA HISTORICA 
        q.append("SELECT NOW(), " + ct.getUsu() + ", " + CAMPOS + " " );
        q.append("  FROM " + TABLA + " " );
        q.append(" WHERE 1=1 " );
        if ( obj.getPk().getIdParam() != null )
        {
            q.append( "AND id_param = ? " );
        }
        this.jdbcTemplate.update(q.toString(), p);
    }
}