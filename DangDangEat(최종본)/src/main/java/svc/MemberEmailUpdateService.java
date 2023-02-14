package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;
import vo.MemberBean;

public class MemberEmailUpdateService {

	public boolean memberEmailUpdate(MemberBean member) {
		System.out.println("MemberEmailUpdateService - memberEmailUpdate()");
		
		boolean isUpdateSuccess = false;
		
		// 공통작업
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		// MemberDAO 의 memberEmailUpdate() 메서드 - 회원 이메일, 인증 상태 업데이트
		int updateCount = dao.memberEmailUpdate(member);
		
		if(updateCount > 0) { // 업데이트 성공 시
			JdbcUtil.commit(con);
			isUpdateSuccess = true;
		} else { // 실패 시
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return isUpdateSuccess;
	}

}
