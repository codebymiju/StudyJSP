package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.CartInsertService;
import vo.ActionForward;
import vo.CartBean;

public class CartInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		try {
			
			int pro_code = Integer.parseInt(request.getParameter("pro_code"));
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("sId");
	//		System.out.println(id);
	//		String id = request.getParameter("id");
			int cart_amount = Integer.parseInt(request.getParameter("amount"));
			System.out.println(cart_amount);
	//		String name = request.getParameter("pro_name");
			String path = request.getParameter("path");
//			System.out.println(pro_code + ", " + id + ", "+ cart_amount);
//			System.out.println(id);
			if(id == null || id.equals(null)) {
				System.out.println(id);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('로그인을 먼저 해주세요')");
				out.println("history.back()");
				out.println("</script>");
				
//				return forward;
			} else {
				
				// 아이디, 제품코드, 수량 받아오기
				CartBean cart = new CartBean();
				cart.setMember_id(id);
				cart.setPro_code(pro_code);
				if(cart_amount == 1) {
					cart.setCart_amount(1);
				} else {
					cart.setCart_amount(cart_amount);
				}
				cart.setCart_ischecked(1);
			//		cart.setCart_wishlist(0);
//					System.out.println(cart + "액션");
				CartInsertService service = new CartInsertService();
				int insertCount = service.insertCart(cart);
//				System.out.println("insertCount = " + insertCount);
				if(insertCount == 1) { // 장바구니 추가 성공
					// ajax 는 경로를 줄 필요가 없음
					if(path.equals("product_detail")) { // 경로 상품상세
//						forward = new ActionForward();
//						forward.setPath("ProductDetail.pd?pro_code=" + pro_code);
//						forward.setRedirect(true);
						response.setContentType("text/html; charset=UTF-8");
						PrintWriter out = response.getWriter();
//						out.println("<script>");
						out.println("장바구니에 상품이 담겼습니다!");
//						out.println("history.back()");
//						out.println("</script>");
					} else if(path.equals("wishlist")) { // 경로 위시리스트
						forward = new ActionForward();
						forward.setPath("CartList.ct");
						forward.setRedirect(true);
					} else if(path.equals("product_list")) { // 경로 상품 목록
						forward = new ActionForward();
						forward.setPath("ProductList.pd");
						forward.setRedirect(true);
					}
//					request.setAttribute("result1", 1);
//					response.setContentType("text/html; charset=UTF-8");
//					PrintWriter out = response.getWriter();
//					out.println("<script>");
//					out.println("장바구니에 상품이 담겼습니다!");
//					out.println("history.back()");
//					out.println("</script>");
				} else if(insertCount == 2){ 
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
//					out.println("<script>");
					out.println("장바구니에 있는 상품입니다!");
//					out.println("history.back()");
//					out.println("</script>");
				} else { // 추가 실패
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('장바구니 추가 실패!')");
					out.println("history.back()");
					out.println("</script>");
				}
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
