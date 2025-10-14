package service;

import java.sql.SQLException;

import dao.UserDAO;
import model.User;

public class RegistLogic {
	private UserDAO userDAO = new UserDAO();
	
	
	public boolean register(User user) throws SQLException {
		 // 既にメールが使われていないかチェック
        User exist = userDAO.findByEmail(user.getEmail());
        if (exist != null) {//emailが存在する場合
            return false; //register＝false
            //以下の指示は実行しないで抜ける
        }
        return userDAO.insert(user);//会員登録情報をDBに追加
    
	}
	

}
