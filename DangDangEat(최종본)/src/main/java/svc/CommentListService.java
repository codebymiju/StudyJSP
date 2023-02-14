package svc;

import java.sql.Connection;
import java.util.List;

import dao.CommentDAO;
import db.JdbcUtil;
import vo.CommentBean;

public class CommentListService {

		public List<CommentBean> getCommentList(int review_code, int startRow, int listLimit) {
						
			List<CommentBean> commentList = null;
			
			Connection con = JdbcUtil.getConnection();
			
			CommentDAO dao = CommentDAO.getInstance();
			
			dao.setConnection(con);
			
			commentList = dao.selectCommentList(review_code, startRow, listLimit);
			
			JdbcUtil.commit(con);
			
			JdbcUtil.close(con);
			
			return commentList;
		}		
		
	}