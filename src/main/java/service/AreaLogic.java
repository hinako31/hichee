package service;

import java.sql.SQLException;
import java.util.List;

import dao.AreaDAO;
import model.Area;

public class AreaLogic {
	 // DAOを呼んでareaListを取得するだけのメソッド
	public List<Area> getAllAreas() throws SQLException {
        AreaDAO dao = new AreaDAO();
        return dao.getAllAreas(); // ← static じゃなくなったのでこれでOK
    }

	    public List<Area> getOrderedAreaList() {
	        AreaDAO dao = new AreaDAO();
	        try {
	            return dao.getOrderedAreaList(); // インスタンスメソッド呼び出し
	        }  catch (SQLException e) {
	            System.err.println("エリア一覧の取得中にエラー発生: " + e.getMessage());
	            e.printStackTrace(); // これ重要！
	            return null;
	        }

	    }

}		