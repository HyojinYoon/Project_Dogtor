<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "dogtor.messageDTO"%>
<%@ page import = "dogtor.messageDAO"%>
<%@ page import=  "dogtor.doctorDAO" %>
<%@ page import=  "dogtor.doctorDTO" %>
<%@ page import = "java.util.ArrayList"%>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "java.util.List" %>
<%//세션검사 지우면 안됩니다. 헤더에서 사용중입니다. 만약 본인 내용에 세션 id 정검이 있을 경우 그 내용을 지워주세요.
String id= (String)session.getAttribute("docId"); 
%>

<!DOCTYPE html>
<html lang="en">
<!-- 헤더 공통 적용 -->

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>보낸 메세지함</title>
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



<%
   request.setCharacterEncoding("UTF-8");

	doctorDAO dao = new doctorDAO();	
	doctorDTO dto = dao.getDoctor(id);


   int readCheckCount = 0; // 안읽은 메세지 갯수.
   int readcheck = 0 ;
   
   int pageSize = 10;
   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

   String pageNum = request.getParameter("pageNum");
   if (pageNum == null) {
       pageNum = "1";
   }

   int currentPage = Integer.parseInt(pageNum);
   int startRow = (currentPage - 1) * pageSize + 1;
   int endRow = currentPage * pageSize;
   int count=0;
   int number=0;
   
   List messageList = null;
   messageDAO dao2 = new messageDAO();
   count = dao2.getMessageCount2(id);
   if (count > 0) {
       messageList = dao2.getMessage(id, startRow, endRow);
   }//이름 받아와야하는데 변수 중복으로 dao->dao2로 수정
   
   String writer = id;
   number=count-(currentPage-1)*pageSize;
%>

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
        <i class="icofont-envelope"></i> <%=dto.getName()%>님 안녕하세요.
        <%}%>
      </div>
    </div>
  </div>

  <!-- ======= Header ======= -->
  <header id="header" class="fixed-top">
    <div class="container d-flex align-items-center">

      <h1 class="logo mr-auto"><a href="/dogtor/Doctor/DoctorHome.jsp">STAFF.</a></h1>
      <!-- Uncomment below if you prefer to use an image logo -->
      <!-- <a href="index.html" class="logo mr-auto"><img src="assets/img/logo.png" alt=""></a>-->

      <nav class="nav-menu d-none d-lg-block">
        <ul>
          <li class="active"><a href="/dogtor/Doctor/DoctorHome.jsp">홈</a></li>
          <%if(id == null){%>
            <li><a href="/dogtor/Doctor/DocLogin.jsp">로그인</a></li>  
            <li><a href="/dogtor/Doctor/DocJoin.jsp">회원가입</a></li>  
          <%}else{%>
               <li><a href="/dogtor/Doctor/DocLogout.jsp">로그아웃</a></li> 
             <%}%>
         
          <li class="drop-down"><a href="/dogtor/Message/Message.jsp">메세지확인</a>
            <ul>
              <li><a href="javascript:void(window.open('/dogtor/Message/MessageWrite.jsp',width=100, heigh=100))">메세지 보내기</a></li>
              <li><a href="/dogtor/Message/Message.jsp">받은메세지함</a></li>
              <li><a href="/dogtor/Message/SendMessage.jsp">보낸메세지함</a></li>
            </ul>
          </li>
          <li class="drop-down"><a href="/dogtor/Doctor/DocMain.jsp">마이페이지</a>
            <ul>
              <li><a href="/dogtor/Doctor/DocMain.jsp">마이페이지</a></li>
              <%if(id != null){%>
              <li><a href="/dogtor/Doctor/DoctorUpdate.jsp">회원정보수정</a></li>
              <%}%>
              <li><a href="/dogtor/Booking/doctorConfirmCalanderR.jsp">진료일정</a></li>
              <li><a href="/dogtor/Booking/doctorConfirmCalanderS.jsp">외출신청 & 외출확인</a></li>
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
       <h1><span>보낸 메세지함</span></h1>
       <br />
       <h3>오늘도 수고해주셔서 감사합니다.</h3>
       <h3><b>신뢰 높은 외과수술병원 DOGTOR</b>입니다. </h3>
        <br />
   
      <div class="d-flex">
      <!-- 진료확인하기 버튼 -->
        <%if(id == null){%>
           
           <br /> <a href="/dogtor/Doctor/DocLogin.jsp" class="btn-get-started scrollto"> 로그인 </a><p>&nbsp;</p>
           <br /> <a href="/dogtor/Doctor/DocJoin.jsp" class="btn-get-started scrollto"> 회원가입 </a>
          <%}else{%>
               <a href="/dogtor/Booking/doctorConfirmCalanderR.jsp" class="btn-get-started scrollto"> 진료일정 </a><p>&nbsp;</p>
               <a href="/dogtor/Doctor/DocLogout.jsp" class="btn-get-started scrollto"> 로그아웃 </a><p>&nbsp;</p>
               <a href="javascript:void(window.open('/dogtor/Message/MessageWrite.jsp',width=100, heigh=100))" class="btn-get-started scrollto">메세지 보내기</a><p>&nbsp;</p>
               <a href="/dogtor/Message/Message.jsp" class="btn-get-started scrollto">받은 메세지 함</a>
             <%}%>  
      </div>
    </div>
  </section><!-- End Hero -->

  <main id="main"><!-- main start -->
  <!-- 여기에 본인 파트 시작 -->
  
<center>
<%if(id == null){%>
      <script>
             alert ("로그인 해주세요.");
             window.location = "/dogtor/Doctor/DocLogin.jsp" ;
      </script>
   <%}else{%>
     
      <br />
        
	<%if(count == 0){%>
      <table><tr><%="보낸 메세지가 없습니다."%></tr></table>
         <%}else{%>
            <table border="1">
               <tr align = "center" bgcolor="#e2eefd"><td>번호</td><td width=250>제목</td><td width=150>받는 사람</td><td width=200>보낸 날짜</td><td>읽음 여부</td></tr>
                  
                  <%for (int i = 0; i < messageList.size() ; i++){
                     messageDTO message = (messageDTO)messageList.get(i);
                     %>
                  
                     <tr>
                        <td align = "center"><%=number--%>
                        <td align = "center"><a href = "Content.jsp?num=<%=message.getNum()%>"><font color = black><%=message.getSubject()%></font></a></td>
                        <td align = "center"><%=message.getReceiver()%></td>
                        <td align = "center"><%=sdf.format(message.getRe_date())%></td>
                        <td align = "center"><%if(message.getReadcheck()>=1){%>읽음<%}else{%>안읽음<%}%></td>  
					</tr>
				<%}%>
			</table>

<%
    if (count > 0) {
        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
        int startPage = (int)(currentPage/10)*10+1;
        int pageBlock=10;
        int endPage = startPage + pageBlock-1;
        if (endPage > pageCount) endPage = pageCount;
        if (startPage > 10) {%>
           <a href="SendMessage.jsp?pageNum=<%= startPage - 10 %>">[이전]</a>
         <%}
        for (int i = startPage ; i <= endPage ; i++) {  %>
            <a href="SendMessage.jsp?pageNum=<%= i %>">[<%= i %>]</a>
         <%}
        if (endPage < pageCount) {  %>
            <a href="SendMessage.jsp?pageNum=<%= startPage + 10 %>">[다음]</a>
         <%}}}}%>

<form action="/dogtor/Message/SendMessageSearch.jsp" method="post">
   <select name="col">
      <option value="subject">제목</option>
      <option value="receiver">받는사람</option>
   </select>
   <input type="text" name="search" />
   <input type="submit" value="검색" />
</form>
</center>

 <!-- 여기에 본인 파트 종료-->
    
     <!-- ======= 자주 묻는 질문 ======= -->
    <section id="faq" class="faq section-bg">
      <div class="container" data-aos="fade-up">

        <div class="section-title">
          <h2>F.A.Q</h2>
          <h3>자주 물어보는 <span>질문</span></h3>
          <p>진료확인 및 외출신청, 쪽지기능을 활용하기 위해서 필요한 내용입니다. <br /> 꼭 숙지해주세요.</p>
        </div>

        <ul class="faq-list" data-aos="fade-up" data-aos-delay="100">

          <li>
            <a data-toggle="collapse" class="" href="#faq1">예약이 있을 경우 외출신청은 어떻게 하나요? <i class="icofont-simple-up"></i></a>
            <div id="faq1" class="collapse show" data-parent=".faq-list">
              <p>
                고객님께서 진료를 예약하셨을 경우 그 시간은 외출이 불가능합니다. 저희 병원은 고객님과의 신뢰를 가장 중요하게 생각합니다.
              </p>
            </div>
          </li>

          <li>
            <a data-toggle="collapse" href="#faq2" class="collapsed">외출 신청 사유는 어떻게 적나요? <i class="icofont-simple-up"></i></a>
            <div id="faq2" class="collapse" data-parent=".faq-list">
              <p>
                외출 이유는 보다 자세하게 작성하여 주세요.<br /> "식사, 약속, 급한 용무"와 같이 간단하게 작성할 경우 인사고과에 반영됩니다.
              </p>
            </div>
          </li>

          <li>
            <a data-toggle="collapse" href="#faq3" class="collapsed">외출 취소는 어떻게 하나요? <i class="icofont-simple-up"></i></a>
            <div id="faq3" class="collapse" data-parent=".faq-list">
              <p>
                외출 신청 후 취소할 경우, 고객님의 불편을 최소화하기 위해서 최소 2일 전에 취소하여야 하며, 하루 전 혹은 당일 외출 취소를 할 경우 인사고과에 반영됩니다.
              </p>
            </div>
          </li>

          <li>
            <a data-toggle="collapse" href="#faq4" class="collapsed">진료확인은 어떻게 하나요? <i class="icofont-simple-up"></i></a>
            <div id="faq4" class="collapse" data-parent=".faq-list">
              <p>
                진료확인은 상단페이지에서 진행해주시면 되며, 고객님의 방문이 예약되어 있을 경우, 꼭 잊지말고 고객님에게 미리 연락해주세요.
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