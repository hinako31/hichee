package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Area;

public class AreaDAO {
   
    // static を外す
    public List<Area> getAllAreas() throws SQLException {
    	List<Area> areaList = new ArrayList<>();
    	  try(Connection conn = DBManager.getConnection()) {
        String sql = "SELECT id, area_name, sort_order FROM areas ORDER BY sort_order ASC";
        PreparedStatement pStmt = conn.prepareStatement(sql);
        ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String area_name = rs.getString("area_name");
                int sort_order = rs.getInt("sort_order");

                Area area = new Area(id, area_name);
                area.setSort_order(sort_order);

                areaList.add(area);
            }
            System.out.println("DBから取得したエリア数: " + areaList.size());
        } catch (SQLException e) {
            System.err.println("DBからのエリア取得に失敗: " + e.getMessage());
            e.printStackTrace();
        }

        return areaList;
    }
}