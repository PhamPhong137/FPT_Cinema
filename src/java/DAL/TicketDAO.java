/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Ticket;
import Models.TicketHistory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC-Phong
 */
public class TicketDAO {

    private Connection con;
    private String status = "";
    public static TicketDAO INSTANCE = new TicketDAO();

    public TicketDAO() {
        if (INSTANCE == null) {
            try {
                con = new DBContext().getConnection();
            } catch (Exception e) {
                status = "Error ar connection" + e.getMessage();
            }
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Integer> getListSeatsByTicketFilmId(int ticket_filmid, int status) {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT seatid FROM ticket_he176151 t WHERE t.ticket_filmid = ? and t.status=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, ticket_filmid);
            ps.setInt(2, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("seatid"));
            }
        } catch (SQLException e) {
            System.err.println("Error at getListSeatsByTicketFilmId: " + e.getMessage());
        }
        return list;
    }

    public int insertTicket(int accountId, String seatId, String ticketFilmId, int status) {

        LocalDateTime curDateTime = java.time.LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTimeStr = curDateTime.format(formatter);

        String[] seatIds = seatId.split(",");
        int totalRowsAffected = 0;

        for (String si : seatIds) {
            String sql = "INSERT INTO ticket_he176151 (accountid, seatid, ticket_filmid,bookingTime,status) VALUES (?, ?, ?,?,?)";

            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, accountId);
                ps.setString(2, si);
                ps.setString(3, ticketFilmId);
                ps.setString(4, dateTimeStr); // Sử dụng chuỗi datetime với định dạng đã được định sẵn
                ps.setInt(5, status);
                int row = ps.executeUpdate();
                totalRowsAffected += row;
            } catch (SQLException e) {
                System.err.println("Error at insertTicket: " + e.getMessage());
            }
        }

        return totalRowsAffected;
    }

    public int removeTicket(int accountId, String seatId, String ticketFilmId) {
        String sql = "DELETE FROM ticket_he176151 WHERE accountid = ? AND seatid = ? AND ticket_filmid = ?";

        int rowsAffected = 0;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, accountId);
            ps.setString(2, seatId);
            ps.setString(3, ticketFilmId);

            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error at removeTicket: " + e.getMessage());
        }

        return rowsAffected;
    }

    public boolean isRecordExist(int accountId, String seatId, String ticketFilmId) {
        String sql = "SELECT 1 FROM ticket_he176151 WHERE accountid = ? AND seatid = ? AND ticket_filmid = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, accountId);
            ps.setString(2, seatId);
            ps.setString(3, ticketFilmId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("Error at isRecordExist: " + e.getMessage());
            return false;
        }
    }

    public List<TicketHistory> getListHistoriesByAccountId(int accountId) {
        List<TicketHistory> list = new ArrayList<>();
        String sql = "SELECT tf.id , s.seat_number, s.price, f.title, r.name, e.startHour,e.date,t.bookingTime\n"
                + "FROM ticket_he176151 t,seat_he176151 s,ticket_film_he176151 tf, film_he176151 f, room_he176151 r,event_he176151 e \n"
                + "WHERE t.accountid = ? and t.seatid = s.id and tf.id = t.ticket_filmid and f.id=tf.filmid and r.id=tf.roomid and tf.eventid=e.id";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new TicketHistory(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDate(7),
                        rs.getTimestamp(8)
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error at getListHistoriesByAccountId: " + e.getMessage());
        }
        return list;
    }

    public List<Ticket> loadTicket() {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM ticket_he176151";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                tickets.add(new Ticket(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getTimestamp(5),
                        rs.getInt(6)
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error at loadDB: " + e.getMessage());
        }

        return tickets;
    }

    public int deleteTicketsWithStatusZero(int accountId) {
      
        String sql = "DELETE FROM ticket_he176151 WHERE status = 0 AND accountid = ?";
        int rowsAffected = 0;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
 
            ps.setInt(1, accountId);
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error at deleteTicketsWithStatusZero: " + e.getMessage());
        }

        return rowsAffected;
    }

}
