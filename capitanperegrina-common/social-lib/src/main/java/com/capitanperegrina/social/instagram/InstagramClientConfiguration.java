package com.capitanperegrina.social.instagram;

import java.io.Serializable;

public class InstagramClientConfiguration implements Serializable {

	private static final long serialVersionUID = -5491479201593884254L;

	private String usuario;
	private String password;

	public InstagramClientConfiguration() {
		super();
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("InstagramClientConfiguration [usuario=").append(this.usuario).append(", password=").append(this.password).append("]");
		return builder.toString();
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}
}
