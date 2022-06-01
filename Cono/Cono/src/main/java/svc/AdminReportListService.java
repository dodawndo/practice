package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminDAO;
import vo.AdminQNADTO;
import vo.AdminReportDTO;

public class AdminReportListService {
	public int getListCount() {
		System.out.println("AdminReportListService - getListCount()");

		int listCount = 0;

		Connection con = getConnection(); // static import 적용되어 있을 경우

		AdminDAO adminDAO = AdminDAO.getInstance();

		adminDAO.setConnection(con);

		listCount = adminDAO.selectReportListCount();

		close(con);

		return listCount;
	}

	public ArrayList<AdminReportDTO> getArticleList(int pageNum, int listLimit) {
		System.out.println("AdminReportListService - getArticleList()");

		ArrayList<AdminReportDTO> reportList = null;

		Connection con = getConnection(); // static import 적용되어 있을 경우

		AdminDAO adminDAO = AdminDAO.getInstance();

		adminDAO.setConnection(con);

		reportList = adminDAO.selectReportList(pageNum, listLimit);

		close(con);

		return reportList;
	}
}
