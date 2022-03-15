package com.spinnakersimple.bin;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.capitanperegrina.social.instagram.ClienteInstagram;
import com.spinnakersimple.modelo.dao.FotografiaDao;
import com.spinnakersimple.modelo.dao.impl.FotografiaDaoImpl;
import com.spinnakersimple.modelo.entidad.FotografiaPOJO;

public class SocialTesting {

	public static void main(final String[] args) {
		try {

			final ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
			final FotografiaDao fDao = ctx.getBean(FotografiaDaoImpl.class);
			FotografiaPOJO f = new FotografiaPOJO();
			f.setIdFotografia(22);
			f = fDao.busca(f);

			// ClienteTwitter.twit("Hola mundo", f.getIdAnuncio().toString(), f.getImagen());
			ClienteInstagram.post("Hola mundo", f.getIdAnuncio().toString(), f.getImagen());

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
