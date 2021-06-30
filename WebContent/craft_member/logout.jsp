<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>


<!-- 세션 무효화 -->
<c:remove var="memId" scope="session"/>
<script>
alert("로그아웃 하였습니다.");
</script>
<meta http-equiv="Refresh" content="0;url=${ctxpath}/craft_member/main.do">