<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>    
<%--deleteForm.jsp --%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>deleteForm.jsp</title>
  <link href="style.css" rel="stylesheet" type="text/css"></style>
  
  <script src="script.js" type="text/javascript"></script>
</head>
<body onload="document.delForm.craft_pw.focus();">
<br>
<form name="delForm" method="post" action="${ctxpath}/craft_board/deletePro.do" onSubmit="return check()">
 <table width="300" id="con" class="top" cellpadding="4">
 
   <tr>
     <td id="mid" bgcolor="#f4f4f4">
               암호를 입력하세요
     </td>
   </tr>
   
   <tr>
    <td id="mid" bgcolor="#f4f4f4">
    암호입력 : <input type="password" name="craft_pw" size="10">
    <input type="hidden" name="craft_pageNum" value="${craft_pageNum}">
    <input type="hidden" name="craft_num" value="${craft_num}">
    </td>
   </tr>
   
   <tr>
     <td id="mid" bgcolor="#f4f4f4">
       <input type="submit" value="글삭제">
       <input type="button" value="글목록" onClick="location='${ctxpath}/craft_board/list.do?craft_pageNum=${craft_pageNum}'">
     </td>
   </tr>
 </table>
</form>
</body>
</html>