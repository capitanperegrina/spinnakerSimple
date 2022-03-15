package com.capitanperegrina.common.spain;

import org.apache.log4j.Logger;

/**
 * Clase utilidad para tratamiento de teléfonos móviles
 */
public class TelefonoMovil
{
	static Logger log = Logger.getLogger( TelefonoMovil.class );
	
	private TelefonoMovil()
	{
	}
	
	/**
	 * Estandariza un número de teléfono eliminando espacios, guiones, barras...
	 * @param tIn número de teléfono sin estandarizar
	 * @return número de teléfono estandarizado
	 */
    public static String estandariza( String tIn )
    {
        String tmp = tIn;
        tmp = tmp.replace('(', ' ');
        tmp = tmp.replace('/', ' ');
        tmp = tmp.replace(')', ' ');
        tmp = tmp.replace('-', ' ');
        tmp = tmp.replaceAll(" ", "");
        return tmp;
    }
}
