package com.spinnakersimple.modelo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.capitanperegrina.common.modelo.dao.SocialDao;
import com.capitanperegrina.common.modelo.dao.rowmapper.SocialRowMapper;
import com.capitanperegrina.common.modelo.entidad.tabla.SocialPOJO;
import com.capitanperegrina.common.utils.Cadenas;

/**
 * Objeto de acceso a datos para la tabla <code>social<code>
 */
@Repository
public class SocialDaoImpl implements SocialDao {
	@Autowired
	protected JdbcTemplate jdbcTemplate;

	static Logger log = Logger.getLogger(SocialDaoImpl.class);
	public static final String ENTIDAD = SocialPOJO.class.getName();
	public static final String TABLA = "social";
	public static final String CAMPOS = "id_social, entidad, clave, facebook, twitter, instagram, id_usuario_alta, fec_alta, id_usuario_modif, fec_modif ";
	public static final String CAMPOSINSERT = " ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ";
	public static final String CAMPOSUPDATE = " entidad = ?, clave = ?, facebook = ?, twitter = ?, instagram = ?, id_usuario_alta = ?, fec_alta = ?, id_usuario_modif = ?, fec_modif = ? ";
	public static final String CAMPOSPKUPDATE = " id_social = ? ";

	private static Object[] toParamsTodos(final SocialPOJO obj) {
		return ArrayUtils.addAll(toParamsClave(obj), toParamsResto(obj));
	}

	private static Object[] toParamsUpdate(final SocialPOJO obj) {
		return ArrayUtils.addAll(toParamsResto(obj), toParamsClave(obj));
	}

	private static Object[] toParamsClave(final SocialPOJO obj) {
		return new Object[] { obj.getIdSocial() };
	}

	private static Object[] toParamsResto(final SocialPOJO obj) {
		return new Object[] { obj.getEntidad(), obj.getClave(), obj.getFacebook(), obj.getTwitter(), obj.getInstagram(), obj.getIdUsuarioAlta(), obj.getFecAlta(), obj.getIdUsuarioModif(), obj.getFecModif() };
	}

	/* (non-Javadoc)
	 * @see com.spinnakersimple.modelo.dao.impl.SocialDao#crea(com.spinnakersimple.modelo.entidad.SocialPOJO)
	 */
	@Override
	public SocialPOJO crea(final SocialPOJO obj) {
		try {
			final SocialPOJO s = obj;
			StringBuilder q = new StringBuilder("");
			Object[] p = new Object[] {};
			q.append("LOCK TABLES " + TABLA + " WRITE;");
			log.trace(Cadenas.generaQuery(q, p));
			this.jdbcTemplate.update(q.toString(), p);

			q = new StringBuilder("");
			p = new Object[] {};
			q.append("SELECT IFNULL( MAX( id_social ) + 1, 1 ) FROM " + TABLA + ";");
			final Integer newId = this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class);

			q = new StringBuilder("");
			p = ArrayUtils.addAll(new Object[] { newId }, toParamsResto(obj));
			q.append("INSERT INTO " + TABLA + " ( " + CAMPOS + " ) ");
			q.append("VALUES ( " + CAMPOSINSERT + " ) ");

			log.trace(Cadenas.generaQuery(q, p));
			this.jdbcTemplate.update(q.toString(), p);

			s.setIdSocial(newId);
			return s;
		} catch (final DuplicateKeyException e) {
			log.debug("Clave duplicada al intentar insertar la informaci�n.", e);
			throw e;
		} finally {
			final StringBuilder q = new StringBuilder("");
			final Object[] p = new Object[] {};
			q.append("UNLOCK TABLES;\n");

			log.trace(Cadenas.generaQuery(q, p));
			this.jdbcTemplate.update(q.toString(), p);
		}
	}

	/* (non-Javadoc)
	 * @see com.spinnakersimple.modelo.dao.impl.SocialDao#existe(com.spinnakersimple.modelo.entidad.SocialPOJO)
	 */
	@Override
	public boolean existe(final SocialPOJO obj) {
		final StringBuilder q = new StringBuilder("");
		final Object[] p = toParamsClave(obj);
		q.append(" SELECT COUNT(*) \n");
		q.append("   FROM " + TABLA + " \n");
		q.append("  WHERE  id_social = ?   \n");

		log.trace(Cadenas.generaQuery(q, p));
		return (this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class)) > 0;
	}

	/* (non-Javadoc)
	 * @see com.spinnakersimple.modelo.dao.impl.SocialDao#busca(com.spinnakersimple.modelo.entidad.SocialPOJO)
	 */
	@Override
	public SocialPOJO busca(final SocialPOJO obj) {
		try {
			final StringBuilder q = new StringBuilder("");
			final Object[] p = toParamsClave(obj);
			q.append(" SELECT " + CAMPOS + " \n");
			q.append("   FROM " + TABLA + " \n");
			q.append("  WHERE  id_social = ?   \n");

			log.trace(Cadenas.generaQuery(q, p));
			return this.jdbcTemplate.queryForObject(q.toString(), p, new SocialRowMapper());
		} catch (final EmptyResultDataAccessException e) {
			log.debug("No se ha encontrado la informaci�n buscada.", e);
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see com.spinnakersimple.modelo.dao.impl.SocialDao#actualiza(com.spinnakersimple.modelo.entidad.SocialPOJO)
	 */
	@Override
	public void actualiza(final SocialPOJO obj) {
		try {
			final StringBuilder q = new StringBuilder("");
			final Object[] p = toParamsUpdate(obj);
			q.append(" UPDATE " + TABLA + " \n");
			q.append("    SET " + CAMPOSUPDATE + " \n");
			q.append("  WHERE " + CAMPOSPKUPDATE + " \n");

			log.trace(Cadenas.generaQuery(q, p));
			this.jdbcTemplate.update(q.toString(), p);
		} catch (final EmptyResultDataAccessException e) {
			log.debug("El registro que se desea actualizar no existe.", e);
			throw e;
		} catch (final DuplicateKeyException e) {
			log.debug("Clave duplicada al intentar actualizar la informaci�n.", e);
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see com.spinnakersimple.modelo.dao.impl.SocialDao#borra(com.spinnakersimple.modelo.entidad.SocialPOJO)
	 */
	@Override
	public void borra(final SocialPOJO obj) {
		try {
			final StringBuilder q = new StringBuilder("");
			final Object[] p = toParamsClave(obj);
			q.append(" DELETE FROM " + TABLA + " \n");
			q.append("  WHERE  id_social = ?   \n");

			log.trace(Cadenas.generaQuery(q, p));
			this.jdbcTemplate.update(q.toString(), p);
		} catch (final EmptyResultDataAccessException e) {
			log.debug("El registro que se desea eliminar no existe.", e);
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see com.spinnakersimple.modelo.dao.impl.SocialDao#buscaTodos()
	 */
	@Override
	public List<SocialPOJO> buscaTodos() {
		final StringBuilder q = new StringBuilder("");
		final Object[] p = {};
		q.append(" SELECT " + CAMPOS + " \n");
		q.append("   FROM " + TABLA + " \n");

		log.trace(Cadenas.generaQuery(q, p));
		return this.jdbcTemplate.query(q.toString(), new SocialRowMapper());
	}

	/* (non-Javadoc)
	 * @see com.spinnakersimple.modelo.dao.impl.SocialDao#buscaVarios(com.spinnakersimple.modelo.entidad.SocialPOJO)
	 */
	@Override
	public List<SocialPOJO> buscaVarios(final SocialPOJO obj) {
		if (obj == null) {
			return buscaTodos();
		}

		final StringBuilder q = new StringBuilder("");
		q.append(" SELECT " + CAMPOS + " \n");
		q.append("   FROM " + TABLA + " \n");
		q.append("  WHERE 1 = 1 \n");

		final List<Object> parametros = new ArrayList<Object>();

		if (obj.getIdSocial() != null) {
			q.append("   AND id_social = ? \n");
			parametros.add(obj.getIdSocial());
		}
		if (obj.getEntidad() != null) {
			q.append("   AND entidad = ? \n");
			parametros.add(obj.getEntidad());
		}
		if (obj.getClave() != null) {
			q.append("   AND clave = ? \n");
			parametros.add(obj.getClave());
		}
		if (obj.getFacebook() != null) {
			q.append("   AND facebook = ? \n");
			parametros.add(obj.getFacebook());
		}
		if (obj.getTwitter() != null) {
			q.append("   AND twitter = ? \n");
			parametros.add(obj.getTwitter());
		}
		if (obj.getInstagram() != null) {
			q.append("   AND instagram = ? \n");
			parametros.add(obj.getInstagram());
		}
		if (obj.getIdUsuarioAlta() != null) {
			q.append("   AND id_usuario_alta = ? \n");
			parametros.add(obj.getIdUsuarioAlta());
		}
		if (obj.getFecAlta() != null) {
			q.append("   AND fec_alta = ? \n");
			parametros.add(obj.getFecAlta());
		}
		if (obj.getIdUsuarioModif() != null) {
			q.append("   AND id_usuario_modif = ? \n");
			parametros.add(obj.getIdUsuarioModif());
		}
		if (obj.getFecModif() != null) {
			q.append("   AND fec_modif = ? \n");
			parametros.add(obj.getFecModif());
		}
		final Object[] p = parametros.toArray();

		log.trace(Cadenas.generaQuery(q, p));
		return this.jdbcTemplate.query(q.toString(), p, new SocialRowMapper());
	}

	/* (non-Javadoc)
	 * @see com.spinnakersimple.modelo.dao.impl.SocialDao#existe(com.spinnakersimple.modelo.entidad.SocialPOJO)
	 */
	@Override
	public boolean existe(final String entidad, final Integer clave) {
		final StringBuilder q = new StringBuilder();
		final Object[] p = { entidad, clave };
		q.append(" SELECT COUNT(*) FROM " + TABLA + " WHERE  entidad = ? AND clave = ? ");

		log.trace(Cadenas.generaQuery(q, p));
		return (this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class)) > 0;
	}

	/* (non-Javadoc)
	 * @see com.spinnakersimple.modelo.dao.impl.SocialDao#busca(com.spinnakersimple.modelo.entidad.SocialPOJO)
	 */
	@Override
	public SocialPOJO busca(final String entidad, final Integer clave) {
		try {
			final StringBuilder q = new StringBuilder();
			final Object[] p = { entidad, clave };
			q.append(" SELECT " + CAMPOS + " FROM " + TABLA + " WHERE  entidad = ? AND clave = ? ");

			log.trace(Cadenas.generaQuery(q, p));
			return this.jdbcTemplate.queryForObject(q.toString(), p, new SocialRowMapper());
		} catch (final EmptyResultDataAccessException e) {
			log.debug("No se ha encontrado la informaci�n buscada.", e);
			throw e;
		}
	}
}