package com.capitanperegrina.common.config;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.capitanperegrina.common.ecb.EcbExchangeRateParser;
import com.capitanperegrina.common.ecb.ExchangeRateParser;

/**
 * The Class DefaultParametersImpl.
 */
public class DefaultParametersImpl implements DefaultParameters {
	static Logger log = Logger.getLogger(DefaultParametersImpl.class);

	/** The parametros. */
	public Map<String, String> parametros = new HashMap<String, String>();
	public Map<String, BigDecimal> ecbExchangeRates;
	public Calendar ultimoAccesoBce;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.capitanperegrina.common.config.DefaultParameters#getParameter(java.
	 * lang.String)
	 */
	@Override
	public String getParameter(String idParam) {
		return this.parametros.get(idParam);
	}

	/**
	 * Gets the parametros.
	 *
	 * @return the parametros
	 */
	public Map<String, String> getParametros() {
		return this.parametros;
	}

	/**
	 * Sets the parametros.
	 *
	 * @param parametros
	 *            the parametros
	 */
	public void setParametros(Map<String, String> parametros) {
		this.parametros = parametros;
	}

	@Override
	public Map<String, BigDecimal> getEcbExchangeRates() {
		final Calendar ahora = Calendar.getInstance();
		if (this.ecbExchangeRates == null || ahora.getTimeInMillis() - this.ultimoAccesoBce.getTimeInMillis() > 60000) {
			final ExchangeRateParser ecbExcangeRateParser = new EcbExchangeRateParser();
			this.ecbExchangeRates = ecbExcangeRateParser.parseActual();
			this.ultimoAccesoBce = ahora;
		}
		return this.ecbExchangeRates;

	}

	public void setEcbExchangeRates(Map<String, BigDecimal> ecbExchangeRates) {
		this.ecbExchangeRates = ecbExchangeRates;
	}
}
