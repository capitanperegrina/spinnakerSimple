package com.spinnakersimple.modelo.entidad;

import java.io.Serializable;

import java.util.Calendar;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * POJO identificado con la tabla <code>slider</code>
 */
public class SliderPOJO implements Serializable
{
    private static final long serialVersionUID = 81075570299L;

    protected Integer idSlider ;
    protected String imagenHome ;
    protected String dondeHome ;

    /**
     * Constructor por defecto.
     */
    public SliderPOJO()
    {
        super();
    }

    public Integer getIdSlider()
    {
        return this.idSlider;
    }

    public void setIdSlider( Integer idSlider )
    {
        this.idSlider = idSlider;
    }

    public String getImagenHome()
    {
        return this.imagenHome;
    }

    public void setImagenHome( String imagenHome )
    {
        this.imagenHome = imagenHome;
    }

    public String getDondeHome()
    {
        return this.dondeHome;
    }

    public void setDondeHome( String dondeHome )
    {
        this.dondeHome = dondeHome;
    }

    @Override
    public String toString()
    {
    	ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
    	return ReflectionToStringBuilder.toString(this);
    }
}