<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dogtor.DTO"%>
<%@ page import="dogtor.DAO" %>
<%@ page import="dogtor.doctorDAO" %>
<%@ page import="dogtor.doctorDTO" %>

<%//세션검사 지우면 안됩니다. 헤더에서 사용중입니다. 만약 본인 내용에 세션 id 정검이 있을 경우 그 내용을 지워주세요.
String id= (String)session.getAttribute("docId"); 
%>
	
	<%
	//진료확인페이지에서 사용하는 달력
	String code = null;
	
	
	DAO dao= new DAO();
	//세션 정보 확인 세션에서 code 정보 받아오기.
	if(session.getAttribute("docId") != null){//로그인이 안되어 있다면, 로그인페이지로 이동
		code = (String)session.getAttribute("docCode");
	}else{%>
	<script>
			alert("로그인 정보가 확인되지 않습니다.")
			location.href ="/dogtor/Doctor/DocLogin.jsp"; //의사로그인페이지
		</script>
	<%}%>
	

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>타이틀 변경해주세요.</title>
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
	doctorDAO docDao = new doctorDAO();
	doctorDTO docDto = docDao.getDoctor(id);
%>

    <style type="text/css">
        td{
            width: 50px;
            height: 50px;
            text-align: center;
            font-size: 20px;
            font-family: 굴림;
            border:2px border-color:#3333FF;
            border-radius: 8px;/*모서리 둥글게*/
        }
    </style>
<script type="text/javascript">

		
		function goMain(){
			alert("외출 날짜를 선택해주세요.");
			}

	
		//해당 선생님 정보 확인 i= 날짜, m=달
		function findList(i,m){
			var code = "<%=code%>";
			var date = i;
			var today = new Date();
			var year = today.getFullYear();
			var month = m;
			var todayMon = today.getMonth()+1;
			var todayDate = today.getDate();
			if(m == todayMon){
				if(i >= todayDate){
			window.open("doctorConfirmReservePro.jsp?code="+code+"&reserve_date="+year+-+month+-+date,"_blank");
				}
			}
			//이번달 이후 경우
			if(m > todayMon){
				window.open("doctorConfirmReservePro.jsp?code="+code+"&reserve_date="+year+-+month+-+date,"_blank");
				
			}		
		}
		
	
	
        var today = new Date();//오늘 날짜//내 컴퓨터 로컬을 기준으로 today에 Date 객체를 넣어줌
        var date = new Date();//today의 Date를 세어주는 역할
        function prevCalendar() {//이전 달
        // 이전 달을 today에 값을 저장하고 달력에 today를 넣어줌
        //today.getFullYear() 현재 년도//today.getMonth() 월  //today.getDate() 일 
        //getMonth()는 현재 달을 받아 오므로 이전달을 출력하려면 -1을 해줘야함
         today = new Date(today.getFullYear(), today.getMonth() - 1, today.getDate());
         buildCalendar(); //달력 cell 만들어 출력 
        }
 
        function nextCalendar() {//다음 달
            //다음 달을 today에 값을 저장하고 달력에 today 넣어줌
            //today.getFullYear() 현재 년도//today.getMonth() 월  //today.getDate() 일 
            //getMonth()는 현재 달을 받아 오므로 다음달을 출력하려면 +1을 해줘야함
             today = new Date(today.getFullYear(), today.getMonth() + 1, today.getDate());
             buildCalendar();//달력 cell 만들어 출력
        }
        function buildCalendar(){//현재 달 달력 만들기
            var doMonth = new Date(today.getFullYear(),today.getMonth(),1);
            //이번 달의 첫째 날,
            //new를 쓰는 이유 : new를 쓰면 이번달의 로컬 월을 정확하게 받아온다.     
            //new를 쓰지 않았을때 이번달을 받아오려면 +1을 해줘야한다. 
            //왜냐면 getMonth()는 0~11을 반환하기 때문
            var lastDate = new Date(today.getFullYear(),today.getMonth()+1,0);
            //이번 달의 마지막 날
            //new를 써주면 정확한 월을 가져옴, getMonth()+1을 해주면 다음달로 넘어가는데
            //day를 1부터 시작하는게 아니라 0부터 시작하기 때문에 
            //대로 된 다음달 시작일(1일)은 못가져오고 1 전인 0, 즉 전달 마지막일 을 가져오게 된다
            var tbCalendar = document.getElementById("calendar");
            //날짜를 찍을 테이블 변수 만듬, 일 까지 다 찍힘
            var tbCalendarYM = document.getElementById("tbCalendarYM");
            //테이블에 정확한 날짜 찍는 변수
            //innerHTML : js 언어를 HTML의 권장 표준 언어로 바꾼다
            //new를 찍지 않아서 month는 +1을 더해줘야 한다. 
             tbCalendarYM.innerHTML = today.getFullYear() + "년 " + (today.getMonth() + 1) + "월"; 
 
             /*while은 이번달이 끝나면 다음달로 넘겨주는 역할*/
            while (tbCalendar.rows.length > 2) {
            //열을 지워줌
            //기본 열 크기는 body 부분에서 2로 고정되어 있다.
                  tbCalendar.deleteRow(tbCalendar.rows.length-1);
                  //테이블의 tr 갯수 만큼의 열 묶음은 -1칸 해줘야지 
                //30일 이후로 담을달에 순서대로 열이 계속 이어진다.
             }
             var row = null;
             row = tbCalendar.insertRow();
             //테이블에 새로운 열 삽입//즉, 초기화
             var cnt = 0;// count, 셀의 갯수를 세어주는 역할
            // 1일이 시작되는 칸을 맞추어 줌
             for (i=0; i<doMonth.getDay(); i++) {
             /*이번달의 day만큼 돌림*/
                  cell = row.insertCell();//열 한칸한칸 계속 만들어주는 역할
                  cnt = cnt + 1;//열의 갯수를 계속 다음으로 위치하게 해주는 역할
             }
             num = 31
             
            /*달력 출력*/
             for (i=1; i<=lastDate.getDate(); i++) { 
             //1일부터 마지막 일까지 돌림
             	  
                  m = today.getMonth()+1;//월
                  var d_day = date.getFullYear()+"-"+ m +"-"+i; 
             	  cell = row.insertCell();//열 한칸한칸 계속 만들어주는 역할
                  cell.innerHTML = "<a href=javascript:findList("+i+","+m+")>"+i+"</a>";//셀을 1부터 마지막 day까지 HTML 문법에 넣어줌
                  
                  //각 달력 값마다 a태그 넣어줌
                  cnt = cnt + 1;//열의 갯수를 계속 다음으로 위치하게 해주는 역할
              if (cnt % 7 == 1) {/*일요일 계산*/
                  //1주일이 7일 이므로 일요일 구하기
                  //월화수목금토일을 7로 나눴을때 나머지가 1이면 cnt가 1번째에 위치함을 의미한다
                cell.innerHTML = "<font color=#F79DC2>" + i
                //1번째의 cell에만 색칠
            }    
              if (cnt%7 == 0){/* 1주일이 7일 이므로 토요일 구하기*/
                  //월화수목금토일을 7로 나눴을때 나머지가 0이면 cnt가 7번째에 위치함을 의미한다
                  cell.innerHTML = "<font color=skyblue>" + i
                  //7번째의 cell에만 색칠
                   row = calendar.insertRow();
                   //토요일 다음에 올 셀을 추가
              }
              /*오늘의 날짜에 노란색 칠하기*/
              if (today.getFullYear() == date.getFullYear()
                 && today.getMonth() == date.getMonth()
                 && i == date.getDate()) {
                  //달력에 있는 년,달과 내 컴퓨터의 로컬 년,달이 같고, 일이 오늘의 일과 같으면
                cell.bgColor = "#FAF58C";//셀의 배경색을 노랑으로 
               }
              //예약 불가능한 날짜에 붉은색 칠하기()
            
              //오늘 보다 이전이면 배경색 적용
              if (today.getFullYear() == date.getFullYear()
                      && today.getMonth() == date.getMonth()
                      && i < date.getDate()) {
                       //달력에 있는 년,달과 내 컴퓨터의 로컬 년,달이 같고, 일이 오늘의 일과 같으면
                     cell.bgColor = "#D5D5D5";//셀의 배경색을 노랑으로 
              }
              
              //이번달 이전이면 배경색 적용
              if (today.getFullYear() == date.getFullYear()
                      && today.getMonth() < date.getMonth()){
                       //달력에 있는 년,달과 내 컴퓨터의 로컬 년,달이 같고, 일이 오늘의 일과 같으면
                     cell.bgColor = "#D5D5D5";//셀의 배경색을 노랑으로 
              }
              //올해 전이면 배경색 적용
              if (today.getFullYear() < date.getFullYear()){
                       //달력에 있는 년,달과 내 컴퓨터의 로컬 년,달이 같고, 일이 오늘의 일과 같으면
                     cell.bgColor = "#D5D5D5";//셀의 배경색을 노랑으로 
              }
            }
        }
    </script>
</head>
<!-- ======= Top Bar ======= -->
  <div id="topbar" class="d-none d-lg-flex align-items-center fixed-top">
    <div class="container d-flex">
      <div class="contact-info mr-auto">
        <i class="icofont-envelope"></i>dogter@naver.com
        <i class="icofont-phone"></i> +83 010 4711 2245
        <%if(id != null){%>
        <i class="icofont-envelope"></i> <%=docDto.getName()%>[<%=docDto.getCode()%>]님 안녕하세요.
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
 		<h1><span>DOGTOR STAFF</span></h1>
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
               <a href="/dogtor/Booking/doctorConfirmCalanderS.jsp" class="btn-get-started scrollto"> 외출신청 </a><p>&nbsp;</p>
               <a href="/dogtor/Doctor/DocLogout.jsp" class="btn-get-started scrollto"> 로그아웃 </a>
             <%}%>  
      </div>
    </div>
  </section><!-- End Hero -->

  <main id="main"><!-- main start -->


<body>

	<div class="container" data-aos="fade-up">
    <p></p>
    <center><img src="/dogtor/save/calande.png" width="360" /></center>
<table id="calendar" border="1" align="center" style="border-color:#9594a2 ">
    <tr><!-- label은 마우스로 클릭을 편하게 해줌 -->
        <td><label onclick="prevCalendar()"><</label></td>
        <td align="center" id="tbCalendarYM" colspan="5">
        yyyy년 m월</td>
        <td><label onclick="nextCalendar()">>
            
        </label></td>
    </tr>
    <tr>
        <td align="center"><font color ="#F79DC2">일</td>
        <td align="center">월</td>
        <td align="center">화</td>
        <td align="center">수</td>
        <td align="center">목</td>
        <td align="center">금</td>
        <td align="center"><font color ="skyblue">토</td>
    </tr> 
</table>	
	<br /><br />
	<center><h3>외출신청을하고 싶다면?</h3>
	<form action="doctorConfirmCalanderS.jsp" method="post" onSubmit="goMain()">
		<input type="hidden" name="code" value="<%=code%>" />
		<input type="submit" value="외출신청"  /></center>
	</form>
	</div>
	<br /><br />


<script language="javascript" type="text/javascript">
    buildCalendar();//
</script>


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
  <script>
  		function sendMessage(){
  				window.open("/dogtor/Message/MessageWrite.jsp");
  		}
  </script>
</body>

</html>
    