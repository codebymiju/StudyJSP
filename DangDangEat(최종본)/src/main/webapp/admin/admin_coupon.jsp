<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 쿠폰 등록</title>
   <link href="css/styles.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
    <!-- 드롭다운에 필요한 부트스트랩 bootstrap.bundle.min.js-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  
  
<!-- Custom fonts for this template -->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

<!-- Custom styles for this template -->
<link href="css/sb-admin-2.min.css" rel="stylesheet">

<!-- Custom styles for this page -->
<link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
   @font-face { /*지마켓 산스*/
    font-family: 'GmarketSansMedium';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}    

@font-face {
    font-family: 'GmarketSans';
    font-weight: normal;
    font-style: normal;
    src: url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansLight.eot');
    src: url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansLight.eot?#iefix') format('embedded-opentype'),
         url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansLight.woff2') format('woff2'),
         url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansLight.woff') format('woff'),
         url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansLight.ttf') format("truetype");
    font-display: swap;
} 
body {
    font-family: 'GmarketSansMedium';
    vertical-align: middle;
    
}
#accordionSidebar{
font-family: 'GmarketSans';
}

	input {
  width: 400x;
  height: 32px;
  font-size: 15px;
  border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  background-color: rgb(233, 233, 233);
  vertical-align: middle;

}


 label {
  vertical-align: middle;
 }
 
 .main-icon {
    padding: 1%;
    height: 40px;
    width: auto;
   margin-right: 3%;
   padding: 5px;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">

//onsumit메서드 실행 시 체크할 항목 변수선언
let submitCheckRegex_name = false;//쿠폰이름
let submitCheckRegex_code = false;//쿠폰코드
let submitCheckCount_canUseCode = false;//사용가능한 쿠폰코드인지

//발행대상 관련 span태그
$(document).on("change","select[name=coupon_target]",function(){
	$("#coupon_target option:selected").val() == 'new_member' ?
			$("#coupon_target_Span").html(" <small>회원가입하는 모든 회원에게 자동 발급 됩니다.</small>")
	:		$("#coupon_target_Span").html(" <small>강아지 생일을 입력한 모든 회원의 생일을 기준으로 [유효기간÷2]일 전 후로 사용 가능합니다.</small>")
	
});
	$(function() {
		
		//쿠폰 자동 생성 radio버튼 클릭시 관련 내용 보이기
		$("#coupon_type2").on("change", function() {//쿠폰 자동 생성 버튼 클릭시
			
			$("#tr_coupon_code").after(
		 		"<tr id='coupon_target'>"+
	 				"<td>발행대상</td>"+
	 				"<td>"+
	 					"<select name='coupon_target' id='coupon_target'>"+
	 						"<option value='new_member'> 첫 회원가입 </option>"+
	 						"<option value='birth'> 댕댕이 생일 </option>"+
	 					"</select><span id='coupon_target_Span'></span>"+
	 				"</td>"+
	 			"</tr>");
			
			
			$("#coupon_start").attr("disabled", true);
			$("#coupon_start_div").html("<small>회원별 상이합니다. 유효기간을 설정해주세요</small>");
			$("#coupon_start_div").css("color", "royalblue");
			$("coupon_date_result").empty();
		});//on("change")
		
			//쿠폰 코드 생성 버튼 클릭시 
			$("#coupon_type1").on("click",function(){
			//위에서 만든 영역(쿠폰 자동 생성 관련) 지우기
			$("#coupon_target").empty();
			$("#coupon_start_div").empty();
			$("#coupon_start").attr("disabled", false);
		
			
			});
		
		



				
				$("#coupon_period").on("change",function(){
					
					if($("#coupon_start").val() != ""){
						
						let startDate = $("#coupon_start").val(); //

						let startDateArr = startDate.split("-"); // 문자열을 분리해 배열로 변환

						//주의! month는 0-11임 ! 
						let date1 = new Date(startDateArr[0], startDateArr[1] - 1, startDateArr[2]);

						let resultDate = date1.getDate()+ Number($("#coupon_period").val()); //Number()함수를 통해서 형변환

						let endDate = new Date(date1.setDate(resultDate));

						//년-월-일로 포멧 변경
						//getMonth(),getDate()메서드는 0-11, 1-31 값을 반환하므로 앞에 00을 붙인 후 slice()로 맨뒤에서 2번째까지의 문자만 가져옴
						let formatDate = endDate.getFullYear()+ "-"+ (("00" + (endDate.getMonth() + 1).toString()).slice(-2))+ "-"+ (("00" + endDate.getDate().toString()).slice(-2));

						let coupon_expir = $("#coupon_period").val();

						$("#coupon_date_result").html(
								"쿠폰 유효기간 :  " + startDate + " - " + formatDate
										+ " ( " + coupon_expir + " 일 )<br>");

						$("#coupon_date_result").css("color", "blue");
				
					}
	
			
				});
		
			let regex = /^[가-힣a-zA-Z0-9_%]{2,30}$/;
	
			
		//정규표현식
		$("#coupon_name").on("change",function(){
			
			if(!regex.exec($("#coupon_name").val())){
				submitCheckRegex_name = false;
				$("#coupon_name-span").html("  <small>2 - 30자 이내 , 한글,영어,특수문자(_%)만 사용가능합니다</small>").css("color","red");
			}else{
				submitCheckRegex_name = true;
				$("#coupon_name-span").html(" <small>사용가능한 쿠폰명입니다</small>").css("color","royalblue");
			}
		});
		
		$("#coupon_code").on("change",function(){
			if(!regex.exec($("#coupon_code").val())){
				submitCheckRegex_code = false;
				$("#code_check-span").html("  <small>2 - 30자 이내 , 한글,영어,특수문자(_%)만 사용가능합니다</small>").css("color","red");
				
			}else{
				submitCheckRegex_code = true;
				$("#code_check-span").empty();
				
			}
		});
	
		

		//쿠폰코드: 중복된 쿠폰코드인지 확인
		$("#coupon_code").on("change",function() {

	
	
				$.ajax({
						url: "CheckExistCouponCode.ad",
						data: {
							coupon_code: $("#coupon_code").val()
							},
						contentType: "application/x-www-form-urlencoded; charset=UTF-8",
						success: function(result) {
							
									if(result == "true"){ //이미 존재하는 쿠폰코드
										$("input[type=submit]").attr("disabled", true);
										$("#code_check").html("<small>이미 사용했던 쿠폰코드입니다.</small>").css("color", "red").focus();
									
									}else{
										
										submitCheckCount_canUseCode = true;
										$("#code_check").html("<small>사용가능한 쿠폰 코드입니다.</small>").css("color", "royalblue").focus();
										$("input[type=submit]").attr("disabled", false);
									
									}//end of else
									
						},//success:
						fail: function(result) {
							alert("중복쿠폰 조회에 실패했습니다.");
					
						}//fail:
					
					});//$.ajax({
			});
					
			
		$("form").submit(function(){
			
// 			alert("submitCheckRegex_name : "+ submitCheckRegex_name);
// 			alert("submitCheckRegex_code : "+ submitCheckRegex_name);
// 			alert("submitCheckCount_canUseCode : "+ submitCheckCount_canUseCode);
				if(submitCheckRegex_name && submitCheckRegex_code && submitCheckCount_canUseCode){
					 alert("쿠폰 등록에 성공했습니다.\n쿠폰 리스트 페이지로 이동합니다.");
					return true;
				
				}else{
					
						switch(false){
							case submitCheckRegex_name: $("#coupon_name").css("backgroundColor", "rgba(255, 0, 0 , 0.5)") ;
							case submitCheckRegex_code: $("#coupon_code").css("backgroundColor", "rgba(255, 0, 0, 0.5)"); 
							case submitCheckCount_codeLength: $("#coupon_code").css("backgroundColor", "rgba(255, 0, 0, 0.5)");
							
						}
						$("input[type=submit]").attr("disabled", false);
					return false;
				}
		
		});



	});//$(function (){}

</script>
</head>
<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

               <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->

            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="AdminMain.ad">
                <div class="div-top-icon">
                <img class="main-icon" src="img/maindog_white.png">
<!--                     <i class="fas fa-laugh-wink"></i> -->
                </div>

                <div class="sidebar-brand-text mx-3">DangDangEat Admin</div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->

            <li class="nav-item active">
                <a class="nav-link" href="AdminMain.ad">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>관리자 메인 페이지</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                관리 페이지
            </div>


            <!-- Nav Item - Tables -->
            <li class="nav-item active">
                <a class="nav-link" href="AdminProductList.ad">
                    <i class="fas fa-fw fa-calendar"></i>
                    <span>상품 관리</span></a>
            </li>
            <!-- Nav Item - Tables -->
            <li class="nav-item active">
                <a class="nav-link" href="AdminMemberList.ad">
                    <i class="fas fa-fw fa-table"></i>
                    <span>회원 관리</span></a>
            </li>
            <!-- Nav Item - Tables -->
            <li class="nav-item active">
                <a class="nav-link" href="AdminOrderList.ad">
                    <i class="fas fa-fw fa-dollar-sign"></i>
                    <span>주문 관리</span></a>
            </li>
            <!-- Nav Item - Tables -->
            <li class="nav-item active">
                <a class="nav-link" href="AdminCouponList.ad">
                    <i class="fas fa-fw fa-clipboard-list"></i>
                    <span>쿠폰 관리</span></a>
            </li>
            <!-- Nav Item - Tables -->
            <li class="nav-item active">
                <a class="nav-link" href="AdminBoardList.ad">
                    <i class="fas fa-fw fa-comments"></i>
                    <span>게시판 관리</span></a>
            </li>

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
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <form class="form-inline">
                        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                            <i class="fa fa-bars"></i>
                        </button>
                    </form>

                    <!-- Topbar Search -->
                    <form
                        class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                        <div class="input-group">
                            <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..."
                                aria-label="Search" aria-describedby="basic-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-primary" type="button">
                                    <i class="fas fa-search fa-sm"></i>
                                </button>
                            </div>
                        </div>
                    </form>

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small"
                                            placeholder="Search for..." aria-label="Search"
                                            aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>

                        <!-- Nav Item - Alerts -->
                        <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-bell fa-fw"></i>
                                <!-- Counter - Alerts -->
                                <span class="badge badge-danger badge-counter">3+</span>
                            </a>
                            <!-- Dropdown - Alerts -->
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="alertsDropdown">
                                <h6 class="dropdown-header">
                                    Alerts Center
                                </h6>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-primary">
                                            <i class="fas fa-file-alt text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 12, 2019</div>
                                        <span class="font-weight-bold">A new monthly report is ready to download!</span>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-success">
                                            <i class="fas fa-donate text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 7, 2019</div>
                                        $290.29 has been deposited into your account!
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-warning">
                                            <i class="fas fa-exclamation-triangle text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 2, 2019</div>
                                        Spending Alert: We've noticed unusually high spending for your account.
                                    </div>
                                </a>
                                <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
                            </div>
                        </li>

                        <!-- Nav Item - Messages -->
                        <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-envelope fa-fw"></i>
                                <!-- Counter - Messages -->
                                <span class="badge badge-danger badge-counter">7</span>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="messagesDropdown">
                                <h6 class="dropdown-header">
                                    Message Center
                                </h6>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="img/undraw_profile_1.svg"
                                            alt="...">
                                        <div class="status-indicator bg-success"></div>
                                    </div>
                                    <div class="font-weight-bold">
                                        <div class="text-truncate">Hi there! I am wondering if you can help me with a
                                            problem I've been having.</div>
                                        <div class="small text-gray-500">Emily Fowler · 58m</div>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="img/undraw_profile_2.svg"
                                            alt="...">
                                        <div class="status-indicator"></div>
                                    </div>
                                    <div>
                                        <div class="text-truncate">I have the photos that you ordered last month, how
                                            would you like them sent to you?</div>
                                        <div class="small text-gray-500">Jae Chun · 1d</div>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="img/undraw_profile_3.svg"
                                            alt="...">
                                        <div class="status-indicator bg-warning"></div>
                                    </div>
                                    <div>
                                        <div class="text-truncate">Last month's report looks great, I am very happy with
                                            the progress so far, keep up the good work!</div>
                                        <div class="small text-gray-500">Morgan Alvarez · 2d</div>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="https://source.unsplash.com/Mv9hjnEUHR4/60x60"
                                            alt="...">
                                        <div class="status-indicator bg-success"></div>
                                    </div>
                                    <div>
                                        <div class="text-truncate">Am I a good boy? The reason I ask is because someone
                                            told me that people say this to all dogs, even if they aren't good...</div>
                                        <div class="small text-gray-500">Chicken the Dog · 2w</div>
                                    </div>
                                </a>
                                <a class="dropdown-item text-center small text-gray-500" href="#">Read More Messages</a>
                            </div>
                        </li>

                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Douglas McGee</span>
                                <img class="img-profile rounded-circle"
                                    src="img/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Profile
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Settings
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Activity Log
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->



 	<div class="m-5">
 	&#128273;관리자 >
 	
<div class="dropdown d-inline">
  <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
    쿠폰
  </a>

  <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
    <li><a class="dropdown-item" href="CouponRegister.ad">쿠폰 등록</a></li>
    <li><a class="dropdown-item" href="AdminCouponList.ad">등록된 쿠폰 관리</a></li>
  </ul>
</div>
 	  > &#127903; 쿠폰등록
 		<div class="m-3"> <h2>쿠폰 정보 &#127903;</h2> </div>
 		<hr>
 		<form action="CouponRegisterPro.ad">
 		
	 		<table class="table table-hove">
	 			<tr>
	 				<td style="width: 300px" >쿠폰명</td>
	 				<td><input type="text" id="coupon_name" name="coupon_name" required="required" ><span id="coupon_name-span"></span></td>
	 			</tr>
	 			<tr>
	 				<td>쿠폰 형식</td>
	 				<td>
	 					<input type="radio" id="coupon_type1" name="coupon_type" value="0" required="required">
	 					<label for="coupon_type1">쿠폰 코드 생성</label>
	 					<input type="radio" id="coupon_type2" name="coupon_type" value="1">
	 					<label for="coupon_type2">쿠폰 자동 생성</label>
	 				</td>
	 			</tr>
	 			<tr id="tr_coupon_code">
	 				<td>쿠폰 코드</td>
	 				<td><input type="text" id="coupon_code" name="coupon_code" required="required"><span id="code_check-span"></span><div id="code_check"></div></td>
	 			</tr>
	 			<tr>
	 				 <td>할인 범위(단위: %)</td>
	 				<td><input type="number" id="discount_unit_input" name="discount_value" min="0" max="100" required="required" ></td>
	 			</tr>
	 			<tr>
	 				<td>쿠폰 시작일</td>
	 				<td><input type="date" id="coupon_start" name="coupon_start" required="required"><div id="coupon_start_div"></div></td>
	 			</tr>
	 			<tr>
	 				<td>쿠폰 유효기간 (일)</td>
	 				<td><input min="0" type="number" id="coupon_period" name ="coupon_period" placeholder="숫자만 입력하세요." required="required">&nbsp;
	 			<tr align="right">
	 				<td colspan="2" ><div id="coupon_date_result"></div></td>
	 			</tr>
	 			<tr>
	 				<td>최소 구매액</td>
	 				<td><input type="number" id="min_amount" name="min_amount" min="0" placeholder="숫자만 입력하세요." required="required"></td>
	 			</tr>
	 			<tr>
	 				<td>최대 할인액</td>
	 				<td><input type="number" id="max_discount" name="max_discount" min="0" placeholder="숫자만 입력하세요." required="required"></td>
	 			</tr>
	 			
	 			
	 		</table>
	 			<input type="submit" value="쿠폰 등록" class = "btn btn-primary btn-sm">
 		</form>
 		
 		</div>
 		</div>

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">

                        <span>Copyright &copy; Your Website 2021</span>

                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
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

<!--     <script src="vendor/chart.js/Chart.min.js"></script> -->

<!--     Page level custom scripts -->
<!--     <script src="js/demo/chart-area-demo.js"></script> -->
<!--     <script src="js/demo/chart-pie-demo.js"></script> -->

    <script src="vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="js/demo/datatables-demo.js"></script>


</body>

</html>