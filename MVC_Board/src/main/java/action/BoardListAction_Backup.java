package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public class BoardListAction_Backup {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		
			forward = new ActionForward();
			forward.setPath("board/qna_board_list.jsp");
			forward.setRedirect(false); // boolean 기본값 false니까 생략가능한 코드
		
		
		return forward;
		
	} // main()
} // class
