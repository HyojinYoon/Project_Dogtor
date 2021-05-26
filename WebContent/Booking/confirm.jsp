<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dogtor.DTO" %>
<%@ page import="dogtor.DAO" %>
<%@ page import="java.util.ArrayList" %>

    <%//세션검사 지우면 안됩니다. 헤더에서 사용중입니다. 만약 본인 내용에 세션 id 정검이 있을 경우 그 내용을 지워주세요.
    String id = (String)session.getAttribute("memId");
    int status = 0;
    if(id != null){
    	status= (int)session.getAttribute("memStatus");
    }%>

    <%
    String code = request.getParameter("code");
    String reserve_date = request.getParameter("reserve_date");
    request.setCharacterEncoding("UTF-8");
    %>
	
	<jsp:useBean class="dogtor.DTO" id="dto" />
	<jsp:setProperty name="dto" property="*" />
	<%
	DAO dao = new DAO();
	//해당 parameter 값과 일치하는 일정을 schedule 테이블에서 불러오는 기능
	ArrayList<DTO> list = dao.select(code, reserve_date);
	%>
	<!DOCTYPE html>
<html lang="en">
<!-- 헤더 공통 적용 -->
<head>
	
	<meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>진료시간 확인</title>
  
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
  
  <main id="main"><!-- main start -->
	
	  <!-- ======= 수의사 소개 ======= -->
    <section id="testimonials" class="testimonials">
      <div class="container" data-aos="zoom-in">
        <div class="owl-carousel testimonials-carousel">
	
		<%if(code.equals("h")){%>	
          <div class="testimonial-item">
            <img src="assets/img/team/team-1.jpg" class="testimonial-img" alt="">
            <h3>Misung Hwang</h3>
            <p>
              <i class="bx bxs-quote-alt-left quote-icon-left"></i>
              멜버른 대학교 수의학박사
              <i class="bx bxs-quote-alt-right quote-icon-right"></i>
             <br />(진료 시간)<br />
			  1time 10시~11시<br />
			  2time 12시~13시 <br />
			  3time 15시~16시<br />
			  4time 17시~18시 
              
            </p>
          </div>
		<%}%>
		
		<%if(code.equals("s")){%>	
          <div class="testimonial-item">
            <img src="assets/img/team/team-2.jpg" class="testimonial-img" alt="">
            <h3>Sarah Jhonson</h3>
            <p>
              <i class="bx bxs-quote-alt-left quote-icon-left"></i>
              세인트죠지 대학교 수의학박사<i class="bx bxs-quote-alt-right quote-icon-right"></i>
              <br />
              (진료 시간)<br />
1time 9시~10시<br />
2time 11시~12시<br />
3time 13시~14시<br />
4time 16시~17시
              
            </p>
          </div>
		<%}%>
		
		<%if(code.equals("w")){%>	
          <div class="testimonial-item">
            <img src="assets/img/team/team-3.jpg" class="testimonial-img" alt="">
            <h3>William Anderson</h3>
            <p>
              <i class="bx bxs-quote-alt-left quote-icon-left"></i>
              메씨 대학교 수의학박사 <i class="bx bxs-quote-alt-right quote-icon-right"></i>
              <br />
              (진료 시간)<br />
1time 9시 30분 ~ 10시 30분<br />
2time 11시 30분 ~ 12시 30분<br />
3time 2시 30분 ~ 3시 30분<br />
4time  4시 30분 ~ 5시 30분
              
            </p>
          </div>
		<%}%>
		
		<%if(code.equals("a")){%>	
          <div class="testimonial-item">
            <img src="assets/img/team/team-4.jpg" class="testimonial-img" alt="">
            <h3>Amanda Jepson</h3>
            <p>
              <i class="bx bxs-quote-alt-left quote-icon-left"></i>
              멜버른 대학교 수의학박사
              <i class="bx bxs-quote-alt-right quote-icon-right"></i><br />
              (진료 시간)<br />
			  1time 10시 30분 ~ 11시30분<br />
			  2time 12시 30분 ~ 1시 30분<br />
			  3time 3시 30분 ~ 4시 30분<br />
			  4time 5시30분 ~ 6시 30분 
             
            </p>
          </div>
		<%}%>
	     </div>

      </div>
    </section><!-- 수의사 소개 -->
	
	
	
	<center><form action="makeSchedule.jsp" method="post">
		<br>
		<div class="section-title">
          <h2>진료시간</h2>
          <h3><span>편하신 시간을 선택해주세요.</span></h3> 
        </div>		
		<%//같은날 예약이 있을 경우, 같은날 이미 예약되있음을 알려준다. 
		if(id != null){
			int check = dao.getReserveCheck(reserve_date, id);
			if(check != 0){
			%>
	    	<h3>고객님 오늘 예약 내역이 이미 있습니다. 정말 예약하시겠습니까?</h3>
			<%}
		}%>
		<br/>
		<h3>진료날짜: <%=reserve_date%></h3>
		*진료 가능한 시간을 확인해주세요*		
		<%--진료 가능 시간 불러오는 기능--%>
		<%if(!list.isEmpty()){%>
			<select name="reserve_time"><%
			for(DTO d:list){%>
				<option value="<%=d.getReserve_time()%>"><%=d.getReserve_date()%> <%=d.getReserve_time()%></option>
			<%}%><br />

			</select>	
			<input type="hidden" name="code" value="<%=code%>" />
			<input type="hidden" name="reserve_date" value="<%=reserve_date%>" />
			<br /><br />
			<input type="submit" value="예약하기" />
			<input type="button" value="다른날 선택하기" onclick="window.close();" />
		<%}else{%>
		<h4>고객님 죄송하지만 해당 날짜는 이미 예약 마감되었습니다.</h4>
		<input type="button" value="다른날 선택하기" onclick="window.close();" />
		<%}%>
		<input type="button" value="예약신청취소" onclick="goMain()" />
	</form>
	</center>
	<br /><br /><br /><br />
	
	 

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
	
	
	
	<script>
	 function goMain(){
			alert("예약신청이 취소되었습니다.");
			opener.location.href = "/dogtor/Member/memberForm/dogtorHome.jsp";
			self.close();
	 }
	</script>


	
	


	
    

    