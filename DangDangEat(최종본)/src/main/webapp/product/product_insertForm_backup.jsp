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
</head>
<body>
	<header>
		<!-- Login, Join 링크 표시 영역(inc/top.jsp 페이지 삽입) -->
		<%-- 		<jsp:include page=""></jsp:include> --%>
	</header>
	<h1>상품 등록</h1>
	<div align="center">
		<form action="ProductInsertPro.pd" method="post" name="InsertForm"
			enctype="multipart/form-data"
			>
			<table border="1">
				<tr>
					<td>상품 코드</td>
				</tr>
				<tr>
					<td>상품명</td>
					<td><input type="text" name="pro_name" id="pro_name" size="20"
						placeholder="상품명을 입력하세요"></td>
				</tr>
				<tr>
					<td>카테고리</td>
					<td><select size="1" id="cate_code" name="cate_code">
							<option value="" selected="selected">카테고리를 선택하세요</option>
							<option value="1">사료</option>
							<option value="2">간식</option>
							<option value="3">영양제</option>
							<option value="4">브랜드</option>
					</select></td>
				</tr>
				<tr>
					<td>브랜드</td>
					<td><select size="1" id="pro_brand" name="pro_brand">
							<option value="" selected="selected">브랜드를 선택하세요</option>
							<option value="1">없음</option>
							<option value="2">브랜드A</option>
							<option value="3">브랜드B</option>
							<option value="4">브랜드C</option>
							<option value="5">브랜드D</option>
					</select></td>
				</tr>
				<tr>
					<td>옵션</td>
					<td><select size="1" id="pro_option" name="pro_option">
							<option value="" selected="selected">옵션을 선택하세요</option>
							<option value="1">순한맛</option>
							<option value="2">매운맛</option>
							<option value="3">카레맛</option>
							<option value="4">빨간맛</option>
					</select></td>
				</tr>
				<tr>
					<td>상품 재고</td>
					<td><input type="number" name="pro_qty" id="pro_qty" size="20"></td>
				</tr>
				<tr>
					<td>상품 가격</td>
					<td><input type="text" name="pro_price" id="pro_price"
						size="20" required="required"></td>
				</tr>
				<tr>
					<td>판매 여부</td>
					<td><input type="radio" name="pro_yn" id="pro_yn" size="20" value="1">판매중&nbsp;&nbsp;
						<input type="radio" name="pro_yn" id="pro_yn" size="20" value="2">판매중단
						<input type="radio" name="pro_yn" id="pro_yn" size="20" value="3">재고없음
					</td>
				</tr>
				<tr>
					<td>상품 메인이미지</td>
					<td><input type="file" name="pro_thumb" id="pro_thumb"
						size="20"></td>
				</tr>
				<tr>
					<td>상품 상세이미지</td>
					<td><input type="file" name="pro_img" id="pro_img" size="20"></td>
				</tr>
				<tr>
					<td>상품 상세정보</td>
					<td><textarea rows="10" cols="50" id="pro_info" name="pro_info"></textarea></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="상품등록"> <input type="button" value="취소"
						onclick="history.back()"></td>
				</tr>
			</table>
		</form>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>