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
<center><h2>비밀번호 찾기</h2>
</div></center>

<br />

<% request.setCharacterEncoding("UTF-8"); %>

<%
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	String phone = request.getParameter("phone");
	
	doctorDAO dao = new doctorDAO();

	String pw = dao.searchPw(id, name, phone);		

%>

<%if(pw == null){%>
<center><script type = "text/javascript">
	alert("입력하신 정보로 가입된 회원은 존재하지 않습니다.");
	history.go(-1);
</script></center>
<%}else{ %>
<center><h3>찾으시는 비밀번호는 [ <font color = blue> <%=pw %></font> ] 입니다.</h3><br /><br /><br />
 <input type="button" value="닫기" onclick="self.close();" /></center>
<%} %>
