package com.application.filmdatabase.servlets;


import com.application.filmdatabase.models.Film;
import com.application.filmdatabase.repos.FilmsRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ShowFilms", value = "/films")
public class ShowFilms extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String[]> params = new HashMap<>(req.getParameterMap());
        int page = 1;
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page")[0]);
            params.remove("page");
        }
        StringBuilder url = new StringBuilder(req.getRequestURI() + "?");
        if (!params.isEmpty()) {
            for (String key : params.keySet()) {
                url.append("&").append(key).append("=").append(params.get(key)[0]);
            }
        }
//        List<Film> films = FilmsRepo.getFilms(params, page - 1);
//        req.setAttribute("films", films);
//        req.setAttribute("url", url);
//        req.setAttribute("page", page);
//        req.getRequestDispatcher("/WEB-INF/Films.jsp").forward(req, resp);

        List<Film> films = FilmsRepo.getFilms(params, page - 1);
        System.out.println("Films got: "+films.size());
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<html><head><title>Film Database</title></head><style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "        }\n" +
                "        table {\n" +
                "            width: 100%;\n" +
                "            border-collapse: collapse;\n" +
                "        }\n" +
                "        td {\n" +
                "            width: 33%;\n" +
                "            padding: 10px;\n" +
                "            box-sizing: border-box;\n" +
                "            border: 1px solid #ccc;\n" +
                "        }\n" +
                "        .pagination {\n" +
                "            display: flex;\n" +
                "            justify-content: space-between;\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "        .pagination a {\n" +
                "            background-color: #0000FF;\n" + // Change button color to blue
                "            color: white;\n" +
                "            padding: 10px 20px;\n" +
                "            text-decoration: none;\n" +
                "            border-radius: 5px;\n" +
                "        }\n" +
                "        .pagination a:hover {\n" +
                "            background-color: #000099;\n" + // Darken the button color when hovered over
                "        }\n" +
                "        a {\n" +
                "            color: #0000FF;\n" + // Change link color to blue
                "        }\n" +
                "        a:hover {\n" +
                "            color: #000099;\n" + // Darken the link color when hovered over
                "        }\n" +
                "        img {\n" +
                "            max-width: 100%;\n" + // Make the image responsive
                "        }\n" +
                "    </style><body>");
        writer.println("<div class='pagination'>");
        if (page > 1)
            writer.println("<a href='" + url + "&page=" + (page - 1) + "'>Previous</a>");
        writer.println("<a href=\"/chooser\">Choose Films</a>");
        writer.println("<a href='" + url + "&page=" + (page + 1) + "'>Next</a>");
        writer.println("</div>");
        writer.println("<table border='1'>");

        for (int i = 0; i < films.size(); i++) {
            Film film = films.get(i);

            // Start a new row every 3 films
            if (i % 3 == 0) {
                writer.println("<tr>");
            }

            writer.println("<td>");
            writer.println("<img src='" + film.getPoster() + "' alt='Film poster'>"); // Display the film poster
            writer.println("<h2>" + (film.getTitle() != null ? film.getTitle() : "N/A") + "</h2>");
            writer.println("<p>Year: " + (film.getYear() != null ? film.getYear() : "N/A") + "</p>");
            writer.println("<p>Duration: " + (film.getRuntime() != null ? film.getRuntime() : "N/A") + "</p>");
            writer.println("<p>Rating: " + (film.getImdbRating() != null ? film.getImdbRating() : "N/A") + "</p>");
            writer.println("</td>");

            // End the row after every 3 films or if it's the last film
            if (i % 3 == 2 || i == films.size() - 1) {
                writer.println("</tr>");
            }
        }

        writer.println("</table>");
//        if (page > 1)
//            writer.println("<a href='" + url + "&page=" + (page - 1) + "'>Previous</a>");
//
//        // Add the "Next" button
//        writer.println("<a href='" + url + "&page=" + (page + 1) + "'>Next</a>");
//        System.out.println(url);
//        writer.println("<a href='/chooser'>Select films</a>");
//        writer.println("</body></html>");

    }
}
