<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import = "board.DAO" %>
<%@ page import = "board.DTO" %>
<%@ page import = "java.sql.Timestamp" %>

<% request.setCharacterEncoding("UTF-8");%>

<jsp:useBean id="article" scope="page" class="board.DTO" />
<jsp:setProperty name="article" property="*"/>

<%
 
    String pageNum = request.getParameter("pageNum");

	DAO dbPro = new DAO();
    int check = dbPro.updateArticle(article);

    if(check==1){
%>
	  <meta http-equiv="Refresh" content="0;url=noticeList.jsp?pageNum=<%=pageNum%>" >
<% }%>


 