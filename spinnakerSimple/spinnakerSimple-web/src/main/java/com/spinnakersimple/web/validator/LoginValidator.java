package com.spinnakersimple.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.validators.Validadores;
import com.spinnakersimple.modelo.util.SpinnakerSimpleGlobals;
import com.spinnakersimple.web.ui.bean.LoginUI;

/**
 * The Class LoginValidator.
 */
@Component
public class LoginValidator implements Validator {

	/** The Constant FIELD_EMAIL_LOGIN. */
	protected static final String FIELD_EMAIL_LOGIN = "emailLogin";

	/** The Constant FIELD_PASS_LOGIN. */
	protected static final String FIELD_PASS_LOGIN = "passLogin";
	protected static final String FIELD_PASS_NEW = "passNew";
	protected static final String FIELD_PASS_REPETIDA = "passRepetida";

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return LoginUI.class.equals(clazz);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object obj, Errors errors) {

		final LoginUI f = (LoginUI) obj;

		if (f.getTipoUso().equals(SpinnakerSimpleGlobals.LOGIN_TIPO_USO_LOGIN)) {

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, FIELD_EMAIL_LOGIN,
					DefaultGlobalNames.FORM_VALIDATION_MADATORY_FIELD);

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, FIELD_PASS_LOGIN,
					DefaultGlobalNames.FORM_VALIDATION_MADATORY_FIELD);

			if (!Validadores.estaVacia(f.getEmailLogin()) && !Validadores.esCorreoElectronico(f.getEmailLogin())) {
				errors.rejectValue(VenderVelaValidator.FIELD_EMAIL_LOGIN,
						DefaultGlobalNames.FORM_VALIDATION_MAIL_FIELD);
			}

		} else if (f.getTipoUso().equals(SpinnakerSimpleGlobals.LOGIN_TIPO_USO_RESET)) {

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, FIELD_PASS_NEW,
					DefaultGlobalNames.FORM_VALIDATION_MADATORY_FIELD);

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, FIELD_PASS_REPETIDA,
					DefaultGlobalNames.FORM_VALIDATION_MADATORY_FIELD);

			if (errors.getErrorCount() == 0 && !f.getPassNew().equals(f.getPassRepetida())) {
				errors.rejectValue(FIELD_PASS_REPETIDA, "loginForm.claveRepetidaDistinta");
			}

		} else if (f.getTipoUso().equals(SpinnakerSimpleGlobals.LOGIN_TIPO_USO_CAMBIO)) {

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, FIELD_PASS_LOGIN,
					DefaultGlobalNames.FORM_VALIDATION_MADATORY_FIELD);

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, FIELD_PASS_NEW,
					DefaultGlobalNames.FORM_VALIDATION_MADATORY_FIELD);

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, FIELD_PASS_REPETIDA,
					DefaultGlobalNames.FORM_VALIDATION_MADATORY_FIELD);

			if (errors.getErrorCount() == 0 && !f.getPassNew().equals(f.getPassRepetida())) {
				errors.rejectValue(FIELD_PASS_REPETIDA, "loginForm.claveRepetidaDistinta");
			}

		} else if (f.getTipoUso().equals(SpinnakerSimpleGlobals.LOGIN_TIPO_USO_RECORDAR)) {

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, FIELD_EMAIL_LOGIN,
					DefaultGlobalNames.FORM_VALIDATION_MADATORY_FIELD);

		}
	}
}
