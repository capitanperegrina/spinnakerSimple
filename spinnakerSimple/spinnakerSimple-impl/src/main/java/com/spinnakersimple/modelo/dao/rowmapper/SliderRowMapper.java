package com.spinnakersimple.modelo.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.capitanperegrina.common.utils.Cadenas;
import com.spinnakersimple.modelo.entidad.SliderPOJO;

/**
 * Rowmapper de la tabla <code>slider</code> a la clase SliderPOJO
 */
public class SliderRowMapper implements RowMapper<SliderPOJO> {
	/**
	 * Mapea un registro de la tabla slider con un la clase SliderPOJO.
	 *
	 * @param rs
	 *            Resultset del que extraer la información
	 * @param rowNum
	 *            Número de fila del resultSet del que obtener la información.
	 * @return POJO con la información mapeada
	 */
	@Override
	public SliderPOJO mapRow(ResultSet rs, int rowNum) throws SQLException {
		final SliderPOJO obj = new SliderPOJO();

		obj.setIdSlider(new Integer(rs.getInt("id_slider")));
		if (rs.wasNull()) {
			obj.setIdSlider(null);
		}
		obj.setImagenHome(Cadenas.trimNoNulo(rs.getString("imagen_home")));
		obj.setDondeHome(Cadenas.trimNoNulo(rs.getString("donde_home")));

		return obj;
	}
}
