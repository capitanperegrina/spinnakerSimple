package com.capitanperegrina.common.web;

import java.io.IOException;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.capitanperegrina.common.utils.Cadenas;
import com.capitanperegrina.common.validators.Validadores;

/**
 * Clase para acceder a la información referente a la aplicación.
 */
public class ContextoAplicacion {

	static Logger log = Logger.getLogger(ContextoAplicacion.class);

	private ContextoAplicacion() {
	}

	public static String getBuildValue() {
		String buildApp = "";
		try {
			InitialContext initialContext = new InitialContext();
			buildApp = (String) initialContext.lookup("java:comp/env/Build");
		} catch (NamingException ne) {
			log.warn("", ne);
		}
		return buildApp;
	}

	public static String getVersionDelPom() {
		String version = "";
		try {
			final Properties properties = new Properties();
			properties.load(ContextoAplicacion.class.getClassLoader().getResourceAsStream("project.properties"));
			version = properties.getProperty("version");
		} catch (IOException e) {
			log.error("", e);
		}
		return version;
	}

	public static String obtenVersion() {
		String version = getVersionDelPom();
		if (Validadores.estaVacia(version)) {
			version = getBuildValue();
		}
		return Cadenas.trimNoNulo(version);
	}
}
