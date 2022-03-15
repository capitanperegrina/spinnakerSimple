package com.spinnakersimple.servicios.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Triple;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.capitanperegrina.common.basebeans.CambioEnTabla;
import com.capitanperegrina.common.bean.Pair;
import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.config.DefaultParameters;
import com.capitanperegrina.common.exceptions.ServicioErrorException;
import com.capitanperegrina.common.exceptions.ServicioException;
import com.capitanperegrina.common.modelo.entidad.tabla.SocialPOJO;
import com.capitanperegrina.common.seguridad.Criptografia;
import com.capitanperegrina.common.seguridad.MD5;
import com.capitanperegrina.common.utils.Formateador;
import com.capitanperegrina.common.utils.StackTraceUtil;
import com.capitanperegrina.social.facebook.ClienteFacebook;
import com.capitanperegrina.social.facebook.FacebookException;
import com.capitanperegrina.social.instagram.ClienteInstagram;
import com.capitanperegrina.social.instagram.InstagramException;
import com.capitanperegrina.social.twitter.ClienteTwitter;
import com.spinnakersimple.helpers.AnuncioMailHelper;
import com.spinnakersimple.helpers.ConsultaVelaMailHelper;
import com.spinnakersimple.helpers.UsuarioMailHelper;
import com.spinnakersimple.modelo.bean.AnuncioCompleto;
import com.spinnakersimple.modelo.dao.AnuncioDao;
import com.spinnakersimple.modelo.dao.CompradoresDao;
import com.spinnakersimple.modelo.dao.FotografiaDao;
import com.spinnakersimple.modelo.dao.RenovacionAnuncioDao;
import com.spinnakersimple.modelo.dao.SliderDao;
import com.spinnakersimple.modelo.dao.SocialSpinnakerSimpleDao;
import com.spinnakersimple.modelo.dao.TextoDao;
import com.spinnakersimple.modelo.dao.TipoVelaDao;
import com.spinnakersimple.modelo.dao.UsuarioDao;
import com.spinnakersimple.modelo.dto.FiltroBusquedaVelaBean;
import com.spinnakersimple.modelo.entidad.AnuncioPOJO;
import com.spinnakersimple.modelo.entidad.CompradoresPOJO;
import com.spinnakersimple.modelo.entidad.FotografiaPOJO;
import com.spinnakersimple.modelo.entidad.RenovacionAnuncioPOJO;
import com.spinnakersimple.modelo.entidad.TipoVelaPOJO;
import com.spinnakersimple.modelo.entidad.UsuarioPOJO;
import com.spinnakersimple.modelo.excepciones.AltaUsuarioDuplicadaException;
import com.spinnakersimple.modelo.util.SpinnakerSimpleGlobals;
import com.spinnakersimple.servicios.AnunciosService;
import com.spinnakersimple.servicios.AuxiliarService;
import com.spinnakersimple.servicios.UsuariosService;
import com.spinnakersimple.util.tablasayuda.Parametrizacion;

import twitter4j.TwitterException;

/**
 * The Class AnunciosServiceImpl.
 *
 * @author <a href="mailto:yo@capitanperegrina.com">Capitán Peregrina</a>
 */
@Service("anunciosService")
@Transactional
public class AnunciosServiceImpl implements AnunciosService {

    /** The log. */
    static Logger log = Logger.getLogger(AnunciosServiceImpl.class);

    @Autowired
    private Criptografia criptografia;

    /** El campo anuncios service. */
    @Autowired
    private AnunciosService anunciosService;

    /** The consulta vela mail helper. */
    @Autowired
    private ConsultaVelaMailHelper consultaVelaMailHelper;

    /** The consulta vela mail helper. */
    @Autowired
    private UsuarioMailHelper usuarioMailHelper;

    /** The consulta vela mail helper. */
    @Autowired
    private AnuncioMailHelper anuncioMailHelper;

    /** The params. */
    @Autowired
    private DefaultParameters params;

    /** El campo auxiliar service. */
    @Autowired
    private AuxiliarService auxiliarService;

    /** The slider dao. */
    @Autowired
    private SliderDao sliderDao;

    /** The tipo vela dao. */
    @Autowired
    private TipoVelaDao tipoVelaDao;

    /** The anuncio dao. */
    @Autowired
    private AnuncioDao anuncioDao;

    /** The usuario dao. */
    @Autowired
    private UsuarioDao usuarioDao;

    /** The texto dao. */
    @Autowired
    private TextoDao textoDao;

    /** The fotografia dao. */
    @Autowired
    private FotografiaDao fotografiaDao;

    /** The compradores dao. */
    @Autowired
    private CompradoresDao compradoresDao;

    /** El campo usuarios service. */
    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private SocialSpinnakerSimpleDao socialDao;

    @Autowired
    private Parametrizacion parametrizacion;

    @Autowired
    private RenovacionAnuncioDao renovacionAnuncioDao;

    /*
     * (non-Javadoc)
     *
     * @see com.spinnakersimple.modelo.servicios.SpinnakerSimplePublico#altaAnuncio(
     * com.spinnakersimple.modelo.dto.form.VenderVelaFormDTO,
     * com.capitanperegrina.common.basebeans.CambioEnTabla)
     */
    @Override
    public AnuncioCompleto altaAnuncio(final AnuncioCompleto ac, final CambioEnTabla ct) {
	log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());

	try {
	    final AnuncioCompleto anuncioCompleto = new AnuncioCompleto();

	    // Guardamos el anuncio.
	    anuncioCompleto.setAnuncio(this.anunciosService.guardaAnuncio(ac.getAnuncio(), ct));

	    final TipoVelaPOJO tipoVela = new TipoVelaPOJO();
	    tipoVela.setIdTipoVela(anuncioCompleto.getAnuncio().getTipoVela());
	    anuncioCompleto.setTipoVela(this.tipoVelaDao.busca(tipoVela));

	    // Guardamos las fotos.
	    for (final FotografiaPOJO f : ac.getFotos()) {
		f.setIdFotografia(0);
		f.setIdAnuncio(anuncioCompleto.getAnuncio().getIdAnuncio());
		f.setFecAlta(ct.getFec());
		f.setFecModif(ct.getFec());
		f.setIdUsuarioAlta(ct.getUsu());
		f.setIdUsuarioModif(ct.getUsu());
		anuncioCompleto.getFotos().add(this.anunciosService.guardaFotografia(f, ct));
	    }

	    // Como el usuario está logueado, se obtiene el usuario desde bbdd.
	    UsuarioPOJO u = new UsuarioPOJO();
	    u.setIdUsuario(ct.getUsu());
	    u = this.usuarioDao.busca(u);
	    anuncioCompleto.setUsuario(u);

	    // Enviamos correos de alta de usuario y alta de vela.
	    this.anuncioMailHelper.enviaCorreosAltaVela(anuncioCompleto.getAnuncio(), anuncioCompleto.getUsuario());
	    return anuncioCompleto;
	} catch (final ServicioException e) {
	    // Usuario no encontrado
	    log.info(e);
	    throw new ServicioErrorException(e);
	} finally {
	    log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
	}
    }

    /*
     * (non-Javadoc)
     *
     * @see com.spinnakersimple.modelo.servicios.SpinnakerSimplePublico#
     * guardaAnuncioCompleto(com.spinnakersimple.modelo.dto.form. VenderVelaFormDTO,
     * com.capitanperegrina.common.basebeans.CambioEnTabla)
     */
    @Override
    public AnuncioCompleto altaUsuarioAnuncio(final AnuncioCompleto anuncioCompleto, final CambioEnTabla ct) {
	log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
	try {
	    if (!this.usuarioDao.existePorMail(anuncioCompleto.getUsuario().getMail())) {
		// Calculamos el MD5 de la pass.
		anuncioCompleto.getUsuario().setPass(MD5.getMD5(anuncioCompleto.getUsuario().getPass()));

		final UsuarioPOJO u = this.usuariosService.guardaUsuario(anuncioCompleto.getUsuario(), ct);
		if (ct.getUsu() == null) {
		    ct.setUsu(u.getIdUsuario());
		}
		anuncioCompleto.setUsuario(u);

		final AnuncioPOJO a = this.anunciosService.guardaAnuncio(anuncioCompleto.getAnuncio(), ct);
		anuncioCompleto.setAnuncio(a);

		final TipoVelaPOJO tipoVela = new TipoVelaPOJO();
		tipoVela.setIdTipoVela(anuncioCompleto.getAnuncio().getTipoVela());
		anuncioCompleto.setTipoVela(this.tipoVelaDao.busca(tipoVela));

		anuncioCompleto.setFotos(new ArrayList<FotografiaPOJO>(anuncioCompleto.getFotos().size()));
		for (final FotografiaPOJO f : anuncioCompleto.getFotos()) {
		    f.setIdFotografia(0);
		    f.setIdAnuncio(anuncioCompleto.getAnuncio().getIdAnuncio());
		    f.setFecAlta(ct.getFec());
		    f.setFecModif(ct.getFec());
		    f.setIdUsuarioAlta(ct.getUsu());
		    f.setIdUsuarioModif(ct.getUsu());
		    anuncioCompleto.getFotos().add(this.anunciosService.guardaFotografia(f, ct));
		}

		this.usuarioMailHelper.enviaCorreosAltaUsuario(u);
		this.anuncioMailHelper.enviaCorreosAltaVela(a, u);

		return anuncioCompleto;
	    } else {
		throw new AltaUsuarioDuplicadaException();
	    }
	} finally {
	    log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
	}
    }

    /*
     * (non-Javadoc)
     *
     * @see com.spinnakersimple.servicios.AnunciosService#actualizaAnuncio(com.
     * spinnakersimple.modelo.dto.AnuncioDTO,
     * com.capitanperegrina.common.basebeans.CambioEnTabla)
     */
    @Override
    public AnuncioPOJO actualizaAnuncio(final AnuncioPOJO a, final CambioEnTabla ct) {
	log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
	AnuncioPOJO ret;
	try {
	    final AnuncioPOJO old = this.anuncioDao.busca(a);
	    // Modificacion
	    old.setTipoBarco(a.getTipoBarco());
	    old.setTipoVela(a.getTipoVela());
	    old.setTituloAnuncio(a.getTituloAnuncio());
	    old.setDescripcion(a.getDescripcion());
	    old.setGramaje(a.getGramaje());
	    old.setGratil(a.getGratil());
	    old.setBaluma(a.getBaluma());
	    old.setPujamen(a.getPujamen());
	    old.setCaidaProa(a.getCaidaProa());
	    old.setCaidaPopa(a.getCaidaPopa());
	    old.setSuperficie(a.getSuperficie());
	    old.setTipoCometa(a.getTipoCometa());
	    old.setPrecio(a.getPrecio());
	    old.setPais(a.getPais());
	    old.setFecModif(ct.getFec());
	    old.setIdUsuarioModif(ct.getUsu());
	    this.anuncioDao.historifica(old, ct);
	    this.anuncioDao.actualiza(old);
	    ret = old;
	    return ret;
	} catch (final ServicioException e) {
	    log.error("", e);
	    throw new ServicioErrorException(
		    "SpinnakerSimplePublicoImpl.actualizaAnuncio.error.instanceNotFoundException");
	} finally {
	    log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
	}
    }

    /*
     * (non-Javadoc)
     *
     * @see com.spinnakersimple.modelo.servicios.SpinnakerSimplePublico#
     * buscaNumeroDeAnunciosPendientesDeConfirmar()
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer buscaNumeroDeAnunciosPendientesDeConfirmar() {
	log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
	try {
	    return this.anuncioDao.numAnunciosPendientes();
	} finally {
	    log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
	}
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.spinnakersimple.modelo.servicios.SpinnakerSimplePublico#buscarAnuncio
     * (java.lang.Integer)
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public AnuncioCompleto buscarAnuncio(final Integer idAnuncio, final Integer idUsuario, final boolean registrar) {
	log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
	try {

	    final AnuncioCompleto anuncioCompleto = new AnuncioCompleto();

	    AnuncioPOJO a = new AnuncioPOJO();
	    a.setIdAnuncio(idAnuncio);
	    a = this.anuncioDao.busca(a);
	    if (registrar) {
		this.anuncioDao.actualizaEstadisticasVisto(idUsuario, a.getIdAnuncio());
	    }

	    // Si hay usuario se busca el mismo.
	    if (idUsuario != null) {
		UsuarioPOJO usuarioQueConsulta = new UsuarioPOJO();
		usuarioQueConsulta.setIdUsuario(idUsuario);
		usuarioQueConsulta = this.usuarioDao.busca(usuarioQueConsulta);
		if (a.getPrecio() != null) {
		    final double factor = this.auxiliarService.leeTipoDeCambio(usuarioQueConsulta.getDivisa());
		    final double importe = a.getPrecio().doubleValue() * factor;
		    a.setPrecio(BigDecimal.valueOf(importe));
		}
	    }
	    anuncioCompleto.setAnuncio(a);
	    final TipoVelaPOJO tipoVela = new TipoVelaPOJO();
	    tipoVela.setIdTipoVela(anuncioCompleto.getAnuncio().getTipoVela());
	    anuncioCompleto.setTipoVela(this.tipoVelaDao.busca(tipoVela));

	    // Leemos el usuario.
	    final UsuarioPOJO u = new UsuarioPOJO();
	    u.setIdUsuario(a.getIdUsuarioAlta());
	    anuncioCompleto.setUsuario(this.usuarioDao.busca(u));

	    anuncioCompleto.setFotos(new ArrayList<FotografiaPOJO>());
	    final FotografiaPOJO fotografiaPojo = new FotografiaPOJO();
	    fotografiaPojo.setIdAnuncio(a.getIdAnuncio());
	    final List<FotografiaPOJO> listaFotografiasPojo = this.fotografiaDao.buscaVarios(fotografiaPojo);
	    anuncioCompleto.getFotos().addAll(listaFotografiasPojo);

	    return anuncioCompleto;

	} catch (final ServicioException e) {
	    log.error(e.getMessage());
	    throw new ServicioErrorException(e);
	} finally {
	    log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
	}
    }

    /*
     * (non-Javadoc)
     *
     * @see com.spinnakersimple.modelo.servicios.SpinnakerSimplePublico#
     * buscarAnuncios(com.spinnakersimple.modelo.dto.form.ComprarVelaFormDTO)
     */
    @Override
    public List<AnuncioCompleto> buscarAnuncios(final FiltroBusquedaVelaBean filtro, final Integer idUsuario) {

	log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
	try {
	    final List<AnuncioCompleto> ret = new ArrayList<>();
	    final List<AnuncioPOJO> listaAnunciosPojo = this.anuncioDao.buscaParaComprar(filtro);
	    final List<Integer> idsAnuncioConsultados = listaAnunciosPojo.stream().map(AnuncioPOJO::getIdAnuncio)
		    .collect(Collectors.toList());
	    this.anuncioDao.actualizaEstadisticasListado(idUsuario, idsAnuncioConsultados);

	    for (final AnuncioPOJO a : listaAnunciosPojo) {
		final AnuncioCompleto anuncioCompleto = new AnuncioCompleto();
		anuncioCompleto.setAnuncio(a);

		final TipoVelaPOJO tipoVela = new TipoVelaPOJO();
		tipoVela.setIdTipoVela(anuncioCompleto.getAnuncio().getTipoVela());
		anuncioCompleto.setTipoVela(this.tipoVelaDao.busca(tipoVela));

		final UsuarioPOJO u = new UsuarioPOJO();
		u.setIdUsuario(a.getIdUsuarioAlta());
		anuncioCompleto.setUsuario(this.usuarioDao.busca(u));
		anuncioCompleto.setFotos(new ArrayList<FotografiaPOJO>());

		final FotografiaPOJO fotografiaPojo = new FotografiaPOJO();
		fotografiaPojo.setIdAnuncio(a.getIdAnuncio());
		anuncioCompleto.getFotos().addAll(this.fotografiaDao.buscaVarios(fotografiaPojo));

		ret.add(anuncioCompleto);
	    }
	    return ret;
	} catch (final ServicioException e) {
	    log.error(e.getMessage());
	    throw new ServicioErrorException(e);
	} finally {
	    log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
	}

    }

    /**
     * Buscar anuncios.
     *
     * @param filtro the filtro
     * @param ct     the ct
     * @param admin  the admin
     * @return the list
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<AnuncioCompleto> buscarAnuncios(final FiltroBusquedaVelaBean filtro, final CambioEnTabla ct,
	    final boolean admin) {
	log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
	try {
	    final List<AnuncioCompleto> ret = new ArrayList<>();
	    final List<AnuncioPOJO> listaAnunciosPojo = this.anuncioDao.buscaMisAnuncios(ct.getUsu(),
		    filtro.getTipoVela(), filtro.getTipoAnuncio(), admin);
	    for (final AnuncioPOJO a : listaAnunciosPojo) {

		final AnuncioCompleto anuncioCompleto = new AnuncioCompleto();
		anuncioCompleto.setAnuncio(a);

		final TipoVelaPOJO tipoVela = new TipoVelaPOJO();
		tipoVela.setIdTipoVela(anuncioCompleto.getAnuncio().getTipoVela());
		anuncioCompleto.setTipoVela(this.tipoVelaDao.busca(tipoVela));

		final UsuarioPOJO u = new UsuarioPOJO();
		u.setIdUsuario(a.getIdUsuarioAlta());
		anuncioCompleto.setUsuario(this.usuarioDao.busca(u));

		final FotografiaPOJO fotografiaPojo = new FotografiaPOJO();
		fotografiaPojo.setIdAnuncio(a.getIdAnuncio());
		anuncioCompleto.setFotos(this.fotografiaDao.buscaVarios(fotografiaPojo));

		ret.add(anuncioCompleto);
	    }
	    return ret;
	} catch (final ServicioException e) {
	    log.error("", e);
	    throw new ServicioErrorException(e);
	} finally {
	    log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
	}
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<AnuncioPOJO> buscarAnuncios(final Integer idUsuario, final String estado) {
	log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
	try {
	    return this.anuncioDao.buscaAnunciosPorUsuario(idUsuario, estado);
	} finally {
	    log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
	}
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.spinnakersimple.servicios.anuncios.AnunciosService#eliminarFotografia
     * (java.lang.Integer, com.capitanperegrina.common.basebeans.CambioEnTabla)
     */
    @Override
    public void eliminarFotografia(final Integer idFotografia, final CambioEnTabla ct) {
	if (log.isDebugEnabled()) {
	    log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
	}
	try {
	    FotografiaPOJO f = new FotografiaPOJO();
	    f.setIdFotografia(idFotografia);
	    f = this.fotografiaDao.busca(f);
	    this.fotografiaDao.historifica(f, ct);
	    this.fotografiaDao.borra(f);
	} catch (final ServicioException e) {
	    log.error("", e);
	    throw new ServicioErrorException(e);
	} finally {
	    if (log.isDebugEnabled()) {
		log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
	    }
	}

    }

    /**
     * Fotos de un anuncio.
     *
     * @param idAnuncio the id anuncio
     * @return the list
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<FotografiaPOJO> fotosDeUnAnuncio(final Integer idAnuncio) {
	if (log.isDebugEnabled()) {
	    log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
	}
	try {
	    final FotografiaPOJO fotografiaPojo = new FotografiaPOJO();
	    fotografiaPojo.setIdAnuncio(idAnuncio);
	    return this.fotografiaDao.buscaVarios(fotografiaPojo);
	} finally {
	    log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
	}
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.spinnakersimple.modelo.servicios.SpinnakerSimplePublico#guardaAnuncio
     * (com.spinnakersimple.modelo.entidad.tabla.AnuncioPOJO,
     * com.capitanperegrina.common.basebeans.CambioEnTabla)
     */
    @Override
    public AnuncioPOJO guardaAnuncio(final AnuncioPOJO a, final CambioEnTabla ct) {
	if (log.isDebugEnabled()) {
	    log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
	}
	AnuncioPOJO ret;
	try {
	    final AnuncioPOJO old = this.anuncioDao.busca(a);
	    // Modificacion
	    old.setTituloAnuncio(a.getTituloAnuncio());
	    old.setTipoVela(a.getTipoVela());
	    old.setGratil(a.getGratil());
	    old.setBaluma(a.getBaluma());
	    old.setPujamen(a.getPujamen());
	    old.setDescripcion(a.getDescripcion());
	    old.setPrecio(a.getPrecio());
	    old.setVisible(a.getVisible());
	    old.setCaduca(a.getCaduca());
	    old.setPais(a.getPais());
	    old.setFecModif(ct.getFec());
	    old.setIdUsuarioModif(ct.getUsu());
	    this.anuncioDao.historifica(old, ct);
	    this.anuncioDao.actualiza(old);
	    ret = old;
	} catch (final ServicioException e) {
	    // Nuevo
	    log.trace(e);
	    log.info("No se encontró anuncio => nuevo.");
	    a.setFecAlta(ct.getFec());
	    a.setIdUsuarioAlta(ct.getUsu());
	    a.setFecModif(ct.getFec());
	    a.setIdUsuarioModif(ct.getUsu());
	    ret = this.anuncioDao.crea(a);
	} finally {
	    if (log.isDebugEnabled()) {
		log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
	    }
	}
	return ret;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.spinnakersimple.modelo.servicios.SpinnakerSimplePublico#
     * guardaFotografia(com.spinnakersimple.modelo.entidad.tabla.FotografiaPOJO,
     * com.capitanperegrina.common.basebeans.CambioEnTabla)
     */
    @Override
    public FotografiaPOJO guardaFotografia(final FotografiaPOJO f, final CambioEnTabla ct) {
	log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
	FotografiaPOJO ret;
	try {
	    final FotografiaPOJO old = this.fotografiaDao.busca(f);
	    // Modificacion
	    old.setIdAnuncio(f.getIdAnuncio());
	    old.setImagen(f.getImagen());
	    old.setTipo(f.getTipo());
	    old.setFecModif(ct.getFec());
	    old.setIdUsuarioModif(ct.getUsu());
	    this.fotografiaDao.historifica(old, ct);
	    this.fotografiaDao.actualiza(old);
	    ret = old;
	} catch (final ServicioException e) {
	    // Nuevo
	    log.trace(e);
	    log.info("No se encontró fotografía => nueva.");
	    f.setFecAlta(ct.getFec());
	    f.setIdUsuarioAlta(ct.getUsu());
	    f.setFecModif(ct.getFec());
	    f.setIdUsuarioModif(ct.getUsu());
	    ret = this.fotografiaDao.crea(f);
	} finally {
	    log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
	}
	return ret;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.spinnakersimple.servicios.anuncios.AnunciosService#guardaFotografias(
     * java.util.List, com.capitanperegrina.common.basebeans.CambioEnTabla)
     */
    @Override
    public void guardaFotografias(final List<FotografiaPOJO> fotos, final CambioEnTabla ct) {
	log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
	try {
	    for (final FotografiaPOJO f : fotos) {
		this.anunciosService.guardaFotografia(f, ct);
	    }
	} finally {
	    log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
	}
    }

    /*
     * (non-Javadoc)
     *
     * @see com.spinnakersimple.servicios.anuncios.AnunciosService#
     * modificaEstadoAnuncio(java.lang.Integer, java.lang.Integer,
     * com.capitanperegrina.common.basebeans.CambioEnTabla)
     */
    @Override
    public boolean modificaEstadoAnuncio(final Integer idAnuncio, final Integer nuevoEstado, final CambioEnTabla ct) {
	if (log.isDebugEnabled()) {
	    log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
	}
	try {
	    AnuncioPOJO a = new AnuncioPOJO();
	    a.setIdAnuncio(idAnuncio);
	    a = this.anuncioDao.busca(a);
	    a.setVisible(nuevoEstado);
	    a = this.anunciosService.guardaAnuncio(a, ct);

	    UsuarioPOJO u = new UsuarioPOJO();
	    u.setIdUsuario(a.getIdUsuarioAlta());
	    u = this.usuarioDao.busca(u);

	    // 1ª vez que se valida => mensaje al cliente.
	    if (this.anuncioDao.visblePorPrimeraVez(a.getIdAnuncio())) {
		// Mensaje
		this.anuncioMailHelper.enviaCorreosConfirmacionAltaVela(a, u);

		// Si además tiene fotografías y se está en producción publicamos en redes
		// sociales.
		if (this.fotografiaDao.anuncioTieneFotografias(a.getIdAnuncio())
			&& this.parametrizacion.isAppEnProduccion()) {

		    // Social
		    final Triple<Integer, Integer, Integer> t = this.socialDao
			    .proximaFotoACompartirEnRedesSociales(a.getIdAnuncio());

		    FotografiaPOJO f = new FotografiaPOJO();
		    f.setIdFotografia(t.getMiddle());
		    f = this.fotografiaDao.busca(f);

		    SocialPOJO s = new SocialPOJO();
		    if (t.getRight() != null) {
			s.setIdSocial(t.getRight());
			s = this.socialDao.busca(s);
		    } else {
			s.setEntidad(SpinnakerSimpleGlobals.ENTIDAD_SOCIAL_ANUNCIO);
			s.setClave(f.getIdFotografia());
			s.setFacebook(0);
			s.setTwitter(0);
			s.setInstagram(0);
			s.setFecAlta(ct.getFec());
			s.setIdUsuarioAlta(ct.getUsu());
			s.setFecModif(ct.getFec());
			s.setIdUsuarioModif(ct.getUsu());
		    }

		    final String idAnuncioCodificado = this.criptografia.codificaParaUrl(a.getIdAnuncio().toString());
		    final StringBuilder sb = new StringBuilder();
		    sb.append(a.getTituloAnuncio()).append(" ").append(this.parametrizacion.getUrlBase())
			    .append("/verVela.action?ida=").append(idAnuncioCodificado);
		    final StringBuilder fUrl = new StringBuilder();
		    fUrl.append(this.parametrizacion.getUrlBase()).append("/picture.action?f=")
			    .append(this.criptografia.codificaParaUrl(
				    Formateador.fill(f.getIdFotografia().toString(), '0', 12, Formateador.IZQUIERDA)));

		    final AnuncioPOJO aa = a;
		    final FotografiaPOJO ff = f;
		    final SocialPOJO ss = s;
		    final AnunciosService as = this.anunciosService;
		    final boolean publicarEnFacebook = this.parametrizacion.isPublicarEnFacebook();
		    final boolean publicarEnTwitter = this.parametrizacion.isPublicarEnTwitter();
		    final boolean publicarEnInstagram = this.parametrizacion.isPublicarEnInstagram();

		    new Thread(new Runnable() {

			@Override
			public void run() {

			    if (publicarEnFacebook) {
				try {
				    final String nombreFoto = ff.getIdFotografia() + "."
					    + ff.getTipo().substring(ff.getTipo().lastIndexOf('/') + 1);
				    ClienteFacebook.post(sb.toString(), nombreFoto, ff.getTipo(), ff.getImagen());
				} catch (final FacebookException e) {
				    log.error("No se pudo publicar en Facebook", e);
				}
			    }

			    if (publicarEnTwitter) {
				try {
				    ClienteTwitter.twit(sb.toString(), aa.getIdAnuncio().toString(), ff.getImagen());
				    ss.setTwitter(ss.getTwitter() + 1);
				} catch (final TwitterException e) {
				    log.error("No se pudo publicar en Twitter", e);
				}
			    }

			    if (publicarEnInstagram) {
				try {
				    ClienteInstagram.post(sb.toString(), aa.getIdAnuncio().toString(), ff.getImagen());
				    ss.setInstagram(ss.getInstagram() + 1);
				} catch (final InstagramException e) {
				    log.error("No se pudo publicar en Instagram", e);
				}
			    }
			    as.actualizaSocial(ss, ct);
			}

		    }).start();
		}
	    }

	    return true;
	} catch (final ServicioException e) {
	    log.error("", e);
	    return false;
	} finally {
	    if (log.isDebugEnabled()) {
		log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
	    }
	}
    }

    @Override
    public void actualizaSocial(final SocialPOJO s, final CambioEnTabla ct) {
	if (log.isDebugEnabled()) {
	    log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
	}
	try {
	    if (s.getIdSocial() != null && this.socialDao.existe(s)) {
		final SocialPOJO old = this.socialDao.busca(s);
		old.setEntidad(s.getEntidad());
		old.setClave(s.getClave());
		old.setFacebook(s.getFacebook());
		old.setTwitter(s.getTwitter());
		old.setInstagram(s.getInstagram());
		old.setIdUsuarioModif(ct.getUsu());
		old.setFecModif(ct.getFec());
		this.socialDao.actualiza(old);
	    } else {
		this.socialDao.crea(s);
	    }
	} finally {
	    if (log.isDebugEnabled()) {
		log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
	    }
	}
    }

    /*
     * (non-Javadoc)
     *
     * @see com.spinnakersimple.modelo.servicios.SpinnakerSimplePublico#saveAndMail(
     * com.spinnakersimple.modelo.entidad.tabla.CompradoresPOJO)
     */
    @Override
    public void saveAndMail(final CompradoresPOJO comprador) {

	log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());

	try {

	    if (this.compradoresDao.buscaVarios(comprador).isEmpty()) {
		final CompradoresPOJO compradorCreado = this.compradoresDao.crea(comprador);

		AnuncioPOJO anuncio = new AnuncioPOJO();
		anuncio.setIdAnuncio(comprador.getIdAnuncio());
		anuncio = this.anuncioDao.busca(anuncio);
		this.anuncioDao.actualizaEstadisticasComentado(null, anuncio.getIdAnuncio());

		UsuarioPOJO usuario = new UsuarioPOJO();
		usuario.setIdUsuario(anuncio.getIdUsuarioAlta());
		usuario = this.usuarioDao.busca(usuario);

		this.consultaVelaMailHelper.enviaCorreoConsultaVela(new Locale(usuario.getLang()),
			LocaleContextHolder.getLocale(), compradorCreado, anuncio, usuario);
	    }
	} catch (final ServicioException e) {
	    log.error(e.getMessage());
	    throw new ServicioErrorException(e);
	} finally {
	    log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
	}
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Integer> obtenEstadisticasEscritorio() {
	final Map<String, Integer> ret = new HashMap<>();
	ret.put(SpinnakerSimpleGlobals.NUM_ANUNCIOS_PENDIENTES, this.anuncioDao.numAnunciosPendientes());
	ret.put(SpinnakerSimpleGlobals.NUM_ANUNCIOS_A_CADUCAR, this.anuncioDao.numAnunciosProximosACaducar());
	ret.put(SpinnakerSimpleGlobals.NUM_ANUNCIOS_PUBLICADOS, this.anuncioDao.numAnunciosVigentes());
	ret.put(SpinnakerSimpleGlobals.NUM_MENSAJES_PENDIENTES, this.compradoresDao.numMensajesPendientes());
	return ret;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<AnuncioPOJO> buscaAnuncios(final Integer idUsuario, final Integer visible, final Integer cadudaEn) {
	final AnuncioPOJO a = new AnuncioPOJO();
	if (idUsuario != null) {
	    a.setIdUsuarioAlta(idUsuario);
	}
	if (visible != null) {
	    a.setVisible(visible);
	}
	if (cadudaEn != null) {
	    final Calendar fecha = Calendar.getInstance();
	    fecha.add(Calendar.DAY_OF_YEAR, cadudaEn);
	    a.setCaduca(fecha);
	}
	return this.anuncioDao.buscaVarios(a);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<CompradoresPOJO> buscaMensajesDeAnunciosNoFinalizados(final Integer revisado) {
	final CompradoresPOJO c = new CompradoresPOJO();
	if (revisado != null) {
	    c.setRevisado(revisado.toString());
	}
	return this.compradoresDao.buscaMensajesDeAnunciosNoFinalizados(c);
    }

    @Override
    public void notificaAnunciosQueCaducanEnNDias(final int daysBefore) {
	if (log.isDebugEnabled()) {
	    log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
	}
	try {
	    final Calendar ahora = Calendar.getInstance();
	    final Map<Integer, RenovacionAnuncioPOJO> rMap = new HashMap<>();
	    final List<AnuncioPOJO> anunciosANotificar = this.anuncioDao.buscaAnunciosQueCaducanEnNDias(daysBefore);
	    final Map<Integer, UsuarioPOJO> usuarios = new HashMap<>();
	    for (final AnuncioPOJO anuncio : anunciosANotificar) {
		try {
		    final RenovacionAnuncioPOJO rn = new RenovacionAnuncioPOJO();
		    rn.setIdAnuncio(anuncio.getIdAnuncio());
		    rn.setIdUsuarioAlta(1);
		    rn.setFecAlta(ahora);
		    rn.setIdUsuarioModif(1);
		    rn.setFecModif(ahora);
		    rn.setIdRenocacionAnuncio(this.renovacionAnuncioDao.crea(rn).getIdRenocacionAnuncio());
		    rMap.put(anuncio.getIdAnuncio(), rn);

		    UsuarioPOJO usuario = new UsuarioPOJO();
		    usuario.setIdUsuario(anuncio.getIdUsuarioAlta());
		    usuario = this.usuarioDao.busca(usuario);
		    usuarios.put(usuario.getIdUsuario(), usuario);
		} catch (final ServicioException e) {
		    log.error(
			    "Esto no deberia pasar porque se está buscando un usuario a partir del campo idUsuarioAlta de un anuncio",
			    e);
		}
	    }
	    this.anuncioMailHelper.notificaCaducidadAnuncioConEnlaceDirecto(anunciosANotificar, usuarios, rMap);
	} finally {
	    if (log.isDebugEnabled()) {
		log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
	    }
	}
    }

    @Override
    public Calendar renuevaAnuncio(final Integer idAnuncio, final CambioEnTabla ct) {
	if (log.isDebugEnabled()) {
	    log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
	}
	try {
	    AnuncioPOJO anuncio = new AnuncioPOJO();
	    anuncio.setIdAnuncio(idAnuncio);
	    anuncio = this.anuncioDao.busca(anuncio);

	    final Calendar fecha = Calendar.getInstance();
	    fecha.set(Calendar.HOUR, 0);
	    fecha.set(Calendar.MINUTE, 0);
	    fecha.set(Calendar.SECOND, 0);
	    fecha.set(Calendar.MILLISECOND, 0);
	    fecha.add(Calendar.DAY_OF_YEAR, SpinnakerSimpleGlobals.DIAS_CADUCIDAD);
	    anuncio.setCaduca(fecha);

	    return this.anunciosService.guardaAnuncio(anuncio, ct).getCaduca();

	} catch (final ServicioException e) {
	    log.warn("No se ha encontrado un anuncio buscando por clave, esto no debería pasar", e);
	    throw new ServicioErrorException(e);
	} finally {
	    if (log.isDebugEnabled()) {
		log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
	    }
	}
    }

    @Override
    public Calendar renuevaAnuncioDesdeUrl(final Integer idRenocacionAnuncio) {
	if (log.isDebugEnabled()) {
	    log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
	}
	try {
	    RenovacionAnuncioPOJO ra = new RenovacionAnuncioPOJO();
	    ra.setIdRenocacionAnuncio(idRenocacionAnuncio);
	    try {
		ra = this.renovacionAnuncioDao.busca(ra);
	    } catch (final EmptyResultDataAccessException e) {
		log.error("No se encuentran los datos de renovación del anuncio");
		throw new ServicioErrorException(
			"AnunciosServiceImpl.renuevaAnuncioDesdeUrl.renovacionAnuncioDao.busca.EmptyResultDataAccessException",
			true, e);
	    }

	    AnuncioPOJO anuncio = new AnuncioPOJO();
	    anuncio.setIdAnuncio(ra.getIdAnuncio());
	    anuncio = this.anuncioDao.busca(anuncio);

	    final CambioEnTabla ct = new CambioEnTabla();
	    ct.setUsu(anuncio.getIdUsuarioAlta());
	    ct.setLocale(LocaleContextHolder.getLocale());
	    ct.setFec(Calendar.getInstance());

	    // Si el anuncio no está publicado no se puede renovar.
	    if (!anuncio.getVisible().equals(SpinnakerSimpleGlobals.ANUNCIO_VISIBLE)) {
		throw new ServicioErrorException("AnunciosServiceImpl.renuevaAnuncioDesdeUrl.noVisable");
	    } else if (anuncio.getVisible().equals(SpinnakerSimpleGlobals.ANUNCIO_VISIBLE)
		    && anuncio.getCaduca().before(ct.getFec())) {
		// Si está publicado, pero está caducado o ha sido dado de baja, no se renueva.
		throw new ServicioErrorException("AnunciosServiceImpl.renuevaAnuncioDesdeUrl.caducado");
	    } else {
		final Calendar fecha = Calendar.getInstance();
		fecha.set(Calendar.HOUR, 0);
		fecha.set(Calendar.MINUTE, 0);
		fecha.set(Calendar.SECOND, 0);
		fecha.set(Calendar.MILLISECOND, 0);
		fecha.add(Calendar.DAY_OF_YEAR, SpinnakerSimpleGlobals.DIAS_CADUCIDAD);
		anuncio.setCaduca(fecha);

		this.renovacionAnuncioDao.borra(ra);
		return this.anunciosService.guardaAnuncio(anuncio, ct).getCaduca();
	    }
	} catch (final ServicioException e) {
	    log.warn("No se ha encontrado un anuncio buscando por clave, esto no debería pasar", e);
	    throw new ServicioErrorException(e);
	} finally {
	    if (log.isDebugEnabled()) {
		log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
	    }
	}
    }

    @Override
    public FotografiaPOJO buscaFotografia(final Integer idFotografia) {
	if (log.isDebugEnabled()) {
	    log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
	}
	try {
	    final FotografiaPOJO f = new FotografiaPOJO();
	    f.setIdFotografia(idFotografia);
	    return this.fotografiaDao.busca(f);
	} catch (final ServicioException e) {
	    log.warn("No se ha encontrado un anuncio buscando por clave, esto no debería pasar", e);
	    throw new ServicioErrorException(e);
	} finally {
	    if (log.isDebugEnabled()) {
		log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
	    }
	}
    }

    @Override
    public String publicaEnRedesSociales(final CambioEnTabla ct) {
	if (log.isDebugEnabled()) {
	    log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
	}
	final StringBuilder ret = new StringBuilder();
	try {
	    final Triple<Integer, Integer, Integer> t = this.socialDao.proximaFotoACompartirEnRedesSociales();
	    AnuncioPOJO a = new AnuncioPOJO();
	    a.setIdAnuncio(t.getLeft());
	    a = this.anuncioDao.busca(a);

	    FotografiaPOJO f = new FotografiaPOJO();
	    f.setIdFotografia(t.getMiddle());
	    f = this.fotografiaDao.busca(f);

	    SocialPOJO s = new SocialPOJO();
	    if (t.getRight() != null) {
		s.setIdSocial(t.getRight());
		s = this.socialDao.busca(s);
	    } else {
		s.setEntidad(SpinnakerSimpleGlobals.ENTIDAD_SOCIAL_ANUNCIO);
		s.setClave(f.getIdFotografia());
		s.setFacebook(0);
		s.setTwitter(0);
		s.setInstagram(0);
		s.setFecAlta(ct.getFec());
		s.setIdUsuarioAlta(ct.getUsu());
		s.setFecModif(ct.getFec());
		s.setIdUsuarioModif(ct.getUsu());
	    }

	    final String idAnuncioCodificado = this.criptografia.codificaParaUrl(a.getIdAnuncio().toString());
	    final StringBuilder sb = new StringBuilder();
	    sb.append(a.getTituloAnuncio()).append(" ").append(this.parametrizacion.getUrlBase())
		    .append("/verVela.action?ida=").append(idAnuncioCodificado);
	    final StringBuilder fUrl = new StringBuilder();
	    fUrl.append(this.parametrizacion.getUrlBase()).append("/picture.action?f=").append(this.criptografia
		    .codificaParaUrl(Formateador.fill(f.getIdFotografia().toString(), '0', 12, Formateador.IZQUIERDA)));

	    final boolean publicarEnTwitter = this.parametrizacion.isPublicarEnTwitter();
	    final boolean publicarEnInstagram = this.parametrizacion.isPublicarEnInstagram();
	    final boolean publicarEnFacebook = this.parametrizacion.isPublicarEnFacebook();

	    ret.append("[");

	    if (publicarEnFacebook) {
		try {
		    final String nombreFoto = f.getIdFotografia() + "."
			    + f.getTipo().substring(f.getTipo().lastIndexOf('/') + 1);
		    ClienteFacebook.post(sb.toString(), nombreFoto, f.getTipo(), f.getImagen());
		    ret.append(" FB ");
		} catch (final FacebookException e) {
		    log.error("No se pudo publicar en Facebook", e);
		}
	    } else {
		ret.append(" -- ");
	    }

	    if (publicarEnTwitter) {
		try {
		    ClienteTwitter.twit(sb.toString(), a.getIdAnuncio().toString(), f.getImagen());
		    s.setTwitter(s.getTwitter() + 1);
		    ret.append(" TW ");
		} catch (final TwitterException e) {
		    log.error("No se pudo publicar en Twitter", e);
		}
	    } else {
		ret.append(" -- ");
	    }

	    if (publicarEnInstagram) {
		try {
		    ClienteInstagram.post(sb.toString(), a.getIdAnuncio().toString(), f.getImagen());
		    s.setInstagram(s.getInstagram() + 1);
		    ret.append(" IN ");
		} catch (final InstagramException e) {
		    log.error("No se pudo publicar en Instagram", e);
		}
	    } else {
		ret.append(" -- ");
	    }
	    ret.append("] - ").append(a.getIdAnuncio()).append(" - ").append(f.getIdFotografia()).append(" - ")
		    .append(sb);
	    this.anunciosService.actualizaSocial(s, ct);

	} catch (final ServicioException e) {
	    log.warn("No se ha encontrado un anuncio buscando por clave, esto no debería pasar", e);
	    throw new ServicioErrorException(e);
	} finally {
	    if (log.isDebugEnabled()) {
		log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
	    }
	}
	return ret.toString();
    }

    @Override
    public List<TipoVelaPOJO> buscaTiposDeVelaPorTipoBarco(final Integer idTipoBarco) {
	return this.tipoVelaDao.buscaTiposDeVelaPorTipoBarco(idTipoBarco);
    }

    @Override
    public List<Pair<String, Integer>> numeroAnunciosPorTipoBarco() {
	return this.anuncioDao.numeroAnunciosPorTipoBarco();
    }

    @Override
    public List<Pair<String, Integer>> numeroAnunciosPorTipoClase() {
	return this.anuncioDao.numeroAnunciosPorTipoClase();
    }

    @Override
    public List<AnuncioCompleto> buscaAnunciosDestacados() {
	log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
	try {
	    final List<AnuncioCompleto> ret = new ArrayList<>();
	    final List<AnuncioPOJO> listaAnunciosPojo = this.anuncioDao.buscaAnunciosDestacados();
	    final List<String> ids = new ArrayList<>();
	    for (final AnuncioPOJO a : listaAnunciosPojo) {

		final AnuncioCompleto anuncioCompleto = new AnuncioCompleto();
		anuncioCompleto.setAnuncio(a);

		final TipoVelaPOJO tipoVela = new TipoVelaPOJO();
		tipoVela.setIdTipoVela(anuncioCompleto.getAnuncio().getTipoVela());
		anuncioCompleto.setTipoVela(this.tipoVelaDao.busca(tipoVela));

		final UsuarioPOJO u = new UsuarioPOJO();
		u.setIdUsuario(a.getIdUsuarioAlta());
		anuncioCompleto.setUsuario(this.usuarioDao.busca(u));

		final FotografiaPOJO fotografiaPojo = new FotografiaPOJO();
		fotografiaPojo.setIdAnuncio(a.getIdAnuncio());
		anuncioCompleto.setFotos(this.fotografiaDao.buscaVarios(fotografiaPojo));

		ids.add(anuncioCompleto.getAnuncio().getIdAnuncio().toString());
		ret.add(anuncioCompleto);
	    }
	    this.anuncioDao.actualizaAnunciosDestacados(ids);
	    return ret;
	} catch (final ServicioException e) {
	    log.error("", e);
	    throw new ServicioErrorException(e);
	} finally {
	    log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
	}
    }
}
