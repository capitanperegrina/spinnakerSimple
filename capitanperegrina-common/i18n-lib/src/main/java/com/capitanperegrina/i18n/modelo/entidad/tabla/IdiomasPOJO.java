package com.capitanperegrina.i18n.modelo.entidad.tabla;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * POJO identificado con la tabla <code>idiomas</code>
 */
public class IdiomasPOJO implements Serializable {
	private static final long serialVersionUID = -33787242930L;

	protected String lang;
	protected String nombreIdioma;

	/**
	 * Constructor por defecto.
	 */
	public IdiomasPOJO() {
		super();
	}

	public String getLang() {
		return this.lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getNombreIdioma() {
		return this.nombreIdioma;
	}

	public void setNombreIdioma(String nombreIdioma) {
		this.nombreIdioma = nombreIdioma;
	}

	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
		return ReflectionToStringBuilder.toString(this);
	}
}