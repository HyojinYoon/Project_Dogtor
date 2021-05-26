<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "memberDAO.MemberDAO" %>
<%@ page import = "memberDTO.MemberDTO" %>

<center>

<% request.setCharacterEncoding("UTF-8"); %>

<%
	String name = request.getParameter("name");
	String phone = request.getParameter("phone");
	
	MemberDAO dao = new MemberDAO();

	String id = dao.searchId(name, phone);		

%>

<%if(id == null){%>
<script type = "text/javascript">
	alert("존재하지 않은 계정입니다.");
	history.go(-1);
</script>
<%}else{ %>
 <h3>찾으시는 아이디는</h3> <h1>[<%=id%>]</h1><h3>입니다.</h3>
 <input type="button" value="비밀번호 찾기" onclick="location.href='/dogtor/Member/memberForm/pwSearchForm.jsp'" />
 <input type="button" value="닫기" onclick="self.close();" />
	
<%} %>

</center>