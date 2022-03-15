package com.capitanperegrina.common.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class HSTSFilter implements Filter {

    @Override
	public void doFilter(final ServletRequest req, final ServletResponse res,
        final FilterChain chain) throws IOException, ServletException {
        final HttpServletResponse resp = (HttpServletResponse) res;

        if (req.isSecure())
            resp.setHeader("Strict-Transport-Security", "max-age=31622400; includeSubDomains");

        chain.doFilter(req, resp);
    }

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}
}