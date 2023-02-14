package svc;

import java.sql.Connection;
import java.util.List;

import dao.CartDAO;
import db.JdbcUtil;
import vo.CartBean;
import vo.cart_wish_proBean;

public class CartListService {

	public List<cart_wish_proBean> getCartList(String id) {
		List<cart_wish_proBean> cartList = null;
		
		// 공통작업-1. Connection 객체 가져오기
		Connection con = JdbcUtil.getConnection();
		// 공통작업-2. BoardDAO 객체 가져오기
		CartDAO dao = CartDAO.getInstance();
		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달하기
		dao.setConnection(con);
		
		cartList = dao.getViewCartList(id, true);
//		System.out.println(cartList);
		// true => 장바구니 조회
		
		// 커밋 처리
		JdbcUtil.commit(con);
		
		// 공통작업-4. Connection 객체 반환하기
		JdbcUtil.close(con);
		
		return cartList;
	}

}
