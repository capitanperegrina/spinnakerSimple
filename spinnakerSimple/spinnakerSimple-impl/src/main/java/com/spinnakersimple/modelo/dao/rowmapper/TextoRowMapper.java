package com.spinnakersimple.modelo.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.capitanperegrina.common.utils.Cadenas;
import com.capitanperegrina.common.utils.Fecha;
import com.spinnakersimple.modelo.entidad.TextoPOJO;

/**
 * Rowmapper de la tabla <code>texto</code> a la clase TextoPOJO
 */
public class TextoRowMapper implements RowMapper<TextoPOJO> {
	/**
	 * Mapea un registro de la tabla texto con un la clase TextoPOJO.
	 * 
	 * @param rs
	 *            Resultset del que extraer la información
	 * @param rowNum
	 *            Número de fila del resultSet del que obtener la información.
	 * @return POJO con la información mapeada
	 */
	@Override
	public TextoPOJO mapRow(ResultSet rs, int rowNum) throws SQLException {
		final TextoPOJO obj = new TextoPOJO();

		obj.setIdTexto(new Integer(rs.getInt("id_texto")));
		if (rs.wasNull()) {
			obj.setIdTexto(null);
		}
		obj.setLang(Cadenas.trimNoNulo(rs.getString("lang")));
		obj.setClave(Cadenas.trimNoNulo(rs.getString("clave")));
		obj.setValor(Cadenas.trimNoNulo(rs.getString("valor")));
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
