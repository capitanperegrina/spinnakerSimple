package com.spinnakersimple.modelo.dao;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.spinnakersimple.modelo.entidad.SliderPOJO;

/**
 * The Interface SliderDao.
 */
public interface SliderDao {

	/**
	 * Actualiza un registro de la tabla <code>slider</code> de la base de
	 * datos.
	 *
	 * @param obj
	 *            Objeto SliderPOJO que se quiere actualizar en la base de
	 *            datos.
	 * @throws EmptyResultDataAccessException
	 *             Si no se encuentra el registro que se desea actualizar en la
	 *             tabla <code>slider</code>.
	 * @throws DuplicateKeyException
	 *             si se encuentra más de un registro a actualizar en la tabla
	 *             la tabla <code>slider</code>.
	 */
	public void actualiza(SliderPOJO obj);

	/**
	 * Elimina físicamente un registro de la tabla <code>slider</code> de la
	 * base de datos a partir de los valores de su clave principal.
	 *
	 * @param obj
	 *            Objeto SliderPOJO con los campos de la clave principal
	 *            informados.
	 * @throws EmptyResultDataAccessException
	 *             Si no se encuentra el registro que se desea eliminar en la
	 *             tabla <code>slider</code>.
	 */
	public void borra(SliderPOJO obj);

	/**
	 * Recupera de la base de datos un registro de la tabla <code>slider</code>
	 * y lo devuelve en el objeto SliderPOJO.
	 *
	 * @param obj
	 *            Objeto SliderPOJO con los campos de la clave principal
	 *            informados.
	 * @return El objeto SliderPOJO con la información recuperada de la base de
	 *         datos.
	 * @throws EmptyResultDataAccessException
	 *             si no se encuentra el resitro en la tabla <code>slider</code>
	 *             .
	 */
	public SliderPOJO busca(SliderPOJO obj);

	/**
	 * Busca para comra.
	 *
	 * @return the list
	 */
	public List<SliderPOJO> buscaParaCompra();

	/**
	 * Busca para venta.
	 *
	 * @return the list
	 */
	public List<SliderPOJO> buscaParaVenta();

	/**
	 * Devuelve una lista con todos los registros de la tabla
	 * <code>slider</code> de la base de datos.
	 *
	 * @return Una lista con todos los elementos de la tabla <code>slider</code>
	 *         .
	 */
	public List<SliderPOJO> buscaTodos();

	/**
	 * Devuelve una lista con todos los registros de la tabla
	 * <code>slider</code> de la base de datos a partir de los campos de de un
	 * POJO.
	 *
	 * @param obj
	 *            Objeto SliderPOJO con los campos por los que se desea buscar
	 *            informados.
	 * @return Una lista con todos los elementos de la tabla <code>slider</code>
	 *         que cumplan los criterios de búsqueda.
	 */
	public List<SliderPOJO> buscaVarios(SliderPOJO obj);

	/**
	 * Inserta un registro en la tabla <code>slider</code> de la base de datos.
	 *
	 * @param obj
	 *            Objeto POJO con la información a insertar en la tabla
	 *            <code>slider</code> de la base de datos.
	 * @throws DuplicateKeyException
	 *             si ya existe un registro en la tabla <code>slider</code> de
	 *             base de datos con igual clave principal.
	 */
	public void crea(SliderPOJO obj);

	/**
	 * Comprueba si existe un registro en la tabla <code>slider</code> de la
	 * base de datos a partir de su clave principal.
	 *
	 * @param obj
	 *            Objeto del que se desea comprobar su existencia en la tabla
	 *            <code>slider</code> de la base de datos.
	 * @return true si existe un registro en la tabla <code>slider</code> de la
	 *         base de datos con la misma clave principal, false si no es así.
	 */
	public boolean existe(SliderPOJO obj);
}
