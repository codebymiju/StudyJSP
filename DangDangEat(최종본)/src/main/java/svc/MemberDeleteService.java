package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;

public class MemberDeleteService {

	public boolean deleteMember(String id) {
		System.out.println("MemberDeleteService - deleteMember()");
		
		boolean isDeleteSuccess = false;
		
		// 공통작업
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		// MemberDAO 의 deleteMember() 호출 - 회원 삭제 작업
		int deleteCount = dao.deleteMember(id);
		
		if(deleteCount > 0) { // 삭제 성공 시
			isDeleteSuccess = true;
			
			JdbcUtil.commit(con); // 커밋
		} else { // 삭제 실패 시
			JdbcUtil.rollback(con); // 롤백
		}
		
		JdbcUtil.close(con);
		
		return isDeleteSuccess;
	}

}
