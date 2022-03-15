package com.spinnakersimple.modelo.dao;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.capitanperegrina.common.basebeans.CambioEnTabla;
import com.capitanperegrina.common.exceptions.ServicioException;
import com.spinnakersimple.modelo.entidad.FotografiaPOJO;

/**
 * The Interface FotografiaDao.
 */
public interface FotografiaDao {

	/**
	 * Actualiza un registro de la tabla <code>fotografia</code> de la base de
	 * datos.
	 *
	 * @param obj Objeto FotografiaPOJO que se quiere actualizar en la base de
	 *            datos.
	 * @throws EmptyResultDataAccessException Si no se encuentra el registro que
	 *                                        se desea actualizar en la tabla
	 *                                        <code>fotografia</code>.
	 * @throws DuplicateKeyException          si se encuentra más de un registro
	 *                                        a actualizar en la tabla la tabla
	 *                                        <code>fotografia</code>.
	 */
	public void actualiza(FotografiaPOJO obj);

	/**
	 * Elimina físicamente un registro de la tabla <code>fotografia</code> de la
	 * base de datos a partir de los valores de su clave principal.
	 *
	 * @param obj Objeto FotografiaPOJO con los campos de la clave principal
	 *            informados.
	 * @throws EmptyResultDataAccessException Si no se encuentra el registro que
	 *                                        se desea eliminar en la tabla
	 *                                        <code>fotografia</code>.
	 */
	public void borra(FotografiaPOJO obj);

	/**
	 * Recupera de la base de datos un registro de la tabla
	 * <code>fotografia</code> y lo devuelve en el objeto FotografiaPOJO.
	 *
	 * @param obj Objeto FotografiaPOJO con los campos de la clave principal
	 *            informados.
	 * @return El objeto FotografiaPOJO con la información recuperada de la base
	 *         de datos.
	 * @throws EmptyResultDataAccessException si no se encuentra el resitro en
	 *                                        la tabla <code>fotografia</code>.
	 */
	public FotografiaPOJO busca(FotografiaPOJO obj) throws ServicioException;

	/**
	 * Devuelve una lista con todos los registros de la tabla
	 * <code>fotografia</code> de la base de datos.
	 *
	 * @return Una lista con todos los elementos de la tabla
	 *         <code>fotografia</code>.
	 */
	public List<FotografiaPOJO> buscaTodos();

	/**
	 * Devuelve una lista con todos los registros de la tabla
	 * <code>fotografia</code> de la base de datos a partir de los campos de de
	 * un POJO.
	 *
	 * @param obj Objeto FotografiaPOJO con los campos por los que se desea
	 *            buscar informados.
	 * @return Una lista con todos los elementos de la tabla
	 *         <code>fotografia</code> que cumplan los criterios de búsqueda.
	 */
	public List<FotografiaPOJO> buscaVarios(FotografiaPOJO obj);

	/**
	 * Iinserta un registro en la tabla <code>fotografia</code> de la base de
	 * datos.
	 *
	 * @param obj Objeto POJO con la informaci�n a insertar en la tabla
	 *            <code>fotografia</code> de la base de datos.
	 * @throws DuplicateKeyException si ya existe un registro en la tabla
	 *                               <code>fotografia</code> de base de datos
	 *                               con igual clave principal.
	 *
	 * @return El objeto POJO con la clave primaria generada.
	 */
	public FotografiaPOJO crea(FotografiaPOJO obj);

	/**
	 * Comprueba si existe un registro en la tabla <code>fotografia</code> de la
	 * base de datos a partir de su clave principal.
	 *
	 * @param obj Objeto del que se desea comprobar su existencia en la tabla
	 *            <code>fotografia</code> de la base de datos.
	 * @return true si existe un registro en la tabla <code>fotografia</code> de
	 *         la base de datos con la misma clave principal, false si no es
	 *         así.
	 */
	public boolean existe(FotografiaPOJO obj);

	/**
	 * Pasa a la tabla de historico un registro.
	 *
	 * @param obj el objeto a pasar al histórico.
	 * @param ct  información de auditoría.
	 */
	public void historifica(FotografiaPOJO obj, CambioEnTabla ct);

	boolean anuncioTieneFotografias(Integer id_anuncio);

	void historificaYEliminaPorUsuario( Integer idUsuario );
}
