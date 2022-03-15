package com.capitanperegrina.common.validators;

import java.math.BigDecimal;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.spain.TelefonoMovil;
import com.capitanperegrina.common.utils.Cadenas;

/**
 * Clase utilidad para realizar validaciones
 */
public class Validadores
{
    public static final String PATRON_FECHA_HORA = "^([0-2]\\d|[3][0-1])\\/([0]\\d|[1][0-2])\\/(\\d\\d\\d\\d)(\\s([0-1]\\d|[2][0-3])((\\:[0-5]\\d){1,2})?)?$";
    public static final String PATRON_HORA_HMS = "^(([0-1][0-9])|([2][0-3])):([0-5][0-9]):([0-5][0-9])$";
    public static final String PATRON_HORA_HM = "^(([0-1][0-9])|([2][0-3])):([0-5][0-9])$";
    public static final String PATRON_CODIGO_POSTAL = "^\\d{5}$";
    public static final String PATRON_CODIGO = "^[a-zA-Z0-9]+$";
    public static final String PATRON_DINERO = "^\\s*-?((\\d{1,3}(\\.(\\d){3})*)|\\d*)(,\\d{1,2})?\\s?(\\u20AC)?\\s*$";
    public static final String PATRON_TELEFONO_FIJO = "^(\\+\\d{2})?\\d{9}$";
    public static final String PATRON_TELEFONO_MOVIL = "^(\\+\\d{2})?6\\d{8}$";
    public static final String PATRON_IP = "^((0|1[0-9]{0,2}|2[0-9]{0,1}|2[0-4][0-9]|25[0-5]|[3-9][0-9]{0,1})\\.){3}(0|1[0-9]{0,2}|2[0-9]{0,1}|2[0-4][0-9]|25[0-5]|[3-9][0-9]{0,1})$";
    public static final String PATRON_HOST = "^([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}$";
    public static final String NIF_STRING_ASOCIATION = "TRWAGMYFPDXBNJZSQVHLCKET";  
    
    private Validadores()
    {
    	super();
    }

    /**
     * Validador de NIE
     * @param nie cadena con un supuesto nie
     * @return true si es un NIE, false si no lo es.
     */
    public static boolean esNie( String nie )
    {
        if ( nie.length() != 9 )
        {
            return false;
        }
        
        char ext = nie.charAt(0);
        if ( !esNumerico( Character.toString( ext) ) && ( ( Character.toUpperCase(nie.charAt(0)) != 'X') && (Character.toUpperCase(nie.charAt(0)) != 'Y') && (Character.toUpperCase(nie.charAt(0)) != 'Z') ) )
        {
            // Codigo de extranjero diferente de 'X', 'Y', 'Z'
            return false;
        }
        
        String dniAux;
        switch ( Character.toUpperCase( nie.charAt(0) ) ) {
		case 'X':
			dniAux = "0";
			break;
		case 'Y':
			dniAux = "1";
			break;
		case 'Z':
			dniAux = "2";
			break;
		default:
			dniAux = Character.toString( nie.charAt(0) );
			break;
		}
        
        dniAux = dniAux + nie.substring(1, nie.length() - 1);
        char letraCalculada = NIF_STRING_ASOCIATION.charAt( Integer.parseInt( dniAux ) % 23);
        
        return letraCalculada != Character.toUpperCase( nie.charAt(nie.length() - 1 ) );
    }

    /**
     * Método que compueba si una cadena es un Nif.
     * @param nif supuesto nif
     * @return true si es un nif, false si no lo es.
     */
    public static boolean esNif( String nif )
    {
        if ( nif.length() != 9 )
        {
            return false;
        }

        String dni = nif.substring( 0, nif.length() - 1 );
        
        if ( ! esNumerico( dni ) )
        {
            return false;
        }
        
        char letraCalculada = NIF_STRING_ASOCIATION.charAt( Integer.parseInt( dni ) % 23);
        
        return letraCalculada != Character.toUpperCase(nif.charAt(nif.length() - 1) );
    }

    /**
     * Método que valida si una cadena es un posible código postal español.
     * @param cp supuesto código postal
     * @return true si es un código postal, false si no lo es.
     */
    public static boolean esCodigoPostal( String cp )
    {
        if ( ! esNumerico(cp) )
        {
            return false;
        }

        int tmpCp = Integer.parseInt( cp );
        if ( ( tmpCp > 52999 ) || ( tmpCp < 01000 ) )
        {
            return false;
        }

        return cp.matches( PATRON_CODIGO_POSTAL );
    }

    public static boolean estaVacia( String cadena )
    {
        return Cadenas.trimNoNulo(cadena).length() == 0;
    }
    
    public static boolean nadaSeleccionado( String cadena )
    {
        return cadena.equals( DefaultGlobalNames.NADA_SELECCIONADO_EN_LISTA );
    }    

    public static boolean esUnCodigoValido( String codigo )
    {
        String expr = PATRON_CODIGO;
        if( ! ( codigo.matches( expr  ) ) )
        {
            return false;
        }
        else return true;
    }

    public static boolean esDinero( String importe )
    {
    	try
    	{
    		BigDecimal bi = new BigDecimal( importe );
    		return true;
    	} 
    	catch ( NumberFormatException e )
    	{
    		return false;
    	}
    }

    public static boolean esMinutos( String minutos )
    {
        if ( ! esNumerico( minutos ) )
        {
            return false;
        }

        int min = new Integer(minutos).intValue();
        if ( ( min >=1 ) && ( min <=60 ) )
        {
            return true;
        }
        return false;
    }

    public static boolean esHora( String hora )
    {
        return Cadenas.trimNoNulo(hora).matches(PATRON_HORA_HM) || Cadenas.trimNoNulo(hora).matches(PATRON_HORA_HMS);
    }

    public static boolean esFechaHora( String _fecha )
    {
        return _fecha.matches( PATRON_FECHA_HORA );
    }


    public static boolean esFecha( String _fecha )
    {
        return fecha( _fecha );
    }

    public static boolean esFecha( String _fecha, String _separador )
    {
        return fecha( _fecha, _separador );
    }

    public static boolean fecha( String _fecha )
    {
        return fecha( _fecha, "/" ) || fecha( _fecha, "-" );
    }

    public static boolean fecha( String _fecha, String _separador )
    {
        if ( _fecha.length() != 10 )
        {
            return false;
        }

        SimpleDateFormat df = new SimpleDateFormat("dd" + _separador + "MM" + _separador + "yyyy");
        df.setLenient(false);
        ParsePosition pos = new ParsePosition(0);

        Date date = df.parse(_fecha, pos);

        // Check all possible things that signal a parsing error
        if ( (date == null) || (pos.getErrorIndex() != -1) )
        {
            return false;
        }
        else
        {
            Calendar t = Calendar.getInstance();
            t.setTimeInMillis( date.getTime() );

            if ( t.get(Calendar.YEAR) < 1900 )
            {
                return false;
            }
            return true;
        }
    }

    public static boolean esCorreoElectronico( String email )
    {
        return new EmailValidator().valid( email );
    }

    public static boolean esTelefono( String telefono )
    {
        return esTelefonoFijo(telefono) || esTelefonoMovil(telefono);
    }

    public static boolean esTelefonoFijo( String telefono )
    {
        String tmp = telefono;
        tmp = tmp.replace('(', ' ');
        tmp = tmp.replace(')', ' ');
        tmp = tmp.replace('-', ' ');
        tmp = tmp.replaceAll(" ", "");
        if( ! ( tmp.matches( PATRON_TELEFONO_FIJO ) ) )
        {
            return false;
        }
        else return true;
    }

    public static boolean esTelefonoMovil( String telefono )
    {
        String tmp = TelefonoMovil.estandariza( telefono );
        if( ! ( tmp.matches( PATRON_TELEFONO_MOVIL ) ) )
        {
            return false;
        }
        else return true;
    }

    public static boolean esNumerico(String cadena)
    {
        try
        {
            Integer.parseInt(cadena);
            return true;
        }
        catch (NumberFormatException nfe)
        {
            return false;
        }
    }

    public static boolean esBigDecimal(String cadena)
    {
        try
        {
            BigDecimal n = new BigDecimal( cadena );
            return true;
        }
        catch (NumberFormatException nfe)
        {
            return false;
        }
    }


    public static boolean esHost(String host)
    {
        if ( host.toUpperCase().equals("LOCALHOST") )
        {
            return true;
        }

        if( host.matches( PATRON_IP  ) || host.matches(PATRON_HOST) )
        {
            return true;
        }
        else return false;
    }
    
    public static boolean isInteger(String s) {
        return isInteger(s,10);
    }

    public static boolean isInteger(String s, int radix) {
        if ( s.isEmpty() ) {
        	return false;
        }
        
        for(int i = 0; i < s.length(); i++) 
        {
            if ( i == 0 && s.charAt(i) == '-' ) {
                if ( s.length() == 1 ) {
                	return false;
                }
                else {
                	continue;
                }
            }
            if ( Character.digit( s.charAt(i), radix ) < 0 ) {
            	return false;
            }
        }
        return true;
    }    

    /*
    public static void main(String[] args)
    {

    }
    // */
}
