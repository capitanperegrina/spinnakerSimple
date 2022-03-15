package com.spinnakersimple.modelo.dao;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.capitanperegrina.common.basebeans.CambioEnTabla;
import com.capitanperegrina.common.exceptions.ServicioException;
import com.spinnakersimple.modelo.entidad.UsuarioPOJO;
import com.spinnakersimple.modelo.vista.UsuarioCompletoView;

/**
 * The Interface UsuarioDao.
 */
public interface UsuarioDao {

	/**
	 * Actualiza un registro de la tabla <code>usuario</code> de la base de
	 * datos.
	 *
	 * @param obj
	 *            Objeto UsuarioPOJO que se quiere actualizar en la base de
	 *            datos.
	 * @throws EmptyResultDataAccessException
	 *             Si no se encuentra el registro que se desea actualizar en la
	 *             tabla <code>usuario</code>.
	 * @throws DuplicateKeyException
	 *             si se encuentra más de un registro a actualizar en la tabla
	 *             la tabla <code>usuario</code>.
	 */
	public void actualiza(UsuarioPOJO obj);

	/**
	 * Eelimina físicamente un registro de la tabla <code>usuario</code> de la
	 * base de datos a partir de los valores de su clave principal.
	 *
	 * @param obj
	 *            Objeto UsuarioPOJO con los campos de la clave principal
	 *            informados.
	 * @throws EmptyResultDataAccessException
	 *             Si no se encuentra el registro que se desea eliminar en la
	 *             tabla <code>usuario</code>.
	 */
	public void borra(UsuarioPOJO obj);

	/**
	 * Rrecupera de la base de datos un registro de la tabla
	 * <code>usuario</code> y lo devuelve en el objeto UsuarioPOJO.
	 *
	 * @param obj
	 *            Objeto UsuarioPOJO con los campos de la clave principal
	 *            informados.
	 * @return El objeto UsuarioPOJO con la información recuperada de la base de
	 *         datos.
	 * @throws EmptyResultDataAccessException
	 *             si no se encuentra el resitro en la tabla
	 *             <code>usuario</code>.
	 */
	public UsuarioPOJO busca(UsuarioPOJO obj) throws ServicioException;

	/**
	 * Busca por mail.
	 *
	 * @param mail
	 *            the mail
	 * @return the usuario POJO
	 * @throws ServicioException
	 *             the servicio exception
	 */
	public UsuarioPOJO buscaPorMail(String mail) throws ServicioException;

	/**
	 * Devuelve una lista con todos los registros de la tabla
	 * <code>usuario</code> de la base de datos.
	 *
	 * @return Una lista con todos los elementos de la tabla
	 *         <code>usuario</code>.
	 */
	public List<UsuarioPOJO> buscaTodos();

	/**
	 * Devuelve una lista con todos los registros de la tabla
	 * <code>usuario</code> de la base de datos a partir de los campos de de un
	 * POJO.
	 *
	 * @param obj
	 *            Objeto UsuarioPOJO con los campos por los que se desea buscar
	 *            informados.
	 * @return Una lista con todos los elementos de la tabla
	 *         <code>usuario</code> que cumplan los criterios de búsqueda.
	 */
	public List<UsuarioPOJO> buscaVarios(UsuarioPOJO obj);

	/**
	 * Inserta un registro en la tabla <code>usuario</code> de la base de datos.
	 *
	 * @param obj
	 *            Objeto POJO con la información a insertar en la tabla
	 *            <code>usuario</code> de la base de datos.
	 * @throws DuplicateKeyException
	 *             si ya existe un registro en la tabla <code>usuario</code> de
	 *             base de datos con igual clave principal.
	 * @return El objeto POJO con la clave primaria generada.
	 */
	public UsuarioPOJO crea(UsuarioPOJO obj);

	/**
	 * Comprueba si existe un registro en la tabla <code>usuario</code> de la
	 * base de datos a partir de su clave principal.
	 *
	 * @param obj
	 *            Objeto del que se desea comprobar su existencia en la tabla
	 *            <code>usuario</code> de la base de datos.
	 * @return true si existe un registro en la tabla <code>usuario</code> de la
	 *         base de datos con la misma clave principal, false si no es así.
	 */
	public boolean existe(UsuarioPOJO obj);

	/**
	 * Existe por mail.
	 *
	 * @param mail
	 *            the mail
	 * @return true, if successful
	 */
	public boolean existePorMail(String mail);

	/**
	 * Pasa a la tabla de historico un registro.
	 *
	 * @param obj
	 *            el objeto a pasar al histórico.
	 * @param ct
	 *            información de auditoría.
	 */
	public void historifica(UsuarioPOJO obj, CambioEnTabla ct);

//	public UsuarioCompletoView buscaUsuarioCompleto(UsuarioPOJO u);

	public List<UsuarioCompletoView> buscaUsuariosCompleto();

	void historificaYEliminaPorUsuario( Integer idUsuario );
}
