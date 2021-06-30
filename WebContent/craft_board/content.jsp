<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/module/header.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>content.jsp</title>
	<link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>
	<br>
	<h3>글내용보기</h3>
	<table id="con" class="top" width="600" cellpadding="4">
	<tr class="bot">
		<td width="100" id="mid" bgcolor="#f4f4f4">글번호</td>
		<td>${dto.craft_num}</td>
	</tr>
	<tr class="bot">
		<td id="mid" bgcolor="#f4f4f4">조회수</td>
		<td>${dto.craft_readcount}</td>
	</tr>
	<tr class="bot">
		<td id="mid" bgcolor="#f4f4f4">작성자</td>
		<td>${dto.craft_writer}</td>
	</tr>
	<tr class="bot">
		<td id="mid" bgcolor="#f4f4f4">글제목</td>
		<td>${dto.craft_subject}</td>
	</tr>
	<tr class="bot">
		<td id="mid" bgcolor="#f4f4f4">글내용</td>
		<td>${craft_content}</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
		<br>
			<input type="button" value="글수정" onClick="location='${ctxpath}/craft_board/updateForm.do?craft_num=${craft_num}&craft_pageNum=${craft_pageNum}'">
			<input type="button" value="글삭제" onClick="location='${ctxpath}/craft_board/deleteForm.do?craft_num=${craft_num}&craft_pageNum=${craft_pageNum}'">
			<input type="button" value="글쓰기" onClick="location='${ctxpath}/craft_board/writeForm.do'">
			<input type="button" value="답글쓰기" onClick="location='${ctxpath}/craft_board/writeForm.do?craft_num=${craft_num}&craft_pageNum=${craft_pageNum}&craft_ref=${craft_ref}&craft_re_step=${craft_re_step }&craft_re_level=${craft_re_level}'">
			<input type="button" value="글목록" onClick="location='${ctxpath}/craft_board/list.do?craft_num=${craft_num}&craft_pageNum=${craft_pageNum}'">
		</td>
		
	</tr>
	</table>
	<br>
</body>
</html>
