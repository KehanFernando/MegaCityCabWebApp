<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megacitycab.controller;

import java.io.IOException;
import java.io.PrintWriter;
=======
package com.megacitycab.controller;

>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
<<<<<<< HEAD

/**
 *
 * @author Kehan Fernando
 */
public class HelpServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HelpServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HelpServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
=======
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
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
<<<<<<< HEAD
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
=======
        request.getRequestDispatcher("help.jsp").forward(request, response);
    }

    /**
     * Handles POST requests by forwarding them to help.jsp.
     *
     * @param request  the HttpServletRequest object that contains the request from the client.
     * @param response the HttpServletResponse object that contains the response to the client.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException      if an I/O error occurs.
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
<<<<<<< HEAD
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

=======
        // For simplicity, POST is treated the same as GET.
        request.getRequestDispatcher("help.jsp").forward(request, response);
    }
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
}
