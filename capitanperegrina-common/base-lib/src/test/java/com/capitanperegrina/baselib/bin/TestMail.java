package com.capitanperegrina.baselib.bin;

import org.apache.log4j.Logger;

import com.capitanperegrina.common.net.mail.Emailer;

public class TestMail {

	static Logger log = Logger.getLogger(TestMail.class);

	public static void main(final String[] args) {

		try {

			final Emailer m = new Emailer();
			m.setAuth("true");
			m.setLogin("spinnakersimple@spinnakersimple.com");
			m.setPass("Atos082018!!!");
			m.setSmtpHost("mail.spinnakersimple.com");
			m.setSmtpPort(465);
			m.setProtocol("smtps");
			m.setTls("true");
			m.setTimeoutConnect("3000");
			m.setTimeoutSend("3000");
			m.setRemitentePorDefecto("spinnakersimple@spinnakersimple.com");
			m.inicializa();

			m.sendEmail("spinnakersimple@spinnakersimple.com", "yo@capitanperegrina.com", "Prueba", "Esto es el mensaje", false, null, null);

		} catch (final Exception e) {
			log.error( e.getMessage(), e );
		}
	}
}
