package svc;

import java.sql.Connection;
import java.util.List;

import dao.MemberDAO;
import db.JdbcUtil;
import vo.MemberBean;

public class MemberListService {

	public List<MemberBean> getMemberList() {
		System.out.println("MemberListService - getMemberList()");
		
		List<MemberBean> memberList = null;
		
		Connection con = JdbcUtil.getConnection();
		
		MemberDAO dao = MemberDAO.getInstance();
		
		dao.setConnection(con);
		
		memberList = dao.selectMemberList();
		
		JdbcUtil.commit(con);
		
		JdbcUtil.close(con);
		
		return memberList;
	}

}
