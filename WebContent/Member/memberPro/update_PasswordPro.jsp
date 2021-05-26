<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="memberDAO.MemberDAO" %>
<%@ page import="memberDTO.MemberDTO" %>      

    <%
    	String pw = request.getParameter("pw");
    	String id = (String)session.getAttribute("memId");
    	MemberDAO dao = new MemberDAO();
    	boolean result = dao.idpw_Check(id, pw);
    	
    	if(result)
    	{%>
    		<script>
    			window.location="/dogtor/Member/memberForm/updateForm.jsp";
    		</script>
      <%}
    	else
    	{%>
    		<script>
    			alert("비밀번호가 맞지 않습니다.");
    			history.go(-1);
    		</script>
      <%}%>