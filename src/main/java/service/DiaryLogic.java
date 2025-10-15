package service;

import dao.DiaryDAO;

public class DiaryLogic {

	
	 //退会したときのDiary削除
    public boolean deleteDiary(int userId) {
    	DiaryDAO dao = new DiaryDAO();
    	return dao.deleteDiary(userId);
    }
    
}
