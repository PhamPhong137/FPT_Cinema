/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author PC-Phong
 */
public class AccountDAO {

    private Connection con;
    private String status = "";
    private List<Account> accList;
    public static AccountDAO INSTANCE = new AccountDAO();

    public AccountDAO() {
        if (INSTANCE == null) {
            try {
                con = new DBContext().getConnection();
            } catch (Exception e) {
                status = "Error ar connection" + e.getMessage();
            }
        }
    }

    public List<Account> getAccList() {
        return accList;
    }

    public void setAccList(List<Account> accList) {
        this.accList = accList;
    }

    public void loadAccount() {
        accList = new Vector<>();
        String sql = "select * from account_he176151";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                accList.add(new Account(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8)
                ));
            }
        } catch (SQLException e) {
            status = "Error ar load Account " + e.getMessage();
        }
    }

    public boolean checkLogin(String username, String password) {
        for (Account acc : accList) {
            if (acc.getUsername().equals(username) && acc.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void insertAccount(String username, String password, String firstname, String lastname, String email, String phonenumber) {
        String sql = "Insert into Account_he176151 values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, firstname);
            ps.setString(4, lastname);
            ps.setString(5, email);
            ps.setString(6, phonenumber);
            ps.setInt(7, 1);
            ps.execute();
        } catch (SQLException e) {
            status = "Error ar insert Account" + e.getMessage();
        }
    }

    public void adminInsertAccount(String username, String password, String firstname, String lastname, String email, String phonenumber, String role_id) {
        String sql = "Insert into Account_he176151 values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, firstname);
            ps.setString(4, lastname);
            ps.setString(5, email);
            ps.setString(6, phonenumber);
            ps.setString(7, role_id);
            ps.execute();
        } catch (SQLException e) {
            status = "Error ar insert Account" + e.getMessage();
        }
    }

    public boolean checkExisted(String username) {
        for (Account acc : accList) {
            if (acc.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    public Account getAccByUsername(String username) {
        Account account = null;
        String sql = "SELECT * FROM Account_he176151 WHERE username = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                account = new Account(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8)
                );
            }
        } catch (SQLException e) {
            status = "Error at get Acc";
        }
        return account;
    }

    public void updateAccount(int id, String first_name, String last_name, String phone_number, String email, String password) {
        String sql = "UPDATE Account_he176151 SET first_name = ?, last_name = ?, phone_number = ?, email = ?, password = ? WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, first_name);
            ps.setString(2, last_name);
            ps.setString(3, phone_number);
            ps.setString(4, email);
            ps.setString(5, password);
            ps.setInt(6, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            status = "Error at update Account: " + e.getMessage();
        }
    }

    public void adminUpdateAccount(int id, String username, String password, String first_name, String last_name, String email, String phone_number, String role_id) {
        String sql = "UPDATE Account_he176151 SET username = ?, password = ?, first_name = ?, last_name = ?, email = ?, phone_number = ?, role_id = ? WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, first_name);
            ps.setString(4, last_name);
            ps.setString(5, email);
            ps.setString(6, phone_number);
            ps.setString(7, role_id);
            ps.setInt(8, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            status = "Error at full update Account: " + e.getMessage();
        }
    }

    public Account getAccountById(int id) {
        Account account = null;
        String sql = "SELECT * FROM Account_he176151 WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                account = new Account(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8)
                );
            }
        } catch (SQLException e) {
            status = "Error at get Account by ID: " + e.getMessage();
        }
        return account;
    }

    public void deleteAccountById(int id) {
        String sql = "DELETE FROM Account_he176151 WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            status = "Error at delete Account by ID: " + e.getMessage();
        }
    }

    public boolean checkUsernameOrPhoneNumberExists(String username, String phoneNumber) {
        for (Account acc : accList) {
            if (acc.getUsername().equals(username) && acc.getPhone_number().equals(phoneNumber)) {
                return true; // Cả username và phone number đều phải tồn tại và khớp với một tài khoản
            }
        }
        return false; // Không tìm thấy tài khoản nào khớp cả hai
    }

    public void updatePasswordByUsernameAndPhoneNumber(String username, String phoneNumber, String newPassword) {
        String sql = "UPDATE Account_he176151 SET password = ? WHERE username = ? AND phone_number = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, newPassword);
            ps.setString(2, username);
            ps.setString(3, phoneNumber);
            int updatedRows = ps.executeUpdate();

            if (updatedRows == 0) {
                status = "No account found or update failed.";
            }
        } catch (SQLException e) {
            status = "Error at update Password: " + e.getMessage();
        }
    }

}
