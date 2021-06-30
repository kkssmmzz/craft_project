<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>    
<%--deletePro.jsp --%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>deletePro.jsp</title>
  <link href="style.css" rel="stylesheet" type="text/css"></style>
</head>
<body>
  <c:if test="${qna_check==1}">
   <meta http-equiv="Refresh" content="0;url=${ctxpath}/craft_board_qna/list.do?qna_pageNum=${qna_pageNum}">
  </c:if>

  <c:if test="${qna_check==0}">
  <br>
   <table width="300" id="con" class="top" cellpadding="4">
     <tr>
       <td align="center" bgcolor="#f4f4f4">
       <br>
                   암호가 일치하지 않습니다.<br>
         <br>
         <a href="javaScript:history.back()">☞ 돌아가기</a>
         <br>
         <br>
       </td>
     </tr>
  </table>
  </c:if>
</body>
</html>