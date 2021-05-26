<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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

  <title>회원 약관</title>
  
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
 
  <%	// 세션이 있는 상태에서 loginForm으로 들어오지 못하게 하기
	if(id != null && status != 50)
	{%>
		<script>
			alert("로그인 상태입니다.");
			window.location = "/dogtor/Member/memberForm/dogtorHome.jsp";
		</script>
  <%}%>
 

 

<script type="text/javascript">
	function before()
	{	history.go(-1);	}
	
	function sub()
	{
		var register = document.getElementsByName('register');
		var infor = document.getElementsByName('infor');
		if(register[0].checked)
		{
			if(infor[0].checked)
			{	window.location = "joinForm.jsp?register="+encodeURI(register)+"&infor="+encodeURI(infor);	}
			else
			{	
				alert("개인정보 취급방침에 동의해주세요.");	
				return false;
			}
		}
		else
		{
			alert("회원약관에 동의해주세요.");
			return false;
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
        </ul>
      </nav><!-- .nav-menu -->
    </div>
  </header><!-- End Header -->



  <main id="main"><!-- main start -->
  <!-- 여기에 본인 파트 시작 -->
<br /><br /><br /><br /><br /><br /><br />

<center>
	<h2>회원가입</h2> <br/>

    <h4>[필수]회원약관 동의</h4>
     <textarea rows="10" cols="100">  
제1장 총 칙

제1조(목적)
이 약관은 DOGTOR(이하 "병원"이라 한다)은 홈페이지에서 제공하는 모든 서비스(이하 "서비스"라 한다)의 이용조건 및 절차에 관한 사항을 규정함을 목적으로 합니다. 

제2조(정의) 
이 약관에서 사용하는 용어의 정의는 다음 각 호와 같습니다. 
1. 이용자 : 본 약관에 따라 병원이 제공하는 서비스를 받는 자
2. 이용계약 : 서비스 이용과 관련하여 병원과 이용자간에 체결하는 계약
3. 가입 :  병원이 제공하는 신청서 양식에 해당 정보를 기입하고, 본 약관에 동의하여 서비스 이용계약을 완료시키는 행위
4. 회원 : 당 사이트에 회원가입에 필요한 개인정보를 제공하여 회원 등록을 한 자로서 병원의 정보를 지속적으로 제공받으며, 병원이 제공하는 서비스를 계속적으로 이요할 수 있는 자
5. 비회원 : 회원에 가입하지 않고 병원이 제공하는 서비스를 이용하는 자
5. 이용자번호(ID) : 회원 식별과 회원의 서비스 이용을 위하여 이용자가 현재 사용하고 있는 이메일 사용(한 사람에 하나의 이메일만 사용 가능함)
6. 패스워드(PASSWORD) : 회원의 정보 보호를 위해 이용자 자신이 설정한 영문자와 숫자, 특수문자의 조합
7. 탈퇴 : 병원 또는 회원이 서비스 이용이후 그 이용계약을 종료시키는 의사표시

제3조(약관의 효력과 변경)
회원은 변경된 약관에 동의하지 않을 경우 회원 탈퇴(해지)를 요청할 수 있으며, 변경된 약관의 효력 발생일로부터 7일 이후에도 거부의사를 표시하지 아니하고 서비스를 계속 사용할 경우 약관의 변경 사항에 동의한 것으로 간주됩니다
① 이 약관의 서비스 화면에 게시하거나 공지사항 게시판 또는 기타의 방법으로 공지함으로써 효력이 발생됩니다. 
② 병원은 필요하다고 인정되는 경우 이 약관의 내용을 변경할 수 있으며, 변경된 약관은 서비스 화면에 공지하며, 공지후 7일 이후에도 거부의사를 표시하지 아니하고 서비스를 계속 사용할 경우 약관의 변경 사항에 동의한 것으로 간주됩니다.
③ 이용자가 변경된 약관에 동의하지 않는 경우 서비스 이용을 중단하고 본인의 회원등록을 취소할 수 있으며, 계속 사용하시는 경우에는 약관 변경에 동의한 것으로 간주되며 변경된 약관은 전항과 같은 방법으로 효력이 발생합니다.

제4조(준용규정) 
이 약관에 명시되지 않은 사항은 전기통신기본법, 전기통신사업법 및 기타 관련법령의 규정에 따릅니다. 

제2장 서비스 이용계약

제5조(이용계약의 성립) 
이용계약은 이용자의 이용신청에 대한 병원의 승낙과 이용자의 약관 내용에 대한 동의로 성립됩니다.

제6조(이용신청) 
이용신청은 서비스의 회원정보 화면에서 이용자가 병원에서 요구하는 가입신청서 양식에 개인의 신상정보를 기록하여 신청할 수 있습니다. 

제7조(이용신청의 승낙)
① 회원이 신청서의 모든 사항을 정확히 기재하여 이용신청을 하였을 경우에 특별한 사정이 없는 한 서비스 이용신청을 승낙합니다.
② 다음 각 호에 해당하는 경우에는 이용 승낙을 하지 않을 수 있습니다. 
1. 본인의 실명으로 신청하지 않았을 때
2. 타인의 명의를 사용하여 신청하였을 때
3. 이용신청의 내용을 허위로 기재한 경우
4. 사회의 안녕 질서 또는 미풍양속을 저해할 목적으로 신청하였을 때
5. 기타 병원이 정한 이용신청 요건에 미비 되었을 때 

제8조(계약사항의 변경) 
회원은 이용신청시 기재한 사항이 변경되었을 경우에는 수정하여야 하며, 수정하지 아니하여 발생하는 문제의 책임은 회원에게 있습니다.


제3장 계약당사자의 의무

제9조(병원의 의무) 
병원은 서비스 제공과 관련해서 알고 있는 회원의 신상 정보를 본인의 승낙 없이 제3자에게 누설하거나 배포하지 않습니다. 단, 전기통신기본법 등 법률의 규정에 의해 국가기관의 요구가 있는 경우, 범죄에 대한 수사상의 목적이 있거나 또는 기타 관계법령에서 정한 절차에 의한 요청이 있을 경우에는 그러하지 아니합니다.

제10조(회원의 의무)
① 회원은 서비스를 이용할 때 다음 각 호의 행위를 하지 않아야 합니다. 
1. 다른 회원의 ID를 부정하게 사용하는 행위 
2. 서비스에서 얻은 정보를 복제, 출판 또는 제3자에게 제공하는 행위 
3. 병원의 저작권, 제3자의 저작권 등 기타 권리를 침해하는 행위 
4. 공공질서 및 미풍양속에 위반되는 내용을 유포하는 행위 
5. 범죄와 결부된다고 객관적으로 판단되는 행위 
6. 기타 관계법령에 위반되는 행위 
② 회원은 서비스를 이용하여 영업활동을 할 수 없으며, 영업활동에 이용하여 발생한 결과에 대하여 병원은 책임을 지지 않습니다. 
③ 회원은 서비스의 이용권한, 기타 이용계약상 지위를 타인에게 양도하거나 증여할 수 없으며, 이를 담보로도 제공할 수 없습니다. 


제4장 서비스 이용 

제11조(회원의 의무)
① 회원은 필요에 따라 자신의 메일, 게시판, 등록자료 등 유지보수에 대한 관리책임을 갖습니다. 
② 회원은 병원에서 제공하는 자료를 임의로 삭제, 변경할 수 없습니다.
③ 회원은 병원의 홈페이지에 공공질서 및 미풍양속에 위반되는 내용물이나 제3자의 저작권 등 기타권리를 침해하는 내용물을 등록하는 행위를 하지 않아야 합니다. 만약 이와 같은 내용물을 게재하였을 때 발생하는 결과에 대한 모든 책임은 회원에게 있습니다. 

제12조(게시물 관리 및 삭제) 
효율적인 서비스 운영을 위하여 회원의 메모리 공간, 메시지크기, 보관일수 등을 제한할 수 있으며 등록하는 내용이 다음 각 호에 해당하는 경우에는 사전 통지없이 삭제할 수 있습니다. 
1. 다른 회원 또는 제3자를 비방하거나 중상모략으로 명예를 손상시키는 내용인 경우
2. 공공질서 및 미풍양속에 위반되는 내용인 경우 
3. 범죄적 행위에 결부된다고 인정되는 내용인 경우 
4. 병원의 저작권, 제3자의 저작권 등 기타 권리를 침해하는 내용인 경우 
5. 회원이 병원의 홈페이지와 게시판에 음란물을 게재하거나 음란 사이트를 링크하는 경우 
6. 기타 관계법령에 위반된다고 판단되는 경우 

제13조(게시물의 저작권) 
게시물의 저작권은 게시자 본인에게 있으며 회원은 서비스를 이용하여 얻은 정보를 가공, 판매하는 행위 등 서비스에 게재된 자료를 상업적으로 사용할 수 없습니다. 

제14조(서비스 이용시간) 
서비스의 이용은 업무상 또는 기술상 특별한 지장이 없는 한 연중무휴 1일 24시간을 원칙으로 합니다. 다만 정기 점검 등의 사유 발생시는 그러하지 않습니다.

제15조(서비스 이용 책임) 
서비스를 이용하여 해킹, 음란사이트 링크, 상용S/W 불법배포 등의 행위를 하여서는 아니되며, 이를 위반으로 인해 발생한 영업활동의 결과 및 손실, 관계기관에 의한 법적 조치 등에 관하여는 병원은 책임을 지지 않습니다. 

제16조(서비스 제공의 중지) 
다음 각 호에 해당하는 경우에는 서비스 제공을 중지할 수 있습니다. 
1. 서비스용 설비의 보수 등 공사로 인한 부득이한 경우 
2. 전기통신사업법에 규정된 기간통신사업자가 전기통신 서비스를 중지했을 경우 
3. 시스템 점검이 필요한 경우
4. 기타 불가항력적 사유가 있는 경우


제5장 계약해지 및 이용제한

제17조(계약해지 및 이용제한)
① 회원이 이용계약을 해지하고자 하는 때에는 회원 본인이 인터넷을 통하여 해지신청을 하여야 하며, 병원에서는 본인 여부를 확인 후 조치합니다.
② 병원은 회원이 다음 각 호에 해당하는 행위를 하였을 경우 해지조치 30일전까지 그 뜻을 이용고객에게 통지하여 의견진술할 기회를 주어야 합니다.
1. 타인의 이용자ID 및 패스워드를 도용한 경우 
2. 서비스 운영을 고의로 방해한 경우 
3. 허위로 가입 신청을 한 경우
4. 같은 사용자가 다른 ID로 이중 등록을 한 경우 
5. 공공질서 및 미풍양속에 저해되는 내용을 유포시킨 경우 
6. 타인의 명예를 손상시키거나 불이익을 주는 행위를 한 경우 
7. 서비스의 안정적 운영을 방해할 목적으로 다량의 정보를 전송하거나 광고성 정보를 전송하는 경우 
8. 정보통신설비의 오작동이나 정보 등의 파괴를 유발시키는 컴퓨터바이러스 프로그램 등을 유포하는 경우 
9. 병원 또는 다른 회원이나 제3자의 지적재산권을 침해하는 경우 
10. 타인의 개인정보, 이용자ID 및 패스워드를 부정하게 사용하는 경우 
11. 회원이 자신의 홈페이지나 게시판 등에 음란물을 게재하거나 음란 사이트를 링크하는 경우 
12. 기타 관련법령에 위반된다고 판단되는 경우


제6장 기 타

제18조(양도금지) 
회원은 서비스의 이용권한, 기타 이용계약상의 지위를 타인에게 양도, 증여할 수 없으며, 이를 담보로 제공할 수 없습니다.

제19조(손해배상) 
병원은 무료로 제공되는 서비스와 관련하여 회원에게 어떠한 손해가 발생하더라도 동 손해가 병원의 고의 또는 중대한 과실로 인한 손해를 제외하고 이에 대하여 책임을 부담하지 아니합니다.

제20조(면책 조항)
① 병원은 천재지변, 전쟁 또는 기타 이에 준하는 불가항력으로 인하여 서비스를 제공할 수 없는 경우에는 서비스 제공에 관한 책임이 면제됩니다.
② 병원은 서비스용 설비의 보수, 교체, 정기점검, 공사 등 부득이한 사유로 발생한 손해에 대한 책임이 면제됩니다.
③ 병원은 회원의 귀책사유로 인한 서비스이용의 장애에 대하여 책임을 지지 않습니다.
④ 병원은 회원이 서비스를 이용하여 기대하는 이익이나 서비스를 통하여 얻는 자료로 인한 손해에 관하여 책임을 지지 않습니다.
⑤ 병원은 회원이 서비스에 게재한 정보, 자료, 사실의 신뢰도, 정확성 등의 내용에 관하여는 책임을 지지 않습니다.

제21조(관할법원) 
서비스 이용으로 발생한 분쟁에 대해 소송이 제기 될 경우 병원의 소재지를 관할하는 법원을 전속 관할법원으로 합니다. 

부 칙 
(시행일) 이 약관은 2021년 1월 1일부터 시행합니다.
     </textarea>
    <br/>
    <table width="750" border="0">
    <tr align="left"><td>
    <input type="radio" name="register" value="agree" /> 회원가입 약관의 내용에 동의합니다.</td></tr><br /></table>
	</center>
    <br/>
    <br/>
    <br/>
	<center>
    <h4>[필수]개인정보 취급방침</h4>
    <textarea rows="10" cols="100"> 
'DOGTOR'(이하 본원 이라 함)은 
귀하의 개인정보보호를 매우 중요시하며, 『개인정보보호법』을 준수하고 있습니다. 
본원은 개인정보처리방침을 통하여 귀하께서 제공하시는 개인정보가 어떠한 용도와 방식으로 이용되고 있으며 개인정보보호를 위해 어떠한 조치가 취해지고 있는지 알려 드립니다.
본원은 개인정보취급방침을 개정하는 경우 홈페이지 공지사항(또는 개별공지)을 통하여 고지할 것 입니다.

■ 수집하는 개인정보의 항목 및 수집방법

본원은 서비스 이용을 위해 필요한 최소한의 개인정보만을 수집합니다. 
귀하가 본원의 서비스를 이용하기 위한 필수항목과 선택항목이 있는데, 
메일수신여부 등과 같은 선택 항목은 입력하지 않더라도 서비스 이용에는 제한이 없습니다.

[진료정보]
- 수집항목 : 성명, 주민등록번호, 주소, 연락처, 진료기록

※ 의료법에 의해 고유식별정보 및 진료정보를 의무적으로 보유하여야 함 (별도 동의 불필요)

[진료 상담, 예약을 위한 글 작성 시 수집항목] 
- 필수항목 : 성명, 비밀번호, 연락처(휴대폰번호) 이메일 주소 
- 선택 항목: 이메일, 메일수신여부 

[홈페이지 회원가입 시 수집항목]
- 필수항목 : 성명, 아이디, 비밀번호, 연락처(전화번호, 휴대폰번호)
- 선택항목 : 이메일, 메일수신여부
- 서비스 이용 과정이나 서비스 제공 업무 처리 과정에서 다음과 같은 정보들이 자동으로 생성되어 수집될 수 있습니다. : 서비스 이용기록, 접속 로그, 쿠키, 접속 IP 정보

[개인정보 수집방법]
- 다음과 같은 방법으로 개인정보를 수집합니다.
홈페이지, 서면양식, 팩스, 전화, 상담 게시판, 예약 게시판, 이메일


■ 개인정보의 수집 및 이용목적

본원은 수집한 개인정보를 다음의 목적을 위해 활용합니다.
이용자가 제공한 모든 정보는 하기 목적에 필요한 용도 이외로는 사용되지 않으며 
이용 목적이 변경될 시에는 사전 동의를 구할 것입니다.

[진료정보]
- 진단 및 치료를 위한 진료서비스와 청구, 수납 및 환급 등의 원무서비스 제공

[홈페이지 회원정보]
- 필수정보 : 홈페이지를 통한 진료 예약, 예약조회 및 회원제 서비스 제공
- 선택정보 : 이메일을 통한 병원소식, 질병정보 등의 안내, 설문조사, 서비스 이용에 대한 통계수집 


■ 개인정보의 보유 및 이용기간

병원은 개인정보의 수집목적 또는 제공받은 목적이 달성된 때에는 귀하의 개인정보를 지체없이 파기합니다.

[진료정보]
- 의료법에 명시된 진료기록 보관 기준에 준하여 보관

[홈페이지 회원정보]
- 회원가입을 탈퇴하거나 회원에서 제명된 때
다만, 수집목적 또는 제공받은 목적이 달성된 경우에도 상법 등 법령의 규정에 의하여 보존할 필요성이 있는 
경우에는 귀하의 개인정보를 보유할 수 있습니다.

- 소비자의 불만 또는 분쟁처리에 관한 기록 : 3년 
(전자상거래 등에서의 소비자보호에 관한 법률) 

- 신용정보의 수집/처리 및 이용 등에 관한 기록 : 3년
(신용정보의 이용 및 보호에 관한 법률) 

- 본인 확인에 관한 기록 : 6개월 (정보통신망 이용촉진 및 정보보호 등에 관한 법률)

- 방문에 관한 기록 : 3개월 (통신비밀보호법)

■ 개인정보의 파기절차 및 그 방법

병원은 『개인정보의 수집 및 이용목적』이 달성된 후에는 즉시 파기합니다. 파기절차 및 방법은 다음과 같습니다.

[파기절차]
이용자가 회원가입 등을 위해 입력한 정보는 목적이 달성된 후 파기방법에 의하여 즉시 파기합니다.

[파기방법]
전자적 파일형태로 저장된 개인정보는 기록을 재생할 수 없는 기술적 방법을 사용하여 삭제합니다. 
종이에 출력된 개인정보는 분쇄기로 분쇄하거나 소각하여 파기합니다.

■ 개인정보 제공 및 공유

병원은 귀하의 동의가 있거나 관련법령의 규정에 의한 경우를 제외하고는 어떠한 경우에도
『개인정보의 수집 및 이용목적』에서 고지한 범위를 넘어 귀하의 개인정보를 이용하거나
 타인 또는 타 기업ㆍ기관에 제공하지 않습니다.

- 통계작성ㆍ학술연구를 위하여 필요한 경우 특정 개인을 알아볼 수 없는 형태로 가공하여 제공
- 법령에 정해진 절차와 방법에 따라 수사기관의 요구가 있는 경우 제출 등

■ 수집한 개인정보의 취급위탁

본원은 고객님의 동의없이 고객님의 정보를 외부 업체에 위탁하지 않습니다.
향후 그러한 필요가 생길 경우, 위탁 대상자와 위탁 업무 내용에 대해 고객님에게 통지하고 필요한 경우 사전 동의를 받도록 하겠습니다.

■ 이용자 및 법정대리인의 권리와 그 행사방법

만14세 미만 아동(이하 "아동"이라 함)의 회원가입은 아동이 이해하기 쉬운 평이한 표현으로 작성된 별도의 양식을 통해 이루어지고 있으며 개인정보 수집 시 반드시 법정대리인의 동의를 구하고 있습니다.

병원은 법정대리인의 동의를 받기 위하여 아동으로부터 법정대리인의 성명과 연락처 등 최소 한의 정보를 수집하고 있으며, 개인정보취급방침에서 규정하고 있는 방법에 따라 법정대리인의 동의를 받고 있습니다.

아동의 법정대리인은 아동의 개인정보에 대한 열람, 정정 및 삭제를 요청할 수 있습니다. 
아동의 개인정보를 열람, 정정 및 삭제 하고자 할 경우에는 회원정보수정을 클릭하여 
법정대리인 확인 절차를 거치신 후 아동의 개인정보를 법정대리인이 직접 열람, 정정 및 삭제 하거나, 개인정보보호책임자로 서면, 전화, 또는 Fax등으로 연락하시면 필요한 조치를 취합니다.

병원은 아동에 관한 정보를 제3자에게 제공하거나 공유하지 않으며, 아동으로부터 수집한 개인정보에 대하여 법정대리인이 오류의 정정을 요구하는 경우 그 오류를 정정할 때까지 해당 개인정보의 이용 및 제공을 금지합니다.

※ 법에 의해 보관이 의무화된 개인정보는 요청이 있더라도 보관 기간 내에 수정·삭제할 수 없습니다.


■ 동의철회 / 회원탈퇴 방법

귀하는 회원가입 시 개인정보의 수집ㆍ이용 및 제공에 대해 동의하신 내용을 언제든지
철회 하실 수 있습니다. 회원탈퇴는 병원 홈페이지 정보수정의 『회원탈퇴』를 클릭하여
본인 확인 절차를 거치신 후 직접 회원탈퇴를 하시거나, 개인정보관리책임자에게 
서면, 전화 또는 Fax 등으로 연락하시면 지체 없이 귀하의 개인정보를 파기하는 등 필요한 조치를 하겠습니다.

■ 개인정보 자동 수집 장치의 설치/운영 및 그 거부에 관한 사항

병원은 귀하의 정보를 수시로 저장하고 찾아내는 '쿠키(cookie)'를 운용합니다. 
쿠키란 병원의 웹사이트를 운영하는데 이용되는 서버가 귀하의 브라우저에 보내는
아주 작은 텍스트 파일로서 귀하의 컴퓨터 하드디스크에 저장됩니다.
병원은 다음과 같은 목적을 위해 쿠키를 사용합니다.

귀하는 쿠키 설치에 대한 선택권을 가지고 있습니다. 
따라서, 귀하는 웹 브라우저에서 옵션을 설정함으로써 모든 쿠키를 허용하거나,
쿠키가 저장될 때마다 확인을 거치거나, 아니면 모든 쿠키의 저장을 거부할 수도 있습니다.

회원님께서 쿠키 설치를 거부하셨을 경우 일부 서비스 제공에 어려움이 있습니다.


■ 개인정보관리책임자

귀하의 개인정보를 보호하고 개인정보와 관련한 불만을 처리하기 위하여 병원은
아래와 같이 개인정보관리책임자를 두고 있습니다.

[개인정보 관리책임자]
이름 : 
직위 : 
소속 : 
전화번호 : 
메일 : 

귀하께서는 병원의 서비스를 이용하시며 발생하는 모든 개인정보보호 관련 민원을 개인정보관리책임자에게 신고하실 수 있습니다. 병원은 이용자들의 신고사항에 대해 신속하게 충분한 답변을 드릴 것입니다. 기타 개인정보침해에 대한 신고나 상담이 필요하신 경우에는 아래 기관에 문의하시기 바랍니다.

개인분쟁조정위원회 (http://www.1336.or.kr / T. 1336) 
정보보호마크인증위원회 (http://www.eprivacy.or.kr / T. (02) 580-0533～4)
대검찰청 사이버범죄수사단 (http://www.spo.go.kr / T. (02) 3480-3573)
경찰청 사이버테러대응센터 (http://www.ctrc.go.kr / T. (02) 392-0330)


■ 개인정보의 안전성 확보조치

병원은 이용자의 개인정보보호를 위한 기술적 대책으로서 여러 보안장치를 마련하고 있습니다.
이용자께서 보내시는 모든 정보는 방화벽장치에 의해 보호되는 보안시스템에 안전하게 보관/관리되고 있습니다.

또한 병원은 이용자의 개인정보보호를 위한 관리적 대책으로서 이용자의 개인정보에 대한 
접근 및 관리에 필요한 절차를 마련하고, 이용자의 개인정보를 취급하는 인원을 최소한으로
제한하여 지속적인 보안교육을 실시하고 있습니다. 또한 개인정보를 처리하는 시스템의 사용자를 
지정하여 사용자 비밀번호를 부여하고 이를 정기적으로 갱신하겠습니다. 


■ 정책 변경에 따른 공지의무

이 개인정보취급방침은 2021 년 1월 1일에 제정되었으며 법령ㆍ정책 또는 보안기술의
변경에 따라 내용의 추가ㆍ삭제 및 수정이 있을 시에는 변경되는 개인정보취급방침을
시행하기 최소 7일전에 병원 홈페이지를 통해 변경이유 및 내용 등을 공지하도록 하겠습니다.

공고일자 : 2021 년 1 월 1 일 
시행일자 : 2021 년 1 월 1 일  
    </textarea>
    <br />
    <table width="750" border="0">
    <tr align="left"><td>
    <input type="radio" name="infor" value="agree" /> 개인정보 취급방침에 동의합니다.</td></tr><br /></table>
     <br/>
     <br/>
     <br/>
     <input type="button" value= "가입취소" onclick="before();"/>
     <input type="button" value= "회원가입" onclick="return sub();"/>
	 </form>
	      </center> 
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