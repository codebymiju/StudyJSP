package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.WishlistInsertService;
import vo.ActionForward;
import vo.CartBean;

public class WishlistInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		int pro_code = Integer.parseInt(request.getParameter("pro_code"));
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sId");
//		String id = request.getParameter("id");
		
		try {
			if(id == null || id.equals(null)) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('로그인을 먼저 해주세요')");
				out.println("history.back()");
				out.println("</script>");
				
				return forward;
			}

			// 아이디, 제품코드, 수량 받아오기
			CartBean cart = new CartBean();
			cart.setMember_id(id);
			cart.setPro_code(pro_code);
			cart.setCart_amount(1);
			cart.setCart_wishlist(1);
			
			WishlistInsertService service = new WishlistInsertService();
			int insertCount = service.insertWishlist(cart);
			
				if(insertCount == 1) { // 위시리스트 추가 성공
					
					forward = new ActionForward();
					forward.setPath("ProductDetail.pd?pro_code=" + pro_code);
					forward.setRedirect(true);
					
					return forward;
				} else if(insertCount == 2){ // 이미 있음
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('위시리스트에 있는 상품입니다!')");
					out.println("history.back()");
					out.println("</script>");
					
					return forward;
				} else { // 추가 실패
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('위시리스트 추가 실패!')");
					out.println("history.back()");
					out.println("</script>");
					
					return forward;
				}
	} catch (IOException e) {
		e.printStackTrace();
	}
		
		return forward;
	}

}
