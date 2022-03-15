package com.spinnakersimple.modelo.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.capitanperegrina.common.utils.Cadenas;
import com.capitanperegrina.common.utils.Fecha;
import com.spinnakersimple.modelo.entidad.FotografiaPOJO;

/**
 * Rowmapper de la tabla <code>fotografia</code> a la clase FotografiaPOJO
 */
public class FotografiaRowMapper implements RowMapper<FotografiaPOJO> {
	/**
	 * Mapea un registro de la tabla fotografia con un la clase FotografiaPOJO.
	 *
	 * @param rs
	 *            Resultset del que extraer la información
	 * @param rowNum
	 *            Número de fila del resultSet del que obtener la información.
	 * @return POJO con la información mapeada
	 */
	@Override
	public FotografiaPOJO mapRow(ResultSet rs, int rowNum) throws SQLException {
		final FotografiaPOJO obj = new FotografiaPOJO();

		obj.setIdFotografia(new Integer(rs.getInt("id_fotografia")));
		if (rs.wasNull()) {
			obj.setIdFotografia(null);
		}
		obj.setIdAnuncio(new Integer(rs.getInt("id_anuncio")));
		if (rs.wasNull()) {
			obj.setIdAnuncio(null);
		}
		obj.setImagen(rs.getBytes("imagen"));
		obj.setTipo(Cadenas.trimNoNulo(rs.getString("tipo")));
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
