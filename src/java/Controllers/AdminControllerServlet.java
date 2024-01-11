/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.AccountDAO;
import Models.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author PC-Phong
 */
public class AdminControllerServlet extends HttpServlet {

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
            out.println("<title>Servlet AdminController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account taiKhoan = (Account) session.getAttribute("taiKhoan");
        PrintWriter out = response.getWriter();
        if (taiKhoan != null && taiKhoan.getRole_id() == 0) {
            String type_raw = request.getParameter("type");
            String account_id_row = request.getParameter("id");
            if (type_raw != null && account_id_row != null) {
                int type = Integer.parseInt(type_raw);
                int account_id = Integer.parseInt(account_id_row);
                if (type == 0) {
                    Account account = AccountDAO.INSTANCE.getAccountById(account_id);
                    request.setAttribute("detailaccount", account);
                } else {
                    AccountDAO.INSTANCE.deleteAccountById(account_id);
                }
            }
            AccountDAO.INSTANCE.loadAccount();
            request.setAttribute("account", AccountDAO.INSTANCE);
            request.getRequestDispatcher("Views/admin.jsp").forward(request, response);
        } else {
            response.sendRedirect("Home");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int account_id = Integer.parseInt(request.getParameter("account_id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String email = request.getParameter("email");
        String phone_number = request.getParameter("phone_number");
        String role_id = request.getParameter("role_id");
        Object iu = request.getParameter("btnInUp");
        boolean checkUpdate = false;
        for (Account ac : AccountDAO.INSTANCE.getAccList()) {
            if (ac.getUsername().equals(username)) {
                checkUpdate = true;
                break;
            }
        }
        if (iu != null) {
            if (!checkUpdate) {
                AccountDAO.INSTANCE.adminInsertAccount(username, password, first_name, last_name, email, phone_number, role_id);
            } else {
                AccountDAO.INSTANCE.adminUpdateAccount(account_id, username, password, first_name, last_name, email, phone_number, role_id);
            }
        }
        
        doGet(request, response);
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
