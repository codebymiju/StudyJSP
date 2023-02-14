<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>DangDangEat Admin - MemberList</title>

<!-- Custom fonts for this template -->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">

<!-- Custom styles for this template -->
<link href="css/sb-admin-2.min.css" rel="stylesheet">

<!-- Custom styles for this page -->
<link href="vendor/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet">

<!-- 폰트 설정 -->
<style>
@font-face {
	font-family: 'GmarketSans';
	font-weight: 300;
	font-style: normal;
	src:
		url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansLight.eot');
	src:
		url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansLight.eot?#iefix')
		format('embedded-opentype'),
		url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansLight.woff2')
		format('woff2'),
		url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansLight.woff')
		format('woff'),
		url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansLight.ttf')
		format("truetype");
	font-display: swap;
}

body {
	font-family: 'GmarketSans';
}

.main-icon {
	padding: 1%;
	height: 40px;
	width: auto;
	margin-right: 3%;
	padding: 5px;
}
</style>
<%
// 세션 아이디가 null 이거나 "admin" 이 아닐 경우 "잘못된 접근입니다!" 출력 후 메인페이지로 이동
String sId = (String)session.getAttribute("sId");
// System.out.println(sId);
// 잘못된 접근일 때 바로 main.jsp 로 보내기
if(sId == null || !sId.equals("admin")) {
	%>
<script>
		alert("잘못된 접근입니다!");
		location.href = "./";
	</script>
<%
}
%>

<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">
	
// 	$(function() { // 차후에 체크박스 선택 후 삭제 혹은 수정으로 변경 예정
// 		$("#deleteMember").on("click", function() {
// 			// confirm dialog 사용하여 "XXX 회원을 삭제하시겠습니까?" 확인 요청
// 			var result = confirm($(".member_id").text() + " 회원을 삭제하시겠습니까?"); // 아니 이거 진짜 let으로 하면 왜 안 돼!!!!
			
// 			if(result) {
// 				$.ajax({
// 					type: "post",
// 					url: "MemberDelete.ad",
// 					data: {
// // 						id: $(".member_id").text() // 수정 필요
// 					},
// 					dataType: "text",
// 					success: function(response) {
// 						alert("회원 삭제 성공!");
// 					},
// 					error: function(xhr, textStatus, errorThrown){
// 						alert("xhr = " + xhr + ", textStatus = " + textStatus + ", errorThrown = " + errorThrown);
// 					}
// 				}); // ajax
// 			} // if문

// 		}); // "#deleteMember"
		
// 	}); // function() {}
	
	function confirmDelete(id) {
		// confirm dialog 사용하여 "XXX 회원 기록을 삭제하시겠습니까?" 확인 요청
		let result = confirm(id + " 회원 기록을 삭제하시겠습니까?");
		
		if(result) {
			location.href = "AdminMemberDelete.ad?id=" + id;
		}
	}
	
	function confirmUpdate(id, status) {
// 		alert(id + ", " + status);
		let result = confirm(id + "회원의 상태를 수정하시겠습니까?");
		
		if(result) {
			location.href = "AdminMemberUpdate.ad?id=" + id + "&status=" + status;
		} else {
			alert("취소되었습니다.");
			location.reload();
		}
		
	}
		
	$(function() {
		// 받은 memberList 변수에 넣기
		let member_list = new Array();
		
		<c:forEach var="member" items="${memberList }" varStatus="status">
			member_list.push({
				id : "${member.member_id }", 
<%--			email : "${member.member_email }", 
				name : "${member.member_name }", 
				mobile : "${member.member_mobile }", 
				addr1 : "${member.member_addr1 }", 
				addr2 : "${member.member_addr2 }", 
				postcode : "${member.member_postcode }", 
				date : "${member.member_date }", 
				birth : "${member.member_birth }", --%>
				status : "${member.member_status }"
<%--			, authStatus : "${member.member_authStatus }" --%>
			});
			
		</c:forEach>
		
// 		for(let member of member_list) { // let i; i < member_list.length; i++
// 			alert(member.id + ", " + member.status);
			
// 			if(member.status == "Y") {
// 				alert("회원");
// 				$("#member_status").val("Y").prop("selected", true);
// 				$("#member_status:eq(1)").prop("selected", true);
// 			} else if(member.status == "N") {
// 				alert("탈퇴 회원");
// 				$("#member_status").val("N").prop("selected", true);
// 				$("#member_status:eq(2)").prop("selected", true);
// 			} else if(member.status == "D") {
// 				alert("휴면 회원");
// 				$("#member_status").val("D").prop("selected", true);
// 				$("#member_status:eq(3)").prop("selected", true);
// 			}
			
// 		}
			
	});
	
	
</script>
</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">

			<!-- Sidebar - Brand -->

			<a
				class="sidebar-brand d-flex align-items-center justify-content-center"
				href="AdminMain.ad">
				<div class="div-top-icon">
					<img class="main-icon" src="img/maindog_white.png">
					<!--                     <i class="fas fa-laugh-wink"></i> -->
				</div>

				<div class="sidebar-brand-text mx-3">DangDangEat Admin</div>
			</a>

			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- Nav Item - Dashboard -->

			<li class="nav-item active"><a class="nav-link"
				href="AdminMain.ad"> <i class="fas fa-fw fa-tachometer-alt"></i>
					<span>관리자 메인 페이지</span></a></li>


			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->
			<div class="sidebar-heading">관리 페이지</div>


			<!-- Nav Item - Tables -->
			<li class="nav-item active"><a class="nav-link"
				href="AdminProductList.ad"> <i class="fas fa-fw fa-calendar"></i>
					<span>상품 관리</span></a></li>
			<!-- Nav Item - Tables -->
			<li class="nav-item active"><a class="nav-link"
				href="AdminMemberList.ad"> <i class="fas fa-fw fa-table"></i> <span>회원
						관리</span></a></li>
			<!-- Nav Item - Tables -->
			<li class="nav-item active"><a class="nav-link"
				href="AdminOrderList.ad"> <i class="fas fa-fw fa-dollar-sign"></i>
					<span>주문 관리</span></a></li>
			<!-- Nav Item - Tables -->
			<li class="nav-item active"><a class="nav-link"
				href="AdminCouponList.ad"> <i
					class="fas fa-fw fa-clipboard-list"></i> <span>쿠폰 관리</span></a></li>
			<!-- Nav Item - Tables -->
			<li class="nav-item active"><a class="nav-link"
				href="AdminBoardList.ad"> <i class="fas fa-fw fa-comments"></i>
					<span>게시판 관리</span></a></li>

			<!-- Divider -->
			<hr class="sidebar-divider d-none d-md-block">

			<!-- Sidebar Toggler (Sidebar) -->
			<div class="text-center d-none d-md-inline">
				<button class="rounded-circle border-0" id="sidebarToggle"></button>
			</div>

		</ul>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

					<!-- Sidebar Toggle (Topbar) -->
					<form class="form-inline">
						<button id="sidebarToggleTop"
							class="btn btn-link d-md-none rounded-circle mr-3">
							<i class="fa fa-bars"></i>
						</button>
					</form>

					<!-- Topbar Search -->
					<form
						class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
						<div class="input-group">

							<!--                             <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." -->
							<!--                                 aria-label="Search" aria-describedby="basic-addon2"> -->
							<div class="input-group-append">
								<!--                                 <button class="btn btn-primary" type="button"> -->
								<!--                                     <i class="fas fa-search fa-sm"></i> -->
								<!--                                 </button> -->
							</div>
						</div>
					</form>

					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">

						<!-- Nav Item - Search Dropdown (Visible Only XS) -->
						<li class="nav-item dropdown no-arrow d-sm-none"><a
							class="nav-link dropdown-toggle" href="#" id="searchDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <i class="fas fa-search fa-fw"></i>
						</a> <!-- Dropdown - Messages -->
							<div
								class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
								aria-labelledby="searchDropdown">
								<form class="form-inline mr-auto w-100 navbar-search">
									<div class="input-group">
										<input type="text"
											class="form-control bg-light border-0 small"
											placeholder="Search for..." aria-label="Search"
											aria-describedby="basic-addon2">
										<div class="input-group-append">
											<button class="btn btn-primary" type="button">
												<i class="fas fa-search fa-sm"></i>
											</button>
										</div>
									</div>
								</form>
							</div></li>


						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <span
								class="mr-2 d-none d-lg-inline text-gray-600 font-weight-bold">Admin</span>
								<img class="img-profile rounded-circle"
								src="img/dangprofile.png">
						</a> <!-- Dropdown - User Information -->
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="userDropdown">
								<a class="dropdown-item" href="./"> <i
									class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i> Member
									Main
								</a> <a class="dropdown-item" href="CheckPassForm.me"> <i
									class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Profile
								</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="MemberLogout.me"> <i
									class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i> Logout
								</a>
							</div></li>

					</ul>

				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<h1 class="h3 mb-2 text-gray-800 font-weight-bold">회원 관리</h1>
					<p class="mb-4">회원 목록 수정 및 삭제</p>

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">회원 목록</h6>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
									<thead>
										<tr>
											<th>아이디</th>
											<th>이메일</th>
											<th>이름</th>
											<th>휴대폰</th>
											<th>주소</th>
											<th>우편<br>번호
											</th>
											<th>가입일</th>
											<th>댕생일</th>
											<th>회원 상태</th>
											<th>이메일<br>인증
											</th>
											<th>삭제</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>아이디</th>
											<th>이메일</th>
											<th>이름</th>
											<th>휴대폰</th>
											<th>주소</th>
											<th>우편<br>번호
											</th>
											<th>가입일</th>
											<th>댕생일</th>
											<th>회원 상태</th>
											<th>이메일<br>인증
											</th>
											<th>삭제</th>
										</tr>
									</tfoot>
									<tbody>
										<c:forEach var="member" items="${memberList }"
											varStatus="status">
											<tr>
												<td id="member_id">${member.member_id }</td>
												<td>${member.member_email }</td>
												<td>${member.member_name }</td>
												<td>${member.member_mobile }</td>
												<td>${member.member_addr1 }<br>
													${member.member_addr2 }
												</td>
												<td>${member.member_postcode }</td>
												<td>${member.member_date }</td>
												<td>${member.member_birth }</td>
												<td><select id="member_status_${status.current}"
													name="${member.member_status }"
													onchange="confirmUpdate('${member.member_id}', this.value)">
														<c:choose>
															<c:when test="${member.member_status eq 'Y' }">
																<option value="Y" selected="selected">회원</option>
															</c:when>
															<c:otherwise>
																<option value="Y">회원</option>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${member.member_status eq 'N' }">
																<option value="N" selected="selected">탈퇴 회원</option>
															</c:when>
															<c:otherwise>
																<option value="N">탈퇴 회원</option>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${member.member_status eq 'D' }">
																<option value="D" selected="selected">휴면 회원</option>
															</c:when>
															<c:otherwise>
																<option value="D">휴면 회원</option>
															</c:otherwise>
														</c:choose>
												</select></td>
												<td>${member.member_authStatus }</td>
												<td>
													<button class="btn btn-danger btn-circle btn-sm"
														onclick="confirmDelete('${member.member_id}')">
														<i class="fas fa-trash"></i>
													</button>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>

				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; Your Website 2020</span>
					</div>
				</div>
			</footer>
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary" href="login.html">Logout</a>
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

	<!-- Page level plugins -->
	<script src="vendor/datatables/jquery.dataTables.min.js"></script>
	<script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

	<!-- Page level custom scripts -->
	<script src="js/demo/datatables-demo.js"></script>

</body>

</html>