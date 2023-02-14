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
		$("form").submit(function() {
			// #selectBox 선택자, 자식 option요소 중 첫번째 option 태그의
			// selected 속성값
// 			alert($("#selectBox > option").eq(0).prop("selected"));
			
			if($("#selectBox > option").eq(0).prop("selected")) {
				alert("항목 선택 필수!");
				return false; // 폼 전송 취소
			} else {
				return true; // 폼 전송 수행 				
			}
			
		})
	});
</script>
</head>
<body>
	<h1>jQuery - test7.jsp</h1>
	<div id="wrap">
		<form action="test7_result.jsp">
			<div id="inputBox">
				아이디 : <input type="text" name="id"><br>
				패스워드 : <input type="password" name="passwd">
			</div>
			<select id="selectBox" name="subject">
				<option value="선택하세요">선택하세요</option>
				<option value="자바">자바</option>
				<option value="JSP">JSP</option>
				<option value="스프링">스프링</option>
			</select>
			<br>
			<input type="submit" value="전송">
		</form>
	</div>
</body>
</html>












