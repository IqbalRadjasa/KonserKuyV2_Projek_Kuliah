package com.example;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class SessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);  // Do not create a new session

        if (session == null || session.getAttribute("user") == null) {
            // Redirect to login page if the session is missing or the user is not logged in
            response.sendRedirect(request.getContextPath() + "/index.xhtml");
        } else {
            chain.doFilter(req, res);  // Proceed to the requested page
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code (optional)
    }

    @Override
    public void destroy() {
        // Cleanup code (optional)
    }
}
