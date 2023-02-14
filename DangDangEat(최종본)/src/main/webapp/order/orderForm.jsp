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
  <title>orderForm</title>
  <link href="css/styles.css" rel="stylesheet" />
  <link href="css/orderForm.css" rel="stylesheet" type="text/css">
  <!-- top.jsp 랑 중복되는 container  : orderForm2.css -->
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
		}
  </style>
  	<!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.3.js"></script>
    <!-- iamport.payment.js -->
     <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js"></script>
    <script type="text/javascript">
    /* Set values + misc */
    var promoCode;
    var promoPrice;
    var fadeTime = 300;
    
    $(function() {
    	
		/* 체크박스 클릭 시 주문자 정보 => 배송정보 */	
		$("#same_info").on("change", function() {
			if($("#same_info").is(":checked")) {
				$("#shipment_name").val($("#order_name").val());
				$("#shipment_mobile").val($("#order_mobile").val());
				$("#shipment_address1").val($("#order_address1").val());
				$("#shipment_address2").val($("#order_address2").val());
				$("#shipment_zipcode").val($("#order_zipcode").val());
			} else {  
				$("#shipment_name").val("");
				$("#shipment_mobile").val("");
				$("#shipment_address1").val("");
				$("#shipment_address2").val("");
				$("#shipment_zipcode").val("");
			}
		});
		
	    
    });
    </script>
</head>

<body>
 <jsp:include page="../inc/top.jsp"></jsp:include>
<!--  <section> -->
  <main>
  	<form action="OrderInsertPro.od" method="post">
  		<!-- order_product 에 들어갈 정보 -->
  		<input type="hidden" name="cart_code" value="${param.cart_code }"> 
  		<input type="hidden" name="pro_code" value="${param.pro_code }">
  		<!--  -->
	    <div class="basket">
	      <div class="basket-labels">
	        <ul>
	          <li class="item item-heading">Item</li>
	          <li class="price">Price</li>
	          <li class="quantity">Quantity</li>
	          <li class="subtotal">Subtotal</li>
	        </ul>
	      </div>
	   
	      <div class="basket-product">
	         <c:forEach var="cart" items="${cartList }" varStatus="status">
		        <div class="item">
			          <div class="product-image">
			            <a href="ProductDetail.pd?pro_code=${cart.pro_code}">
			            	<img src="http://localhost:8080/DangDangEat/upload/${cart.pro_real_thumb }" alt="${cart.pro_name }" 
			            	class="product-frame" height="130" width="140" onerror="this.src='./img/sample1_thumb.png';" >
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
	     </div> 
	   <!-- 주문자 정보 -->
	  <hr />    
	  <h1>주문자 정보</h1>
	  <p>Please check your order details.</p>
	  <hr />
		  <div class="form">
		    <c:forEach var="member" items="${memberList }" varStatus="status">
			  <div class="fields fields--2">
			    <label class="field">
			      <span class="field__label" >이름</span>
			      <input class="field__input" type="text" id="order_name" name="order_name" value="${member.member_name }">
			    </label>
			  </div>
			      
			  <div class="fields fields--2">
			  	<label class="field">
			      <span class="field__label">연락처</span>
			      <input class="field__input" type="text" id="order_mobile" name="order_mobile" value="${member.member_mobile }">
			    </label>
			   </div>
			   
	     	  <div class="fields fields--2">
			    <label class="field">
			      <span class="field__label">우편번호</span>
			      <input class="field__input" type="text" id="order_zipcode" value="${member.member_postcode }"/>
			    </label>
			  </div>
			   
			  <label class="field">
			    <span class="field__label" >Address1</span>
			    <input class="field__input" type="text" id="order_address1" value="${member.member_addr1 }"/>
			  </label>
			  <label class="field">
			    <span class="field__label" >Address2(상세주소)</span>
			    <input class="field__input" type="text" id="order_address2" value="${member.member_addr2 }"/>
			  </label>
			  
			
			  
	  	</c:forEach>
	  </div>
	  <!-- 주문자 정보 -->
	  
		  <!-- 배송 정보 -->
		  <hr />    
		  <h1>배송 정보</h1>
		  <p>Please enter your shipping details.</p>
		  <hr />
		  <div>
			  <label class="same_info">
			  <input type="checkbox" id="same_info" />주문자 정보와 동일합니까?</label>
		  </div>
		  <hr />
  	
		  <div class="form">
			  <div class="fields fields--2">
			    <label class="field">
			      <span class="field__label" >이름</span>
			      <input class="field__input" type="text" id="shipment_name" name="shipment_name" placeholder="김댕댕" required="required">
			    </label>
			  </div>
			      
			  <div class="fields fields--2">
			  	<label class="field">
			      <span class="field__label">연락처</span>
			      <input class="field__input" type="text" id="shipment_mobile" name="shipment_mobile" placeholder="010-1234-5678" required="required">
			    </label>
			  </div>
		      <div class="fields fields--2">
				    <label class="field">
				      <span class="field__label" >우편번호</span>
				      <input class="field__input" type="text" id="shipment_zipcode" name="shipment_zipcode" required="required">
			    	</label>
			       <input type="button" value="주소 검색" onclick="kakaoAddr()">
		      </div>
			   
			  <label class="field">
				    <span class="field__label" >Address1</span>
				    <input class="field__input" type="text" id="shipment_address1" name="shipment_address1"  required="required">
			  </label>
			  
			  <label class="field">
				    <span class="field__label" >Address2(상세주소)</span>
				    <input class="field__input" type="text" id="shipment_address2" name ="shipment_address2" required="required">
			  </label>
			  
		  	  <div class="fields fields--2">
			     <label class="field">
			       	  <span class="field__label" >배송 메시지 선택</span>
				      <select id="message" name="message">
				      	<option>선택하세요</option>
				      	<option value="배송 전 미리 연락 바랍니다.">배송 전 미리 연락바랍니다.</option>
				      	<option value="부재 시 경비실에 맡겨주세요.">부재 시 경비실에 맡겨주세요.</option>
				      	<option value="부재 시 문 앞에 놓아주세요.">부재 시 문 앞에 놓아주세요.</option>
				      	<option value="빠른 배송 부탁드립니다.">빠른 배송 부탁드립니다.</option>
				      	<option value="택배함에 놓아주세요">택배함에 놓아주세요</option>
				      </select>
			     </label>
			  </div>
		 </div>
		 <br><br>
	      <div class="summary-checkout">
	          <button class="button" type="submit" id="checkout" >Checkout</button><hr>
	      </div>
	   </form>   
  </main>
<!--   </section> -->
  	<!-- Footer-->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; DangDangEat 2023</p>
		</div>
	</footer>
</body>
<%-- 카카오 주소 API 적용하기 --%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
	function kakaoAddr() { // onclick 시 작동할 함수 선언
		new daum.Postcode({
			oncomplete: function(data) {
				var roadAddr = data.roadAddress;
				// 회원이 검색해서 찾은 주소 클릭 시 폼에 뿌리기
				document.getElementById("shipment_zipcode").value = data.zonecode;
				document.getElementById("shipment_address1").value = data.roadAddress;
			}
		}).open();
	}
</script>
</html>
