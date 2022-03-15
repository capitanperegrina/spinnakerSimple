package com.capitanperegrina.utils.numbers;

import java.math.BigDecimal;

/**
 * Operaciones con números.
 */
public class Numeros {

	public static double redondea(final double d, final double precision) {
		return Math.round(d * Math.pow(10, precision)) / Math.pow(10, precision);
	}

	public static BigDecimal redondea(final BigDecimal d, final int precision) {
		return d == null ? d : d.setScale(precision, BigDecimal.ROUND_HALF_EVEN);
	}
}
