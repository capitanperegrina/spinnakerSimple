package com.capitanperegrina.common.utils;

import java.math.BigDecimal;

/**
 * Clase utilidad para operaciones con unidades de medida
 */
public class UnidadesMedida
{
	/**
	 * 1 pie = 0,3048 metros
	 */
	public static final BigDecimal PIE_EN_METROS = new BigDecimal( "0.3048" );

	private UnidadesMedida()
	{
		super();
	}
	
	/**
	 * Convierte una magnitud en pies a metros
	 * @param pies magnitud en pies
	 * @return valor equivalente en metros
	 */
	public static BigDecimal pies2metros( BigDecimal pies )
	{

		return pies.multiply( PIE_EN_METROS );
	}

	/**
	 * Convierte una magnitud en metros a pies
	 * @param metros magnitud en metros
	 * @return valor equivalente en pies
	 */
	public static BigDecimal metros2pies( BigDecimal metros )
	{
		return metros.divide( PIE_EN_METROS );
	}
}
