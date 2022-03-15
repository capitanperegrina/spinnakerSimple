package com.capitanperegrina.usuarios.web.forms;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.capitanperegrina.usuarios.modelo.entidad.tabla.UsuarioPOJO;

public class UserFormPOJO extends UsuarioPOJO
{
	private static final long serialVersionUID = -5387869927971042398L;

	private String claveRepetida;
	private String codigoRecuperacion;
	private String aceptaCondiciones;
	private String captcha;
	
	public UserFormPOJO( UsuarioPOJO obj )
	{
		super();
		this.pk = obj.getPk();
		this.mail = obj.getMail();
		this.nick = obj.getNick();
		this.pass = obj.getPass();
		this.ip = obj.getIp();
		this.fallosLogin = obj.getFallosLogin();
		this.idUsuarioAlta = obj.getIdUsuarioAlta();
		this.fecAlta = obj.getFecAlta();
		this.idUsuarioModif = obj.getIdUsuarioModif();
		this.fecModif = obj.getFecModif();
	}
	
	public UserFormPOJO()
	{
		super();
	}
	
	public String getClaveRepetida() {
		return claveRepetida;
	}

	public void setClaveRepetida(String claveRepetida) {
		this.claveRepetida = claveRepetida;
	}

	public String getCodigoRecuperacion() {
		return codigoRecuperacion;
	}

	public void setCodigoRecuperacion(String codigoRecuperacion) {
		this.codigoRecuperacion = codigoRecuperacion;
	}

	public String getAceptaCondiciones() {
		return aceptaCondiciones;
	}

	public void setAceptaCondiciones(String aceptaCondiciones) {
		this.aceptaCondiciones = aceptaCondiciones;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	@Override
    public String toString()
    {
    	ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
    	return ReflectionToStringBuilder.toString(this);
    }
}
