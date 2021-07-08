"UTF-8"

/**
 *  Јаваскрипт за форме које су услућне за разне провејре на фоамама регистрације. 
 */

function testPasswords(){
	var password1 = document.getElementById("register_form_password").value; 
	var password2 = document.getElementById("register_form_password2").value;
	if(password1!=password2){
		alert('Корисник није регистрован. Лозинке се не слажу.'); 
		return false; 
	}
	return true;
}