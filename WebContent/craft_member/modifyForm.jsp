<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>modifyForm.jsp</title>

<link href="style.css" rel=stylesheet type="text/css">
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<script>
   function openDaumPostcode(){
       
      new daum.Postcode({
         oncomplete:function(data){
            document.getElementById('craft_zipcode').value=data.zonecode;
            document.getElementById('craft_addr').value=data.address;
          }
         
      }).open();
   }//openDaumPostcode()---
  </script>
<script>
	function check(){
		var inForm=eval("document.modifyForm");
		
		if(inForm.craft_pw.value==''){
			alert("암호를 입력 하세요");
			inForm.craft_pw.focus();
			return false;
		}
		if(inForm.craft_pw2.value==''){
			alert("암호확인를 입력 하세요");
			inForm.craft_pw2.focus();
			return false;
		}
		if(inForm.craft_pw.value!=inForm.craft_pw2.value){
			alert("암호와 암호확인이 다르다");
			inForm.craft_pw.value="";
			inForm.craft_pw2.value="";
			inForm.craft_pw.focus();
			return false;
		}
		return true;
	}//function end
	
</script>
</head>
<body>
	<form name="modifyForm" method="post" action="${ctxpath}/craft_member/modifyPro.do" onSubmit="return check()">

	<h2 align="center"><b>내 정보 수정</b></h2>	  
  <table id="con" class="top" cellpadding="2" align="center" valign="middle" width="600">
  
   <tr class="bot">
    <td id="con" bgcolor="#f4f4f4" width="100" height="30" align="center">ID</td>
    <td>
     ${dto.craft_id}
     <input type="hidden" name="craft_id" value="${sessionScope.memId}">
     </td>
    </tr>
		
   <tr class="bot">
    <td id="con" bgcolor="#f4f4f4" width="100" height="30" align="center">암호</td>
    <td><input type="password" name="craft_pw" id="craft_pw" size="15"></td>
   </tr>
   
   <tr class="bot">
    <td id="con" bgcolor="#f4f4f4" width="100" height="30" align="center">암호확인</td>
    <td><input type="password" name="craft_pw2" id="craft_pw2" size="15"></td>
   </tr>
		
  <tr class="bot">
    <td id="con" bgcolor="#f4f4f4" width="100" height="30" align="center">사용자 이름</td>
    <td>
     <input type="text" name="craft_name" value="${dto.craft_name}">
    </td>
   </tr>
		
   <tr class="bot">
    <td id="con" bgcolor="#f4f4f4" width="100" height="30" align="center">이메일</td>
    <td>
     <input type="text" name="craft_email1" value="${craft_email1}">
     <select name="craft_email2">
      <option value="${craft_email2}">${craft_email2}</option>
      <option value="@naver.com">@naver.com</option>
      <option value="@daum.net">@daum.net</option>
      <option value="@nate.com">@nate.com</option>
     </select>
    </td>
   </tr>
   
   <tr class="bot">
    <td id="con" bgcolor="#f4f4f4" width="100" height="30" align="center">전화번호</td>
    <td>
     <select name="craft_tel1" id="craft_tel1">
      <option value="${craft_tel1}">${craft_tel1}</option>
      <option value="010">010</option>
      <option value="018">018</option>
      <option value="017">017</option>
     </select>
     <input type="text" name="craft_tel2" value="${craft_tel2}" size="4">-
     <input type="text" name="craft_tel3" value="${craft_tel3}" size="4">
    </td>
   </tr>
			
   <tr class="bot">
    <td id="con" bgcolor="#f4f4f4" width="100" height="30" align="center">우편번호</td>
    <td>
     <input type="text" name="craft_zipcode" id="craft_zipcode" value="${dto.craft_zipcode}" size="7">
     <input type="button" value="주소검색" onClick="openDaumPostcode()">
    </td>
   </tr>
			
   <tr class="bot">
    <td id="con" bgcolor="#f4f4f4" width="100" height="60" align="center">주소</td>
    <td>
     <input type="text" name="craft_addr" id="craft_addr" value="${dto.craft_addr}" size="60" readonly>
     <br>
           상세주소:<input type="text" name="craft_addr2" value="${dto.craft_addr2}" size="40">
    </td>
   </tr>
			
    <tr height="15">
   </tr>
			
   <tr>
    <td id="con" width="100" height="30" align="center" colspan="2">
      <input type="submit" value="내정보수정">
      <input type="reset" value="다시입력">
      <input type="button" value="취소" onClick="location='${ctxpath}/craft_member/main.do'">
    </td>
   </tr>
  </table>
 </form>
</body>
</html>