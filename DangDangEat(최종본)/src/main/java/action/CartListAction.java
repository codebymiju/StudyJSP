package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.CartListService;
import vo.ActionForward;
import vo.cart_wish_proBean;

public class CartListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
//		request.setCharacterEncoding("UTF-8");
		
//		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sId");
		
		CartListService service = new CartListService();
		List<cart_wish_proBean> cartList = service.getCartList(id);
		
		request.setAttribute("cartList", cartList);
		System.out.println(cartList);
		forward = new ActionForward();
		forward.setPath("cart/cart_detail_view.jsp");
		forward.setRedirect(false);

		return forward;
		
	}

}
