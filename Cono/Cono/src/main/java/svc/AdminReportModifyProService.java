package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.AdminDAO;
import vo.AdminReportDTO;

public class AdminReportModifyProService {

	public boolean modifyReport(AdminReportDTO report) {
		System.out.println("AdminReportModifyProService-modifyReport");
		boolean isModifySuccess = false;

		Connection con = getConnection();

		AdminDAO adminDAO = AdminDAO.getInstance();

		adminDAO.setConnection(con);

		int modifyCount = adminDAO.modifyReport(report);

		System.out.println(modifyCount);
		if (modifyCount > 0) {
			commit(con);
			isModifySuccess = true;
		} else {
			rollback(con);
		}
		close(con);

		return isModifySuccess;

	}

}
