package com.spinnakersimple.servicios;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.capitanperegrina.common.basebeans.CambioEnTabla;
import com.capitanperegrina.common.bean.Pair;
import com.capitanperegrina.common.modelo.entidad.tabla.SocialPOJO;
import com.spinnakersimple.modelo.bean.AnuncioCompleto;
import com.spinnakersimple.modelo.dto.FiltroBusquedaVelaBean;
import com.spinnakersimple.modelo.entidad.AnuncioPOJO;
import com.spinnakersimple.modelo.entidad.CompradoresPOJO;
import com.spinnakersimple.modelo.entidad.FotografiaPOJO;
import com.spinnakersimple.modelo.entidad.TipoVelaPOJO;

// TODO: Auto-generated Javadoc
/**
 * Interfaz del servicio de mantenimiento de compra venta de anuncios.
 */
public interface AnunciosService {

    /**
     * Alta anuncio y fotografías.
     *
     * @param ac the ac
     * @param ct the ct
     * @return the anuncio completo
     */
    AnuncioCompleto altaAnuncio(AnuncioCompleto ac, CambioEnTabla ct);

    /**
     * Alta anuncio, usuario y fotografías.
     *
     * @param ac the ac
     * @param ct the ct
     * @return the anuncio completo
     */
    AnuncioCompleto altaUsuarioAnuncio(AnuncioCompleto ac, CambioEnTabla ct);

    /**
     * Buscar anuncios.
     *
     * @param filtro    the filtro
     * @param idUsuario the id usuario
     * @return the list
     */
    List<AnuncioCompleto> buscarAnuncios(final FiltroBusquedaVelaBean filtro, final Integer idUsuario);

    /**
     * Actualiza anuncio un anuncio con los datos recibidos.
     *
     * @param a  the a Anuncio a actualizar.
     * @param ct the ct Datos de audiroría.
     * @return el anuncio tal y como ha quedado guardado en base de datos.
     */
    AnuncioPOJO actualizaAnuncio(final AnuncioPOJO a, final CambioEnTabla ct);

    /**
     * Busca numero de anuncios pendientes de confirmar.
     *
     * @return the integer
     */
    Integer buscaNumeroDeAnunciosPendientesDeConfirmar();

    /**
     * Buscar anuncio.
     *
     * @param idAnuncio the id anuncio
     * @param idUsuario the id usuario
     * @param registrar the registrar
     * @return the anuncio completo
     */
    AnuncioCompleto buscarAnuncio(Integer idAnuncio, Integer idUsuario, boolean registrar);

    /**
     * Buscar anuncios.
     *
     * @param f     the f
     * @param ct    the ct
     * @param admin the admin
     * @return the list
     */
    List<AnuncioCompleto> buscarAnuncios(FiltroBusquedaVelaBean f, CambioEnTabla ct, boolean admin);

    /**
     * Buscar anuncios.
     *
     * @param idUsuario the id usuario
     * @param estado    the estado
     * @return the list
     */
    List<AnuncioPOJO> buscarAnuncios(Integer idUsuario, String estado);

    /**
     * Eliminar fotografia.
     *
     * @param idFotografia the id fotografia
     * @param ct           the ct
     */
    void eliminarFotografia(Integer idFotografia, CambioEnTabla ct);

    /**
     * Fotos de un anuncio.
     *
     * @param idAnuncio the id anuncio
     * @return the list
     */
    List<FotografiaPOJO> fotosDeUnAnuncio(Integer idAnuncio);

    /**
     * Guarda anuncio.
     *
     * @param a  the a
     * @param ct the ct
     * @return the anuncio POJO
     */
    AnuncioPOJO guardaAnuncio(final AnuncioPOJO a, final CambioEnTabla ct);

    /**
     * Guarda fotografia.
     *
     * @param f  the f
     * @param ct the ct
     * @return the fotografia POJO
     */
    FotografiaPOJO guardaFotografia(final FotografiaPOJO f, CambioEnTabla ct);

    /**
     * Guarda fotografias.
     *
     * @param fotos the fotos
     * @param ct    the ct
     */
    void guardaFotografias(final List<FotografiaPOJO> fotos, CambioEnTabla ct);

    /**
     * Modifica estado anuncio.
     *
     * @param idAnuncio   the id anuncio
     * @param nuevoEstado the nuevo estado
     * @param ct          the ct
     * @return true, if successful
     */
    boolean modificaEstadoAnuncio(Integer idAnuncio, Integer nuevoEstado, CambioEnTabla ct);

    /**
     * Actualiza social.
     *
     * @param s  the s
     * @param ct the ct
     */
    public void actualizaSocial(SocialPOJO s, CambioEnTabla ct);

    /**
     * Save and mail.
     *
     * @param comprador the comprador
     */
    void saveAndMail(CompradoresPOJO comprador);

    /**
     * Obten estadisticas escritorio.
     *
     * @return the map
     */
    Map<String, Integer> obtenEstadisticasEscritorio();

    /**
     * Busca anuncios.
     *
     * @param idUsuario the id usuario
     * @param estado    the estado
     * @param caducaEn  the caduca en
     * @return the list
     */
    List<AnuncioPOJO> buscaAnuncios(Integer idUsuario, Integer estado, Integer caducaEn);

    /**
     * Busca mensajes de anuncios no finalizados.
     *
     * @param revisado the revisado
     * @return the list
     */
    List<CompradoresPOJO> buscaMensajesDeAnunciosNoFinalizados(Integer revisado);

    /**
     * Notifica anuncios que caducan en N dias.
     *
     * @param daysBefore the days before
     */
    void notificaAnunciosQueCaducanEnNDias(int daysBefore);

    /**
     * Renueva anuncio.
     *
     * @param idAnuncio the id anuncio
     * @param ct        the ct
     * @return the calendar
     */
    Calendar renuevaAnuncio(Integer idAnuncio, CambioEnTabla ct);

    /**
     * Renueva anuncio desde url.
     *
     * @param idRenocacionAnuncio the id renocacion anuncio
     * @return the calendar
     */
    Calendar renuevaAnuncioDesdeUrl(Integer idRenocacionAnuncio);

    /**
     * Busca fotografia.
     *
     * @param idFotografia the id fotografia
     * @return the fotografia POJO
     */
    FotografiaPOJO buscaFotografia(Integer idFotografia);

    /**
     * Publica en redes sociales.
     *
     * @param ct the ct
     * @return the string
     */
    String publicaEnRedesSociales(CambioEnTabla ct);

    // List<AnuncioPOJO> buscaAnunciosPendientes();
    //
    // List<AnuncioPOJO> buscaAnunciosTodos();
    //
    // List<AnuncioPOJO> buscaAnunciosPublicados();
    //
    // List<AnuncioPOJO> buscaAnunciosProximosACaducar();

    /**
     * Busca tipos de vela por tipo barco.
     *
     * @param idTipoBarco the id tipo barco
     * @return the list
     */
    List<TipoVelaPOJO> buscaTiposDeVelaPorTipoBarco(final Integer idTipoBarco);

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
    List<AnuncioCompleto> buscaAnunciosDestacados();

}
