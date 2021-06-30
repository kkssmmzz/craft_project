<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>    
<%-- deleteForm.jsp --%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>deleteForm.jsp</title>
  <link href="style.css" rel="stylesheet" type="text/css"></link>
  
  <script>
  function check(){
	  if(document.delForm.noti_pw.value==''){
		  alert("암호를 입력하시오.");
		  document.delForm.noti_pw.focus();
		  return false;		  
	  }//if end
	  return true;
  }//check() end
  </script>
</head>
<body>
 <br>
 <center><h2>글 삭제<h2></center>

<form name="delForm" method="post" action="${ctxpath}/craft_board_noti/deletePro.do" onSubmit="return check()">
  <table id="con" class="top" width="600" cellpadding="4">
    <tr>
      <td align="center" class="bot">
      	암호를 입력하세요
      </td>
    </tr>
    
    <tr>
      <td align="center" class="bot">
      	암호입력 : <input type="password" name="noti_pw" size="10">
      	<input type="hidden" name="pageNum" value="${pageNum}">
      	<input type="hidden" name="noti_num" value="${noti_num}">
      </td>
    </tr>
    
    <tr>
      <td align="center">
        <br>
        <input type="submit" value="글삭제">
        <input type="button" value="취소" onClick="location='${ctxpath}/craft_board_noti/list.do?pageNum=${pageNum}'">
      </td>
    </tr>
  </table>
  <br>
</form>
</body>
</html>