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

<title>DangDangShop - Check Pass</title>

<%
// 세션 아이디가 null 이거나 "" 일 경우 "로그인이 필요한 페이지입니다." 출력 후 로그인 페이지로 이동
String sId = (String)session.getAttribute("sId");
// System.out.println(sId);
// 잘못된 접근일 때 바로 로 보내기
if(sId == null || sId.equals("")) {
		%>
<script>
		alert("로그인이 필요한 페이지입니다.");
		location.href="MemberLoginForm.me";
		</script>
<%
}
%>

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

</head>

<body>

	<!-- Header-->
	<!-- top.jsp -->
	<jsp:include page="../inc/top.jsp"></jsp:include>

	<div class="container">

		<!-- Outer Row -->
		<div class="row justify-content-center">

			<div class="col-xl-10 col-lg-12 col-md-9">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<!-- Nested Row within Card Body -->
						<div class="row">
							<div class="col-lg-6 d-none d-lg-block bg-password-image"></div>
							<div class="col-lg-6">
								<div class="p-5">
									<div class="text-center">
										<h1 class="h4 text-gray-900 mb-2">Password 확인</h1>
										<p class="mb-4">비밀번호를 한 번 더 입력해주시개</p>
									</div>
									<form class="user" action="MemberCheckPro.me" method="post">
										<div class="form-group">
											<input type="password" name="pass"
												class="form-control form-control-user"
												id="exampleInputEmail" aria-describedby="emailHelp"
												placeholder="Password">
										</div>
										<input type="submit"
											class="btn btn-primary btn-user btn-block" value="확인">
									</form>
									<hr>
									<div class="text-center">
										<a class="small" href="MemberFindPassForm.me">비밀번호를 잊으셨나요?</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>

		</div>

	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js"></script>

	<!-- Footer-->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy;
				DangDangEat 2023</p>
		</div>
	</footer>

</body>

</html>