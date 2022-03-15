package com.capitanperegrina.common.i18n;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.context.i18n.LocaleContextHolder;

import com.capitanperegrina.common.exceptions.ServicioErrorException;
import com.capitanperegrina.common.utils.Cadenas;

public class Mensajes {
	static Logger log = Logger.getLogger(Mensajes.class);

	public static final String DEFAULT_BASE_NAME = "Mensajes";

	public static Properties getResourceBundle(final String basename, final Locale locale) {
		try {
			final InputStream utf8in = Mensajes.class.getClassLoader().getResourceAsStream(basename + "_" + locale.getLanguage() + ".properties");
			final Reader reader = new InputStreamReader(utf8in, "UTF-8");
			final Properties props = new Properties();
			props.load(reader);
			return props;
		} catch (final IOException e) {
			log.error("", e);
			throw new ServicioErrorException(e);
		}
	}

	public static ResourceBundle getResourceBundle() {
		return ResourceBundle.getBundle(DEFAULT_BASE_NAME, LocaleContextHolder.getLocale());
	}

	public static String getString(final String basename, final String key, final Object[] parameters,
					final String language, final String country, final Locale locale) {
		final Properties mensajes = getResourceBundle(basename, locale);

		if (mensajes != null) {
			String result;
			if (parameters == null) {
				try {
					result = mensajes.getProperty(key);
				} catch (final MissingResourceException e) {
					log.error("", e);
					return key;
				}
			} else {
				String patternString = null;

				try {
					patternString = mensajes.getProperty(key);
				} catch (final MissingResourceException e) {
					log.error("", e);
					return key;
				}

				final MessageFormat formatter = new MessageFormat(patternString);
				if (locale != null) {
					formatter.setLocale(locale);
				}
				result = formatter.format(parameters);
			}

			return result;
		} else {
			throw new ServicioErrorException("No se puede localizar el fichero de recursos " + basename + " para el idioma " + locale.getLanguage());
		}
	}

	public static String getStringSafe(final String key) {
		try {
			return getString(DEFAULT_BASE_NAME, key, null, null, null, LocaleContextHolder.getLocale());
		} catch (final Exception e) {
			log.warn(e.getMessage() + " key = " + Cadenas.toStringGenerico(key), e);
			return "";
		}
	}

	public static String getStringSafe(final String key, final Locale locale) {
		try {
			return getString(DEFAULT_BASE_NAME, key, null, null, null, locale);
		} catch (final Exception e) {
			log.warn(e.getMessage() + " key = " + Cadenas.toStringGenerico(key) + " locale = " + Cadenas.toStringGenerico(locale), e);
			return "";
		}
	}

	public static String getStringSafe(final String bundle, final String key, final Locale locale) {
		try {
			return getString(bundle, key, null, null, null, locale);
		} catch (final Exception e) {
			log.warn(e.getMessage() + " bundle = " + Cadenas.toStringGenerico(bundle) + " key = " + Cadenas.toStringGenerico(key) + " locale = " + Cadenas.toStringGenerico(locale), e);
			return "";
		}
	}

	public static String getStringSafe(final String bundle, final String key, final Object[] parameters,
					final Locale locale) {
		try {
			return getString(bundle, key, parameters, null, null, locale);
		} catch (final Exception e) {
			log.warn(e.getMessage() + " bundle = " + Cadenas.toStringGenerico(bundle) + " key = " + Cadenas.toStringGenerico(key) + " parameters = " + Cadenas.toStringGenerico(parameters) + " locale = " + Cadenas.toStringGenerico(locale), e);
			return "";
		}
	}

	public static String getStringSafe(final String key, final Object[] parameters, final Locale locale) {
		try {
			return getString(DEFAULT_BASE_NAME, key, parameters, null, null, locale);
		} catch (final Exception e) {
			log.warn(e.getMessage() + " key = " + Cadenas.toStringGenerico(key) + " parameters = " + Cadenas.toStringGenerico(parameters) + " locale = " + Cadenas.toStringGenerico(locale), e);
			return "";
		}
	}

	public static String getStringSafe(final Locale locale, final String key, final Object[] parameters) {
		try {
			return getString(DEFAULT_BASE_NAME, key, parameters, null, null, locale);
		} catch (final Exception e) {
			log.warn(e.getMessage() + " key = " + Cadenas.toStringGenerico(key) + " parameters = " + Cadenas.toStringGenerico(parameters) + " locale = " + Cadenas.toStringGenerico(locale), e);
			return "";
		}
	}
}
