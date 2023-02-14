package svc;

import java.sql.Connection;

import dao.BoardDAO;
import db.JdbcUtil;
import vo.BoardBean;

//Action 클래스로부터 작업 요청을 받아 DAO 클래스와 상호작용을 통해
//실제 비즈니스 로직(DB 작업)을 수행하는 클래스
//또한, DB 작업 수행 후 결과 판별을 통해 트랜잭션 처리(commit or rollback)도 수행
public class BoardWriteProService {

	public boolean registBoard(BoardBean board) {
		// **글쓰기 성공, 실패 판별처리할 공간
		System.out.println("BoardWriteProService - registBoard()");
		
		// 1. 글쓰기 작업 요청 처리 결과를 저장할 boolean 타입 변수 선언 	
		boolean isWriteSuccess = false;
		
		// 2. JdbcUtil 객체로부터 Connection Pool 에 저장된 Connection 객체 가져오기(공통)
		Connection con = JdbcUtil.getConnection();
		
		// 3. BoardDAO 클래스로부터 BoardDAO 객체 가져오기(공통)
		BoardDAO dao = BoardDAO.getInstance();
		
		// 4. BoardDAO.setConnection() > Connection 객체 전달(공통) / connection을 이 페이지가 가지고 있으니 넘겨줘야 함
		dao.setConnection(con);
		
		// 5. BoardDAO 객체의 xxx() 메서드를 호출하여 xxx 작업 수행 요청 및 결과 리턴받기
		int insertCount = dao.insertBoard(board);
		
		// 6. 작업 처리 결과에 따른 트랜잭션 처리
		if(insertCount > 0) {
			
			JdbcUtil.commit(con);
			isWriteSuccess = true;
			
		} else {
			JdbcUtil.rollback(con);
			// isWriteSuccess = false가 기본값이므로 변경 생략
		}
		// 12/02
		// 7. Connection Pool로부터 가져온 Connection 자원 반환(공통)
		JdbcUtil.close(con);
		
		// 8. 작업 요청 처리 결과 리턴
		return isWriteSuccess; // BoardWriteProAction
	}

}
