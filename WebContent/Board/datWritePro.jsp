<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "board.datDAO" %>
<%@ page import = "java.sql.Timestamp" %>

<%
request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id="dto" scope="page" class="board.datDTO" />
<jsp:setProperty name="dto" property="*"/>

<%
    int superNum = dto.getSuperNum();
    int num = dto.getNum();
    String nick = (String)session.getAttribute("memNick");
    dto.setWriter(nick);
    datDAO dao = new datDAO();
    dao.insertArticleDat(dto);
    int pageNum = Integer.parseInt(request.getParameter("pageNum"));
    

   
%>

 <script>
    
    alert("댓글 작성이 완료되었습니다.");
	window.location="freeContent.jsp?num=<%=superNum%>&pageNum=<%=pageNum%>";
 </script>
 

 
