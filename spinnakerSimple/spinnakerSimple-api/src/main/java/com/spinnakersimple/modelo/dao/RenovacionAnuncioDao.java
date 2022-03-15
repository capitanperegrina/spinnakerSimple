package com.spinnakersimple.modelo.dao;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.capitanperegrina.common.basebeans.CambioEnTabla;
import com.spinnakersimple.modelo.entidad.RenovacionAnuncioPOJO;

public interface RenovacionAnuncioDao {

    /**
     * Inserta un registro en la tabla <code>renovacion_anuncio</code> de la base de
     * datos.
     *
     * @param obj Objeto POJO con la informaci&oacute;n a insertar en la tabla
     *            <code>renovacion_anuncio</code> de la base de datos.
     * @throws DuplicateKeyException si ya existe un registro en la tabla
     *                               <code>renovacion_anuncio</code> de base de
     *                               datos con igual clave principal.
     */
    RenovacionAnuncioPOJO crea(RenovacionAnuncioPOJO obj);

    /**
     * Comprueba si existe un registro en la tabla <code>renovacion_anuncio</code>
     * de la base de datos a partir de su clave principal.
     *
     * @param obj Objeto del que se desea comprobar su existencia en la tabla
     *            <code>renovacion_anuncio</code> de la base de datos.
     * @return true si existe un registro en la tabla
     *         <code>renovacion_anuncio</code> de la base de datos con la misma
     *         clave principal, false si no es así.
     */
    boolean existe(RenovacionAnuncioPOJO obj);

    /**
     * Recupera de la base de datos un registro de la tabla
     * <code>renovacion_anuncio</code> y lo devuelve en el objeto
     * RenovacionAnuncioPOJO.
     *
     * @param obj Objeto RenovacionAnuncioPOJO con los campos de la clave principal
     *            informados.
     * @return El objeto RenovacionAnuncioPOJO con la informaci&oacute;n recuperada
     *         de la base de datos.
     * @throws EmptyResultDataAccessException si no se encuentra el resitro en la
     *                                        tabla <code>renovacion_anuncio</code>.
     */
    RenovacionAnuncioPOJO busca(RenovacionAnuncioPOJO obj);

    /**
     * Actualiza un registro de la tabla <code>renovacion_anuncio</code> de la base
     * de datos.
     *
     * @param obj Objeto RenovacionAnuncioPOJO que se quiere actualizar en la base
     *            de datos.
     * @throws EmptyResultDataAccessException Si no se encuentra el registro que se
     *                                        desea actualizar en la tabla
     *                                        <code>renovacion_anuncio</code>.
     * @throws DuplicateKeyException          si se encuentra m&aacute;s de un
     *                                        registro a actualizar en la tabla la
     *                                        tabla <code>renovacion_anuncio</code>.
     */
    void actualiza(RenovacionAnuncioPOJO obj);

    /**
     * Elimina f&iacute;sicamente un registro de la tabla
     * <code>renovacion_anuncio</code> de la base de datos a partir de los valores
     * de su clave principal.
     *
     * @param obj Objeto RenovacionAnuncioPOJO con los campos de la clave principal
     *            informados.
     * @throws EmptyResultDataAccessException Si no se encuentra el registro que se
     *                                        desea eliminar en la tabla
     *                                        <code>renovacion_anuncio</code>.
     */
    void borra(RenovacionAnuncioPOJO obj);

    /**
     * Método devuelve una lista con todos los registros de la tabla
     * <code>renovacion_anuncio</code> de la base de datos.
     *
     * @return Una lista con todos los elementos de la tabla
     *         <code>renovacion_anuncio</code>.
     */
    List<RenovacionAnuncioPOJO> buscaTodos();

    /**
     * Devuelve una lista con todos los registros de la tabla
     * <code>renovacion_anuncio</code> de la base de datos a partir de los campos de
     * de un POJO.
     *
     * @param obj Objeto RenovacionAnuncioPOJO con los campos por los que se desea
     *            buscar informados.
     * @return Una lista con todos los elementos de la tabla
     *         <code>renovacion_anuncio</code> que cumplan los criterios de
     *         b�squeda.
     */
    List<RenovacionAnuncioPOJO> buscaVarios(RenovacionAnuncioPOJO obj);

    /**
     * Pasa a la tabla de historico un registro.
     */
    void historifica(RenovacionAnuncioPOJO obj, CambioEnTabla ct);

}