package com.spinnakersimple.modelo.entidad;

import java.io.Serializable;

import java.util.Calendar;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * POJO identificado con la tabla <code>compradores</code>
 */
public class CompradoresPOJO implements Serializable
{
    private static final long serialVersionUID = -33245886648L;

    protected Integer idComprador ;
    protected Integer idAnuncio ;
    protected String nombre ;
    protected String mail ;
    protected String observaciones ;
    protected Calendar fecha ;
    protected String revisado ;
    protected String telefono ;

    /**
     * Constructor por defecto.
     */
    public CompradoresPOJO()
    {
        super();
    }

    public Integer getIdComprador()
    {
        return this.idComprador;
    }

    public void setIdComprador( Integer idComprador )
    {
        this.idComprador = idComprador;
    }

    public Integer getIdAnuncio()
    {
        return this.idAnuncio;
    }

    public void setIdAnuncio( Integer idAnuncio )
    {
        this.idAnuncio = idAnuncio;
    }

    public String getNombre()
    {
        return this.nombre;
    }

    public void setNombre( String nombre )
    {
        this.nombre = nombre;
    }

    public String getMail()
    {
        return this.mail;
    }

    public void setMail( String mail )
    {
        this.mail = mail;
    }

    public String getObservaciones()
    {
        return this.observaciones;
    }

    public void setObservaciones( String observaciones )
    {
        this.observaciones = observaciones;
    }

    public Calendar getFecha()
    {
        return this.fecha;
    }

    public void setFecha( Calendar fecha )
    {
        this.fecha = fecha;
    }

    public String getRevisado()
    {
        return this.revisado;
    }

    public void setRevisado( String revisado )
    {
        this.revisado = revisado;
    }

    public String getTelefono()
    {
        return this.telefono;
    }

    public void setTelefono( String telefono )
    {
        this.telefono = telefono;
    }

    @Override
    public String toString()
    {
    	ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
    	return ReflectionToStringBuilder.toString(this);
    }
}