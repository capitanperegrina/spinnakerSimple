package com.spinnakersimple.modelo.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.capitanperegrina.common.utils.Cadenas;
import com.spinnakersimple.modelo.entidad.TipoBarcoPOJO;

/**
 * Rowmapper de la tabla <code>tipo_barco</code> a la clase TipoBarcoPOJO
 */
public class TipoBarcoRowMapper implements RowMapper<TipoBarcoPOJO>
{
    /**
     * Método que mapea un registro de la tabla tipo_barco con un la clase TipoBarcoPOJO.
     * @param rs Resultset del que extraer la información
     * @param rowNum Número de fila del resultSet del que obtener la información.
     * @return POJO con la información mapeada
     */
    @Override
    public TipoBarcoPOJO mapRow( final ResultSet rs, final int rowNum ) throws SQLException
    {
        final TipoBarcoPOJO obj = new TipoBarcoPOJO();

        obj.setIdTipoBarco( new Integer( rs.getInt( "id_tipo_barco" ) ) );
        if ( rs.wasNull() )
        {
            obj.setIdTipoBarco( null );
        }
        obj.setTipoBarco( Cadenas.trimNoNulo( rs.getString( "tipo_barco" ) ) );
        obj.setTipoClase( Cadenas.trimNoNulo( rs.getString( "tipo_clase" ) ) );
        obj.setOrden( new Integer( rs.getInt( "orden" ) ) );
        if ( rs.wasNull() )
        {
            obj.setOrden( null );
        }

    	return obj;
    }
}
