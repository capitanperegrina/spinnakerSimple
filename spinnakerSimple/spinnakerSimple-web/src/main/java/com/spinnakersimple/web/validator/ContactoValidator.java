package com.spinnakersimple.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.validators.Validadores;
import com.spinnakersimple.web.ui.bean.ContactoUI;

/**
 * The Class ContactoValidator.
 */
@Component
public class ContactoValidator implements Validator {

	/** The Constant FIELD_NOMBRE. */
	protected static final String FIELD_NOMBRE = "nombre";

	/** The Constant FIELD_MAIL. */
	protected static final String FIELD_MAIL = "email";

	/** The Constant FIELD_TELEFONO. */
	protected static final String FIELD_CONSULTA = "consulta";

	protected static final String FIELD_PRIVACIDAD = "privacidad";

	protected static final String FIELD_TERMINOS = "terminos";

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(final Class<?> clazz) {
		return ContactoUI.class.equals(clazz);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(final Object obj, final Errors errors) {
		final ContactoUI f = (ContactoUI) obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, FIELD_NOMBRE, DefaultGlobalNames.FORM_VALIDATION_MADATORY_FIELD);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, FIELD_MAIL, DefaultGlobalNames.FORM_VALIDATION_MADATORY_FIELD);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, FIELD_CONSULTA, DefaultGlobalNames.FORM_VALIDATION_MADATORY_FIELD);

		if (!Validadores.estaVacia(f.getEmail()) && !Validadores.esCorreoElectronico(f.getEmail())) {
			errors.rejectValue(ConsultarVelaValidator.FIELD_MAIL, DefaultGlobalNames.FORM_VALIDATION_MAIL_FIELD);
		}

		if (Validadores.estaVacia(f.getPrivacidad()) || !f.getPrivacidad().equals(DefaultGlobalNames.SI)) {
			errors.rejectValue(FIELD_PRIVACIDAD, DefaultGlobalNames.FORM_VALIDATION_PRIVACIDAD_FIELD);
		}
	}
}
