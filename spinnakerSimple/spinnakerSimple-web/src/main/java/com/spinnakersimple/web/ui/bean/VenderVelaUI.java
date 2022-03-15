package com.spinnakersimple.web.ui.bean;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.capitanperegrina.common.web.ui.CaptchaUI;

/**
 * The Class VenderVelaUI.
 *
 * @author <a href="mailto:yo@capitanperegrina.com">Capitán Peregrina</a>
 */
public class VenderVelaUI extends CaptchaUI implements Serializable {

    private static final long serialVersionUID = -5718173997575435700L;

    /** The id anuncio. */
    protected String idAnuncio;

    /** The titulo anuncio. */
    protected String tituloAnuncio;

    /** The tipo vela. */
    protected String tipoClase;

    /** The tipo vela. */
    protected String tipoBarco;

    /** The tipo vela. */
    protected String tipoVela;

    protected String gramaje;

    /** The gratil. */
    protected String gratil;

    /** The baluma. */
    protected String baluma;

    /** The pujamen. */
    protected String pujamen;

    protected String caidaProa;
    protected String caidaPopa;
    protected String superficie;
    protected String tipoCometa;

    /** The descripcion. */
    protected String descripcion;

    /** The precio. */
    protected String precio;

    protected String paisVela;

    /** The nombre. */
    protected String nombre;

    /** The apellidos. */
    protected String apellidos;

    /** The direccion. */
    protected String direccion1;

    /** The direccion. */
    protected String direccion2;

    /** The codPostal. */
    protected String codPostal;

    /** The provincia. */
    protected String provincia;

    /** The pais. */
    protected String pais;

    /** The pais. */
    protected String divisa;

    /** The email. */
    protected String email;

    /** The movil. */
    protected String movil;

    /** The images. */
    protected List<MultipartFile> files;

    /** The pass. */
    protected String pass;

    /** The lang. */
    protected String lang;

    /** The email login. */
    protected String emailLogin;

    /** The pass login. */
    protected String passLogin;

    /** The tipo alta. */
    protected String tipoAlta;

    protected String caduca;

    /** El campo id anuncio codificado. */
    protected String idAnuncioCodificado;

    protected String privacidad;
    protected String terminos;

    protected String tipoBarcoDescripcion;

    /**
     * Instantiates a new vender vela DTO.
     */
    public VenderVelaUI() {
        super();
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
     * Gets the baluma.
     *
     * @return the baluma
     */
    public String getBaluma() {
        return this.baluma;
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
     * Gets the descripcion.
     *
     * @return the descripcion
     */
    public String getDescripcion() {
        return this.descripcion;
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
     * Gets the direccion 2.
     *
     * @return the direccion 2
     */
    public String getDireccion2() {
        return this.direccion2;
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
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Gets the email login.
     *
     * @return the email login
     */
    public String getEmailLogin() {
        return this.emailLogin;
    }

    /**
     * Gets the files.
     *
     * @return the files
     */
    public List<MultipartFile> getFiles() {
        return this.files;
    }

    /**
     * Gets the gratil.
     *
     * @return the gratil
     */
    public String getGratil() {
        return this.gratil;
    }

    /**
     * Gets the id anuncio.
     *
     * @return the id anuncio
     */
    public String getIdAnuncio() {
        return this.idAnuncio;
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
     * Gets the movil.
     *
     * @return the movil
     */
    public String getMovil() {
        return this.movil;
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
     * Gets the pais.
     *
     * @return the pais
     */
    public String getPais() {
        return this.pais;
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
     * Gets the pass login.
     *
     * @return the pass login
     */
    public String getPassLogin() {
        return this.passLogin;
    }

    /**
     * Gets the precio.
     *
     * @return the precio
     */
    public String getPrecio() {
        return this.precio;
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
     * Gets the pujamen.
     *
     * @return the pujamen
     */
    public String getPujamen() {
        return this.pujamen;
    }

    /**
     * Gets the tipo alta.
     *
     * @return the tipo alta
     */
    public String getTipoAlta() {
        return this.tipoAlta;
    }

    /**
     * Gets the tipo vela.
     *
     * @return the tipo vela
     */
    public String getTipoVela() {
        return this.tipoVela;
    }

    /**
     * Gets the titulo anuncio.
     *
     * @return the titulo anuncio
     */
    public String getTituloAnuncio() {
        return this.tituloAnuncio;
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
     * Sets the baluma.
     *
     * @param baluma the new baluma
     */
    public void setBaluma(final String baluma) {
        this.baluma = baluma;
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
     * Sets the descripcion.
     *
     * @param descripcion the new descripcion
     */
    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
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
     * Sets the direccion 2.
     *
     * @param direccion2 the new direccion 2
     */
    public void setDireccion2(final String direccion2) {
        this.direccion2 = direccion2;
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
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Sets the email login.
     *
     * @param emailLogin the new email login
     */
    public void setEmailLogin(final String emailLogin) {
        this.emailLogin = emailLogin;
    }

    /**
     * Sets the files.
     *
     * @param files the new files
     */
    public void setFiles(final List<MultipartFile> files) {
        this.files = files;
    }

    /**
     * Sets the gratil.
     *
     * @param gratil the new gratil
     */
    public void setGratil(final String gratil) {
        this.gratil = gratil;
    }

    /**
     * Sets the id anuncio.
     *
     * @param idAnuncio the new id anuncio
     */
    public void setIdAnuncio(final String idAnuncio) {
        this.idAnuncio = idAnuncio;
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
     * Sets the movil.
     *
     * @param movil the new movil
     */
    public void setMovil(final String movil) {
        this.movil = movil;
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
     * Sets the pais.
     *
     * @param pais the new pais
     */
    public void setPais(final String pais) {
        this.pais = pais;
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
     * Sets the pass login.
     *
     * @param passLogin the new pass login
     */
    public void setPassLogin(final String passLogin) {
        this.passLogin = passLogin;
    }

    /**
     * Sets the precio.
     *
     * @param precio the new precio
     */
    public void setPrecio(final String precio) {
        this.precio = precio;
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
     * Sets the pujamen.
     *
     * @param pujamen the new pujamen
     */
    public void setPujamen(final String pujamen) {
        this.pujamen = pujamen;
    }

    /**
     * Sets the tipo alta.
     *
     * @param tipoAlta the new tipo alta
     */
    public void setTipoAlta(final String tipoAlta) {
        this.tipoAlta = tipoAlta;
    }

    /**
     * Sets the tipo vela.
     *
     * @param tipoVela the new tipo vela
     */
    public void setTipoVela(final String tipoVela) {
        this.tipoVela = tipoVela;
    }

    /**
     * Sets the titulo anuncio.
     *
     * @param tituloAnuncio the new titulo anuncio
     */
    public void setTituloAnuncio(final String tituloAnuncio) {
        this.tituloAnuncio = tituloAnuncio;
    }

    /**
     * Obtiene la propiedad id anuncio codificado.
     *
     * @return la propiedad id anuncio codificado
     */
    public String getIdAnuncioCodificado() {
        return this.idAnuncioCodificado;
    }

    /**
     * Establece la propiedad id anuncio codificado.
     *
     * @param idAnuncioCodificado el nuevo valor de la propiedad id anuncio
     *                            codificado
     */
    public void setIdAnuncioCodificado(final String idAnuncioCodificado) {
        this.idAnuncioCodificado = idAnuncioCodificado;
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

    public String getCaduca() {
        return this.caduca;
    }

    public void setCaduca(final String caduca) {
        this.caduca = caduca;
    }

    public String getTipoClase() {
        return this.tipoClase;
    }

    public void setTipoClase(final String tipoClase) {
        this.tipoClase = tipoClase;
    }

    public String getTipoBarco() {
        return this.tipoBarco;
    }

    public void setTipoBarco(final String tipoBarco) {
        this.tipoBarco = tipoBarco;
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

    public String getTipoBarcoDescripcion() {
        return this.tipoBarcoDescripcion;
    }

    public void setTipoBarcoDescripcion(final String tipoBarcoDescripcion) {
        this.tipoBarcoDescripcion = tipoBarcoDescripcion;
    }

    public String getGramaje() {
        return this.gramaje;
    }

    public void setGramaje(final String gramaje) {
        this.gramaje = gramaje;
    }

    public String getPaisVela() {
        return this.paisVela;
    }

    public void setPaisVela(final String paisVela) {
        this.paisVela = paisVela;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("VenderVelaUI [idAnuncio=").append(this.idAnuncio).append(", tituloAnuncio=").append(this.tituloAnuncio)
                .append(", tipoClase=").append(this.tipoClase).append(", tipoBarco=").append(this.tipoBarco).append(", tipoVela=")
                .append(this.tipoVela).append(", gramaje=").append(this.gramaje).append(", gratil=").append(this.gratil)
                .append(", baluma=").append(this.baluma).append(", pujamen=").append(this.pujamen).append(", caidaProa=")
                .append(this.caidaProa).append(", caidaPopa=").append(this.caidaPopa).append(", superficie=")
                .append(this.superficie).append(", tipoCometa=").append(this.tipoCometa).append(", descripcion=")
                .append(this.descripcion).append(", precio=").append(this.precio).append(", paisVela=").append(this.paisVela)
                .append(", nombre=").append(this.nombre).append(", apellidos=").append(this.apellidos).append(", direccion1=")
                .append(this.direccion1).append(", direccion2=").append(this.direccion2).append(", codPostal=")
                .append(this.codPostal).append(", provincia=").append(this.provincia).append(", pais=").append(this.pais)
                .append(", divisa=").append(this.divisa).append(", email=").append(this.email).append(", movil=").append(this.movil)
                .append(", files=").append(this.files).append(", pass=").append(this.pass).append(", lang=").append(this.lang)
                .append(", emailLogin=").append(this.emailLogin).append(", passLogin=").append(this.passLogin).append(", tipoAlta=")
                .append(this.tipoAlta).append(", caduca=").append(this.caduca).append(", idAnuncioCodificado=")
                .append(this.idAnuncioCodificado).append(", privacidad=").append(this.privacidad).append(", terminos=")
                .append(this.terminos).append(", tipoBarcoDescripcion=").append(this.tipoBarcoDescripcion).append("]");
        return builder.toString();
    }
}
