package com.spinnakersimple.modelo.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Triple;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.capitanperegrina.common.utils.Cadenas;
import com.spinnakersimple.modelo.dao.SocialSpinnakerSimpleDao;
import com.spinnakersimple.modelo.util.SpinnakerSimpleGlobals;

@Repository
public class SocialSpinnakerSimpleDaoImpl extends SocialDaoImpl
				implements SocialSpinnakerSimpleDao {

	@Override
	public Triple<Integer, Integer, Integer> proximaFotoACompartirEnRedesSociales() {
		return proximaFotoACompartirEnRedesSociales(null);
	}

	@Override
	public Triple<Integer, Integer, Integer> proximaFotoACompartirEnRedesSociales(final Integer idAnuncio) {
		try {

			final StringBuilder q = new StringBuilder("");
			final List<Object> p = new ArrayList<Object>();
			q.append("SELECT a.id_anuncio, f.id_fotografia, s.id_social ");
			q.append("FROM anuncio a ");
			q.append("JOIN fotografia f ON a.id_anuncio = f.id_anuncio ");
			q.append("LEFT JOIN social s ON s.entidad = 1 AND f.id_fotografia = s.clave ");
			q.append("WHERE a.caduca > NOW() ");
			q.append("AND a.visible = " + SpinnakerSimpleGlobals.ENTIDAD_SOCIAL_ANUNCIO + " ");
			if (idAnuncio != null) {
				q.append("AND a.id_anuncio = ? ");
				p.add(idAnuncio);
			}
			q.append("ORDER BY s.facebook+s.twitter+s.instagram ASC, s.fec_modif ASC ");
			q.append("LIMIT 1; ");

			log.trace(Cadenas.generaQuery(q, p));
			return this.jdbcTemplate.queryForObject(q.toString(), p.toArray(), new RowMapper<Triple<Integer, Integer, Integer>>() {
				@Override
				public Triple<Integer, Integer, Integer> mapRow(final ResultSet rs, final int rowNum) throws SQLException {
					Integer idAnuncio = new Integer(rs.getInt("id_anuncio"));
					if (rs.wasNull()) {
						idAnuncio = null;
					}
					Integer idFotografia = new Integer(rs.getInt("id_fotografia"));
					if (rs.wasNull()) {
						idFotografia = null;
					}
					Integer idSocial = new Integer(rs.getInt("id_social"));
					if (rs.wasNull()) {
						idSocial = null;
					}
					final Triple<Integer, Integer, Integer> ret = Triple.of(idAnuncio, idFotografia, idSocial);
					return ret;
				}
			});
		} catch (final EmptyResultDataAccessException e) {
			log.debug("No se ha encontrado la informaci�n buscada.", e);
			throw e;
		}
	}
}
