package svc;

import java.sql.Connection;

import dao.BoardDAO;
import db.JdbcUtil;

public class BoardDeleteProService {

	// 패스워드 판별하는 메서드() // 12/05 03-(2)
	public boolean isBoardWriter(int board_num, String board_pass) {
		boolean isBoardWriter = false;
		
		Connection con = JdbcUtil.getConnection();
		BoardDAO dao = BoardDAO.getInstance();
		dao.setConnection(con);
		
		// BoardDAO.isBoardWriter() 호출하여 패스워드 확인 작업 수행
		isBoardWriter = dao.isBoardWriter(board_num, board_pass);
		
		JdbcUtil.close(con);
		return isBoardWriter;
	} //isBoardWriter

	// 패스워드가 일치하면 시행할 게시물 삭제하는 메서드() // 12/05 03-(3)
	public boolean removeBoard(int board_num) {
		boolean isDeleteSuccess = false;
		
		Connection con = JdbcUtil.getConnection();
		BoardDAO dao = BoardDAO.getInstance();
		dao.setConnection(con);
		
		int deleteCount = dao.deleteBoard(board_num);
	
		if(deleteCount > 0) {
			isDeleteSuccess = true;
			JdbcUtil.commit(con);
		} else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		return isDeleteSuccess;
	} //removeBoard

}
