<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>DangDangShop - Find Id Result</title>

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">

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

<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">
	
	$(function() {
		
	});
	

</script>
</head>

<body>
	<!-- Header-->
	<!-- top.jsp -->
	<jsp:include page="../inc/top.jsp"></jsp:include>

	<div class="container">

		<!-- Outer Row -->
		<div class="content">
			<div class="animated fadeIn">
				<div class="row"></div>
			</div>
		</div>

		<div class="row justify-content-center">

			<div class="col-xl-10 col-lg-12 col-md-9">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<!-- Nested Row within Card Body -->
						<div class="row">
							<div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
							<div class="col-lg-6">
								<div class="p-5">
									<div class="text-center">
										<h1 class="h4 text-gray-900 mb-4">아이디 찾기</h1>
									</div>
									<div class="col-md-12">
										<div class="card">
											<div class="card-header">
												<strong class="card-title mb-3">아이디 조회 결과</strong>
											</div>
											<div class="card-body">
												<div class="mx-auto d-block">
													<div class="location text-sm-center">조회된 아이디</div>
													<h5 class="text-sm-center mt-2 mb-1">${member.member_id }</h5>
												</div>
												<hr>
												<div class="card-text text-sm-center">
													<div>이름 : ${member.member_name }</div>
													<div>이메일 : ${member.member_email }</div>
												</div>
											</div>
										</div>
									</div>
									<hr>
									<div class="text-center">
										<a class="small" href="MemberLoginForm.me">Login</a>
									</div>
									<div class="text-center">
										<a class="small" href="MemberFindPassForm.me">Forgot
											Password?</a>
									</div>
								</div>
							</div>
						</div>
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

	<!-- Bootstrap core JavaScript-->
	<script src="../vendor/jquery/jquery.min.js"></script>
	<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="../vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="../js/sb-admin-2.min.js"></script>

</body>

</html>