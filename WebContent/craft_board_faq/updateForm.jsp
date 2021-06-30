<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>

<c:if test="${empty sessionScope.memId}">
  <script>
  alert("로그인 하셔야 수정이 가능합니다");
  </script>
  <Meta http-equiv="Refresh" content="0;url=${ctxpath}/craft_member/main.do">
</c:if>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>updateForm.jsp</title>
	<link rel="stylesheet" href="style.css" type="text/css"></style>
	
	<script src="script.js" type="text/javascript"></script>
</head>
<body>
	<br>
	<h3 align="center">글 수정하기</h3>
	<form name="faq_writeform" method="post" action="${ctxpath}/craft_board_faq/updatePro.do?faq_num=${faq_num}&faq_pageNum=${faq_pageNum}" onSubmit="return writeSave()">
		<table id="con" class="top" width="600" cellpadding="4">
			<tr class="bot">
				<td id="mid" bgcolor="#f4f4f4">글쓴이</td>
				<td>
				<input type="text" name="faq_writer" id="faq_writer" size="20" value="${sessionScope.memId}" readonly>
				<input type="hidden" name="faq_num" value="${faq_num}">
				</td>
			</tr>
			<tr class="bot">
				<td id="mid" bgcolor="#f4f4f4">글제목</td>
				<td>
				<input type="text" name="faq_subject" id="faq_subject" size="40" value="${faq_dto.faq_subject}">
				</td>
			</tr>
			<tr class="bot">
				<td id="mid" bgcolor="#f4f4f4">글내용</td>
				<td>
				<textarea name="faq_content" id="faq_content" rows="10" cols="60">${faq_dto.faq_content}</textarea>
				</td>
			</tr>
			<tr class="bot">
				<td id="mid" bgcolor="#f4f4f4">암호</td>
				<td>
				<input type="password" name="faq_pw" id="faq_pw">
				</td>
			</tr>			
			<tr>
				<td colspan="2" align="center">
					<br>
					<input type="submit" value="글수정">
					<input type="reset" value="다시작성">
					<input type="button" value="목록보기" onClick="location='${ctxpath}/craft_board_faq/list.do?faq_pageNum=${faq_pageNum}'">
				</td>
			</tr>
		</table>
		</form>
</body>
</html>