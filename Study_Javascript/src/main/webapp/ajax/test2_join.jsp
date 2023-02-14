<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 외부 CSS 가져오기 -->
<link href="css/default.css" rel="stylesheet" type="text/css">
<script src="js/jquery-3.6.3.js"></script>
<script type="text/javascript">
	$(function() {
		// 아이디 입력 항목에 변동 사항이 있을 경우 이벤트 처리
		$("#id").on("change", function() {
			
			// AJAX 를 활용하여 MemberCheckId.me 서블릿 요청을 통해
			// 아이디 중복 검사 작업 수행 후 결과값(사용 가능한 아이디, 이미 존재하는 아이디) 리턴
			$.ajax({
				url: "MemberCheckId.me",
				data: {
					id: $("#id").val()
				},
				success: function(result) {
					// 리턴받은 판별 결과("true", "false") 판별
					if(result == "true") { // 아이디 존재(중복)
						$("#checkIdResult").html("이미 존재하는 아이디").css("color", "red");
					} else {
						$("#checkIdResult").html("사용 가능한 아이디").css("color", "blue");
					}
				}
			});
			
		}); // id.change
		
		$("#selectDomain").on("change", function() {
			// 선택된 도메인의 값을 email2 value 값으로 변경
			let domain = $("#selectDomain").val();
			$("#email2").val(domain);
			
			// 단, 선택된 도메인이 "직접입력"이 아닐 경우 email2 입력장 잠금
			if(domain == "") {
				$("#email2").prop("readonly", false); // 입력창 잠금 해제
				$("#email2").css("background", "white"); // 배경색 흰색
				$("#email2").focus(); // 커서 요청				
			} else {
				$("#email2").prop("readonly", true); // 입력창 잠금
				$("#email2").css("background", "lightgray"); // 배경색 회색
			}
		}); // selectDomain.change
		
	}); // func()
</script>
</head>
<body>
	<header>
		<!-- Login, Join 링크 표시 영역(inc/top.jsp 페이지 삽입) -->
<%-- 		<jsp:include page="/inc/top.jsp"></jsp:include> --%>
	</header>
	<article>
		<h1>회원 가입</h1>
		<form action="" method="post" name="joinForm">
			<table border="1">
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" id="name" required="required" size="20"></td>
				</tr>
				<tr>
					<td>성별</td>
					<td>
						<input type="radio" name="gender" value="남">남&nbsp;&nbsp;
						<input type="radio" name="gender" value="여" checked="checked">여
					</td>
				</tr>
				<tr>
					<td>E-Mail</td>
					<td>
						<input type="text" name="email1" id="email1" required="required" size="10">@
						<input type="text" name="email2" id="email2" required="required" size="10">
						<select name="selectDomain" id="selectDomain">
							<option value="">직접입력</option>	
							<option value="naver.com">naver.com</option>
							<option value="nate.com">nate.com</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>아이디</td>
					<td>
						<input type="text" name="id" id="id" required="required" size="20" placeholder="4-16자리 영문자,숫자 조합">
						<span id="checkIdResult"><!-- 자바스크립트에 의해 메세지가 표시될 공간 --></span>
					</td>
				</tr>
				<tr>
					<td>패스워드</td>
					<td>
						<input type="password" name="passwd" id="passwd" required="required" size="20" placeholder="8-20자리 영문자,숫자,특수문자 조합">
						<span id="checkPasswdResult"><!-- 자바스크립트에 의해 메세지가 표시될 공간 --></span>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="회원가입">
						<input type="button" value="취소" onclick="history.back()">
					</td>
				</tr>
			</table>
		</form>
	</article>
</body>
</html>