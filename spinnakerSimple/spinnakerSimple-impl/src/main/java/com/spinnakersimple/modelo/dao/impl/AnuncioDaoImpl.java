package com.spinnakersimple.modelo.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.capitanperegrina.common.basebeans.CambioEnTabla;
import com.capitanperegrina.common.bean.Pair;
import com.capitanperegrina.common.config.ExpresionesRegularesGlobals;
import com.capitanperegrina.common.exceptions.ServicioException;
import com.capitanperegrina.common.utils.Cadenas;
import com.capitanperegrina.common.validators.Validadores;
import com.spinnakersimple.modelo.dao.AnuncioDao;
import com.spinnakersimple.modelo.dao.rowmapper.AnuncioRowMapper;
import com.spinnakersimple.modelo.dto.FiltroBusquedaVelaBean;
import com.spinnakersimple.modelo.entidad.AnuncioPOJO;
import com.spinnakersimple.modelo.util.SpinnakerSimpleGlobals;

/**
 * The Class AnuncioDaoImpl.
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public class AnuncioDaoImpl implements AnuncioDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    static Logger log = Logger.getLogger(AnuncioDao.class);
    public static final String ENTIDAD = AnuncioPOJO.class.getName();
    public static final String TABLA = "anuncio";
    public static final String CAMPOS = "id_anuncio, titulo_anuncio, tipo_barco, tipo_vela, gramaje, gratil, baluma, pujamen, caida_proa, caida_popa, superficie, tipo_cometa, descripcion, precio, pais, caduca, visible, listado, visto, comentado, destacado, id_usuario_alta, fec_alta, id_usuario_modif, fec_modif ";
    public static final String CAMPOSINSERT = " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
    public static final String CAMPOSUPDATE = " titulo_anuncio = ?, tipo_barco = ?, tipo_vela = ?, gramaje = ?, gratil = ?, baluma = ?, pujamen = ?, caida_proa = ?, caida_popa = ?, superficie = ?, tipo_cometa = ?, descripcion = ?, precio = ?, pais = ?, caduca = ?, visible = ?, listado = ?, visto = ?, comentado = ?, destacado = ?, id_usuario_alta = ?, fec_alta = ?, id_usuario_modif = ?, fec_modif = ? ";
    public static final String CAMPOSPKUPDATE = " id_anuncio = ? ";

    private static Object[] toParamsTodos(final AnuncioPOJO obj) {
	return ArrayUtils.addAll(toParamsClave(obj), toParamsResto(obj));
    }

    private static Object[] toParamsUpdate(final AnuncioPOJO obj) {
	return ArrayUtils.addAll(toParamsResto(obj), toParamsClave(obj));
    }

    private static Object[] toParamsClave(final AnuncioPOJO obj) {
	return new Object[] { obj.getIdAnuncio() };
    }

    private static Object[] toParamsResto(final AnuncioPOJO obj) {
	return new Object[] { obj.getTituloAnuncio(), obj.getTipoBarco(), obj.getTipoVela(), obj.getGramaje(),
		obj.getGratil(), obj.getBaluma(), obj.getPujamen(), obj.getCaidaProa(), obj.getCaidaPopa(),
		obj.getSuperficie(), obj.getTipoCometa(), obj.getDescripcion(), obj.getPrecio(), obj.getPais(),
		obj.getCaduca(), obj.getVisible(), obj.getListado(), obj.getVisto(), obj.getComentado(),
		obj.getComentado(), obj.getIdUsuarioAlta(), obj.getFecAlta(), obj.getIdUsuarioModif(),
		obj.getFecModif() };
    }

    /*
     * (non-Javadoc)
     *
     * @see com.spinnakersimple.modelo.dao.AnuncioDao#actualiza(com.spinnakersimple.
     * modelo.entidad.tabla.AnuncioPOJO)
     */
    @Override
    public void actualiza(final AnuncioPOJO obj) {
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
     * @see com.spinnakersimple.modelo.dao.AnuncioDao#borra(com.spinnakersimple.
     * modelo.entidad.tabla.AnuncioPOJO)
     */
    @Override
    public void borra(final AnuncioPOJO obj) {
	try {
	    final StringBuilder q = new StringBuilder("");
	    final Object[] p = toParamsClave(obj);
	    q.append("DELETE FROM " + TABLA + " ");
	    q.append("WHERE  id_anuncio = ?   ");

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
     * @see com.spinnakersimple.modelo.dao.AnuncioDao#busca(com.spinnakersimple.
     * modelo.entidad.tabla.AnuncioPOJO)
     */
    @Override
    public AnuncioPOJO busca(final AnuncioPOJO obj) throws ServicioException {
	try {
	    final StringBuilder q = new StringBuilder("");
	    final Object[] p = toParamsClave(obj);
	    q.append("SELECT " + CAMPOS + " ");
	    q.append("FROM " + TABLA + " ");
	    q.append("WHERE  id_anuncio = ?   ");

	    log.trace(Cadenas.generaQuery(q, p));
	    return this.jdbcTemplate.queryForObject(q.toString(), p, new AnuncioRowMapper());
	} catch (final EmptyResultDataAccessException e) {
	    log.debug("No se ha encontrado la información buscada.");
	    throw new ServicioException(e);
	}
    }

    /*
     * (non-Javadoc)
     *
     * @see com.spinnakersimple.modelo.dao.AnuncioDao#buscaMisAnuncios(java.lang.
     * Integer, java.lang.Integer, java.lang.String[], boolean)
     */
    @Override
    public List<AnuncioPOJO> buscaMisAnuncios(final Integer idUsuario, final Integer tipoVela,
	    final String[] tiposAnuncio, final boolean admin) {
	List<AnuncioPOJO> ret;

	final StringBuilder in = new StringBuilder();
	if (tiposAnuncio.length > 0) {
	    for (final String el : tiposAnuncio) {
		in.append("'" + el + "', ");
	    }
	}

	final StringBuilder q = new StringBuilder();
	final List<Object> p = new ArrayList<>();
	q.append("SELECT " + CAMPOS + " ");
	q.append("FROM " + TABLA + " ");
	if (admin) {
	    q.append("WHERE 1 = 1 ");
	} else {
	    q.append("WHERE id_usuario_alta = ? ");
	    p.add(idUsuario);
	}
	if (tiposAnuncio.length > 0) {
	    q.append("AND visible IN (" + in.deleteCharAt(in.length() - 2).toString() + ") ");
	}
	if (tipoVela > -1) {
	    q.append("AND tipo_vela = ? ");
	    p.add(tipoVela);
	}
	q.append("ORDER BY id_anuncio ");

	log.trace(Cadenas.generaQuery(q, p));
	ret = this.jdbcTemplate.query(q.toString(), p.toArray(), new AnuncioRowMapper());
	return ret;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.spinnakersimple.modelo.dao.AnuncioDao#buscaParaComprar(com.
     * spinnakersimple.modelo.dto.FiltroBusquedaVelaBean)
     */
    @Override
    public List<AnuncioPOJO> buscaParaComprar(final FiltroBusquedaVelaBean filtro) {
	List<AnuncioPOJO> ret = new ArrayList<>();
	if (filtro != null) {
	    final StringBuilder q = new StringBuilder();
	    final List<Object> p = new ArrayList<>();
	    q.append("SELECT " + CAMPOS + " ");
	    q.append("FROM " + TABLA + " ");
	    q.append("WHERE visible='1' ");
	    q.append("AND caduca > ? ");
	    p.add(Calendar.getInstance());
	    if (StringUtils.isNotEmpty(filtro.getPais())) {
		q.append("AND pais = ? ");
		p.add(filtro.getPais());
	    }
	    if (filtro.getTipoBarco() != null) {
		q.append("AND tipo_barco = ? ");
		p.add(filtro.getTipoBarco());
	    }
	    if (filtro.getTipoVela() != null) {
		q.append("AND tipo_vela = ? ");
		p.add(filtro.getTipoVela());
	    }
	    if (filtro.getGratilmax() != null && filtro.getGratilmin() != null) {
		q.append("AND ( ( gratil <= ? AND gratil >= ? ) OR gratil IS NULL )");
		p.add(filtro.getGratilmax());
		p.add(filtro.getGratilmin());
	    }
	    if (filtro.getBalumamax() != null && filtro.getBalumamin() != null) {
		q.append("AND ( ( baluma <= ? AND baluma >= ? ) OR baluma IS NULL ) ");
		p.add(filtro.getBalumamax());
		p.add(filtro.getBalumamin());
	    }
	    if (filtro.getPujamenmax() != null && filtro.getPujamenmin() != null) {
		q.append("AND ( ( pujamen <= ? AND pujamen >= ? ) OR pujamen IS NULL ) ");
		p.add(filtro.getPujamenmax());
		p.add(filtro.getPujamenmin());
	    }
	    if (filtro.getCaidaProamax() != null && filtro.getCaidaProamin() != null) {
		q.append("AND ( ( caida_proa <= ? AND caida_proa >= ? ) OR caida_proa IS NULL ) ");
		p.add(filtro.getCaidaProamax());
		p.add(filtro.getCaidaProamin());
	    }
	    if (filtro.getCaidaPopamax() != null && filtro.getCaidaPopamin() != null) {
		q.append("AND ( ( caida_popa <= ? AND caida_popa >= ? ) OR caida_popa IS NULL ) ");
		p.add(filtro.getCaidaPopamax());
		p.add(filtro.getCaidaPopamin());
	    }
	    if (filtro.getSuperficiemax() != null && filtro.getSuperficiemin() != null) {
		q.append("AND ( ( superficie <= ? AND superficie >= ? ) OR superficie IS NULL ) ");
		p.add(filtro.getSuperficiemax());
		p.add(filtro.getSuperficiemin());
	    }
	    if (filtro.getTipoCometa() != null && !filtro.getTipoCometa().equals("-1")) {
		q.append("AND tipo_cometa = ? ");
		p.add(filtro.getTipoCometa());
	    }
	    final StringBuilder clausulaLike = new StringBuilder();
	    if (!Validadores.estaVacia(filtro.getTitulo())) {
		for (final String campo : filtro.getTitulo().split(ExpresionesRegularesGlobals.ESPACIO_BLANCO)) {
		    clausulaLike.append("AND ( UPPER( titulo_anuncio ) LIKE ? ) ");
		    p.add("%" + campo.toUpperCase() + "%");
		}
		q.append(clausulaLike);
	    }
	    q.append("ORDER BY " + filtro.getOrdenarPor() + " " + filtro.getTipoOrdenacion());
	    if (filtro.getRegInicial() != null && filtro.getNumRegistros() != null) {
		q.append(" LIMIT ").append(filtro.getRegInicial().toString()).append(", ")
			.append(filtro.getNumRegistros().toString());
	    }

	    log.trace(Cadenas.generaQuery(q, p));
	    ret = this.jdbcTemplate.query(q.toString(), p.toArray(), new AnuncioRowMapper());
	}
	return ret;
    }

    @Override
    public List<AnuncioPOJO> buscaAnunciosPorUsuario(final Integer idUsuario, final String estadoAnuncio) {
	final StringBuilder q = new StringBuilder();
	final List<Object> p = new ArrayList<>();
	q.append("SELECT " + CAMPOS + " ");
	q.append("FROM " + TABLA + " ");
	q.append("WHERE id_usuario_alta= ? ");
	p.add(idUsuario);
	if (!StringUtils.isEmpty(estadoAnuncio)) {
	    q.append("AND visible = ? ");
	    p.add(estadoAnuncio);
	}
	q.append("ORDER BY visible ");

	if (log.isDebugEnabled()) {
	    log.trace(Cadenas.generaQuery(q, p));
	}
	return this.jdbcTemplate.query(q.toString(), p.toArray(), new AnuncioRowMapper());
    }

    /*
     * (non-Javadoc)
     *
     * @see com.spinnakersimple.modelo.dao.AnuncioDao#buscaTodos()
     */
    @Override
    public List<AnuncioPOJO> buscaTodos() {
	final StringBuilder q = new StringBuilder("");
	final Object[] p = {};
	q.append("SELECT " + CAMPOS + " ");
	q.append("FROM " + TABLA + " ");

	log.trace(Cadenas.generaQuery(q, p));
	return this.jdbcTemplate.query(q.toString(), new AnuncioRowMapper());
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.spinnakersimple.modelo.dao.AnuncioDao#buscaVarios(com.spinnakersimple
     * .modelo.entidad.tabla.AnuncioPOJO)
     */
    @Override
    public List<AnuncioPOJO> buscaVarios(final AnuncioPOJO obj) {
	if (obj == null) {
	    return buscaTodos();
	}

	final StringBuilder q = new StringBuilder("");
	q.append(" SELECT " + CAMPOS + " \n");
	q.append("   FROM " + TABLA + " \n");
	q.append("  WHERE 1 = 1 \n");

	final List<Object> parametros = new ArrayList<Object>();

	if (obj.getIdAnuncio() != null) {
	    q.append("   AND id_anuncio = ? \n");
	    parametros.add(obj.getIdAnuncio());
	}
	if (obj.getTituloAnuncio() != null) {
	    q.append("   AND titulo_anuncio = ? \n");
	    parametros.add(obj.getTituloAnuncio());
	}
	if (obj.getTipoBarco() != null) {
	    q.append("   AND tipo_barco = ? \n");
	    parametros.add(obj.getTipoBarco());
	}
	if (obj.getTipoVela() != null) {
	    q.append("   AND tipo_vela = ? \n");
	    parametros.add(obj.getTipoVela());
	}
	if (obj.getGramaje() != null) {
	    q.append("   AND gramaje = ? \n");
	    parametros.add(obj.getGramaje());
	}
	if (obj.getGratil() != null) {
	    q.append("   AND gratil = ? \n");
	    parametros.add(obj.getGratil());
	}
	if (obj.getBaluma() != null) {
	    q.append("   AND baluma = ? \n");
	    parametros.add(obj.getBaluma());
	}
	if (obj.getPujamen() != null) {
	    q.append("   AND pujamen = ? \n");
	    parametros.add(obj.getPujamen());
	}
	if (obj.getCaidaProa() != null) {
	    q.append("   AND caida_proa = ? \n");
	    parametros.add(obj.getCaidaProa());
	}
	if (obj.getCaidaPopa() != null) {
	    q.append("   AND caida_popa = ? \n");
	    parametros.add(obj.getCaidaPopa());
	}
	if (obj.getSuperficie() != null) {
	    q.append("   AND superficie = ? \n");
	    parametros.add(obj.getSuperficie());
	}
	if (obj.getTipoCometa() != null) {
	    q.append("   AND tipo_cometa = ? \n");
	    parametros.add(obj.getTipoCometa());
	}
	if (obj.getDescripcion() != null) {
	    q.append("   AND descripcion = ? \n");
	    parametros.add(obj.getDescripcion());
	}
	if (obj.getPrecio() != null) {
	    q.append("   AND precio = ? \n");
	    parametros.add(obj.getPrecio());
	}
	if (obj.getPais() != null) {
	    q.append("   AND pais = ? \n");
	    parametros.add(obj.getPais());
	}
	if (obj.getCaduca() != null) {
	    q.append("   AND caduca = ? \n");
	    parametros.add(obj.getCaduca());
	}
	if (obj.getVisible() != null) {
	    q.append("   AND visible = ? \n");
	    parametros.add(obj.getVisible());
	}
	if (obj.getListado() != null) {
	    q.append("   AND listado = ? \n");
	    parametros.add(obj.getListado());
	}
	if (obj.getVisto() != null) {
	    q.append("   AND visto = ? \n");
	    parametros.add(obj.getVisto());
	}
	if (obj.getComentado() != null) {
	    q.append("   AND comentado = ? \n");
	    parametros.add(obj.getComentado());
	}
	if (obj.getDestacado() != null) {
	    q.append("   AND destacado = ? \n");
	    parametros.add(obj.getDestacado());
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
	return this.jdbcTemplate.query(q.toString(), p, new AnuncioRowMapper());
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.spinnakersimple.modelo.dao.AnuncioDao#crea(com.spinnakersimple.modelo
     * .entidad.tabla.AnuncioPOJO)
     */
    @Override
    public AnuncioPOJO crea(final AnuncioPOJO obj) {
	try {
	    final AnuncioPOJO a = obj;
	    StringBuilder q = new StringBuilder("");
	    Object[] p = new Object[] {};
	    q.append("LOCK TABLES " + TABLA + " WRITE;");
	    log.trace(Cadenas.generaQuery(q, p));
	    this.jdbcTemplate.update(q.toString(), p);

	    q = new StringBuilder("");
	    p = new Object[] {};
	    q.append("SELECT IFNULL( MAX( id_anuncio ) + 1, 1 ) FROM " + TABLA + ";");
	    final Integer newId = this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class);

	    q = new StringBuilder("");
	    p = ArrayUtils.addAll(new Object[] { newId }, toParamsResto(obj));
	    q.append("INSERT INTO " + TABLA + " ( " + CAMPOS + " ) ");
	    q.append("VALUES ( " + CAMPOSINSERT + " ) ");

	    log.trace(Cadenas.generaQuery(q, p));
	    this.jdbcTemplate.update(q.toString(), p);

	    a.setIdAnuncio(newId);
	    return a;
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
     * @see com.spinnakersimple.modelo.dao.AnuncioDao#existe(com.spinnakersimple.
     * modelo.entidad.tabla.AnuncioPOJO)
     */
    @Override
    public boolean existe(final AnuncioPOJO obj) {
	final StringBuilder q = new StringBuilder("");
	final Object[] p = toParamsClave(obj);
	q.append("SELECT COUNT(*) ");
	q.append("FROM " + TABLA + " ");
	q.append("WHERE  id_anuncio = ?   ");

	log.trace(Cadenas.generaQuery(q, p));
	return this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class) > 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.spinnakersimple.modelo.dao.AnuncioDao#historifica(com.spinnakersimple
     * .modelo.entidad.tabla.AnuncioPOJO,
     * com.capitanperegrina.common.basebeans.CambioEnTabla)
     */
    @Override
    public void historifica(final AnuncioPOJO obj, final CambioEnTabla ct) {
	final StringBuilder q = new StringBuilder();
	final Object[] p = toParamsClave(obj);
	q.append("INSERT INTO " + TABLA + "_his ");
	q.append("SELECT NOW(), " + ct.getUsu() + ", " + CAMPOS + " ");
	q.append("FROM " + TABLA + " ");
	q.append("WHERE 1=1 ");
	if (obj.getIdAnuncio() != null) {
	    q.append("AND id_anuncio = ? ");
	}
	this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
    public Integer numAnunciosPendientes() {
	final StringBuilder q = new StringBuilder("");
	final Object[] p = new Object[] { SpinnakerSimpleGlobals.ANUNCIO_NO_VISIBLE };
	q.append("SELECT COUNT(*) ");
	q.append("FROM " + TABLA + " ");
	q.append("WHERE visible = ? AND caduca > NOW() ");

	log.trace(Cadenas.generaQuery(q, p));
	return this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class);
    }

    @Override
    public Integer numAnunciosVigentes() {
	final StringBuilder q = new StringBuilder("");
	final Object[] p = new Object[] { SpinnakerSimpleGlobals.ANUNCIO_VISIBLE };
	q.append("SELECT COUNT(*) ");
	q.append("FROM " + TABLA + " ");
	q.append("WHERE visible = ? AND caduca > NOW() ");

	log.trace(Cadenas.generaQuery(q, p));
	return this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class);
    }

    @Override
    public Integer numAnunciosProximosACaducar() {
	final StringBuilder q = new StringBuilder("");
	final Object[] p = new Object[] {};
	q.append("SELECT COUNT(*) ");
	q.append("FROM " + TABLA + " ");
	q.append("WHERE caduca > NOW() and caduca < DATE_ADD(NOW(), INTERVAL 10 DAY) ");

	log.trace(Cadenas.generaQuery(q, p));
	return this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class);
    }

    @Override
    public List<AnuncioPOJO> buscaAnunciosPendientes() {
	final StringBuilder q = new StringBuilder("");
	final Object[] p = new Object[] { SpinnakerSimpleGlobals.ANUNCIO_NO_VISIBLE };
	q.append("SELECT " + CAMPOS + " ");
	q.append("FROM " + TABLA + " ");
	q.append("WHERE visible = ? AND caduca > NOW() ");

	log.trace(Cadenas.generaQuery(q, p));
	return this.jdbcTemplate.query(q.toString(), p, new AnuncioRowMapper());
    }

    @Override
    public List<AnuncioPOJO> buscaAnunciosPublicados() {
	final StringBuilder q = new StringBuilder("");
	final Object[] p = new Object[] { SpinnakerSimpleGlobals.ANUNCIO_VISIBLE };
	q.append("SELECT " + CAMPOS + " ");
	q.append("FROM " + TABLA + " ");
	q.append("WHERE visible = ? AND caduca > NOW() ");

	log.trace(Cadenas.generaQuery(q, p));
	return this.jdbcTemplate.query(q.toString(), p, new AnuncioRowMapper());
    }

    @Override
    public List<AnuncioPOJO> buscaAnunciosQueCaducanEnNDias(final int numeroDias) {
	final StringBuilder q = new StringBuilder("");
	final Object[] p = new Object[] { SpinnakerSimpleGlobals.ANUNCIO_VISIBLE };
	q.append("SELECT " + CAMPOS + " ");
	q.append("FROM " + TABLA + " ");
	q.append("WHERE DATE( caduca ) = DATE_ADD(  DATE( NOW() ), INTERVAL " + numeroDias + " DAY ) ");
	q.append("AND visible = ? ");

	log.trace(Cadenas.generaQuery(q, p));
	return this.jdbcTemplate.query(q.toString(), p, new AnuncioRowMapper());
    }

    @Override
    public void actualizaEstadisticasListado(final Integer idUsuario, final List<Integer> idsAnuncioConsultados) {
	if (idsAnuncioConsultados.size() > 0) {
	    try {
		final StringBuilder q = new StringBuilder("");
		final List<Object> p = new ArrayList<>();
		p.addAll(idsAnuncioConsultados);
		final StringBuilder interrogantes = new StringBuilder();
		for (int i = 0; i < p.size(); i++) {
		    interrogantes.append("?, ");
		}
		q.append("UPDATE " + TABLA + " ");
		q.append("SET listado = listado + 1 ");
		q.append("WHERE id_anuncio IN ( " + interrogantes.substring(0, interrogantes.lastIndexOf(",")) + " ) ");
		if (idUsuario != null) {
		    q.append("AND id_usuario_alta != ? ");
		    p.add(idUsuario);
		}
		log.trace(Cadenas.generaQuery(q, p));
		this.jdbcTemplate.update(q.toString(), p.toArray());
	    } catch (final EmptyResultDataAccessException e) {
		log.debug("El registro que se desea actualizar no existe.", e);
		throw e;
	    } catch (final DuplicateKeyException e) {
		log.debug("Clave duplicada al intentar actualizar la información.", e);
		throw e;
	    }
	}
    }

    @Override
    public void actualizaEstadisticasVisto(final Integer idUsuario, final Integer idAnuncio) {
	try {
	    final StringBuilder q = new StringBuilder("");
	    final List<Object> p = new ArrayList<>();
	    q.append("UPDATE " + TABLA + " ");
	    q.append("SET visto = visto + 1 ");
	    q.append("WHERE id_anuncio = ? ");
	    p.add(idAnuncio);
	    if (idUsuario != null) {
		q.append("AND id_usuario_alta != ? ");
		p.add(idUsuario);
	    }
	    log.trace(Cadenas.generaQuery(q, p));
	    this.jdbcTemplate.update(q.toString(), p.toArray());
	} catch (final EmptyResultDataAccessException e) {
	    log.debug("El registro que se desea actualizar no existe.", e);
	    throw e;
	} catch (final DuplicateKeyException e) {
	    log.debug("Clave duplicada al intentar actualizar la información.", e);
	    throw e;
	}
    }

    @Override
    public void actualizaEstadisticasComentado(final Integer idUsuario, final Integer idAnuncio) {
	try {
	    final StringBuilder q = new StringBuilder("");
	    final List<Object> p = new ArrayList<>();
	    q.append("UPDATE " + TABLA + " ");
	    q.append("SET comentado = comentado + 1 ");
	    q.append("WHERE id_anuncio = ? ");
	    p.add(idAnuncio);
	    if (idUsuario != null) {
		q.append("AND id_usuario_alta != ? ");
		p.add(idUsuario);
	    }
	    log.trace(Cadenas.generaQuery(q, p));
	    this.jdbcTemplate.update(q.toString(), p.toArray());
	} catch (final EmptyResultDataAccessException e) {
	    log.debug("El registro que se desea actualizar no existe.", e);
	    throw e;
	} catch (final DuplicateKeyException e) {
	    log.debug("Clave duplicada al intentar actualizar la información.", e);
	    throw e;
	}
    }

    @Override
    public boolean visblePorPrimeraVez(final Integer id_anuncio) {
	final StringBuilder q = new StringBuilder("");
	final Object[] p = { id_anuncio, id_anuncio };
	q.append(
		"SELECT count(*) FROM anuncio WHERE id_anuncio = ? AND visible = 1 AND ( SELECT count(*) FROM anuncio_his WHERE id_anuncio = ? and visible = 1 ) = 0");
	log.trace(Cadenas.generaQuery(q, p));
	return this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class) == 1;
    }

    @Override
    public void historificaYEliminaPorUsuario(final Integer idUsuario) {
	final Object[] p = { idUsuario };
	StringBuilder q = new StringBuilder();
	q.append(
		"insert into anuncio_his ( select NOW(), 1, a.id_anuncio, a.titulo_anuncio, a.tipo_vela, a.gratil, a.baluma, a.pujamen, a.descripcion, a.precio, a.caduca, a.listado, a.visto, a.comentado, a.visible, a.id_usuario_alta, a.fec_alta, a.id_usuario_modif, a.fec_modif from anuncio a where a.id_usuario_alta = ? );");
	log.trace(Cadenas.generaQuery(q, p));
	this.jdbcTemplate.update(q.toString(), p);
	;

	q = new StringBuilder();
	q.append("delete from anuncio where id_usuario_alta = ?;");
	log.trace(Cadenas.generaQuery(q, p));
	this.jdbcTemplate.update(q.toString(), p);
	;
    }

    @Override
    public List<Pair<String, Integer>> numeroAnunciosPorTipoBarco() {
	final StringBuilder q = new StringBuilder("");
	final Object[] p = {};
	q.append(
		"SELECT tb.tipo_barco, COUNT(*) FROM tipo_barco tb, anuncio a WHERE tb.id_tipo_barco = a.tipo_barco AND a.visible = '1' GROUP BY tb.tipo_barco ");
	log.trace(Cadenas.generaQuery(q, p));
	return this.jdbcTemplate.query(q.toString(), new RowMapper<Pair<String, Integer>>() {
	    @Override
	    public Pair<String, Integer> mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		final Pair<String, Integer> obj = new Pair<String, Integer>(StringUtils.trimToEmpty(rs.getString(1)),
			Integer.valueOf(rs.getInt(2)));
		return obj;
	    }
	});
    }

    @Override
    public List<Pair<String, Integer>> numeroAnunciosPorTipoClase() {
	final StringBuilder q = new StringBuilder("");
	final Object[] p = {};
	q.append(
		"SELECT tb.tipo_clase, COUNT(*) FROM tipo_barco tb, anuncio a WHERE tb.id_tipo_barco = a.tipo_barco AND a.visible = '1' GROUP BY tb.tipo_barco");
	log.trace(Cadenas.generaQuery(q, p));
	return this.jdbcTemplate.query(q.toString(), new RowMapper<Pair<String, Integer>>() {
	    @Override
	    public Pair<String, Integer> mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		final Pair<String, Integer> obj = new Pair<String, Integer>(StringUtils.trimToEmpty(rs.getString(1)),
			Integer.valueOf(rs.getInt(2)));
		return obj;
	    }
	});
    }

    @Override
    public List<AnuncioPOJO> buscaAnunciosDestacados() {
	final StringBuilder q = new StringBuilder("");
	final Object[] p = new Object[] { SpinnakerSimpleGlobals.ANUNCIO_VISIBLE };
	q.append("SELECT " + Cadenas.prefijaCamposTabla(CAMPOS, "a") + " ");
	q.append("FROM anuncio a ");
//        q.append("WHERE LENGTH(a.descripcion) > 0 ");
//        q.append("AND a.visible = ? ");
	q.append("WHERE a.visible = ? ");
	q.append("AND caduca > NOW() ");
	q.append("AND ( SELECT COUNT(*) ");
	q.append("      FROM fotografia f ");
	q.append("      WHERE f.id_anuncio = a.id_anuncio ) > 0 ");
//        q.append("AND ( ");
//        q.append("    IFNULL(gratil,0) != 0 OR IFNULL(baluma,0) != 0 OR ");
//        q.append("    IFNULL(pujamen,0) != 0 OR IFNULL(caida_popa,0) != 0 OR ");
//        q.append("    IFNULL(caida_proa,0) != 0 OR IFNULL(superficie,0) != 0 OR ");
//        q.append("    IFNULL(tipo_cometa,0) != 0 ");
//        q.append(") ");
	q.append("ORDER BY destacado ASC, LENGTH(a.descripcion) DESC ");
	q.append("LIMIT 3 ");
	log.trace(Cadenas.generaQuery(q, p));
	return this.jdbcTemplate.query(q.toString(), p, new AnuncioRowMapper());
    }

    @Override
    public int actualizaAnunciosDestacados(final List<String> ids) {
	final StringBuilder q = new StringBuilder("");
	final Object[] p = new Object[] {};
	q.append("UPDATE anuncio ");
	q.append("SET destacado = destacado + 1 ");
	q.append("WHERE id_anuncio IN ( " + Cadenas.generaClausulaIn(ids, "\"") + " ) ");
	log.trace(Cadenas.generaQuery(q, p));
	return this.jdbcTemplate.update(q.toString(), p);
    }
}