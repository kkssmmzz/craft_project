<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>    
<%-- writeForm.jsp --%>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>writeForm.jsp</title>
  <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="script.js" type="text/javascript"></script>
  <link href="style.css" rel="stylesheet" type="text/css">
  </link>
  
</head>
<body>
<br>
<center><h2>글쓰기</h2></center>

     <c:if test="${sessionScope.memId!='admin'}">
       <script>
    alert("관리자만 이용 가능합니다. 관리자 계정으로 로그인해주세요.");
       </script>
       <Meta http-equiv="Refresh" content="0;url=${ctxpath}/craft_board_noti/list.do">
     </c:if>
<form name="writeform" method="post" action="${ctxpath}/craft_board_noti/writePro.do" onSubmit="return writeSave()" >

  <input type="hidden" name="noti_num" value="${noti_num}">

  
  <table id="con" width="700" cellpadding="4" align="center">
    <tr class="bot">
      <td colspan="2" align="right">
        <a href="${ctxpath}/craft_board_noti/list.do"><font size=4><b>목록으로</b></font></a>
      </td>
    </tr>
    
    <!-- 글쓴이 -->
    <tr class="bot">
      <td bgcolor="#f4f4f4">글쓴이</td>
      <td><input type="text" name="noti_writer" id="noti_writer" value="admin" size="55"></td>
    </tr>
    
    <!-- 글제목 -->
    <tr class="bot">
      <td bgcolor="#f4f4f4">제목</td>
      <td>
        <!-- 원글 -->
        <c:if test="${noti_num==0}">
          <input type="text" name="noti_subject" id="noti_subject" size="55">
        </c:if>
      </td>
    </tr>
    
    <!-- 글내용 -->
    <tr class="bot">
      <td bgcolor="#f4f4f4">글내용</td>
      <td>
        <textarea name="noti_content" id="noti_content" rows="10" cols="60"></textarea>
      </td>
    </tr>
    
    
    <!-- 암호 -->
    <tr class="bot">
      <td bgcolor="#f4f4f4">암호</td>
      <td><input type="password" name="noti_pw" id="noti_pw" size="55"></td>
    </tr>
    
    <tr>
      <td colspan="2" align="center"> 
        <!-- 글쓰기 완료 -->
        <c:if test="${noti_num==0}">
          <br>
          <input type="submit" value="글쓰기">
          <input type="button" value="취소" onClick="location='${ctxpath}/craft_board_noti/list.do'">
          <br>
        </c:if>
      </td>
    </tr>
    
  </table>
</form>
</body>
</html>