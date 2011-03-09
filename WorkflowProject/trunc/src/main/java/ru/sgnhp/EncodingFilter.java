package ru.sgnhp;

/**
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 */
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {

    private String encoding;
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig fc) throws ServletException {
        this.filterConfig = fc;
        this.encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
            FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding(encoding);
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }
}
