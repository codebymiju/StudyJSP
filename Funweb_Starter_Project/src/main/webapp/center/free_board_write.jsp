<%@page import="board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>center/free_board_write.jsp</title>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/subpage.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="wrap">
		<!-- 헤더 들어가는곳 -->
		<jsp:include page="../inc/top.jsp" />
		<!-- 헤더 들어가는곳 -->

		<!-- 본문들어가는 곳 -->
		<!-- 본문 메인 이미지 -->
		<div id="sub_img_center"></div>
		<!-- 왼쪽 메뉴 -->
		<jsp:include page="../inc/left.jsp"></jsp:include>
		<!-- 본문 내용 -->
		<article>
			<h1>Free Board Write</h1>
				<!-- 
			파일 업로드 기능을 위해서는 form 태그에 enctype="multipart/form-data" 속성 추가 필수!
			=> 속성 미지정 시 모든 데이터는 문자열로 전송되므로 파일도 파일명만 전송됨
			=> 주의1) 반드시 cos.jar 라이브러리 등록 필수!
			=> 주의2) 파라미터 가져오는 request.getParameter() 메서드 사용 불가!
			          (MultipartRequest 객체의 getParameter() 메서드로 대체)
			-->
			<form action="free_board_writePro.jsp" method="post" enctype="multipart/form-data">
				<table id="notice">
					<tr>
						<td>글쓴이</td>
						<td><input type="text" name="name" required="required" ></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="pass" required="required" ></td>
					</tr>
					<tr>
						<td>제목</td>
						<td><input type="text" name="subject" required="required" ></td>
					</tr>
					<tr>
						<td>내용</td>
						<td><textarea rows="10" cols="20" name="content" required="required" ></textarea></td>
					</tr>
					<!-- 파일 업로드를 위한 입력폼 추가 -->
					<tr>
						<td>파일</td>
						<td><input type="file" name="original_file" required="required" ></td>
					</tr>
				</table>

				<div id="table_search">
					<input type="submit" value="글쓰기" class="btn" >
					<input type="button" value="취소" class="btn" onclick="history.back()">
				</div>
			</form>
			<div class="clear"></div>
		</article>


		<div class="clear"></div>
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="../inc/bottom.jsp" />
		<!-- 푸터 들어가는곳 -->
	</div>
</body>
</html>


