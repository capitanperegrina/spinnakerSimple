package com.spinnakersimple.modelo.entidad;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * POJO identificado con la tabla <code>tipo_vela</code>
 */
public class TipoVelaPOJO implements Serializable
{
    private static final long serialVersionUID = 98525841895L;

    protected Integer idTipoVela ;
    protected String tipoVela ;
    protected String urlTipoVela ;
    protected String imagenTipoVela ;
    protected String gratil ;
    protected String baluma ;
    protected String pujamen ;
    protected String caidaProa ;
    protected String caidaPopa ;
    protected String superficie ;
    protected String tipoCometa ;

    /**
     * Constructor por defecto.
     */
    public TipoVelaPOJO()
    {
        super();
    }

    public Integer getIdTipoVela()
    {
        return this.idTipoVela;
    }

    public void setIdTipoVela( final Integer idTipoVela )
    {
        this.idTipoVela = idTipoVela;
    }

    public String getTipoVela()
    {
        return this.tipoVela;
    }

    public void setTipoVela( final String tipoVela )
    {
        this.tipoVela = tipoVela;
    }

    public String getUrlTipoVela()
    {
        return this.urlTipoVela;
    }

    public void setUrlTipoVela( final String urlTipoVela )
    {
        this.urlTipoVela = urlTipoVela;
    }

    public String getImagenTipoVela()
    {
        return this.imagenTipoVela;
    }

    public void setImagenTipoVela( final String imagenTipoVela )
    {
        this.imagenTipoVela = imagenTipoVela;
    }

    public String getGratil()
    {
        return this.gratil;
    }

    public void setGratil( final String gratil )
    {
        this.gratil = gratil;
    }

    public String getBaluma()
    {
        return this.baluma;
    }

    public void setBaluma( final String baluma )
    {
        this.baluma = baluma;
    }

    public String getPujamen()
    {
        return this.pujamen;
    }

    public void setPujamen( final String pujamen )
    {
        this.pujamen = pujamen;
    }

    public String getCaidaProa() {
		return this.caidaProa;
	}

	public void setCaidaProa(final String caidaProa) {
		this.caidaProa = caidaProa;
	}

	public String getCaidaPopa() {
		return this.caidaPopa;
	}

	public void setCaidaPopa(final String caidaPopa) {
		this.caidaPopa = caidaPopa;
	}

	public String getSuperficie() {
		return this.superficie;
	}

	public void setSuperficie(final String superficie) {
		this.superficie = superficie;
	}

	public String getTipoCometa() {
		return this.tipoCometa;
	}

	public void setTipoCometa(final String tipoCometa) {
		this.tipoCometa = tipoCometa;
	}

	@Override
    public String toString()
    {
    	ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
    	return ReflectionToStringBuilder.toString(this);
    }
}