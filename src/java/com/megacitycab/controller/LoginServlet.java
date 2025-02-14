package com.megacitycab.controller;

import com.megacitycab.service.AuthenticationService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * LoginServlet handles user login requests for the Mega City Cab system.
 * <p>
 * It retrieves username and password from the request, uses the AuthenticationService to validate credentials,
 * and forwards the user to the dashboard upon successful login or back to the login page with an error message.
 * </p>
 */
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    // Singleton instance of AuthenticationService.
    private AuthenticationService authService;

    @Override
    public void init() throws ServletException {
        super.init();
        // Obtain the singleton instance of AuthenticationService.
        authService = AuthenticationService.getInstance();
    }

    /**
     * Handles GET requests by forwarding them to the login page.
     *
     * @param request  the HttpServletRequest object that contains the request from the client.
     * @param response the HttpServletResponse object that contains the response to the client.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException      if an I/O error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to the login page (e.g., index.jsp or login.jsp)
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    /**
     * Handles POST requests for user login.
     *
     * @param request  the HttpServletRequest object that contains the request from the client.
     * @param response the HttpServletResponse object that contains the response to the client.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException      if an I/O error occurs.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Retrieve username and password from the request.
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Validate that username and password are provided.
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            
            request.setAttribute("errorMessage", "Username and password are required.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        
        // Delegate authentication to AuthenticationService.
        boolean isAuthenticated = authService.authenticate(username, password);
        
        if (isAuthenticated) {
            // Redirect or forward to the dashboard (or home page) on successful login.
            request.getSession().setAttribute("username", username);
            response.sendRedirect("dashboard.jsp");
        } else {
            // Return to login page with error message on failure.
            request.setAttribute("errorMessage", "Invalid username or password. Please try again.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
