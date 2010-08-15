/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skytelecom.utils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 */
public class EncodingFilter implements Filter {

    private String encoding;
    private FilterConfig filterConfig;

    public void init(FilterConfig fc) throws ServletException {
        this.filterConfig = fc;
        this.encoding = filterConfig.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest req, ServletResponse resp,
            FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding(encoding);
        chain.doFilter(req, resp);
    }

    public void destroy() {
    }
}
