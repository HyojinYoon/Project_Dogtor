<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.time.*"%>

<% LocalDate today = LocalDate.now();
	
	String year = today.getYear()+"";
	String month = today.getMonthValue()+"";
	String day = today.getDayOfMonth()+"";
	
	String date = year+ "-" + month + "-" + day;
	out.print(date);


%>