package com.spinnakersimple.modelo.dao;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.capitanperegrina.common.basebeans.CambioEnTabla;
import com.capitanperegrina.common.bean.Pair;
import com.capitanperegrina.common.exceptions.ServicioException;
import com.spinnakersimple.modelo.dto.FiltroBusquedaVelaBean;
import com.spinnakersimple.modelo.entidad.AnuncioPOJO;

// TODO: Auto-generated Javadoc
/**
 * The Interface AnuncioDao.
 */
public interface AnuncioDao {

    /**
     * Actualiza un registro de la tabla <code>anuncio</code> de la base de datos.
     *
     * @param obj Objeto AnuncioPOJO que se quiere actualizar en la base de datos.
     * @throws EmptyResultDataAccessException Si no se encuentra el registro que se
     *                                        desea actualizar en la tabla
     *                                        <code>anuncio</code>.
     * @throws DuplicateKeyException          si se encuentra más de un registro a
     *                                        actualizar en la tabla la tabla
     *                                        <code>anuncio</code>.
     */
    void actualiza(AnuncioPOJO obj);

    /**
     * Elimina físicamente un registro de la tabla <code>anuncio</code> de la base
     * de datos a partir de los valores de su clave principal.
     *
     * @param obj Objeto AnuncioPOJO con los campos de la clave principal
     *            informados.
     * @throws EmptyResultDataAccessException Si no se encuentra el registro que se
     *                                        desea eliminar en la tabla
     *                                        <code>anuncio</code>.
     */
    void borra(AnuncioPOJO obj);

    /**
     * Recupera de la base de datos un registro de la tabla <code>anuncio</code> y
     * lo devuelve en el objeto AnuncioPOJO.
     *
     * @param obj Objeto AnuncioPOJO con los campos de la clave principal
     *            informados.
     * @return El objeto AnuncioPOJO con la información recuperada de la base de
     *         datos.
     * @throws ServicioException              the servicio exception
     * @throws EmptyResultDataAccessException si no se encuentra el resitro en la
     *                                        tabla <code>anuncio</code>.
     */
    AnuncioPOJO busca(AnuncioPOJO obj) throws ServicioException;

    /**
     * Busca mis anuncios.
     *
     * @param idUsuario    the id usuario
     * @param tipoVela     the tipo vela
     * @param tiposAnuncio the tipos anuncio
     * @param admin        the admin
     * @return the list
     */
    List<AnuncioPOJO> buscaMisAnuncios(Integer idUsuario, Integer tipoVela, String[] tiposAnuncio, boolean admin);

    /**
     * Bbúsqueda de velas que comprar.
     *
     * @param filtro Objeto con los datos a usar para filtrar.
     * @return Lista de los anuncios encontrados.
     */
    List<AnuncioPOJO> buscaParaComprar(final FiltroBusquedaVelaBean filtro);

    /**
     * Busca anuncios por usuario.
     *
     * @param idUsuario     the id usuario
     * @param estadoAnuncio the estado anuncio
     * @return the list
     */
    List<AnuncioPOJO> buscaAnunciosPorUsuario(Integer idUsuario, String estadoAnuncio);

    /**
     * Devuelve una lista con todos los registros de la tabla <code>anuncio</code>
     * de la base de datos.
     *
     * @return Una lista con todos los elementos de la tabla <code>anuncio</code>.
     */
    List<AnuncioPOJO> buscaTodos();

    /**
     * Devuelve una lista con todos los registros de la tabla <code>anuncio</code>
     * de la base de datos a partir de los campos de de un POJO.
     *
     * @param obj Objeto AnuncioPOJO con los campos por los que se desea buscar
     *            informados.
     * @return Una lista con todos los elementos de la tabla <code>anuncio</code>
     *         que cumplan los criterios de búsqueda.
     */
    List<AnuncioPOJO> buscaVarios(AnuncioPOJO obj);

    /**
     * Inserta un registro en la tabla <code>anuncio</code> de la base de datos.
     *
     * @param obj Objeto POJO con la información a insertar en la tabla
     *            <code>anuncio</code> de la base de datos.
     * @return the anuncio POJO
     * @throws DuplicateKeyException si ya existe un registro en la tabla
     *                               <code>anuncio</code> de base de datos con igual
     *                               clave principal.
     */
    AnuncioPOJO crea(AnuncioPOJO obj);

    /**
     * Comprueba si existe un registro en la tabla <code>anuncio</code> de la base
     * de datos a partir de su clave principal.
     *
     * @param obj Objeto del que se desea comprobar su existencia en la tabla
     *            <code>anuncio</code> de la base de datos.
     * @return true si existe un registro en la tabla <code>anuncio</code> de la
     *         base de datos con la misma clave principal, false si no es así.
     */
    boolean existe(AnuncioPOJO obj);

    /**
     * Pasa a la tabla de historico un registro.
     *
     * @param obj the obj
     * @param ct  the ct
     */
    void historifica(AnuncioPOJO obj, CambioEnTabla ct);

    /**
     * Num anuncios pendientes.
     *
     * @return the integer
     */
    Integer numAnunciosPendientes();

    /**
     * Num anuncios vigentes.
     *
     * @return the integer
     */
    Integer numAnunciosVigentes();

    /**
     * Num anuncios proximos A caducar.
     *
     * @return the integer
     */
    Integer numAnunciosProximosACaducar();

    /**
     * Busca anuncios pendientes.
     *
     * @return the list
     */
    List<AnuncioPOJO> buscaAnunciosPendientes();

    /**
     * Busca anuncios publicados.
     *
     * @return the list
     */
    List<AnuncioPOJO> buscaAnunciosPublicados();

    /**
     * Busca anuncios que caducan en N dias.
     *
     * @param numeroDias the numero dias
     * @return the list
     */
    List<AnuncioPOJO> buscaAnunciosQueCaducanEnNDias(int numeroDias);

    /**
     * Actualiza estadisticas listado.
     *
     * @param idUsuario             the id usuario
     * @param idsAnuncioConsultados the ids anuncio consultados
     */
    void actualizaEstadisticasListado(Integer idUsuario, List<Integer> idsAnuncioConsultados);

    /**
     * Actualiza estadisticas visto.
     *
     * @param idUsuario the id usuario
     * @param idAnuncio the id anuncio
     */
    void actualizaEstadisticasVisto(Integer idUsuario, Integer idAnuncio);

    /**
     * Actualiza estadisticas comentado.
     *
     * @param idUsuario the id usuario
     * @param idAnuncio the id anuncio
     */
    void actualizaEstadisticasComentado(Integer idUsuario, Integer idAnuncio);

    /**
     * Visble por primera vez.
     *
     * @param id_anuncio the id anuncio
     * @return true, if successful
     */
    boolean visblePorPrimeraVez(final Integer id_anuncio);

    /**
     * Historifica Y elimina por usuario.
     *
     * @param idUsuario the id usuario
     */
    void historificaYEliminaPorUsuario(Integer idUsuario);

    /**
     * Numero anuncios por tipo barco.
     *
     * @return the list
     */
    List<Pair<String, Integer>> numeroAnunciosPorTipoBarco();

    /**
     * Numero anuncios por tipo clase.
     *
     * @return the list
     */
    List<Pair<String, Integer>> numeroAnunciosPorTipoClase();

    /**
     * Busca anuncios destacados.
     *
     * @return the list
     */
    List<AnuncioPOJO> buscaAnunciosDestacados();

    /**
     * Actualiza anuncios destacados.
     *
     * @param ids the ids
     * @return the list
     */
    int actualizaAnunciosDestacados(List<String> ids);
}
