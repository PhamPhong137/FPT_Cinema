/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Event;

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
public class EventDAO {

    private Connection con;
    private String status = "";
    private List<Event> eventFilm;
    private List<Event> dateFilm;
    
    private List<Event> listEvent;
    public static EventDAO INSTANCE = new EventDAO();

    public EventDAO() {
        if (INSTANCE == null) {
            try {
                con = new DBContext().getConnection();
            } catch (Exception e) {
                status = "Error ar connection" + e.getMessage();
            }
        }
    }

    public List<Event> getListEvent() {
        return listEvent;
    }

    public void setListEvent(List<Event> listEvent) {
        this.listEvent = listEvent;
    }
    
    public List<Event> getDateFilm() {
        return dateFilm;
    }

    public void setDateFilm(List<Event> dateFilm) {
        this.dateFilm = dateFilm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Event> getEventFilm() {
        return eventFilm;
    }

    public void setEventFilm(List<Event> eventFilm) {
        this.eventFilm = eventFilm;
    }

    public void getEventbyFilmId(int filmId) {
        eventFilm = new Vector<>();
        String sql = "SELECT e.id,e.startHour, e.date\n"
                + "FROM film_he176151 AS f\n"
                + "INNER JOIN ticket_film_he176151 AS tf ON f.id = tf.filmid\n"
                + "INNER JOIN event_he176151 AS e ON tf.eventid = e.id\n"
                + "WHERE f.id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, filmId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                eventFilm.add(new Event(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3)
                ));
            }
        } catch (SQLException e) {
            status = "Error at getEventbyFilmId " + e.getMessage();
        }
    }
    public void getDatebyFilmId(int filmId) {
        dateFilm = new Vector<>();
        String sql = "SELECT distinct e.date\n"
                + "FROM film_he176151 AS f\n"
                + "INNER JOIN ticket_film_he176151 AS tf ON f.id = tf.filmid\n"
                + "INNER JOIN event_he176151 AS e ON tf.eventid = e.id\n"
                + "WHERE f.id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, filmId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dateFilm.add(new Event(
                        rs.getDate(1)
                ));
            }
        } catch (SQLException e) {
            status = "Error at getDatebyId " + e.getMessage();
        }
    }
     public void loadEvent() {
        listEvent = new Vector<>();
        String sql = "select * from Event_he176151";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listEvent.add(new Event(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3)                   
                ));
            }
        } catch (SQLException e) {
            status = "Error ar load Event " + e.getMessage();
        }
    }
    
    

}
