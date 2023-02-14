package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ReviewDetailService;
import vo.ActionForward;
import vo.ReviewBean;

public class ReviewReplyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		int review_code = Integer.parseInt(request.getParameter("review_code"));
		
		// ReviewDetailService 클래스의 인스턴스 생성 및 getReview() 메서드를 호출하여
		// 글 상세정보 조회 작업 요청
		// => 파라미터 : 글번호, 조회수 증가 여부(false)   리턴타입 : ReviewBean(review)
		ReviewDetailService service = new ReviewDetailService();
		ReviewBean review = service.getReview(review_code, false);
//		System.out.println(review);
		
		// 뷰페이지로 데이터 전달을 위해 request 객체에 저장
		request.setAttribute("review", review);
		
		forward = new ActionForward();
		forward.setPath("board/review_reply.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}