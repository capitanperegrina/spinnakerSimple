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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.capitanperegrina.common.utils.Cadenas;
import com.spinnakersimple.modelo.dao.TipoVelaDao;
import com.spinnakersimple.modelo.dao.rowmapper.TipoVelaRowMapper;
import com.spinnakersimple.modelo.entidad.TipoVelaPOJO;

/**
 * The Class TipoVelaDaoImpl.
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public class TipoVelaDaoImpl implements TipoVelaDao {

    /** The jdbc template. */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /** The log. */
    static Logger log = Logger.getLogger( TipoVelaDao.class );

    /** The Constant ENTIDAD. */
    public static final String ENTIDAD = TipoVelaPOJO.class.getName();

    /** The Constant TABLA. */
    public static final String TABLA = "tipo_vela";

    /** The Constant CAMPOS. */
    public static final String CAMPOS = "id_tipo_vela, tipo_vela, url_tipo_vela, imagen_tipo_vela, gratil, baluma, pujamen, caida_proa, caida_popa, superficie, tipo_cometa ";

    /** The Constant CAMPOSINSERT. */
    public static final String CAMPOSINSERT = " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ";

    /** The Constant CAMPOSUPDATE. */
    public static final String CAMPOSUPDATE = " tipo_vela = ?, url_tipo_vela = ?, imagen_tipo_vela = ?, gratil = ?, baluma = ?, pujamen = ?, caida_proa = ?, caida_popa = ?, superficie = ?, tipo_cometa = ? ";

    /** The Constant CAMPOSPKUPDATE. */
    public static final String CAMPOSPKUPDATE = " id_tipo_vela = ? ";

    /**
     * To params todos.
     *
     * @param obj the obj
     * @return the object[]
     */
    private static Object[] toParamsTodos( final TipoVelaPOJO obj ) {
        return ArrayUtils.addAll( toParamsClave( obj ) , toParamsResto( obj ) );
    }

    /**
     * To params update.
     *
     * @param obj the obj
     * @return the object[]
     */
    private static Object[] toParamsUpdate( final TipoVelaPOJO obj ) {
        return ArrayUtils.addAll( toParamsResto( obj ), toParamsClave( obj ) );
    }

    /**
     * To params clave.
     *
     * @param obj the obj
     * @return the object[]
     */
    private static Object[] toParamsClave( final TipoVelaPOJO obj ) {
        return new Object[] {
            obj.getIdTipoVela()
         };
    }

    /**
     * To params resto.
     *
     * @param obj the obj
     * @return the object[]
     */
    private static Object[] toParamsResto( final TipoVelaPOJO obj ) {
        return new Object[] {
            obj.getTipoVela(),
            obj.getUrlTipoVela(),
            obj.getImagenTipoVela(),
            obj.getGratil(),
            obj.getBaluma(),
            obj.getPujamen(),
            obj.getCaidaProa(),
            obj.getCaidaPopa(),
            obj.getSuperficie(),
            obj.getTipoCometa()
        };
    }

    /* (non-Javadoc)
     * @see com.spinnakersimple.modelo.dao.TipoVelaDao#crea(com.spinnakersimple.modelo.entidad.TipoVelaPOJO)
     */
    @Override
	public void crea( final TipoVelaPOJO obj )
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
        	log.debug( "Clave duplicada al intentar insertar la información.", e );
        	throw e;
        }
    }

    /* (non-Javadoc)
     * @see com.spinnakersimple.modelo.dao.TipoVelaDao#existe(com.spinnakersimple.modelo.entidad.TipoVelaPOJO)
     */
    @Override
	public boolean existe ( final TipoVelaPOJO obj )
    {
        final StringBuilder q = new StringBuilder("");
        final Object[] p = toParamsClave( obj );
        q.append( " SELECT COUNT(*) \n" );
        q.append( "   FROM " + TABLA +" \n");
        q.append( "  WHERE  id_tipo_vela = ?   \n");

        log.trace( Cadenas.generaQuery( q, p ) );
        return ( this.jdbcTemplate.queryForObject( q.toString(), p, Integer.class ) ) > 0;
    }

    /* (non-Javadoc)
     * @see com.spinnakersimple.modelo.dao.TipoVelaDao#busca(com.spinnakersimple.modelo.entidad.TipoVelaPOJO)
     */
    @Override
	public TipoVelaPOJO busca( final TipoVelaPOJO obj )
    {
        try
        {
        	final StringBuilder q = new StringBuilder("");
        	final Object[] p = toParamsClave( obj );
        	q.append( " SELECT " + CAMPOS + " \n");
        	q.append( "   FROM " + TABLA + " \n");
        	q.append( "  WHERE  id_tipo_vela = ?   \n");

        	log.trace( Cadenas.generaQuery( q, p ) );
        return this.jdbcTemplate.queryForObject( q.toString(), p, new TipoVelaRowMapper() );
        }
        catch ( final EmptyResultDataAccessException e )
        {
        	log.debug( "No se ha encontrado la información buscada.", e );
        	throw e;
        }
    }

    /* (non-Javadoc)
     * @see com.spinnakersimple.modelo.dao.TipoVelaDao#actualiza(com.spinnakersimple.modelo.entidad.TipoVelaPOJO)
     */
    @Override
	public void actualiza( final TipoVelaPOJO obj )
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
			log.debug( "Clave duplicada al intentar actualizar la información.", e );
			throw e;
		}
    }

    /* (non-Javadoc)
     * @see com.spinnakersimple.modelo.dao.TipoVelaDao#borra(com.spinnakersimple.modelo.entidad.TipoVelaPOJO)
     */
    @Override
	public void borra( final TipoVelaPOJO obj )
    {
		try
		{
        	final StringBuilder q = new StringBuilder("");
        	final Object[] p = toParamsClave( obj );
        	q.append( " DELETE FROM " + TABLA + " \n" );
        	q.append( "  WHERE  id_tipo_vela = ?   \n");

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
     * @see com.spinnakersimple.modelo.dao.TipoVelaDao#buscaTodos()
     */
    @Override
	public List<TipoVelaPOJO> buscaTodos()
    {
        final StringBuilder q = new StringBuilder("");
        final Object[] p = {};
        q.append( " SELECT " + CAMPOS + " \n" );
        q.append( "   FROM " + TABLA + " \n");

        log.trace( Cadenas.generaQuery( q, p ) );
        return this.jdbcTemplate.query( q.toString(), new TipoVelaRowMapper() );
    }

    /* (non-Javadoc)
     * @see com.spinnakersimple.modelo.dao.TipoVelaDao#buscaVarios(com.spinnakersimple.modelo.entidad.TipoVelaPOJO)
     */
    @Override
	public List<TipoVelaPOJO> buscaVarios( final TipoVelaPOJO obj )
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

        if ( obj.getIdTipoVela() != null )
        {
            q.append( "   AND id_tipo_vela = ? \n" );
            parametros.add( obj.getIdTipoVela() );
        }
        if ( obj.getTipoVela() != null )
        {
            q.append( "   AND tipo_vela = ? \n" );
            parametros.add( obj.getTipoVela() );
        }
        if ( obj.getUrlTipoVela() != null )
        {
            q.append( "   AND url_tipo_vela = ? \n" );
            parametros.add( obj.getUrlTipoVela() );
        }
        if ( obj.getImagenTipoVela() != null )
        {
            q.append( "   AND imagen_tipo_vela = ? \n" );
            parametros.add( obj.getImagenTipoVela() );
        }
        if ( obj.getGratil() != null )
        {
            q.append( "   AND gratil = ? \n" );
            parametros.add( obj.getGratil() );
        }
        if ( obj.getBaluma() != null )
        {
            q.append( "   AND baluma = ? \n" );
            parametros.add( obj.getBaluma() );
        }
        if ( obj.getPujamen() != null )
        {
            q.append( "   AND pujamen = ? \n" );
            parametros.add( obj.getPujamen() );
        }
        if ( obj.getCaidaProa() != null )
        {
            q.append( "   AND caida_proa = ? \n" );
            parametros.add( obj.getCaidaProa() );
        }
        if ( obj.getCaidaPopa() != null )
        {
            q.append( "   AND caida_popa = ? \n" );
            parametros.add( obj.getCaidaPopa() );
        }
        if ( obj.getSuperficie() != null )
        {
            q.append( "   AND superficie = ? \n" );
            parametros.add( obj.getSuperficie() );
        }
        if ( obj.getTipoCometa() != null )
        {
            q.append( "   AND tipo_cometa = ? \n" );
            parametros.add( obj.getTipoCometa() );
        }
        final Object[] p = parametros.toArray();

        log.trace( Cadenas.generaQuery( q, p ) );
        return this.jdbcTemplate.query( q.toString(), p, new TipoVelaRowMapper() );
    }

	public List<TipoVelaPOJO> buscaTiposDeVelaPorTipoBarco( final Integer idTipoBarco )
    {
        final StringBuilder q = new StringBuilder();
        final List<Object> p = new ArrayList<Object>();

        q.append( "select " + Cadenas.prefijaCamposTabla(CAMPOS, "v" ) + " " );
        q.append( "from tipo_vela v, tipo_barco_vela bv " );
        q.append( "where v.id_tipo_vela = bv.id_tipo_vela " );
        q.append( "and bv.id_tipo_barco = ?;" );
        p.add( idTipoBarco );

        log.trace( Cadenas.generaQuery( q, p ) );
        return this.jdbcTemplate.query( q.toString(), p.toArray(), new TipoVelaRowMapper() );
    }
}