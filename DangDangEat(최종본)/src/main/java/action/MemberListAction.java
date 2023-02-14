package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberListService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("MemberListAction");
		
		ActionForward forward = null;
		
		MemberListService service = new MemberListService();
		List<MemberBean> memberList = service.getMemberList();
		
		request.setAttribute("memberList", memberList);
		
		forward = new ActionForward();
		forward.setPath("admin/admin_member_list.jsp");
		forward.setRedirect(false); // 디스패치
		
		return forward;
	}

}
