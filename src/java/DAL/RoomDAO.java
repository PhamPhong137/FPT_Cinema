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
import java.util.Vector;

/**
 *
 * @author PC-Phong
 */
public class RoomDAO {

    private Connection con;
    private String status = "";
    private List<Room> listRoom;
    public static RoomDAO INSTANCE = new RoomDAO();

    public RoomDAO() {
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

    public List<Room> getListRoom() {
        return listRoom;
    }

    public void setListRoom(List<Room> listRoom) {
        this.listRoom = listRoom;
    }

    public Room getRoomById(int id) {
        Room r = null;
        String sql = "SELECT * FROM room_he176151 WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                r = new Room(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)
                );
            }
            return r;
        } catch (SQLException e) {
            status = "Error at getRoomById " + e.getMessage();
        }
        return null;
    }

    public void loadRoom() {
        listRoom = new Vector<>();
        String sql = "SELECT * FROM room_he176151";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listRoom.add(new Room(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)
                ));
            }
        } catch (SQLException e) {
            status = "Error at loadRoom " + e.getMessage();
        }
    }

    public boolean insertRoom(String name, int capacity) {
        String sql = "INSERT INTO room_he176151 (name, capacity) VALUES (?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, capacity);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Return true if insertion was successful
        } catch (SQLException e) {
            status = "Error at insertRoom " + e.getMessage();
        }
        return false;
    }

    public boolean updateRoom(int id, String name, int capacity) {
        String sql = "UPDATE room_he176151 SET name = ?, capacity = ? WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, capacity);
            ps.setInt(3, id);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Return true if update was successful
        } catch (SQLException e) {
            status = "Error at updateRoom " + e.getMessage();
        }
        return false;
    }

    public boolean deleteRoomById(int id) {
        String sql = "DELETE FROM room_he176151 WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Return true if deletion was successful
        } catch (SQLException e) {
            status = "Error at deleteRoomById " + e.getMessage();
        }
        return false;
    }
    
    public List<Room> listRoomAvailableByStartHour(String startHour, int length) {
         List<Room> list = new ArrayList<>();
        String sql = "select r.* \n" +
"from room_he176151 r\n" +
"where r.id not in (select r.id\n" +
"    from ticket_film_he176151 tf, film_he176151 f, event_he176151 e, room_he176151 r\n" +
"where tf.filmid=f.id and tf.eventid=e.id and tf.roomid=r.id  and DATEADD(MINUTE, f.length, e.startHour)>"+startHour+" "
                + "and DATEADD(MINUTE, f.length, e.startHour)<DATEADD(MINUTE, "+length+", CAST('"+startHour+"' AS TIME)))";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listRoom.add(new Room(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)
                ));
            }
            
        } catch (SQLException e) {
            status = "Error at deleteRoomById " + e.getMessage();
        }
        return list;
    }
            

}
