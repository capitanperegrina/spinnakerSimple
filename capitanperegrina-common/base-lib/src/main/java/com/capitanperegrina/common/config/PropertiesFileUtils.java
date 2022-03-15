package com.capitanperegrina.common.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.capitanperegrina.common.exceptions.ServicioErrorException;
import com.capitanperegrina.common.utils.Cadenas;

public class PropertiesFileUtils {

	static Logger log = Logger.getLogger(PropertiesFileUtils.class);

	/**
	 * Lee un archivo .properties y devuelve su contenido como un mapa.
	 * 
	 * @param filename
	 *            Archivo a leer
	 * @param delimiter
	 *            Delimitador que separa clave de valor
	 * @return Mapa con el contenido del archivo de propiedades
	 * @throws Exception
	 */
	public static Map<String, String> readPropertiesFileAsMap(String filename, String delimiter) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = reader.readLine()) != null) {
				line = Cadenas.trimNoNulo(line);
				if (line.trim().length() == 0 || line.charAt(0) == '#') {
					continue;
				}
				int delimPosition = line.indexOf(delimiter);
				String key = line.substring(0, delimPosition).trim();
				String value = line.substring(delimPosition + 1).trim();
				map.put(key, value);
			}
			reader.close();
			return map;
		} catch (Exception e) {
			log.error("", e);
			throw new ServicioErrorException(e);
		}
	}
}
