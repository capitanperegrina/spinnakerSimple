package com.capitanperegrina.social.instagram;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramUploadPhotoRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramConfigurePhotoResult;

import com.capitanperegrina.common.utils.Imagenes;

public class ClienteInstagram {

    private static Logger log = Logger.getLogger(ClienteInstagram.class);

    public static final InstagramConfigurePhotoResult post(final String mensaje, final String imageName, final byte[] imageBytes)
            throws InstagramException {
        try {
            final InputStream in = new ByteArrayInputStream(imageBytes);
            BufferedImage imagen = ImageIO.read(in);

            // Normalizamos a cuadrado
            int lado = 0;
            int x = 0;
            int y = 0;
            if (imagen.getHeight() > imagen.getWidth()) {
                // Vertical
                lado = imagen.getWidth();
                y = (imagen.getHeight() - lado) / 2;
            } else {
                // Horizontal
                lado = imagen.getHeight();
                ;
                x = (imagen.getWidth() - lado) / 2;
            }
            final Rectangle cuadro = new Rectangle(x, y, lado, lado);
            imagen = Imagenes.crop(imagen, cuadro);

            final Instagram4j instagram = InstagramClientFactory.getTwitterInstance();
            final InstagramUploadPhotoRequest req = new InstagramUploadPhotoRequest(imagen, mensaje, imageName);
            final InstagramConfigurePhotoResult res = instagram.sendRequest(req);
            log.info(res.getStatus());
            return res;
        } catch (final Exception e) {
            log.error("Excepción publicando en Instagram: " + e.getMessage());
            return null;
            // throw new InstagramException("Excepción publicando en Instagram", e);
        }
    }
}
