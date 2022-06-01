package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.AdminQNASearchService;
import svc.AdminReportSearchService;
import vo.ActionForward;
import vo.AdminQNADTO;
import vo.AdminReportDTO;

public class AdminReportSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminReportSearchAction");

		ActionForward forward = null;

		String report_idx = request.getParameter("report_idx");
				
		String searchType = request.getParameter("searchType");
		String search = request.getParameter("search");

		AdminReportSearchService service = new AdminReportSearchService();
		ArrayList<AdminReportDTO> reportList = service.searchReport(search, searchType, report_idx);

		request.setAttribute("reportList", reportList);
		forward = new ActionForward();
		forward.setPath("admin_report_search.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
