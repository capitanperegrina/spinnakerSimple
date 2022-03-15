package com.capitanperegrina.common.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.capitanperegrina.common.exceptions.ServicioErrorException;

/**
 * @author Carlos N&uacute;ñez Garc&iacute;a
 */
public class CheckExecutionParametersManager {

	private static final Logger log = Logger.getLogger(CheckExecutionParametersManager.class);

	private static final String CONFIGURATION_FILE = "CheckExecutionParameters.properties";

	private static Map<String, String> parameters = new HashMap<String, String>();

	private CheckExecutionParametersManager() {
		super();
	}

	private static void leerMapa() {
		try {
			final ClassLoader classLoader = CheckExecutionParametersManager.class.getClassLoader();
			final InputStream inputStream = classLoader.getResourceAsStream(CONFIGURATION_FILE);
			final Properties properties = new Properties();
			properties.load(inputStream);
			inputStream.close();

			for (final String name : properties.stringPropertyNames()) {
				parameters.put(name, properties.getProperty(name));
			}
		} catch (final IOException e) {
			log.error("", e);
			throw new ServicioErrorException("CheckExecutionParametersManager.leerMapa.IOException", e);
		}
	}

	/**
	 * Reads a parameter from CheckExecutionParameters.properties file
	 *
	 * @param name Parameter to be readed.
	 * @return The value of the parameter.
	 */
	public static String getParameter(final String name) {
		CheckExecutionParametersManager.leerMapa();

		final String value = parameters.get(name);

		if (value == null) {
			final String[] args = { name };
			throw new ServicioErrorException("CheckExecutionParametersManager.leerMapa.IOException", args);
		}

		return value;
	}
}