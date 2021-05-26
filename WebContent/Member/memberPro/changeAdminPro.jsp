<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="memberDAO.MemberDAO" %>
<%@ page import="memberDTO.MemberDTO" %>


<%
	String id = request.getParameter("id");

	MemberDAO dao = new MemberDAO();
	dao.adminChangeStatus(id);

%>
	<script>
		alert("관리자로 변경되었습니다.")
		window.location="/dogtor/Member/memberForm/adminForm.jsp";
	</script>
    