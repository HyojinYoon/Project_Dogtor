<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import = "board.DAO" %>
<%@ page import = "board.DTO" %>
<%@ page import = "java.sql.Timestamp" %>

<%
request.setCharacterEncoding("UTF-8");
%>

<%
  String pageNum = request.getParameter("pageNum");
  String passwd = request.getParameter("passwd"); //삭제폼에서 입력한 pw
  String id = (String)session.getAttribute("memId");
  String number = request.getParameter("num");
  int status = (int)session.getAttribute("memStatus");
  int num = Integer.parseInt(number);
  
  

  DAO dbPro = new DAO();
  int check = 0;
  

  if(status == 100){
	  check = dbPro.deleteArticleFreeArticle(num);
	  if(check==1){
%>
	      <meta http-equiv="Refresh" content="0;url=freeList.jsp?pageNum=<%=pageNum%>" >
	  <%}
  }else{
	 check = dbPro.deleteArticleQna(num, passwd);
     if(check==1){%>
         
	     <meta http-equiv="Refresh" content="0;url=freeList.jsp?pageNum=<%=pageNum%>" >
     <%}else{%>
         <script language="JavaScript">         
         alert("비밀번호가 맞지 않습니다");
         history.go(-1);
         </script>
   <%}
  }%>