package com.capitanperegrina.common.utils;

import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.exceptions.ServicioErrorException;

/**
 * A Class Imagenes.
 *
 * @author Carlos N&uacute;ñez Garc&iacute;a
 */
public class Imagenes extends Panel {

	/** The log. */
	static Logger log = Logger.getLogger(Imagenes.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2986701362698097458L;

	/**
	 * Crea unha instancia da clase.
	 */
	private Imagenes() {
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Convierte una imagen a una cadena base64.
	 *
	 * @param url url de la imagen
	 * @return cadena con la imagen codificada en base64
	 */
	public static String imgToBase64(final URL url) {
		try {
			final byte[] imageBytes = IOUtils.toByteArray(url);
			return Base64.encodeBytes(imageBytes);
		} catch (final IOException e) {
			log.error("", e);
			throw new ServicioErrorException(e);
		}
	}

	/**
	 * Img to base 64.
	 *
	 * @param tipo  the tipo
	 * @param bytes the bytes
	 * @return the string
	 */
	public static String imgToBase64(final String tipo, final byte[] bytes) {
		final StringBuilder ret = new StringBuilder("data:image/" + tipo + ";base64,");
		ret.append(Base64.encodeBytes(bytes));
		return ret.toString();
	}

	/**
	 * Resize image.
	 *
	 * @param in       the in
	 * @param type     the type
	 * @param newWidth the new width
	 * @return the byte[]
	 */
	public static byte[] resizeImage(final byte[] in, final String contentType, final Integer newWidth) {
		try {
			final InputStream is = new ByteArrayInputStream(in);
			final BufferedImage imageIn = ImageIO.read(is);

			final Float ancho = Float.valueOf(imageIn.getWidth());
			final Float alto = Float.valueOf(imageIn.getHeight());

			final float factor = newWidth / ancho;
			final int nuevoAncho = newWidth;
			final int nuevoAlto = Math.round(alto * factor);

			final BufferedImage imageOut = scaleImage(imageIn, nuevoAncho, nuevoAlto);

			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(imageOut, DefaultGlobalNames.contentType2type.get(contentType), baos);
			baos.flush();
			final byte[] ret = baos.toByteArray();
			baos.close();
			return ret;
		} catch (final IOException e) {
			log.error("", e);
			throw new ServicioErrorException(e);
		}
	}

	/**
	 * Scale image.
	 *
	 * @param image     the image
	 * @param newWidth  the new width
	 * @param newHeight the new height
	 * @return the buffered image
	 */
	private static BufferedImage scaleImage(final BufferedImage image, final int newWidth, final int newHeight) {
		final BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
		final Graphics2D g = newImage.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(image, 0, 0, newWidth, newHeight, null);
		return newImage;
	}

	public static BufferedImage crop(final BufferedImage src, final Rectangle tamano) {
		final BufferedImage dest = src.getSubimage(tamano.x, tamano.y, tamano.width, tamano.height);
		return dest;
	}
}
