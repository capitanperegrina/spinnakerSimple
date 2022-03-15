package com.capitanperegrina.common.servicios.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.capitanperegrina.common.basebeans.CambioEnTabla;
import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.exceptions.ServicioException;
import com.capitanperegrina.common.modelo.dao.ParamsDao;
import com.capitanperegrina.common.modelo.entidad.tabla.ParamsPOJO;
import com.capitanperegrina.common.servicios.ParamsService;
import com.capitanperegrina.common.utils.StackTraceUtil;

/**
 * The Class ParamsServiceImpl.
 */
@Service
@Transactional
public class ParamsServiceImpl implements ParamsService {

	/** The log. */
	static Logger log = Logger.getLogger(ParamsServiceImpl.class);
	
	/** The params dao. */
	@Autowired
	ParamsDao paramsDao;

	/* (non-Javadoc)
	 * @see com.capitanperegrina.common.servicios.ParamsService#leeParametroBean(java.lang.String)
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public ParamsPOJO leeParametroBean(String key) {
		ParamsPOJO p = new ParamsPOJO();
		p.getPk().setIdParam(key);
		try {
			return this.paramsDao.busca(p);	
		} catch(ServicioException e) {
			log.warn("",e);
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.capitanperegrina.common.servicios.ParamsService#leeTodos()
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<ParamsPOJO> leeTodos() {
		return this.paramsDao.buscaTodos();
	}
	
	/* (non-Javadoc)
	 * @see com.capitanperegrina.common.servicios.ParamsService#leeParametro(java.lang.String)
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public String leeParametro(String key) {
		ParamsPOJO p = new ParamsPOJO();
		p.getPk().setIdParam(key);
		try {
			return this.paramsDao.busca(p).getValor();	
		} catch(ServicioException e) {
			log.warn("",e);
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.capitanperegrina.common.servicios.ParamsService#actualizaParametro(com.capitanperegrina.common.modelo.entidad.tabla.ParamsPOJO, com.capitanperegrina.common.basebeans.CambioEnTabla)
	 */
	@Override
	public boolean actualizaParametro(ParamsPOJO p, CambioEnTabla ct) {
		if ( log.isDebugEnabled() ) {
			log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
		}
		try {
			final ParamsPOJO old = this.paramsDao.busca(p);
			// Modificacion
			old.setValor(p.getValor());
			old.setFecModif(ct.getFec());
			old.setIdUsuarioModif(ct.getUsu());
			this.paramsDao.historifica(old, ct);
			this.paramsDao.actualiza(old);
			return true;
		} catch (final ServicioException e) {
			log.warn("",e);
			return false;
		} finally {
			if ( log.isDebugEnabled() ) {
				log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
			}
		}
	}
}
