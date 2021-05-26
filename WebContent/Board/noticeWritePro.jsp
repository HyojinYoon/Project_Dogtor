<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import = "board.DAO" %>
<%@ page import = "java.sql.Timestamp" %>

<% request.setCharacterEncoding("UTF-8");%>

<jsp:useBean id="article" scope="page" class="board.DTO" />
<jsp:setProperty name="article" property="*"/>

<%
    article.setReg(new Timestamp(System.currentTimeMillis()) );

    DAO dbPro = new DAO();
    dbPro.insertArticleNotice(article);

    response.sendRedirect("noticeList.jsp");
%>
