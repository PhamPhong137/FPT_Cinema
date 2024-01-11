/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.NewsDAO;
import Models.Paging;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author PC-Phong
 */
@WebServlet(name = "NewsServlet", urlPatterns = {"/News"})
public class NewsServlet extends HttpServlet {

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
            out.println("<title>Servlet NewsServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewsServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NewsDAO.INSTANCE.loadNews();
        int nrpp = 4; 
      
        int index = -1;
        try {
            index = Integer.parseInt(request.getAttribute("index") + "");
        } catch (NumberFormatException e) {
        }
        Paging p = new Paging(nrpp, index, NewsDAO.INSTANCE.getListNews().size());
        p.calc();
        request.setAttribute("page", p);
        request.setAttribute("nrppArr", nrpp);
        request.setAttribute("newDAO", NewsDAO.INSTANCE);
        request.getRequestDispatcher("Views/News.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int index = Integer.parseInt(request.getParameter("index"));
        int totalPage = Integer.parseInt(request.getParameter("totalPage"));
        if (request.getParameter("btnHome") != null) {
            index = 0;
        }
        if (request.getParameter("btnEnd") != null) {
            index = totalPage - 1;
        }
        if (request.getParameter("btnNext") != null) {
            index += 1;
        }
        if (request.getParameter("btnPre") != null) {
            index -= 1;
        }
        for (int i = 0; i < totalPage; i++) {
            if (request.getParameter("btn" + i) != null) {
                index = i;
            }
        }
 
        request.setAttribute("index", index);
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}