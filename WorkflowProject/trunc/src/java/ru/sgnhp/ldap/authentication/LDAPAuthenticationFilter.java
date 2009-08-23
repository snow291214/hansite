package ru.sgnhp.ldap.authentication;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import ru.sgnhp.domain.WorkflowUser;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class LDAPAuthenticationFilter implements Filter {
    private String authenticationPage;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.authenticationPage = filterConfig.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        WorkflowUser workflowUser = (WorkflowUser) session.getAttribute("initiator");
        if (workflowUser != null) {
            chain.doFilter(request, response);
        } else {
            //chain.doFilter(request, response);
            RequestDispatcher rd = request.getRequestDispatcher(authenticationPage);
            rd.forward(request, response);
        }
    }

    public void destroy() {
        
    }
}
