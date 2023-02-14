<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 외부 jQuery 라이브러리 등록 -->
<script src="../js/jquery-3.6.3.js"></script>
<script type="text/javascript">
// 화면에 요소가 순서대로 자동 실행됨 (아래 3개)
// 	window.onload = function() {
// 		alert("1번 - window.onload");
// 	}
	
// 	$(window).on("load", function() {
// 		alert("2번 - $(window).on('load')");
// 	});
	//------------------------------------------
	// 로딩되고 나면 자동으로 호출되는 이벤트 document.ready이벤트
	// 문서가 로딩될때 자동으로 동작함! (2,3번 형태 기억)
	// 1. 첫번째 Ready 이벤트 처리 문법
	jQuery(document).ready(function() {
		alert("Ready 이벤트 처리 문법 -1");
	});
	
	// 2. 첫번째 Ready 이벤트 처리 문법
	// 	 -> jQuery 객체명을 $ 기호로 대체
	$(document).ready(function() { // $(객체명).함수명();
// 		alert("3번 - $(document).ready()");
		alert("Ready 이벤트 처리 문법 -2");
	});
	
	// 3. 첫번째 Ready 이벤트 처리 문법
	//   -> document 객체 지정 및 ready 이벤트 함수 호출 생략
	$(function() {
		alert("Ready 이벤트 처리 문법 -3");
	});
</script>
</head>
<body>
	<h1>jQuery - test1.jsp</h1>
	<img src = "도비.jpg" width="300" height="300"/>
</body>
</html>