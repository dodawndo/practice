package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.AdminReportModifyProService;
import vo.ActionForward;
import vo.AdminQNADTO;
import vo.AdminReportDTO;

public class AdminReportModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminReportModifyProAction");

		ActionForward forward = null;

		String report_idx = request.getParameter("report_idx");
		String report_type = request.getParameter("report_type");
		HttpSession session= request.getSession();
	    String sId = (String)session.getAttribute("sId");
		String report_subject = request.getParameter("report_subject");
		String report_content = request.getParameter("report_content");

		AdminReportModifyProService service = new AdminReportModifyProService();

		AdminReportDTO report = new AdminReportDTO();
		report.setReport_idx(report_idx);
		report.setReport_type(report_type);
		report.setMember_id(sId);
		report.setReport_subject(report_subject);
		report.setReport_content(report_content);

		
		boolean isModifySuccess = service.modifyReport(report);

		if (!isModifySuccess) { // 수정 실패
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정 실패!')");
			out.println("history.back()");
			out.println("</script>");

		} else {

			forward = new ActionForward();
//				forward.setPath("AdminNoticeList.admin?page=" + request.getParameter("page"));
			forward.setPath("AdminReportList.admin");
			forward.setRedirect(true);

		}

		return forward;
	}

}
