<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>Shop Homepage - Start Bootstrap Template</title>

<link href="css/styles.css" rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<script src="../js/jquery-3.6.3.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		$('.slider_image').bxSlider({
			mode : "horizontal",
			auto : true,
			pager : true,
			//        slideWidth: 1300,  // 절대 수정 금지!!!건들지마세요!!!
			speed : 15, // 숫자 작을수록 빠르게 넘어감
			infinitelLoop : true
		});
	});
	
	$(document).ready(function() {
		
		$imgPath = $("#imgPath").val();
		
		
		$.ajax({
			type:"get",
			url:"Main",
			dataType : 'json', 
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success: function(result) {
// 						alert(JSON.stringify(result));
// 						alert($imgPath);
						
						for(let product of result){

							let decodingImg = decodeURI(product.pro_real_thumb);
							
							$("#new_eat").append(
							 "<img class='bg-light main_rec m-3' onclick=\"location.href='ProductDetail.pd?pro_code=" + product.pro_code + "'\" src='" + $imgPath +  "/upload/" + decodingImg  + "'></img>"
							);
						}
						
			},//success:
			fail: function(result) {
			alert("이미지 로드 실패");
		
			}//fail:
	
		});
	
});

function couponDown() {
let ok = prompt('할인 쿠폰 발급 완료!\n (확인을 누르면 쿠폰등록창으로 넘어갑니다. ) \n↓↓↓할인코드↓↓↓',"2023년도_잘_부탁한댕")
if(ok){
	location.href="MyPage.me?cp_code="+ ok ;
	
	}
} 
</script>
<style type="text/css">
@font-face {
	font-family: 'GmarketSansMedium';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

body {
	font-family: "GmarketSansMedium";
}

#slider {
	margin: 0 auto;
	width: 1200px;
	padding: 0;
	position: relative;
}


.main_rec{
	/*main 네모*/
width: 280px;
height:  280px;
}

#downloadCoupon {
position: absolute;
width: 60%;
align-items: center;
height: 50px;
bottom: 10%;
left: 15%;
/* background-color: red; */
/* background: red; */
cursor: pointer;

}

.custom-shape-divider-bottom-1673107194 {
    position: absolute;
    bottom: 0;
    left: 0;
    width: 100%;
    overflow: hidden;
    line-height: 0;
    transform: rotate(180deg);
}

.custom-shape-divider-bottom-1673107194 svg {
    position: relative;
    display: block;
    width: calc(103% + 1.3px);
    height: 232px;
    transform: rotateY(180deg);
}

.custom-shape-divider-bottom-1673107194 .shape-fill {
    fill: #D7E9B9;
}
</style>
</head>
<body>
	<!-- top.jsp -->
	<jsp:include page="../inc/top.jsp"></jsp:include>
	<!--         </nav> -->
	<!-- Header-->

	<section class="py-1"></section>

	<div id="slider">
		<ul class="slider_image">
			<li><img src="img/slider1.png" /></li>
			<li><img src="img/cheerup.jpg" /></li>
			<li><img src="img/cheerup.jpg" /></li>
		</ul>
	</div>

<article>

<input type="hidden" id="imgPath" value="<%=request.getScheme()+"://"+request.getServerName() + ":" + request.getServerPort() +"/"+request.getContextPath() %>">

	<%
	System.out.println(request.getContextPath());
	System.out.println(request.getScheme()+"://"+request.getServerName() + ":" + request.getServerPort());
	System.out.println("request.getSession().getServletContext().getRealPath(/)"+  request.getSession().getServletContext().getRealPath("upload"));
	%>
	     <!-- 메인 네모 부분 // 관련 클래스: main_rec-->
        <div class="mt-5 d-flex flex-row justify-content-center" >
         </div>
	        <div class=" mb-5 d-flex flex-row justify-content-center" >
	        		<div  width="500" style="position:relative;">
				       <img class="m-3" src="img/salebanner.png" width="500"></img>
				       <div id="downloadCoupon" onclick="couponDown()"></div> 
	       			</div>
			       <img class="m-3"  src="img/orderbanner.png" width="500"></img>
	        </div>
	
         <!-- 메인 네모 부분 // 관련 클래스: main_rec-->
        <div class="mt-5 d-flex flex-row justify-content-center">
	         <h3> NEW EAT </h3>
         </div>
	        <div style="position:relative;" class=" mb-5 d-flex flex-row justify-content-center" id="new_eat">
<%-- 		         <c:forEachCitems="${productList}" var="product" > --%>
<%-- 		         ${product.pro_real_thumb } --%>
<%-- 			       <img class=" bg-light main_rec m-3" src="<%=request.getSession().getServletContext().getRealPath(uploadPath) %>/${product.pro_real_thumb }"></img> --%>
<%-- 		         </c:forEach> --%>

		</div>
</article>
 
</body>
<!-- Footer-->
<footer class="py-5" style="position:relative;">
	<div class="container">
<div class="custom-shape-divider-bottom-1673107194">

    <svg data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1200 120" preserveAspectRatio="none">
        <path d="M321.39,56.44c58-10.79,114.16-30.13,172-41.86,82.39-16.72,168.19-17.73,250.45-.39C823.78,31,906.67,72,985.66,92.83c70.05,18.48,146.53,26.09,214.34,3V0H0V27.35A600.21,600.21,0,0,0,321.39,56.44Z" class="shape-fill"></path>
    </svg>
</div>
		<p class="m-0 text-center text-white">Copyright &copy; DANGDANGEAT
			2022</p>
	</div>
</footer>
<!-- Bootstrap core JS-->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="js/scripts.js"></script>
</body>
</html>
