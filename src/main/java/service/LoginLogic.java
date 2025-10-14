package service;

import java.util.List;

import dao.DiaryDAO;
import dao.UserDAO;
import model.Diary;
import model.LoginUser;
import model.User;

public class LoginLogic {
	
	//ユーザー情報の照合の指示
    public User execute(LoginUser loginUser) {
        UserDAO dao = new UserDAO();
//        DAOの照合処理を指示
        User user = dao.findByLogin(loginUser);
        return user; //userはDAOからreturnされたものをそのまま返す
//        入力したemail、passが存在してない→user=null
//        存在する→user=DBに保存されているid,name,email,passが代入        
    }
    public List<Diary> getDiaryList(int user_id) {
        DiaryDAO diaryDAO = new DiaryDAO();
        return diaryDAO.findByUserId(user_id);
    }
}

