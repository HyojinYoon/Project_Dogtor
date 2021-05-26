<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
 <%//세션검사 지우면 안됩니다. 헤더에서 사용중입니다. 만약 본인 내용에 세션 id 정검이 있을 경우 그 내용을 지워주세요.
    String id = (String)session.getAttribute("memId");
    int status = 0;
    String nick = "";
    if(id != null){
    	status= (int)session.getAttribute("memStatus");
    	nick = (String)session.getAttribute("memNick");
    }
  %>
  
  <%	// 비회원 session 안주고 예약조회 가능하게 하기!! => 로그인 폼에서 예약가능 생성을 만들면 됨
	if(id == null || status == 0)
	{%>
		<script>
			alert("잘못된 경로입니다.");
			window.location = "/dogtor/Member/memberForm/dogtorHome.jsp";
		</script>
  <%}%>

<%
	session.invalidate();		//user와 관련된 세션 삭제
%>

<script>
	alert("로그아웃 되었습니다.")
	window.location = "dogtorHome.jsp";
</script>