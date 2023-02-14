package svc;

import java.sql.Connection;
import java.util.List;

import dao.QnaDAO;
import db.JdbcUtil;
import vo.QnaBean;

public class QnaListService {

	public List<QnaBean> getQnaList(String keyword, int startRow, int listLimit) {		
		System.out.println("QnaListService - getQnaList()");
		
		List<QnaBean> qnaList = null;
		
		Connection con = JdbcUtil.getConnection();
		
		QnaDAO dao = QnaDAO.getInstance();
		
		dao.setConnection(con);
		
		qnaList = dao.selectQnaList(keyword, startRow, listLimit);
		
		JdbcUtil.commit(con);
		
		JdbcUtil.close(con);
		
		return qnaList;
	}
	

	public int getQnaListCount(String keyword) {
		System.out.println("QnaListService - getQnaListCount()");

		int listCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		
		QnaDAO dao = QnaDAO.getInstance();
		
		dao.setConnection(con);
		
		listCount = dao.selectQnaListCount(keyword);
		
		JdbcUtil.commit(con);
		
		JdbcUtil.close(con);
		
		return listCount;
	}


}
