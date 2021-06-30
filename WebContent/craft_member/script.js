
function writeSave(){
 //script.js		   
	if($("#writer").val()==''){
	   alert("이름을 입력 하시요 ");
	   $("#writer").focus();
	   return false;
   }
   if($("#subject").val()==''){
	   alert("글제목을 입력 하시요 ");
	   $("#subject").focus();
	   return false;
   }
   if($("#content").val()==''){
	   alert("글내용을 입력 하시요 ");
	   $("#content").focus();
	   return false;
   }
   if($("#pw").val()==''){
	   alert("암호을 입력 하시요 ");
	   $("#pw").focus();
		   return false;
	   }
   
     return true;
   }//writeSave() end

function loginCheck(){
	if(document.loginForm.craft_id.value==""){
		alert("ID를 입력 하세요");
		document.loginForm.craft_id.focus();
		return false;
	}
	if(document.loginForm.craft_pw.value==""){
		alert("암호를 입력 하세요");
		document.loginForm.craft_pw.focus();
		return false;
	}
	return true
}//loginCehck() end

/*function writeSave(){
	//alert("aaa")
	if(document.writeform.writer.value==""){
	  alert("작성자를 입력하십시요.");
	  document.writeform.writer.focus();
	  return false;
	}
	if(document.writeform.subject.value==""){
	  alert("제목을 입력하십시요.");
	  document.writeform.subject.focus();
	  return false;
	}
	
	if(document.writeform.content.value==""){
	  alert("내용을 입력하십시요.");
	  document.writeform.content.focus();
	  return false;
	}
        
	if(document.writeform.pw.value==""){
	  alert(" 비밀번호를 입력하십시요.");
	  document.writeform.pw.focus();
	  return false;
	}
	
	return true;
	//document.writeform.submit();
 }//writeSave()end
 */    