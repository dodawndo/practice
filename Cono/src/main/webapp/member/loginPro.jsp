<%@page import="dao.MemberDAO"%>
<%@page import="vo.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String id = request.getParameter("id");
String pass = request.getParameter("pass");

MemberDTO member = new MemberDTO();
member.setId(id);
member.setPass(pass);

MemberDAO memberDAO = new MemberDAO();
boolean isLoginSuccess = memberDAO.checkUser(member);

if(isLoginSuccess) { 
	session.setAttribute("sId", id);
	response.sendRedirect("../user/user.jsp");
} else {
%>
	<script>
		alert("아이디 또는 패스워드 틀림!");
		history.back()
	</script>
	<%
}
%>
















