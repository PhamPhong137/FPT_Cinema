/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.TicketDAO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.Reader;

/**
 *
 * @author PC-Phong
 */
public class RemoveTicketStatus0Servlet extends HttpServlet {

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
            out.println("<title>Servlet RemoveTicketStatus0Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RemoveTicketStatus0Servlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Gson gson = new Gson();

        // Đọc dữ liệu từ request
        Reader reader = request.getReader();

        // Chuyển đổi dữ liệu JSON thành một đối tượng Java
        JsonObject inputJson = gson.fromJson(reader, JsonObject.class);

        // Lấy dữ liệu từ đối tượng JsonObject
        int taikhoanId = inputJson.get("taikhoanid").getAsInt();

        // Thực hiện xóa vé
        int rowsDeleted = TicketDAO.INSTANCE.deleteTicketsWithStatusZero(taikhoanId);

        // Tạo và gửi phản hồi JSON
        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("status", "success");
        responseJson.addProperty("rowsDeleted", rowsDeleted);
        response.getWriter().write(gson.toJson(responseJson));
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
