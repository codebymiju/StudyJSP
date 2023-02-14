package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MailCheckService;
import svc.MemberAuthUpdateService;
import vo.ActionForward;
import vo.AuthBean;
import vo.MemberBean;

public class MailCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("MailCheckAction");
		ActionForward forward = null;
		
		try {
			request.setCharacterEncoding("UTF-8");
			// AuthBean 객체 생성하고 파리미터값 저장
			AuthBean auth = new AuthBean();
			auth.setAuth_id(request.getParameter("auth_id"));
			auth.setAuth_code(request.getParameter("auth_code"));
//			System.out.println(auth_id + " / " + auth_code);
			
			// MemberBean 객체 생성하고 추후 업데이트를 위해 id값 저장
			MemberBean member = new MemberBean();
			member.setMember_id(request.getParameter("auth_id"));
			
			// MailCheckService 객체 생성하고 mailAuthCheck() 메서드 호출
			// 파라미터 : AuthBean(auth), 리턴값 : boolean(isSuccess)
			MailCheckService service = new MailCheckService();
			boolean isSuccess = service.mailAuthCheck(auth);
			
			if(isSuccess) { // mailAuthCheck 성공 시
				member.setMember_authStatus("Y"); // member_authStatus 값 변경
				
				// MemberAuthUpdateService 객체 생성 memberAuthUpdate() 메서드 호출
				// 파라미터 : MemberBean(member), 리턴값 : boolean(isAuthSuccess)
				MemberAuthUpdateService aus = new MemberAuthUpdateService();
				boolean isAuthSuccess = aus.memberAuthUpdate(member);
				
				if(isAuthSuccess) { // memberAuthUpdate() 성공시 MemberInfo.me 포워딩
					forward = new ActionForward();
					forward.setPath("MemberInfo.me");
					forward.setRedirect(true);
				} else {
					response.setContentType("text/html; charset=UTF-8");
					
					PrintWriter out = response.getWriter();
					
					out.println("<script>");
					out.println("alert('이미 만료된 링크입니다.')");
					out.println("location.href='http://localhost:8080/DangDangEat/'");
					out.println("</script>");
				}
			} else {
				response.setContentType("text/html; charset=UTF-8");
				
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('이미 만료된 링크입니다.')");
				out.println("location.href='http://localhost:8080/DangDangEat/'");
				out.println("</script>");
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		return forward;
	}

}
