package com.capitanperegrina.common.ecb;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Andrej Kuročenko <kurochenko@mail.muni.cz>
 */
public class ExchangeRateDTO {

	Map<String, Map<String, BigDecimal>> rates;

	public Map<String, Map<String, BigDecimal>> getRates() {
		return this.rates;
	}

	public void setRates(Map<String, Map<String, BigDecimal>> rates) {
		this.rates = rates;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		final ExchangeRateDTO that = (ExchangeRateDTO) o;

		if (this.rates != null ? !this.rates.equals(that.rates) : that.rates != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return this.rates != null ? this.rates.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "ExchangeRateDTO [rates=" + this.rates + "]";
	}
}