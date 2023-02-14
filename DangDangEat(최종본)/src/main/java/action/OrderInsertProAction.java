package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.OrderInsertService;
import vo.ActionForward;
import vo.MemberBean;
import vo.Order_productBean;
import vo.OrdersBean;
import vo.cart_productBean;

public class OrderInsertProAction implements Action {

	// 주문페이지에서 받은 정보를 orders 테이블에 입력하는 작업 
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("orderInsertFormAction 들어왔다");
		ActionForward forward = null;
		
		// 세션에 저장된 회원 아이디 변수에 저장
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sId");
		int pro_code = Integer.parseInt(request.getParameter("pro_code"));
		int cart_code = Integer.parseInt(request.getParameter("cart_code"));
 
		try {
			request.setCharacterEncoding("UTF-8");
			
			// orders 테이블
			OrdersBean orders = new OrdersBean();
			orders.setMember_id(id);
			orders.setOrder_name(request.getParameter("shipment_name"));
			orders.setOrder_postcode(request.getParameter("shipment_zipcode"));
			orders.setOrder_address1(request.getParameter("shipment_address1"));
			orders.setOrder_address2(request.getParameter("shipment_address2"));
			orders.setOrder_mobile(request.getParameter("shipment_mobile"));
			orders.setOrder_comment(request.getParameter("message"));
			
			// order_product 테이블
			Order_productBean order_product = new Order_productBean();
			order_product.setPro_code(pro_code);
			order_product.setCart_code(cart_code);
			
			// 주문자 정보 & 주문상품 등록 구문 (orders & order_product table)
			OrderInsertService service = new OrderInsertService();
			boolean isInsertSuccess = service.insertOrder(orders, order_product, cart_code);
			
			// 주문자 정보 리스트 (출력용)
			List<OrdersBean> orderMemberList = service.getOrderMemberList(id, cart_code);
			request.setAttribute("orderMemberList", orderMemberList);
			
			// 주문상품 정보 리스트 (출력용)
			List<cart_productBean> orderProductList = service.getOrderProductList(id, cart_code);
			request.setAttribute("orderProductList", orderProductList);
			
			if(isInsertSuccess) { // 주문정보(주문자 정보 & 주문상품) 등록 성공
				
				forward = new ActionForward();
				forward.setPath("OrderPaymentForm.od");
				forward.setRedirect(false);
				
			} else {
				response.setContentType("text/html; charset=UTF-8");
				
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('주문정보 등록 실패')");
				out.println("history.back()");
				out.println("</script>");
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return forward;
	}

}
