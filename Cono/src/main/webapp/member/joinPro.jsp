<%@page import="dao.MemberDAO"%>
<%@page import="vo.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");

String id = request.getParameter("id");
String pass = request.getParameter("pass");
String email = request.getParameter("email");
String nick = request.getParameter("nick");
String phone = request.getParameter("phone");

MemberDTO member = new MemberDTO();
member.setId(id);
member.setPass(pass);
member.setEmail(email);
member.setNick(nick);
member.setPhone(phone);

MemberDAO memberDAO = new MemberDAO();

int insertCount = memberDAO.insertMember(member);

if(insertCount > 0) {
	response.sendRedirect("joinSuccess.jsp");
} else {
%>
	<script>
		alert("회원 가입 실패!");
		history.back();
	</script>
	<%
}

%>













