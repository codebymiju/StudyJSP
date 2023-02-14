package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.OrderListService;
import vo.ActionForward;
import vo.MemberBean;
import vo.cart_productBean;

public class OrderFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("orderFormAction 도착!");
		
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sId");
		int proCode = Integer.parseInt(request.getParameter("pro_code"));
		int cartCode = Integer.parseInt(request.getParameter("cart_code"));
		
		OrderListService service = new OrderListService();
		
		// 주문회원 정보 조회
		List<MemberBean> memberList = service.selectMemberList(id);
		request.setAttribute("memberList", memberList);
		
		// 장바구니 정보 조회
		List<cart_productBean> cartList = service.getViewCartList(id, proCode, cartCode);
		request.setAttribute("cartList", cartList);
		
		forward = new ActionForward();
		forward.setPath("order/orderForm.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
