<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "dogtor.doctorDAO" %>
<%@ page import = "dogtor.doctorDTO" %>


<!-- Vendor CSS Files -->
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/icofont/icofont.min.css" rel="stylesheet">
  <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="assets/vendor/owl.carousel/assets/owl.carousel.min.css" rel="stylesheet">
  <link href="assets/vendor/venobox/venobox.css" rel="stylesheet">
  <link href="assets/vendor/aos/aos.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="assets/css/style.css" rel="stylesheet">
  <br />
<br />
<br />
  
<div class="section-title">
<center><h2>아이디 찾기</h2>
</div>
<br />

<% request.setCharacterEncoding("UTF-8"); %>

<%
	String name = request.getParameter("name");
	String phone = request.getParameter("phone");
	
	doctorDAO dao = new doctorDAO();

	String id = dao.searchId(name, phone);		

%>

<%if(id == null){%>
<center><script type = "text/javascript">
	alert("존재하지 않은 계정입니다.");
	history.go(-1);
</script></center>
<%}else{ %>
 <center><h3>찾으시는 아이디는 [ <font color = blue><%=id%></font> ]입니다.</h3><br /><br />
 <input type="button" value="비밀번호 찾기" onclick="location.href='/dogtor/Doctor/DocPwSearch.jsp'" />&nbsp;&nbsp;
 <input type="button" value="닫기" onclick="self.close();" /></center>
	
<%} %>

