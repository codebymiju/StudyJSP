package ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberJoinProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		try {
			request.setCharacterEncoding("UTF-8");
			
			// 폼 파라미터 저장할 MemberBean 객체 생성
			MemberBean member = new MemberBean();
			member.setName(request.getParameter("name"));
			member.setGender(request.getParameter("gender"));
			// E-Mail 의 경우 email1, email2 두 개의 파라미터가 전달되므로 결합 필요
			member.setEmail(request.getParameter("email1") + '@' + request.getParameter("email2"));
			member.setId(request.getParameter("id"));
//			member.setPasswd(request.getParameter("passwd"));
//			System.out.println(member);
			// -----------------------------------------------------------------------------
			// 패스워드 암호화(해싱) 기능 추가
			// encrypt.MyMessageDigest 클래스 인스턴스 생성(파라미터로 해싱 알고리즘명 전달)
			MyMessageDigest md = new MyMessageDigest("SHA-256");
			// MyMessageDigest 객체의 hashing() 메서드를 호출하여 암호화 수행
			// => 리턴되는 암호문(해싱된 패스워드)을 MemberBean 객체에 저장
			member.setPasswd(md.hashing(request.getParameter("passwd")));
			// -----------------------------------------------------------------------------
			
			// MemberJoinProService - joinMember()
			// => 파라미터 : MemberBean 객체   리턴타입 : boolean(isJoinSuccess)
			MemberJoinProService service = new MemberJoinProService();
			boolean isJoinSuccess = service.joinMember(member);
			
			// 회원 가입 성공 여부 판별
			if(!isJoinSuccess) { // 실패 시(= 아이디 등의 중복)
				// 자바스크립트 사용하여 "회원 가입 실패!" 출력 후 이전페이지로 돌아가기
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('회원 가입 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				// ActionForward 객체를 사용하여 member_join_result.jsp 페이지로 이동
				forward = new ActionForward();
				// 만약, 현재 서블릿 주소 유지할 경우 - Dispatch
//				forward.setPath("member/member_join_result.jsp");
//				forward.setRedirect(false);
				
				// 만약, 현재 주소 대신 MemberJoinResult.me 서블릿 주소를 요청할 경우 - Redirect
				forward.setPath("MemberJoinResult.me");
				forward.setRedirect(true);
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return forward;
	}


}
