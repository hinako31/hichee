package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.LoginUser;
import model.User;
 
public class UserDAO {
	
	
	 // ログイン認証用メソッド
    public User findByLogin(LoginUser loginUser) {
        User user = null;

        try(Connection conn = DBManager.getConnection()) {
            // SELECT文を準備
            String sql ="SELECT id,name,pass,email FROM users WHERE email=? AND pass=?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, loginUser.getEmail());//ユーザーが入力したemailをSELECT文の1番目のパラメータemail=?に
            pStmt.setString(2, loginUser.getPass());//ユーザーが入力したパスワードを2番目のパラメータに
            //SQLの？にセットしDBからそのメールアドレスとemailが一致するユーザー
            //を検索するクエリを作成した結果が返ってきて
            //↓でユーザー情報を取得
            ResultSet rs = pStmt.executeQuery();

            if (rs.next()) {//検索結果に該当ユーザーがいたら中の処理をする
                int id = rs.getInt("id");//ユーザー情報取得して代入
                String email = rs.getString("email");
                String pass = rs.getString("pass");
                String name = rs.getString("name");
          
                user = new User(id, name , email , pass);
                //代入したものをUserのthis.〇〇＝〇〇に代入→データベースの情報をセット
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        //入力したパスワードとパスワードが存在…データベースのユーザー情報を返す
        //存在しない…nullを返す
        return user;
    }
    public User findByEmail(String email) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBManager.getConnection();
            String sql = "SELECT name, pass, email FROM users WHERE email = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {//emailが存在したら
                User user = new User();
                user.setEmail(rs.getString("name"));//ユーザー情報取得する
                user.setPass(rs.getString("pass"));
                user.setName(rs.getString("email"));
                return user;//null→userに変える
            } else {
                return null;//該当するメールアドレスがなかったらnullのまま返す
            }
        } finally {
            if (rs != null) try { rs.close(); } catch(Exception e){}
            if (ps != null) try { ps.close(); } catch(Exception e){}
            if (conn != null) try { conn.close(); } catch(Exception e){}
        }
    }
    
    //会員登録内容をテーブルに追加する
    public boolean insert(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBManager.getConnection();
            String sql = "INSERT INTO users(name, pass, email) VALUES(? , ? , ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getPass());
            ps.setString(3, user.getEmail());
            int r = ps.executeUpdate();
            return (r == 1);
        } finally {
            if (ps != null) try { ps.close(); } catch(Exception e){}
            if (conn != null) try { conn.close(); } catch(Exception e){}
        }
    }

    
    
}

    
    
   
