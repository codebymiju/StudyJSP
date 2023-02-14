package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.QnaDetailService;
import vo.ActionForward;
import vo.QnaBean;

public class QnaDetailAction implements Action {


	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("QnaDetailAction");
		
		ActionForward forward = null;
		
		// 상세정보 조회에 필요한 글번호 가져오기
		int qna_code = Integer.parseInt(request.getParameter("qna_code"));
		
//		System.out.println("qna_code = " + qna_code);
		QnaDetailService service = new QnaDetailService();
		QnaBean qna = service.getQna(qna_code);
//		System.out.println(qna);
		
		// 뷰페이지로 데이터 전달을 위해 request 객체에 저장
		request.setAttribute("qna", qna);
		
		forward = new ActionForward();
		forward.setPath("board/qna_detail.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
}