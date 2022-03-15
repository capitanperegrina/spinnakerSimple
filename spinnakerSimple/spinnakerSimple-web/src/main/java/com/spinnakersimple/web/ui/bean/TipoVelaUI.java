package com.spinnakersimple.web.ui.bean;

import java.io.Serializable;

public class TipoVelaUI implements Serializable {
    private static final long serialVersionUID = 98523241895L;

    protected String idTipoVela;
    protected String tipoVela;
    protected String urlTipoVela;
    protected String imagenTipoVela;
    protected String gratil;
    protected String baluma;
    protected String pujamen;
    protected String caidaProa;
    protected String caidaPopa;
    protected String superficie;
    protected String tipoCometa;
    protected String tipoVelaLocalizado;

    public TipoVelaUI() {
        super();
    }

    public String getIdTipoVela() {
        return this.idTipoVela;
    }

    public void setIdTipoVela(final String idTipoVela) {
        this.idTipoVela = idTipoVela;
    }

    public String getTipoVela() {
        return this.tipoVela;
    }

    public void setTipoVela(final String tipoVela) {
        this.tipoVela = tipoVela;
    }

    public String getUrlTipoVela() {
        return this.urlTipoVela;
    }

    public void setUrlTipoVela(final String urlTipoVela) {
        this.urlTipoVela = urlTipoVela;
    }

    public String getImagenTipoVela() {
        return this.imagenTipoVela;
    }

    public void setImagenTipoVela(final String imagenTipoVela) {
        this.imagenTipoVela = imagenTipoVela;
    }

    public String getGratil() {
        return this.gratil;
    }

    public void setGratil(final String gratil) {
        this.gratil = gratil;
    }

    public String getBaluma() {
        return this.baluma;
    }

    public void setBaluma(final String baluma) {
        this.baluma = baluma;
    }

    public String getPujamen() {
        return this.pujamen;
    }

    public void setPujamen(final String pujamen) {
        this.pujamen = pujamen;
    }

    public String getCaidaProa() {
        return this.caidaProa;
    }

    public void setCaidaProa(final String caidaProa) {
        this.caidaProa = caidaProa;
    }

    public String getCaidaPopa() {
        return this.caidaPopa;
    }

    public void setCaidaPopa(final String caidaPopa) {
        this.caidaPopa = caidaPopa;
    }

    public String getSuperficie() {
        return this.superficie;
    }

    public void setSuperficie(final String superficie) {
        this.superficie = superficie;
    }

    public String getTipoCometa() {
        return this.tipoCometa;
    }

    public void setTipoCometa(final String tipoCometa) {
        this.tipoCometa = tipoCometa;
    }

    public String getTipoVelaLocalizado() {
        return this.tipoVelaLocalizado;
    }

    public void setTipoVelaLocalizado(final String tipoVelaLocalizado) {
        this.tipoVelaLocalizado = tipoVelaLocalizado;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("TipoVelaUI [idTipoVela=").append(this.idTipoVela).append(", tipoVela=").append(this.tipoVela)
                .append(", urlTipoVela=").append(this.urlTipoVela).append(", imagenTipoVela=").append(this.imagenTipoVela)
                .append(", gratil=").append(this.gratil).append(", baluma=").append(this.baluma).append(", pujamen=")
                .append(this.pujamen).append(", caidaProa=").append(this.caidaProa).append(", caidaPopa=").append(this.caidaPopa)
                .append(", superficie=").append(this.superficie).append(", tipoCometa=").append(this.tipoCometa)
                .append(", tipoVelaLocalizado=").append(this.tipoVelaLocalizado).append("]");
        return builder.toString();
    }
}
