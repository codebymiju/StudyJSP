package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;
import vo.MemberBean;

public class MemberSelectService {

	public MemberBean selectMember(String id) {
		System.out.println("MemberSelectService - MemberBean");
		
		MemberBean member = null;
		
		// 공통작업-1. Connection 객체 가져오기
		Connection con = JdbcUtil.getConnection();
		
		// 공통작업-2. BoardDAO 객체 가져오기
		MemberDAO dao = MemberDAO.getInstance();
		
		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달하기
		dao.setConnection(con);
		
		// MemberDAO 객체의 selectMember() 메서드
		// => 파라미터 : 아이디   리턴타입 : MemberBean(member)
		member = dao.selectMember(id);
		
		JdbcUtil.commit(con);
		
		// 공통작업-4. Connection 객체 반환하기
		JdbcUtil.close(con);
		
		return member;
	}

}
