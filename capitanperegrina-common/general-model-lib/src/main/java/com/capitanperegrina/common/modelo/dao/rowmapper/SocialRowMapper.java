package com.capitanperegrina.common.modelo.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.capitanperegrina.common.modelo.entidad.tabla.SocialPOJO;
import com.capitanperegrina.common.utils.Fecha;

/**
 * Rowmapper de la tabla <code>social</code> a la clase SocialPOJO
 */
public class SocialRowMapper implements RowMapper<SocialPOJO> {
	/**
	 * Método que mapea un registro de la tabla social con un la clase
	 * SocialPOJO.
	 * 
	 * @param rs     Resultset del que extraer la información
	 * @param rowNum Número de fila del resultSet del que obtener la
	 *               información.
	 * @return POJO con la información mapeada
	 */
	@Override
	public SocialPOJO mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		final SocialPOJO obj = new SocialPOJO();

		obj.setIdSocial(new Integer(rs.getInt("id_social")));
		if (rs.wasNull()) {
			obj.setIdSocial(null);
		}
		obj.setEntidad(new Integer(rs.getInt("entidad")));
		if (rs.wasNull()) {
			obj.setEntidad(null);
		}
		obj.setClave(new Integer(rs.getInt("clave")));
		if (rs.wasNull()) {
			obj.setClave(null);
		}
		obj.setFacebook(new Integer(rs.getInt("facebook")));
		if (rs.wasNull()) {
			obj.setFacebook(null);
		}
		obj.setTwitter(new Integer(rs.getInt("twitter")));
		if (rs.wasNull()) {
			obj.setTwitter(null);
		}
		obj.setInstagram(new Integer(rs.getInt("instagram")));
		if (rs.wasNull()) {
			obj.setInstagram(null);
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
