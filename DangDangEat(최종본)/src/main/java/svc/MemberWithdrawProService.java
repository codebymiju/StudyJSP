package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;
import vo.MemberBean;

public class MemberWithdrawProService {

	public boolean withdrawMember(MemberBean member) {
		System.out.println("MemberWithdrawProService - withdrawMember()");
		
		boolean isWithdrawSuccess = false;
		
		// 공통작업 - Connection 객체, MemberDAO 객체, connection 객체 전달
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		// MemberDAO 객체의 withdrawMember() 메서드 호출
		// => 파라미터 : MemberBean 객체   리턴타입 : boolean(isWithdrawSuccess)
		int updateCount = dao.withdrawMember(member);
		
		// 회원 정보 수정 결과 판별 -> 성공 시 commit, 실패 시 rollback
		if(updateCount > 0) {
			JdbcUtil.commit(con);
			isWithdrawSuccess = true;
		} else {
			JdbcUtil.rollback(con);;
		}
		
		// 공통작업 - Connection 객체 반환
		JdbcUtil.close(con);
		
		return isWithdrawSuccess;
		
	}

}
