package com.applicaton.filmdatabase.servlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet (name = "ShowFilms", value = "/films")
public class ShowFilms extends HttpServlet {
    String title;
    String scenarist;
    int duration;
    String rating;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        title = req.getParameter("title");
        scenarist = req.getParameter("scenarist");
        duration = Integer.parseInt(req.getParameter("duration"));
        rating = req.getParameter("rating");

        resp.sendRedirect("/films");
    }
}
