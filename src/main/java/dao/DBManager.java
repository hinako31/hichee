package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

	private final static String JDBC_URL="jdbc:mysql://localhost:3306/hichee?useSSL=false&serverTimezone=UTC";
	private final static String DB_USER="root";
	private final static String DB_PASS="root";


    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // JDBC 8以降の正しいドライバークラス名
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
    }
}