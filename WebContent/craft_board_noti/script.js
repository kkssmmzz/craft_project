
function writeSave(){
 //script.js		   
	if($("#noti_writer").val()==''){
	   alert("이름을 입력 하시요 ");
	   $("#noti_writer").focus();
	   return false;
   }
   if($("#noti_subject").val()==''){
	   alert("글제목을 입력 하시요 ");
	   $("#noti_subject").focus();
	   return false;
   }
   if($("#noti_content").val()==''){
	   alert("글내용을 입력 하시요 ");
	   $("#noti_content").focus();
	   return false;
   }
   if($("#noti_pw").val()==''){
	   alert("암호을 입력 하시요 ");
	   $("#noti_pw").focus();
		   return false;
	   }
   
     return true;
   }//writeSave() end


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