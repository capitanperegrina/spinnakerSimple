package com.spinnakersimple.helpers.adapters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capitanperegrina.common.seguridad.Criptografia;
import com.spinnakersimple.modelo.bean.MisAnunciosBean;
import com.spinnakersimple.modelo.dto.VenderVelaDTO;
import com.spinnakersimple.modelo.entidad.UsuarioPOJO;

/**
 * The Class AdapterToDtoHelper.
 */
@Component
public class AdapterToDtoHelper {

	/** The criptografia. */
	@Autowired
	Criptografia criptografia;

	/**
	 * Instantiates a new adapter to dto.
	 */
	private AdapterToDtoHelper() {
		super();
	}

	/**
	 * Adapt.
	 * @param obj the obj
	 * @return the anuncio DTO
	 */

	/**
	 * Adapt.
	 * @param u the u
	 * @return the vender vela form DTO
	 */
	public VenderVelaDTO adapt(UsuarioPOJO u) {
		if (u == null) {
			return null;
		}
		final VenderVelaDTO obj = new VenderVelaDTO();
		obj.setNombre(u.getNombre());
		obj.setApellidos(u.getApellidos());
		obj.setDireccion1(u.getDireccion1());
		obj.setDireccion2(u.getDireccion2());
		obj.setCodPostal(u.getCodPostal());
		obj.setProvincia(u.getProvincia());
		obj.setPais(u.getPais());
		obj.setDivisa(u.getDivisa());
		obj.setEmail(u.getMail());
		obj.setMovil(u.getMovil());
		return obj;
	}

	/**
	 * Adapt to mis anuncios DTO.
	 * @param s the s
	 * @return the mis anuncios DTO
	 */
	public static MisAnunciosBean adaptToMisAnunciosDTO(String s) {
		if (s == null) {
			return null;
		}
		final MisAnunciosBean ret = new MisAnunciosBean();
		final String[] params = s.split(";");
		ret.setTipoVela(Integer.parseInt(params[0]));
		final List<String> checks = new ArrayList<>();
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
