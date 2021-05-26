<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ page import="dogtor.doctorDAO" %>
<%@ page import="dogtor.doctorDTO" %>
    
<%
	String pw = request.getParameter("pw");
	String id = (String)session.getAttribute("docId");
	
	doctorDAO dao = new doctorDAO();
	boolean result = dao.deleteDoctor(id, pw);
	
	if(result){
		
		session.invalidate(); %>
		
		<script>
			alert("탈퇴되었습니다.");
			window.location="/dogtor/Doctor/DoctorHome.jsp";
		</script>		
<%	}else{ %>

	<script>
		alert("비밀번호가 올바르지 않습니다.");
		history.go(-1);
	</script>
		
	<%}%>