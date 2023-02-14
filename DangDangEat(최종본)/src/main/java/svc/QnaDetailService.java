package svc;

import java.sql.Connection;

import dao.QnaDAO;
import db.JdbcUtil;
import vo.QnaBean;

public class QnaDetailService {
	// 글 상세정보 조회
	public QnaBean getQna(int qna_code) {
		
		QnaBean qna = null;
		
		Connection con = JdbcUtil.getConnection();
		
		QnaDAO dao = QnaDAO.getInstance();
		
		dao.setConnection(con);
		
		qna = dao.selectQna(qna_code);
		
		JdbcUtil.commit(con);
				
		JdbcUtil.close(con);
		
		return qna;
	}

}