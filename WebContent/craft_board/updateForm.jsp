<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>updateForm.jsp</title>
	<link rel="stylesheet" href="style.css" type="text/css"></style>
	
	<script src="script.js" type="text/javascript"></script>
</head>
<body onload="document.writeform.craft_writer.focus();">
	<br>
	<h3>글수정</h3>
	<form name="writeform" method="post" action="${ctxpath}/craft_board/updatePro.do?craft_num=${craft_num}&craft_pageNum=${craft_pageNum}" onSubmit="return writeSave()">
		<table id="con" class="top" width="600" cellpadding="4">
			<tr class="bot">
				<td width="50" id="mid" bgcolor="#f4f4f4">글쓴이</td>
				<td>
				<input type="text" name="craft_writer" id="craft_writer" size="20" value="${dto.craft_writer}">
				</td>
			</tr>
			<tr class="bot">
				<td id="mid" bgcolor="#f4f4f4">글제목</td>
				<td>
				<input type="text" name="craft_subject" id="craft_subject" size="40" value="${dto.craft_subject}">
				</td>
			</tr>
			<tr class="bot">
				<td id="mid" bgcolor="#f4f4f4">글내용</td>
				<td>
				<textarea name="craft_content" id="craft_content" rows="10" cols="60">${dto.craft_content}</textarea>
				</td>
			</tr>
			<tr class="bot">
				<td id="mid" bgcolor="#f4f4f4">암호</td>
				<td>
				<input type="password" name="craft_pw" id="craft_pw">
				</td>
			</tr>			
			<tr>
				<td colspan="2" align="center">
					<br>
					<input type="submit" value="글수정">
					<input type="reset" value="다시작성">
					<input type="button" value="글목록" onClick="location='${ctxpath}/craft_board/list.do?craft_pageNum=${craft_pageNum}'">
				</td>
			</tr>
		</table>
		</form>
</body>
</html>