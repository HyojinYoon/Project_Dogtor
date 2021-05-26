<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "memberDAO.MemberDAO" %>
<%@ page import = "memberDTO.MemberDTO" %>

<%
	String pw = request.getParameter("pw");
	String id = (String)session.getAttribute("memId");

	
	MemberDAO dao = new MemberDAO();
	boolean mem_Result = dao.deleteMember(id, pw);
	boolean pet_Result = dao.deletePet(id);
	if(mem_Result && pet_Result)
	{	session.invalidate();	%>
		<script>
			alert("탈퇴되었습니다.");
			window.location = "/dogtor/Member/memberForm/dogtorHome.jsp";
		</script>
	<%}else{%>
		<script>
			alert("비밀번호를 확인해주세요");
			history.go(-1);
		</script>
	<%}%>