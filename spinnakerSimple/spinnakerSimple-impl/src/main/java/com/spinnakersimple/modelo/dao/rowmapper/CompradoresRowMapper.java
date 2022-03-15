package com.spinnakersimple.modelo.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.capitanperegrina.common.utils.Cadenas;
import com.capitanperegrina.common.utils.Fecha;
import com.spinnakersimple.modelo.entidad.CompradoresPOJO;

/**
 * Rowmapper de la tabla <code>compradores</code> a la clase CompradoresPOJO
 */
public class CompradoresRowMapper implements RowMapper<CompradoresPOJO> {
	/**
	 * Mapea un registro de la tabla compradores con un la clase
	 * CompradoresPOJO.
	 *
	 * @param rs
	 *            Resultset del que extraer la información
	 * @param rowNum
	 *            Número de fila del resultSet del que obtener la información.
	 * @return POJO con la información mapeada
	 */
	@Override
	public CompradoresPOJO mapRow(ResultSet rs, int rowNum) throws SQLException {
		final CompradoresPOJO obj = new CompradoresPOJO();

		obj.setIdComprador(new Integer(rs.getInt("id_comprador")));
		if (rs.wasNull()) {
			obj.setIdComprador(null);
		}
		obj.setIdAnuncio(new Integer(rs.getInt("id_anuncio")));
		if (rs.wasNull()) {
			obj.setIdAnuncio(null);
		}
		obj.setNombre(Cadenas.trimNoNulo(rs.getString("nombre")));
		obj.setMail(Cadenas.trimNoNulo(rs.getString("mail")));
		obj.setObservaciones(Cadenas.trimNoNulo(rs.getString("observaciones")));
		obj.setFecha(Fecha.bbdd2Calendar(rs.getTimestamp("fecha")));
		obj.setRevisado(Cadenas.trimNoNulo(rs.getString("revisado")));
		obj.setTelefono(Cadenas.trimNoNulo(rs.getString("telefono")));

		return obj;
	}
}
