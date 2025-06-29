package com.goit;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/time")
public class TimezoneValidateFilter extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        String timezone = req.getParameter("timezone");
        if (timezone == null) {
            chain.doFilter(req, resp);
            return;
        }
        timezone = timezone.replace("UTC", "");
        try {
           int time =  Integer.parseInt(timezone);
            if (time < -12 || time > 12) {
                resp.setStatus(400);
                resp.getWriter().write("Invalid timezone");
            } else chain.doFilter(req,resp);
        } catch (NumberFormatException e) {
            resp.setStatus(401);
            resp.getWriter().write("Invalid timezone");
        }

    }
}
