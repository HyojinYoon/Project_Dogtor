<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dogtor.DTO" %>
<%@ page import="dogtor.DAO" %>  

<%request.setCharacterEncoding("UTF-8");
  String code = request.getParameter("code");
  String reserve_date = request.getParameter("reserve_date");
  String reserve_time = request.getParameter("reserve_time");
  String pageType = request.getParameter("pageType");
  	
  DAO dao = new DAO();
  //reserve 내역 삭제
  dao.Delete(code, reserve_date, reserve_time); 
  //선생님 스케쥴 재생성
  dao.cancel(code, reserve_date, reserve_time);

 %>
 	<%=pageType%>	
  <script>
  	if(<%=pageType%> != null){
  		//회원 예약확인
  		if(<%=pageType.equals("1")%>){
  		window.location = "/dogtor/Booking/reserveCheckPro.jsp?code=<%=code%>&reserve_date=<%=reserve_date%>";
  		alert("삭제가 완료 되었습니다.")
  		}
  		//의사진료확인
  	  	if(<%=pageType.equals("2")%>){
  	  	window.location = "/dogtor/Booking/doctorConfirmReservePro.jsp?code=<%=code%>&reserve_date=<%=reserve_date%>";
  	  	alert("삭제가 완료 되었습니다.")
  	  	}	
  		//비회원 예약확인
  	  	if(<%=pageType.equals("3")%>){
  	  	window.location = "/dogtor/Member/memberForm/dogtorHome.jsp";
  	  	  	alert("삭제가 완료 되었습니다.")
  	  	  	}	
  	}
  	
 </script>
