package com.capitanperegrina.common.net.mail;

import java.io.Serializable;
import java.util.List;

public class MailObject implements Serializable {

	private static final long serialVersionUID = -6471777029105722933L;

	private String remite;
	private String nombreRemite;
	private String destinatario;
	private String asunto;
	private String mensaje;
	private boolean html;
	private List<FicheroAdjunto> adjuntos;
	private String replyTo;

	public MailObject() {
		super();
	}

	public String getRemite() {
		return this.remite;
	}

	public void setRemite(final String remite) {
		this.remite = remite;
	}

	public String getNombreRemite() {
		return this.nombreRemite;
	}

	public void setNombreRemite(final String nombreRemite) {
		this.nombreRemite = nombreRemite;
	}

	public String getDestinatario() {
		return this.destinatario;
	}

	public void setDestinatario(final String destinatario) {
		this.destinatario = destinatario;
	}

	public String getAsunto() {
		return this.asunto;
	}

	public void setAsunto(final String asunto) {
		this.asunto = asunto;
	}

	public String getMensaje() {
		return this.mensaje;
	}

	public void setMensaje(final String mensaje) {
		this.mensaje = mensaje;
	}

	public boolean isHtml() {
		return this.html;
	}

	public void setHtml(final boolean html) {
		this.html = html;
	}

	public List<FicheroAdjunto> getAdjuntos() {
		return this.adjuntos;
	}

	public void setAdjuntos(final List<FicheroAdjunto> adjuntos) {
		this.adjuntos = adjuntos;
	}

	public String getReplyTo() {
		return this.replyTo;
	}

	public void setReplyTo(final String replyTo) {
		this.replyTo = replyTo;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("MailObject [remite=").append(this.remite).append(", nombreRemite=").append(this.nombreRemite).append(", destinatario=").append(this.destinatario).append(", asunto=").append(this.asunto).append(", mensaje=").append(this.mensaje).append(", html=").append(this.html).append(", adjuntos=").append(this.adjuntos).append(", replyTo=").append(this.replyTo).append("]");
		return builder.toString();
	}
}
