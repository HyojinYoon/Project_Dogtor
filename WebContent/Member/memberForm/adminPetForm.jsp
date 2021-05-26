<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="memberDAO.MemberDAO" %>
<%@ page import="memberDTO.PetDTO" %>    
<%@ page import="java.util.ArrayList" %>  

   <%//세션검사 지우면 안됩니다. 헤더에서 사용중입니다. 만약 본인 내용에 세션 id 정검이 있을 경우 그 내용을 지워주세요.
    String id = (String)session.getAttribute("memId");
    int status = 0;
    if(id != null){
    	status= (int)session.getAttribute("memStatus");
    }%>
    
    
<!DOCTYPE html>
<html lang="en">
<!-- 헤더 공통 적용 -->
<head>
	
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>반려동물 목록 관리</title>
  
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
  	if(id == null || status != 100)
  	{%>
  		<script>
		alert("접근 권한 없는 페이지입니다.");
		window.location = "/dogtor/Member/memberForm/dogtorHome.jsp";
		</script>
  <%}%>
  
<%
	request.setCharacterEncoding("UTF-8");

	int pageSize = 10;			// 한페이지의 pet정보 list 수
	String pageNum = request.getParameter("pageNum");	// 펫 목록에서 페이지번호 클릭시 받을 수 있다.
	if(pageNum == null)			// 펫 목록에서 페이지번호를 받을 수 없을 때 1을 받는다.
	{	pageNum ="1";	}
	
	int currentPage = Integer.parseInt(pageNum);		// 현재 페이지(1)
	int startRow = (currentPage -1) * pageSize + 1;		// 시작 리스트번호(1)
	int endRow = currentPage * pageSize;				// 끝나는 리스트번호(10)
	int count = 0;										// 전체 게시물 수
//	int number = 0;										// 화면 글 번호
	
	ArrayList<PetDTO> petCurrentList = null;
	MemberDAO p_dao = new MemberDAO();					// DAO 생성
	count = p_dao.getPetCount();						// pet 테이블의 전체 레코드 수 리턴 (129)
	if(count > 0)
	{	petCurrentList = p_dao.getPetList(startRow, endRow);	}	// startRow부터 endRow까지의 해당 pet정보를 얻는 메서드(1~10)
//	number = count - (currentPage-1)*pageSize;
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
        </ul>
      </nav><!-- .nav-menu -->
    </div>
  </header><!-- End Header -->

  <!-- ======= Hero Section ======= -->
  <section id="hero" class="d-flex align-items-center">
    <div class="container" data-aos="zoom-out" data-aos-delay="100">
      <h1><span>전체 반려동물 관리</span></h1>
	  <br />
   
      <div class="d-flex">
      <!-- 예약하기 버튼 -->
          <%if(id == null){%>
            <%if(status != 100){%>
           		<a href="/dogtor/Booking/selectDoctor.jsp" class="btn-get-started scrollto"> 예약하기 </a><p>&nbsp;</p>
           	<%}%>
           <br /> <a href="/dogtor/Member/memberForm/loginForm.jsp" class="btn-get-started scrollto"> 로그인 </a><p>&nbsp;</p>
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

<html>
<head>
<title>반려동물 관리</title>
</head>

<body>
<center><br/><h1>𝗣𝗘𝗧 𝗟𝗜𝗦𝗧</h1>
<b>전체 반려동물:<%=count %></b>
<table width="1300">
	<tr height="10">
		<td align= "left" cellpadding="0" cellspacing="0">
		<form action="searchPetForm.jsp" method="post">
		<select name="col">
			<option value="p_name">이름</option>
			<option value="id">ID</option>
		</select>
		<input type="text" name="searchPet" />
		<input type="submit" value="검색" />
	</form>
	</td>
		<td align="right"" cellpadding="0" cellspacing="0">	
			<%if(status == 100 )
			  {%>	
			  	  <a href="adminMemberForm.jsp">회원목록</a> &emsp;
			<%}%>
		</td>
	</tr>
</table>
<%
	if(count == 0)
	{%>
		<table width="1300" border="1" cellpadding="0" cellspacing="0">
			<tr height="30">
				<td align="center">
					목록에 저장된 반려동물 정보가 없습니다.
				</td>
			</tr>		
		</table>
  <%}
	else
	{%>
		<table border="1" width="1300" cellpadding="0" cellspacing="0" align="center">
			<tr height="30">
				<td align="center" width="50" ><font size = "4em">No.</font></td>
				<td align="center" width="200" ><font size = "4em">ID</font></td>
				<td align="center" width="100" ><font size = "4em">이름</font></td>
				<td align="center" width="100" ><font size = "4em">생년월일</font></td>
				<td align="center" width="80" ><font size = "4em">성별</font></td>
				<td align="center" width="150" ><font size = "4em">종류</font></td>
				<td align="center" width="200" ><font size = "4em">기타사항</font></td>
				<td align="center" width="40" ><font size = "4em">순서</font></td>
				<td align="center" width="80" ><font size = "4em">회원상태</font></td>
			</tr>
	<%
		for(int i=0; i < petCurrentList.size(); i++)		// 10개의 펫 데이터 생성
		{	PetDTO p_dto = petCurrentList.get(i);	%>
		
			<tr height="30">
				<td align="center" width="50" ><font size = "2em"><%=p_dto.getNum() %> </font></td>
				<td align="center" width="200"><font size = "2em"> <%=p_dto.getId() %></font></td>
				<td align="center" width="100"><font size = "2em"> <%=p_dto.getP_name() %></font> </td>
				<td align="center" width="100" ><font size = "2em"><%=p_dto.getP_birth() %></font></td>
				<td align="center" width="80" ><font size = "2em"><%=p_dto.getP_gender() %></font></td>
				<td align="center" width="150" ><font size = "2em"><%=p_dto.getP_kind() %></font></td>
				<td align="center" width="200" ><font size = "2em"><%=p_dto.getP_etc() %></font></td>
				<td align="center" width="40" ><font size = "2em"><%=p_dto.getMyp_num() %></font></td>
				<td align="center" width="80" >
				<% if(p_dto.getStatus() == 0)
					{%><font size="2em">비회원</font><%}
					else if(p_dto.getStatus() == 1)
					{%><font size="2em">일반회원</font><%}
					else if(p_dto.getStatus() == 50)
					{%><font size="2em" color="red">탈퇴회원</font><%}
					else if(p_dto.getStatus() == 100)
					{%><font size="2em">관리자</font><%}%>
				</td>
			</tr>
	  <%}%>
		</table>
  <%}%>
  <br />
 <%	// 페이지 번호 나타내기
	if(count > 0)
	{
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);		// 펫목록 페이지 수 : 129/10 +1 = 13
		int startPage = (int)(currentPage/10)*10+1;			// 페이지 시작 번호 :1/10*10+1=2
		int pageBlock = 10;
		int endPage = startPage + pageBlock - 1;	// 2+10-1=11
		if(endPage > pageCount)		// 11 > 13
		{	endPage = pageCount;	}		// endPage=13
		
		if(startPage > 10)
		{%> <a href="adminPetForm.jsp?pageNum=<%=startPage - 10 %>">[이전]</a> <%}
		
		for(int i=startPage; i <= endPage; i++)
		{%>	<a href="adminPetForm.jsp?pageNum=<%=i %>">[<%=i %>]</a>	<%}
		
		if(endPage < pageCount)
		{%> <a href="adminPetForm.jsp?pageNum=<%=startPage+10 %>">[다음]</a>	<%}
	}%>

</center>
</body>
</html>
  <!-- 여기에 본인 파트 종료-->
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
   
    
    