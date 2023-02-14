package svc;

import java.sql.Connection;
import java.util.List;

import dao.OrderDAO;
import db.JdbcUtil;
import vo.OrdersBean;
import vo.cart_productBean;
import vo.paymentsBean;

public class OrderPaymentService {

	// 결제완료시 orders 테이블의 order_status를 결제완료로 변경하는 메서드 (0 > 1)
	public boolean orderStatusUpdate(String id, int order_code) {
		boolean isOrderStatusUpdate = false;
		
		Connection con = JdbcUtil.getConnection();
		OrderDAO dao = OrderDAO.getInstance();
		dao.setConnection(con);
		
		int OrderStatusUpdateCount = dao.orderStatusUpdate(id, order_code);
		
		if(OrderStatusUpdateCount > 0 ) {
			JdbcUtil.commit(con);
			isOrderStatusUpdate = true; 
		} else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return isOrderStatusUpdate;
	}// orderStatusUpdate

	// 결제 완료 시 장바구니(cart)에 있는 정보 업데이트하는 메서드  
	public int cartInfoUpdate(String id, int cart_code) {
		int cartInfoUpdateCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		OrderDAO dao = OrderDAO.getInstance();
		dao.setConnection(con);
		
		cartInfoUpdateCount = dao.cartInfoUpdate(id, cart_code);
		
		if(cartInfoUpdateCount > 0) {
			JdbcUtil.commit(con);
		} else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		return cartInfoUpdateCount;
		
	} // cartInfoDelete

	// payments 테이블에 정보를 넣는 구문
	public int paymentInsertPro(paymentsBean payments) {
		int paymentInsertCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		OrderDAO dao = OrderDAO.getInstance();
		dao.setConnection(con);
		
		paymentInsertCount = dao.paymentInsertPro(payments);
		
		if(paymentInsertCount > 0) {
			JdbcUtil.commit(con);
		} else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return paymentInsertCount;
	}

	// 결제 완료시 주문상품 수량 업데이트 하는 구문
	public int productQtyUpdate(int order_code, int pro_code) {
		int productQtyUpdateCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		OrderDAO dao = OrderDAO.getInstance();
		dao.setConnection(con);
		
		productQtyUpdateCount = dao.productQtyUpdate(order_code, pro_code);
		
		if(productQtyUpdateCount > 0) {
			JdbcUtil.commit(con);
		} else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return productQtyUpdateCount;
	}

	//----------------------------------------- 23/01/07 추가부분
	// 주문 상품 정보 가져오는 메서드(주문확인서 출력용)
	public List<cart_productBean> getOrderProductList(String id, int pro_code, int cart_code) {
		List<cart_productBean> orderProductList = null;
		
		Connection con = JdbcUtil.getConnection();
		OrderDAO dao = OrderDAO.getInstance();
		dao.setConnection(con);
		
		orderProductList = dao.getViewCartList(id, pro_code, cart_code);
		
		JdbcUtil.commit(con);
		JdbcUtil.close(con);
		return orderProductList;
	}

	// 주문 정보 가져오는 메서드(주문확인서 출력용)
	public List<OrdersBean> getOrderInfoList(String id, int cart_code) {
		List<OrdersBean> orderInfoList = null;
		
		Connection con = JdbcUtil.getConnection();
		OrderDAO dao = OrderDAO.getInstance();
		dao.setConnection(con);
		
		orderInfoList = dao.getOrderMemberList(id, cart_code);
		
		JdbcUtil.commit(con);
		JdbcUtil.close(con);
		
		return orderInfoList;
	}

	// 결제 정보 가져오는 메서드(주문확인서 출력용)
	public List<paymentsBean> getPaymentsList(int pay_number, int cart_code) {
		List<paymentsBean> paymentsList = null;
		
		Connection con = JdbcUtil.getConnection();
		OrderDAO dao = OrderDAO.getInstance();
		dao.setConnection(con);
		
		paymentsList = dao.getOrderPaymentsList(pay_number, cart_code);
		System.out.println("service로 잘넘어온 결제 정보 : " + paymentsList);
		
		JdbcUtil.commit(con);
		JdbcUtil.close(con);
		
		return paymentsList;
	}

	// 23/01/10 추가
	// 카트번호 중복 해결 방법
	// 결제 완료된 카트 (order_product, cart를 삭제하기)
	// 단, 위시리스트도 함께 삭제되니 유의
	public int deleteCartPro(int cart_code) {
		int deleteCartCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		OrderDAO dao = OrderDAO.getInstance();
		dao.setConnection(con);
		
		deleteCartCount = dao.deleteCart(cart_code);
		
		if(deleteCartCount > 0) {
			JdbcUtil.commit(con);
		} else {
			JdbcUtil.rollback(con);
		}
		JdbcUtil.close(con);
		
		return deleteCartCount;
	}

	// 23/01/10 추가된 메서드
	// 쿠폰 사용여부를 N > Y로 변경하는 메서드
	public int getCouponUpdateCount(String cp_code, String id) {
		int couponUpdateCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		OrderDAO dao = OrderDAO.getInstance();
		dao.setConnection(con);
		
		couponUpdateCount = dao.getCouponUpdateCount(cp_code, id);

			if(couponUpdateCount >= 0) {
				JdbcUtil.commit(con);
			} else {
				JdbcUtil.rollback(con);
			}
			JdbcUtil.close(con);
		
		return couponUpdateCount;
	}
 
	
	
}
