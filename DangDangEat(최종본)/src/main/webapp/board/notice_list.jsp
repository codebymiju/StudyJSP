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
	  
	body {
	    font-family:"GmarketSansMedium" ;
	}
	
	#listForm {
 		width: 1224px; 
        max-height: 610px;
        margin: auto;
	}
	
	h2 {
	    font-family:"GmarketSansMedium" ;
		text-align: center;
	}
	
	table {
		margin: auto;
		width: 1024px;
	}
	
	#tr_top {
		background: #D3D3D3;
		text-align: center;
	}
	
	table td {
		text-align: center;
	}
	
	#subject {
		text-align: left;
		padding-left: 20px;
	}
	
	#pageList {
		margin-bottom: 30px;
/* 		width: 1024px; */
		text-align: center;
	}
	
	#emptyArea {
		margin: auto;
		width: 1024px;
		text-align: center;
	}
	
	#buttonArea {
		margin: auto;
		width: 1024px;
		text-align: right;
		margin-top: 10px;
		margin-bottom: 30px;
	}
	
	a {
		text-decoration: none;
	}
		input[type=button], input[type=submit], input[type=text] {
	    font-family:"GmarketSansMedium" ;
	    border-radius: 0px;
	}
	
	</style>
	
    </head>
       
    <body>
       
    <!-- Header-->
        <!-- top.jsp -->
		<jsp:include page="/inc/top.jsp"></jsp:include>
		<!-- Section-->
		
  <section id="listForm">
  <h2>Notice List</h2>
	<table class="table container">
	<tr id="tr_top">
      <th width="100">No</th>
      <td width="400px">Subject</td>
      <td width="150px">Writer</td>
      <td width="150px">Date</td>
      <td width="100px">Hit</td>
    </tr>	
		<%-- for(NoticeBean notice : noticeList) {} --%>
		<c:forEach var="notice" items="${noticeList }">
			<tr>
				<td>${notice.notice_code }</td>
				<!-- 제목 하이퍼링크(NoticeDetail.bo) 연결 -> 파라미터 : 글번호, 페이지번호 -->
				<c:choose>
					<c:when test="${empty param.pageNum }">
						<c:set var="pageNum" value="1" />
					</c:when>
					<c:otherwise>
						<c:set var="pageNum" value="${param.pageNum }" />
					</c:otherwise>
				</c:choose>
				<td id="subject">
					<a href="NoticeDetail.bo?notice_code=${notice.notice_code }&pageNum=${pageNum }">
						${notice.notice_subject }
					</a>
				</td>
				<td>${notice.member_id }</td>
				<td>
					<fmt:formatDate value="${notice.notice_date }" pattern="yy-MM-dd"/>
				</td>	
				<td>${notice.notice_readcount }</td>
			</tr>
		</c:forEach>

    </tbody>
	</table>
	</section>

<section id="buttonArea">
<form action="NoticeList.bo">
			<input type="text" name="keyword">
			<input type="submit"  value="검색">
			&nbsp;&nbsp;
			<c:if test="${not empty sessionScope.sId and sessionScope.sId eq 'admin'}">
            <input type="button" value="글쓰기" onclick="location.href='NoticeWriteForm.bo'" />   
            </c:if>     
		</form>
	</section>


<section id="pageList">
		<c:choose>
			<c:when test="${pageNum > 1}">
				<input type="button" value="이전" onclick="location.href='NoticeList.bo?pageNum=${pageNum - 1}'">
			</c:when>
			<c:otherwise>
				<input type="button" value="이전">
			</c:otherwise>
		</c:choose>
			
		<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }">
			<c:choose>
				<c:when test="${pageNum eq i}">
					${i }
				</c:when>
				<c:otherwise>
					<a href="NoticeList.bo?pageNum=${i }">${i }</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<c:choose>
			<c:when test="${pageNum < pageInfo.maxPage}">
				<input type="button" value="다음" onclick="location.href='NoticeList.bo?pageNum=${pageNum + 1}'">
			</c:when>
			<c:otherwise>
				<input type="button" value="다음">
			</c:otherwise>
		</c:choose>
	</section>
       
 <!-- Footer-->
   <footer class="py-5 bg-dark">
      <div class="container">
         <p class="m-0 text-center text-white">Copyright &copy; DangDangEat 2023</p>
      </div>
   </footer>