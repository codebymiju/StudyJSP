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
// 		$("#wrap_append").append("<div>append()로 삽입한 div 태그 </div>");
// 		$("<div>appendTo()로 삽입한 div 태그 </div>").appendTo("#wrap_append");
		
		$("#wrap_prepend").prepend("<div>prepend()로 삽입한 div 태그 </div>");
// 		$("<div>prependTo()로 삽입한 div 태그 </div>").prependTo("#wrap_prepend");
		
// 		$("#wrap_after").after("<div>after()로 삽입한 div 태그 </div>");
		$("<div>insertAfter()로 삽입한 div 태그 </div>").insertAfter("#wrap_after");
// 		$("#wrap_before").before("<div>before()로 삽입한 div 태그 </div>");
		$("<div>insertBefore()로 삽입한 div 태그 </div>").insertBefore("#wrap_before");
		
		
		$("#ta").val($("#wrap").html());
	});
</script>
</head>
<body>
	<h1>JQuery - test5.jsp</h1>
	<div id="wrap">
		<div id="wrap_append">
			<div>
				<div>div 태그1</div>
				<div>div 태그2</div>
			</div>		
		</div>
		<hr>
		<div id="wrap_prepend">
			<div>
				<div>div 태그1</div>
				<div>div 태그2</div>
			</div>		
		</div>
		<hr>
		<div id="wrap_after">
			<div>
				<div>div 태그1</div>
				<div>div 태그2</div>
			</div>		
		</div>
		<hr>
		<div id="wrap_before">
			<div>
				<div>div 태그1</div>
				<div>div 태그2</div>
			</div>		
		</div>
	</div>	
	<!-- 결과 확인을 위한 태그 출력용 textarea -->
	<textarea id="ta" rows="20" cols="100"></textarea>
</body>
</html>