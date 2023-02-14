package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberUpdateService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("MemberUpdateAction");
		
		ActionForward forward = null;
		
		try {
			// MemberBean 생성 및 파라미터 저장
			MemberBean member = new MemberBean();
			member.setMember_id(request.getParameter("id"));
			member.setMember_status(request.getParameter("status"));
			
			System.out.println(member);
			
			// 서비스 객체 생성 및 메서드 호출
			MemberUpdateService service = new MemberUpdateService();
			boolean isUpdateSuccess = service.updateMember(member);
			
			if(isUpdateSuccess) {
				
				forward = new ActionForward();
				forward.setPath("AdminMemberList.ad");
				forward.setRedirect(true);
				
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('회원 정보 수정 실패')");
				out.println("</script>");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return forward;
	}

}
