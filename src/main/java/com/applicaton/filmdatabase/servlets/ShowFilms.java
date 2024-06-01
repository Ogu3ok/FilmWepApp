package com.applicaton.filmdatabase.servlets;


import com.applicaton.filmdatabase.models.Film;
import com.applicaton.filmdatabase.repos.FilmsRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet (name = "ShowFilms", value = "/films")
public class ShowFilms extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getParameterMap();
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        String title = req.getParameter("title");
        int minDuration = Integer.parseInt(req.getParameter("minDuration"));
        int maxDuration = Integer.parseInt(req.getParameter("maxDuration"));
        String ratingStr = req.getParameter("rating");

        int rating = ratingStr == null || ratingStr.isEmpty() ? 0 : Integer.parseInt(ratingStr);

        List<Film> movies = FilmsRepo.getFilms(title,  minDuration, maxDuration, rating);
        writer.println("<html><body>");
        writer.println("<h1>Films</h1>");
        writer.println("<table border='1'>");
        writer.println("<tr><th>Title</th><th>Year of release</th><th>Duration</th><th>Rating</th></tr>");
        for (Film movie : movies) {
            writer.println("<tr>");
            writer.println("<td>" + movie.getTitle() + "</td>");
            writer.println("<td>" + movie.getYear() + "</td>");
            writer.println("<td>" + movie.getRuntime() + "</td>");
            writer.println("<td>" + movie.getImdbRating() + "</td>");
            writer.println("</tr>");
        }
        writer.println("</table>");
        writer.println("<a href='/chooser'>Select films</a>");
        writer.println("</body></html>");
    }
}
