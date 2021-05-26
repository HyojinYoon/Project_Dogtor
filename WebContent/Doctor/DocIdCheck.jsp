<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="dogtor.doctorDAO" %>
<%@ page import="dogtor.doctorDTO" %>
<%@ page import="dogtor.ValueCheck" %>


<center>
<%
	String id = request.getParameter("id");
	
	doctorDAO dao = new doctorDAO();
	doctorDTO dto = new doctorDTO();
	ValueCheck vc = new ValueCheck();
	
	boolean cs = vc.charSmall(id);
	boolean cn = vc.charNumber(id);
	boolean cl = vc.charLength(id,4,8);
	boolean cbId = vc.charBlank(id);
	
	dto.setId(id);
	boolean result = dao.idCheck(id);
	
	if(vc.checkNull(id))
	{
		
		if(cbId && cs && cn && cl )
		{
			if(result)
			{%> <h3>입력하신 [ <font color = blue><%=id%></font> ]는 사용가능한 id입니다.</h3><%}
			else
			{%> <h3>이미 존재하는 id입니다.</h3>	<%}
		}
		else
		{%> <h3>입력조건에 맞지 않습니다.</br> 다시 입력해주세요!</h3> <%}
	}
	else
	{%> <h3>id를 입력해주세요.</h3> <%}%>

	<input type="button" value="닫기" onclick="self.close();" />
	
</center>

   
    