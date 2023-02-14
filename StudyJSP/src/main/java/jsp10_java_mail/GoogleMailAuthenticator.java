package jsp10_java_mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

//자바 메일 기능 사용 시 메일 서버(ex. Gmail 등) 인증을 위한 인증 정보를 관리하기 위한
//javax.mail.Authenticator 클래스를 상속받는 서브클래스 정의
public class GoogleMailAuthenticator extends Authenticator {
	// 인증 정보(아이디, 패스워드)를 관리할 javax.mail.PasswordAuthentication 클래스 타입 변수 선언
	private PasswordAuthentication passwordAuthentication;

	// 기본 생성자 정의
	public GoogleMailAuthenticator() {
		// 인증에 사용할 아이디와 패스워드 정보를 갖는 PasswordAuthentication 객체 생성
		// => 파라미터 : 메일 서버 계정명, 패스워드
		passwordAuthentication = new PasswordAuthentication("miju.kim.kr", "mvmuwmcuenoitriu");
	}

	// 인증 정보를 관리하는 PasswordAuthentication 객체를 외부로 리턴하는 
	// getPasswordAuthentication() 메서드 정의	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return passwordAuthentication;
	} 
	
}
