package com.capitanperegrina.common.config;

import java.math.BigDecimal;
import java.util.Map;

/**
 * The Interface DefaultParameters.
 */
public interface DefaultParameters {

	/**
	 * Gets the parameter.
	 *
	 * @param param
	 *            the param
	 * @return the parameter
	 */
	public String getParameter(String param);

	/**
	 * Gets the ecb exchange rates.
	 *
	 * @return the ecb exchange rates
	 */
	public Map<String, BigDecimal> getEcbExchangeRates();
}
