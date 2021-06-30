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
	<h3 align="center">Q&A</h3>
	<table id="con" class="top" width="600" cellpadding="4">
	<tr class="bot">
		<td width="100" id="mid" bgcolor="#f4f4f4" align="center">글번호</td>
		<td>${dto.qna_num}</td>
	</tr>
	<tr class="bot">
		<td id="mid" bgcolor="#f4f4f4" align="center">조회수</td>
		<td>${dto.qna_readcount}</td>
	</tr>
	<tr class="bot">
		<td id="mid" bgcolor="#f4f4f4" align="center">작성자</td>
		<td>${dto.qna_writer}</td>
	</tr>
	<tr class="bot">
		<td id="mid" bgcolor="#f4f4f4" align="center">글제목</td>
		<td>${dto.qna_subject}</td>
	</tr>
	<tr class="bot">
		<td id="mid" bgcolor="#f4f4f4" align="center">글내용</td>
		<td>${qna_content}</td>
	</tr>

	<tr>
		<td colspan="2" align="center">
			<input type="button" value="글수정" onClick="location='${ctxpath}/craft_board_qna/updateForm.do?qna_num=${qna_num}&qna_pageNum=${qna_pageNum}'">
			<input type="button" value="글삭제" onClick="location='${ctxpath}/craft_board_qna/deleteForm.do?qna_num=${qna_num}&qna_pageNum=${qna_pageNum}'">
			<input type="button" value="글쓰기" onClick="location='${ctxpath}/craft_board_qna/writeForm.do'">
			<input type="button" value="답글쓰기" onClick="location='${ctxpath}/craft_board_qna/writeForm.do?qna_num=${qna_num}&qna_pageNum=${qna_pageNum}&qna_ref=${qna_ref}&qna_re_step=${qna_re_step}&qna_re_level=${qna_re_level}'">
			<input type="button" value="리스트" onClick="location='${ctxpath}/craft_board_qna/list.do?qna_num=${qna_num}&qna_pageNum=${qna_pageNum}'">
		</td>
	</tr>
	</table>
	<br>
</body>
</html>
