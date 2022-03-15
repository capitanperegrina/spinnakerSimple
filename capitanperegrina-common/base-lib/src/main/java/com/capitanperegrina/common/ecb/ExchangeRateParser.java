package com.capitanperegrina.common.ecb;

import java.math.BigDecimal;
import java.util.Map;

/**
 * The Interface ExchangeRateParser.
 *
 * @author Andrej Kuročenko <kurochenko@mail.muni.cz>
 */
public interface ExchangeRateParser {

	/**
	 * Parses the actual.
	 *
	 * @return the exchange rate DTO
	 */
	public Map<String, BigDecimal> parseActual();
}