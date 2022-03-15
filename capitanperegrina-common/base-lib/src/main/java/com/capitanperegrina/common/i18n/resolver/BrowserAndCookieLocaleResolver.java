package com.capitanperegrina.common.i18n.resolver;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.i18n.CookieLocaleResolver;

/**
 * The Class BrowserAndCookieLocaleResolver.
 * org.springframework.web.servlet.i18n.CookieLocaleResolver
 */
public class BrowserAndCookieLocaleResolver extends CookieLocaleResolver {

    /** The Constant idiomasAceptados. */
    private static final List<String> idiomasAceptados = Arrays.asList(new String[] { "es", "en", "it", "gl", "ca" });

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.web.servlet.i18n.CookieLocaleResolver#
     * determineDefaultLocale(javax.servlet.http.HttpServletRequest)
     */
    @Override
    protected Locale determineDefaultLocale(final HttpServletRequest request) {

        Locale browserLocale = null;
        final Enumeration<Locale> locales = request.getLocales();
        while (locales.hasMoreElements() && browserLocale == null) {
            final Locale locale = locales.nextElement();
            if (idiomasAceptados.contains(locale.getLanguage())) {
                browserLocale = locale;
            }
        }
        final Locale cookieLocale = super.determineDefaultLocale(request);
        final Locale sessionLocale = (Locale) request.getSession().getAttribute("capitanperegrina_locale");

        Locale locale = null;
        if (sessionLocale == null) {
            if (browserLocale == null) {
                locale = cookieLocale;
            } else {
                if (cookieLocale.getLanguage().equals(super.getDefaultLocale().getLanguage())) {
                    locale = browserLocale;
                } else {
                    locale = cookieLocale;
                }
            }
        } else {
            locale = sessionLocale;
        }

        if (!idiomasAceptados.contains(locale.getLanguage())) {
            locale = new Locale("en");
        }

        return locale;
    }
}