package svc;

import java.sql.Connection;

import dao.CouponDAO;
import db.JdbcUtil;

public class CouponTargetInsertService {

	public int CouponTargetInsert(String cp_code) {
		int count = 0;
		// 공통작업-1. Connection 객체 가져오기
		Connection con = JdbcUtil.getConnection();
		
		// 공통작업-2. BoardDAO 객체 가져오기
		CouponDAO dao = CouponDAO.getInstance();
		
		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달하기
		dao.setConnection(con);
		
		count = dao.insetCouponAll(cp_code);
		if(count > 0 ) {
			JdbcUtil.commit(con);
		}else {
			JdbcUtil.rollback(con);
		}
		// 공통작업-4. Connection 객체 반환하기
		JdbcUtil.close(con);
		
		return count;
	}
	
}
