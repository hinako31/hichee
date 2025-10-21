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
            String sql ="SELECT id,name,email,pass FROM users WHERE email=? AND pass=?";
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
                String name = rs.getString("name");
                String pass = rs.getString("pass");
                
          
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
        User user = null;
        String sql = "SELECT name, email, pass FROM users WHERE email = ?";
        try {
            conn = DBManager.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {//emailが存在したら
            	int id = rs.getInt("id");
                String name = rs.getString("name");
                String pass = rs.getString("pass");

                user = new User(id, name, email, pass); 
            } 
        } finally{
            // ResultSet を閉じる
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // PreparedStatement を閉じる
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // Connection を閉じる
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return user;
    }
    //会員登録内容をテーブルに追加する
    public boolean insert(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBManager.getConnection();
            String sql = "INSERT INTO users(name, email, pass) VALUES(? , ? , ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPass());
            int r = ps.executeUpdate();
            return (r == 1);
        } finally {
            if (ps != null) try { ps.close(); } catch(Exception e){}
            if (conn != null) try { conn.close(); } catch(Exception e){}
        }
    }
    //ユーザー情報変更
    public boolean update(User user) throws SQLException {
        String sql = "UPDATE users SET name = ?, email = ?, pass = ? WHERE id = ?";
        try (Connection conn = DBManager.getConnection();	
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        	
        	 conn.setAutoCommit(false);  // 明示的にOFFにするなら（OFFなら不要）
        	 
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPass());
            stmt.setInt(4, user.getId());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                conn.rollback();  // 更新されなかったらロールバック
                return false;
            }

            conn.commit();  // 明示的にコミットする

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
	  
    //退会時ユーザー削除
    public boolean delete(int userId) {
    	
    	String sql="DELETE FROM users WHERE id = ?";
    	try (
                Connection conn = DBManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
            ) {
    		pstmt.setInt(1,userId);
    		int rowAffected = pstmt.executeUpdate();
    	
    		return rowAffected >0;
    	}catch (SQLException e) {
            System.out.println("SQLエラー: " + e.getMessage());
    		e.printStackTrace();
    		return false;
    	}
    }
}

    
    
   
