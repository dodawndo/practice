package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ItemImgListService;
import vo.ActionForward;
import vo.ImgDTO;

public class ItemDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ItemDetailAction");
		ActionForward forward = null;
		
		String item_idx = request.getParameter("item_idx");
		
		ItemImgListService service = new ItemImgListService();
		ArrayList<ImgDTO> imgList = service.selectImgList(item_idx);
		
		return forward;
	}

}
