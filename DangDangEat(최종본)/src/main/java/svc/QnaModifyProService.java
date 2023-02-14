package svc;

import java.sql.Connection;

import dao.QnaDAO;
import db.JdbcUtil;
import vo.QnaBean;

public class QnaModifyProService {
	public boolean isQnaWriter(QnaBean qna) {
		boolean isQnaWriter = false;
		
		Connection con = JdbcUtil.getConnection();
		
		QnaDAO dao = QnaDAO.getInstance();
		
		dao.setConnection(con);
		
		isQnaWriter = dao.isQnaWriter(qna.getQna_code());
		
		JdbcUtil.commit(con);
		
		JdbcUtil.close(con);
		
		return isQnaWriter;
	}

	public boolean modifyQna(QnaBean qna) {
		boolean isModifySuccess = false;
		
		Connection con = JdbcUtil.getConnection();
		
		QnaDAO dao = QnaDAO.getInstance();
		
		dao.setConnection(con);
		
		int updateCount = dao.updateQna(qna);
		
		if(updateCount > 0) {
			JdbcUtil.commit(con);
			isModifySuccess = true;
		} else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.commit(con);
		
		JdbcUtil.close(con);
		
		return isModifySuccess;
	}
}
