package svc;

import java.sql.Connection;

import dao.NoticeDAO;
import db.JdbcUtil;
import vo.NoticeBean;

public class NoticeWriteProService {
	
		public boolean registNotice(NoticeBean notice) {
			System.out.println("NoticeWriteProService - registNotice()");
			
			boolean isWriteSuccess = false;
			
			Connection con = JdbcUtil.getConnection();
			
			NoticeDAO dao = NoticeDAO.getInstance();
			
			dao.setConnection(con);
						
			int insertCount = dao.insertNotice(notice);
			
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