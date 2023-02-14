package svc;

import java.sql.Connection;

import dao.ReviewDAO;
import db.JdbcUtil;
import vo.ReviewBean;

public class ReviewReplyProService {

	// 글쓰기 작업 요청
	public boolean registReplyReview(ReviewBean review) {
		boolean isWriteSuccess = false;

		Connection con = JdbcUtil.getConnection();
		
		ReviewDAO dao = ReviewDAO.getInstance();
		
		dao.setConnection(con);
		
		int insertCount = dao.insertReplyReview(review);
		
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
