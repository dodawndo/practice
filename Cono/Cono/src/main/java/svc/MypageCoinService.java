package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import vo.CoinDTO;

public class MypageCoinService {
	public ArrayList<CoinDTO> getArticleList(String sId) {
		System.out.println("Purchased item - getListCount");
		
		ArrayList<CoinDTO> articleList = new ArrayList<CoinDTO>();
		Connection con = getConnection();

		
		close(con);
		
		return articleList;
	}
}
