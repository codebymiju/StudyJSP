package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.OrderPaymentService;
import vo.ActionForward;
import vo.OrdersBean;
import vo.cart_productBean;
import vo.paymentsBean;

public class  implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("주문 결제를 진행하는 OrderPaymentProAction");
		ActionForward forward = null;
		
		// 결제 페이지 진행하는 액션 (주문번호 생성)
		
		/*
		 *  완료 1. 입력받은 쿠폰 코드를 가져와서 수식 계산을 하는(구문만 짜두기 연결은 별도)
		 *  
		 *  완료 2. payment(결제) 테이블로 정보를 입력 
		 *  	pay_number : varchar(20)
		 *      order_code : int 
		 *      pay_amount : int 
		 *   
		 *  완료 3. orders 테이블에 order_status = 1로 변경 시키기 
		 *  완료 4. 주문 확인서 페이지로 이동
		 *  완료 5. cart에 있는 상품 cart_ischecked 업데이트 해서 카트 페이지에서 삭제하기 
		 *  
		 *  23/01/06 완료 부분
		 *  완료 6. 주문완료한 수량 product 수량에서 (-)변경하기   
		 *  
		 *  23/01/07 진행 부분
		 *  완료 1. 주문 확인서 페이지에 넘길 정보 생성하기 (주문상품 + 배송정보 + 주문 금액 관련 정보)
		 *  완료 2. 쿠폰 페이지 연결하기
		 *  3. 아임포트 api 연결하기
		 *  
		 *  23/01/08 진행 
		 *  완료 1. 쿠폰 코드 + 할인금액 포함해서 payments 빈 생성하기
		 *  
		 *  23/01/09 진행
		 *  완료 1. 쿠폰 팝업(자식창)에서 쿠폰 코드 가져오기
		 *  완료 2. action과 연결 시켜서 할인 금액 리턴 및 연산 작업 수행
		 *  완료 / 문제 발생 3. 쿠폰코드와 할인금액 payments 테이블에 저장하기
		 *  4. 쿠폰 사용시 mc_used (member_coupon) n > y로 변경하기
		 *  
		 *  23/01/10 진행
		 *  완료 1. 결제 완료시 order_product, cart 삭제하기 (같은 카트번호 사용)
		 *  완료 2. 쿠폰 사용시 mc_used (member_coupon) n > y로 변경하기
		 *  
		 */
		
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sId");
		int pro_code = Integer.parseInt(request.getParameter("pro_code"));
		int cart_code = Integer.parseInt(request.getParameter("cart_code"));
		int pro_amount = Integer.parseInt(request.getParameter("pro_amount"));
		int order_code = Integer.parseInt(request.getParameter("order_code"));
		
		// 23/01/10 발생한 문제점
		// 쿠폰을 사용하지 않고는 결제 불가능
		
		String cp_code = request.getParameter("cp_code");
		String cp_discount = request.getParameter("cp_discount_amount");
		int cp_discount_amount = Integer.parseInt(request.getParameter("cp_discount_amount"));
		
		if(cp_code.equals(null) || cp_discount.equals(null) ) {
			cp_code = "none";
			cp_discount_amount = 0;
		}
		System.out.println("상품 코드 잘넘어 옵니까" + pro_code);
		System.out.println("카트 코드 잘넘어 옵니까" + cart_code);
		System.out.println("상품금액 잘넘어 옵니까" + pro_amount);
		System.out.println("주문번호 잘넘어 옵니까" + order_code);
		System.out.println("쿠폰번호 잘넘어 옵니까" + cp_code);
		System.out.println("할인금액 잘넘어 옵니까" + cp_discount_amount);
		
		// 주문번호 생성을 위한 구문------------------------------------------
		
			// 현재 날짜 구하기
	        LocalDate now = LocalDate.now();
	        // 포맷 정의 (230107 + 주문번호)
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd00");
	        int formatedNow = Integer.parseInt(now.format(formatter));
	        int pay_number = (formatedNow + order_code);
	        // 결과 출력
	        System.out.println(pay_number);  // 23010500
	        
	     //------------------------------------------------------------------
	        
		paymentsBean payments = new paymentsBean();
		payments.setPay_number(pay_number); // 최종 주문번호 
		payments.setPay_amount(pro_amount); // 결제금액
		payments.setOrder_code(order_code); // orders 테이블 주문번호
		payments.setCp_code(cp_code);
		payments.setCp_discount_amount(cp_discount_amount);
		
		OrderPaymentService service = new OrderPaymentService();
		// orders 테이블에 order_status = 1 (결제완료) 로 변환하는 작업
		boolean isOrderStatusUpdate = service.orderStatusUpdate(id, order_code);
		// payments 테이블에 결제정보 입력하는 작업
		int paymentInsertCount = service.paymentInsertPro(payments);
		// 결제 정보 리스트 생성 구문
		List<paymentsBean> paymentList = service.getPaymentsList(pay_number, order_code);
		request.setAttribute("paymentList", paymentList);
		System.out.println("페이먼트 프로 액션 결제 정보 리스트" + paymentList);
		// 결제 상품 정보 리스트 생성 구문
		List<cart_productBean> orderProductList = service.getOrderProductList(id, pro_code, cart_code);
		request.setAttribute("orderProductList", orderProductList);
		System.out.println("페이먼트 프로 액션 주문 상품 리스트" + orderProductList);
		// 배송 정보 및 주문 정보 리스트 생성 구문
		List<OrdersBean> orderInfoList = service.getOrderInfoList(id, cart_code);
		request.setAttribute("orderInfoList", orderInfoList);
		System.out.println("페이먼트 프로 액션 주문 정보 리스트" + orderInfoList);
		// 카트 중복 해결하는 방법 > 결제 완료 후 order_product와 cart 삭제
		int deleteCartCount = service.deleteCartPro(cart_code);
		// 장바구니 화면에서 제거(cart테이블 cart_ischecked)
		int cartUpdateCount = service.cartInfoUpdate(id, cart_code);
		// 결제완료시 상품 테이블에서 수량변경하는 코드
		int productQtyUpdateCount = service.productQtyUpdate(order_code, pro_code);
		// 사용한 쿠폰 mc_used = 'N' > 'Y'
		int couponUpdateCount = service.getCouponUpdateCount(cp_code, id);
		
		
		
			try {
				// orders 테이블에 order_status = 1 (결제완료) 로 변환하는 작업
				if(isOrderStatusUpdate) {
					// 결제 후 장바구니 보이지 않도록 수정
//					 if(cartUpdateCount > 0) {
							// 상품 수량 수정
//							 if(productQtyUpdateCount > 0) {
								 // 결제 정보 입력
								 if(paymentInsertCount > 0 ) {
									 // 결제 완료 카트 삭제 (23/01/10 코드 꼬여서 실패)
									 if(deleteCartCount > 0) {
										 // cp_target(new_member, event)면 쿠폰 사용여부를 N > Y로 바꿈
										 if( couponUpdateCount >= 0) {
												 forward = new ActionForward();
												 forward.setPath("OrderConfirm.od");
												 forward.setRedirect(false);
											 } else {
											 	response.setContentType("text/html; charset=UTF-8");
												PrintWriter out = response.getWriter();
												out.println("<script>");
												out.println("alert('쿠폰 사용여부 업데이트 실패')");
												out.println("history.back()");
												out.println("</script>");
											 }
										 } else {
												response.setContentType("text/html; charset=UTF-8");
												PrintWriter out = response.getWriter();
												out.println("<script>");
												out.println("alert('중복카트 삭제 실패')");
												out.println("history.back()");
												out.println("</script>");
												
										 } // deleteCartCount
									 
									} else {
										response.setContentType("text/html; charset=UTF-8");
										PrintWriter out = response.getWriter();
										out.println("<script>");
										out.println("alert('결제정보 입력 실패')");
										out.println("history.back()");
										out.println("</script>");
									} // paymentInsertCount
//							 	} else {
//									response.setContentType("text/html; charset=UTF-8");
//									PrintWriter out = response.getWriter();
//									out.println("<script>");
//									out.println("alert('상품 수량 업데이트 실패')");
//									out.println("history.back()");
//									out.println("</script>");
								 
//							 } // productQtyUpdateCount
						 
						
//					 } else {
//						response.setContentType("text/html; charset=UTF-8");
//						PrintWriter out = response.getWriter();
//						
//						out.println("<script>");
//						out.println("alert('장바구니 업데이트 실패')");
//						out.println("history.back()");
//						out.println("</script>");
//					 } // cartUpdateCount
				 
				} else {
					response.setContentType("text/html; charset=UTF-8");
//				
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('주문상태 업데이트 실패')");
					out.println("history.back()");
					out.println("</script>");
				} //isOrderStatusUpdate
				
			} catch (IOException e) {
				System.out.println("OrderPaymentProAction 오류 발생");
				e.printStackTrace();
			} 
		
		
		return forward;
	}

}
