<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>deleteForm.jsp</title>
	<link href="style.css" rel="stylesheet" type="text/css">
	
	<script>
		function check(){
		 if(document.delForm.craft_pw.value==''){
		 	alert("암호를 입력하세요");
			document.delForm.craft_pw.focus();
			return false;
			}
		return true;
		}
	</script>

</head>
 <body>
 <h2 align="center">회원 탈퇴</h2>
 <form name="delForm" method="post" action="${ctxpath}/craft_member/deletePro.do" onSubmit="return check()">
 <table width="300" id="con" class="top" cellpadding="4" align="center" valign="middle">
   <tr>
     <td id="mid"  bgcolor="#f4f4f4">
      <b>계정 암호를 입력하세요.</b>
     </td>
   </tr>
		
	 <tr>
    <td id="mid" bgcolor="#f4f4f4">
	     암호: <input type="password" name="craft_pw" id="craft_pw" size="15" align="center">
	   <input type="hidden" name="craft_id" value="${sessionScope.memId}">
	  </td>			
	 </tr>
		
	<tr height="15" >
	 <td bgcolor="#f4f4f4">
	 </td>
	</tr>
	
	 <tr>
	  <td id="mid" bgcolor="#f4f4f4" class="bot">
	   <input type="submit" value="회원 탈퇴">
	   <input type="button" value="취소" onClick="location='${ctxpath}/craft_member/main.do'">
		  
	  </td>
	 </tr>
	</table>

	</form>
 </body>
</html>