package com.spinnakersimple.modelo.dao;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.spinnakersimple.modelo.entidad.TipoBarcoPOJO;

public interface TipoBarcoDao {

	/**
	 * Inserta un registro en la tabla <code>tipo_barco</code> de la base de datos.
	 * @param obj Objeto POJO con la informaci&oacute;n a insertar en la tabla <code>tipo_barco</code> de la base de datos.
	 * @throws DuplicateKeyException si ya existe un registro en la tabla <code>tipo_barco</code> de base de datos con igual clave principal.
	 */
	void crea(TipoBarcoPOJO obj);

	/**
	 * Comprueba si existe un registro en la tabla <code>tipo_barco</code> de la base de datos a partir de su clave principal.
	 * @param obj Objeto del que se desea comprobar su existencia en la tabla <code>tipo_barco</code> de la base de datos.
	 * @return true si existe un registro en la tabla <code>tipo_barco</code> de la base de datos con la misma clave principal, false si no es así.
	 */
	boolean existe(TipoBarcoPOJO obj);

	/**
	 * Recupera de la base de datos un registro de la tabla <code>tipo_barco</code> y lo devuelve en el objeto TipoBarcoPOJO.
	 * @param obj Objeto TipoBarcoPOJO con los campos de la clave principal informados.
	 * @return El objeto TipoBarcoPOJO con la informaci&oacute;n recuperada de la base de datos.
	 * @throws EmptyResultDataAccessException si no se encuentra el resitro en la tabla <code>tipo_barco</code>.
	 */
	TipoBarcoPOJO busca(TipoBarcoPOJO obj);

	/**
	 * Actualiza un registro de la tabla <code>tipo_barco</code> de la base de datos.
	 * @param obj Objeto TipoBarcoPOJO que se quiere actualizar en la base de datos.
	 * @throws EmptyResultDataAccessException Si no se encuentra el registro que se desea actualizar en la tabla <code>tipo_barco</code>.
	 * @throws DuplicateKeyException si se encuentra m&aacute;s de un registro a actualizar en la tabla la tabla <code>tipo_barco</code>.
	 */
	void actualiza(TipoBarcoPOJO obj);

	/**
	 * Elimina f&iacute;sicamente un registro de la tabla <code>tipo_barco</code> de la base de datos a partir de los valores de su clave principal.
	 * @param obj Objeto TipoBarcoPOJO con los campos de la clave principal informados.
	 * @throws EmptyResultDataAccessException Si no se encuentra el registro que se desea eliminar en la tabla <code>tipo_barco</code>.
	 */
	void borra(TipoBarcoPOJO obj);

	/**
	 * Método devuelve una lista con todos los registros de la tabla <code>tipo_barco</code> de la base de datos.
	 * @return Una lista con todos los elementos de la tabla <code>tipo_barco</code>.
	 */
	List<TipoBarcoPOJO> buscaTodos();

	/**
	 * Devuelve una lista con todos los registros de la tabla <code>tipo_barco</code> de la base de datos a partir de los campos de de un POJO.
	 * @param obj Objeto TipoBarcoPOJO con los campos por los que se desea buscar informados.
	 * @return Una lista con todos los elementos de la tabla <code>tipo_barco</code> que cumplan los criterios de b�squeda.
	 */
	List<TipoBarcoPOJO> buscaVarios(TipoBarcoPOJO obj);
}