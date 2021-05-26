<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dogtor.DTO" %>
<%@ page import="dogtor.DAO" %> 
<% 	//외출 신청 취소 페이지
	request.setCharacterEncoding("UTF-8");
	String code = request.getParameter("code");
	String reserve_date = request.getParameter("reserve_date");
	String reserve_time = request.getParameter("reserve_time");
	DAO dao = new DAO();
	dao.BreaktimeCancle(code, reserve_date, reserve_time);
	
%>	
	<h1>외출 취소 페이지</h1>

	<script>
		window.location = "/dogtor/Booking/doctorConfirmSchedulePro.jsp?code=<%=code%>&reserve_date=<%=reserve_date%>";
	  	alert("외출취소 신청이 완료 되었습니다.")
	</script>
	
