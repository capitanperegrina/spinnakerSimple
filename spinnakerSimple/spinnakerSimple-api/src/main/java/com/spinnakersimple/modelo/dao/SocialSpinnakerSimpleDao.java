package com.spinnakersimple.modelo.dao;

import org.apache.commons.lang3.tuple.Triple;

import com.capitanperegrina.common.modelo.dao.SocialDao;

public interface SocialSpinnakerSimpleDao extends SocialDao {

	Triple<Integer, Integer, Integer> proximaFotoACompartirEnRedesSociales();

	Triple<Integer, Integer, Integer> proximaFotoACompartirEnRedesSociales(final Integer idAnuncio);
}
