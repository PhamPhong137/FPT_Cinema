/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.RoomDAO;
import Models.Room;
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
public class AdminEditRoom extends HttpServlet {

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
            out.println("<title>Servlet AdminEditRoom</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminEditRoom at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String room_raw = request.getParameter("roomid");
        if (room_raw != null) {
            int room = Integer.parseInt(room_raw);
            int type = Integer.parseInt(request.getParameter("type"));
            if (type == 0) {
                Room detailFilm = RoomDAO.INSTANCE.getRoomById(room);
                request.setAttribute("detailRoom", detailFilm);
            } else {
                RoomDAO.INSTANCE.deleteRoomById(room);
            }
        }
        RoomDAO.INSTANCE.loadRoom();
        request.setAttribute("RoomDAO", RoomDAO.INSTANCE);

        request.getRequestDispatcher("Views/admineditroom.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int capacity = Integer.parseInt(request.getParameter("capacity"));
        PrintWriter out = response.getWriter();
   
        Object iu = request.getParameter("btnInUp");
        boolean checkUpdate = false;
        for (Room r : RoomDAO.INSTANCE.getListRoom()) {
            if (r.getName().equals(name)) {
                checkUpdate = true;
                break;
            }
        }
        if (iu != null) {
            if (!checkUpdate) {
                RoomDAO.INSTANCE.insertRoom(name, capacity);
            } else {
                RoomDAO.INSTANCE.updateRoom(id, name, capacity);
            }
        }
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
