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
import com.capitanperegrina.common.exceptions.ServicioException;
import com.capitanperegrina.common.sql.SQLUtils;
import com.capitanperegrina.common.utils.Cadenas;
import com.spinnakersimple.modelo.dao.UsuarioDao;
import com.spinnakersimple.modelo.dao.rowmapper.UsuarioCompletoViewRowmapper;
import com.spinnakersimple.modelo.dao.rowmapper.UsuarioRowMapper;
import com.spinnakersimple.modelo.entidad.UsuarioPOJO;
import com.spinnakersimple.modelo.vista.UsuarioCompletoView;

/**
 * The Class UsuarioDaoImpl.
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public class UsuarioDaoImpl implements UsuarioDao {

	/** The log. */
	static Logger log = Logger.getLogger(UsuarioDaoImpl.class);

	/** The Constant ENTIDAD. */
	public static final String ENTIDAD = UsuarioPOJO.class.getName();

	/** The Constant TABLA. */
	public static final String TABLA = "usuario";

	/** The Constant CAMPOS. */
	public static final String CAMPOS = "id_usuario, nombre, apellidos, mail, movil, direccion_1, direccion_2, provincia, pais, divisa, cod_postal, admin, pass, lang, ip, fallos_login, id_usuario_alta, fec_alta, id_usuario_modif, fec_modif ";

	/** The Constant CAMPOSINSERT. */
	public static final String CAMPOSINSERT = " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ";

	/** The Constant CAMPOSUPDATE. */
	public static final String CAMPOSUPDATE = " nombre = ?, apellidos = ?, mail = ?, movil = ?, direccion_1 = ?, direccion_2 = ?, provincia = ?, pais = ?, divisa = ?, cod_postal = ?, admin = ?, pass = ?, lang = ?, ip = ?, fallos_login = ?, id_usuario_alta = ?, fec_alta = ?, id_usuario_modif = ?, fec_modif = ? ";

	/** The Constant CAMPOSPKUPDATE. */
	public static final String CAMPOSPKUPDATE = " id_usuario = ? ";

	/**
	 * To params clave.
	 *
	 * @param obj
	 *            the obj
	 * @return the object[]
	 */
	private static Object[] toParamsClave(final UsuarioPOJO obj) {
		return new Object[] { obj.getIdUsuario() };
	}

	/**
	 * To params resto.
	 *
	 * @param obj
	 *            the obj
	 * @return the object[]
	 */
	private static Object[] toParamsResto(final UsuarioPOJO obj) {
		return new Object[] { obj.getNombre(), obj.getApellidos(), obj.getMail(), obj.getMovil(), obj.getDireccion1(),
				obj.getDireccion2(), obj.getProvincia(), obj.getPais(), obj.getDivisa(), obj.getCodPostal(),
				obj.getAdmin(), obj.getPass(), obj.getLang(), obj.getIp(), obj.getFallosLogin(), obj.getIdUsuarioAlta(),
				obj.getFecAlta(), obj.getIdUsuarioModif(), obj.getFecModif() };
	}

	/**
	 * To params todos.
	 *
	 * @param obj
	 *            the obj
	 * @return the object[]
	 */
	private static Object[] toParamsTodos(final UsuarioPOJO obj) {
		return ArrayUtils.addAll(toParamsClave(obj), toParamsResto(obj));
	}

	/**
	 * To params update.
	 *
	 * @param obj
	 *            the obj
	 * @return the object[]
	 */
	private static Object[] toParamsUpdate(final UsuarioPOJO obj) {
		return ArrayUtils.addAll(toParamsResto(obj), toParamsClave(obj));
	}

	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.spinnakersimple.modelo.dao.UsuarioDao#actualiza(com.spinnakersimple.
	 * modelo.entidad.tabla.UsuarioPOJO)
	 */
	@Override
	public void actualiza(final UsuarioPOJO obj) {
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
	 * @see com.spinnakersimple.modelo.dao.UsuarioDao#borra(com.spinnakersimple.
	 * modelo.entidad.tabla.UsuarioPOJO)
	 */
	@Override
	public void borra(final UsuarioPOJO obj) {
		try {
			final StringBuilder q = new StringBuilder("");
			final Object[] p = toParamsClave(obj);
			q.append("DELETE FROM " + TABLA + " ");
			q.append("WHERE  id_usuario = ?   ");

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
	 * @see com.spinnakersimple.modelo.dao.UsuarioDao#busca(com.spinnakersimple.
	 * modelo.entidad.tabla.UsuarioPOJO)
	 */
	@Override
	public UsuarioPOJO busca(final UsuarioPOJO obj) throws ServicioException {
		try {
			final StringBuilder q = new StringBuilder("");
			final Object[] p = toParamsClave(obj);
			q.append("SELECT " + CAMPOS + " ");
			q.append("FROM " + TABLA + " ");
			q.append("WHERE  id_usuario = ?   ");

			log.trace(Cadenas.generaQuery(q, p));
			return this.jdbcTemplate.queryForObject(q.toString(), p, new UsuarioRowMapper());
		} catch (final EmptyResultDataAccessException e) {
			log.debug("No se ha encontrado la información buscada.");
			throw new ServicioException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.spinnakersimple.modelo.dao.UsuarioDao#buscaPorMail(java.lang.String)
	 */
	@Override
	public UsuarioPOJO buscaPorMail(final String mail) throws ServicioException {
		try {
			final StringBuilder q = new StringBuilder("");
			final Object[] p = { mail };
			q.append("SELECT " + CAMPOS + " ");
			q.append("FROM " + TABLA + " ");
			q.append("WHERE  mail = ?   ");

			log.trace(Cadenas.generaQuery(q, p));
			return this.jdbcTemplate.queryForObject(q.toString(), p, new UsuarioRowMapper());
		} catch (final EmptyResultDataAccessException e) {
			log.debug("No se ha encontrado la información buscada.");
			throw new ServicioException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.spinnakersimple.modelo.dao.UsuarioDao#buscaTodos()
	 */
	@Override
	public List<UsuarioPOJO> buscaTodos() {
		final StringBuilder q = new StringBuilder("");
		final Object[] p = {};
		q.append("SELECT " + CAMPOS + " ");
		q.append("FROM " + TABLA + " ");

		log.trace(Cadenas.generaQuery(q, p));
		return this.jdbcTemplate.query(q.toString(), new BeanPropertyRowMapper<UsuarioPOJO>(UsuarioPOJO.class));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.spinnakersimple.modelo.dao.UsuarioDao#buscaVarios(com.spinnakersimple
	 * .modelo.entidad.tabla.UsuarioPOJO)
	 */
	@Override
	public List<UsuarioPOJO> buscaVarios(final UsuarioPOJO obj) {
		if (obj == null) {
			return buscaTodos();
		}

		final StringBuilder q = new StringBuilder("");
		q.append("SELECT " + CAMPOS + " ");
		q.append("FROM " + TABLA + " ");
		q.append("WHERE 1 = 1 ");

		final List<Object> parametros = new ArrayList<Object>();

		if (obj.getIdUsuario() != null) {
			q.append("AND id_usuario = ? ");
			parametros.add(obj.getIdUsuario());
		}
		if (obj.getNombre() != null) {
			q.append("AND nombre = ? ");
			parametros.add(obj.getNombre());
		}
		if (obj.getApellidos() != null) {
			q.append("AND apellidos = ? ");
			parametros.add(obj.getApellidos());
		}
		if (obj.getMail() != null) {
			q.append("AND mail = ? ");
			parametros.add(obj.getMail());
		}
		if (obj.getMovil() != null) {
			q.append("AND movil = ? ");
			parametros.add(obj.getMovil());
		}
		if (obj.getDireccion1() != null) {
			q.append("AND direccion_1 = ? ");
			parametros.add(obj.getDireccion1());
		}
		if (obj.getDireccion2() != null) {
			q.append("AND direccion_2 = ? ");
			parametros.add(obj.getDireccion2());
		}
		if (obj.getProvincia() != null) {
			q.append("AND provincia = ? ");
			parametros.add(obj.getProvincia());
		}
		if (obj.getPais() != null) {
			q.append("AND pais = ? ");
			parametros.add(obj.getPais());
		}
		if (obj.getDivisa() != null) {
			q.append("AND divisa = ? ");
			parametros.add(obj.getDivisa());
		}
		if (obj.getCodPostal() != null) {
			q.append("AND cod_postal = ? ");
			parametros.add(obj.getCodPostal());
		}
		if (obj.getAdmin() != null) {
			q.append("AND admin = ? ");
			parametros.add(obj.getAdmin());
		}
		if (obj.getPass() != null) {
			q.append("AND pass = ? ");
			parametros.add(obj.getPass());
		}
		if (obj.getLang() != null) {
			q.append("AND lang = ? ");
			parametros.add(obj.getLang());
		}
		if (obj.getIp() != null) {
			q.append("AND ip = ? ");
			parametros.add(obj.getIp());
		}
		if (obj.getFallosLogin() != null) {
			q.append("AND fallos_login = ? ");
			parametros.add(obj.getFallosLogin());
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
		return this.jdbcTemplate.query(q.toString(), p, new UsuarioRowMapper());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.spinnakersimple.modelo.dao.UsuarioDao#crea(com.spinnakersimple.modelo
	 * .entidad.tabla.UsuarioPOJO)
	 */
	@Override
	public UsuarioPOJO crea(final UsuarioPOJO obj) {
		try {
			final UsuarioPOJO u = obj;
			StringBuilder q = new StringBuilder("");
			Object[] p = new Object[] {};
			q.append("LOCK TABLES " + TABLA + " WRITE;");
			log.trace(Cadenas.generaQuery(q, p));
			this.jdbcTemplate.update(q.toString(), p);

			q = new StringBuilder("");
			p = new Object[] {};
			q.append("SELECT IFNULL( MAX( id_usuario ) + 1, 1 ) FROM " + TABLA + ";");
			final Integer newId = this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class);

			obj.setIdUsuario(newId);
			obj.setIdUsuarioAlta(newId);
			obj.setIdUsuarioModif(newId);
			q = new StringBuilder("");
			p = toParamsTodos(obj);
			q.append("INSERT INTO " + TABLA + " ( " + CAMPOS + " ) ");
			q.append("VALUES ( " + CAMPOSINSERT + " ) ");

			log.trace(Cadenas.generaQuery(q, p));
			this.jdbcTemplate.update(q.toString(), p);

			u.setIdUsuario(newId);
			return u;
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
	 * com.spinnakersimple.modelo.dao.UsuarioDao#existe(com.spinnakersimple.
	 * modelo.entidad.tabla.UsuarioPOJO)
	 */
	@Override
	public boolean existe(final UsuarioPOJO obj) {
		final StringBuilder q = new StringBuilder("");
		final Object[] p = toParamsClave(obj);
		q.append("SELECT COUNT(*) ");
		q.append("FROM " + TABLA + " ");
		q.append("WHERE  id_usuario = ?   ");

		log.trace(Cadenas.generaQuery(q, p));
		return this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class) > 0;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.spinnakersimple.modelo.dao.UsuarioDao#existePorMail(java.lang.String)
	 */
	@Override
	public boolean existePorMail(final String mail) {
		final StringBuilder q = new StringBuilder("");
		final Object[] p = { mail };
		q.append("SELECT COUNT(*) ");
		q.append("FROM " + TABLA + " ");
		q.append("WHERE mail = ?   ");

		log.trace(Cadenas.generaQuery(q, p));
		return this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class) > 0;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.spinnakersimple.modelo.dao.UsuarioDao#historifica(com.spinnakersimple
	 * .modelo.entidad.tabla.UsuarioPOJO,
	 * com.capitanperegrina.common.basebeans.CambioEnTabla)
	 */
	@Override
	public void historifica(final UsuarioPOJO obj, final CambioEnTabla ct) {
		final StringBuilder q = new StringBuilder();
		final Object[] p = toParamsClave(obj);
		q.append("INSERT INTO " + TABLA + "_his ");
		q.append("SELECT NOW(), " + ct.getUsu() + ", " + CAMPOS + " ");
		q.append("FROM " + TABLA + " ");
		q.append("WHERE 1=1 ");
		if (obj.getIdUsuario() != null) {
			q.append("AND id_usuario = ? ");
		}
		this.jdbcTemplate.update(q.toString(), p);
	}

//	@Override
//	public UsuarioCompletoView buscaUsuarioCompleto( UsuarioPOJO u ) {
//		StringBuilder q = new StringBuilder();
//		Object[] p = { u.getIdUsuario() };
//		List<String> listaTablasTemporales = new ArrayList<String>();
//		try {
//			q.append("CREATE TEMPORARY TABLE anunciosPorUsuario AS ( ");
//			q.append("SELECT u.id_usuario, count(a.id_anuncio) AS num_anuncios ");
//			q.append("FROM " + TABLA + " u, anuncio a ");
//			q.append("WHERE a.id_usuario_alta = u.id_usuario ");
//			q.append("AND u.id_usuario = ? ");
//			q.append("GROUP BY 1 ); ");
//			if ( log.isDebugEnabled() ) {
//				log.trace(Cadenas.generaQuery(q, p));
//			}
//			this.jdbcTemplate.update(q.toString(), p);
//			listaTablasTemporales.add("anunciosPorUsuario");
//
//			q = new StringBuilder();
//			q.append("SELECT " + Cadenas.prefijaCamposTabla( CAMPOS, "u" ) + ", num_anuncios ");
//			q.append("FROM usuario u, anunciosPorUsuario au ");
//			q.append("WHERE u.id_usuario = au.id_usuario ");
//			q.append("AND u.id_usuario = ? ");
//			if ( log.isDebugEnabled() ) {
//				log.trace(Cadenas.generaQuery(q, p));
//			}
//			return this.jdbcTemplate.queryForObject(q.toString(), p, new UsuarioCompletoViewRowmapper());
//		} finally  {
//			SQLUtils.dropTemporaryTables( this.jdbcTemplate, listaTablasTemporales );
//		}
//	}

	@Override
	public List<UsuarioCompletoView> buscaUsuariosCompleto() {
		StringBuilder q = new StringBuilder();
		final Object[] p = {};
		final List<String> listaTablasTemporales = new ArrayList<String>();
		try {
			q.append("CREATE TEMPORARY TABLE anunciosPorUsuario AS ( ");
			q.append("SELECT u.id_usuario, count(a.id_anuncio) AS num_anuncios ");
			q.append("FROM " + TABLA + " u, anuncio a ");
			q.append("WHERE a.id_usuario_alta = u.id_usuario ");
			q.append("GROUP BY 1 ); ");
			if ( log.isDebugEnabled() ) {
				log.trace(Cadenas.generaQuery(q, p));
			}
			this.jdbcTemplate.update(q.toString(), p);
			listaTablasTemporales.add("anunciosPorUsuario");

			q = new StringBuilder();
			q.append("SELECT " + Cadenas.prefijaCamposTabla( CAMPOS, "u" ) + ", num_anuncios ");
			q.append("FROM usuario u, anunciosPorUsuario au ");
			q.append("WHERE u.id_usuario = au.id_usuario; ");
			if ( log.isDebugEnabled() ) {
				log.trace(Cadenas.generaQuery(q, p));
			}
			return this.jdbcTemplate.query(q.toString(), new UsuarioCompletoViewRowmapper());
		} finally  {
			SQLUtils.dropTemporaryTables( this.jdbcTemplate, listaTablasTemporales );
		}
	}

	@Override
	public void historificaYEliminaPorUsuario( final Integer idUsuario ) {
		final Object[] p = { idUsuario };
		StringBuilder q = new StringBuilder();
		q.append("insert into usuario_his ( select NOW(), 1, u.* from usuario u where u.id_usuario = ? );" );
		log.trace(Cadenas.generaQuery(q, p));
		this.jdbcTemplate.update(q.toString(), p);;

		q = new StringBuilder();
		q.append("delete from usuario where id_usuario = ?;" );
		log.trace(Cadenas.generaQuery(q, p));
		this.jdbcTemplate.update(q.toString(), p);;
	}
}