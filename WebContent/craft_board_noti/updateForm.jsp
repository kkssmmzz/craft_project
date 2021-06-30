<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>    
<%-- updateForm.jsp --%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>updateForm.jsp</title>
  <link href="style.css" rel="stylesheet" type="text/css">
  </link>
  
  <script src="script.js" type="text/javascript"></script>
</head>
<body>
  <br>
  <center><h2>글수정</h2></center>

  <form name="writeform" method="post" action="${ctxpath}/craft_board_noti/updatePro.do?noti_num=${noti_num}&pageNum=${pageNum}">
    <table id="con" class="top" width="700" cellpadding="4">
      <tr class="bot">
        <td bgcolor="#f4f4f4">글쓴이</td>
        <td>
          <input type="text" name="noti_writer" id="noti_writer" size="20" value="${dto.noti_writer}">
          <input type="hidden" name="noti_num" value="${noti_num}">
        </td>
      </tr>
      
      <tr class="bot">
        <td bgcolor="#f4f4f4">제목</td>
        <td>
          <input type="text" name="noti_subject" id="noti_subject" size="40" value="${dto.noti_subject}">
        </td>
      </tr>
      
      <tr class="bot">
        <td bgcolor="#f4f4f4">글내용</td>
        <td>
          <textarea name="noti_content" id="noti_content" rows="10" cols="60">${dto.noti_content}</textarea>
        </td>
      </tr>
      
      <tr class="bot">
        <td bgcolor="#f4f4f4">암호</td>
        <td>
          <input type="password" name="noti_pw" id="noti_pw">
        </td>
      </tr>
      
      <tr>
        <td colspan="2" align="center">
          <br>
          <input type="submit" value="글수정" >
          <input type="button" value="취소" onClick="location='${ctxpath}/craft_board_noti/list.do?pageNum=${pageNum}'">
        </td>
      </tr>
      
    </table>
    <br>
    </form>
</body>
</html>