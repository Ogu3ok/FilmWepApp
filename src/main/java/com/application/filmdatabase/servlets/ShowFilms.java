package com.application.filmdatabase.servlets;


import com.application.filmdatabase.models.Actor;
import com.application.filmdatabase.models.Film;
import com.application.filmdatabase.models.Genre;
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
import java.util.stream.Collectors;

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

        List<Film> films = FilmsRepo.getFilms(params, page - 1);
        System.out.println("Films got: " + films.size());
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<html><head><title>Film Database</title><style>\n" +
                "<link href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap\" rel=\"stylesheet\">\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: 'Roboto', sans-serif;\n" +
                "            margin: 20px;\n" +
                "        }\n" +
                "\n" +
                "        table {\n" +
                "            width: 100%;\n" +
                "            border-collapse: separate;\n" +
                "            border-spacing: 15px; /* Add space between cells */\n" +
                "        }\n" +
                "\n" +
                "        td {\n" +
                "            border: 1px solid #ddd;\n" +
                "            padding: 10px;\n" +
                "            vertical-align: top;\n" +
                "            text-align: center;\n" +
                "            border-radius: 10px; /* Rounded corners */\n" +
                "            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* Add shadow for better visibility */\n" +
                "        }\n" +
                "\n" +
                "        .navigation-buttons {\n" +
                "            text-align: center;\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "\n" +
                "        .navigation-buttons button {\n" +
                "            padding: 10px 20px;\n" +
                "            background-color: #007BFF;\n" +
                "            color: white;\n" +
                "            border: none;\n" +
                "            border-radius: 5px;\n" +
                "            cursor: pointer;\n" +
                "            margin: 0 5px;\n" +
                "        }\n" +
                "\n" +
                "        .navigation-buttons button:hover {\n" +
                "            background-color: #0056b3;\n" +
                "        }\n" +
                "\n" +
                "        .film-cell {\n" +
                "            display: flex;\n" +
                "            flex-direction: column;\n" +
                "            align-items: center;\n" +
                "            background-color: #fff; /* Add background color */\n" +
                "            padding: 20px;\n" +
                "        }\n" +
                "\n" +
                "        .main-info {\n" +
                "            display: flex;\n" +
                "            align-items: flex-start;\n" +
                "            margin-bottom: 10px;\n" +
                "        }\n" +
                "\n" +
                "        .poster {\n" +
                "            margin-right: 20px;\n" +
                "        }\n" +
                "\n" +
                "        .poster img {\n" +
                "            width: 150px; /* Increase the width of the poster */\n" +
                "            height: auto;\n" +
                "            border-radius: 10px; /* Rounded corners for the poster */\n" +
                "        }\n" +
                "\n" +
                "        .film-details {\n" +
                "            text-align: left;\n" +
                "        }\n" +
                "\n" +
                "        .additional-info {\n" +
                "            text-align: center;\n" +
                "            margin-bottom: 10px;\n" +
                "        }\n" +
                "\n" +
                "        .film-link {\n" +
                "            margin-top: 10px;\n" +
                "        }\n" +
                "\n" +
                "        .button {\n" +
                "            display: inline-block;\n" +
                "            padding: 10px 20px;\n" +
                "            background-color: #007BFF;\n" +
                "            color: white;\n" +
                "            text-decoration: none;\n" +
                "            border-radius: 5px;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .button:hover {\n" +
                "            background-color: #0056b3;\n" +
                "        }\n" +
                "    </style></head><body>");
        writer.println("<div class='navigation-buttons'>");
        if (page > 1)
            writer.println("<a href='" + url + "&page=" + (page - 1) + "' class=\"button\">Previous</a>");
        writer.println("<a href=\"/chooser\" class=\"button\">Choose Films</a>");
        writer.println("<a href='" + url + "&page=" + (page + 1) + "' class=\"button\">Next</a>");
        writer.println("</div>");
        writer.println("<table border='1'>");

        for (int i = 0; i < films.size(); i++) {
            Film film = films.get(i);

            // Start a new row every 3 films
            if (i % 3 == 0) {
                writer.println("<tr>");
            }

            writer.println("<td>");
            writer.println("<div class='film-cell'>");
            writer.println("    <div class=\"main-info\">");
            writer.println("        <div class=\"poster\">\n" +
                    "               <img src='" + film.getPoster() + "' alt=\"Film Poster\">\n" +
                    "               </div>");
            writer.println("        <div class=\"film-details\">");
            writer.println("            <h3>" + (film.getTitle() != null ? film.getTitle() : "N/A") + "</h3>");
            writer.println("            <p><strong>Director: </strong>" + (film.getDirectors().isEmpty() ? "N/A" : film.getDirectors().get(0).getName()) + "</p>");
            writer.println("            <p><strong>Year: </strong>" + (film.getYear() != null ? film.getYear() : "N/A") + "</p>");
            writer.println("            <p>" + (film.getRuntime() != null ? film.getRuntime() + " minutes" : "N/A") + "</p>");
            writer.println("            <p>" + (film.getImdbRating() != null ? film.getImdbRating() + " ★" : "N/A") + "</p>");
            writer.println("        </div>");
            writer.println("    </div>");
            writer.println("    <div class=\"additional-info\">");
            writer.println("        <p>" + (film.getGenres().isEmpty() ? "N/A" : film.getGenres().stream().map(Genre::getName).collect(Collectors.joining(", "))) + "</p>");
            writer.println("        <p>" + (film.getActors().isEmpty() ? "N/A" : film.getActors().stream().map(Actor::getName).collect(Collectors.joining(", "))) + "</p>");
            writer.println("    </div>");
            writer.println("    <div class='film-link'>");
            writer.println("        <a href='https://www.imdb.com/title/"+film.getImdbID()+"' class=\"button\">Open IMDB page</a>");
            writer.println("    </div>");
            writer.println("</div>");
            writer.println("</td>");

//            writer.println("<td>");
//            writer.println("<img src='" + film.getPoster() + "' alt='Film poster'>"); // Display the film poster
//            writer.println("<h2>" + (film.getTitle() != null ? film.getTitle() : "N/A") + "</h2>");
//            writer.println("<p>Year: " + (film.getYear() != null ? film.getYear() : "N/A") + "</p>");
//            writer.println("<p>Duration: " + (film.getRuntime() != null ? film.getRuntime() : "N/A") + "</p>");
//            writer.println("<p>Rating: " + (film.getImdbRating() != null ? film.getImdbRating() : "N/A") + "</p>");
//            writer.println("</td>");

            // End the row after every 3 films or if it's the last film
            if (i % 3 == 2 || i == films.size() - 1) {
                writer.println("</tr>");
            }
        }

        writer.println("</table>");
        writer.println("</body></html>");
    }
}
