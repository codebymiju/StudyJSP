package svc;

import java.sql.Connection;

import dao.CouponDAO;
import db.JdbcUtil;

public class CouponCountService {

	public int SelectCouponCount() {
		//관리자 쿠폰리스트 페이지에 보여질 쿠폰 개수 조회
		int count = 0;
		Connection con = JdbcUtil.getConnection();
		CouponDAO dao = CouponDAO.getInstance();
		dao.setConnection(con);
		count = dao.selectCouponCount();
		
		JdbcUtil.commit(con);
		return count;
		
	}

}
