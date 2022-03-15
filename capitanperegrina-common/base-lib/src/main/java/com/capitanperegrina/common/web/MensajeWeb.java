package com.capitanperegrina.common.web;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MensajeWeb implements Serializable {
    private static final long serialVersionUID = 4048675166315712960L;

    protected String var;
    protected String key;
    protected Object[] args;
    protected String tilesMapping;
    protected Integer pasosAtras = 1;
    protected String urlVolver;
    protected String cerrarDialogoMensaje;
    protected Map<String, Object> parametros = new HashMap<>();

    public MensajeWeb() {
        super();
    }

    public String getVar() {
        return this.var;
    }

    public void setVar(final String var) {
        this.var = var;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public Object[] getArgs() {
        return this.args;
    }

    public void setArgs(final Object[] args) {
        this.args = args;
    }

    public String getTilesMapping() {
        return this.tilesMapping;
    }

    public void setTilesMapping(final String tilesMapping) {
        this.tilesMapping = tilesMapping;
    }

    public Integer getPasosAtras() {
        return this.pasosAtras;
    }

    public void setPasosAtras(final Integer pasosAtras) {
        this.pasosAtras = pasosAtras;
    }

    public String getUrlVolver() {
        return this.urlVolver;
    }

    public void setUrlVolver(final String urlVolver) {
        this.urlVolver = urlVolver;
    }

    public String getCerrarDialogoMensaje() {
        return this.cerrarDialogoMensaje;
    }

    public void setCerrarDialogoMensaje(final String cerrarDialogoMensaje) {
        this.cerrarDialogoMensaje = cerrarDialogoMensaje;
    }

    public Map<String, Object> getParametros() {
        return this.parametros;
    }

    public void setParametros(final Map<String, Object> parametros) {
        this.parametros = parametros;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("MensajeWeb [var=").append(this.var).append(", key=").append(this.key).append(", args=")
                .append(Arrays.toString(this.args)).append(", tilesMapping=").append(this.tilesMapping).append(", pasosAtras=")
                .append(this.pasosAtras).append(", urlVolver=").append(this.urlVolver).append(", cerrarDialogoMensaje=")
                .append(this.cerrarDialogoMensaje).append(", parametros=").append(this.parametros).append("]");
        return builder.toString();
    }
}
