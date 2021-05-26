<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="dogtor.doctorDAO" %>
<%@ page import="dogtor.doctorDTO" %>

<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean class="dogtor.doctorDTO" id="dto" />
 <jsp:setProperty name="dto" property="pw" param="pw" />
 <jsp:setProperty name="dto" property="birth" param="birth" />
 <jsp:setProperty name="dto" property="phone" param="phone" />


<%
	String id = (String)session.getAttribute("docId");
	dto.setId(id);
	
	
	doctorDAO dao = new doctorDAO();
	dao.updateDoctor(dto);

%>

<script>
	alert("수정이 완료되었습니다.");
	history.go(-1);

</script>