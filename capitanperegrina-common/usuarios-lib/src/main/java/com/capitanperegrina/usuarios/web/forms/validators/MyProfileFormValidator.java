package com.capitanperegrina.usuarios.web.forms.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.capitanperegrina.common.validators.EmailValidator;
import com.capitanperegrina.usuarios.web.forms.UserFormPOJO;

@Component
public class MyProfileFormValidator  implements Validator 
{
	@Autowired
	EmailValidator emailValidator;

	@Override
	public boolean supports(Class<?> clazz) {
		return UserFormPOJO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) 
	{
		UserFormPOJO usuario = (UserFormPOJO) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mail", "error.notEmpty.login");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nick", "error.notEmpty.nick");

		if( ! emailValidator.valid( usuario.getMail() ) )
		{
			errors.rejectValue("email", "email.erroneo");
		}
	}
}
