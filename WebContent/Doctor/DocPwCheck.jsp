<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dogtor.ValueCheck" %>

<center>
<%
	String pw = request.getParameter("pw");
	String pc = request.getParameter("pwCheck");
	ValueCheck vc = new ValueCheck();
	
	if(vc.checkNull(pw) && vc.checkNull(pc))
	{
		boolean cbl = vc.charBlank(pw);
		boolean cl = vc.charLength(pw,8,12);
		boolean cb = vc.charBig(pw);
		boolean cs = vc.charSmall(pw);
		boolean cn = vc.charNumber(pw);
		boolean csp = vc.charSpecial(pw,33,64,42);
		
		if(cbl && cl && (cb || cs) && cn && csp)
		{
			if(pc.equals(pw))
			{%><h3>pw가 일치합니다.</h3><%}
			else
			{%><h3>pw가 일치하지않습니다.</br> 다시 입력해주세요!</h3><%}
		}
		else
		{%><h3>입력하신 pw는 조건에 맞지 않습니다.</br> 다시 입력해주세요!</h3><%}
	}
	else
	{%><h3>pw를 입력해주세요.</h3> <%}%>
	
	<input type="button" value="닫기" onclick="self.close();" />
</center>