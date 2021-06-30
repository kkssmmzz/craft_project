<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>inputForm.jsp</title>
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
      //데이터 유효성 체크, jQuery
      if($('#craft_id').val()==''){
         alert("ID를 입력 하세요");
         $('#craft_id').focus();
         return false;
      }
      if($('#craft_pw').val()==''){
         alert("암호를 입력 하세요");
         $('#craft_pw').focus();
         return false;
      }
      if($('#craft_pw2').val()==''){
         alert("암호확인를 입력 하세요");
         $('#craft_pw2').focus();
         return false;
      }
      
      //암호와 암호확인 같은지 비교 
      if($('#craft_pw').val()!=$('#craft_pw2').val()){
         alert("암호와 암호확인이 틀립니다");
         $('#craft_pw').val('').focus();
         $('#craft_pw2').val('');
          
         return false;
      }
      
      if($('#craft_name').val()==''){
         alert("이름를 입력 하세요");
         $('#craft_name').focus();
         return false;
      }
      
      if($('#idcheck').val()=='false'){
         alert("id중복 체크 하세요");
         $('#craft_id').focus();
         return false;
      }
      return true;
      //document.inForm.submit();
   }//check()-end
   
   //Ajax
   function confirmIDCheck(){
      if($('#craft_id').val()==''){
         alert("ID를 입력 하세요");
         $('#craft_id').focus();
      }else{
         $.ajax({
            type:"POST",
            url:'confirmID.jsp',
            data:"craft_id="+$('#craft_id').val(),//서버로 보내는 인수값
            dataType:'JSON',//서버가 보내준(Type)
            success:function(data){
               if(data.x==1){
                  alert("사용중인 ID");
                  $('#craft_id').val('').focus();
                  return;
               }else{
                  alert("사용가능한 ID");
                  $('#craft_pw').focus();
               }
            }//success end
         });
         
         $('#idcheck').val('true');//id 중복 체크 완료
      }//else end
   }//confirmIDCheck() end
   
   function idCheck(){
      if($('#idcheck').val()=='false'){
         alert("id중복 체크 하세요");
         $('#craft_id').focus();
         return false;
      }
   }//idCheck() end
 </script>
	<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
 <form name="inputForm" method="post" action="${ctxpath}/craft_member/inputPro.do" onSubmit="return check()">
	<h2 align="center"><b>회원 가입</b></h2>	 
  <table id="con" class="top" cellpadding="2" align="center" valign="middle" width="600">
 
   <tr class="bot">
    <td bgcolor="#f4f4f4" width="100" height="30" align="center">ID</td>
    <td>
     <input type="text" name="craft_id" id="craft_id" size="15">
     <input type="hidden" name="idcheck" id="idcheck" value="false">
     <input type="button" value="ID중복체크" onClick="confirmIDCheck()">
    </td>
   </tr>
			
   <tr class="bot">
    <td id="mid" bgcolor="#f4f4f4" width="100" height="30" align="center">암호</td>
    <td>
     <input type="password" name="craft_pw" id="craft_pw" size="15" onFocus="idCheck()"></td>
   </tr>
   
  <tr class="bot">
    <td id="mid" bgcolor="#f4f4f4" width="100" height="30" align="center">암호확인</td>
    <td><input type="password" name="craft_pw2" id="craft_pw2" size="15"></td>
   </tr>
   
   <tr class="bot">
    <td id="mid" bgcolor="#f4f4f4" width="100" height="30" align="center">이름</td>
    <td><input type="text" name="craft_name" id="craft_name" size="30"></td>
   </tr>
   
   <tr class="bot">
    <td id="mid" bgcolor="#f4f4f4" width="100" height="30" align="center">이메일</td>
    <td>
     <input type="text" name="craft_email1" id="craft_email1">
     <select name="craft_email2" id="craft_email2">
      <option value="@naver.com">@naver.com</option>
      <option value="@daum.net">@daum.net</option>
      <option value="@nate.com">@nate.com</option>
     </select>
    </td>
   </tr>
   
   <tr class="bot">
    <td id="mid" bgcolor="#f4f4f4" width="100" height="30" align="center">전화번호</td>
    <td>
     <select name="craft_tel1" id="craft_tel1">
      <option value="010">010</option>
      <option value="018">018</option>
      <option value="017">017</option>
     </select>
					
     <input type="text" name="craft_tel2" id="craft_tel2" size="4">-
     <input type="text" name="craft_tel3" id="craft_tel3" size="4">
    </td>
   </tr>
   
   <tr class="bot">
    <td id="mid" bgcolor="#f4f4f4" width="100" height="30" align="center">우편번호</td>
    <td>
     <input type="text" name="craft_zipcode" id="craft_zipcode" size="7">
     <input type="button" value="주소검색" onClick="openDaumPostcode()">
    </td>
   </tr>
			
   <tr class="bot">
    <td id="mid" bgcolor="#f4f4f4" width="100" height="60" align="center">주소</td>
    <td>
     <input type="text" name="craft_addr" id="craft_addr" size="60" readonly>
      <br>
	   상세주소:&nbsp;<input type="text" name="craft_addr2" id="craft_addr2" size="40">
    </td>
   </tr >
   
   <tr height="15">
   </tr>
			
   <tr>
    <td id="con" width="100" height="30" align="center" colspan="2">
     <input type="submit" id="button" value="회원가입">
     <input type="reset" value="다시입력">
     <input type="button" value="가입안함" onClick="location='${ctxpath}/craft_member/main.do'">
    </td>
   </tr>
  </table>
 </form>
</body>
</html>