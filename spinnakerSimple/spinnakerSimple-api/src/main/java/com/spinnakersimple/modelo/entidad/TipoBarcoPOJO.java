package com.spinnakersimple.modelo.entidad;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * POJO identificado con la tabla <code>tipo_barco</code>
 */
public class TipoBarcoPOJO implements Serializable
{
    private static final long serialVersionUID = -45941139781L;

    protected Integer idTipoBarco ;
    protected String tipoBarco ;
    protected String tipoClase ;
    protected Integer orden ;

    /**
     * Constructor por defecto.
     */
    public TipoBarcoPOJO()
    {
        super();
    }

    public Integer getIdTipoBarco()
    {
        return this.idTipoBarco;
    }

    public void setIdTipoBarco( final Integer idTipoBarco )
    {
        this.idTipoBarco = idTipoBarco;
    }

    public String getTipoBarco()
    {
        return this.tipoBarco;
    }

    public void setTipoBarco( final String tipoBarco )
    {
        this.tipoBarco = tipoBarco;
    }

	public String getTipoClase() {
		return this.tipoClase;
	}

	public void setTipoClase(final String tipoClase) {
		this.tipoClase = tipoClase;
	}

	public Integer getOrden()
    {
        return this.orden;
    }

    public void setOrden( final Integer orden )
    {
        this.orden = orden;
    }

    @Override
    public String toString()
    {
    	ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
    	return ReflectionToStringBuilder.toString(this);
    }
}