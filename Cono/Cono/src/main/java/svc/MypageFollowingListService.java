package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.FollowDAO;

public class MypageFollowingListService {
	public ArrayList<ArrayList<String>> getArticleList(String sId) {
		System.out.println("MypageFollowingListService");
		
		ArrayList<ArrayList<String>> articleList = new ArrayList<ArrayList<String>>();
		Connection con = getConnection();
		FollowDAO followDAO = FollowDAO.getInstance();
		followDAO.setConnection(con);
		
		 articleList = followDAO.selectFollowingList(sId);
		
		close(con);
		
		return articleList;
	}
}
