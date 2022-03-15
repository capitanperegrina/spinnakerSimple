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
public class RegisterFormValidator implements Validator 
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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pass", "error.notEmpty.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nick", "error.notEmpty.nick");

		if ( ! StringUtils.isEmpty( usuario.getMail() ) ) {
			if( ! emailValidator.valid( usuario.getMail() ) )
			{
				errors.rejectValue("mail", "email.erroneo");
			}
			
		}
		
		if ( ! usuario.getPass().equals( usuario.getClaveRepetida() ) )
		{
			errors.rejectValue( "pass", "global.vacio" );
			errors.rejectValue( "claveRepetida", "error.notEqual.passwords" );
		}
	}
}
