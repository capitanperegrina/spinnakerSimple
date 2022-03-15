package com.spinnakersimple.modelo.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.capitanperegrina.common.utils.Cadenas;
import com.spinnakersimple.modelo.entidad.TipoVelaPOJO;

/**
 * Rowmapper de la tabla <code>tipo_vela</code> a la clase TipoVelaPOJO
 */
public class TipoVelaRowMapper implements RowMapper<TipoVelaPOJO> {
    /**
     * Método que mapea un registro de la tabla tipo_vela con un la clase
     * TipoVelaPOJO.
     * 
     * @param rs     Resultset del que extraer la información
     * @param rowNum Número de fila del resultSet del que obtener la información.
     * @return POJO con la información mapeada
     */
    @Override
    public TipoVelaPOJO mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final TipoVelaPOJO obj = new TipoVelaPOJO();

        obj.setIdTipoVela(new Integer(rs.getInt("id_tipo_vela")));
        if (rs.wasNull()) {
            obj.setIdTipoVela(null);
        }
        obj.setTipoVela(Cadenas.trimNoNulo(rs.getString("tipo_vela")));
        obj.setUrlTipoVela(Cadenas.trimNoNulo(rs.getString("url_tipo_vela")));
        obj.setImagenTipoVela(Cadenas.trimNoNulo(rs.getString("imagen_tipo_vela")));
        obj.setGratil(Cadenas.trimNoNulo(rs.getString("gratil")));
        obj.setBaluma(Cadenas.trimNoNulo(rs.getString("baluma")));
        obj.setPujamen(Cadenas.trimNoNulo(rs.getString("pujamen")));
        obj.setCaidaProa(Cadenas.trimNoNulo(rs.getString("caida_proa")));
        obj.setCaidaPopa(Cadenas.trimNoNulo(rs.getString("caida_popa")));
        obj.setSuperficie(Cadenas.trimNoNulo(rs.getString("superficie")));
        obj.setTipoCometa(Cadenas.trimNoNulo(rs.getString("tipo_cometa")));

        return obj;
    }
}
