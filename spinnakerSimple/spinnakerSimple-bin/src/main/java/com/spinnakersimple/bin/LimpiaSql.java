package com.spinnakersimple.bin;

import org.apache.log4j.Logger;

import com.capitanperegrina.common.utils.files.Ficheros;

public class LimpiaSql {

	static Logger log = Logger.getLogger(LimpiaSql.class);

	public static void main(final String[] args) {
		try {
			String str = Ficheros.file2String("C:/churreraOut/spinnake_atlantico.sql");
			str = str.replaceAll("ÃƒÃ¡", "á");
			str = str.replaceAll("ÃƒÂ©", "e");
			str = str.replaceAll("ÃƒÂ­", "i");
			str = str.replaceAll("ÃƒÂ¶", "o");
			str = str.replaceAll("ÃƒÃº", "u");
			str = str.replaceAll("ÃƒÃ±", "n");
			str = str.replaceAll("Ã¡", "á");
			str = str.replaceAll("Ã©", "é");
			str = str.replaceAll("Ã­", "í");
			str = str.replaceAll("Ã³", "ó");
			str = str.replaceAll("Ãº", "ú");
			str = str.replaceAll("Ã‰", "É");
			str = str.replaceAll("Ã“", "Ó");
			str = str.replaceAll("Ã±", "ñ");
			str = str.replaceAll("Ã‘", "Ñ");
			str = str.replaceAll("Ã¶", "ø");
			str = str.replaceAll("\\'", "'");
			Ficheros.string2File("C:/churreraOut/2spinnake_atlantico.sql", str);
			log.debug(str);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
