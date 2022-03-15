package com.spinnakersimple.modelo.dao;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.spinnakersimple.modelo.entidad.CompradoresPOJO;

/**
 * The Interface CompradoresDao.
 */
public interface CompradoresDao {

	/**
	 * Actualiza un registro de la tabla <code>compradores</code> de la base de
	 * datos.
	 *
	 * @param obj
	 *            Objeto CompradoresPOJO que se quiere actualizar en la base de
	 *            datos.
	 * @throws EmptyResultDataAccessException
	 *             Si no se encuentra el registro que se desea actualizar en la
	 *             tabla <code>compradores</code>.
	 * @throws DuplicateKeyException
	 *             si se encuentra más de un registro a actualizar en la tabla
	 *             la tabla <code>compradores</code>.
	 */
	void actualiza(CompradoresPOJO obj);

	/**
	 * Elimina físicamente un registro de la tabla <code>compradores</code> de
	 * la base de datos a partir de los valores de su clave principal.
	 *
	 * @param obj
	 *            Objeto CompradoresPOJO con los campos de la clave principal
	 *            informados.
	 * @throws EmptyResultDataAccessException
	 *             Si no se encuentra el registro que se desea eliminar en la
	 *             tabla <code>compradores</code>.
	 */
	void borra(CompradoresPOJO obj);

	/**
	 * Recupera de la base de datos un registro de la tabla
	 * <code>compradores</code> y lo devuelve en el objeto CompradoresPOJO.
	 *
	 * @param obj
	 *            Objeto CompradoresPOJO con los campos de la clave principal
	 *            informados.
	 * @return El objeto CompradoresPOJO con la información recuperada de la
	 *         base de datos.
	 * @throws EmptyResultDataAccessException
	 *             si no se encuentra el resitro en la tabla
	 *             <code>compradores</code>.
	 */
	CompradoresPOJO busca(CompradoresPOJO obj);

	/**
	 * Devuelve una lista con todos los registros de la tabla
	 * <code>compradores</code> de la base de datos.
	 *
	 * @return Una lista con todos los elementos de la tabla
	 *         <code>compradores</code>.
	 */
	List<CompradoresPOJO> buscaTodos();

	/**
	 * Devuelve una lista con todos los registros de la tabla
	 * <code>compradores</code> de la base de datos a partir de los campos de de
	 * un POJO.
	 *
	 * @param obj
	 *            Objeto CompradoresPOJO con los campos por los que se desea
	 *            buscar informados.
	 * @return Una lista con todos los elementos de la tabla
	 *         <code>compradores</code> que cumplan los criterios de búsqueda.
	 */
	List<CompradoresPOJO> buscaVarios(CompradoresPOJO obj);

	/**
	 * Inserta un registro en la tabla <code>compradores</code> de la base de
	 * datos.
	 *
	 * @param obj
	 *            Objeto POJO con la información a insertar en la tabla
	 *            <code>compradores</code> de la base de datos.
	 * @throws DuplicateKeyException
	 *             si ya existe un registro en la tabla <code>compradores</code>
	 *             de base de datos con igual clave principal.
	 * @return El objeto POJO con la clave primaria generada.
	 */
	CompradoresPOJO crea(CompradoresPOJO obj);

	/**
	 * Comprueba si existe un registro en la tabla <code>compradores</code> de
	 * la base de datos a partir de su clave principal.
	 *
	 * @param obj
	 *            Objeto del que se desea comprobar su existencia en la tabla
	 *            <code>compradores</code> de la base de datos.
	 * @return true si existe un registro en la tabla <code>compradores</code>
	 *         de la base de datos con la misma clave principal, false si no es
	 *         así.
	 */
	boolean existe(CompradoresPOJO obj);

	/**
	 * Num mensajes pendientes.
	 *
	 * @return the integer
	 */
	public Integer numMensajesPendientes();

	public List<CompradoresPOJO> buscaMensajesDeAnunciosNoFinalizados(CompradoresPOJO obj);

	void historificaYEliminaPorUsuario( Integer idUsuario );
}
