<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="memberDAO.MemberDAO" %>
<%@ page import="memberDTO.MemberDTO" %>
<%@ page import="memberDTO.PetDTO" %>
<%@ page import="java.util.ArrayList" %>

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

  <title>회원정보변경</title>
  
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
  	if(id == null || status == 0)
  	{%>
  		<script>
		alert("로그인 후 이용 가능합니다.");
		window.location = "/dogtor/Member/memberForm/loginForm.jsp";
		</script>
  <%}%>
   <%
    	MemberDAO dao = new MemberDAO();
    	MemberDTO m_dto = dao.getMember(id);
    	ArrayList<PetDTO> myPetList = new ArrayList<PetDTO>();
    	myPetList = dao.getPet(id);
    %>
   
 <script>
	function sub()
    {		
    	var pw = document.join.pw.value;
    	var pwCheck = document.join.pwCheck.value;
    	if(!pw || !pwCheck)
    	{
    		alert("비밀번호를 입력해주세요.");
    		return false;
    	}
    	if(document.join.pwDuplication.value != "pwCheck")
    	{
    		alert("비밀번호 확인을 해주세요.");
    		return false;
    	}
    		
		var name = document.join.name.value;
    	if(!name)
    	{
    		alert("이름을 입력해주세요.");
    		return false;
    	}
    		
    	var birth = document.join.birth.value;
    	if(!birth)
    	{
    		alert("생년월일을 입력해주세요.");
    		return false;
    	}
    		
    	var phone = document.join.phone.value;
    	if(!phone)
    	{
    		alert("휴대폰번호를 입력해주세요.");
    		return false;
    	}
    }
</script>

<script type="text/javascript">
	function pw_Check()
	{
		var pw = document.join.pw.value;
		var pwCheck = document.join.pwCheck.value;
	
		if(pw.length >=8 && pw.length <= 12)
		{	
			if(pw.search(/\s/) == -1)
			{
				if(!pw.match(/([a-zA-Z0-9].*[!,@,*])|([!,@,*].*[a-zA-Z0-9])/))
				{
					document.getElementById("check").innerHTML = "영문, 숫자, 특수문자 조합으로 입력해주세요.";
					document.getElementById("check").style.color = "red";
					document.getElementById("pw").value = "";
				}
				else
				{	document.getElementById("check").innerHTML = "";	}
			}
			else
			{	
				document.getElementById("check").innerHTML = "빈공백 없이 입력해주세요.";
				document.getElementById("check").style.color = "red";
				document.getElementById("pw").value = "";
			}
		}
		else
		{
			document.getElementById("check").innerHTML = "8글자 이상 12글자 이하입니다.";
			document.getElementById("check").style.color = "red";
			document.getElementById("pw").value = "";
		}
	
		if(pw != "" && pwCheck != "")
		{
			if(pw == pwCheck)
			{
				document.getElementById("same").innerHTML = "비밀번호가 일치합니다.";
				document.getElementById("same").style.color = "blue";
			}
			else
			{
				document.getElementById("same").innerHTML = "비밀번호가 일치하지 않습니다.";
				document.getElementById("same").style.color = "red";
			}
		}
	}	
</script>

<script>
	function addRow()
	{
		var pet_table = document.getElementById("pet_tbody");		// 행을 추가할 테이블명
		var pet_row = pet_table.insertRow(pet_table.rows.length);	// 추가할 행

		// 실제 행 추가 (숫자는 컬럼의 수)
		var cell1 = pet_row.insertCell(0);
		var cell2 = pet_row.insertCell(1);
		var cell3 = pet_row.insertCell(2);
		var cell4 = pet_row.insertCell(3);
		var cell5 = pet_row.insertCell(4);
		var cell6 = pet_row.insertCell(5);
		var cell7 = pet_row.insertCell(6);
		
		// 추가할 행에 원하는 요소 추가
		cell1.innerHTML = "<td><input type='checkbox' class='form-control' name='check' style='width:30px;height:15px;' /></td>";
		cell2.innerHTML = "<td><input type='text' class='form-control' name='p_name' placeholder='ex.멍멍이' /></td>";
		cell3.innerHTML = "<td><input type='text' class='form-control' name='p_birth' placeholder='ex.20140908' /></td>";
		cell4.innerHTML = "<td><select class='form-control' name='p_gender'>"
								+"<option selected='selected' value='선택없음'>선택없음</option>"
								+"<option selected='selected' value='수컷'>수컷</option>"
								+"<option value='암컷'>암컷</option>"
								+"<option value='중성화 수컷'>중성화 수컷</option>"
								+"<option value='중성화 암컷'>중성화 암컷</option>"
								+"</select></td>";
		cell5.innerHTML = "<td><select class='form-control' name='p_kind'>"
								+"<option selected='selected' value='선택없음'>선택없음</option>"
								+"<option value='강아지'> 강아지</option>"
								+"<option value='고양이'> 고양이</option>"
								+"<option value='특수동물'> 특수동물</option>"
								+"</select></td>";
		cell6.innerHTML = "<td><input type='text' class='form-control' name='p_etc' placeholder='기타사항을 입력해주세요.'/></td>";
		cell7.innerHTML = "<td><input type='hidden' class='form-control' name='num' value='0' /></td>";
	}
	
	function delRow()
	{
		var pet_table = document.getElementById("pet_tbody");		// 행을 삭제할 테이블명
		for(var i=0; i<pet_table.rows.length; i++)
		{
			var delcheck = pet_table.rows[i].cells[0].childNodes[0].checked;
			if(delcheck)
			{
				pet_table.deleteRow(i);
				i--;
			}
		}
	}
	
	</script>
	
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
      <h1><span>회원정보수정</span></h1>
      𝗘𝗗𝗜𝗧 𝗣𝗥𝗢𝗙𝗜𝗟𝗘
 		<br />

      
    </div>
  </section><!-- End Hero -->
		
	<main id="main"><!-- main start -->
  <!-- 여기에 본인 파트 시작 -->
 <%
 	if(id != null && status != 50)
 	{%>
     <form name="join" action="/dogtor/Member/memberPro/updatePro.jsp" method="post" onsubmit="return sub();">

    	<table align="center" border="0" cellpadding="10" cellspacing="0" >
   		<tr height="30"><td align="left"><b>아이디</b></td><td> <%=id %></td>
   		<br /></tr>
   		<tr height="60">
   			 <td align="left"><b>비밀번호</b></td>
    		 <td> <input type="password" name="pw" id="pw" value="<%=m_dto.getPw() %>" onchange="pw_Check()"/>&nbsp;<span id="check"></span></br>
    		 		<font size= "1em">8~12글자/영문,숫자,특수문자(!,@,*) 조합</font></td>
    	<br /></tr>    
    	<tr height="60">
   			 <td align="left"><b>비밀번호 확인</b></td>
  			 <td> <input type="password" name="pwCheck" id="pwCheck" onchange="pw_Check()"/>&nbsp;<span id="same"></span>
   		<br /></tr>
    	<tr height="60">
   			<td align="left"> <b>이름</b></td>
 			<td> <input type="text" name="name" value="<%=m_dto.getName() %>" /></td>
   		<br /></tr>
   		<tr height="60">
    		<td align="left"><b>생년월일</b></td>
    		<td><input type="text" name="birth" value="<%=m_dto.getBirth() %>" placeholder="ex.19950414" /></td>
    	<br /></tr>
		<tr height="60">
    		<td align="left"><b>닉네임</b></td><td><%=nick %></td>
		<tr height="60">
    		<td align="left"><b>휴대전화</b></td>
    		<td><input type="text" name="phone" value="<%=m_dto.getPhone() %>" placeholder="-빼고 입력하세요."/></td>
    	<br /></tr>
    </table> <br /> 
 	<hr align="center" style=" width: 70%">
<center>
    <h2>반려동물 정보 추가/수정</h2>
</center>
    <table  width="830" align="left">
   <center><font size="2em">✔특수동물은 진료 예약 시 병원으로 문의 부탁드립니다.</font></center> 
    <tr ><td>
    <input type="button" name="plus" value="+"  onclick="addRow();"/>
    <input type="button" name="minus" value="-" onclick="delRow();"/></td></tr></table> 
    <br />
    <table id="pet_table" class="table">
    	<thead>
    		<tr>
    			<th>선택삭제</th>
    			<th>이름</th>
    			<th>생일</th>
    			<th>성별</th>
    			<th>종류</th>
    			<th>기타사항</th>
    			<th></th>
    		</tr>
    	</thead>
    
    	<tbody id="pet_tbody">
    		<% 
    			for(int i=0; i < myPetList.size(); i++)
    			{
    				PetDTO p_dto = new PetDTO();
    				p_dto = myPetList.get(i);
    			%>
    		<tr>
    			<td><input type="checkbox" class="form-control" name="check" style="width:30px;height:15px;" /></td>
    			<td><input type="text" class="form-control" name="p_name" value="<%=p_dto.getP_name() %>" placeholder="ex.멍멍이" /></td>
    			<td><input type="text" class="form-control" name="p_birth" value="<%=p_dto.getP_birth() %>" maxlength="8" placeholder="ex.20140908"/></td>
    			<td><select class="form-control" name="p_gender">
    					<option <%if(p_dto.getP_gender().equals("선택없음")){%>selected<%}%>>선택없음</option>
    					<option <%if(p_dto.getP_gender().equals("수컷")){%>selected<%}%>>수컷</option>
    					<option <%if(p_dto.getP_gender().equals("암컷")){%>selected<%}%>>암컷</option>
    					<option <%if(p_dto.getP_gender().equals("중성화 수컷")){%>selected<%}%>>중성화 수컷</option>
    					<option <%if(p_dto.getP_gender().equals("중성화 암컷")){%>selected<%}%>>중성화 암컷</option>
    				</select></td>
    			<td><select class="form-control" name="p_kind">
    					<option <%if(p_dto.getP_gender().equals("선택없음")){%>selected<%}%>>선택없음</option>
    					<option <%if(p_dto.getP_kind().equals("강아지")){%>selected<%}%>>강아지</option>
    					<option <%if(p_dto.getP_kind().equals("고양이")){%>selected<%}%>>고양이</option>
    					<option <%if(p_dto.getP_kind().equals("특수동물")){%>selected<%}%>>특수동물</option>
    			</select></td>
    			<td><input type="text" class="form-control" name="p_etc" value="<%=p_dto.getP_etc()%>" placeholder="기타사항을 입력해주세요." /></td>
    			<td><input type="hidden" class="form-control" name="num" value="<%=p_dto.getNum() %>" /></td>
    		</tr>
    		  <%}%>
    	</tbody>
    </table>
    <br />
    <br />
    <br />
    <center>
    <input type="button" value="이전으로" onclick="window.location='/dogtor/Member/memberForm/update_PasswordForm.jsp'" />
    <input type="submit" value="회원 수정" />
    
    </form>   
    </center>
    
   <%}%>
    
    <!-- 여기에 본인 파트 종료-->
    
     
    
    
    <!-- 병원정보 메인 -->

    

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