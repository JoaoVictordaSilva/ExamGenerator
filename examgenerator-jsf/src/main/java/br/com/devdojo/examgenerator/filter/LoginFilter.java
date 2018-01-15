package br.com.devdojo.examgenerator.filter;

import br.com.devdojo.examgenerator.util.TokenUtil;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@WebFilter(urlPatterns = {"/*"}, description = "Session checker filter")
public class LoginFilter implements Filter, Serializable {

    @Inject
    private TokenUtil mTokenUtil;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if(!request.getRequestURI().endsWith("login.xhtml") && !isTokenValid(request)){
            response.sendRedirect(request.getContextPath() + "/login.xhtml");
            return;
        }
        filterChain.doFilter(request, response);

    }

    private boolean isTokenValid(HttpServletRequest request){
        return mTokenUtil.isExpirationTimeFromCookieValid(request) && !mTokenUtil.getTokenFromCookies(request).isEmpty();
    }

    @Override
    public void destroy() {

    }
}
