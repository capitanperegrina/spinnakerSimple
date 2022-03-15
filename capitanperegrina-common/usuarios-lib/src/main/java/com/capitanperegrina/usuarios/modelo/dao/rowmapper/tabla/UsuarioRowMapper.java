package com.capitanperegrina.usuarios.modelo.dao.rowmapper.tabla;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.capitanperegrina.common.utils.Cadenas;
import com.capitanperegrina.common.utils.Fecha;
import com.capitanperegrina.usuarios.modelo.entidad.tabla.UsuarioPOJO;
import com.capitanperegrina.usuarios.modelo.entidad.tabla.pk.UsuarioPKPOJO;

/**
 * Rowmapper de la tabla <code>usuario</code> a la clase UsuarioPOJO
 */
public class UsuarioRowMapper implements RowMapper<UsuarioPOJO>
{
    /**
     * M�todo que mapea un registro de la tabla usuario con un la clase UsuarioPOJO.
     * @param rs Resultset del que extraer la informaci�n
     * @param rowNum N�mero de fila del resultSet del que obtener la informaci�n.
     * @return POJO con la informaci�n mapeada
     */
    @Override
    public UsuarioPOJO mapRow( ResultSet rs, int rowNum ) throws SQLException
    {
        UsuarioPKPOJO pk = new UsuarioPKPOJO();
        UsuarioPOJO obj = new UsuarioPOJO();
        obj.setPk( pk );

        obj.getPk().setIdUsuario( new Integer( rs.getInt( "id_usuario" ) ) );
        if ( rs.wasNull() )
        {
            obj.getPk().setIdUsuario( null );
        }
        obj.setMail( Cadenas.trimNoNulo( rs.getString( "mail" ) ) );
        obj.setNick( Cadenas.trimNoNulo( rs.getString( "nick" ) ) );
        obj.setPass( Cadenas.trimNoNulo( rs.getString( "pass" ) ) );
        obj.setIp( Cadenas.trimNoNulo( rs.getString( "ip" ) ) );
        obj.setFallosLogin( new Integer( rs.getInt( "fallos_login" ) ) );
        if ( rs.wasNull() )
        {
            obj.setFallosLogin( null );
        }
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
