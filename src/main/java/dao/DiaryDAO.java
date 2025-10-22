package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Diary;

public class DiaryDAO {

    //ログインしたときのDiary記録取得
    public List<Diary> findByUserId(int user_id) throws SQLException {
        List<Diary> diaryList = new ArrayList<>();

        try(Connection conn = DBManager.getConnection()) {
            String sql = "SELECT id, name, period_year, period_month, user_id, area_id, file_name, file_path, review, created_at, updated_at FROM diaries WHERE user_id = ? ORDER BY created_at DESC";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();
            pStmt.setInt(1, user_id);
             
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
            }catch (SQLException e) {
            System.err.println("DBからのエリア取得に失敗: " + e.getMessage());
            e.printStackTrace();
            throw e; 
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
	
	
	//new Cheese作成時の登録指示
	public boolean insertDiary(Diary diary) {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    boolean result = false;

	    try {
	        conn = DBManager.getConnection(); // ここだけでOK

	        String sql = "INSERT INTO diaries (name, period_year, period_month, user_id, area_id, file_name, file_path, review, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";

	        ps = conn.prepareStatement(sql);

	        ps.setString(1, diary.getName());

	        if (diary.getPeriod_year() != null) {
	            ps.setInt(2, diary.getPeriod_year());
	        } else {
	            ps.setNull(2, java.sql.Types.INTEGER);
	        }

	        if (diary.getPeriod_month() != null) {
	            ps.setInt(3, diary.getPeriod_month());
	        } else {
	            ps.setNull(3, java.sql.Types.INTEGER);
	        }

	        ps.setInt(4, diary.getUserid());
	        ps.setInt(5, diary.getArea_id());
	        ps.setString(6, diary.getFile_name());
	        ps.setString(7, diary.getFile_path());
	        ps.setString(8, diary.getReview());

	        int count = ps.executeUpdate();
	        if (count == 1) {
	            result = true;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBUtil.close(ps);
	        DBUtil.close(conn);
	    }

	    return result;
	}
	
	
	public List<model.Diary> searchByConditions(int userId, String name, String periodYearStr, String periodMonthStr, String areaIdStr) {
	    List<model.Diary> diaryList = new ArrayList<>();

	    StringBuilder sql = new StringBuilder("SELECT * FROM diaries WHERE user_id = ?");
	    List<Object> params = new ArrayList<>();
	    params.add(userId);

	    // 店名あいまい検索
	    if (name != null && !name.trim().isEmpty()) {
	        sql.append(" AND name LIKE ?");
	        params.add("%" + name.trim() + "%");
	    }

	    // 記念年条件
	    if (periodYearStr != null && !periodYearStr.isEmpty()) {
	        if ("unknown".equals(periodYearStr)) {
	            sql.append(" AND period_year IS NULL");
	        } else {
	            sql.append(" AND period_year = ?");
	            params.add(Integer.parseInt(periodYearStr));
	        }
	    }

	    // 記念月条件
	    if (periodMonthStr != null && !periodMonthStr.isEmpty()) {
	        if ("unknown".equals(periodMonthStr)) {
	            sql.append(" AND period_month IS NULL");
	        } else {
	            sql.append(" AND period_month = ?");
	            params.add(Integer.parseInt(periodMonthStr));
	        }
	    }

	    // 場所条件
	    if (areaIdStr != null && !areaIdStr.isEmpty()) {
	        if ("unknown".equals(areaIdStr)) {
	            sql.append(" AND area_id IS NULL");
	        } else {
	            sql.append(" AND area_id = ?");
	            params.add(Integer.parseInt(areaIdStr));
	        }
	    }

	    sql.append(" ORDER BY created_at DESC");

	    try (Connection conn = DBManager.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql.toString())) {

	        // パラメータセット
	        for (int i = 0; i < params.size(); i++) {
	            Object param = params.get(i);
	            if (param instanceof String) {
	                ps.setString(i + 1, (String) param);
	            } else if (param instanceof Integer) {
	                ps.setInt(i + 1, (Integer) param);
	            }
	        }

	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Diary diary = new Diary();
	                diary.setId(rs.getInt("id"));
	                diary.setUserid(rs.getInt("user_id"));
	                diary.setName(rs.getString("name"));
	                diary.setReview(rs.getString("review"));
	                diary.setPeriod_year(rs.getObject("period_year") != null ? rs.getInt("period_year") : null);
	                diary.setPeriod_month(rs.getObject("period_month") != null ? rs.getInt("period_month") : null);
	                diary.setArea_id(rs.getObject("area_id") != null ? rs.getInt("area_id") : null);
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

	//詳細画面に行くときのdiary情報取得の指示
	public Diary findById(int id) {
	    Diary diary = null;

	    String sql = "SELECT * FROM diaries WHERE id = ?";

	    try (Connection conn = DBManager.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            diary = new Diary();
	            diary.setId(rs.getInt("id"));
	            diary.setName(rs.getString("name"));
	            diary.setReview(rs.getString("review"));
	            diary.setUserid(rs.getInt("user_id"));
	            diary.setPeriod_year((Integer) rs.getObject("period_year"));
	            diary.setPeriod_month((Integer) rs.getObject("period_month"));
	            diary.setArea_id(rs.getInt("area_id"));
	            diary.setFile_name(rs.getString("file_name"));
	            diary.setFile_path(rs.getString("file_path"));
	            diary.setCreated_at(rs.getTimestamp("created_at"));
	            diary.setUpdated_at(rs.getTimestamp("updated_at"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return diary;
	}
	


}