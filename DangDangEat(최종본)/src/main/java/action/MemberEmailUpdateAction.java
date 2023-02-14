package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberEmailUpdateService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberEmailUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("MemberEmailUpdateAction");
		
		ActionForward forward = null;
		
		try {
			// MemberBean 객체 생성 및 파라미터 저장
			MemberBean member = new MemberBean();
			member.setMember_id(request.getParameter("id"));
			member.setMember_email(request.getParameter("email"));		
			System.out.println(member);
			
			// MemberEmailUpdateService 객체 생성 및 memberEmailUpdate() 호출
			MemberEmailUpdateService service = new MemberEmailUpdateService();
			boolean isUpdateSuccess = service.memberEmailUpdate(member);
			
			if(isUpdateSuccess) { // 메일 변경 성공 시
				
				forward = new ActionForward();
				forward.setPath("MemberInfo.me");
				forward.setRedirect(false);
				
			} else { // 실패 시
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('이메일을 다시 입력해주시개')");
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
