<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dogtor.doctorDAO" %>
<%@ page import="dogtor.ValueCheck" %>

<% request.setCharacterEncoding("UTF-8");	%>



<center>
<%
	String code = request.getParameter("code");
	doctorDAO dao = new doctorDAO();
	boolean result = dao.codeCheck(code);
	ValueCheck vc = new ValueCheck();
	boolean size = vc.charLength(code, 1, 1);
	boolean cb = vc.charSmall(code);
	
	if(size && cb)
	{
		if(result)
		{%> <h3>입력하신 [ <font color=blue><%=code %></font> ]는 사용 가능합니다.</h3><%}
		else
		{%>	<h3>이미 사용중인 코드입니다.</h3> <%} 
	}
	else
	{%><h3>입력조건에 맞지 않습니다. 다시 입력해주세요!</h3>	<%}%>
	
	<input type="button" value="닫기" onclick="self.close();" />

	</center>
	
	