<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
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
 
</style>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>

<script type="text/javascript">

	
$(function () {
	$("input[name=deleteCpBtn]" ).on("click", function () {
		let tr_id  = $(this).closest('tr').attr('id');// 버튼이 속한 tr태그의 id가져오기
		let cp_name =  $("#"+tr_id).children('td').eq(0).text(); //tr태그안의 td에서 첫번째(=쿠폰명)의 text가져오기
		let cp_code =  $("#"+tr_id).children('td').eq(2).text(); 
// 		alert(cp_name + "/ " + cp_code);
		let isDelete = confirm("쿠폰을 삭제하시겠습니까? \n\n ※삭제될 쿠폰명 : "+ cp_name);
		
		if(isDelete){
			location.href = "CouponDelete.ad?cp_code="+cp_code+"&isDelete=" + isDelete;
		}
	});
	
	$("input[name=updateCpBtn]" ).on("click", function () {
		let tr_id  = $(this).closest('tr').attr('id');// 버튼이 속한 tr태그의 id가져오기
		let cp_name =  $("#"+tr_id).children('td').eq(0).text(); //tr태그안의 td에서 첫번째(=쿠폰명)의 text가져오기
		let cp_code =  $("#"+tr_id).children('td').eq(2).text(); 
// 		alert(cp_name + "/ " + cp_code);

		let isDelete = false;
		
	 $originalName =  $("#"+tr_id).children('td').eq(0).text();
	 $originalperiod = $("#"+tr_id).children('td').eq(4).text()
	 $originalMin =  $("#"+tr_id).children('td').eq(7).text();
	 $originalMax =  $("#"+tr_id).children('td').eq(8).text();
	 $originalEnddate =  $("#"+tr_id).children('td').eq(9).text();
	 


	
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
							   +"<td><input type='button' value='수정' class ='btn btn-success btn-sm' name='updateComplete'>"
							   +" <input type='button' value='취소' class ='btn btn-danger btn-sm' name='cancelUpdate'></td>"
					);
			
			
			$("input[name=cancelUpdate]" ).on("click", function () {//수정 창에서 취소버튼 클릭시
				
			
				$("#"+tr_id).html( //기존내용으로 바꾸기
							"<td>"+ $originalName +"</td>"//cp_name
						   +"<td>"+$("#"+tr_id).children('td').eq(1).text() +"</td>"//cp_target
						   +"<td>"+$("#"+tr_id).children('td').eq(2).text()+"</td>"//cp_code
						   +"<td>"+$("#"+tr_id).children('td').eq(3).text()+"</td>"//cp_discount_value
						   +"<td>"+$originalperiod+"</td>"//cp_period
						   +"<td>"+$("#"+tr_id).children('td').eq(5).text()+"</td>"//cp_startdate
						   +"<td>"+$("#"+tr_id).children('td').eq(6).text()+"</td>"//cp_enddate
						   +"<td>"+  $originalMin  +"</td>"//
						   +"<td>"+ $originalMax +"</td>"//
						   +"<td>"+ $originalEnddate+"</td>"//cp_enddate
						   +"<td><input type='button' value='수정' class ='btn btn-primary btn-sm' name='updateComplete'>"
						   +" <input type='button' value='취소' class ='btn btn-primary btn-sm' name='cancelUpdate'></td>"
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
				
			$("input[name=updateComplete]" ).on("click",function () {//수정 창에서 쿠폰 수정 버튼 클릭시
				
			
			
				
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
									alert("수정 성공 !")
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
			

	});
	});//$(function 
		
		

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
 		<div class="m-3"> <h3>등록된 쿠폰 관리&#127903;</h3> </div>
 		<hr>
 		
	 		<table class="table">
	 		  <thead class="thead-dark">
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
	 					<input type="button" value="수정" class = "btn btn-primary btn-sm" name="updateCpBtn"> 
	 					<input type="button" value="삭제" class = "btn btn-primary btn-sm" name="deleteCpBtn">
	 				</td>
		 		</tr>
	 				
	 			<%} //end of for()%>
	

	 		
	 		</table>
	 		</div>
	 	
	

</body>
</html>