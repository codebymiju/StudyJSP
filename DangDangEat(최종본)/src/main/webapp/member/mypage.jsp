<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>DangDangShop - My Page</title>
<meta name="description" content="Ela Admin - HTML5 Admin Template">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="apple-touch-icon" href="https://i.imgur.com/QRAUqs9.png">
<link rel="shortcut icon" href="https://i.imgur.com/QRAUqs9.png">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/normalize.css@8.0.0/normalize.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/font-awesome@4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/gh/lykmapipo/themify-icons@0.1.2/css/themify-icons.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/pixeden-stroke-7-icon@1.2.3/pe-icon-7-stroke/dist/pe-icon-7-stroke.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/3.2.0/css/flag-icon.min.css">
<link rel="stylesheet" href="assets/css/cs-skin-elastic.css">
<link rel="stylesheet" href="assets/css/style.css">

<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800'
	rel='stylesheet' type='text/css'>

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
</style>
<%
// ?????? ???????????? null ????????? "" ??? ?????? "???????????? ????????? ??????????????????." ?????? ??? ????????? ???????????? ??????
String sId = (String) session.getAttribute("sId");
// System.out.println(sId);
if (sId == null || sId.equals("")) {
%>
<script>
	alert("???????????? ????????? ??????????????????.");
	location.href = "MemberLoginForm.me";
</script>
<%
}
%>
<!-- <script type="text/javascript" src="https://cdn.jsdelivr.net/html5shiv/3.7.3/html5shiv.min.js"></script> -->
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>





<script type="text/javascript">

	
	
	$(function() {

		let id = $("#id").text(); // ?????? ?????????
		let email = $("#email").val(); // ????????? ?????? ?????? ????????? (?????? ????????? ??????)
		// 		alert(email);

		const param = new URL(location).searchParams;
		//???????????? ??????
		function searchCoupon(){
				
								 $.ajax({//??????????????? ??????(????????????,??????????????????) ????????? ??????
											type: "get",
											url: "SearchUsableCoupon.od",
											contentType: "application/x-www-form-urlencoded; charset=UTF-8",
											dataType: "HTML",
											data:{
												"isMypage":true//????????????????????? ????????? ?????? HTML??? ?????????
											}
							
										})
										.done(function(result){
								    		
											$("#ajax_changeDiv").html(result);
											
											$("#couponDiv").after("<input type='button' value='??????????????? ??????' class='mb-5 mx-3 p-2 btn btn-sm  btn-secondary rounded '  style='float: right;' id='goMypagebtn'></button>");
											
											if(param.get("cp_code") != null){//???????????? cp_code ??????
// 												alert(param.get("cp_code"));
												$("#search_coupon_code").text(param.get("cp_code"));
											}
										})
										.fail(function(data){
											alert("ajax?????? ??????");
										});
											
									}
									
		

		
		
		
		//1)??????????????? ???????????? ?????? ajax ??????
		if(param.get("cp_code") != null){//???????????? cp_code ??????
			
			searchCoupon(); //?????? ????????? ???????????? ajax??????
			
			
		}else {
			
		//2) ???????????? ?????? ??? ?????? ??????????????? ajax??? ??????
			$("#CouponCount").on("click", function (){
				searchCoupon();
			});//$("#CouponCount")
			
		}
	});
	
	
	
	//Mypage??? ???????????? ?????? - ??????????????? ??????????????? ?????? ??????
	$(document).on("click", "#goMypagebtn", function(){
	
		const param = new URL(location).searchParams;
		if(param.get("cp_code") != null){//????????????cp_code??? ???????????? ???????????? ?????????????????? ??????????????? ????????? ??????
			
			location.href="MyPage.me"; 
			
		}else{//??????????????? ????????? ?????????//????????????cp_code??? ???????????? ???????????? ?????????????????? ??????????????? ????????? ??????
			
			location.reload(true);
		}
	});
</script>

<!-- ????????? ?????? API -->
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
	function kakaoAddr() { // onclick ??? ????????? ?????? ??????
		new daum.Postcode({
			oncomplete : function(data) {
				var roadAddr = data.roadAddress;
				// ????????? ???????????? ?????? ?????? ?????? ??? ?????? ?????????
				document.getElementById("postcode").value = data.zonecode;
				document.getElementById("addr1").value = data.roadAddress;

			}
		}).open();
	}
</script>

</head>
<body>

	<!-- Header-->
	<!-- top.jsp -->
	<jsp:include page="../inc/top.jsp"></jsp:include>

	<!-- Animated -->
	<div class="animated fadeIn container">
		<!-- Widgets  -->
		<div class="row">
			<div class="col-lg-3 col-md-6">
				<div class="card">
					<div class="card-body">
						<div class="stat-widget-five">
							<div class="stat-icon dib flat-color-1">
								<i class="fa fa-shopping-cart"></i>
							</div>
							<div class="stat-content">
								<div class="text-left dib">
									<div class="stat-heading">??????</div>
									<div class="stat-text">
										<span>${order_count } ???</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-lg-3 col-md-6">
				<div class="card">
					<div class="card-body">
						<div class="stat-widget-five">
							<div class="stat-icon dib flat-color-2">
								<i class="fa fa-tags"></i>
							</div>
							<div class="stat-content" id="CouponCount">
								<div class="text-left dib">
									<div class="stat-heading">??????</div>
									<div class="stat-text">
										<span>${coupon_count } ???</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-lg-3 col-md-6">
				<div class="card">
					<div class="card-body">
						<div class="stat-widget-five">
							<div class="stat-icon dib flat-color-3">
								<i class="fa fa-question"></i>
							</div>
							<div class="stat-content">
								<div class="text-left dib">
									<div class="stat-heading">??????</div>
									<div class="stat-text">
										<span>${review_count } ???</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-lg-3 col-md-6">
				<div class="card">
					<div class="card-body">
						<div class="stat-widget-five">
							<div class="stat-icon dib flat-color-4">
								<i class="fa fa-list-ul"></i>
							</div>
							<div class="stat-content">
								<div class="text-left dib">
									<div class="stat-heading">Q&A</div>
									<div class="stat-text">
										<span>${qna_count } ???</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /Widgets -->


	<div class="container" id="ajax_changeDiv">
		<div class="card" >
			<div class="card-header">
				<strong>?????? ??????</strong>
			</div>
			<div class="card-body card-block" >
				<div class="row form-group">
					<div class="col col-md-3">
						<label class="form-control-label font-weight-bold">ID</label>
					</div>
					<div class="col-12 col-md-9">
						<span class="form-control-static bg-gradient-light" id="id">${member.member_id }</span>
					</div>
				</div>
				<div class="row form-group">
					<div class="col col-md-3">
						<label class="form-control-label font-weight-bold">??????</label>
					</div>
					<div class="col-12 col-md-9">
						<span class="form-control-static bg-gradient-light">${member.member_name }</span>
					</div>
				</div>
				<div class="row form-group">
					<div class="col col-md-3">
						<label for="text-input"
							class="form-control-label font-weight-bold">??????<font
							color="red">*</font></label>
					</div>
					<div class="col-12 col-md-4 d-flex">
						<span class="form-control-static bg-gradient-light" id="postcode">${member.member_postcode }</span>
					</div>
				</div>
				<div class="row form-group">
					<div class="col col-md-3"></div>
					<div class="col-12 col-md-8">
						<span class="form-control-static bg-gradient-light" id="addr1">${member.member_addr1 }</span>
						<small class="form-text text-muted">???????????????</small>
					</div>
				</div>
				<div class="row form-group">
					<div class="col col-md-3">
						<label for="text-input" class=" form-control-label"></label>
					</div>
					<div class="col-12 col-md-8">
						<span class="form-control-static bg-gradient-light" id="addr2">${member.member_addr2 }</span>
						<small class="form-text text-muted">????????????</small>
					</div>
				</div>
				<div class="row form-group">
					<div class="col col-md-3">
						<label for="text-input"
							class=" form-control-label font-weight-bold">????????????<font
							color="red">*</font></label>
					</div>
					<div class="col-12 col-md-6">
						<span class="form-control-static bg-gradient-light" id="mobile">${member.member_mobile }</span>
						<small class="form-text text-muted"></small>
					</div>
				</div>
				<div class="row form-group">
					<div class="col col-md-3">
						<label for="email-input"
							class="form-control-label font-weight-bold">?????????<font
							color="red">*</font></label>
					</div>
					<div class="col-12 col-md-6">
						<span class="form-control-static bg-gradient-light" id="email">${member.member_email }</span>
						<c:choose>
							<c:when test="${member.member_authStatus == 'Y' }">
								<small class="help-block form-text text-muted"
									id="email_auth_status">????????? ????????? ?????????????????????.</small>
							</c:when>
							<c:otherwise>
								<small class="help-block form-text text-muted"
									id="email_auth_status">????????? ????????? ???????????????.</small>
							</c:otherwise>
						</c:choose>
					</div>
				</div>

				<div class="row form-group">
					<div class="col col-md-3">
						<label for="text-input"
							class=" form-control-label font-weight-bold">?????????</label>
					</div>
					<div class="col-12 col-md-6">
						<c:choose>
							<c:when test="${empty member.member_birth }">
								<span class="form-control-static bg-gradient-light" id="birth">????????? ????????? ????????????.</span>
							</c:when>
							<c:otherwise>
								<span class="form-control-static bg-gradient-light" id="birth">${member.member_birth }</span>
							</c:otherwise>
						</c:choose>
						<small class="form-text text-muted">?????? ????????? ???????????????.</small>
					</div>
				</div>
				<div class="row form-group">
					<div class="col col-md-3">
						<small class="form-text text-muted" style="text-align: right;"><font
							color="red">*</font>???(???) ?????? ?????? ???????????????.</small>
					</div>
					<div class="col-12 col-md-8">
						<button onclick="location.href='CheckPassForm.me'"
							class="btn btn-secondary" value="?????? ?????? ?????????" style="float: right;">?????? ?????? ?????????</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Footer-->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy;
				DangDangEat 2023</p>
		</div>
	</footer>

	<!-- Scripts -->
	<script
		src="https://cdn.jsdelivr.net/npm/jquery@2.2.4/dist/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.14.4/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/jquery-match-height@0.7.2/dist/jquery.matchHeight.min.js"></script>
	<script src="assets/js/main.js"></script>
</body>
</html>