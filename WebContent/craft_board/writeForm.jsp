<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>

<%--writeForm.jsp --%>

<c:if test="${empty sessionScope.memId}">
  <script>
  alert("로그인하셔야 글쓰기가 가능합니다");
  </script>
  <Meta http-equiv="Refresh" content="0;url=${ctxpath}/craft_member/main.do">
</c:if>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Insert title here</title>
  <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="script.js" type="text/javascript"></script>
  <link rel="stylesheet" href="style.css" type="text/css"></style>
</head>

<body onload="document.writeform.craft_writer.focus();">
<br> 
<h3>글쓰기,답글쓰기</h3>
<form name="writeform" method="post" action="${ctxpath}/craft_board/writePro.do" onSubmit="return writeSave()">

 <input type="hidden" name="craft_num" value="${craft_num}">
 <input type="hidden" name="craft_ref" value="${craft_ref}">
 <input type="hidden" name="craft_re_step" value="${craft_re_step}">
 <input type="hidden" name="craft_re_level" value="${craft_re_level}">
 
 <table id="con" width="600" cellpadding="4">
   <tr class="bot">
     <td colspan="2" align="right">
       <a href="${ctxpath}/craft_board/list.do">리스트</a>
     </td>
   </tr>
   
   <!-- 글쓴이 -->
   <tr class="bot">
     <td id="mid" bgcolor="#f4f4f4">글쓴이</td>
     <td><input type="text" name="craft_writer" id="craft_writer" size="20"></td>
   </tr>
   
   <!-- 글제목 -->
   
   <tr class="bot">
     <td id="mid" bgcolor="#f4f4f4">글제목</td>
     <td>
     <!-- 원글 -->
      <c:if test="${craft_num==0}">
        <input type="text" name="craft_subject" id="craft_subject" size="40">
      </c:if>
      
      <!-- 답글 -->
      <c:if test="${craft_num!=0}">
        <input type="text" name="craft_subject" id="craft_subject" size="40" value="[답변]">
      </c:if>
     </td>
   </tr>
   
   <!-- 글내용 -->
   <tr class="bot">
     <td id="mid" bgcolor="#f4f4f4">글내용</td>
     <td>
      <textarea name="craft_content" id="craft_content" rows="10" cols="60"></textarea>
     </td>
   </tr>
   
   <!-- 암호 -->
   <tr class="bot">
     <td id="mid" bgcolor="#f4f4f4">암호</td>
     <td><input type="password" name="craft_pw" id="craft_pw" size="20"></td>
   </tr>
   
   <tr>
     <td id="mid" colspan="2">
     <br>
       <!-- 원글 -->
       <c:if test="${craft_num==0}">
        <input type="submit" value="글쓰기">
       </c:if>
       
        <!-- 답글 -->
       <c:if test="${craft_num!=0}">
        <input type="submit" value="답글쓰기">
       </c:if>
       
       <input type="reset" value="다시쓰기">
       
       <input type="button" value="글목록" onclick="location='${ctxpath}/craft_board/list.do'">
     </td>
   </tr>
 </table>
 <br>
</form>

</body>
</html>