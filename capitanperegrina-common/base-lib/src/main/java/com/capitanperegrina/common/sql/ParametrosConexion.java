package com.capitanperegrina.common.sql;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Clase con la información necesaria para establecer una conexión jdbc.
 */
public class ParametrosConexion implements Serializable {

	private static final long serialVersionUID = 2507660721584593627L;

	protected String driverClassName;
	protected String url;
	protected String user;
	protected String password;

	public String getDriverClassName() {
		return this.driverClassName;
	}

	public void setDriverClassName(final String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(final String user) {
		this.user = user;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
		return ReflectionToStringBuilder.toStringExclude(this, "password");
	}
}
