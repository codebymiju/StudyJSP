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
// 		alert($("h3").html());  - "제목1" 텍스트 요소 출력
// 		alert($("#h3_2").html()); // "h3_2" 선택자 내의 요소가 그대로 출력(태그, 공백)
// 		alert($("#h3_2").text()); // "h3_2" 선택자 내의 요소 중 텍스트 요소만 출력됨
			
// 		$("#h3_1").html("<i>italic 적용된 제목1</i>"); // .html() : 태그 적용된 텍스트 출력
		$("#h3_1").text("<i>italic 적용된 제목1</i>"); // .text() : 태그가 일반 텍스트로 출력
		
		//-------------------------------------------------------
		// html() 함수 내에서 익명함수 구현을 통해 반복문 형태로 내용 변경
		$("h3").html(function(index, oldHtml) {
			return oldHtml + "입니다.";
		});
		//--------------------------------------------------------
		// empty() 함수 : 제거
// 		$("#h3_2").empty(); // <h3>는 남아있음
		$("#h3_2").remove(); 
		
// 		$("#ta").val($("#wrap").html()); // 해당 태그의 값이 나옴(태그x)
		$("#ta").html($("#wrap").html()); // html 태그 요소 그대로 설정
		
	});
	
</script>
</head>
<body>
	<h1>jQuery - test4.jsp</h1>
	<div id="wrap">
		<!-- 제목 표시 공간 -->
		<h3 id="h3_1">제목1</h3>
		<h3 id="h3_2">제목2&nbsp;<span>제목2-1</span>&nbsp;<span>제목2-2</span></h3>
		<h3 id="h3_3">제목3</h3>
	</div>
	<textarea id="ta" rows="5" cols="50"></textarea>
</body>
</html>
