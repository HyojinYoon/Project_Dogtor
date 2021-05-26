<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="dogtor.doctorDAO" %> 
<%@ page import="dogtor.doctorDTO" %> 


<jsp:useBean id="dto" class="dogtor.doctorDTO"/>
<jsp:setProperty property="*" name="dto" />


<%

	String id = request.getParameter("id");
	String pw = request.getParameter("pw");

	
	
	
	doctorDAO dao = new doctorDAO();
	
	boolean result= dao.loginCheck(id, pw);
	
	if(result){
		
		doctorDTO dto2 = dao.getDoctor(dto.getId());
		String code = dto2.getCode();
		session.setAttribute("docId", dto.getId());
		session.setAttribute("docCode",dto2.getCode());	
		
		
%>

<script>
window.location.href="/dogtor/Doctor/DoctorHome.jsp";
</script>

		
<%	}else{%>
		<script>
			alert("입력하신 정보가 올바르지 않습니다.");
			window.location.href="/dogtor/Doctor/DocLogin.jsp";
		</script>
  <%}%>



