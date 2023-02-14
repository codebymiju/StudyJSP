package svc;

import java.sql.Connection;
import java.util.List;

import dao.ReviewDAO;
import db.JdbcUtil;
import vo.ReviewBean;

public class ReviewListService {

	public List<ReviewBean> getReviewList(String keyword, int startRow, int listLimit) {
		List<ReviewBean> reviewList = null;
		
		Connection con = JdbcUtil.getConnection();
		
		ReviewDAO dao = ReviewDAO.getInstance();
		
		dao.setConnection(con);
		
		reviewList = dao.selectReviewList(keyword, startRow, listLimit);
		
		JdbcUtil.commit(con);
		
		JdbcUtil.close(con);
		
		return reviewList;
	}

	public int getReviewListCount(String keyword) {
		int listCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		
		ReviewDAO dao = ReviewDAO.getInstance();
		
		dao.setConnection(con);
		
		listCount = dao.selectReviewListCount(keyword);
		
		JdbcUtil.commit(con);
		
		JdbcUtil.close(con);
		
		return listCount;
	}
	
}