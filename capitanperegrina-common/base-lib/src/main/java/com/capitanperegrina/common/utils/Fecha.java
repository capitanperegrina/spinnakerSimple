package com.capitanperegrina.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.exceptions.ServicioErrorException;
import com.capitanperegrina.common.validators.Validadores;

/**
 * Clase utilidad para manipular fechas
 */
public class Fecha {
	static Logger log = Logger.getLogger(Fecha.class);

	private static final String PATRON_SELLO = "yyyy-MM-dd'T'HH:mm:ss";
	private static final String PATRON_FECHA = "dd/MM/yyyy";
	private static final String PATRON_FECHA_HORA_PARA_FICHERO = "yyyyMMdd'T'HHmmss";

	/**
	 * calendar2bbdd.
	 * 
	 * @param timestamp
	 *            Timestamp a convertir.
	 * @return Devuelve un Calendar a partir del timestamp. (Usado por los DAOs)
	 */
	public static Calendar bbdd2Calendar(Timestamp timestamp) {
		Calendar fecha = null;
		if (timestamp != null) {
			fecha = Calendar.getInstance();
			fecha.setTime(timestamp);
		}
		return fecha;
	}

	/**
	 * calendar2bbdd.
	 * 
	 * @param fecha
	 *            Fecha a convertir.
	 * @return Devuelve un Timestamp para almacenar en base de datos. (Usado por
	 *         los DAOs)
	 */
	public static Timestamp calendar2bbdd(Calendar fecha) {
		if (fecha == null) {
			return null;
		} else {
			return new Timestamp(fecha.getTimeInMillis());
		}
	}

	public static Date calendar2Date(Calendar cal) {
		if (cal != null) {
			return new Date(cal.getTimeInMillis());
		} else {
			return null;
		}
	}

	public static String calendar2DateHourString(Calendar fecha) {
		return calendar2DateHourString(fecha, '/', ':');
	}

	/**
	 * Convierte un dato de tipo Calendar a algo mostrable en las funciones de
	 * debug de las clases VO y CVO.
	 * 
	 * @param fecha
	 *            Fecha que se desea formatear
	 * @param sepFecha
	 *            Carácter separador de los campos de la fecha.
	 * @param sepHora
	 *            Carácter separador de los campos de la hora.
	 * @return Una cadena formateada con la fecha.
	 */
	public static String calendar2DateHourString(Calendar fecha, char sepFecha, char sepHora) {
		if (fecha == null) {
			return "";
		} else {
			return calendar2DateStringDDMMYYYY(fecha, sepFecha) + " " + calendar2HoraString(fecha, sepHora);
		}
	}

	/**
	 * Convierte un dato de tipo Calendar a algo mostrable en las funciones de
	 * debug de las clases VO y CVO. formato DDMMYYYY
	 * 
	 * @param fecha
	 *            Fecha que se desea formatear
	 * @param sepFecha
	 *            Carácter separador de los campos de la fecha.
	 * @param sepHora
	 *            Carácter separador de los campos de la hora.
	 * @return Una cadena formateada con la fecha.
	 */
	public static String calendar2DateHourStringDDMMYYYY(Calendar fecha, char sepFecha, char sepHora) {
		if (fecha == null) {
			return "";
		} else {
			return calendar2DateStringDDMMYYYY(fecha, sepFecha) + " " + calendar2HoraString(fecha, sepHora);
		}
	}

	/**
	 * calendar2DateIntegerYYYYMMDD.
	 * 
	 * @param fecha
	 *            Fecha a convertir.
	 * @return Devuelve el entero (YYYYMMDD) que corresponde con la fecha del
	 *         Calendar
	 */
	public static long calendar2DateIntegerYYYYMMDD(Calendar fecha) {
		return fecha.get(Calendar.YEAR) * 10000 + (fecha.get(Calendar.MONTH) + 1) * 100
				+ fecha.get(Calendar.DAY_OF_MONTH) * 1;
	}

	/**
	 * Calendar2StringDDMMYY.
	 * 
	 * @param Calendar.
	 *            El objeto q se desea formatear
	 * @param caracter.
	 *            El caracter separador
	 * @return Devuelve un String en formato DD<caracter>MM<caracter>YYYY
	 */
	public static String calendar2DateStringDDMMYY(Calendar fecha) {
		if (fecha == null) {
			return null;
		}
		// Nos apoyamos en la funcion programada
		final String fechaString = calendar2DateStringDDMMYYYY(fecha);
		return fechaString.substring(0, 2) + fechaString.substring(2, 4) + fechaString.substring(6, 8);
	}

	/**
	 * calendar2DateStringDDMMYYYY.
	 * 
	 * @param fecha
	 *            Fecha a convertir.
	 * @return Devuelve un String en formato DDMMYYYY
	 */
	public static String calendar2DateStringDDMMYYYY(Calendar fecha) {
		if (fecha == null) {
			return null;
		}

		final Integer diaInt = new Integer(fecha.get(Calendar.DAY_OF_MONTH));
		final Integer mesInt = new Integer(fecha.get(Calendar.MONTH) + 1);
		final Integer anioInt = new Integer(fecha.get(Calendar.YEAR));

		String diaString = diaInt.toString();
		String mesString = mesInt.toString();
		final String anioString = anioInt.toString();

		// Por si es un número de un sólo digito
		if (diaString.length() == 1) {
			diaString = "0" + diaString;
		}

		// Por si es un número de un sólo digito
		if (mesString.length() == 1) {
			mesString = "0" + mesString;
		}

		return diaString + mesString + anioString;
	}

	/**
	 * calendar2DateStringDDMMYYYY.
	 * 
	 * @param fecha
	 *            Fecha a convertir.
	 * @param caracter
	 *            El caracter separador
	 * @return Devuelve un String en formato DD<caracter>MM<caracter>YYYY
	 */
	public static String calendar2DateStringDDMMYYYY(Calendar fecha, char caracter) {
		if (fecha == null) {
			return null;
		}

		// Nos apoyamos en la funcion programada
		final String fechaString = calendar2DateStringDDMMYYYY(fecha);

		return fechaString.substring(0, 2) + caracter + fechaString.substring(2, 4) + caracter
				+ fechaString.substring(4, 8);
	}

	public static String calendar2HoraCortaString(Calendar fecha, char caracter) {

		if (fecha == null) {
			return null;
		}
		final String horaString = calendar2HourString(fecha);
		return horaString.substring(0, 2) + caracter + horaString.substring(2, 4);
	}

	/**
	 * calendar2HourString.
	 * 
	 * @param fecha
	 *            El objeto q se desea formatear
	 * @return Devuelve un String en formato HH<caracter>MM<caracter>SS
	 */
	public static String calendar2HoraString(Calendar fecha, char caracter) {

		if (fecha == null) {
			return null;
		}
		final String horaString = calendar2HourString(fecha);
		return horaString.substring(0, 2) + caracter + horaString.substring(2, 4) + caracter
				+ horaString.substring(4, 6);
	}

	public static String calendar2HourMinutSegunString(Calendar fecha) {

		if (fecha == null) {
			return null;
		}

		final Integer horaInt = new Integer(fecha.get(Calendar.HOUR_OF_DAY));
		final Integer minutoInt = new Integer(fecha.get(Calendar.MINUTE));
		final Integer segundoInt = new Integer(fecha.get(Calendar.SECOND));

		String horaString = horaInt.toString();
		String minutoString = minutoInt.toString();
		String segundosString = segundoInt.toString();

		// Por si es un número de un sólo digito
		if (horaString.length() == 1) {
			horaString = "0" + horaString;
		}

		if (minutoString.length() == 1) {
			minutoString = "0" + minutoString;
		}

		if (segundosString.length() == 1) {
			segundosString = "0" + segundosString;
		}

		return horaString + minutoString + segundosString;
	}

	public static String calendar2HourMinutSegunString(Calendar fecha, char caracter) {

		if (fecha == null) {
			return null;
		}
		final String horaString = calendar2HourMinutSegunString(fecha);
		return horaString.substring(0, 2) + caracter + horaString.substring(2, 4) + caracter
				+ horaString.substring(4, 6);
	}

	/**
	 * calendar2HourString
	 * 
	 * @param fecha
	 *            El objeto Calendar cuya hora desea formatear.
	 * @return Devuelve un String en formato HHMMSS
	 */
	public static String calendar2HourString(Calendar fecha) {

		if (fecha == null) {
			return null;
		}

		final Integer horaInt = new Integer(fecha.get(Calendar.HOUR_OF_DAY));
		final Integer minutoInt = new Integer(fecha.get(Calendar.MINUTE));
		final Integer segundoInt = new Integer(fecha.get(Calendar.SECOND));

		String horaString = horaInt.toString();
		String minutoString = minutoInt.toString();
		String segundoString = segundoInt.toString();

		// Por si es un número de un sólo digito
		if (horaString.length() == 1) {
			horaString = "0" + horaString;
		}
		if (minutoString.length() == 1) {
			minutoString = "0" + minutoString;
		}
		if (segundoString.length() == 1) {
			segundoString = "0" + segundoString;
		}

		return horaString + minutoString + segundoString;
	}

	/**
	 * calendar2IntegerDDMMYYYY.
	 * 
	 * @param fecha
	 *            Fecha a convertir.
	 * @return Devuelve el entero (DDMMYYYY) que corresponde con la fecha del
	 *         Calendar.
	 */
	public static long calendar2IntegerDDMMYYYY(Calendar fecha) {
		return fecha.get(Calendar.YEAR) * 1 + (fecha.get(Calendar.MONTH) + 1) * 10000
				+ fecha.get(Calendar.DAY_OF_MONTH) * 1000000;
	}

	public static String calendar2NombreFicheroString(Calendar fecha) {

		if (fecha == null) {
			return null;
		}
		final SimpleDateFormat df = new SimpleDateFormat(PATRON_FECHA_HORA_PARA_FICHERO);
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
		final Date fechaDate = calendar2Date(fecha);
		return df.format(fechaDate);
	}

	/**
	 * Crea una cadena con la fecha actual siguiendo el patron
	 * yyyy-MM-dd'T'HH:mm:ss.
	 */
	public static String crearTsp() {
		final SimpleDateFormat df = new SimpleDateFormat(PATRON_SELLO);
		return df.format(new Date());
	}

	/**
	 * Crea una cadena con la fecha actual siguiendo el patron
	 * yyyy-MM-dd'T'HH:mm:ss.
	 */
	public static String crearTsp(Calendar fecha) {
		final Date date = calendar2Date(fecha);
		final SimpleDateFormat df = new SimpleDateFormat(PATRON_SELLO);
		return df.format(date);
	}

	/**
	 * Crea una cadena con la fecha actual siguiendo el patron
	 * yyyy-MM-dd'T'HH:mm:ss.
	 */
	public static String crearTsp(Date fecha) {
		final SimpleDateFormat df = new SimpleDateFormat(PATRON_SELLO);
		return df.format(fecha);
	}

	/**
	 * convierte una fecha Date en una fecha Calendar
	 * 
	 * @param d
	 *            fecha entrada
	 * @return la fecha en formato Calendar
	 */
	public static Calendar date2Calendar(Date d) {
		final Calendar c = Calendar.getInstance();
		c.setTime(d);
		return c;
	}

	public static Collection<Calendar> getDiasEntreFechasCollectionString(Calendar fecha_inicio, Calendar fecha_final) {
		final Collection<Calendar> ret = new ArrayList<Calendar>();

		if (fecha_inicio == null || fecha_final == null) {
			return ret;
		}

		final Calendar ini = Fecha.soloLaFecha(fecha_inicio);
		final Calendar fin = Fecha.soloLaFecha(fecha_final);

		while (ini.before(fin) || ini.equals(fin)) {
			ret.add((Calendar) ini.clone());
			ini.add(Calendar.DAY_OF_YEAR, 1);
		}
		return ret;
	}

	public static Calendar horaCortaString2Calendar(String s) {
		if (s != null) {
			try {
				final String patron = "H:m";
				final SimpleDateFormat formatter = new SimpleDateFormat(patron);
				final Calendar c = Calendar.getInstance();
				c.setTime(formatter.parse(s));
				return c;
			} catch (final ParseException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	public static Calendar horaString2Calendar(String s) {
		if (s != null) {
			try {
				final String patron = "HH:mm:ss";
				final SimpleDateFormat formatter = new SimpleDateFormat(patron);
				final Calendar c = Calendar.getInstance();
				c.setTime(formatter.parse(s));
				return c;
			} catch (final ParseException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	public static long milisegundosEntreFechas(Calendar fechaInicial, Calendar fechaFinal) {
		return fechaFinal.getTimeInMillis() - fechaInicial.getTimeInMillis();
	}

	public static String nombreMes(int mes) {
		return nombreMes(mes, DefaultGlobalNames.IDIOMA_DEFECTO);
	}

	public static String nombreMes(int mes, String lang) {
		try {
			return UtilMensajes.getString("Global.mes." + mes, new Locale(lang));
		} catch (final ServicioErrorException e) {
			return Roman.toRoman(mes);
		}
	}

	public static Calendar parse(int dia, int mes, int ano) {
		final StringBuffer sb = new StringBuffer("");
		sb.append(Formateador.fill("" + dia, '0', 2, Formateador.IZQUIERDA)).append("/")
				.append(Formateador.fill("" + mes, '0', 2, Formateador.IZQUIERDA)).append("/")
				.append(Formateador.fill("" + ano, '0', 4, Formateador.IZQUIERDA));

		return string2Calendar(sb.toString());
	}

	public static Calendar soloLaFecha(Calendar fecha) {
		return string2Calendar(calendar2DateStringDDMMYYYY(fecha, '/'));
	}

	public static Calendar soloLaHora(Calendar hora) {
		return horaCortaString2Calendar(calendar2HoraString(hora, ':'));
	}

	/**
	 * Esta funci&oacute;n converte unha cadea nun dato de tipo
	 * calendar,primeiro damoslle unha cadea patron pola cal vamoslle indicar
	 * como &eacute; o formato no que vamos a pasar a data, son este patron
	 * convertira a cadea nun dato Calendar
	 * 
	 * @param String
	 *            cadea: a cadea co formato da data
	 * @return Calendar c: Calendar cos datos entrantes
	 * @throws null
	 *             en caso de erro na conversi&oacute;n por exemplo se a cadea
	 *             non esta dentro dos parametros do patr&oacute;n
	 */
	public static Calendar string2Calendar(String s) {
		try {
			final String patron = PATRON_FECHA;
			final SimpleDateFormat formatter = new SimpleDateFormat(patron);
			final Calendar c = Calendar.getInstance();
			c.setTime(formatter.parse(s));
			return c;
		} catch (final Exception e1) {
			try {
				final String patron = "ddMMyyyy";
				final SimpleDateFormat formatter = new SimpleDateFormat(patron);
				final Calendar c = Calendar.getInstance();
				c.setTime(formatter.parse(s));
				return c;
			} catch (final Exception e2) {
				return null;
			}
		}
	}

	public static Calendar string2Calendar(String fecha, String hora) {
		String fecha_completa = "";

		if (!Validadores.estaVacia(fecha)) {
			if (!Validadores.estaVacia(hora)) {
				fecha_completa = Cadenas.trimNoNulo(fecha) + " " + Cadenas.trimNoNulo(hora);
			} else {
				fecha_completa = Cadenas.trimNoNulo(fecha);
			}
		} else {
			if (!Validadores.estaVacia(hora)) {
				fecha_completa = Cadenas.trimNoNulo(fecha) + " " + Cadenas.trimNoNulo(hora);
			} else {
				return null;
			}
		}
		return stringDateHour2Calendar(fecha_completa);
	}

	/**
	 * Convierte una cadena en una fecha
	 * 
	 * @param dateInString
	 *            cadena con la fecha a convertir
	 * @return la fecha
	 */
	public static Date string2Date(String dateInString) {
		final SimpleDateFormat formatter = new SimpleDateFormat(PATRON_FECHA);
		try {
			return formatter.parse(dateInString);
		} catch (final ParseException e) {
			log.error(e);
			throw new ServicioErrorException(e);
		}
	}

	public static Calendar stringDateHour2Calendar(String s) {
		try {
			final String patron = "dd/MM/yyyy HH:mm:ss";
			final SimpleDateFormat formatter = new SimpleDateFormat(patron);
			final Calendar c = Calendar.getInstance();
			c.setTime(formatter.parse(s));
			return c;
		} catch (final Exception e) {
			try {
				final String patron = "dd/MM/yyyy HH:mm";
				final SimpleDateFormat formatter = new SimpleDateFormat(patron);
				final Calendar c = Calendar.getInstance();
				c.setTime(formatter.parse(s));
				return c;
			} catch (final Exception ee) {
				try {
					final String patron = PATRON_FECHA;
					final SimpleDateFormat formatter = new SimpleDateFormat(patron);
					final Calendar c = Calendar.getInstance();
					c.setTime(formatter.parse(s));
					return c;
				} catch (final Exception eee) {
					try {
						final String patron = "HH:mm";
						final SimpleDateFormat formatter = new SimpleDateFormat(patron);
						final Calendar c = Calendar.getInstance();
						c.setTime(formatter.parse(s));
						return c;
					} catch (final Exception eeee) {
						return null;
					}
				}
			}
		}
	}

	public static Calendar tsp2Calendar(String tsp) {
		final Date d = tsp2Date(tsp);
		if (d != null) {
			return date2Calendar(d);
		} else {
			return null;
		}
	}

	public static Date tsp2Date(String tsp) {
		if (!Validadores.estaVacia(tsp)) {
			try {
				final SimpleDateFormat formatter = new SimpleDateFormat(PATRON_SELLO);
				return formatter.parse(tsp);
			} catch (final ParseException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * Constructor.
	 */
	private Fecha() {
	}
}