
function writeSave(){
 //script.js		   
	if($("#qna_writer").val()==''){
	   alert("이름을 입력 하시요 ");
	   $("#qna_writer").focus();
	   return false;
   }
   if($("#qna_subject").val()==''){
	   alert("글제목을 입력 하시요 ");
	   $("#craft_subject").focus();
	   return false;
   }
   if($("#qna_content").val()==''){
	   alert("글내용을 입력 하시요 ");
	   $("#qna_content").focus();
	   return false;
   }
   if($("#qna_pw").val()==''){
	   alert("암호을 입력 하시요 ");
	   $("#qna_pw").focus();
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