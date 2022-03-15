package com.capitanperegrina.common.utils;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtils {

	private JSONUtils() {}
	
	public static JSONObject creaPar( String nombre, String valor ) throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put( "nom", nombre );
		obj.put( "val", valor );
		return obj;
	}
}
