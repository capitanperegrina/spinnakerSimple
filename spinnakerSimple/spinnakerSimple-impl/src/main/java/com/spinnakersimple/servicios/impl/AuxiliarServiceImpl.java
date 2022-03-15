package com.spinnakersimple.servicios.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.config.DefaultParameters;
import com.capitanperegrina.common.utils.StackTraceUtil;
import com.spinnakersimple.modelo.dao.SliderDao;
import com.spinnakersimple.modelo.dao.TextoDao;
import com.spinnakersimple.modelo.entidad.SliderPOJO;
import com.spinnakersimple.modelo.util.SpinnakerSimpleGlobals;
import com.spinnakersimple.servicios.AuxiliarService;

/**
 * The Class AuxiliarServiceImpl.
 */
@Service
public class AuxiliarServiceImpl implements AuxiliarService {

	/** The log. */
	static Logger log = Logger.getLogger(AuxiliarServiceImpl.class);

	@Autowired
	SliderDao sliderDao;

	@Autowired
	TextoDao textoDao;

	@Autowired
	DefaultParameters params;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.spinnakersimple.modelo.servicios.SpinnakerSimplePublico#
	 * leeImagenCabecera(java.lang.String)
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<SliderPOJO> leeImagenCabecera(String tipoImagen) {
		final SliderPOJO filtro = new SliderPOJO();
		filtro.setDondeHome(tipoImagen);
		return this.sliderDao.buscaVarios(filtro);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.spinnakersimple.modelo.servicios.SpinnakerSimplePublico#leeParametro(
	 * java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public String leeParametro(String clave, String lang) {
		log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_INI + StackTraceUtil.getCallerInfo());
		try {
			return this.textoDao.buscaPorClaveLang(clave, lang).getValor();
		} finally {
			log.debug(DefaultGlobalNames.PREFIJO_SERVICIO_FIN + StackTraceUtil.getCallerInfo());
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public double leeTipoDeCambio(String divisa) {
		double tipoDeCambio = 1d;
		if (!divisa.equals(SpinnakerSimpleGlobals.DIVISA_EUR)) {
			tipoDeCambio = this.params.getEcbExchangeRates().get(divisa).doubleValue();
		}
		return tipoDeCambio;
	}
}
