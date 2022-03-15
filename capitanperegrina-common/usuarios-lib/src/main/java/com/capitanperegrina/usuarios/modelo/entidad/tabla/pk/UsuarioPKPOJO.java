package com.capitanperegrina.usuarios.modelo.entidad.tabla.pk;

import java.io.Serializable;

import java.util.Calendar;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * POJO identificado con la clave principal de la tabla <code>usuario</code>
 */
public class UsuarioPKPOJO implements Serializable
{
    private static final long serialVersionUID = -3673862243851L;

    protected Integer idUsuario ;

    /**
     * Constructor por defecto.
     */
    public UsuarioPKPOJO()
    {
        super();
    }

    public Integer getIdUsuario()
    {
        return this.idUsuario;
    }

    public void setIdUsuario( Integer idUsuario )
    {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString()
    {
    	ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
    	return ReflectionToStringBuilder.toString(this);
    }
}