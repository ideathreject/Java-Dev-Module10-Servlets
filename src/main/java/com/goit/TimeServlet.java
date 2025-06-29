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
        resp.getWriter().write("<h1>Current time on ${timezone} </h1>".replace("${timezone}", parseTimeZone(req)));
        String time = ZonedDateTime
                .now(ZoneOffset.ofHours(parseTime(req)))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss '${timezone}'")).replace("${timezone}", parseTimeZone(req));
        resp.getWriter().write(time);

    }

    private String parseTimeZone(HttpServletRequest request) {
        if (request.getParameterMap().containsKey("timezone")) {
            return request.getParameter("timezone");

        } else return "UTC";
    }

    private int parseTime(HttpServletRequest request) {
        if (request.getParameterMap().containsKey("timezone")) {
            return Integer.parseInt(request.getParameter("timezone").replace("UTC", ""));

        } else return 0;
    }
}
