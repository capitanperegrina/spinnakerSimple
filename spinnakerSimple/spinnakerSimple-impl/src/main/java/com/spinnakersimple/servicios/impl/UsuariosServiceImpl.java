package com.spinnakersimple.servicios.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitanperegrina.common.basebeans.CambioEnTabla;
import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.config.DefaultParameters;
import com.capitanperegrina.common.exceptions.ServicioErrorException;
import com.capitanperegrina.common.exceptions.ServicioException;
import com.capitanperegrina.common.seguridad.MD5;
import com.capitanperegrina.common.utils.Fecha;
import com.capitanperegrina.common.utils.StackTraceUtil;
import com.spinnakersimple.helpers.AnuncioMailHelper;
import com.spinnakersimple.helpers.ConsultaVelaMailHelper;
import com.spinnakersimple.helpers.UsuarioMailHelper;
import com.spinnakersimple.helpers.adapters.AdapterToDtoHelper;
import com.spinnakersimple.helpers.adapters.AdapterToPojoHelper;
import com.spinnakersimple.modelo.dao.AnuncioDao;
import com.spinnakersimple.modelo.dao.CompradoresDao;
import com.spinnakersimple.modelo.dao.FotografiaDao;
import com.spinnakersimple.modelo.dao.SliderDao;
import com.spinnakersimple.modelo.dao.TextoDao;
import com.spinnakersimple.modelo.dao.TipoVelaDao;
import com.spinnakersimple.modelo.dao.UsuarioDao;
import com.spinnakersimple.modelo.entidad.UsuarioPOJO;
import com.spinnakersimple.modelo.excepciones.AltaUsuarioDuplicadaException;
import com.spinnakersimple.modelo.vista.UsuarioCompletoView;
import com.spinnakersimple.servicios.UsuariosService;

@Service("usuarioService")
public class UsuariosServiceImpl implements UsuariosService {

	/** The log. */
	static Logger log = Logger.getLogger(UsuariosServiceImpl.class);

	@Autowired
	UsuariosService usuariosService;

	/** The consulta vela mail helper. */
	@Autowired
	ConsultaVelaMailHelper consultaVelaMailHelper;

	/** The consulta vela mail helper. */
	@Autowired
	UsuarioMailHelper usuarioMailHelper;

	/** The consulta vela mail helper. */
	@Autowired
	AnuncioMailHelper altaAnuncioMailHelper;

	@Autowired
	AdapterToPojoHelper adapterToPojoHelper;

	@Autowired
	AdapterToDtoHelper adapterToDtoHelper;

	@Autowired
	DefaultParameters params;

	/** The slider dao. */
	@Autowired
	SliderDao sliderDao;

	/** The tipo vela dao. */
	@Autowired
	TipoVelaDao tipoVelaDao;

	/** The anuncio dao. */
	@Autowired
	AnuncioDao anuncioDao;

	/** The usuario dao. */
	@Autowired
	UsuarioDao usuarioDao;

	/** The texto dao. */
	@Autowired
	TextoDao textoDao;

	/** The fotografia dao. */
	@Autowired
	FotografiaDao fotografiaDao;

	/** The compradores dao. */
	@Autowired
	CompradoresDao compradoresDao;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.spinnakersimple.modelo.servicios.SpinnakerSimplePublico#altaUsuario(
	 * com.spinnakersimple.modelo.dto.form.VenderVelaFormDTO,
	 * com.capitanperegrina.common.basebeans.CambioEnTabla)
	 */
	@Override
	public UsuarioPOJO actualizaPerfil(final UsuarioPOJO u, final CambioEnTabla ct) {
		log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
		try {
			// Leo de bbdd el usuario buscandolo por mail.
			final UsuarioPOJO old = this.usuarioDao.buscaPorMail(u.getMail());

			// Reemplazo los valores que puede ser reemplazados.
			old.setApellidos(u.getApellidos());
			old.setCodPostal(u.getCodPostal());
			old.setDireccion1(u.getDireccion1());
			old.setDireccion2(u.getDireccion2());
			old.setMail(u.getMail());
			old.setNombre(u.getNombre());
			old.setPais(u.getPais());
			old.setDivisa(u.getDivisa());
			old.setProvincia(u.getProvincia());
			old.setMovil(u.getMovil());

			// Historifico y guardo.
			this.usuarioDao.historifica(old, ct);
			this.usuarioDao.actualiza(old);

			// Devuelve el objeto modificado.
			return old;
		} catch (final ServicioException e) {
			log.error(e);
			throw e.toServicioErrorException();
		} finally {
			log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.spinnakersimple.modelo.servicios.SpinnakerSimplePublico#altaUsuario(
	 * com.spinnakersimple.modelo.dto.form.VenderVelaFormDTO,
	 * com.capitanperegrina.common.basebeans.CambioEnTabla)
	 */
	@Override
	public UsuarioPOJO altaUsuario(final UsuarioPOJO u, final CambioEnTabla ct) {
		log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
		try {
			if (!this.usuarioDao.existePorMail(u.getMail())) {
				final String pass = u.getPass();
				u.setPass(MD5.getMD5(pass));
				final UsuarioPOJO usuario = this.usuariosService.guardaUsuario(u, ct);
				if (ct.getUsu() == null) {
					ct.setUsu(u.getIdUsuario());
				}
				this.usuarioMailHelper.enviaCorreosAltaUsuario(usuario);
				return usuario;
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
	 * @see
	 * com.spinnakersimple.modelo.servicios.SpinnakerSimplePublico#guardaUsuario
	 * (com.spinnakersimple.modelo.entidad.tabla.UsuarioPOJO,
	 * com.capitanperegrina.common.basebeans.CambioEnTabla)
	 */
	@Override
	public UsuarioPOJO guardaUsuario(final UsuarioPOJO u, final CambioEnTabla ct) {
		log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
		UsuarioPOJO ret;
		try {
			final UsuarioPOJO old = this.usuarioDao.buscaPorMail(u.getMail());
			// Modificacion
			old.setNombre(u.getNombre());
			old.setApellidos(u.getApellidos());
			old.setMail(u.getMail());
			old.setMovil(u.getMovil());
			old.setDireccion1(u.getDireccion1());
			old.setDireccion2(u.getDireccion2());
			old.setProvincia(u.getProvincia());
			old.setPais(u.getPais());
			old.setDivisa(u.getDivisa());
			old.setCodPostal(u.getCodPostal());
			old.setAdmin(u.getAdmin());
			old.setPass(u.getPass());
			old.setLang(u.getLang());
			old.setIp(u.getIp());
			old.setFallosLogin(u.getFallosLogin());
			old.setFecModif(ct.getFec());
			old.setIdUsuarioModif(ct.getUsu());
			this.usuarioDao.historifica(old, ct);
			this.usuarioDao.actualiza(old);
			ret = old;
		} catch (final ServicioException e) {
			// Nuevo
			log.trace(e);
			log.info("No se encontró usuario => nuevo.");
			u.setFallosLogin(0);
			u.setFecAlta(ct.getFec());
			u.setIdUsuarioAlta(ct.getUsu());
			u.setFecModif(ct.getFec());
			u.setIdUsuarioModif(ct.getUsu());
			ret = this.usuarioDao.crea(u);
		} finally {
			log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.spinnakersimple.modelo.servicios.SpinnakerSimplePublico#login(java.
	 * lang.String, java.lang.String,
	 * com.capitanperegrina.common.basebeans.CambioEnTabla)
	 */
	@Override
	public UsuarioPOJO login(final String login, final String pass, final CambioEnTabla ct) {
		log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
		try {
			final UsuarioPOJO u = this.usuarioDao.buscaPorMail(login);
			ct.setUsu(u.getIdUsuario());
			if (MD5.getMD5(pass).equals(u.getPass())) {
				// login ok
				u.setFallosLogin(0);
				u.setIdUsuarioModif(u.getIdUsuario());
				u.setFecModif(ct.getFec());
				this.usuarioDao.historifica(u, ct);
				this.usuarioDao.actualiza(u);
				return u;
			} else {
				// login err
				u.setFallosLogin(u.getFallosLogin() + 1);
				u.setFecModif(ct.getFec());
				this.usuarioDao.historifica(u, ct);
				this.usuarioDao.actualiza(u);
				return null;
			}
		} catch (final ServicioException e) {
			// usuario no encontrado
			log.info(e);
			return null;
		} finally {
			log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.spinnakersimple.modelo.servicios.SpinnakerSimplePublico#resetPass(
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * com.capitanperegrina.common.basebeans.CambioEnTabla)
	 */
	@Override
	public UsuarioPOJO resetPass(final String id, final String pw, final String phm, final String pass, final CambioEnTabla ct) {
		log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
		try {
			UsuarioPOJO u = new UsuarioPOJO();
			u.setIdUsuario(Integer.parseInt(id));
			u = this.usuarioDao.busca(u);
			final boolean ok = u.getPass().equals(pw)
					&& Fecha.calendar2DateHourString(u.getFecModif(), '/', ':').equals(phm);
			if (ok) {
				u.setPass(MD5.getMD5(pass));
			}
			ct.setUsu(u.getIdUsuario());
			u.setFecModif(ct.getFec());
			u.setIdUsuario(u.getIdUsuario());
			this.usuarioDao.historifica(u, ct);
			this.usuarioDao.actualiza(u);
			if (ok) {
				return u;
			} else {
				throw new ServicioErrorException("SpinnakerSimplePublicoImpl.resetPassForm.urlInvalida");
			}
		} catch (final ServicioException e) {
			log.warn("", e);
			throw new ServicioErrorException("SpinnakerSimplePublicoImpl.resetPassForm.urlInvalida", e);
		} finally {
			log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.spinnakersimple.modelo.servicios.SpinnakerSimplePublico#resetPassForm
	 * (java.lang.String, java.lang.String, java.lang.String,
	 * com.capitanperegrina.common.basebeans.CambioEnTabla)
	 */
	@Override
	public UsuarioPOJO resetPassForm(final String id, final String pw, final String phm, final CambioEnTabla ct) {
		log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
		try {
			UsuarioPOJO u = new UsuarioPOJO();
			u.setIdUsuario(Integer.parseInt(id));
			u = this.usuarioDao.busca(u);
			final boolean ok = u.getPass().equals(pw)
					&& Fecha.calendar2DateHourString(u.getFecModif(), '/', ':').equals(phm);
			ct.setUsu(u.getIdUsuario());
			u.setFecModif(ct.getFec());
			u.setIdUsuario(u.getIdUsuario());
			this.usuarioDao.historifica(u, ct);
			this.usuarioDao.actualiza(u);
			if (ok) {
				return u;
			} else {
				throw new ServicioErrorException("SpinnakerSimplePublicoImpl.resetPassForm.urlInvalida");
			}
		} catch (final ServicioException e) {
			log.warn("", e);
			throw new ServicioErrorException("SpinnakerSimplePublicoImpl.resetPassForm.urlInvalida", e);
		} finally {
			log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.spinnakersimple.modelo.servicios.SpinnakerSimplePublico#
	 * resetPassRequest(java.lang.String,
	 * com.capitanperegrina.common.basebeans.CambioEnTabla)
	 */
	@Override
	public void resetPassRequest(final String mail, final CambioEnTabla ct) {
		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
		}
		try {
			final UsuarioPOJO u = this.usuarioDao.buscaPorMail(mail);
			this.usuarioMailHelper.enviaCorreoResetPass(u);
		} catch (final ServicioException e) {
			log.trace("", e);
		} finally {
			log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
		}
	}

//	@Override
//	public UsuarioCompletoView buscaUsuarioCompleto( Integer idUsuario ) {
//		if (log.isDebugEnabled()) {
//			log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
//		}
//		try {
//			UsuarioPOJO u = new UsuarioPOJO();
//			u.setIdUsuario(idUsuario);
//			// return this.usuarioDao.buscaUsuarioCompleto(u);
//			return this.usuarioDao.buscaUsuario(u);
//		} finally {
//			log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
//		}
//	}

	@Override
	public UsuarioPOJO buscaUsuario( final Integer idUsuario ) {
		try {
			final UsuarioPOJO u = new UsuarioPOJO();
			u.setIdUsuario(idUsuario);
			return this.usuarioDao.busca(u);
		} catch (final ServicioException e) {
			log.warn("", e);
			throw new ServicioErrorException("SpinnakerSimplePublicoImpl.buscaUsuario.noEncontrado", e);
		}
	}

	@Override
	public List<UsuarioCompletoView> buscaUsuariosCompleto() {
		return this.usuarioDao.buscaUsuariosCompleto();
	}

	@Override
	public void eliminaCompletamenteUnUsuario( final Integer idUsuario ) {
		this.compradoresDao.historificaYEliminaPorUsuario(idUsuario);
		this.fotografiaDao.historificaYEliminaPorUsuario(idUsuario);
		this.anuncioDao.historificaYEliminaPorUsuario(idUsuario);
		this.usuarioDao.historificaYEliminaPorUsuario(idUsuario);
	}

	@Override
	public void eliminaCompletamenteUnUsuario( final String mail ) {
		try {
			final Integer idUsuario = this.usuarioDao.buscaPorMail(mail).getIdUsuario();
			eliminaCompletamenteUnUsuario(idUsuario);
		} catch ( final ServicioException e ) {
			log.error("No se ha encontrado el usuario que se deseaba eliminar buscando por el mail " + mail );
		}
	}

	@Override
	public boolean existePorMail( final String mail ) {
		return this.usuarioDao.existePorMail(mail);
	}
}
