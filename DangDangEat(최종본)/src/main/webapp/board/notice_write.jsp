<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html lang="en">
    <head>
  <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
  <link href="css/styles.css" rel="stylesheet" /> 
  <style type="text/css">
	@font-face {
	    font-family: 'GmarketSansMedium';
	    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
	    font-weight: normal;
	    font-style: normal;
	}
	  
	#writeForm {
		width: 1224x;
		height: 500px;
		border: 1px;
		margin: auto;
	}
	
	h2 {
		font-family: 'GmarketSansMedium';
		text-align: center;
	}
	
	table {
	    border-collapse: collapse; 
	 	width: 1224px;
	   }
	
	
	.td_left {
/* 		width: 150px; */
		background: #D3D3D3;
		text-align: center;
	}
	
	.td_right {
/* 		width: 300px; */
		background: white;
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
	
	table > tbody {
		border-style: none;
	}
		
	
</style>
    </head>
   
		<jsp:include page="/inc/top.jsp"></jsp:include>
	
    <body>

	<!-- 게시판 등록 -->
	<section id="writeForm">
		<h2>Notice Write</h2>
		<form action="NoticeWritePro.bo" name="noticeForm" method="post">
			<table class="table container">
			<tbody>
<!-- 				<tr> -->
<!--  					<td class="td_left"><label for="member_id">Writer</label></td>  -->
<!-- 					<td class="td_right"><input type="text" name="member_id" required="required" /></td>  -->
<!-- 				</tr>				  -->
					
				<tr>
					<td class="td_left"><label for="notice_subject">제목</label></td>
					<td class="td_right"><input type="text" name="notice_subject" required="required" style="width: 1224px;"/></td>
				</tr>
				<tr>
					<td class="td_left"><label for="notice_content">내용</label></td>
					<td class="td_right">
<!-- 						<input type="textarea" id="notice_content" style="width: 500px"> -->
						<textarea id="notice_content" name="notice_content" required="required" style="width: 1224px; height: 500px;"></textarea>
					</td>
				</tr>	
			</table>			
			<section id="commandCell">
				<input type="submit" value="등록">&nbsp;&nbsp;
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