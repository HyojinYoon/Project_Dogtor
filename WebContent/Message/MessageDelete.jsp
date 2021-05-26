<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import = "dogtor.messageDAO" %>
<%@ page import = "dogtor.messageDTO" %>
<%@ page import = "java.sql.Timestamp" %>

<% request.setCharacterEncoding("UTF-8");%>

<%
  int num = Integer.parseInt(request.getParameter("num"));
  String pageNum = request.getParameter("pageNum");

  messageDAO dao = new messageDAO();
  boolean result = dao.deleteMessage(num);

  if(result){%>
     <script>         
         alert("삭제되었습니다.");
         window.location="Message.jsp";
      </script>
<%}%>
       