package com.capitanperegrina.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import org.springframework.context.i18n.LocaleContextHolder;

import com.capitanperegrina.common.validators.Validadores;


/**
 * @author Carlos N&uacute;ñez Garc&iacute;a
 */
public class Formateador
{
    public static final int DERECHA = 1;
    public static final int IZQUIERDA = 0;
    
    /**
     * Campos para indicar si queremos trabajar con un n£mero o con un
     * porcentaje
     */
    public static final int NUMERO = 0;
    public static final int PORCENTAJE = 1;

    /**
     * Mtodo que cambia la coma del texto introducido por punto:
     * 
     * @param java.lang.String
     *            texto: El texto a ser formateado
     * @return java.lang.String Texto ya formateado
     */
    public static String CambiarComa(String texto)
    {
        int Long = (texto.length());

        String Entera;
        String Decimal = "";

        int IndiceComa = texto.indexOf(",");

        if (IndiceComa == (-1))
        {
            Entera = texto;
        }
        else
        {
            Entera = texto.substring(0, IndiceComa);
            Decimal = texto.substring(IndiceComa + 1, Long);
        }

        texto = Entera + "." + Decimal;

        return texto;
    }

    /**
     * Mtodo que cambia el punto del texto introducido por coma:
     * 
     * @param java.lang.String
     *            texto: El texto a ser formateado
     * @return java.lang.String Texto ya formateado
     */
    public static String CambiarPunto(String texto)
    {
        int Long = (texto.length());

        String Entera;
        String Decimal = "";

        int IndiceComa = texto.indexOf(".");

        if (IndiceComa == (-1))
        {
            Entera = texto;
        }
        else
        {
            Entera = texto.substring(0, IndiceComa);
            Decimal = texto.substring(IndiceComa + 1, Long);
        }

        if (IndiceComa == -1)
            texto = Entera + ",00";
        else
            if ((texto.length() - IndiceComa) == 2)
                texto = Entera + "," + Decimal + "0";
            else texto = Entera + "," + Decimal;

        return texto;
    }

    /**
     * Mtodo que devuelve la cadena rellena con espacios por la derecha, hasta
     * ocupar "longitud" posiciones.
     * <ul>
     * <li>Utiliza el espacio como s¡mbolo de relleno</li>
     * </ul>
     * 
     * @param java.lang.Object
     *            cadena: Cadena a ser formateada
     * @param int
     *            longitud: Indica la longitud de la cadena resultante
     * @return java.lang.String Cadena rellena
     */
    public static String fill(Object cadena, int longitud)
    {
        return fill(cadena.toString(), ' ', longitud, 1);
    }

    /**
     * M&eacute;todo que devuelve la cadena rellena con el caracter "car", hasta
     * ocupar "lon" posiciones, desde el lado indicado.
     * <ul>
     * <li>Utiliza el caracter como s¡mbolo de relleno</li>
     * <li>Valores posibles de lado
     * <ul>
     * <li>0: izquierda</li>
     * <li>1: derecha</li>
     * </ul>
     * </li>
     * </ul>
     * 
     * @param cadena:
     *            Cadena a ser formateada
     * @param caracterRelleno:
     *            Caracter con el que se va a rellenar
     * @param longitud:
     *            Indica la longitud de la cadena resultante
     * @param lado:
     *            nos indica en que lado situar los caracteres de Relleno
     * @return Cadena rellena
     */
    public static String fill(String cadena, char caracterRelleno, int longitud, int lado)
    {
        String salida = cadena;

        // Si tiene la misma longitud la devuelve
        if (salida.length() == longitud) return salida;

        // Si es m s larga la trunca
        if (salida.length() > longitud) return salida.substring(0, longitud);

        // Si es menor, entonces modificamos
        if (salida.length() < longitud)
        {
            if (lado == DERECHA)
            {
                // Rellenar por la derecha
                for (int k = cadena.length(); k < longitud; k++)
                    salida = salida + caracterRelleno;
            }
            else
                if (lado == IZQUIERDA)
                {
                    // Rellenar por la izquierda
                    for (int k = cadena.length(); k < longitud; k++)
                        salida = caracterRelleno + salida;
                }
                else
                {
                    return "Lado pasado a la función fill es incorrecto";
                }

            return salida;
        }

        return cadena;
    }

    /**
     * Mtodo que devuelve un n£mero convertido en texto bien formateado:
     * <ul>
     * <li>Utiliza la coma como separador decimal</li>
     * <li>Utiliza el punto como separador de miles</li>
     * <li>Si tiene m s decimales que los indicados realiza un redondeo hacia
     * arriba</li>
     * </ul>
     * 
     * @param java.math.BigDecimal
     *            cantidad: El n£mero a ser formateado.
     * @param int
     *            nDecimales: El n£mero de decimales a mostrar.
     * @return java.lang.String N£mero ya formateado
     */
    private static String formatearNumero(BigDecimal importe, int nDecimales)
    {
        // Preparo un objeto para realizar el formateo
        NumberFormat formateo = NumberFormat.getNumberInstance();
        formateo.setMaximumFractionDigits(nDecimales);
        formateo.setMinimumFractionDigits(nDecimales);
        return formateo.format(importe);
    }
    
    public static String formatearNumero(BigDecimal numero, int nDecimales_max, int nDecimales_min)
    {
        NumberFormat formateo = NumberFormat.getNumberInstance();
        formateo.setMaximumFractionDigits(nDecimales_max);
        formateo.setMinimumFractionDigits(nDecimales_min);
        return formateo.format( numero );
    }    

    /**
     * <p>
     * Mtodo que devuelve un n£mero convertido en texto bien formateado como
     * porcentaje. El n£mero entrante debe ser del tipo 0.53 para convertirlo en
     * 53%
     * </p>
     * Puntos a tener en cuenta:
     * <ul>
     * <li>Utiliza la coma como separador decimal</li>
     * <li>Utiliza el punto como separador de miles</li>
     * <li>Si tiene m s decimales que los indicados realiza un redondeo hacia
     * arriba</li>
     * <li>A€ade el s¡mbolo de porcentaje al final</li>
     * </ul>
     * 
     * @param java.math.BigDecimal
     *            cantidad: El n£mero a ser formateado.
     * @param int
     *            nDecimales: El n£mero de decimales a mostrar.
     * @return java.lang.String N£mero ya formateado
     */
    private static String formatearPorcentaje(BigDecimal importe, int nDecimales, boolean conSimbolo)
    {
        // Preparo un objeto para realizar el formateo
        NumberFormat formateo = NumberFormat.getPercentInstance();
        formateo.setMaximumFractionDigits(nDecimales);
        formateo.setMinimumFractionDigits(nDecimales);
        String salida = formateo.format(importe);
        if (!conSimbolo)
        {
            salida = salida.substring(0, salida.length() - 1);
        }

        return salida;
    }

    /**
     * Mtodo que devuelve un n£mero convertido en texto bien formateado:
     * <ul>
     * <li>Utiliza la coma como separador decimal</li>
     * <li>Utiliza el punto como separador de miles</li>
     * <li>Si tiene m s decimales que los indicados realiza un redondeo hacia
     * arriba</li>
     * </ul>
     * Realiza una llamada al otro mtodo indicando que no queremos el s¡mbolo
     * del porcentaje al final
     * 
     * @param java.math.BigDecimal
     *            cantidad: El n£mero a ser formateado.
     * @param int
     *            nDecimales: El n£mero de decimales a mostrar.
     * @param int
     *            TipoCampo: indicamos si vamos a formatear un Numero o un
     *            Porcentaje
     * @return java.lang.String N£mero ya formateado
     */
    public static String getFormatedNumber(BigDecimal cantidad, int nDecimales, int tipoCampo)
    {
        return getFormatedNumber(cantidad, nDecimales, tipoCampo, false);
    }

    /**
     * Mtodo que devuelve un n£mero convertido en texto bien formateado:
     * <ul>
     * <li>Utiliza la coma como separador decimal</li>
     * <li>Utiliza el punto como separador de miles</li>
     * <li>Si tiene m s decimales que los indicados realiza un redondeo hacia
     * arriba</li>
     * </ul>.
     * 
     * @param cantidad cantidad
     * @param nDecimales n decimales
     * @param TipoCampo tipo campo
     * @param simboloPorcentaje simbolo porcentaje
     * 
     * @return java.lang.String N£mero ya formateado
     */
    public static String getFormatedNumber(BigDecimal cantidad, int nDecimales, int tipoCampo, boolean simboloPorcentaje)
    {
        if (tipoCampo == NUMERO)
        {
            // Es un n£mero
            return formatearNumero(cantidad, nDecimales);
        }
        else
            if (tipoCampo == PORCENTAJE)
            {
                // Es un porcentaje
                return formatearPorcentaje(cantidad, nDecimales, simboloPorcentaje);
            }
            else
            {
                // Han pasado un valor no v lido como tipo de datos
                return "Tercer par metro pasado a getFormatedNumber rroneo";
            }
    }

    /**
     * Mtodo que devuelve un n£mero convertido en texto bien formateado:
     * <ul>
     * <li>Utiliza la coma como separador decimal</li>
     * <li>Utiliza el punto como separador de miles</li>
     * <li>Si tiene m s decimales que los indicados realiza un redondeo hacia
     * arriba</li>
     * </ul>
     * 
     * <pre>
     * Convierte la cadena pasada en un BigDecimal y llama al mtodo &quot;getFormatedNumber&quot;
     * </pre>
     * 
     * @param java.lang.String
     *            numero400: El n£mero a ser formateado (sin s¡mbolo decimal)
     * @param int
     *            nDecimalesEntrada: El n£mero de decimales que tiene el n£mero
     *            de Entrada.
     * @param int
     *            nDecimalesSalida: El n£mero de decimales que tendr  el
     *            resultado
     * @param int
     *            TipoCampo: indicamos si vamos a formatear un Numero o un
     *            Porcentaje
     * @return java.lang.String N£mero ya formateado
     */
    public static String getFormatedNumberForAS400(String numero400, int nDecimalesEntrada, int nDecimalesSalida, int TipoCampo)
    {
        return getFormatedNumberForAS400(numero400, nDecimalesEntrada, nDecimalesSalida, TipoCampo, false);
    }

    /**
     * Mtodo que devuelve un n£mero convertido en texto bien formateado:
     * <ul>
     * <li>Utiliza la coma como separador decimal</li>
     * <li>Utiliza el punto como separador de miles</li>
     * <li>Si tiene m s decimales que los indicados realiza un redondeo hacia
     * arriba</li>
     * </ul>
     * 
     * <pre>
     * Convierte la cadena pasada en un BigDecimal y llama al mtodo &quot;getFormatedNumber&quot;
     * </pre>
     * 
     * @param java.lang.String
     *            numero400: El n£mero a ser formateado (sin s¡mbolo decimal)
     * @param int
     *            nDecimalesEntrada: El n£mero de decimales que tiene el n£mero
     *            de Entrada.
     * @param int
     *            nDecimalesSalida: El n£mero de decimales que tendr  el
     *            resultado
     * @param int
     *            TipoCampo: indicamos si vamos a formatear un Numero o un
     *            Porcentaje
     * @param boolean
     *            simboloPorcentaje: indica si debe mostrar o no el s¡mbolo del
     *            Porcentaje
     * @return java.lang.String N£mero ya formateado
     */
    public static String getFormatedNumberForAS400(String numero400, int nDecimalesEntrada, int nDecimalesSalida, int TipoCampo, boolean simboloPorcentaje)
    {
        // Convierto el String en un BigDecimal
        BigDecimal importeEntrada = new BigDecimal(numero400).setScale(nDecimalesEntrada);

        // Divido el importe entre 10 elevado a nDecimalesEntrada
        double divisor = Math.pow(10, Double.parseDouble(String.valueOf(nDecimalesEntrada)));
        importeEntrada = importeEntrada.divide(new BigDecimal(divisor), BigDecimal.ROUND_HALF_UP);

        // Llamo a la funci¢n principal
        return getFormatedNumber(importeEntrada, nDecimalesSalida, TipoCampo, simboloPorcentaje);
    }

    /**
     * Mtodo que elimina el s¡mbolo indicado de una cadena.
     * 
     * @param java.lang.String
     *            cadena: Cadena de donde eliminaremos el s¡mbolo
     * @param char
     *            simbolo: simbolo a eliminar
     * @return java.lang.String Cadena modificada
     */
    public static String quitarSimbolo(String cadena, char simbolo)
    {
        char[] txt = cadena.toCharArray();
        String Resultado = "";

        for (int i = 0; i < cadena.length(); i++)
            Resultado += simbolo != txt[i] ? String.valueOf(txt[i]) : "";

        return Resultado;
    }

    /**
     * Mtodo que formatea un nombre con may£sculas y minusculas. Tiene en
     * cuenta como separadores " ", "-", "_" y "."
     * 
     * @param java.lang.String
     *            nombre: Cadena que contiene el nombre a formatear.
     * @return java.lang.String Nombre formateado
     */
    public static String caseNombre(String nombre)
    {
        String entrada = nombre.trim();
        boolean proximaMayus = true;
        String salida = "";
        for (int i = 0; i < entrada.length(); i++)
        {
            if (proximaMayus)
            {
                salida = salida.concat(entrada.substring(i, i + 1).toUpperCase());
            }
            else
            {
                salida = salida.concat(entrada.substring(i, i + 1).toLowerCase());
            }

            proximaMayus = (entrada.substring(i, i + 1).equals(" ") || entrada.substring(i, i + 1).equals("-") || entrada.substring(i, i + 1).equals(".") || entrada.substring(i, i + 1).equals("_"));
        }
        return salida;
    }

    public static String bigDecimal2StringPlano2Decimales(BigDecimal num)
    {
        String s = null;

        if (num != null)
        {
            s = getFormatedNumber(num, 2, 0);
        }
        s = quitarSimbolo(s, '.');
        s = quitarSimbolo(s, ',');

        return s;
    }
    
    public static String nombreCompleto( String n, String a1, String a2 )
    {
        String tmp = "";
        
        if ( ! Validadores.estaVacia( a1 ) )
        {
            tmp = tmp + " " + a1.trim();
        }        
        
        if ( ! Validadores.estaVacia( a2 ) )
        {
            tmp = tmp + " " + a2.trim();
        }
        
        if ( ! Validadores.estaVacia( n ) )
        {
            tmp = tmp + ", " + n.trim();
        }
        
        return tmp;
    }
    
    /** 
     * bigDecimal2CSB
     * Convierte un dato num&eacute;rico en una cadena con todos los d&iacuteMgitos del
     * n&uacute;mero que se pasa al m&eacute;todo sin separador de decim&aacute;les.
     * @param  N&uacute;mero a convertir a cadena sin separador.
     * @return String Cadena con el n&uacute;mero y sin separador.
     */
    public static String bigDecimal2CsbString(BigDecimal numero)
    {
        Integer resultado = new Integer ( numero.multiply(new BigDecimal(100)).intValue() );
        return ( resultado.toString() );
    }
    
    public static BigDecimal csbString2BigDecimal(String numeroCsb )
    {
    	BigDecimal numero = new BigDecimal( numeroCsb );
    	numero = numero.divide( new BigDecimal(100) );
        return ( numero );
    }
    
    public static String bigDecimal2LocalizedString( BigDecimal bd )
    {
    	DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance( LocaleContextHolder.getLocale() );
    	DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
    	formatter.setDecimalFormatSymbols( symbols );
    	return formatter.format( bd );	
    }
    
    /*
    public static void main(String[] args)
    {
        System.out.println( bigDecimal2CsbString( new BigDecimal( "184.3" ) ) );
        System.out.println( csbString2BigDecimal( bigDecimal2CsbString( new BigDecimal( "184.3" ) ) ) );
    }
    //*/
}