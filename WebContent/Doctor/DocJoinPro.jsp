<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dogtor.doctorDAO" %>

<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean class="dogtor.doctorDTO" id="dto" />
<jsp:setProperty name="dto" property="*" />

<%
	doctorDAO dao = new doctorDAO();
	dao.insertDoctor(dto);
%>
<script>
alert("직원등록이 완료되었습니다. 로그인해주세요!");
window.location="/dogtor/Doctor/DocLogin.jsp";
</script>