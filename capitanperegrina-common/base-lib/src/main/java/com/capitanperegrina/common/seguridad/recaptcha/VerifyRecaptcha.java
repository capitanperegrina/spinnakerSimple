package com.capitanperegrina.common.seguridad.recaptcha;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;
import org.json.JSONObject;

public class VerifyRecaptcha {
	private static final Logger logger = Logger.getLogger(VerifyRecaptcha.class);
	public static final String url = "https://www.google.com/recaptcha/api/siteverify";
	private final static String USER_AGENT = "Mozilla/5.0";

	public static boolean verify(final String secretKey, final String gRecaptchaResponse) {
		if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
			return false;
		}
		try {
			final URL obj = new URL(url);
			final HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

			// add request header
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

			final String postParams = "secret=" + secretKey + "&response=" + gRecaptchaResponse;

			// Send post request
			con.setDoOutput(true);
			final DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();

			final int responseCode = con.getResponseCode();
			final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			final StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			final JSONObject jsonObject = new JSONObject(response.toString());
			return jsonObject.getBoolean("success");
		} catch (final Exception e) {
			logger.info(e);
			return false;
		}
	}
}