package com.spinnakersimple.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.validators.Validadores;
import com.spinnakersimple.web.ui.bean.ConsultarVelaUI;

/**
 * The Class ConsultarVelaValidator.
 */
@Component
public class ConsultarVelaValidator implements Validator {

	/** The Constant FIELD_ID_ARTICULO. */
	protected static final String FIELD_ID_ARTICULO = "ida";

	/** The Constant FIELD_NOMBRE. */
	protected static final String FIELD_NOMBRE = "nombre";

	/** The Constant FIELD_MAIL. */
	protected static final String FIELD_MAIL = "mail";

	/** The Constant FIELD_TELEFONO. */
	protected static final String FIELD_TELEFONO = "telefono";

	/** The Constant FIELD_OBSERVACIONES. */
	protected static final String FIELD_OBSERVACIONES = "observaciones";

	protected static final String FIELD_PRIVACIDAD = "privacidad";

	protected static final String FIELD_TERMINOS = "terminos";

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(final Class<?> clazz) {
		return ConsultarVelaUI.class.equals(clazz);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(final Object obj, final Errors errors) {
		final ConsultarVelaUI f = (ConsultarVelaUI) obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, FIELD_NOMBRE, DefaultGlobalNames.FORM_VALIDATION_MADATORY_FIELD);

		if (Validadores.estaVacia(f.getMail()) && Validadores.estaVacia(f.getTelefono())) {
			errors.rejectValue(ConsultarVelaValidator.FIELD_MAIL, "ConsultarVelaValidator.mailOtelefono");
			errors.rejectValue(ConsultarVelaValidator.FIELD_TELEFONO, "ConsultarVelaValidator.mailOtelefono");
		} else {
			if (!Validadores.estaVacia(f.getMail()) && !Validadores.esCorreoElectronico(f.getMail())) {
				errors.rejectValue(ConsultarVelaValidator.FIELD_MAIL, DefaultGlobalNames.FORM_VALIDATION_MAIL_FIELD);
			}

			if (!Validadores.estaVacia(f.getTelefono()) && !Validadores.esTelefono(f.getTelefono())) {
				errors.rejectValue(ConsultarVelaValidator.FIELD_TELEFONO, DefaultGlobalNames.FORM_VALIDATION_TELEPHONE_FIELD);
			}
		}

		if (Validadores.estaVacia(f.getPrivacidad()) || !f.getPrivacidad().equals(DefaultGlobalNames.SI)) {
			errors.rejectValue(FIELD_PRIVACIDAD, DefaultGlobalNames.FORM_VALIDATION_PRIVACIDAD_FIELD);
		}
	}
}
