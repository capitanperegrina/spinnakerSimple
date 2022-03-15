package com.capitanperegrina.i18n.modelo.rowmappers.impl.tabla;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.capitanperegrina.common.utils.Cadenas;
import com.capitanperegrina.i18n.modelo.entidad.tabla.DivisasPOJO;

/**
 * Rowmapper de la tabla <code>divisas</code> a la clase DivisasPOJO
 */
public class DivisasRowMapper implements RowMapper<DivisasPOJO> {
	/**
	 * Método que mapea un registro de la tabla divisas con un la clase
	 * DivisasPOJO.
	 * 
	 * @param rs
	 *            Resultset del que extraer la información
	 * @param rowNum
	 *            Número de fila del resultSet del que obtener la información.
	 * @return POJO con la información mapeada
	 */
	@Override
	public DivisasPOJO mapRow(ResultSet rs, int rowNum) throws SQLException {
		final DivisasPOJO obj = new DivisasPOJO();

		obj.setCodigoDivisa(Cadenas.trimNoNulo(rs.getString("codigo_divisa")));
		obj.setDescripcionDivisa(Cadenas.trimNoNulo(rs.getString("descripcion_divisa")));
		obj.setSimboloDivisa(Cadenas.trimNoNulo(rs.getString("simbolo_divisa")));

		return obj;
	}
}
