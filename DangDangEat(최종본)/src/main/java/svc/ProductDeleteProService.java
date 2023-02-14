package svc;

import java.sql.Connection;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.ProductBean;

public class ProductDeleteProService {

	public boolean removeProduct(ProductBean product) {
		
		boolean isDeleteSucess = false;
		
		// 공통작업-1. Connection 객체 가져오기
		Connection con = JdbcUtil.getConnection();
		
		// 공통작업-2. BoardDAO 객체 가져오기
		ProductDAO dao = ProductDAO.getInstance();
		
		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달하기
		dao.setConnection(con);
		
		//ProductDAO 의 deleteCount() 메서드를 호출
		// => 파라미터 : ProductBean 객체    리턴타입 :int deleteCount
		
		int deleteCount = dao.deleteProduct(product);
		
		//리턴받은 결과를 판별하여 commit, rollback
		if(deleteCount > 0) {
			JdbcUtil.commit(con);
			isDeleteSucess = true;
		} else {
			JdbcUtil.rollback(con);;
		}
		
		// 공통작업-4. Connection 객체 반환하기
		JdbcUtil.close(con);
		
		return isDeleteSucess;
	}

}
