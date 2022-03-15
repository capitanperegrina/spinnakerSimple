package com.capitanperegrina.i18n.modelo.entidad.tabla;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * POJO identificado con la tabla <code>paises</code>
 */
public class PaisesPOJO implements Serializable {
	private static final long serialVersionUID = 59199108272L;

	protected Integer codigo;
	protected String lang;
	protected String codigo2;
	protected String codigo3;
	protected String nombrePais;

	/**
	 * Constructor por defecto.
	 */
	public PaisesPOJO() {
		super();
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getLang() {
		return this.lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getCodigo2() {
		return this.codigo2;
	}

	public void setCodigo2(String codigo2) {
		this.codigo2 = codigo2;
	}

	public String getCodigo3() {
		return this.codigo3;
	}

	public void setCodigo3(String codigo3) {
		this.codigo3 = codigo3;
	}

	public String getNombrePais() {
		return this.nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}

	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
		return ReflectionToStringBuilder.toString(this);
	}
}