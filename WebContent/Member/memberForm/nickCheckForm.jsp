<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="memberDAO.MemberDAO" %>
<%@ page import="check.ValueCheck" %>

<% request.setCharacterEncoding("UTF-8");	%>

<%//세션검사 지우면 안됩니다. 헤더에서 사용중입니다. 만약 본인 내용에 세션 id 정검이 있을 경우 그 내용을 지워주세요.
    String id = (String)session.getAttribute("memId");
    int status = 0;
    if(id != null){
    	status= (int)session.getAttribute("memStatus");
	 }%>
  
<script type="text/javascript">
	if(opener == null)
	{
		alert("잘못된 경로입니다.");
		window.location = '/dogtor/Member/memberForm/dogtorHome.jsp';
	}

	function useNick()
	{
		opener.document.join.nickDuplication.value = "nickCheck";
	
		if(opener != null)
		{
			opener.nickCheckForm = null;
			self.close();
		}
	}
</script>

<center>
<%
	String nick = request.getParameter("nick");
	MemberDAO dao = new MemberDAO();
	boolean result = dao.nickCheck(nick);
	ValueCheck vc = new ValueCheck();
	boolean size = vc.charLength(nick, 2, 7);
	
	if(size)
	{
		if(result)
		{%> 
			<h3>입력하신</h3> <h1>'<%=nick %>'</h1><h3>은/는 사용 가능합니다.</h3>	
			<input type="button" value="사용하기" onclick="useNick()" />
	  <%}
		else
		{%>	
			<h3>이미 사용중인 닉네임입니다.</h3> 
			<input type="button" value="닫기" onclick="window.close();" />	
	  <%} 
	}
	else
	{%>	
		<h3>글자수가 맞지 않습니다.</h3> <h3>다시 입력해주세요.</h3>
		<input type="button" value="닫기" onclick="window.close();" />	
  <%}%>
	
	<script type="text/javascript">
		function selfClose()
		{
			var r = <%=result %>;
			var s = <%=size %>;
			var h = "<b><font color=red>사용 불가능</font><b>";
			if(r && s)
			{ 	h="<b><font color=green>사용 가능</font><b>"}
			opener.document.getElementById("nickresult").innerHTML=h;
			self.close();
		}
	
	</script>
</center>	