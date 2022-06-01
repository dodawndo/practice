package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MypageCoinService;
import vo.ActionForward;

public class MypageCoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Coin Action");
		ActionForward forward = null;
		HttpSession session= request.getSession();
		String sId = (String)session.getAttribute("sId");
		
		MypageCoinService service = new MypageCoinService();
		
		forward = new ActionForward();
		forward.setPath("./mypage/mypage_coin.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
