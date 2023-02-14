package svc;

import java.sql.Connection;

import dao.CommentDAO;
import db.JdbcUtil;
import vo.CommentBean;

// Action 클래스로부터 작업 요청을 받아 DAO 클래스와 상호작용을 통해
// 실제 비즈니스 로직(DB 작업)을 수행하는 클래스
// 또한, DB 작업 수행 후 결과 판별을 통해 트랜잭션 처리(commit or rollback)도 수행
public class CommentWriteProService {
	
	public boolean registComment(CommentBean comment) {
		System.out.println("CommentWriteProService - registComment()");
		
		boolean isWriteSuccess = false;
		
		Connection con = JdbcUtil.getConnection();
		
		CommentDAO dao = CommentDAO.getInstance();
		
		dao.setConnection(con);		
		
		int insertCount = dao.insertCommentReview(comment);
		

					if(insertCount > 0) { 
						JdbcUtil.commit(con);							
						isWriteSuccess = true;
					} else { // 실패 시
						
						JdbcUtil.rollback(con);
						
					}					
					
					JdbcUtil.close(con);					
					
					return isWriteSuccess; 
				}
				
			}