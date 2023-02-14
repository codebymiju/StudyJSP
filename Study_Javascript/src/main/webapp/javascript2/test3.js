// 외부로부터 아이디(id), 패스워드(pass)를 전달받아 판별하는 login() 함수 정의
// -> 아이디가 "admin" 이고, 패스워드가 "1234" 일 경우 "로그인 성공!" 출력하고
//    아니면, "로그인 실패!" 출력하기
 function login(id, pass) {
	if (id == "admin" && pass == "1234"){
		document.write("<h3>" + id + " , " + pass + "로그인 성공!"+ "</h3>");
	} else {
		document.write("<h3>" + id + " , " + pass + "로그인 실패!"+ "</h3>")
	}
}

