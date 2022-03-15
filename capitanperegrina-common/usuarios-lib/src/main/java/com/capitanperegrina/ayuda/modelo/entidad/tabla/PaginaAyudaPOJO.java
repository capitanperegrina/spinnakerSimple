package com.capitanperegrina.ayuda.modelo.entidad.tabla;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.capitanperegrina.ayuda.modelo.entidad.tabla.pk.PaginaAyudaPKPOJO;

/**
 * POJO identificado con la tabla <code>paginas_ayuda</code>
 */
public class PaginaAyudaPOJO implements Serializable
{
    private static final long serialVersionUID = -5817693616040L;

    protected PaginaAyudaPKPOJO pk ;
    protected String idForm ;
    protected String idElemento ;
    protected String idSubelemento ;
    protected String titulo ;
    protected String contenido ;

    /**
     * Constructor por defecto.
     */
    public PaginaAyudaPOJO()
    {
        super();
        this.pk = new PaginaAyudaPKPOJO();
    }

    public PaginaAyudaPKPOJO getPk()
    {
        return this.pk;
    }

    public void setPk( PaginaAyudaPKPOJO pk )
    {
        this.pk = pk;
    }

    public String getIdForm()
    {
        return this.idForm;
    }

    public void setIdForm( String idForm )
    {
        this.idForm = idForm;
    }

    public String getIdElemento()
    {
        return this.idElemento;
    }

    public void setIdElemento( String idElemento )
    {
        this.idElemento = idElemento;
    }

    public String getIdSubelemento()
    {
        return this.idSubelemento;
    }

    public void setIdSubelemento( String idSubelemento )
    {
        this.idSubelemento = idSubelemento;
    }

    public String getTitulo()
    {
        return this.titulo;
    }

    public void setTitulo( String titulo )
    {
        this.titulo = titulo;
    }

    public String getContenido()
    {
        return this.contenido;
    }

    public void setContenido( String contenido )
    {
        this.contenido = contenido;
    }

    @Override
    public String toString()
    {
    	ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
    	return ReflectionToStringBuilder.toString(this);
    }
}