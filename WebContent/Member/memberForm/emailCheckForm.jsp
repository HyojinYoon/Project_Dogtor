<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="memberDAO.MemberDAO" %>
<%@ page import="memberDTO.MemberDTO" %>
<%@ page import="check.ValueCheck" %>

<%//세션검사 지우면 안됩니다. 헤더에서 사용중입니다. 만약 본인 내용에 세션 id 정검이 있을 경우 그 내용을 지워주세요.
    String id = (String)session.getAttribute("memId");
    int status = 0;
    if(id != null){
    	status= (int)session.getAttribute("memStatus");
	 }%>
	 
<%	// 세션이 있는 상태에서 loginForm으로 들어오지 못하게 하기
	if(id != null && status != 50)
	{%>
		<script>
			alert("로그인 상태입니다.");
			window.location = "/dogtor/Member/memberForm/dogtorHome.jsp";
		</script>
  <%}%>


<script>
	
if(opener == null)
{
	alert("잘못된 경로입니다.");
	window.location = '/dogtor/Member/memberForm/dogtorHome.jsp';
}
	function useId()
	{
		opener.document.join.idDuplication.value = "idCheck";
		
		if(opener != null)
		{
			opener.emailCheckForm = null;
			self.close();
		}
	}
</script>



<center>
<%
	String idEmail = request.getParameter("idEmail");
	String siteEmail = request.getParameter("siteEmail");
	MemberDAO dao = new MemberDAO();
	MemberDTO m_dto = new MemberDTO();
	ValueCheck vc = new ValueCheck();
	
	m_dto.setId(idEmail, siteEmail);
	String idcheck = m_dto.getId();
	boolean result = dao.emailCheck(idcheck);
	
	if(vc.checkNull(idEmail) && vc.checkNull(siteEmail))
	{
		boolean cbId = vc.charBlank(idEmail);
		boolean cbSite = vc.charBlank(siteEmail);
		boolean cs = vc.charSpecial(siteEmail, 46);
		
		if(cbId && cbSite && cs)
		{
			if(result)
			{%> 
				<h3>입력하신</h3> <h1>[<%=idcheck%>]<h1/><h3>는 사용가능한 ID입니다.</h3>	
				<input type="button" value="사용하기" onclick="useId()" />
		  <%}
			else
			{%> 
				<h3>이미 존재하는 ID입니다. 다시 입력해주세요.</h3>	
				<input type="button" value="닫기" onclick="window.close();" />
		  <%}
		}
		else
		{%> <h3>ID를 잘못입력하였습니다.</h3><h3> 다시 입력해주세요.</h3> 
			<input type="button" value="닫기" onclick="window.close();" />
	  <%}
	}
	else
	{%> <h3>ID를 입력해주세요.</h3> 
		<input type="button" value="닫기" onclick="window.close();" />
  <%}%>
</center>
    
    