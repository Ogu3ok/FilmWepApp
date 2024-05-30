package com.applicaton.filmdatabase.servlets;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DefaultServlet", value = "/")
public class DefaultServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestURI = request.getRequestURI();
        if (requestURI.equals("/")) {
            response.sendRedirect("/chooser");
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}