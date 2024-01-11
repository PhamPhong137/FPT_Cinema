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
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author PC-Phong
 */
public class FilmsServlet extends HttpServlet {

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
            out.println("<title>Servlet Films</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Films at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int film_id = Integer.parseInt(request.getParameter("movie"));
        List<Film> films = FilmDAO.INSTANCE.getFilmsById(film_id);
        EventDAO.INSTANCE.getEventbyFilmId(film_id);
        EventDAO.INSTANCE.getDatebyFilmId(film_id);

        request.setAttribute("film_detail", films);
        request.setAttribute("eventDao", EventDAO.INSTANCE);

        HttpSession session = request.getSession();
        Account taiKhoan = (Account) session.getAttribute("taiKhoan");

        String startHour = request.getParameter("time");
        if (startHour != null) {
            Ticket_film tf = Ticket_filmDAO.INSTANCE.getTicketByFilmIdAndStartHour(film_id, startHour);
            List<Seat> loadSeat = SeatDAO.INSTANCE.loadSeat(film_id, startHour);
            List<Integer> listSeatsFilm_booked = TicketDAO.INSTANCE.getListSeatsByTicketFilmId(tf.getId(), 1);
            List<Integer> listSeatsFilm_booking = TicketDAO.INSTANCE.getListSeatsByTicketFilmId(tf.getId(), 0);

            List<Ticket> loadTicket = TicketDAO.INSTANCE.loadTicket();
            // PrintWriter out = response.getWriter();

            request.setAttribute("loadSeat", loadSeat);
            request.setAttribute("startHour", startHour);
            request.setAttribute("ticketfilm", tf);
            request.setAttribute("listSeatsFilm_booked", listSeatsFilm_booked);
            request.setAttribute("listSeatsFilm_booking", listSeatsFilm_booking);
            request.setAttribute("taiKhoan", taiKhoan);
            request.setAttribute("loadTicket", loadTicket);

        }
        request.getRequestDispatcher("Views/movies.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
