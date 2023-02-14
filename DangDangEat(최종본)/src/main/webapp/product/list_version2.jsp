<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<style type="text/css">
@font-face {
    font-family: 'GmarketSansMedium';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
  
body {
    font-family:"GmarketSansMedium" ;
}

</style>
<style type="text/css">
	* {
		margin: 0;
		padding: 0;
	}
	
	ul {
		list-style: none;
	}
	
	a {
		color: inherit;
		text-decoration: none;
	}
	
	#wrap {
		width: 100%;
		height: 100%;
		display: flex;
		flex-direction: row;
		flex-wrap: wrap;
		justify-items: flex-start;
		align-items: stretch
	}
	
	img {
		max-width: 100%;
	}
	
	#nav {
		flex-basis: 20vw;
		flex-grow: 1;
		flex-shrink: 4;
		text-align: center;
	}
	
	#nav ul li {
		line-height: 50px;
		font-weight: bold;
		font-size: 20px;
	}
	
	.logoli {
		padding: 20px 0;
	}
	
	.widget--vendor .widget-title {
	    margin-bottom: 35px;
	    border-bottom: 2px solid #5fa30f;
	    line-height: 3em;
	    font-size: 20px;
	    color: #000;
	    font-weight: 600;
	}
	
	/* Top Button */
	
	#myBtn:hover {
	  background-color: #555; /* Add a dark-grey background on hover */
	}

</style>

<script src="js/jquery-3.6.3.js"></script>
<!--<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.14.4/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
 <script
	src="https://cdn.jsdelivr.net/npm/jquery-match-height@0.7.2/dist/jquery.matchHeight.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.6.1/gsap.min.js" integrity="sha512-cdV6j5t5o24hkSciVrb8Ki6FveC2SgwGfLE31+ZQRHAeSRxYhAQskLkq3dLm8ZcWe1N3vBOEYmmbhzf7NTtFFQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.6.1/ScrollToPlugin.min.js" integrity="sha512-kSI9CgGh60rJbNVeMJvfNX0UTKAq8LEOea/yKJQbFpIroxT7bf9/zUFXbnfsQP5F6xlOOHtCfBPgsE1ceiHnRw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="assets/js/main.js"></script> -->
<script>
	function fn_search(){
		location.href ="./ProductList.pd?keyword="+$("#query").val();
	}
</script>
</head>
<body>
	<!-- Header-->

	<!-- top.jsp -->
	<jsp:include page="../inc/top.jsp" />

	<!-- Section-->
	<section class="py-5">
		<!-- nav -->
		<!-- 		<div id="wrap">
			<nav id="nav">
				<ul>
					<li class="logoli">
					<a href="/" class="logo">
					<img src="http://gahyun.wooga.kr/main/img/testImg/logoimg.png" alt=""></a></li>
					<li><a href="#">사료</a></li>
					<li><a href="#">간식</a></li>
					<li><a href="#">영양제</a></li>
					<li><a href="#">브랜드</a></li>
				</ul>
			</nav>
		</div> -->
		<div class="container-fluid-px py-6 container-fluid">

			<div class="row">

				<div class="products-grid order-lg-2 col-xl-9 col-lg-8 ms-4">
					<div
						class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
						<!-- for 문 시작 -->
						<%--                 	<c:forEach var="item" items="${names }" varStatus="status"> --%>
						<c:forEach var="product" items="${productList }">
							<div class="col mb-5">
								<div class="card h-100">
									<!-- Product image-->
									<a href="ProductDetail.pd?pro_code=${product.pro_code}"> 
									<img class="card-img-top" style="height: 250px"
										src="http://localhost:8080/DangDangEat/upload/${product.pro_real_thumb }"
										alt="..." onerror="this.src='./img/sample1_thumb.png';" />
<!-- 										alt="..." onerror="this.src='./img/error_img.png';" /> -->
									</a>
									<!-- Product details-->
									<div class="card-body p-4">
										<div class="text-center">
											<!-- Product name-->
											<a href="ProductDetail.pd?pro_code=${product.pro_code}">
												<h5 class="fw-bolder">${product.pro_name }</h5>
											</a>
											<!-- Brand Name-->
											${product.pro_brand }<br>
											<!-- Product price-->
											<fmt:formatNumber value="${product.pro_price}"
												pattern="#,###" />
											원
										</div>
									</div>
									<!-- Product actions-->
									<!-- 장바구니 추가 -->
									<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
										<div class="text-center">
											<a class="btn btn-outline-dark mt-auto" href="#">Add Cart</a>
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									<div class="card-footer p-2 pt-0 border-top-0 bg-transparent"> -->
<!-- 										<div class="text-center"> -->
											<a class="btn btn-outline-dark mt-auto" href="#">WishList</a>
										</div>
									</div>
								</div>
							</div>
							<!-- 상품 창 -->
						</c:forEach>
						<!-- for 문 끝 -->
					</div>
				</div>
				<div class="sidebar pe-xl-5 order-lg-1 col-xl-2 col-lg-4">
					<div class="sidebar-block px-3 px-lg-0">
						<div id="wrap">
							<nav id="nav">
								<ul>
									<li><h3 class="widget-title" id="shop">
									<a href="./ProductList.pd" class="fst-italic"><strong>shop</strong></a></h3>
									<li>
									<li><a href="./ProductList.pd?category=1">사료</a></li>
									<li><a href="./ProductList.pd?category=2">간식</a></li>
									<li><a href="./ProductList.pd?category=3">파우더 · 토핑제</a></li>
									<li><a href="./ProductList.pd?category=4">껌 · 츄르</a></li>
									<li><a href="./ProductList.pd?category=5">건강보조제</a></li>
									<li><input type="text" class="search-query" id="query"
										onkeyup="if(window.event.keyCode==13){fn_search()}"
										placeholder="search" style="width: 80%;height: 80%;padding-left: 10px;">
								        <a href="javascript:fn_search()" class="bi bi-search fs-5"></a>
								    </li>
								</ul>
							</nav>
						</div>
					</div>
				</div>
<!-- 				pagination -->
<!-- 					<nav aria-label="..."> -->
<!-- 			<ul class="pagination justify-content-center"> -->
<!-- 				<li class="page-item disabled"><a class ="page-link text-secondary"  href="#" -->
<!-- 					tabindex="-1" aria-disabled="true">Previous</a></li> -->
<!-- 				<li class="page-item"><a class="page-link" href="#">1</a></li> -->
				
<!-- 				<li class="page-item active" aria-current="page"><a -->
<!-- 					class="page-link" href="#">2</a></li> -->
<!-- 				<li class="page-item"><a class="page-link" href="#">3</a></li> -->
<!-- 				<li class="page-item"><a class="page-link text-secondary" href="#" >Next</a></li> -->
<!-- 			</ul> -->
<!-- 		</nav> -->
			</div>
		</div>
	</section>
	<!-- Footer-->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Your
				Website 2022</p>
		</div>
	</footer>
	<!-- Bootstrap core JS-->
<!-- 	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	Core theme JS
	<script src="js/scripts.js"></script> -->
</body>
</html>