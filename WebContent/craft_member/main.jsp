<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<img src="${ctxpath}/imgs/main.gif" display="block" width="100%">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>main.jsp</title>
	<style href="style.css" rel="stylesheet" type="text/css"></style>
	<script src="script.js" type="text/javascript"></script>
</head>
<body>

<c:if test="${empty sessionScope.memId }">
<form name="loginForm" method="post" action="${ctxpath}/craft_member/loginPro.do" onSubmit="return loginCheck()">
	<table width="80%" border="0" align="center">
		<tr align="center">
			<td colspan="3" height="50">
			<br>
				나무네 공방은 대구 전통의 공예품만을 전시, 판매하는 곳으로 <br>
				우리나라만의 멋을 찾으시는 분들께 새로운 경험을 선사해드립니다
			<br>
			<br>
			</td>
		</tr>
		
		<tr>
			<td></td>
			<td align="center">아이디 :&nbsp;<input type="text" name="craft_id" size="15">&nbsp;
			암호	:&nbsp;<input type="password" name="craft_pw" size="15">
			</td>
			<td></td>
		</tr>
		
		<tr>
			<td colspan="3" align="center">
				<br>
				<input type="submit" value="로그인">
				<input type="button" value="회원가입" onClick="location='${ctxpath}/craft_member/inputForm.do'">
				<br>
			</td>
		</tr>
	</table>
</form>
</c:if>
<!-- 로그아웃, 정보수정 -->
<c:if test="${!empty sessionScope.memId }">
	<table width="80%" border="0" align="center">
		<tr align="center">
			<td colspan="3" height="50">
			<br>
				나무네 공방은 대구 전통의 공예품만을 전시, 판매하는 곳으로 <br>
				우리나라만의 멋을 찾으시는 분들께 새로운 경험을 선사해드립니다
			<br>
			<br>
			</td>
		</tr>
		<tr>
			<td align="center">
				${sessionScope.memId}님<br>
				방문해 주셔서 감사합니다.
				<br>
				<br>
			<form method="post" action="${ctxpath}/craft_member/logout.do">
				<input type="submit" value="로그아웃">
				<input type="button" value="회원정보 변경" onClick="location='${ctxpath}/craft_member/modify.do'">
			</form>
			</td>
		</tr>
	</table>
</c:if>

</body>
</html>