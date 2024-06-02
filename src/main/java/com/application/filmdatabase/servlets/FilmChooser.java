package com.application.filmdatabase.servlets;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import com.application.filmdatabase.repos.GenresRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "FilmChooser", value = "/chooser")
public class FilmChooser extends HttpServlet{
    private static final long serialVersionUID = 1L;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<String> genres = GenresRepo.getGenres();
        request.setAttribute("genres", genres);
        request.getRequestDispatcher("/WEB-INF/FilmChooser.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String title = request.getParameter("title");
        String director = request.getParameter("director");
        int minDuration = Integer.parseInt(request.getParameter("minDuration"));
        int maxDuration = Integer.parseInt(request.getParameter("maxDuration"));
        int rating = Integer.parseInt(request.getParameter("minRating"));
        String[] genres = request.getParameterValues("genres");
        StringBuilder redirectURL = new StringBuilder("/films?");
        if (minDuration > maxDuration) {
            request.setAttribute("errorMessage", "You entered invalid duration range. Please try again.");
            request.getRequestDispatcher("/WEB-INF/FilmChooser.jsp").forward(request, response);
        }
        if (minDuration > 30)
            redirectURL.append("minDuration=").append(minDuration);
        if (maxDuration < 330)
            redirectURL.append("&maxDuration=").append(maxDuration);
        if (title != null && !title.isEmpty()) {
            redirectURL.append("&title=").append(title);
        }
        if(director != null && !director.isEmpty()) {
            redirectURL.append("&director=").append(director);
        }
        if (director != null && !director.isEmpty()) {
            redirectURL.append("&director=").append(director);
        }
        if (rating > 0) {
            redirectURL.append("&rating=").append(rating);
        }
        if(genres != null) {
            for (String genre : genres) {
                redirectURL.append("&genre=").append(genre);
            }
        }
        System.out.println(redirectURL);
        response.sendRedirect(redirectURL.toString());
    }
}
