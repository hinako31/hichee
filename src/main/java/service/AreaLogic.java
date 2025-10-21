package service;

import java.sql.SQLException;
import java.util.ArrayList;
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
	    try {
	    	 AreaDAO dao = new AreaDAO();
	    	  return dao.getAllAreas();  // sort_orderで並んでくる
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ArrayList<>(); // nullじゃなくて空リストにしておくと安全
	    }
	}

}		