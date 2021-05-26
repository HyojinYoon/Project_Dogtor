<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ page import="memberDAO.MemberDAO" %>
<%@ page import="memberDTO.MemberDTO" %>

  	
  	
 <%//세션검사 지우면 안됩니다. 헤더에서 사용중입니다. 만약 본인 내용에 세션 id 정검이 있을 경우 그 내용을 지워주세요.
    String id = (String)session.getAttribute("memId");
    int status = 0;
    String nick = "";
    if(id != null){
    	status= (int)session.getAttribute("memStatus");
    	nick = (String)session.getAttribute("memNick");
    }
 %>
  
  <%
  	if(id == null || status != 100)
  	{%>
  		<script>
		alert("접근 권한 없는 페이지입니다.");
		window.location = "/dogtor/Member/memberForm/dogtorHome.jsp";
		</script>
  <%} %>
  
<%
	String idMember = request.getParameter("id");			// 고객의 아이디
%>
	<script>
		var re = confirm("<%=idMember%>님을 탈퇴 시키겠습니까?");
		if(re){
			window.location="/dogtor/Member/memberPro/adminDeletePro.jsp?id=<%=idMember%>";
		}else{
			history.go(-1);
		}
	</script>
	
