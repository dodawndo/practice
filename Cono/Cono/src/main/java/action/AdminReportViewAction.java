package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.AdminNoticeViewService;
import svc.AdminReportViewService;
import vo.ActionForward;
import vo.AdminNoticeDTO;
import vo.AdminReportDTO;

public class AdminReportViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminReportViewAction");

		ActionForward forward = null;
		String pageNum = request.getParameter("page");
		String report_idx = request.getParameter("report_idx");
		String sId = request.getParameter("sId");

		AdminReportViewService service = new AdminReportViewService();
		AdminReportDTO report = service.getReport(report_idx,sId);

		request.setAttribute("report", report);
		forward = new ActionForward();
//		forward.setPath("AdminNoticeView.admin?page=" + request.getParameter("page"));
//		forward.setPath("AdminNoticeView.admin?page=" + pageNum);
		forward.setPath("admin_report_view.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
