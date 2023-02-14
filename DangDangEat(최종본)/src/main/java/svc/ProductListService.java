package svc;

import java.sql.Connection;
import java.util.List;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.ProductBean;

public class ProductListService {

	// 상품 리스트 조회- getProductList()
	// => 파라미터 : 검색어, 시작행번호, 목록갯수   리턴타입 : List<ProductBean> productList
	public List<ProductBean> getProductList(String keyword,String category, int startRow, int listLimit) {
		List<ProductBean> productList = null;
		
		// 공통작업-1. Connection 객체 가져오기
		Connection con = JdbcUtil.getConnection();
		
		// 공통작업-2. BoardDAO 객체 가져오기
		ProductDAO dao = ProductDAO.getInstance();
		
		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달하기
		dao.setConnection(con);
		
		// ProductDAO 객체의 selectProductList() 메서드를 호출하여 글목록 조회 작업 수행
		// => 파라미터 : 검색어, 시작행번호, 목록갯수   리턴타입 : List<ProductBean> productList
		productList = dao.selectProductList(keyword,category, startRow, listLimit);
		
		JdbcUtil.commit(con);
		
		// 공통작업-4. Connection 객체 반환하기
		JdbcUtil.close(con);
		
		
		return productList;
	}

	// 상품 목록 갯수 조회 - getBoardListCount()
	// => 파라미터 : 검색어   리턴타입 : int(listCount)
	public int getProductListCount(String keyword,String category) {
		int listCount = 0;
		
		// 공통작업-1. Connection 객체 가져오기
		Connection con = JdbcUtil.getConnection();
		
		// 공통작업-2. BoardDAO 객체 가져오기
		ProductDAO dao = ProductDAO.getInstance();
		
		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달하기
		dao.setConnection(con);
		
		// BoardDAO 객체의 selectBoardListCount() 메서드를 호출하여 글목록 갯수 조회 작업 수행
		// => 파라미터 : 검색어     리턴타입 : int(listCount)
		listCount = dao.selectProductListCount(keyword,category);
		
		JdbcUtil.commit(con);
		
		// 공통작업-4. Connection 객체 반환하기
		JdbcUtil.close(con);
		
		return listCount;
	}

}
