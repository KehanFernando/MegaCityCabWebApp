package com.megacitycab.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HelpServlet provides usage guidelines and system help information.
 * <p>
 * It forwards incoming requests to the help.jsp view, where help information
 * regarding the Mega City Cab system usage is displayed.
 * </p>
 */
public class HelpServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Handles GET requests by forwarding them to help.jsp.
     *
     * @param request  the HttpServletRequest object that contains the request from the client.
     * @param response the HttpServletResponse object that contains the response to the client.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException      if an I/O error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("help.jsp").forward(request, response);
    }

    /**
     * Handles POST requests by forwarding them to help.jsp.
     *
     * @param request  the HttpServletRequest object that contains the request from the client.
     * @param response the HttpServletResponse object that contains the response to the client.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException      if an I/O error occurs.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // For simplicity, POST is treated the same as GET.
        request.getRequestDispatcher("help.jsp").forward(request, response);
    }
}
