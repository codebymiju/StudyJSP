package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberIdCheckService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberEmailCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("MemberEmailCheckAction");
		ActionForward forward = null;
		boolean isCheck = false;
		try {
			MemberBean member = new MemberBean();
			// 회원이 입력한 id 파라미터에서 전달받아 변수에 저장
			if(!request.getParameter("email").equals("") || request.getParameter("email") != null) {
				member.setMember_email(request.getParameter("email"));
				// MemberIdCheckService 객체 생성 후 isCheckId() 메서드 호출
				// 파라미터 : String(id) , 리턴값 : boolean(isCheck)
				MemberIdCheckService service = new MemberIdCheckService();
				isCheck = service.isCheckId(member);
			}
			if(isCheck) { // 아이디가 중복일때
				response.setContentType("text/html; charset=UTF-8");
				
				
				PrintWriter out = response.getWriter();
				
				out.print("true");
			} else { // 아이디가 사용 가능할때(중복아님)
				response.setContentType("text/html; charset=UTF-8");
				
				
				PrintWriter out = response.getWriter();
				
				out.print("false");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return forward;
	}

}
