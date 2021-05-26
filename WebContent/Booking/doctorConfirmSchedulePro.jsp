<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "memberDAO.MemberDAO" %>
<%@ page import = "memberDTO.MemberDTO" %>
<%@ page import="dogtor.DTO" %>
<%@ page import="dogtor.DAO" %>    
<%@ page import="java.util.ArrayList" %>
<%@ page import = "java.util.List" %>
<jsp:useBean class = "memberDTO.MemberDTO" id="dto" />
<jsp:setProperty name = "dto" property = "*" />
	
<% 	request.setCharacterEncoding("UTF-8");
	String code = request.getParameter("code");
	String reserve_date = request.getParameter("reserve_date");
%>
	<center><img src="/dogtor/save/doctorConfirmSchedule.png" width="1400" /></center><br />	
<%//예약확인영역.(like 게시판)
    int pageSize = 10;

    String pageNum = request.getParameter("pageNum");  //113번 줄 for 문 통해서 pageNum 전달 받는다
    if (pageNum == null) { //안넘어올수도 있고 넘어올수 도 있는 파라메터
        pageNum = "1";
    }

    int currentPage = Integer.parseInt(pageNum); //현재 보여주는 페이지 이다.
    int startRow = (currentPage - 1) * pageSize + 1;  //받아온 페이지 number에 따라서 10개의 게시글을 한 화면에 보여주기 위해서 시작과 끝을 정해주는 것
    int endRow = currentPage * pageSize;
    int count = 0; //전체 게시글의 갯수를 저장할 변수
    int number=0; //화면 글 번호. 데이터 베이스 저장 글 번호갸 아니다.\

	
    List reserveList = null;
    DAO reserveDao =new DAO(); //DAO 생성 //dbpro였음
    
    //code를 기준으로 데이터 베이스에 있는 게시글 수를 받아온다.
    count = reserveDao.getDoctorScheduleCount(code,reserve_date); // 메서드 호출 crtl 누르며 마우스 클리하면 해당 메서드로 이동한다.
  	
    //code를 기준으로 schedule이 하나라도 있으면, 해당 schedule을 ArrayList로 받아온다.(일정 정보 받아오기)
    if (count > 0) {
       reserveList = reserveDao.getDoctorSchedules(startRow, endRow, code, reserve_date);
    }
	number=count-(currentPage-1)*pageSize; //다른 페이지 때문에 계산해 주는것.
%>
<html>
<head>
<title>외출신청</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body>
<center><b>글목록(전체 글:<%=count%>)</b> <%-- <center> 전체 가운데 정렬 --%>

<%if (count == 0) {%>
	<table width="900" border="1" cellpadding="0" cellspacing="0">
		<tr>
    		<td align="center">
    			진료 일정이 없습니다.
    		</td>
    	</tr>
	</table>


<%  } else {%>
<table border="1" width="1400" cellpadding="0" cellspacing="0" align="center">
	<tr height="30" >
		<td align="center"  width="150"  >진료 날짜</td>
		<td align="center"  width="150" >진료 시간</td>
		<td align="center"  width="100" >일정 상태</td>
	    <td align="center"  width="100" >외출 신청</td>
	    <td align="center"  width="100" >외출 취소</td>
	    <td align="center"  width="800" >외출 이유</td>
    </tr>
    
<%	//테이블에 표시할 게시글 정보를 보여준다.email 기준으로 리스트업
	for (int i = 0 ; i < reserveList.size() ; i++) {
    	DTO reserve = (DTO)reserveList.get(i); //반복할때마다 테이블 늘어난다.
%>
	<tr height="30">             <%--number 감소하고 있음. --%>
    	<td align="center"  width="150" > <%=reserve.getReserve_date()%></td> <%--진료 날짜 --%>
    	<td align="center" width="150" ><%--진료 시간 --%>
    			<%=reserve.getReserve_time()%>
    	</td>
    	<td align="center"  width="100"> <%--일정 상태 --%>
				<%if(reserve.getStatus().equals("0")){%>
				(예약대기)
				<%}if(reserve.getStatus().equals("1")){%>
				(예약중)
				<%}if(reserve.getStatus().equals("2")){%>
				(외출)
				<%}%>
		</td>
		<%--외출 신청버튼 --%>
    		<form action="doctorBreakScheduleForm.jsp" method="post">
				<input type="hidden" name="code" value="<%=reserve.getCode()%>" />
				<input type="hidden" name="reserve_date" value="<%=reserve.getReserve_date()%>" />
				<input type="hidden" name="reserve_time" value="<%=reserve.getReserve_time()%>" />
    				<td align="center"  width="100">
    				<%if(reserve.getStatus().equals("0")){%>
    					<input type="submit" value="외출 신청"  />
    			<%}else{%>
    			(비활성화)
    			<%}%>
    		</form>	
    		</td>
    	<%--외출 취소버튼 --%>
    	<form action="doctorBreakScheduleCancle.jsp" method="post">
			<input type="hidden" name="code" value="<%=reserve.getCode()%>" />
			<input type="hidden" name="reserve_date" value="<%=reserve.getReserve_date()%>" />
			<input type="hidden" name="reserve_time" value="<%=reserve.getReserve_time()%>" />
    		<td align="center"  width="100">
    		<%if(reserve.getStatus().equals("2")){%>
    			<input type="submit" value="외출 취소"  /></td>
    		<%}else{%>
    		(비활성화)
    		<%}%>
    	</form>
    	<td align="center"  width="800">
    	<%--외출 이유 --%>
    	<%if(reserve.getReason() == null){%>
    		외출 상태가 아닙니다.	
    	<%}else{%>
    	<%=reserve.getReason()%></td>
		<%}%>
	</tr>
    <%}%>
</table>
<%}%>

<%
	//아래 페이지 넘버 표시
    if (count > 0) { //게시글이 하나라도 있으면,
    	
    	//페이지 총 카운팅 수 저장 변수 10자리 보다 하나라도 더 있으면 페이지 하나 추가해줌.
        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
		 
        //시작페이지 어디서부터 보여줄지.
        int startPage = (int)(currentPage/10)*10+1;
		int pageBlock=10;
		//
        int endPage = startPage + pageBlock-1;
        if (endPage > pageCount) endPage = pageCount;

    }
%>
<center><h3><%=code%> 선생님 외출 일정 꼼꼼히 확인해주세요.</h3></center>

<br /><br />
<h3>메인화면으로 가고 싶다면?</h3>
	<input type="button" value="메인가기" onclick="goMain()" />
</center>
</body>
</html>	

<script>
	//외출일정 달력 메인페이지로 이동.
	function goMain(){
		opener.location.href ="/dogtor/Doctor/DoctorHome.jsp";
		self.close();
	}
</script>


