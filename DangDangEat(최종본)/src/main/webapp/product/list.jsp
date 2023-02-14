<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no, user-scalable=no">
<title>list | Template</title>
<link href="css/styles.css" rel="stylesheet" />
 <link rel="stylesheet" href="resources/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="resources/css/slicknav.min.css"
	type="text/css">
<link rel="stylesheet" href="resources/css/style.css" type="text/css">	

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
<!-- Css Styles -->
<!-- <link rel="stylesheet" href="resources/css/bootstrap.min.css"
	type="text/css">

Bootstrap icons
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
Core theme CSS (includes Bootstrap)
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="resources/css/elegant-icons.css"
	type="text/css">
<link rel="stylesheet" href="resources/css/magnific-popup.css"
	type="text/css">
<link rel="stylesheet" href="resources/css/nice-select.css"
	type="text/css">
<link rel="stylesheet" href="resources/css/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet" href="resources/css/slicknav.min.css"
	type="text/css">
<link rel="stylesheet" href="resources/css/style.css" type="text/css">
-->

<style>
h1 {
	text-align: center;
}

/*default*/
@font-face {
	font-family: 'GmarketSansMedium';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

@font-face {
	/* 영어(소문자,대문자)적용 */
	font-family: 'Abril Fatface';
	src:
		url('https://fonts.googleapis.com/css2?family=Bigshot+One&display=swap');
	unicode-range: U+0041-005A, U+0061-007A;
}

body {
	font-family: "GmarketSansMedium";
}

.card-img-top {
    position: relative;
    width: 300px;
    height: 300px;
}
.card-img-top img {
    position: absolute;
    top: 0;
    left: 0;
    transform: translate(50, 50);
    width: 100%;
    height: 100%;
    object-fit: cover;
    margin: auto;
}



</style>
<script src="js/jquery-3.6.3.js"></script>
<script>
	//검색 기능 추가
	function fn_search() {
		location.href = "./ProductList.pd?keyword=" + $("#query").val();
	}
	
	
	function fn_count_qty(obj,price) {

		var totalPrice =obj.value*price;
		totalPrice = totalPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
		$(".totalPrice").text(totalPrice+" 원");
	}



	$(function() {
		
		$("#cart").on("click",function(){
			   
		      var url_href = window.location.href;
		      var url = new URL(url_href);

		      alert($("#amount").val());
		       
		       $.ajax({
		          url: "CartInsert.ct",
		          data: {
		             pro_code : url.searchParams.get("pro_code"),
		             amount : $("#amount").val(),
		             pro_name : $("#pro_name").val()
		          },
		          success: function(result) {
		             alert("성공");
		          }
		          
		          
		       });//    $.ajax({

	 	 });
	 });
	
</script>

</head>
<body>
	<!-- top.jsp -->
	<jsp:include page="../inc/top.jsp" />

	<!-- Shop Section Begin -->
	<section class="shop spad"
		style="padding-top: 50px; padding-bottom: 100px;">
		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<div class="shop__sidebar">
						<!-- 사이드 메뉴바 -->
						<div class="shop__sidebar__accordion">
							<div class="accordion" id="accordionExample">
								<div class="card">
									<div class="card-title">
										<h5 style="margin-bottom: revert;">
											<a href="./ProductList.pd" class="fst-italic"><strong>Categories</strong></a>
										</h5>
									</div>
									<div id="collapseOne" class="collapse show"
										data-parent="#accordionExample">
										<div class="card-body">
											<div class="shop__sidebar__categories">
												<ul class="nice-scroll">
													<li><a href="./ProductList.pd?category=1">사료</a></li>
													<li><a href="./ProductList.pd?category=2">간식</a></li>
													<li><a href="./ProductList.pd?category=3">파우더 · 토핑제</a></li>
													<li><a href="./ProductList.pd?category=4">껌 · 츄르</a></li>
													<li><a  href="./ProductList.pd?category=5">건강보조제</a></li>
												</ul>
											</div>
										</div>
									</div>
								</div>
								<!-- 검색바 -->
								<div class="shop__sidebar__search">
									<form action="#">
										<input type="text" id="query"
											onkeyup="if(window.event.keyCode==13){fn_search()}"
											placeholder="Search..."> <a
											href="javascript:fn_search()"></a>
										<button type="button" onclick="javascript:fn_search()"><i class="bi bi-search"></i></button>	
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-9">
					<div class="shop__product__option">
						<div class="row">
							<div class="col-lg-6 col-md-6 col-sm-6">
								<div class="shop__product__option__left">
									<p>Showing ${pageInfo.startPage}–${pageInfo.listCount} of ${resultlistCount} results</p>
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
								<!-- 								<div class="shop__product__option__right"> -->
								<!-- 									<p>Sort by Price:</p> -->
								<!-- 									<select> -->
								<!-- 										<option value="">Low To High</option> -->
								<!-- 										<option value="">$0 - $55</option> -->
								<!-- 										<option value="">$55 - $100</option> -->
								<!-- 									</select> -->
								<!-- 								</div> -->
							</div>
						</div>
					</div>
					<div class="row">
							<c:choose>
								<c:when test="${resultlistCount eq 0}">
									<h3>검색결과가 없습니다.</h3>
								</c:when>
								<c:otherwise>
								<!-- 검색결과가 있을 경우 결과 도출  -->
								<c:forEach var="product" items="${productList }">
									<div class="col-lg-4 col-md-6 col-sm-6">
										<div class="product__item">
											<div class="product__item__pic set-bg">
												<!-- Product image-->
												<a href="ProductDetail.pd?pro_code=${product.pro_code}">
													<!-- 상품사진 뿌리기 --> 
													<!-- [방법1] localhost 주소 설정 -->
<!-- 													<img class="card-img-top" -->
<%-- 													src="http://localhost:8080/DangDangEat/upload/${product.pro_real_thumb }" --%>
<!-- 													alt="..." onerror="this.src='./img/sample1_thumb.png';" /> -->
													
													<!-- [방법2] -->
													<img class="card-img-top"
													src="<%=request.getScheme()+"://"+request.getServerName() + ":" + request.getServerPort() +"/"+request.getContextPath()%>/upload/${product.pro_real_thumb }"
													alt="..." onerror="this.src='./img/sample1_thumb.png';" />
												</a>
											</div>
											<div class="product__item__text">
												<div style="height: 50px;">
													<!-- 상품명 -->
													<h6>${product.pro_name }</h6>
												</div>
												<div style="height: 30px;">
													<!-- 브랜드명 -->
													<h8>${product.pro_brand }</h8>
												</div>
												<div style="height: 30px;">
													<!-- 									<i class="bi bi-cart"></i> -->
													<!-- 									<a href="#" class="add-cart">+ Add To Cart</a> -->
													<!--상품가격-->
													<h5>
														<fmt:formatNumber value="${product.pro_price}"
															pattern="#,###" />
														원
													</h5>
												</div>
												<!-- 									<div class="product__color__select"> -->
												<!-- 										<label for="pc-4"> <input type="radio" id="pc-4"> -->
												<!-- 										</label> <label class="active black" for="pc-5"> <input -->
												<!-- 											type="radio" id="pc-5"> -->
												<!-- 										</label> <label class="grey" for="pc-6"> <input type="radio" -->
												<!-- 											id="pc-6"> -->
												<!-- 										</label> -->
												<!-- 									</div> -->
											</div>
											<div>
												<!-- 장바구니 -->
												<form action="CartInsert.ct" method="post"
													style="display: inline">
													<input type="hidden" name="pro_code"
														value=${product.pro_code }> <input type="hidden"
														name="path" value="product"> <input type="hidden"
														name="amount" value="1">
													<!-- 											<i class="bi-cart-fill me-1"></i> -->
													<input type="submit"
														class="btn btn-outline-dark flex-shrink-0"
														value="Add to Cart">
												</form>
												<!-- wishList -->
												<form action="WishlistInsert.ct" method="post"
													style="display: inline">
													<input type="hidden" name="pro_code"
														value=${product.pro_code }> <input type="hidden"
														name="amount" value="1">
													<!-- 											<i class="bi bi-heart-fill"></i> -->
													<input type="submit"
														class="btn btn-outline-dark flex-shrink-0"
														value="Wishlist">
												</form>
											</div>
										</div>
									</div>
								</c:forEach>
								</c:otherwise>
							</c:choose>

					</div>
				</div>
			</div>
		</div>
		<!-- 페이징처리 -->
		<div class="row" id="pageList">
			<div class="col-lg-12">
				<div class="product__pagination">
					<!-- 페이지 번호 목록은 시작 페이지(startPage)부터 끝 페이지(endPage) 까지 표시 -->
					<c:forEach var="i" begin="${pageInfo.startPage}"
						end="${pageInfo.endPage}">
						<!-- 단, 현재 페이지 번호는 링크 없이 표시 -->
						<c:choose>
							<c:when test="${pageNum eq i}">
								<a class="active" href="#none"> ${i}</a>
							</c:when>
							<c:otherwise>
								<a href="ProductList.pd?pageNum=${i }">${i }</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>

				</div>
			</div>
		</div>
	</section>
	<!-- Shop Section End -->
	
	<!-- Footer-->
	   <footer class="py-5 bg-dark">
	      <div class="container">
	         <p class="m-0 text-center text-white">Copyright &copy; DangDangEat 2023</p>
	      </div>
	   </footer>

	<!-- Js Plugins -->
	<script src="js/jquery-3.6.3.js"></script>
	<!--     <script src="resources/js/jquery-3.3.1.min.js"></script> -->
	<!--     <script src="resources/js/bootstrap.min.js"></script> -->
	<!--     <script src="resources/js/jquery.nice-select.min.js"></script> -->
	<!--     <script src="resources/js/jquery.nicescroll.min.js"></script> -->
	<!--     <script src="resources/js/jquery.magnific-popup.min.js"></script> -->
	<!--     <script src="resources/js/jquery.countdown.min.js"></script> -->
	<!--     <script src="resources/js/jquery.slicknav.js"></script> -->
	<!--     <script src="resources/js/mixitup.min.js"></script> -->
	<!--     <script src="resources/js/owl.carousel.min.js"></script> -->
	<!--     <script src="resources/js/main.js"></script> -->
</body>
</html>