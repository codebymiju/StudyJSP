package svc;

import java.sql.Connection;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.ProductBean;
import vo.ProductBean;

public class ProductDetailService {

	public ProductBean getBoard(int pro_code) {
		ProductBean board = null;

		// 공통작업-1. Connection 객체 가져오기
		Connection con = JdbcUtil.getConnection();

		// 공통작업-2. ProductDAO 객체 가져오기
		ProductDAO dao = ProductDAO.getInstance();

		// 공통작업-3. ProductDAO 객체에 Connection 객체 전달하기
		dao.setConnection(con);

		// ProductDAO 의 selectProduct() 메서드 호출하여 게시물 상세 정보 조회 작업 수행
		// => 파라미터 : 글번호 리턴타입 : ProductBean(product)
		board = dao.selectProduct(pro_code);


		JdbcUtil.commit(con);
		
		
		// 공통작업-4. Connection 객체 반환하기
		JdbcUtil.close(con);

		return board;
	}

}
