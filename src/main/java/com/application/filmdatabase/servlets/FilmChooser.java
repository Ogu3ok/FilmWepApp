package com.application.filmdatabase.servlets;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "FilmChooser", value = "/chooser")
public class FilmChooser extends HttpServlet{
    private static final long serialVersionUID = 1L;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/FilmChooser.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String title = request.getParameter("title");
        String director = request.getParameter("director");
        int minDuration = Integer.parseInt(request.getParameter("minDuration"));
        int maxDuration = Integer.parseInt(request.getParameter("maxDuration"));
        int rating = Integer.parseInt(request.getParameter("minRating"));
        String redirectURL = "/films?";
        if (minDuration > maxDuration) {
            request.setAttribute("errorMessage", "You entered invalid duration range. Please try again.");
            request.getRequestDispatcher("/WEB-INF/FilmChooser.jsp").forward(request, response);
        }
        if (minDuration > 30)
            redirectURL += "minDuration=" + minDuration;
        if (maxDuration < 330)
            redirectURL += "&maxDuration=" + maxDuration;
        if (title != null && !title.isEmpty()) {
            redirectURL += "&title=" + title;
        }
        if(director != null && !director.isEmpty()) {
            redirectURL += "&director=" + director;
        }
        if (director != null && !director.isEmpty()) {
            redirectURL += "&director=" + director;
        }
        if (rating > 0) {
            redirectURL += "&rating=" + rating;
        }
        System.out.println(redirectURL);
        response.sendRedirect(redirectURL);
    }
}
