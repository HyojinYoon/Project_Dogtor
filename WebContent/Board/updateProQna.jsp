<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import = "board.DAO" %>
<%@ page import = "board.DTO" %>
<%@ page import = "java.sql.Timestamp" %>

<%
request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id="article" scope="page" class="board.DTO" />
<jsp:setProperty name="article" property="*"/>

<%
String nick = (String)session.getAttribute("memNick");
    int num = Integer.parseInt(request.getParameter("num"));
    String passwd = request.getParameter("passwd"); //내가 입력한 비밀번호
    String pageNum = request.getParameter("pageNum");

	DAO dbPro = new DAO();
   
    boolean check = dbPro.nickCheck(nick, passwd,article);

    if(check){
%>
	  <meta http-equiv="Refresh" content="0;url=list2.jsp?pageNum=<%=pageNum%>" >
<% }else{%>
      <script language="JavaScript">      
         
        alert("비밀번호가 맞지 않습니다");
        history.go(-1);
      
     </script>
<%
    }
 %>  

 