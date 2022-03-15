package com.capitanperegrina.common.modelo.entidad.tabla.pk;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * POJO identificado con la clave principal de la tabla <code>params</code>
 */
public class ParamsPKPOJO implements Serializable
{
    private static final long serialVersionUID = -6066992911185L;

    protected String idParam ;

    /**
     * Constructor por defecto.
     */
    public ParamsPKPOJO()
    {
        super();
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