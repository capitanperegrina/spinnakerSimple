package com.capitanperegrina.usuarios.web.forms.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.capitanperegrina.usuarios.web.forms.UserFormPOJO;

@Component
public class ResetPassFormValidator  implements Validator 
{

	@Override
	public boolean supports(Class<?> clazz) {
		return UserFormPOJO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) 
	{
		UserFormPOJO usuario = (UserFormPOJO) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pass", "error.notEmpty.password");
		
		if ( ! usuario.getPass().equals( usuario.getClaveRepetida() ) )
		{
			errors.rejectValue( "claveRepetida", "error.notEqual.passwords" );
		}
	}
}
