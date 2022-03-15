package com.spinnakersimple.web.ui.bean;

import java.io.Serializable;

import com.capitanperegrina.common.web.ui.CaptchaUI;

/**
 * The Class ComprarVelaUI.
 */
public class ComprarVelaUI extends CaptchaUI implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8663632584116612482L;

    protected String tipoBarco;

    protected String tipoClase;

    /** The tipo vela. */
    protected String tipoVela;

    /** The titulo. */
    protected String titulo;

    /** The gratilmax. */
    protected String gratilmax;

    /** The gratilmin. */
    protected String gratilmin;

    /** The balumamax. */
    protected String balumamax;

    /** The balumamin. */
    protected String balumamin;

    /** The pujamenmax. */
    protected String pujamenmax;

    /** The pujamenmin. */
    protected String pujamenmin;

    protected String caidaProaMax;
    protected String caidaProaMin;
    protected String caidaPopaMax;
    protected String caidaPopaMin;
    protected String superficieMax;
    protected String superficieMin;
    protected String tipoCometa;
    protected String regInicial;

    protected String genErr;

    protected String orden;

    protected String tipoOrden;

    protected String pais;

    /**
     * Instantiates a new comprar vela form DTO.
     */
    public ComprarVelaUI() {
	super();
    }

    /**
     * Gets the balumamax.
     *
     * @return the balumamax
     */
    public String getBalumamax() {
	return this.balumamax;
    }

    /**
     * Gets the balumamin.
     *
     * @return the balumamin
     */
    public String getBalumamin() {
	return this.balumamin;
    }

    /**
     * Gets the gratilmax.
     *
     * @return the gratilmax
     */
    public String getGratilmax() {
	return this.gratilmax;
    }

    /**
     * Gets the gratilmin.
     *
     * @return the gratilmin
     */
    public String getGratilmin() {
	return this.gratilmin;
    }

    /**
     * Gets the pujamenmax.
     *
     * @return the pujamenmax
     */
    public String getPujamenmax() {
	return this.pujamenmax;
    }

    /**
     * Gets the pujamenmin.
     *
     * @return the pujamenmin
     */
    public String getPujamenmin() {
	return this.pujamenmin;
    }

    /**
     * Gets the tipo vela.
     *
     * @return the tipo vela
     */
    public String getTipoVela() {
	return this.tipoVela;
    }

    /**
     * Gets the titulo.
     *
     * @return the titulo
     */
    public String getTitulo() {
	return this.titulo;
    }

    /**
     * Sets the balumamax.
     *
     * @param balumamax the new balumamax
     */
    public void setBalumamax(final String balumamax) {
	this.balumamax = balumamax;
    }

    /**
     * Sets the balumamin.
     *
     * @param balumamin the new balumamin
     */
    public void setBalumamin(final String balumamin) {
	this.balumamin = balumamin;
    }

    /**
     * Sets the gratilmax.
     *
     * @param gratilmax the new gratilmax
     */
    public void setGratilmax(final String gratilmax) {
	this.gratilmax = gratilmax;
    }

    /**
     * Sets the gratilmin.
     *
     * @param gratilmin the new gratilmin
     */
    public void setGratilmin(final String gratilmin) {
	this.gratilmin = gratilmin;
    }

    /**
     * Sets the pujamenmax.
     *
     * @param pujamenmax the new pujamenmax
     */
    public void setPujamenmax(final String pujamenmax) {
	this.pujamenmax = pujamenmax;
    }

    /**
     * Sets the pujamenmin.
     *
     * @param pujamenmin the new pujamenmin
     */
    public void setPujamenmin(final String pujamenmin) {
	this.pujamenmin = pujamenmin;
    }

    /**
     * Sets the tipo vela.
     *
     * @param tipoVela the new tipo vela
     */
    public void setTipoVela(final String tipoVela) {
	this.tipoVela = tipoVela;
    }

    /**
     * Sets the titulo.
     *
     * @param titulo the new titulo
     */
    public void setTitulo(final String titulo) {
	this.titulo = titulo;
    }

    public String getGenErr() {
	return this.genErr;
    }

    public void setGenErr(final String genErr) {
	this.genErr = genErr;
    }

    public String getOrden() {
	return this.orden;
    }

    public void setOrden(final String orden) {
	this.orden = orden;
    }

    public String getTipoOrden() {
	return this.tipoOrden;
    }

    public void setTipoOrden(final String tipoOrden) {
	this.tipoOrden = tipoOrden;
    }

    public String getCaidaProaMax() {
	return this.caidaProaMax;
    }

    public void setCaidaProaMax(final String caidaProaMax) {
	this.caidaProaMax = caidaProaMax;
    }

    public String getCaidaProaMin() {
	return this.caidaProaMin;
    }

    public void setCaidaProaMin(final String caidaProaMin) {
	this.caidaProaMin = caidaProaMin;
    }

    public String getCaidaPopaMax() {
	return this.caidaPopaMax;
    }

    public void setCaidaPopaMax(final String caidaPopaMax) {
	this.caidaPopaMax = caidaPopaMax;
    }

    public String getCaidaPopaMin() {
	return this.caidaPopaMin;
    }

    public void setCaidaPopaMin(final String caidaPopaMin) {
	this.caidaPopaMin = caidaPopaMin;
    }

    public String getSuperficieMax() {
	return this.superficieMax;
    }

    public void setSuperficieMax(final String superficieMax) {
	this.superficieMax = superficieMax;
    }

    public String getSuperficieMin() {
	return this.superficieMin;
    }

    public void setSuperficieMin(final String superficieMin) {
	this.superficieMin = superficieMin;
    }

    public String getTipoCometa() {
	return this.tipoCometa;
    }

    public void setTipoCometa(final String tipoCometa) {
	this.tipoCometa = tipoCometa;
    }

    public String getRegInicial() {
	return this.regInicial;
    }

    public void setRegInicial(final String regInicial) {
	this.regInicial = regInicial;
    }

    public String getTipoBarco() {
	return this.tipoBarco;
    }

    public void setTipoBarco(final String tipoBarco) {
	this.tipoBarco = tipoBarco;
    }

    public String getTipoClase() {
	return this.tipoClase;
    }

    public void setTipoClase(final String tipoClase) {
	this.tipoClase = tipoClase;
    }

    public String getPais() {
	return this.pais;
    }

    public void setPais(final String pais) {
	this.pais = pais;
    }

    @Override
    public String toString() {
	final StringBuilder builder = new StringBuilder();
	builder.append("ComprarVelaUI [tipoBarco=").append(this.tipoBarco).append(", tipoClase=").append(this.tipoClase)
		.append(", tipoVela=").append(this.tipoVela).append(", titulo=").append(this.titulo)
		.append(", gratilmax=").append(this.gratilmax).append(", gratilmin=").append(this.gratilmin)
		.append(", balumamax=").append(this.balumamax).append(", balumamin=").append(this.balumamin)
		.append(", pujamenmax=").append(this.pujamenmax).append(", pujamenmin=").append(this.pujamenmin)
		.append(", caidaProaMax=").append(this.caidaProaMax).append(", caidaProaMin=").append(this.caidaProaMin)
		.append(", caidaPopaMax=").append(this.caidaPopaMax).append(", caidaPopaMin=").append(this.caidaPopaMin)
		.append(", superficieMax=").append(this.superficieMax).append(", superficieMin=")
		.append(this.superficieMin).append(", tipoCometa=").append(this.tipoCometa).append(", regInicial=")
		.append(this.regInicial).append(", genErr=").append(this.genErr).append(", orden=").append(this.orden)
		.append(", tipoOrden=").append(this.tipoOrden).append(", pais=").append(this.pais).append("]");
	return builder.toString();
    }
}
