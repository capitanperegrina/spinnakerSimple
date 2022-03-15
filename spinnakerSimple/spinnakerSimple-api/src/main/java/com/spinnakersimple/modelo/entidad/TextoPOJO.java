package com.spinnakersimple.modelo.entidad;

import java.io.Serializable;

import java.util.Calendar;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * POJO identificado con la tabla <code>texto</code>
 */
public class TextoPOJO implements Serializable
{
    private static final long serialVersionUID = -58718191177L;

    protected Integer idTexto ;
    protected String lang ;
    protected String clave ;
    protected String valor ;
    protected Integer idUsuarioAlta ;
    protected Calendar fecAlta ;
    protected Integer idUsuarioModif ;
    protected Calendar fecModif ;

    /**
     * Constructor por defecto.
     */
    public TextoPOJO()
    {
        super();
    }

    public Integer getIdTexto()
    {
        return this.idTexto;
    }

    public void setIdTexto( Integer idTexto )
    {
        this.idTexto = idTexto;
    }

    public String getLang()
    {
        return this.lang;
    }

    public void setLang( String lang )
    {
        this.lang = lang;
    }

    public String getClave()
    {
        return this.clave;
    }

    public void setClave( String clave )
    {
        this.clave = clave;
    }

    public String getValor()
    {
        return this.valor;
    }

    public void setValor( String valor )
    {
        this.valor = valor;
    }

    public Integer getIdUsuarioAlta()
    {
        return this.idUsuarioAlta;
    }

    public void setIdUsuarioAlta( Integer idUsuarioAlta )
    {
        this.idUsuarioAlta = idUsuarioAlta;
    }

    public Calendar getFecAlta()
    {
        return this.fecAlta;
    }

    public void setFecAlta( Calendar fecAlta )
    {
        this.fecAlta = fecAlta;
    }

    public Integer getIdUsuarioModif()
    {
        return this.idUsuarioModif;
    }

    public void setIdUsuarioModif( Integer idUsuarioModif )
    {
        this.idUsuarioModif = idUsuarioModif;
    }

    public Calendar getFecModif()
    {
        return this.fecModif;
    }

    public void setFecModif( Calendar fecModif )
    {
        this.fecModif = fecModif;
    }

    @Override
    public String toString()
    {
    	ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
    	return ReflectionToStringBuilder.toString(this);
    }
}