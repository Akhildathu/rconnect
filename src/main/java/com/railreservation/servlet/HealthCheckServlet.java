package com.railreservation.servlet;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;

@WebServlet(urlPatterns = "/health")
public class HealthCheckServlet extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            response.getWriter().print("Up");
        } catch (IOException e) {
            throw new ServletException(e);
        }

    }
}