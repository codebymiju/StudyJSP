package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.QnaDetailService;
import vo.ActionForward;
import vo.QnaBean;

public class QnaModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("QnaModifyAction");
		
		ActionForward forward = null;
		
		int qna_code = Integer.parseInt(request.getParameter("qna_code"));
		
		QnaDetailService service = new QnaDetailService();
		QnaBean qna = service.getQna(qna_code);

		request.setAttribute("qna", qna);
			
		forward = new ActionForward();
		forward.setPath("board/qna_modify.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}