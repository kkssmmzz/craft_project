
function writeSave(){
 //script.js		   
	if($("#craft_writer").val()==''){
	   alert("이름을 입력하시오.");
	   $("#craft_writer").focus();
	   return false;
   }
   if($("#craft_subject").val()==''){
	   alert("글제목을 입력하시오.");
	   $("#craft_subject").focus();
	   return false;
   }
   if($("#craft_content").val()==''){
	   alert("글내용을 입력하시오.");
	   $("#craft_content").focus();
	   return false;
   }
   if($("#craft_pw").val()==''){
	   alert("암호을 입력하시오.");
	   $("#craft_pw").focus();
		   return false;
	   }
   
     return true;
   }//writeSave() end

function check(){
	if(document.delForm.craft_pw.value==''){
		  alert("암호를 입력 하시요");
		  document.delForm.craft_pw.focus();
		  return false;
	}//if-end
	return true
}