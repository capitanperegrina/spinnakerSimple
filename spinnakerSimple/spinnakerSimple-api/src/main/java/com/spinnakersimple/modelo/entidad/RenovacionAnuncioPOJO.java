package com.spinnakersimple.modelo.entidad;

import java.io.Serializable;
import java.util.Calendar;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * POJO identificado con la tabla <code>renovacion_anuncio</code>
 */
public class RenovacionAnuncioPOJO implements Serializable {
    private static final long serialVersionUID = -15200785355L;

    protected Integer idRenocacionAnuncio;
    protected Integer idAnuncio;
    protected Integer idUsuarioAlta;
    protected Calendar fecAlta;
    protected Integer idUsuarioModif;
    protected Calendar fecModif;

    /**
     * Constructor por defecto.
     */
    public RenovacionAnuncioPOJO() {
        super();
    }

    public Integer getIdRenocacionAnuncio() {
        return this.idRenocacionAnuncio;
    }

    public void setIdRenocacionAnuncio(final Integer idRenocacionAnuncio) {
        this.idRenocacionAnuncio = idRenocacionAnuncio;
    }

    public Integer getIdAnuncio() {
        return this.idAnuncio;
    }

    public void setIdAnuncio(final Integer idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public Integer getIdUsuarioAlta() {
        return this.idUsuarioAlta;
    }

    public void setIdUsuarioAlta(final Integer idUsuarioAlta) {
        this.idUsuarioAlta = idUsuarioAlta;
    }

    public Calendar getFecAlta() {
        return this.fecAlta;
    }

    public void setFecAlta(final Calendar fecAlta) {
        this.fecAlta = fecAlta;
    }

    public Integer getIdUsuarioModif() {
        return this.idUsuarioModif;
    }

    public void setIdUsuarioModif(final Integer idUsuarioModif) {
        this.idUsuarioModif = idUsuarioModif;
    }

    public Calendar getFecModif() {
        return this.fecModif;
    }

    public void setFecModif(final Calendar fecModif) {
        this.fecModif = fecModif;
    }

    @Override
    public String toString() {
        ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
        return ReflectionToStringBuilder.toString(this);
    }
}