package svc;

import java.sql.Connection;

import dao.BoardDAO;
import db.JdbcUtil;
import vo.BoardBean;
// 12/05 숙제 / 12/07 풀이
public class BoardModifyProService {

	// 1-(2) 보드빈에 담아서 필요한 파라미터 꺼내서 전달
	// 패스워드 일치 여부 판별하는 메서드() 
	public boolean isBoardWriter(BoardBean board) {
		boolean isBoardWriter = false;
		
		Connection con = JdbcUtil.getConnection();
		BoardDAO dao = BoardDAO.getInstance();
		dao.setConnection(con);
		
		// BoardDAO.isBoardWriter() 호출하여 패스워드 확인 작업 수행
		isBoardWriter = dao.isBoardWriter(board.getBoard_num(), board.getBoard_pass());
		
		JdbcUtil.close(con);
		return isBoardWriter;
	} //isBoardWriter
	
	// 1-(2).2
	// 글 수정 작업하는 메서드()
	public boolean modifyBoard(BoardBean board) {
		boolean ismodifySuccess = false;
		Connection con = JdbcUtil.getConnection();
		BoardDAO dao = BoardDAO.getInstance();
		dao.setConnection(con);

		// 글 수정 작업 수행
		int updateCount = dao.updateBoard(board);
		
		// 글 수정 결과 판별
		 if(updateCount > 0) { // 성공시 커밋
			 ismodifySuccess = true;
			 JdbcUtil.commit(con);
		 } else { // 실패시 롤백
			 JdbcUtil.rollback(con);
		 }
	
		JdbcUtil.close(con);
		
		return ismodifySuccess;
		
	} //modifyBoard()
	
}
