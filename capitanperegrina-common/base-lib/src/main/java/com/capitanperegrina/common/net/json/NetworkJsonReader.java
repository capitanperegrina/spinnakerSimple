package com.capitanperegrina.common.net.json;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class NetworkJsonReader {

    private static String streamToString(final InputStream inputStream) {
        final Scanner scanner = new Scanner(inputStream, "UTF-8");
        try {
            return scanner.useDelimiter("\\Z").next();
        } finally {
            scanner.close();
        }
    }

    public static String jsonGetRequest(final String urlQueryString) {
        String json = null;
        try {

            final URL url = new URL(urlQueryString);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.connect();
            final InputStream inStream = connection.getInputStream();
            json = streamToString(inStream); // input stream to string

        } catch (final IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }
}
