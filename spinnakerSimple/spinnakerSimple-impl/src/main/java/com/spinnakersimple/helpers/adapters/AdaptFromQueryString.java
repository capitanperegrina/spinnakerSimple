package com.spinnakersimple.helpers.adapters;

import java.util.ArrayList;
import java.util.List;

import com.spinnakersimple.modelo.dto.FiltroBusquedaVelaBean;

/**
 * La clase Class AdaptFromQueryString.
 * @author <a href="mailto:yo@capitanperegrina.com">Capitán Peregrina</a>
 */
public class AdaptFromQueryString {

	private AdaptFromQueryString() {
		super();
	}

	/**
	 * Adapt to mis anuncios form DTO.
	 * @param s the s
	 * @return the filtro busqueda vela bean
	 */
	public static FiltroBusquedaVelaBean adaptToMisAnunciosFormDTO(String s) {
		final FiltroBusquedaVelaBean ret = new FiltroBusquedaVelaBean();
		final String[] params = s.split(";");
		ret.setTipoVela(Integer.parseInt(params[0]));
		final List<String> checks = new ArrayList<String>();
		if (Boolean.parseBoolean(params[1])) {
			checks.add("0");
		}
		if (Boolean.parseBoolean(params[2])) {
			checks.add("1");
		}
		if (Boolean.parseBoolean(params[3])) {
			checks.add("2");
		}
		String[] checksArray = new String[checks.size()];
		checksArray = checks.toArray(checksArray);
		ret.setTipoAnuncio(checksArray);
		return ret;
	}
}
