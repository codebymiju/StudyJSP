package svc;

import java.sql.Connection;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.ProductBean;


// Action 클래스로부터 작업 요청을 받아 DAO 클래스와 상호작용을 통해
// 실제 비즈니스 로직(DB 작업)을 수행하는 클래스
// 또한, DB 작업 수행 후 결과 판별을 통해 트랜잭션 처리(commit or rollback)도 수행
public class ProductInsertProService {
	// 글쓰기 작업 요청을 위한 registProduct() 메서드 정의
	//파라미터 : ProductBean객체  리턴타입 : boolean(isInsertSuccess)
	public boolean registProduct(ProductBean product) {
		System.out.println("ProductInsertProService - registProduct()");
		
		// 1. 상품 등록 작업 요청 처리 결과를 저장할 boolean 타입 변수 선언
		boolean isInsertSuccess = false;
		
		// 2. JdbcUtil 객체로부터 Connection Pool 에 저장된 Connection 객체 가져오기(공통)
		// => 트랜잭션 처리를 Service 객체에서 수행하므로
		//    DAO 가 아닌 Service 가 Connection 객체를 관리함
		Connection con = JdbcUtil.getConnection();
		
		// 3. ProductDAO 클래스로부터 ProductDAO 객체 가져오기(공통)
		// => 싱글톤 디자인 패턴으로 구현되어 있는 객체를 getInstance() 메서드로 리턴받기
		ProductDAO dao = ProductDAO.getInstance();
		
		// 4. ProductDAO 객체의 setConnection() 메서드를 호출하여 Connection 객체 전달(공통)
		dao.setConnection(con);
		
		// 5. ProductDAO 객체의 xxx() 메서드를 호출하여 xxx 작업 수행 요청 및 결과 리턴받기
		//    insertProduct() 메서드를 호출하여 글쓰기 작업 요청 및 결과 리턴받기
		// => 파라미터 : ProductBean 객체   리턴타입 : int(insertCount)
		int insertCount = dao.insertProduct(product);
		
		// 6. 작업 처리 결과에 따른 트랜잭션 처리
		if(insertCount > 0) { // 성공 시
			// INSERT 작업 성공했을 경우의 트랜잭션 처리(commit) 을 위해
			// JdbcUtil 클래스의 commit() 메서드를 호출하여 commit 작업 수행
			// => 파라미터 : Connection 객체
			JdbcUtil.commit(con);
			
			// 작업 처리 결과를 성공으로 표시하여 리턴하기 위해 isInsertSuccess 를 true 로 변경
			isInsertSuccess = true;
			
		} else { // 실패 시
			// INSERT 작업 실패했을 경우의 트랜잭션 처리(rollback) 을 위해
			// JdbcUtil 클래스의 rollback() 메서드를 호출하여 rollback 작업 수행
			JdbcUtil.rollback(con);
			// isWriteSuccess 기본값이 false 이므로 변경 생략
		}
		
		// 7. Connection Pool 로부터 가져온 Connection 자원 반환(공통)
		// => 주의! DAO 객체 내에서 Connection 객체를 반환하지 않도록 해야한다!
		JdbcUtil.close(con);
		
		// 작업 요청 처리 결과 리턴
		return isInsertSuccess; // BoardWriteProAction 으로 리턴
	}
	
}














