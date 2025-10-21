package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import model.Area;

public class AreaDAO {
    private final static String JDBC_URL = "jdbc:mysql://localhost:3306/sampledb";
    private final static String DB_USER = "root";
    private final static String DB_PASS = "password";

    // static を外す
    public static List<Area> getAllAreas() throws SQLException {
        List<Area> areaList = new ArrayList<>();
        String sql = "SELECT id, area_name FROM areas";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("area_name");
                areaList.add(new Area(id, name));
            }
        }

        return areaList;
    }

    public List<Area> getOrderedAreaList() throws SQLException {
        List<Area> allAreas = getAllAreas(); // static でなくなったのでOK

        List<Area> sortedAreas = new ArrayList<>();
        Area overseas = null;
        Area unknown = null;

        for (Area area : allAreas) {
            if (area.getId() == 0) {
                overseas = area;
            } else if (area.getId() == 99) {
                unknown = area;
            } else {
                sortedAreas.add(area); // 1〜47
            }
        }

        sortedAreas.sort(Comparator.comparingInt(Area::getId));

        List<Area> result = new ArrayList<>();
        if (overseas != null) result.add(overseas);
        result.addAll(sortedAreas);
        if (unknown != null) result.add(unknown);

        return result;
    }
}