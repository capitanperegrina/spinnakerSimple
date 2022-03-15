package com.spinnakersimple.web.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.web.FormUtils;
import com.spinnakersimple.web.ui.bean.ComprarVelaUI;

/**
 * The Class ComprarVelaValidator.
 */
@Component
public class ComprarVelaValidator implements Validator {

	private static final String GENERR = "genErr";

	/** The Constant GRATILMAX. */
	private static final String GRATILMAX = "gratilmax";

	/** The Constant GRATILMIN. */
	private static final String GRATILMIN = "gratilmin";

	/** The Constant BALUMAMAX. */
	private static final String BALUMAMAX = "balumamax";

	/** The Constant BALUMAMIN. */
	private static final String BALUMAMIN = "balumamin";

	/** The Constant PUJAMENMAX. */
	private static final String PUJAMENMAX = "pujamenmax";

	/** The Constant PUJAMENMIN. */
	private static final String PUJAMENMIN = "pujamenmin";

	/** The Constant FORM_VALIDATION_RANGO_UN_CAMPO_VACIO. */
	private static final String FORM_VALIDATION_RANGO_UN_CAMPO_VACIO = "ComprarVelaValidator.err.rangoUnCampoVacio";

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(final Class<?> clazz) {
		return ComprarVelaUI.class.equals(clazz);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(final Object obj, final Errors errors) {

		final ComprarVelaUI f = (ComprarVelaUI) obj;

		//		if (Validadores.estaVacia(f.getGratilmax()) ^ Validadores.estaVacia(f.getGratilmin())) {
		//			if (Validadores.estaVacia(f.getGratilmax())) {
		//				errors.rejectValue(GRATILMAX, FORM_VALIDATION_RANGO_UN_CAMPO_VACIO);
		//			} else {
		//				errors.rejectValue(GRATILMIN, FORM_VALIDATION_RANGO_UN_CAMPO_VACIO);
		//			}
		//		}
		//
		//		if (Validadores.estaVacia(f.getBalumamax()) ^ Validadores.estaVacia(f.getBalumamin())) {
		//			if (Validadores.estaVacia(f.getBalumamax())) {
		//				errors.rejectValue(BALUMAMAX, FORM_VALIDATION_RANGO_UN_CAMPO_VACIO);
		//			} else {
		//				errors.rejectValue(BALUMAMIN, FORM_VALIDATION_RANGO_UN_CAMPO_VACIO);
		//			}
		//		}
		//
		//		if (Validadores.estaVacia(f.getPujamenmax()) ^ Validadores.estaVacia(f.getPujamenmin())) {
		//			if (Validadores.estaVacia(f.getPujamenmax())) {
		//				errors.rejectValue(PUJAMENMAX, FORM_VALIDATION_RANGO_UN_CAMPO_VACIO);
		//			} else {
		//				errors.rejectValue(PUJAMENMIN, FORM_VALIDATION_RANGO_UN_CAMPO_VACIO);
		//			}
		//		}

		if (!StringUtils.isEmpty(f.getBalumamax()) && !FormUtils.isBigDecimal(f.getBalumamax())) {
			errors.rejectValue(GENERR, DefaultGlobalNames.FORM_VALIDATION_NUMERIC_BIGDECIMAL_FORMAT_FIELD);
			errors.rejectValue(BALUMAMAX, DefaultGlobalNames.FORM_VALIDATION_MARK_FIELD);
		}
		if (!StringUtils.isEmpty(f.getBalumamin()) && !FormUtils.isBigDecimal(f.getBalumamin())) {
			errors.rejectValue(GENERR, DefaultGlobalNames.FORM_VALIDATION_NUMERIC_BIGDECIMAL_FORMAT_FIELD);
			errors.rejectValue(BALUMAMIN, DefaultGlobalNames.FORM_VALIDATION_MARK_FIELD);
		}

		if (!StringUtils.isEmpty(f.getGratilmax()) && !FormUtils.isBigDecimal(f.getGratilmax())) {
			errors.rejectValue(GENERR, DefaultGlobalNames.FORM_VALIDATION_NUMERIC_BIGDECIMAL_FORMAT_FIELD);
			errors.rejectValue(GRATILMAX, DefaultGlobalNames.FORM_VALIDATION_MARK_FIELD);
		}

		if (!StringUtils.isEmpty(f.getGratilmin()) && !FormUtils.isBigDecimal(f.getGratilmin())) {
			errors.rejectValue(GENERR, DefaultGlobalNames.FORM_VALIDATION_NUMERIC_BIGDECIMAL_FORMAT_FIELD);
			errors.rejectValue(GRATILMIN, DefaultGlobalNames.FORM_VALIDATION_MARK_FIELD);
		}

		if (!StringUtils.isEmpty(f.getPujamenmax()) && !FormUtils.isBigDecimal(f.getPujamenmax())) {
			errors.rejectValue(GENERR, DefaultGlobalNames.FORM_VALIDATION_NUMERIC_BIGDECIMAL_FORMAT_FIELD);
			errors.rejectValue(PUJAMENMAX, DefaultGlobalNames.FORM_VALIDATION_MARK_FIELD);
		}

		if (!StringUtils.isEmpty(f.getPujamenmin()) && !FormUtils.isBigDecimal(f.getPujamenmin())) {
			errors.rejectValue(GENERR, DefaultGlobalNames.FORM_VALIDATION_NUMERIC_BIGDECIMAL_FORMAT_FIELD);
			errors.rejectValue(PUJAMENMIN, DefaultGlobalNames.FORM_VALIDATION_MARK_FIELD);
		}
	}
}
