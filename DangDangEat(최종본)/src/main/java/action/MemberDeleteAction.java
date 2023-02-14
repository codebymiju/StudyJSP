package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberDeleteService;
import vo.ActionForward;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		// id 파라미터 가져오기
		String id = request.getParameter("id");
		System.out.println(id);
		
		try {
			// MemberDeleteService 객체 호출
			MemberDeleteService service = new MemberDeleteService();
			boolean isDeleteSuccess = service.deleteMember(id);
			
			if(isDeleteSuccess) { // 삭제 성공 시
				// MemberList.ad 로 리다이렉트
				forward = new ActionForward();
				forward.setPath("MemberList.ad");
				forward.setRedirect(true);
			} else { // 삭제 실패 시
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('회원 삭제 실패!')");
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
