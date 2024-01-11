/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.TicketDAO;
import Models.Account;
import Models.TicketHistory;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author PC-Phong
 */
public class HistoryServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HistoryServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HistoryServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        Account taiKhoan = (Account) session.getAttribute("taiKhoan");
        List<TicketHistory> lh = TicketDAO.INSTANCE.getListHistoriesByAccountId(taiKhoan.getAccount_id());
        Map<Timestamp, TicketHistory> hashmap = new HashMap<>();

        for (TicketHistory t : lh) {
            if (hashmap.containsKey(t.getBookingTime())) {
                TicketHistory existingTicket = hashmap.get(t.getBookingTime());
                existingTicket.setSeat_number(existingTicket.getSeat_number() + ", " + t.getSeat_number());
                existingTicket.setPrice((int) (existingTicket.getPrice() + t.getPrice()));
            } else {
                hashmap.put(t.getBookingTime(), t);
            }
        }

        List<TicketHistory> valuesList = new ArrayList<>(hashmap.values());
        request.setAttribute("listHistories", valuesList);

        request.getRequestDispatcher("Views/History.jsp").forward(request, response);

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
