package com.capitanperegrina.common.modelo.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.capitanperegrina.common.modelo.entidad.tabla.ParamsPOJO;
import com.capitanperegrina.common.modelo.entidad.tabla.pk.ParamsPKPOJO;
import com.capitanperegrina.common.utils.Cadenas;
import com.capitanperegrina.common.utils.Fecha;

/**
 * Rowmapper de la tabla <code>params</code> a la clase ParamsPOJO
 */
public class ParamsRowMapper implements RowMapper<ParamsPOJO>
{
    /**
     * M�todo que mapea un registro de la tabla params con un la clase ParamsPOJO.
     * @param rs Resultset del que extraer la informaci�n
     * @param rowNum N�mero de fila del resultSet del que obtener la informaci�n.
     * @return POJO con la informaci�n mapeada
     */
    @Override
    public ParamsPOJO mapRow( ResultSet rs, int rowNum ) throws SQLException
    {
        ParamsPKPOJO pk = new ParamsPKPOJO();
        ParamsPOJO obj = new ParamsPOJO();
        obj.setPk( pk );

        obj.getPk().setIdParam( Cadenas.trimNoNulo( rs.getString( "id_param" ) ) );
        obj.setVisible( Cadenas.trimNoNulo( rs.getString( "visible" ) ) );
        obj.setValor( Cadenas.trimNoNulo( rs.getString( "valor" ) ) );
        obj.setTipoJava( Cadenas.trimNoNulo( rs.getString( "tipo_java" ) ) );
        obj.setTipoJs( Cadenas.trimNoNulo( rs.getString( "tipo_js" ) ) );
        obj.setIdUsuarioAlta( new Integer( rs.getInt( "id_usuario_alta" ) ) );
        if ( rs.wasNull() )
        {
            obj.setIdUsuarioAlta( null );
        }
        obj.setFecAlta( Fecha.bbdd2Calendar( rs.getTimestamp( "fec_alta" ) ) );
        obj.setIdUsuarioModif( new Integer( rs.getInt( "id_usuario_modif" ) ) );
        if ( rs.wasNull() )
        {
            obj.setIdUsuarioModif( null );
        }
        obj.setFecModif( Fecha.bbdd2Calendar( rs.getTimestamp( "fec_modif" ) ) );

    	return obj;
    }
}
