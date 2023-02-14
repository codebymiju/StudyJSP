package svc;

import java.sql.Connection;

import dao.CartDAO;
import db.JdbcUtil;
import vo.CartBean;

public class CartUpdateService {

	public int updateCart(CartBean cart) {
		int updateCount = 0;
		
		// 공통작업-1. Connection 객체 가져오기
		Connection con = JdbcUtil.getConnection();
		// 공통작업-2. BoardDAO 객체 가져오기
		CartDAO dao = CartDAO.getInstance();
		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달하기
		dao.setConnection(con);
		
		updateCount = dao.updateCartNumber(cart);
		
		if(updateCount > 0) {
			JdbcUtil.commit(con);
		} else {
			JdbcUtil.rollback(con);
		}
		
		// 공통작업-4. Connection 객체 반환하기
		JdbcUtil.close(con);
		
		return updateCount;
	}

}
