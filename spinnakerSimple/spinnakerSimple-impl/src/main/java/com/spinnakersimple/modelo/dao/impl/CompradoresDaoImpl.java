package com.spinnakersimple.modelo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.capitanperegrina.common.utils.Cadenas;
import com.spinnakersimple.modelo.dao.CompradoresDao;
import com.spinnakersimple.modelo.dao.rowmapper.CompradoresRowMapper;
import com.spinnakersimple.modelo.entidad.CompradoresPOJO;

/**
 * The Class CompradoresDaoImpl.
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public class CompradoresDaoImpl implements CompradoresDao {

	/** The log. */
	static Logger log = Logger.getLogger(CompradoresDaoImpl.class);

	/** The Constant ENTIDAD. */
	public static final String ENTIDAD = CompradoresPOJO.class.getName();

	/** The Constant TABLA. */
	public static final String TABLA = "compradores";

	/** The Constant CAMPOS. */
	public static final String CAMPOS = "id_comprador, id_anuncio, nombre, mail, observaciones, fecha, revisado, telefono ";

	/** The Constant CAMPOSINSERT. */
	public static final String CAMPOSINSERT = " ?, ?, ?, ?, ?, ?, ?, ? ";

	/** The Constant CAMPOSUPDATE. */
	public static final String CAMPOSUPDATE = " id_anuncio = ?, nombre = ?, mail = ?, observaciones = ?, fecha = ?, revisado = ?, telefono = ? ";

	/** The Constant CAMPOSPKUPDATE. */
	public static final String CAMPOSPKUPDATE = " id_comprador = ? ";

	/**
	 * To params clave.
	 *
	 * @param obj
	 *            the obj
	 * @return the object[]
	 */
	private static Object[] toParamsClave(final CompradoresPOJO obj) {
		return new Object[] { obj.getIdComprador() };
	}

	/**
	 * To params resto.
	 *
	 * @param obj
	 *            the obj
	 * @return the object[]
	 */
	private static Object[] toParamsResto(final CompradoresPOJO obj) {
		return new Object[] { obj.getIdAnuncio(), obj.getNombre(), obj.getMail(), obj.getObservaciones(),
				obj.getFecha(), obj.getRevisado(), obj.getTelefono() };
	}

	/**
	 * To params update.
	 *
	 * @param obj
	 *            the obj
	 * @return the object[]
	 */
	private static Object[] toParamsUpdate(final CompradoresPOJO obj) {
		return ArrayUtils.addAll(toParamsResto(obj), toParamsClave(obj));
	}

	/**
	 * To params todos.
	 *
	 * @param obj
	 *            the obj
	 * @return the object[]
	 */
	private static Object[] toParamsTodos(final CompradoresPOJO obj) {
		return ArrayUtils.addAll(toParamsClave(obj), toParamsResto(obj));
	}

	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.spinnakersimple.modelo.dao.CompradoresDao#actualiza(com.
	 * spinnakersimple.modelo.entidad.tabla.CompradoresPOJO)
	 */
	@Override
	public void actualiza(final CompradoresPOJO obj) {
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
	 * com.spinnakersimple.modelo.dao.CompradoresDao#borra(com.spinnakersimple.
	 * modelo.entidad.tabla.CompradoresPOJO)
	 */
	@Override
	public void borra(final CompradoresPOJO obj) {
		try {
			final StringBuilder q = new StringBuilder("");
			final Object[] p = toParamsClave(obj);
			q.append("DELETE FROM " + TABLA + " ");
			q.append("WHERE  id_comprador = ?   ");

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
	 * com.spinnakersimple.modelo.dao.CompradoresDao#busca(com.spinnakersimple.
	 * modelo.entidad.tabla.CompradoresPOJO)
	 */
	@Override
	public CompradoresPOJO busca(final CompradoresPOJO obj) {
		try {
			final StringBuilder q = new StringBuilder("");
			final Object[] p = toParamsClave(obj);
			q.append("SELECT " + CAMPOS + " ");
			q.append("FROM " + TABLA + " ");
			q.append("WHERE  id_comprador = ?   ");

			log.trace(Cadenas.generaQuery(q, p));
			return this.jdbcTemplate.queryForObject(q.toString(), p, new CompradoresRowMapper());
		} catch (final EmptyResultDataAccessException e) {
			log.debug("No se ha encontrado la información buscada.", e);
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.spinnakersimple.modelo.dao.CompradoresDao#buscaTodos()
	 */
	@Override
	public List<CompradoresPOJO> buscaTodos() {
		final StringBuilder q = new StringBuilder("");
		final Object[] p = {};
		q.append("SELECT " + CAMPOS + " ");
		q.append("FROM " + TABLA + " ");

		log.trace(Cadenas.generaQuery(q, p));
		return this.jdbcTemplate.query(q.toString(), new BeanPropertyRowMapper<CompradoresPOJO>(CompradoresPOJO.class));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.spinnakersimple.modelo.dao.CompradoresDao#buscaVarios(com.
	 * spinnakersimple.modelo.entidad.tabla.CompradoresPOJO)
	 */
	@Override
	public List<CompradoresPOJO> buscaVarios(final CompradoresPOJO obj) {
		if (obj == null) {
			return buscaTodos();
		}

		final StringBuilder q = new StringBuilder("");
		q.append("SELECT " + CAMPOS + " ");
		q.append("FROM " + TABLA + " ");
		q.append("WHERE 1 = 1 ");

		final List<Object> parametros = new ArrayList<Object>();

		if (obj.getIdComprador() != null) {
			q.append("AND id_comprador = ? ");
			parametros.add(obj.getIdComprador());
		}
		if (obj.getIdAnuncio() != null) {
			q.append("AND id_anuncio = ? ");
			parametros.add(obj.getIdAnuncio());
		}
		if (obj.getNombre() != null) {
			q.append("AND nombre = ? ");
			parametros.add(obj.getNombre());
		}
		if (obj.getMail() != null) {
			q.append("AND mail = ? ");
			parametros.add(obj.getMail());
		}
		if (obj.getObservaciones() != null) {
			q.append("AND observaciones = ? ");
			parametros.add(obj.getObservaciones());
		}
		if (obj.getFecha() != null) {
			q.append("AND fecha = ? ");
			parametros.add(obj.getFecha());
		}
		if (obj.getRevisado() != null) {
			q.append("AND revisado = ? ");
			parametros.add(obj.getRevisado());
		}
		if (obj.getTelefono() != null) {
			q.append("AND telefono = ? ");
			parametros.add(obj.getTelefono());
		}
		final Object[] p = parametros.toArray();

		log.trace(Cadenas.generaQuery(q, p));
		return this.jdbcTemplate.query(q.toString(), p,
				new BeanPropertyRowMapper<CompradoresPOJO>(CompradoresPOJO.class));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.spinnakersimple.modelo.dao.CompradoresDao#crea(com.spinnakersimple.
	 * modelo.entidad.tabla.CompradoresPOJO)
	 */
	@Override
	public CompradoresPOJO crea(final CompradoresPOJO obj) {
		try {
			final CompradoresPOJO b = obj;
			StringBuilder q = new StringBuilder("");
			Object[] p = new Object[] {};
			q.append("LOCK TABLES " + TABLA + " WRITE;");
			log.trace(Cadenas.generaQuery(q, p));
			this.jdbcTemplate.update(q.toString(), p);

			q = new StringBuilder("");
			p = new Object[] {};
			q.append("SELECT IFNULL( MAX( id_comprador ) + 1, 1 ) FROM " + TABLA + ";");
			final Integer newId = this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class);

			q = new StringBuilder("");
			p = ArrayUtils.addAll(new Object[] { newId }, toParamsResto(obj));
			q.append("INSERT INTO " + TABLA + " ( " + CAMPOS + " ) ");
			q.append("VALUES ( " + CAMPOSINSERT + " ) ");

			log.trace(Cadenas.generaQuery(q, p));
			this.jdbcTemplate.update(q.toString(), p);

			b.setIdComprador(newId);
			return b;
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
	 * com.spinnakersimple.modelo.dao.CompradoresDao#existe(com.spinnakersimple.
	 * modelo.entidad.tabla.CompradoresPOJO)
	 */
	@Override
	public boolean existe(final CompradoresPOJO obj) {
		final StringBuilder q = new StringBuilder("");
		final Object[] p = toParamsClave(obj);
		q.append("SELECT COUNT(*) ");
		q.append("FROM " + TABLA + " ");
		q.append("WHERE  id_comprador = ?   ");

		log.trace(Cadenas.generaQuery(q, p));
		return this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class) > 0;
	}

	@Override
	public Integer numMensajesPendientes() {
		final StringBuilder q = new StringBuilder("");
		final Object[] p = new Object[] {};
		q.append("SELECT COUNT(*) ");
		q.append("FROM " + TABLA + " ");
		q.append("WHERE revisado = '0' ");

		log.trace(Cadenas.generaQuery(q, p));
		return this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class);
	}

	@Override
	public List<CompradoresPOJO> buscaMensajesDeAnunciosNoFinalizados(final CompradoresPOJO obj) {
		final StringBuilder q = new StringBuilder("");
		final List<Object> parametros = new ArrayList<Object>();
		q.append("SELECT " + Cadenas.prefijaCamposTabla(CAMPOS, "c") + " ");
		q.append("FROM " + TABLA + " c ");
		q.append("WHERE c.id_anuncio in ( SELECT id_anuncio ");
		q.append("FROM anuncio a ");
		q.append("WHERE a.id_anuncio = c.id_anuncio ");
		q.append("AND a.caduca > NOW() ");
		q.append("AND a.visible != 2 ) ");
		if (obj.getRevisado() != null) {
			q.append("AND c.revisado = ? ");
			parametros.add(obj.getRevisado());
		}
		if (log.isTraceEnabled()) {
			log.trace(Cadenas.generaQuery(q, parametros));
		}
		return this.jdbcTemplate.query(q.toString(), parametros.toArray(), new CompradoresRowMapper());
	}

	@Override
	public void historificaYEliminaPorUsuario( final Integer idUsuario ) {
		final Object[] p = { idUsuario };
		StringBuilder q = new StringBuilder();
		q.append("insert into compradores_his ( select NOW(), 1, c.* from compradores c where c.id_anuncio in ( select a.id_anuncio from anuncio a where a.id_usuario_alta = ? ) );" );
		log.trace(Cadenas.generaQuery(q, p));
		this.jdbcTemplate.update(q.toString(), p);;

		q = new StringBuilder();
		q.append("delete from compradores where id_anuncio in ( select a.id_anuncio from anuncio a where a.id_usuario_alta = ? );" );
		log.trace(Cadenas.generaQuery(q, p));
		this.jdbcTemplate.update(q.toString(), p);;
	}
}