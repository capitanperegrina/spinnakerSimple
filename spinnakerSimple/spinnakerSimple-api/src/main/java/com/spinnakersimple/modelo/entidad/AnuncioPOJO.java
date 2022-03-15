package com.spinnakersimple.modelo.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

// TODO: Auto-generated Javadoc
/**
 * POJO identificado con la tabla <code>anuncio</code>.
 */
public class AnuncioPOJO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -84840367119L;

    /** The id anuncio. */
    protected Integer idAnuncio;

    /** The titulo anuncio. */
    protected String tituloAnuncio;

    /** The tipo barco. */
    protected Integer tipoBarco;

    /** The tipo vela. */
    protected Integer tipoVela;

    /** The gramaje. */
    protected BigDecimal gramaje;

    /** The gratil. */
    protected BigDecimal gratil;

    /** The baluma. */
    protected BigDecimal baluma;

    /** The pujamen. */
    protected BigDecimal pujamen;

    /** The caida proa. */
    protected BigDecimal caidaProa;

    /** The caida popa. */
    protected BigDecimal caidaPopa;

    /** The superficie. */
    protected BigDecimal superficie;

    /** The tipo cometa. */
    protected String tipoCometa;

    /** The descripcion. */
    protected String descripcion;

    /** The precio. */
    protected BigDecimal precio;

    /** The pais. */
    protected String pais;

    /** The caduca. */
    protected Calendar caduca;

    /** The visible. */
    protected Integer visible;

    /** The listado. */
    protected Integer listado;

    /** The visto. */
    protected Integer visto;

    /** The comentado. */
    protected Integer comentado;

    /** The destacado. */
    protected Integer destacado;

    /** The id usuario alta. */
    protected Integer idUsuarioAlta;

    /** The fec alta. */
    protected Calendar fecAlta;

    /** The id usuario modif. */
    protected Integer idUsuarioModif;

    /** The fec modif. */
    protected Calendar fecModif;

    /**
     * Constructor por defecto.
     */
    public AnuncioPOJO() {
        super();
    }

    /**
     * Gets the id anuncio.
     *
     * @return the id anuncio
     */
    public Integer getIdAnuncio() {
        return this.idAnuncio;
    }

    /**
     * Sets the id anuncio.
     *
     * @param idAnuncio the new id anuncio
     */
    public void setIdAnuncio(final Integer idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    /**
     * Gets the titulo anuncio.
     *
     * @return the titulo anuncio
     */
    public String getTituloAnuncio() {
        return this.tituloAnuncio;
    }

    /**
     * Sets the titulo anuncio.
     *
     * @param tituloAnuncio the new titulo anuncio
     */
    public void setTituloAnuncio(final String tituloAnuncio) {
        this.tituloAnuncio = tituloAnuncio;
    }

    /**
     * Gets the tipo barco.
     *
     * @return the tipo barco
     */
    public Integer getTipoBarco() {
        return this.tipoBarco;
    }

    /**
     * Sets the tipo barco.
     *
     * @param tipoBarco the new tipo barco
     */
    public void setTipoBarco(final Integer tipoBarco) {
        this.tipoBarco = tipoBarco;
    }

    /**
     * Gets the tipo vela.
     *
     * @return the tipo vela
     */
    public Integer getTipoVela() {
        return this.tipoVela;
    }

    /**
     * Sets the tipo vela.
     *
     * @param tipoVela the new tipo vela
     */
    public void setTipoVela(final Integer tipoVela) {
        this.tipoVela = tipoVela;
    }

    /**
     * Gets the gramaje.
     *
     * @return the gramaje
     */
    public BigDecimal getGramaje() {
        return this.gramaje;
    }

    /**
     * Sets the gramaje.
     *
     * @param gramaje the new gramaje
     */
    public void setGramaje(final BigDecimal gramaje) {
        this.gramaje = gramaje;
    }

    /**
     * Gets the gratil.
     *
     * @return the gratil
     */
    public BigDecimal getGratil() {
        return this.gratil;
    }

    /**
     * Sets the gratil.
     *
     * @param gratil the new gratil
     */
    public void setGratil(final BigDecimal gratil) {
        this.gratil = gratil;
    }

    /**
     * Gets the baluma.
     *
     * @return the baluma
     */
    public BigDecimal getBaluma() {
        return this.baluma;
    }

    /**
     * Sets the baluma.
     *
     * @param baluma the new baluma
     */
    public void setBaluma(final BigDecimal baluma) {
        this.baluma = baluma;
    }

    /**
     * Gets the pujamen.
     *
     * @return the pujamen
     */
    public BigDecimal getPujamen() {
        return this.pujamen;
    }

    /**
     * Sets the pujamen.
     *
     * @param pujamen the new pujamen
     */
    public void setPujamen(final BigDecimal pujamen) {
        this.pujamen = pujamen;
    }

    /**
     * Gets the caida proa.
     *
     * @return the caida proa
     */
    public BigDecimal getCaidaProa() {
        return this.caidaProa;
    }

    /**
     * Sets the caida proa.
     *
     * @param caidaProa the new caida proa
     */
    public void setCaidaProa(final BigDecimal caidaProa) {
        this.caidaProa = caidaProa;
    }

    /**
     * Gets the caida popa.
     *
     * @return the caida popa
     */
    public BigDecimal getCaidaPopa() {
        return this.caidaPopa;
    }

    /**
     * Sets the caida popa.
     *
     * @param caidaPopa the new caida popa
     */
    public void setCaidaPopa(final BigDecimal caidaPopa) {
        this.caidaPopa = caidaPopa;
    }

    /**
     * Gets the superficie.
     *
     * @return the superficie
     */
    public BigDecimal getSuperficie() {
        return this.superficie;
    }

    /**
     * Sets the superficie.
     *
     * @param superficie the new superficie
     */
    public void setSuperficie(final BigDecimal superficie) {
        this.superficie = superficie;
    }

    /**
     * Gets the tipo cometa.
     *
     * @return the tipo cometa
     */
    public String getTipoCometa() {
        return this.tipoCometa;
    }

    /**
     * Sets the tipo cometa.
     *
     * @param tipoCometa the new tipo cometa
     */
    public void setTipoCometa(final String tipoCometa) {
        this.tipoCometa = tipoCometa;
    }

    /**
     * Gets the descripcion.
     *
     * @return the descripcion
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Sets the descripcion.
     *
     * @param descripcion the new descripcion
     */
    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the precio.
     *
     * @return the precio
     */
    public BigDecimal getPrecio() {
        return this.precio;
    }

    /**
     * Sets the precio.
     *
     * @param precio the new precio
     */
    public void setPrecio(final BigDecimal precio) {
        this.precio = precio;
    }

    /**
     * Gets the pais.
     *
     * @return the pais
     */
    public String getPais() {
        return this.pais;
    }

    /**
     * Sets the pais.
     *
     * @param pais the new pais
     */
    public void setPais(final String pais) {
        this.pais = pais;
    }

    /**
     * Gets the caduca.
     *
     * @return the caduca
     */
    public Calendar getCaduca() {
        return this.caduca;
    }

    /**
     * Sets the caduca.
     *
     * @param caduca the new caduca
     */
    public void setCaduca(final Calendar caduca) {
        this.caduca = caduca;
    }

    /**
     * Gets the visible.
     *
     * @return the visible
     */
    public Integer getVisible() {
        return this.visible;
    }

    /**
     * Sets the visible.
     *
     * @param visible the new visible
     */
    public void setVisible(final Integer visible) {
        this.visible = visible;
    }

    /**
     * Gets the listado.
     *
     * @return the listado
     */
    public Integer getListado() {
        return this.listado;
    }

    /**
     * Sets the listado.
     *
     * @param listado the new listado
     */
    public void setListado(final Integer listado) {
        this.listado = listado;
    }

    /**
     * Gets the visto.
     *
     * @return the visto
     */
    public Integer getVisto() {
        return this.visto;
    }

    /**
     * Sets the visto.
     *
     * @param visto the new visto
     */
    public void setVisto(final Integer visto) {
        this.visto = visto;
    }

    /**
     * Gets the comentado.
     *
     * @return the comentado
     */
    public Integer getComentado() {
        return this.comentado;
    }

    /**
     * Sets the comentado.
     *
     * @param comentado the new comentado
     */
    public void setComentado(final Integer comentado) {
        this.comentado = comentado;
    }

    /**
     * Gets the destacado.
     *
     * @return the destacado
     */
    public Integer getDestacado() {
        return this.destacado;
    }

    /**
     * Sets the destacado.
     *
     * @param destacado the new destacado
     */
    public void setDestacado(final Integer destacado) {
        this.destacado = destacado;
    }

    /**
     * Gets the id usuario alta.
     *
     * @return the id usuario alta
     */
    public Integer getIdUsuarioAlta() {
        return this.idUsuarioAlta;
    }

    /**
     * Sets the id usuario alta.
     *
     * @param idUsuarioAlta the new id usuario alta
     */
    public void setIdUsuarioAlta(final Integer idUsuarioAlta) {
        this.idUsuarioAlta = idUsuarioAlta;
    }

    /**
     * Gets the fec alta.
     *
     * @return the fec alta
     */
    public Calendar getFecAlta() {
        return this.fecAlta;
    }

    /**
     * Sets the fec alta.
     *
     * @param fecAlta the new fec alta
     */
    public void setFecAlta(final Calendar fecAlta) {
        this.fecAlta = fecAlta;
    }

    /**
     * Gets the id usuario modif.
     *
     * @return the id usuario modif
     */
    public Integer getIdUsuarioModif() {
        return this.idUsuarioModif;
    }

    /**
     * Sets the id usuario modif.
     *
     * @param idUsuarioModif the new id usuario modif
     */
    public void setIdUsuarioModif(final Integer idUsuarioModif) {
        this.idUsuarioModif = idUsuarioModif;
    }

    /**
     * Gets the fec modif.
     *
     * @return the fec modif
     */
    public Calendar getFecModif() {
        return this.fecModif;
    }

    /**
     * Sets the fec modif.
     *
     * @param fecModif the new fec modif
     */
    public void setFecModif(final Calendar fecModif) {
        this.fecModif = fecModif;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("AnuncioPOJO [idAnuncio=").append(this.idAnuncio).append(", tituloAnuncio=").append(this.tituloAnuncio)
                .append(", tipoBarco=").append(this.tipoBarco).append(", tipoVela=").append(this.tipoVela).append(", gramaje=")
                .append(this.gramaje).append(", gratil=").append(this.gratil).append(", baluma=").append(this.baluma)
                .append(", pujamen=").append(this.pujamen).append(", caidaProa=").append(this.caidaProa).append(", caidaPopa=")
                .append(this.caidaPopa).append(", superficie=").append(this.superficie).append(", tipoCometa=")
                .append(this.tipoCometa).append(", descripcion=").append(this.descripcion).append(", precio=").append(this.precio)
                .append(", caduca=").append(this.caduca).append(", visible=").append(this.visible).append(", listado=")
                .append(this.listado).append(", visto=").append(this.visto).append(", comentado=").append(this.comentado)
                .append(", idUsuarioAlta=").append(this.idUsuarioAlta).append(", fecAlta=").append(this.fecAlta)
                .append(", idUsuarioModif=").append(this.idUsuarioModif).append(", fecModif=").append(this.fecModif).append("]");
        return builder.toString();
    }
}