<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "memberDAO.MemberDAO" %>
<%@ page import = "memberDTO.MemberDTO" %>

<center>
<% request.setCharacterEncoding("UTF-8"); %>

<%
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	String phone = request.getParameter("phone");
	
	MemberDAO dao = new MemberDAO();

	String pw = dao.searchPw(id, name, phone);		

%>

<%if(pw == null){%>
<script type = "text/javascript">
	alert("입력하신 정보로 가입된 회원은 존재하지 않습니다.");
	history.go(-1);
</script>
<%}else{ %>
<h3>찾으시는 비밀번호는</h3> <h1>[<%=pw %>]</h1> <h3>입니다.</h3>
 <input type="button" value="닫기" onclick="self.close();" />
<%} %>
</center>