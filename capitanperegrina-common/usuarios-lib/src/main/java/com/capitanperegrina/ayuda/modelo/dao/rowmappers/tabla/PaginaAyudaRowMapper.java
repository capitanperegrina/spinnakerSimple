package com.capitanperegrina.ayuda.modelo.dao.rowmappers.tabla;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.capitanperegrina.ayuda.modelo.entidad.tabla.PaginaAyudaPOJO;
import com.capitanperegrina.ayuda.modelo.entidad.tabla.pk.PaginaAyudaPKPOJO;
import com.capitanperegrina.common.utils.Cadenas;

/**
 * Rowmapper de la tabla <code>paginas_ayuda</code> a la clase PaginasAyudaPOJO
 */
public class PaginaAyudaRowMapper implements RowMapper<PaginaAyudaPOJO>
{
    /**
     * M�todo que mapea un registro de la tabla paginas_ayuda con un la clase PaginasAyudaPOJO.
     * @param rs Resultset del que extraer la informaci�n
     * @param rowNum N�mero de fila del resultSet del que obtener la informaci�n.
     * @return POJO con la informaci�n mapeada
     */
    @Override
    public PaginaAyudaPOJO mapRow( ResultSet rs, int rowNum ) throws SQLException
    {
        PaginaAyudaPKPOJO pk = new PaginaAyudaPKPOJO();
        PaginaAyudaPOJO obj = new PaginaAyudaPOJO();
        obj.setPk( pk );

        obj.getPk().setIdAyuda( new Integer( rs.getInt( "id_ayuda" ) ) );
        if ( rs.wasNull() )
        {
            obj.getPk().setIdAyuda( null );
        }
        obj.setIdForm( Cadenas.trimNoNulo( rs.getString( "id_form" ) ) );
        obj.setIdElemento( Cadenas.trimNoNulo( rs.getString( "id_elemento" ) ) );
        obj.setIdSubelemento( Cadenas.trimNoNulo( rs.getString( "id_subelemento" ) ) );
        obj.setTitulo( Cadenas.trimNoNulo( rs.getString( "titulo" ) ) );
        obj.setContenido( Cadenas.trimNoNulo( rs.getString( "contenido" ) ) );

    	return obj;
    }
}
