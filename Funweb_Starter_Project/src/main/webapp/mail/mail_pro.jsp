<%@page import="javax.mail.Transport"%>
<%@page import="java.util.Date"%>
<%@page import="javax.mail.Message.RecipientType"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.Address"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.Session"%>
<%@page import="mail.GoogleMailAuthenticator"%>
<%@page import="java.util.Properties"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// mail_form.jsp 페이지로부터 전달받은 파라미터 가져와서 확인
request.setCharacterEncoding("UTF-8");

String sender = request.getParameter("sender");
String receiver = "miju.kim.kr@gmail.com";
String title = request.getParameter("title");
String content = request.getParameter("content");
// out.println(sender +", "+ receiver +", "+ title +", "+ content);
String sId = request.getParameter("sId");
System.out.println(sId);

	content = "<h4>메일 발송 회원 id : " + sId + "</h4>";

try {
//-------------------메일 전송을 위한 설정 작업---------------------------
// 메일 전송 프로토콜 : SMTP(Simple Mail Transfer Protocol)
// 1. 시스템의 속성정보를 java.util.Properties 객체로 리턴받기
Properties properties = System.getProperties();
// 2. Properties 객체 활용하여 메일 전송에 필요한 기본 설정 정보를 서버 속성 정보에 추가
// 메일 전송에 사용할 메일 서버 지정(아래는 구글)
properties.put("mail.smtp.host", "smtp.gmail.com"); // SMTP 서버 주소
properties.put("mail.smtp.auth", "true");  // SMTP 서버에 대한 인증 여부 설정
properties.put("mail.smtp.port", "587");   // SMTP 서비스 포트 설정 (구글 : 587)
// 메일 인증 관련 정보 설정 (아래 두개는 한세트)
properties.put("mail.smtp.starttls.enable", "true"); // TLS 인증 프로토콜 사용 여부 설정
properties.put("mail.smtp.ssl.protocols", "TLSv1.2"); // 인증 프로토콜 버전 설정

// 3. 메일 서버 인증 정보를 생성하는 사용자 정의 클래스 인스턴스 생성
GoogleMailAuthenticator authenticator = new GoogleMailAuthenticator();

// 4. 자바 메일 전송을 수행하는 작업의 기본단위를 관리하는 Session 객체 얻어오기
// 파라미터 : properties 객체, Authenticator 객체
Session mailSession = Session.getDefaultInstance(properties, authenticator);

// 5. 서버 정보와 인증 정보를 포함하는 MimeMessage 객체 생성
Message mailMessage = new MimeMessage(mailSession); 

// 6. 전송할 메일에 대한 정보 설정
// 1) 발신자 정보 설정 InternetAddress 객체 생성 (파라미터 : 발신자 주소, 이름)
Address senderAddress = new InternetAddress(sender, "아이티윌");
// 2) 수신자 정보 설정을 위한 InternetAddress 객체 생성 (파라미터 : 수신자 주소)
Address receiverAddress = new InternetAddress(receiver);
// 3) Message 객체를 통해 전송할 메일에 대한 내용 정보 설정
// 3-1) 메일 헤더 정보 설정
mailMessage.setHeader("content-type", "text/html; charset=UTF-8");
// 3-2) 발신자 정보 설정
mailMessage.setFrom(senderAddress);
// 3-3) 수신자 정보 설정 (addRecipient() 사용하여 수신자 정보 설정)
mailMessage.addRecipient(RecipientType.TO, receiverAddress);
// 3-4) 메일 제목 설정
mailMessage.setSubject(title);
// 3-5) 메일 본문 설정
mailMessage.setContent(content, "text/html; charset=UTF-8");
// 3-6) 메일 전송 날짜 및 시각 정보 설정
mailMessage.setSentDate(new Date());

// 7. 메일 전송
Transport.send(mailMessage);%>
		<script>
		alert("메일이 정상적으로 발송되었습니다!");
		history.back();
		</script>
<%} catch(Exception e) {
	e.printStackTrace();
	out.println("<h3>SMTP 서버 설정 또는 서비스 문제 발생!</h3>");
}

%>