package com.spinnakersimple.modelo.dao;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.spinnakersimple.modelo.entidad.TipoVelaPOJO;

/**
 * The Interface TipoVelaDao.
 */
public interface TipoVelaDao {

	/**
	 * Actualiza un registro de la tabla <code>tipo_vela</code> de la base de
	 * datos.
	 *
	 * @param obj
	 *            Objeto TipoVelaPOJO que se quiere actualizar en la base de
	 *            datos.
	 * @throws EmptyResultDataAccessException
	 *             Si no se encuentra el registro que se desea actualizar en la
	 *             tabla <code>tipo_vela</code>.
	 * @throws DuplicateKeyException
	 *             si se encuentra más de un registro a actualizar en la tabla
	 *             la tabla <code>tipo_vela</code>.
	 */
	public void actualiza(TipoVelaPOJO obj);

	/**
	 * Elimina físicamente un registro de la tabla <code>tipo_vela</code> de la
	 * base de datos a partir de los valores de su clave principal.
	 *
	 * @param obj
	 *            Objeto TipoVelaPOJO con los campos de la clave principal
	 *            informados.
	 * @throws EmptyResultDataAccessException
	 *             Si no se encuentra el registro que se desea eliminar en la
	 *             tabla <code>tipo_vela</code>.
	 */
	public void borra(TipoVelaPOJO obj);

	/**
	 * Recupera de la base de datos un registro de la tabla
	 * <code>tipo_vela</code> y lo devuelve en el objeto TipoVelaPOJO.
	 *
	 * @param obj
	 *            Objeto TipoVelaPOJO con los campos de la clave principal
	 *            informados.
	 * @return El objeto TipoVelaPOJO con la información recuperada de la base
	 *         de datos.
	 * @throws EmptyResultDataAccessException
	 *             si no se encuentra el resitro en la tabla
	 *             <code>tipo_vela</code>.
	 */
	public TipoVelaPOJO busca(TipoVelaPOJO obj);

	/**
	 * Devuelve una lista con todos los registros de la tabla
	 * <code>tipo_vela</code> de la base de datos.
	 *
	 * @return Una lista con todos los elementos de la tabla
	 *         <code>tipo_vela</code>.
	 */
	public List<TipoVelaPOJO> buscaTodos();

	/**
	 * Devuelve una lista con todos los registros de la tabla
	 * <code>tipo_vela</code> de la base de datos a partir de los campos de de
	 * un POJO.
	 *
	 * @param obj
	 *            Objeto TipoVelaPOJO con los campos por los que se desea buscar
	 *            informados.
	 * @return Una lista con todos los elementos de la tabla
	 *         <code>tipo_vela</code> que cumplan los criterios de búsqueda.
	 */
	public List<TipoVelaPOJO> buscaVarios(TipoVelaPOJO obj);

	/**
	 * Inserta un registro en la tabla <code>tipo_vela</code> de la base de
	 * datos.
	 *
	 * @param obj
	 *            Objeto POJO con la información a insertar en la tabla
	 *            <code>tipo_vela</code> de la base de datos.
	 * @throws DuplicateKeyException
	 *             si ya existe un registro en la tabla <code>tipo_vela</code>
	 *             de base de datos con igual clave principal.
	 */
	public void crea(TipoVelaPOJO obj);

	/**
	 * Comprueba si existe un registro en la tabla <code>tipo_vela</code> de la
	 * base de datos a partir de su clave principal.
	 *
	 * @param obj
	 *            Objeto del que se desea comprobar su existencia en la tabla
	 *            <code>tipo_vela</code> de la base de datos.
	 * @return true si existe un registro en la tabla <code>tipo_vela</code> de
	 *         la base de datos con la misma clave principal, false si no es
	 *         así.
	 */
	public boolean existe(TipoVelaPOJO obj);

	public List<TipoVelaPOJO> buscaTiposDeVelaPorTipoBarco( final Integer idTipoBarco );
}
