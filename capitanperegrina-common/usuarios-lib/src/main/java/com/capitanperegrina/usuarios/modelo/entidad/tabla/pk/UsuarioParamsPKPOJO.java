package com.capitanperegrina.usuarios.modelo.entidad.tabla.pk;

import java.io.Serializable;

import java.util.Calendar;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * POJO identificado con la clave principal de la tabla <code>usuario_params</code>
 */
public class UsuarioParamsPKPOJO implements Serializable
{
    private static final long serialVersionUID = 8007656797696L;

    protected Integer idUsuario ;
    protected String idParam ;

    /**
     * Constructor por defecto.
     */
    public UsuarioParamsPKPOJO()
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

    public String getIdParam()
    {
        return this.idParam;
    }

    public void setIdParam( String idParam )
    {
        this.idParam = idParam;
    }

    @Override
    public String toString()
    {
    	ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
    	return ReflectionToStringBuilder.toString(this);
    }
}