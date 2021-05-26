<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import = "dogtor.messageDAO" %>
<%@ page import = "java.sql.Timestamp" %>
<%@ page import = "dogtor.messageDTO" %>

<% request.setCharacterEncoding("UTF-8");%>

<jsp:useBean id="message" scope="page" class="dogtor.messageDTO" />
<jsp:setProperty name="message" property="*"/>

<%
   	messageDTO dto = new messageDTO();
	message.setRe_date(new Timestamp(System.currentTimeMillis()));
	

    messageDAO dao = new messageDAO();
    dao.insertMessage(message);
%>


<script>
alert("전송되었습니다!");
self.close();
opener.location.reload();
</script>
