<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>    
<%--deleteForm.jsp --%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>deleteForm.jsp</title>
  <link href="style.css" rel="stylesheet" type="text/css"></style>
  
  <c:if test="${empty sessionScope.memId}">
  	<script>
  		alert("로그인 하셔야 삭제가 가능합니다");
  	</script>
  	<Meta http-equiv="Refresh" content="0;url=${ctxpath}/craft_member/main.do">
  </c:if>

  <script>
  function check(){
	  if(document.delForm.qna_pw.value==''){
		  alert("암호를 입력 하세요");
		  document.delForm.qna_pw.focus();
		  return false;
	  }//if-end
	  return true
  }//check()-end
  </script>
</head>
<body>
<h2 align="center">글 삭제하기</h2>
<form name="delForm" method="post" action="${ctxpath}/craft_board_qna/deletePro.do" onSubmit="return check()">
 <table width="300" id="con" class="top" cellpadding="4">
 
   <tr>
     <td id="mid" bgcolor="#f4f4f4">
               암호를 입력하세요
     </td>
   </tr>
   
   <tr>
    <td id="mid" bgcolor="#f4f4f4">
    암호입력 : <input type="password" name="qna_pw" size="10">
    <input type="hidden" name="qna_pageNum" value="${qna_pageNum}">
    <input type="hidden" name="qna_num" value="${qna_num}">
    </td>
   </tr>
   
   <tr>
     <td id="mid" bgcolor="#f4f4f4">
       <input type="submit" value="글삭제">
       <input type="button" value="글목록" onClick="location='${ctxpath}/craft_board_qna/list.do?qna_pageNum=${qna_pageNum}'">
     </td>
   </tr>
 </table>
</form>
</body>
</html>