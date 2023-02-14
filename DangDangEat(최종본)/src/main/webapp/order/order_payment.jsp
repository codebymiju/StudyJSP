<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>   
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="x-ua-compatible" content="ie=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>orderPayment</title>
  <link href="css/styles.css" rel="stylesheet" />
  <link href="css/orderForm.css" rel="stylesheet" type="text/css">
  <link href="css/orderForm2.css" rel="stylesheet" type="text/css">
  <style type="text/css">
		@font-face {
		    font-family: 'GmarketSansMedium';
		    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
		    font-weight: normal;
		    font-style: normal;
		}
		  
		* {
		    font-family:"GmarketSansMedium" ;
/* 		    vertical-align: middle; */
/* 			font-size: large; */
		}
  </style>
  <!-- iamport.payment.js -->
  <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
  <script src="https://code.jquery.com/jquery-3.6.3.js"></script>
  <script type="text/javascript">
 
  	 $(function() {
		$("#iamportPayment").click(function() {
			iamport();
		});
  		 
	 });	
  
  	 function iamport() {
		  
	        IMP.init('imp03617521'); // 댕댕잇 (가맹점코드)
	        IMP.request_pay({
	            pg : 'kakaopay', // 거래방법
	            pay_method : 'card',
	            merchant_uid : 'merchant_' + new Date().getTime(), // 주문번호
	            name : '${sessionScope.sId}' + "님의 주문" , // 화면에 표시되는 거래명
	            amount : parseInt($('#hidden_total').val() - $('#cp_discount_amount').val()), // 총 결제금액
	            buyer_name : '${sessionScope.sId}' // 거래명
	        }, function(rsp) {
	           console.log(rsp);
	            if ( rsp.success ) {
	            	
					 $.ajax({
		                  type: "GET", 
		                  url: "OrderPaymentPro.od", 
		                  data: {
				                   pro_code : $('#pro_code').val(),
				           		   cart_code : $('#cart_code').val(),
				           		   pro_amount : $('#pro_amount').val(),
				           		   order_code : $('#order_code').val(),
				           		   cp_code : $('#cp_code').val(),
				           		   cp_discount_amount : $('#cp_discount_amount').val()
		                		 },
					 	 }); // ajax
					 	 
			               var msg = '결제가 완료되었습니다.';
// 			                msg += 'DangDangEat: ' + rsp.imp_uid;
			                msg += '결제 금액 : ' + rsp.paid_amount;
// 			                msg += '상점 거래ID : ' + rsp.merchant_uid;

					 	 $('#orderPaymentForm').submit(); // 폼 제출하여 결제 & 주문서 생성 작업 진행
					  
	            } else {
	                var msg = '결제에 실패하였습니다.';
	                 msg += '에러내용 : ' + rsp.error_msg;
	                 window.history.back();
	            }
	            alert(msg);
	            
	        }); // fun()
    
  		} // iamport()
	        
	function getCouponCode(couponCode) {
    	
		$("#promo-code").val(couponCode);
		
		$.ajax({
				type: "get",
				url: "OrderCouponPro.od",
				dataType : "text",
				async:false,
				data: {
					cp_code : couponCode, 
					pro_amount : $('#pro_amount').val(),
					cart_code : $('#cart_code').val(),
					order_code : $('#order_code').val()
					},
				success: function (data) {
					alert("할인가격이 넘어왔습니다 : " + data);
					
					 $('#basket-promo').text(parseInt(data)).css("color","red"); // action을 통해 계산 완료 후 전달받은 할인금액 입력
					 $('#basket-total').text(parseInt($('#hidden_total').val()) - parseInt(data)); // 전체 금액 - 완료받은 할인금액 
					 
// 					 $('#iamportPayment').text(parseInt($('#hidden_total').val()) - parseInt(data));
					 
					 $('#cp_discount_amount').val(parseInt(data)); // 할인 금액 전달
					 $('#cp_code').val(couponCode); // 사용한 쿠폰 코드 전달
					 
	               	} // success
			
			}); // ajax
		
	}// getCouponCode

	</script>
</head>

<body>
 <jsp:include page="../inc/top.jsp"></jsp:include>
  <main>
  <div id="resultArea"></div>
  	<form action="OrderPaymentPro.od" method="post" id="orderPaymentForm" >
  	<c:forEach var="cart" items="${orderProductList }" varStatus="status">
  		<input type="hidden" name="cart_code" id="cart_code" value="${cart.cart_code }">
  		<input type="hidden" name="pro_code"  value="${cart.pro_code }">
  		<input type="hidden" name="pro_amount" id ="pro_amount" value="${cart.pro_price * cart.cart_amount + 3500 }">
  	</c:forEach>
  		<!-- 쿠폰을 사용하지 않을 경우 오류 발생 / 쿠폰 테이블에 쿠폰코드 null 기본값 0인 쿠폰 추가해야 함(임시방편) -->
  		<input type="hidden" name="cp_code"  id="cp_code" value="Test">
  		<input type="hidden" name="cp_discount_amount" id="cp_discount_amount" value="${0 }">
  		<br><h1 align="center">주문서 확인 & 결제 페이지</h1><hr>
	    <div class="basket">
  		<h1>주문상품 확인</h1>
  		<p>Please check your shopping details.</p>
	      <div class="basket-labels">
	        <ul>
	          <li class="item item-heading">Item</li>
	          <li class="price">Price</li>
	          <li class="quantity">Quantity</li>
	          <li class="subtotal">Subtotal</li>
	        </ul>
	      </div>
	   
	      <div class="basket-product">
	         <c:forEach var="cart" items="${orderProductList }" varStatus="status">
		        <div class="item">
			          <div class="product-image">
			          		<a href="ProductDetail.pd?pro_code=${cart.pro_code}">
			            	<img src="http://localhost:8080/DangDangEat/upload/${cart.pro_real_thumb }" alt="${cart.pro_name }" 
			            	class="product-frame" height="130" width="140" onerror="this.src='./img/sample1_thumb.png';"  >
			            	</a>
			          </div>
			          <div class="product-details">
			            <h1><strong><span class="item-quantity" >상품명 : ${cart.pro_name }</span></strong></h1>
<%-- 			            <p><strong>브랜드명 : ${cart.pro_brand }</strong></p> --%>
			            <p>Product Code : ${cart.pro_code }</p>
			          </div>
		        </div>
		        <div class="price" ><fmt:formatNumber pattern="#,###">${cart.pro_price }</fmt:formatNumber></div>
		        <div class="quantity">
		          <input type="number" value="${cart.cart_amount }" id="quantitiy" class="quantity-field">
		        </div>
		        <div class="subtotal"><fmt:formatNumber pattern="#,###">${cart.pro_price * cart.cart_amount }</fmt:formatNumber></div>
	        </c:forEach>
	      </div>
	      
	   <!-- 주문자 정보 -->
	  <hr />    
	  <h1>배송 정보 확인</h1>
	  <p>Please check your shipping details.</p>
	  <hr />
		  <div class="form">
		    <c:forEach var="order" items="${orderMemberList }" varStatus="status">
		    	<input type="hidden" name="order_code" id="order_code" value="${order.order_code }">
			  <div class="fields fields--2">
			    <label class="field">
			      <span class="field__label" >이름</span>
			      <input class="field__input" type="text" id="order_name" name="order_name" value="${order.order_name }" readonly="readonly">
			    </label>
			  </div>
			      
			  <div class="fields fields--2">
			  	<label class="field">
			      <span class="field__label">연락처</span>
			      <input class="field__input" type="text" id="order_mobile" name="order_mobile" value="${order.order_mobile }" readonly="readonly">
			    </label>
			   </div>
			   
	     	  <div class="fields fields--2">
			    <label class="field">
			      <span class="field__label">우편번호</span>
			      <input class="field__input" type="text" id="order_zipcode" value="${order.order_postcode }" readonly="readonly"/>
			    </label>
			  </div>
			   
			  <label class="field">
			    <span class="field__label" >Address1</span>
			    <input class="field__input" type="text" id="order_address1" value="${order.order_address1 }" readonly="readonly"/>
			  </label>
			  <label class="field">
			    <span class="field__label" >Address2(상세주소)</span>
			    <input class="field__input" type="text" id="order_address2" value="${order.order_address2 }" readonly="readonly"/>
			  </label>
			  <label class="field">
			    <span class="field__label" >배송 메세지</span>
			    <input class="field__input" type="text" id="order_message" value="${order.order_comment }" readonly="readonly"/>
			  </label>
			  
	  	</c:forEach>
	  </div>
	  <!-- 주문자 정보 -->
	  
		 
 	
		<!-- 할인 정보 -->
	  <hr />    
	  <h1>할인 정보</h1>
	       <div class="basket-module"> 
		        <label for="promo-code">Enter a promotional code</label>
		        <input id="promo-code" type="text"  maxlength="5" class="promo-code-field">
	         <c:forEach var="price" items="${orderProductList }" varStatus="status">
		        <button class="promo-code-cta" type="button" onclick = "window.open('SelectCoupon.od?total=${price.pro_price * price.cart_amount }', '_blank', 'height=700, width=450, top=70, left=400')">Coupon</button>
	         </c:forEach>
	      </div>
	  	 <hr>
	</div>
		      <div class="basket">
			        <div class="summary-total-items"><span class="total-items"></span> Items in your Bag</div>
			        <div class="summary-subtotal">
				          <div class="subtotal-title">상품금액</div>
				<c:forEach var="price" items="${orderProductList }" varStatus="status">
				          <div class="subtotal-value final-value" id="basket-subtotal"><fmt:formatNumber pattern="#,###">${price.pro_price * price.cart_amount }</fmt:formatNumber></div>
			    </c:forEach> 
						  <div class="subtotal-title">할인금액</div>
				          <div class="subtotal-value final-value" id="basket-promo">0</div>
				          <div class="subtotal-title">배송비</div>
				          <div class="subtotal-value final-value" id="basket-delivery">3,500</div>
			        </div>
		     <c:forEach var="price" items="${orderProductList }" varStatus="status">
			      <div class="summary-total">
			          <div class="total-title">Total</div>
			          <div class="total-value final-value" id="basket-total"><fmt:formatNumber pattern="#,###">${price.pro_price * price.cart_amount + 3500}</fmt:formatNumber></div>
			          <input type="hidden" id="hidden_total" value="${price.pro_price * price.cart_amount + 3500}" >
<!-- 			          <input type="hidden" id="iamportPayment" > -->
			      </div>
	      	</c:forEach>
		      	  <div class="summary-checkout">
		             <button class="button" id="checkout" type="submit" >Checkout</button><hr>
		             <button class="button" id="iamportPayment" type="button" >iamport</button><hr>
		     	  </div>
		      </div><!-- basket div 태그 -->
	   </form>   
  </main>
  	<!-- Footer-->
	<!-- Footer-->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; DangDangEat 2023</p>
		</div>
	</footer>
</body>

</html>

 
 
