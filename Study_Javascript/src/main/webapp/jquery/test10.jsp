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
		$("table").css("width", "300px");
		$("table").css("text-align", "center");
		
		$("#allCheck").on("change", function() {
			// 전체선택 체크박스 체크 시 모든 체크박스 체크
			if($("#allCheck").is(":checked")) { // 전체선택 체크박스 상태가 checked인지 판별
// 				$(":checkbox").prop("checked", "true");
				$(":checkbox").each(function(index,item) { // each() 사용해 체크박스 반복
					$(item).prop("checked", true);
				})
			
			} else {
				$(":checkbox").prop("checked", false);
			}
		});
		
		// 셀렉트박스 항목 선택(항목 변경)시 이벤트 처리
		$("#selectBox").on("change", function() {
			alert($("#selectBox").val() + " 선택됨");
			
// 			if($("#selectBox").val() == "전체"){} 
// 			if($("#selectBox").eq(0).is(":checked") ){}
		
		});
		
		// 텍스트박스의 change 이벤트 = 입력 끝난 후 포커스 해제 시 내용 변경 여부 판별하여 동작
		$("input[name=search]").on("change", function() {
// 			alert("내용 변경됨!")
			$("#result").html($("input[name=search]").val());
		});
		
		// 텍스트박스의 keyup 이벤트는 키를 눌렀다 뗄 때 무조건 동작
		$("input[name=search]").on("keyup", function() {
			$("#result").html($("input[name=search]").val());
		});
		
	});
</script>
</head>
<body>
	<h1>jQuery - test10.jsp</h1>
	<table border="1">
        <tr>
        	<td colspan="3">
		        <select id="selectBox" name="subject">
					<option value="전체">전체</option>
					<option value="VIP">VIP</option>
					<option value="일반">일반</option>
				</select>
			</td>
		</tr>
        <tr>
            <th><input type="checkbox" id="allCheck">전체선택</th>
            <th>번호</th>
            <th>이름</th>
        </tr>
        <tr>
            <td><input type="checkbox" name="check"></td>
            <td>1</td>
            <td>홍길동</td>
        </tr>
        <tr>
            <td><input type="checkbox" name="check"></td>
            <td>2</td>
            <td>이순신</td>
        </tr>
        <tr>
            <td><input type="checkbox" name="check"></td>
            <td>3</td>
            <td>강감찬</td>
        </tr>
        <tr>
        	<td colspan="3">
		        <select id="searchType" name="searchType">
					<option value="이름">이름</option>
					<option value="아이디">아이디</option>
				</select>
				<input type="text" name="search" placeholder="검색어 입력">
			</td>
		</tr>
    </table>
    <div id="result"></div>
</body>
</html>