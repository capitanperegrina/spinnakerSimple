package com.spinnakersimple.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;

/**
 * The Class FiltroBusquedaVelaBean.
 */
public class FiltroBusquedaVelaBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8663632584116612482L;

    protected Integer tipoBarco;

    protected Integer tipoClase;

    /** The tipo vela. */
    protected Integer tipoVela;

    /** The titulo. */
    protected String titulo;

    /** The gratilmax. */
    protected BigDecimal gratilmax;

    /** The gratilmin. */
    protected BigDecimal gratilmin;

    /** The balumamax. */
    protected BigDecimal balumamax;

    /** The balumamin. */
    protected BigDecimal balumamin;

    /** The pujamenmax. */
    protected BigDecimal pujamenmax;

    /** The pujamenmin. */
    protected BigDecimal pujamenmin;

    protected BigDecimal caidaProamax;
    protected BigDecimal caidaProamin;

    protected BigDecimal caidaPopamax;
    protected BigDecimal caidaPopamin;

    protected BigDecimal superficiemax;
    protected BigDecimal superficiemin;

    protected String tipoCometa;

    protected Integer regInicial;

    protected Integer numRegistros;

    /** The pendientes. */
    protected String[] tipoAnuncio;

    protected String ordenarPor;

    protected String tipoOrdenacion;

    protected String pais;

    /**
     * Instantiates a new comprar vela form DTO.
     */
    public FiltroBusquedaVelaBean() {
	super();
    }

    public String[] getTipoAnuncio() {
	return this.tipoAnuncio;
    }

    public void setTipoAnuncio(final String[] tipoAnuncio) {
	this.tipoAnuncio = tipoAnuncio;
    }

    public Integer getTipoVela() {
	return this.tipoVela;
    }

    public void setTipoVela(final Integer tipoVela) {
	this.tipoVela = tipoVela;
    }

    public String getTitulo() {
	return this.titulo;
    }

    public void setTitulo(final String titulo) {
	this.titulo = titulo;
    }

    public BigDecimal getGratilmax() {
	return this.gratilmax;
    }

    public void setGratilmax(final BigDecimal gratilmax) {
	this.gratilmax = gratilmax;
    }

    public BigDecimal getGratilmin() {
	return this.gratilmin;
    }

    public void setGratilmin(final BigDecimal gratilmin) {
	this.gratilmin = gratilmin;
    }

    public BigDecimal getBalumamax() {
	return this.balumamax;
    }

    public void setBalumamax(final BigDecimal balumamax) {
	this.balumamax = balumamax;
    }

    public BigDecimal getBalumamin() {
	return this.balumamin;
    }

    public void setBalumamin(final BigDecimal balumamin) {
	this.balumamin = balumamin;
    }

    public BigDecimal getPujamenmax() {
	return this.pujamenmax;
    }

    public void setPujamenmax(final BigDecimal pujamenmax) {
	this.pujamenmax = pujamenmax;
    }

    public BigDecimal getPujamenmin() {
	return this.pujamenmin;
    }

    public void setPujamenmin(final BigDecimal pujamenmin) {
	this.pujamenmin = pujamenmin;
    }

    public String getOrdenarPor() {
	return this.ordenarPor;
    }

    public void setOrdenarPor(final String ordenarPor) {
	this.ordenarPor = ordenarPor;
    }

    public String getTipoOrdenacion() {
	return this.tipoOrdenacion;
    }

    public void setTipoOrdenacion(final String tipoOrdenacion) {
	this.tipoOrdenacion = tipoOrdenacion;
    }

    public Integer getTipoBarco() {
	return this.tipoBarco;
    }

    public void setTipoBarco(final Integer tipoBarco) {
	this.tipoBarco = tipoBarco;
    }

    public Integer getTipoClase() {
	return this.tipoClase;
    }

    public void setTipoClase(final Integer tipoClase) {
	this.tipoClase = tipoClase;
    }

    public BigDecimal getCaidaProamax() {
	return this.caidaProamax;
    }

    public void setCaidaProamax(final BigDecimal caidaProamax) {
	this.caidaProamax = caidaProamax;
    }

    public BigDecimal getCaidaProamin() {
	return this.caidaProamin;
    }

    public void setCaidaProamin(final BigDecimal caidaProamin) {
	this.caidaProamin = caidaProamin;
    }

    public BigDecimal getCaidaPopamax() {
	return this.caidaPopamax;
    }

    public void setCaidaPopamax(final BigDecimal caidaPopamax) {
	this.caidaPopamax = caidaPopamax;
    }

    public BigDecimal getCaidaPopamin() {
	return this.caidaPopamin;
    }

    public void setCaidaPopamin(final BigDecimal caidaPopamin) {
	this.caidaPopamin = caidaPopamin;
    }

    public BigDecimal getSuperficiemax() {
	return this.superficiemax;
    }

    public void setSuperficiemax(final BigDecimal superficiemax) {
	this.superficiemax = superficiemax;
    }

    public BigDecimal getSuperficiemin() {
	return this.superficiemin;
    }

    public void setSuperficiemin(final BigDecimal superficiemin) {
	this.superficiemin = superficiemin;
    }

    public String getTipoCometa() {
	return this.tipoCometa;
    }

    public String getPais() {
	return this.pais;
    }

    public void setPais(final String pais) {
	this.pais = pais;
    }

    public void setTipoCometa(final String tipoCometa) {
	this.tipoCometa = tipoCometa;
    }

    public Integer getRegInicial() {
	return this.regInicial;
    }

    public void setRegInicial(final Integer regInicial) {
	this.regInicial = regInicial;
    }

    public Integer getNumRegistros() {
	return this.numRegistros;
    }

    public void setNumRegistros(final Integer numRegistros) {
	this.numRegistros = numRegistros;
    }

    @Override
    public String toString() {
	final StringBuilder builder = new StringBuilder();
	builder.append("FiltroBusquedaVelaBean [tipoBarco=").append(this.tipoBarco).append(", tipoClase=")
		.append(this.tipoClase).append(", tipoVela=").append(this.tipoVela).append(", titulo=")
		.append(this.titulo).append(", gratilmax=").append(this.gratilmax).append(", gratilmin=")
		.append(this.gratilmin).append(", balumamax=").append(this.balumamax).append(", balumamin=")
		.append(this.balumamin).append(", pujamenmax=").append(this.pujamenmax).append(", pujamenmin=")
		.append(this.pujamenmin).append(", caidaProamax=").append(this.caidaProamax).append(", caidaProamin=")
		.append(this.caidaProamin).append(", caidaPopamax=").append(this.caidaPopamax).append(", caidaPopamin=")
		.append(this.caidaPopamin).append(", superficiemax=").append(this.superficiemax)
		.append(", superficiemin=").append(this.superficiemin).append(", tipoCometa=").append(this.tipoCometa)
		.append(", regInicial=").append(this.regInicial).append(", numRegistros=").append(this.numRegistros)
		.append(", tipoAnuncio=").append(Arrays.toString(this.tipoAnuncio)).append(", ordenarPor=")
		.append(this.ordenarPor).append(", tipoOrdenacion=").append(this.tipoOrdenacion).append(", pais=")
		.append(this.pais).append("]");
	return builder.toString();
    }
}
