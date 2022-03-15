package com.capitanperegrina.i18n.modelo.dao.impl.tabla;

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
import com.capitanperegrina.i18n.modelo.entidad.tabla.IdiomasPOJO;
import com.capitanperegrina.i18n.modelo.rowmappers.impl.tabla.IdiomasRowMapper;

/**
 * Objeto de acceso a datos para la tabla <code>idiomas<code>
 */
@Repository("idiomasDao")
@Transactional(propagation = Propagation.SUPPORTS)
public class IdiomasDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	static Logger log = Logger.getLogger(IdiomasDao.class);
	public static final String ENTIDAD = IdiomasPOJO.class.getName();
	public static final String TABLA = "idiomas";
	public static final String CAMPOS = "lang, nombre_idioma ";
	public static final String CAMPOSINSERT = " ?, ? ";
	public static final String CAMPOSUPDATE = " nombre_idioma = ? ";
	public static final String CAMPOSPKUPDATE = " lang = ? ";

	private static Object[] toParamsTodos(IdiomasPOJO obj) {
		return ArrayUtils.addAll(toParamsClave(obj), toParamsResto(obj));
	}

	private static Object[] toParamsUpdate(IdiomasPOJO obj) {
		return ArrayUtils.addAll(toParamsResto(obj), toParamsClave(obj));
	}

	private static Object[] toParamsClave(IdiomasPOJO obj) {
		return new Object[] { obj.getLang() };
	}

	private static Object[] toParamsResto(IdiomasPOJO obj) {
		return new Object[] { obj.getNombreIdioma() };
	}

	/**
	 * Método que inserta un registro en la tabla <code>idiomas</code> de la
	 * base de datos.
	 *
	 * @param obj
	 *            Objeto POJO con la información a insertar en la tabla
	 *            <code>idiomas</code> de la base de datos.
	 * @throws DuplicateKeyException
	 *             si ya existe un registro en la tabla <code>idiomas</code> de
	 *             base de datos con igual clave principal.
	 */
	public void crea(IdiomasPOJO obj) {
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

	/**
	 * Método que comprueba si existe un registro en la tabla
	 * <code>idiomas</code> de la base de datos a partir de su clave principal.
	 *
	 * @param obj
	 *            Objeto del que se desea comprobar su existencia en la tabla
	 *            <code>idiomas</code> de la base de datos.
	 * @return true si existe un registro en la tabla <code>idiomas</code> de la
	 *         base de datos con la misma clave principal, false si no es así.
	 */
	public boolean existe(IdiomasPOJO obj) {
		final StringBuilder q = new StringBuilder("");
		final Object[] p = toParamsClave(obj);
		q.append("SELECT COUNT(*) ");
		q.append("FROM " + TABLA + " ");
		q.append("WHERE  lang = ?   ");

		log.trace(Cadenas.generaQuery(q, p));
		return this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class) > 0;
	}

	/**
	 * Método que recupera de la base de datos un registro de la tabla
	 * <code>idiomas</code> y lo devuelve en el objeto IdiomasPOJO.
	 *
	 * @param obj
	 *            Objeto IdiomasPOJO con los campos de la clave principal
	 *            informados.
	 * @return El objeto IdiomasPOJO con la información recuperada de la base de
	 *         datos.
	 * @throws EmptyResultDataAccessException
	 *             si no se encuentra el resitro en la tabla
	 *             <code>idiomas</code>.
	 */
	public IdiomasPOJO busca(IdiomasPOJO obj) {
		try {
			final StringBuilder q = new StringBuilder("");
			final Object[] p = toParamsClave(obj);
			q.append("SELECT " + CAMPOS + " ");
			q.append("FROM " + TABLA + " ");
			q.append("WHERE  lang = ?   ");

			log.trace(Cadenas.generaQuery(q, p));
			return this.jdbcTemplate.queryForObject(q.toString(), p, new IdiomasRowMapper());
		} catch (final EmptyResultDataAccessException e) {
			log.debug("No se ha encontrado la información buscada.", e);
			throw e;
		}
	}

	/**
	 * Método que actualiza un registro de la tabla <code>idiomas</code> de la
	 * base de datos.
	 *
	 * @param obj
	 *            Objeto IdiomasPOJO que se quiere actualizar en la base de
	 *            datos.
	 * @throws EmptyResultDataAccessException
	 *             Si no se encuentra el registro que se desea actualizar en la
	 *             tabla <code>idiomas</code>.
	 * @throws DuplicateKeyException
	 *             si se encuentra más de un registro a actualizar en la tabla
	 *             la tabla <code>idiomas</code>.
	 */
	public void actualiza(IdiomasPOJO obj) {
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

	/**
	 * Método que elimina físicamente un registro de la tabla
	 * <code>idiomas</code> de la base de datos a partir de los valores de su
	 * clave principal.
	 *
	 * @param obj
	 *            Objeto IdiomasPOJO con los campos de la clave principal
	 *            informados.
	 * @throws EmptyResultDataAccessException
	 *             Si no se encuentra el registro que se desea eliminar en la
	 *             tabla <code>idiomas</code>.
	 */
	public void borra(IdiomasPOJO obj) {
		try {
			final StringBuilder q = new StringBuilder("");
			final Object[] p = toParamsClave(obj);
			q.append("DELETE FROM " + TABLA + " ");
			q.append("WHERE  lang = ?   ");

			log.trace(Cadenas.generaQuery(q, p));
			this.jdbcTemplate.update(q.toString(), p);
		} catch (final EmptyResultDataAccessException e) {
			log.debug("El registro que se desea eliminar no existe.", e);
			throw e;
		}
	}

	/**
	 * Método devuelve una lista con todos los registros de la tabla
	 * <code>idiomas</code> de la base de datos.
	 *
	 * @return Una lista con todos los elementos de la tabla
	 *         <code>idiomas</code>.
	 */
	public List<IdiomasPOJO> buscaTodos() {
		final StringBuilder q = new StringBuilder("");
		final Object[] p = {};
		q.append("SELECT " + CAMPOS + " ");
		q.append("FROM " + TABLA + " ");

		log.trace(Cadenas.generaQuery(q, p));
		return this.jdbcTemplate.query(q.toString(), new BeanPropertyRowMapper<IdiomasPOJO>(IdiomasPOJO.class));
	}

	/**
	 * Método devuelve una lista con todos los registros de la tabla
	 * <code>idiomas</code> de la base de datos a partir de los campos de de un
	 * POJO.
	 *
	 * @param obj
	 *            Objeto IdiomasPOJO con los campos por los que se desea buscar
	 *            informados.
	 * @return Una lista con todos los elementos de la tabla
	 *         <code>idiomas</code> que cumplan los criterios de búsqueda.
	 */
	public List<IdiomasPOJO> buscaVarios(IdiomasPOJO obj) {
		if (obj == null) {
			return buscaTodos();
		}

		final StringBuilder q = new StringBuilder("");
		q.append("SELECT " + CAMPOS + " ");
		q.append("FROM " + TABLA + " ");
		q.append("WHERE 1 = 1 ");

		final List<Object> parametros = new ArrayList<Object>();

		if (obj.getLang() != null) {
			q.append("AND lang = ? ");
			parametros.add(obj.getLang());
		}
		if (obj.getNombreIdioma() != null) {
			q.append("AND nombre_idioma = ? ");
			parametros.add(obj.getNombreIdioma());
		}
		final Object[] p = parametros.toArray();

		log.trace(Cadenas.generaQuery(q, p));
		return this.jdbcTemplate.query(q.toString(), p, new BeanPropertyRowMapper<IdiomasPOJO>(IdiomasPOJO.class));
	}

	/**
	 * Método que pasa a la tabla de historico un registro.
	 */
	public void historifica(IdiomasPOJO obj, CambioEnTabla ct) {
		final StringBuilder q = new StringBuilder();
		final Object[] p = toParamsClave(obj);
		q.append("INSERT INTO " + TABLA + "_his ");
		q.append("SELECT CURRENT, " + ct.getUsu() + ", " + CAMPOS + " ");
		q.append("FROM " + TABLA + " ");
		q.append("WHERE 1=1 ");
		if (obj.getLang() != null) {
			q.append("AND lang = ? ");
		}
		this.jdbcTemplate.update(q.toString(), p);
	}

	/**
	 * Método devuelve una lista con todos los registros de la tabla
	 * <code>idiomas</code> de la base de datos.
	 *
	 * @return Una lista con todos los elementos de la tabla
	 *         <code>idiomas</code>.
	 */
	public List<IdiomasPOJO> buscaTodosOrdenado() {
		final StringBuilder q = new StringBuilder("");
		final Object[] p = {};
		q.append("SELECT " + CAMPOS + " ");
		q.append("FROM " + TABLA + " ");
		q.append("ORDER BY nombre_idioma ");

		log.trace(Cadenas.generaQuery(q, p));
		return this.jdbcTemplate.query(q.toString(), new IdiomasRowMapper());
	}
}