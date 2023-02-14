package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ReviewDetailService;
import vo.ActionForward;
import vo.ReviewBean;

public class ReviewModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("ReviewModifyFormAction");
		
		ActionForward forward = null;
		
		int review_code = Integer.parseInt(request.getParameter("review_code"));
		ReviewDetailService service = new ReviewDetailService();
		ReviewBean review = service.getReview(review_code, false);
		
		// 뷰페이지로 데이터 전달을 위해 request 객체에 저장
		request.setAttribute("review", review);
		forward = new ActionForward();
		forward.setPath("board/review_modify.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
