package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.CouponSearchAction;
import action.OrderCouponProAction;
import action.OrderFormAction;
import action.OrderInsertProAction;
import action.OrderPaymentProAction;
import action.SearchExistMemberCouponAction;
import vo.ActionForward;

@WebServlet("*.od")
public class OrderFrontController extends HttpServlet {
	
	// GET or POST 방식 요청을 공통으로 처리할 doProcess() 메서드 정의
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("orderFrontController");
	
	// POST 방식 요청에 대한 한글 인코딩 처리
	request.setCharacterEncoding("UTF-8");
	
	// 서블릿 주소 추출
	String command = request.getServletPath();
	System.out.println("command : " + command);
	
	// 공통으로 사용할 변수 선언
	Action action = null; // XXXAction 클래스를 공통으로 관리할 Action 인터페이스 타입 변수
	ActionForward forward = null; // 포워딩 정보를 저장할 ActionForward 타입 변수
	
	// 추출 된 서블릿 주소(command)를 if문을 통해 문자열 비교를 수행하고
	// 각 주소에 따른 액션(작업) 요청

	/*
	 *  1. 주문 상품 관련 정보 + 주문 회원 정보 들고 이동
	 *  (post 방식으로 주문자의 order_detail을 db에 저장)	
	 * 
	 *  2. 주문 상품 관련 정보를 목록에 뿌리기 - orderform & orderConfirmation
	 *  (get 방식으로 db에 있는 정보 가져오기)
	 *  
	 *  3. 주문 회원 정보 - 주문자 정보에 뿌리기 (memberDAO 이용)
	 *  + 주문 정보 DB에 저장
	 * 
	 *  4. 주문자 정보와 배송정보가 일치하지 않는
	 *    비회원 주문일 경우 따로 주문 정보 DB에 저장
	 *  
	 *  5. 주문상품, 총수량, 총 금액 결제 테이블로 전달
	 *  
	 *  6. 결제 단계에서 결제 완료 후 
	 *    상품 테이블 : 재고 변경
	 *    회원 테이블 : 주문정보 추가 
	 *    관리자 테이블 : 주문정보 추가 (비회원/회원 분류) + 매출 연결
	 *    
	 *   ------------------------------------------------------ 완료 
	 *   
	 *   
	 *   
	 */
	

   if(command.equals("/SelectCoupon.od")) {//회원주문 - 쿠폰 적용,조회
		forward = new ActionForward();
		forward.setPath("order/coupon_select.jsp");
		forward.setRedirect(false);
		
	} else if(command.equals("/SearchUsableCoupon.od")) {
		//coupon_select.jsp 페이지에서 존재하는 쿠폰 조회 (ajax)
		action = new SearchExistMemberCouponAction();
		forward = action.execute(request, response);
		System.out.println("SearchUsableCoupon");
		
		
	} else if(command.equals("/SearchCouponCode.od")) {//coupon_select.jsp 쿠폰 검색 버튼(ajax)
		action = new CouponSearchAction();
		action.execute(request, response);
		
    //------------------------------------------ order 작업 부분 
		} else if(command.equals("/OrderForm.od")) {
			// 1. 주문 페이지 (주문서 작성)
			action = new OrderFormAction();
			forward = action.execute(request, response);
		
		} else if(command.equals("/OrderInsertPro.od")) {
			// 2. orders & order_product 테이블로 주문정보 입력 
			System.out.println("오더 테이블로 구문 넣는 orderInsertPro");
			action = new OrderInsertProAction();
			forward = action.execute(request, response);	
			
		} else if(command.equals("/OrderPaymentForm.od")) { 
			// 3. 장바구니 상품 결제 페이지
			System.out.println("장바구니 상품 결제 페이지 > OrderPaymentForm.od");
			forward = new ActionForward();
			forward.setPath("/order/order_payment.jsp");
			forward.setRedirect(false);
			
		} else if(command.equals("/OrderPaymentPro.od")) {
			// 4. 주문확인서 생성 및 결제 작업 진행 페이지
			System.out.println("주문확인서 생성 페이지 > orderPaymentPro.od");
			action = new OrderPaymentProAction();
			forward = action.execute(request, response);	
			
		} else if(command.equals("/OrderConfirm.od")) {
			// 5. 주문확인서 페이지
			System.out.println("주문확인서 > OrderConfirm.od");
			forward = new ActionForward();
			forward.setPath("order/orderConfirmation.jsp");
			forward.setRedirect(false);
			
		} else if(command.equals("/OrderCouponPro.od")) {
			// 3-(1).쿠폰 페이지에서 받아온 쿠폰코드로 할인금액 계산하는 페이지
			action = new OrderCouponProAction();
			forward = action.execute(request, response);
			
		} 
 


		
		//------------------------------------------ order 작업 부분
		if(forward != null) {
			// 2. ActionForward 객체에 저장된 포워딩 방식 판별
			if(forward.isRedirect()) { // Redirect 방식
				// Redirect 방식의 포워딩 작업 수행
				// => 포워딩 경로는 ActionForward 객체의 getPath() 메서드 활용
				response.sendRedirect(forward.getPath());
			} else { // Dispatch 방식
				// Dispatch 방식의 포워딩 작업 수행
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	
	}// end of doProcess()
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
