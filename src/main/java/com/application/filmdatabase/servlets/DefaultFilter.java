package com.application.filmdatabase.servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "DefaultFilter", urlPatterns = "/*")
public class DefaultFilter implements Filter{
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String requestURI = request.getRequestURI();

        if (requestURI.equals("/")) {
            response.sendRedirect("/chooser");
        }  else {
            chain.doFilter(req, res);
        }
    }
}
