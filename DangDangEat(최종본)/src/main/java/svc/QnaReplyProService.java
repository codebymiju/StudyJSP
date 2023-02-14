package svc;

import java.sql.Connection;

import dao.QnaDAO;
import db.JdbcUtil;
import vo.QnaBean;

public class QnaReplyProService {

	// 글쓰기 작업 요청
	public boolean registReplyQna(QnaBean qna) {
		boolean isWriteSuccess = false;
		
		Connection con = JdbcUtil.getConnection();
		
		QnaDAO dao = QnaDAO.getInstance();
		
		dao.setConnection(con);
		
		int insertCount = dao.insertReplyQna(qna);
		
		if(insertCount > 0) { // 성공 시
			JdbcUtil.commit(con);
			isWriteSuccess = true;
		} else { // 실패 시
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.commit(con);
		
		JdbcUtil.close(con);
		
		return isWriteSuccess; 
	}

}
