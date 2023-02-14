package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.OrderCouponProService;
import vo.ActionForward;

public class OrderCouponProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// 쿠폰코드를 이용하여 할인금액을 계산하는 페이지
		System.out.println("쿠폰코드가 넘어오면 나오는 OrderCouponProAction");
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sId");
		int cart_code = Integer.parseInt(request.getParameter("cart_code"));
		int order_code = Integer.parseInt(request.getParameter("order_code"));
		String cp_code = request.getParameter("cp_code");
		int pro_amount = Integer.parseInt(request.getParameter("pro_amount"));
		
		System.out.println("쿠폰 입력시 쿠폰 코드 잘넘어 옵니까" + cp_code);
		System.out.println("쿠폰 입력시 카트 코드 잘넘어 옵니까" + cart_code);
		System.out.println("쿠폰 입력시 상품금액 잘넘어 옵니까" + pro_amount);
		System.out.println("쿠폰 입력시 주문번호 잘넘어 옵니까" + order_code);
		
		OrderCouponProService service = new OrderCouponProService();
		int cp_discount_amount = service.getCouponDiscountAmount(cart_code, cp_code);
		System.out.println("할인 금액 : " + cp_discount_amount);
		request.setAttribute("cp_discount_amount", cp_discount_amount);
		
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(cp_discount_amount);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
