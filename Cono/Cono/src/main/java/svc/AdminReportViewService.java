package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.AdminDAO;
import vo.AdminQNADTO;
import vo.AdminReportDTO;

public class AdminReportViewService {

	public AdminReportDTO getReport(String report_idx, String sId) {
		System.out.println("AdminReportViewService - getReport()");

		AdminReportDTO adminReportDTO = null;

		Connection con = getConnection(); // static import 적용되어 있을 경우

		AdminDAO adminDAO = AdminDAO.getInstance();

		adminDAO.setConnection(con);

		adminReportDTO = adminDAO.selectReportArticle(report_idx, sId);

		close(con);

		return adminReportDTO;
	}

}
