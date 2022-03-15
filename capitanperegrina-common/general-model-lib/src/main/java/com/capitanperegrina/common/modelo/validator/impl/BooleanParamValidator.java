package com.capitanperegrina.common.modelo.validator.impl;

import org.apache.commons.lang3.StringUtils;

import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.modelo.validator.ParamValidator;

public class BooleanParamValidator  implements ParamValidator {

	@Override
	public boolean valid(String valor) {
		String v = StringUtils.trimToEmpty(valor).toUpperCase();
		return v.equals(DefaultGlobalNames.SI) || v.equals(DefaultGlobalNames.NO);
	}
}
