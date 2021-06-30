<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>

<%-- 로그인 성공 --%>
	<c:if test="${check==1}">
		<c:set var="memId" value="${craft_id}" scope="session"/>
		<meta http-equiv="Refresh" content="0;url=${ctxpath}/craft_member/main.do">
	</c:if>
<%--암호 틀림--%>
<c:if test="${check==0}">
	<script>
		alert("암호 틀림");
		history.back();
	</script>
</c:if>
<%--없는 ID--%>
<c:if test="${check==-1}">
	<script>
	alert("없는 ID 입니다");
	history.back();
	</script>
</c:if>