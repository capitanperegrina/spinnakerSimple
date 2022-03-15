package com.capitanperegrina.usuarios.modelo.entidad.tabla;

import java.io.Serializable;
import java.util.Calendar;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.capitanperegrina.usuarios.modelo.entidad.tabla.pk.UsuarioPKPOJO;

/**
 * POJO identificado con la tabla <code>usuario</code>
 */
public class UsuarioPOJO implements Serializable
{
    private static final long serialVersionUID = 5450616303024L;

    protected UsuarioPKPOJO pk ;
    protected String mail ;
    protected String nick ;
    protected String pass ;
    protected String ip ;
    protected Integer fallosLogin ;
    protected Integer idUsuarioAlta ;
    protected Calendar fecAlta ;
    protected Integer idUsuarioModif ;
    protected Calendar fecModif ;

    /**
     * Constructor por defecto.
     */
    public UsuarioPOJO()
    {
        super();
        this.pk = new UsuarioPKPOJO();
    }

    public UsuarioPKPOJO getPk()
    {
        return this.pk;
    }

    public void setPk( UsuarioPKPOJO pk )
    {
        this.pk = pk;
    }

    public String getMail()
    {
        return this.mail;
    }

    public void setMail( String mail )
    {
        this.mail = mail;
    }

    public String getNick()
    {
        return this.nick;
    }

    public void setNick( String nick )
    {
        this.nick = nick;
    }

    public String getPass()
    {
        return this.pass;
    }

    public void setPass( String pass )
    {
        this.pass = pass;
    }

    public String getIp()
    {
        return this.ip;
    }

    public void setIp( String ip )
    {
        this.ip = ip;
    }

    public Integer getFallosLogin()
    {
        return this.fallosLogin;
    }

    public void setFallosLogin( Integer fallosLogin )
    {
        this.fallosLogin = fallosLogin;
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