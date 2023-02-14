package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import encrypt.MyMessageDigest;
import svc.MemberFindIdProService;
import svc.MemberLoginProService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberFindIdProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("MemberFindIdProAction");
		
		ActionForward forward = null;
		
//		System.out.println(request.getParameter("id") + ", " + request.getParameter("pass"));
		
		try {
			
			// MemberBean 객체 생성 및 저장
			MemberBean unfindedMember = new MemberBean();
			
			unfindedMember.setMember_name(request.getParameter("name"));
			unfindedMember.setMember_email(request.getParameter("email"));
			System.out.println(unfindedMember);
			
			// MemberFindIdProService - findMemberId()
			// 파라미터 : MemberBean 객체   리턴타입 : MemberBean 객체
			MemberFindIdProService service = new MemberFindIdProService();
			MemberBean findedMember = new MemberBean();
			findedMember = service.findMemberId(unfindedMember);
			
			if(findedMember != null) { // 아이디 있음
				// request 객체에 id 저장
				request.setAttribute("member", findedMember);
				
				// 아이디 안내 페이지로 redirect
				forward = new ActionForward();
				forward.setPath("member/find_id_result.jsp");
				forward.setRedirect(false);
			} else { // 아이디 없음
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('입력하신 정보로 가입 된 회원 아이디는 존재하지 않는댕!')");
				out.println("history.back()");
				out.println("</script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return forward;
	}

}
