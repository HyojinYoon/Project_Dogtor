<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>스태프메인</title>
<center><img src="/dogtor/save/staffmain.png" width="400" /></center>
<center><h3> "원하시는 메뉴를 선택해주세요."</h3></center>
<%
	//세션 확인 후 code 출력
	String code = null;
	
	//세션 정보 확인
	//if(session.getAttribute("dogId") != null){//로그인이 안되어 있다면, 로그인페이지로 이동
	//code = (String)session.getAttribute("dogId");
	//}else{%>
	<%--	<script>
			alert("로그인 정보가 확인되지 않습니다.")
			location.href ="login.jsp"; //의사로그인페이지
		</script> --%>
	<%//}
	code= "j";//임의로 지정해줌 추후 삭제 필요.	
	%>
<center>
<form action="doctorConfirmCalanderR.jsp" method="post">
	<input type="hidden" name="code" value="<%=code%>" />
	<input type="submit" value="진료일정 확인하기" />

</form>

<form action="doctorConfirmCalanderS.jsp" method="post">
	<input type="hidden" name="code" value="<%=code%>" />
	<input type="submit" value="외출신청 및 외출확인" />
</form>

</center>