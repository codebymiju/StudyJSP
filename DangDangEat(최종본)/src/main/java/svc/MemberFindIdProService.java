package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;
import vo.MemberBean;

public class MemberFindIdProService {

	public MemberBean findMemberId(MemberBean unfindedMember) {
		System.out.println("MemberFindIdProService - findMemberId()");
		
		MemberBean findMember = null;
		
		// 공통작업
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		// MemberDAO - findMemberId()
		findMember = dao.findMemberId(unfindedMember);
		
		JdbcUtil.commit(con);
		
		JdbcUtil.close(con);
		
		return findMember;
	}

	
	
}
