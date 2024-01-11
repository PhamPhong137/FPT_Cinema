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
import java.util.List;

/**
 *
 * @author PC-Phong
 */
public class AdminEditTicket extends HttpServlet {

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
            out.println("<title>Servlet AdminEditTicket</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminEditTicket at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        FilmDAO.INSTANCE.loadFilm();
        RoomDAO.INSTANCE.loadRoom();
        EventDAO.INSTANCE.loadEvent();
        List<TicketFilmDTO> list = Ticket_filmDAO.INSTANCE.getlistTicketFilmDTO();
        
        request.setAttribute("list", list);
        request.setAttribute("filmDao", FilmDAO.INSTANCE);
        request.setAttribute("roomDao", RoomDAO.INSTANCE);
//int film_id = Integer.parseInt(request.getParameter("film"));
//int length = FilmDAO.INSTANCE.getFilmsById(film_id).get(0).getLength();
//String startHour = request.getParameter("event");
//List<Room> listRooms = RoomDAO.INSTANCE.listRoomAvailableByStartHour(startHour, length);
//        request.setAttribute("listRooms", listRooms);
        request.setAttribute("eventDao", EventDAO.INSTANCE);

        request.getRequestDispatcher("Views/admineditticket.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int film_id = Integer.parseInt(request.getParameter("film"));
        int room_id = Integer.parseInt(request.getParameter("room"));
        int event_id = Integer.parseInt(request.getParameter("event"));

        Ticket_filmDAO.INSTANCE.insertTicket(film_id, room_id, event_id);
        doGet(request, response);

    }

    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
