<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "board.datDAO" %>
<%@ page import = "board.datDTO" %>
<%@ page import = "java.sql.Timestamp" %>

<%
request.setCharacterEncoding("UTF-8");
%>

<%
  String pageNum = request.getParameter("pageNum");
  String id = (String)session.getAttribute("memId");
  Integer num = Integer.parseInt(request.getParameter("num"));
  Integer superNum = Integer.parseInt(request.getParameter("superNum"));
  Integer status = (Integer)session.getAttribute("memStatus");
  if(status == null){
		status = 0;
  }
   
  datDAO dbPro = new datDAO();
  int check = 0;
  if(status == 100 || status == 1){
       check = dbPro.deleteArticle(num); 
       if(check == 1){%> 
	     <script language="JavaScript">         
	           window.location="freeContent.jsp?num=<%=superNum%>&pageNum=<%=pageNum%>";
         </script>
      <%}
 }else{%>
      <script>
              alert("본인의 글이 아닙니다");
              history.go(-1);
      </script>
 <%}%>
 