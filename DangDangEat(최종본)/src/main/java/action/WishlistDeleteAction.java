package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.WishlistDeleteService;
import vo.ActionForward;
import vo.CartBean;
import vo.cart_wish_proBean;

public class WishlistDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		int pro_code = Integer.parseInt(request.getParameter("pro_code"));
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sId");
		CartBean cart = new CartBean();
		cart.setPro_code(pro_code);
		cart.setMember_id(id);
		
		try {
			WishlistDeleteService service = new WishlistDeleteService();
			// 위시리스트 유무 조회
			boolean isWishlistExist = service.isExist(cart, true);
			
			if(!isWishlistExist){ // 위시리스트에 없으면
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제할 상품이 없습니다!')");
				out.println("history.back()");
				out.println("</script>");
			} else { // 위시리스트에 있으면
				// 장바구니에 있는지 조회
				boolean isCartExist = service.isExist(cart, false);
				if(!isCartExist) { // 장바구니에 없으면 delete
					boolean isDeleteSuccess = service.removeWishlist(cart);
					
					if(!isDeleteSuccess) { // 삭제 실패
						response.setContentType("text/html; charset=UTF-8");
						PrintWriter out = response.getWriter();
						out.println("<script>");
						out.println("alert('삭제 실패!')");
						out.println("history.back()");
						out.println("</script>");
					} else { // 삭제 성공
						forward = new ActionForward();
						forward.setPath("CartList.ct");
						forward.setRedirect(true);
					}
				} else { // 장바구니에 있으면 update
					int updateCount = service.updateWishlist(pro_code);
					if(updateCount > 0) { // 성공
						forward = new ActionForward();
						forward.setPath("CartList.ct");
						forward.setRedirect(true);
					} else { //실패
						response.setContentType("text/html; charset=UTF-8");
						PrintWriter out = response.getWriter();
						out.println("<script>");
						out.println("alert('삭제 실패!')");
						out.println("history.back()");
						out.println("</script>");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
