package com.capitanperegrina.common.modelo.validator.impl;

import com.capitanperegrina.common.modelo.validator.ParamValidator;
import com.capitanperegrina.common.validators.Validadores;

public class MailParamValidator implements ParamValidator {

	@Override
	public boolean valid(String valor) {
		return Validadores.esCorreoElectronico(valor);
	}
}
