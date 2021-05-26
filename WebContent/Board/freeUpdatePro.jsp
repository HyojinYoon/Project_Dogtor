<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import = "board.DAO" %>
<%@ page import = "board.DTO" %>
<%@ page import = "java.sql.Timestamp" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>

<% request.setCharacterEncoding("UTF-8");%>

<%
 
    
    DTO dto = new DTO();
	DAO dao = new DAO();
	String path = request.getRealPath("save");         // 업로드파일 저장 경로
	String enc = "UTF-8";
	int size = 1024*1024*10;                     // 10MB
	DefaultFileRenamePolicy dp = new DefaultFileRenamePolicy();
	MultipartRequest mr = new MultipartRequest(request,path,size,enc,dp);
	
	String id = (String)session.getAttribute("memId");
	String nick = (String)session.getAttribute("memNick");
	int num = Integer.parseInt(mr.getParameter("num"));
	String pageNum = mr.getParameter("pageNum");
	String writer = mr.getParameter("writer");
	String subject = mr.getParameter("subject");
	String content = mr.getParameter("content");
	String save = mr.getFilesystemName("save");
	String save1 = mr.getParameter("save1");
	System.out.println(save+"==="+save1);
	if(save == null){
		save = save1;
	}
	String head = mr.getParameter("head");
	String passwd = mr.getParameter("passwd");
	
	dto.setWriter(writer);
	dto.setSave(save); 
    dto.setSubject(subject);
	dto.setContent(content);
	dto.setNum(num);
	dto.setHead(head);
	
	boolean check = dao.nickCheck(nick, passwd, dto);

    if(check){
%>
	  <meta http-equiv="Refresh" content="0;url=freeList.jsp?pageNum=<%=pageNum%>" >
<% }else{%>
      <script language="JavaScript">      
      
         alert("비밀번호가 맞지 않습니다");
         history.go(-1);

      </script>
<%
}
%>  
 