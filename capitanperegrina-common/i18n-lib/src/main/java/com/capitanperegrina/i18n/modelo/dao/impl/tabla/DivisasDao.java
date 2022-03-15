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

import com.capitanperegrina.common.utils.Cadenas;
import com.capitanperegrina.i18n.modelo.entidad.tabla.DivisasPOJO;
import com.capitanperegrina.i18n.modelo.rowmappers.impl.tabla.DivisasRowMapper;

/**
 * Objeto de acceso a datos para la tabla <code>divisas<code>
 */
@Repository("divisasDao")
@Transactional(propagation = Propagation.SUPPORTS)
public class DivisasDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	static Logger log = Logger.getLogger(DivisasDao.class);
	public static final String ENTIDAD = DivisasPOJO.class.getName();
	public static final String TABLA = "divisas";
	public static final String CAMPOS = "codigo_divisa, descripcion_divisa, simbolo_divisa ";
	public static final String CAMPOSINSERT = " ?, ?, ? ";
	public static final String CAMPOSUPDATE = " descripcion_divisa = ?, simbolo_divisa = ? ";
	public static final String CAMPOSPKUPDATE = " codigo_divisa = ? ";

	private static Object[] toParamsTodos(DivisasPOJO obj) {
		return ArrayUtils.addAll(toParamsClave(obj), toParamsResto(obj));
	}

	private static Object[] toParamsUpdate(DivisasPOJO obj) {
		return ArrayUtils.addAll(toParamsResto(obj), toParamsClave(obj));
	}

	private static Object[] toParamsClave(DivisasPOJO obj) {
		return new Object[] { obj.getCodigoDivisa() };
	}

	private static Object[] toParamsResto(DivisasPOJO obj) {
		return new Object[] { obj.getDescripcionDivisa(), obj.getSimboloDivisa() };
	}

	/**
	 * Método que inserta un registro en la tabla <code>divisas</code> de la
	 * base de datos.
	 *
	 * @param obj
	 *            Objeto POJO con la información a insertar en la tabla
	 *            <code>divisas</code> de la base de datos.
	 * @throws DuplicateKeyException
	 *             si ya existe un registro en la tabla <code>divisas</code> de
	 *             base de datos con igual clave principal.
	 */
	public void crea(DivisasPOJO obj) {
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
	 * <code>divisas</code> de la base de datos a partir de su clave principal.
	 *
	 * @param obj
	 *            Objeto del que se desea comprobar su existencia en la tabla
	 *            <code>divisas</code> de la base de datos.
	 * @return true si existe un registro en la tabla <code>divisas</code> de la
	 *         base de datos con la misma clave principal, false si no es así.
	 */
	public boolean existe(DivisasPOJO obj) {
		final StringBuilder q = new StringBuilder("");
		final Object[] p = toParamsClave(obj);
		q.append("SELECT COUNT(*) ");
		q.append("FROM " + TABLA + " ");
		q.append("WHERE  codigo_divisa = ?   ");

		log.trace(Cadenas.generaQuery(q, p));
		return this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class) > 0;
	}

	/**
	 * Método que recupera de la base de datos un registro de la tabla
	 * <code>divisas</code> y lo devuelve en el objeto DivisasPOJO.
	 *
	 * @param obj
	 *            Objeto DivisasPOJO con los campos de la clave principal
	 *            informados.
	 * @return El objeto DivisasPOJO con la información recuperada de la base de
	 *         datos.
	 * @throws EmptyResultDataAccessException
	 *             si no se encuentra el resitro en la tabla
	 *             <code>divisas</code>.
	 */
	public DivisasPOJO busca(DivisasPOJO obj) {
		try {
			final StringBuilder q = new StringBuilder("");
			final Object[] p = toParamsClave(obj);
			q.append("SELECT " + CAMPOS + " ");
			q.append("FROM " + TABLA + " ");
			q.append("WHERE  codigo_divisa = ?   ");

			log.trace(Cadenas.generaQuery(q, p));
			return this.jdbcTemplate.queryForObject(q.toString(), p, new DivisasRowMapper());
		} catch (final EmptyResultDataAccessException e) {
			log.debug("No se ha encontrado la información buscada.", e);
			throw e;
		}
	}

	/**
	 * Método que actualiza un registro de la tabla <code>divisas</code> de la
	 * base de datos.
	 *
	 * @param obj
	 *            Objeto DivisasPOJO que se quiere actualizar en la base de
	 *            datos.
	 * @throws EmptyResultDataAccessException
	 *             Si no se encuentra el registro que se desea actualizar en la
	 *             tabla <code>divisas</code>.
	 * @throws DuplicateKeyException
	 *             si se encuentra más de un registro a actualizar en la tabla
	 *             la tabla <code>divisas</code>.
	 */
	public void actualiza(DivisasPOJO obj) {
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
	 * <code>divisas</code> de la base de datos a partir de los valores de su
	 * clave principal.
	 *
	 * @param obj
	 *            Objeto DivisasPOJO con los campos de la clave principal
	 *            informados.
	 * @throws EmptyResultDataAccessException
	 *             Si no se encuentra el registro que se desea eliminar en la
	 *             tabla <code>divisas</code>.
	 */
	public void borra(DivisasPOJO obj) {
		try {
			final StringBuilder q = new StringBuilder("");
			final Object[] p = toParamsClave(obj);
			q.append("DELETE FROM " + TABLA + " ");
			q.append("WHERE  codigo_divisa = ?   ");

			log.trace(Cadenas.generaQuery(q, p));
			this.jdbcTemplate.update(q.toString(), p);
		} catch (final EmptyResultDataAccessException e) {
			log.debug("El registro que se desea eliminar no existe.", e);
			throw e;
		}
	}

	/**
	 * Método devuelve una lista con todos los registros de la tabla
	 * <code>divisas</code> de la base de datos.
	 *
	 * @return Una lista con todos los elementos de la tabla
	 *         <code>divisas</code>.
	 */
	public List<DivisasPOJO> buscaTodos() {
		final StringBuilder q = new StringBuilder("");
		final Object[] p = {};
		q.append("SELECT " + CAMPOS + " ");
		q.append("FROM " + TABLA + " ");

		log.trace(Cadenas.generaQuery(q, p));
		return this.jdbcTemplate.query(q.toString(), new BeanPropertyRowMapper<DivisasPOJO>(DivisasPOJO.class));
	}

	/**
	 * Método devuelve una lista con todos los registros de la tabla
	 * <code>divisas</code> de la base de datos a partir de los campos de de un
	 * POJO.
	 *
	 * @param obj
	 *            Objeto DivisasPOJO con los campos por los que se desea buscar
	 *            informados.
	 * @return Una lista con todos los elementos de la tabla
	 *         <code>divisas</code> que cumplan los criterios de búsqueda.
	 */
	public List<DivisasPOJO> buscaVarios(DivisasPOJO obj) {
		if (obj == null) {
			return buscaTodos();
		}

		final StringBuilder q = new StringBuilder("");
		q.append("SELECT " + CAMPOS + " ");
		q.append("FROM " + TABLA + " ");
		q.append("WHERE 1 = 1 ");

		final List<Object> parametros = new ArrayList<Object>();

		if (obj.getCodigoDivisa() != null) {
			q.append("AND codigo_divisa = ? ");
			parametros.add(obj.getCodigoDivisa());
		}
		if (obj.getDescripcionDivisa() != null) {
			q.append("AND descripcion_divisa = ? ");
			parametros.add(obj.getDescripcionDivisa());
		}
		if (obj.getSimboloDivisa() != null) {
			q.append("AND simbolo_divisa = ? ");
			parametros.add(obj.getSimboloDivisa());
		}
		final Object[] p = parametros.toArray();

		log.trace(Cadenas.generaQuery(q, p));
		return this.jdbcTemplate.query(q.toString(), p, new BeanPropertyRowMapper<DivisasPOJO>(DivisasPOJO.class));
	}
}