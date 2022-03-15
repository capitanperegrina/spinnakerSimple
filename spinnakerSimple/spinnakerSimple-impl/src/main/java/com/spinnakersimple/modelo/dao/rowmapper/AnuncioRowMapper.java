package com.spinnakersimple.modelo.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.capitanperegrina.common.utils.Cadenas;
import com.capitanperegrina.common.utils.Fecha;
import com.capitanperegrina.utils.sql.ResultSetUtils;
import com.spinnakersimple.modelo.entidad.AnuncioPOJO;

/**
 * Rowmapper de la tabla <code>anuncio</code> a la clase AnuncioPOJO
 */
public class AnuncioRowMapper implements RowMapper<AnuncioPOJO> {
	/**
	 * Mapea un registro de la tabla anuncio con un la clase AnuncioPOJO.
	 *
	 * @param rs     Resultset del que extraer la información
	 * @param rowNum Número de fila del resultSet del que obtener la información.
	 * @return POJO con la información mapeada
	 */
	@Override
	public AnuncioPOJO mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		final AnuncioPOJO obj = new AnuncioPOJO();

		obj.setIdAnuncio(new Integer(rs.getInt("id_anuncio")));
		if (rs.wasNull()) {
			obj.setIdAnuncio(null);
		}
		obj.setTituloAnuncio(Cadenas.trimNoNulo(rs.getString("titulo_anuncio")));
		obj.setTipoBarco(new Integer(rs.getInt("tipo_barco")));
		if (rs.wasNull()) {
			obj.setTipoBarco(null);
		}
		obj.setTipoVela(new Integer(rs.getInt("tipo_vela")));
		if (rs.wasNull()) {
			obj.setTipoVela(null);
		}
		obj.setGramaje(rs.getBigDecimal("gramaje"));
		obj.setGratil(rs.getBigDecimal("gratil"));
		obj.setBaluma(rs.getBigDecimal("baluma"));
		obj.setPujamen(rs.getBigDecimal("pujamen"));
		obj.setCaidaProa(rs.getBigDecimal("caida_proa"));
		obj.setCaidaPopa(rs.getBigDecimal("caida_popa"));
		obj.setSuperficie(rs.getBigDecimal("superficie"));
		obj.setTipoCometa(Cadenas.trimNoNulo(rs.getString("tipo_cometa")));
		obj.setDescripcion(Cadenas.trimNoNulo(rs.getString("descripcion")));
		obj.setPrecio(rs.getBigDecimal("precio"));
		obj.setPais(ResultSetUtils.getString("pais", rs));
		obj.setCaduca(Fecha.bbdd2Calendar(rs.getTimestamp("caduca")));
		obj.setVisible(new Integer(rs.getInt("visible")));
		if (rs.wasNull()) {
			obj.setVisible(null);
		}
		obj.setListado(new Integer(rs.getInt("listado")));
		if (rs.wasNull()) {
			obj.setListado(null);
		}
		obj.setVisto(new Integer(rs.getInt("visto")));
		if (rs.wasNull()) {
			obj.setVisto(null);
		}
		obj.setComentado(new Integer(rs.getInt("comentado")));
		if (rs.wasNull()) {
			obj.setComentado(null);
		}
		obj.setDestacado(new Integer(rs.getInt("destacado")));
		if (rs.wasNull()) {
			obj.setDestacado(null);
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
