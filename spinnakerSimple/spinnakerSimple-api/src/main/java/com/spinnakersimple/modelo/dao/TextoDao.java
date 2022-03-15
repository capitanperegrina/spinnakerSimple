package com.spinnakersimple.modelo.dao;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.capitanperegrina.common.basebeans.CambioEnTabla;
import com.spinnakersimple.modelo.entidad.TextoPOJO;

/**
 * The Interface TextoDao.
 */
public interface TextoDao {

	/**
	 * Actualiza un registro de la tabla <code>texto</code> de la base de datos.
	 *
	 * @param obj
	 *            Objeto TextoPOJO que se quiere actualizar en la base de datos.
	 * @throws EmptyResultDataAccessException
	 *             Si no se encuentra el registro que se desea actualizar en la
	 *             tabla <code>texto</code>.
	 * @throws DuplicateKeyException
	 *             si se encuentra más de un registro a actualizar en la tabla
	 *             la tabla <code>texto</code>.
	 */
	public void actualiza(TextoPOJO obj);

	/**
	 * Elimina físicamente un registro de la tabla <code>texto</code> de la base
	 * de datos a partir de los valores de su clave principal.
	 *
	 * @param obj
	 *            Objeto TextoPOJO con los campos de la clave principal
	 *            informados.
	 * @throws EmptyResultDataAccessException
	 *             Si no se encuentra el registro que se desea eliminar en la
	 *             tabla <code>texto</code>.
	 */
	public void borra(TextoPOJO obj);

	/**
	 * Recupera de la base de datos un registro de la tabla <code>texto</code> y
	 * lo devuelve en el objeto TextoPOJO.
	 *
	 * @param obj
	 *            Objeto TextoPOJO con los campos de la clave principal
	 *            informados.
	 * @return El objeto TextoPOJO con la información recuperada de la base de
	 *         datos.
	 * @throws EmptyResultDataAccessException
	 *             si no se encuentra el resitro en la tabla <code>texto</code>.
	 */
	public TextoPOJO busca(TextoPOJO obj);

	/**
	 * Recupera de la base de datos un registro de la tabla <code>datos</code> y
	 * lo devuelve en el objeto DatosPOJO.
	 *
	 * @param lang
	 *            the lang
	 * @param clave
	 *            valor a buscar
	 * @return El objeto DatosPOJO con la información recuperada de la base de
	 *         datos.
	 * @throws EmptyResultDataAccessException
	 *             si no se encuentra el resitro en la tabla <code>datos</code>.
	 */
	public TextoPOJO buscaPorClaveLang(String clave, String lang);

	/**
	 * Devuelve una lista con todos los registros de la tabla <code>texto</code>
	 * de la base de datos.
	 *
	 * @return Una lista con todos los elementos de la tabla <code>texto</code>.
	 */
	public List<TextoPOJO> buscaTodos();

	/**
	 * Devuelve una lista con todos los registros de la tabla <code>texto</code>
	 * de la base de datos a partir de los campos de de un POJO.
	 *
	 * @param obj
	 *            Objeto TextoPOJO con los campos por los que se desea buscar
	 *            informados.
	 * @return Una lista con todos los elementos de la tabla <code>texto</code>
	 *         que cumplan los criterios de búsqueda.
	 */
	public List<TextoPOJO> buscaVarios(TextoPOJO obj);

	/**
	 * Inserta un registro en la tabla <code>texto</code> de la base de datos.
	 *
	 * @param obj
	 *            Objeto POJO con la información a insertar en la tabla
	 *            <code>texto</code> de la base de datos.
	 * @throws DuplicateKeyException
	 *             si ya existe un registro en la tabla <code>texto</code> de
	 *             base de datos con igual clave principal.
	 */
	public void crea(TextoPOJO obj);

	/**
	 * Comprueba si existe un registro en la tabla <code>texto</code> de la base
	 * de datos a partir de su clave principal.
	 *
	 * @param obj
	 *            Objeto del que se desea comprobar su existencia en la tabla
	 *            <code>texto</code> de la base de datos.
	 * @return true si existe un registro en la tabla <code>texto</code> de la
	 *         base de datos con la misma clave principal, false si no es así.
	 */
	public boolean existe(TextoPOJO obj);

	/**
	 * Pasa a la tabla de historico un registro.
	 *
	 * @param obj
	 *            el objeto a pasar al histórico.
	 * @param ct
	 *            información de auditoría.
	 */
	public void historifica(TextoPOJO obj, CambioEnTabla ct);
}
