<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="utf-8">

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>DangDangEat Admin - 쿠폰 등록</title>

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
<!-- 폰트 설정 -->
<style>
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
  @font-face { /*지마켓 산스*/
    font-family: 'GmarketSansMedium';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}    

body {
    font-family: 'GmarketSansMedium';
    vertical-align: middle;
}

#accordionSidebar{
font-family: 'GmarketSans';
}

</style>

<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
     
<style type="text/css">



	input {
  width: 300x;
  height: 32px;
  font-size: 15px;
  border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  background-color: rgb(233, 233, 233);
  vertical-align: middle;

}
.input_wid65{
  width: 85px;
  height: 32px;
  border-radius: 10px;
  align-content: center;
}

.input_wid100{
  width: 200px;
  height: 32px;
  border-radius: 10px;
  align-content: center;
}


 label {
  vertical-align: middle;
 }
 
 tr {
 	text-align: center;
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

//수정 버튼 클릭시
$(document).on("click", "input[name=updateCpBtn]", function(){
	
		let tr_id  = $(this).closest('tr').attr('id');// 버튼이 속한 tr태그의 id가져오기
		$this_td = $("#"+tr_id).children('td');
		
		let cp_name =  $this_td.eq(0).text(); //tr태그안의 td에서 첫번째(=쿠폰명)의 text가져오기
		let cp_code =  $this_td.eq(2).text(); 



		//처음 로드된 값 변수에 저장
		 let isDelete = false;
		
		//=> tr태그안의 td에서 ep(N)번째의 text가져오기
		 $originalName =  $this_td.eq(0).text();
		 $originalCp_target =  $this_td.eq(1).text();
		 $originalCp_code =  $this_td.eq(2).text();
		 $originalCp_discount_value=  $this_td.eq(3).text();
		 $originalperiod = $this_td.eq(4).text();
		 $originalCp_startdate =  $this_td.eq(5).text();
		 $originalCp_enddate =  $this_td.eq(6).text();
		 $originalCp_min_price =  $this_td.eq(7).text();
		 $originalCp_max_discount =  $this_td.eq(8).text();
		 $originalCp_state =  $this_td.eq(9).text();


		
	 


	
			$("#"+tr_id).html("<td><input class='input_wid100' type='text' required='required' value="+$("#"+tr_id).children('td').eq(0).text() +"><div></div></td>"//cp_name
							   +"<td>"+$("#"+tr_id).children('td').eq(1).text() +"</td>"//cp_target
							   +"<td>"+$("#"+tr_id).children('td').eq(2).text()+"</td>"//cp_code
							   +"<td>"+$("#"+tr_id).children('td').eq(3).text()+"</td>"//cp_discount_value
							   +"<td><input class='input_wid65' type='number' value="+$("#"+tr_id).children('td').eq(4).text()+"></td>"//cp_period
							   +"<td>"+$("#"+tr_id).children('td').eq(5).text()+"</td>"//cp_startdate
							   +"<td>"+"</td>"//cp_enddate
							   +"<td><input class='input_wid65' type='number' required='required' value="+$("#"+tr_id).children('td').eq(7).text()+"></td>"//cp_min_price
							   +"<td><input class='input_wid65' type='number' required='required' value="+$("#"+tr_id).children('td').eq(8).text()+"></td>"//cp_max_discount
							   +"<td></td>"
							   +"<td><input type='button' value='수정' class ='btn btn-success btn-sm m-1' name='updateComplete'>"
							   +"<input type='button' value='취소' class ='btn btn-danger btn-sm m-1' name='cancelUpdate'></td>"
					);
			
			
			$("input[name=cancelUpdate]" ).on("click", function () {//수정 창에서 취소버튼 클릭시
				
			
				$("#"+tr_id).html( //기존내용으로 바꾸기
						
							"<td>"+ $originalName +"</td>"//cp_name
						   +"<td>"+ $originalCp_target +"</td>"//cp_target
						   +"<td>"+ $originalCp_code +"</td>"//cp_code
						   +"<td>"+ $originalCp_discount_value +"</td>"//cp_discount_value
						   +"<td>"+ $originalperiod +"</td>"//cp_period
						   +"<td>"+ $originalCp_startdate +"</td>"//cp_startdate
						   +"<td>"+ $originalCp_enddate +"</td>"//cp_enddate
						   +"<td>"+ $originalCp_min_price +"</td>"//cp_min_price
						   +"<td>"+ $originalCp_max_discount +"</td>"//cp_max_discount
						   +"<td>"+ $originalCp_state +"</td>"//쿠폰상태: 발급중/ 만료됨
						   +"<td><input type='button' value='수정' class ='btn btn-primary m-1 btn-sm' name='updateCpBtn'>"
						   +"<input type='button' value='삭제' class ='btn btn-primary m-1 btn-sm' name='deleteCpBtn'></td>"
				);
		
			});
			
			
				$name = $("#"+tr_id).children('td').eq(0).children('input');
				$btn =  $("input[name=updateComplete]")
				
				$name.on("change", function() {
				let regex = /^[가-힣a-zA-Z0-9_%]{2,30}$/;
					
					if(!regex.exec($name.val())){
			
						$name.next().html("<small>2 - 30자 이내, 한글,영어,특수문자(_%)</small>").css("color","red").css("magin","0");
						$btn.prop("disable", true);
					}else{
						$name.closest('div').html();
					}
				});
				
			$("input[name=updateComplete]").on("click",function () {//수정 창에서 쿠폰 수정 버튼 클릭시
				
			
			
				
				let coupon_name =  $("#"+tr_id).children('td').eq(0).children('input').val();
				let cp_period = $("#"+tr_id).children('td').eq(4).children('input').val();
				let cp_min_price = $("#"+tr_id).children('td').eq(7).children('input').val();
				let cp_max_discount = $("#"+tr_id).children('td').eq(8).children('input').val();
		
	
				
					$.ajax({//
							url:"CouponUpdate.ad",
							data:{
								"cp_code": cp_code,
								"cp_name": coupon_name,
								"isDelete":isDelete,//false
								"cp_period":cp_period,
								"cp_min_price": cp_min_price,
								"cp_max_discount": cp_max_discount
							},
							 dataType: "json" ,
							
							success: function(result){
							
								for(let cp of result){
// 									alert("수정 성공 !")
// 									alert(tr_id);
											//cp_current_st를 발급중/만료됨 문자열로 치환
												let statStr = "";
											if(cp.cp_current_st.equals == 1){
												statStr = "발급중";
											}else{
												if(cp.cp_target.equals =="event"){
													statStr ="만료됨";
												}else{
													statStr ="-";
													}
												}
									$("#"+tr_id).empty();
									$("#"+tr_id).html(
											"<td>"+cp.cp_name+"</td>"
											+"<td>"+cp.cp_target+"</td>"
											+"<td>"+cp.cp_code+"</td>"
											+"<td>"+cp.cp_discount_value+" %</td>"
											+"<td>"+cp.cp_period+"</td>"
											+"<td>"+cp.cp_startdate+"</td>"
											+"<td>"+cp.cp_enddate+"</td>"
											+"<td>"+cp.cp_min_price+"</td>"
											+"<td>"+cp.cp_max_discount+"</td>"
											+"<td>"+statStr+"</td>"
											+"<td>"
							 					+"<input type='button' value='수정 완료' class = 'btn btn-success btn-sm' name='updateCpBtn'>"
							 				+"</td>"
									);
								}
								if(result == false){//액션클래스에서 false리턴받을 경우
									alert("쿠폰 정보 수정에 실패했습니다.")
								}
							},
							error:  function(result){}
					});//$.ajax{()
						
				
			});
			


	});//$(function 
			
			
			
	//삭제버튼 클릭시
	$(document).on("click", "input[name=deleteCpBtn]", function(){
		
			let tr_id  = $(this).closest('tr').attr('id');// 버튼이 속한 tr태그의 id가져오기
			let cp_name =  $("#"+tr_id).children('td').eq(0).text(); //tr태그안의 td에서 첫번째(=쿠폰명)의 text가져오기
			let cp_code =  $("#"+tr_id).children('td').eq(2).text(); 
//	 		alert(cp_name + "/ " + cp_code);
			let isDelete = confirm("쿠폰을 삭제하시겠습니까? \n\n ※삭제될 쿠폰명 : "+ cp_name);
			
			if(isDelete){
				location.href = "CouponDelete.ad?cp_code="+cp_code+"&isDelete=" + isDelete;
			}
		
	});
		
		

</script>
</head>
<body>
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

 <!------------------------------------------------------------------------------------------------------------------- -->
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
 		<div class="m-3"> <h3>등록된 쿠폰 관리&#127903;</h3></div>
 		<hr>
 		
	 		<table class="table">
	 		  <thead class="">
		 		<tr>
<!-- 		 		<th>#</th> -->
	 				<th>쿠폰명</th>
	 				<th>발행대상</th>
	 				<th>쿠폰 코드</th>
	 				<th>할인율</th>
	 				<th>쿠폰 유효기간 (일)</th>
	 				<th>쿠폰 시작일</th>
	 				<th>쿠폰 만료일</th>
	 				<th>최소 적용 금액</th>
	 				<th>최대 할인액</th>
	 				<th>쿠폰 상태</th>
	 				<th>쿠폰 관리</th>
	 				
		 		</tr>
	 			</thead>
	
	 			
	 		<%  JSONArray  couponList = (JSONArray)request.getAttribute("CouponList");
// 	 					for(Object obj : couponList){
// 	 						JSONObject jo = (JSONObject)couponList;
							
						for(int i=0; i<couponList.length(); i++){
							JSONObject coupon = couponList.getJSONObject(i);%>
					
		 		<tr id="couponIdx_<%=i %>">
<%-- 		 			<td><%=i %><td> --%>
	 				<td><%=coupon.get("cp_name")%></td>
	 				<td><%=coupon.get("cp_target")%></td>
	 				<td><%=coupon.get("cp_code")%></td>
	 				<td><%=coupon.get("cp_discount_value")%> %</td>
	 				<td><%=coupon.get("cp_period")%></td>
	 				<td><%=coupon.get("cp_startdate")%></td>
	 				<td><%= coupon.get("cp_enddate") %></td>
	 				<td><%=coupon.get("cp_min_price")%></td>
	 				<td><%=coupon.get("cp_max_discount")%></td>
	 				<td><%if(coupon.get("cp_current_st").equals(1)){%>
	 					발급중
	 				<%}else{ 
	 					if(coupon.get("cp_target").equals("event")){%> 
	 					   만료됨
	 					<%}else{%>
						   - </td>
	 				<%} 
	 				} %>
	 				<td>
	 					<input type="button" value="수정" class = "btn btn-primary btn-sm m-1" name="updateCpBtn"> 
	 					<input type="button" value="삭제" class = "btn btn-primary btn-sm m-1" name="deleteCpBtn">
	 				</td>
		 		</tr>
	 				
	 			<%} //end of for()%>
	

	 		
	 		</table>
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