package com.spinnakersimple.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.validators.Validadores;
import com.spinnakersimple.web.ui.bean.UsuarioUI;

/**
 * La clase Class UsuarioValidator.
 *
 * @author <a href="mailto:yo@capitanperegrina.com">Capitán Peregrina</a>
 */
@Component
public class UsuarioValidator implements Validator {

	/** La constante FIELD_NOMBRE. */
	protected static final String FIELD_NOMBRE = "nombre";

	/** La constante FIELD_APELLIDOS. */
	protected static final String FIELD_APELLIDOS = "apellidos";

	/** La constante FIELD_DIRECCION. */
	protected static final String FIELD_DIRECCION = "direccion";

	/** La constante FIELD_LOCALIDAD. */
	protected static final String FIELD_LOCALIDAD = "localidad";

	/** La constante FIELD_CODPOSTAL. */
	protected static final String FIELD_CODPOSTAL = "codPostal";

	/** La constante FIELD_PROVINCIA. */
	protected static final String FIELD_PROVINCIA = "provincia";

	/** La constante FIELD_PAIS. */
	protected static final String FIELD_PAIS = "pais";

	/** La constante FIELD_EMAIL. */
	protected static final String FIELD_EMAIL = "mail";

	/** La constante FIELD_MOVIL. */
	protected static final String FIELD_MOVIL = "movil";

	/** La constante FIELD_PASS. */
	protected static final String FIELD_PASS = "pass";

	/** La constante FIELD_LANG. */
	protected static final String FIELD_LANG = "lang";

	protected static final String FIELD_PRIVACIDAD = "privacidad";

	protected static final String FIELD_TERMINOS = "terminos";

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(final Class<?> clazz) {
		return UsuarioUI.class.equals(clazz);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(final Object obj, final Errors errors) {

		final UsuarioUI f = (UsuarioUI) obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, FIELD_NOMBRE, DefaultGlobalNames.FORM_VALIDATION_MADATORY_FIELD);

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, FIELD_EMAIL, DefaultGlobalNames.FORM_VALIDATION_MADATORY_FIELD);

		if (!Validadores.estaVacia(f.getMail()) && !Validadores.esCorreoElectronico(f.getMail())) {
			errors.rejectValue(FIELD_EMAIL, DefaultGlobalNames.FORM_VALIDATION_MAIL_FIELD);
		}

		if (!Validadores.estaVacia(f.getMovil()) && !Validadores.esTelefono(f.getMovil())) {
			errors.rejectValue(FIELD_MOVIL, DefaultGlobalNames.FORM_VALIDATION_TELEPHONE_FIELD);
		}

		if (f.getPais().equals(DefaultGlobalNames.NADA_SELECCIONADO_EN_LISTA)) {
			errors.rejectValue(FIELD_PAIS, DefaultGlobalNames.FORM_VALIDATION_MADATORY_FIELD);
		}

		if (!Validadores.estaVacia(f.getCodPostal()) && !Validadores.esCodigoPostal(f.getCodPostal())) {
			errors.rejectValue(FIELD_CODPOSTAL, DefaultGlobalNames.FORM_VALIDATION_CODIGOPOSTAL_FIELD);
		}

		if (Validadores.estaVacia(f.getPrivacidad()) || !f.getPrivacidad().equals(DefaultGlobalNames.SI)) {
			errors.rejectValue(FIELD_PRIVACIDAD, DefaultGlobalNames.FORM_VALIDATION_PRIVACIDAD_FIELD);
		}

		if (Validadores.estaVacia(f.getTerminos()) || !f.getTerminos().equals(DefaultGlobalNames.SI)) {
			errors.rejectValue(FIELD_TERMINOS, DefaultGlobalNames.FORM_VALIDATION_TERMINOS_FIELD);
		}
	}
}
