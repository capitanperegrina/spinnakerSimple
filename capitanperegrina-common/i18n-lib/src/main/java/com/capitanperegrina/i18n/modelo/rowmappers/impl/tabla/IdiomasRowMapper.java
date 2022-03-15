package com.capitanperegrina.i18n.modelo.rowmappers.impl.tabla;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.capitanperegrina.common.utils.Cadenas;
import com.capitanperegrina.i18n.modelo.entidad.tabla.IdiomasPOJO;

/**
 * Rowmapper de la tabla <code>idiomas</code> a la clase IdiomasPOJO
 */
public class IdiomasRowMapper implements RowMapper<IdiomasPOJO> {
	/**
	 * M�todo que mapea un registro de la tabla idiomas con un la clase
	 * IdiomasPOJO.
	 *
	 * @param rs
	 *            Resultset del que extraer la informaci�n
	 * @param rowNum
	 *            N�mero de fila del resultSet del que obtener la informaci�n.
	 * @return POJO con la informaci�n mapeada
	 */
	@Override
	public IdiomasPOJO mapRow(ResultSet rs, int rowNum) throws SQLException {
		final IdiomasPOJO obj = new IdiomasPOJO();

		obj.setLang(Cadenas.trimNoNulo(rs.getString("lang")));
		obj.setNombreIdioma(Cadenas.trimNoNulo(rs.getString("nombre_idioma")));

		return obj;
	}
}
