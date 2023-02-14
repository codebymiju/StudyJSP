package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import encrypt.MyMessageDigest;
import mail.GoogleMailAuthenticator;
import svc.MemberCheckProService;
import svc.MemberFindPassProService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberFindPassProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("MemberFindPassProAction");
		
		ActionForward forward = null;
		
		try {
			// MemberBean 객체에 폼 파라미터 저장
			MemberBean member = new MemberBean();
			member.setMember_id(request.getParameter("id"));
			member.setMember_name(request.getParameter("name"));
			member.setMember_email(request.getParameter("email"));
			System.out.println(member);
			
			// 아이디와 이메일이 일치하는 컬럼이 있는지 (회원인지) 확인
			MemberCheckProService service = new MemberCheckProService();
			boolean isRightMember = service.CheckRightId(member, false); // 패스워드 없음
			
			if(isRightMember) { // 회원일 경우
				// 임시 비밀번호 발급
				// 인증코드에 사용될 문자를 배열로 모두 저장
				char[] codeTable = {
						'A', 'B', 'C', 'D', 'C', 'F', 'G', 'H', 'I', 'J', 
						'K', 'L', 'N', 'M', 'O', 'P', 'Q', 'R', 'S', 'T', 
						'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 
						'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 
						'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 
						'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', 
						'8', '9', '!', '@', '$'
				};
				
				Random r = new Random();
				String randomPass = "";
				int codeLength = 10;
				
				// 원하는 코드 길이만큼 for문을 사용하여 반복문으로 처리
				for(int i = 1; i <= codeLength; i++) {
					// 배열 크기를 난수의 범위로 지정하여 난수 생성
					int index = r.nextInt(codeTable.length);
					
					// 생성된 난수를 배열 인덱스로 활용하여 1개의 코드 가져와서 문자열 결합
					randomPass += codeTable[index];
					
				}
				
				System.out.println("임시 비밀번호 : " + randomPass);
				
				// 임시 비밀번호로 변경
				// 패스워드 암호화(해싱)
				MyMessageDigest md = new MyMessageDigest("SHA-256");
				member.setMember_pass(md.hashing(randomPass));
				System.out.println(member);
				
				MemberFindPassProService service2 = new MemberFindPassProService();
				boolean isUpdateSuccess = service2.updatePass(member);
				
				if(isUpdateSuccess) { // 임시 비밀번호 DB 적용 후
					// 임시 비밀번호 메일 전송
					String title = "댕댕잇 임시 비밀번호입니다.";
					String content = "<b>" + request.getParameter("name") + "</b> 님의 비밀번호가 요청되었습니다.<br>"
							+ "회원 아이디 : " + request.getParameter("id") + "<br>"
							+ "임시 비밀번호 : <strong>" + randomPass + "</strong><br>"
							+ "비밀번호는 정보관리시스템에서 매우 중요하게 사용되므로 안전하게 관리하시기 바랍니다.<br>"
							+ "해당 비밀번호로 로그인 후 비밀번호 재설정하세요.<br>"
							+ "<font color='#ff0000'>본 메일은 발신 전용이므로 회신이 되지 않습니다.</font><br>"
							+ "<a href='http://localhost:8080/DangDangEat/MemberLoginForm.me'>댕댕잇 로그인</a>";
					
					try {
						// 1. 시스템(서버 = 톰캣)의 속성 정보(= 서버 정보)
						Properties properties = System.getProperties();
						// 2. Properties 객체를 활용하여 메일 전송에 필요한 기본 설정 정보를 서버 속성 정보에 추가
						properties.put("mail.smtp.host", "smtp.gmail.com"); // 구글(GMail) SMTP 서버 주소
						properties.put("mail.smtp.auth", "true"); // SMTP 서버에 대한 인증 여부 설정
						properties.put("mail.smtp.port", "587"); // SMTP 서비스 포트 설정
						// 메일 인증 관련 정보 설정
						properties.put("mail.smtp.starttls.enable", "true"); // TLS 인증 프로토콜 사용 여부 설정
						properties.put("mail.smtp.ssl.protocols", "TLSv1.2"); // 인증 프로토콜 버전 설정
						
						// 3. 메일 서버 인증 정보를 생성하는 사용자 정의 클래스 인스턴스 생성
						Authenticator authenticator = new GoogleMailAuthenticator();
						
						// 4. Session 객체 얻어오기
						Session mailSession = Session.getDefaultInstance(properties, authenticator);
						
						// 5. 서버 정보와 인증 정보를 포함하는 javax.mail.MimeMessage 객체 생성
						// => 파라미터 : javax.mail.Session 객체 전달
						// => 생성된 MimeMessage 객체를 통해 전송할 메일에 대한 정보 생성
						Message mailMessage = new MimeMessage(mailSession);
						
						// 6. 전송할 메일에 대한 정보 설정
						// 1) 발신자 정보 설정
						Address senderAddress = new InternetAddress("this.team.pj@gmail.com", "댕댕잇");
						// 2) 수신자 정보 설정
						Address receiverAddress = new InternetAddress(request.getParameter("email"));
						// 3) Message 객체를 통해 전송할 메일에 대한 내용 정보 설정
						// 3-1) 메일 헤더 정보 설정
						mailMessage.setHeader("content-type", "text/html; charset=UTF-8");
						// 3-2) 발신자 정보 설정
						mailMessage.setFrom(senderAddress);
						// 3-3) 수신자 정보 설정
						// => addRecipient() 메서드를 사용하여 수신자 정보 설정
						//    파라미터 : 수신 타입(기본 수신자이므로 TO 상수 활용), 수신자 정보 객체
						mailMessage.addRecipient(RecipientType.TO, receiverAddress);
						// 3-4) 메일 제목 설정
						mailMessage.setSubject(title);
						// 3-5) 메일 본문 설정
						mailMessage.setContent(content, "text/html; charset=UTF-8");
						// 3-6) 메일 전송 날짜 및 시각 정보 설정
						//      => java.util.Date 객체 생성을 통해 시스템 시각 정보 사용
						mailMessage.setSentDate(new Date());
						
						// 7. 메일 전송
						Transport.send(mailMessage);
						
						response.setContentType("text/html; charset=UTF-8");
						PrintWriter out = response.getWriter();
						
						out.println("<script>");
						out.println("alert('임시 비밀번호가 발송댕!')");
						out.println("location.href='MemberLoginForm.me'");
						out.println("</script>");
					} catch (AddressException e) {
						System.out.println("이메일 주소 문제");
						e.printStackTrace();
					} catch (MessagingException e) {
						System.out.println("뭔가 잘못됨..");
						e.printStackTrace();
					}
					
				}
				
				
			} else { // 회원이 아닐 경우
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('입력하신 정보로 가입 된 회원 아이디는 존재하지 않는댕!')");
				out.println("history.back()");
				out.println("</script>");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return forward;
	}

}
