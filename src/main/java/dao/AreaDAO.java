package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Area;

public class AreaDAO {
    private final static String JDBC_URL = "jdbc:mysql://localhost:3306/sampledb";
    private final static String DB_USER = "root";
    private final static String DB_PASS = "password";

    // static を外す
    public List<Area> getAllAreas() throws SQLException {
        List<Area> areaList = new ArrayList<>();
        String sql = "SELECT id, area_name FROM areas ORDER BY sort_order ASC";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String area_name = rs.getString("area_name");
                areaList.add(new Area(id, area_name));
            }
        }

        return areaList;
    }

}