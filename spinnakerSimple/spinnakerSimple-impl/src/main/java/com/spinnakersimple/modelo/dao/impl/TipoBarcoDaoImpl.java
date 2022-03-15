package com.spinnakersimple.modelo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.capitanperegrina.common.utils.Cadenas;
import com.spinnakersimple.modelo.dao.TipoBarcoDao;
import com.spinnakersimple.modelo.dao.rowmapper.TipoBarcoRowMapper;
import com.spinnakersimple.modelo.entidad.TipoBarcoPOJO;

// TODO: Auto-generated Javadoc
/**
 * The Class TipoBarcoDaoImpl.
 */
@Repository
public class TipoBarcoDaoImpl implements TipoBarcoDao
{

    /** The jdbc template. */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /** The log. */
    static Logger log = Logger.getLogger( TipoBarcoDaoImpl.class );

    /** The Constant ENTIDAD. */
    public static final String ENTIDAD = TipoBarcoPOJO.class.getName();

    /** The Constant TABLA. */
    public static final String TABLA = "tipo_barco";

    /** The Constant CAMPOS. */
    public static final String CAMPOS = "id_tipo_barco, tipo_barco, tipo_clase, orden ";

    /** The Constant CAMPOSINSERT. */
    public static final String CAMPOSINSERT = " ?, ?, ?, ? ";

    /** The Constant CAMPOSUPDATE. */
    public static final String CAMPOSUPDATE = " tipo_barco = ?, tipo_clase = ?, orden = ? ";

    /** The Constant CAMPOSPKUPDATE. */
    public static final String CAMPOSPKUPDATE = " id_tipo_barco = ? ";

    /**
     * To params todos.
     *
     * @param obj the obj
     * @return the object[]
     */
    private static Object[] toParamsTodos( final TipoBarcoPOJO obj ) {
        return ArrayUtils.addAll( toParamsClave( obj ) , toParamsResto( obj ) );
    }

    /**
     * To params update.
     *
     * @param obj the obj
     * @return the object[]
     */
    private static Object[] toParamsUpdate( final TipoBarcoPOJO obj ) {
        return ArrayUtils.addAll( toParamsResto( obj ), toParamsClave( obj ) );
    }

    /**
     * To params clave.
     *
     * @param obj the obj
     * @return the object[]
     */
    private static Object[] toParamsClave( final TipoBarcoPOJO obj ) {
        return new Object[] {
            obj.getIdTipoBarco()
         };
    }

    /**
     * To params resto.
     *
     * @param obj the obj
     * @return the object[]
     */
    private static Object[] toParamsResto( final TipoBarcoPOJO obj ) {
        return new Object[] {
            obj.getTipoBarco(),
            obj.getTipoClase(),
            obj.getOrden()
        };
    }

    /* (non-Javadoc)
     * @see com.spinnakersimple.modelo.dao.TipoBarcoDao#crea(com.spinnakersimple.modelo.entidad.TipoBarcoPOJO)
     */
    @Override
	public void crea( final TipoBarcoPOJO obj )
    {
        try
        {
        	final StringBuilder q = new StringBuilder("");
        	final Object[] p = toParamsTodos( obj );
        	q.append( "INSERT INTO " + TABLA + " ( " + CAMPOS + " ) \n" );
        	q.append( "VALUES ( " + CAMPOSINSERT + " ) \n" );

        	log.trace( Cadenas.generaQuery( q, p ) );
        	this.jdbcTemplate.update( q.toString(), p );
        }
        catch ( final DuplicateKeyException e )
        {
        	log.debug( "Clave duplicada al intentar insertar la informaci�n.", e );
        	throw e;
        }
    }

    /* (non-Javadoc)
     * @see com.spinnakersimple.modelo.dao.TipoBarcoDao#existe(com.spinnakersimple.modelo.entidad.TipoBarcoPOJO)
     */
    @Override
	public boolean existe ( final TipoBarcoPOJO obj )
    {
        final StringBuilder q = new StringBuilder("");
        final Object[] p = toParamsClave( obj );
        q.append( " SELECT COUNT(*) \n" );
        q.append( "   FROM " + TABLA +" \n");
        q.append( "  WHERE  id_tipo_barco = ?   \n");

        log.trace( Cadenas.generaQuery( q, p ) );
        return ( this.jdbcTemplate.queryForObject( q.toString(), p, Integer.class ) ) > 0;
    }

    /* (non-Javadoc)
     * @see com.spinnakersimple.modelo.dao.TipoBarcoDao#busca(com.spinnakersimple.modelo.entidad.TipoBarcoPOJO)
     */
    @Override
	public TipoBarcoPOJO busca( final TipoBarcoPOJO obj )
    {
        try
        {
        	final StringBuilder q = new StringBuilder("");
        	final Object[] p = toParamsClave( obj );
        	q.append( " SELECT " + CAMPOS + " \n");
        	q.append( "   FROM " + TABLA + " \n");
        	q.append( "  WHERE  id_tipo_barco = ?   \n");

        	log.trace( Cadenas.generaQuery( q, p ) );
        return this.jdbcTemplate.queryForObject( q.toString(), p, new TipoBarcoRowMapper() );
        }
        catch ( final EmptyResultDataAccessException e )
        {
        	log.debug( "No se ha encontrado la informaci�n buscada.", e );
        	throw e;
        }
    }

    /* (non-Javadoc)
     * @see com.spinnakersimple.modelo.dao.TipoBarcoDao#actualiza(com.spinnakersimple.modelo.entidad.TipoBarcoPOJO)
     */
    @Override
	public void actualiza( final TipoBarcoPOJO obj )
    {
		try
		{
        	final StringBuilder q = new StringBuilder("");
        	final Object[] p = toParamsUpdate( obj );
        	q.append( " UPDATE " + TABLA + " \n"  );
        	q.append( "    SET " + CAMPOSUPDATE + " \n" );
        	q.append( "  WHERE " + CAMPOSPKUPDATE + " \n" );

   	    	log.trace( Cadenas.generaQuery( q, p ) );
        	this.jdbcTemplate.update( q.toString(), p );
		}
		catch ( final EmptyResultDataAccessException e )
		{
			log.debug( "El registro que se desea actualizar no existe.", e );
			throw e;
		}
		catch ( final DuplicateKeyException e )
		{
			log.debug( "Clave duplicada al intentar actualizar la informaci�n.", e );
			throw e;
		}
    }

    /* (non-Javadoc)
     * @see com.spinnakersimple.modelo.dao.TipoBarcoDao#borra(com.spinnakersimple.modelo.entidad.TipoBarcoPOJO)
     */
    @Override
	public void borra( final TipoBarcoPOJO obj )
    {
		try
		{
        	final StringBuilder q = new StringBuilder("");
        	final Object[] p = toParamsClave( obj );
        	q.append( " DELETE FROM " + TABLA + " \n" );
        	q.append( "  WHERE  id_tipo_barco = ?   \n");

        	log.trace( Cadenas.generaQuery( q, p ) );
        	this.jdbcTemplate.update( q.toString(), p );
		}
		catch ( final EmptyResultDataAccessException e )
		{
			log.debug( "El registro que se desea eliminar no existe.", e );
			throw e;
		}
    }

    /* (non-Javadoc)
     * @see com.spinnakersimple.modelo.dao.TipoBarcoDao#buscaTodos()
     */
    @Override
	public List<TipoBarcoPOJO> buscaTodos()
    {
        final StringBuilder q = new StringBuilder("");
        final Object[] p = {};
        q.append( " SELECT " + CAMPOS + " FROM " + TABLA + " ORDER BY orden");

        log.trace( Cadenas.generaQuery( q, p ) );
        return this.jdbcTemplate.query( q.toString(), new TipoBarcoRowMapper() );
    }

    /* (non-Javadoc)
     * @see com.spinnakersimple.modelo.dao.TipoBarcoDao#buscaVarios(com.spinnakersimple.modelo.entidad.TipoBarcoPOJO)
     */
    @Override
	public List<TipoBarcoPOJO> buscaVarios( final TipoBarcoPOJO obj )
    {
        if ( obj == null )
        {
            return buscaTodos();
        }

        final StringBuilder q = new StringBuilder("");
        q.append( " SELECT " + CAMPOS + " \n" );
        q.append( "   FROM " + TABLA + " \n");
        q.append( "  WHERE 1 = 1 \n");

        final List<Object> parametros = new ArrayList<Object>();

        if ( obj.getIdTipoBarco() != null )
        {
            q.append( "   AND id_tipo_barco = ? \n" );
            parametros.add( obj.getIdTipoBarco() );
        }
        if ( obj.getTipoBarco() != null )
        {
            q.append( "   AND tipo_barco = ? \n" );
            parametros.add( obj.getTipoBarco() );
        }
        if ( obj.getTipoClase() != null )
        {
            q.append( "   AND tipo_clase = ? \n" );
            parametros.add( obj.getTipoClase() );
        }
        if ( obj.getOrden() != null )
        {
            q.append( "   AND orden = ? \n" );
            parametros.add( obj.getOrden() );
        }
        final Object[] p = parametros.toArray();

        log.trace( Cadenas.generaQuery( q, p ) );
        return this.jdbcTemplate.query( q.toString(), p, new TipoBarcoRowMapper() );
    }
}