package com.capitanperegrina.common.seguridad;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capitanperegrina.common.config.DefaultParameters;
import com.capitanperegrina.common.config.DefaultParametersDef;
import com.capitanperegrina.common.exceptions.ServicioErrorException;
import com.capitanperegrina.common.utils.Base64;

/**
 * Utilidades de criptografía.
 */
@Component
public class Criptografia {

	/** The params. */
	@Autowired
	DefaultParameters params;

	/** The log. */
	private static Logger log = Logger.getLogger(Criptografia.class);

	/** The Constant ALGORITMO_DE_ENCRIPTACION. */
	private static final String ALGORITMO_DE_ENCRIPTACION = "DES/ECB/PKCS5Padding";

	/**
	 * Instantiates a new criptografia.
	 */
	private Criptografia() {
	}

	/**
	 * Encriptar.
	 *
	 * @param cadena
	 *            the cadena
	 * @return the string
	 */
	public String encriptar(final String cadena) {
		return encriptar(cadena, this.params.getParameter(DefaultParametersDef.SEMILLA_ENCRIPTACION));
	}

	/**
	 * Desencriptar.
	 *
	 * @param cadenaEncriptada
	 *            the cadena encriptada
	 * @return the string
	 */
	public String desencriptar(final String cadenaEncriptada) {
		return desencriptar(cadenaEncriptada, this.params.getParameter(DefaultParametersDef.SEMILLA_ENCRIPTACION));
	}

	/**
	 * Encripta una cadena utilizando una clave de encriptación y codifica la
	 * respuesta como Base64.
	 *
	 * @param cadena
	 *            cadena que se desea encriptar
	 * @param semilla
	 *            semilla de encriptación
	 * @return la cadena encriptada
	 */
	private String encriptar(final String cadena, final String semilla) {
		String cadenaEncriptada = null;

		try {
			final SecretKeySpec desKey = new SecretKeySpec(
					new String((semilla.trim().concat("99999999")).substring(0, 8)).getBytes(), "DES");
			final Cipher cipher = Cipher.getInstance(ALGORITMO_DE_ENCRIPTACION);
			cipher.init(Cipher.ENCRYPT_MODE, desKey);
			final byte[] claveEncriptadaBytes = cipher.doFinal(cadena.getBytes());
			cadenaEncriptada = Base64.encodeBytes(claveEncriptadaBytes);
		} catch (final GeneralSecurityException e) {
			log.error("", e);
			throw new ServicioErrorException("Criptografia.encriptar.GeneralSecurityException", e);
		}

		return cadenaEncriptada;
	}

	/**
	 * Desencripta una cadena codificada como Base64 utilizando una clave de
	 * encriptación.
	 *
	 * @param cadenaEncriptada
	 *            cadena a desencriptar
	 * @param semilla
	 *            semilla de encriptación
	 * @return cadena desencriptada
	 */
	private String desencriptar(final String cadenaEncriptada, final String semilla) {
		String claveDesencriptada = null;

		try {
			final byte[] claveDesc = Base64.decode(cadenaEncriptada);

			final SecretKeySpec desKey = new SecretKeySpec(
					new String((semilla.trim().concat("99999999")).substring(0, 8)).getBytes(), "DES");
			final Cipher cipher = Cipher.getInstance(ALGORITMO_DE_ENCRIPTACION);
			cipher.init(Cipher.DECRYPT_MODE, desKey);
			claveDesencriptada = new String(cipher.doFinal(claveDesc));
		} catch (final IOException e) {
			log.error(e.getMessage());
			throw new ServicioErrorException("Criptografia.desencriptar.IOException", false, e);
		} catch (final GeneralSecurityException e) {
			log.error(e.getMessage());
			throw new ServicioErrorException("Criptografia.desencriptar.GeneralSecurityException", false, e);
		}
		return claveDesencriptada;
	}

	/**
	 * Codifica para url.
	 *
	 * @param cadena
	 *            the cadena
	 * @return the string
	 */
	public String codificaParaUrl(final String cadena) {
		return Base64.encodeBytes(encriptar(cadena).getBytes());
	}

	/**
	 * Descodifica para url.
	 *
	 * @param cadena
	 *            the cadena
	 * @return the string
	 */
	public String descodificaParaUrl(final String cadena) {
		return desencriptar(new String(Base64.decode(cadena.getBytes())));
	}
}
