package com.goit;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.ZoneId;

@WebFilter("/time")
public class TimezoneValidateFilter extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        String timezone = req.getParameter("timezone");

        if (timezone == null || timezone.isBlank()) {
            chain.doFilter(req, resp);
            return;
        }

        try {
            timezone = timezone.replace("UTC", "");
            if (Integer.parseInt(timezone) < -12 || Integer.parseInt(timezone) > 12) {
                resp.setStatus(400);
                resp.getWriter().write("Invalid timezone");
                return;
            }
            ZoneId.of(timezone);
            chain.doFilter(req, resp);
        } catch (DateTimeException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Invalid timezone");
        }
    }
}
