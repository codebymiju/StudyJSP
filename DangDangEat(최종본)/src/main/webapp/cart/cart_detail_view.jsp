<%@page import="vo.cart_wish_proBean"%>
<%@page import="vo.CartBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%
request.setCharacterEncoding("UTF-8");
List<cart_wish_proBean> cartlist = (List<cart_wish_proBean>)request.getAttribute("cartList");
int finalTotal = 0;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<!-- 외부 CSS 가져오기 -->
<!-- <link href="css/bootstrap.css" rel="stylesheet" type="text/css"> -->
<link href="css/styles.css" rel="stylesheet" /> 
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
	#total {
		text-align: right;
	}
	
	table {
	margin: auto;
	}
	
	#id, #empty {
		text-align: center;
	}
	
	th {
		text-align: center;
	}
	
	input, input[type=number] {
	    font-family:"GmarketSansMedium" ;
	    border-radius: 0px;
	}
	
	#lastbutton {
		margin-bottom: 30px;
	}
	
	input[type=text] {
	    font-family:"GmarketSansMedium" ;
	    border: none;
	    text-align: center;
/* 	    width: 70px; */
	}
</style>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">
function amountChange(){
	var value = Number(document.getElementById('amount').value);
	var price = Number(document.getElementById('price').value;
	var totalPrice = value * price;
	
	alert(totalPrice);
	alert(value);
	alert(price);
}

$(function() {
	    // 주문
	    $("#order").on("click", function() {
	        $("#result").val();
// 	       alert($("#result").val());
	    });
	    
// 	    $("#amount").on("click", function(){
// 	    	alert($("#amount").val());
// // 	    	console.log()
// 	    })
       
    // 전체선택 체크박스의 상태가 변하면 이벤트 처리
	$("#allCheck").on("change", function() {
		// 전체선택 체크박스 체크 시 모든 체크박스 체크, 체크해제 시 모두 해제
		if($("#allCheck").is(":checked")) { // 전체선택 체크박스 상태가 checked 인지 판별
			// 대상의 자바스크립트 속성 변경 시 prop() 함수 활용
// 				$(":checkbox").prop("checked", true);
				
			// each() 함수를 사용하여 체크박스 반복도 가능함
			$(":checkbox").each(function(index, item) {
				$(item).prop("checked", true);
			});
			
		} else {
			$(":checkbox").prop("checked", false);
		}

	});

});

// 챗봇 api
(function() {
    var w = window;
    if (w.ChannelIO) {
      return (window.console.error || window.console.log || function(){})('ChannelIO script included twice.');
    }
    var ch = function() {
      ch.c(arguments);
    };
    ch.q = [];
    ch.c = function(args) {
      ch.q.push(args);
    };
    w.ChannelIO = ch;
    function l() {
      if (w.ChannelIOInitialized) {
        return;
      }
      w.ChannelIOInitialized = true;
      var s = document.createElement('script');
      s.type = 'text/javascript';
      s.async = true;
      s.src = 'https://cdn.channel.io/plugin/ch-plugin-web.js';
      s.charset = 'UTF-8';
      var x = document.getElementsByTagName('script')[0];
      x.parentNode.insertBefore(s, x);
    }
    if (document.readyState === 'complete') {
      l();
    } else if (window.attachEvent) {
      window.attachEvent('onload', l);
    } else {
      window.addEventListener('DOMContentLoaded', l, false);
      window.addEventListener('load', l, false);
    }
  })();
//   ChannelIO('boot', {
//     pluginKey: "1340eb3c-ddf6-43c5-a497-6a91281156bc", //please fill with your plugin key
//     memberId: "YOUR_USER_ID", //fill with user id
//     profile: {
//       "name": "YOUR_USER_NAME", //fill with user name
//       "mobileNumber": "YOUR_USER_MOBILE_NUMBER", //fill with user phone number
//       "CUSTOM_VALUE_1": "VALUE_1", //any other custom meta data
//       "CUSTOM_VALUE_2": "VALUE_2"
//     }
//   });
  ChannelIO('boot', {
     pluginKey: '1340eb3c-ddf6-43c5-a497-6a91281156bc'
      }, function onBoot(error, user) {
           if (error) {
                console.error(error);
           } else {
             console.log('boot success', user)
           }
   });
// 챗봇 api 끝
</script>
</head>
<body>
	<!-- Login, Join 링크 표시 영역 -->
	<jsp:include page="/inc/top.jsp"></jsp:include>
	<!-- 장바구니 번호, 사진(대표이미지), 상품명, 사이즈,색상,수량,가격, 삭제 -->
	<div id="id">${sessionScope.sId }님의 장바구니</div>
	<div class="container">
	 <table class="table">
		<tr>
		   <th><input type="checkbox" id="allCheck" name="allCheck"></th>
		   <th>이미지</th>
		   <th>상품명</th>
		   <th>가격</th>
		   <th>수량</th>
		   <th>총금액</th>
		   <th>선택</th>
		</tr>
		<c:choose>
			<c:when test="${empty cartList }">
				<tr>
					<td colspan="7">
						<div id="empty">장바구니가 비어있습니다.</div>
					</td>
				</tr>
			</c:when>
		<c:otherwise>
			<c:forEach var="cart" items="${cartList }">
				<input type="hidden" name="cart_code" value=${cart.cart_code }>
				<tr>
					<td>
						<input type="checkbox" id="selection" name="selection">
					</td>
					<td>
					<div style="text-align: center;">
                      <img class="card-img-top" style="width: 100px; height: 100px;"
		                src="<%=request.getScheme()+"://"+request.getServerName() + ":" + request.getServerPort() +"/"+request.getContextPath()%>/upload/${cart.pro_real_thumb }"
        		        alt="..." onerror="this.src='./img/sample1_thumb.png';" />
        		        </div>
					</td>
					<td><a href="ProductDetail.pd?pro_code=${cart.pro_code }">${cart.pro_name }</a></td>
					<td>
						<div style="text-align: center;">
						<input type="text" id="price" value="<fmt:formatNumber value="${cart.pro_price }" pattern="###,###,###"/>">
						</div>
<%-- 					<fmt:formatNumber value="${cart.pro_price }" pattern="###,###,###"/> --%>
					</td>
					 <!-- 상품 수량 -->
                     <td class="text-center">
	                     <form action="CartUpdate.ct" method="post">
        				 <input type="hidden" name="pro_code" value=${cart.pro_code }>
	                     <input class="form-control text-center me-3" name="amount" id="amount" type="number" value="${cart.cart_amount}" MIN="1" MAX="100" onchange="amountChange()" />
	                     <input type="submit" value="변경">
	                     </form>
                     </td>
<!-- 					<td> -->
<!-- 						<input type="button" class="minus" value="-" onclick="count('minus')"> -->
<%-- 						<div id='result'>${cart.cart_amount }</div> --%>
<!-- 						<input type="submit"> -->
<%-- 						<input type="text" id="result" value="${cart.cart_amount }"> --%>
<!-- 						<input type="button" class="plus" value="+" onclick="count('plus')"> -->
<!-- 					</td> -->
					<td>
						<div style="text-align: center;">
						<input type="text" value="<fmt:formatNumber value="${cart.pro_price * cart.cart_amount }" pattern="###,###,###"/>">
<%-- 					<c:set var="total" value="${total+(cart.pro_price*cart.cart_amount) }"/> --%>
<%-- 					<c:out value="${total }"/> --%>
						</div>
					</td>
					<td>
						<input type="button" value="주문하기" id="order" onclick="location.href='OrderForm.od?pro_code=${cart.pro_code}&cart_code=${cart.cart_code}'">
						<form action="CartDelete.ct" method="post">
							<input type="hidden" name="pro_code" value=${cart.pro_code }>
							<input type="submit" value="삭제">
						</form>
					</td>
					<c:set var="finalTotal" value="${finalTotal+(cart.pro_price*cart.cart_amount) }" />
				</tr> 
			</c:forEach>
		</c:otherwise>
		</c:choose>
	</table>
	</div>
	<div class="container" id="total">
		Total : <fmt:formatNumber value="${finalTotal }" pattern="###,###,###"/>원
<%-- 		<c:out value="Total : ${finalTotal }원" /> --%>
	</div>
	<div class="container" id="lastbutton">
		<input type="button" value="선택상품 주문하기" onclick="location.href='orderFormPro.od'">
		<input type="button" value="선택상품 삭제하기" onclick="location.href='CartDelete.ct'">
	</div>
<!-- Footer-->
   <footer class="py-5 bg-dark">
      <div class="container">
         <p class="m-0 text-center text-white">Copyright &copy; DangDangEat 2023</p>
      </div>
   </footer>
</body>
</html>