package com.capitanperegrina.common.auditoria;

import java.io.Serializable;
import java.util.Calendar;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Clase AuditoriaTabla.
 */
public abstract class AuditoriaTabla implements Serializable {
	
	private static final long serialVersionUID = 1545258965068256621L;

	/** A propidedade app alta. */
	protected String appAlta;

	/** A propidedade fec alta. */
	protected Calendar fecAlta;

	/** A propidedade us alta. */
	protected String usAlta;

	/** A propidedade app modif. */
	protected String appModif;

	/** A propidedade fec modif. */
	protected Calendar fecModif;

	/** A propidedade us modif. */
	protected String usModif;

	/**
	 * Instancia un novo obxecto da clase auditoria tabla.
	 */
	public AuditoriaTabla() {
		reset();
	}

	/**
	 * Reset.
	 */
	public void reset() {
		this.appAlta = null;
		this.fecAlta = null;
		this.usAlta = null;
		this.appModif = null;
		this.fecModif = null;
		this.usModif = null;
	}

	/**
	 * Crea.
	 * 
	 * @param codApp _cod_app
	 * @param uAlta  _u alta
	 */
	public void crea(final String codApp, final String uAlta) {
		if (codApp == null && uAlta == null) {
			this.fecAlta = null;
		} else {
			this.fecAlta = Calendar.getInstance();
		}
		this.appAlta = codApp;
		this.usAlta = uAlta;
	}

	/**
	 * Modifica.
	 * 
	 * @param codApp _cod_app
	 * @param uModif _u modif
	 */
	public void modifica(final String codApp, final String uModif) {
		if (codApp == null && uModif == null) {
			this.fecModif = null;
		} else {
			this.fecModif = Calendar.getInstance();
		}
		this.appModif = codApp;
		this.usModif = uModif;
	}

	/**
	 * Obt&eacute;n o valor da propiedade app alta.
	 * 
	 * @return o valor da propiedade app alta
	 */
	public String getAppAlta() {
		return this.appAlta;
	}

	/**
	 * Estabrece o valor da propiedade app alta.
	 * 
	 * @param appAlta o novo valor da propiedade app alta
	 */
	public void setAppAlta(final String appAlta) {
		this.appAlta = appAlta;
	}

	/**
	 * Obt&eacute;n o valor da propiedade fec alta.
	 * 
	 * @return o valor da propiedade fec alta
	 */
	public Calendar getFecAlta() {
		return this.fecAlta;
	}

	/**
	 * Estabrece o valor da propiedade fec alta.
	 * 
	 * @param fecAlta o novo valor da propiedade fec alta
	 */
	public void setFecAlta(final Calendar fecAlta) {
		this.fecAlta = fecAlta;
	}

	/**
	 * Obt&eacute;n o valor da propiedade us alta.
	 * 
	 * @return o valor da propiedade us alta
	 */
	public String getUsAlta() {
		return this.usAlta;
	}

	/**
	 * Estabrece o valor da propiedade us alta.
	 * 
	 * @param usAlta o novo valor da propiedade us alta
	 */
	public void setUsAlta(final String usAlta) {
		this.usAlta = usAlta;
	}

	/**
	 * Obt&eacute;n o valor da propiedade app modif.
	 * 
	 * @return o valor da propiedade app modif
	 */
	public String getAppModif() {
		return this.appModif;
	}

	/**
	 * Estabrece o valor da propiedade app modif.
	 * 
	 * @param appModif o novo valor da propiedade app modif
	 */
	public void setAppModif(final String appModif) {
		this.appModif = appModif;
	}

	/**
	 * Obt&eacute;n o valor da propiedade fec modif.
	 * 
	 * @return o valor da propiedade fec modif
	 */
	public Calendar getFecModif() {
		return this.fecModif;
	}

	/**
	 * Estabrece o valor da propiedade fec modif.
	 * 
	 * @param fecModif o novo valor da propiedade fec modif
	 */
	public void setFecModif(final Calendar fecModif) {
		this.fecModif = fecModif;
	}

	/**
	 * Obt&eacute;n o valor da propiedade us modif.
	 * 
	 * @return o valor da propiedade us modif
	 */
	public String getUsModif() {
		return this.usModif;
	}

	/**
	 * Estabrece o valor da propiedade us modif.
	 * 
	 * @param usModif o novo valor da propiedade us modif
	 */
	public void setUsModif(final String usModif) {
		this.usModif = usModif;
	}

	@Override
	public String toString() {
		ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
		return ReflectionToStringBuilder.toString(this);
	}
}
