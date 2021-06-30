<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>     
<%-- deletePro.jsp --%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>deletePro.jsp</title>
  <link href="style.css" rel="stylesheet" type="text/css"></link>
</head>
<body>
  <c:if test="${check==1}">
    <script>alert("게시글이 삭제되었습니다.")</script>
    <meta http-equiv="Refresh" content="0;url=${ctxpath}/craft_board_noti/list.do?pageNum=${pageNum}">
  </c:if>
  
  <c:if test="${check==0}">
    <script>
    alert("암호가 올바르지 않습니다.");
    history.back();
    </script>
  </c:if>
</body>
</html>