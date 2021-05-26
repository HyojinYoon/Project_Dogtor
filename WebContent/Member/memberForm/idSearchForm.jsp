<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%//세션검사 지우면 안됩니다. 헤더에서 사용중입니다. 만약 본인 내용에 세션 id 정검이 있을 경우 그 내용을 지워주세요.
    String id = (String)session.getAttribute("memId");
    int status = 0;
    if(id != null){
    	status= (int)session.getAttribute("memStatus");
	 }%>
	 
<%	// 세션이 있는 상태에서 loginForm으로 들어오지 못하게 하기
	if(id != null && status != 50)
	{%>
		<script>
			alert("로그인 상태입니다.");
			window.location = "/dogtor/Member/memberForm/dogtorHome.jsp";
		</script>
  <%}%>
    
<center>
<script>
function sub(){
	
	var name = document.idSearch.name.value;
	if(!name)
	{
		alert("이름을 입력해주세요.");
		return false;
	}
	
	var phone = document.idSearch.phone.value;
	if(!phone)
	{
		alert("휴대폰번호를 입력해주세요.");
		return false;
	}
}
</script>

<form name ="idSearch" action = "/dogtor/Member/memberPro/idSearchPro.jsp" method = "post" onsubmit="return sub();">
𝗜𝗗찾기 <br />
	<table align="center" >
	<tr><td align="center">이름</td> <td><input type = "text" name = "name"  /></td></tr><br />
	<tr><td align="center">휴대폰번호</td><td><input type = "text" name = "phone" placeholder="ex.01012345678"/></td></tr>
	</table><br />
		<input type = "submit" value = "아이디찾기" /> 
		<input type = "button" value = "닫기" onclick = "self.close();" /> <%--새창 닫기 --%>
</form>
</center>