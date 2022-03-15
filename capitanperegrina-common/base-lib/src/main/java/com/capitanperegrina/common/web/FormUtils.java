package com.capitanperegrina.common.web;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.BigDecimalValidator;
import org.apache.commons.validator.routines.IntegerValidator;
import org.apache.log4j.Logger;
import org.springframework.context.i18n.LocaleContextHolder;

import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.exceptions.ServicioErrorException;
import com.capitanperegrina.common.i18n.Mensajes;
import com.capitanperegrina.common.utils.Cadenas;
import com.capitanperegrina.common.utils.Fecha;
import com.capitanperegrina.common.validators.Validadores;

/**
 * Clase con distintas utilidades para formularios.
 *
 * @author <a href="mailto:yo@capitanperegrina.com">Capitán Peregrina</a>
 */
public class FormUtils {

    /** La constante PATRON_DDMYYY_HHMMSS. */
    private static final String PATRON_DDMYYY_HHMMSS = "dd/MM/yyyy hh:mm:ss";

    /** La constante log. */
    private static final Logger log = Logger.getLogger(Cadenas.class);

    /** El campo integer validator. */
    private static IntegerValidator integerValidator = IntegerValidator.getInstance();

    /** El campo big decimal validator. */
    private static BigDecimalValidator bigDecimalValidator = BigDecimalValidator.getInstance();

    private static Locale localePersonalizado(Locale locale) {
        if ("CA".equals(locale.getLanguage().toUpperCase())) {
            locale = new Locale("es");
        }
        return locale;
    }

    private static Locale localePersonalizado() {
        Locale locale = LocaleContextHolder.getLocale();
        if ("CA".equals(locale.getLanguage().toUpperCase())) {
            locale = new Locale("es");
        }
        return locale;
    }

    /**
     * To string form.
     *
     * @param valor the valor
     * @return the string
     */
    public static String toStringForm(final Object valor) {
        return toStringForm(valor, localePersonalizado());
    }

    public static String toStringForm(final Object valor, final String lang) {
        return toStringForm(valor, new Locale(lang));
    }

    public static String toStringForm(final Object valor, final Locale locale) {
        try {
            if (valor != null) {
                if (valor instanceof Calendar) {
                    final DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, localePersonalizado(locale));
                    return df.format(((Calendar) valor).getTime());
                } else if (valor instanceof Date) {
                    final DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, localePersonalizado(locale));
                    return df.format(((Date) valor));
                } else if (valor instanceof BigDecimal) {
                    final BigDecimal n = (BigDecimal) valor;
                    return NumberFormat.getNumberInstance(localePersonalizado(locale)).format(n);
                } else if (valor instanceof Integer) {
                    return ((Integer) valor).toString();
                } else {
                    return valor.toString();
                }
            } else {
                return "";
            }
        } catch (final Exception e) {
            log.error("", e);
            throw new ServicioErrorException(e);
        }
    }

    /**
     * Parses the calendar form field.
     *
     * @param str the str
     * @return the calendar
     */
    public static Calendar parseCalendarFormField(final String str) {
        if (!Validadores.estaVacia(str)) {
            try {
                final SimpleDateFormat formatter = new SimpleDateFormat(PATRON_DDMYYY_HHMMSS);
                return Fecha.date2Calendar(formatter.parse(Cadenas.trimNoNulo(str)));
            } catch (final ParseException e) {
                log.error(e);
                throw new ServicioErrorException(e);
            }
        }
        return null;
    }

    /**
     * Parses the calendar form field.
     *
     * @param str the str
     * @return the calendar
     */
    public static Calendar parseCalendarFormField(final String str, final Locale locale) {
        if (!Validadores.estaVacia(str)) {
            try {
                final DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, localePersonalizado());
                return Fecha.date2Calendar(df.parse(Cadenas.trimNoNulo(str)));
            } catch (final ParseException e) {
                log.error(e);
                throw new ServicioErrorException(e);
            }
        }
        return null;
    }

    /**
     * Parses the integer form field.
     *
     * @param str          the str
     * @param defaultValue the default value
     * @return the integer
     */
    public static Integer parseIntegerFormField(final String str, final Integer defaultValue) {
        Integer ret = integerValidator.validate(str);
        if (ret == null) {
            ret = defaultValue;
        }
        return ret;
    }

    /**
     * Parses the integer form field.
     *
     * @param str the str
     * @return the integer
     */
    public static Integer parseIntegerFormField(final String str) {
        return parseIntegerFormField(str, null);
    }

    /**
     * Parses the big decimal form field.
     *
     * @param str          the str
     * @param defaultValue the default value
     * @return the big decimal
     */
    public static BigDecimal parseBigDecimalFormField(final String str, final BigDecimal defaultValue) {
        return parseBigDecimalFormField(str, defaultValue, localePersonalizado());
    }

    /**
     * Parses the big decimal form field.
     *
     * @param str          the str
     * @param defaultValue the default value
     * @param locale       the locale
     * @return the big decimal
     */
    public static BigDecimal parseBigDecimalFormField(final String str, final BigDecimal defaultValue, final Locale locale) {
        BigDecimal ret = bigDecimalValidator.validate(str, locale);
        if (ret == null) {
            ret = defaultValue;
        }
        return ret;
    }

    /**
     * Parses the big decimal form field.
     *
     * @param str    the str
     * @param locale the locale
     * @return the big decimal
     */
    public static BigDecimal parseBigDecimalFormField(final String str, final Locale locale) {
        return parseBigDecimalFormField(str, null, locale);
    }

    /**
     * Parses the big decimal form field.
     *
     * @param str the str
     * @return the big decimal
     */
    public static BigDecimal parseBigDecimalFormField(final String str) {
        return parseBigDecimalFormField(str, null, null);
    }

    /**
     * Parses the string form field.
     *
     * @param str the str
     * @return the string
     */
    public static String parseStringFormField(final String str) {
        return StringUtils.trimToEmpty(str).toUpperCase();
    }

    public static boolean isBigDecimal(final String str) {
        return isBigDecimal(str, localePersonalizado());
    }

    public static boolean isNatural(final String str) {
        return StringUtils.isNumeric(str);
    }

    public static boolean isBigDecimal(final String str, final Locale locale) {
        final String regExBigDecimal = Mensajes.getStringSafe(DefaultGlobalNames.REGEXP_BIGDECIMAL, locale);
        return str.matches(regExBigDecimal);
    }

//    public static void main(final String[] args) {
//        System.out.println("es 1.234.567,89 : " + "1.234.567,89".matches("(((\\d{1,3})([.]\\d{3})*)|(\\d+))([,]\\d+)?"));
//        System.out.println("en 1,234,567.89 : " + "1,234,567.89".matches("(((\\d{1,3})([,]\\d{3})*)|(\\d+))([.]\\d+)?"));
//        System.out.println("pt 1.234.567,89 : " + "1.234.567,89".matches("(((\\d{1,3})([.]\\d{3})*)|(\\d+))([,]\\d+)?"));
//        System.out.println("fr 1 234 567,89 : " + "1 234567,89".matches("(((\\d{1,3})([ ]\\d{3})*)|(\\d+))([,]\\d+)?"));
//        System.out.println("de 1.234.567,89 : " + "1.234.567,89".matches("(((\\d{1,3})([.]\\d{3})*)|(\\d+))([,]\\d+)?"));
//        System.out.println("it 1.234.567,89 : " + "1.234.567,89".matches("(((\\d{1,3})([.]\\d{3})*)|(\\d+))([,]\\d+)?"));
//
//        final Calendar c = Calendar.getInstance();
//        c.set(2019, 2, 15, 0, 0, 0);
//        System.out.println(toStringForm(c, new Locale("es")));
//        System.out.println(toStringForm(c, "es"));
//        System.out.println(toStringForm(c.getTime(), new Locale("es")));
//        System.out.println(toStringForm(c.getTime(), "es"));
//
//        System.out.println(toStringForm(c, new Locale("en")));
//        System.out.println(toStringForm(c.getTime(), new Locale("en")));
//        System.out.println(toStringForm(c, "en"));
//        System.out.println(toStringForm(c.getTime(), "en"));
//    }
}
