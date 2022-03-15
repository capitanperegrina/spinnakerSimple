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
import com.capitanperegrina.i18n.modelo.entidad.tabla.PaisesPOJO;
import com.capitanperegrina.i18n.modelo.rowmappers.impl.tabla.PaisesRowMapper;

/**
 * Objeto de acceso a datos para la tabla <code>paises<code>
 */
@Repository("paisesDao")
@Transactional(propagation = Propagation.SUPPORTS)
public class PaisesDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	static Logger log = Logger.getLogger(PaisesDao.class);
	public static final String ENTIDAD = PaisesPOJO.class.getName();
	public static final String TABLA = "paises";
	public static final String CAMPOS = "codigo, lang, codigo2, codigo3, nombre_pais ";
	public static final String CAMPOSINSERT = " ?, ?, ?, ?, ? ";
	public static final String CAMPOSUPDATE = " codigo2 = ?, codigo3 = ?, nombre_pais = ? ";
	public static final String CAMPOSPKUPDATE = " codigo = ? and lang = ? ";

	private static Object[] toParamsTodos(PaisesPOJO obj) {
		return ArrayUtils.addAll(toParamsClave(obj), toParamsResto(obj));
	}

	private static Object[] toParamsUpdate(PaisesPOJO obj) {
		return ArrayUtils.addAll(toParamsResto(obj), toParamsClave(obj));
	}

	private static Object[] toParamsClave(PaisesPOJO obj) {
		return new Object[] { obj.getCodigo(), obj.getLang() };
	}

	private static Object[] toParamsResto(PaisesPOJO obj) {
		return new Object[] { obj.getCodigo2(), obj.getCodigo3(), obj.getNombrePais() };
	}

	/**
	 * Método que inserta un registro en la tabla <code>paises</code> de la base
	 * de datos.
	 *
	 * @param obj
	 *            Objeto POJO con la información a insertar en la tabla
	 *            <code>paises</code> de la base de datos.
	 * @throws DuplicateKeyException
	 *             si ya existe un registro en la tabla <code>paises</code> de
	 *             base de datos con igual clave principal.
	 */
	public void crea(PaisesPOJO obj) {
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
	 * <code>paises</code> de la base de datos a partir de su clave principal.
	 *
	 * @param obj
	 *            Objeto del que se desea comprobar su existencia en la tabla
	 *            <code>paises</code> de la base de datos.
	 * @return true si existe un registro en la tabla <code>paises</code> de la
	 *         base de datos con la misma clave principal, false si no es así.
	 */
	public boolean existe(PaisesPOJO obj) {
		final StringBuilder q = new StringBuilder("");
		final Object[] p = toParamsClave(obj);
		q.append("SELECT COUNT(*) ");
		q.append("FROM " + TABLA + " ");
		q.append("WHERE  codigo = ? \\n   AND lang = ?   ");

		log.trace(Cadenas.generaQuery(q, p));
		return this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class) > 0;
	}

	/**
	 * Método que recupera de la base de datos un registro de la tabla
	 * <code>paises</code> y lo devuelve en el objeto PaisesPOJO.
	 *
	 * @param obj
	 *            Objeto PaisesPOJO con los campos de la clave principal
	 *            informados.
	 * @return El objeto PaisesPOJO con la información recuperada de la base de
	 *         datos.
	 * @throws EmptyResultDataAccessException
	 *             si no se encuentra el resitro en la tabla <code>paises</code>
	 *             .
	 */
	public PaisesPOJO busca(PaisesPOJO obj) {
		try {
			final StringBuilder q = new StringBuilder("");
			final Object[] p = toParamsClave(obj);
			q.append("SELECT " + CAMPOS + " ");
			q.append("FROM " + TABLA + " ");
			q.append("WHERE  codigo = ? \\n   AND lang = ?   ");

			log.trace(Cadenas.generaQuery(q, p));
			return this.jdbcTemplate.queryForObject(q.toString(), p, new PaisesRowMapper());
		} catch (final EmptyResultDataAccessException e) {
			log.debug("No se ha encontrado la información buscada.", e);
			throw e;
		}
	}

	/**
	 * Método que actualiza un registro de la tabla <code>paises</code> de la
	 * base de datos.
	 *
	 * @param obj
	 *            Objeto PaisesPOJO que se quiere actualizar en la base de
	 *            datos.
	 * @throws EmptyResultDataAccessException
	 *             Si no se encuentra el registro que se desea actualizar en la
	 *             tabla <code>paises</code>.
	 * @throws DuplicateKeyException
	 *             si se encuentra más de un registro a actualizar en la tabla
	 *             la tabla <code>paises</code>.
	 */
	public void actualiza(PaisesPOJO obj) {
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
	 * <code>paises</code> de la base de datos a partir de los valores de su
	 * clave principal.
	 *
	 * @param obj
	 *            Objeto PaisesPOJO con los campos de la clave principal
	 *            informados.
	 * @throws EmptyResultDataAccessException
	 *             Si no se encuentra el registro que se desea eliminar en la
	 *             tabla <code>paises</code>.
	 */
	public void borra(PaisesPOJO obj) {
		try {
			final StringBuilder q = new StringBuilder("");
			final Object[] p = toParamsClave(obj);
			q.append("DELETE FROM " + TABLA + " ");
			q.append("WHERE  codigo = ? \\n   AND lang = ?   ");

			log.trace(Cadenas.generaQuery(q, p));
			this.jdbcTemplate.update(q.toString(), p);
		} catch (final EmptyResultDataAccessException e) {
			log.debug("El registro que se desea eliminar no existe.", e);
			throw e;
		}
	}

	/**
	 * Método devuelve una lista con todos los registros de la tabla
	 * <code>paises</code> de la base de datos.
	 *
	 * @return Una lista con todos los elementos de la tabla <code>paises</code>
	 *         .
	 */
	public List<PaisesPOJO> buscaTodos() {
		final StringBuilder q = new StringBuilder("");
		final Object[] p = {};
		q.append("SELECT " + CAMPOS + " ");
		q.append("FROM " + TABLA + " ");

		log.trace(Cadenas.generaQuery(q, p));
		return this.jdbcTemplate.query(q.toString(), new BeanPropertyRowMapper<PaisesPOJO>(PaisesPOJO.class));
	}

	/**
	 * Método devuelve una lista con todos los registros de la tabla
	 * <code>paises</code> de la base de datos a partir de los campos de de un
	 * POJO.
	 *
	 * @param obj
	 *            Objeto PaisesPOJO con los campos por los que se desea buscar
	 *            informados.
	 * @return Una lista con todos los elementos de la tabla <code>paises</code>
	 *         que cumplan los criterios de búsqueda.
	 */
	public List<PaisesPOJO> buscaVarios(PaisesPOJO obj, boolean ordenado) {
		if (obj == null) {
			return buscaTodos();
		}

		final StringBuilder q = new StringBuilder("");
		q.append("SELECT " + CAMPOS + " ");
		q.append("FROM " + TABLA + " ");
		q.append("WHERE 1 = 1 ");

		final List<Object> parametros = new ArrayList<Object>();

		if (obj.getCodigo() != null) {
			q.append("AND codigo = ? ");
			parametros.add(obj.getCodigo());
		}
		if (obj.getLang() != null) {
			q.append("AND lang = ? ");
			parametros.add(obj.getLang());
		}
		if (obj.getCodigo2() != null) {
			q.append("AND codigo2 = ? ");
			parametros.add(obj.getCodigo2());
		}
		if (obj.getCodigo3() != null) {
			q.append("AND codigo3 = ? ");
			parametros.add(obj.getCodigo3());
		}
		if (obj.getNombrePais() != null) {
			q.append("AND nombre_pais = ? ");
			parametros.add(obj.getNombrePais());
		}
		if (ordenado) {
			q.append("ORDER BY nombre_pais ");
		}
		final Object[] p = parametros.toArray();

		log.trace(Cadenas.generaQuery(q, p));
		return this.jdbcTemplate.query(q.toString(), p, new BeanPropertyRowMapper<PaisesPOJO>(PaisesPOJO.class));
	}

	/**
	 * Método que pasa a la tabla de historico un registro.
	 */
	public void historifica(PaisesPOJO obj, CambioEnTabla ct) {
		final StringBuilder q = new StringBuilder();
		final Object[] p = toParamsClave(obj);
		q.append("INSERT INTO " + TABLA + "_his ");
		q.append("SELECT CURRENT, " + ct.getUsu() + ", " + CAMPOS + " ");
		q.append("FROM " + TABLA + " ");
		q.append("WHERE 1=1 ");
		if (obj.getCodigo() != null) {
			q.append("AND codigo = ? ");
		}
		if (obj.getLang() != null) {
			q.append("AND lang = ? ");
		}
		this.jdbcTemplate.update(q.toString(), p);
	}
}