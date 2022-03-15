package com.spinnakersimple.web.ui.bean;

import java.io.Serializable;

import com.capitanperegrina.common.web.ui.CaptchaUI;

/**
 * The Class UsuarioUI.
 *
 * @author <a href="mailto:yo@capitanperegrina.com">Capitán Peregrina</a>
 */
public class UsuarioUI extends CaptchaUI implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3946761183325687238L;

	/** The id usuario. */
	protected String idUsuario;

	/** The nombre. */
	protected String nombre;

	/** The apellidos. */
	protected String apellidos;

	/** The mail. */
	protected String mail;

	/** The movil. */
	protected String movil;

	/** The direccion 1. */
	protected String direccion1;

	/** The direccion 2. */
	protected String direccion2;

	/** The provincia. */
	protected String provincia;

	/** The pais. */
	protected String pais;

	/** The divisa. */
	protected String divisa;

	/** The cod postal. */
	protected String codPostal;

	/** The admin. */
	protected String admin;

	/** The pass. */
	protected String pass;

	/** The lang. */
	protected String lang;

	/** The ip. */
	protected String ip;

	/** The fallos login. */
	protected String fallosLogin;

	/** The id usuario alta. */
	protected String idUsuarioAlta;

	/** The fec alta. */
	protected String fecAlta;

	/** The id usuario modif. */
	protected String idUsuarioModif;

	/** The fec modif. */
	protected String fecModif;

	/** El campo tipo alta. */
	protected String tipoAlta;

	protected Integer numAnuncios;
	protected String idUsuarioCodificado;

	protected String nombrePropietario;
	protected String nombreCompleto;
	protected String direccionCompleta;
	protected String fechaAlta;
	protected String fechaOrdenacion;

	protected String privacidad;
	protected String terminos;

	/**
	 * Instantiates a new usuario DTO.
	 */
	public UsuarioUI() {
		super();
	}

	/**
	 * Gets the id usuario.
	 *
	 * @return the id usuario
	 */
	public String getIdUsuario() {
		return this.idUsuario;
	}

	/**
	 * Sets the id usuario.
	 *
	 * @param idUsuario the new id usuario
	 */
	public void setIdUsuario(final String idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the apellidos.
	 *
	 * @return the apellidos
	 */
	public String getApellidos() {
		return this.apellidos;
	}

	/**
	 * Sets the apellidos.
	 *
	 * @param apellidos the new apellidos
	 */
	public void setApellidos(final String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Gets the mail.
	 *
	 * @return the mail
	 */
	public String getMail() {
		return this.mail;
	}

	/**
	 * Sets the mail.
	 *
	 * @param mail the new mail
	 */
	public void setMail(final String mail) {
		this.mail = mail;
	}

	/**
	 * Gets the movil.
	 *
	 * @return the movil
	 */
	public String getMovil() {
		return this.movil;
	}

	/**
	 * Sets the movil.
	 *
	 * @param movil the new movil
	 */
	public void setMovil(final String movil) {
		this.movil = movil;
	}

	/**
	 * Gets the direccion 1.
	 *
	 * @return the direccion 1
	 */
	public String getDireccion1() {
		return this.direccion1;
	}

	/**
	 * Sets the direccion 1.
	 *
	 * @param direccion1 the new direccion 1
	 */
	public void setDireccion1(final String direccion1) {
		this.direccion1 = direccion1;
	}

	/**
	 * Gets the direccion 2.
	 *
	 * @return the direccion 2
	 */
	public String getDireccion2() {
		return this.direccion2;
	}

	/**
	 * Sets the direccion 2.
	 *
	 * @param direccion2 the new direccion 2
	 */
	public void setDireccion2(final String direccion2) {
		this.direccion2 = direccion2;
	}

	/**
	 * Gets the provincia.
	 *
	 * @return the provincia
	 */
	public String getProvincia() {
		return this.provincia;
	}

	/**
	 * Sets the provincia.
	 *
	 * @param provincia the new provincia
	 */
	public void setProvincia(final String provincia) {
		this.provincia = provincia;
	}

	/**
	 * Gets the pais.
	 *
	 * @return the pais
	 */
	public String getPais() {
		return this.pais;
	}

	/**
	 * Sets the pais.
	 *
	 * @param pais the new pais
	 */
	public void setPais(final String pais) {
		this.pais = pais;
	}

	/**
	 * Gets the divisa.
	 *
	 * @return the divisa
	 */
	public String getDivisa() {
		return this.divisa;
	}

	/**
	 * Sets the divisa.
	 *
	 * @param divisa the new divisa
	 */
	public void setDivisa(final String divisa) {
		this.divisa = divisa;
	}

	/**
	 * Gets the cod postal.
	 *
	 * @return the cod postal
	 */
	public String getCodPostal() {
		return this.codPostal;
	}

	/**
	 * Sets the cod postal.
	 *
	 * @param codPostal the new cod postal
	 */
	public void setCodPostal(final String codPostal) {
		this.codPostal = codPostal;
	}

	/**
	 * Gets the admin.
	 *
	 * @return the admin
	 */
	public String getAdmin() {
		return this.admin;
	}

	/**
	 * Sets the admin.
	 *
	 * @param admin the new admin
	 */
	public void setAdmin(final String admin) {
		this.admin = admin;
	}

	/**
	 * Gets the pass.
	 *
	 * @return the pass
	 */
	public String getPass() {
		return this.pass;
	}

	/**
	 * Sets the pass.
	 *
	 * @param pass the new pass
	 */
	public void setPass(final String pass) {
		this.pass = pass;
	}

	/**
	 * Gets the lang.
	 *
	 * @return the lang
	 */
	public String getLang() {
		return this.lang;
	}

	/**
	 * Sets the lang.
	 *
	 * @param lang the new lang
	 */
	public void setLang(final String lang) {
		this.lang = lang;
	}

	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 */
	public String getIp() {
		return this.ip;
	}

	/**
	 * Sets the ip.
	 *
	 * @param ip the new ip
	 */
	public void setIp(final String ip) {
		this.ip = ip;
	}

	/**
	 * Gets the fallos login.
	 *
	 * @return the fallos login
	 */
	public String getFallosLogin() {
		return this.fallosLogin;
	}

	/**
	 * Sets the fallos login.
	 *
	 * @param fallosLogin the new fallos login
	 */
	public void setFallosLogin(final String fallosLogin) {
		this.fallosLogin = fallosLogin;
	}

	/**
	 * Gets the id usuario alta.
	 *
	 * @return the id usuario alta
	 */
	public String getIdUsuarioAlta() {
		return this.idUsuarioAlta;
	}

	/**
	 * Sets the id usuario alta.
	 *
	 * @param idUsuarioAlta the new id usuario alta
	 */
	public void setIdUsuarioAlta(final String idUsuarioAlta) {
		this.idUsuarioAlta = idUsuarioAlta;
	}

	/**
	 * Gets the fec alta.
	 *
	 * @return the fec alta
	 */
	public String getFecAlta() {
		return this.fecAlta;
	}

	/**
	 * Sets the fec alta.
	 *
	 * @param fecAlta the new fec alta
	 */
	public void setFecAlta(final String fecAlta) {
		this.fecAlta = fecAlta;
	}

	/**
	 * Gets the id usuario modif.
	 *
	 * @return the id usuario modif
	 */
	public String getIdUsuarioModif() {
		return this.idUsuarioModif;
	}

	/**
	 * Sets the id usuario modif.
	 *
	 * @param idUsuarioModif the new id usuario modif
	 */
	public void setIdUsuarioModif(final String idUsuarioModif) {
		this.idUsuarioModif = idUsuarioModif;
	}

	/**
	 * Gets the fec modif.
	 *
	 * @return the fec modif
	 */
	public String getFecModif() {
		return this.fecModif;
	}

	/**
	 * Sets the fec modif.
	 *
	 * @param fecModif the new fec modif
	 */
	public void setFecModif(final String fecModif) {
		this.fecModif = fecModif;
	}

	/**
	 * Obtiene la propiedad tipo alta.
	 *
	 * @return la propiedad tipo alta
	 */
	public String getTipoAlta() {
		return this.tipoAlta;
	}

	/**
	 * Establece la propiedad tipo alta.
	 *
	 * @param tipoAlta el nuevo valor de la propiedad tipo alta
	 */
	public void setTipoAlta(final String tipoAlta) {
		this.tipoAlta = tipoAlta;
	}

	public String getFechaOrdenacion() {
		return this.fechaOrdenacion;
	}

	public void setFechaOrdenacion(final String fechaOrdenacion) {
		this.fechaOrdenacion = fechaOrdenacion;
	}

	public String getNombrePropietario() {
		return this.nombrePropietario;
	}

	public void setNombrePropietario(final String nombrePropietario) {
		this.nombrePropietario = nombrePropietario;
	}

	public String getNombreCompleto() {
		return this.nombreCompleto;
	}

	public void setNombreCompleto(final String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(final String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Integer getNumAnuncios() {
		return this.numAnuncios;
	}

	public void setNumAnuncios(final Integer numAnuncios) {
		this.numAnuncios = numAnuncios;
	}

	public String getIdUsuarioCodificado() {
		return this.idUsuarioCodificado;
	}

	public void setIdUsuarioCodificado(final String idUsuarioCodificado) {
		this.idUsuarioCodificado = idUsuarioCodificado;
	}

	public String getDireccionCompleta() {
		return this.direccionCompleta;
	}

	public void setDireccionCompleta(final String direccionCompleta) {
		this.direccionCompleta = direccionCompleta;
	}

	public String getPrivacidad() {
		return this.privacidad;
	}

	public void setPrivacidad(final String privacidad) {
		this.privacidad = privacidad;
	}

	public String getTerminos() {
		return this.terminos;
	}

	public void setTerminos(final String terminos) {
		this.terminos = terminos;
	}

	@Override
	public String toString() {
		return "UsuarioUI [idUsuario=" + this.idUsuario + ", nombre=" + this.nombre + ", apellidos=" + this.apellidos + ", mail=" + this.mail + ", movil=" + this.movil + ", direccion1=" + this.direccion1 + ", direccion2=" + this.direccion2 + ", provincia=" + this.provincia + ", pais=" + this.pais + ", divisa=" + this.divisa + ", codPostal=" + this.codPostal + ", admin=" + this.admin + ", pass=" + this.pass + ", lang=" + this.lang + ", ip=" + this.ip + ", fallosLogin=" + this.fallosLogin + ", idUsuarioAlta=" + this.idUsuarioAlta + ", fecAlta=" + this.fecAlta + ", idUsuarioModif=" + this.idUsuarioModif + ", fecModif=" + this.fecModif + ", tipoAlta=" + this.tipoAlta + ", numAnuncios=" + this.numAnuncios + ", idUsuarioCodificado=" + this.idUsuarioCodificado + ", nombrePropietario=" + this.nombrePropietario + ", nombreCompleto=" + this.nombreCompleto + ", direccionCompleta=" + this.direccionCompleta + ", fechaAlta=" + this.fechaAlta + ", fechaOrdenacion=" + this.fechaOrdenacion + "]";
	}

	public String getBotonera() {
		final StringBuilder ret = new StringBuilder();
		ret.append(getBotonVer());
		ret.append(" ");
		ret.append(getBotonMod());
		ret.append(" ");
		ret.append(getBotonDel());
		return ret.toString();
	}

	/**
	 * Gets the boton edita anuncio.
	 *
	 * @return the boton edita anuncio
	 */
	public String getBotonMod() {
		final StringBuilder sb = new StringBuilder();
		sb.append("<button id=\"btnEditaUsuario_" + this.idUsuarioCodificado + "\" cod=\"" + this.idUsuarioCodificado + "\" type=\"button\" class=\"btnModUsuario btn btn-info btn-circle\">");
		sb.append("<i class=\"fa fa-pencil\"></i>");
		sb.append("</button>");
		return sb.toString();
	}

	/**
	 * Gets the boton elimina anuncio.
	 *
	 * @return the boton elimina anuncio
	 */
	public String getBotonDel() {
		final StringBuilder sb = new StringBuilder();
		sb.append("<button id=\"btnEliminaUsuario_" + this.idUsuarioCodificado + "\" cod=\"" + this.idUsuarioCodificado + "\" type=\"button\" class=\"btnDelUsuario btn btn-danger btn-circle");
		sb.append("\"><i class=\"fa fa-trash\"></i>");
		sb.append("</button>");
		return sb.toString();
	}

	/**
	 * Gets the boton ver anuncio.
	 *
	 * @return the boton ver anuncio
	 */
	public String getBotonVer() {
		final StringBuilder sb = new StringBuilder();
		sb.append("<button id=\"btnVerUsuario_" + this.idUsuarioCodificado + "\" cod=\"" + this.idUsuarioCodificado + "\" type=\"button\" class=\"btnVerUsuario btn btn-warning btn-circle");
		sb.append("\"><i class=\"fa fa-eye\"></i>");
		sb.append("</button>");
		return sb.toString();
	}
}
