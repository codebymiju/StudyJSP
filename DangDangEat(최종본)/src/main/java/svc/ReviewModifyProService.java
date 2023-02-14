package svc;

import java.sql.Connection;

import dao.ReviewDAO;
import db.JdbcUtil;
import vo.ReviewBean;

public class ReviewModifyProService {
	public boolean isReviewWriter(ReviewBean review) {
		boolean isReviewWriter = false;
		
		Connection con = JdbcUtil.getConnection();
		
		ReviewDAO dao = ReviewDAO.getInstance();
		
		dao.setConnection(con);
		
		isReviewWriter = dao.isReviewWriter(review.getReview_code());
		
		JdbcUtil.commit(con);
		
		JdbcUtil.close(con);
		
		return isReviewWriter;
	}

	public boolean modifyReview(ReviewBean review) {
		boolean isModifySuccess = false;
		
		Connection con = JdbcUtil.getConnection();
		
		ReviewDAO dao = ReviewDAO.getInstance();
		
		dao.setConnection(con);
		
		int updateCount = dao.updateReview(review);
		
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
