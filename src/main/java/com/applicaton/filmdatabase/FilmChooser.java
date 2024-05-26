package com.applicaton.filmdatabase;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "FilmChooser", value = "/films")
public class FilmChooser extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Set the response content type to HTML
        response.setContentType("text/html");

        // Get the server's context path
        String contextPath = request.getServletContext().getRealPath("");

        // Create a file object for the HTML file
        File htmlFile = new File(contextPath + "/FilmChooser.jsp");

        // Read the HTML file and write it to the response
        try (FileReader reader = new FileReader(htmlFile);
             BufferedReader br = new BufferedReader(reader);
             PrintWriter out = response.getWriter()) {

            String line;
            while ((line = br.readLine()) != null) {
                out.println(line);
            }
        }
    }
    public void processRequest  (HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        String scenarist = request.getParameter("scenarist");
        int duration = Integer.parseInt(request.getParameter("duration"));
        String rating = request.getParameter("rating");

        PrintWriter out = response.getWriter();
        out.println("<h1>Film Information</h1>");
        out.println("<p>Title: " + title + "</p>");
        out.println("<p>Scenarist: " + scenarist + "</p>");
        out.println("<p>Min Duration: " + duration + " minutes</p>");
        out.println("<p>Rating: " + rating + "</p>");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }
}
