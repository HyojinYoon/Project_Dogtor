<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dogtor.DTO"%>
<%@ page import="dogtor.DAO"%>
<%@ page import="java.util.ArrayList"%>
	<%String code = (String)request.getParameter("code");
	boolean ddd= true;
	DAO dao= new DAO();%>
	
    <%//세션검사 지우면 안됩니다. 헤더에서 사용중입니다. 만약 본인 내용에 세션 id 정검이 있을 경우 그 내용을 지워주세요.
    String id= (String)session.getAttribute("memId");
    int status = 0;
    if(id != null){
    	status= (int)session.getAttribute("memStatus");
    }%>
<!DOCTYPE html>
<html lang="en">
<head>
	
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>예약캘린더</title>
  
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
			alert("예약신청이 취소되었습니다.");
			location.href = "/dogtor/Member/memberForm/dogtorHome.jsp";
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
			//이번달일경우 오늘날짜 이후만 예약가능
			if(m == todayMon){
				if(i > todayDate){
					window.open("confirm.jsp?code="+code+"&reserve_date="+year+-+month+-+date,"_blank");
				}
			}
			//이번달 이후 경우
			if(m > todayMon){
					window.open("confirm.jsp?code="+code+"&reserve_date="+year+-+month+-+date,"_blank");
				
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
             for (i=1; i<=lastDate.getDate(); i++){ 
             //1일부터 마지막 일까지 돌림
             	  
                  m = today.getMonth()+1;//월
                  var d_day = date.getFullYear()+"-"+ m +"-"+i; 

             	  cell = row.insertCell();//열 한칸한칸 계속 만들어주는 역할
                  cell.innerHTML = "<a href=javascript:findList("+i+","+m+")>"+i+"</a>";
                  //셀을 1부터 마지막 day까지 HTML 문법에 넣어줌
                  
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
              //예약 가능한 날짜 표시

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
      <h1><span>진료날짜 선택</span></h1>
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
	<center>
	<h4>*당일예약은 불가능합니다.*</h4><br/>
	
	<h3>예약신청을 취소하고 싶다면?</h3>
	<input type="button" value="예약신청취소" onclick="goMain()" /></center>


<script language="javascript" type="text/javascript">
    buildCalendar();//
</script>

</div>

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
    