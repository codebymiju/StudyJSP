package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;
import vo.MemberBean;


public class MemberIdCheckService {

	public boolean isCheckId(MemberBean member) {
		System.out.println("MemberIdCheckService - isCheckId");
		boolean isCheck = false;
		
		Connection con = JdbcUtil.getConnection();
		
		MemberDAO dao = MemberDAO.getInstance();
		
		dao.setConnection(con);
		
		isCheck = dao.selectMemberId(member);
		
		JdbcUtil.commit(con);
		
		JdbcUtil.close(con);
		
		
		return isCheck;
	}

}
