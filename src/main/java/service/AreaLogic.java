package service;

import java.sql.SQLException;
import java.util.List;

import dao.AreaDAO;
import model.Area;

public class AreaLogic {
	 // DAOを呼んでareaListを取得するだけのメソッド
	public List<model.Area> getAllAreas() throws SQLException {
	    return AreaDAO.getAllAreas();
	}

	    public List<Area> getOrderedAreaList() {
	        AreaDAO dao = new AreaDAO();
	        try {
	            return dao.getOrderedAreaList(); // インスタンスメソッド呼び出し
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

}		