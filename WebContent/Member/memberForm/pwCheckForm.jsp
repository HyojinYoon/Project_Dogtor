<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="check.ValueCheck" %>

<%//세션검사 지우면 안됩니다. 헤더에서 사용중입니다. 만약 본인 내용에 세션 id 정검이 있을 경우 그 내용을 지워주세요.
    String id = (String)session.getAttribute("memId");
    int status = 0;
    if(id != null){
    	status= (int)session.getAttribute("memStatus");
	 }%>
	 



<script>
	if(opener == null)
	{
		alert("잘못된 경로입니다.");
		window.location = '/dogtor/Member/memberForm/dogtorHome.jsp';
	}

	function usePw()
	{
		if(opener.document.join.pwDuplication)
		{	opener.document.join.pwDuplication.value = "pwCheck";	}
		
		if(opener != null)
		{
			opener.pwCheckForm = null;
			self.close();
		}
	}
</script>


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
			{%> 
				<h3>비밀번호가 일치합니다.</h3> 
				<input type="button" value="사용하기" onclick="usePw();" />
		  <%}
			else
			{%> 
				<h3>비밀번호가 일치하지않습니다.</h3><h3> 다시 입력해주세요.</h3> 
				<input type="button" value="닫기" onclick="window.close();" />
		  <%}
		}
		else
		{%> 
			<h3>입력하신 비밀번호는 조건에 맞지 않습니다.</h3><h3>다시 입력해주세요.</h3>	
			<input type="button" value="닫기" onclick="window.close();" />	
	  <%}
	}
	else
	{%> 
		<h3>비밀번호를 입력해주세요.</h3> 
		<input type="button" value="닫기" onclick="window.close();" />
  <%}%>
</center>