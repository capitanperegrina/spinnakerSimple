package com.capitanperegrina.common.log;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log4jManager {

	/** The log. */
	private static Logger log = Logger.getLogger(Log4jManager.class);

	Log4jManager() {
		super();
	}

	public static boolean cambiaNivelLogger(String loggerName, String newLevel) {

		Level nivel = null;
		if ("OFF".equals(newLevel)) {
			nivel = Level.OFF;
		} else if ("FATAL".equals(newLevel)) {
			nivel = Level.FATAL;
		} else if ("ERROR".equals(newLevel)) {
			nivel = Level.ERROR;
		} else if ("WARN".equals(newLevel)) {
			nivel = Level.WARN;
		} else if ("INFO".equals(newLevel)) {
			nivel = Level.INFO;
		} else if ("DEBUG".equals(newLevel)) {
			nivel = Level.DEBUG;
		} else if ("TRACE".equals(newLevel)) {
			nivel = Level.TRACE;
		} else if ("ALL".equals(newLevel)) {
			nivel = Level.ALL;
		}

		try {
			Logger logger = LogManager.getLogger(loggerName);
			logger.setLevel(nivel);

			logger.trace("Test log en nivel trace - Este mensaje no es un error.");
			logger.debug("Test log en nivel debug - Este mensaje no es un error.");
			logger.info("Test log en nivel info - Este mensaje no es un error.");
			logger.warn("Test log en nivel warn - Este mensaje no es un error.");
			logger.error("Test log en nivel error - Este mensaje no es un error.");
			logger.fatal("Test log en nivel fatal - Este mensaje no es un error.");

			return true;
		} catch (Exception ex) {
			log.error("", ex);
			return false;
		}
	}
}
