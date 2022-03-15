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

import com.capitanperegrina.common.basebeans.CambioEnTabla;
import com.capitanperegrina.common.utils.Cadenas;
import com.spinnakersimple.modelo.dao.TextoDao;
import com.spinnakersimple.modelo.dao.rowmapper.TextoRowMapper;
import com.spinnakersimple.modelo.entidad.TextoPOJO;

/**
 * The Class TextoDaoImpl.
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public class TextoDaoImpl implements TextoDao {

	/** The log. */
	static Logger log = Logger.getLogger(TextoDaoImpl.class);

	/** The Constant ENTIDAD. */
	public static final String ENTIDAD = TextoPOJO.class.getName();

	/** The Constant TABLA. */
	public static final String TABLA = "texto";

	/** The Constant CAMPOS. */
	public static final String CAMPOS = "id_texto, lang, clave, valor, id_usuario_alta, fec_alta, id_usuario_modif, fec_modif ";

	/** The Constant CAMPOSINSERT. */
	public static final String CAMPOSINSERT = " ?, ?, ?, ?, ?, ?, ?, ? ";

	/** The Constant CAMPOSUPDATE. */
	public static final String CAMPOSUPDATE = " lang = ?, clave = ?, valor = ?, id_usuario_alta = ?, fec_alta = ?, id_usuario_modif = ?, fec_modif = ? ";

	/** The Constant CAMPOSPKUPDATE. */
	public static final String CAMPOSPKUPDATE = " id_texto = ? ";

	/**
	 * To params clave.
	 *
	 * @param obj
	 *            the obj
	 * @return the object[]
	 */
	private static Object[] toParamsClave(TextoPOJO obj) {
		return new Object[] { obj.getIdTexto() };
	}

	/**
	 * To params resto.
	 *
	 * @param obj
	 *            the obj
	 * @return the object[]
	 */
	private static Object[] toParamsResto(TextoPOJO obj) {
		return new Object[] { obj.getLang(), obj.getClave(), obj.getValor(), obj.getIdUsuarioAlta(), obj.getFecAlta(),
				obj.getIdUsuarioModif(), obj.getFecModif() };
	}

	/**
	 * To params todos.
	 *
	 * @param obj
	 *            the obj
	 * @return the object[]
	 */
	private static Object[] toParamsTodos(TextoPOJO obj) {
		return ArrayUtils.addAll(toParamsClave(obj), toParamsResto(obj));
	}

	/**
	 * To params update.
	 *
	 * @param obj
	 *            the obj
	 * @return the object[]
	 */
	private static Object[] toParamsUpdate(TextoPOJO obj) {
		return ArrayUtils.addAll(toParamsResto(obj), toParamsClave(obj));
	}

	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.spinnakersimple.modelo.dao.TextoDao#actualiza(com.spinnakersimple.
	 * modelo.entidad.tabla.TextoPOJO)
	 */
	@Override
	public void actualiza(TextoPOJO obj) {
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
	 * com.spinnakersimple.modelo.dao.TextoDao#borra(com.spinnakersimple.modelo.
	 * entidad.tabla.TextoPOJO)
	 */
	@Override
	public void borra(TextoPOJO obj) {
		try {
			final StringBuilder q = new StringBuilder("");
			final Object[] p = toParamsClave(obj);
			q.append("DELETE FROM " + TABLA + " ");
			q.append("WHERE  id_texto = ?   ");

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
	 * com.spinnakersimple.modelo.dao.TextoDao#busca(com.spinnakersimple.modelo.
	 * entidad.tabla.TextoPOJO)
	 */
	@Override
	public TextoPOJO busca(TextoPOJO obj) {
		try {
			final StringBuilder q = new StringBuilder("");
			final Object[] p = toParamsClave(obj);
			q.append("SELECT " + CAMPOS + " ");
			q.append("FROM " + TABLA + " ");
			q.append("WHERE  id_texto = ?   ");

			log.trace(Cadenas.generaQuery(q, p));
			return this.jdbcTemplate.queryForObject(q.toString(), p, new TextoRowMapper());
		} catch (final EmptyResultDataAccessException e) {
			log.debug("No se ha encontrado la información buscada.", e);
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.spinnakersimple.modelo.dao.TextoDao#buscaPorClaveLang(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public TextoPOJO buscaPorClaveLang(String clave, String lang) {
		try {
			final StringBuilder q = new StringBuilder("");
			final Object[] p = { clave, lang };
			q.append("SELECT " + CAMPOS + " ");
			q.append("FROM " + TABLA + " ");
			q.append("WHERE  clave = ? ");
			q.append("AND  lang = ? ");

			log.trace(Cadenas.generaQuery(q, p));
			return this.jdbcTemplate.queryForObject(q.toString(), p, new TextoRowMapper());
		} catch (final EmptyResultDataAccessException e) {
			log.debug("No se ha encontrado la información buscada.", e);
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.spinnakersimple.modelo.dao.TextoDao#buscaTodos()
	 */
	@Override
	public List<TextoPOJO> buscaTodos() {
		final StringBuilder q = new StringBuilder("");
		final Object[] p = {};
		q.append("SELECT " + CAMPOS + " ");
		q.append("FROM " + TABLA + " ");

		log.trace(Cadenas.generaQuery(q, p));
		return this.jdbcTemplate.query(q.toString(), new BeanPropertyRowMapper<TextoPOJO>(TextoPOJO.class));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.spinnakersimple.modelo.dao.TextoDao#buscaVarios(com.spinnakersimple.
	 * modelo.entidad.tabla.TextoPOJO)
	 */
	@Override
	public List<TextoPOJO> buscaVarios(TextoPOJO obj) {
		if (obj == null) {
			return buscaTodos();
		}

		final StringBuilder q = new StringBuilder("");
		q.append("SELECT " + CAMPOS + " ");
		q.append("FROM " + TABLA + " ");
		q.append("WHERE 1 = 1 ");

		final List<Object> parametros = new ArrayList<Object>();

		if (obj.getIdTexto() != null) {
			q.append("AND id_texto = ? ");
			parametros.add(obj.getIdTexto());
		}
		if (obj.getLang() != null) {
			q.append("AND lang = ? ");
			parametros.add(obj.getLang());
		}
		if (obj.getClave() != null) {
			q.append("AND clave = ? ");
			parametros.add(obj.getClave());
		}
		if (obj.getValor() != null) {
			q.append("AND valor = ? ");
			parametros.add(obj.getValor());
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
		return this.jdbcTemplate.query(q.toString(), p, new BeanPropertyRowMapper<TextoPOJO>(TextoPOJO.class));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.spinnakersimple.modelo.dao.TextoDao#crea(com.spinnakersimple.modelo.
	 * entidad.tabla.TextoPOJO)
	 */
	@Override
	public void crea(TextoPOJO obj) {
		try {
			final StringBuilder q = new StringBuilder("");
			final Object[] p = toParamsTodos(obj);
			q.append("INSERT INTO " + TABLA + " ( " + CAMPOS + " ) ");
			q.append("VALUES ( " + CAMPOSINSERT + " ) ");

			log.trace(Cadenas.generaQuery(q, p));
			this.jdbcTemplate.update(q.toString(), p);
		} catch (final DuplicateKeyException e) {
			log.debug("Clave duplicada al intentar insertar la información.", e);
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.spinnakersimple.modelo.dao.TextoDao#existe(com.spinnakersimple.modelo
	 * .entidad.tabla.TextoPOJO)
	 */
	@Override
	public boolean existe(TextoPOJO obj) {
		final StringBuilder q = new StringBuilder("");
		final Object[] p = toParamsClave(obj);
		q.append("SELECT COUNT(*) ");
		q.append("FROM " + TABLA + " ");
		q.append("WHERE  id_texto = ?   ");

		log.trace(Cadenas.generaQuery(q, p));
		return this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class) > 0;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.spinnakersimple.modelo.dao.TextoDao#historifica(com.spinnakersimple.
	 * modelo.entidad.tabla.TextoPOJO,
	 * com.capitanperegrina.common.basebeans.CambioEnTabla)
	 */
	@Override
	public void historifica(TextoPOJO obj, CambioEnTabla ct) {
		final StringBuilder q = new StringBuilder();
		final Object[] p = toParamsClave(obj);
		q.append("INSERT INTO " + TABLA + "_his ");
		q.append("SELECT CURRENT, " + ct.getUsu() + ", " + CAMPOS + " ");
		q.append("FROM " + TABLA + " ");
		q.append("WHERE 1=1 ");
		if (obj.getIdTexto() != null) {
			q.append("AND id_texto = ? ");
		}
		this.jdbcTemplate.update(q.toString(), p);
	}
}