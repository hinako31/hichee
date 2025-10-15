package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Diary;

public class DiaryDAO {

    private final String JDBC_URL = "jdbc:mysql://localhost:3306/sampledb";
    private final String DB_USER = "root";
    private final String DB_PASS = "password";

    //ログインしたときのDiary記録取得
    public List<Diary> findByUserId(int user_id) {
        List<Diary> diaryList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "SELECT id, name, period_year, period_month, user_id, area_id, file_name, file_path, review, created_at, updated_at FROM diaries WHERE user_id = ? ORDER BY created_at DESC";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, user_id);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Diary diary = new Diary();
                    diary.setId(rs.getInt("id"));
                    diary.setUserid(rs.getInt("user_id"));
                    diary.setName(rs.getString("name"));
                    diary.setReview(rs.getString("review"));
                    diary.setPeriod_year(rs.getInt("period_year"));
                    diary.setPeriod_month(rs.getInt("period_month"));
                    diary.setArea_id(rs.getInt("area_id"));
                    diary.setFile_name(rs.getString("file_name"));
                    diary.setFile_path(rs.getString("file_path"));
                    diary.setCreated_at(rs.getTimestamp("created_at"));
                    diary.setUpdated_at(rs.getTimestamp("updated_at"));

                    diaryList.add(diary);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return diaryList;
    }

    //退会時お気に入り削除
	public boolean deleteDiary(int userId) {
    	String sql = "DELETE FROM diaries WHERE user_id = ?";
    	try (Connection conn = DBManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
    		){
    		 ps.setInt(1, userId);
             ps.executeUpdate();
             return true; // 件数は気にしない（0でもOK）
    	
    	}catch (SQLException e) {
            System.out.println("SQLエラー: " + e.getMessage());
    		e.printStackTrace();
    		return false;
    	}
    }
	
}