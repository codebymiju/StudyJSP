package svc;

import java.sql.Connection;

import dao.QnaDAO;
import db.JdbcUtil;

public class QnaDeleteProService {

	public boolean isQnaWriter(int qna_code) {
		boolean isQnaWriter = false;
		
		Connection con = JdbcUtil.getConnection();
		
		QnaDAO dao = QnaDAO.getInstance();
		
		dao.setConnection(con);
		
		isQnaWriter = dao.isQnaWriter(qna_code);
		
		JdbcUtil.close(con);
		
		return isQnaWriter;
	}

	public boolean removeQna(int qna_code) {
		boolean isDeleteSuccess = false;
		
		Connection con = JdbcUtil.getConnection();
		
		QnaDAO dao = QnaDAO.getInstance();
		
		dao.setConnection(con);
		
		int deleteCount = dao.deleteQna(qna_code);
		
		if(deleteCount > 0) {
			JdbcUtil.commit(con);
			isDeleteSuccess = true;
		} else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return isDeleteSuccess;
	}

}
