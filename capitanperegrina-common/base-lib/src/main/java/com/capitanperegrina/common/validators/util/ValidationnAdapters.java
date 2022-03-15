package com.capitanperegrina.common.validators.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.capitanperegrina.common.exceptions.ServicioErrorException;

/**
 * The Class ValidationnAdapters.
 */
@Component
public class ValidationnAdapters {

	/** The log. */
	private static Logger log = Logger.getLogger(ValidationnAdapters.class);

	/** The message source. */
	@Autowired
	MessageSource messageSource;

	/**
	 * Adapt 2 map.
	 *
	 * @param bindingResult the binding result
	 * @return the map
	 */
	public Map<String, String> adapt2Map(final BindingResult bindingResult) {
		final Map<String, String> fieldErrors = new HashMap<String, String>();
		for (final FieldError error : bindingResult.getFieldErrors()) {
			fieldErrors.put(error.getField(), this.messageSource.getMessage(error, LocaleContextHolder.getLocale()));
		}
		return fieldErrors;
	}

	/**
	 * Adapt 2 json.
	 *
	 * @param bindingResult the binding result
	 * @return the JSON array
	 */
	public JSONArray adapt2Json(final BindingResult bindingResult) {
		try {
			final JSONArray arr = new JSONArray();
			for (final FieldError error : bindingResult.getFieldErrors()) {
				final JSONObject obj = new JSONObject();
				obj.put(error.getField(), StringEscapeUtils.escapeHtml(this.messageSource.getMessage(error, LocaleContextHolder.getLocale())));
				arr.put(obj);
			}
			return arr;
		} catch (final JSONException e) {
			log.error("", e);
			throw new ServicioErrorException(e);
		}
	}
}