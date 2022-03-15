package com.capitanperegrina.usuarios.modelo.dao.rowmapper.tabla;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.capitanperegrina.common.utils.Cadenas;
import com.capitanperegrina.common.utils.Fecha;
import com.capitanperegrina.usuarios.modelo.entidad.tabla.UsuarioParamsPOJO;
import com.capitanperegrina.usuarios.modelo.entidad.tabla.pk.UsuarioParamsPKPOJO;

/**
 * Rowmapper de la tabla <code>usuario_params</code> a la clase UsuarioParamsPOJO
 */
public class UsuarioParamsRowMapper implements RowMapper<UsuarioParamsPOJO>
{
    /**
     * M�todo que mapea un registro de la tabla usuario_params con un la clase UsuarioParamsPOJO.
     * @param rs Resultset del que extraer la informaci�n
     * @param rowNum N�mero de fila del resultSet del que obtener la informaci�n.
     * @return POJO con la informaci�n mapeada
     */
    @Override
    public UsuarioParamsPOJO mapRow( ResultSet rs, int rowNum ) throws SQLException
    {
        UsuarioParamsPKPOJO pk = new UsuarioParamsPKPOJO();
        UsuarioParamsPOJO obj = new UsuarioParamsPOJO();
        obj.setPk( pk );

        obj.getPk().setIdUsuario( new Integer( rs.getInt( "id_usuario" ) ) );
        if ( rs.wasNull() )
        {
            obj.getPk().setIdUsuario( null );
        }
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
