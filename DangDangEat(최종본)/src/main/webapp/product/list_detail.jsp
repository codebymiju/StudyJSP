<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<% pageContext.setAttribute("replaceChar", "\n"); %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>DangDangEat - Shop Item</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
<!-- 부트스트랩 폰트 아이콘 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script src="https://kit.fontawesome.com/9e19a1717c.js" ></script>
<script type="text/javascript">

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
		             path : "product_detail"
// 		             pro_name : $("#pro_name").val()
		          },
		          success: function(result) {
		             alert("장바구니에 상품이 담겼습니다.");
		          }
		       }); // ajax
		}); //cart
		          
  		$("#wishlist").on("click",function(){
			   
		      var url_href = window.location.href;
		      var url = new URL(url_href);
//  		      alert($("#amount").val());
		       
		       $.ajax({
		          url: "WishlistInsert.ct",
		          data: {
		             pro_code : url.searchParams.get("pro_code"),
		             amount : 1,
		             path : "product_detail"
//  		             pro_name : $("#pro_name").val()
		          },
		          success: function(result) {
		             alert("위시리스트에 상품이 담겼습니다.");
		          }
		          
		          
		       });//    $.ajax({

	 	 }); // wishlist
		
		$("#qna_button").on("click", function() {
			let sendData = $("form").serialize();
			$.ajax({
				type: "post", // AJAX 로 요청 시
				url: "QnaList.bo?pd=1",
				// serialize() 함수를 통해 가져온 폼 데이터를 전송할 데이터로 지정
				data: sendData,
				dataType: "text", // 전송되는 데이터에 대한 타입 지정(일반 데이터는 text)
				success: function(response) { // 요청에 대한 처리 성공 시 이벤트 호출
					// 익명 함수 파라미터로 응답 데이터가 전달됨(처리 페이지의 응답 결과)
					// id 선택자 resultArea 영역에 응답 데이터(response) 출력
					$("#resultArea").html(response);
				},
				error: function(xhr, textStatus, errorThrown) {
					$("#resultArea").html("xhr = " + xhr + "<br>textStatus =  " + textStatus + "<br>errorThrown =  " + errorThrown );
				}
			});
		}); // qna_button
		
		$("#review_button").on("click", function() {
			let sendData = $("form").serialize();
			$.ajax({
				type: "post", // AJAX 로 요청 시
				url: "ReviewList.bo?pd=1",
				// serialize() 함수를 통해 가져온 폼 데이터를 전송할 데이터로 지정
				data: sendData,
				dataType: "text", // 전송되는 데이터에 대한 타입 지정(일반 데이터는 text)
				success: function(response) { // 요청에 대한 처리 성공 시 이벤트 호출
					// 익명 함수 파라미터로 응답 데이터가 전달됨(처리 페이지의 응답 결과)
					// id 선택자 resultArea 영역에 응답 데이터(response) 출력
					$("#resultArea").html(response);
				},
				error: function(xhr, textStatus, errorThrown) {
					$("#resultArea").html("xhr = " + xhr + "<br>textStatus =  " + textStatus + "<br>errorThrown =  " + errorThrown );
				}
			});
		}); // review_button
	 });
		
</script>
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
   

.btn-space {
	    margin-right: 5px;
}

.navbar > .container, .navbar > .container-fluid, .navbar > .container-sm, .navbar > .container-md, .navbar > .container-lg, .navbar > .container-xl, .navbar > .container-xxl {
    display: flex;
    flex-wrap: inherit;
    align-items: center;
    justify-content: space-evenly;
}

.navbar-text {
    padding-top: 0rem;
    padding-bottom: 0rem;
}

</style>
</head>
<body>
	<!-- Header-->
	<!-- top.jsp -->
	<jsp:include page="../inc/top.jsp" />
	<!-- Product section-->
	<section>
		<div class="container px-4 px-lg-5 my-5">
			<div class="row gx-4 gx-lg-5 align-items-center">
				<div class="col-md-6" id="pro_info">
					<!-- Product 메인 썸네일-->
<!-- 					<img class="card-img-top mb-5 mb-md-0"  -->
<%-- 						src="http://localhost:8080/DangDangEat/upload/${product.pro_real_thumb }" --%>
<!-- 						onerror="this.src='./img/sample1_thumb.png';" /> -->
<!-- 						alt="..." onerror= "this.src='./img/error_img.png'"/> -->
						<img class="card-img-top"
						src="<%=request.getScheme()+"://"+request.getServerName() + ":" + request.getServerPort() +"/"+request.getContextPath()%>/upload/${product.pro_real_thumb }"
						alt="..." onerror="this.src='./img/sample1_thumb.png';" />

				</div>
				<div class="col-md-6">
					<!-- 브랜드 페이지 링크 삽입 -->
<!-- 					<div class="small mb-1"> -->
<%-- 						 ${product.pro_brand } --%>
<!-- 					</div> -->
					<h2 class="display-5 fw-bolder" id="pro_name">${product.pro_name }</h2>
					<!-- 상품 상세내용 -->
					<p class="lead fs-5">${fn:replace(product.pro_info, replaceChar, "<br/>")}</p>
					<p class="fs-5">
					판매가 : <span><fmt:formatNumber value="${product.pro_price}" pattern="#,###" /> 원</span><br>
					브랜드 : ${product.pro_brand }<br>
					배송 방법 : <strong>택배 배송</strong><br>
					배송비 : 3,500원 <br>
					</p>
					
					<!-- 선택한 상품 출력 테이블 -->
					<table class="table"> 
						<colgroup>
							<col style="width:50%;">
							<col style="width:20%;">
							<col style="">
						</colgroup>
					  <thead>
					    <tr>
					      <th scope="col" class="text-center">이름</th>
					      <th scope="col" class="text-center">수량</th>
					      <th scope="col" class="text-center">가격</th>
					    </tr>
					  </thead>
					  <tbody>
					    <tr>
					      <td>${product.pro_name }</td>
					      <!-- 상품 수량 -->
					      <td class="text-center">
					      <input class="form-control text-center me-3" id="amount" type="number" value="1" MIN="1" MAX="${product.pro_qty}" 
					      				onchange="fn_count_qty(this,${product.pro_price})" /></td>
						  <!-- 상품 가격 -->		
					      <td class="text-center totalPrice"><fmt:formatNumber value="${product.pro_price}" pattern="#,###"/> 원</td>
					    </tr>
					    <tr>
					    	<td colspan="3" class="text-end">총 상품금액 : <strong class="totalPrice"><fmt:formatNumber value="${product.pro_price}" pattern="#,###"/> 원 </strong> </td>
					    </tr>
					    
					  </tbody>
					</table>
					
					<div class="fs-5 mb-5">
						<div class="d-flex">
							<!-- 장바구니 추가 -->	
							<button class="btn btn-outline-dark flex-shrink-0" type="button" id="cart">
								<i class="bi-cart-fill me-1"></i> Add to cart
							</button>
<!-- 							<form action="CartInsert.ct" method="post"> -->
<%-- 								<input type="hidden" name="pro_code" value=${product.pro_code }> --%>
<!-- 								<input type="hidden" name="path" value="product"> -->
<%-- 								<input type="hidden" name="amount" value=${amount }> --%>
<!-- 								<i class="bi-cart-fill me-1"></i> -->
<!-- 								<input type="submit" class="btn btn-outline-dark flex-shrink-0" value="Add to Cart"> -->
<!-- 							</form> -->
							&nbsp;&nbsp;&nbsp;
							<!-- wishList -->
							<button class="btn btn-outline-dark flex-shrink-0" type="button" id="wishlist">
								<i class="bi bi-heart-fill"></i> Wishlist
							</button>
<!-- 							<form action="WishlistInsert.ct" method="post"> -->
<%-- 								<input type="hidden" name="pro_code" value=${product.pro_code }> --%>
<!-- 								<input type="hidden" name="amount" value="1"> -->
<!-- 								<i class="bi bi-heart-fill"></i>  -->
<!-- 								<input type="submit" class="btn btn-outline-dark flex-shrink-0" value="Wishlist"> -->
<!-- 							</form> -->
							<div>
<!-- 							총 합계 <input type="text" id="count" value="1"> -->
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</section>
	 <!-- 중간 배너 (링크연결) -->
	 <!-- class="container-md" --> 
	 <div class="container px-4 px-lg-5 mt-3"> 
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<div class="container navbar-text">
				<a class="navbar-brand" href="#pro_info">상품상세</a>
				<a class="navbar-brand" href="#pro_delivery">배송/교환/환불</a>
				<a class="navbar-brand" type="button" id="review_button">리뷰</a>
				<a class="navbar-brand" type="button" id="qna_button">상품문의</a>
			</div>
		</nav>
	 </div>	
	 <div id="resultArea"><%-- 리뷰,상품문의 게시판 보여주는곳 --%></div>
	<!-- 상품 상세 -->	
	<section class="py-2">
		<div class="container px-4 px-lg-5 mt-3">
			<div class="">
					<!-- Product 상세 image-->
<!-- 					<img class="card-img-top " -->
<%-- 						src="http://localhost:8080/DangDangEat/upload/${product.pro_real_img }" --%>
<!-- 						alt="..." onerror="this.src='./img/sample1_img.jpg';" /> -->
						<img class="card-img-top"
						src="<%=request.getScheme()+"://"+request.getServerName() + ":" + request.getServerPort() +"/"+request.getContextPath()%>/upload/${product.pro_real_img }"
						alt="..." onerror="this.src='./img/sample1_thumb.png';" />

				</div>
		</div>
	</section>
	<section class="py-2 bg-light" id="pro_delivery">
		<div class="container px-lg-5 mt-5">
			<h3>배송안내</h3>
			&nbsp;
			<p>
				<strong>-&nbsp;</strong><strong>브랜드 및 상품에 따라 파트너(브랜드) 배송과
					댕댕잇 자체 배송으로 나뉘어 배송됩니다.</strong>
			</p>
			<p>
				<strong>-&nbsp;</strong>상품준비중&nbsp;상태일&nbsp;경우&nbsp;출고작업&nbsp;준비중으로&nbsp;출고여부에&nbsp;따라&nbsp;환불이나&nbsp;취소가&nbsp;거절될수&nbsp;있습니다.
			</p>
			<p>
				<strong>-&nbsp;</strong>브랜드 및 상품에 따라 배송비가 다르니 각 상품의 배송정보를 확인 바랍니다.
			</p>
			<p>
				<strong>-&nbsp;</strong>도서산간 지역은 추가배송비가 발생할 수 있습니다.
			</p>
			<p>
				<strong>-&nbsp;</strong>주문하신 상품은 입금 확인 후 배송해 드립니다. 다만, 상품 종류에 따라서
				상품의 배송이 다소 지연될 수 있습니다.
			</p>
			<p>
				<strong>-&nbsp;</strong>상품의 부피/무게 또는 주문 개수 등에 따라 복수의 택배가 발송될 수 있으며
				배송완료일이 다를 수 있습니다.
			</p>
			<p>
				<strong>-&nbsp;</strong>복수의 택배로 배송되는 경우 주문내역 상 한 건의 송장번호만 확인이 가능합니다.
			</p>
			<p>
				<strong>-&nbsp;</strong>본&nbsp;상품의&nbsp;평균&nbsp;배송일은&nbsp;입금확인 후
				2~3일입니다.
			</p>
			<p>
				<strong>-&nbsp;</strong>배송예정일은&nbsp;주문시점(주문순서) 및 외부 상황에
				따른&nbsp;유동성이&nbsp;발생하므로&nbsp;평균&nbsp;배송일과는&nbsp;차이가&nbsp;발생할&nbsp;수&nbsp;있습니다.
			</p>
			<p>
				<strong>-&nbsp;</strong><strong>예약배송/프리오더&nbsp;제품을&nbsp;다른&nbsp;상품과&nbsp;함께&nbsp;구매하시는&nbsp;경우&nbsp;
					예약배송/프리오더&nbsp;제품&nbsp;출고날짜에&nbsp;합배송되어&nbsp;순차&nbsp;출고되므로&nbsp;별도배송을&nbsp;원하시면&nbsp;각각&nbsp;따로&nbsp;주문
					바랍니다.</strong>
			</p>
			<p>
				<strong><br></strong>
			</p>
			<p>
				<strong><span style="font-size: 11pt;">발송 전 취소 안내</span></strong>
			</p>
			<p>
				<strong>-&nbsp;</strong>고객센터를 통한 변경 및 취소를 요청하시는 경우 순차적으로 처리드리고 있으나,
				문의량에 따라 답변이 늦어지면 요청이 반영되지 않고 발송될 수 있으며 이는 교환 및 환불 사유가 되지 않습니다.
			</p>
			<p>
				<strong>-&nbsp;</strong>결제완료 후 배송 전 제품 변경 희망하시는 경우 취소 후 재결제 부탁드립니다.
			</p>
			<p>
				<strong>-&nbsp;</strong>주문상태가 상품준비중으로 넘어갈 경우 취소가 어렵습니다. 제품 수령 후 반품
				접수 바랍니다.
			</p>
		</div>
	</section>
	<!-- Related items section-->
	<%-- <section class="py-5 bg-light">
		<div class="container px-4 px-lg-5 mt-5">
			<h2 class="fw-bolder mb-4">Related products</h2>
			<div
				class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">

				<c:forEach var="product" items="${productDetail }">
					<div class="col mb-5">
						<div class="card h-100">
							<!-- Sale badge-->
							<!-- 							<div class="badge bg-dark text-white position-absolute" -->
							<!-- 								style="top: 0.5rem; right: 0.5rem">Sale</div> -->
							<!-- Product image-->
													<c:forEach var="product" items="${productList }">
							<a href="ProductDetail.pd?pro_code=${product.pro_code}"> <img
								class="card-img-top"
								src="http://localhost:8080/DangDangEat/upload/${product.pro_real_thumb }"
								alt="" onerror="this.src='../img/error_img.png';" />
							</a>
							<!-- Product details-->
							<div class="card-body p-4">
								<div class="text-center">
									<!-- Product name-->
									<h5 class="fw-bolder">${product.pro_name }</h5>
									<!-- Product reviews-->
									<div
										class="d-flex justify-content-center small text-warning mb-2">
										<div class="bi-star-fill"></div>
										<div class="bi-star-fill"></div>
										<div class="bi-star-fill"></div>
										<div class="bi-star-fill"></div>
										<div class="bi-star-fill"></div>
									</div>
									<!-- Product price-->
									<span>${product.pro_price }원</span>
								</div>
							</div>
							<!-- Product actions-->
							<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
								<div class="text-center">
									<a class="btn btn-outline-dark mt-auto" href="#">Add to
										cart</a>
								</div>
							</div>
						</div>
					</div>
					<div class="col mb-5">
						<div class="card h-100">
							<!-- Sale badge-->
							<div class="badge bg-dark text-white position-absolute"
								style="top: 0.5rem; right: 0.5rem">Sale</div>
							<!-- Product image-->
							<img class="card-img-top"
								src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
							<!-- Product details-->
							<div class="card-body p-4">
								<div class="text-center">
									<!-- Product name-->
									<h5 class="fw-bolder">Special Item</h5>
									<!-- Product reviews-->
									<div
										class="d-flex justify-content-center small text-warning mb-2">
										<div class="bi-star-fill"></div>
										<div class="bi-star-fill"></div>
										<div class="bi-star-fill"></div>
										<div class="bi-star-fill"></div>
										<div class="bi-star-fill"></div>
									</div>
									<!-- Product price-->
									<span class="text-muted text-decoration-line-through">$20.00</span>
									$18.00
								</div>
							</div>
							<!-- Product actions-->
							<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
								<div class="text-center">
									<a class="btn btn-outline-dark mt-auto" href="#">Add to
										cart</a>
								</div>
							</div>
						</div>
					</div>
					<div class="col mb-5">
						<div class="card h-100">
							<!-- Sale badge-->
							<div class="badge bg-dark text-white position-absolute"
								style="top: 0.5rem; right: 0.5rem">Sale</div>
							<!-- Product image-->
							<img class="card-img-top"
								src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
							<!-- Product details-->
							<div class="card-body p-4">
								<div class="text-center">
									<!-- Product name-->
									<h5 class="fw-bolder">Sale Item</h5>
									<!-- Product price-->
									<span class="text-muted text-decoration-line-through">$50.00</span>
									$25.00
								</div>
							</div>
							<!-- Product actions-->
							<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
								<div class="text-center">
									<a class="btn btn-outline-dark mt-auto" href="#">Add to
										cart</a>
								</div>
							</div>
						</div>
					</div>
					<div class="col mb-5">
						<div class="card h-100">
							<!-- Product image-->
							<img class="card-img-top"
								src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
							<!-- Product details-->
							<div class="card-body p-4">
								<div class="text-center">
									<!-- Product name-->
									<h5 class="fw-bolder">Popular Item</h5>
									<!-- Product reviews-->
									<div
										class="d-flex justify-content-center small text-warning mb-2">
										<div class="bi-star-fill"></div>
										<div class="bi-star-fill"></div>
										<div class="bi-star-fill"></div>
										<div class="bi-star-fill"></div>
										<div class="bi-star-fill"></div>
									</div>
									<!-- Product price-->
									$40.00
								</div>
							</div>
							<!-- Product actions-->
							<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
								<div class="text-center">
									<a class="btn btn-outline-dark mt-auto" href="#">Add to
										cart</a>
								</div>
							</div>
						</div>
					</div>
					<!-- for 문 끝 -->
				</c:forEach>
			</div>
		</div>
	</section> --%>
	<!-- Footer-->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; DangDangEat 2023</p>
		</div>
	</footer>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
</body>
</html>
