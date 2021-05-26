<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "memberDAO.MemberDAO" %>
<%@ page import = "memberDTO.MemberDTO" %>


<jsp:useBean class="memberDTO.MemberDTO" id="m_dto" />
<jsp:setProperty name="m_dto" property="*" />

<%
	// MemberDAO에서 id와 pw확인하기
	MemberDAO dao = new MemberDAO();
	boolean result = dao.loginCheck(m_dto);
	
	if(result)
	{
		MemberDTO n_dto = dao.getMember(m_dto.getId());				// 해당 id의 닉네임과 status 호출
		session.setAttribute("memId", m_dto.getId());				// 세션 연결(id)
		session.setAttribute("memNick",n_dto.getNick());			// 세션 연결(닉네임)
		session.setAttribute("memStatus", n_dto.getStatus());		// 세션 연결(회원 상태)
  	    response.sendRedirect("/dogtor/Member/memberForm/dogtorHome.jsp"); // 메인 이동
	}
	else
	{%>
		<script>
			alert("아이디 혹은 비밀번호가 존재하지 않거나 맞지 않습니다.");
			history.go(-1);
		</script>
  <%}%>
  

