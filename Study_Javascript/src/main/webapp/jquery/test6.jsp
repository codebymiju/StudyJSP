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
		// 버튼 클릭 시 이벤트 수행
		$("#btn").click(function() {
			// 선택자 요소에 eq() 함수 사용 > 인덱스 해당 요소 지정
			// 요소 지정 후 attr() or prop()함수를 통해 해당 요소의 속성에 접근
			let attr1 = $("input[type=checkbox]").eq(1).attr("checked");
			let prop1 = $("input[type=checkbox]").eq(1).prop("checked");
			
			// checked 속성이 존재하지 않기에 undefined - attr
			// checked 속성의 상태에 따라 true 혹은 false - prop
			let attr2 = $("input[type=checkbox]").eq(2).attr("checked");
			let prop2 = $("input[type=checkbox]").eq(2).prop("checked");
			
			let is1 = $("input[type=checkbox]").eq(1).is(":checked");
			let is2 = $("input[type=checkbox]").eq(2).is(":checked");
			
			// 결과 확인
			$("#result").html(
					"attr1 = " + attr1 + "<br>prop1 = " + prop1
					+ "<br>attr2 = " + attr2 + "<br>prop2 = " + prop2
					+ "<br>is1 = " + is1 + "<br>is2 = " + is2);
			
		});
	});
</script>
</head>
<body>
	<h1>jQuery - test6.jsp</h1>
	<table border="1">
        <tr>
            <th><input type="checkbox" id="allCheck"></th>
            <th>번호</th>
            <th>이름</th>
        </tr>
        <tr>
            <td><input type="checkbox" id="check1" name="check" checked="checked" value="1"></td>
            <td>1</td>
            <td>홍길동</td>
        </tr>
        <tr>
            <td><input type="checkbox" name="check" value="2"></td>
            <td>2</td>
            <td>이순신</td>
        </tr>
        <tr>
            <td><input type="checkbox" name="check" value="3"></td>
            <td>3</td>
            <td>강감찬</td>
        </tr>
        <tr>
        	<td colspan="3">
        		<input type="button" value="확인" id="btn"><br>
        		<div id="result">결과 확인 위치</div>
        	</td>
        </tr>
    </table>	
</body>
</html>