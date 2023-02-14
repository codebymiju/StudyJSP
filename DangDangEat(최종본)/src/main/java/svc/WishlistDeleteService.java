package svc;

import java.sql.Connection;

import dao.CartDAO;
import db.JdbcUtil;
import vo.CartBean;
import vo.cart_wish_proBean;

public class WishlistDeleteService {

	// 상품코드로 장바구니 or 위시리스트 존재 유무 조회
	public boolean isExist(CartBean cart, boolean isWishlist) {
		boolean isExist = false;
		
		// 공통작업-1. Connection 객체 가져오기
		Connection con = JdbcUtil.getConnection();
		// 공통작업-2. BoardDAO 객체 가져오기
		CartDAO dao = CartDAO.getInstance();
		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달하기
		dao.setConnection(con);
		
		if(isWishlist) { // 위시리스트 조회
			isExist = dao.isExist(cart , false);
		} else { // 장바구니 조회
			isExist = dao.isExist(cart , true);
		}
		
		// 커밋 처리
		JdbcUtil.commit(con);
		
		// 공통작업-4. Connection 객체 반환하기
		JdbcUtil.close(con);
		
		return isExist;
	}
	
	// 장바구니에 없어서 삭제 - delete
	public boolean removeWishlist(CartBean cart) {
		boolean isDeleteSuccess = false;
		
		// 공통작업-1. Connection 객체 가져오기
		Connection con = JdbcUtil.getConnection();
		// 공통작업-2. BoardDAO 객체 가져오기
		CartDAO dao = CartDAO.getInstance();
		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달하기
		dao.setConnection(con);
		
		int deleteCount = dao.deleteCart(cart);
		
		if(deleteCount > 0) {
			JdbcUtil.commit(con);
			isDeleteSuccess = true;
		} else {
			JdbcUtil.rollback(con);
		}
		
		// 공통작업-4. Connection 객체 반환하기
		JdbcUtil.close(con);
		
		return isDeleteSuccess;
	}
	
	// 장바구니에 있는데 삭제하려는 경우 - update
	public int updateWishlist(int pro_code) {
		int updateCount = 0;
		
		// 공통작업-1. Connection 객체 가져오기
		Connection con = JdbcUtil.getConnection();
		// 공통작업-2. BoardDAO 객체 가져오기
		CartDAO dao = CartDAO.getInstance();
		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달하기
		dao.setConnection(con);
		
		CartBean cart = new CartBean();
		cart.setPro_code(pro_code);
		// cart_wishlist = 0 으로 만들기
		updateCount = dao.resetCart(cart, false);
		
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
