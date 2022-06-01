package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminDAO;
import vo.AdminNoticeDTO;
import vo.AdminReportDTO;

public class AdminReportSearchService {

	public ArrayList<AdminReportDTO> searchReport(String search, String searchType, String report_idx) {
		System.out.println("AdminReportSearchService");
		ArrayList<AdminReportDTO> reportList = null;

		Connection con = getConnection();

		AdminDAO adminDAO = AdminDAO.getInstance();

		adminDAO.setConnection(con);

		reportList = adminDAO.searchReportList(search, searchType, report_idx);

		close(con);

		return reportList;
	}

}
