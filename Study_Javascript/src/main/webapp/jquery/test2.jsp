<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.6.3.js"></script>
<script type="text/javascript">
	// ready 이벤트를 정의하여 문서 내의 선택자 요소에 접근
	
	$(document).ready(function() {
		alert("ready 이벤트");
	});
	
	$(function() {
// 		alert("압축된 ready 이벤트지롱");
		$("*").css("color","red");
		
		$("#idSelector").css("color","blue");
		
// 		alert($("#idSelector").css("color"));
		
		$(".classSelector").css("color","orange");
		
		let color = $(".classSelector").css("color");
// 		alert(color);

		// 태그 지정하기
// 		$("table").css("border","1px solid blue");
// 		$("table").css("background","yellow");
		
// 		$("table").css("border","1px solid blue").css("background","pink");
		
		// 동일한 대상에 동일한 함수를 적용할 경우
		$("table").css({
			border:"1px solid DodgerBlue",
			background : "lightgrey"
		});
		
		// 태그들 중 특정 속성값을 갖는 태그 지정하기
		$("input[type=text]").css("background", "CadetBlue");
		
		// 인접관계 선택자
		$("documnet").ready(function() { // $(function() {}); 과 동일한 코드
			$("table tr:first").css("background","LimeGreen")
			// 홀수번째 tr
			$("tr:odd").css("background","LightCoral")
			// 짝수번째 tr
			$("tr:even").css("background","LemonChiffon")
			
			let id = $("#inputBox > input[type=text]").val();
			let passwd = $("#inputBox > input[type=password]").val();
			
// 			alert("아이디 : " + id + "패스워드 : " + passwd);
			$("#inputBox textarea[readonly]").css("background","PaleTurquoise");
			
// 			$("#divBox div").css("color","SteelBlue");
			$("#divBox > div").css("color","SteelBlue");
			
			// id선택자 selectBo
			let selectedItem = $("#selectBox > option:selected").val();
			$("#selectResult").val(selectedItem);
			
			
		});
		
		
	});

</script>
</head>
<body>
	<h1>jQuery - test2.jsp</h1>
	<hr>
	<h3 id="idSelector">id 선택자</h3>
	<h3 class="classSelector">class 선택자</h3>
	<h3 class="classSelector">class 선택자2</h3>
	<hr>
	<table border="1">
		<tr><td>번호</td><td>제목</td></tr>
		<tr><td>1</td><td>1번제목</td></tr>
		<tr><td>2</td><td>2번제목</td></tr>
		<tr><td>3</td><td>3번제목</td></tr>
		<tr><td>4</td><td>4번제목</td></tr>
	</table>
	<hr>
	<div id="inputBox">
		<input type="text" value="admin">
		<input type="password" value="1234"><br>
		<textarea rows="5" cols="20" readonly="readonly"></textarea>
		<textarea rows="5" cols="20" id="inputData"></textarea>
	</div>
	<hr>
	<div id="divBox">
		<div>
			1번 div 태그
			<div>1-1번 div 태그</div>
			<div>1-2번 div 태그</div>
		</div>
		<span>span 태그</span>
		<div>2번 div 태그</div>
		<div>
			3번 div 태그
			<div>3-1번 div 태그</div>
			<div>3-2번 div 태그</div>
		</div>
	</div>
	<hr>
	<select id="selectBox">
		<option value="JAVA">JAVA</option>
		<option value="JSP">JSP</option>
		<option value="Spring Framework">Spring Framework</option>
	</select>
	<input type="text" id="selectResult">
</body>
</html>