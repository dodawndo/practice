package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtil {
	public static Connection getConnection() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/Cono";
		String user = "root";
		String password = "1234";
		
		Connection con = null;
		
		try { 
			Class.forName(driver); 
			
			
			con = DriverManager.getConnection(url, user, password);
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			System.out.println("드라이버 로드 실패!");
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("DB 접속 실패!");
		}
		
		
		return con;
		
	}
	
	
	public static void close(Connection con) {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void close(PreparedStatement pstmt) {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
		
	
	public static void close(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 3. 트랜잭션 처리에 필요한 commit, rollback 작업을 수행하기 위한 메서드 정의
		// => 단, Connection 객체에 대해 Auto Commit 기능 해제 필수
		// => 파라미터 : Connection 객체(con)
		public static void commit(Connection con) {
			try {
				con.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public static void rollback(Connection con) {
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
}















