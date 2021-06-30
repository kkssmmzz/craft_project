<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/module/header.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>content.jsp</title>
	<link rel="stylesheet" href="style.css" type="text/css"></link>
</head>
<body>
<br>
	</h3><center><h2>자주묻는 질문(FAQ)<h2></center>
	
  <table id="con" class="top" width="700" cellpadding="4" align="center">
  
    <tr class="bot">  
      <td bgcolor="#f4f4f4" width="100" id="mid">작성자</td>
      <td>${dto.faq_writer}</td>
      
      <td bgcolor="#f4f4f4" width="100" id="mid">작성일</td>
      <td>${dto.faq_regdate}</td>
    </tr>
    
    <tr class="bot">
      <td bgcolor="#f4f4f4" width="100" id="mid">글번호</td>
      <td>${dto.faq_num}</td>
  
      <td bgcolor="#f4f4f4" width="100" id="mid">조회수</td>
      <td>${dto.faq_readcount}</td>
    </tr>
    
    <tr class="bot">
      <td bgcolor="#f4f4f4" width="100" id="mid">글제목</td>
      <td colspan="3">${dto.faq_subject}</td>
    </tr>
    
    
    
    <tr class="bot">
      <td colspan=4 bgcolor="white">${dto.faq_content}</td>
    </tr>
    
	<tr>
		<td colspan="2">
		<br>
		<c:if test="${sessionScope.memId=='admin'}">
			<input type="button" value="글수정" onClick="location='${ctxpath}/craft_board_faq/updateForm.do?faq_num=${faq_num}&faq_pageNum=${faq_pageNum}'">
			<input type="button" value="글삭제" onClick="location='${ctxpath}/craft_board_faq/deleteForm.do?faq_num=${faq_num}&faq_pageNum=${faq_pageNum}'">
			<input type="button" value="글쓰기" onClick="location='${ctxpath}/craft_board_faq/writeForm.do'">
		</c:if>
			<input type="button" value="리스트" onClick="location='${ctxpath}/craft_board_faq/list.do?faq_num=${faq_num}&faq_pageNum=${faq_pageNum}'">
		</td>
	</tr>
	</table>
	<br>
</body>
</html>
