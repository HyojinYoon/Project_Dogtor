<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import = "board.DAO" %>
<%@ page import = "board.DTO" %>
<%@ page import = "java.sql.Timestamp" %>

<% request.setCharacterEncoding("UTF-8");%>

<%
  int num = Integer.parseInt(request.getParameter("num"));
  String pageNum = request.getParameter("pageNum");
  String passwd = request.getParameter("passwd");

  DAO dbPro = new DAO();
  int check = dbPro.deleteArticle(num, passwd);

  if(check==1){%>
  	  <meta http-equiv="Refresh" content="0;url=noticeList.jsp?pageNum=<%=pageNum%>" >	
	  
	  <meta http-equiv="Refresh" content="0;url=noticeList.jsp?pageNum=<%=pageNum%>" >
	  
<%}else{%>
		
        <script language="JavaScript">         
         alert("입력어가 맞지 않습니다.");
         history.go(-1);
      </script>
<%}%>