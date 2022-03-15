package com.capitanperegrina.common.modelo.dao;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.capitanperegrina.common.modelo.entidad.tabla.SocialPOJO;

public interface SocialDao {

	/**
	 * Inserta un registro en la tabla <code>social</code> de la base de datos.
	 *
	 * @param obj Objeto POJO con la informaci&oacute;n a insertar en la tabla
	 *            <code>social</code> de la base de datos.
	 * @throws DuplicateKeyException si ya existe un registro en la tabla
	 *                               <code>social</code> de base de datos con
	 *                               igual clave principal.
	 */
	SocialPOJO crea(SocialPOJO obj);

	/**
	 * Comprueba si existe un registro en la tabla <code>social</code> de la
	 * base de datos a partir de su clave principal.
	 *
	 * @param obj Objeto del que se desea comprobar su existencia en la tabla
	 *            <code>social</code> de la base de datos.
	 * @return true si existe un registro en la tabla <code>social</code> de la
	 *         base de datos con la misma clave principal, false si no es así.
	 */
	boolean existe(SocialPOJO obj);

	/**
	 * Recupera de la base de datos un registro de la tabla <code>social</code>
	 * y lo devuelve en el objeto SocialPOJO.
	 *
	 * @param obj Objeto SocialPOJO con los campos de la clave principal
	 *            informados.
	 * @return El objeto SocialPOJO con la informaci&oacute;n recuperada de la
	 *         base de datos.
	 * @throws EmptyResultDataAccessException si no se encuentra el resitro en
	 *                                        la tabla <code>social</code>.
	 */
	SocialPOJO busca(SocialPOJO obj);

	/**
	 * Actualiza un registro de la tabla <code>social</code> de la base de
	 * datos.
	 *
	 * @param obj Objeto SocialPOJO que se quiere actualizar en la base de
	 *            datos.
	 * @throws EmptyResultDataAccessException Si no se encuentra el registro que
	 *                                        se desea actualizar en la tabla
	 *                                        <code>social</code>.
	 * @throws DuplicateKeyException          si se encuentra m&aacute;s de un
	 *                                        registro a actualizar en la tabla
	 *                                        la tabla <code>social</code>.
	 */
	void actualiza(SocialPOJO obj);

	/**
	 * Elimina f&iacute;sicamente un registro de la tabla <code>social</code> de
	 * la base de datos a partir de los valores de su clave principal.
	 *
	 * @param obj Objeto SocialPOJO con los campos de la clave principal
	 *            informados.
	 * @throws EmptyResultDataAccessException Si no se encuentra el registro que
	 *                                        se desea eliminar en la tabla
	 *                                        <code>social</code>.
	 */
	void borra(SocialPOJO obj);

	/**
	 * Método devuelve una lista con todos los registros de la tabla
	 * <code>social</code> de la base de datos.
	 *
	 * @return Una lista con todos los elementos de la tabla
	 *         <code>social</code>.
	 */
	List<SocialPOJO> buscaTodos();

	/**
	 * Devuelve una lista con todos los registros de la tabla
	 * <code>social</code> de la base de datos a partir de los campos de de un
	 * POJO.
	 *
	 * @param obj Objeto SocialPOJO con los campos por los que se desea buscar
	 *            informados.
	 * @return Una lista con todos los elementos de la tabla <code>social</code>
	 *         que cumplan los criterios de b�squeda.
	 */
	List<SocialPOJO> buscaVarios(SocialPOJO obj);

	SocialPOJO busca(final String entidad, final Integer clave);

	boolean existe(final String clave, final Integer valor);
}