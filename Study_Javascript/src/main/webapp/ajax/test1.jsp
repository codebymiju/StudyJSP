<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.6.3.js"></script>
<script type="text/javascript">
	/*
	AJAX(Asynchronous Javascript And XML, 비동기식 자바스크립트 & XML)
	- 웹페이지(브라우저)의 갱신 없이도 요청에 대한 응답과 함께 
	  화면 상의 요소를 다룰 수 있는 자바스크립트 라이브러리(기술)
	- 데이터베이스 등의 작업 요청 시 요청된 응답이 돌아올 때까지 기다리지 않고
	  다른 작업을 수행하면서 요청에 대한 응답이 돌아오면 해당 응답을 처리하는 기술
	- 주로, 아이디 및 패스워드 검증 등의 실시간 정보 조회 작업에 활용
	
	< 기본 문법 >
	$.ajax(); 또는 $.ajax({});
	*/
	$(function() {
		$("#btnLogin").on("click", function() {
			// AJAX 를 사용하여 test1_result.jsp 페이지로 요청 전송하기
// 			$.ajax({
// 				type: "post", // AJAX 로 요청 시 HTTP 요청 방식(GET or POST) 지정
// 				url: "test1_result.jsp", // AJAX 로 요청할 요청 주소(URL)
// 				data: { // 전송할 데이터(파라미터) 지정(일반 파라미터일 경우 중괄호로 묶음)
// 					// 폼 데이터를 가져와서 파라미터로 표현(전송)하는 경우
// 					// 파라미터명: 데이터 형식으로 지정
// // 					id: $("#id").val(), // id 선택자의 value 속성값을 id 파라미터로 저장
// // 					passwd: $("#passwd").val() // passwd 선택자의 value 속성값을 passwd 파라미터로 저장
// 					// -------- 값을 직접 지정 시 ---------
// 					id: "admin",
// 					passwd: "1234"
// 				},
// 				dataType: "text", // 응답 데이터에 대한 타입 지정(일반 데이터는 text 이며 HTML 코드도 포함 가능, 자바스크립트 포함되면 html 사용)
// 				success: function(response) { // 요청에 대한 처리 성공 시(= 정답 응답) 처리할 함수 정의
// 					// 익명 함수 파라미터로 응답 데이터가 전달됨(처리 페이지의 응답 결과)
// 					// id 선택자 resultArea 영역에 응답 데이터(response) 출력하기
// 					$("#resultArea").html(response);
// 				}, 
// 				error: function(xhr, textStatus, errorThrown) { 
// 					// 요청에 대한 처리 실패 시(= 에러 발생 시) 실행되는 이벤트
// 					$("#resultArea").html("xhr = " + xhr + "<br>textStatus = " + textStatus + "<br>errorThrown = " + errorThrown);
// 				}
// 			});
			
			// 폼 태그 내부 파라미터를 모두 요청 페이지로 전송하기 위해서
			// 폼을 대상으로 serialize() 함수 호출하면 해당 폼의 데이터 직렬화(= 내보내기)
			let sendData = $("form").serialize(); // 아이디, 패스워드 파라미터 저장됨
			$.ajax({
				type: "post", // AJAX 로 요청 시 HTTP 요청 방식(GET or POST) 지정
				url: "http://localhost:8080/MVC_Board/MemberLoginPro.me", // AJAX 로 요청할 요청 주소(URL)
				// serialize() 함수를 통해 가져온 폼 데이터를 전송할 데이터로 지정(중괄호 불필요)
				data: sendData,
				dataType: "text", // 응답 데이터에 대한 타입 지정(일반 데이터는 text 이며 HTML 코드도 포함 가능, 자바스크립트 포함되면 html 사용)
				success: function(response) { // 요청에 대한 처리 성공 시(= 정답 응답) 처리할 함수 정의
					// 익명 함수 파라미터로 응답 데이터가 전달됨(처리 페이지의 응답 결과)
					// id 선택자 resultArea 영역에 응답 데이터(response) 출력하기
					$("#resultArea").html(response);
				}, 
				error: function(xhr, textStatus, errorThrown) { 
					// 요청에 대한 처리 실패 시(= 에러 발생 시) 실행되는 이벤트
					$("#resultArea").html("xhr = " + xhr + "<br>textStatus = " + textStatus + "<br>errorThrown = " + errorThrown);
				}
			});
			
			
		});
	});
</script>
</head>
<body>
	<h1>AJAX - test1.jsp</h1>
	<h1>로그인</h1>
	<form action="" method="post">
		<table>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id" id="id" required="required" size="20"></td>
			</tr>
			<tr>
				<td>패스워드</td>
				<td><input type="password" name="passwd" id="passwd" required="required" size="20"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="로그인" id="btnLogin">
				</td>
			</tr>
		</table>
	</form>
	<hr>
	<div id="resultArea"><!-- AJAX 요청에 대한 응답 처리 결과 출력할 위치 --></div>	
	<hr>
</body>
</html>











