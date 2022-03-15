package com.capitanperegrina.common.web.filters;

import java.io.CharArrayWriter;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class CharResponseWrapper extends HttpServletResponseWrapper {

	private final CharArrayWriter output;

	@Override
	public String toString() {
		return this.output.toString();
	}

	public CharResponseWrapper(final HttpServletResponse response) {
		super(response);
		this.output = new CharArrayWriter();
	}

	@Override
	public PrintWriter getWriter() {
		return new PrintWriter(this.output);
	}
}
