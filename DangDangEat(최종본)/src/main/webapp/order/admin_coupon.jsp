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
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
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
</style>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">

//onsumit메서드 실행 시 체크할 항목 변수선언
let submitCheckRegex_name = false;
let submitCheckRegex_code = false;
let submitCheckCount_codeLength = false;

	$(function() {
		
		//쿠폰 자동 생성 radio버튼 클릭시 관련 내용 보이기
		$("#coupon_type2").on("change", function() {//쿠폰 자동 생성 버튼 클릭시
			
			$("#tr_coupon_code").after(
		 		"<tr id='coupon_target'>"+
	 				"<td>발행대상</td>"+
	 				"<td>"+
	 					"<select name='coupon_target'>"+
	 						"<option value='new_member'> 첫 회원가입 </option>"+
	 						"<option value='birth'> 댕댕이 생일 </option>"+
	 					"</select>"+
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
										
										submitCheckCount_codeLength = true;
										$("#code_check").html("<small>사용가능한 쿠폰 코드입니다.</small>").css("color", "royalblue").focus();
										$("input[type=submit]").attr("disabled", false);
									
									}//end of else
									
						},//success:
						fail: function(result) {
						
					
						}//fail:
					
					});//$.ajax({
			});
					
			
		$("form").submit(function(){
			
			
				if(submitCheckRegex_name && submitCheckRegex_code && submitCheckCount_codeLength){
					return true;
				}else{
					switch(true){
						case submitCheckRegex_name: $("#coupon_name").focus(); 
						case submitCheckRegex_code: $("#coupon_code").focus(); 
						case submitCheckCount_codeLength:$("#coupon_code").focus();
				
					}
				
					return false;
				}
		
		});



	});//$(function (){}

</script>
</head>
<body>

 	 <!-- top.jsp -->
         <jsp:include page="../inc/top.jsp"></jsp:include>
         
 	<div class="m-5">
 	&#128273;관리자 >
 	
<div class="dropdown d-inline">
  <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
    쿠폰
  </a>

  <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
    <li><a class="dropdown-item" href="CouponRegister.ad">쿠폰 등록</a></li>
    <li><a class="dropdown-item" href="CouponList.ad">등록된 쿠폰 관리</a></li>
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
</body>
</html>