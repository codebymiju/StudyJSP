package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardDetailService;
import vo.ActionForward;
import vo.BoardBean;

public class BoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("BoardDetailAction");
		
		ActionForward forward = null;
		
		// 상세정보 조회에 필요한 글번호 조회
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		System.out.println("board_num : " + board_num);
		
		// 12/05   01
		// 12/07 2-(1). isUpdateReadcount() 파라미터값 추가
		BoardDetailService service = new BoardDetailService();
		BoardBean board =  service.getBoard(board_num, true);
//		System.out.println(board);
		
		// 12/05   02
		// view 페이지로 데이터 전달을 위해 request 객체에 저장
		request.setAttribute("board", board);
		
		forward = new ActionForward();
		forward.setPath("board/qna_board_view.jsp");
		forward.setRedirect(false); // dispatcher 방식
		
		return forward;
	}

}
