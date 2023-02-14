<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<!DOCTYPE html>
<html>
<head>
  <title>Payment</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <link rel="stylesheet" href="../css/orderConfirmation.css">
  <!-- 버튼을 위한 css -->
  <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
  <link href="css/styles.css" rel="stylesheet" />
  <style type="text/css">
		@font-face {
		    font-family: 'GmarketSansMedium';
		    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
		    font-weight: normal;
		    font-style: normal;
		}
		  
		* {
		    font-family:"GmarketSansMedium" ;
		}
		
		input:button {
			font-family:"GmarketSansMedium" ;
		}
		
		h6{
		 font-family:"GmarketSansMedium" ;
		}
		
  </style>
    <!-- jQuery for import--> 
  <script src="https://code.jquery.com/jquery-3.6.3.js"></script>
</head>
<body>
<jsp:include page="../inc/top.jsp"></jsp:include>
  <main>
    <div class="container mt-5 mb-5">
	<div class="row d-flex justify-content-center">
	<div class="col-md-8">
	<div class="card">
	<div class="invoice p-5">
	<div class = "dangLogo" align="center"><img src="img/order.png" width="50" > </div>
		<c:forEach var="pay" items="${paymentList }" varStatus="status">
        
			<h5 align="center"><span class="font-weight-bold d-block mt-4">${sessionScope.sId } 고객님, 주문이 완료되었습니다!</span></h5> 
			<h6 align="center">주문번호 ${pay.pay_number}</h6> 
			<h6 align="center"><span>댕댕잇을 이용해주셔서 감사합니다.</span></h6>
		</c:forEach>
	<div class="payment border-top mt-3 mb-3 border-bottom table-responsive">
	
		<table class="table table-borderless">
			<tbody>
			<tr>
			<c:forEach var="pay" items="${paymentList }" varStatus="status">
				<td><div class="py-2"> <span class="d-block text-muted">Order No</span> <span>${pay.pay_number}</span> </div></td>
			</c:forEach>
			<c:forEach var="orderInfo" items="${orderInfoList }" varStatus="status">
				<td><div class="py-2"> <span class="d-block text-muted">Order Date</span> <span>${orderInfo.order_date}</span> </div></td>
				<td><div class="py-2"> <span class="d-block text-muted">Payment</span> <span><img src="https://img.icons8.com/color/48/000000/mastercard.png" width="20" /></span> </div></td>
				<td><div class="py-2"> <span class="d-block text-muted">Shiping Address</span> <span>${orderInfo.order_address1},${orderInfo.order_address2},${orderInfo.order_postcode}</span> </div></td>
			</c:forEach>
			</tr>
			</tbody>
		</table>

	</div>
	
	<div class="product border-bottom table-responsive">
		<table class="table table-borderless">
		<c:forEach var="orderProduct" items="${orderProductList }" varStatus="status">
			<tbody>
			<tr>
				<td width="20%"><img src="http://localhost:8080/DangDangEat/upload/${orderProduct.pro_real_thumb }" alt="${orderProduct.pro_name }" 
			            	class="product-frame" width="90" onerror="this.src='./img/sample1_thumb.png';" > </td>
				<td width="60%"> <span class="font-weight-bold">상품명 : ${orderProduct.pro_name }</span>
					<div class="product-qty"> <span class="d-block">주문수량 : ${orderProduct.cart_amount }개</span></div>
				</td>
				<td width="40%">
					<div class="text-right"><span class="font-weight-bold">상품금액<br><fmt:formatNumber pattern="#,###">${orderProduct.pro_price}</fmt:formatNumber>원</span></div>
				</td>
			</tr>
			</tbody>
		</c:forEach>
		</table>
	</div>
	
	<div class="row d-flex justify-content-end">
	<div class="col-md-5">
		<table class="table table-borderless">
		<c:forEach var="pay" items="${paymentList }" varStatus="status">
			<tbody class="totals">
			<tr>
				<td><div class="text-left"><span class="text-muted">주문금액</span></div></td>
				<td><div class="text-right"><span class="price"><fmt:formatNumber pattern="#,###">${pay.pay_amount - 3500}</fmt:formatNumber>원</span></div></td>
			</tr>
			<tr>
				<td><div class="text-left"> <span class="text-muted">배송비</span> </div></td>
				<td><div class="text-right"> <span class="price">3,500원</span> </div></td>
			</tr>
			<tr>
				<td><div class="text-left"> <span class="text-muted">할인금액</span> </div></td>
				<td><div class="text-right"><span class="text-success"><fmt:formatNumber pattern="#,###">${pay.cp_discount_amount }</fmt:formatNumber>원</span></div></td>
			</tr>
			<tr class="border-top border-bottom">
				<td><div class="text-left"> <span class="font-weight-bold">총주문금액</span> </div></td>
				<td><div class="text-right"><span class="font-weight-bold"><fmt:formatNumber pattern="#,###">${pay.pay_amount - pay.cp_discount_amount}</fmt:formatNumber>원</span> </div></td>
			</tr>
			</tbody>
		</c:forEach>	
		</table>
	  </div>
	</div>
			<p align="center"><br>We will be sending shipping confirmation email <br> when the item shipped successfully!</p>
			<p class="font-weight-bold mb-0" align="center">Thanks for shopping with us!</p><br> <p align="center"><span >DangDangEat Team</span></p><br>
	<div class="container" align="center">
		<input type="button" value="장바구니로 돌아가기" onclick="location.href='CartList.ct'">&nbsp;&nbsp;
		<input type="button" value="홈으로 이동하기" onclick="location.href='./'">
	</div>
	</div>
<!-- 	<div class="d-flex justify-content-between footer p-3"> <span>Need Help? visit our <a href="#"> help center</a></span> <span>12 June, 2020</span> </div> -->
	</div>
	</div>
	</div>
	</div> 
  </main>
	<!-- Footer-->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; DangDangEat 2023</p>
		</div>
	</footer>
</body>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js?version=2"></script>

</html>
