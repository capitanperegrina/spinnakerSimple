package com.capitanperegrina.common.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.htmlcompressor.compressor.HtmlCompressor;

public class CompressResponseFilter implements Filter {

	private HtmlCompressor compressor;

	@Override
	public void doFilter(final ServletRequest req, final ServletResponse resp, final FilterChain chain) throws IOException, ServletException {

		final CharResponseWrapper responseWrapper = new CharResponseWrapper((HttpServletResponse) resp);

		chain.doFilter(req, responseWrapper);

		final String servletResponse = new String(responseWrapper.toString());
		resp.getWriter().write(this.compressor.compress(servletResponse));
	}

	@Override
	public void init(final FilterConfig config) throws ServletException {
		this.compressor = new HtmlCompressor();
		this.compressor.setCompressCss(true);
		this.compressor.setCompressJavaScript(true);
	}

	@Override
	public void destroy() {
	}

}