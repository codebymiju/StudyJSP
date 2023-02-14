<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html lang="en">
    <head>
  <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
  <style type="text/css">
	@font-face {
	    font-family: 'GmarketSansMedium';
	    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
	    font-weight: normal;
	    font-style: normal;
	}
	  body {
	    font-family:"GmarketSansMedium" ;
	}		  
	
	h2 {
		font-family:"GmarketSansMedium" ;
		text-align: center;
	}
		
	.td_left {
		width: 150px; 
		background: #D3D3D3;
		text-align: center;
	}
	
	input, textarea {
	    font-family:"GmarketSansMedium" ;
	    border-radius: 0px;
	}		
		
	.td_right {
 		width: 300px; 
	}
	textarea {
		resize: none;
	}
	
	#commandCell {
		text-align: center;
		margin-bottom: 30px;
	}
	input, textarea {
	    font-family:"GmarketSansMedium" ;
	    border-radius: 0px;
	}
	
		input[type=button], input[type=submit], input[type=reset], input[type=text] {
	    font-family:"GmarketSansMedium" ;
	}	
	
	
</style>
</head>

	<jsp:include page="/inc/top.jsp"></jsp:include>

<body>	
	<!-- 리뷰 글 수정 -->
	
		<h2>Review Modify</h2>
		<form action="ReviewModifyPro.bo" name="reviewForm" method="post" enctype="multipart/form-data">
			<!-- 입력받지 않은 글번호, 페이지번호 hidden 속성으로 전달 -->
			<input type="hidden" name="review_code" value="${param.review_code }" >
			<input type="hidden" name="pageNum" value="${param.pageNum }" >
			<!-- 파일 수정 시 기존 파일 삭제를 위해 실제 파일명도 파라미터로 전달 필요 -->
			<input type="hidden" name="review_real_file" value="${review.review_real_file }" >
			<table class="table container">
				<tr>
					<td class="td_left"><label for="member_id">작성자</label></td>
					<td class="td_right"><input type="text" name="member_id" value="${review.member_id }" readonly="readonly"></td>
				</tr>
				<tr>
					<td class="td_left"><label for="review_pass">비밀번호</label></td>
					<td class="td_right"><input type="password" name="review_pass" required="required"></td>
				</tr>
				<tr>
					<td class="td_left"><label for="review_subject">제목</label></td>
					<td class="td_right"><input type="text" name="review_subject" value="${review.review_subject }" required="required"></td>
				</tr>
				<tr>
					<td class="td_left"><label for="review_content">내용</label></td>
					<td class="td_right">
						<textarea id="review_content" name="review_content" cols="40" rows="15" required="required">${review.review_content }</textarea>
					</td>
				</tr>
				<tr>
					<td class="td_left"><label for="review_file">file</label></td>
					<!-- 파일 수정할 경우에만 선택하도록 required 속성 제거 -->
					<td class="td_right">
						<input type="file" name="review_file">
						<br>(기존 파일 : ${review.review_file })
					</td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="수정">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기">&nbsp;&nbsp;
				<input type="button" value="취소" onclick="history.back()">
			</section>
		</form>
 	  <!-- Footer-->
   <footer class="py-5 bg-dark">
      <div class="container">
         <p class="m-0 text-center text-white">Copyright &copy; DangDangEat 2023</p>
      </div>
   </footer>