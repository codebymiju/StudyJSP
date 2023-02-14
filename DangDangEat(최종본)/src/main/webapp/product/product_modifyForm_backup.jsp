<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
<style type="text/css">
h1 {
	text-align: center;
}
</style>
<script type="text/javascript">

	function confirm_delete() {
		// Confirm Dialog 를 활용하여 "상품을 삭제하시겠습니까?" 질문 처리
		let result = confirm("상품을 삭제하시겠습니까?");	
		
		// 선택된 결과값이 true 일 경우 페이지로 이동
		// 단, multi로 설정했기때문에(=> 첨부파일 등록이 아니므로) 
		// form.enctype을 기본값으로 재설정하는 과정이 필요하다!
		if(result) {
			const form = document.getElementById('ProductMoidfyForm');
			form.action = 'ProductDeletePro.pd';
			form.enctype = 'application/x-www-form-urlencoded';
			form.submit();
		}
	}


</script>
</head>
<body>
	<header>
		<!-- Login, Join 링크 표시 영역(inc/top.jsp 페이지 삽입) -->
		<%-- 		<jsp:include page=""></jsp:include> --%>
	</header>
<%-- 	${product.pro_code} --%>
	<h1>상품 수정</h1>
	<div align="center">
		<form action="ProductModifyPro.pd" method="post" name="ModifyForm" id="ProductMoidfyForm"
			enctype="multipart/form-data">
			<!-- 파일 수정 시 기존파일 삭제를 위해 실제 파일명도 파라미터로 전달 필요 -->
			<input type="hidden" name="">
			<table border="1">
				<tr>
					<td>상품 코드</td>
					<td><input type="text" name="pro_code" value="${product.pro_code }" id="pro_code" size="20"></td>
				</tr>
				<tr>
					<td>상품명</td>
					<td><input type="text" name="pro_name" value="${product.pro_name }" id="pro_name" size="50"
						placeholder="상품명을 입력하세요"></td>
				</tr>
				<tr>
					<td>카테고리</td>
					<td><select size="1" id="cate_code" name="cate_code">
							<option value="" >카테고리를 선택하세요</option>
							<option value="1" <c:if test="${product.cate_code eq '1'}">selected</c:if>>사료</option>
							<option value="2" <c:if test="${product.cate_code eq '2'}">selected</c:if>>간식</option>
							<option value="3" <c:if test="${product.cate_code eq '3'}">selected</c:if>>영양제</option>
							<option value="4" <c:if test="${product.cate_code eq '4'}">selected</c:if>>브랜드</option>
					</select></td>
				</tr>
				<tr>
					<td>브랜드</td>
					<td><select size="1" id="pro_brand" name="pro_brand">
							<option value="">브랜드를 선택하세요</option>
							<option value="1" <c:if test="${product.pro_brand eq '1'}">selected</c:if>>없음</option>
							<option value="2" <c:if test="${product.pro_brand eq '2'}">selected</c:if>>브랜드A</option>
							<option value="3" <c:if test="${product.pro_brand eq '3'}">selected</c:if>>브랜드B</option>
							<option value="4" <c:if test="${product.pro_brand eq '4'}">selected</c:if>>브랜드C</option>
							<option value="5" <c:if test="${product.pro_brand eq '5'}">selected</c:if>>브랜드D</option>
					</select></td>
				</tr>
				<tr>
					<td>옵션</td>
					<td><select size="1" id="pro_option" name="pro_option">
							<option value="" >옵션을 선택하세요</option>
							<option value="1" <c:if test="${product.pro_option eq '1'}">selected</c:if>>순한맛</option>
							<option value="2" <c:if test="${product.pro_option eq '2'}">selected</c:if>>매운맛</option>
							<option value="3" <c:if test="${product.pro_option eq '3'}">selected</c:if>>카레맛</option>
							<option value="4" <c:if test="${product.pro_option eq '4'}">selected</c:if>>빨간맛</option>
					</select></td>
				</tr>
				<tr>
					<td>상품 재고</td>
					<td><input type="number" name="pro_qty" id="pro_qty" size="20" value="${product.pro_qty }"></td>
				</tr>
				<tr>
					<td>상품 가격</td>
					<td><input type="number" name="pro_price" id="pro_price"
						size="20" required="required" value="${product.pro_price }" ></td>
				</tr>
				<tr>
					<td>판매 여부</td>
					<td><input type="radio" name="pro_yn" id="pro_yn" size="20" value="1" <c:if test="${product.pro_yn eq '1'}">checked</c:if>>판매중&nbsp;&nbsp;
						<input type="radio" name="pro_yn" id="pro_yn" size="20" value="2" <c:if test="${product.pro_yn eq '2'}">checked</c:if>>판매중단&nbsp;&nbsp;
						<input type="radio" name="pro_yn" id="pro_yn" size="20" value="3" <c:if test="${product.pro_yn eq '3'}">checked</c:if>>재고없음
					</td>
				</tr>
				<tr>
					<td>상품 메인이미지</td>
					<td><input type="file" name="pro_thumb" id="pro_thumb"
						size="20" value="${product.pro_thumb }">
						<br>(기존파일 : ${product.pro_thumb })</td>
				</tr>
				<tr>
					<td>상품 상세이미지</td>
					<td><input type="file" name="pro_img" id="pro_img" size="20">
					<br>(기존파일 : ${product.pro_img })</td>
				</tr>
				<tr>
					<td>상품 상세정보</td>
					<td><textarea rows="10" cols="50" id="pro_info" name="pro_info" >${product.pro_info }</textarea></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="수정">&nbsp;&nbsp;
						<input type="button" value="취소" onclick="history.back()">&nbsp;&nbsp;
						<input type="button" value="삭제" onclick="javascript:confirm_delete()">&nbsp;&nbsp;
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>