package svc;

import java.sql.Connection;

import dao.NoticeDAO;
import db.JdbcUtil;

public class NoticeDeleteProService {


	public boolean isNoticeWriter(int notice_code) {
		boolean isNoticeWriter = false;
		
		Connection con = JdbcUtil.getConnection();
		
		NoticeDAO dao = NoticeDAO.getInstance();
		
		dao.setConnection(con);
		
		isNoticeWriter = dao.isNoticeWriter(notice_code);
		
		JdbcUtil.close(con);
		
		return isNoticeWriter;
	}
	
	
	
	public boolean removeNotice(int notice_code) {
		System.out.println("NoticeDeleteProService - removeNotice()");
		
		boolean isDeleteSuccess = false;

		Connection con = JdbcUtil.getConnection();

		NoticeDAO dao = NoticeDAO.getInstance();

		dao.setConnection(con);

		int deleteCount = dao.deleteNotice(notice_code);
		
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