package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.AdminQNAViewService;
import svc.AdminReportViewService;
import vo.ActionForward;
import vo.AdminQNADTO;
import vo.AdminReportDTO;

public class AdminReportModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminReportModifyFormAction");

		ActionForward forward = null;
		HttpSession session = request.getSession();
		String sId = (String) session.getAttribute("sId");
		String report_idx = request.getParameter("report_idx");

		AdminReportViewService service = new AdminReportViewService();
		AdminReportDTO report = service.getReport(report_idx,sId);

		request.setAttribute("report", report);
		forward = new ActionForward();
		forward.setPath("admin_report_modify.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
