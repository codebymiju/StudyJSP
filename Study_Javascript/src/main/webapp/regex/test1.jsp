<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.6.3.js"></script>
<script type="text/javascript">
	$(function() {
		
		// 1. 이름에 대한 유효성 검증(Validation Check)
		$("#name").on("keyup", function() {
// 			$("#nameResult").html($("#name").val()); // 누를때마다 출력
			let name = $("#name").val() // 입력받은 이름 가져오는 변수
			
			// 이름 검증에 사용할 정규표현식
			let regex = /^[가-힣]{2,10}$/;
// 			alert(regex + " : " + typeof(regex)); // object 타입 출력

			// if문을 활용, 입력받은 이름 > 정규표현식 통해 검증
			if(!regex.exec(name)) { // 정규표현식에 맞지 않을 경우
				$("#nameResult").html("이름이 올바르지 않습니다!").css("color","red");
			} else {
				$("#nameResult").html("이름이 올바르다!").css("color","blue");
			}
			
		});
		
		// 2. 아이디에 대한 입력값 검증 : 영문,숫자,특수문자(-_.) 조합 8~16자
		$("#id").on("change", function() {
			let id = $("#id").val();
			
			let lengthRegex = /^[A-Za-z0-9-_.]{8,16}$/;
			if(!lengthRegex.exec(id)) {
				$("#idResult").html("사용 불가 아이디!").css("color","red");
			} else {
				$("#idResult").html("사용 가능 아이디!").css("color","blue");
			}
			
		});
		
		// 3. 패스워드에 대한 입력값 검증 : 영문,숫자,특수문자(-_.) 조합 8~16자
		// -> 단, 패스워드 복잡도 검사를 통해 "안전", "보통", "위험", "사용불가" 판별 
		$("#passwd").on("change", function() {
			let passwd = $("#passwd").val();
			
			let lengthRegex = /^[A-Za-z0-9~!@#$%^&*()-_]{8,16}$/;  // 전체 규칙만 판별 가능(시작부터 끝까지 저 기능을 써서 있느냐만 파악, 영문자 있는지 등 파악 불가)
			
			// 부분 검사(영문자, 숫자, 특수문자 각각 판별)를 위한 정규표현식 작성
			let engUpperRegex = /[A-Z]/; // 대문자
			let engLowerRegex = /[a-z]/; // 소문자
			let numRegex = /[0-9]/; // 숫자
			let specRegex = /[!@#$%]/; // 특수문자
			
			if(!lengthRegex.exec(passwd)) {
				$("#passwdResult").html("사용 불가능한 패스워드!").css("color","red");
			} else {
				$("#passwdResult").html("사용 가능한 패스워드!").css("color","blue");
				
				// 복잡도 검사(전체 규칙 검사 통과 시에만)
				let count = 0; // 각 항목별 포함 갯수를 카운팅 할 변수 선언
				
				// 항목별 검사 후 포함시 count증가
				if(engUpperRegex.exec(passwd)) {count++};
				if(engLowerRegex.exec(passwd)) {count++};
				if(numRegex.exec(passwd)) {count++};
				if(specRegex.exec(passwd)) {count++};
				
				// 카운트 값 판별
				switch(count) {
					case 4 : $("#passwdResult").html("안전").css("color","green"); break;
					case 3 : $("#passwdResult").html("보통").css("color","orange"); break;
					case 2 : $("#passwdResult").html("위험").css("color","yellow"); break;
					case 1 : $("#passwdResult").html("사용불가").css("color","red"); 
					
				}
			}
			
		});
	});

</script>
</head>
<body>
	<h1>정규표현식 - test1.jsp</h1>
	<input type="text" id="name" placeholder="이름(한글 2~10글자)">
	<span id="nameResult"></span><br>
	<input type="text" id="id" placeholder="아이디(영문,숫자,특수문자 8~16자)">
	<span id="idResult"></span><br>
	<input type="text" id="passwd" placeholder="패스워드(영문,숫자,특수문자 8~16자)">
	<span id="passwdResult"></span><br>
</body>
</html>