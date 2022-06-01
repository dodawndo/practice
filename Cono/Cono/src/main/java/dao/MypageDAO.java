package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MypageDAO {
	private static MypageDAO instance = new MypageDAO();
	private MypageDAO() {}
	public static MypageDAO getInstance() {
		return instance;
	};
	Connection con;
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	// coupon
	public ArrayList<ArrayList<String>> selectCouponList(String sId) {
		System.out.println("DAO - selectCouponList");
		
		ArrayList<ArrayList<String>> articleList = new ArrayList<ArrayList<String>>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT coupon_name, coupon_price "
					+ "FROM coupon c "
					+ "WHERE c.member_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ArrayList<String> article = new ArrayList<String>();
				article.add(rs.getString("coupon_name"));
				article.add(rs.getString("coupon_price"));
				articleList.add(article);
			}
			
		} catch (SQLException e) {
			System.out.println("sql 구문오류 - selectFollowerList");
			e.printStackTrace();
		}
		
		return articleList;
	}
	
}
