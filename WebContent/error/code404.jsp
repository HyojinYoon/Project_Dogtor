<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
    <%-- 404 : 경로가 잘못되었을 때 에러 처리 --%>
    
    <%-- 서버로부터 정상 요청을 받는다. 200번 --%>
    <%	response.setStatus(HttpServletResponse.SC_OK);	%>
    
<h1>요청하신 페이지는 존재하지 않습니다.</h1>