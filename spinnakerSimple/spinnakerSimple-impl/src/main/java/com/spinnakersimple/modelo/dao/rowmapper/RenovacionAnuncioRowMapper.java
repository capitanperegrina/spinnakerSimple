package com.spinnakersimple.modelo.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.capitanperegrina.common.utils.Fecha;
import com.spinnakersimple.modelo.entidad.RenovacionAnuncioPOJO;

/**
 * Rowmapper de la tabla <code>renovacion_anuncio</code> a la clase
 * RenovacionAnuncioPOJO
 */
public class RenovacionAnuncioRowMapper implements RowMapper<RenovacionAnuncioPOJO> {
    /**
     * Método que mapea un registro de la tabla renovacion_anuncio con un la clase
     * RenovacionAnuncioPOJO.
     * 
     * @param rs     Resultset del que extraer la información
     * @param rowNum Número de fila del resultSet del que obtener la información.
     * @return POJO con la información mapeada
     */
    @Override
    public RenovacionAnuncioPOJO mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final RenovacionAnuncioPOJO obj = new RenovacionAnuncioPOJO();

        obj.setIdRenocacionAnuncio(new Integer(rs.getInt("id_renocacion_anuncio")));
        if (rs.wasNull()) {
            obj.setIdRenocacionAnuncio(null);
        }
        obj.setIdAnuncio(new Integer(rs.getInt("id_anuncio")));
        if (rs.wasNull()) {
            obj.setIdAnuncio(null);
        }
        obj.setIdUsuarioAlta(new Integer(rs.getInt("id_usuario_alta")));
        if (rs.wasNull()) {
            obj.setIdUsuarioAlta(null);
        }
        obj.setFecAlta(Fecha.bbdd2Calendar(rs.getTimestamp("fec_alta")));
        obj.setIdUsuarioModif(new Integer(rs.getInt("id_usuario_modif")));
        if (rs.wasNull()) {
            obj.setIdUsuarioModif(null);
        }
        obj.setFecModif(Fecha.bbdd2Calendar(rs.getTimestamp("fec_modif")));

        return obj;
    }
}
