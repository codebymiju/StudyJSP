<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>product_modifyForm</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" >
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
<script type="text/javascript">

	//상품 수정 클릭 시 확인창
	function confirm_modify() {
		let result = confirm("상품을 수정하시겠습니까?");
		
		if(result){
// 			form.action = 'ProductModifyPro.pd';
// 			form.submit();
			alert("상품 수정이 완료되었습니다!");
			opener.document.location.reload();
			self.close();
// 			window.close();
		}

	}
		
	//삭제 진행여부 확인
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
			alert("상품 삭제가 완료되었습니다")
		}
// 			location.href = "ProductList.pd";
	}

	//radio(판매여부 pro_yn)에서 선택된 value값 가져오기
	var value = document.querySelector('input[name="pro_yn"]:checked').value;
	
	
	//카테고리 선택된 value값 가져오기
	var select_cate_code = function(value2) {
		$("#cate_code").val(value2);
	};
	
		
</script>
<style>
h2 {
	text-align: center;
	padding-top: 20px;
}

/*default*/
@font-face {
    font-family: 'GmarketSansMedium';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
  
@font-face{
    /* 영어(소문자,대문자)적용 */
    font-family: 'Abril Fatface' ;
    src: url('https://fonts.googleapis.com/css2?family=Bigshot+One&display=swap') ;
	unicode-range:U+0041-005A,U+0061-007A ;
}

body {
    font-family:"GmarketSansMedium" ;
}
</style>
</head>
<body>
	<header>
		<!-- Login, Join 링크 표시 영역(inc/top.jsp 페이지 삽입) -->
		<%-- 		<jsp:include page=""></jsp:include> --%>
	</header>
<%-- 	${product.pro_code} --%>
	<br>
	<h2>상품 수정</h2>
	<br>
	<div class="container mr-sm-2">
		<form action="ProductModifyPro.pd" method="post" name="ModifyForm" id="ProductMoidfyForm" enctype="multipart/form-data">
			<!-- 파일 수정 시 기존파일 삭제를 위해 실제 파일명도 파라미터로 전달 필요 -->
			<input type="hidden" name="">
			<table class="table table-bordered">
				<tr>
					<th>상품 코드</th>
					<td><input type="text" class="form-control" name="pro_code" value="${product.pro_code }" id="pro_code" size="20" readonly="readonly"></td>
				</tr>
				<tr>
					<th>상품명</th>
					<td><input type="text" class="form-control" name="pro_name" value="${product.pro_name }" id="pro_name" size="50"
						placeholder="상품명을 입력하세요"></td>
				</tr>
				<tr>
					<th>카테고리</th>
					<th><select class="form-select" size="1" id="cate_code" name="cate_code" onchange="select_cate_code(this.value2)">
							<option value="" >카테고리를 선택하세요</option>
							<option value="1" <c:if test="${product.cate_code eq '1'}">selected</c:if>>사료</option>
							<option value="2" <c:if test="${product.cate_code eq '2'}">selected</c:if>>간식</option>
							<option value="3" <c:if test="${product.cate_code eq '3'}">selected</c:if>>파우더 · 토핑</option>
							<option value="4" <c:if test="${product.cate_code eq '4'}">selected</c:if>>껌 · 츄르</option>
							<option value="5" <c:if test="${product.cate_code eq '5'}">selected</c:if>>건강보조제</option>
					</select></th>
				</tr>
				<tr>
					<th>브랜드</th>
					<th><select  class="form-select" size="1" id="pro_brand" name="pro_brand" onchange="select_pro_brand">
							<option value="">브랜드를 선택하세요</option>
							<option value="1" <c:if test="${product.pro_brand eq '1'}">selected</c:if>>없음</option>
							<option value="2" <c:if test="${product.pro_brand eq '2'}">selected</c:if>>LILY'S KITCHEN</option>
							<option value="3" <c:if test="${product.pro_brand eq '3'}">selected</c:if>>PETSGREEN</option>
							<option value="4" <c:if test="${product.pro_brand eq '4'}">selected</c:if>>RICHZ BOX</option>
							<option value="5" <c:if test="${product.pro_brand eq '5'}">selected</c:if>>LORENZ</option>
					</select></th>
				</tr>
				<tr>
					<th>옵션</th>
					<td><select class="form-select" size="1" id="pro_option" name="pro_option">
							<option value="" >옵션을 선택하세요</option>
							<option value="1" <c:if test="${product.pro_option eq '1'}">selected</c:if>>강아지용</option>
							<option value="2" <c:if test="${product.pro_option eq '2'}">selected</c:if>>고양이용</option>
							<option value="3" <c:if test="${product.pro_option eq '3'}">selected</c:if>>사람용</option>
							<option value="4" <c:if test="${product.pro_option eq '4'}">selected</c:if>>호랑이용</option>
					</select></td>
				</tr>
				<tr>
					<th>상품 재고</th>
					<td><input type="number" class="form-control" name="pro_qty" id="pro_qty" size="20" value="${product.pro_qty }"></td>
				</tr>
				<tr>
					<th>상품 가격</th>
					<td><input type="number" class="form-control" name="pro_price" id="pro_price"
						size="20" required="required" value="${product.pro_price }" ></td>
				</tr>
				<tr>
					<th>판매 여부</th>
					<td><input class="form-check-input" type="radio" name="pro_yn" id="pro_yn" size="20" value="1" <c:if test="${product.pro_yn eq '1'}">checked</c:if>>&nbsp;&nbsp;판매중&nbsp;&nbsp;
						<input class="form-check-input" type="radio" name="pro_yn" id="pro_yn" size="20" value="2" <c:if test="${product.pro_yn eq '2'}">checked</c:if>>&nbsp;&nbsp;판매중단&nbsp;&nbsp;
						<input class="form-check-input" type="radio" name="pro_yn" id="pro_yn" size="20" value="3" <c:if test="${product.pro_yn eq '3'}">checked</c:if>>&nbsp;&nbsp;재고없음
					</td>
				</tr>
				<tr>
					<th>상품 메인이미지</th>
					<td><input class="form-control" type="file" name="pro_thumb" id="pro_thumb"
						size="20" value="${product.pro_thumb }">
						<br>(기존파일 : ${product.pro_thumb })</td>
				</tr>
				<tr>
					<th>상품 상세이미지</th>
					<td><input class="form-control" type="file" name="pro_img" id="pro_img" size="20">
					<br>(기존파일 : ${product.pro_img })</td>
				</tr> 
			
				<tr>
					<th>상품 상세정보</th>
					<td><textarea class="form-control" rows="10" cols="50" id="pro_info" name="pro_info" >${product.pro_info }</textarea></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input class="btn btn-outline-dark" type="submit" value="수정" onclick="javascript:confirm_modify()">&nbsp;&nbsp;
						<input class="btn btn-outline-dark" type="button" value="취소" onclick="top.window.close()">&nbsp;&nbsp;
						<input class="btn btn-outline-dark" type="button" value="삭제" onclick="javascript:confirm_delete()">&nbsp;&nbsp;
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="../js/scripts.js"></script>
</body>
</html>