<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%//세션검사 지우면 안됩니다. 헤더에서 사용중입니다. 만약 본인 내용에 세션 id 정검이 있을 경우 그 내용을 지워주세요.
    String id= (String)session.getAttribute("memId");
    int status = 1;
    if(id != null){
    	status= (int)session.getAttribute("memStatus");
    }%>
    
    
<!DOCTYPE html>
<html lang="en">
<!-- 헤더 공통 적용 -->
<head>
	
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>의사 선택</title>
  
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
  
  
  <!-- 스크립트 및 자바 정리해주세요. -->
  
  
  
  
  
  
  
  
  
  <!-- 스크립트 및 자바 정리 종료. -->

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
          <li class="drop-down"><a href="/dogtor/Member/memberForm/mypageForm.jsp">마이페이지</a>
            <ul>
              <li><a href="/dogtor/Member/memberForm/mypageForm.jsp">마이페이지</a></li>
              <li><a href="/dogtor/Member/memberForm/update_PasswordForm.jsp">회원정보수정</a></li>
               	<%if(status != 100){%>
              <li><a href="/dogtor/Booking/reserveCheck.jsp">예약확인</a></li>
              <%}%>
              <%if(status == 100){%>
              	<li><a href="/dogtor/Member/memberForm/adminForm.jsp">회원관리</a></li>	
              <%}%>
            </ul>
          </li>
          <li><a href="#faq">FAQ</a></li>
          <li><a href="#contact">병원정보</a></li>
        </ul>
      </nav><!-- .nav-menu -->
    </div>
  </header><!-- End Header -->

  <!-- ======= Hero Section ======= -->
  <section id="hero" class="d-flex align-items-center">
    <div class="container" data-aos="zoom-out" data-aos-delay="100">
      <h1><span>진료선생님 선택</span></h1>
 		<br />
 		<h3>노령동물도 안심하고 맡길 수 있는 </h3>
 		<h3><b>신뢰 높은 외과수술병원</b>입니다. </h3>
        <br />
   
      <div class="d-flex">
      <!-- 예약하기 버튼 -->
          <%if(id == null){%>
            <%if(status != 100){%>

           	<%}%>
           <br /> <a href="/dogtor/Member/memberForm/loginForm.jsp" class="btn-get-started scrollto"> 로그인 </a><p>&nbsp;</p>
           <br /> <a href="/dogtor/Member/memberForm/registerForm.jsp" class="btn-get-started scrollto"> 회원가입 </a>
          <%}else{%>
          		<%if(status != 100){%>

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
  
  
 <!-- ======= 팀영역 ======= -->
    <section id="수의사 소개" class="team section-bg">
      <div class="container" data-aos="fade-up">

        <div class="section-title">
          <h3><span>전문 수의사 소개</span></h3>
          <p>최고의 수술 결과를 만들기 위해, 최고의 선생님만 모시고 있습니다.</p>
        </div>

        <div class="row">

          <div class="col-lg-3 col-md-6 d-flex align-items-stretch" data-aos="fade-up" data-aos-delay="100">
            <div class="member">
              <div class="member-img">
                <a href="calander.jsp?code=h"><img src="assets/img/team/team-1.jpg" class="img-fluid" alt=""></a>
                <div class="social">

			  <div class="btn-wrap">
                <a href="calander.jsp?code=h" class="btn-buy">예약</a>
              </div>

                </div>
              </div>
              <div class="member-info">
                <h4>Misung Hwang</h4>
                <span>원장선생님</span>
                <span>멜버른 대학교 수의학박사</span>
              </div>
            </div>
          </div>

          <div class="col-lg-3 col-md-6 d-flex align-items-stretch" data-aos="fade-up" data-aos-delay="200">
            <div class="member">
              <div class="member-img">
                <a href="calander.jsp?code=s"><img src="assets/img/team/team-2.jpg" class="img-fluid" alt=""></a>
                <div class="social">
              
			  <div class="btn-wrap">
                <a href="calander.jsp?code=s" class="btn-buy">예약</a>
              </div>
					
                </div>
              </div>
              <div class="member-info">
                <h4>Sarah Jhonson</h4>
                <span>외과전문 선생님</span>
                <span>세인트죠지 대학교 수의학박사</span>
              </div>
            </div>
          </div>

          <div class="col-lg-3 col-md-6 d-flex align-items-stretch" data-aos="fade-up" data-aos-delay="300">
            <div class="member">
              <div class="member-img">
                <a href="calander.jsp?code=w"><img src="assets/img/team/team-3.jpg" class="img-fluid" alt=""></a>
                <div class="social">
			  		<div class="btn-wrap">
               			 <a href="calander.jsp?code=w" class="btn-buy">예약</a>
            		</div>
                </div>
              </div>
              <div class="member-info">
                <h4>William Anderson</h4>
                <span>마취전문 선생님</span>
                <span>메씨 대학교 수의학박사</span>
              </div>
            </div>
          </div>

          <div class="col-lg-3 col-md-6 d-flex align-items-stretch" data-aos="fade-up" data-aos-delay="400">
            <div class="member">
              <div class="member-img">
                <a href="calander.jsp?code=a"><img src="assets/img/team/team-4.jpg" class="img-fluid" alt=""></a>
                <div class="social">
					<div class="btn-wrap">
               			 <a href="calander.jsp?code=a" class="btn-buy">예약</a>
              		</div>
                </div>
              </div>
              <div class="member-info">
                <h4>Amanda Jepson</h4>
                <span>내과전문 선생님</span>
                <span>멜버른 대학교 수의학박사</span>
              </div>
            </div>
          </div>

        </div>

      </div>
    </section><!-- 팀영역 -->
  
  
  


     <!-- 여기에 본인 파트 종료-->
    
     <!-- ======= 자주 묻는 질문 ======= -->
    <section id="faq" class="faq section-bg">
      <div class="container" data-aos="fade-up">

        <div class="section-title">
          <h2>F.A.Q</h2>
          <h3>자주 물어봐야하는 <span>8가지 질문</span></h3>
          <p>다른 동물 병원을 방문하시더라도 해당 질문은 꼭 해주세요.</p>
        </div>

        <ul class="faq-list" data-aos="fade-up" data-aos-delay="100">

          <li>
            <a data-toggle="collapse" class="" href="#faq1">제가 이런걸 먹이는데 먹여도 괜찮을까요? <i class="icofont-simple-up"></i></a>
            <div id="faq1" class="collapse show" data-parent=".faq-list">
              <p>
                먹을거리로 인해, 발생하는 건강문제가 많습니다. <br />평소에 어떤걸 먹이시는지 꼭 알려주시고, 꼭 물어보셔야합니다.
              </p>
            </div>
          </li>

          <li>
            <a data-toggle="collapse" href="#faq2" class="collapsed">체중이 적당한가요? <i class="icofont-simple-up"></i></a>
            <div id="faq2" class="collapse" data-parent=".faq-list">
              <p>
                우리 아이기 과체중 혹은 비만이라는 이야기를 들으신다면, 꼭 체중조절을 해주셔야하고,<br /> 많이 먹지 않음에도 살이 찐다면 건강검진을 받아야 합니다.
              </p>
            </div>
          </li>

          <li>
            <a data-toggle="collapse" href="#faq3" class="collapsed">이런 행동들이 정상인가요? <i class="icofont-simple-up"></i></a>
            <div id="faq3" class="collapse" data-parent=".faq-list">
              <p>
                반려동물을 처음 키우신다면, 동물의 행동이 재밌고 신기하기만해서 이러한 행동이 문제가 있는 것인지 모르는 경우가 많습니다. <br />만약을 위해서 꼭 물어보셔야 합니다.
              </p>
            </div>
          </li>

          <li>
            <a data-toggle="collapse" href="#faq4" class="collapsed">잇몸 건강은 괜찮은가요? <i class="icofont-simple-up"></i></a>
            <div id="faq4" class="collapse" data-parent=".faq-list">
              <p>
                반려 동물을 양치 시키는 일은 초보자에게는 굉장히 어려워 힘들어하시는 경우가 많습니다. <br />병원에 방문하셨을 때 꼭 체크를 받아야합니다.
              </p>
            </div>
          </li>

          <li>
            <a data-toggle="collapse" href="#faq5" class="collapsed">구충을 어떻게 해야하나요? <i class="icofont-simple-up"></i></a>
            <div id="faq5" class="collapse" data-parent=".faq-list">
              <p>
                반려동물은 여라가지 이유로, 기생충에 노출되기 쉬운 환경에 있습니다.<br /> 그래서 정기적으로 구충을 해주어야하는데, 처음 동물을 키우는 경우 잘 모를 수 있으니 꼭 물어봐주세요.
              </p>
            </div>
          </li>
          
             <li>
            <a data-toggle="collapse" href="#faq6" class="collapsed">우리아이 만졌을 때 덩어리가 만져지는데 이게 뭔가요? <i class="icofont-simple-up"></i></a>
            <div id="faq6" class="collapse" data-parent=".faq-list">
              <p>
                수의사가 진단을 내리는 시간은 굉장히 짧은 시간이기 때문에, 동물에 대한 상태는 주인이 훨씬 잘알 수 밖에 없습니다.<br /> 그래서 무언가 이상한 덩어리가 만져진다면, 꼭 말씀해주세요.
              </p>
            </div>
          </li>
          
             <li>
            <a data-toggle="collapse" href="#faq7" class="collapsed">다른 치료법이나 검사 방법은 없을까요? <i class="icofont-simple-up"></i></a>
            <div id="faq7" class="collapse" data-parent=".faq-list">
              <p>
                수의사도 본인이 알고 있는 지식과 경험을 바탕으로 진단을 내립니다.<br /> 따라서, 다른 치료방법 혹은 다른 병원을 방문하는 것이 좋지는 않을지 꼭 물어봐주세요.
              </p>
            </div>
          </li>
          
             <li>
            <a data-toggle="collapse" href="#faq8" class="collapsed">다음 내원까지 제가 해야할 일은 무엇일까요? <i class="icofont-simple-up"></i></a>
            <div id="faq8" class="collapse" data-parent=".faq-list">
              <p>
                진료가 끝날때는 어떤 점을 주의하여 아이를 돌어봐야하는지 물어보아야합니다.<br /> 아이를 관리하면서 체크해보셔야 할 것에 대해서 설명을 잘 들으셔야합니다.
               </p>
            </div>
          </li>
          

        </ul>

      </div>
    </section><!-- End Frequently Asked Questions Section -->
    
    
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