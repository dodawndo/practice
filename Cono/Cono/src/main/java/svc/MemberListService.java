package svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminDAO;
import vo.MemberDTO;

import static db.JdbcUtil.*;

public class MemberListService {

	public int getListCount() {
		System.out.println("MemberListService - getListCount()");
		
		int listCount = 0;
		
		Connection con = getConnection();
		
		AdminDAO  adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		//총 게시물 수 조회
		listCount = adminDAO.selectListCount();
		
		close(con);
		
		return listCount;
	}

	public ArrayList<MemberDTO> getMemberList(int pageNum, int listLimit) {
		System.out.println("MemberListService - getMemberList()");
		
		ArrayList<MemberDTO> memberList  = null;
		
		Connection con = getConnection();
		
		AdminDAO  adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		// 게시물 목록 조회
		memberList = adminDAO.selectAllMember(pageNum, listLimit);
		
		close(con);
		
		return memberList;
	}

}
