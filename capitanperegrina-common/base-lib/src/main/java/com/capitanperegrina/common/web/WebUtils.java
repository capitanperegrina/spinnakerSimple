package com.capitanperegrina.common.web;

import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;

import com.capitanperegrina.common.utils.Cadenas;

public class WebUtils {

	public static String paramsToString( final HttpServletRequest req) {
		String ret = "";
		final StringBuilder sb = new StringBuilder();
		if ( MapUtils.isNotEmpty( req.getParameterMap() ) ) {
			for ( final Entry<String,String[]> entry : req.getParameterMap().entrySet() ) {
				sb.append( entry.getKey() ).append( " : " ).append( Cadenas.toStringGenerico( entry.getValue() ) ).append("|");
			}
			ret = sb.toString();
			ret = ret.substring(0, ret.lastIndexOf("|"));
		}
		return ret;
	}
}
