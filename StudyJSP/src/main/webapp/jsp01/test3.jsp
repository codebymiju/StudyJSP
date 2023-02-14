<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
// 이 곳은 JSP 선언문!
// 1. 멤버변수 선언
String str1 = "멤버 변수입니다."; // 인스턴스, 클래스 변수 상관 없음

// 2. 메서드 정의
public void method1() {
	System.out.println("선언문의 method1() 메서드 호출됨!");
}

// 2-2. 리턴값이 있는 메서드
public String method2() {
	return "method2() 메서드의 리턴값";
}
%>
<%! String str2 = "두번째 멤버 변수 입니다."; %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>test3.jsp</h1>
	<%--
	표현식 <%= %>
	 - 자바 코드와 System.out.print() 메서드와 동일한 문법 구조 사용
	 --%>
	 <h3>멤버변수 str1 = <%=str1 %></h3>
	 <h3>method2() 메서드 호출 결과 : <%=method2() %></h3>
	 
	 <%--
	 스크립틀릿 <% %>
	 `- 자바 문장을 그대로 표현 가능한 블럭
	  - 스크립틀릿 내에서 선언되는 변수는 로컬 변수로 취급되며
	    또한, 메서드는 정의할 수 없다!
	  --%>
	  
	  <%-- 스크립틀릿 내에서 선언된 로컬변수보다 위쪽에서 접근 불가 --%>
<%-- 	  <h3>로컬변수 str3 = <%=str3 %></h3> --%>
	  <%
	  // 이 곳은 스크립틀릿 내부입니다.
	  // 변수 선언도 가능하며, 해당 변수는 로컬 변수로 취급됨
	  String str3 = "로컬 변수입니다."; // 이 부분 이하에서 로컬변수에 접근 가능
	  
	  // 다른 메서드 호출하거나, 객체 생성 등의 다양한 작업이 가능함
	  method1();
	  
	  // 스크립틀릿 내에서는 메서드 정의 불가(= 자바의 메서드도 중첩 불가)
// 	  public void method3()	{} // 컴파일 에러 발생!
	  
	  System.out.println("이 문장은 이클립스 콘솔창에 출력됨!");
	  System.out.println(str3);
	  %>
	  
	  <%-- 로컬 변수는 반드시 변수 선언 위치보다 아래쪽에서만 접근 가능 --%>
	  <h3>로컬변수 str3 = <%=str3 %></h3>
	  
	  <%-- 멤버변수는 선언문보다 윗쪽에서 접근하더라도 접근 가느 --%>
	  <h3>멤버변수 str4(선언문보다 위) = <%=str4 %></h3>
	  
	  <%-- String 타입 멤버변수 str4 선언 및 "멤버변수 str4" 문자열 저장 --%>
	  <%! String str4 = "멤버변수 str"; %>
	  
	  <h3>멤버변수 str4(선언문보다 아래) = <%=str4 %></h3>
	  
</body>
</html>