<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="css/styles.css" rel="stylesheet" /> 
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

#passForm {
	width: 300px;
	margin: auto;
	border: 1px #D3D3D3;
	text-align: center;
	margin-bottom : 100px;
}

h2 {
	font-family: "GmarketSansMedium";
	text-align: center;
}

table {
	width: 300px;
	margin: auto;
	text-align: center;
	border-collapse: separate;
    border-spacing: 0 50px
}
#buttonArea {
	margin: auto;
	width: 1024px;
	text-align: right;
	margin-top: 10px;
}
input[type=button], input[type=submit], input[type=password] {
	border-radius: 0px;
	font-family: "GmarketSansMedium";
}
</style>
</head>
<body>

	<jsp:include page="/inc/top.jsp"></jsp:include>
	
	<h2>Q & A Delete</h2>
	<section id="passForm">
		<form action="QnaDeletePro.bo" name="deleteForm" method="post">
			<input type="hidden" name="qna_code" value="${param.qna_code }" >
			<input type="hidden" name="pageNum" value="${param.pageNum }" >
			<table>
				<tr>
					<td><label>PASSWORD&nbsp;&nbsp;</label></td>
					<td><input type="password" name="qna_pass" required="required"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="삭제">&nbsp;&nbsp;
						<input type="button" value="돌아가기" onclick="javascript:history.back()">
					</td>
				</tr>
			</table>
		</form>
	</section>
   <!-- Footer-->
   <footer class="py-5 bg-dark">
      <div class="container">
         <p class="m-0 text-center text-white">Copyright &copy; DangDangEat 2023</p>
      </div>
   </footer>