package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberSelectService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberSelectAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("MemberSelectAction");
		
		ActionForward forward = null;
		
		// 세션
		HttpSession session = request.getSession();
		
		try {
			// 세션 아이디가 없거나 "" 일 경우 자바스크립트를 사용하여 돌려보내기
			if(session.getAttribute("sId") == null || session.getAttribute("sId").equals("")) {
				response.setContentType("text/html; charset=UTF-8");
				
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('로그인이 필요한 페이지입니다!')");
				out.println("location.href='MemberLoginForm.me'");
				out.println("</script>");
			} else { // 세션 아이디 있을 경우
				// MemberSelectService - selectMember() 으로 회원 목록 조회
				// => 파라미터 : 세션아이디    리턴타입 : List<MemberBean>(memberList)
				MemberSelectService service = new MemberSelectService();
				MemberBean member = service.selectMember(session.getAttribute("sId").toString());
//				System.out.println(memberList);
				
				// request 객체에 목록 저장
				request.setAttribute("member", member);
				
				// member/member_list.jsp 포워딩
				forward = new ActionForward();
				forward.setPath("member/member_info.jsp");
				forward.setRedirect(false); // 디스패치
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
