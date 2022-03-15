package com.capitanperegrina.common.seguridad;

import java.util.Random;

import org.apache.log4j.Logger;

/**
 * Clase utilidad para generar cadenas aleatorias.
 */
public final class GeneradorClaves {

	/** The log. */
	static Logger log = Logger.getLogger(GeneradorClaves.class);

	/** The rn. */
	private static Random rn = new Random();

	/**
	 * Instantiates a new generador claves.
	 */
	private GeneradorClaves() {
	}

	/**
	 * Genera cadena random.
	 *
	 * @param caracteres
	 *            the caracteres
	 * @param length
	 *            the length
	 * @return the string
	 */
	public static String generaCadenaRandom(char[] caracteres, int length) {
		final StringBuilder ret = new StringBuilder();
		for (int i = 0; i < length; i++) {
			ret.append(caracteres[rn.nextInt(caracteres.length)]);
		}
		return ret.toString();
	}

	/**
	 * Método que genera una cadena de caracteres numéricos (0-9) de longitud
	 * <code>longitud</code> al azar.
	 *
	 * @param longitud
	 *            Longitud de la cadena de caracteres al azar deseada.
	 * @return pin de la longitud indicada
	 */
	public static String generaPin(int longitud) {
		return generaCadenaRandom(new char[] { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' }, longitud);
	}

	/**
	 * Método que genera una cadena de caracteres alfanúmericos (0-9, a-z, A-Z)
	 * de longitud <code>longitud</code> al azar.
	 *
	 * @param longitud
	 *            Longitud de la cadena de caracteres al azar deseada.
	 * @return códifo para url de la longitud indicada
	 */
	public static String generaCodigoURL(int longitud) {
		return generaCadenaRandom(new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
				'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
				'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4',
				'5', '6', '7', '8', '9', '0' }, longitud);
	}

	/**
	 * Método que genera una cadena de caracteres alfabéticos mayúsculas (A-Z)
	 * de longitud <code>longitud</code> al azar.
	 *
	 * @param longitud
	 *            Longitud de la cadena de caracteres al azar deseada.
	 * @return códifo para url de la longitud indicada
	 */
	public static String generaCodigoURLLetras(int longitud) {
		return generaCadenaRandom(new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
				'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' }, longitud);
	}

	/**
	 * Método que genera una cadena de caracteres alfabéticos mayúsculas (a-z,
	 * A-Z) de longitud <code>longitud</code> al azar.
	 *
	 * @param longitud
	 *            Longitud de la cadena de caracteres al azar deseada.
	 * @return códifo para url de la longitud indicada
	 */
	public static String generaCodigoURLSinCaracteresIguales(int longitud) {
		return generaCadenaRandom(new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P',
				'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '2', '3', '4', '5', '6', '7', '8',
				'9' }, longitud);
	}

	/**
	 * Método que genera una cadena de caracteres alfanumericos mayúsculas y
	 * minusculas excluyendo caracteres ambiguos como l y 1, 0 y O de longitud
	 * <code>longitud</code> al azar.
	 *
	 * @param longitud
	 *            Longitud de la cadena de caracteres al azar deseada.
	 * @return códifo para url de la longitud indicada
	 */
	public static String generaCodigoURLLetrasSinCaracteresIguales(int longitud) {
		return generaCadenaRandom(new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N', 'P', 'Q',
				'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9' }, longitud);
	}

	/**
	 * Genera password.
	 *
	 * @param longitud
	 *            the longitud
	 * @return the string
	 */
	public static String generaPassword(int longitud) {
		return generaCadenaRandom(new char[] { '!', '$', '%', '&', '/', '(', ')', '=', '-', '+', '*', '_', ':', 'A',
				'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
				'Z', '2', '3', '4', '5', '6', '7', '8', '9' }, longitud);
	}
}