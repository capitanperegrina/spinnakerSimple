package com.capitanperegrina.common.modelo.dao;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.capitanperegrina.common.basebeans.CambioEnTabla;
import com.capitanperegrina.common.exceptions.ServicioException;
import com.capitanperegrina.common.modelo.entidad.tabla.ParamsPOJO;

public interface ParamsDao {

	/**
	 * Inserta un registro en la tabla <code>params</code> de la base de datos.
	 * @param obj Objeto POJO con la información a insertar en la tabla <code>params</code> de la base de datos.
	 * @throws DuplicateKeyException si ya existe un registro en la tabla <code>params</code> de base de datos con igual clave principal.
	 */
	void crea(ParamsPOJO obj);

	/**
	 * Comprueba si existe un registro en la tabla <code>params</code> de la base de datos a partir de su clave principal.
	 * @param obj Objeto del que se desea comprobar su existencia en la tabla <code>params</code> de la base de datos.
	 * @return true si existe un registro en la tabla <code>params</code> de la base de datos con la misma clave principal, false si no es as�.
	 */
	boolean existe(ParamsPOJO obj);

	/**
	 * Recupera de la base de datos un registro de la tabla <code>params</code> y lo devuelve en el objeto ParamsPOJO.
	 * @param obj Objeto ParamsPOJO con los campos de la clave principal informados.
	 * @return El objeto ParamsPOJO con la información recuperada de la base de datos.
	 * @throws EmptyResultDataAccessException si no se encuentra el resitro en la tabla <code>params</code>.
	 */
	ParamsPOJO busca(ParamsPOJO obj) throws ServicioException;

	/**
	 * Actualiza un registro de la tabla <code>params</code> de la base de datos.
	 * @param obj Objeto ParamsPOJO que se quiere actualizar en la base de datos.
	 * @throws EmptyResultDataAccessException Si no se encuentra el registro que se desea actualizar en la tabla <code>params</code>.
	 * @throws DuplicateKeyException si se encuentra más de un registro a actualizar en la tabla la tabla <code>params</code>.
	 */
	void actualiza(ParamsPOJO obj);

	/**
	 * Elimina físicamente un registro de la tabla <code>params</code> de la base de datos a partir de los valores de su clave principal.
	 * @param obj Objeto ParamsPOJO con los campos de la clave principal informados.
	 * @throws EmptyResultDataAccessException Si no se encuentra el registro que se desea eliminar en la tabla <code>params</code>.
	 */
	void borra(ParamsPOJO obj);

	/**
	 * Devuelve una lista con todos los registros de la tabla <code>params</code> de la base de datos.
	 * @return Una lista con todos los elementos de la tabla <code>params</code>.
	 */
	List<ParamsPOJO> buscaTodos();

	/**
	 * Devuelve una lista con todos los registros de la tabla <code>params</code> de la base de datos a partir de los campos de de un POJO.
	 * @param obj Objeto ParamsPOJO con los campos por los que se desea buscar informados.
	 * @return Una lista con todos los elementos de la tabla <code>params</code> que cumplan los criterios de búsqueda.
	 */
	List<ParamsPOJO> buscaVarios(ParamsPOJO obj);

	/**
	 * Hace una copia del objeto en su tabla de historico correspondiente.
	 * @param obj Objeto ParamsPOJO que se desea historificar.
	 * @param ct Objeto CambioEnTabla con los datos de quién y cuando historifica.
	 */
	void historifica(ParamsPOJO obj, CambioEnTabla ct);

}