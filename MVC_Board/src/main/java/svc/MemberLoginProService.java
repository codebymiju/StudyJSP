package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;
import vo.MemberBean;

public class MemberLoginProService {
	// 로그인 정보 = db회원 정보와 일치하는지 확인
	public int loginMember(MemberBean member) {
		int loginResult = 0;
		
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		// 12/12 수정
		// 조회만 할 것이라 트랜잭션 필요 없음!
		loginResult = dao.selectMember(member);
			
		JdbcUtil.close(con);
		
		return loginResult;
	}
	
}
