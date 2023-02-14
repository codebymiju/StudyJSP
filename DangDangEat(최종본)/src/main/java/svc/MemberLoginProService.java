package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;
import vo.MemberBean;

public class MemberLoginProService {

	public boolean loginMember(MemberBean member, boolean isPass) {
		System.out.println("MemberLoginProService - loginMember()");
		
		boolean isLoginSuccess = false;
		
		// 공통작업 - Connection 객체, MemberDAO 객체, connection 객체 전달
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		// MemberDAO 객체의 selectMember() 메서드 호출
		// => 파라미터 : MemberBean 객체   리턴타입 : boolean(isLoginSuccess)
		isLoginSuccess = dao.selectMember(member, isPass);
		
		JdbcUtil.commit(con);
		
		// 공통작업 - Connection 객체 반환
		JdbcUtil.close(con);
		
		return isLoginSuccess;
	}

}
