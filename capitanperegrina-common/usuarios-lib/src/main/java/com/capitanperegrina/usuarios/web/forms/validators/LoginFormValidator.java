package com.capitanperegrina.usuarios.web.forms.validators;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.capitanperegrina.common.validators.EmailValidator;
import com.capitanperegrina.usuarios.web.forms.UserFormPOJO;

@Component
public class LoginFormValidator implements Validator {
	private final static String FIELD_MAIL = "mail";
	private final static String FIELD_PASS = "pass";

	@Autowired
	EmailValidator emailValidator;

	@Override
	public boolean supports(Class<?> clazz) {
		return UserFormPOJO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserFormPOJO usuario = (UserFormPOJO) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, FIELD_MAIL, "error.notEmpty.login");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, FIELD_PASS, "error.notEmpty.password");

		if (!StringUtils.isEmpty(usuario.getMail()) && !emailValidator.valid(usuario.getMail())) {
			errors.rejectValue(FIELD_MAIL, "error.email.noEsMail");
		}
	}
}
