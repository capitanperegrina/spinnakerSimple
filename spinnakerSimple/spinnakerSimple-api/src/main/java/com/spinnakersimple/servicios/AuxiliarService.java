package com.spinnakersimple.servicios;

import java.util.List;

import com.spinnakersimple.modelo.entidad.SliderPOJO;

/**
 * The Interface AuxiliarService.
 */
public interface AuxiliarService {

	/**
	 * Lee imagen cabecera.
	 *
	 * @param tipoImagen
	 *            the tipo imagen
	 * @return the list
	 */
	public List<SliderPOJO> leeImagenCabecera(String tipoImagen);

	/**
	 * Lee parametro.
	 *
	 * @param clave
	 *            the clave
	 * @param lang
	 *            the lang
	 * @return the string
	 */
	public String leeParametro(String clave, String lang);

	public double leeTipoDeCambio(String divisa);
}
