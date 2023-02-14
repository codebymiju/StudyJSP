package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.WishlistDetailService;
import vo.ActionForward;
import vo.cart_wish_proBean;

public class WishlistDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
//		request.setCharacterEncoding("UTF-8");
		
//		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sId");
		try {
			if(id == null || id.equals(null)){
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('회원만 접근 가능합니다!')");
				out.println("history.back()");
				out.println("</script>");
				
				return forward;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		WishlistDetailService service = new WishlistDetailService();
		List<cart_wish_proBean> wishlist = service.getWishlist(id);
		
		request.setAttribute("wishlist", wishlist);
		
		forward = new ActionForward();
		forward.setPath("cart/wishlist_detail_view.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
