package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.AdminDAO;

public class AdminReportDeleteProService {

	public boolean removeArticle(String report_idx) {
		System.out.println("AdminReportDeleteProService - removeArticle()");
		boolean isDeleteSuccess = false;

		Connection con = getConnection(); 

		AdminDAO adminDAO = AdminDAO.getInstance();

		adminDAO.setConnection(con);

		int deleteCount = adminDAO.deleteReportArticle(report_idx);

		if (deleteCount > 0) {
			commit(con);
			isDeleteSuccess = true;
		} else {
			rollback(con);
		}

		close(con);

		return isDeleteSuccess;
	}

}
