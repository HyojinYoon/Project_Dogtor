<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import = "board.DAO" %>
<%@ page import = "board.DTO" %>
<%@ page import = "java.sql.Timestamp" %>


<%
request.setCharacterEncoding("UTF-8");
%>

<%
  int num = Integer.parseInt(request.getParameter("num"));
  String pageNum = request.getParameter("pageNum");
  String passwd = request.getParameter("passwd"); //내가 입력한 비밀번호
  String id = (String)session.getAttribute("memId");
  int ref = Integer.parseInt(request.getParameter("ref")); //그룹번호
  int re_step = Integer.parseInt(request.getParameter("re_step")); //그룹내 게시순서
  int re_level = Integer.parseInt(request.getParameter("re_level")); //그룹내 댓글그룹

  DAO dbPro = new DAO();
  boolean check = dbPro.passwdCheck(id, passwd, ref);

  if(check){
%>  <%--비밀번호 맞으면 content페이지로 이동해라 <ㄱ --%>
	  <meta http-equiv="Refresh" content="0;url=content.jsp?num=<%=num %>&pageNum=<%=pageNum%>" >
<%}else{%>
       <script language="JavaScript">         
         alert("비밀번호가 맞지 않습니다");
         history.go(-1);
      </script>
<%}%>

