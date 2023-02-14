package svc;

import java.sql.Connection;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.ProductBean;

public class ProductModifyProService {

	//글 수정
	public boolean modifyProduct(ProductBean product) {

		boolean isModifySuccess = false;

		// 공통작업-1. Connection 객체 가져오기
		Connection con = JdbcUtil.getConnection();

		// 공통작업-2. ProductDAO 객체 가져오기
		ProductDAO dao = ProductDAO.getInstance();

		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달하기
		dao.setConnection(con);

		// ProductBean 의 updateProduct() 메서드를 호출 - 글수정
		// => 파라미터 : ProductBean객체(product)    리턴타입 : int(updateCount)
		int updateCount = dao.updateProduct(product);

		//글 수정 결과 판별 
		//-> 리턴받은 결과를 판별하여 성공 시 commit, 실패 시 rollback
		if(updateCount > 0) {
			JdbcUtil.commit(con);
			isModifySuccess = true;
		} else {
			JdbcUtil.rollback(con);
		}

		// 공통작업-4. Connection 객체 반환하기
		JdbcUtil.close(con);

		return isModifySuccess;

	}
}
