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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC-Phong
 */
public class Ticket_filmDAO {

    private Connection con;
    private String status = "";
    public static Ticket_filmDAO INSTANCE = new Ticket_filmDAO();

    public Ticket_filmDAO() {
        if (INSTANCE == null) {
            try {
                con = new DBContext().getConnection();
            } catch (Exception e) {
                status = "Error ar connection" + e.getMessage();
            }
        }
    }

    public Ticket_film getTicketByFilmIdAndStartHour(int film_id, String startHour) {
        Ticket_film ticket = null;
        String sql = "SELECT tf.* "
                + "FROM ticket_film_he176151 AS tf "
                + "INNER JOIN event_he176151 AS e ON tf.eventid = e.id "
                + "WHERE tf.filmid = ? AND e.startHour = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, film_id);
            ps.setString(2, startHour);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ticket = new Ticket_film(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4)
                );
            }
        } catch (SQLException e) {
            System.err.println("Error at getTicketByFilmIdAndStartHour: " + e.getMessage());
        }
        return ticket;
    }

    public boolean insertTicket(int filmId, int roomId, int eventId) {
        String sql = "INSERT INTO ticket_film_he176151(filmid, roomid, eventid) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, filmId);
            ps.setInt(2, roomId);
            ps.setInt(3, eventId);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Error at insertTicket: " + e.getMessage());
            return false;
        }
    }

    public List<TicketFilmDTO> getlistTicketFilmDTO() {
        List<TicketFilmDTO> list = new ArrayList<>();
        String sql = "select f.title,e.startHour, e.date, r.name\n"
                + "from ticket_film_he176151 tf,  film_he176151 f, event_he176151 e,  room_he176151 r\n"
                + "where tf.filmid = f.id and tf.eventid=e.id and r.id=tf.roomid";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TicketFilmDTO t = new TicketFilmDTO(rs.getString(1),rs.getString(4), rs.getString(2) +"   "+ rs.getString(3));
                list.add(t);
            }
        } catch (SQLException e) {
            System.err.println("Error at getTicketByFilmIdAndStartHour: " + e.getMessage());
        }
        return list;
    }

}
