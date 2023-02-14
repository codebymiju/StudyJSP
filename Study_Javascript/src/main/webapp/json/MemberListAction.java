package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberListService;
import vo.ActionForward;
import vo.BoardBean;
import vo.MemberBean;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		// 12/12
		// 세션 아이디가 null 이거나 "admin" 이 아닐 경우 자바스크립트를 사용하여 돌려보내기
	try {
			HttpSession session = request.getSession();
			if(session.getAttribute("sId") == null 
					|| !session.getAttribute("sId").equals("admin")) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('잘못된 접근입니다!')");
				out.println("history.back()");
				out.println("</script>");
			
			} else { // 관리자일 경우 (목록조회 및 포워딩)
				
				MemberListService service = new MemberListService();
				List<MemberBean> memberList = service.getMemberList();
				
				request.setAttribute("memberList", memberList);
				
				forward = new ActionForward();
				forward.setPath("member/member_list.jsp");
				forward.setRedirect(false);
				
			}
		
		}  catch (IOException e) {
			e.printStackTrace();
		}
	
		return forward;
	}

}
