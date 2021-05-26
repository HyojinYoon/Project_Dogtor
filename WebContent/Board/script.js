function writeSave(){
	
	if(document.writeform.writer.value==""){
	  alert("올바르게 입력해주세요.");
	  document.writeform.writer.focus();
	  return false;
	}
	if(document.writeform.subject.value==""){
	  alert("올바르게 입력해주세요.");
	  document.writeform.subject.focus();
	  return false;
	}
	
	if(document.writeform.content.value==""){
	  alert("올바르게 입력해주세요.");
	  document.writeform.content.focus();
	  return false;
	}
        
	if(document.writeform.passwd.value==""){
	  alert("비밀번호를 올바르게 입력해주세요.");
	  document.writeform.passwd.focus();
	  return false;
	}
 }    

