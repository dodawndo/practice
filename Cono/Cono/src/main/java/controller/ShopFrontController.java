package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ItemDetailAction;
import action.ItemHideAction;
import action.ItemMngAction;
import action.ItemUploadProAction;
import vo.ActionForward;

@WebServlet("*.shop")
public class ShopFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ItemFrontController");
		
		request.setCharacterEncoding("UTF-8");
		
		String command = request.getServletPath();
		System.out.println("command : " + command);
		
		Action action = null;
		ActionForward forward = null;
		
		// ---------------------------------------------------------------------------------------
		
		if(command.equals("/ItemUploadPro.shop")) {	// 상품 등록
			action = new ItemUploadProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/ItemMng.shop")) {
			request.getAttribute("member_id");
			action = new ItemMngAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/Myshop.shop")) {
			forward = new ActionForward();
			forward.setPath("myshop/my_page.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/ItemUploadForm.shop")) {
			forward = new ActionForward();
			forward.setPath("myshop/item_upload.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/ItemHide.shop")) {
			action = new ItemHideAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/ItemDetail.shop")) {
			action = new ItemDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// ---------------------------------------------------------------------------------------
		
		if(forward != null) {	
			if(forward.isRedirect()) {  // Redirect 방식
				response.sendRedirect(forward.getPath());
			} else {	// Dispatcher 방식
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		} else {
			System.out.println("ActionForward 객체가 null 입니다");
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
