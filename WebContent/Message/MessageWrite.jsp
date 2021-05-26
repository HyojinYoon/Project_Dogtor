<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="dogtor.doctorDAO" %>
<%@ page import="dogtor.doctorDTO" %>
<%@ page import="java.util.ArrayList" %>


<html>
<head>
<script language="JavaScript" src="script.js"></script>
</head>

<%

	doctorDAO dao = new doctorDAO();
	ArrayList<doctorDTO> list = dao.selectDoctor();
	String id = (String)session.getAttribute("docId");
	String name = (String)session.getAttribute("docName");
	
%>	

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/icofont/icofont.min.css" rel="stylesheet">
  <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="assets/vendor/owl.carousel/assets/owl.carousel.min.css" rel="stylesheet">
  <link href="assets/vendor/venobox/venobox.css" rel="stylesheet">
  <link href="assets/vendor/aos/aos.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="assets/css/style.css" rel="stylesheet">

<script>
	function sub()
    {
		var subject= document.writeform.subject.value;
		<%--writeform이란는 form 안에 subject, content 이름의 태그 값을 불러옴--%>
		
		if(!subject)    
		{
			alert("제목을 입력하세요!");
			return false;
		}
		
		var content= document.writeform.content.value;
		
		if(!content)    
		{
			alert("내용을 입력하세요!");
			return false;
		}
    }
		
		
</script>


 <%if(id == null){%>
		<script language="JavaScript">
		alert("로그인 후 이용 가능합니다!");
		self.close();
		window.location="/dogtor/Doctor/DocLogin.jsp";
	</script>	
	<%}else{%>
		<!-- 로그인 후 실행 코드 -->

<% 
  int num=0,ref=1,re_step=0,re_level=0;
 
    if(request.getParameter("num")!=null){
		num=Integer.parseInt(request.getParameter("num"));
		ref=Integer.parseInt(request.getParameter("ref"));
		re_step=Integer.parseInt(request.getParameter("re_step"));
		re_level=Integer.parseInt(request.getParameter("re_level"));
	}
%>
   
<body>
<br><br><br> 
<div class="section-title">
<center><h2>메세지 보내기</h2>
</div>
<br>

<form method="post" name="writeform" action="/dogtor/Message/MessageWritePro.jsp" onsubmit="return sub();">
<input type="hidden" name="num" value="<%=num%>">
<input type="hidden" name="ref" value="<%=ref%>">
<input type="hidden" name="re_step" value="<%=re_step%>">
<input type="hidden" name="re_level" value="<%=re_level%>">

<table width="450" border="1" cellspacing="0" cellpadding="0" align="center">
   
   <tr>
    <td  width="150"align="center" bgcolor="#e2eefd">보내는 사람</td>
    <td  width="330">
       <%=id%> <!-- 로그인 된 아이디가 작성자로 들어감 -->
       <input type="hidden" name="writer" value="<%=id%>"></td>
  </tr>
   <tr>
    <td  width="150"align="center" bgcolor="#e2eefd">받는 사람</td>
    <td  width="330">
       <select name="receiver">
		<%for(doctorDTO dto : list){ %>
		<%if(!id.equals(dto.getId())){%>
		<option value="<%=dto.getId()%>"><%=dto.getName() %>[<%=dto.getId()%>]</option><%}%><%}%>
	</select>
       <input type="hidden" name="receiver" value="<%=name%>"></td>
  </tr>
  <tr>
    <td  width="150" align="center" bgcolor="#e2eefd">제 목</td>
    <td  width="330">
    <%if(request.getParameter("num")==null){%>
       <input type="text" size="40" maxlength="50" name="subject"></td>
	<%}%>
  </tr>
  <tr>
    <td  width="150"  align="center" bgcolor="#e2eefd">내 용</td>
    <td  width="330" >
     <textarea name="content" rows="13" cols="40"></textarea> </td>
  </tr></table>
  <br><br>
  <center>
<tr><td> 
  <input type="submit" value="전송" onclick = "windw.close()">  
  <input type="reset" value="다시작성">
</td></tr></center>   
   
</form>      
</body>
</html>
<%}%>