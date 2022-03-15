package com.spinnakersimple.web.ui.bean;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;

import com.capitanperegrina.common.i18n.Mensajes;
import com.capitanperegrina.common.web.ui.CaptchaUI;
import com.spinnakersimple.modelo.util.SpinnakerSimpleGlobals;

/**
 * The Class AnuncioUI.
 */
public class AnuncioUI extends CaptchaUI implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 4592633874875603526L;

    /** The id anuncio. */
    protected String idAnuncio;

    /** The titulo anuncio. */
    protected String tituloAnuncio;

    protected String tipoBarco;

    /** The tipo vela. */
    protected String tipoVela;

    /** The tipo vela. */
    protected String tipoVelaDescripcion;

    /** The gratil. */
    protected String gramaje;

    /** The gratil. */
    protected String gratil;

    /** The baluma. */
    protected String baluma;

    /** The pujamen. */
    protected String pujamen;

    protected String caidaProa;

    protected String caidaPopa;

    protected String superficie;

    protected String tipoCometa;

    /** The descripcion. */
    protected String descripcion;

    /** The precio. */
    protected String precio;

    /** The precio. */
    protected String pais;

    /** The caduca. */
    protected String caduca;

    /** The visible. */
    protected String visible;

    protected String listado;
    protected String visto;
    protected String comentado;

    /** The id usuario alta. */
    protected String idUsuarioAlta;

    /** The fec alta. */
    protected String fecAlta;

    /** The id usuario modif. */
    protected String idUsuarioModif;

    /** The fec modif. */
    protected String fecModif;

    /** The id anuncio codificado. */
    protected String idAnuncioCodificado;

    /** The extracto anuncio. */
    protected String extractoAnuncio;

    /** The divisa. */
    protected String divisa;

    protected String tipoCometaDescripcion;

    /** The fecha ordenacion. */
    protected String fechaOrdenacion;

    protected String diasParaCaducar;

    protected String diasParaCaducarOrdenacion;

    /**
     * Instantiates a new anuncio DTO.
     */
    public AnuncioUI() {
        super();
    }

    public String getGramaje() {
        return this.gramaje;
    }

    public void setGramaje(final String gramaje) {
        this.gramaje = gramaje;
    }

    /**
     * Gets the baluma.
     *
     * @return the baluma
     */
    public String getBaluma() {
        return this.baluma;
    }

    /**
     * Gets the caduca.
     *
     * @return the caduca
     */
    public String getCaduca() {
        return this.caduca;
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
     * Gets the extracto anuncio.
     *
     * @return the extracto anuncio
     */
    public String getExtractoAnuncio() {
        return this.extractoAnuncio;
    }

    /**
     * Gets the fec alta.
     *
     * @return the fec alta
     */
    public String getFecAlta() {
        return this.fecAlta;
    }

    /**
     * Gets the fec modif.
     *
     * @return the fec modif
     */
    public String getFecModif() {
        return this.fecModif;
    }

    /**
     * Gets the gratil.
     *
     * @return the gratil
     */
    public String getGratil() {
        return this.gratil;
    }

    /**
     * Gets the id anuncio.
     *
     * @return the id anuncio
     */
    public String getIdAnuncio() {
        return this.idAnuncio;
    }

    /**
     * Gets the id anuncio codificado.
     *
     * @return the id anuncio codificado
     */
    public String getIdAnuncioCodificado() {
        return this.idAnuncioCodificado;
    }

    /**
     * Gets the id usuario alta.
     *
     * @return the id usuario alta
     */
    public String getIdUsuarioAlta() {
        return this.idUsuarioAlta;
    }

    /**
     * Gets the id usuario modif.
     *
     * @return the id usuario modif
     */
    public String getIdUsuarioModif() {
        return this.idUsuarioModif;
    }

    /**
     * Gets the precio.
     *
     * @return the precio
     */
    public String getPrecio() {
        return this.precio;
    }

    /**
     * Gets the pujamen.
     *
     * @return the pujamen
     */
    public String getPujamen() {
        return this.pujamen;
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
     * Gets the tipo vela descripcion.
     *
     * @return the tipo vela descripcion
     */
    public String getTipoVelaDescripcion() {
        return this.tipoVelaDescripcion;
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
     * Gets the visible.
     *
     * @return the visible
     */
    public String getVisible() {
        return this.visible;
    }

    /**
     * Sets the baluma.
     *
     * @param baluma the new baluma
     */
    public void setBaluma(final String baluma) {
        this.baluma = baluma;
    }

    /**
     * Sets the caduca.
     *
     * @param caduca the new caduca
     */
    public void setCaduca(final String caduca) {
        this.caduca = caduca;
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
     * Sets the extracto anuncio.
     *
     * @param extractoAnuncio the new extracto anuncio
     */
    public void setExtractoAnuncio(final String extractoAnuncio) {
        this.extractoAnuncio = extractoAnuncio;
    }

    /**
     * Sets the fec alta.
     *
     * @param fecAlta the new fec alta
     */
    public void setFecAlta(final String fecAlta) {
        this.fecAlta = fecAlta;
    }

    /**
     * Sets the fec modif.
     *
     * @param fecModif the new fec modif
     */
    public void setFecModif(final String fecModif) {
        this.fecModif = fecModif;
    }

    /**
     * Sets the gratil.
     *
     * @param gratil the new gratil
     */
    public void setGratil(final String gratil) {
        this.gratil = gratil;
    }

    /**
     * Sets the id anuncio.
     *
     * @param idAnuncio the new id anuncio
     */
    public void setIdAnuncio(final String idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    /**
     * Sets the id anuncio codificado.
     *
     * @param idAnuncioCodificado the new id anuncio codificado
     */
    public void setIdAnuncioCodificado(final String idAnuncioCodificado) {
        this.idAnuncioCodificado = idAnuncioCodificado;
    }

    /**
     * Sets the id usuario alta.
     *
     * @param idUsuarioAlta the new id usuario alta
     */
    public void setIdUsuarioAlta(final String idUsuarioAlta) {
        this.idUsuarioAlta = idUsuarioAlta;
    }

    /**
     * Sets the id usuario modif.
     *
     * @param idUsuarioModif the new id usuario modif
     */
    public void setIdUsuarioModif(final String idUsuarioModif) {
        this.idUsuarioModif = idUsuarioModif;
    }

    /**
     * Sets the precio.
     *
     * @param precio the new precio
     */
    public void setPrecio(final String precio) {
        this.precio = precio;
    }

    /**
     * Sets the pujamen.
     *
     * @param pujamen the new pujamen
     */
    public void setPujamen(final String pujamen) {
        this.pujamen = pujamen;
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
     * Sets the tipo vela descripcion.
     *
     * @param tipoVelaDescripcion the new tipo vela descripcion
     */
    public void setTipoVelaDescripcion(final String tipoVelaDescripcion) {
        this.tipoVelaDescripcion = tipoVelaDescripcion;
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
     * Gets the divisa.
     *
     * @return the divisa
     */
    public String getDivisa() {
        return this.divisa;
    }

    /**
     * Sets the divisa.
     *
     * @param divisa the new divisa
     */
    public void setDivisa(final String divisa) {
        this.divisa = divisa;
    }

    /**
     * Sets the visible.
     *
     * @param visible the new visible
     */
    public void setVisible(final String visible) {
        this.visible = visible;
    }

    /**
     * Gets the fecha ordenacion.
     *
     * @return the fecha ordenacion
     */
    public String getFechaOrdenacion() {
        return this.fechaOrdenacion;
    }

    /**
     * Sets the fecha ordenacion.
     *
     * @param fechaOrdenacion the new fecha ordenacion
     */
    public void setFechaOrdenacion(final String fechaOrdenacion) {
        this.fechaOrdenacion = fechaOrdenacion;
    }

    /**
     * Gets the estado anuncio descripcion.
     *
     * @return the estado anuncio descripcion
     */
    public String getEstadoAnuncioDescripcion() {
        return Mensajes.getStringSafe("anuncio.estado." + getVisible());
    }

    public String getDiasParaCaducar() {
        return this.diasParaCaducar;
    }

    public void setDiasParaCaducar(final String diasParaCaducar) {
        this.diasParaCaducar = diasParaCaducar;
    }

    public String getDiasParaCaducarOrdenacion() {
        return this.diasParaCaducarOrdenacion;
    }

    public void setDiasParaCaducarOrdenacion(final String diasParaCaducarOrdenacion) {
        this.diasParaCaducarOrdenacion = diasParaCaducarOrdenacion;
    }

    public String getListado() {
        return this.listado;
    }

    public void setListado(final String listado) {
        this.listado = listado;
    }

    public String getVisto() {
        return this.visto;
    }

    public void setVisto(final String visto) {
        this.visto = visto;
    }

    public String getComentado() {
        return this.comentado;
    }

    public void setComentado(final String comentado) {
        this.comentado = comentado;
    }

    public String getTipoBarco() {
        return this.tipoBarco;
    }

    public void setTipoBarco(final String tipoBarco) {
        this.tipoBarco = tipoBarco;
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

    /**
     * Gets the botones.
     *
     * @return the botones
     */
    public String getBotones() {
        return getBotoneraVerAnuncio();
        // final StringBuilder botones = new StringBuilder();
        // if (this.visible.equals(SpinnakerSimpleGlobals.ESTADO_FINALIZADO)) {
        // botones.append(
        // "<img class=\"btnAnuncioRecupera\" src=\"imagenes/iconos/recupera.png\"
        // style=\"margin-left: 10px; cursor:pointer;\"")
        // .append("title=\"" + Mensajes.getStringSafe("global.recuperar") + "\"")
        // .append("alt=\"" + Mensajes.getStringSafe("global.recuperar") + "\"")
        // .append("ida=\"" + this.idAnuncioCodificado + "\"/>");
        // } else if (this.visible.equals(SpinnakerSimpleGlobals.ESTADO_PUBLICADO)) {
        // botones.append(
        // "<img class=\"btnAnuncioMod\" src=\"imagenes/iconos/mod.png\"
        // style=\"margin-left: 10px; cursor:pointer;\"")
        // .append("title=\"" + Mensajes.getStringSafe("global.modificar") + "\"")
        // .append("alt=\"" + Mensajes.getStringSafe("global.modificar") + "\"")
        // .append("ida=\"" + this.idAnuncioCodificado + "\"/>");
        // botones.append(
        // "<img class=\"btnAnuncioDel\" src=\"imagenes/iconos/del-naranja.png\"
        // style=\"margin-left: 10px; cursor:pointer;\"")
        // .append("title=\"" + Mensajes.getStringSafe("global.eliminar") + "\"")
        // .append("alt=\"" + Mensajes.getStringSafe("global.eliminar") + "\"")
        // .append("ida=\"" + this.idAnuncioCodificado + "\"/>");
        // } else {
        // botones.append(
        // "<img class=\"btnAnuncioOk\" src=\"imagenes/iconos/ok-verde.png\"
        // style=\"margin-left: 10px; cursor:pointer;\"")
        // .append("title=\"" + Mensajes.getStringSafe("global.validar") + "\"")
        // .append("alt=\"" + Mensajes.getStringSafe("global.validar") + "\"")
        // .append("ida=\"" + this.idAnuncioCodificado + "\"/>");
        // botones.append(
        // "<img class=\"btnAnuncioMod\" src=\"imagenes/iconos/mod.png\"
        // style=\"margin-left: 10px; cursor:pointer;\"")
        // .append("title=\"" + Mensajes.getStringSafe("global.modificar") + "\"")
        // .append("alt=\"" + Mensajes.getStringSafe("global.modificar") + "\"")
        // .append("ida=\"" + this.idAnuncioCodificado + "\"/>");
        // botones.append(
        // "<img class=\"btnAnuncioDel\" src=\"imagenes/iconos/del-naranja.png\"
        // style=\"margin-left: 10px; cursor:pointer;\"")
        // .append("title=\"" + Mensajes.getStringSafe("global.eliminar") + "\"")
        // .append("alt=\"" + Mensajes.getStringSafe("global.eliminar") + "\"")
        // .append("ida=\"" + this.idAnuncioCodificado + "\"/>");
        // }
        // return botones.toString();

    }

    /**
     * Gets the icono mensajes.
     *
     * @return the icono mensajes
     */
    public String getIconoMensajes() {
        final StringBuilder icono = new StringBuilder();
        icono.append("<div class=\"contenedorComments\">\n")
                .append("  <img src=\"imagenes/iconos/comments24.png\" alt=\"Comentarios\">\n")
                .append("  <div class=\"textoComments\">0</div>\n").append("</div>\n");
        return icono.toString();
    }

    /**
     * Gets the extracto titulo.
     *
     * @return the extracto titulo
     */
    public String getExtractoTitulo() {
        if (StringUtils.trimToEmpty(this.tituloAnuncio).length() > 25) {
            return this.tituloAnuncio.substring(0, 20) + "...";
        } else {
            return this.tituloAnuncio;
        }
    }

    /**
     * Gets the botonera.
     *
     * @return the botonera
     */
    public String getBotonera() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getBotonVerAnuncio());
        sb.append("\n");
        sb.append(getBotonRenuevaAnuncio());
        sb.append("\n");
        sb.append(getBotonEditaAnuncio());
        sb.append("\n");
        sb.append(getBotonEliminaAnuncio());
        return sb.toString();
    }

    public String getBotonesAdministrador() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getBotonVerAnuncio());
        sb.append("\n");
        sb.append(getBotonValidaAnuncio());
        sb.append("\n");
        sb.append(getBotonRenuevaAnuncio());
        sb.append("\n");
        sb.append(getBotonEditaAnuncio());
        sb.append("\n");
        sb.append(getBotonEliminaAnuncio());
        return sb.toString();
    }

    public String getBotoneraVerAnuncio() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getBotonVerAnuncio());
        sb.append("\n");
        sb.append(getBotonRenuevaAnuncio());
        sb.append("\n");
        sb.append(getBotonEditaAnuncio());
        sb.append("\n");
        sb.append(getBotonEliminaAnuncio());
        return sb.toString();
    }

    /**
     * Gets the boton valida anuncio.
     *
     * @return the boton valida anuncio
     */
    public String getBotonValidaAnuncio() {
        final StringBuilder sb = new StringBuilder();
        sb.append("<button id=\"btnValidaAnuncio_" + this.idAnuncioCodificado + "\" cod=\"" + this.idAnuncioCodificado
                + "\" type=\"button\" class=\"btnValidaAnuncio btn btn-circle");
        if (this.visible.equals(SpinnakerSimpleGlobals.ANUNCIO_VISIBLE.toString())) {
            sb.append(" btn-success\"");
            sb.append(" title=\"" + Mensajes.getStringSafe("global.validar", LocaleContextHolder.getLocale()) + "\">");
            sb.append("<i class=\"fa fa-check\">");
        } else if (this.visible.equals(SpinnakerSimpleGlobals.ANUNCIO_NO_VISIBLE.toString())) {
            sb.append(" btn-default\">");
            sb.append("<i class=\"fa fa-check\">");
        } else if (this.visible.equals(SpinnakerSimpleGlobals.ANUNCIO_ELIMINADO.toString())) {
            sb.append(" btn-primary\">");
            sb.append("<i class=\"fa fa-undo\">");
        }
        sb.append("</i></button>");
        return sb.toString();
    }

    public String getBotonRenuevaAnuncio() {
        final StringBuilder sb = new StringBuilder();
        sb.append("<button id=\"btnRenuevaAnuncio_" + this.idAnuncioCodificado + "\" cod=\"" + this.idAnuncioCodificado
                + "\" type=\"button\" class=\"btnRenuevaAnuncio btn btn-warning btn-circle\"");
        sb.append(" title=\"" + Mensajes.getStringSafe("global.renovarAnuncio", LocaleContextHolder.getLocale()) + "\">");
        sb.append("<i class=\"fa fa-recycle\"></i>");
        sb.append("</button>");
        return sb.toString();
    }

    /**
     * Gets the boton edita anuncio.
     *
     * @return the boton edita anuncio
     */
    public String getBotonEditaAnuncio() {
        final StringBuilder sb = new StringBuilder();
        sb.append("<button id=\"btnEditaAnuncio_" + this.idAnuncioCodificado + "\" cod=\"" + this.idAnuncioCodificado
                + "\" type=\"button\" class=\"btnEditaAnuncio btn btn-info btn-circle\"");
        sb.append(" title=\"" + Mensajes.getStringSafe("global.modificar", LocaleContextHolder.getLocale()) + "\">");
        sb.append("<i class=\"fa fa-pen fa-pencil\"></i>");
        sb.append("</button>");
        return sb.toString();
    }

    /**
     * Gets the boton elimina anuncio.
     *
     * @return the boton elimina anuncio
     */
    public String getBotonEliminaAnuncio() {
        final StringBuilder sb = new StringBuilder();

        sb.append("<button id=\"btnEliminaAnuncio_" + this.idAnuncioCodificado + "\" cod=\"" + this.idAnuncioCodificado
                + "\" type=\"button\" class=\"btnEliminaAnuncio btn btn-danger btn-circle");
        if (this.visible.equals(SpinnakerSimpleGlobals.ESTADO_FINALIZADO)) {
            sb.append(" disabled");
        }
        sb.append("\"");
        sb.append(" title=\"" + Mensajes.getStringSafe("global.eliminar", LocaleContextHolder.getLocale()) + "\">");
        sb.append("<i class=\"fa fa-trash\"></i>");
        sb.append("</button>");
        return sb.toString();
    }

    /**
     * Gets the boton ver anuncio.
     *
     * @return the boton ver anuncio
     */
    public String getBotonVerAnuncio() {
        final StringBuilder sb = new StringBuilder();
        sb.append("<button id=\"btnVerAnuncio_" + this.idAnuncioCodificado + "\" cod=\"" + this.idAnuncioCodificado
                + "\" type=\"button\" class=\"btnVerAnuncio btn btn-primary btn-circle\"");
        sb.append(" title=\"" + Mensajes.getStringSafe("anuncio.consulta.titulo", LocaleContextHolder.getLocale()) + "\">");
        sb.append("<i class=\"fa fa-eye\"></i>");
        sb.append("</button>");
        return sb.toString();
    }

    public String getDivEstado() {
        final StringBuilder sb = new StringBuilder();
        sb.append("<div id=\"divEstadoAnuncio\" class=\"alert ");
        switch (this.visible) {
        case "0":
            sb.append("alert-warning");
            break;
        case "1":
            sb.append("alert-success");
            break;
        case "2":
            sb.append("alert-danger");
            break;
        }
        sb.append("\">");
        sb.append("<div class=\"float-izda\">");
        sb.append("<span id=\"textoEstadoAnuncio\">");
        sb.append(this.getEstadoAnuncioDescripcion());
        sb.append("</span>");
        sb.append("</div>");
        sb.append("<div class=\"float-dcha\">" + this.getBotonesAdministrador() + "</div>");
        sb.append("<div class=\"clearfix\"></div>");
        sb.append("</div>\n");

        return sb.toString();
    }

    public String getTipoCometaDescripcion() {
        return this.tipoCometaDescripcion;
    }

    public void setTipoCometaDescripcion(final String tipoCometaDescripcion) {
        this.tipoCometaDescripcion = tipoCometaDescripcion;
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
        builder.append("AnuncioUI [idAnuncio=").append(this.idAnuncio).append(", tituloAnuncio=").append(this.tituloAnuncio)
                .append(", tipoBarco=").append(this.tipoBarco).append(", tipoVela=").append(this.tipoVela)
                .append(", tipoVelaDescripcion=").append(this.tipoVelaDescripcion).append(", gramaje=").append(this.gramaje)
                .append(", gratil=").append(this.gratil).append(", baluma=").append(this.baluma).append(", pujamen=")
                .append(this.pujamen).append(", caidaProa=").append(this.caidaProa).append(", caidaPopa=").append(this.caidaPopa)
                .append(", superficie=").append(this.superficie).append(", tipoCometa=").append(this.tipoCometa)
                .append(", descripcion=").append(this.descripcion).append(", precio=").append(this.precio).append(", pais=")
                .append(this.pais).append(", caduca=").append(this.caduca).append(", visible=").append(this.visible)
                .append(", listado=").append(this.listado).append(", visto=").append(this.visto).append(", comentado=")
                .append(this.comentado).append(", idUsuarioAlta=").append(this.idUsuarioAlta).append(", fecAlta=")
                .append(this.fecAlta).append(", idUsuarioModif=").append(this.idUsuarioModif).append(", fecModif=")
                .append(this.fecModif).append(", idAnuncioCodificado=").append(this.idAnuncioCodificado)
                .append(", extractoAnuncio=").append(this.extractoAnuncio).append(", divisa=").append(this.divisa)
                .append(", tipoCometaDescripcion=").append(this.tipoCometaDescripcion).append(", fechaOrdenacion=")
                .append(this.fechaOrdenacion).append(", diasParaCaducar=").append(this.diasParaCaducar)
                .append(", diasParaCaducarOrdenacion=").append(this.diasParaCaducarOrdenacion).append("]");
        return builder.toString();
    }
}
