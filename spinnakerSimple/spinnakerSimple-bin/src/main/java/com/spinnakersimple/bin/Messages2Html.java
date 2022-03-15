package com.spinnakersimple.bin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringEscapeUtils;

import com.capitanperegrina.common.utils.files.Ficheros;

public class Messages2Html {

    public static final StringBuilder generaCabecera() {
        return new StringBuilder("<html><head></head><body><table>\n");
    }

    public static final String generaCierre() {
        return "<table></body></html>";
    }

    public static void main(final String[] args) {
        try {

            final List<String> langs = Arrays.asList("es", "de", "fr", "it", "en", "pt");
            final Map<String, ResourceBundle> r = new HashMap<String, ResourceBundle>();
            final Map<String, StringBuilder> t = new HashMap<String, StringBuilder>();

            for (final String lang : langs) {
                r.put(lang, ResourceBundle.getBundle("Mensajes", new Locale(lang)));
            }

            final Enumeration<String> keysEnum = r.get("es").getKeys();
            final List<String> keys = new ArrayList<String>();
            while (keysEnum.hasMoreElements()) {
                keys.add(keysEnum.nextElement());
            }
            Collections.sort(keys);

            for (final String lang : langs) {
                t.put(lang, generaCabecera());
                for (final String key : keys) {
                    t.put(lang,
                            t.get(lang).append("<tr><td>").append(key).append("</td><td>")
                                    .append(StringEscapeUtils.escapeHtml(r.get("es").getString(key))).append("</td><td>")
                                    .append(StringEscapeUtils.escapeHtml(r.get(lang).getString(key))).append("</td></tr>\n"));
                }
                t.put(lang, t.get(lang).append(generaCierre()));
                Ficheros.string2File("C:/churreraOut/" + lang + ".html", t.get(lang).toString());
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
