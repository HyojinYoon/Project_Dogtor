<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	
	request.setCharacterEncoding("UTF-8");
	String id = (String)session.getAttribute("memId"); 
	String reserve_time = request.getParameter("reserve_time");
	String code = request.getParameter("code");
	String reserve_date = request.getParameter("reserve_date");
    int status = 0;
    if(id != null){
    	status= (int)session.getAttribute("memStatus");
    }
%>
<!DOCTYPE html>
<html lang="en">
<!-- 헤더 공통 적용 -->
<head>

  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>진료차트 작성</title>
  
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Roboto:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

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
	
		var email = document.reserve.email.value;
			
		if(!email){
				alert("이메일을 입력하세요!");
				return false;
		}
		

		var name = document.reserve.name.value;
    	if(!name)
    	{
    		alert("동반자 성함을 입력하세요!");
    		return false;
    	}
    		
		var phone = document.reserve.phone.value;
    	if(!phone)
    	{
    		alert("전화번호를 입력하세요!");
    		return false;
    	}
    		
    	var p_name = document.reserve.p_name.value;
    	if(!p_name)
    	{
    		alert("애완동물 이름을 입력하세요!");
    		return false;
    	}
    	
    	var symptom = document.reserve.symptom.value;
    	if(!symptom)
    	{
    		alert("증상을 입력하세요!");
    		return false;
    	}

    }
</script>

</head>
<body>
  <!-- ======= Top Bar ======= -->
  <div id="topbar" class="d-none d-lg-flex align-items-center fixed-top">
    <div class="container d-flex">
      <div class="contact-info mr-auto">
        <i class="icofont-envelope"></i>dogter@naver.com
        <i class="icofont-phone"></i> +83 010 4711 2245 
        <%if(id != null){%>
        <i class="icofont-envelope"></i> <%=id%>님 안녕하세요.
        <%}%>
      </div>
    </div>
  </div>


<center>
	<br /><br /><br /><br /><br /><br /><br />
	<img src="/dogtor/save/reserve.png" width="600" />
	<%-- 전달되는 파라메터 총 9가지 pro로 넘어가면 insert하고 select 로 보여줌.--%>
	<br/>
	<form name="reserve" action="makeSchedulePro.jsp" method="post" onsubmit="return sub();">
		<br /><h4>(최대한 자세하게 작성 부탁드립니다.)</h4>
		
		<%if(id != null){%>
			e-mail : <input type="text" name="email" value="<%=id%>" /><br /><br/>
		<%}else{%>
			e-mail : <input type="text" name="email" /><br /><br/>		
		<%}%>
		동반 보호자명 : <input type="text" name="name" /><br /><br/>
		전화번호 : <input type="text" name="phone" /><br /><br/>
		동물 이름 : <input type="text" name="p_name" /><br /><br/>

		<br /><br/>	
		
		상세 증상<br /><br />
		<textarea name="symptom" cols="50" rows="7" placeholder="애완동물 증상을 정확하게 입력해주세요."></textarea><br />
		<br />
		<input type="hidden" name="code" value="<%=code%>" />
		<input type="hidden" name="reserve_date" value="<%=reserve_date%>" />
		<input type="hidden" name="reserve_time" value="<%=reserve_time%>" />	
		<%//회원이면 1 비회원이면 0 값 넘겨준다.
		if(id != null){%>
			<input type="hidden" name="member" value="1" />	
		<%}else{%>
		<input type="hidden" name="member" value="0" />	
		<%}%>
		<input type="submit" value="예약완료" />	
		<input type="button" value="돌아가기" onclick="window.close();" />
		<input type="button" value="예약신청취소" onclick="goMain()" />
	</form>
</center>

   

  <!-- ======= Footer ======= -->
  <footer id="footer">

    <div class="footer-top">
      <div class="container">
        <div class="row">

          <div class="col-lg-3 col-md-6 footer-contact">
            <h3>DOGTOR<span>.</span></h3>
            <p>
              서울 관악구 <br>
              관악로 174 1층<br>
              대한민국 <br><br>
              <strong>전화번호:</strong> +83 010 4711 2245<br>
              <strong>이메일:</strong> dogter@naver.com<br>
              <strong>운영시간:</strong> 00:00 ~ 24:00(24시간운영) <br>
              <strong>사업자등록번호:</strong> 255-34-01121 <br>
              <strong>대표:</strong> 김독터 <br>
            </p>
          </div>   
          
        </div>
      </div>
    </div>

    <div class="container py-4">
      <div class="copyright">
        &copy; Copyright <strong><span>BizLand</span></strong>. All Rights Reserved
      </div>
      <div class="credits">
        <!-- All the links in the footer should remain intact. -->
        <!-- You can delete the links only if you purchased the pro version. -->
        <!-- Licensing information: https://bootstrapmade.com/license/ -->
        <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/bizland-bootstrap-business-template/ -->
        Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
      </div>
    </div>
  </footer><!-- End Footer -->

  <div id="preloader"></div>
  <a href="#" class="back-to-top"><i class="icofont-simple-up"></i></a>

  <!-- Vendor JS Files -->
  <script src="assets/vendor/jquery/jquery.min.js"></script>
  <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="assets/vendor/jquery.easing/jquery.easing.min.js"></script>
  <script src="assets/vendor/php-email-form/validate.js"></script>
  <script src="assets/vendor/waypoints/jquery.waypoints.min.js"></script>
  <script src="assets/vendor/counterup/counterup.min.js"></script>
  <script src="assets/vendor/owl.carousel/owl.carousel.min.js"></script>
  <script src="assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
  <script src="assets/vendor/venobox/venobox.min.js"></script>
  <script src="assets/vendor/aos/aos.js"></script>

  <!-- Template Main JS File -->
  <script src="assets/js/main.js"></script>

</body>

</html>

	<script>
	 function goMain(){
			alert("예약신청이 취소되었습니다.");
			opener.location.href = "/dogtor/Member/memberForm/dogtorHome.jsp";
			self.close();
	 }
	</script>