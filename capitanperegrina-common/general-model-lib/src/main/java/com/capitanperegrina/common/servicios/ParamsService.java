package com.capitanperegrina.common.servicios;

import java.util.List;

import com.capitanperegrina.common.basebeans.CambioEnTabla;
import com.capitanperegrina.common.modelo.entidad.tabla.ParamsPOJO;

/**
 * The Interface ParamsService.
 */
public interface ParamsService {

	/**
	 * Lee parametro bean.
	 *
	 * @param key the key
	 * @return the params POJO
	 */
	public ParamsPOJO leeParametroBean(String key);
	
	/**
	 * Lee todos.
	 *
	 * @return the list
	 */
	public List<ParamsPOJO> leeTodos();

	/**
	 * Lee parametro.
	 *
	 * @param key the key
	 * @return the string
	 */
	public String leeParametro(String key);
	
	/**
	 * Actualiza parametro.
	 *
	 * @param p the p
	 * @param ct the ct
	 * @return true, if successful
	 */
	public boolean actualizaParametro(ParamsPOJO p, CambioEnTabla ct);
}
