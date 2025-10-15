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

	    public static List<Area> getAllAreas() {
	        List<Area> areaList = new ArrayList<>();

	        String sql = "SELECT id, area_name FROM areas ORDER BY id";

	        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
	             PreparedStatement stmt = conn.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("area_name");
	                areaList.add(new Area(id, name));
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            // 必要に応じて例外を再スローやログ出力などの処理を行う
	        }

	        return areaList;
	    }
	}