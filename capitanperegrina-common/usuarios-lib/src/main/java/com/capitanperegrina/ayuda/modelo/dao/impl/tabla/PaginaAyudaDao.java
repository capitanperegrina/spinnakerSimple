package com.capitanperegrina.ayuda.modelo.dao.impl.tabla;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.capitanperegrina.ayuda.modelo.dao.rowmappers.tabla.PaginaAyudaRowMapper;
import com.capitanperegrina.ayuda.modelo.entidad.tabla.PaginaAyudaPOJO;
import com.capitanperegrina.common.exceptions.ServicioException;
import com.capitanperegrina.common.utils.Cadenas;

/**
 * Objeto de acceso a datos para la tabla <code>paginas_ayuda<code>
 */
@Repository("paginaAyudaDao")
public class PaginaAyudaDao
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    static Logger log = Logger.getLogger( PaginaAyudaDao.class );
    public static final String ENTIDAD = PaginaAyudaPOJO.class.getName();
    public static final String TABLA = "paginas_ayuda";
    public static final String CAMPOS = "id_ayuda, id_form, id_elemento, id_subelemento, titulo, contenido ";
    public static final String CAMPOSINSERT = " ?, ?, ?, ?, ?, ? ";
    public static final String CAMPOSUPDATE = " id_form = ?, id_elemento = ?, id_subelemento = ?, titulo = ?, contenido = ? ";
    public static final String CAMPOSPKUPDATE = " id_ayuda = ? ";

    private static Object[] toParamsTodos( PaginaAyudaPOJO obj ) {
        return ArrayUtils.addAll( toParamsClave( obj ) , toParamsResto( obj ) );
    }

    private static Object[] toParamsUpdate( PaginaAyudaPOJO obj ) {
        return ArrayUtils.addAll( toParamsResto( obj ), toParamsClave( obj ) );
    }

    private static Object[] toParamsClave( PaginaAyudaPOJO obj ) {
        return new Object[] {
            obj.getPk().getIdAyuda()
         };
    }

    private static Object[] toParamsResto( PaginaAyudaPOJO obj ) {
        return new Object[] {
            obj.getIdForm(),
            obj.getIdElemento(),
            obj.getIdSubelemento(),
            obj.getTitulo(),
            obj.getContenido()
        };
    }

    /**
     * Método que inserta un registro en la tabla <code>paginas_ayuda</code> de la base de datos.
     * @param obj Objeto POJO con la información a insertar en la tabla <code>paginas_ayuda</code> de la base de datos.
     * @throws DuplicateKeyException si ya existe un registro en la tabla <code>paginas_ayuda</code> de base de datos con igual clave principal.
     */
    public void crea( PaginaAyudaPOJO obj )
    {
        try
        {
        	StringBuilder q = new StringBuilder("");
        	Object[] p = toParamsTodos( obj );
        	q.append( "INSERT INTO " + TABLA + " ( " + CAMPOS + " ) \n" );
        	q.append( "VALUES ( " + CAMPOSINSERT + " ) \n" );

        	log.trace( Cadenas.generaQuery( q, p ) );
        	this.jdbcTemplate.update( q.toString(), p );

/* ESTE CÓDIGO ES PARA EL CASO DE TABLAS CON CAMPO CLAVE SERIAL
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
        	log.debug( "Clave duplicada al intentar insertar la información.", e );
        	throw e;
        }
    }

    /**
     * Método que comprueba si existe un registro en la tabla <code>paginas_ayuda</code> de la base de datos a partir de su clave principal.
     * @param obj Objeto del que se desea comprobar su existencia en la tabla <code>paginas_ayuda</code> de la base de datos.
     * @return true si existe un registro en la tabla <code>paginas_ayuda</code> de la base de datos con la misma clave principal, false si no es así.
     */
    public boolean existe ( PaginaAyudaPOJO obj )
    {
        StringBuilder q = new StringBuilder("");
        Object[] p = toParamsClave( obj );
        q.append( " SELECT COUNT(*) \n" );
        q.append( "   FROM " + TABLA +" \n");
        q.append( "  WHERE  id_ayuda = ?   \n");

        log.trace( Cadenas.generaQuery( q, p ) );
        return ( this.jdbcTemplate.queryForObject( q.toString(), p, Integer.class ) ) > 0;
    }

    /**
     * Método que recupera de la base de datos un registro de la tabla <code>paginas_ayuda</code> y lo devuelve en el objeto PaginaAyudaPOJO.
     * @param obj Objeto PaginaAyudaPOJO con los campos de la clave principal informados.
     * @return El objeto PaginaAyudaPOJO con la información recuperada de la base de datos.
     * @throws EmptyResultDataAccessException si no se encuentra el resitro en la tabla <code>paginas_ayuda</code>.
     */
    public PaginaAyudaPOJO busca( PaginaAyudaPOJO obj ) throws ServicioException
    {
        try
        {
        	StringBuilder q = new StringBuilder("");
        	Object[] p = toParamsClave( obj );
        	q.append( " SELECT " + CAMPOS + " \n");
        	q.append( "   FROM " + TABLA + " \n");
        	q.append( "  WHERE  id_ayuda = ?   \n");

        	log.trace( Cadenas.generaQuery( q, p ) );
        	return this.jdbcTemplate.queryForObject( q.toString(), p, new PaginaAyudaRowMapper() );
        }
        catch ( EmptyResultDataAccessException e )
        {
        	log.debug( "No se ha encontrado la informaci�n buscada.", e );
        	throw new ServicioException( "PaginaAyudaDao.busca.EmptyResultDataAccessException", e);
        }
    }

    /**
     * Método que actualiza un registro de la tabla <code>paginas_ayuda</code> de la base de datos.
     * @param obj Objeto PaginaAyudaPOJO que se quiere actualizar en la base de datos.
     * @throws EmptyResultDataAccessException Si no se encuentra el registro que se desea actualizar en la tabla <code>paginas_ayuda</code>.
     * @throws DuplicateKeyException si se encuentra más de un registro a actualizar en la tabla la tabla <code>paginas_ayuda</code>.
     */
    public void actualiza( PaginaAyudaPOJO obj )
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
			log.debug( "Clave duplicada al intentar actualizar la información.", e );
			throw e;
		}
    }

    /**
     * Método que elimina físicamente un registro de la tabla <code>paginas_ayuda</code> de la base de datos a partir de los valores de su clave principal.
     * @param obj Objeto PaginaAyudaPOJO con los campos de la clave principal informados.
     * @throws EmptyResultDataAccessException Si no se encuentra el registro que se desea eliminar en la tabla <code>paginas_ayuda</code>.
     */
    public void borra( PaginaAyudaPOJO obj )
    {
		try
		{
        	StringBuilder q = new StringBuilder("");
        	Object[] p = toParamsClave( obj );
        	q.append( " DELETE FROM " + TABLA + " \n" );
        	q.append( "  WHERE  id_ayuda = ?   \n");

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
     * Método devuelve una lista con todos los registros de la tabla <code>paginas_ayuda</code> de la base de datos.
     * @return Una lista con todos los elementos de la tabla <code>paginas_ayuda</code>.
     */
    public List<PaginaAyudaPOJO> buscaTodos()
    {
        StringBuilder q = new StringBuilder("");
        Object[] p = {};
        q.append( " SELECT " + CAMPOS + " \n" );
        q.append( "   FROM " + TABLA + " \n");

        log.trace( Cadenas.generaQuery( q, p ) );
        return this.jdbcTemplate.query( q.toString(), new PaginaAyudaRowMapper() );
    }

    /**
     * Método devuelve una lista con todos los registros de la tabla <code>paginas_ayuda</code> de la base de datos a partir de los campos de de un POJO.
     * @param obj Objeto PaginaAyudaPOJO con los campos por los que se desea buscar informados.
     * @return Una lista con todos los elementos de la tabla <code>paginas_ayuda</code> que cumplan los criterios de búsqueda.
     */
    public List<PaginaAyudaPOJO> buscaVarios( PaginaAyudaPOJO obj )
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

        if ( obj.getPk().getIdAyuda() != null )
        {
            q.append( "   AND id_ayuda = ? \n" );
            parametros.add( obj.getPk().getIdAyuda() );
        }
        if ( obj.getIdForm() != null )
        {
            q.append( "   AND id_form = ? \n" );
            parametros.add( obj.getIdForm() );
        }
        if ( obj.getIdElemento() != null )
        {
            q.append( "   AND id_elemento = ? \n" );
            parametros.add( obj.getIdElemento() );
        }
        if ( obj.getIdSubelemento() != null )
        {
            q.append( "   AND id_subelemento = ? \n" );
            parametros.add( obj.getIdSubelemento() );
        }
        if ( obj.getTitulo() != null )
        {
            q.append( "   AND titulo = ? \n" );
            parametros.add( obj.getTitulo() );
        }
        if ( obj.getContenido() != null )
        {
            q.append( "   AND contenido = ? \n" );
            parametros.add( obj.getContenido() );
        }
        Object[] p = parametros.toArray();

        log.trace( Cadenas.generaQuery( q, p ) );
        return this.jdbcTemplate.query( q.toString(), p, new PaginaAyudaRowMapper() );
    }

    public PaginaAyudaPOJO buscaPorFormElemento( PaginaAyudaPOJO obj ) throws ServicioException
    {
        try
        {
        	StringBuilder q = new StringBuilder("");
        	Object[] p = new Object[] { obj.getIdForm(), obj.getIdElemento(), obj.getIdSubelemento() };
        	q.append( " SELECT " + CAMPOS + " \n");
        	q.append( "   FROM " + TABLA + " \n");
        	q.append( "  WHERE  id_form = ?   \n");
        	q.append( "    AND  id_elemento = ?   \n");
        	q.append( "    AND  id_subelemento = ?   \n");

        	log.trace( Cadenas.generaQuery( q, p ) );
        	return this.jdbcTemplate.queryForObject( q.toString(), p, new PaginaAyudaRowMapper() );
        }
        catch ( EmptyResultDataAccessException e )
        {
        	log.trace( "No se ha encontrado la información buscada.", e );
        	throw new ServicioException( "PaginaAyudaDao.busca.EmptyResultDataAccessException", e);
        }
    }
}