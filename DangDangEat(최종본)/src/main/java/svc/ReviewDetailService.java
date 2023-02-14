package svc;

import java.sql.Connection;

import dao.ReviewDAO;
import db.JdbcUtil;
import vo.ReviewBean;

public class ReviewDetailService {
	public ReviewBean getReview(int review_code, boolean isUpdateReadcount) {
		ReviewBean review = null;
		
		Connection con = JdbcUtil.getConnection();
		
		ReviewDAO dao = ReviewDAO.getInstance();
		
		dao.setConnection(con);
		
		review = dao.selectReview(review_code);
		
		if(review != null && isUpdateReadcount) {
			int updateCount = dao.updateReadcount(review_code);
			
			if(updateCount > 0) {
				JdbcUtil.commit(con);
				
				review.setReview_readcount(review.getReview_readcount() + 1);
			}
		
		}
		
		JdbcUtil.commit(con);
		
		JdbcUtil.close(con);
		
		return review;
	}

}