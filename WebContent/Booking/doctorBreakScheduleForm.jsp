<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("UTF-8");
	//code, 날짜, 시간 받아옴
	String code = request.getParameter("code");
	String reserve_date = request.getParameter("reserve_date");
	String reserve_time = request.getParameter("reserve_time");
	
%>
<center><img src="/dogtor/save/doctorBreakReason.png" width="600" /><br />
<br />
외출 시간 : <%=reserve_date %>, <%=reserve_time %>
<br /><br />
<form action="doctorBreakSchedulePro.jsp" method="post">
	<input type="hidden" name="code" value="<%=code%>" />
	<input type="hidden" name="reserve_date" value="<%=reserve_date%>" />
	<input type="hidden" name="reserve_time" value="<%=reserve_time%>" />
	외출 상세 이유<br /><br />
		<textarea name="reason" cols="50" rows="7" placeholder="외출이유를 상세히 입력해주세요."></textarea><br />
		<br />
	<input type="submit" value="외출신청" />
</form>


</center>