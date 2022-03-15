package com.capitanperegrina.usuarios.servicios.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitanperegrina.common.basebeans.CambioEnTabla;
import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.config.DefaultParametersDef;
import com.capitanperegrina.common.exceptions.ServicioErrorException;
import com.capitanperegrina.common.exceptions.ServicioException;
import com.capitanperegrina.common.modelo.dao.ParamsDao;
import com.capitanperegrina.common.modelo.entidad.tabla.ParamsPOJO;
import com.capitanperegrina.common.net.mail.Emailer;
import com.capitanperegrina.common.seguridad.Criptografia;
import com.capitanperegrina.common.seguridad.GeneradorClaves;
import com.capitanperegrina.common.seguridad.MD5;
import com.capitanperegrina.common.utils.StackTraceUtil;
import com.capitanperegrina.usuarios.modelo.dao.impl.tabla.UsuarioDao;
import com.capitanperegrina.usuarios.modelo.dao.impl.tabla.UsuarioParamsDao;
import com.capitanperegrina.usuarios.modelo.entidad.tabla.UsuarioPOJO;
import com.capitanperegrina.usuarios.modelo.entidad.tabla.UsuarioParamsPOJO;
import com.capitanperegrina.usuarios.servicios.UsuarioSrv;

@Service
@Transactional
public class UsuarioSrvBean implements UsuarioSrv {
	static Logger log = Logger.getLogger(UsuarioSrvBean.class);

	@Autowired
	MessageSource messageSource;

	@Autowired
	ParamsDao paramsDao;

	@Autowired
	UsuarioDao usuarioDao;

	@Autowired
	UsuarioParamsDao usuarioParamsDao;

	@Autowired
	Emailer emailer;

	/** The criptografia. */
	@Autowired
	Criptografia criptografia;

	@Override
	public UsuarioPOJO creaUsuario(UsuarioPOJO u) {
		log.debug(">> UsuarioSrvBean.creaUsuario()");
		try {
			final UsuarioPOJO uMail = new UsuarioPOJO();
			uMail.setMail(u.getMail().toLowerCase());
			if (!this.usuarioDao.buscaVarios(uMail).isEmpty()) {
				final Object[] params = { u.getMail() };
				throw new ServicioErrorException("usuarios", "UsuarioSrvBean.creaUsuario.yaExiste", params);
			}

			u.setPass(MD5.getMD5(u.getPass()));
			this.usuarioDao.crea(u);

			ParamsPOJO paramUrlBase = new ParamsPOJO();
			paramUrlBase.getPk().setIdParam(DefaultParametersDef.APP_URL_BASE);
			paramUrlBase = this.paramsDao.busca(paramUrlBase);

			ParamsPOJO paramRemitente = new ParamsPOJO();
			paramRemitente.getPk().setIdParam(DefaultParametersDef.EMAIL_NO_REPLY);
			paramRemitente = this.paramsDao.busca(paramRemitente);

			final String asunto = this.messageSource.getMessage("register.mail.subject", null,
					LocaleContextHolder.getLocale());

			final StringBuilder t = new StringBuilder();
			t.append("<p>" + this.messageSource.getMessage("register.mail.1", new Object[] { u.getNick() },
					LocaleContextHolder.getLocale()) + "</p>");
			t.append("<p>"
					+ this.messageSource.getMessage("register.mail.2", new Object[] {}, LocaleContextHolder.getLocale())
					+ "</p>");
			t.append("<p>" + this.messageSource.getMessage("register.mail.3",
					new Object[] { paramUrlBase.getValor() + "/loginForm.action" }, LocaleContextHolder.getLocale())
					+ "</p>");
			t.append("<p>" + this.messageSource.getMessage("register.mail.4", new Object[] { u.getMail() },
					LocaleContextHolder.getLocale()) + "</p>");
			t.append("<p>" + this.messageSource.getMessage("register.mail.5",
					new Object[] { paramUrlBase.getValor() + "/recoverPassForm.action" },
					LocaleContextHolder.getLocale()) + "</p>");

			this.emailer.sendEmail(paramRemitente.getValor(), u.getMail(), asunto, t.toString(), true, null, null);

			return this.usuarioDao.buscaPorMail(u.getMail());

		} catch (final ServicioException e) {
			log.error("", e);
			throw new ServicioErrorException(e.getBundle(), e.getMessage(), e.getArgs(), e);
		} finally {
			log.debug("<< UsuarioSrvBean.creaUsuario()");
		}
	}

	@Override
	public UsuarioPOJO login(String login, String pass, CambioEnTabla ct) throws ServicioException {
		log.debug("UsuarioSrvBean.login()");

		try {
			final UsuarioPOJO u = this.usuarioDao.buscaPorMail(login);
			if (!MD5.getMD5(pass).equals(u.getPass())) {
	        	log.debug( "Password incorrecta: incrementamos el número de fallos de login." );
				this.usuarioDao.incrementaFallos(u);
				final Object[] params = { login };
				throw new ServicioException("usuarios", "UsuarioSrvBean.login.passwordIncorrecta", params);
			}
			this.usuarioDao.reseteaFallos(u);
			return u;
		} catch (final EmptyResultDataAccessException e) {
        	log.debug( "No se ha encontrado el usuario la información buscada." );
        	Object[] params = { login };
        	throw new ServicioException( "usuarios", "UsuarioSrvBean.login.passwordIncorrecta", params );
		}
	}

	@Override
	public UsuarioPOJO buscaPorMail(String mail) {
		if (log.isDebugEnabled()) {
			log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
		}

		try {
			return this.usuarioDao.buscaPorMail(mail);
		} catch (final EmptyResultDataAccessException e) {
        	log.debug( "No se ha encontrado la información buscada." );
        	Object[] params = { mail };
        	throw new ServicioErrorException( "usuarios", "UsuarioSrvBean.buscaPorMail.EmptyResultDataAccessException", params );
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}

	@Override
	public boolean existePeticionResetPassActiva(Integer idUsuario, String codigoRecuperacion) {
		log.debug(">> UsuarioSrvBean.buscaPorMailCodigoRecuperacion()");

		try {
			final UsuarioParamsPOJO up = new UsuarioParamsPOJO();
			up.getPk().setIdUsuario(idUsuario);
			up.getPk().setIdParam(DefaultParametersDef.RECOVER_PASS_CODE);
			up.setValor(codigoRecuperacion);
			return this.usuarioParamsDao.buscaVarios(up).size() == 1;
		} finally {
			log.debug("<< UsuarioSrvBean.buscaPorMailCodigoRecuperacion()");
		}
	}

	@Override
	public UsuarioPOJO recoverPassInit(String mail, CambioEnTabla ct) {
		log.debug("UsuarioSrvBean.recoverPassInit()");

		UsuarioPOJO u;

		// Generamos código de recuperación y lo guardamos en bbdd
		try {
			u = this.usuarioDao.buscaPorMail(mail);
		} catch (final EmptyResultDataAccessException e) {
        	log.debug( "No se ha encontrado la información buscada." );
        	Object[] params = { mail };
        	throw new ServicioErrorException( "usuarios", "UsuarioSrvBean.recoverPassInit.EmptyResultDataAccessException", params );
		}

		UsuarioParamsPOJO up = new UsuarioParamsPOJO();
		up.getPk().setIdParam(DefaultParametersDef.RECOVER_PASS_CODE);
		up.getPk().setIdUsuario(u.getPk().getIdUsuario());

		try {
			up = this.usuarioParamsDao.busca(up);
			up.setValor(GeneradorClaves.generaCodigoURL(100));
			up.setIdUsuarioModif(ct.getUsu());
			up.setFecModif(ct.getFec());

			this.usuarioParamsDao.actualiza(up);
		} catch (final ServicioException e) {
			log.debug("", e);

			up.setVisible(DefaultGlobalNames.NO);
			up.setValor(GeneradorClaves.generaCodigoURL(100));
			up.setTipoJava(DefaultGlobalNames.TIPO_JAVA_STRING);
			up.setTipoJs(DefaultGlobalNames.TIPO_JAVASCRIPT_STRING);

			up.setIdUsuarioAlta(ct.getUsu());
			up.setFecAlta(ct.getFec());
			up.setIdUsuarioModif(ct.getUsu());
			up.setFecModif(ct.getFec());

			this.usuarioParamsDao.crea(up);
		}

		try {
			// Enviamos correo electrónico.
			ParamsPOJO paramUrlBase = new ParamsPOJO();
			paramUrlBase.getPk().setIdParam(DefaultParametersDef.APP_URL_BASE);
			paramUrlBase = this.paramsDao.busca(paramUrlBase);

			ParamsPOJO paramRemitente = new ParamsPOJO();
			paramRemitente.getPk().setIdParam(DefaultParametersDef.EMAIL_NO_REPLY);
			paramRemitente = this.paramsDao.busca(paramRemitente);

			final String idUsuario = this.criptografia.encriptar(u.getPk().getIdUsuario().toString());

			final StringBuilder t = new StringBuilder();
			t.append("<p>" + this.messageSource.getMessage("recoverPassForm.mail.1", new Object[] { u.getNick() },
					ct.getLocale()) + "</p>");
			t.append("<p style=\"margin-left: 1em\">"
					+ this.messageSource.getMessage("recoverPassForm.mail.2", null, ct.getLocale()) + "</p>");
			t.append("<p style=\"margin-left: 1em\">"
					+ this.messageSource.getMessage("recoverPassForm.mail.3", null, ct.getLocale()) + "</p>");
			t.append("<p style=\"margin-left: 2em\">" + paramUrlBase.getValor() + "/resetPassForm.action?u=" + idUsuario
					+ "&c=" + up.getValor() + "</p>");

			final String asunto = this.messageSource.getMessage("recoverPassForm.mail.subject", null, ct.getLocale());

			this.emailer.sendEmail(paramRemitente.getValor(), u.getMail(), asunto, t.toString(), true, null, null);
		} catch (final ServicioException e) {
			log.error("", e);
			throw new ServicioErrorException(e.getBundle(), e.getMessage(), e.getArgs(), e);
		}

		return u;
	}

	@Override
	public UsuarioPOJO recoverPassExec(Integer idUsuario, String codigoRecuperacion, String newPass, CambioEnTabla ct)
			throws ServicioException {
		log.debug("UsuarioSrvBean.recoverPassInit()");

		UsuarioPOJO u = new UsuarioPOJO();
		u.getPk().setIdUsuario(idUsuario);
		u = this.usuarioDao.busca(u);

		UsuarioParamsPOJO up = new UsuarioParamsPOJO();
		up.getPk().setIdParam(DefaultParametersDef.RECOVER_PASS_CODE);
		up.getPk().setIdUsuario(u.getPk().getIdUsuario());

		try {
			up = this.usuarioParamsDao.busca(up);
		} catch (final ServicioException e) {
			log.error("", e);
			throw new ServicioErrorException(e.getBundle(),
					"usuarioParamsDao.notFound." + DefaultParametersDef.RECOVER_PASS_CODE, e.getArgs(), e);
		}

		if (up.getValor().equals(codigoRecuperacion)) {
			u.setPass(MD5.getMD5(newPass));
			u.setFallosLogin(0);
			u.setIdUsuarioModif(u.getPk().getIdUsuario());
			u.setFecModif(ct.getFec());

			this.usuarioDao.historifica(u, ct);
			this.usuarioDao.actualiza(u);
			this.usuarioParamsDao.borra(up);

			return u;
		} else {
			this.usuarioParamsDao.borra(up);
			throw new ServicioException("", "resetPassForm.error.codigoRecuperacion");
		}
	}

	@Override
	public UsuarioPOJO actualiza(UsuarioPOJO usuario, CambioEnTabla ct) {

		final UsuarioPOJO u = this.usuarioDao.busca(usuario);

		u.setNick(usuario.getNick());
		u.setMail(usuario.getMail());
		u.setIp(ct.getIp());
		u.setFecModif(ct.getFec());
		u.setIdUsuarioModif(ct.getUsu());

		this.usuarioDao.historifica(u, ct);
		this.usuarioDao.actualiza(u);

		return u;
	}
}