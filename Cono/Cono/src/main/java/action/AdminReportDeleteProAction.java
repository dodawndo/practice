package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.AdminQNADeleteProService;
import svc.AdminReportDeleteProService;
import vo.ActionForward;

public class AdminReportDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminReportDeleteProAction");

		ActionForward forward = null;

		String report_idx = request.getParameter("report_idx");
		String pageNum = request.getParameter("page");

		AdminReportDeleteProService service = new AdminReportDeleteProService();

		boolean isDeleteSuccess = service.removeArticle(report_idx);
		if (!isDeleteSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
//			forward.setPath("AdminReportList.admin?page=" + pageNum);
			forward.setPath("AdminReportList.admin");
			forward.setRedirect(true);
		}

		return forward;
	}

}
