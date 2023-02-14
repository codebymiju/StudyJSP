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
		$("#btnSelect").on("click", function() {
			$.ajax({
				type: "get",
				url: "MemberJSONList.me",
				dataType: "json"
			})
			.done(function(data) { 
// 				$("#resultArea").html(JSON.stringify(data));
// 				alert(JSON.stringify(data));
				
				for(let member of data) {
// 					alert(JSON.stringify(member));
					$("#resultArea > table").append(
						"<tr><td>" + member.name + "</td>"
						+ "<td>" + member.age + "</td>"
						+ "<td>" + member.address.address1 + "</td>"
						+ "<td>" + member.address.address2 + "</td></tr>"
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
	<input type="button" value="회원 목록 조회" id="btnSelect">
	<div id="resultArea">
		<table border="1">
			<tr>
				<th>이름</th>
				<th>나이</th>
				<th>주소1</th>
				<th>주소2</th>
			</tr>
		</table>
	</div>
</body>
</html>
