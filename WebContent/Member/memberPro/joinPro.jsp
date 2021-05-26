<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="memberDAO.MemberDAO" %>
<%@ page import="memberDTO.PetDTO" %>
<%@ page import="java.util.ArrayList" %>


<% request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean class="memberDTO.MemberDTO" id="joinmember" />
<jsp:setProperty name="joinmember" property="idEmail" param="idEmail" />
<jsp:setProperty name="joinmember" property="siteEmail" param="siteEmail" />
<jsp:setProperty name="joinmember" property="pw" param="pw" />
<jsp:setProperty name="joinmember" property="name" param="name" />
<jsp:setProperty name="joinmember" property="birth" param="birth" />
<jsp:setProperty name="joinmember" property="nick" param="nick" />
<jsp:setProperty name="joinmember" property="phone" param="phone" />

<%
	// 각 속성의 열값들을 저장
	String eid = request.getParameter("idEmail")+"@"+request.getParameter("siteEmail");
	String[] p_name = request.getParameterValues("p_name");
	String[] p_birth = request.getParameterValues("p_birth");
	String[] p_gender = request.getParameterValues("p_gender");
	String[] p_kind = request.getParameterValues("p_kind");
	String[] p_etc = request.getParameterValues("p_etc");
	
	// 각 속성의 행값들을 저장
	ArrayList<PetDTO> petList = new ArrayList<PetDTO>(); 
	for(int i=0; i<p_name.length; i++)
	{
		PetDTO p_dto = new PetDTO();	// 각 속성의 행값들을 저장
		p_dto.setId(eid);
		p_dto.setP_name(p_name[i]);
		p_dto.setP_birth(p_birth[i]);
		p_dto.setP_gender(p_gender[i]);
		p_dto.setP_kind(p_kind[i]);
		p_dto.setP_etc(p_etc[i]);
		petList.add(p_dto);				// 각 dto 저장
	}
	
	MemberDAO dao = new MemberDAO();
	dao.insert(joinmember);
	dao.insert(petList);
%>

<script>
	confirm("<%=joinmember.getNick() %>님, 환영합니다 :)")
    window.location="/dogtor/Member/memberForm/loginForm.jsp"
</script>