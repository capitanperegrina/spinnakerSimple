package com.capitanperegrina.common.web.seguridad.xss;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XSSRequestWrapper extends HttpServletRequestWrapper 
{
    private static Pattern[] patterns = new Pattern[] {
    		Pattern.compile( "<script>(.*?)</script>", Pattern.CASE_INSENSITIVE ),
    		Pattern.compile( "src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ), 
    		Pattern.compile( "src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "</script>", Pattern.CASE_INSENSITIVE ), Pattern.compile( "<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "javascript:", Pattern.CASE_INSENSITIVE ),
    		Pattern.compile( "vbscript:", Pattern.CASE_INSENSITIVE ),
            Pattern.compile( "onabort(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "onblur(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "onchange(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "onclick(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "ondblclick(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "ondragdrop(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "onerror(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "onfocus(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "onkeydown(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "onkeypress(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "onkeyup(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "onmousedown(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "onmousemove(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "onmouseout(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "onmouseover(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "onmouseup(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "onmove(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "onreset(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "onresize(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "onselect(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "onsubmit(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "onunload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "base64(.*?)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "prompt(.*?)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "data:_(.*?)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "onmouseover(.*?)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL ),
    		Pattern.compile( "\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL )
    };

    public XSSRequestWrapper(HttpServletRequest servletRequest) 
    {
        super(servletRequest);
    }

    @Override
    public String[] getParameterValues(String parameter) 
    {
        String[] values = super.getParameterValues(parameter);

        if (values == null) 
        {
            return null;
        }

        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) 
        {
            encodedValues[i] = stripXSS(values[i]);
        }

        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) 
    {
        String value = super.getParameter(parameter);

        return stripXSS(value);
    }

    @Override
    public String getHeader(String name) 
    {
        String value = super.getHeader(name);
        return stripXSS(value);
    }

    private String stripXSS(String value) 
    {
        if (value != null) 
        {
            // NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
            // avoid encoded attacks.
            // value = ESAPI.encoder().canonicalize(value);

            // Avoid null characters
            value = value.replaceAll("\0", "");

            // Remove all sections that match a pattern
            for (Pattern scriptPattern : patterns)
            {
                value = scriptPattern.matcher(value).replaceAll("");
            }
        }
        return value;
    }
}
