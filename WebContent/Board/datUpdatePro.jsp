<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import = "board.datDAO" %>
<%@ page import = "board.datDTO" %>
<%@ page import = "java.sql.Timestamp" %>

<%
request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id="article" scope="page" class="board.datDTO" />
<jsp:setProperty name="article" property="*"/>

<%
    String nick = (String)session.getAttribute("memNick");
    int num = Integer.parseInt(request.getParameter("num"));            //댓글의 고유번호
    int superNum = Integer.parseInt(request.getParameter("superNum"));  //원글의 고유번호
    String passwd = request.getParameter("passwd");                     //내가 입력한 비밀번호
    String pageNum = request.getParameter("pageNum");

    datDAO dao = new datDAO();
   
    boolean check = dao.nickCheck(nick, passwd, article);

    if(check){
%>
	   <script>
            alert("댓글 작성이 완료되었습니다.");
	        window.location="freeContent.jsp?num=<%=superNum%>&pageNum=<%=pageNum%>";
	       
       </script>
<% }else{%>
      <script language="JavaScript">      
         
        alert("비밀번호가 맞지 않습니다");
        history.go(-1);
      
     </script>
<%
    }
 %>  