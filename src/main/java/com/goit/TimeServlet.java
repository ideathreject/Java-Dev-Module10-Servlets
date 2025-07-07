package com.goit;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");

        String tzParam = req.getParameter("timezone");
        ZoneId zone;


        zone = (tzParam == null || tzParam.isBlank())
                ? ZoneOffset.UTC
                : ZoneId.of(tzParam);
        String zoneLabel = (tzParam == null || tzParam.isBlank()) ? "UTC" : zone.getId();

        String time = ZonedDateTime.now(zone)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " " + zoneLabel;

        resp.getWriter().write("<h1>Current time on " + zoneLabel + "</h1>");
        resp.getWriter().write(time);
    }
}
