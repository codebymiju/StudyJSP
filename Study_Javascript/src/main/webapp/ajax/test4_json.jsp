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
			$.ajax({
				type: "get",
				url: "test4_json_data.txt",
				dataType: "json", // 응답 데이터 형식을 JSON 타입(객체)으로 지정
			})
			.done(function(data) { // 요청 성공 시
				// JSON 객체 데이터를 가공하여 표시할 테이블 생성
				$("#resultArea").html("<table border='1' width='700'></table>");
				// table 태그 내에 append() 함수를 사용하여 각 행 추가
				$("#resultArea > table").append("<tr><th colspan='5'>JSON 데이터 파싱 결과</th></tr>");
				$("#resultArea > table").append("<tr><th>아이디</th><th>이름</th><th>나이</th><th>정보수신동의</th><th>주소</th></tr>");
				
				for(let obj of data) {
					let id = obj.id;
					let name = obj.name;
					let age = obj.age;
					let agreeRcvSpam = obj.agreeRcvSpam;
					
					// 배열 인덱스를 통해 접근하는 데이터 중 "address" 속성은 또 다른 객체이므로
					// "address" 객체 내의 "address1", "address2" 속성에 각각 접근해야함
					let address = obj.address; 
					
					$("#resultArea > table").append(
							"<tr><td>" + id + "</td>"
							+ "<td>" + name + "</td>"
							+ "<td>" + age + "</td>"
							+ "<td>" + agreeRcvSpam + "</td>"
							+ "<td>" + (address.address1 + " " + address.address2) + "</td></tr>"
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
	<h1>AJAX - test4_json.jsp</h1>
	<input type="button" value="JSON 데이터 파싱" id="btnOk">
	<hr>
	<div id="resultArea"></div>	
</body>
</html>











