<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <h1></h1>
    
    
    <%
    	session.invalidate(); //세션 모두 삭제
    %>
    <script>
    	alert("로그아웃 되었습니다.");
    	window.location="/dogtor/Doctor/DoctorHome.jsp";
    </script>
    
    