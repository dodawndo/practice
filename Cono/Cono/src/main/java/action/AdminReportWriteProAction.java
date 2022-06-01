package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.AdminQNAWriteProService;
import svc.AdminReportWriteProService;
import vo.ActionForward;
import vo.AdminQNADTO;
import vo.AdminReportDTO;

public class AdminReportWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminReportWriteProAction");

		ActionForward forward = null;
		HttpSession session= request.getSession();
		String sId = (String)session.getAttribute("sId");
		AdminReportDTO adminReportDTO = new AdminReportDTO();

		String report_idx = request.getParameter("report_idx");
		String member_id = request.getParameter("member_id");
		String report_type = request.getParameter("report_type");
		String report_subject = request.getParameter("report_subject");
		String report_content = request.getParameter("report_content");
		String report_date = request.getParameter("report_date");
		String report_status = request.getParameter("report_status");

		adminReportDTO.setReport_idx(report_idx);
		adminReportDTO.setMember_id(member_id);
		adminReportDTO.setReport_type(report_type);
		adminReportDTO.setReport_subject(report_subject);
		adminReportDTO.setReport_content(report_content);
		adminReportDTO.setReport_date(report_date);

		AdminReportWriteProService service = new AdminReportWriteProService();
		boolean isWriteSuccess = service.registArticle(adminReportDTO,sId);

		if (!isWriteSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글쓰기 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setPath("AdminReportList.admin");
			forward.setRedirect(true);
		}

		return forward;
	}

}
