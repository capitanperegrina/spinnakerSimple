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

import com.capitanperegrina.common.basebeans.CambioEnTabla;
import com.capitanperegrina.common.utils.Cadenas;
import com.spinnakersimple.modelo.dao.RenovacionAnuncioDao;
import com.spinnakersimple.modelo.dao.rowmapper.RenovacionAnuncioRowMapper;
import com.spinnakersimple.modelo.entidad.RenovacionAnuncioPOJO;

/**
 * Objeto de acceso a datos para la tabla <code>renovacion_anuncio<code>
 */
@Repository
public class RenovacionAnuncioDaoImpl implements RenovacionAnuncioDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    static Logger log = Logger.getLogger(RenovacionAnuncioDaoImpl.class);
    public static final String ENTIDAD = RenovacionAnuncioPOJO.class.getName();
    public static final String TABLA = "renovacion_anuncio";
    public static final String CAMPOS = "id_renocacion_anuncio, id_anuncio, id_usuario_alta, fec_alta, id_usuario_modif, fec_modif ";
    public static final String CAMPOSINSERT = " ?, ?, ?, ?, ?, ? ";
    public static final String CAMPOSUPDATE = " id_anuncio = ?, id_usuario_alta = ?, fec_alta = ?, id_usuario_modif = ?, fec_modif = ? ";
    public static final String CAMPOSPKUPDATE = " id_renocacion_anuncio = ? ";

    private static Object[] toParamsTodos(final RenovacionAnuncioPOJO obj) {
        return ArrayUtils.addAll(toParamsClave(obj), toParamsResto(obj));
    }

    private static Object[] toParamsUpdate(final RenovacionAnuncioPOJO obj) {
        return ArrayUtils.addAll(toParamsResto(obj), toParamsClave(obj));
    }

    private static Object[] toParamsClave(final RenovacionAnuncioPOJO obj) {
        return new Object[] { obj.getIdRenocacionAnuncio() };
    }

    private static Object[] toParamsResto(final RenovacionAnuncioPOJO obj) {
        return new Object[] { obj.getIdAnuncio(), obj.getIdUsuarioAlta(), obj.getFecAlta(), obj.getIdUsuarioModif(),
                obj.getFecModif() };
    }

    /*
     * (non-Javadoc)
     *
     * @see com.spinnakersimple.modelo.dao.impl.RenovacionAnuncioDao#crea(com.
     * spinnakersimple.modelo.entidad.RenovacionAnuncioPOJO)
     */
    @Override
    public RenovacionAnuncioPOJO crea(final RenovacionAnuncioPOJO obj) {
        try {
            final RenovacionAnuncioPOJO o = obj;
            StringBuilder q = new StringBuilder("");
            Object[] p = new Object[] {};
            q.append("LOCK TABLES " + TABLA + " WRITE;");
            log.trace(Cadenas.generaQuery(q, p));
            this.jdbcTemplate.update(q.toString(), p);

            q = new StringBuilder("");
            p = new Object[] {};
            q.append("SELECT IFNULL( MAX( id_renocacion_anuncio ) + 1, 1 ) FROM " + TABLA + ";");
            final Integer newId = this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class);

            q = new StringBuilder("");
            p = ArrayUtils.addAll(new Object[] { newId }, toParamsResto(obj));
            q.append("INSERT INTO " + TABLA + " ( " + CAMPOS + " ) ");
            q.append("VALUES ( " + CAMPOSINSERT + " ) ");

            log.trace(Cadenas.generaQuery(q, p));
            this.jdbcTemplate.update(q.toString(), p);

            o.setIdRenocacionAnuncio(newId);
            return o;
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
     * @see com.spinnakersimple.modelo.dao.impl.RenovacionAnuncioDao#existe(com.
     * spinnakersimple.modelo.entidad.RenovacionAnuncioPOJO)
     */
    @Override
    public boolean existe(final RenovacionAnuncioPOJO obj) {
        final StringBuilder q = new StringBuilder("");
        final Object[] p = toParamsClave(obj);
        q.append(" SELECT COUNT(*) \n");
        q.append("   FROM " + TABLA + " \n");
        q.append("  WHERE  id_renocacion_anuncio = ?   \n");

        log.trace(Cadenas.generaQuery(q, p));
        return (this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class)) > 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.spinnakersimple.modelo.dao.impl.RenovacionAnuncioDao#busca(com.
     * spinnakersimple.modelo.entidad.RenovacionAnuncioPOJO)
     */
    @Override
    public RenovacionAnuncioPOJO busca(final RenovacionAnuncioPOJO obj) {
        try {
            final StringBuilder q = new StringBuilder("");
            final Object[] p = toParamsClave(obj);
            q.append(" SELECT " + CAMPOS + " \n");
            q.append("   FROM " + TABLA + " \n");
            q.append("  WHERE  id_renocacion_anuncio = ?   \n");

            log.trace(Cadenas.generaQuery(q, p));
            return this.jdbcTemplate.queryForObject(q.toString(), p, new RenovacionAnuncioRowMapper());
        } catch (final EmptyResultDataAccessException e) {
            log.debug("No se ha encontrado la informaci�n buscada.", e);
            throw e;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.spinnakersimple.modelo.dao.impl.RenovacionAnuncioDao#actualiza(com.
     * spinnakersimple.modelo.entidad.RenovacionAnuncioPOJO)
     */
    @Override
    public void actualiza(final RenovacionAnuncioPOJO obj) {
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

    /*
     * (non-Javadoc)
     *
     * @see com.spinnakersimple.modelo.dao.impl.RenovacionAnuncioDao#borra(com.
     * spinnakersimple.modelo.entidad.RenovacionAnuncioPOJO)
     */
    @Override
    public void borra(final RenovacionAnuncioPOJO obj) {
        try {
            final StringBuilder q = new StringBuilder("");
            final Object[] p = toParamsClave(obj);
            q.append(" DELETE FROM " + TABLA + " \n");
            q.append("  WHERE  id_renocacion_anuncio = ?   \n");

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
     * @see com.spinnakersimple.modelo.dao.impl.RenovacionAnuncioDao#buscaTodos()
     */
    @Override
    public List<RenovacionAnuncioPOJO> buscaTodos() {
        final StringBuilder q = new StringBuilder("");
        final Object[] p = {};
        q.append(" SELECT " + CAMPOS + " \n");
        q.append("   FROM " + TABLA + " \n");

        log.trace(Cadenas.generaQuery(q, p));
        return this.jdbcTemplate.query(q.toString(), new RenovacionAnuncioRowMapper());
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.spinnakersimple.modelo.dao.impl.RenovacionAnuncioDao#buscaVarios(com.
     * spinnakersimple.modelo.entidad.RenovacionAnuncioPOJO)
     */
    @Override
    public List<RenovacionAnuncioPOJO> buscaVarios(final RenovacionAnuncioPOJO obj) {
        if (obj == null) {
            return buscaTodos();
        }

        final StringBuilder q = new StringBuilder("");
        q.append(" SELECT " + CAMPOS + " \n");
        q.append("   FROM " + TABLA + " \n");
        q.append("  WHERE 1 = 1 \n");

        final List<Object> parametros = new ArrayList<Object>();

        if (obj.getIdRenocacionAnuncio() != null) {
            q.append("   AND id_renocacion_anuncio = ? \n");
            parametros.add(obj.getIdRenocacionAnuncio());
        }
        if (obj.getIdAnuncio() != null) {
            q.append("   AND id_anuncio = ? \n");
            parametros.add(obj.getIdAnuncio());
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
        return this.jdbcTemplate.query(q.toString(), p, new RenovacionAnuncioRowMapper());
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.spinnakersimple.modelo.dao.impl.RenovacionAnuncioDao#historifica(com.
     * spinnakersimple.modelo.entidad.RenovacionAnuncioPOJO,
     * com.capitanperegrina.common.basebeans.CambioEnTabla)
     */
    @Override
    public void historifica(final RenovacionAnuncioPOJO obj, final CambioEnTabla ct) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = toParamsClave(obj);
        q.append("INSERT INTO " + TABLA + "_his ");
        q.append("SELECT CURRENT, " + ct.getUsu() + ", " + CAMPOS + " \n");
        q.append("  FROM " + TABLA + " \n");
        q.append(" WHERE 1=1 \n");
        if (obj.getIdRenocacionAnuncio() != null) {
            q.append("   AND id_renocacion_anuncio = ? \n");
        }
        this.jdbcTemplate.update(q.toString(), p);
    }
}