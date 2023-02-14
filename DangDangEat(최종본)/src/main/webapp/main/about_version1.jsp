<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no, user-scalable=no">
<link href="css/styles.css" rel="stylesheet" />
<link rel="stylesheet" 	href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script	src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<script src="../js/jquery-3.6.3.js"></script>
<title>DangDangEat-about</title>
<script src="../js/jquery-3.6.3.js"></script>

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


</style>
</head>
<body>

	<!-- top.jsp -->
	<jsp:include page="../inc/top.jsp" />

	<!-- lookbook -->
	<section class="hero bg-purple"
		style="background-color: #cce4a8; padding: 65px 0px;"> 
		<div class="container-fluid-px py-6 container-fluid">
			<div class="row justify-content-center" style="margin-bottom: 20px;">
				<div class="col-lg-8">
					<h1 class="mb-2" style="text-align: center;font-style: oblique;">
					<img src="img/dog_logo.png" width="50px" height="50px" style="margin-bottom: 20px;">&nbsp;모든 동물들의 더 나은 내일을 만듭니다</h1>
					<br>
					<h3 style="font-weight: 900;color: #4c8e5f; text-align: center;">#For Better Life</h3><br>
					<p class="lead" style="text-align: center; ">	올바른 반려 문화를 만들기 위해, 더 의식 있는 <b style="font-weight: bold;">'브랜드'</b>와 <b style="font-weight: bold;">'서비스'</b>를 제공합니다 </p>
					<br>
					<p class="lead" style="text-align: center; ">
						<span style="font-size: 25px;font-weight: bold;">"댕댕잇"</span>은 마음이 놓이는 믿을 수 있는 제품만,<br> 여러분이 더욱 똑똑한 선택을 할 수 있도록 돕습니다.
					</p>
					<br>
					<p class="lead" style="text-align: center; ">건강한 반려생활을 위한 더 나은 방법을 고민하고,<br>
						빠르고 쉬운 길보다는 옳은 길을 향해 가고자 합니다 </p>
					<br>
				</div>
			</div>
<!-- 			<div class="row gutter-1 masonry gallery collage mb-10"> -->
<!-- 				<div class="col-6"> -->
<!-- 					<figure class="equal equal-75"> -->
<!-- 						<a class="image image-scale" href="img/web-2-1-1.jpg" -->
<!-- 							style="background-image: url(img/web-2-1-1.jpg);"> </a> -->
<!-- 					</figure> -->
<!-- 				</div> -->
<!-- 				<div class="col-6"> -->
<!-- 					<figure class="equal equal-125"> -->
<!-- 						<a class="image image-scale" href="resources/img/about_5.jpg" -->
<!-- 							style="background-image: url(resources/img/about_5.jpg);"> </a> -->
<!-- 					</figure> -->
<!-- 				</div> -->
<!-- 				<div class="col-6"> -->
<!-- 					<figure class="equal equal-50"> -->
<!-- 						<a class="image image-scale" href="resources/img/about_3.jpg" -->
<!-- 							style="background-image: url(resources/img/about_3.jpg);"> </a> -->
<!-- 					</figure> -->
<!-- 				</div> -->
<!-- 			</div> -->

			
			<div class="row gutter-4">
				<div class="col-md-4">
					<div class="text-center px-2">
						<i class="icon-Guard fs-40 mb-2"></i>
						<h4 class="eyebrow d-block">반려동물 제품도 브랜드를 말하다</h4>
						<p>국내 대표 브랜드 커머스 플랫폼</p>
						<img src="img/circle_1.png" width="150px" height="150px" style="border-radius: 100%;margin-bottom: 20px;">
					</div>
				</div>
				<div class="col-md-4">
					<div class="text-center px-2">
						<i class="icon-Truck fs-40 mb-2"></i>
						<h4 class="eyebrow d-block">편리한 댕댕잇 자체 배송</h4>
						<p>더욱더 안전한 배송 시스템</p>
						<img src="img/circle_2.png" width="150px" height="150px" style="border-radius: 100%;margin-bottom: 20px;">
					</div>
				</div>
				<div class="col-md-4">
					<div class="text-center px-2">
						<i class="icon-Money---Alt fs-40 mb-2"></i>
						<h4 class="eyebrow d-block">지구와 환경을 사랑하는 마음, 가득담아</h4>
						<p>지구와 환경에 이로운 방법을 고민합니다</p>
						<img src="img/circle_3.jpeg" width="150px" height="150px" style="border-radius: 100%;margin-bottom: 20px;">
						
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- text -->
	<section style="padding-top: 100px;padding-bottom: 50px;background-color: #f4f4f4;">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-8">
					<p class="lead" style="text-align: center; font-size: 30px;">
						Hello, We Are 댕댕잇 Developers!</p>
				</div>
			</div>
		</div>
	</section>


	<!-- numbers -->
	<section style="padding: 20px;padding-bottom: 60px;background-color: #f4f4f4;">
		<div class="container">
			<div class="row gutter-2 gutter-md-4" style="text-align: center;">
				<div class="col-md-6 col-lg-12">
					<!-- <span class="h1 font-weight-bold">50</span> -->
					<span><img src="img/miju_dog.png" width="120px"
						height="120px" style="margin-bottom: 20px;"></span>
					<p>
						김미주/팀장<br>김미주@gmail.com
					</p>
				</div>
				<div class="col-md-6 col-lg-4">
					<span><img src="img/sangwoo_dog.png" width="120px"
						height="120px" style="margin-bottom: 20px;"></span>
					<p>
						오상우/팀원<br>오상우@gmail.com
					</p>
				</div>
				<div class="col-md-6 col-lg-4">
					<span><img src="img/jooyoung_dog.png" width="120px"
						height="120px" style="margin-bottom: 20px;"></span>
					<p>
						윤주영/팀원<br>윤주영@gmail.com
					</p>
				</div>
				<div class="col-md-6 col-lg-4">
					<span><img src="img/hawon_dog.png" width="120px"
						height="120px" style="margin-bottom: 20px;"></span>
					<p>
						이하원/팀원<br>이하원@gmail.com
					</p>
				</div>
				<div class="col-md-6 col-lg-4">
					<span><img src="img/jakyoung_dog.png" width="120px"
						height="120px" style="margin-bottom: 20px;"></span>
					<p>
						김자경/팀원<br>김자경@gmail.com
					</p>
				</div>
				<div class="col-md-6 col-lg-4">
					<span><img src="img/sewon_dog.png" width="120px"
						height="120px" style="margin-bottom: 20px;"></span>
					<p>
						전세원/팀원<br>전세원@gmail.com
					</p>
				</div>
				<div class="col-md-6 col-lg-4">
					<span><img src="img/yeram_dog.png" width="120px"
						height="120px" style="margin-bottom: 20px;"></span>
					<p>
						문예람/팀원<br>문예람@gmail.com
					</p>
				</div>
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
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
</body>
</html>