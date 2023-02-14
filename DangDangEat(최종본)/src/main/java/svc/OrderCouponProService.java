package svc;

import java.sql.Connection;

import dao.OrderDAO;
import db.JdbcUtil;

public class OrderCouponProService {
	
	// 쿠폰코드를 이용하여 할인금액을 계산하는 메서드
	public int getCouponDiscountAmount(int cart_code, String cp_code) {
		int couponDiscounAmount = 0;
		System.out.println("서비스 - getCouponDiscountAmount");
		
		Connection con = JdbcUtil.getConnection();
		OrderDAO dao = OrderDAO.getInstance();
		dao.setConnection(con);
		
		couponDiscounAmount = dao.getCouponDiscountAmount(cart_code, cp_code);
		System.out.println("OrderCouponProService로 넘어온 최종 할인 금액 : " + couponDiscounAmount);
		if(couponDiscounAmount > 0 ) {
			JdbcUtil.commit(con);
		} else {
			JdbcUtil.rollback(con);
		}
		JdbcUtil.close(con);
		
		return couponDiscounAmount;
	}

}
