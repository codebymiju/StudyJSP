package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberSelectService;
import svc.MyPageService;
import vo.ActionForward;
import vo.MemberBean;

public class MyPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("MyPageAction");
		
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
				// 아이디 저장
				String id = session.getAttribute("sId").toString();
				
				// 회원 정보 조회
				MemberSelectService service = new MemberSelectService();
				MemberBean member = service.selectMember(id);
//			System.out.println(memberList);
				
				// request 객체에 목록 저장
				request.setAttribute("member", member);
				
				// 회원 주문 건수, 쿠폰 수, 리뷰 수, Q&A 수 조회
				MyPageService service2 = new MyPageService();
				int memberOrderCount = service2.selectMemberOrder(id);
				int memberCouponCount = service2.selectMemberCoupon(id);
				int memberReviewCount = service2.selectMemberReview(id);
				int memberQnaCount = service2.selectMemberQna(id);
				System.out.println(
						"주문 수 : " + memberOrderCount
						+ "쿠폰 수 : " + memberCouponCount
						+ "리뷰 수 : " + memberReviewCount
						+ "질문 수 : " + memberQnaCount
						);
				
				// request 객체에 값 저장
				request.setAttribute("order_count", memberOrderCount);
				request.setAttribute("coupon_count", memberCouponCount);
				request.setAttribute("review_count", memberReviewCount);
				request.setAttribute("qna_count", memberQnaCount);
				
				// member/mypage.jsp 포워딩
				forward = new ActionForward();
				forward.setPath("member/mypage.jsp");
				forward.setRedirect(false); // 디스패치
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return forward;
	}

}
