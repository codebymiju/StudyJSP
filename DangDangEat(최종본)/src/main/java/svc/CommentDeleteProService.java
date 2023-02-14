package svc;

import java.sql.Connection;

import dao.CommentDAO;
import db.JdbcUtil;

public class CommentDeleteProService {	
		
	public boolean removeComment(int comment_code) {
		System.out.println("CommentDeleteProService - removeComment()");
		
		boolean isDeleteSuccess2 = false;

		Connection con = JdbcUtil.getConnection();

		CommentDAO dao = CommentDAO.getInstance();

		dao.setConnection(con);

		int deleteCount = dao.deleteComment(comment_code);
		
		if(deleteCount > 0) {
			JdbcUtil.commit(con);
			isDeleteSuccess2 = true;
		} else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return isDeleteSuccess2;
	}

}
