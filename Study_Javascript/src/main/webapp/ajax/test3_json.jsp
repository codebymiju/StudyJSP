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
		$("#btnOk").on("click", function() {
			// test3_json_data.txt 파일의 내용을 AJAX 를 통해 요청 => text 형식 응답
			// => AJAX 요청 성공 시 자동으로 호출되는 콜백 함수를 success 에 기술할 수도 있지만
			//    success 와 유사한 done() 함수로도 동일한 처리가 가능(기술하는 위치 다름)
// 			$.ajax({
// 				type: "get",
// 				url: "test3_json_data.txt",
// 				dataType: "text",
// 				success: function(msg) {
// 					$("#resultArea").html(msg);
// 				}
// 			});
			
			// 성공에 대한 콜백 함수를 success 대신 done() 함수로 처리
			// => success 와 동일하나 success 보다 호출 우선순위가 낮음
// 			$.ajax({
// 				type: "get",
// 				url: "test3_json_data.txt",
// 				dataType: "text",
// 			})
// 			.done(function(msg) {
// 				$("#resultArea").html(msg);
// 			})
// 			.fail(function() {
// 				$("#resultArea").html("요청 실패!");
// 			});
			
			// ============================================================================
			// test3_json_data.txt 파일을 AJAX 를 통해 요청 => 응답 형식을 JSON 객체로 지정 
			$.ajax({
				type: "get",
				url: "test3_json_data3.txt",
				dataType: "json", // 응답 데이터 형식을 JSON 타입(객체)으로 지정
			})
			.done(function(data) { // 요청 성공 시
// 				$("#resultArea").html("<h3>타입 : " + typeof(data) + "</h3>");
// 				$("#resultArea").html(data); // JSON 객체 형태 응답은 그대로 사용 불가능(출력X)
				// ----------------------------------------------------------
				// 만약, 전달받은 JSON 객체를 문자열 형식으로 변환할 경우
// 				let strData = JSON.stringify(data);
// 				$("#resultArea").html(strData); // JSON 객체 형태 응답은 그대로 사용 불가능(출력X)
// 				$("#resultArea").append("<h3>타입 : " + typeof(strData) + "</h3>");
				// ----------------------------------------------------------
				// JSON 객체 데이터를 가공하여 표시할 테이블 생성
				$("#resultArea").html("<table border='1' width='500'></table>");
// 				// table 태그 내에 append() 함수를 사용하여 각 행 추가
				$("#resultArea > table").append("<tr><th colspan='4'>JSON 데이터 파싱 결과</th></tr>");
				$("#resultArea > table").append("<tr><th>아이디</th><th>이름</th><th>나이</th><th>정보수신동의</th></tr>");
// 				alert(JSON.stringify(data));
				// JSON 데이터가 대괄호[] 로 감싸져 있을 경우 배열 데이터(객체)로 취급되고
				// 해당 배열 내에서 중괄호{} 로 감싸져 있는 객체에 접근하여 객체의 속성 지정 가능
				// => 배열명[인덱스].속성명 형태로 배열 내의 객체에 접근(ex. data[0].name)
// 				$("#resultArea").after(data[0].id); // 첫번째 배열의 id 속성값 출력

				// 파싱된 JSON 객체의 배열 크기만큼 for문을 통해 반복 작업 수행
// 				for(let i = 0; i < data.length; i++) {
// 					let id = data[i].id;
// 					let name = data[i].name;
// 					let age = data[i].age;
// 					let agreeRcvSpam = data[i].agreeRcvSpam;
					
// 					$("#resultArea > table").append(
// 							"<tr><td>" + id + "</td>"
// 							+ "<td>" + name + "</td>"
// 							+ "<td>" + age + "</td>"
// 							+ "<td>" + agreeRcvSpam + "</td></tr>"
// 					);
// 				}

				for(let obj of data) {
					let id = obj.id;
					let name = obj.name;
					let age = obj.age;
					let agreeRcvSpam = obj.agreeRcvSpam;
					
					$("#resultArea > table").append(
							"<tr><td>" + id + "</td>"
							+ "<td>" + name + "</td>"
							+ "<td>" + age + "</td>"
							+ "<td>" + agreeRcvSpam + "</td></tr>"
					);
				}
				
			})
			.fail(function() {
				$("#resultArea").html("요청 실패!");
			});
			
			
		});
	});
</script>
</head>
<body>
	<h1>AJAX - test3_json.jsp</h1>
	<input type="button" value="JSON 데이터 파싱" id="btnOk">
	<hr>
	<div id="resultArea"></div>	
</body>
</html>











