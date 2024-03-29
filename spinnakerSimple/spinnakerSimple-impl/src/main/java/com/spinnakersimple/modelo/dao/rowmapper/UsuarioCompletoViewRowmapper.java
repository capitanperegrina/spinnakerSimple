package com.spinnakersimple.modelo.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.capitanperegrina.common.utils.Cadenas;
import com.capitanperegrina.common.utils.Fecha;
import com.spinnakersimple.modelo.vista.UsuarioCompletoView;

public class UsuarioCompletoViewRowmapper implements RowMapper<UsuarioCompletoView> {

	@Override
	public UsuarioCompletoView mapRow(ResultSet rs, int rowNum) throws SQLException {
		UsuarioCompletoView obj = new UsuarioCompletoView();
		obj.setIdUsuario(new Integer(rs.getInt("id_usuario")));
		if (rs.wasNull()) {
			obj.setIdUsuario(null);
		}
		obj.setNombre(Cadenas.trimNoNulo(rs.getString("nombre")));
		obj.setApellidos(Cadenas.trimNoNulo(rs.getString("apellidos")));
		obj.setMail(Cadenas.trimNoNulo(rs.getString("mail")));
		obj.setMovil(Cadenas.trimNoNulo(rs.getString("movil")));
		obj.setDireccion1(Cadenas.trimNoNulo(rs.getString("direccion_1")));
		obj.setDireccion2(Cadenas.trimNoNulo(rs.getString("direccion_2")));
		obj.setProvincia(Cadenas.trimNoNulo(rs.getString("provincia")));
		obj.setPais(Cadenas.trimNoNulo(rs.getString("pais")));
		obj.setDivisa(Cadenas.trimNoNulo(rs.getString("divisa")));
		obj.setCodPostal(Cadenas.trimNoNulo(rs.getString("cod_postal")));
		obj.setAdmin(Cadenas.trimNoNulo(rs.getString("admin")));
		obj.setPass(Cadenas.trimNoNulo(rs.getString("pass")));
		obj.setLang(Cadenas.trimNoNulo(rs.getString("lang")));
		obj.setIp(Cadenas.trimNoNulo(rs.getString("ip")));
		obj.setFallosLogin(new Integer(rs.getInt("fallos_login")));
		if (rs.wasNull()) {
			obj.setFallosLogin(null);
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
		obj.setNumAnuncios( rs.getInt( "num_anuncios" ) );
		return obj;
	}
}
