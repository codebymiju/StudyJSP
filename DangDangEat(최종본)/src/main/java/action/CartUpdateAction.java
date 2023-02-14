package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.CartUpdateService;
import vo.ActionForward;
import vo.CartBean;

public class CartUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		int pro_code = Integer.parseInt(request.getParameter("pro_code"));
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sId");
		int cart_amount = Integer.parseInt(request.getParameter("amount"));
		System.out.println(pro_code + id + cart_amount);
		CartBean cart = new CartBean();
		cart.setPro_code(pro_code);
		cart.setMember_id(id);
		cart.setCart_amount(cart_amount);
		
		CartUpdateService service = new CartUpdateService();
		int updateCount = service.updateCart(cart);
		
		if(updateCount > 0) {
			forward = new ActionForward();
			forward.setPath("CartList.ct");
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
