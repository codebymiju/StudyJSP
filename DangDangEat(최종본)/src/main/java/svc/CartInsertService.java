package svc;

import java.sql.Connection;

import dao.CartDAO;
import db.JdbcUtil;
import vo.CartBean;

public class CartInsertService {

	public int insertCart(CartBean cart) {
		int insertCount = 0;
		
		// 공통작업-1. Connection 객체 가져오기
		Connection con = JdbcUtil.getConnection();
		// 공통작업-2. BoardDAO 객체 가져오기
		CartDAO dao = CartDAO.getInstance();
		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달하기
		dao.setConnection(con);
		
		System.out.println(cart.getCart_amount());
		// 장바구니에 있는지 확인
		boolean isCartExist = dao.isExist(cart, true);
		
		if(!isCartExist) { // 장바구니에 없으면
			// 위시리스트에 있는지 확인
			boolean isWishlistExist = dao.isExist(cart, false);
			
			if(!isWishlistExist) { // 위시리스트에 없으면 insert
				int insertCartCount = dao.insertCart(cart);
				
				if(insertCartCount > 0) { //성공
					JdbcUtil.commit(con);
					insertCount = 1;
				} else {
					JdbcUtil.rollback(con);
				}
			} else { // 위시리스트에 있으면 장바구니에 update
				int updateCount = dao.updateCart(cart, true);
				
				if(updateCount > 0) { //성공
					JdbcUtil.commit(con);
					insertCount = 1;
//					System.out.println("update 실행");
				} else { //실패
					JdbcUtil.rollback(con);
				}
			}
		} else { // 장바구니에 있으면 이미 있습니다 출력
			insertCount = 2;
		}
		
		// 공통작업-4. Connection 객체 반환하기
		JdbcUtil.close(con);
		
		return insertCount;
	}

}
