package com.capitanperegrina.common.net.mail;

import java.util.List;
import java.util.Properties;

import javax.activation.DataSource;
import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;

import org.apache.log4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.capitanperegrina.common.exceptions.ServicioErrorException;
import com.capitanperegrina.common.exceptions.ServicioException;
import com.capitanperegrina.common.validators.Validadores;

public class Emailer {
	static final Logger log = Logger.getLogger(Emailer.class);

	public static final String CLAVE_MAIL_ADMINISTRADOR_PARA_CONTACTO = "mail.administrador";

	private String auth;
	private String login;
	private String pass;
	private String protocol = "smtp";
	private String smtpHost;
	private Integer smtpPort;
	private String tls;
	private String timeoutConnect;
	private String timeoutSend;
	private String remitentePorDefecto;
	private String nombreRemitentePorDefecto;
	private String bcc;

	private JavaMailSender mailSender;

	/**
	 * Método que carga la configuración del enviador para la aplicación.
	 */
	@PostConstruct
	public void inicializa() {
		log.debug("Emailer.inicializa()");

		try {
			final JavaMailSenderImpl impl = new JavaMailSenderImpl();
			final Properties prop = new Properties();
			impl.setHost(this.smtpHost);
			impl.setPort(this.smtpPort);
			impl.setUsername(this.login);
			impl.setPassword(this.pass);
			impl.setProtocol(this.protocol);
			prop.setProperty("mail.smtp.auth", this.auth);
			prop.setProperty("mail.smtp.starttls.enable", this.tls);
			prop.setProperty("mail.smtp.connectiontimeout", this.timeoutConnect);
			prop.setProperty("mail.smtp.timeout", this.timeoutSend);
			impl.setJavaMailProperties(prop);
			this.mailSender = impl;
		} catch (final Exception e) {
			log.fatal("Error configurando el enviador de correos electrónicos.", e);
			throw new ServicioErrorException("", "Emailer.inicializa.error", e);
		}
	}

	/**
	 * Método que envía un mensaje de correo electrónico.
	 *
	 * @param remite       Remitente del mensaje.
	 * @param destinatario Destinatario del mensaje.
	 * @param asunto       Asunto del mensaje
	 * @param mensaje      Cuerpo del mensaje (puede ser plano o html).
	 * @param html         true => el mensaje tiene código html / false => el
	 *                     mensaje es texto plano.
	 * @param adjuntos     Lista de archivos que se deben adjuntar al mensaje.
	 * @throws ServicioException Si se produce un error en el envío.
	 */
	public void sendEmail(final String remite, final String destinatario, final String asunto, final String mensaje,
					final boolean html, final List<FicheroAdjunto> adjuntos, final String replyTo)
					throws ServicioException {
		log.debug("- Emailer.sendEmail()");
		try {
			final MimeMessage message = this.mailSender.createMimeMessage();
			final MimeMessageHelper helper = new MimeMessageHelper(message, true);

			String from = Emailer.this.remitentePorDefecto;
			String nom = Emailer.this.nombreRemitentePorDefecto;
			if (!Validadores.estaVacia(from)) {
				from = remite;
				nom = from;
			}
			helper.setFrom(new InternetAddress(from, nom));
			helper.setTo(destinatario);
			if (this.bcc != null) {
				helper.setBcc(this.bcc);
			}
			if (replyTo != null) {
				helper.setReplyTo(replyTo);
			}

			helper.setSubject(MimeUtility.encodeText(asunto, "utf-8", null));
			helper.setText(mensaje, html);

			if (adjuntos != null) {
				for (final FicheroAdjunto f : adjuntos) {
					final DataSource dataSource = new ByteArrayDataSource(f.getContenido(), f.getContentType());
					helper.addAttachment(f.getNombreFichero(), dataSource);
				}
			}

			this.mailSender.send(message);
		} catch (final MessagingException e) {
			log.warn("No se puede enviar un correo a " + destinatario, e);
			throw new ServicioException(e);
		} catch (final Exception e) {
			log.warn("Error desconocido al intentar enviar un correo a " + destinatario, e);
			throw new ServicioException(e);
		}
		log.debug("- Emailer.sendEmail()");
	}

	public void sendEmail(final List<MailObject> mails) throws ServicioException {

		try {
			final MimeMessagePreparator[] preparators = new MimeMessagePreparator[mails.size()];
			int i = 0;
			for (final MailObject mail : mails) {
				preparators[i++] = new MimeMessagePreparator() {
					@Override
					public void prepare(final MimeMessage mimeMessage) throws Exception {
						final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
						message.setSubject(MimeUtility.encodeText(mail.getAsunto(), "utf-8", null));
						String from = Emailer.this.remitentePorDefecto;
						String nom = Emailer.this.nombreRemitentePorDefecto;
						if (!Validadores.estaVacia(mail.getRemite())) {
							from = mail.getRemite();
							if (!Validadores.estaVacia(mail.getNombreRemite())) {
								nom = mail.getNombreRemite();
							} else {
								nom = from;
							}
						}
						message.setFrom(new InternetAddress(from, nom));
						message.setTo(mail.getDestinatario());
						if (mail.getReplyTo() != null) {
							message.setReplyTo(mail.getReplyTo());
						}
						message.setText(mail.getMensaje(), mail.isHtml());

						if (mail.getAdjuntos() != null) {
							for (final FicheroAdjunto f : mail.getAdjuntos()) {
								final DataSource dataSource = new ByteArrayDataSource(f.getContenido(), f.getContentType());
								message.addAttachment(f.getNombreFichero(), dataSource);
							}
						}
					}
				};
			}
			this.mailSender.send(preparators);
		} catch (final MailException e) {
			throw new ServicioException(e);
		} catch (final Exception e) {
			log.warn("Error desconocido al intentar enviar correos.", e);
			throw new ServicioException(e);
		}
	}

	public String getAuth() {
		return this.auth;
	}

	public void setAuth(final String auth) {
		this.auth = auth;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(final String login) {
		this.login = login;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(final String pass) {
		this.pass = pass;
	}

	public String getSmtpHost() {
		return this.smtpHost;
	}

	public void setSmtpHost(final String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public Integer getSmtpPort() {
		return this.smtpPort;
	}

	public void setSmtpPort(final Integer smtpPort) {
		this.smtpPort = smtpPort;
	}

	public String getTls() {
		return this.tls;
	}

	public void setTls(final String tls) {
		this.tls = tls;
	}

	public String getTimeoutConnect() {
		return this.timeoutConnect;
	}

	public void setTimeoutConnect(final String timeoutConnect) {
		this.timeoutConnect = timeoutConnect;
	}

	public String getTimeoutSend() {
		return this.timeoutSend;
	}

	public void setTimeoutSend(final String timeoutSend) {
		this.timeoutSend = timeoutSend;
	}

	public String getRemitentePorDefecto() {
		return this.remitentePorDefecto;
	}

	public void setRemitentePorDefecto(final String remitentePorDefecto) {
		this.remitentePorDefecto = remitentePorDefecto;
	}

	public JavaMailSender getMailSender() {
		return this.mailSender;
	}

	public void setMailSender(final JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public String getBcc() {
		return this.bcc;
	}

	public void setBcc(final String bcc) {
		this.bcc = bcc;
	}

	public String getProtocol() {
		return this.protocol;
	}

	public void setProtocol(final String protocol) {
		this.protocol = protocol;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Emailer [auth=").append(this.auth).append(", login=").append(this.login).append(", pass=").append(this.pass).append(", protocol=").append(this.protocol).append(", smtpHost=").append(this.smtpHost).append(", smtpPort=").append(this.smtpPort).append(", tls=").append(this.tls).append(", timeoutConnect=").append(this.timeoutConnect).append(", timeoutSend=").append(this.timeoutSend).append(", remitentePorDefecto=").append(this.remitentePorDefecto).append(", nombreRemitentePorDefecto=").append(this.nombreRemitentePorDefecto).append(", bcc=").append(this.bcc).append(", mailSender=").append(this.mailSender).append("]");
		return builder.toString();
	}

	public String getNombreRemitentePorDefecto() {
		return this.nombreRemitentePorDefecto;
	}

	public void setNombreRemitentePorDefecto(final String nombreRemitentePorDefecto) {
		this.nombreRemitentePorDefecto = nombreRemitentePorDefecto;
	}
}
