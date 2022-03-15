package com.capitanperegrina.common.utils.files;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.log4j.Logger;

import com.capitanperegrina.common.exceptions.ServicioErrorException;

/**
 * Clase utilidad para procesar ficheros
 */
public class Ficheros {
	private static final Logger log = Logger.getLogger(Ficheros.class);

	private Ficheros() {
	}

	/**
	 * Copia de un fichero a otro.
	 *
	 * @param fromFile
	 *            fichero de origen
	 * @param toFile
	 *            fichero de destino
	 */
	public static void copyFile(final File fromFile, final File toFile) {
		log.debug("fromFile : " + fromFile.getPath() + "\ntoFile : " + toFile.getPath());

		try {
			final File fp = toFile.getParentFile();
			if (!fp.exists()) {
				fp.mkdirs();
			}
			final FileInputStream in = new FileInputStream(fromFile);
			final FileOutputStream fo = new FileOutputStream(toFile);
			final BufferedOutputStream out = new BufferedOutputStream(fo);
			final byte[] buffer = new byte[512];
			for (int i; (i = in.read(buffer)) > -1;) {
				out.write(buffer, 0, i);
			}
			out.close();
			fo.close();
			in.close();
		} catch (final FileNotFoundException e) {
			log.error("", e);
			throw new ServicioErrorException("Ficheros.copyFile.FileNotFoundException");
		} catch (final IOException e) {
			log.error("", e);
			throw new ServicioErrorException("Ficheros.copyFile.IOException");
		}
	}

	/**
	 * Mueve un archivo
	 *
	 * @param fromFile
	 *            origen
	 * @param toFile
	 *            destino
	 */
	public static void moveFile(final File fromFile, final File toFile) {
		copyFile(fromFile, toFile);
		if (!fromFile.delete()) {
			log.warn("No se pudo eliminar el archivo de origen al moverlo.");
		}
	}

	/**
	 * Guardar una cadena en un fichero
	 *
	 * @param nombreFichero
	 *            nombre del fichero
	 * @param contenido
	 *            contenido a guardar en el fichero
	 */
	public static void string2File(String nombreFichero, String contenido) {
		try {
			final File outFile = new File(nombreFichero);
			final FileWriter out = new FileWriter(outFile);
			out.write(contenido);
			out.close();
		} catch (final IOException e) {
			log.error("", e);
			throw new ServicioErrorException("Ficheros.string2File.IOException");
		}

	}

	/**
	 * Carga el contenido de un fichero en un string.
	 *
	 * @param pathFichero
	 *            fichero del que cargar.
	 * @return String cadena con el contenido del fichero.
	 */
	public static String file2String(String pathFichero) {
		final StringBuilder salida = new StringBuilder("");

		final File fichero = new File(pathFichero);

		if (!fichero.exists()) {
			log.warn("El fichero " + pathFichero + "no existe.");
		} else {
			if (!fichero.canRead()) {
				log.warn("El fichero " + pathFichero + "no se puede leer.");
			} else {
				salida.append(lee(fichero));
			}
		}
		return salida.toString();

	}

	private static String lee(File fichero) {
		try {
			final StringBuilder leido = new StringBuilder("");
			final FileReader reader = new FileReader(fichero);
			int caracter = reader.read();
			while (caracter >= 0) {
				final char charCaracter = (char) caracter;
				leido.append(charCaracter);
				caracter = reader.read();
			}
			reader.close();

			return leido.toString();
		} catch (final FileNotFoundException e) {
			log.error("", e);
			throw new ServicioErrorException("Ficheros.lee.FileNotFoundException");
		} catch (final IOException e) {
			log.error("", e);
			throw new ServicioErrorException("Ficheros.lee.IOException");
		}
	}

	/**
	 * Carga el contenido de un fichero en un string.
	 *
	 * @param url
	 *            url del fichero del que cargar.
	 * @return String cadena con el contenido del fichero.
	 */
	public static String file2String(URL url) {
		try {
			final StringBuilder sb = new StringBuilder();
			final BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				sb.append(inputLine);
			}
			in.close();

			return sb.toString();
		} catch (final IOException e) {
			log.error("", e);
			throw new ServicioErrorException("Ficheros.file2String.IOException");
		}
	}

	/**
	 * Comprueba si una ruta se corresponde con un directorio.
	 *
	 * @param directorio
	 *            ruta al supuesto directorio
	 * @return true si es un directorio, false si no lo es.
	 */
	public static boolean esDirectorio(String directorio) {
		return new File(directorio).isDirectory();
	}

	/**
	 * Comprueba si una ruta existe.
	 *
	 * @param directorio
	 *            ruta al supuesto directorio
	 * @return true si es un directorio, false si no lo es.
	 */
	public static boolean existe(String directorio) {
		return new File(directorio).exists();
	}

	/**
	 * Crea un directorio.
	 *
	 * @param directorio
	 *            a crear
	 */
	public static void creaDirectorio(String directorio) {
		if (!existe(directorio)) {
			final File dir = new File(directorio);
			dir.mkdirs();
		}
	}

	/**
	 * Calcula el tamaño de un directorio
	 *
	 * @param directory
	 *            directorio del que calcular el tamaño
	 * @return tamaño del directorio.
	 */
	public static long tamanoDirectorio(File directory) {
		long length = 0;
		for (final File file : directory.listFiles()) {
			if (file.isFile()) {
				length += file.length();
			} else {
				length += tamanoDirectorio(file);
			}
		}
		return length;
	}

	/**
	 * Copia un directorio a otro
	 *
	 * @param src
	 *            ruta de origen
	 * @param dest
	 *            ruta de destino
	 */
	public static void copyFolder(File src, File dest) {
		if (src.isDirectory()) {
			// el origen es un directorio

			// si el directorio de destino no existe se crea
			if (!dest.exists()) {
				dest.mkdir();
			}

			// lista el contenido del directorio
			final String[] files = src.list();

			for (final String file : files) {
				// copia cada uno de los ficheros
				final File srcFile = new File(src, file);
				final File destFile = new File(dest, file);
				copyFolder(srcFile, destFile);
			}
		} else {
			// el origen es un fichero
			copyFile(src, dest);
		}
	}

	public static byte[] leeArchivoBinario(String pathToFile) {
		try {
			final Path path = Paths.get(pathToFile);
			return Files.readAllBytes(path);
		} catch (final IOException e) {
			log.error("", e);
			throw new ServicioErrorException("Ficheros.leeArchivoBinario.IOException");
		}
	}

	public static byte[] leeArchivoBinario(URL url) throws IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream is = null;
		try {
			is = url.openStream();
			byte[] byteChunk = new byte[4096];
			int n;

			while ((n = is.read(byteChunk)) > 0) {
				baos.write(byteChunk, 0, n);
			}
			return baos.toByteArray();
		} catch (IOException e) {
			log.error("", e);
			throw e;
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}
}
