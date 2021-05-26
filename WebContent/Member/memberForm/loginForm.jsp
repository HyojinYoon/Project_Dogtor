<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%//세션검사 지우면 안됩니다. 헤더에서 사용중입니다. 만약 본인 내용에 세션 id 정검이 있을 경우 그 내용을 지워주세요.
    String id = (String)session.getAttribute("memId");
    int status = 0;
    String nick = "";
    if(id != null){
    	status= (int)session.getAttribute("memStatus");
    	nick = (String)session.getAttribute("memNick");
    }%>
    
<!DOCTYPE html>
<html lang="en">
<!-- 헤더 공통 적용 -->
<head>
	
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>로그인</title>
  
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

<%	// 세션이 있는 상태에서 loginForm으로 들어오지 못하게 하기
	if(id != null && status != 50)
	{%>
		<script>
			alert("로그인 상태입니다.");
			window.location = "/dogtor/Member/memberForm/dogtorHome.jsp";
		</script>
  <%}%>


<script>
	function sub()
    {
		var id = document.login.id.value;
		if(!id)
		{
			alert("아이디를 입력해주세요.");
			login.id.focus();
			return false;
		}
    		
    	var pw = document.login.pw.value;
    	if(!pw)
    	{
    		alert("비밀번호를 입력해주세요.");
    		login.pw.focus();
    		return false;
    	}
    }
</script>	

<script type = "text/javascript"> 
	function idSearch(){		
			//새창 경로,		이름,			새창 옵션
		open("idSearchForm.jsp","idSearch","width=400, height=200"); //아이디찾기 새창 실행 함수
	}
	function pwSearch(){		
		//새창 경로,		이름,			새창 옵션
	open("pwSearchForm.jsp","pwSearch","width=400, height=200"); //비번찾기 새창 실행 함수
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

  <!-- ======= Header ======= -->
  <header id="header" class="fixed-top">
    <div class="container d-flex align-items-center">

      <h1 class="logo mr-auto"><a href="/dogtor/Member/memberForm/dogtorHome.jsp">DOGTOR.</a></h1>
      <!-- Uncomment below if you prefer to use an image logo -->
      <!-- <a href="index.html" class="logo mr-auto"><img src="assets/img/logo.png" alt=""></a>-->

      <nav class="nav-menu d-none d-lg-block">
        <ul>
          <li class="active"><a href="/dogtor/Member/memberForm/dogtorHome.jsp">홈</a></li>
             <%if(id == null){%>
            <li><a href="/dogtor/Member/memberForm/loginForm.jsp">로그인</a></li>  
            <li><a href="/dogtor/Member/memberForm/registerForm.jsp">회원가입</a></li>  
          <%}else{%>
               <li><a href="/dogtor/Member/memberForm/logoutForm.jsp">로그아웃</a></li> 
             <%}%>
          <%if(status != 100){%>
          <li><a href="/dogtor/Booking/selectDoctor.jsp">예약하기</a></li>
          <%}%>
          <li class="drop-down"><a href="/dogtor/Board/noticeList.jsp">게시판</a>
            <ul>
              <li><a href="/dogtor/Board/noticeList.jsp">공지사항</a></li>
              <li><a href="/dogtor/Board/freeList.jsp">자유게시판</a></li>
              <li><a href="/dogtor/Board/list2.jsp">문의게시판</a></li>
            </ul>
          </li>
          <li><a href="#contact">병원정보</a></li>
        </ul>
      </nav><!-- .nav-menu -->
    </div>
  </header><!-- End Header -->

  <!-- ======= Hero Section ======= -->
  <section id="hero" class="d-flex align-items-center">
    <div class="container" data-aos="zoom-out" data-aos-delay="100">
      <h1><span>진료예약</span></h1>
 		<br />
 		<h3>노령동물도 안심하고 맡길 수 있는 </h3>
 		<h3><b>신뢰 높은 외과수술병원</b>입니다. </h3>
        <br />
   
      <div class="d-flex">
      <!-- 예약하기 버튼 -->
          <%if(id == null){%>
            <%if(status != 100){%>
           		<a href="/dogtor/Booking/selectDoctor.jsp" class="btn-get-started scrollto"> 예약하기 </a><p>&nbsp;</p>
           	<%}%>
           <br /> <a href="/dogtor/Member/memberForm/registerForm.jsp" class="btn-get-started scrollto"> 회원가입 </a>
          <%}else{%>
          		<%if(status != 100){%>
               		<a href="/dogtor/Booking/selectDoctor.jsp" class="btn-get-started scrollto"> 예약하기 </a><p>&nbsp;</p>
                <%}%>
                <%if(status == 100){%>
                	<a href="/dogtor/Member/memberForm/adminForm.jsp" class="btn-get-started scrollto"> 회원관리 </a><p>&nbsp;</p>
                <%}%>
               <a href="/dogtor/Member/memberForm/logoutForm.jsp" class="btn-get-started scrollto"> 로그아웃 </a>
             <%}%>  
      </div>
      
    </div>
  </section><!-- End Hero -->

  <main id="main"><!-- main start -->
  <!-- 여기에 본인 파트 시작 -->

<center>
<img src = "/dogtor/Member/memberForm/assets/img/mainlogo.png" width="400"/>
<form name = "login" action = "/dogtor/Member/memberPro/loginPro.jsp" method = "post" onsubmit = "return sub();">

	𝗜𝗗 	<input type = "text" name = "id" />  
	𝗣𝗪 	<input type = "password" name = "pw" /> <br /><br />
	   <input type = "button" value = "ID 찾기" onclick = "idSearch();" /> 
	   <input type = "button" value = "PW 찾기" onclick = "pwSearch();"/> 
	   <input type = "submit" value = "로그인" /> <br /><br />
</form>
</center>

 <!-- 여기에 본인 파트 종료-->
    
    
    <!-- 병원정보 메인 -->

    <!-- ======= 병원정보 영역 ======= -->
    <section id="contact" class="contact">
      <div class="container" data-aos="fade-up">

        <div class="section-title">
          <h2>병원정보</h2>
          <h3><span>환영합니다.</span></h3>
          <p>서울대 입구 2번출구에 위치해있는 접근성 좋은 동물병원입니다. <br /> 예약제로 운영되고 있으니 꼭 예약해주세요.</p>
        </div>

        <div class="row" data-aos="fade-up" data-aos-delay="100">
          <div class="col-lg-6">
            <div class="info-box mb-4">
              <i class="bx bx-map"></i>
              <h3>위치</h3>
              <p>서울 관악구 관악로 174 1층 / 봉천동 857-5 1층</p>
            </div>
          </div>

          <div class="col-lg-3 col-md-6">
            <div class="info-box  mb-4">
              <i class="bx bx-envelope"></i>
              <h3>이메일</h3>
              <p>dogter@naver.com</p>
            </div>
          </div>

          <div class="col-lg-3 col-md-6">
            <div class="info-box  mb-4">
              <i class="bx bx-phone-call"></i>
              <h3>전화번호</h3>
              <p>+83 010 4711 2245</p>
            </div>
          </div>
         </div>
          <!-- 구글 맵 연동 서울대입구 -->
          <div class="row" data-aos="fade-up" data-aos-delay="100">
        	  <div class="col-lg-6 ">
           		 <iframe class="mb-4 mb-lg-0" src="https://www.google.com/maps/embed/v1/place?key=AIzaSyB3Zr_LJ7njRYtUop-gi_-n32mCoLkEWOw&q=37.480656925296366, 126.95296063268745" frameborder="0" style="border:0; width: 100%; height: 384px;" allowfullscreen></iframe>
       		  </div>
      	 </div>
          
        </div>
    </section><!-- End Contact Section -->

  </main><!-- End #main -->

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