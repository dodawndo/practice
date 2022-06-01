package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.AdminDAO;
import vo.AdminReportDTO;

public class AdminReportWriteProService {


	public boolean registArticle(AdminReportDTO adminReportDTO, String sId) {
		System.out.println("AdminReportWriteProService - registArticle()");

		boolean isWriteSuccess = false;

		Connection con = getConnection(); // static import 적용되어 있을 경우

		AdminDAO adminDAO = AdminDAO.getInstance();

		adminDAO.setConnection(con);

		int insertCount = adminDAO.insertArticle(adminReportDTO,sId);

		if (insertCount > 0) { // 작업 성공 시
			commit(con);

			isWriteSuccess = true;
		} else { // 작업 실패 시
			rollback(con);
		}

		close(con);

		return isWriteSuccess;
	
	}
}
