<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.6.3.js"></script>
<script type="text/javascript">
	// css() 함수 
	
	$(function() { 
		// 값을 변경할 때는 복수개의 요소가 있으면 그 대상에 일괄 적용
// 		$("h3").css("color","Tomato");

// 		$("h3:first").css("color","SeaGreen");
// 		alert($("h3").css("color")); // 마지막 SeaGreen만 출력됨
		
		// 1
// 		$("h3").css("color", function() { // 해당 태그 횟수만큼 반복 수행
// 			alert("h3 태그 접근!");
// 		});

		// 2
// 		$("h3").css("color", function(index) { // 해당 태그 index 수만큼 반복 수행
// 			alert(index + "번 h3 태그 접근!");
// 		});
		
		// 3
// 		$("h3").css("color", function(index, value) { // 해당 태그 index 수만큼 반복 수행
// 			alert(index + "번 h3 태그 접근! - " + value);
// 		});
		
		// 4
		let colorValue = ["RosyBrown", "SeaGreen", "PowderBlue"];
		let bgColorValue = ["cyan", "skyblue", "yellow"];
		
		// 순서대로 글자색 변경
// 		$("h3").css("color", function(index) { // 해당 태그 index 수만큼 반복 수행
// // 			return "red"; // 모든 h3 태그 요소 글자색 > red
// 			return colorValue[index];
// 		});
		
		// 순서대로 글자 배경색 변경
// 		$("h3").css("background", function(index) { // 해당 태그 index 수만큼 반복 수행
// // 			
// 			return bgColorValue[index];
// 		});
		
		// 동일한 대상에 복수개의 속성을 반복문 형태로 
		$("h3").css({
			color: function(index) {
				return colorValue[index];
			},
			background: function(index) {
				return bgColorValue[index];
			}
				
		});
		
	});

</script>
</head>
<body>
	<h1>jQuery - test3.jsp</h1>
	<h3>제목1</h3>
	<h3>제목2</h3>
	<h3>제목3</h3>
</body>
</html>