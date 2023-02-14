<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<% pageContext.setAttribute("replaceChar", "\n"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>product_insertForm</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">
	
	function confirm_insert() {
		alert("상품등록이 완료되었습니다!");
	}
	
	//옵션 추가 폼 (사용안할예정이라 주석처리함)
// 	function fn_addOption(obj){
// 		if(obj.classList.contains("bi-plus")){
// 			obj.classList.remove('bi-plus');
// 			var innerRow ='<tr><td><input class="form-control"/></td>';
// 			innerRow+='<td  class="text-center"><button onclick="fn_addOption(this)" class="bi bi-plus fs-5"></button> </td></tr>';
// 			$('#optionTable > tbody:last').append(innerRow);
// 			obj.classList.add('bi-dash');
// 		}else{
// 			 document.getElementById('optionTable').deleteRow(obj.parentNode.parentNode.rowIndex);
// 		}
		
// 	}

</script>
<style>
h2 {
	text-align: center;
	padding-top: 20px;
}


th {
	background-color: #e9ecef;
/* 	text-align: center; */
}


body {
    font-family:"GmarketSansMedium" ;
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

</style>
</head>
<body>
	<header>
		<!-- Login, Join 링크 표시 영역(inc/top.jsp 페이지 삽입) -->
		<%-- 		<jsp:include page=""></jsp:include> --%>
	</header>
	<br>
	<h2>상품 등록</h2>
	<br>
	<div class="container mr-sm-2">
		<form action="ProductInsertPro.pd" method="post" name="InsertForm"
			enctype="multipart/form-data" style="width: 700px;">
			<table class="table table-bordered">
				<tr>
					<th>상품 코드</th>
					<td></td>
				</tr>
				<tr>
					<th>상품명</th>
					<td><input type="text" class="form-control" name="pro_name" id="pro_name" size="50"
						placeholder="상품명을 입력하세요"></td>
				</tr>
				<tr>
					<th>카테고리</th>
					<td><select class="form-select" size="1" id="cate_code"
						name="cate_code">
							<option value="" selected="selected">카테고리를 선택하세요</option>
							<option value="1">사료</option>
							<option value="2">간식</option>
							<option value="3">파우더 · 토핑</option>
							<option value="4">껌 · 츄르</option>
							<option value="5">건강보조제</option>
					</select></td>
				</tr>
				<tr>
					<th>브랜드</th>
					<td><select class="form-select" size="1" id="pro_brand"
						name="pro_brand">
							<option value="" selected="selected">브랜드를 선택하세요</option>
							<option value="1">없음</option>
							<option value="2">LILY'S KITCHEN</option>  <!-- 릴리스키친 -->
							<option value="3">PETSGREEN</option>  <!-- 펫츠크린 -->
							<option value="4">RICHZ BOX</option>  <!-- 리치즈박스 -->
							<option value="5">LORENZ</option> <!-- 로렌츠 -->
					</select></td>
				</tr>
				<tr>
					<th>옵션</th>
					<td><select class="form-select" size="1" id="pro_option"
						name="pro_option">
							<option value="" selected="selected">옵션을 선택하세요</option>
							<option value="1">강아지용</option>
							<option value="2">고양이용</option>
							<option value="3">사람용</option>
							<option value="4">호랑이용</option>
					</select></td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td>종류</td> -->
<!-- 					<td> -->
<!-- 						<table class="table" id="optionTable">  -->
<%-- 							<colgroup> --%>
<%-- 								<col style=""> --%>
<%-- 								<col style="width:150px;"> --%>
<%-- 							</colgroup> --%>
<!-- 						<thead> -->
<!-- 							<th scope="col" class="text-center">종류명</th> -->
<!-- 					        <th scope="col" class="text-center">추가/삭제</th> -->
<!-- 						</thead> -->
<!-- 						<tbody> -->
<!-- 							<td><input class="form-control"/></td> -->
<!-- 							<td  class="text-center"><button onclick="fn_addOption(this)" class="bi bi-plus fs-5"></button></td> -->
<!-- 						</tbody> -->
<!-- 						</table> -->
					
<!-- 					</td> -->
<!-- 				</tr> -->
				<tr>
					<th>상품 재고</th>
					<td><input class="form-control" type="number" name="pro_qty" id="pro_qty" size="20" placeholder="상품 재고를 선택하세요 ex) 10"></td>
				</tr>
				<tr>
					<th>상품 가격</th>
					<td><input type="text" class="form-control" name="pro_price" id="pro_price"
						size="20" required="required" placeholder="상품 가격을 입력하세요 ex) 50000"></td>
				</tr>
				<tr>
					<th>판매 여부</th>
					<td><input class="form-check-input" type="radio" name="pro_yn"
						id="pro_yn" size="20" value="1">&nbsp;&nbsp;판매중&nbsp;&nbsp;
						<input class="form-check-input" type="radio" name="pro_yn"
						id="pro_yn" size="20" value="2">&nbsp;&nbsp;판매중단&nbsp;&nbsp;
						<input class="form-check-input" type="radio" name="pro_yn"
						id="pro_yn" size="20" value="3">&nbsp;&nbsp;재고없음</td>
				</tr>
				<tr>
					<th>상품 메인이미지</th>
					<td><input class="form-control" type="file" name="pro_thumb"
						id="pro_thumb" size="20"></td>
				</tr>
				<tr>
					<th>상품 상세이미지</th>
					<td><input class="form-control" type="file" name="pro_img"
						id="pro_img" size="20"></td>
				</tr>
				<tr>
					<th>상품 상세정보</th>
					<td><textarea class="form-control" rows="10" cols="50"
							id="pro_info" name="pro_info" placeholder="상품 상세내용을 입력하세요" ></textarea>
						<div id="content_cnt">(0 / 500)</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input class="btn btn-outline-dark" type="submit" value="상품등록" onclick="javascript:confirm_insert()">&nbsp;&nbsp;
						<input class="btn btn-outline-dark" type="button" value="취소"
						onclick="top.window.close()"></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="../js/scripts.js"></script>
</body>
</html>