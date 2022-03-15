package com.capitanperegrina.common.modelo.validator.impl;

import org.apache.commons.validator.routines.UrlValidator;

import com.capitanperegrina.common.modelo.validator.ParamValidator;

public class URLParamValidator implements ParamValidator {

	@Override
	public boolean valid(String valor) {
		UrlValidator urlValidator = new UrlValidator();
		return urlValidator.isValid(valor);
	}
}
