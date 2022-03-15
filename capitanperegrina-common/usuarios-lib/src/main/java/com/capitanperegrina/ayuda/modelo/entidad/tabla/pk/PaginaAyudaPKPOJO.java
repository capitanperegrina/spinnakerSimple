package com.capitanperegrina.ayuda.modelo.entidad.tabla.pk;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * POJO identificado con la clave principal de la tabla <code>paginas_ayuda</code>
 */
public class PaginaAyudaPKPOJO implements Serializable
{
    private static final long serialVersionUID = -2519772460065L;

    protected Integer idAyuda ;

    /**
     * Constructor por defecto.
     */
    public PaginaAyudaPKPOJO()
    {
        super();
    }

    public Integer getIdAyuda()
    {
        return this.idAyuda;
    }

    public void setIdAyuda( Integer idAyuda )
    {
        this.idAyuda = idAyuda;
    }

    @Override
    public String toString()
    {
    	ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
    	return ReflectionToStringBuilder.toString(this);
    }
}