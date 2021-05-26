<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="memberDAO.MemberDAO" %>
<%@ page import="memberDTO.MemberDTO" %>


<%
	String id = request.getParameter("id");

	MemberDAO dao = new MemberDAO();
	dao.adminDeleteMember(id);
	dao.deletePet(id);
%>
	<script>
		alert("강제 탈퇴 되었습니다.")
		window.location="/dogtor/Member/memberForm/adminMemberForm.jsp";
	</script>
