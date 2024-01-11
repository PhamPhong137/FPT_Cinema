/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.*;

import Models.*;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author PC-Phong
 */
public class AdminEditFilm extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminEditFilm</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminEditFilm at " + request.getContextPath() + "</h1>");
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
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String film = request.getParameter("film");
        if (film != null) {
            Film detailFilm = FilmDAO.INSTANCE.getFilmByTitle(film);
            request.setAttribute("detailFilm", detailFilm);
        }
        FilmDAO.INSTANCE.loadFilm();
        request.setAttribute("filmDao", FilmDAO.INSTANCE);
        request.getRequestDispatcher("Views/admineditfilm.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int filmid = Integer.parseInt(request.getParameter("filmid"));
        String title = request.getParameter("title");
        String category = request.getParameter("category");
        int length = Integer.parseInt(request.getParameter("length"));
        String description = request.getParameter("description");
        int age_limit = Integer.parseInt(request.getParameter("age_limit"));
        String director = request.getParameter("director");
        String actor = request.getParameter("actor");
        String warning_text = request.getParameter("warning_text");
        String publish_time = request.getParameter("publish_time");
        String origin = request.getParameter("origin");
        String img = request.getParameter("img");
        String img_background = request.getParameter("img_background");
        int status = Integer.parseInt(request.getParameter("status"));
        Object iu = request.getParameter("btnInUp");
        boolean checkUpdate = false;
        for (Film ac : FilmDAO.INSTANCE.getAccFilm()) {
            if (ac.getTitle().equals(title)) {
                checkUpdate = true;
                break;
            }
        }
        if (iu != null) {
            if (!checkUpdate) {
                FilmDAO.INSTANCE.insertFilm(title, category, length, description, age_limit, director, actor, warning_text, publish_time, origin, img, img_background, status);
            } else {
                FilmDAO.INSTANCE.updateFilm(filmid, title, category, length, description, age_limit, director, actor, warning_text, publish_time, origin, img, img_background, status);
            }
        }
        doGet(request, response);
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

}
