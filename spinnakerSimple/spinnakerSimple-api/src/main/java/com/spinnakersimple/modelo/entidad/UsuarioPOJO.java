package com.spinnakersimple.modelo.entidad;

import java.io.Serializable;
import java.util.Calendar;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * POJO identificado con la tabla <code>usuario</code>
 */
public class UsuarioPOJO implements Serializable {
	private static final long serialVersionUID = 86913607066L;

	protected Integer idUsuario;
	protected String nombre;
	protected String apellidos;
	protected String mail;
	protected String movil;
	protected String direccion1;
	protected String direccion2;
	protected String provincia;
	protected String pais;
	protected String divisa;
	protected String codPostal;
	protected String admin;
	protected String pass;
	protected String lang;
	protected String ip;
	protected Integer fallosLogin;
	protected Integer idUsuarioAlta;
	protected Calendar fecAlta;
	protected Integer idUsuarioModif;
	protected Calendar fecModif;

	/**
	 * Constructor por defecto.
	 */
	public UsuarioPOJO() {
		super();
	}

	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMovil() {
		return this.movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public String getDireccion1() {
		return this.direccion1;
	}

	public void setDireccion1(String direccion1) {
		this.direccion1 = direccion1;
	}

	public String getDireccion2() {
		return this.direccion2;
	}

	public void setDireccion2(String direccion2) {
		this.direccion2 = direccion2;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getDivisa() {
		return this.divisa;
	}

	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	public String getCodPostal() {
		return this.codPostal;
	}

	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}

	public String getAdmin() {
		return this.admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getLang() {
		return this.lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getFallosLogin() {
		return this.fallosLogin;
	}

	public void setFallosLogin(Integer fallosLogin) {
		this.fallosLogin = fallosLogin;
	}

	public Integer getIdUsuarioAlta() {
		return this.idUsuarioAlta;
	}

	public void setIdUsuarioAlta(Integer idUsuarioAlta) {
		this.idUsuarioAlta = idUsuarioAlta;
	}

	public Calendar getFecAlta() {
		return this.fecAlta;
	}

	public void setFecAlta(Calendar fecAlta) {
		this.fecAlta = fecAlta;
	}

	public Integer getIdUsuarioModif() {
		return this.idUsuarioModif;
	}

	public void setIdUsuarioModif(Integer idUsuarioModif) {
		this.idUsuarioModif = idUsuarioModif;
	}

	public Calendar getFecModif() {
		return this.fecModif;
	}

	public void setFecModif(Calendar fecModif) {
		this.fecModif = fecModif;
	}

	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
		return ReflectionToStringBuilder.toString(this);
	}
}