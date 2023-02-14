package svc;

import java.sql.Connection;
import java.util.List;

import dao.OrderDAO;
import db.JdbcUtil;
import vo.Order_productBean;
import vo.OrdersBean;
import vo.cart_productBean;

public class OrderInsertService {
	
	// 주문자 정보 & 주문상품 등록 구문 (orders & order_product table)
	public boolean insertOrder(OrdersBean order, Order_productBean order_product, int cart_code) {
		System.out.println("OrderFormService > insertOrder");
		boolean isInsertOrderSuccess = false;
		
		Connection con = JdbcUtil.getConnection();
		OrderDAO dao = OrderDAO.getInstance();
		dao.setConnection(con);
		
		// 중복되는 항목 삭제해주는 메서드 
		// 테이블 구조상 (order_product > orders 삭제) 
		int selectCount = dao.selectCartOrder(cart_code);
		
			if(selectCount > 0) {
				int deleteCount = dao.deleteCartOrder();
				if(deleteCount > 0 ) {
					System.out.println("구문 중복 삭제 성공");
				}
			}

		// 주문자 정보 & 주문상품 등록 구문
		int insertCount = dao.insertOrder(order, order_product, cart_code);
		
			if(insertCount > 0) { //주문상품 테이블 등록 성공
				
				JdbcUtil.commit(con);
				isInsertOrderSuccess = true; 
				
			} else { // 등록 실패
				
				JdbcUtil.rollback(con);
			}
		
			JdbcUtil.close(con);
			
		return isInsertOrderSuccess;
	} // insertOrder

	// 등록 완료된 주문상품 리스트 생성 구문
	public List<cart_productBean> getOrderProductList(String id, int cart_code) {
		List<cart_productBean> orderProductList = null;
		
		Connection con = JdbcUtil.getConnection();
		OrderDAO dao = OrderDAO.getInstance();
		dao.setConnection(con);
		
		orderProductList = dao.getOrderProductList(id, cart_code);
		
		JdbcUtil.close(con);
		
		return orderProductList;
	}
	
	// 등록 완료된 주문정보 리스트 생성 구문
	public List<OrdersBean> getOrderMemberList(String id, int cart_code) {
		List<OrdersBean> orderMemberList = null;
		
		Connection con = JdbcUtil.getConnection();
		OrderDAO dao = OrderDAO.getInstance();
		dao.setConnection(con);
		
		orderMemberList = dao.getOrderMemberList(id, cart_code);
		
		JdbcUtil.close(con);
		
		return orderMemberList;
	}

}
