package com.capitanperegrina.i18n.modelo.rowmappers.impl.tabla;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.capitanperegrina.common.utils.Cadenas;
import com.capitanperegrina.i18n.modelo.entidad.tabla.PaisesPOJO;

/**
 * Rowmapper de la tabla <code>paises</code> a la clase PaisesPOJO
 */
public class PaisesRowMapper implements RowMapper<PaisesPOJO> {
	/**
	 * M�todo que mapea un registro de la tabla paises con un la clase
	 * PaisesPOJO.
	 * 
	 * @param rs
	 *            Resultset del que extraer la informaci�n
	 * @param rowNum
	 *            N�mero de fila del resultSet del que obtener la informaci�n.
	 * @return POJO con la informaci�n mapeada
	 */
	@Override
	public PaisesPOJO mapRow(ResultSet rs, int rowNum) throws SQLException {
		final PaisesPOJO obj = new PaisesPOJO();

		obj.setCodigo(new Integer(rs.getInt("codigo")));
		if (rs.wasNull()) {
			obj.setCodigo(null);
		}
		obj.setLang(Cadenas.trimNoNulo(rs.getString("lang")));
		obj.setCodigo2(Cadenas.trimNoNulo(rs.getString("codigo2")));
		obj.setCodigo3(Cadenas.trimNoNulo(rs.getString("codigo3")));
		obj.setNombrePais(Cadenas.trimNoNulo(rs.getString("nombrePais")));

		return obj;
	}
}
