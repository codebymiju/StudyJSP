package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import encrypt.MyMessageDigest;
import svc.MemberLoginProService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberLoginProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("MemberLoginProAction");
		
		ActionForward forward = null;
		
//		System.out.println(request.getParameter("id") + ", " + request.getParameter("pass"));
		
		try {
			// MemberBean 객체 생성 및 저장
			MemberBean member = new MemberBean();
			
			member.setMember_id(request.getParameter("id"));
//			member.setMember_pass(request.getParameter("pass"));
			
			// 패스워드 암호화(해싱)
			MyMessageDigest md = new MyMessageDigest("SHA-256");
			member.setMember_pass(md.hashing(request.getParameter("pass")));
//			System.out.println(member);
			
			// MemberLoginProService - loginMember()
			// 파라미터 : MemberBean 객체   리턴타입 : boolean(isLoginSuccess)
			MemberLoginProService service = new MemberLoginProService();
			boolean isLoginSuccess = service.loginMember(member, true); // 비밀번호 있음
			
			if(isLoginSuccess) { // 로그인 성공
				// 세션 객체에 id 저장
				HttpSession session = request.getSession();
				session.setAttribute("sId", member.getMember_id());
				System.out.println(session.getAttribute("sId"));
				
				// 메인페이지로 redirect
				forward = new ActionForward();
				forward.setPath("./");
				forward.setRedirect(true);
			} else { // 로그인 실패
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('아이디 혹은 비밀번호가 틀렸댕!')");
				out.println("history.back()");
				out.println("</script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return forward;
	}

}
