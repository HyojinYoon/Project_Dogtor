<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dogtor.DTO" %>
<%@ page import="dogtor.DAO" %> 
<% 	request.setCharacterEncoding("UTF-8");
	String code = request.getParameter("code");
	String reserve_date = request.getParameter("reserve_date");
	String reserve_time = request.getParameter("reserve_time");
	String reason = request.getParameter("reason");
	
	DAO dao = new DAO();
	dao.Breaktime(code, reserve_date, reserve_time, reason);
%>
	<script>
		window.location = "/dogtor/Booking/doctorConfirmSchedulePro.jsp?code=<%=code%>&reserve_date=<%=reserve_date%>";
	  	alert("외출신청이 완료 되었습니다.")
	</script>
	
<h1> 휴가이유, 스케쥴 정보 받아서 해당 내용 업데이트 페이지</h1>