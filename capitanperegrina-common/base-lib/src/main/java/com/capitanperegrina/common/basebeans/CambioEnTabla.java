package com.capitanperegrina.common.basebeans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Locale;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CambioEnTabla implements Serializable {
	private static final long serialVersionUID = -2774946008399310308L;

	protected Integer usu;
	protected Calendar fec;
	protected String ip;
	protected Locale locale;

	public Integer getUsu() {
		return this.usu;
	}

	public void setUsu(final Integer usu) {
		this.usu = usu;
	}

	public Calendar getFec() {
		return this.fec;
	}

	public void setFec(final Calendar fec) {
		this.fec = fec;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(final String ip) {
		this.ip = ip;
	}

	public Locale getLocale() {
		return this.locale;
	}

	public void setLocale(final Locale locale) {
		this.locale = locale;
	}

	@Override
	public String toString() {
		ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
		return ReflectionToStringBuilder.toString(this);
	}
}
