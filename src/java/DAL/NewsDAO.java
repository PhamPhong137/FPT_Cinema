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
public class NewsDAO {

    private Connection con;
    private String status = "";
    private List<New> listNews;
    public static NewsDAO INSTANCE = new NewsDAO();

    public NewsDAO() {
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

    public List<New> getListNews() {
        return listNews;
    }

    public void setListNews(List<New> listNews) {
        this.listNews = listNews;
    }

    public void loadNews() {
        listNews = new Vector<>();
        String sql = "select * from news_he176151";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listNews.add(new New(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getString(4)
                ));
            }
        } catch (SQLException e) {
            status = "Error ar load News " + e.getMessage();
        }
    }

}
