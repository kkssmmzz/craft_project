<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<link href="style.css" rel="stylesheet" type="text/css"></style>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>updatePro.jsp</title>
</head>
<body>
<c:if test="${check==1 }">
	<meta http-equiv="Refresh" content="0;url=${ctxpath}/craft_board/list.do?craft_pageNum=${craft_pageNum}">
</c:if>

<c:if test="${check==0}">
<br>
   <table width="300" id="con" class="top" cellpadding="4">
     <tr>
       <td align="center" bgcolor="#f4f4f4">
       <br>
                   암호가 틀렸습니다<br>
         <br>
         <a href="javaScript:history.back()">☞ 돌아가기</a>
         <br>
         <br>
       </td>
     </tr>
  </table>
  <br>
</c:if>
</body>
</html>