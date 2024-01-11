/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Film;
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
public class FilmDAO {

    private Connection con;
    private String status = "";
    private List<Film> accFilm;
    public static FilmDAO INSTANCE = new FilmDAO();

    public FilmDAO() {
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

    public List<Film> getAccFilm() {
        return accFilm;
    }

    public void setAccFilm(List<Film> accFilm) {
        this.accFilm = accFilm;
    }

    public void loadFilm() {
        accFilm = new Vector<>();
        String sql = "select * from Film_he176151";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                accFilm.add(new Film(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getDate(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getInt(14)
                ));
            }
        } catch (SQLException e) {
            status = "Error ar load Film " + e.getMessage();
        }
    }

    public List<Film> getFilmsById(int id) {
        List<Film> films = new Vector<>();
        String sql = "SELECT * FROM Film_he176151 WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                films.add(new Film(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getDate(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getInt(14)
                ));
            }
        } catch (SQLException e) {
            status = "Error at getFilmsById " + e.getMessage();
        }
        return films;
    }

    public Film getFilmByTitle(String title) {
        Film film = null;
        String sql = "SELECT * FROM Film_he176151 WHERE title LIKE ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + title + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                film = new Film(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getDate(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getInt(14)
                );
            }
        } catch (SQLException e) {
            status = "Error at getFilmByTitle " + e.getMessage();
        }
        return film;
    }

    public boolean insertFilm(String title, String category, int length, String description, int age_limit, String director, String actor, String warning_text, String publish_time, String origin, String img, String img_background, int status) {
    String sql = "INSERT INTO Film_he176151 (title, category, length, description, age_limit, director, actor, warning_text, publish_time, origin, img, img_background, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, category);
        ps.setInt(3, length);
        ps.setString(4, description);
        ps.setInt(5, age_limit);
        ps.setString(6, director);
        ps.setString(7, actor);
        ps.setString(8, warning_text);
        ps.setString(9, publish_time); 
        ps.setString(11, img);
        ps.setString(12, img_background);
        ps.setInt(13, status);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0; // Return true if insertion was successful

    } catch (SQLException e) {

    }
    return false;
}


   public boolean updateFilm(int id, String title, String category, int length, String description, int age_limit, String director, String actor, String warning_text, String publish_time, String origin, String img, String img_background, int status) {
    String sql = "UPDATE Film_he176151 SET title = ?, category = ?, length = ?, description = ?, age_limit = ?, director = ?, actor = ?, warning_text = ?, publish_time = ?, origin = ?, img = ?, img_background = ?, status = ? WHERE id = ?";
    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, category);
        ps.setInt(3, length);
        ps.setString(4, description);
        ps.setInt(5, age_limit);
        ps.setString(6, director);
        ps.setString(7, actor);
        ps.setString(8, warning_text);
        ps.setString(9, publish_time); 
        ps.setString(11, img);
        ps.setString(12, img_background);
        ps.setInt(13, status);
        ps.setInt(14, id); 
        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0; 

    } catch (SQLException e) {
     
    }
    return false;
}


}
