<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>    
<%-- content.jsp --%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>content.jsp</title>
  <link href="style.css" rel="stylesheet" type="text/css"></link>
</head>
<body>
   <br>
   <center><h2>공지사항<h2></center>

  <table id="con" class="top" width="600" cellpadding="4" align="center">
  
    <tr class="bot">  
      <td bgcolor="#f4f4f4">작성자</td>
      <td>${dto.noti_writer}</td>
      
      <td bgcolor="#f4f4f4">작성일</td>
      <td>${dto.noti_regdate}</td>
    </tr>
    
    <tr class="bot">
      <td bgcolor="#f4f4f4">글번호</td>
      <td>${dto.noti_num}</td>
  
      <td bgcolor="#f4f4f4">조회수</td>
      <td>${dto.noti_readcount}</td>
    </tr>
    
    <tr class="bot">
      <td bgcolor="#f4f4f4">글제목</td>
      <td colspan="3">${dto.noti_subject}</td>
    </tr>
    
    
    
    <tr class="bot">
      <td colspan=4 bgcolor="white">${dto.noti_content}</td>
    </tr>
    
    <tr>
      <td colspan=4 align="center">
      <c:if test="${sessionScope.memId=='admin'}">
        <br>
        <input type="button" value="글수정" onClick="location='${ctxpath}/craft_board_noti/updateForm.do?noti_num=${noti_num}&pageNum=${pageNum}'">
        <input type="button" value="글삭제" onClick="location='${ctxpath}/craft_board_noti/deleteForm.do?noti_num=${noti_num}&pageNum=${pageNum}'">
        <input type="button" value="목록" onClick="location='${ctxpath}/craft_board_noti/list.do?noti_num=${noti_num}&pageNum=${pageNum}'">
      </c:if>
      <c:if test="${sessionScope.memId!='admin'}">
        <br>
        <input type="button" value="목록" onClick="location='${ctxpath}/craft_board_noti/list.do?noti_num=${noti_num}&pageNum=${pageNum}'">
      </c:if>
       
      </td>
    </tr>
    
  </table>
  <br>
</body>
</html>