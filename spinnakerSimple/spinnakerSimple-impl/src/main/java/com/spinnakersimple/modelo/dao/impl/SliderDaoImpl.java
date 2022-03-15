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
import com.spinnakersimple.modelo.dao.SliderDao;
import com.spinnakersimple.modelo.dao.rowmapper.SliderRowMapper;
import com.spinnakersimple.modelo.entidad.SliderPOJO;
import com.spinnakersimple.modelo.util.SpinnakerSimpleGlobals;

/**
 * The Class SliderDaoImpl.
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public class SliderDaoImpl implements SliderDao {

	/** The log. */
	static Logger log = Logger.getLogger(SliderDaoImpl.class);

	/** The Constant ENTIDAD. */
	public static final String ENTIDAD = SliderPOJO.class.getName();

	/** The Constant TABLA. */
	public static final String TABLA = "slider";

	/** The Constant CAMPOS. */
	public static final String CAMPOS = "id_slider, imagen_home, donde_home ";

	/** The Constant CAMPOSINSERT. */
	public static final String CAMPOSINSERT = " ?, ?, ? ";

	/** The Constant CAMPOSUPDATE. */
	public static final String CAMPOSUPDATE = " imagen_home = ?, donde_home = ? ";

	/** The Constant CAMPOSPKUPDATE. */
	public static final String CAMPOSPKUPDATE = " id_slider = ? ";

	/**
	 * To params clave.
	 *
	 * @param obj
	 *            the obj
	 * @return the object[]
	 */
	private static Object[] toParamsClave(SliderPOJO obj) {
		return new Object[] { obj.getIdSlider() };
	}

	/**
	 * To params resto.
	 *
	 * @param obj
	 *            the obj
	 * @return the object[]
	 */
	private static Object[] toParamsResto(SliderPOJO obj) {
		return new Object[] { obj.getImagenHome(), obj.getDondeHome() };
	}

	/**
	 * To params todos.
	 *
	 * @param obj
	 *            the obj
	 * @return the object[]
	 */
	private static Object[] toParamsTodos(SliderPOJO obj) {
		return ArrayUtils.addAll(toParamsClave(obj), toParamsResto(obj));
	}

	/**
	 * To params update.
	 *
	 * @param obj
	 *            the obj
	 * @return the object[]
	 */
	private static Object[] toParamsUpdate(SliderPOJO obj) {
		return ArrayUtils.addAll(toParamsResto(obj), toParamsClave(obj));
	}

	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.spinnakersimple.modelo.dao.SliderDao#actualiza(com.spinnakersimple.
	 * modelo.entidad.tabla.SliderPOJO)
	 */
	@Override
	public void actualiza(SliderPOJO obj) {
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
	 * com.spinnakersimple.modelo.dao.SliderDao#borra(com.spinnakersimple.modelo
	 * .entidad.tabla.SliderPOJO)
	 */
	@Override
	public void borra(SliderPOJO obj) {
		try {
			final StringBuilder q = new StringBuilder("");
			final Object[] p = toParamsClave(obj);
			q.append("DELETE FROM " + TABLA + " ");
			q.append("WHERE  id_slider = ?   ");

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
	 * com.spinnakersimple.modelo.dao.SliderDao#busca(com.spinnakersimple.modelo
	 * .entidad.tabla.SliderPOJO)
	 */
	@Override
	public SliderPOJO busca(SliderPOJO obj) {
		try {
			final StringBuilder q = new StringBuilder("");
			final Object[] p = toParamsClave(obj);
			q.append("SELECT " + CAMPOS + " ");
			q.append("FROM " + TABLA + " ");
			q.append("WHERE  id_slider = ?   ");

			log.trace(Cadenas.generaQuery(q, p));
			return this.jdbcTemplate.queryForObject(q.toString(), p, new SliderRowMapper());
		} catch (final EmptyResultDataAccessException e) {
			log.debug("No se ha encontrado la información buscada.", e);
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.spinnakersimple.modelo.dao.SliderDao#buscaParaCompra()
	 */
	@Override
	public List<SliderPOJO> buscaParaCompra() {
		final SliderPOJO filtro = new SliderPOJO();
		filtro.setDondeHome(SpinnakerSimpleGlobals.COMRAS);
		return buscaVarios(filtro);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.spinnakersimple.modelo.dao.SliderDao#buscaParaVenta()
	 */
	@Override
	public List<SliderPOJO> buscaParaVenta() {
		final SliderPOJO filtro = new SliderPOJO();
		filtro.setDondeHome(SpinnakerSimpleGlobals.VENTAS);
		return buscaVarios(filtro);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.spinnakersimple.modelo.dao.SliderDao#buscaTodos()
	 */
	@Override
	public List<SliderPOJO> buscaTodos() {
		final StringBuilder q = new StringBuilder("");
		final Object[] p = {};
		q.append("SELECT " + CAMPOS + " ");
		q.append("FROM " + TABLA + " ");

		log.trace(Cadenas.generaQuery(q, p));
		return this.jdbcTemplate.query(q.toString(), new BeanPropertyRowMapper<SliderPOJO>(SliderPOJO.class));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.spinnakersimple.modelo.dao.SliderDao#buscaVarios(com.spinnakersimple.
	 * modelo.entidad.tabla.SliderPOJO)
	 */
	@Override
	public List<SliderPOJO> buscaVarios(SliderPOJO obj) {
		if (obj == null) {
			return buscaTodos();
		}

		final StringBuilder q = new StringBuilder("");
		q.append("SELECT " + CAMPOS + " ");
		q.append("FROM " + TABLA + " ");
		q.append("WHERE 1 = 1 ");

		final List<Object> parametros = new ArrayList<Object>();

		if (obj.getIdSlider() != null) {
			q.append("AND id_slider = ? ");
			parametros.add(obj.getIdSlider());
		}
		if (obj.getImagenHome() != null) {
			q.append("AND imagen_home = ? ");
			parametros.add(obj.getImagenHome());
		}
		if (obj.getDondeHome() != null) {
			q.append("AND donde_home = ? ");
			parametros.add(obj.getDondeHome());
		}
		final Object[] p = parametros.toArray();

		log.trace(Cadenas.generaQuery(q, p));
		return this.jdbcTemplate.query(q.toString(), p, new BeanPropertyRowMapper<SliderPOJO>(SliderPOJO.class));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.spinnakersimple.modelo.dao.SliderDao#crea(com.spinnakersimple.modelo.
	 * entidad.tabla.SliderPOJO)
	 */
	@Override
	public void crea(SliderPOJO obj) {
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
	 * @see com.spinnakersimple.modelo.dao.SliderDao#existe(com.spinnakersimple.
	 * modelo.entidad.tabla.SliderPOJO)
	 */
	@Override
	public boolean existe(SliderPOJO obj) {
		final StringBuilder q = new StringBuilder("");
		final Object[] p = toParamsClave(obj);
		q.append("SELECT COUNT(*) ");
		q.append("FROM " + TABLA + " ");
		q.append("WHERE  id_slider = ?   ");

		log.trace(Cadenas.generaQuery(q, p));
		return this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class) > 0;
	}
}