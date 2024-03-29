package com.capitanperegrina.usuarios.modelo.entidad.tabla;

import java.io.Serializable;
import java.util.Calendar;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.capitanperegrina.usuarios.modelo.entidad.tabla.pk.UsuarioParamsPKPOJO;

/**
 * POJO identificado con la tabla <code>usuario_params</code>
 */
public class UsuarioParamsPOJO implements Serializable
{
    private static final long serialVersionUID = -6254694010707L;

    protected UsuarioParamsPKPOJO pk ;
    protected String visible ;
    protected String valor ;
    protected String tipoJava ;
    protected String tipoJs ;
    protected Integer idUsuarioAlta ;
    protected Calendar fecAlta ;
    protected Integer idUsuarioModif ;
    protected Calendar fecModif ;

    /**
     * Constructor por defecto.
     */
    public UsuarioParamsPOJO()
    {
        super();
        this.pk = new UsuarioParamsPKPOJO();
    }

    public UsuarioParamsPKPOJO getPk()
    {
        return this.pk;
    }

    public void setPk( UsuarioParamsPKPOJO pk )
    {
        this.pk = pk;
    }

    public String getVisible()
    {
        return this.visible;
    }

    public void setVisible( String visible )
    {
        this.visible = visible;
    }

    public String getValor()
    {
        return this.valor;
    }

    public void setValor( String valor )
    {
        this.valor = valor;
    }

    public String getTipoJava()
    {
        return this.tipoJava;
    }

    public void setTipoJava( String tipoJava )
    {
        this.tipoJava = tipoJava;
    }

    public String getTipoJs()
    {
        return this.tipoJs;
    }

    public void setTipoJs( String tipoJs )
    {
        this.tipoJs = tipoJs;
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