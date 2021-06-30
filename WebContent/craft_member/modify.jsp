<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>modify.jsp</title>
	<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
 <table width="400" id="con" class="top" cellpadding="4" align="center" valign="middle" bgcolor="#f4f4f4">
 <tr height="20">
 </tr>
  <tr align="center" bgcolor="#f4f4f4" valign="middle">
   <td>
    <form name="modifyForm" method="post" action="${ctxpath}/craft_member/modifyForm.do">
     <input type="hidden" name="craft_id" value="${sessionScope.memId}">
	회원 정보를 변경하시겠습니까?
	 <input type="submit" value="내정보수정">
    </form>
   </td>
  </tr>	
<tr class="bot" >
   <td align="center" bgcolor="#f4f4f4" valign="middle">
    <form name="deleteForm" method="post" action="${ctxpath}/craft_member/deleteForm.do">
     <input type="hidden" name="craft_id" value="${sessionScope.memId}">
	회원 탈퇴하시겠습니까?
	 <input type="submit" value="회원탈퇴">
    </form>
   </td>
  </tr>
 </table>
  <br>

</body>
</html>