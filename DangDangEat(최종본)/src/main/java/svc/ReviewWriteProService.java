package svc;

import java.sql.Connection;

import dao.ReviewDAO;
import db.JdbcUtil;
import vo.ReviewBean;

public class ReviewWriteProService {
	
	public boolean registReview(ReviewBean review) {
		System.out.println("ReviewWriteProService - registReview()");
		
		boolean isWriteSuccess = false;
		
		Connection con = JdbcUtil.getConnection();
		
		ReviewDAO dao = ReviewDAO.getInstance();
		
		dao.setConnection(con);		
		
		int insertCount = dao.insertReview(review);
		
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