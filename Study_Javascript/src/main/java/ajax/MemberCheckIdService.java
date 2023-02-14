package ajax;

import java.sql.Connection;

public class MemberCheckIdService {

	public boolean isExistId(String id) {
		boolean isExist = false;
		
		// 공통작업-1. Connection 객체 가져오기
		Connection con = JdbcUtil.getConnection();
		
		// 공통작업-2. MemberDAO 객체 가져오기
		MemberDAO dao = MemberDAO.getInstance();
		
		// 공통작업-3. MemberDAO 객체에 Connection 객체 전달하기
		dao.setConnection(con);
		
		// MemberDAO 객체의 selectMemberId() 메서드를 호출하여 아이디 조회 작업 요청
		// => 파라미터 : 아이디   리턴타입 : boolean(isExist)
		isExist = dao.selectMemberId(id);

		// 공통작업-4. Connection 객체 반환하기
		JdbcUtil.close(con);
		
		return isExist;
	}

}
