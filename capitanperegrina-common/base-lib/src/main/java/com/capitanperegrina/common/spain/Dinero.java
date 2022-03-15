package com.capitanperegrina.common.spain;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.validators.Validadores;

/**
 * Clase utilidad para operaciones con dinero.
 */
public class Dinero
{
	static Logger log = Logger.getLogger( Dinero.class );
	
	private Dinero()
	{
	}
	
	/**
	 * Método que formatea según el estandar español una cantidad
	 * @param importe importe a formatear
	 * @param nDecimales número de decimales a mostrar
	 * @param moneda moneda en la que está denominado el importe
	 * @return cadena formateada
	 */
    public static String bigDecimal2StringFormateado(BigDecimal importe, int nDecimales, String moneda)
    {
    	NumberFormat formateo = NumberFormat.getNumberInstance( new Locale("es", "ES") );
        formateo.setMaximumFractionDigits(nDecimales);
        formateo.setMinimumFractionDigits(nDecimales);
        String devolver = formateo.format(importe);
        
        if (moneda.length() > 0)
        {
            devolver = devolver + " " + moneda;
        }

        return devolver;
    }

	/**
	 * Método que formatea según el estandar español una cantidad, pero sin separador de miles
	 * @param importe importe a formatear
	 * @param nDecimales número de decimales a mostrar
	 * @param moneda moneda en la que está denominado el importe
	 * @return cadena formateada
	 */
    public static String bigDecimal2String(BigDecimal importe, int nDecimales, String moneda)
    {
    	String tmp = Dinero.bigDecimal2StringFormateado( importe, 2, moneda );
    	tmp = tmp.replace( ".", "" );
    	if ( "".equals( tmp ) )
    	{
    		return "";
    	}
    	else
    	{
    		return tmp;
    	}  
    }    

    /**
     * Convierte una cadena formateada en una cadena parseable como BigDecimal
     * @param importeFormateado cadena con un importe formateado
     * @return la cadena del importe parseable como BigDecimal
     */
    public static String importe2ImporteSinMoneda(String importeFormateado)
    {
    	String tmp = importeFormateado;
    	tmp = tmp.replace('.', '#');
    	tmp = tmp.replace(',', '.');
    	tmp = tmp.replaceAll("#", "");
    	tmp = tmp.replaceAll(" " + DefaultGlobalNames.MONEDA, "");
        return tmp;
    }    
    
    /**
     * Convierte una cadena en BigDecimal
     * @param importe cadena con la cantidad a convertir a BigDecimal
     * @return BigDecimal con el valor del string.
     */
    public static BigDecimal string2BigDecimal(String importe)
    {
    	if ( Validadores.estaVacia( importe ) ) 
    	{
    		return null;
    	}
    	return new BigDecimal( importe );
    }
    
	/**
	 * Método que formatea según el estandar español una cantidad, pero sin separador de miles
	 * @param importe importe a formatear
	 * @param nDecimales número de decimales a mostrar
	 * @return cadena formateada
	 */
    public static String bigDecimal2String(BigDecimal importe, int nDecimales)
    {
    	if ( importe == null ) 
    	{
    		return "0";
    	}
        return bigDecimal2String(importe, nDecimales, "");
    }
}

