<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>

<%--세션 설정--%>
<c:set var="memId" value="${craft_id}" scope="session"/>
<script>
alert("회원가입이 완료되었습니다.");
</script>
<meta http-equiv="Refresh" content="0;url=${ctxpath}/craft_member/main.do">