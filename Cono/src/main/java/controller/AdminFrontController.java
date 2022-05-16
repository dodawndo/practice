package controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.AdminNoticeListAction;
import action.AdminWriteProAction;
import vo.ActionForward;

@WebServlet("*.admin")
public class AdminFrontController extends HttpServlet {
	

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardFrontController");
		
		request.setCharacterEncoding("UTF-8");
		
		String command = request.getServletPath();
		System.out.println("command : " + command);
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/AdminNoticeList.admin")) {
			action = new AdminNoticeListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/AdminWriteForm.admin")) {
			forward = new ActionForward();
			forward.setPath("./admin/admin_notice_write.jsp");
			forward.setRedirect(false); // Dispatcher 방식(생략 가능)
			
		} else if(command.equals("/AdminWritePro.admin")) {
			action = new AdminWriteProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		
		} 
		
		//==============================================================================================
		
		if(forward != null) { // ActionForward 객체가 비어있지 않을 경우
			// Redirect 방식 vs Dispatcher 방식 판별하여 각 방식으로 포워딩
			if(forward.isRedirect()) { // Redirect 방식
				response.sendRedirect(forward.getPath());
			} else { // Dispatcher 방식
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		} else {
			// ActionForward 객체가 비어있을 경우 메세지 출력(임시)
			System.out.println("ActionForward 객체가 null 입니다!");
		}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
