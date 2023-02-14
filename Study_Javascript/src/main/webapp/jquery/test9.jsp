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
		// 대상.each() 함수 : 대상에 대한 반복을 수행하는 함수 
		// -> 지정 가능 대상 : 태그, 배열 등
		// 기본 문법 : 대상.each(function(index, item) {} );

		$("h3").each(function(index, item) {
// 			alert(index + " : " + item);
// 			$("table").append("<tr><td>"+index+"</td></tr>");
			
// 			$("table").eq(1).append("<tr><td>"+index+"</td><td>"+ $(item).html() +"</td></tr>");
			
			// 배열 활용할 경우
			let arr = [
				{no:"1번", name:"금"},
				{no:"2번", name:"토"},
				{no:"3번", name:"일"}
			];
			
			// -> 지정할 선택자 없으므로 $.each() 호출 후 파라미터 배열,함수 형태로 지정
			$.each(arr, function(index, item) {
				$("table").eq(1).append("<tr><td>"+ item.no +"</td><td>"+ item.name +"</td></tr>");
			});
			
		});
		
	});
</script>
</head>
<body>
	<h1>jQuery - test9.jsp</h1>
	<h3>item-0</h3>
	<h3>item-1</h3>
	<h3>item-2</h3>
	<div></div>
	<table border="1">
		<tr>
			<td>번호</td>
		</tr>
	</table>
	<table border="1">
		<tr>
			<td>번호</td>
			<td>항목</td>
		</tr>
	</table>
</body>
</html>