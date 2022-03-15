/*
 *
 */
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.capitanperegrina.common.basebeans.CambioEnTabla;
import com.capitanperegrina.common.exceptions.ServicioException;
import com.capitanperegrina.common.utils.Cadenas;
import com.spinnakersimple.modelo.dao.FotografiaDao;
import com.spinnakersimple.modelo.dao.rowmapper.FotografiaRowMapper;
import com.spinnakersimple.modelo.entidad.FotografiaPOJO;

/**
 * The Class FotografiaDaoImpl.
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public class FotografiaDaoImpl implements FotografiaDao {

	/** The log. */
	static Logger log = Logger.getLogger(FotografiaDaoImpl.class);

	/** The Constant ENTIDAD. */
	public static final String ENTIDAD = FotografiaPOJO.class.getName();

	/** The Constant TABLA. */
	public static final String TABLA = "fotografia";

	/** The Constant CAMPOS. */
	public static final String CAMPOS = "id_fotografia, id_anuncio, imagen, tipo, id_usuario_alta, fec_alta, id_usuario_modif, fec_modif ";

	/** The Constant CAMPOSINSERT. */
	public static final String CAMPOSINSERT = " ?, ?, ?, ?, ?, ?, ?, ? ";

	/** The Constant CAMPOSUPDATE. */
	public static final String CAMPOSUPDATE = " id_anuncio = ?, imagen = ?, tipo = ?, id_usuario_alta = ?, fec_alta = ?, id_usuario_modif = ?, fec_modif = ? ";

	/** The Constant CAMPOSPKUPDATE. */
	public static final String CAMPOSPKUPDATE = " id_fotografia = ? ";

	/**
	 * To params clave.
	 *
	 * @param obj the obj
	 * @return the object[]
	 */
	private static Object[] toParamsClave(final FotografiaPOJO obj) {
		return new Object[] { obj.getIdFotografia() };
	}

	/**
	 * To params resto.
	 *
	 * @param obj the obj
	 * @return the object[]
	 */
	private static Object[] toParamsResto(final FotografiaPOJO obj) {
		return new Object[] { obj.getIdAnuncio(), obj.getImagen(), obj.getTipo(), obj.getIdUsuarioAlta(), obj.getFecAlta(), obj.getIdUsuarioModif(), obj.getFecModif() };
	}

	/**
	 * To params update.
	 *
	 * @param obj the obj
	 * @return the object[]
	 */
	private static Object[] toParamsUpdate(final FotografiaPOJO obj) {
		return ArrayUtils.addAll(toParamsResto(obj), toParamsClave(obj));
	}

	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.spinnakersimple.modelo.dao.FotografiaDao#actualiza(com.
	 * spinnakersimple.modelo.entidad.tabla.FotografiaPOJO)
	 */
	@Override
	public void actualiza(final FotografiaPOJO obj) {
		try {
			final StringBuilder q = new StringBuilder("");
			final Object[] p = toParamsUpdate(obj);
			q.append("UPDATE " + TABLA + " ");
			q.append("SET " + CAMPOSUPDATE + " ");
			q.append("WHERE " + CAMPOSPKUPDATE + " ");

			log.trace(Cadenas.generaQuery(q, p));
			this.jdbcTemplate.update(q.toString(), p);
		} catch (final EmptyResultDataAccessException e) {
			log.debug("El registro que se desea actualizar no existe.", e);
			throw e;
		} catch (final DuplicateKeyException e) {
			log.debug("Clave duplicada al intentar actualizar la información.", e);
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.spinnakersimple.modelo.dao.FotografiaDao#borra(com.spinnakersimple.
	 * modelo.entidad.tabla.FotografiaPOJO)
	 */
	@Override
	public void borra(final FotografiaPOJO obj) {
		try {
			final StringBuilder q = new StringBuilder("");
			final Object[] p = toParamsClave(obj);
			q.append("DELETE FROM " + TABLA + " ");
			q.append("WHERE  id_fotografia = ?   ");

			log.trace(Cadenas.generaQuery(q, p));
			this.jdbcTemplate.update(q.toString(), p);
		} catch (final EmptyResultDataAccessException e) {
			log.debug("El registro que se desea eliminar no existe.", e);
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.spinnakersimple.modelo.dao.FotografiaDao#busca(com.spinnakersimple.
	 * modelo.entidad.tabla.FotografiaPOJO)
	 */
	@Override
	public FotografiaPOJO busca(final FotografiaPOJO obj) throws ServicioException {
		try {
			final StringBuilder q = new StringBuilder("");
			final Object[] p = toParamsClave(obj);
			q.append("SELECT " + CAMPOS + " ");
			q.append("FROM " + TABLA + " ");
			q.append("WHERE  id_fotografia = ?   ");

			log.trace(Cadenas.generaQuery(q, p));
			return this.jdbcTemplate.queryForObject(q.toString(), p, new FotografiaRowMapper());
		} catch (final EmptyResultDataAccessException e) {
			log.debug("No se ha encontrado la información buscada.");
			throw new ServicioException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.spinnakersimple.modelo.dao.FotografiaDao#buscaTodos()
	 */
	@Override
	public List<FotografiaPOJO> buscaTodos() {
		final StringBuilder q = new StringBuilder("");
		final Object[] p = {};
		q.append("SELECT " + CAMPOS + " ");
		q.append("FROM " + TABLA + " ");

		log.trace(Cadenas.generaQuery(q, p));
		return this.jdbcTemplate.query(q.toString(), new FotografiaRowMapper());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.spinnakersimple.modelo.dao.FotografiaDao#buscaVarios(com.
	 * spinnakersimple.modelo.entidad.tabla.FotografiaPOJO)
	 */
	@Override
	public List<FotografiaPOJO> buscaVarios(final FotografiaPOJO obj) {
		if (obj == null) {
			return buscaTodos();
		}

		final StringBuilder q = new StringBuilder("");
		q.append("SELECT " + CAMPOS + " ");
		q.append("FROM " + TABLA + " ");
		q.append("WHERE 1 = 1 ");

		final List<Object> parametros = new ArrayList<Object>();

		if (obj.getIdFotografia() != null) {
			q.append("AND id_fotografia = ? ");
			parametros.add(obj.getIdFotografia());
		}
		if (obj.getIdAnuncio() != null) {
			q.append("AND id_anuncio = ? ");
			parametros.add(obj.getIdAnuncio());
		}
		if (obj.getImagen() != null) {
			q.append("AND imagen = ? ");
			parametros.add(obj.getImagen());
		}
		if (obj.getTipo() != null) {
			q.append("AND tipo = ? ");
			parametros.add(obj.getTipo());
		}
		if (obj.getIdUsuarioAlta() != null) {
			q.append("AND id_usuario_alta = ? ");
			parametros.add(obj.getIdUsuarioAlta());
		}
		if (obj.getFecAlta() != null) {
			q.append("AND fec_alta = ? ");
			parametros.add(obj.getFecAlta());
		}
		if (obj.getIdUsuarioModif() != null) {
			q.append("AND id_usuario_modif = ? ");
			parametros.add(obj.getIdUsuarioModif());
		}
		if (obj.getFecModif() != null) {
			q.append("AND fec_modif = ? ");
			parametros.add(obj.getFecModif());
		}
		final Object[] p = parametros.toArray();

		log.trace(Cadenas.generaQuery(q, p));
		return this.jdbcTemplate.query(q.toString(), p, new FotografiaRowMapper());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.spinnakersimple.modelo.dao.FotografiaDao#crea(com.spinnakersimple.
	 * modelo.entidad.tabla.FotografiaPOJO)
	 */
	@Override
	public FotografiaPOJO crea(final FotografiaPOJO obj) {
		try {
			final FotografiaPOJO f = obj;
			StringBuilder q = new StringBuilder("");
			Object[] p = new Object[] {};
			q.append("LOCK TABLES " + TABLA + " WRITE;");
			log.trace(Cadenas.generaQuery(q, p));
			this.jdbcTemplate.update(q.toString(), p);

			q = new StringBuilder("");
			p = new Object[] {};
			q.append("SELECT IFNULL( MAX( id_fotografia ) + 1, 1 ) FROM " + TABLA + ";");
			final Integer newId = this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class);

			q = new StringBuilder("");
			p = ArrayUtils.addAll(new Object[] { newId }, toParamsResto(obj));
			q.append("INSERT INTO " + TABLA + " ( " + CAMPOS + " ) ");
			q.append("VALUES ( " + CAMPOSINSERT + " ) ");

			log.trace(Cadenas.generaQuery(q, p));
			this.jdbcTemplate.update(q.toString(), p);

			f.setIdAnuncio(newId);
			return f;
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

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.spinnakersimple.modelo.dao.FotografiaDao#existe(com.spinnakersimple.
	 * modelo.entidad.tabla.FotografiaPOJO)
	 */
	@Override
	public boolean existe(final FotografiaPOJO obj) {
		final StringBuilder q = new StringBuilder("");
		final Object[] p = toParamsClave(obj);
		q.append("SELECT COUNT(*) ");
		q.append("FROM " + TABLA + " ");
		q.append("WHERE  id_fotografia = ?   ");

		log.trace(Cadenas.generaQuery(q, p));
		return this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class) > 0;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.spinnakersimple.modelo.dao.FotografiaDao#historifica(com.
	 * spinnakersimple.modelo.entidad.tabla.FotografiaPOJO,
	 * com.capitanperegrina.common.basebeans.CambioEnTabla)
	 */
	@Override
	public void historifica(final FotografiaPOJO obj, final CambioEnTabla ct) {
		final StringBuilder q = new StringBuilder();
		final Object[] p = toParamsClave(obj);
		q.append("INSERT INTO " + TABLA + "_his ");
		q.append("SELECT NOW(), " + ct.getUsu() + ", " + CAMPOS + " ");
		q.append("FROM " + TABLA + " ");
		q.append("WHERE 1=1 ");
		if (obj.getIdFotografia() != null) {
			q.append("AND id_fotografia = ? ");
		}
		this.jdbcTemplate.update(q.toString(), p);
	}

	@Override
	public boolean anuncioTieneFotografias(final Integer id_anuncio) {
		final StringBuilder q = new StringBuilder("");
		final Object[] p = { id_anuncio };
		q.append("SELECT COUNT(*) FROM " + TABLA + " WHERE id_anuncio = ? ");
		log.trace(Cadenas.generaQuery(q, p));
		return this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class) > 0;
	}

	@Override
	public void historificaYEliminaPorUsuario( final Integer idUsuario ) {
		final Object[] p = { idUsuario };
		StringBuilder q = new StringBuilder();
		q.append("insert into fotografia_his ( select NOW(), 1, f.* from fotografia f where f.id_anuncio in( select a.id_anuncio from anuncio a where a.id_usuario_alta = ? ) );" );
		log.trace(Cadenas.generaQuery(q, p));
		this.jdbcTemplate.update(q.toString(), p);;

		q = new StringBuilder();
		q.append("delete from fotografia where id_anuncio in ( select a.id_anuncio from anuncio a where a.id_usuario_alta = ? );" );
		log.trace(Cadenas.generaQuery(q, p));
		this.jdbcTemplate.update(q.toString(), p);;
	}
}