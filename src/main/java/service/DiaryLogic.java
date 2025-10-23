package service;

import java.util.List;

import dao.DiaryDAO;
import model.Diary;


public class DiaryLogic {

	
	 //退会したときのDiary削除
    public boolean deleteDiary(int userId) {
    	DiaryDAO dao = new DiaryDAO();
    	return dao.deleteDiary(userId);
    }

    //new Cheese作成時の登録指示
	public boolean registerDiary(Diary diary, int userId) {
		DiaryDAO dao = new DiaryDAO();   
		// diary に userId をセット
	        diary.setUserid(userId);

	        // DAOに登録依頼
	        return dao.insertDiary(diary);
	    
	}

	//MyCheese検索
        /**
	     * 複数条件での検索
	     * @param userId ログインユーザーID
	     * @param name 店名あいまい検索（空文字やnullは条件にしない）
	     * @param periodYear 記念年の文字列（""、"unknown"あり）
	     * @param periodMonth 記念月の文字列（""、"unknown"あり）
	     * @param areaId 場所IDの文字列（""、"unknown"あり）
	     * @return 検索結果リスト
	     */
	    public List<model.Diary> searchDiaries(int userId, String name, String periodYearStr, String periodMonthStr, String areaIdStr) {
	    	DiaryDAO diaryDAO = new DiaryDAO();
	    	// ここで条件を組み立て、DAOへ渡す形にする
	        return diaryDAO.searchByConditions(userId,name,periodYearStr, periodMonthStr,areaIdStr);
	    }

	    
	    public Diary getDiaryById(int id, int userId) {
	        DiaryDAO dao = new DiaryDAO();
	        return dao.findById(id, userId);
	    }
		

    
}
