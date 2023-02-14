package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import encrypt.MyMessageDigest;
import svc.MemberCheckProService;
import svc.MemberLoginProService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberCheckProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("MemberCheckProAction");
		
		ActionForward forward = null;
		
		try {
			HttpSession session = request.getSession(); // 세션

			response.setContentType("text/html; charset=UTF-8"); // 스크립트 전송
			PrintWriter out = response.getWriter();

			// 세션 아이디가 없거나 ""일 경우 알림 메세지
			if(session.getAttribute("sId") == null || session.getAttribute("sId").equals("")) {
				out.println("<script>");
				out.println("alert('로그인이 필요한 페이지입니다.')");
				out.println("history.back()");
				out.println("</script>");
			}
			
			// MemberBean 객체 생성 및 저장
			MemberBean member = new MemberBean();
			
			// 세션 아이디, 파라미터 패스워드 저장
			member.setMember_id(session.getAttribute("sId").toString());
//			member.setMember_pass(request.getParameter("pass"));
			
			// 패스워드 암호화(해싱)
			MyMessageDigest md = new MyMessageDigest("SHA-256");
			member.setMember_pass(md.hashing(request.getParameter("pass")));
//			System.out.println(member);
			
			// MemberCheckProService - CheckRightPass()
			// 파라미터 : MemberBean 객체   리턴타입 : boolean(isRightPass)
			MemberCheckProService service = new MemberCheckProService();
			boolean isRightPass = service.CheckRightPass(member, true); // 비밀번호 있음
			
			if(isRightPass) { // 비밀번호 맞음
				
				// MemberInfo.me로 redirect
				forward = new ActionForward();
				forward.setPath("MemberInfo.me");
				forward.setRedirect(true);
				
			} else { // 비밀번호 틀림
				
				out.println("<script>");
				out.println("alert('비밀번호가 틀렸댕!')");
				out.println("history.back()");
				out.println("</script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
