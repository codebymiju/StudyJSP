package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CommentListService;
import svc.ReviewDetailService;
import vo.ActionForward;
import vo.CommentBean;
import vo.ReviewBean;

public class ReviewDetailAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
      System.out.println("ReviewDetailAction");

      ActionForward forward = null;

      // 상세정보 조회에 필요한 글번호 가져오기
      int review_code = Integer.parseInt(request.getParameter("review_code"));
//      System.out.println("review_code = " + review_code);

      // ReviewDetailService 인스턴스 생성 후 getReview() 메서드 호출하여 게시물 조회 요청
      // => 파라미터 : 글번호, 조회수 증가 여부(true) 리턴타입 : ReviewBean(review)
      ReviewDetailService service = new ReviewDetailService();
      ReviewBean review = service.getReview(review_code, true);
//      System.out.println(review);

      // 뷰페이지로 데이터 전달을 위해 request 객체에 저장
      request.setAttribute("review", review);

      int startRow = 0; // 계산 생략
      int listLimit = 5;

      CommentListService a = new CommentListService();
      List<CommentBean> commentList = a.getCommentList(review_code, startRow, listLimit);

      // ----------------------------------------------------------------------
      request.setAttribute("commentList", commentList);

      forward = new ActionForward();
      forward.setPath("board/review_detail.jsp");
      forward.setRedirect(false);

      return forward;
   }

}