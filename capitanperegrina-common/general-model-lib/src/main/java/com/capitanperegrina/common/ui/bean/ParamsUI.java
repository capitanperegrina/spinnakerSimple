package com.capitanperegrina.common.ui.bean;

public class ParamsUI {

    protected String idParam ;
    protected String visible ;
    protected String valor ;
    protected String tipoJava ;
    protected String tipoJs ;
    protected String idUsuarioAlta ;
    protected String fecAlta ;
    protected String idUsuarioModif ;
    protected String fecModif ;
    protected String idParamWeb;
    
    protected String label;
    
	public ParamsUI() {
		super();
	}

	public String getIdParam() {
		return idParam;
	}

	public void setIdParam(String idParam) {
		this.idParam = idParam;
	}

	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getTipoJava() {
		return tipoJava;
	}

	public void setTipoJava(String tipoJava) {
		this.tipoJava = tipoJava;
	}

	public String getTipoJs() {
		return tipoJs;
	}

	public void setTipoJs(String tipoJs) {
		this.tipoJs = tipoJs;
	}

	public String getIdUsuarioAlta() {
		return idUsuarioAlta;
	}

	public void setIdUsuarioAlta(String idUsuarioAlta) {
		this.idUsuarioAlta = idUsuarioAlta;
	}

	public String getFecAlta() {
		return fecAlta;
	}

	public void setFecAlta(String fecAlta) {
		this.fecAlta = fecAlta;
	}

	public String getIdUsuarioModif() {
		return idUsuarioModif;
	}

	public void setIdUsuarioModif(String idUsuarioModif) {
		this.idUsuarioModif = idUsuarioModif;
	}

	public String getFecModif() {
		return fecModif;
	}

	public void setFecModif(String fecModif) {
		this.fecModif = fecModif;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getIdParamWeb() {
		return idParamWeb;
	}

	public void setIdParamWeb(String idParamWeb) {
		this.idParamWeb = idParamWeb;
	}

	@Override
	public String toString() {
		return "ParamsUI [idParam=" + idParam + ", visible=" + visible + ", valor=" + valor + ", tipoJava=" + tipoJava
				+ ", tipoJs=" + tipoJs + ", idUsuarioAlta=" + idUsuarioAlta + ", fecAlta=" + fecAlta
				+ ", idUsuarioModif=" + idUsuarioModif + ", fecModif=" + fecModif + ", idParamWeb=" + idParamWeb
				+ ", label=" + label + "]";
	}
}
