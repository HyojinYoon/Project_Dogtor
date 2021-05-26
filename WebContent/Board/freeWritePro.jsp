<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import = "board.DAO" %>
<%@ page import = "board.DTO" %>
<%@ page import = "java.sql.Timestamp" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>


<% 
   
   DAO dao = new DAO();
   DTO dto = new DTO();
   String path = request.getRealPath("save");         // 업로드파일 저장 경로
   String enc = "UTF-8";
   int size = 1024*1024*10;                     // 10MB
   DefaultFileRenamePolicy dp = new DefaultFileRenamePolicy();
   MultipartRequest mr = new MultipartRequest(request,path,size,enc,dp);
   MultipartRequest mReq = null;
   
   String id = (String)session.getAttribute("memId");
   String nick = dao.getNickname(id);
   int num = Integer.parseInt(mr.getParameter("num"));
   String pageNum = mr.getParameter("pageNum");
   int ref = Integer.parseInt(mr.getParameter("ref")); //그룹번호
   int re_step = Integer.parseInt(mr.getParameter("re_step")); //그룹내 게시순서
   int re_level = Integer.parseInt(mr.getParameter("re_level"));
   String writer = mr.getParameter("writer");
   String subject = mr.getParameter("subject");
   String content = mr.getParameter("content");
   String save = mr.getFilesystemName("save");
   String save1 = mr.getFilesystemName("save1");
   String save2 = mr.getFilesystemName("save2");
   String head = mr.getParameter("head");
   
   dto.setWriter(writer);
   dto.setSave(save);
   dto.setSave1(save1);
   dto.setSave2(save2);
   dto.setSubject(subject);
   dto.setContent(content);
   dto.setNum(num);
   dto.setRef(ref);
   dto.setRe_step(re_step);
   dto.setRe_level(re_level);
   dto.setHead(head);

   dto.setReg(new Timestamp(System.currentTimeMillis()) );

    
   dao.insertFreeArticle(dto);


    response.sendRedirect("freeList.jsp");

%>