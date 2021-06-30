<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>deletePro.jsp</title>
	<style href="style.css" rel="stylesheet" type="text/css"></style>
</head>
<body>
<c:if test="${check==1}"><!-- 삭제성공 -->
	<c:remove var="memId" scope="session"/><!-- 세션 무효화 -->
	<table width="500" cellpadding="5" align="center">
		<tr height="40">
			<td>
				<font size="5">회원정보가 삭제 되었습니다.</font>
			</td>
		</tr>
		
		<tr height="40">
			<td align="center">
			<p></p>
			<meta http-equiv="Refresh" content="2;url=${ctxpath}/craft_member/main.do">
			</td>
		</tr>
	</table>
</c:if>

<c:if test="${check==-1}"><!-- 암호틀림 -->
	<script>
	alert("암호가 일치하지 않습니다.");
	history.back();
	</script>
</c:if>

</body>
</html>