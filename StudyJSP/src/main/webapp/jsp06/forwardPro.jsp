<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>forwardPro.jsp</h1>
	<%
	// 이전 페이지에서 POST 방식으로 전달받은 파라미터 중 한글 데이터가 포함될 경우 깨지므로
	// request.setCharacterEncoding("UTF-8"); 코드를 사용하여 한글 처리 필요
	// 또한, jsp:forward 액션태그의 jsp:param 태그를 통해 파라미터 전달 시에도
	// 전달할 데이터를 지정하는 시점에 한글 처리가 되어있어야한다!
	// => 데이터를 처리하는 forwardPro2.jsp 가 아닌 데이터를 생성하는 forwardPro.jsp 페이지에서
	//    한글 처리를 수행해야한다
	// => Dispatch 방식의 포워딩으로 인해 request 객체가 유지되므로
	//    request 객체를 통해 설정한 한글 처리(인코딩) 방식도 그대로 유지됨
	request.setCharacterEncoding("UTF-8"); 
	
	// 폼 파라미터로 전달받은 데이터 (아이디, 패스워드) 가져와서 변수에 저장 후 출력
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	
	// input type="hidden" 속성으로 전달받은 파라미터("jumin")도 
    // 동일한 방식으로 가져올 수 있음
	String jumin = request.getParameter("jumin");
	
	// forward 액션태그에 포함시켜 전달할 데이터를 변수에 저장
	int age = 25;
	%>
	<h3>아이디 : <%=id %></h3>
	<h3>패스워드 : <%=passwd %></h3>
	<h3>주민번호 : <%=jumin %></h3>
	<!-- ========================================================== -->
	<!-- forward 액션태그를 사용하여 "forwardPro2.jsp" 페이지로 포워딩 작업 수행 -->
<%-- 	<jsp:forward page="forwardPro2.jsp"/> --%>
    
     <!-- 한글 처리할 때 이걸 작성하는 페이지에서 처리 -->
	<jsp:forward page="forwardPro2.jsp">
		<jsp:param name="paramValue1" value="forward 액션태그의 param 태그 데이터"/>
		<jsp:param name="paramValue2" value="<%=age %>"/>
	</jsp:forward>
</body>
</html>