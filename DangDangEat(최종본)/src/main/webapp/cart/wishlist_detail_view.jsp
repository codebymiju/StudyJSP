<%@page import="vo.CartBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%
request.setCharacterEncoding("UTF-8");
// List wishlist = (List)request.getAttribute("wishlist");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>wishlist</title>
<!-- 외부 CSS 가져오기 -->
<!-- <link href="css/bootstrap.css" rel="stylesheet" type="text/css"> -->
<link href="css/styles.css" rel="stylesheet" /> 
<style>
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
	
	#id {
		text-align: center;
	}
	
	th {
		text-align: center;
	}

	input{
	    font-family:"GmarketSansMedium" ;
	    border-radius: 0px;
	}
	
</style>
</head>
<body>
	<!-- Login, Join 링크 표시 영역 -->
	<jsp:include page="/inc/top.jsp"></jsp:include>
	<!-- 장바구니 번호, 사진(대표이미지), 상품명, 사이즈,색상,수량,가격, 삭제 -->
	<div class="container">
	<div id="id">${sessionScope.sId }님의 위시리스트</div>
	 <table class="table">
		<tr>
		   <th></th>
		   <th>이미지</th>
		   <th>상품명</th>
		   <th>가격</th>
		   <th>선택</th>
		</tr>
		<c:forEach var="wishlist" items="${wishlist }">
			<input type="hidden" name="cart_code" value=${wishlist.cart_code }>
			<tr>
				<td>
					<input type="checkbox">
				</td>
				<td>
					<div style="text-align: center;">
                      <img class="card-img-top" style="width: 100px; height: 100px;"
		                src="<%=request.getScheme()+"://"+request.getServerName() + ":" + request.getServerPort() +"/"+request.getContextPath()%>/upload/${wishlist.pro_real_thumb }"
        		        alt="..." onerror="this.src='./img/sample1_thumb.png';" />
        		        </div>
				</td>
				<td><a href="ProductDetail.pd?pro_code=${wishlist.pro_code }">${wishlist.pro_name }</a></td>
				<td><fmt:formatNumber value="${wishlist.pro_price }" pattern="###,###,###"/></td>
				<td>
				<form action="CartInsert.ct" method="post">
					<input type="hidden" name="path" value="wishlist">
					<input type="hidden" name="pro_code" value=${wishlist.pro_code }>
					<input type="hidden" name="amount" value="1">
					<input type="submit" value="장바구니">
				</form>
				<form action="WishlistDelete.ct" method="post">
					<input type="hidden" name="path" value="wishlist">
					<input type="hidden" name="pro_code" value=${wishlist.pro_code }>
					<input type="submit" value="삭제">
				</form>
				</td>
			</tr> 
		</c:forEach>
	</table>
	</div>
	
<!-- 	<form action="WishlistDelete.ct" method="post"> -->
<%-- 		<input type="hidden" name="pro_code" value=${wishlist.pro_code }> --%>
<!-- 		<input type="submit" value="삭제하기"> -->
<!-- 	</form> -->
<!-- 	<input type="button" value="위시리스트 비우기" onclick="location.href='WishlistDelete.ct'"> -->
<!-- Footer-->
   <footer class="py-5 bg-dark margin">
      <div class="container">
         <p class="m-0 text-center text-white">Copyright &copy; DangDangEat 2023</p>
      </div>
   </footer>
</body>
</html>