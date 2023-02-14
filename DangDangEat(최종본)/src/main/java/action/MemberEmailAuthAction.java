package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import encrypt.MyMessageDigest;
import mail.GoogleMailAuthenticator;
import svc.MemberMailAuthService;
import vo.ActionForward;
import vo.AuthBean;
import vo.MemberBean;

public class MemberEmailAuthAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("MemberEmailAuthAction");
		
		ActionForward forward = null;
		
		try {
			// MemberBean 객체 생성 및 파라미터 저장
			MemberBean member = new MemberBean();
			member.setMember_id(request.getParameter("id"));
			member.setMember_email(request.getParameter("email"));
			
			// 이메일 인증시 필요한 데이터 저장할 AuthBean 객체 생성 및 데이터 저장
			AuthBean auth = new AuthBean();
			auth.setAuth_id(request.getParameter("id"));
			// 해싱 암호화 기능
			MyMessageDigest md = new MyMessageDigest("SHA-256");
			auth.setAuth_code(md.hashing(request.getParameter("email")));
			
			// DB에 등록 성공(회원가입 성공시) 인증 이메일 발송 절차 실행 //
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			String host = "http://localhost:8080/DangDangEat/";
			String from = "this.team.pj@gmail.com";
			String to = member.getMember_email();
			String subject = "DangDangEat 이메일 인증 메일";
			String content = "링크에 접속해 이메일 인증을 완료해주세요."
					+ "<a href='" + host + "MailCheck.me?auth_id=" + auth.getAuth_id()
					+ "&auth_code=" + auth.getAuth_code() + "'>이메일 인증해주개!</a>";

			// SMTP 이용하기 위한 세팅 
			Properties properties = System.getProperties();
			//SMTP 서버 주소 (구글,네이버,아웃룩 등)
			properties.put("mail.smtp.host", "smtp.gmail.com"); // 구글(GMail)SMTP 서버 주소
			properties.put("mail.smtp.auth", "true"); // SMTP 서버에 대한 인증 여부 설정
			properties.put("mail.smtp.port", "587"); // SMTP 서비스 포트 설정

			// 메일 인증 관련 정보 설정
			properties.put("mail.smtp.starttls.enable", "true"); // TLS 인증 프로토콜 사용 여부 설정
			properties.put("mail.smtp.ssl.protocols", "TLSv1.2"); // 인증 프로토콜 버전 설정


			Authenticator authenticator = new GoogleMailAuthenticator();
			Session mailSession = Session.getDefaultInstance(properties, authenticator);

			// 5. 서버 정보와 인증 정보를 포함하는 javax.mail.MimeMessage 객체 생성
			Message mailMessage = new MimeMessage(mailSession);

			// 6. 전송할 메일에 대한 정보 설정
			// 1) 발신자 정보 설정을 위한 객체 생성
			Address senderAddress = new InternetAddress(from, "댕댕잇");

			// 2) 수신자 정보 설정을 위한 객체 생성
			Address receiverAddress = new InternetAddress(to);

			// 3) Message 객체를 통해 전송할 메일에 대한 내용 정보 설정
			// 3-1) 메일 헤더 정보 설정
			mailMessage.setHeader("content-type", "text/html; charset=UTF-8");
			// 3-2) 발신자 정보 설정
			mailMessage.setFrom(senderAddress);
			// 3-3) 수신자 정보 설정
			mailMessage.addRecipient(RecipientType.TO, receiverAddress);
			// 3-4) 메일 제목 설정
			mailMessage.setSubject(subject);
			// 3-5) 메일 본문 설정
			mailMessage.setContent(content, "text/html; charset=UTF-8");

			// 7. 메일 전송
			Transport.send(mailMessage);

			MemberMailAuthService mas = new MemberMailAuthService();
			boolean mailSuccess = mas.mailauth(auth);


			if(mailSuccess) { // 메일 발송 성공시
				response.setContentType("text/html; charset=UTF-8");
				out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('인증 메일이 발송되었댕')");
				out.println("location.reload()");
				out.println("</script>");

			} else { // 메일 발송 실패시
				response.setContentType("text/html; charset=UTF-8");
				out = response.getWriter();

				out.println("<script>");
				out.println("alert('인증메일 발송 실패')");
				out.println("history.back()");
				out.println("</script>");
			}
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return forward;
	}

}
