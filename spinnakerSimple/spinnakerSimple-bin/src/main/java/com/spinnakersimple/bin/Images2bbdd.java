//package com.spinnakersimple.bin;
//
//import java.awt.Graphics2D;
//import java.awt.RenderingHints;
//import java.awt.geom.AffineTransform;
//import java.awt.image.AffineTransformOp;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.imageio.ImageIO;
//
//import org.apache.log4j.Logger;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.capitanperegrina.common.exceptions.ServicioErrorException;
//import com.capitanperegrina.common.utils.Base64;
//import com.capitanperegrina.common.validators.Validadores;
//import com.spinnakersimple.modelo.dao.AnuncioDao;
//import com.spinnakersimple.modelo.dao.FotografiaDao;
//import com.spinnakersimple.modelo.entidad.tabla.AnuncioPOJO;
//import com.spinnakersimple.modelo.entidad.tabla.FotografiaPOJO;
//
//public class Images2bbdd {
//
//	private static final Logger log = Logger.getLogger(Images2bbdd.class);
//
//	private static final int IMG_WIDTH = 1024;
//	private static final int IMG_HEIGHT = 768;
//
//	public static final String RUTA = "C:/Users/USUARIO/Documents/desa/java/workspaceCNG/spinnakerSimple/spinnakerSimple-web/src/main/webapp/imagenes/velas";
//
//	/**
//	 * @param image The image to be scaled
//	 * @param imageType Target image type, e.g. TYPE_INT_RGB
//	 * @param newWidth The required width
//	 * @param newHeight The required width
//	 * @return The scaled image
//	 */
//	public static BufferedImage scaleImage(BufferedImage image, int imageType, int newWidth, int newHeight) {
//		// Make sure the aspect ratio is maintained, so the image is not
//		// distorted
//		final double thumbRatio = (double) newWidth / (double) newHeight;
//		final int imageWidth = image.getWidth(null);
//		final int imageHeight = image.getHeight(null);
//		final double aspectRatio = (double) imageWidth / (double) imageHeight;
//
//		if (thumbRatio < aspectRatio) {
//			newHeight = (int) (newWidth / aspectRatio);
//		} else {
//			newWidth = (int) (newHeight * aspectRatio);
//		}
//
//		// Draw the scaled image
//		final BufferedImage newImage = new BufferedImage(newWidth, newHeight, imageType);
//		final Graphics2D graphics2D = newImage.createGraphics();
//		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//		graphics2D.drawImage(image, 0, 0, newWidth, newHeight, null);
//
//		return newImage;
//	}
//
//	public static BufferedImage getScaledImage(BufferedImage image, int width, int height) throws IOException {
//		final int imageWidth = image.getWidth();
//		final int imageHeight = image.getHeight();
//
//		final double scaleX = (double) width / imageWidth;
//		final double scaleY = (double) height / imageHeight;
//		final AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX, scaleY);
//		final AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform,
//				AffineTransformOp.TYPE_BILINEAR);
//
//		return bilinearScaleOp.filter(image, new BufferedImage(width, height, image.getType()));
//	}
//
//	public static byte[] procesaArchivo(File f) {
//
//		final StringBuilder msg = new StringBuilder();
//		try {
//			final BufferedImage in = ImageIO.read(f);
//
//			final Float ancho = Float.parseFloat("" + in.getWidth());
//			final Float alto = Float.parseFloat("" + in.getHeight());
//			int nuevoAncho;
//			int nuevoAlto;
//			if (ancho >= alto) {
//				final float factor = IMG_WIDTH / ancho;
//				nuevoAncho = IMG_WIDTH;
//				nuevoAlto = new Float(new Float(alto).floatValue() * factor).intValue();
//			} else {
//				final float factor = IMG_HEIGHT / alto;
//				nuevoAlto = IMG_HEIGHT;
//				nuevoAncho = new Float(new Float(ancho).floatValue() * factor).intValue();
//			}
//			msg.append(f.getName() + "( " + ancho + " x " + alto + ") ----> (" + nuevoAncho + " x " + nuevoAlto + " )");
//			final BufferedImage out = scaleImage(in, BufferedImage.TYPE_INT_RGB, nuevoAncho, nuevoAlto);
//			// ImageIO.write(out, "jpg", new File(f.getParent() + "/out/" +
//			// f.getName()));
//			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			ImageIO.write(out, "jpg", baos);
//			baos.flush();
//			final byte[] imageInByte = baos.toByteArray();
//			baos.close();
//			log.debug(msg.toString());
//			return imageInByte;
//
//		} catch (final Exception e) {
//			log.error("", e);
//			throw new ServicioErrorException(e);
//		}
//	}
//
//	public static void main(String[] args) {
//
//		try {
//			final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
//
//			final AnuncioDao anuncioDao = (AnuncioDao) ctx.getBean("anuncioDao");
//			final FotografiaDao fotografiaDao = (FotografiaDao) ctx.getBean("fotografiaDao");
//
//			// final List<AnuncioPOJO> anuncios = anuncioDao.buscaTodos();
//			final List<AnuncioPOJO> anuncios = new ArrayList<AnuncioPOJO>();
//
//			for (final AnuncioPOJO a : anuncios) {
//
//				log.debug(a.toString());
//				if (!Validadores.estaVacia(a.getImagen1())) {
//					final File f = new File(RUTA + "/" + a.getImagen1());
//					final FotografiaPOJO foto = new FotografiaPOJO();
//					foto.setIdAnuncio(a.getIdAnuncio());
//					foto.setTipo(f.getPath().substring(f.getPath().lastIndexOf('.') + 1).toLowerCase());
//					if (foto.getTipo().equals("jpeg")) {
//						foto.setTipo("jpg");
//					}
//					foto.setImagen(procesaArchivo(f));
//					fotografiaDao.crea(foto);
//				}
//				if (!Validadores.estaVacia(a.getImagen2())) {
//					final File f = new File(RUTA + "/" + a.getImagen2());
//					final FotografiaPOJO foto = new FotografiaPOJO();
//					foto.setIdAnuncio(a.getIdanuncio());
//					foto.setTipo(f.getPath().substring(f.getPath().lastIndexOf('.') + 1).toLowerCase());
//					if (foto.getTipo().equals("jpeg")) {
//						foto.setTipo("jpg");
//					}
//					foto.setImagen(procesaArchivo(f));
//					fotografiaDao.crea(foto);
//				}
//				if (!Validadores.estaVacia(a.getImagen3())) {
//					final File f = new File(RUTA + "/" + a.getImagen3());
//					final FotografiaPOJO foto = new FotografiaPOJO();
//					foto.setIdAnuncio(a.getIdanuncio());
//					foto.setTipo(f.getPath().substring(f.getPath().lastIndexOf('.') + 1).toLowerCase());
//					if (foto.getTipo().equals("jpeg")) {
//						foto.setTipo("jpg");
//					}
//					foto.setImagen(procesaArchivo(f));
//					fotografiaDao.crea(foto);
//				}
//				if (!Validadores.estaVacia(a.getImagen4())) {
//					final File f = new File(RUTA + "/" + a.getImagen4());
//					final FotografiaPOJO foto = new FotografiaPOJO();
//					foto.setIdAnuncio(a.getIdanuncio());
//					foto.setTipo(f.getPath().substring(f.getPath().lastIndexOf('.') + 1).toLowerCase());
//					if (foto.getTipo().equals("jpeg")) {
//						foto.setTipo("jpg");
//					}
//					foto.setImagen(procesaArchivo(f));
//					fotografiaDao.crea(foto);
//				}
//			}
//
//			File f;
//			final byte[] s;
//			StringBuilder ret;
//
//			// SPI
//			f = new File("C:/churreraOut/simetrico.jpg");
//			ret = new StringBuilder("data:image/jpg;base64,");
//			ret.append(Base64.encodeBytes(procesaArchivo(f)));
//			log.debug("SPI : " + ret.toString());
//			/*
//			 * // ASIM f = new File("C:/churreraOut/asimetrico.jpg"); ret = new
//			 * StringBuilder("data:image/jpg;base64,");
//			 * ret.append(Base64.encodeBytes(procesaArchivo(f))); log.debug(
//			 * "ASIM : " + ret.toString());
//			 *
//			 * // MAYOR f = new File("C:/churreraOut/mayor.jpg"); ret = new
//			 * StringBuilder("data:image/jpg;base64,");
//			 * ret.append(Base64.encodeBytes(procesaArchivo(f))); log.debug(
//			 * "MAYOR : " + ret.toString());
//			 *
//			 * // FOQUE f = new File("C:/churreraOut/genova.jpg"); ret = new
//			 * StringBuilder("data:image/jpg;base64,");
//			 * ret.append(Base64.encodeBytes(procesaArchivo(f))); log.debug(
//			 * "FOQUE : " + ret.toString());
//			 */
//		} catch (final Exception e) {
//			log.error("", e);
//		}
//	}
//}
