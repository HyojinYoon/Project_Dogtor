<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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


<center><script>
function sub(){
	
	var name = document.idSearch.name.value;
	if(!name)
	{
		alert("이름을 입력하세요!");
		return false;
	}
	
	var phone = document.idSearch.phone.value;
	if(!phone)
	{
		alert("휴대폰번호를 입력하세요!");
		return false;
	}
}
</script></center>

<center><form name ="idSearch" action = "/dogtor/Doctor/DocIdSearchPro.jsp" method = "post" onsubmit="return sub();">
	이름: <input type = "text" name = "name"  /> <br /><br />
	휴대폰번호: <input type = "text" name = "phone" placeholder="-빼고 입력하세요." /> <br /><br /><br />
		<input type = "submit" value = "아이디찾기" /> &nbsp;&nbsp;
		<input type = "button" value = "닫기" onclick = "self.close();" /> 