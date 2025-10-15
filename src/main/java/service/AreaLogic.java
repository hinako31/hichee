package service;

import java.util.List;

import dao.AreaDAO;

public class AreaLogic {
	 // DAOを呼んでareaListを取得するだけのメソッド
    public List<model.Area> getAllAreas() {
        return AreaDAO.getAllAreas();
    }

    // 他に業務ロジックがあればここに追加
}