package service;

import java.sql.SQLException;
import java.util.List;

import dao.DiaryDAO;
import dao.UserDAO;
import model.Diary;
import model.LoginUser;
import model.User;

public class LoginLogic {
	 private UserDAO userDAO = new UserDAO();
	//ユーザー情報の照合の指示
    public User execute(LoginUser loginUser) {
//        DAOの照合処理を指示
        User user = userDAO.findByLogin(loginUser);
        return user; //userはDAOからreturnされたものをそのまま返す
//        入力したemail、passが存在してない→user=null
//        存在する→user=DBに保存されているid,name,email,passが代入        
    }
    public User login(String email, String pass) throws SQLException {
        User user = userDAO.findByEmail(email);

        if (user == null) {
            return null; // メールアドレスが未登録
        }

        if (!user.getPass().equals(pass)) {
            // パスワードが違う → パスワードのみ不一致と示すために "INVALID" をセット
            user.setPass("INVALID");
        }

        return user;
    }

    public List<Diary> getDiaryList(int userId) throws SQLException {
        DiaryDAO dao = new DiaryDAO();
        return dao.findByUserId(userId); // try-catchしない
    }
}