package com.capitanperegrina.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.apache.log4j.Logger;

import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.i18n.Mensajes;

/**
 * Clase con diferentes operaciones con cadenas y caracteres que no estn en Java
 * (o que soy un paquete y no he encontrado o sabido utilizar).
 *
 * @author Carlos N&uacute;ez Garc&iacute;a
 */
public class Cadenas
{
	private final static Logger	LOGGER	= Logger.getLogger( Cadenas.class );

	// Patrn Singleton
	static private Cadenas		cadenas	= null;

	private Cadenas()
	{
	}

	static public Cadenas getCadenas()
	{
		if ( cadenas == null )
		{
			cadenas = new Cadenas();
		}
		return cadenas;
	}

	/**
	 * Funcion para saber si un caracter es un smbolo o no.
	 *
	 * @param c
	 *            Caracter a comprobar si es un smbolo o no.
	 * @return Verdadero si el caracter es '+', '-', '*', o '/'.
	 */
	public static boolean esSimbolo( final char c )
	{
		boolean es = false;
		if ( ( c == '+' ) || ( c == '-' ) || ( c == '*' ) || ( c == '/' ) )
		{
			es = true;
		}
		return es;
	}

	public static String booleanToString( final Boolean b )
	{
		if ( b == null )
		{
			return "";
		}
		else
		{
			if ( b.booleanValue() )
			{
				return DefaultGlobalNames.SI;

			}
			else
			{
				return DefaultGlobalNames.NO;
			}
		}
	}

	public static Boolean stringToBoolean( final String s )
	{
		if ( s.equals( DefaultGlobalNames.SI ) )
		{
			return Boolean.TRUE;
		}
		else if ( s.equals( DefaultGlobalNames.NO ) )
		{
			return Boolean.FALSE;
		}
		else
		{
			return null;
		}
	}

	/**
	 * Funcion que codifica los caracteres especiales a los tags de HTML. v b
	 * vmvm,vn,v,nkvn,vnmv,nkv,nk m mm,,mxdsas .€vc c vñp
	 *
	 * @param cadena
	 *            Texto a codificar.
	 * @return Cadena con el texto codificado.
	 */
	public static String txt2Html( String cadena )
	{
		cadena = cadena.replace( "", "&aacute;" );
		cadena = cadena.replace( "", "&eacute;" );
		cadena = cadena.replace( "", "&iacute;" );
		cadena = cadena.replace( "", "&oacute;" );
		cadena = cadena.replace( "", "&uacute;" );
		cadena = cadena.replace( "", "&Aacute;" );
		cadena = cadena.replace( "", "&Eacute;" );
		cadena = cadena.replace( "", "&Iacute;" );
		cadena = cadena.replace( "", "&Oacute;" );
		cadena = cadena.replace( "", "&Uacute;" );
		cadena = cadena.replace( "", "&agrave;" );
		cadena = cadena.replace( "", "&egrave;" );
		cadena = cadena.replace( "", "&igrave;" );
		cadena = cadena.replace( "", "&ograve;" );
		cadena = cadena.replace( "", "&ugrave;" );
		cadena = cadena.replace( "", "&Agrave;" );
		cadena = cadena.replace( "", "&Egrave;" );
		cadena = cadena.replace( "", "&Igrave;" );
		cadena = cadena.replace( "", "&Ograve;" );
		cadena = cadena.replace( "", "&Ugrave;" );
		cadena = cadena.replace( "", "&uuml;" );
		cadena = cadena.replace( "", "&Uuml;" );
		cadena = cadena.replace( "", "&ntilde;" );
		cadena = cadena.replace( "", "&Ntilde;" );
		cadena = cadena.replace( "<", "&lt;" );
		cadena = cadena.replace( ">", "&gt;" );
		cadena = cadena.replace( "<", "&lt;" );
		cadena = cadena.replace( "'", "&quot;" );
		return cadena;
	}

	/**
	 * Funcion que descodifica los tags de HTML a caracteres especiales de
	 * texto.
	 *
	 * @param cadena
	 *            Texto a descodificar.
	 * @return Cadena con el texto descodificado.
	 */
	public static String html2Txt( String cadena )
	{
		cadena = cadena.replace( "&aacute;", "" );
		cadena = cadena.replace( "&eacute;", "" );
		cadena = cadena.replace( "&iacute;", "" );
		cadena = cadena.replace( "&oacute;", "" );
		cadena = cadena.replace( "&uacute;", "" );
		cadena = cadena.replace( "&Aacute;", "" );
		cadena = cadena.replace( "&Eacute;", "" );
		cadena = cadena.replace( "&Iacute;", "" );
		cadena = cadena.replace( "&Oacute;", "" );
		cadena = cadena.replace( "&Uacute;", "" );
		cadena = cadena.replace( "&agrave;", "" );
		cadena = cadena.replace( "&egrave;", "" );
		cadena = cadena.replace( "&igrave;", "" );
		cadena = cadena.replace( "&ograve;", "" );
		cadena = cadena.replace( "&ugrave;", "" );
		cadena = cadena.replace( "&Agrave;", "" );
		cadena = cadena.replace( "&Egrave;", "" );
		cadena = cadena.replace( "&Igrave;", "" );
		cadena = cadena.replace( "&Ograve;", "" );
		cadena = cadena.replace( "&Ugrave;", "" );
		cadena = cadena.replace( "&uuml;", "" );
		cadena = cadena.replace( "&Uuml;", "" );
		cadena = cadena.replace( "&ntilde;", "" );
		cadena = cadena.replace( "&Ntilde;", "" );
		cadena = cadena.replace( "&lt;", "<" );
		cadena = cadena.replace( "&gt;", ">" );
		cadena = cadena.replace( "&lt;", "<" );
		cadena = cadena.replace( "&quot;", "'" );
		return cadena;
	}

	/**
	 * Mtodo que devuelve el las primeras <code>numeroPalabras</code> palabras
	 * de la cadena <code>texto</code>.
	 *
	 * @param texto
	 *            Texto del cual se quieren extraer las
	 *            <code>numeroPalabras</code> primeras palabras.
	 * @param numeroPalabras
	 *            Nmero de palabras que se quieren extraer de <code>texto</code>
	 *            .
	 * @return Devuelve un String
	 */
	public static String obtenPalabras( final String texto, final int numeroPalabras )
	{
		String devolver = "";
		int palabrasRestantes = numeroPalabras;
		final int longitud = texto.length();
		int i = 0;
		String caracterAnalizado = "";

		while ( ( i < longitud ) && ( palabrasRestantes > 0 ) )
		{
			caracterAnalizado = texto.substring( i, i + 1 );
			if ( caracterAnalizado.equals( " " ) )
			{
				palabrasRestantes = palabrasRestantes - 1;
			}
			devolver = devolver + caracterAnalizado;
			i = i + 1;
		}
		return devolver;
	}

	public static String trim( final String valor )
	{
		return StringUtils.trimToNull( valor );
	}

	public static String trimNoNulo( final String valor )
	{
		return StringUtils.trimToEmpty( valor );
	}

	public static String toStringGenerico( final Object[] valor )
	{
		String ret = "";
		final StringBuilder sb = new StringBuilder();
		if ( valor != null )
		{
			for ( final Object o : valor )
			{
				sb.append( Cadenas.toStringGenerico( o ) ).append( "|" );
			}
			ret = sb.toString();
			ret = ret.substring( 0, ret.lastIndexOf( "|" ) );
		}
		return ret;
	}

	public static String toStringGenerico( final Object valor )
	{
		try
		{
			if ( valor != null )
			{
				if ( valor instanceof Calendar )
				{
					return Fecha.calendar2DateHourString( (Calendar) valor, '/', ':' );
				}
				if ( valor instanceof HashMap )
				{
					final Map<Object, Object> mapa = (Map<Object, Object>) valor;
					final StringBuilder str = new StringBuilder();
					for ( final Entry<Object, Object> e : mapa.entrySet() )
					{
						str.append( Cadenas.toStringGenerico( e.getKey() ) ).append( " -> " ).append( Cadenas.toStringGenerico( e.getValue() ) ).append( "\n" );

					}
					return str.toString();
				}
				return valor.toString();
			}
			else
			{
				return "null";
			}
		}
		catch ( final Exception e )
		{
			return "null_err";
		}
	}

	public static int getNumeroOcurrencias( final String cadena, final char caracter )
	{
		int total = 0;
		for ( int i = 0; i < cadena.length(); i++ )
		{
			if ( cadena.charAt( i ) == caracter )
			{
				total++;
			}
		}
		return total;
	}

	public static int getOcurrenciasEnUnGrupo( final String clave, final String grupo )
	{
		int encontrados = 0;

		for ( int i = 0; i < clave.length(); i++ )
		{
			if ( grupo.indexOf( clave.substring( i, i + 1 ) ) != -1 )
			{
				encontrados++;
			}
		}

		return encontrados;
	}

	public static String elementoCadena( final String cadena, final int elemento, final String separador )
	{
		final StringTokenizer st = new StringTokenizer( cadena, separador, true );

		int i = 0;
		String token = separador;
		String tokenPrevio = "";
		while ( st.hasMoreElements() && ( i < elemento ) )
		{
			tokenPrevio = token;
			token = (String) st.nextElement();

			if ( token.equals( tokenPrevio ) && token.equals( separador ) )
			{
				i++;
				if ( i == elemento )
				{
					return "";
				}
			}
			else if ( !token.equals( separador ) )
			{
				i++;
				if ( i == elemento )
				{
					return token;
				}
			}
		}
		return "";
	}

	public static Collection<String> elementoCadenaString2Collection( final String cadena, final String separador )
	{
		final Collection<String> elementos = new ArrayList<String>();

		final StringTokenizer st = new StringTokenizer( cadena, separador, true );

		String token = separador;
		String tokenPrevio = "";
		while ( st.hasMoreElements() )
		{
			tokenPrevio = token;
			token = (String) st.nextElement();

			if ( token.equals( tokenPrevio ) && token.equals( separador ) )
			{
				elementos.add( "" );
			}
			else if ( !token.equals( separador ) )
			{
				elementos.add( token );
			}
		}
		return elementos;
	}

	public static String bytes2HexString( final byte[] b )
	{
		final BigInteger temp = new BigInteger( b );
		return temp.toString( 16 );
	}

	public static byte[] hexString2bytes( final String hexString )
	{
		return new BigInteger( hexString, 16 ).toByteArray();
	}

	public static String prefijaCamposTabla( final String camposComoEnSQL, final String prefijoSinPunto )
	{
		final StringBuilder sb = new StringBuilder( "" );
		final Collection<String> campos = Cadenas.elementoCadenaString2Collection( camposComoEnSQL, "," );
		final Iterator<String> it = campos.iterator();
		while ( it.hasNext() )
		{
			final String campo = Cadenas.trimNoNulo( it.next() );
			sb.append( prefijoSinPunto + "." + campo + ", " );
		}
		String out = sb.toString();
		out = out.substring( 0, out.lastIndexOf( ',' ) );

		return out;
	}

	public static String eliminaAcentos( final String cadena )
	{
		String tmp = trimNoNulo( cadena );

		tmp = tmp.replaceAll( "Ñ", "###N" );
		tmp = tmp.replaceAll( "ñ", "###n" );

		tmp = tmp.replaceAll( "Ç", "###C" );
		tmp = tmp.replaceAll( "ç", "###c" );

		final String nfdNormalizedString = Normalizer.normalize( tmp, Normalizer.Form.NFD );
		final Pattern pattern = Pattern.compile( "\\p{InCombiningDiacriticalMarks}+" );
		tmp = pattern.matcher( nfdNormalizedString ).replaceAll( "" );

		tmp = tmp.replaceAll( "###N", "Ñ" );
		tmp = tmp.replaceAll( "###n", "ñ" );

		tmp = tmp.replaceAll( "###C", "Ç" );
		tmp = tmp.replaceAll( "###c", "ç" );

		return tmp;
	}

	/**
	 * Retorna o valor da propiedade fichero2 string buffer.
	 *
	 * @param aFile
	 *            - File
	 * @return StringBuilder
	 */
	public static StringBuilder getFichero2StringBuilder( final File aFile )
	{
		final StringBuilder contents = new StringBuilder();

		try
		{
			final BufferedReader input = new BufferedReader( new FileReader( aFile ) );
			try
			{
				String line = null; // not declared within while loop
				while ( ( line = input.readLine() ) != null )
				{
					contents.append( line );
					contents.append( System.getProperty( "line.separator" ) );
				}
			}
			finally
			{
				input.close();
			}
		}
		catch ( final IOException ex )
		{
			ex.printStackTrace();
		}

		return contents;
	}

	/**
	 * Reemplaza.
	 *
	 * @param in
	 *            - StringBuilder
	 * @param buscar
	 *            - String
	 * @param reemplazar
	 *            - String
	 * @return StringBuilder
	 */
	public static StringBuilder reemplaza( StringBuilder in, final String buscar, final String reemplazar )
	{
		final StringBuilder out = new StringBuilder( "" );

		while ( in.length() > 0 )
		{
			final int indice = in.indexOf( buscar );
			if ( indice == -1 )
			{
				out.append( in.toString() );
				in = new StringBuilder( "" );
			}
			else
			{
				out.append( in.substring( 0, indice ) );
				out.append( reemplazar );
				in = in.delete( 0, indice + buscar.length() );
			}
		}
		return out;
	}

	/**
	 * Replace.
	 *
	 * @param str
	 *            - String
	 * @param pattern
	 *            - String
	 * @param replace
	 *            - String
	 * @return String
	 */
	public static String replace( final String str, final String pattern, final String replace )
	{
		int s = 0;
		int e = 0;
		final StringBuilder result = new StringBuilder();

		while ( ( e = str.indexOf( pattern, s ) ) >= 0 )
		{
			result.append( str.substring( s, e ) );
			result.append( replace );
			s = e + pattern.length();
		}
		result.append( str.substring( s ) );
		return result.toString();
	}

	/**
	 * Replace.
	 *
	 * @param str
	 *            - StringBuilder
	 * @param pattern
	 *            - String
	 * @param replace
	 *            - String
	 * @return StringBuilder
	 */
	public static StringBuilder replace( final StringBuilder str, final String pattern, final String replace )
	{
		int s = 0;
		int e = 0;
		final StringBuilder result = new StringBuilder();

		while ( ( e = str.indexOf( pattern, s ) ) >= 0 )
		{
			result.append( str.substring( s, e ) );
			result.append( replace );
			s = e + pattern.length();
		}
		result.append( str.substring( s ) );
		return result;
	}

	public static String string2Informix( final String s )
	{
		if ( s == null )
		{
			return "null";
		}
		else
		{
			return "'" + s + "'";
		}
	}

	public static String integer2Informix( final Integer i )
	{
		if ( i == null )
		{
			return "null";
		}
		else
		{
			return i.toString();
		}
	}

	public static String toInformixString( final Object o )
	{
		if ( o instanceof String )
		{
			return string2Informix( (String) o );
		}
		else if ( o instanceof Integer )
		{
			return integer2Informix( (Integer) o );
		}
		else if ( o instanceof Calendar )
		{
			return "'" + Fecha.calendar2DateHourString( (Calendar) o, '/', ':' ) + "'";
		}
		else if ( o instanceof BigDecimal )
		{
			return ( (BigDecimal) o ).toString();
		}
		else if ( o == null )
		{
			return "null";
		}
		else
		{
			return "<ERR>";
		}
	}

	public static String pintaQuery( final StringBuilder query, final Object[] parametros )
	{
		return pintaQuery( query.toString(), Arrays.asList( parametros ) );
	}

	public static String pintaQuery( final StringBuilder query, final List<Object> parametros )
	{
		return pintaQuery( query.toString(), parametros );
	}

	public static String pintaQuery( final String query, final List<Object> parametros )
	{
		String tmp = query;
		if ( parametros != null )
		{
			for ( final Object obj : parametros )
			{
				tmp = tmp.replaceFirst( "\\?", Cadenas.toInformixString( obj ) );
			}
		}
		return tmp;
	}

	public static String generaQuery( final StringBuilder query, final List<Object> parametros )
	{
		return pintaQuery( query, parametros.toArray() );
	}

	public static String generaQuery( final StringBuilder query, final Object[] parametros )
	{
		return pintaQuery( query, parametros );
	}

	public static String tildes2Html( final String cadena )
	{
		String str = cadena.replaceAll( "á", "&aacute;" );
		str = str.replaceAll( "é", "&eacute;" );
		str = str.replaceAll( "í", "&iacute;" );
		str = str.replaceAll( "ó", "&oacute;" );
		str = str.replaceAll( "ú", "&uacute;" );
		str = str.replaceAll( "Á", "&Aacute;" );
		str = str.replaceAll( "É", "&Eacute;" );
		str = str.replaceAll( "Í", "&Iacute;" );
		str = str.replaceAll( "Ó", "&Oacute;" );
		str = str.replaceAll( "Ú", "&Uacute;" );
		str = str.replaceAll( "à", "&agrave;" );
		str = str.replaceAll( "è", "&egrave;" );
		str = str.replaceAll( "ì", "&igrave;" );
		str = str.replaceAll( "ò", "&ograve;" );
		str = str.replaceAll( "ù", "&ugrave;" );
		str = str.replaceAll( "À", "&Agrave;" );
		str = str.replaceAll( "È", "&Egrave;" );
		str = str.replaceAll( "Ì", "&Igrave;" );
		str = str.replaceAll( "Ò", "&Ograve;" );
		str = str.replaceAll( "Ù", "&Ugrave;" );
		str = str.replaceAll( "ü", "&uuml;" );
		str = str.replaceAll( "Ü", "&Uuml;" );
		str = str.replaceAll( "ñ", "&ntilde;" );
		str = str.replaceAll( "Ñ", "&Ntilde;" );
		return str;
	}

	public static List<String> obtenListaVariables( final String cadena, final String delimitadorInicial, final String delimitadorFinal )
	{

		String str = cadena;
		final List<String> ret = new ArrayList<String>();
		final int longitudDelimitadorInicial = delimitadorInicial.length();
		final int longitudDelimitadorFinal = delimitadorFinal.length();

		while ( str.indexOf( delimitadorInicial ) > -1 )
		{
			str = str.substring( str.indexOf( delimitadorInicial ) + longitudDelimitadorInicial );
			if ( str.indexOf( delimitadorFinal ) > -1 )
			{
				ret.add( str.substring( 0, str.indexOf( delimitadorFinal ) ) );
				str = str.substring( str.indexOf( delimitadorFinal ) + longitudDelimitadorFinal );
			}
		}
		return ret;
	}

	public static String substituyeVariables( final String cadena, final String delimitadorInicial, final String delimitadorFinal )
	{
		String ret = cadena;
		final List<String> variables = obtenListaVariables( cadena, delimitadorInicial, delimitadorFinal );
		for ( final String variable : variables )
		{
			ret = ret.replace( delimitadorInicial + variable + delimitadorFinal, Mensajes.getStringSafe( variable ) );
		}
		return ret;
	}

	public static String generaClausulaIn( final List<String> valores, final String delimitador )
	{
		final StringBuilder in = new StringBuilder();
		for ( final String valor : valores )
		{
			in.append( delimitador ).append( valor ).append( delimitador ).append( ", " );
		}
		return in.toString().substring( 0, in.lastIndexOf( ", " ) );
	}

	/**
	 * Replace last.
	 *
	 * @param str
	 *            the str
	 * @param replace
	 *            the replace
	 * @param replacement
	 *            the replacement
	 * @return the string
	 */
	public static String replaceLast( final String str, final String replace, final String replacement )
	{
		final int ind = str.lastIndexOf( replace );
		if ( ind >= 0 )
		{
			return new StringBuilder( str ).replace( ind, ind + 1, replacement ).toString();
		}
		return str;
	}

	/**
	 * Método que indica si un mensaje se puede considerar similar, basado en la
	 * distancia de evenshtein con una variación máxima aceptada del
	 * DefaultGlobalNames.PORCENTAJE_DISTANCIA_LEVENSHTEIN_POR_DEFECTO %.
	 *
	 * @param texto1
	 *            the reference text
	 * @param texto2
	 *            the target text for the comparison
	 * @return true if the can be considered similar else false
	 */
	public static boolean esSimilar( final String texto1, final String texto2 )
	{
		return esSimilar( texto1, texto2, DefaultGlobalNames.PORCENTAJE_DISTANCIA_LEVENSHTEIN_POR_DEFECTO );
	}

	/**
	 * Método que indica si un mensaje se puede considerar similar, basado en la
	 * distancia de evenshtein con una variación máxima aceptada del N%.
	 *
	 * @param texto1
	 *            the reference text
	 * @param texto2
	 *            the target text for the comparison
	 * @param porcentajeDistancia
	 *            porcentaje máximo a partir del que no se consideran palabras
	 *            similares
	 * @return true if the can be considered similar else false
	 */
	public static boolean esSimilar( final String texto1, final String texto2, Integer porcentajeDistancia )
	{
		if ( porcentajeDistancia == null )
		{
			porcentajeDistancia = DefaultGlobalNames.PORCENTAJE_DISTANCIA_LEVENSHTEIN_POR_DEFECTO;
		}
		int threshold = Math.round( ( porcentajeDistancia / 100 ) * texto1.length() );
		if ( threshold == 0 )
		{
			threshold++;
		}
		final String tmp1 = normalizaParaComparacion( texto1 );
		final String tmp2 = normalizaParaComparacion( texto2 );
		final LevenshteinDistance levenshteinDistance = new LevenshteinDistance( threshold );
		final Integer distance = levenshteinDistance.apply( tmp1, tmp2 );
		if ( LOGGER.isDebugEnabled() )
		{
			LOGGER.debug( "\"" + tmp1 + "\" - \"" + tmp2 + "\" = " + distance.toString() );
		}
		return distance != -1;
	}

	public static String normalizaParaComparacion( final String cadena )
	{
		return StringUtils.stripAccents( StringUtils.trimToEmpty( cadena ) ).toUpperCase();
	}
}
