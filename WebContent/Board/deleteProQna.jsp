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
  Integer status = (Integer)session.getAttribute("memStatus");
  if(status == null){
		status = 0;
  }
  int num = Integer.parseInt(number);
 
  int ref=Integer.parseInt(request.getParameter("ref"));
  int re_step=Integer.parseInt(request.getParameter("re_step"));
  int re_level=Integer.parseInt(request.getParameter("re_level"));

  DAO dbPro = new DAO();
  int check = 0;
  

  if(status == 100){
	  check = dbPro.deleteArticleQna(num, ref, re_level);
	  if(check==1){
%>
	      <script>  
               alert("게시물 삭제가 완료되었습니다.");
	           window.location="list2.jsp?num=<%=num%>&pageNum=<%=pageNum%>";
          </script>
	  <%}
  }else{
	 check = dbPro.deleteArticleQna(num, passwd, ref, re_level, id);
     if(check==1){%>
         
	     <meta http-equiv="Refresh" content="0;url=list2.jsp?pageNum=<%=pageNum%>" >
     <%}else{%>
         <script language="JavaScript">         
         alert("비밀번호가 맞지 않습니다");
         history.go(-1);
         </script>
   <%}
  }%>