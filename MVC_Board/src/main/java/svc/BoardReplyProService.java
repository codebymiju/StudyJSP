package svc;

import java.sql.Connection;

import dao.BoardDAO;
import db.JdbcUtil;
import vo.BoardBean;
// 12/07 6. writeProService랑 똑같다!!!
public class BoardReplyProService {

	public boolean registReplyBoard(BoardBean board) {
		boolean isWriteSuccess = false;
		
		Connection con = JdbcUtil.getConnection();
		BoardDAO dao = BoardDAO.getInstance();
		dao.setConnection(con);
		// 6. write랑 다르게 바뀐 부분
		int insertCount = dao.insertReplyBoard(board);
		
		if(insertCount > 0) { // 성공시
			JdbcUtil.commit(con);
			isWriteSuccess = true;
		} else { // 실패시
			JdbcUtil.rollback(con);
		}
		JdbcUtil.close(con);
		
		return isWriteSuccess; // BoardReplyProAction
	}

}
