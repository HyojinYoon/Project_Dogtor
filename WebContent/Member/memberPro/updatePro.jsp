<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="memberDAO.MemberDAO" %>
<%@ page import="memberDTO.MemberDTO" %>
<%@ page import="memberDTO.PetDTO" %>
<%@ page import="java.util.ArrayList" %>
    
<%	request.setCharacterEncoding("UTF-8"); %> 
   
    
    <jsp:useBean class="memberDTO.MemberDTO" id="updatemember" />
    <jsp:setProperty name="updatemember" property="pw" param="pw" />
    <jsp:setProperty name="updatemember" property="name" param="name" />
    <jsp:setProperty name="updatemember" property="birth" param="birth" />
    <jsp:setProperty name="updatemember" property="phone" param="phone" />
     <%   	
    	// 고객정보 수정
    	String id = (String)session.getAttribute("memId");
		String nick = (String)session.getAttribute("memNick");
		updatemember.setId(id);
		updatemember.setNick(nick);
    	
    	// 애완동물정보 수정
    	String[] p_name = request.getParameterValues("p_name");
    	String[] p_birth = request.getParameterValues("p_birth");
    	String[] p_gender = request.getParameterValues("p_gender");
    	String[] p_kind = request.getParameterValues("p_kind");
    	String[] p_etc = request.getParameterValues("p_etc");
    	String[] num = request.getParameterValues("num");

    	MemberDAO updatedao = new MemberDAO();
    	// 수정된 애완동물 정보 저장
    	if(p_name != null)
    	{
    		ArrayList<PetDTO> petList = new ArrayList<PetDTO>();
    		for(int i=0; i<p_name.length; i++)
    		{
    			PetDTO p_dto = new PetDTO();
    			p_dto.setId(id);
    			p_dto.setP_name(p_name[i]);
    			p_dto.setP_birth(p_birth[i]);
    			p_dto.setP_gender(p_gender[i]);
    			p_dto.setP_kind(p_kind[i]);
    			p_dto.setP_etc(p_etc[i]);
    			p_dto.setNum(Integer.parseInt(num[i]));
    			petList.add(p_dto);
    		}
    		
        	updatedao.updatePetDel(id, petList);
    		updatedao.updateMember(updatemember);
        	updatedao.updatePet(petList);
    	}
    	else
    	{	
    		updatedao.updateMember(updatemember);	
    		updatedao.updatePetDel(id);	
    	}
    	
    
    %>
    
    <script>
    	alert("회원정보가 수정되었습니다.");
    	window.location="/dogtor/Member/memberForm/mypageForm.jsp";
    </script>