<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    int num = Integer.parseInt(request.getParameter("num"));
    int ref=Integer.parseInt(request.getParameter("ref"));
    int re_step=Integer.parseInt(request.getParameter("re_step"));
    int re_level=Integer.parseInt(request.getParameter("re_level"));
    String pageNum = request.getParameter("pageNum");
// 삭제하시겠습니까? 출력 후 삭제 진행
%>
<%--alert는 확인버튼만 나오고, confirm은 확인과 취소가 있다.  confirm은 확인은 true, 취소는 false return--%>
<script>
     var re = confirm("<%=num%>번 글을 삭제하시겠습니까?");
     if(re){
    	 window.location="deleteProQna.jsp?num=<%=num%>&pageNum=<%=pageNum%>&ref=<%=ref%>&re_step=<%=re_step%>&re_level=<%=re_level%>";
     }else{
    	 history.go(-1);
     }
</script>