package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.AreaDAO;
import model.Area;

public class AreaLogic {
	
    public List<Area> getAllAreas() {
        try {
            AreaDAO dao = new AreaDAO();
            return dao.getAllAreas(); // すでにORDER BY sort_order されてる
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>(); // nullを返さず安全
        }
    }

}		