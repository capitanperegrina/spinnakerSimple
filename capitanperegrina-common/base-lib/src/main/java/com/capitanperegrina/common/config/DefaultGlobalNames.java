package com.capitanperegrina.common.config;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase de constantes globales.
 */
public class DefaultGlobalNames {

	public static final Map<String, String> type2contentType = new HashMap<String, String>(3);
	public static final Map<String, String> contentType2type = new HashMap<String, String>(3);

	static {
		type2contentType.put("jpg", "image/jpeg");
		type2contentType.put("gif", "image/gif");
		type2contentType.put("png", "image/png");

		contentType2type.put("image/jpeg", "jpg");
		contentType2type.put("image/gif", "gif");
		contentType2type.put("image/png", "png");
	}

	// public static final String SEMILLA = "1234567890";
	// public static final String SEMILLA = "$dN7s2k7/f.FsWm4D&L9=8cW-xG";

	public static final String SI = "S";
	public static final String NO = "N";
	public static final String INDEFINIDO = "X";

	public static final String ALTA = "A";
	public static final String BAJA = "B";

	public static final String ACCION = "accion";
	public static final String ACCION_ADD = "A";
	public static final String ACCION_MOD = "M";
	public static final String ACCION_DEL = "D";
	public static final String ACCION_SEE = "C";

	public static final String NADA = "";
	public static final String NADA_SELECCIONADO_EN_LISTA = "-1";

	public static final String IDIOMA_DEFECTO = "es";
	public static final String MONEDA = "EUR";

	public static final String CONFIGURATIONPARAMETERS = "ConfigurationParameters.properties";
	public static final String CHECKEXECUTIONPARAMETERS = "CheckExecutionParameters.properties";

	public static final Integer MAX_REGISTROS_BUSQUEDA = 250;

	public static final String NIF = "NIF";
	public static final String PASAPORTE = "PAS";

	public static final String USERSESSION = "usuarioEnSesion";
	public static final String LANGSESSION = "langEnSesion";

	public static final String TIPO_JAVA_STRING = "java.lang.String";
	public static final String TIPO_JAVA_INTEGER = "java.lang.Integer";

	public static final String TIPO_JAVASCRIPT_STRING = "S";
	public static final String TIPO_JAVASCRIPT_INTEGER = "I";

	public static final Integer ID_USUARIO_ANONIMO = 0;

	public static final String PAREMETRO_MODO_DEBUG = "modo.debug";

	public static final String JSON_RES_OK = "OK";
	public static final String JSON_RES_ERR = "ERR";

	public static final String REGEXP_BIGDECIMAL = "regEx.bigDecimal";

	public static final String FORM_VALIDATION_MADATORY_FIELD = "global.campoObligatorio";
	public static final String FORM_VALIDATION_NUMERIC_INTEGER_FIELD = "global.campoNumerico.entero";
	public static final String FORM_VALIDATION_NUMERIC_BIGDECIMAL_FIELD = "global.campoNumerico";
	public static final String FORM_VALIDATION_NUMERIC_BIGDECIMAL_FORMAT_FIELD = "regEx.bigDecimal.err";

	public static final String FORM_VALIDATION_MARK_FIELD = "global.err.marcaCampo";
	public static final String FORM_VALIDATION_MAIL_FIELD = "validacion.error.email";
	public static final String FORM_VALIDATION_TELEPHONE_FIELD = "validacion.error.telefono";
	public static final String FORM_VALIDATION_CODIGOPOSTAL_FIELD = "validacion.error.codigoPostal";
	public static final String FORM_VALIDATION_FICHERO_VACIO_UNO = "validacion.error.ficheroVacio.uno";
	public static final String FORM_VALIDATION_IMAGEN_VALIDA_UNO = "validacion.error.ficheroNoEsImagenValida.uno";
	public static final String FORM_VALIDATION_FICHERO_VACIO_VARIOS = "validacion.error.ficheroVacio.varios";
	public static final String FORM_VALIDATION_IMAGEN_VALIDA_VARIOS = "validacion.error.ficheroNoEsImagenValida.varios";
	public static final String FORM_VALIDATION_PRIVACIDAD_FIELD = "validacion.error.privacidad";
	public static final String FORM_VALIDATION_TERMINOS_FIELD = "validacion.error.terminos";

	public static final SimpleDateFormat FORMATO_FECHA_ORDENACION = new SimpleDateFormat("yyyy-MM-dd");

	public static final String MENSAJE_MSG = "msg";

	public static final String DELIMITADOR_INICIAL_VARIABLES_SUBSTITUIBLES = "[i18n]";
	public static final String DELIMITADOR_FINAL_VARIABLES_SUBSTITUIBLES = "[/i18n]";

	public static final String PREFIJO_FILTRO_INI = "F> ";
	public static final String PREFIJO_CONTROLADOR_INI = "C>> ";
	public static final String PREFIJO_SERVICIO_INI = "S>>> ";
	public static final String PREFIJO_HELPER_INI = "H>>>> ";
	public static final String PREFIJO_UTIL_INI = "U>>>> ";
	public static final String PREFIJO_DAO_INI = "D>>>>> ";

	public static final String PREFIJO_FILTRO_FIN = "F< ";
	public static final String PREFIJO_CONTROLADOR_FIN = "C<< ";
	public static final String PREFIJO_SERVICIO_FIN = "S<<< ";
	public static final String PREFIJO_HELPER_FIN = "H<<<< ";
	public static final String PREFIJO_UTIL_FIN = "U<<<< ";
	public static final String PREFIJO_DAO_FIN = "D<<<<< ";

	public static final String COD_DIVISA_EURO = "EUR";

	public static final String HTML_BR = "<br/>";

	public static final Integer PORCENTAJE_DISTANCIA_LEVENSHTEIN_POR_DEFECTO = 80;

	protected DefaultGlobalNames() {
		super();
	}
}
